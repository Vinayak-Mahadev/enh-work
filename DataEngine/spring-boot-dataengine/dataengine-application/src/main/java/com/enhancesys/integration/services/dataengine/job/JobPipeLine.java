package com.enhancesys.integration.services.dataengine.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.integration.services.dataengine.job.connector.JobQueue;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class JobPipeLine implements Runnable 
{
	@Autowired 
	private ApplicationContext applicationContext;
	
	private BlockingQueue<Exception> _failureQueue_;
	private List<JobProcessor> _processors_;
	private JobQueue _inputQueue_;
	private JobQueue _outputQueue_;
	private String _pipeLineName_;
	private static Logger LOGGER = Logger.getLogger(JobPipeLine.class);
	
	@SuppressWarnings("unchecked")
	public void init(String pipeLineName, JSONObject pipeLineConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		LOGGER.info(pipeLineName + " : Entry init..");
		JobProcessor jobProcessor = null;
		JSONObject processor = null;
		
		try
		{
			this._pipeLineName_ = pipeLineName;
			this._failureQueue_ = (BlockingQueue<Exception>) applicationContext.getBean("failureQueue");
			
			_processors_ = new ArrayList<JobProcessor>(); 
			
			for(Object processorObj : (JSONArray) pipeLineConfig.get("processors"))
			{
				processor = (JSONObject) processorObj;
				LOGGER.info(pipeLineName + " : Processor : " + processor);
				jobProcessor = (JobProcessor) applicationContext.getBean(processor.get("class").toString());
				jobProcessor.init(pipeLineName, processor.get("class").toString(), processor, jobConfiguration, jobParameters); //processorConfig is from specified config using context path from templateConfig
				_processors_.add(jobProcessor);
			}
			LOGGER.info(pipeLineName + " : Processors size : " + _processors_.size());
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			jobProcessor = null;
			processor = null;
			LOGGER.info(pipeLineName + " : Exit init..");
		}
	}
	
	public void setInputQueue(JobQueue inputQueue)
	{
		this._inputQueue_ = inputQueue;
	}
	
	public void setOutputQueue(JobQueue outputQueue)
	{
		this._outputQueue_ = outputQueue;
	}

	public void run()
	{
		LOGGER.info(_pipeLineName_ + " : Entry run..");
		JSONObject outputObject = null;
//		JSONObject payloadObject = null;
//		JSONArray dataArray = null;
		
		try
		{
			while(true)
			{
//				log.info(pipeLineName + " : failureQueue.size() : " + failureQueue.size());
				if(_failureQueue_.size() > 0)
				{
					LOGGER.error(_pipeLineName_ + " : Exception occured, Stopping Pipeline : ");
					break;
				}
				
				if(_inputQueue_ != null)
				{
					LOGGER.debug(_pipeLineName_ + " : Going to fetch item from Queue..");
					outputObject = _inputQueue_.dequeue(null);
					if(outputObject != null)
					{
						if(outputObject.containsKey(DataConstants.PAYLOAD))
						{
							//pass it to the processors in the sequence. If failed in any processor, skip or stop based on configuration/set status in outputObject to notify the next level processor.
							for(JobProcessor processor : _processors_)
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
							LOGGER.info(_pipeLineName_ + " : pushing back empty object to the queue from : " + _pipeLineName_);
							_inputQueue_.enqueue(outputObject); //pushing back to the input queue on empty object to notify other parallel threads of same pipeline..
						}
					}
				}
				else
				{
					//the first processor will fill the data into jsonobject
//					processors.get(0).process(outputObject);
					outputObject = new JSONObject();
					for(JobProcessor processor : _processors_)
					{
//							log.info(pipeLineName + " : " + processor);
//							log.info(pipeLineName + " : outputObject : " + outputObject);
						if(outputObject.get("status") != null && "Completed".equalsIgnoreCase(outputObject.get("status").toString()))
						{
							LOGGER.info(_pipeLineName_ + " : breaking for loop on completion. else.. and going to publish emptyobject to next level if..");
							break; //breaks the for loop.. what about the infinite loop.
						}
						processor.process(outputObject);
					}
				}
				
//				log.info(pipeLineName + " : outputQueue : " + outputQueue + " : " + outputObject);
				if(_outputQueue_ != null && outputObject != null)
				{
					if(outputObject.containsKey(DataConstants.PAYLOAD))
					{
						JSONArray dataArr = (JSONArray) ((JSONObject) outputObject.get(DataConstants.PAYLOAD)).get(DataConstants.DATA_LIST);
						if(dataArr != null && !dataArr.isEmpty())
							_outputQueue_.enqueue(outputObject);
						if(outputObject.get("status") != null && "Completed".equalsIgnoreCase(outputObject.get("status").toString())) //to notify the next level pipeline on completion.
						{
							LOGGER.info(_pipeLineName_ + " : Sending emptyObject to the next level to stop process on completion..");
							_outputQueue_.enqueue(new JSONObject());
							break; //breaks infinite loop..
						}
					}
					else
					{
						LOGGER.info(_pipeLineName_ + " : Sending emptyObject to the next level to stop process..");
						_outputQueue_.enqueue(new JSONObject());
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
			LOGGER.error(_pipeLineName_ + " : Exception occured : Pipeline " + exception.getMessage());
			_failureQueue_.offer(exception);
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			stop();
		}
		finally
		{
			outputObject = null;
			LOGGER.info(_pipeLineName_ + " : Exit run.." + _pipeLineName_);
			stop();
		}
	}
	
	public void stop()
	{
		try
		{
			for(JobProcessor processor : _processors_)
			{
				processor.stop();
			}
		}
		catch(Exception exception)
		{
			
		}
	}
}