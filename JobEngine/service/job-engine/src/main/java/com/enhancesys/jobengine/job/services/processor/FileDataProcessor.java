package com.enhancesys.jobengine.job.services.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;
import com.enhancesys.jobengine.job.services.util.PropertiesLoader;

public class FileDataProcessor extends JobProcessor
{
	@Autowired
	PropertiesLoader propertiesLoader;
	
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private static Logger log = Logger.getLogger(FileDataProcessor.class);
	
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
		FileOutputStream outputStream = null;
		File file = null;
		
		try
		{
			file = new File(propertiesLoader.getValueFor("OUT_FILE_PATH") + processorName + ".txt");
			outputStream = new FileOutputStream(file, true);
			
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					for(Object object : jsonArray)
					{
						outputStream.write(object.toString().getBytes());
						outputStream.write(System.getProperty("line.separator").getBytes());
					}
				}
				else
					jobObject.put("status", "Completed");
			}
			
			outputStream.close();
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
			try 
			{
				if(outputStream != null)
					outputStream.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			file = null;
			outputStream = null;
			payloadObject = null;
			log.info(pipeLineName + " : Exit process.." + processorName);
		}
		return false;
	}
	
	public void stop()
	{
		
	}
}