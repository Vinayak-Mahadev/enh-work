package com.enhancesys.jobengine.job.services.pg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.enhancesys.jobengine.job.services.util.GenericProcessorException;

@Component
public class JdbcConnectionUtil 
{
	private String driverClassName = null;
	private String url = null;
	private String username = null;
	private String password = null;
	private String socketTimeout = null;
	private static Logger log = Logger.getLogger(JdbcConnectionUtil.class);
	
	public JdbcConnectionUtil(String driverClassName, String url, String username, String password, String socketTimeout)
	{
		this.driverClassName = driverClassName;
		this.url = url;
		this.username = username;
		this.password = password;
		this.socketTimeout = socketTimeout;
	}
	
	public Connection getConnection() throws GenericProcessorException
	{
		Connection connection = null;
		Properties properties = null;
		
		try
		{
			properties = new Properties();
			properties.setProperty("user", username);
			properties.setProperty("password", password);
			if(socketTimeout != null && !socketTimeout.trim().isEmpty())
				properties.setProperty("socketTimeout", socketTimeout);
			Class.forName(driverClassName);
			connection = DriverManager.getConnection(url, properties);
			return connection;
		}
		catch(Exception exception)
		{
			log.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			connection = null;
			properties = null;
		}
	}
}
