package com.enhancesys.jobengine.job.services.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;

public class InMemoryQueue extends JobQueue
{
	private BlockingQueue<JSONObject> queue = null;
	private static Logger log = Logger.getLogger(InMemoryQueue.class);
	
	public InMemoryQueue(JSONObject configData) 
	{
		super(configData);
		queue = new ArrayBlockingQueue<JSONObject>((int) queueSize);
	}

	public static JobQueue createBean(JSONObject configData)
	{
		return new InMemoryQueue(configData);
	}
	
	public void enqueue(JSONObject jobObject) throws GenericProcessorException
	{
//		log.info(name + " : Entry enqueue.." + jobObject);
		long waitTime = 0;
		long localRetryCount = 0;
		
		try
		{
			if(jobObject == null)
				return;
			
			while (true)
			{
				if(localRetryCount > retryCount)
				{
					log.error(name + " : Retry limit reached, Unable to send the RequestData to the next level..");
					throw new GenericProcessorException(name + " : Retry limit reached, Unable to send the RequestData to the next level..");
				}
				if (queue.size() < queueSize && queue.offer(jobObject, pushWaitTime, TimeUnit.MILLISECONDS))
				{
					log.info(name + " : Sent RequestData to the next level.. size : " + queue.size());
					break;
				}
				
				log.error(name + " : Failed to send RequestData to the next level.. : size : " + queue.size() + " : Data : " + jobObject);
				localRetryCount ++;
				Thread.sleep(pushSleepTime);
				
				if(maxWaitTime > 0)
				{
					waitTime = waitTime + (pushWaitTime + pushSleepTime);
					if(waitTime > maxWaitTime)
					{	
						log.error(name + " : Max Wait Time reached, Unable to fetch the RequestData from Queue : " + name);
						throw new GenericProcessorException(name + " : Max Wait Time reached, Unable to fetch the RequestData from Queue : " + name);
					}
				}
				continue;
			}
		}
		catch (InterruptedException interruptedException)
		{
			log.error(name + " : Interrupted Exception : " + interruptedException.getMessage());
			throw new GenericProcessorException(name + " : Interrupted Exception : " + interruptedException.getMessage(), interruptedException);
		}
		catch(Exception exception)
		{
			log.error(name + " : Exception Occured : " + exception.getMessage());
			throw new GenericProcessorException(name + " :Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info(name + " : Exit enqueue..");
		}
	}

	@Override
	public JSONObject dequeue(JSONObject jobObject) throws GenericProcessorException
	{
		log.info(name + " : Entry dequeue..");
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
				
				log.info(name + " : size : " + queue.size());
				outputObject = queue.peek();
//				log.error(name + " : dequeue : " + outputObject);
				if(outputObject == null )
				{
//					log.info(name + " : Trying to fetch from " + name);
//					localRetryCount ++;
					Thread.sleep(pollSleepTime);
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
				outputObject = queue.poll(pollWaitTime, TimeUnit.MILLISECONDS);
				if(outputObject != null)
					break;
			}
			
//			Thread.sleep(1000L);
			return outputObject;
		}
		catch (InterruptedException interruptedException)
		{
			log.error(name + " : Interrupted Exception : " + interruptedException.getMessage());
			throw new GenericProcessorException(name + " : Interrupted Exception : " + interruptedException.getMessage(), interruptedException);
		}
		catch(Exception exception)
		{
			log.error(name + " : Exception Occured : " + exception.getMessage());
			throw new GenericProcessorException(name + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			outputObject = null;
			log.info(name + " : Exit dequeue..");
		}
	}
}