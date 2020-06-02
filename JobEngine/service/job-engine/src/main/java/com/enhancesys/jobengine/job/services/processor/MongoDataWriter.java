package com.enhancesys.jobengine.job.services.processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.mongo.util.MongoConnectionUtil;
import com.enhancesys.jobengine.job.services.mongo.util.MongoTemplate;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class MongoDataWriter extends JobProcessor
{
	@Autowired
	MongoConnectionUtil mongoConnectionUtil;
	
	@Autowired 
	private ApplicationContext applicationContext;
	
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private DBCollection collection = null;
	private JSONObject outputConf = null;
	private static Logger log = Logger.getLogger(MongoDataWriter.class);
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfigurationReader, jobParameters);
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
			
			outputConf = (JSONObject) jobConfigData.get(DataConstants.OUTPUT_CONF);
		}
		catch (Exception exception) 
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info("Exit init..");
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		log.info(pipeLineName + " : Entry process.." + processorName + " :jobCount: " + jobCount);
		JSONObject payloadObject = null;
		JSONArray jsonArray = null;
		JSONArray excludeFields = null;
		MongoTemplate template = null;
		BasicDBObject dbObject = null;
		List<DBObject> dbList = null;
		DateFormat dateFormat = null;
		Calendar calendar = null;
		String collectionName = null;
		
		try
		{
			if(outputConf == null)
			{
				log.error(pipeLineName + " : " + processorName + " : Output configuration not exist..");
				throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Output configuration not exist..");
			}
			if(!DataConstants.MONGO.equalsIgnoreCase(outputConf.get(DataConstants.TYPE).toString()))
			{
				log.equals(pipeLineName + " : " + processorName + " : Output type should be mongo..");
				throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Output type should be mongo..");
			}
			
			if(collection == null)
			{
				collectionName = outputConf.get(DataConstants.COLLECTION_NAME).toString();
				if(outputConf.get(DataConstants.COLLECTION_NAME_PATTERN) != null && !outputConf.get(DataConstants.COLLECTION_NAME_PATTERN).toString().trim().isEmpty())
				{
					dateFormat = new SimpleDateFormat(outputConf.get(DataConstants.COLLECTION_NAME_PATTERN).toString().trim());
					if(calendar == null)
					{
						calendar = Calendar.getInstance();
						calendar.add(Calendar.DATE, -1);
					}
					
					collectionName += Integer.parseInt(dateFormat.format(calendar.getTime()));
				}
				
				template = mongoConnectionUtil.getConnection(applicationContext, outputConf.get(DataConstants.CONNECTION_ID).toString());
				collection = template.getCollection(outputConf.get(DataConstants.SCHEMA_NAME).toString(), collectionName);
			}

			excludeFields = (JSONArray) outputConf.get(DataConstants.EXCLUDE_FIELDS);
			
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					dbList = new ArrayList<DBObject>();
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					for(Object object : jsonArray)
					{
						dbObject = new BasicDBObject();
						
						dbObject.putAll((JSONObject) object);
						if(excludeFields != null)
						{
							for(Object field : excludeFields)
							{
								dbObject.remove(field);
							}
						}
						dbList.add(dbObject);
						if(dbList.size() == 1000)
						{
							collection.insert(dbList);
							dbList.clear();
							dbList = new ArrayList<DBObject>();
						}
					}
					if(!dbList.isEmpty())
					{
						collection.insert(dbList);
//						log.info("DBList : " + dbList);
						dbList = null;
					}
				}
				else
					jobObject.put("status", "Completed");
			}
			
			log.info(pipeLineName + " : " + processorName + " : DataObject : " + jobObject);
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
				log.error("Exception Occured : " + e.getMessage());
				throw new GenericProcessorException("Exception Occured : " + e.getMessage());
			}
		}
		catch(Exception exception)
		{
			jobObject.put("status", "Failure");
			log.error(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			payloadObject = null;
			jsonArray = null;
			excludeFields = null;
			template = null;
			dbObject = null;
			dbList = null;
			dateFormat = null;
			calendar = null;
			collectionName = null;
			log.info(pipeLineName + " : Exit process.." + processorName);
		}
		return false;
	}
	
	public void stop()
	{
		
	}
}