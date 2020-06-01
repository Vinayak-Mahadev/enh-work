package com.enhancesys.jobengine.services.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.jobengine.services.processor.JobProcessor;
import com.enhancesys.jobengine.services.queue.JobQueue;
import com.enhancesys.jobengine.services.util.DataConstants;
import com.enhancesys.jobengine.services.util.GenericProcessorException;

public class JobPipeLine implements Runnable 
{
	@Autowired 
	private ApplicationContext applicationContext;
	
	private BlockingQueue<Exception> failureQueue = null;
	private List<JobProcessor> processors = null;
	private JobQueue inputQueue = null;
	private JobQueue outputQueue = null;
	private String pipeLineName = null;
	private static Logger log = Logger.getLogger(JobPipeLine.class);
	
	@SuppressWarnings("unchecked")
	public void init(String pipeLineName, JSONObject pipeLineConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info(pipeLineName + " : Entry init..");
		JobProcessor jobProcessor = null;
		JSONObject processor = null;
		
		try
		{
			this.pipeLineName = pipeLineName;
			this.failureQueue = (BlockingQueue<Exception>) applicationContext.getBean("failureQueue");
			
			processors = new ArrayList<JobProcessor>(); 
			
			for(Object processorObj : (JSONArray) pipeLineConfig.get("processors"))
			{
				processor = (JSONObject) processorObj;
				log.info(pipeLineName + " : Processor : " + processor);
				jobProcessor = (JobProcessor) applicationContext.getBean(processor.get("class").toString());
				jobProcessor.init(pipeLineName, processor.get("class").toString(), processor, jobConfigurationReader, jobParameters); //processorConfig is from specified config using context path from templateConfig
				processors.add(jobProcessor);
			}
			log.info(pipeLineName + " : Processors size : " + processors.size());
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			jobProcessor = null;
			processor = null;
			log.info(pipeLineName + " : Exit init..");
		}
	}
	
	public void setInputQueue(JobQueue inputQueue)
	{
		this.inputQueue = inputQueue;
	}
	
	public void setOutputQueue(JobQueue outputQueue)
	{
		this.outputQueue = outputQueue;
	}

	public void run()
	{
		log.info(pipeLineName + " : Entry run..");
		JSONObject outputObject = null;
//		JSONObject payloadObject = null;
//		JSONArray dataArray = null;
		
		try
		{
			while(true)
			{
//				log.info(pipeLineName + " : failureQueue.size() : " + failureQueue.size());
				if(failureQueue.size() > 0)
				{
					log.error(pipeLineName + " : Exception occured, Stopping Pipeline : ");
					break;
				}
				
				if(inputQueue != null)
				{
					log.info(pipeLineName + " : Going to fetch item from Queue..");
					outputObject = inputQueue.dequeue(null);
					if(outputObject != null)
					{
						if(outputObject.containsKey(DataConstants.PAYLOAD))
						{
							//pass it to the processors in the sequence. If failed in any processor, skip or stop based on configuration/set status in outputObject to notify the next level processor.
							for(JobProcessor processor : processors)
							{
//								log.info(pipeLineName + " : " + processor);
								/*if(outputObject.get("status") != null && "Completed".equalsIgnoreCase(outputObject.get("status").toString()))
								{
									log.info(pipeLineName + " : breaking for loop on completion.. and going to publish emptyobject to next level if..");
									break; //breaks the for loop..
								}*/
								processor.process(outputObject);
							}
						}
						else
						{
							log.info(pipeLineName + " : pushing back empty object to the queue from : " + pipeLineName);
							inputQueue.enqueue(outputObject); //pushing back to the input queue on empty object to notify other parallel threads of same pipeline..
						}
					}
				}
				else
				{
					//the first processor will fill the data into jsonobject
//					processors.get(0).process(outputObject);
					outputObject = new JSONObject();
					for(JobProcessor processor : processors)
					{
//							log.info(pipeLineName + " : " + processor);
//							log.info(pipeLineName + " : outputObject : " + outputObject);
						if(outputObject.get("status") != null && "Completed".equalsIgnoreCase(outputObject.get("status").toString()))
						{
							log.info(pipeLineName + " : breaking for loop on completion. else.. and going to publish emptyobject to next level if..");
							break; //breaks the for loop.. what about the infinite loop.
						}
						processor.process(outputObject);
					}
				}
				
//				log.info(pipeLineName + " : outputQueue : " + outputQueue + " : " + outputObject);
				if(outputQueue != null && outputObject != null)
				{
					if(outputObject.containsKey(DataConstants.PAYLOAD))
					{
						JSONArray dataArr = (JSONArray) ((JSONObject) outputObject.get(DataConstants.PAYLOAD)).get(DataConstants.DATA_LIST);
						if(dataArr != null && !dataArr.isEmpty())
							outputQueue.enqueue(outputObject);
						if(outputObject.get("status") != null && "Completed".equalsIgnoreCase(outputObject.get("status").toString())) //to notify the next level pipeline on completion.
						{
							log.info(pipeLineName + " : Sending emptyObject to the next level to stop process on completion..");
							outputQueue.enqueue(new JSONObject());
							break; //breaks infinite loop..
						}
					}
					else
					{
						log.info(pipeLineName + " : Sending emptyObject to the next level to stop process..");
						outputQueue.enqueue(new JSONObject());
						break;//breaks infinite loop..
					}
				}
				else if(outputObject != null && (!outputObject.containsKey(DataConstants.PAYLOAD) 
						|| (outputObject.get("status") != null && "Completed".equalsIgnoreCase(outputObject.get("status").toString()))))
				{
//					log.error("breaking infinite loop.. outputObject : " + outputObject);
					break;
				}
			}
		}
		catch(Exception exception)
		{
			log.error(pipeLineName + " : Exception occured : Pipeline " + exception.getMessage());
			failureQueue.offer(exception);
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			stop();
		}
		finally
		{
			outputObject = null;
			log.info(pipeLineName + " : Exit run.." + pipeLineName);
			stop();
		}
	}
	
	public void stop()
	{
		try
		{
			for(JobProcessor processor : processors)
			{
				processor.stop();
			}
		}
		catch(Exception exception)
		{
			
		}
	}
}