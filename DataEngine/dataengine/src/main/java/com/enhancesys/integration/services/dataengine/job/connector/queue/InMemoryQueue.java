package com.enhancesys.integration.services.dataengine.job.connector.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.job.connector.JobQueue;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class InMemoryQueue extends JobQueue
{
	private BlockingQueue<JSONObject> queue = null;
	private static Logger LOGGER = Logger.getLogger(InMemoryQueue.class);
	private static int logForOffer;
	private static int logForFail;

	public InMemoryQueue() 
	{
		super();
	}

	@Override
	public void init(JSONObject configData)
	{
		super.init(configData);
		queue = new ArrayBlockingQueue<JSONObject>((int) _queueSize_);
	}
	
	public static JobQueue createBean(JSONObject configData)
	{
		InMemoryQueue inMemoryQueue = new InMemoryQueue();
		inMemoryQueue.init(configData);
		return inMemoryQueue;
	}

	public void enqueue(JSONObject jobObject) throws GenericProcessorException
	{
		LOGGER.debug(_name_ + " : Entry enqueue.." + jobObject);
		long waitTime = 0;
		long localRetryCount = 0;
		try
		{
			if(jobObject == null)
				return;

			while (true)
			{
				if(localRetryCount > _retryCount_)
				{
					LOGGER.error(_name_ + " : Retry limit reached, Unable to send the RequestData to the next level..");
					throw new GenericProcessorException(_name_ + " : Retry limit reached, Unable to send the RequestData to the next level..");
				}
				if (queue.size() <= _queueSize_ && queue.offer(jobObject, _pushWaitTime_, TimeUnit.MILLISECONDS))
				{
					if(logForOffer++ == 0 || logForOffer == 500) // below log will print every 500 queue records once 
					{
						LOGGER.info(_name_ + " : Sent RequestData to the next level :: queue size : " + queue.size());
						logForOffer = 1;			
					}
					break;
				}

				if((logForFail++ == 0 && localRetryCount == 0) || logForFail > 6) // below log will print every 6 queue records once 
				{
					LOGGER.error(_name_ + " : Failed to send RequestData to the next level.. : queue size : " + queue.size() + " : Retry Count : " + localRetryCount);
					logForFail = 1;			
				}

				if(localRetryCount == 0)
					Thread.sleep(_pushSleepTime_ - (_pushSleepTime_/5));
				else if(localRetryCount == 1)
					Thread.sleep(_pushSleepTime_ - (_pushSleepTime_/4));
				else if(localRetryCount == 2)
					Thread.sleep(_pushSleepTime_ - (_pushSleepTime_/3));
				else if(localRetryCount == 3)
					Thread.sleep(_pushSleepTime_ - (_pushSleepTime_/2));
				else
					Thread.sleep(_pushSleepTime_);

				localRetryCount ++;

				if(_maxWaitTime_ > 0)
				{
					waitTime = waitTime + (_pushWaitTime_ + _pushSleepTime_);
					if(waitTime > _maxWaitTime_)
					{	
						LOGGER.error(_name_ + " : Failed to send RequestData to the next level.. : queue size : " + queue.size() + " : Data : " + jobObject);
						LOGGER.error(_name_ + " : Max Wait Time reached, Unable to fetch the RequestData from Queue : " + _name_);
						throw new GenericProcessorException(_name_ + " : Max Wait Time reached, Unable to fetch the RequestData from Queue : " + _name_);
					}
				}
				continue;
			}
		}
		catch (InterruptedException interruptedException)
		{
			LOGGER.error(_name_ + " : Interrupted Exception : " + interruptedException.getMessage());
			throw new GenericProcessorException(_name_ + " : Interrupted Exception : " + interruptedException.getMessage(), interruptedException);
		}
		catch(Exception exception)
		{
			LOGGER.error(_name_ + " : Exception Occured : " + exception.getMessage());
			throw new GenericProcessorException(_name_ + " :Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			LOGGER.debug(_name_ + " : Exit enqueue..");
		}
	}

	@Override
	public JSONObject dequeue(JSONObject jobObject) throws GenericProcessorException
	{
		LOGGER.debug(_name_ + " : Entry dequeue..");
		JSONObject outputObject = null;
		//		long waitTime = 0;
		//		long localRetryCount = 0;

		try
		{
			while(true)
			{
				/*if(localRetryCount > retryCount)
				{
					log.error(name + " : Retry limit reached, Unable to fetch the RequestData from Queue : " + name);
					throw new GenericProcessorException(name + " : Retry limit reached, Unable to fetch the RequestData from Queue : " + name);
				}*/

				LOGGER.debug(_name_ + " : size : " + queue.size());
				outputObject = queue.peek();
				LOGGER.debug(_name_ + " : queue size : " + queue.size() + " : dequeue : " + outputObject);
				if(outputObject == null )
				{
					//					log.info(name + " : Trying to fetch from " + name);
					//					localRetryCount ++;
					Thread.sleep(_pollSleepTime_);
					//					
					//					if(maxWaitTime > 0)
					//					{
					//						waitTime = waitTime + (pushWaitTime + pushSleepTime);
					//						if(waitTime > maxWaitTime)
					//						{	
					//							log.error(name + " : Max Wait Time reached, Unable to fetch the RequestData from Queue : " + name);
					//							throw new GenericProcessorException(name + " : Max Wait Time reached, Unable to fetch the RequestData from Queue : " + name);
					//						}
					//					}
					continue;
				}
				if(!outputObject.containsKey(DataConstants.PAYLOAD))
					break;
				outputObject = queue.poll(_pollWaitTime_, TimeUnit.MILLISECONDS);
				if(outputObject != null)
					break;
			}

			//			Thread.sleep(1000L);
			return outputObject;
		}
		catch (InterruptedException interruptedException)
		{
			LOGGER.error(_name_ + " : Interrupted Exception : " + interruptedException.getMessage());
			throw new GenericProcessorException(_name_ + " : Interrupted Exception : " + interruptedException.getMessage(), interruptedException);
		}
		catch(Exception exception)
		{
			LOGGER.error(_name_ + " : Exception Occured : " + exception.getMessage());
			throw new GenericProcessorException(_name_ + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			outputObject = null;
			LOGGER.debug(_name_ + " : Exit dequeue..");
		}
	}
}