package com.enhancesys.integration.services.dataengine.job.writer.queue;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobProcessor;
import com.enhancesys.integration.services.dataengine.job.components.DataWriter;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class ActiveMQDataPublisher extends JobProcessor implements DataWriter
{
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private Context context = null;
	private Session session = null;
	private boolean transacted;
	
	private static Logger log = Logger.getLogger(ActiveMQDataPublisher.class);
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfiguration, jobParameters);
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
		catch(Exception exception)
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
		log.info(_pipeLineName_ + " : Entry process.." + _processorName_ + " :jobCount: " + jobCount);
		JSONObject payloadObject = null;
		JSONArray jsonArray = null;
		JSONArray excludeFields = null;
		Destination destination = null;
		MessageProducer producer = null;
		ObjectMessage message = null;
		
		try
		{
			if(_outputConf_ == null)
			{
				log.error(_pipeLineName_ + " : " + _processorName_ + " : Output configuration not exist..");
				throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Output configuration not exist..");
			}
			if(!DataConstants.QUEUE.equalsIgnoreCase(_outputConf_.get(DataConstants.TYPE).toString()))
			{
				log.error(_pipeLineName_ + " : " + _processorName_ + " : Output type should be 'queue'..");
				throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Output type should be 'queue'..");
			}
			
			if(session == null)
			{
				initializeJMSConnection(_outputConf_);
			}

			excludeFields = (JSONArray) _outputConf_.get(DataConstants.EXCLUDE_FIELDS);
			
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					for(Object object : jsonArray)
					{
						if(excludeFields != null)
						{
							for(Object field : excludeFields)
							{
								((JSONObject)object).remove(field);
							}
						}
					}
					destination = (Destination)context.lookup("slQueue");
					//Using Message selector ObjectClass = "AlarmImpl"
					producer = session.createProducer(destination);
					message = session.createObjectMessage();
					message.setObject((Serializable) jobObject) ;// Messages
					log.info("Before Publishing");
					producer.send(message); 
					log.info("Published item and Closing connection");
				}
				else
					jobObject.put("status", "Completed");
			}
			
			log.info(_pipeLineName_ + " : " + _processorName_ + " : DataObject : " + jobObject);
			
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
			log.error(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage());
		}
		finally
		{
			payloadObject = null;
			jsonArray = null;
			excludeFields = null;
			destination = null;
			producer = null;
			message = null;
			log.info(_pipeLineName_ + " : Exit process.." + _processorName_);
		}
		return false;
	}
	
	private void initializeJMSConnection(JSONObject outputConf) throws GenericProcessorException
	{
		log.info("Entry initializeJMSConnection..");
		
		Properties properties = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		
		try
		{
			//JNDI properties
			properties = new Properties();
			properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, outputConf.get(DataConstants.INITIAL_CONTEXT_FACTORY).toString());
			properties.setProperty(Context.PROVIDER_URL, outputConf.get(DataConstants.PROVIDER_URL).toString());
			
			//specify queue propertyname as queue.jndiname
			properties.setProperty("queue.slQueue", outputConf.get(DataConstants.QUEUE_NAME).toString());
			
			context = new InitialContext(properties);
			connectionFactory = (ConnectionFactory)context.lookup(outputConf.get(DataConstants.CONNECTION_FACTORY).toString());
			connection = connectionFactory.createConnection(outputConf.get(DataConstants.USER).toString(), outputConf.get(DataConstants.PASSWORD).toString());
			connection.start();
			
			session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			
			log.info("Published item and Closing connection");
		}
		catch(Exception exception)
		{
			log.error(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			properties = null;
			connectionFactory = null;
			connection = null;
			
			log.info("Exit initializeJMSConnection..");
		}
	}
	public void stop()
	{
		
	}
}