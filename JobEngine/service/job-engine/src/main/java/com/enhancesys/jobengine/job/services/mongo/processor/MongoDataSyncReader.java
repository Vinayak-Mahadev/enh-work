package com.enhancesys.jobengine.job.services.mongo.processor;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.mongo.util.MongoDataUtil;
import com.enhancesys.jobengine.job.services.processor.JobProcessor;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;
import com.enhancesys.jobengine.job.services.util.NoMoreDataException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class MongoDataSyncReader extends JobProcessor 
{
	@Autowired
	MongoDataUtil mongoDataUtil;
	
	private long delayCount;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private int batchSize;
	private int collectionPosition = 0;
	private int collectionIndex = 0;
	private DBCursor cursor = null;
	private JSONArray collectionArr = null;
	private JSONArray collectionNameArr = null;
	private JSONObject collectionConf = null;
	private static Logger log = Logger.getLogger(MongoDataSyncReader.class);
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters)
	{
		log.info(pipeLineName + " : Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfigurationReader, jobParameters);
			
			if(processorConfig.get("job_count") != null && !processorConfig.get("job_count").toString().trim().isEmpty())
				this.jobCount = (Long) processorConfig.get("job_count");
			else
				this.jobCount = -1;
			
			if(processorConfig.get("batch_size") != null && !processorConfig.get("batch_size").toString().trim().isEmpty())
				this.batchSize = Integer.parseInt(processorConfig.get("batch_size").toString().trim());
			else
				this.batchSize = 1;
			
			if(processorConfig.get("delay_count") != null && !processorConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) processorConfig.get("delay_count");
			else
				this.delayCount = 0;
			
			if(processorConfig.get("sleep_time") != null && !processorConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) processorConfig.get("sleep_time");
			else
				this.sleepTime = 0;
			
			collectionArr = (JSONArray) jobConfigData.get(DataConstants.PRIMARY);
			collectionPosition = Integer.parseInt(processorConfig.get("collection_position").toString());
			collectionConf = (JSONObject) collectionArr.get(collectionPosition);
			collectionNameArr = ((JSONArray) collectionConf.get(DataConstants.COLLECTION_NAME));
		}
		catch(Exception exception)
		{
			log.error(exception.getMessage(), exception);
		}
		finally
		{
			log.info(pipeLineName + " : Exit init..");
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean process(JSONObject jobObject) throws GenericProcessorException 
	{
		log.info("Entry process..");
		
		JSONArray dataArray = null;
		JSONObject payLoadObject = null;
		JSONObject dbObject = null;
		JSONObject configObject = null;
		
		try 
		{
			payLoadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
			if(payLoadObject == null)
			{
				payLoadObject = new JSONObject();
				jobObject.put(DataConstants.PAYLOAD, payLoadObject);
			}
			
			dataArray = (JSONArray) payLoadObject.get(DataConstants.DATA_LIST);
			if(dataArray == null)
			{
				dataArray = new JSONArray();
				payLoadObject.put(DataConstants.DATA_LIST, dataArray);
			}
			
				
			try
			{
				while(true)
				{
					if(dataArray.size() > batchSize)
						break;
					
					if(cursor == null)
					{
						configObject = new JSONObject();
						configObject.put(DataConstants.SCHEMA_NAME, collectionConf.get(DataConstants.SCHEMA_NAME));
						configObject.put(DataConstants.CONNECTION_ID, collectionConf.get(DataConstants.CONNECTION_ID));
						configObject.put(DataConstants.COLLECTION_NAME, collectionNameArr.get(collectionIndex));
						
						payLoadObject.put(DataConstants.SCHEMA_NAME, collectionConf.get(DataConstants.SCHEMA_NAME));
						payLoadObject.put(DataConstants.CONNECTION_ID, collectionConf.get(DataConstants.CONNECTION_ID));
						payLoadObject.put(DataConstants.COLLECTION_NAME, collectionNameArr.get(collectionIndex));
						
						cursor = mongoDataUtil.getCursor(configObject, false);
					}
					
					if(cursor.hasNext())
					{
						dbObject = new JSONObject();
						dbObject.putAll((BasicDBObject)cursor.next());
						dataArray.add(dbObject);
					}
					else
					{
						collectionIndex ++;
						if(collectionIndex >= collectionNameArr.size())
						{
							log.error(pipeLineName + " : " + processorName + " : No more data exists in the cursor..");
							throw new NoMoreDataException(pipeLineName + " : " + processorName + " : No more data exists in the cursor..");
						}
						
						break;
					}
				}
			}
			catch(NoMoreDataException noMoreDataException)
			{
//					noMoreDatalog.error("Unhandled Exception : " + exception.getMessage(), exception);
				log.error("No More Data Exists..");
				jobObject.put("status", "Completed");
				log.info(pipeLineName + " : jobObject : " + jobObject);
				return true;
			}
			
			try
			{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException interruptedException)
			{
				log.error(pipeLineName + " : " + processorName + " :  Interrupted Exception : " + interruptedException.getMessage());
			}
			
			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
		}
		catch (Exception exception) 
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dataArray = null;
			payLoadObject = null;
			dbObject = null;
			configObject = null;
			
			log.info("Exit process..");
		}
		return false;
	}

	@Override
	public void stop() 
	{
		if(cursor != null)
			cursor.close();
	}
}