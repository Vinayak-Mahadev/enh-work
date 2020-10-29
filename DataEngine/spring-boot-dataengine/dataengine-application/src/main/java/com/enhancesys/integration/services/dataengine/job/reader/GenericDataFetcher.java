package com.enhancesys.integration.services.dataengine.job.reader;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobProcessor;
import com.enhancesys.integration.services.dataengine.job.components.DataReader;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;

public class GenericDataFetcher extends JobProcessor implements DataReader
{
	@Autowired 
	private ApplicationContext applicationContext;

	private long delayCount;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private int batchSize;
	private List<DBProcessor> processors = null;
	private static Logger LOGGER = Logger.getLogger(GenericDataFetcher.class);

	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		LOGGER.info(pipeLineName + " : Entry init..");
		DBProcessor dbProcessor = null;
		JSONObject processor = null;

		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfiguration, jobParameters);

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

			if(processorConfig.get("processors") != null)
			{
				processors = new ArrayList<DBProcessor>(); 

				for(Object processorObj : (JSONArray) processorConfig.get("processors"))
				{
					processor = (JSONObject) processorObj;
					LOGGER.info(pipeLineName + " : Processor : " + processor);
					dbProcessor = (DBProcessor) applicationContext.getBean(processor.get("class").toString());
					dbProcessor.init(pipeLineName, processor.get("class").toString(), processor, jobConfiguration, jobParameters); //processorConfig is from specified config using context path from templateConfig
					processors.add(dbProcessor);
				}
				LOGGER.info(pipeLineName + " : Processors size : " + processors.size());
			}
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dbProcessor = null;
			processor = null;
			LOGGER.info(pipeLineName + " : Exit init..");
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean process(JSONObject jobObject) throws GenericProcessorException 
	{
		LOGGER.debug("Entry process..");

		JSONArray keyField = null;
		JSONArray dataArray = null;
		JSONObject primaryObject = null;
		JSONObject payLoadObject = null;

		try 
		{
			keyField = new JSONArray();
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

			/*if(jobCount != -1)
			{
				if(jobCount == 0)
				{
					jobObject.put("status", "Completed");
					System.out.println(pipeLineName + " : jobObject : " + jobObject);
					System.out.println(pipeLineName + " : jobCount : " + jobCount);
					return true;
				}*/

			try
			{
				while(true)
				{
					if(dataArray.size() > batchSize)
						break;
					primaryObject = new JSONObject();
					for(DBProcessor processor : processors)
					{
						processor.fetchData(keyField, primaryObject);
					}

					if(_jobConfigData_.get(DataConstants.SECONDARY_MATCH) != null 
							&& _jobConfigData_.get(DataConstants.SECONDARY_MATCH).toString().trim().equalsIgnoreCase(DataConstants.TRUE))
					{
						if(primaryObject.get(DataConstants.DATA_LIST) != null 
								&& primaryObject.get(DataConstants.SECONDARY_MATCH) != null 
								&& primaryObject.get(DataConstants.SECONDARY_MATCH).toString().trim().equalsIgnoreCase(DataConstants.TRUE))
							dataArray.addAll((JSONArray) primaryObject.get(DataConstants.DATA_LIST));
					}
					else
					{
						if(primaryObject.get(DataConstants.DATA_LIST) != null)
							dataArray.addAll((JSONArray) primaryObject.get(DataConstants.DATA_LIST));
					}
				}
			}
			catch(NoMoreDataException noMoreDataException)
			{
				LOGGER.error("No More Data Exists..");
				jobObject.put("status", "Completed");
				return true;
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
			/*}

			jobCount --;*/
		}
		catch (Exception exception) 
		{
			LOGGER.error(exception.getMessage(), exception);
		}
		finally
		{
			keyField = null;
			dataArray = null;
			primaryObject = null;
			LOGGER.debug("Exit process..");
		}
		return false;
	}

	@Override
	public void stop() 
	{
		for(DBProcessor processor : processors)
		{
			processor.stop();
		}
	}
}