package com.enhancesys.integration.services.dataengine.job.connector.queue;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.job.connector.JobQueue;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class ActiveMQueue extends JobQueue
{
	protected String initialContextFactory = null;
	protected String providerUrl = null;
	protected String remoteConnectionFactory = null;
	protected String username = null;
	protected String password = null;
	protected String queueName = null;

	private Connection connection = null;
	private Destination destination = null;
	private static Logger log = Logger.getLogger(ActiveMQueue.class);

	public ActiveMQueue()
	{
		super();
	}

	@Override
	public void init(JSONObject configData)
	{
		super.init(configData);

		if(configData.get("initial_context_factory") != null && !configData.get("initial_context_factory").toString().trim().isEmpty())
			initialContextFactory = configData.get("initial_context_factory").toString();

		if(configData.get("provider_url") != null && !configData.get("provider_url").toString().trim().isEmpty())
			providerUrl = configData.get("provider_url").toString();

		if(configData.get("remote_connection_factory") != null && !configData.get("remote_connection_factory").toString().trim().isEmpty())
			remoteConnectionFactory = configData.get("remote_connection_factory").toString();

		if(configData.get("queue_name") != null && !configData.get("queue_name").toString().trim().isEmpty())
			queueName = configData.get("queue_name").toString();

		if(configData.get("username") != null && !configData.get("username").toString().trim().isEmpty())
			username = configData.get("username").toString();

		if(configData.get("password") != null && !configData.get("password").toString().trim().isEmpty())
			password = configData.get("password").toString();

		try 
		{
			prepareConnection();
		} 
		catch (GenericProcessorException genericProcessorException) 
		{
			log.error("Unhandled Exception : " + genericProcessorException.getMessage(), genericProcessorException);
		}

	}

	private void prepareConnection() throws GenericProcessorException
	{
		log.info("Entry prepareConnection..");
		Properties properties = null;
		Context context = null;
		ConnectionFactory connectionFactory = null;

		try
		{
			//JNDI properties
			properties = new Properties();
			//			properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
			properties.setProperty(Context.PROVIDER_URL, providerUrl);
			properties.setProperty("queue.slQueue", queueName);

			context = new InitialContext(properties);
			connectionFactory = (ConnectionFactory) context.lookup(remoteConnectionFactory);
			connection = connectionFactory.createConnection(username, password);
			connection.start();
			destination = (Destination) context.lookup("slQueue");

		}
		catch(JMSException jmsException)
		{
			log.error("JMSException : " + jmsException.getMessage(), jmsException);
			throw new GenericProcessorException("JMSException : " + jmsException.getMessage(), jmsException);
		}
		catch(Exception exception)
		{
			log.error("Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			properties = null;
			context = null;
			connectionFactory = null;
			log.info("Exit prepareConnection..");
		}
	}

	public static JobQueue createBean(JSONObject configData)
	{
		ActiveMQueue activeMQueue = new ActiveMQueue();
		activeMQueue.init(configData);
		return activeMQueue;
	}

	public void enqueue(JSONObject jobObject) throws GenericProcessorException
	{
		log.info("Entry enqueue..");
		Session session = null;
		MessageProducer producer = null;
		ObjectMessage message = null;
		boolean transacted = false;
		boolean flag = false;

		try
		{
			try
			{
				if(connection != null && connection.getMetaData() != null);
			}
			catch(Exception exception)
			{
				log.error("Connection got retired..");
				flag = true;
			}

			if(flag)
				prepareConnection();

			try
			{
				if(connection == null)
				{
					log.error("Connection is empty..");
					throw new GenericProcessorException("Connection is empty..");
				}
				else if(connection.getMetaData() != null);
			}
			catch(Exception exception)
			{
				log.error("Connection got retired..");
				throw new GenericProcessorException("Connection got retired..");
			}

			session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			message = session.createObjectMessage();
			message.setObject((Serializable) jobObject);
			producer.send(message); 
			log.info("Published item to the queue : " + queueName);

			producer.close();
			producer = null;
			session.close();
			session = null;
		}
		catch(JMSException jmsException)
		{
			log.error("JMSException : " + jmsException.getMessage());
			throw new GenericProcessorException("JMSException : " + jmsException.getMessage(), jmsException);
		}
		catch(Exception exception)
		{
			log.error("Exception Occured : " + exception.getMessage());
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(producer != null)
				{
					producer.close();
				}
				if(session != null)
				{
					session.close();
				}
			}
			catch(Exception exception)
			{
				log.error("Unhandled Exception : " + exception.getMessage(), exception);
			}

			session = null;
			producer = null;
			message = null;
			log.info("Exit enqueue..");
		}
	}

	public JSONObject dequeue(JSONObject jobObject) throws GenericProcessorException
	{
		log.info("Entry dequeue..");
		JSONObject messageObject = null;
		Message message = null;
		MessageConsumer consumer = null;
		Session session = null;
		boolean transacted = false;
		boolean flag = false;

		try
		{
			try
			{
				if(connection != null && connection.getMetaData() != null);
			}
			catch(Exception exception)
			{
				log.error("Connection got retired..");
				flag = true;
			}

			if(flag)
				prepareConnection();

			try
			{
				if(connection == null)
				{
					log.error("Connection is empty..");
					throw new GenericProcessorException("Connection is empty..");
				}
				else if(connection.getMetaData() != null);
			}
			catch(Exception exception)
			{
				log.error("Connection got retired..", exception);
				throw new GenericProcessorException("Connection got retired..");
			}

			session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
			consumer = session.createConsumer(destination);			
			log.info("Waiting for message...");
			message = consumer.receive(_pollWaitTime_);

			if(message == null)
			{
				return null;
			}

			if(message != null && message instanceof ActiveMQObjectMessage)
			{
				messageObject = (JSONObject) ((ActiveMQObjectMessage) message).getObject();
			}

			log.info("Received Response From : "+ queueName + messageObject);

			return messageObject;
		}
		catch(JMSException jmsException)
		{
			log.error("JMSException : " + jmsException.getMessage(), jmsException);
			throw new GenericProcessorException("JMSException : " + jmsException.getMessage(), jmsException);
		}
		catch(Exception exception)
		{
			log.error("Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(consumer != null)
				{
					consumer.close();
				}
				if(session != null)
				{
					session.close();
				}
			}
			catch(JMSException jmsException)
			{
				log.error(jmsException.getMessage(), jmsException);
			}

			messageObject = null;
			message = null;
			consumer = null;
			session = null;
			log.info("Exit dequeue..");
		}
	}
}