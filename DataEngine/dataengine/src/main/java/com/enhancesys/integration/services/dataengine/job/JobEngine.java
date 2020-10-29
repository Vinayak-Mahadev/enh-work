package com.enhancesys.integration.services.dataengine.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.integration.services.dataengine.job.connector.JobQueue;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoDataUtil;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class JobEngine 
{
	@Autowired 
	private ApplicationContext applicationContext;

	@Autowired
	private JobConfiguration jobConfiguration;

	@Autowired
	private MongoDataUtil mongoDataUtil;

	private List<Thread> pipeLines = null;
	private static Logger LOGGER = Logger.getLogger(JobEngine.class);

	public void init(JSONObject jobParameters)
	{
		LOGGER.info("Entry init..");

		JSONObject templateConfigData = null;
		JSONArray definitionArr = null;
		JSONObject defObject = null;
		JSONObject jobConfigData = null;
		JobPipeLine jobPipeLine = null;
		Thread thread = null;
		Map<Object, JobQueue> queueMap = null;
		long instances = 0;
		String tempateName = null;
		JSONObject jobData = null;
		try
		{
			pipeLines = new ArrayList<Thread>();

			if(jobParameters.get("template-name") != null && !jobParameters.get("template-name").toString().isEmpty())
				tempateName = jobParameters.get("template-name").toString() + ".json";

			if(jobParameters.get("job-data") != null)
				jobData = (JSONObject) jobParameters.get("job-data");

			jobConfiguration.readConfiguration(tempateName, jobParameters.get("template-id").toString(), jobParameters.get("job-id").toString(), jobData, jobParameters);

			templateConfigData = jobConfiguration.getTemplateConfigData(null);

			if(jobParameters.get("request-id") != null)
			{
				jobConfigData = jobConfiguration.getJobConfigData(null);
				if(jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF) != null)
					mongoDataUtil.updateStatus(jobParameters.get("request-id"), jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF), DataConstants.STATUS_IN_PROGRESS);
			}

			definitionArr = (JSONArray) templateConfigData.get("definition");
			LOGGER.info("definitionArr : " + definitionArr);

			queueMap = loadJobQueues(definitionArr, templateConfigData);
			LOGGER.info("queueMap : " + queueMap);

			for(int i = 0; i < definitionArr.size(); i ++)
			{
				defObject = (JSONObject) templateConfigData.get(definitionArr.get(i));
				if("PipeLine".equalsIgnoreCase(defObject.get("type").toString()))
				{
					instances = (Long) defObject.get("instance");
					jobPipeLine = (JobPipeLine) applicationContext.getBean(defObject.get("class").toString());
					jobPipeLine.init(definitionArr.get(i).toString(), defObject, jobConfiguration, jobParameters);

					LOGGER.info(definitionArr.get(i).toString() + " :" );
					if(i > 0 && queueMap.get(definitionArr.get(i-1)) != null)
					{
						LOGGER.info("InputQ : " + queueMap.get(definitionArr.get(i-1))._name_);
						jobPipeLine.setInputQueue(queueMap.get(definitionArr.get(i-1)));
					}
					if((i+1 < definitionArr.size()) && definitionArr.get(i+1) != null && queueMap.get(definitionArr.get(i+1)) != null)
					{
						LOGGER.info("OutputQ : " + queueMap.get(definitionArr.get(i+1))._name_);
						jobPipeLine.setOutputQueue(queueMap.get(definitionArr.get(i+1)));
					}

					for(int count = 0; count < instances; count ++)
					{
						thread = new Thread(jobPipeLine, definitionArr.get(i).toString() + "_" + count);
						pipeLines.add(thread);
					}
				}
			}

			start();

			//cleanup all the queues before shuttilog.infoine..
			/*log.info("Cleanup from JobEngine..");
			for(JobQueue queue : queueMap.values())
			{
				while(queue.dequeue(null) != null);
			}*/
		}
		catch(Exception exception)
		{
			try 
			{
				if(jobParameters.get("request-id") != null && jobConfigData != null && jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF) != null)
					mongoDataUtil.updateStatus(jobParameters.get("request-id"), jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF), DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				LOGGER.error("GenericProcessor Exception : " + excep.getMessage(), excep);
			}
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			templateConfigData = null;
			definitionArr = null;
			defObject = null;
			jobConfigData = null;
			jobPipeLine = null;
			thread = null;
			queueMap = null;
			tempateName = null;
			LOGGER.info("Exit init..");
		}
	}

	public void start()
	{
		LOGGER.info("Entry start : pipeLine threads size : " + pipeLines.size());
		try
		{
			for(Thread pipeLine : pipeLines)
			{
				LOGGER.info("Starting pipeline : " + pipeLine.getName());
				pipeLine.start();
			}

			for(Thread pipeLine : pipeLines)
			{
				pipeLine.join();
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			LOGGER.info("Exit start : pipeLine threads size : " + pipeLines.size());
		}
	}

	@SuppressWarnings("deprecation")
	public void stop()
	{
		for(Thread pipeLine : pipeLines)
		{
			pipeLine.stop();
		}
	}

	private Map<Object, JobQueue> loadJobQueues(JSONArray definitionArr, JSONObject templateConfigData)
	{
		LOGGER.info("Entry loadJobQueues..");
		Map<Object, JobQueue> queueMap = null;
		JSONObject definitionObj = null;
		JobQueue jobQueue = null;

		try
		{
			queueMap = new HashMap<Object, JobQueue>();
			for(Object arrObject : definitionArr)
			{
				definitionObj = (JSONObject) templateConfigData.get(arrObject);
				if("Queue".equalsIgnoreCase(definitionObj.get("type").toString()))
				{
					jobQueue = (JobQueue) applicationContext.getBean(definitionObj.get("class").toString());
					jobQueue.init((JSONObject) definitionObj.get("config"));
					jobQueue.setName(arrObject.toString());
					queueMap.put(arrObject, jobQueue);
				}
			}

			return queueMap;
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			queueMap = null;
			definitionObj = null;
			jobQueue = null;
			LOGGER.info("Exit loadJobQueues..");
		}
		return null;
	}
}