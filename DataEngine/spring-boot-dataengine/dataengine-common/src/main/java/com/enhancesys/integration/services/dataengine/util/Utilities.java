package com.enhancesys.integration.services.dataengine.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class Utilities 
{
	private static Logger LOGGER = Logger.getLogger(Utilities.class);

	public static Date getStartOfTheDay(Object  object) throws Exception 
	{
		LOGGER.info("getStartOfTheDay:: Parsing Object : "+object);
		Date date = null;

		try
		{
			if(object!=null && !object.toString().equals(""))
			{
				DateTime dateTime  = new DateTime(object);
				dateTime=dateTime.withHourOfDay(0);
				dateTime=dateTime.withMinuteOfHour(0);
				dateTime=dateTime.withSecondOfMinute(0);
				dateTime=dateTime.withMillisOfSecond(0);
				date = new DateTime(dateTime, DateTimeZone.UTC).toDate();
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("getStartOfTheDay:: Exception is: >>"+exception.getMessage());
			throw exception;
		}
		LOGGER.info("getStartOfTheDay:: Returning Object : "+date);
		return date;
	}

	public static Date getEndOfTheDay(Object  object) throws Exception 
	{
		LOGGER.info("getEndOfTheDay:: Parsing Object : "+object);
		Date date = null;

		try
		{
			if(object!=null && !object.toString().equals(""))
			{
				DateTime dateTime  = new DateTime(object);
				dateTime=dateTime.withHourOfDay(23);
				dateTime=dateTime.withMinuteOfHour(59);
				dateTime=dateTime.withSecondOfMinute(59);
				dateTime=dateTime.withMillisOfSecond(999);
				date = new DateTime(dateTime, DateTimeZone.UTC).toDate();
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("getEndOfTheDay:: Exception is: >>"+exception.getMessage());
			throw exception;
		}
		LOGGER.info("getEndOfTheDay:: Returning Object : "+date);
		return date;
	}

	public static Object getFieldValue(String jsonPath, DBObject requestJson) throws Exception
	{
		DBObject tempJson = null;
		String[] arr = null;

		try
		{
			tempJson = requestJson;
			arr = jsonPath.split("\\.");
			//			TLogger.debug("arr.length : " + arr.length);
			for(int i = 0; i < arr.length; i ++)
			{
				//				TLogger.debug("arr[i]" + arr[i] + " :: tempJson.get(arr[i]) : " + tempJson.get(arr[i]));
				if(tempJson.get(arr[i]) == null || tempJson.get(arr[i]).toString().trim().isEmpty())
				{
					return "";
				}
				if(tempJson.get(arr[i]) instanceof DBObject)
				{
					//					TLogger.debug("insdie object..");
					tempJson = (DBObject) tempJson.get(arr[i]);
					if(i == arr.length - 1)
						return tempJson;
				}
				if(tempJson.get(arr[i]) instanceof BasicDBList)
				{
					//					TLogger.debug("inside array..");
					return (BasicDBList) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof String)
				{
					//					TLogger.debug("insdie string..");
					return (String) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof Long)
				{
					//					TLogger.debug("insdie long.." + tempJson.get(arr[i]));
					return ((Long) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Integer)
				{
					//					TLogger.debug("insdie integer.." + tempJson.get(arr[i]));
					return ((Integer) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Double)
				{
					//					TLogger.debug("insdie double.." + tempJson.get(arr[i]));
					return ((Double) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Date)
				{
					return (Date) tempJson.get(arr[i]);
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			tempJson = null;
			arr = null;
		}
		return null;
	}

	public static List<Object> getFieldValue(String jsonPath, JSONObject requestJson)
	{
		List<Object> values = null;

		try
		{
			values = new ArrayList<Object>();
			getFieldValue(jsonPath, requestJson, values);
			return values;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			values = null;
		}
		return null;
	}

	private static void getFieldValue(String jsonPath, JSONObject requestJson, List<Object> values)
	{
		JSONArray tempArr = null;
		JSONObject tempObject = null;
		String[] arr = null;
		String tempPath = null;
		Object childResult = null;

		try
		{
			//			log.info(jsonPath + " : " + requestJson);
			tempObject = requestJson;
			arr = jsonPath.split("\\.");
			for(int i = 0; i < arr.length; i ++)
			{
				childResult = getValue(arr[i], tempObject);
				//				log.info("childResult : " + childResult);
				if(childResult instanceof JSONObject)
				{
					tempObject = (JSONObject) childResult;
					if(i == arr.length - 1)
						values.add(tempObject);
				}
				else if(childResult instanceof JSONArray)
				{
					tempArr = (JSONArray) childResult;
					if(i == arr.length - 1)
						values.add(tempArr);
					else
					{
						tempPath = "";
						for(int j = i + 1; j < arr.length; j ++)
						{
							tempPath = tempPath + arr[j] + ".";
						}
						tempPath = tempPath.substring(0, tempPath.length() - 1);
						for(Object childObject : tempArr)
						{
							getFieldValue(tempPath, (JSONObject)childObject, values);
						}
					}
				}
				else
				{
					if(childResult != null && !childResult.toString().trim().isEmpty())
						values.add(childResult);
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			tempArr = null;
			tempObject = null;
			arr = null;
			tempPath = null;
			childResult = null;
		}
	}

	private static Object getValue(String jsonPath, JSONObject requestJson)
	{
		JSONObject tempJson = null;
		String[] arr = null;

		try
		{
			//			log.info(jsonPath + " : " + requestJson);
			tempJson = requestJson;
			arr = jsonPath.split("\\.");
			//			TLogger.debug("arr.length : " + arr.length);
			for(int i = 0; i < arr.length; i ++)
			{
				//				TLogger.debug("arr[i]" + arr[i] + " :: tempJson.get(arr[i]) : " + tempJson.get(arr[i]));
				if(tempJson.get(arr[i]) == null || tempJson.get(arr[i]).toString().trim().isEmpty())
				{
					return "";
				}
				if(tempJson.get(arr[i]) instanceof JSONObject)
				{
					//					TLogger.debug("insdie object..");
					tempJson = (JSONObject) tempJson.get(arr[i]);
					if(i == arr.length - 1)
						return tempJson;
				}
				if(tempJson.get(arr[i]) instanceof JSONArray)
				{
					//					TLogger.debug("inside array..");
					return (JSONArray) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof String)
				{
					//					TLogger.debug("insdie string..");
					return (String) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof Long)
				{
					//					TLogger.debug("insdie long.." + tempJson.get(arr[i]));
					return ((Long) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Integer)
				{
					//					TLogger.debug("insdie integer.." + tempJson.get(arr[i]));
					return ((Integer) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Double)
				{
					//					TLogger.debug("insdie double.." + tempJson.get(arr[i]));
					return ((Double) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Date)
				{
					return (Date) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof Object)
				{
					//					log.info("It's object..");
					return tempJson.get(arr[i]);
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			tempJson = null;
			arr = null;
		}
		return null;
	}

	public static BasicDBObject prepareQuery(JSONObject configObject, JSONObject dataObject) throws GenericProcessorException
	{
		BasicDBObject queryObject = null;
		JSONObject queryConf = null;
		DateFormat fromFormat = null;
		DateFormat toFormat = null;
		String fieldValue = null;
		String queryString = null;
		List<Object> values = null;

		try
		{
			//			log.error("util prepareQuery dataObject : " + dataObject);
			queryObject = new BasicDBObject();
			queryConf = (JSONObject) configObject.get(DataConstants.QUERY_CONF);
			//			log.error("queryConf : " + queryConf);
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

				//				log.error("param val : " + queryConf.get(DataConstants.PARAM_VALUE).toString() + " : fieldValue : " + fieldValue);
				if(fieldValue.trim().isEmpty())
					return queryObject;
				if(DataConstants.DATE_TYPE.equalsIgnoreCase(queryConf.get(DataConstants.PARAM_TYPE).toString()))
				{
					if(queryConf.get(DataConstants.FROM_DATE_FORMAT) != null && !queryConf.get(DataConstants.FROM_DATE_FORMAT).toString().trim().isEmpty())
					{
						//						log.error("inside format..");
						fromFormat = new SimpleDateFormat(queryConf.get(DataConstants.FROM_DATE_FORMAT).toString());
						if(fromFormat != null)
						{
							if(queryConf.get(DataConstants.TO_DATE_FORMAT) == null || queryConf.get(DataConstants.TO_DATE_FORMAT).toString().trim().isEmpty())
							{
								LOGGER.error("To Date Format should not be empty..");
								throw new GenericProcessorException("To Date Format should not be empty..");
							}
							toFormat = new SimpleDateFormat(queryConf.get(DataConstants.TO_DATE_FORMAT).toString());

							fieldValue = toFormat.format(fromFormat.parse(fieldValue));
							//							log.error("fieldValue : " + fieldValue);
							if(queryConf.get(DataConstants.PARAM_VALUE_TYPE) != null 
									&& !queryConf.get(DataConstants.PARAM_VALUE_TYPE).toString().trim().isEmpty() 
									&& DataConstants.REPLACE.equalsIgnoreCase(queryConf.get(DataConstants.PARAM_VALUE_TYPE).toString().trim()))
							{
								queryString = queryConf.get(DataConstants.QUERY).toString();
								queryString = queryString.replaceAll(queryConf.get(DataConstants.PARAM_NAME).toString(), fieldValue);
								queryObject = (BasicDBObject) JSON.parse(queryString);
								//								log.error("queryObject : " + queryObject);
								return queryObject;
							}
						}
					}
				}
			}
			return queryObject;
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			queryObject = null;
			queryConf = null;
			fromFormat = null;
			toFormat = null;
			fieldValue = null;
			queryString = null;
			values = null;
		}
	}

	@SuppressWarnings("unchecked")
	public static void prepareQueryWithParams(JSONObject configObject, BasicDBObject queryObject, JSONObject dataObject) throws GenericProcessorException
	{
		JSONObject paramObject = null;
		JSONArray paramValues = null;
		JSONArray valueArr = null;
		BasicDBObject dateQuery = null;
		DateFormat dateFormat = null;
		DateFormat fromFormat = null;
		DateFormat toFormat = null;
		Date startDate = null;
		Date endDate = null;
		Calendar calendar = null;
		Calendar calendar2 = null;

		try
		{
			//			log.error("util dataObject : " + dataObject);
			for(Object param : (JSONArray) configObject.get(DataConstants.PARAMETERS))
			{
				paramObject = (JSONObject) param;
				if(paramObject != null && paramObject.keySet().size() > 0)
				{
					if((paramObject.get(DataConstants.MANDATORY) != null && DataConstants.FALSE.equalsIgnoreCase(paramObject.get(DataConstants.MANDATORY).toString().trim())) 
							&& (paramObject.get(DataConstants.PARAM_VALUE) == null || paramObject.get(DataConstants.PARAM_VALUE).toString().trim().isEmpty()))
						continue;
					if(paramObject.get(DataConstants.PARAM_TYPE) != null)
					{
						if(DataConstants.DATE_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
						{
							if(paramObject.get(DataConstants.FROM_DATE_FORMAT) != null && !paramObject.get(DataConstants.FROM_DATE_FORMAT).toString().trim().isEmpty())
							{
								fromFormat = new SimpleDateFormat(paramObject.get(DataConstants.FROM_DATE_FORMAT).toString());
								if(fromFormat != null)
								{
									if(paramObject.get(DataConstants.TO_DATE_FORMAT) == null || paramObject.get(DataConstants.TO_DATE_FORMAT).toString().trim().isEmpty())
									{
										LOGGER.error("To Date Format should not be empty..");
										throw new GenericProcessorException("To Date Format should not be empty..");
									}
									toFormat = new SimpleDateFormat(paramObject.get(DataConstants.TO_DATE_FORMAT).toString());

									if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
									{
										paramValues = (JSONArray) paramObject.get(DataConstants.PARAM_VALUE);
										if(paramValues.size() != 2)
										{
											LOGGER.error("Date params should be 2 always.." + paramValues);
											throw new GenericProcessorException("Date params should be 2 always.." + paramValues);
										}

										if(paramObject.get(DataConstants.PARAM_VALUE_TYPE) != null 
												&& !paramObject.get(DataConstants.PARAM_VALUE_TYPE).toString().trim().isEmpty() 
												&& DataConstants.REPLACE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_VALUE_TYPE).toString().trim()))
										{
											//											log.error("paramValues.get(0).toString() : " + paramValues.get(0).toString());
											String fieldValue = null;
											List<Object> values = null;
											if(paramValues.get(0).toString().contains("."))
											{
												values = Utilities.getFieldValue(paramValues.get(0).toString(), dataObject);
												if(values != null && !values.isEmpty())
													fieldValue = values.iterator().next().toString();   
											}
											else 
												fieldValue = dataObject.get(paramValues.get(0).toString()) != null ? dataObject.get(paramValues.get(0).toString()).toString() : "";

												//											log.error("fieldValue : " + fieldValue);
												startDate = toFormat.parse(toFormat.format(fromFormat.parse(fieldValue)));

												fieldValue = null;
												if(paramValues.get(1).toString().contains("."))
												{
													values = Utilities.getFieldValue(paramValues.get(1).toString(), dataObject);
													if(values != null && !values.isEmpty())
														fieldValue = values.iterator().next().toString();   
												}
												else 
													fieldValue = dataObject.get(paramValues.get(1).toString()) != null ? dataObject.get(paramValues.get(1).toString()).toString() : "";

													endDate = toFormat.parse(toFormat.format(fromFormat.parse(fieldValue)));

													fieldValue = null;
													values = null;
										}
										else
										{
											startDate = toFormat.parse(toFormat.format(fromFormat.parse(paramValues.get(0).toString())));
											endDate = toFormat.parse(toFormat.format(fromFormat.parse(paramValues.get(1).toString())));
										}

										dateQuery = new BasicDBObject("$gte", Utilities.getStartOfTheDay(startDate)).append("$lte", Utilities.getEndOfTheDay(endDate));
										queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
									}
									else
									{
										//										log.error("paramObject : " + paramObject);
										if(paramObject.get(DataConstants.PARAM_VALUE_TYPE) != null 
												&& !paramObject.get(DataConstants.PARAM_VALUE_TYPE).toString().trim().isEmpty() 
												&& DataConstants.REPLACE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_VALUE_TYPE).toString().trim()))
										{
											String fieldValue = null;
											List<Object> values = null;
											if(paramObject.get(DataConstants.PARAM_VALUE).toString().contains("."))
											{
												values = Utilities.getFieldValue(paramObject.get(DataConstants.PARAM_VALUE).toString(), dataObject);
												if(values != null && !values.isEmpty())
													fieldValue = values.iterator().next().toString();   
											}
											else 
												fieldValue = dataObject.get(paramObject.get(DataConstants.PARAM_VALUE).toString()) != null ? dataObject.get(paramObject.get(DataConstants.PARAM_VALUE).toString()).toString() : "";

												if(paramObject.get(DataConstants.OPERATION_TYPE) != null && !paramObject.get(DataConstants.OPERATION_TYPE).toString().trim().isEmpty())
												{
													startDate = toFormat.parse(toFormat.format(fromFormat.parse(fieldValue)));
													String timeFormatType = "";
													if(paramObject.get(DataConstants.TIME_FORMAT_TYPE) != null && !paramObject.get(DataConstants.TIME_FORMAT_TYPE).toString().trim().isEmpty())
														timeFormatType = paramObject.get(DataConstants.TIME_FORMAT_TYPE).toString().trim();

													Date queryReqDate = startDate;
													if(DataConstants.START_OF_THE_DAY.equalsIgnoreCase(timeFormatType))
														queryReqDate = Utilities.getStartOfTheDay(startDate);
													else if(DataConstants.END_OF_THE_DAY.equalsIgnoreCase(timeFormatType))
														queryReqDate = Utilities.getEndOfTheDay(startDate);

													//												log.error("timeFormatType : " + timeFormatType + " : queryReqDate : " + queryReqDate);
													if(DataConstants.LESS_THAN.equalsIgnoreCase(paramObject.get(DataConstants.OPERATION_TYPE).toString().trim()))
														dateQuery = new BasicDBObject("$lt", queryReqDate);
													else if(DataConstants.LESS_THAN_EQUALS.equalsIgnoreCase(paramObject.get(DataConstants.OPERATION_TYPE).toString().trim()))
														dateQuery = new BasicDBObject("$lte", queryReqDate);
													else if(DataConstants.GREATER_THAN.equalsIgnoreCase(paramObject.get(DataConstants.OPERATION_TYPE).toString().trim()))
														dateQuery = new BasicDBObject("$gt", queryReqDate);
													else if(DataConstants.GREATER_THAN_EQUALS.equalsIgnoreCase(paramObject.get(DataConstants.OPERATION_TYPE).toString().trim()))
														dateQuery = new BasicDBObject("$gte", queryReqDate);

													queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);

													queryReqDate = null;
													timeFormatType = null;
												}
												else
												{
													startDate = toFormat.parse(toFormat.format(fromFormat.parse(fieldValue)));
													endDate = toFormat.parse(toFormat.format(fromFormat.parse(fieldValue)));

													dateQuery = new BasicDBObject("$gte", Utilities.getStartOfTheDay(startDate)).append("$lte", Utilities.getEndOfTheDay(endDate));
													queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
												}

												fieldValue = null;
												values = null;
										}
										else
										{
											startDate = toFormat.parse(toFormat.format(fromFormat.parse(paramObject.get(DataConstants.PARAM_VALUE).toString())));
											endDate = toFormat.parse(toFormat.format(fromFormat.parse(paramObject.get(DataConstants.PARAM_VALUE).toString())));

											dateQuery = new BasicDBObject("$gte", Utilities.getStartOfTheDay(startDate)).append("$lte", Utilities.getEndOfTheDay(endDate));
											queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
										}
									}
								}
							}
							else
							{
								if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
								{
									paramValues = (JSONArray) paramObject.get(DataConstants.PARAM_VALUE);
									if(paramValues.size() != 2)
									{
										LOGGER.error("Date params should be 2 always.." + paramValues);
										throw new GenericProcessorException("Date params should be 2 always.." + paramValues);
									}

									calendar = Calendar.getInstance();
									calendar2 = Calendar.getInstance();
									calendar.add(Calendar.DATE, - Integer.parseInt(paramValues.get(0).toString()));
									calendar2.add(Calendar.DATE, - Integer.parseInt(paramValues.get(1).toString()));
									dateQuery = new BasicDBObject("$gte", Utilities.getStartOfTheDay(calendar.getTime())).append("$lte", Utilities.getEndOfTheDay(calendar2.getTime()));
									queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
								}
								else
								{
									calendar = Calendar.getInstance();
									calendar.add(Calendar.DATE, - Integer.parseInt(paramObject.get(DataConstants.PARAM_VALUE).toString()));
									dateQuery = new BasicDBObject("$gte", Utilities.getStartOfTheDay(calendar.getTime())).append("$lte", Utilities.getEndOfTheDay(calendar.getTime()));
									queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
								}
							}
						}
						else if(paramObject.get(DataConstants.DATE_FORMAT) != null && !paramObject.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty() 
								&& DataConstants.DATE_NUM.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
						{
							if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
							{
								paramValues = (JSONArray) paramObject.get(DataConstants.PARAM_VALUE);
								if(paramValues.size() != 2)
								{
									LOGGER.error("Date params should be 2 always.." + paramValues);
									throw new GenericProcessorException("Date params should be 2 always.." + paramValues);
								}

								calendar = Calendar.getInstance();
								calendar2 = Calendar.getInstance();
								calendar.add(Calendar.DATE, - Integer.parseInt(paramValues.get(0).toString()));
								calendar2.add(Calendar.DATE, - Integer.parseInt(paramValues.get(1).toString()));
								dateFormat = new SimpleDateFormat(paramObject.get(DataConstants.DATE_FORMAT).toString().trim());
								dateQuery = new BasicDBObject("$gte", Long.valueOf(dateFormat.format(calendar.getTime()))).append("$lte", Long.valueOf(dateFormat.format(calendar2.getTime())));
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
							}
							else
							{
								calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, - Integer.parseInt(paramObject.get(DataConstants.PARAM_VALUE).toString()));
								dateFormat = new SimpleDateFormat(paramObject.get(DataConstants.DATE_FORMAT).toString().trim());
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), Long.valueOf(dateFormat.format(calendar.getTime())));
							}
						}
						else if(DataConstants.LONG_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
						{
							if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
							{
								valueArr = new JSONArray();
								for(Object pValue : (JSONArray) paramObject.get(DataConstants.PARAM_VALUE))
								{
									valueArr.add(Long.parseLong(pValue.toString().trim()));
								}
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), new BasicDBObject("$in", valueArr));
							}
							else
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), Long.parseLong(paramObject.get(DataConstants.PARAM_VALUE).toString()));
						}
						else
						{
							if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), new BasicDBObject("$in", (JSONArray) paramObject.get(DataConstants.PARAM_VALUE)));
							else
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), paramObject.get(DataConstants.PARAM_VALUE));
						}
					}
					else
					{
						if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
							queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), new BasicDBObject("$in", (JSONArray) paramObject.get(DataConstants.PARAM_VALUE)));
						else
							queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), paramObject.get(DataConstants.PARAM_VALUE));
					}
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			paramObject = null;
			paramValues = null;
			valueArr = null;
			dateQuery = null;
			dateFormat = null;
			fromFormat = null;
			toFormat = null;
			startDate = null;
			endDate = null;
			calendar = null;
			calendar2 = null;
		}
	}

	@SuppressWarnings("unchecked")
	public static JSONObject convertToJSON(ResultSet resultSet, ResultSetMetaData metaData) throws GenericProcessorException
	{
		JSONObject jsonObject = null;
		String columnName = null;

		try
		{
			jsonObject = new JSONObject();
			for (int i = 1; i < metaData.getColumnCount() + 1; i++) 
			{
				//				columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metaData.getColumnName(i));
				columnName = metaData.getColumnName(i).toLowerCase();
				/*if (metaData.getColumnType(i) == java.sql.Types.VARCHAR) 
					jsonObject.put(columnName, resultSet.getString(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.NUMERIC) 
					jsonObject.put(columnName, resultSet.getLong(columnName));
				else*/ if (metaData.getColumnType(i) == java.sql.Types.DATE) 
					jsonObject.put(columnName, resultSet.getDate(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIME)
					jsonObject.put(columnName, resultSet.getTime(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIMESTAMP) 
					jsonObject.put(columnName, resultSet.getTimestamp(columnName));
				else
					jsonObject.put(columnName, resultSet.getObject(columnName));
			}
			return jsonObject;
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			jsonObject = null;
			columnName = null;
		}
	}

	public static List<String> convertToDataList(ResultSet resultSet, int columnCount) throws GenericProcessorException
	{
		List<String> dataList = null;

		try
		{
			dataList = new ArrayList<String>();
			for (int i = 1; i <= columnCount; i++) 
			{
				dataList.add(resultSet.getString(i));
			}
			return dataList;
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			dataList = null;
		}
	}

	public static BasicDBObject convertToDBObject(ResultSet resultSet, ResultSetMetaData metaData) throws GenericProcessorException
	{
		BasicDBObject dbObject = null;
		String columnName = null;

		try
		{
			dbObject = new BasicDBObject();
			for (int i = 1; i < metaData.getColumnCount() + 1; i++) 
			{
				//				columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metaData.getColumnName(i));
				columnName = metaData.getColumnName(i).toLowerCase();
				/*if (metaData.getColumnType(i) == java.sql.Types.VARCHAR) 
					dbObject.put(columnName, resultSet.getString(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.NUMERIC) 
					dbObject.put(columnName, resultSet.getLong(columnName));
				else*/ if (metaData.getColumnType(i) == java.sql.Types.DATE) 
					dbObject.put(columnName, resultSet.getDate(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIME)
					dbObject.put(columnName, resultSet.getTime(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIMESTAMP) 
					dbObject.put(columnName, resultSet.getTimestamp(columnName));
				else
					dbObject.put(columnName, resultSet.getObject(columnName));
			}
			return dbObject;
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			dbObject = null;
			columnName = null;
		}
	}

	@SuppressWarnings("unchecked")
	public static BasicDBObject convertToDBObject(ResultSet resultSet, ResultSetMetaData metaData, Object mapKey) throws GenericProcessorException
	{
		BasicDBObject dbObject = null;
		String columnName = null;
		JSONArray mapKeyArray = null;
		String dataStr = "";

		try
		{
			if(mapKey instanceof JSONArray)
				mapKeyArray = (JSONArray) mapKey;
			else if(mapKey instanceof String)
			{
				mapKeyArray = new JSONArray();
				mapKeyArray.add(mapKey.toString());
			}

			dbObject = new BasicDBObject();
			for (int i = 1; i < metaData.getColumnCount() + 1; i++) 
			{
				//				columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metaData.getColumnName(i));
				columnName = metaData.getColumnName(i).toLowerCase();
				if(mapKeyArray.contains(columnName))
				{
					/*if (metaData.getColumnType(i) == java.sql.Types.VARCHAR) 
					dbObject.put(columnName, resultSet.getString(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.NUMERIC) 
					dbObject.put(columnName, resultSet.getLong(columnName));
				else*/ if (metaData.getColumnType(i) == java.sql.Types.DATE) 
					dbObject.put(columnName, resultSet.getDate(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIME)
					dbObject.put(columnName, resultSet.getTime(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIMESTAMP) 
					dbObject.put(columnName, resultSet.getTimestamp(columnName));
				else
					dbObject.put(columnName, resultSet.getObject(columnName));
				}
				else
				{
					if (metaData.getColumnType(i) == java.sql.Types.DATE) 
						dataStr = dataStr + resultSet.getDate(columnName);
					else if (metaData.getColumnType(i) == java.sql.Types.TIME)
						dataStr = dataStr + resultSet.getTime(columnName);
					else if (metaData.getColumnType(i) == java.sql.Types.TIMESTAMP) 
						dataStr = dataStr + resultSet.getTimestamp(columnName);
					else
						dataStr = dataStr + resultSet.getObject(columnName);
					if(i < metaData.getColumnCount())
						dataStr = dataStr + "|";
				}
				dbObject.put("dataStr", dataStr);
			}
			return dbObject;
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			dbObject = null;
			columnName = null;
			mapKeyArray = null;
			dataStr = "";
		}
	}

	@SuppressWarnings("unchecked")
	public static void mergeObject(JSONObject mainObject, JSONObject subObject)
	{
		for(Object key : subObject.keySet())
		{
			if(mainObject.containsKey(key))
			{
				mainObject.put(key, mainObject.get(key) + "|" + subObject.get(key));
			}
			else
			{
				mainObject.put(key, subObject.get(key));
			}
		}
	}

	public static void mergeObject(DBObject mainObject, JSONObject subObject)
	{
		for(Object key : subObject.keySet())
		{
			if(mainObject.containsField(key.toString()))
			{
				mainObject.put(key.toString(), mainObject.get(key.toString()) + "|" + subObject.get(key.toString()));
			}
			else
			{
				mainObject.put(key.toString(), subObject.get(key.toString()));
			}
		}
	}

	/**
	 * @author VINAYAK-MAHADEV
	 * <b>Algorithm</b>
	 * <pre>
	 * 		<p>If zip is failed zipFile is null. After zip if u want to delete files pass deleteFileList is true </p>
	 * </pre>
	 * @param fileList
	 * @param zipFile
	 * @param deleteFilesFlag
	 * @return zipFile
	 * 
	 */
	public File zipMultipleFiles(List<File> fileList, File zipFile, boolean deleteFilesFlag)
	{
		FileInputStream  zipFileInputStream  = null;
		ZipOutputStream  zipOutputStream = null;
		FileOutputStream zipFileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		ZipEntry zipEntry = null;
		byte[] tmp = null;

		try
		{
			zipFileOutputStream = new FileOutputStream(zipFile);
			bufferedOutputStream = new BufferedOutputStream(zipFileOutputStream);
			zipOutputStream = new ZipOutputStream(bufferedOutputStream);

			for(File file : fileList)
			{
				zipFileInputStream = new FileInputStream(file);
				zipEntry = new ZipEntry(file.getName());
				zipOutputStream.putNextEntry(zipEntry);
				tmp = new byte[4*1024];
				int size = 0;
				while((size = zipFileInputStream.read(tmp)) != -1)
					zipOutputStream.write(tmp, 0, size);

				zipOutputStream.flush();
				zipFileInputStream.close();
			}
			zipOutputStream.close();
			bufferedOutputStream.close();
			zipFileOutputStream.close();

			if(deleteFilesFlag)
				for(File file : fileList)
					file.delete();

		} 
		catch (Exception e) 
		{
			zipFile = null;
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(zipFileInputStream != null)
					zipFileInputStream.close();
				if(zipOutputStream != null)
					zipOutputStream.close();
				if(bufferedOutputStream != null)
					bufferedOutputStream.close();
				if(zipFileOutputStream != null)
					zipFileOutputStream.close();
			}
			catch(IOException exception)
			{
				exception.printStackTrace();
			}

			fileList = null;
			zipFileOutputStream = null;
			zipFileInputStream = null;
			bufferedOutputStream = null;
			zipOutputStream = null;
			zipEntry = null;
			tmp = null;

		}
		return zipFile;
	}

	public static String getExceptionResponse(long requestId, String response, long responseCode) 
	{
		return DataConstants.RESULT_FORMAT
				.replace("$requestId$", String.valueOf(requestId))
				.replace("$responseMessage$", response)
				.replace("$responseCode$", String.valueOf(responseCode))
				.replace("$response$", "");
	}

	public static String getSuccessResponse(long requestId, String response, long responseCode, String payload) throws Exception
	{
		String result = new String();
		try
		{
			result=DataConstants.RESULT_FORMAT
					.replace("$requestId$", String.valueOf(requestId))
					.replace("$responseMessage$", response)
					.replace("$responseCode$", String.valueOf(responseCode))
					.replace("$response$", "," + payload);
		}
		catch(Exception exception)
		{
			throw exception;
		}
		return result;
	}

	public static JSONObject getJobParameter(final String filepath) 
	{
		String jsonStr = null;
		String line = null;
		StringBuffer buffer = null;
		File file = null;
		FileInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		JSONParser parser = null;
		JSONObject jobParameters = null;
		try
		{
			if(filepath != null)
			{

				file  = new File(filepath);
				if(!file.exists())
					throw new IllegalArgumentException("File not present given location");

				inputStream = new FileInputStream(file);
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader);

				buffer = new StringBuffer();
				line = null;
				while ((line= bufferedReader.readLine()) != null)
				{
					buffer.append(line);
				}

				inputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
				jsonStr = buffer.toString();

				parser = new JSONParser();
				jobParameters = (JSONObject) parser.parse(jsonStr);

			}
		}
		catch(Exception exception)
		{
			jobParameters = null;
		}
		finally
		{
			jsonStr = null;
			line = null;
			buffer = null;
			file = null;
			bufferedReader = null;
			inputStreamReader = null;
			inputStream = null;
			parser = null;
			jobParameters = null;
		}
		return jobParameters;	
	}

	public static File writeFile(String fileName, Object data) throws IOException
	{
		File file = null;
		FileWriter fileWriter = null;
		try 
		{
			file = new File(fileName);
			fileWriter = new FileWriter(file);
			fileWriter.write(data + "");
		}
		finally 
		{
			if(fileWriter != null) {
				fileWriter.close();
				LOGGER.info("File written :: " + file.getAbsolutePath());				
			}
			fileWriter = null;
			data = null;
		}
		return file;
	}

	public static BasicDBObject getDefaultRequestUpdateConf()
	{
		BasicDBObject requestUpdateConf = new BasicDBObject();
		requestUpdateConf.put("connection-id", DataConstants.REPORT_SERVICE_CONNECTION_ID);
		requestUpdateConf.put("schema-name", DataConstants.REPORT_SERVICE_SCHEMA);
		requestUpdateConf.put("collection-name", DataConstants.REPORT_SERVICE_COLLECTION);
		requestUpdateConf.put("field", DataConstants.REPORT_SERVICE_COLLECTION_ID);
		return requestUpdateConf;
	}

	public static BasicDBObject getDefaultStoreFileConf(boolean deleteLocalFile, boolean storeFileEnable)
	{
		BasicDBObject storeFileConf = new BasicDBObject();
		storeFileConf.put("connection-id", DataConstants.REPORT_SERVICE_STORE_FILE_CONNECTION_ID);
		storeFileConf.put("schema-name", DataConstants.REPORT_SERVICE_STORE_FILE_SCHEMA);
		storeFileConf.put("delete-file-local", deleteLocalFile);
		storeFileConf.put("is-enable", storeFileEnable);
		return storeFileConf;
	}


	public static String validateBsonSchema(String request, String bsonSchema)
	{
		// need to handle validateBsonSchema
		
		List<String> validationErrors = new ArrayList<String>();
		String response = DataConstants.V_SUCCESS;
		try
		{
			//ObjectMapper mapper = new ObjectMapper();
		}
		catch(Exception ioException)
		{	
			validationErrors.add(ioException.getMessage());
		}
		finally
		{
			if(validationErrors.size() > 0)
			{
				for(int errorCount = 0; errorCount < validationErrors.size(); errorCount++)
				{
					if(errorCount == 0)
					{
						response = validationErrors.get(errorCount);
					}
					else
					{
						response += "~~" + validationErrors.get(errorCount);
					}
				}
			}
		}
		return response;
	}
	
	public static List<Long> getLongLists(String key)
	{
		List<Long> returnList = new ArrayList<Long>();
		if(key != null && !"".equals(key.trim()))
		{
			for(String str : key.split(","))
			{
				returnList.add(Long.valueOf(str));
			}
		}
		return returnList;
	}
}