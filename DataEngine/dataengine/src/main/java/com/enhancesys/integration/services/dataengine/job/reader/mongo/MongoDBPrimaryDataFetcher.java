package com.enhancesys.integration.services.dataengine.job.reader.mongo;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.reader.DBProcessor;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoDataUtil;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoTimeoutException;

public class MongoDBPrimaryDataFetcher extends DBProcessor
{
	@Autowired
	MongoDataUtil mongoDataUtil;
	
	private DBCursor cursor = null;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount = 0;
	private int collectionPosition = 0;
	private static Logger LOGGER = Logger.getLogger(MongoDBPrimaryDataFetcher.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		LOGGER.info("Entry init..");
		super.init(pipeLineName, processorName, _processorConfig_, jobConfiguration, jobParameters);
		
		try
		{
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			cursor = mongoDataUtil.getCursor((JSONObject) _collectionArr_.get(collectionPosition), true);
			
			if(cursor.count() == 0)
			{
				throw new Exception("No data found in primary collection");
			}
			
			if(primaryConfig.get("job_count") != null && !primaryConfig.get("job_count").toString().trim().isEmpty())
				this.jobCount = (Long) primaryConfig.get("job_count");
			else
				this.jobCount = -1;
			
			if(primaryConfig.get("sleep_time") != null && !primaryConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) primaryConfig.get("sleep_time");
			else
				this.sleepTime = 0;
			
			if(primaryConfig.get("delay_count") != null && !primaryConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) primaryConfig.get("delay_count");
			else
				this.delayCount = 0;
		}
		catch (MongoTimeoutException exception) 
		{
			LOGGER.error("Not able to connect mongo : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Not able to connect mongo : " + exception.getMessage(), exception);
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			LOGGER.info("Exit init..");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException
	{
		LOGGER.debug("Entry fetchData..");
		
		JSONObject dbObject = null;
		JSONObject mapKeyConf = null;
		JSONArray jsonArray = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONObject mapKeyObject = null;
		Object mapKey = null;
		Object localKey = null;
		String keyType = null; 
		List<Object> values = null;
		
		try
		{
			if(cursor.hasNext())
			{
				dbObject = new JSONObject();
				dbObject.putAll((BasicDBObject)cursor.next());
				jsonArray = new JSONArray();
				LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : Primary : " + dbObject);
				jsonArray.add(dbObject);
				fetchedData.put(DataConstants.DATA_LIST, jsonArray);
//				log.info(pipeLineName + " : " + processorName + " : Data : " + fetchedData);
				keyField.clear();
				mapKeyConf = (JSONObject) ((JSONObject) _collectionArr_.get(collectionPosition)).get(DataConstants.MAP_KEY);
				if(mapKeyConf != null)
				{
					mapKey = mapKeyConf.get(DataConstants.KEY);
					localKey = mapKeyConf.get(DataConstants.LOCAL_KEY);
					keyType = mapKeyConf.get(DataConstants.TYPE) != null ? mapKeyConf.get(DataConstants.TYPE).toString() : "String";
					mapKeyObject = new JSONObject();
					if(localKey instanceof JSONArray)
					{
						localKeyArray = (JSONArray) localKey;
						mapKeyArray = (JSONArray) mapKey;
						
						for(int i = 0; i < localKeyArray.size(); i++)
						{
							if(localKeyArray.get(i).toString().contains("."))
							{
								try 
								{
									Object value = null;
									values = Utilities.getFieldValue(localKeyArray.get(i).toString(), dbObject);
									if(values != null && !values.isEmpty())
										 value = values.iterator().next();
									if(value != null)
									{
										if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
											mapKeyObject.put(mapKeyArray.get(i).toString(), value.toString());
										
										if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
											mapKeyObject.put(mapKeyArray.get(i).toString(), Long.parseLong(value.toString()));
									}
									else
										mapKeyObject = null;
								} 
								catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
							else if(dbObject.get(localKeyArray.get(i).toString()) != null)
							{
								if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
									mapKeyObject.put(mapKeyArray.get(i).toString(), dbObject.get(localKeyArray.get(i).toString()).toString());
								if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
									mapKeyObject.put(mapKeyArray.get(i).toString(), Long.parseLong(dbObject.get(localKeyArray.get(i).toString()).toString()));
							}
							else
								mapKeyObject = null;
						}
						
						if(mapKeyObject != null)
							keyField.add(mapKeyObject);
					}
					else
					{
						if(localKey.toString().contains("."))
						{
							try 
							{
								Object value = null; 
								values = Utilities.getFieldValue(localKey.toString(), dbObject);
								if(values != null && !values.isEmpty())
									value = values.iterator().next();
								if(value != null)
								{
									if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
										mapKeyObject.put(mapKey.toString(), value.toString());
									
									if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
										mapKeyObject.put(mapKey.toString(), Long.parseLong(value.toString()));
								}
								else
									mapKeyObject = null;
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
						}
						else if(dbObject.get(localKey.toString()) != null)
						{
							if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
								mapKeyObject.put(mapKey.toString(), dbObject.get(localKey.toString()).toString());
							if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
								mapKeyObject.put(mapKey.toString(), Long.parseLong(dbObject.get(localKey.toString()).toString()));
						}
						else
							mapKeyObject = null;
						
						if(mapKeyObject != null)
							keyField.add(mapKeyObject);
					}
					LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : keyField : " + keyField);
				}
			}
			else
			{
				LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
				throw new NoMoreDataException(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
			}
			
			try
			{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException interruptedException)
			{
				LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " :  Interrupted Exception : " + interruptedException.getMessage());
			}
			
			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
		}
		catch(NoMoreDataException noMoreDataException)
		{
//			log.error(noMoreDataException.getMessage(), noMoreDataException);
			throw noMoreDataException;
		}
		finally
		{
			dbObject = null;
			mapKeyConf = null;
			jsonArray = null;
			localKeyArray = null;
			mapKeyArray = null;
			mapKeyObject = null;
			mapKey = null;
			localKey = null;
			keyType = null;
			
			LOGGER.debug("Exit fetchData..");
		}
	}
	
	public void stop()
	{
		if(cursor != null)
			cursor.close();
	}
}
