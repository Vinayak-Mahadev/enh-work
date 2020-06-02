package com.enhancesys.jobengine.job.services.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.jobengine.job.services.mongo.util.MongoDataUtil;
import com.enhancesys.jobengine.job.services.queue.JobQueue;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;

public class JobEngine 
{
	@Autowired 
	private ApplicationContext applicationContext;
	
	@Autowired
	private JobConfigurationReader jobConfigurationReader;
	
	@Autowired
	private MongoDataUtil mongoDataUtil;
	
	private List<Thread> pipeLines = null;
	private static Logger log = Logger.getLogger(JobEngine.class);
	
	public void init(JSONObject jobParameters)
	{
		log.info("Entry init..");
		
		JSONObject templateConfigData = null;
		JSONArray definitionArr = null;
		JSONObject defObject = null;
		JSONObject jobConfigData = null;
		JobPipeLine jobPipeLine = null;
		Thread thread = null;
		Map<Object, JobQueue> queueMap = null;
		long instances = 0;
		
		try
		{
			pipeLines = new ArrayList<Thread>();
			jobConfigurationReader.readConfiguration(jobParameters.get("template-id").toString(), jobParameters.get("job-id").toString(), jobParameters.get("job-data"));
			templateConfigData = jobConfigurationReader.getTemplateConfigData(null);
			if(jobParameters.get("request-id") != null)
			{
				jobConfigData = jobConfigurationReader.getJobConfigData(null);
				mongoDataUtil.updateStatus(jobParameters.get("request-id"), jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF), DataConstants.STATUS_IN_PROGRESS);
			}

			definitionArr = (JSONArray) templateConfigData.get("definition");
			log.info("definitionArr : " + definitionArr);

			queueMap = loadJobQueues(definitionArr, templateConfigData);
			log.info("queueMap : " + queueMap);
			
			for(int i = 0; i < definitionArr.size(); i ++)
			{
				defObject = (JSONObject) templateConfigData.get(definitionArr.get(i));
				if("PipeLine".equalsIgnoreCase(defObject.get("type").toString()))
				{
					instances = (Long) defObject.get("instance");
					jobPipeLine = (JobPipeLine) applicationContext.getBean(defObject.get("class").toString());
					jobPipeLine.init(definitionArr.get(i).toString(), defObject, jobConfigurationReader, jobParameters);
					
					log.info(definitionArr.get(i).toString() + " :" );
					if(i > 0 && queueMap.get(definitionArr.get(i-1)) != null)
					{
						log.info("InputQ : " + queueMap.get(definitionArr.get(i-1)).name);
						jobPipeLine.setInputQueue(queueMap.get(definitionArr.get(i-1)));
					}
					if((i+1 < definitionArr.size()) && definitionArr.get(i+1) != null && queueMap.get(definitionArr.get(i+1)) != null)
					{
						log.info("OutputQ : " + queueMap.get(definitionArr.get(i+1)).name);
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
				mongoDataUtil.updateStatus(jobParameters.get("request-id"), jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF), DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				log.error("GenericProcessor Exception : " + excep.getMessage(), excep);
			}
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
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
			log.info("Exit init..");
		}
	}
	
	public void start()
	{
		log.info("Entry start : pipeLine threads size : " + pipeLines.size());
		try
		{
			for(Thread pipeLine : pipeLines)
			{
				log.info("Starting pipeline : " + pipeLine.getName());
				pipeLine.start();
			}

			for(Thread pipeLine : pipeLines)
			{
				pipeLine.join();
			}
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info("Exit start..");
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
		log.info("Entry loadJobQueues..");
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
					jobQueue = (JobQueue) applicationContext.getBean(definitionObj.get("class").toString(), new Object[]{(JSONObject) definitionObj.get("config")});
					jobQueue.setName(arrObject.toString());
					queueMap.put(arrObject, jobQueue);
				}
			}
			
			return queueMap;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			queueMap = null;
			definitionObj = null;
			jobQueue = null;
			log.info("Exit loadJobQueues..");
		}
		return null;
	}
}