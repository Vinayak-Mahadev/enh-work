package com.enhancesys.integration.services.dataengine.job.writer;

import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobProcessor;
import com.enhancesys.integration.services.dataengine.job.components.DataWriter;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class SampleProcessor extends JobProcessor implements DataWriter
{
	private long jobCount;
//	private long sleepTime;
	private long delayCount;
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfiguration, jobParameters);
			if(processorConfig.get("job_count") != null && !processorConfig.get("job_count").toString().trim().isEmpty())
				this.jobCount = (Long) processorConfig.get("job_count");
			else
				this.jobCount = -1;
			
			/*if(processorConfig.get("sleep_time") != null && !processorConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) processorConfig.get("sleep_time");
			else
				this.sleepTime = 500;*/
			
			if(processorConfig.get("delay_count") != null && !processorConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) processorConfig.get("delay_count");
			else
				this.delayCount = 100;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
//		System.out.println(pipeLineName + " : Entry process.." + processorName + " :jobCount: " + jobCount);
		JSONObject localObject = null;
		JSONObject payloadObject = null;
		
		try
		{
//			System.out.println("jobObject : " + jobObject);
			if(jobCount != -1)
			{
				if(jobObject.keySet().isEmpty())
				{
					payloadObject = new JSONObject();
					payloadObject.put(_pipeLineName_, "TestValue-1000");
					if(jobCount > 0)
						payloadObject.put(_pipeLineName_ + jobCount, "TestValue-1000");
					jobObject.put(DataConstants.PAYLOAD, payloadObject);
					jobObject.put("status", "Initiated");
				}
			}
			else
			{
				localObject = new JSONObject();
				localObject.put("local", "localValue");
				payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
				payloadObject.put(_pipeLineName_, localObject);
				jobObject.put("status", "Success");
			}
			
			if(jobCount != -1) 
			{
				if(jobCount == 0)
					jobObject.put("status", "Completed");
//				System.out.println(pipeLineName + " : jobObject : " + jobObject);
				jobCount --;
			}

//			System.out.println(pipeLineName + " : " + processorName + " : DataObject : " + jobObject);
			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
			/*try
			{
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e) 
			{
				throw new GenericProcessorException("Exception Occured : " + e.getMessage());
			}*/
		}
		catch(Exception exception)
		{
			jobObject.put("status", "Failure");
			exception.printStackTrace();
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage());
		}
		finally
		{
			localObject = null;
			payloadObject = null;
//			System.out.println(pipeLineName + " : Exit process.." + processorName);
		}
		return false;
	}
	
	public void stop()
	{
		
	}
}