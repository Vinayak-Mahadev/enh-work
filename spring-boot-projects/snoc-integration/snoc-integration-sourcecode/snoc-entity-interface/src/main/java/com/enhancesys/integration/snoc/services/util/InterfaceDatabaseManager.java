package com.enhancesys.integration.snoc.services.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;




public class InterfaceDatabaseManager 
{
	private static Logger LOGGER = Logger.getLogger(InterfaceDatabaseManager.class);
	
	/**
	 * @author Kannan
	 * <b>Algorithm:</b>
	 * <pre>	  
	 * 	1.Static block to load the PostgresDriverClass.   
	 * </pre>
	 */	
	static
	{
		try
		{
			LOGGER.debug("Loading the Postgres Driver..!");
			
//			Class.forName(IntegrationConstants.INTERFACE_JDBC_DRIVER);
		}
//		catch(ClassNotFoundException classNotFoundException)
//		{
//			LOGGER.error("ClassNotFoundException: ", classNotFoundException);
//		}
		catch(Exception exception)
		{
			LOGGER.error("Exception: ", exception);
		}
	}
	
	/**
	 * @author Kannan
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
		Connection connection = null;
		try
		{
		
			LOGGER.debug("Getting the Postgres Connection..!");
			
			if(connection == null || connection.isClosed())
			{
				connection = DriverManager.getConnection(IntegrationConstants.INTERFACE_JDBC_URL, IntegrationConstants.INTERFACE_JDBC_USER, IntegrationConstants.INTERFACE_JDBC_PASS);
				connection.setAutoCommit(true);
			}
		}
		catch(SQLException sqlException)
		{
			LOGGER.error("SQLException: ", sqlException);
		}
		catch(Exception exception)
		{
			LOGGER.error("Exception: ", exception);
		}
		return connection;
	}
	
}