package com.enhancesys.integration.services.dataengine.job.transformer.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobProcessor;
import com.enhancesys.integration.services.dataengine.job.components.DataTransformer;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoConnectionUtil;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoDataUtil;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoTemplate;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.condition.ExpressionProcessor;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.mathematical.MathematicalProcessor;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class GenericDataTransformer extends JobProcessor implements DataTransformer
{

	@Autowired
	MongoConnectionUtil mongoConnectionUtil;

	@Autowired 
	private ApplicationContext applicationContext;

	@Autowired
	ExpressionProcessor exProcessor;

	@Autowired
	MathematicalProcessor mathProcessor;

	@Autowired
	MongoDataUtil mongoDataUtil;

	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private Map<String, DBObject> lookupValuesMap = null;
	private JSONObject configurationObj = null;
	private JSONObject lookupObj = null;
	private JSONObject multipleRowConf = null;
	private DBCollection collection = null;
	private DateFormat defaultDateFormat = null;
	private DateFormat defaultDateTimeFormat = null;
	private static Logger LOGGER = Logger.getLogger(GenericDataTransformer.class);

	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		LOGGER.info("Entry init..");
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfiguration, jobParameters);
			if(processorConfig.get("job_count") != null && !processorConfig.get("job_count").toString().trim().isEmpty())
				this.jobCount = (Long) processorConfig.get("job_count");
			else
				this.jobCount = -1;

			if(processorConfig.get("sleep_time") != null && !processorConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) processorConfig.get("sleep_time");
			else
				this.sleepTime = 0;

			if(processorConfig.get("delay_count") != null && !processorConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) processorConfig.get("delay_count");
			else
				this.delayCount = 0;

			if(_jobConfigData_.containsKey(DataConstants.CONFIGURATION))
				configurationObj = (JSONObject) _jobConfigData_.get(DataConstants.CONFIGURATION);

			if(_jobConfigData_.containsKey(DataConstants.LOOKUP))
				lookupObj = (JSONObject) _jobConfigData_.get(DataConstants.LOOKUP);

			if(_jobConfigData_.containsKey(DataConstants.MULTIPLE_ROW_CONF))
				multipleRowConf = (JSONObject) _jobConfigData_.get(DataConstants.MULTIPLE_ROW_CONF);

			defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:SS.S");
		}
		catch (Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		LOGGER.info("Exit init..");
	}

	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		LOGGER.debug("Entry process :: " + _pipeLineName_ + " : " + _processorName_ + " : "  + " jobCount : " + jobCount  + " : DataObject : " + jobObject);
		JSONObject fieldObject = null;
		JSONObject dataObject = null;
		JSONObject tempObject = null;
		JSONObject fieldLookupObject = null;
		JSONArray dataList = null;
		JSONArray newDataList = null;
		JSONArray expressionFields = null;
		DBObject lookupDBObject = null;
		Object configurationField = null;
		Object fieldValue = null;
		String lookupMapKey = null;
		List<Object> values = null; 
		try
		{
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
					jobObject.put("status", "Success");
				else
					jobObject.put("status", "Completed");
			}

			if(lookupObj != null)
			{
				if(lookupValuesMap == null)
					lookupValuesMap = new HashMap<String, DBObject>();
				dataList = (JSONArray) ((JSONObject)jobObject.get(DataConstants.PAYLOAD)).remove(DataConstants.DATA_LIST);
				newDataList = new JSONArray();
				for(Object dataObj : dataList)
				{
					expressionFields = new JSONArray();
					dataObject = (JSONObject) dataObj;
					tempObject = new JSONObject();
					fieldLevel : for(Object configurationFieldKey : configurationObj.keySet())
					{
						JSONArray fieldArr = null;
						fieldObject = (JSONObject) configurationObj.get(configurationFieldKey);
						if(fieldObject.get(DataConstants.FIELD) instanceof JSONArray)
							fieldArr = (JSONArray) fieldObject.get(DataConstants.FIELD);
						else
							configurationField = fieldObject.get(DataConstants.FIELD);

						if(configurationField != null)
						{
							if(configurationField.toString().contains("."))
							{
								values = Utilities.getFieldValue(configurationField.toString(), dataObject);
								if(values != null && !values.isEmpty())
									fieldValue = values.iterator().next();   
							}
							else 
								fieldValue = dataObject.get(configurationField.toString()) != null ? dataObject.get(configurationField.toString()) : "";	
						}

						//						log.info(field + " : fieldValue : " + fieldValue + " : fieldObject : " + fieldObject);
						//						log.info("Test : " + field.toString() + " : fieldValue : " + fieldValue + " : " + dataObject.get(field.toString()));
						if(fieldArr != null || (fieldValue != null && !fieldValue.toString().isEmpty()))
						{
							if(fieldObject.containsKey(DataConstants.F_LOOKUP))
							{
								JSONArray mappingArr = null;
								for(Object fieldLookupObj : (JSONArray) fieldObject.get(DataConstants.F_LOOKUP))
								{
									fieldLookupObject = (JSONObject) fieldLookupObj;
									mappingArr = null;
									if(fieldLookupObject.get(DataConstants.MAPPING_FIELD) instanceof JSONArray)
										mappingArr = (JSONArray) fieldLookupObject.get(DataConstants.MAPPING_FIELD);
									if(fieldArr != null)
									{
										if(mappingArr != null)
										{
											LOGGER.debug("inside Mapping arr : " + mappingArr);
											lookupDBObject = getLookupObject((JSONObject) lookupObj.get(fieldLookupObject.get(DataConstants.NAME)), fieldLookupObject, dataObject, fieldArr);
										}
										else if(fieldValue != null)
										{
											lookupMapKey = fieldLookupObject.get(DataConstants.NAME).toString() + "~" + configurationField.toString() + "~" + fieldLookupObject.get(DataConstants.MAPPING_FIELD).toString() + "~" + fieldValue;
											lookupDBObject = getLookupObject((JSONObject) lookupObj.get(fieldLookupObject.get(DataConstants.NAME)), fieldLookupObject, lookupValuesMap, lookupMapKey, dataObject, fieldValue);
											//											log.info(fieldKey + " : " + fieldLookupObject.get(DataConstants.LOOKUP_FIELD).toString() + " : LookupDBObject : " + lookupDBObject);
										}
									}
									else
									{
										lookupMapKey = fieldLookupObject.get(DataConstants.NAME).toString() + "~" + configurationField.toString() + "~" + fieldLookupObject.get(DataConstants.MAPPING_FIELD).toString() + "~" + fieldValue;
										lookupDBObject = getLookupObject((JSONObject) lookupObj.get(fieldLookupObject.get(DataConstants.NAME)), fieldLookupObject, lookupValuesMap, lookupMapKey, dataObject, fieldValue);
										//										log.info(fieldKey + " : " + fieldLookupObject.get(DataConstants.LOOKUP_FIELD).toString() + " : LookupDBObject : " + lookupDBObject);
									}
									//									log.info(fieldKey + " : " + fieldLookupObject.get(DataConstants.LOOKUP_FIELD).toString() + " : LookupDBObject : " + lookupDBObject);
									if(lookupDBObject != null)
									{
										if(fieldLookupObject.get(DataConstants.LOOKUP_FIELD) instanceof JSONArray)
										{
											fieldValue = "";
											String tempVal = null;
											for(Object lkpField : (JSONArray) fieldLookupObject.get(DataConstants.LOOKUP_FIELD))
											{
												tempVal = lookupDBObject.get(lkpField.toString()) != null ? lookupDBObject.get(lkpField.toString()).toString() : "";
												if(!tempVal.trim().isEmpty())
													fieldValue = fieldValue + tempVal + fieldLookupObject.get(DataConstants.FIELD_DELIMITER).toString();
											}
											tempVal = null;
											if(fieldValue != null)
											{
												fieldValue = fieldValue.toString().trim();
												if(fieldValue.toString().endsWith(fieldLookupObject.get(DataConstants.FIELD_DELIMITER).toString()))
													fieldValue = fieldValue.toString().substring(0, fieldValue.toString().lastIndexOf(fieldLookupObject.get(DataConstants.FIELD_DELIMITER).toString()));
											}
										}
										else
											fieldValue = lookupDBObject.get(fieldLookupObject.get(DataConstants.LOOKUP_FIELD).toString());
									}
									else
										fieldValue = "";
									//									log.info("FieldValue : " + fieldValue);
									if(fieldValue == null || fieldValue.toString().isEmpty())
									{
										fieldValue = validateData("", dataObject, fieldObject);
										tempObject.put(configurationFieldKey.toString(), fieldValue);
										continue fieldLevel;
									}

									if(fieldLookupObject.get(DataConstants.MATCH_VALUE) != null && !fieldLookupObject.get(DataConstants.MATCH_VALUE).toString().trim().isEmpty())
									{
										if(!fieldLookupObject.get(DataConstants.MATCH_VALUE).toString().equals(fieldValue.toString()))
										{
											fieldValue = validateData("", dataObject, fieldObject);
											tempObject.put(configurationFieldKey.toString(), fieldValue);
											continue fieldLevel;
										}

										if(fieldLookupObject.get(DataConstants.LOOKUP_FIELD_ON_MATCH) != null)
										{
											fieldValue = lookupDBObject.get(fieldLookupObject.get(DataConstants.LOOKUP_FIELD_ON_MATCH).toString());
										}
									}
								}
								//								log.info("FieldValue : " + fieldValue);
								fieldValue = validateData(fieldValue, dataObject, fieldObject);
								//								log.info("FieldValue : " + fieldValue);
								tempObject.put(configurationFieldKey.toString(), fieldValue);
							}
							else if(fieldObject.containsKey(DataConstants.QUERY_FIELDS))
							{
								processQueryFields(fieldObject, dataObject, tempObject, configurationFieldKey);
							}
							else if(fieldObject.containsKey(DataConstants.EXPRESSION) && fieldObject.get(DataConstants.EXPRESSION) != null && !fieldObject.get(DataConstants.EXPRESSION).toString().trim().isEmpty())
							{
								fieldObject.put("field-key", configurationFieldKey.toString());
								expressionFields.add(fieldObject);
							}
							else 
							{
								fieldValue = validateData(fieldValue, dataObject, fieldObject);
								tempObject.put(configurationFieldKey.toString(), fieldValue);
							}
						}
						else
						{
							if(fieldObject.get(DataConstants.EXPRESSION) != null && !fieldObject.get(DataConstants.EXPRESSION).toString().trim().isEmpty())
							{
								fieldObject.put("field-key", configurationFieldKey.toString());
								expressionFields.add(fieldObject);
							}
							else
							{
								fieldValue = validateData("", dataObject, fieldObject);
								tempObject.put(configurationFieldKey.toString(), fieldValue);
							}
						}
						fieldObject = null;
						fieldValue = null;
					}

					for(Object exFieldObject : expressionFields)
					{
						fieldObject = (JSONObject) exFieldObject;
						LOGGER.debug("Process Expression.." + fieldObject);
						fieldValue = executeExpression(tempObject, fieldObject);
						fieldValue = validateData(fieldValue, dataObject, fieldObject);
						tempObject.put(fieldObject.get("field-key"), fieldValue);
					}
					expressionFields.clear();
					expressionFields = null;

					if(multipleRowConf != null)
						newDataList.addAll(processMultipleRowConf(tempObject, dataObject));
					else
						newDataList.add(tempObject);
				}
				((JSONObject)jobObject.get(DataConstants.PAYLOAD)).put(DataConstants.DATA_LIST, newDataList);
			}
			else
			{
				dataList = (JSONArray) ((JSONObject)jobObject.get(DataConstants.PAYLOAD)).remove(DataConstants.DATA_LIST);
				newDataList = new JSONArray();
				for(Object dataObj : dataList)
				{
					expressionFields = new JSONArray();
					dataObject = (JSONObject) dataObj;
					tempObject = new JSONObject();
					LOGGER.debug("Data Object : " + dataObject);
					for(Object fieldKey : configurationObj.keySet())
					{
						fieldObject = (JSONObject) configurationObj.get(fieldKey);
						configurationField = fieldObject.get(DataConstants.FIELD);
						LOGGER.debug("Field : " + configurationField);
						if(configurationField.toString().contains("."))
						{	
							values = Utilities.getFieldValue(configurationField.toString(), dataObject);
							if(values != null && !values.isEmpty())
								fieldValue = values.iterator().next();  
						}
						else 
							fieldValue = dataObject.get(configurationField.toString()) != null ? dataObject.get(configurationField.toString()) : "";
							/*if(fieldValue != null && !fieldValue.toString().isEmpty())
							tempObject.put(fieldKey.toString(), fieldValue);
						else
							tempObject.put(fieldKey.toString(), "");*/

							if(fieldValue != null && !fieldValue.toString().isEmpty())
							{
								if(fieldObject.containsKey(DataConstants.QUERY_FIELDS))
								{
									processQueryFields(fieldObject, dataObject, tempObject, fieldKey);
								}
							}

							if(fieldObject.get(DataConstants.EXPRESSION) != null && !fieldObject.get(DataConstants.EXPRESSION).toString().trim().isEmpty())
							{
								fieldObject.put("field-key", fieldKey.toString());
								expressionFields.add(fieldObject);
							}
							else
							{
								fieldValue = validateData(fieldValue, dataObject, fieldObject);
								tempObject.put(fieldKey.toString(), fieldValue);
							}
					}

					for(Object exFieldObject : expressionFields)
					{
						fieldObject = (JSONObject) exFieldObject;
						LOGGER.debug("Process Expression.." + fieldObject);
						fieldValue = executeExpression(tempObject, fieldObject);
						fieldValue = validateData(fieldValue, dataObject, fieldObject);
						tempObject.put(fieldObject.get("field-key"), fieldValue);
					}
					expressionFields.clear();
					expressionFields = null;

					if(multipleRowConf != null && !multipleRowConf.keySet().isEmpty())
						newDataList.addAll(processMultipleRowConf(tempObject, dataObject));
					else
						newDataList.add(tempObject);
				}

				((JSONObject)jobObject.get(DataConstants.PAYLOAD)).put(DataConstants.DATA_LIST, newDataList);
			}


			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
			try
			{
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e) 
			{
				LOGGER.error("Exception Occured : " + e.getMessage());
				throw new GenericProcessorException("Exception Occured : " + e.getMessage());
			}
		}
		catch(Exception exception)
		{
			try 
			{
				if(_requestId_ != null && _requestUpdateConf_ != null)
					mongoDataUtil.updateStatus(_requestId_, _requestUpdateConf_, DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				LOGGER.error("Unhandled Exception : " + excep.getMessage(), excep);
			}

			jobObject.put("status", "Failure");
			LOGGER.error(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			LOGGER.debug("Exit process :: "+ _pipeLineName_ + " : " + _processorName_ + " : DataObject : " + jobObject);
			fieldObject = null;
			dataObject = null;
			tempObject = null;
			fieldLookupObject = null;
			dataList = null;
			newDataList = null;
			expressionFields = null;
			lookupDBObject = null;
			configurationField = null;
			fieldValue = null;
			lookupMapKey = null;
			values = null;
			LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : DataObject : " + jobObject);
		}
		return false;
	}

	private DBObject getLookupObject(JSONObject lookupObject, JSONObject fieldLookupObject, Map<String, DBObject> lookupMap, String lookupMapKey, JSONObject dataObject, Object value) throws GenericProcessorException
	{
		DBObject lookupDBObject = null;
		BasicDBObject queryObject = null;
		JSONObject queryConf = null;
		Long cacheSize = null;
		MongoTemplate template = null;
		DBCursor cursor = null;
		String fieldValue = null;
		List<Object> values = null;

		try
		{
			if(lookupObject.get(DataConstants.QUERY_CONF) != null)
			{
				queryConf = (JSONObject) lookupObject.get(DataConstants.QUERY_CONF);
				//				log.error("queryConf : " + queryConf);
				if(queryConf.get(DataConstants.PARAM_TYPE) != null)
				{
					if(queryConf.get(DataConstants.PARAM_VALUE).toString().contains("."))
					{
						values = Utilities.getFieldValue(queryConf.get(DataConstants.PARAM_VALUE).toString(), dataObject);
						if(values != null && !values.isEmpty())
							fieldValue = values.iterator().next().toString();   
					}
					else 
					{
						fieldValue = dataObject.get(queryConf.get(DataConstants.PARAM_VALUE).toString()) != null ? dataObject.get(queryConf.get(DataConstants.PARAM_VALUE).toString()).toString() : "";
					}
					lookupMapKey = lookupMapKey + "~" + fieldValue;
				}
			}

			//			log.error(lookupMapKey + " : lookupMap.containsKey(lookupMapKey) : " + lookupMap.containsKey(lookupMapKey));
			//			log.error("dataObject : " + dataObject);
			if(lookupMap.containsKey(lookupMapKey))
				lookupDBObject = lookupMap.get(lookupMapKey);
			else
			{
				//				log.error("inside db lookup..");
				cacheSize = Long.parseLong(DataConstants.LOOKUP_CACHE_SIZE);
				queryObject = new BasicDBObject();
				//				log.error("lookupObject : " + lookupObject);
				if(fieldLookupObject.get(DataConstants.QUERY) != null)
					queryObject = (BasicDBObject) JSON.parse(fieldLookupObject.get(DataConstants.QUERY).toString());
				else if(lookupObject.get(DataConstants.QUERY_CONF) != null)
					queryObject = Utilities.prepareQuery(lookupObject, dataObject);
				if(lookupObject.get(DataConstants.PARAMETERS) != null)
					Utilities.prepareQueryWithParams(lookupObject, queryObject, dataObject);
				if(fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE) != null && fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE).toString().equalsIgnoreCase(DataConstants.LONG_TYPE))
					queryObject.put(fieldLookupObject.get(DataConstants.MAPPING_FIELD).toString(), Long.parseLong(value.toString()));
				else
					queryObject.put(fieldLookupObject.get(DataConstants.MAPPING_FIELD).toString(), value);

				/*if(collection == null)
				{
					log.info("lookupObject : " + lookupObject + " : applicationContext : " + applicationContext + " : mongoConnectionUtil : " + mongoConnectionUtil);

					collection = template.getCollection(lookupObject.get(DataConstants.SCHEMA_NAME).toString(), lookupObject.get(DataConstants.COLLECTION_NAME).toString());
				}*/
				template = mongoConnectionUtil.getConnection(applicationContext, lookupObject.get(DataConstants.CONNECTION_ID).toString());
				collection = template.getCollection(lookupObject.get(DataConstants.SCHEMA_NAME).toString(), lookupObject.get(DataConstants.COLLECTION_NAME).toString());
				//				log.info(collection + "Lookup query : " + queryObject);
				//				log.error(collection + "Lookup query : " + queryObject);
				if(lookupObject.get(DataConstants.SORT_BY) != null && !lookupObject.get(DataConstants.SORT_BY).toString().trim().isEmpty())
				{
					cursor = collection.find(queryObject).sort((DBObject)JSON.parse(lookupObject.get(DataConstants.SORT_BY).toString()));
					if(cursor.hasNext())
						lookupDBObject = cursor.next();
					cursor.close();
				}
				else 
					lookupDBObject = collection.findOne(queryObject);
				/*if(lookupObject.get(DataConstants.CACHE_REQUIRED) != null && !lookupObject.get(DataConstants.CACHE_REQUIRED).toString().trim().isEmpty() 
						&& "True".equalsIgnoreCase(lookupObject.get(DataConstants.CACHE_REQUIRED).toString().trim()))*/
				if(lookupDBObject != null)
				{
					if(lookupMap.size() >= cacheSize)
						lookupMap.remove(lookupMap.keySet().iterator().next());
					lookupMap.put(lookupMapKey, lookupDBObject);
				}
			}

			//			log.error("lookupDBObject from DB : " + lookupDBObject);
			return lookupDBObject;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			lookupDBObject = null;
			queryConf = null;
			queryObject = null;
			cacheSize = null;
			template = null;
			cursor = null;
			fieldValue = null;
			values = null;
		}
	}

	private DBObject getLookupObject(JSONObject lookupObject, JSONObject fieldLookupObject, JSONObject dataObject, JSONArray fieldArr) throws GenericProcessorException
	{
		DBObject lookupDBObject = null;
		BasicDBObject queryObject = null;
		MongoTemplate template = null;
		JSONArray mappingArr = null;
		DBCursor cursor = null;

		try
		{
			if(fieldLookupObject.get(DataConstants.MAPPING_FIELD) instanceof JSONArray)
				mappingArr = (JSONArray) fieldLookupObject.get(DataConstants.MAPPING_FIELD);

			LOGGER.debug("mappingArr : " + mappingArr + " : fieldArr : " + fieldArr);
			if(mappingArr.size() != fieldArr.size())
				return null;

			queryObject = new BasicDBObject();
			if(fieldLookupObject.get(DataConstants.QUERY) != null)
				queryObject = (BasicDBObject) JSON.parse(fieldLookupObject.get(DataConstants.QUERY).toString());
			else if(fieldLookupObject.get(DataConstants.QUERY_CONF) != null)
				queryObject = Utilities.prepareQuery(lookupObject, dataObject);
			if(lookupObject.get(DataConstants.PARAMETERS) != null)
				Utilities.prepareQueryWithParams(lookupObject, queryObject, dataObject);
			if(fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE) != null && fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE).toString().equalsIgnoreCase(DataConstants.LONG_TYPE))
			{
				for(int i = 0; i < mappingArr.size(); i ++)
				{
					queryObject.put(mappingArr.get(i).toString(), Long.parseLong(dataObject.get(fieldArr.get(i).toString()).toString()));
				}
			}
			else
			{
				for(int i = 0; i < mappingArr.size(); i ++)
				{
					queryObject.put(mappingArr.get(i).toString(), dataObject.get(fieldArr.get(i).toString()));
				}
			}

			template = mongoConnectionUtil.getConnection(applicationContext, lookupObject.get(DataConstants.CONNECTION_ID).toString());
			collection = template.getCollection(lookupObject.get(DataConstants.SCHEMA_NAME).toString(), lookupObject.get(DataConstants.COLLECTION_NAME).toString());
			//				log.info(collection + "Lookup query : " + queryObject);
			if(lookupObject.get(DataConstants.SORT_BY) != null && !lookupObject.get(DataConstants.SORT_BY).toString().trim().isEmpty())
			{
				cursor = collection.find(queryObject).sort((DBObject)JSON.parse(lookupObject.get(DataConstants.SORT_BY).toString()));
				if(cursor.hasNext())
					lookupDBObject = cursor.next();
				cursor.close();
			}
			else 
				lookupDBObject = collection.findOne(queryObject);
			//			log.error("lookupDBObject from DB : " + lookupDBObject);
			return lookupDBObject;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			lookupDBObject = null;
			queryObject = null;
			template = null;
			mappingArr = null;
			cursor = null;
		}
	}

	private Object validateData(Object fieldValue, JSONObject dataObject, JSONObject fieldObject) throws GenericProcessorException
	{
		JSONObject fieldCondObject = null;
		JSONObject resultCondObject = null;
		JSONObject fetchObject = null;
		JSONObject tempObject = null;
		JSONArray condFieldArr = null;
		JSONArray condValueArr = null;
		Object tempValue = null;
		DateFormat format = null;
		String conditionFieldValue = null;
		List<Object> values = null;
		String val_1 = null;
		String val_2 = null;
		String value = null;
		String delimiter = null;
		Object arrField = null;

		try
		{
			//			log.info(fieldObject +  " : fieldValue : " + fieldValue);
			if(fieldValue != null && !fieldValue.toString().trim().isEmpty())
			{
				if(fieldObject.containsKey(DataConstants.FETCH_ON_CONDITION))
				{
					if(fieldValue instanceof JSONObject)
					{
						fetchObject = (JSONObject) fieldValue;
						LOGGER.debug("fetchObject : " + fetchObject);
						boolean condFlag = false;
						for(Object fieldCondObj : (JSONArray) fieldObject.get(DataConstants.FETCH_ON_CONDITION))
						{
							fieldCondObject = (JSONObject) fieldCondObj;
							LOGGER.debug("fieldCondObject : " + fieldCondObject);
							if(fieldCondObject.get(DataConstants.COND_OPERATION) != null)
							{
								if(fieldCondObject.get(DataConstants.COND_FIELD).toString().contains("."))
								{
									values = Utilities.getFieldValue(fieldCondObject.get(DataConstants.COND_FIELD).toString(), fetchObject);
									if(values != null && !values.isEmpty())
										conditionFieldValue = values.iterator().next().toString();
								}
								else
									conditionFieldValue = fetchObject.get(fieldCondObject.get(DataConstants.COND_FIELD)) != null ? fetchObject.get(fieldCondObject.get(DataConstants.COND_FIELD)).toString() : "";

									if(conditionFieldValue == null || conditionFieldValue.trim().isEmpty())
										tempValue = "";
									else
									{
										if(fieldCondObject.get(DataConstants.COND_VALUE) != null)
										{
											if(DataConstants.EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
											{
												if(fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
													condFlag = true;
												else
													condFlag = false;
											}
											else if(DataConstants.NOT_EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
											{
												if(!fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
													condFlag = true;
												else
													condFlag = false;
											}
											else if(DataConstants.IN.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
											{
												if(fieldCondObject.get(DataConstants.COND_VALUE) instanceof JSONArray)
												{
													condValueArr = (JSONArray) fieldCondObject.get(DataConstants.COND_VALUE);
													boolean flag = false;
													condValueLabel : for(Object condValue : condValueArr)
													{
														if(condValue.toString().equals(conditionFieldValue))
														{
															flag = true;
															break condValueLabel;
														}
													}
													if(flag)
														condFlag = true;
													else 
														condFlag = false;
												}
											}
										}
									}
							}
							else
							{
								if(fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
									condFlag = true;
								else
									condFlag = false;
							}
						}
						if(condFlag)
							tempValue = fetchObject.get(fieldObject.get(DataConstants.FETCH_FIELD).toString()) != null ? fetchObject.get(fieldObject.get(DataConstants.FETCH_FIELD).toString()) : "";
							else
								tempValue = "";
					}
					else if(fieldValue instanceof JSONArray)
					{
						dataArr : for(Object obj : (JSONArray) fieldValue)
						{
							fetchObject = (JSONObject) obj;
							LOGGER.debug("fetchObject : " + fetchObject);
							boolean condFlag = false;
							for(Object fieldCondObj : (JSONArray) fieldObject.get(DataConstants.FETCH_ON_CONDITION))
							{
								fieldCondObject = (JSONObject) fieldCondObj;
								LOGGER.debug("fieldCondObject : " + fieldCondObject);
								if(fieldCondObject.get(DataConstants.COND_OPERATION) != null)
								{
									if(fieldCondObject.get(DataConstants.COND_FIELD).toString().contains("."))
									{
										values = Utilities.getFieldValue(fieldCondObject.get(DataConstants.COND_FIELD).toString(), fetchObject);
										if(values != null && !values.isEmpty())
											conditionFieldValue = values.iterator().next().toString();
									}
									else
										conditionFieldValue = fetchObject.get(fieldCondObject.get(DataConstants.COND_FIELD)) != null ? fetchObject.get(fieldCondObject.get(DataConstants.COND_FIELD)).toString() : "";

										if(conditionFieldValue == null || conditionFieldValue.trim().isEmpty())
											tempValue = "";
										else
										{
											if(fieldCondObject.get(DataConstants.COND_VALUE) != null)
											{
												if(DataConstants.EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
												{
													if(fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
														condFlag = true;
													else
														condFlag = false;
												}
												else if(DataConstants.NOT_EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
												{
													if(!fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
														condFlag = true;
													else
														condFlag = false;
												}
												else if(DataConstants.IN.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
												{
													if(fieldCondObject.get(DataConstants.COND_VALUE) instanceof JSONArray)
													{
														condValueArr = (JSONArray) fieldCondObject.get(DataConstants.COND_VALUE);
														boolean flag = false;
														condValueLabel : for(Object condValue : condValueArr)
														{
															if(condValue.toString().equals(conditionFieldValue))
															{
																flag = true;
																break condValueLabel;
															}
														}
														if(flag)
															condFlag = true;
														else 
															condFlag = false;
													}
												}
											}
										}
								}
								else
								{
									if(fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
										condFlag = true;
									else
										condFlag = false;
								}
							}
							LOGGER.debug("condFlag : " + condFlag);
							if(condFlag)
							{
								tempValue = fetchObject.get(fieldObject.get(DataConstants.FETCH_FIELD).toString()) != null ? fetchObject.get(fieldObject.get(DataConstants.FETCH_FIELD).toString()) : "";
								break dataArr;
							}
							else
								tempValue = "";
						}
					}
				}
				else if(fieldObject.containsKey(DataConstants.CONDITION) && !fieldObject.containsKey(DataConstants.EXPRESSION))
				{
					//					TLogger.debug("Condition Obj : " + fieldObject.get(DataConstants.CONDITION));
					for(Object fieldCondObj : (JSONArray) fieldObject.get(DataConstants.CONDITION))
					{
						fieldCondObject = (JSONObject) fieldCondObj;
						if(fieldCondObject.get(DataConstants.COND_FIELD) != null)
						{
							if(fieldCondObject.get(DataConstants.COND_FIELD) instanceof JSONArray)
							{
								if(fieldCondObject.get(DataConstants.COND_OPERATION) != null)
								{
									condFieldArr = (JSONArray) fieldCondObject.get(DataConstants.COND_FIELD);
									if(condFieldArr.size() > 1)
									{
										val_1 = "";
										val_2 = "";
										condFields : for(int i = 0; i < condFieldArr.size(); i ++)
										{
											if(i + 1 < condFieldArr.size())
											{
												if(condFieldArr.get(i).toString().contains("."))
												{
													values = Utilities.getFieldValue(condFieldArr.get(i).toString(), dataObject);
													if(values != null && !values.isEmpty())
														val_1 = values.iterator().next().toString();
												}
												else
													val_1 = dataObject.get(condFieldArr.get(i)) != null ? dataObject.get(condFieldArr.get(i)).toString().trim() : "";

													if(condFieldArr.get(i+1).toString().contains("."))
													{
														values = Utilities.getFieldValue(condFieldArr.get(i+1).toString(), dataObject);
														if(values != null && !values.isEmpty())
															val_2 = values.iterator().next().toString();
													}
													else 
														val_2 = dataObject.get(condFieldArr.get(i+1)) != null ? dataObject.get(condFieldArr.get(i+1)).toString().trim() : "";

														if(DataConstants.EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
														{
															if(!val_1.equals(val_2))
															{
																tempValue = "";
																break condFields;
															}
														}
														else if(DataConstants.NOT_EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
														{
															if(val_1.equals(val_2))
															{
																tempValue = "";
																break condFields;
															}
														}
														else if(DataConstants.IN.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
														{
															if(fieldCondObject.get(DataConstants.COND_VALUE) instanceof JSONArray)
															{
																condValueArr = (JSONArray) fieldCondObject.get(DataConstants.COND_VALUE);
																boolean flag = false;
																condValueLabel : for(Object condValue : condValueArr)
																{
																	//need to cross verify this logic..
																	if(condValue.toString().equals(dataObject.get(fieldCondObject.get(DataConstants.COND_FIELD)).toString())) 
																	{
																		flag = true;
																		break condValueLabel;
																	}
																}
																if(!flag)
																{
																	tempValue = "";
																}
															}
														}
											}
										}
									}
									else
									{
										LOGGER.error("Please configure more than 1 condition fields for field : " + fieldObject.get(DataConstants.FIELD));
										throw new GenericProcessorException("Please configure more than 1 condition fields for field : " + fieldObject.get(DataConstants.FIELD));
									}
								}
								else
								{
									condFieldArr = (JSONArray) fieldCondObject.get(DataConstants.COND_FIELD);
									if(condFieldArr.size() > 1)
									{
										val_1 = "";
										val_2 = "";
										condFields : for(int i = 0; i < condFieldArr.size(); i ++)
										{
											if(i + 1 < condFieldArr.size())
											{
												if(condFieldArr.get(i).toString().contains("."))
												{
													values = Utilities.getFieldValue(condFieldArr.get(i).toString(), dataObject);
													if(values != null && !values.isEmpty())
														val_1 = values.iterator().next().toString();
												}
												else
													val_1 = dataObject.get(condFieldArr.get(i)) != null ? dataObject.get(condFieldArr.get(i)).toString().trim() : "";

													if(condFieldArr.get(i+1).toString().contains("."))
													{
														values = Utilities.getFieldValue(condFieldArr.get(i+1).toString(), dataObject);
														if(values != null && !values.isEmpty())
															val_2 = values.iterator().next().toString();
													}
													else 
														val_2 = dataObject.get(condFieldArr.get(i+1)) != null ? dataObject.get(condFieldArr.get(i+1)).toString().trim() : "";

														if(!dataObject.get(condFieldArr.get(i)).toString().equals(dataObject.get(condFieldArr.get(i+1)).toString()))
														{
															tempValue = "";
															break condFields;
														}
											}
										}
									}
									else
									{
										LOGGER.error("Please configure more than 1 condition fields for field : " + fieldObject.get(DataConstants.FIELD));
										throw new GenericProcessorException("Please configure more than 1 condition fields for field : " + fieldObject.get(DataConstants.FIELD));
									}
								}
							}
							else
							{
								if(dataObject.get(fieldCondObject.get(DataConstants.COND_FIELD)) == null || dataObject.get(fieldCondObject.get(DataConstants.COND_FIELD)).toString().trim().isEmpty())
									tempValue = "";
								else
								{
									if(fieldCondObject.get(DataConstants.COND_FIELD).toString().contains("."))
									{
										values = Utilities.getFieldValue(fieldCondObject.get(DataConstants.COND_FIELD).toString(), dataObject);
										if(values != null && !values.isEmpty())
											conditionFieldValue = values.iterator().next().toString();
									}
									else
										conditionFieldValue = dataObject.get(fieldCondObject.get(DataConstants.COND_FIELD)) != null ? dataObject.get(fieldCondObject.get(DataConstants.COND_FIELD)).toString() : "";

										if(conditionFieldValue == null || conditionFieldValue.trim().isEmpty())
											tempValue = "";
										else
										{
											if(fieldCondObject.get(DataConstants.COND_VALUE) != null)
											{
												String result = "true";
												if(fieldCondObject.get(DataConstants.COND_OPERATION) != null)
												{
													if(DataConstants.EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
													{
														if(!fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
														{
															tempValue = "";
															result = "false";
														}
													}
													else if(DataConstants.NOT_EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
													{
														if(fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
														{
															tempValue = "";
															result = "false";
														}
													}
													else if(DataConstants.IN.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
													{
														if(fieldCondObject.get(DataConstants.COND_VALUE) instanceof JSONArray)
														{
															condValueArr = (JSONArray) fieldCondObject.get(DataConstants.COND_VALUE);
															boolean flag = false;
															condValueLabel : for(Object condValue : condValueArr)
															{
																if(condValue.toString().equals(conditionFieldValue))
																{
																	flag = true;
																	break condValueLabel;
																}
															}
															if(!flag)
															{
																tempValue = "";
															}
														}
													}
												}
												else if(!fieldCondObject.get(DataConstants.COND_VALUE).toString().equals(conditionFieldValue))
												{
													tempValue = "";	
													result = "false";
												}

												if(fieldCondObject.get(result) != null)
												{
													resultCondObject = (JSONObject) fieldCondObject.get(result);
													if(resultCondObject.get(DataConstants.COND_FIELD) != null && resultCondObject.get(DataConstants.COND_OPERATION) != null)
													{
														if(DataConstants.DATE_DURATION.equalsIgnoreCase(resultCondObject.get(DataConstants.COND_OPERATION).toString()))
															tempValue = calculateDuration(dataObject, (JSONArray) resultCondObject.get(DataConstants.COND_FIELD));
													}
												}
											}
										}
								}
							}
						}
						else if(fieldCondObject.get(DataConstants.COND_VALUE) != null)
						{
							if(fieldCondObject.get(DataConstants.COND_OPERATION) != null)
							{
								if(DataConstants.EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
								{
									if(!tempValue.toString().equals(fieldCondObject.get(DataConstants.COND_VALUE).toString()))
										tempValue = "";
								}
								else if(DataConstants.NOT_EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
								{
									if(tempValue.toString().equals(fieldCondObject.get(DataConstants.COND_VALUE).toString()))
										tempValue = "";
								}
							}
							else if(!tempValue.toString().equals(fieldCondObject.get(DataConstants.COND_VALUE).toString()))
								tempValue = "";
						}
					}
				}
				else
					tempValue = fieldValue;
			}
			else
				tempValue = "";

			if(fieldObject.containsKey(DataConstants.APPEND))
			{
				value = "";
				delimiter = fieldObject.get(DataConstants.FIELD_DELIMITER) != null ? fieldObject.get(DataConstants.FIELD_DELIMITER).toString() : "";
				condFieldArr = (JSONArray) fieldObject.get(DataConstants.APPEND);
				//				log.info("condFieldArr : " + condFieldArr);
				for(int i = 0; i < condFieldArr.size(); i ++)
				{
					arrField = condFieldArr.get(i);
					if(!arrField.toString().isEmpty())
					{
						if(arrField.toString().contains("."))
						{
							values = Utilities.getFieldValue(arrField.toString(), dataObject);
							if(values != null && !values.isEmpty())
							{
								value = value + values.iterator().next().toString();
								if(i != condFieldArr.size() - 1)
									value = value + delimiter;
							}
						}
						else
						{
							//							log.info(arrField.toString() + " : " + dataObject.get(arrField.toString()));
							value = value + (dataObject.get(arrField.toString()) != null ? dataObject.get(arrField.toString()).toString() : "");
							if(i != condFieldArr.size() - 1)
								value = value + delimiter;
						}
					}
				}

				//				log.info("value : " + value);
				if(value.trim().isEmpty())
					tempValue = "";
				else
					tempValue = value;
				//				log.info("tempValue : " + tempValue);
			}

			if(fieldObject.containsKey(DataConstants.PROPERTIES) && tempValue != null && !tempValue.toString().trim().isEmpty())
			{
				tempObject = (JSONObject) fieldObject.get(DataConstants.PROPERTIES);
				tempValue = tempObject.get(tempValue.toString()) != null ? tempObject.get(tempValue.toString()).toString() : tempValue; 
			}

			if(fieldObject.containsKey(DataConstants.DATE_FORMAT) && tempValue != null && !tempValue.toString().trim().isEmpty())
			{
				LOGGER.debug("date tempValue : " + tempValue + " Format : " + fieldObject.get(DataConstants.DATE_FORMAT).toString() + " : " + tempValue.getClass());
				format = new SimpleDateFormat(fieldObject.get(DataConstants.DATE_FORMAT).toString());
				if(tempValue instanceof Date)
				{
					if(fieldObject.get(DataConstants.ADD_TIME) != null && !fieldObject.get(DataConstants.ADD_TIME).toString().trim().isEmpty())
					{
						Calendar calendar = Calendar.getInstance();
						calendar.setTime((Date) tempValue);
						String time = fieldObject.get(DataConstants.ADD_TIME).toString().trim();
						String[] timeArr = time.split(":");
						if(time.startsWith("-"))
						{
							calendar.add(Calendar.HOUR, -(Integer.parseInt(timeArr[0])));
							calendar.add(Calendar.MINUTE, -(Integer.parseInt(timeArr[1])));
						}
						else
						{
							calendar.add(Calendar.HOUR, Integer.parseInt(timeArr[0]));
							calendar.add(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
						}
						tempValue = calendar.getTime();
					}
					tempValue = format.format(tempValue);
					LOGGER.debug("Date : " + tempValue);
				}
				else if(tempValue instanceof JSONObject && ((JSONObject)tempValue).containsKey("$date"))
				{
					JSONObject tempJSON = (JSONObject) tempValue;
					Date dateValue = null;
					if(fieldObject.get(DataConstants.TIME_FLAG) != null && !fieldObject.get(DataConstants.TIME_FLAG).toString().trim().isEmpty() 
							&& DataConstants.TRUE.equalsIgnoreCase(fieldObject.get(DataConstants.TIME_FLAG).toString().trim()))
						dateValue = defaultDateTimeFormat.parse(tempJSON.get("$date").toString());
					else
						dateValue = defaultDateFormat.parse(tempJSON.get("$date").toString());

					if(fieldObject.get(DataConstants.ADD_TIME) != null && !fieldObject.get(DataConstants.ADD_TIME).toString().trim().isEmpty())
					{
						Calendar calendar = Calendar.getInstance();
						calendar.setTime((Date) dateValue);
						String time = fieldObject.get(DataConstants.ADD_TIME).toString().trim();
						String[] timeArr = time.split(":");
						if(time.startsWith("-"))
						{
							calendar.add(Calendar.HOUR, -(Integer.parseInt(timeArr[0])));
							calendar.add(Calendar.MINUTE, -(Integer.parseInt(timeArr[1])));
						}
						else
						{
							calendar.add(Calendar.HOUR, Integer.parseInt(timeArr[0]));
							calendar.add(Calendar.MINUTE, Integer.parseInt(timeArr[1]));
						}
						dateValue = calendar.getTime();
					}

					tempValue = format.format(dateValue);
					dateValue = null;
					tempJSON = null;
				}
				else
					tempValue = "";
			}

			if(fieldObject.get(DataConstants.DEFAULT_VALUE) != null && (tempValue == null || tempValue.toString().trim().isEmpty()))
				tempValue = fieldObject.get(DataConstants.DEFAULT_VALUE).toString();

			LOGGER.debug("fieldObject : " + fieldObject + " : tempValue : " + tempValue);
			return tempValue;
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			fieldCondObject = null;
			resultCondObject = null;
			condFieldArr = null;
			condValueArr = null;
			tempValue = null;
			format = null;
			conditionFieldValue = null;
			values = null;
			val_1 = null;
			val_2 = null;
		}
	}

	@SuppressWarnings("unchecked")
	private void processQueryFields(JSONObject fieldObject, JSONObject dataObject, JSONObject tempObject, Object fieldKey) throws GenericProcessorException
	{
		JSONObject fieldLookupObject = null;
		List<Object> values = null;
		Object fieldValue = null;
		MongoTemplate template = null;
		DBCursor dataCursor = null;
		BasicDBObject projectionObject = null;
		BasicDBObject queryObject = null;

		try
		{
			queryObject = new BasicDBObject();
			for(Object queryFieldObj : (JSONArray) fieldObject.get(DataConstants.QUERY_FIELDS))
			{
				fieldLookupObject = (JSONObject) queryFieldObj;
				if(fieldLookupObject.get(DataConstants.PATH) != null)
				{
					//					log.error("fieldLookupObject.get(DataConstants.PATH) : " + fieldLookupObject.get(DataConstants.PATH) + " : Data Object : " + dataObject);
					values = Utilities.getFieldValue(fieldLookupObject.get(DataConstants.PATH).toString(), dataObject);
					if(values != null && !values.isEmpty())
					{
						fieldValue = values.iterator().next(); 
						//						log.error("fieldValue : " + fieldValue);
						if(fieldLookupObject.get(DataConstants.TYPE) != null)
						{
							if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldLookupObject.get(DataConstants.TYPE).toString().trim()))
								queryObject.put(fieldLookupObject.get(DataConstants.PARAM_NAME).toString(), Long.parseLong(fieldValue.toString()));
							else if(DataConstants.DATE_NUM.equalsIgnoreCase(fieldLookupObject.get(DataConstants.TYPE).toString().trim()))
							{
								DateFormat format = new SimpleDateFormat(fieldLookupObject.get(DataConstants.DATE_FORMAT).toString());
								if(fieldLookupObject.containsKey(DataConstants.OPERATION))
								{
									BasicDBObject operationObj = new BasicDBObject();
									operationObj.put(fieldLookupObject.get(DataConstants.OPERATION).toString(), Long.parseLong(format.format(fieldValue)));
									queryObject.put(fieldLookupObject.get(DataConstants.PARAM_NAME).toString(), operationObj);
									operationObj = null;
								}
								else
									queryObject.put(fieldLookupObject.get(DataConstants.PARAM_NAME).toString(), format.format(fieldValue));
							}
						}
						else
							queryObject.put(fieldLookupObject.get(DataConstants.PARAM_NAME).toString(), fieldValue);
					}
				}
				else
				{
					if(fieldLookupObject.get(DataConstants.TYPE) != null)
					{
						//						log.info("fieldLookupObject.get(DataConstants.OPERATION) 2: " + fieldLookupObject.get(DataConstants.OPERATION));
						if(DataConstants.DATE_NUM.equalsIgnoreCase(fieldLookupObject.get(DataConstants.TYPE).toString().trim()))
						{
							DateFormat format = new SimpleDateFormat(fieldLookupObject.get(DataConstants.DATE_FORMAT).toString());
							if(fieldLookupObject.containsKey(DataConstants.OPERATION))
							{
								BasicDBObject operationObj = new BasicDBObject();
								operationObj.put(fieldLookupObject.get(DataConstants.OPERATION).toString(), Long.parseLong(format.format(new Date())));
								//								log.info("Operation 2: " + operationObj);
								queryObject.put(fieldLookupObject.get(DataConstants.PARAM_NAME).toString(), operationObj);
								operationObj = null;
							}
							else
								queryObject.put(fieldLookupObject.get(DataConstants.PARAM_NAME).toString(), format.format(new Date()));
						}
					}
				}
			}

			template = mongoConnectionUtil.getConnection(applicationContext, fieldObject.get(DataConstants.CONNECTION_ID).toString());
			collection = template.getCollection(fieldObject.get(DataConstants.SCHEMA_NAME).toString(), fieldObject.get(DataConstants.COLLECTION_NAME).toString());
			//			log.info(collection + " : Query Object : " + queryObject);
			projectionObject = new BasicDBObject();
			if(fieldObject.get(DataConstants.PROJECTION) != null)
				projectionObject = (BasicDBObject) JSON.parse(fieldObject.get(DataConstants.PROJECTION).toString());

			dataCursor = collection.find(queryObject, projectionObject);
			if(fieldObject.get(DataConstants.QUERY_OPERATION) != null)
			{
				JSONObject qryOperation = (JSONObject) fieldObject.get(DataConstants.QUERY_OPERATION);
				if(DataConstants.COUNT.equalsIgnoreCase(qryOperation.get(DataConstants.OPERATION).toString().trim()))
				{
					if(qryOperation.get(DataConstants.FIELD) != null && qryOperation.get(DataConstants.CONDITION) != null)
					{
						int count = 0;
						BasicDBObject dataObj = null;
						Object fetchObj = null;
						JSONArray condArr = (JSONArray) qryOperation.get(DataConstants.CONDITION);
						JSONObject condObject = null;
						Object fetchValue = null;
						Object value = null;
						while(dataCursor.hasNext())
						{
							dataObj = (BasicDBObject) dataCursor.next();
							fetchObj = dataObj.get(qryOperation.get(DataConstants.FIELD).toString().trim());
							if(fetchObj instanceof BasicDBList)
							{
								for(Object arrObj : (BasicDBList)fetchObj)
								{
									boolean flag = false;
									for(Object condObj : condArr)
									{
										condObject = (JSONObject) condObj;
										fetchValue = ((BasicDBObject) arrObj).get(condObject.get(DataConstants.COND_FIELD).toString());
										if(condObject.get(DataConstants.COND_VALUE) != null)
										{
											if(DataConstants.LONG_TYPE.equalsIgnoreCase(condObject.get(DataConstants.TYPE).toString().trim()))
											{
												value = condObject.get(DataConstants.COND_VALUE);
												if(DataConstants.LESS_THAN.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) < Long.parseLong(value.toString()))
														flag = true;
												}
												else if(DataConstants.GREATER_THAN.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) > Long.parseLong(value.toString()))
														flag = true;
												}
												else if(DataConstants.LESS_THAN_EQUALS.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) <= Long.parseLong(value.toString()))
														flag = true;
												}
												else if(DataConstants.GREATER_THAN_EQUALS.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) >= Long.parseLong(value.toString()))
														flag = true;
												}
											}
										}
										else
										{
											if(DataConstants.DATE_NUM.equalsIgnoreCase(condObject.get(DataConstants.TYPE).toString().trim()))
											{
												DateFormat format = new SimpleDateFormat(condObject.get(DataConstants.DATE_FORMAT).toString().trim());
												value = format.format(new Date());
												//												log.info("FetchValue : " + fetchValue + " : Value : " + value);
												if(DataConstants.LESS_THAN.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) < Long.parseLong(value.toString()))
														flag = true;
												}
												else if(DataConstants.GREATER_THAN.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) > Long.parseLong(value.toString()))
														flag = true;
												}
												else if(DataConstants.LESS_THAN_EQUALS.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) <= Long.parseLong(value.toString()))
														flag = true;
												}
												else if(DataConstants.GREATER_THAN_EQUALS.equalsIgnoreCase(condObject.get(DataConstants.COND_OPERATION).toString().trim()))
												{
													if(Long.parseLong(fetchValue.toString()) >= Long.parseLong(value.toString()))
														flag = true;
												}
											}
										}
									}
									if(flag)
										count = count + 1;
								}
							}
							else 
								count = count + 1;
						}
						fieldValue = count;
						//						log.info("count : " + count + " : " + fieldValue);

						dataObj = null;
						fetchObj = null;
						condArr = null;
						condObject = null;
						fetchValue = null;
						value = null;
					}
					else
						fieldValue = dataCursor.size();
				}

				tempObject.put(fieldKey.toString(), fieldValue);
			}
			else
				tempObject.put(fieldKey.toString(), "");

			if(fieldObject.containsKey(DataConstants.OPERATION))
			{
				fieldLookupObject = (JSONObject) fieldObject.get(DataConstants.OPERATION);
				if(fieldLookupObject.get(DataConstants.OPERATION_TYPE) != null)
				{
					if(DataConstants.MINUS.equalsIgnoreCase(fieldLookupObject.get(DataConstants.OPERATION_TYPE).toString()))
					{
						long value = 0;
						long tempVal = 0;
						JSONArray fields = (JSONArray) fieldLookupObject.get(DataConstants.OPERATION_FIELDS);
						if(fields.get(0).toString().contains("$value"))
							value = Long.parseLong(fieldValue.toString());
						else
						{
							values = Utilities.getFieldValue(fields.get(0).toString(), dataObject);
							if(values != null && !values.isEmpty())
								value = Long.parseLong(values.iterator().next().toString());
						}
						for(int i = 0; i < fields.size() - 1; i++)
						{
							if(fields.get(i+1).toString().contains("$value"))
							{
								if(fieldValue instanceof Long)
									value = value - Long.parseLong(fieldValue.toString());
								else if(fieldValue instanceof Integer)
									value = value - Integer.parseInt(fieldValue.toString());
							}
							else
							{
								values = Utilities.getFieldValue(fields.get(i+1).toString(), dataObject);
								if(values != null && !values.isEmpty())
								{
									tempVal = Long.parseLong(values.iterator().next().toString());
									value = value - tempVal;
								}
							}
						}
						fieldValue = value;
					}
					else if(DataConstants.SUM.equalsIgnoreCase(fieldLookupObject.get(DataConstants.OPERATION_TYPE).toString()))
					{
						long value = 0;
						long tempVal = 0;
						JSONArray fields = (JSONArray) fieldLookupObject.get(DataConstants.OPERATION_FIELDS);
						if(fields.get(0).toString().contains("$value"))
							value = Long.parseLong(fieldValue.toString());
						else
						{
							values = Utilities.getFieldValue(fields.get(0).toString(), dataObject);
							if(values != null && !values.isEmpty())
								value = Long.parseLong(values.iterator().next().toString());
						}
						for(int i = 0; i < fields.size() - 1; i++)
						{
							if(fields.get(i+1).toString().contains("$value"))
							{
								if(fieldValue instanceof Long)
									value = value + Long.parseLong(fieldValue.toString());
								else if(fieldValue instanceof Integer)
									value = value + Integer.parseInt(fieldValue.toString());
							}
							else
							{
								values = Utilities.getFieldValue(fields.get(i+1).toString(), dataObject);
								if(values != null && !values.isEmpty())
								{
									tempVal = Long.parseLong(values.iterator().next().toString());
									value = value + tempVal;
								}
							}
						}
						fieldValue = value;
					}
				}
			}
			tempObject.put(fieldKey.toString(), fieldValue);
			LOGGER.debug(queryObject.get("ord_master_id") + " : " + queryObject.get("prd_id") + " : " + fieldValue);
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			fieldLookupObject = null;
			values = null;
			fieldValue = null;
			template = null;
			dataCursor = null;
			projectionObject = null;
			queryObject = null;
		}
	}

	private String calculateDuration(JSONObject dbObject, JSONArray fields) throws GenericProcessorException
	{
		Calendar calendar1 = null;
		Calendar calendar2 = null;
		String value = null;
		Long days = null;

		try 
		{
			calendar1 = Calendar.getInstance();
			calendar2 = Calendar.getInstance();
			value = "";

			if(fields.size() == 2)
			{
				calendar1.setTime((Date) dbObject.get(fields.get(0).toString()));
				calendar2.setTime((Date) dbObject.get(fields.get(1).toString()));
				days = TimeUnit.MILLISECONDS.toDays(Math.abs(calendar1.getTimeInMillis() - calendar2.getTimeInMillis())) + 1;
				value = days.toString();
			}
			else if(fields.size() == 1)
			{
				calendar1.setTime(new Date());
				calendar2.setTime((Date) dbObject.get(fields.get(0).toString()));
				days = TimeUnit.MILLISECONDS.toDays(Math.abs(calendar1.getTimeInMillis() - calendar2.getTimeInMillis())) + 1;
				value = days.toString();
			}
			return value;
		}
		catch (Exception exception) 
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			calendar1 = null;
			calendar2 = null;
			value = null;
			days = null;
		}
	}

	private Object executeExpression(JSONObject dataObject, JSONObject fieldObject) throws GenericProcessorException
	{
		Map<String, Object> dataMap = null;
		JSONObject jsonData = null;
		JSONObject fieldCondObject = null;
		JSONArray condValueArr = null;
		Object expressionResult = null;
		Object condField = null;
		List<Object> values = null;
		String expression = null;
		String val_1 = "";

		try
		{
			expression = fieldObject.get(DataConstants.EXPRESSION).toString().trim();
			if(fieldObject.get(DataConstants.CONDITION) != null && !fieldObject.get(DataConstants.CONDITION).toString().trim().isEmpty())
			{
				fieldCondObject = (JSONObject) fieldObject.get(DataConstants.CONDITION);
				//				log.error("fieldCondObject : " + fieldCondObject);
				if(fieldCondObject.get(DataConstants.COND_FIELD) != null)
				{
					if(fieldCondObject.get(DataConstants.COND_OPERATION) != null)
					{
						condField = fieldCondObject.get(DataConstants.COND_FIELD);
						if(condField.toString().contains("."))
						{
							values = Utilities.getFieldValue(condField.toString(), dataObject);
							if(values != null && !values.isEmpty())
								val_1 = values.iterator().next().toString();
						}
						else
						{	
							val_1 = dataObject.get(condField) != null ? dataObject.get(condField).toString().trim() : "";
						}

						//						log.error("val_1 : " + val_1);
						if(DataConstants.EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
						{
							if(fieldCondObject.get(DataConstants.TYPE) != null && !fieldCondObject.get(DataConstants.TYPE).toString().trim().isEmpty())
							{
								if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldCondObject.get(DataConstants.TYPE).toString().trim()))
								{
									if(Long.valueOf(val_1) == Long.parseLong(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
									{
										expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
										//										log.error("long equals..");
									}
								}
								else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(fieldCondObject.get(DataConstants.TYPE).toString().trim()))
								{
									if(Double.valueOf(val_1) == Double.parseDouble(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
									{
										expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
										//										log.error("double equals..");
									}
								}
							}
							else
							{
								if(val_1.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
								{
									expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
									//									log.error("equals..");
								}
							}
						}
						else if(DataConstants.NOT_EQUALS.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
						{
							if(fieldCondObject.get(DataConstants.TYPE) != null && !fieldCondObject.get(DataConstants.TYPE).toString().trim().isEmpty())
							{
								if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldCondObject.get(DataConstants.TYPE).toString().trim()))
								{
									if(Long.valueOf(val_1) != Long.parseLong(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
									{
										expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
										//										log.error("long not equals..");
									}
								}
								else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(fieldCondObject.get(DataConstants.TYPE).toString().trim()))
								{
									if(Double.valueOf(val_1) != Double.parseDouble(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
									{
										expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
										//										log.error("double not equals..");
									}
								}
							}
							else
							{
								if(!val_1.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
								{
									expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
									//									log.error("not equals..");
								}
							}
						}
						else if(DataConstants.IN.equalsIgnoreCase(fieldCondObject.get(DataConstants.COND_OPERATION).toString()))
						{
							if(fieldCondObject.get(DataConstants.COND_VALUE) instanceof JSONArray)
							{
								condValueArr = (JSONArray) fieldCondObject.get(DataConstants.COND_VALUE);
								boolean flag = false;
								condValueLabel : for(Object condValue : condValueArr)
								{
									if(fieldCondObject.get(DataConstants.TYPE) != null && !fieldCondObject.get(DataConstants.TYPE).toString().trim().isEmpty())
									{
										if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldCondObject.get(DataConstants.TYPE).toString().trim()))
										{
											if(Long.valueOf(val_1) == Long.parseLong(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
											{
												expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
												//												log.error("long in..");
											}
										}
										else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(fieldCondObject.get(DataConstants.TYPE).toString().trim()))
										{
											if(Double.valueOf(val_1) == Double.parseDouble(fieldCondObject.get(DataConstants.COND_VALUE).toString().trim()))
											{
												expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
												//												log.error("double in..");
											}
										}
									}
									else
									{
										//need to cross verify this logic..
										if(condValue.toString().trim().equalsIgnoreCase(val_1)) 
										{
											//											log.error("in..");
											flag = true;
											break condValueLabel;
										}
									}
								}
								if(flag)
								{
									expression = fieldCondObject.get(DataConstants.EXPRESSION).toString().trim();
								}
							}
						}
					}
				}
			}

			//			log.error("expression : " + expression);
			//			log.error("dataObject : " + dataObject);

			if(fieldObject.get(DataConstants.EXPRESSION_TYPE) != null && !fieldObject.get(DataConstants.EXPRESSION_TYPE).toString().trim().isEmpty() 
					&& DataConstants.CONDITIONAL_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.EXPRESSION_TYPE).toString().trim()))
			{
				dataMap = prepareDataMap(expression, fieldObject, dataObject);
				exProcessor.init(expression);
				try 
				{
					expressionResult = exProcessor.processExpression(dataMap);

				}
				catch (Exception e) {}
				//				LOGGER.error("expressionResult :: "+expressionResult);
				if(expressionResult != null && expressionResult.toString().equalsIgnoreCase("true"))
					expressionResult = fieldObject.get(DataConstants.IF);
				else
					expressionResult = fieldObject.get(DataConstants.ELSE);	
			}
			else if(fieldObject.get(DataConstants.EXPRESSION_TYPE) != null 
					&& !fieldObject.get(DataConstants.EXPRESSION_TYPE).toString().trim().isEmpty() 
					&& DataConstants.MATHEMATICAL_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.EXPRESSION_TYPE).toString().trim()))
			{
				jsonData = prepareJSONData(expression, fieldObject, dataObject);
				//				log.error("expression : " + expression);
				//				log.error("jsonData : " + jsonData);
				try 
				{
					expressionResult = mathProcessor.process(expression, jsonData.toJSONString());
				} 
				catch (Exception e) {}
			}

			//			log.error("expressionResult : " + expressionResult);
			if(expressionResult != null && ("NaN".equalsIgnoreCase(expressionResult.toString().trim()) || "Infinity".equalsIgnoreCase(expressionResult.toString().trim())))
				expressionResult = 0;
			return expressionResult;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dataMap = null;
			jsonData = null;
			fieldCondObject = null;
			condValueArr = null;
			expressionResult = null;
			condField = null;
			values = null;
			expression = null;
			val_1 = null;
		}
	}

	@SuppressWarnings("unchecked")
	private JSONObject prepareJSONData(String expression, JSONObject fieldObject, JSONObject dataObject) throws GenericProcessorException
	{
		JSONObject jsonData = null;
		JSONArray arr = null;
		JSONObject expFieldObject = null;
		Object fieldValue = null;
		Object field = null;
		List<Object> values = null;

		try
		{
			//			log.error("dataObject : " + dataObject);
			jsonData = new JSONObject();
			if(fieldObject.get(DataConstants.EXPRESSION_FIELDS) != null && !fieldObject.get(DataConstants.EXPRESSION_FIELDS).toString().trim().isEmpty())
			{
				// time-being solution..
				arr = (JSONArray) fieldObject.get(DataConstants.EXPRESSION_FIELDS);
				//				log.error("expression fields : " + arr);
				for(Object fieldObj : arr)
				{
					expFieldObject = (JSONObject) fieldObj;
					field = expFieldObject.get(DataConstants.FIELD);

					if(expFieldObject.containsKey(DataConstants.DEFAULT_VALUE) 
							&& expFieldObject.get(DataConstants.DEFAULT_VALUE) != null 
							&& !expFieldObject.get(DataConstants.DEFAULT_VALUE).toString().trim().isEmpty())
					{
						fieldValue = expFieldObject.get(DataConstants.DEFAULT_VALUE);
					}
					else
					{
						if(field.toString().contains("."))
						{
							values = Utilities.getFieldValue(field.toString(), dataObject);
							if(values != null && !values.isEmpty())
								fieldValue = values.iterator().next();   
						}
						else 
						{
							//						log.error("dataObject.get(field.toString() : " + dataObject.get(field.toString()));
							fieldValue = dataObject.get(field.toString()) != null ? dataObject.get(field.toString()) : 0;
						}
					}
					//					log.error(field.toString() + " : " + fieldValue);
					jsonData.put(field.toString(), fieldValue);

					//					if(fieldValue.toString().trim().isEmpty() && expFieldObject.get(DataConstants.DEFAULT_VALUE) != null && !expFieldObject.get(DataConstants.DEFAULT_VALUE).toString().trim().isEmpty())
					//						jsonData.put(field.toString(), expFieldObject.get(DataConstants.DEFAULT_VALUE));
				}
			}
			else
			{
				// need to implement this case using expression..
			}

			return jsonData;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			jsonData = null;
			arr = null;
			expFieldObject = null;
			fieldValue = null;
			field = null;
			values = null;
		}
	}

	private Map<String, Object> prepareDataMap(String expression, JSONObject fieldObject, JSONObject dataObject) throws GenericProcessorException
	{
		Map<String, Object> dataMap = null;
		JSONArray arr = null;
		JSONObject expFieldObject = null;
		Object fieldValue = null;
		Object field = null;
		List<Object> values = null;

		try
		{
			dataMap = new HashMap<String, Object>();
			if(fieldObject.get(DataConstants.EXPRESSION_FIELDS) != null && !fieldObject.get(DataConstants.EXPRESSION_FIELDS).toString().trim().isEmpty())
			{
				// time-being solution..
				arr = (JSONArray) fieldObject.get(DataConstants.EXPRESSION_FIELDS);
				for(Object fieldObj : arr)
				{
					expFieldObject = (JSONObject) fieldObj;
					field = expFieldObject.get(DataConstants.FIELD);

					if(expFieldObject.containsKey(DataConstants.DEFAULT_VALUE) 
							&& expFieldObject.get(DataConstants.DEFAULT_VALUE) != null 
							&& !expFieldObject.get(DataConstants.DEFAULT_VALUE).toString().trim().isEmpty())
					{
						fieldValue = expFieldObject.get(DataConstants.DEFAULT_VALUE);
					}
					else
					{
						if(expFieldObject.get(DataConstants.FIELD).toString().contains("."))
						{
							values = Utilities.getFieldValue(field.toString(), dataObject);
							if(values != null && !values.isEmpty())
								fieldValue = values.iterator().next();   
						}
						else
						{
							fieldValue = dataObject.get(field.toString()) != null ? dataObject.get(field.toString()) : "";
						}
					}
					dataMap.put(field.toString(), fieldValue);

					//					if(fieldValue.toString().trim().isEmpty() && expFieldObject.get(DataConstants.DEFAULT_VALUE) != null && !expFieldObject.get(DataConstants.DEFAULT_VALUE).toString().trim().isEmpty())
					//						dataMap.put(field.toString(), expFieldObject.get(DataConstants.DEFAULT_VALUE));
				}
			}
			else
			{
				// need to implement this case using expression..
			}

			return dataMap;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dataMap = null;
			arr = null;
			expFieldObject = null;
			field = null;
			fieldValue = null;
			values = null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<JSONObject> processMultipleRowConf(JSONObject tempObject, JSONObject dataObject) throws GenericProcessorException
	{
		LOGGER.debug("Entry processMultipleRowConf .. " + multipleRowConf + " : dataObject : " + dataObject);
		List<JSONObject> newDataList = null;
		JSONObject multipleRowObj = null;
		JSONObject fieldValueObj = null;
		JSONObject configFieldObj = null;
		JSONObject fieldMultiRowConf = null;
		JSONObject tempDataObject = null;
		JSONArray fieldConf = null;
		Object field = null;
		Object fieldValue = null;
		List<Object> values = null;

		try
		{
			newDataList = new ArrayList<JSONObject>();
			for(Object multipleRowName : multipleRowConf.keySet())
			{
				multipleRowObj = (JSONObject) multipleRowConf.get(multipleRowName);
				field = multipleRowObj.get(DataConstants.FIELD);
				fieldConf = (JSONArray) multipleRowObj.get(DataConstants.FIELD_CONF);
				LOGGER.debug("fieldConf : " + fieldConf);
				if(field.toString().contains("."))
				{
					values = Utilities.getFieldValue(field.toString(), dataObject);
					LOGGER.debug("values : " + values);
					if(values != null && !values.isEmpty())
					{
						for(Object fieldVal : values)
						{
							LOGGER.debug("fieldVal : " + fieldVal + " : " + fieldVal.getClass());
							if(fieldVal instanceof JSONObject)
							{
								boolean flag = false;
								tempDataObject = new JSONObject();
								tempDataObject.putAll(tempObject);
								fieldValueObj = (JSONObject) fieldVal;
								for(Object fieldName : fieldConf)
								{
									configFieldObj = (JSONObject) configurationObj.get(fieldName);
									fieldMultiRowConf = (JSONObject) configFieldObj.get(DataConstants.MULTIPLE_ROW_CONF);
									LOGGER.debug("fieldMultiRowConf : " + fieldMultiRowConf);
									if(fieldMultiRowConf != null && fieldMultiRowConf.get(DataConstants.NAME).toString().equalsIgnoreCase(multipleRowName.toString()))
									{
										tempDataObject.put(fieldName.toString(), fieldValueObj.get(fieldMultiRowConf.get(DataConstants.LOOKUP_FIELD)));
										flag = true;
									}
								}

								LOGGER.debug("flag : " + flag + " : " + tempDataObject);
								if(flag)
									newDataList.add(tempDataObject);
							}
							else if(fieldVal instanceof JSONArray)
							{
								for(Object fieldValObj : (JSONArray) fieldVal)
								{
									if(fieldValObj instanceof JSONObject)
									{
										boolean flag = false;
										tempDataObject = new JSONObject();
										tempDataObject.putAll(tempObject);
										fieldValueObj = (JSONObject) fieldValObj;
										for(Object fieldName : fieldConf)
										{
											configFieldObj = (JSONObject) configurationObj.get(fieldName);
											fieldMultiRowConf = (JSONObject) configFieldObj.get(DataConstants.MULTIPLE_ROW_CONF);
											LOGGER.debug("fieldMultiRowConf : " + fieldMultiRowConf);
											if(fieldMultiRowConf != null && fieldMultiRowConf.get(DataConstants.NAME).toString().equalsIgnoreCase(multipleRowName.toString()))
											{
												tempDataObject.put(fieldName.toString(), fieldValueObj.get(fieldMultiRowConf.get(DataConstants.LOOKUP_FIELD)));
												flag = true;
											}
										}

										LOGGER.debug("flag : " + flag + " : " + tempDataObject);
										if(flag)
											newDataList.add(tempDataObject);
									}
								}
							}
						}
					}
				}
				else 
				{
					fieldValue = dataObject.get(field.toString()) != null ? dataObject.get(field.toString()) : "";
					LOGGER.debug("fieldValue : " + fieldValue + " : " + fieldValue.getClass());
					if(fieldValue instanceof JSONArray)
					{
						for(Object fieldVal : (JSONArray)fieldValue)
						{
							if(fieldVal instanceof JSONObject)
							{
								boolean flag = false;
								tempDataObject = new JSONObject();
								tempDataObject.putAll(tempObject);
								fieldValueObj = (JSONObject) fieldVal;
								for(Object fieldName : fieldConf)
								{
									configFieldObj = (JSONObject) configurationObj.get(fieldName);
									if(configFieldObj != null && configFieldObj.containsKey(DataConstants.MULTIPLE_ROW_CONF) && configFieldObj.get(DataConstants.MULTIPLE_ROW_CONF) != null)
										fieldMultiRowConf = (JSONObject) configFieldObj.get(DataConstants.MULTIPLE_ROW_CONF);
									if(fieldMultiRowConf != null && fieldMultiRowConf.get(DataConstants.NAME).toString().equalsIgnoreCase(multipleRowName.toString()))
									{
										tempDataObject.put(fieldName.toString(), fieldValueObj.get(fieldMultiRowConf.get(DataConstants.LOOKUP_FIELD)));
										flag = true;
									}
								}

								if(flag)
									newDataList.add(tempDataObject);
							}
						}
					}
					else if(fieldValue instanceof JSONObject)
					{
						boolean flag = false;
						tempDataObject = new JSONObject();
						tempDataObject.putAll(tempObject);
						fieldValueObj = (JSONObject) fieldValue;
						for(Object fieldName : fieldConf)
						{
							configFieldObj = (JSONObject) configurationObj.get(fieldName);
							fieldMultiRowConf = (JSONObject) configFieldObj.get(DataConstants.MULTIPLE_ROW_CONF);
							if(fieldMultiRowConf.get(DataConstants.NAME).toString().equalsIgnoreCase(multipleRowName.toString()))
							{
								tempDataObject.put(fieldName.toString(), fieldValueObj.get(fieldMultiRowConf.get(DataConstants.LOOKUP_FIELD)));
								flag = true;
							}
						}

						if(flag)
							newDataList.add(tempDataObject);
					}
				}
			}

			LOGGER.debug("New DataList Size : " + newDataList.size());
			
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			if(newDataList.isEmpty())
				newDataList.add(tempObject);
			multipleRowObj = null;
			fieldValueObj = null;
			configFieldObj = null;
			fieldMultiRowConf = null;
			tempDataObject = null;
			fieldConf = null;
			field = null;
			fieldValue = null;
			values = null;
		}
		return newDataList;
	}

	public void stop()
	{

	}
}
