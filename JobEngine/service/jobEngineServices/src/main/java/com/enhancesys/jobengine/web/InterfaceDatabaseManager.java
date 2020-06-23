package com.enhancesys.integration.response.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.enhancesys.integration.response.util.IntegrationConstants;


public class InterfaceDatabaseManager 
{
	private static Logger log = Logger.getLogger(InterfaceDatabaseManager.class);
	private static Connection connection;
	static
	{
		try
		{
			log.info("Loading the Postgres Driver..!");
			Class.forName(IntegrationConstants.INTERFACE_JDBC_DRIVER);
			log.info("Getting the Postgres Connection..!");
			getConnection();
		}
		catch(ClassNotFoundException classNotFoundException)
		{
			log.error("ClassNotFoundException: ", classNotFoundException);
		}
		catch(Exception exception)
		{
			log.error("Exception: ", exception);
		}
	}
	
	/**
	 * <b>Algorithm:</b>
	 * <pre>	  
	 * 	1.Check if the Connection object is already created.
	 * 	2.If the Connection object is not created
	 * 		then 
	 * 			Create a new instance of DBSessionManager object and return it.
	 * 		else
	 * 			return the Connection object which is already created.	 	
	 * </pre>
	 * @return Connection
	 */	
	public static Connection getConnection()
	{
		try
		{
			if(connection == null || connection.isClosed())
			{
				connection = DriverManager.getConnection(IntegrationConstants.INTERFACE_JDBC_URL, IntegrationConstants.INTERFACE_JDBC_USER, IntegrationConstants.INTERFACE_JDBC_PASS);
				connection.setAutoCommit(true);
			}
		}
		catch(SQLException sqlException)
		{
			log.error("SQLException: ", sqlException);
		}
		catch(Exception exception)
		{
			log.error("Exception: ", exception);
		}
		return connection;
	}
	
}