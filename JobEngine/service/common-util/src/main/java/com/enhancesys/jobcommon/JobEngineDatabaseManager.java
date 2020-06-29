package com.enhancesys.jobcommon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * <b>Purpose:</b><br>
 * 		Datadbase singleton connection<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-05-2010		   Vinayak Mahadev (vinay.nagaraj@enhancesys.com)
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */

public class JobEngineDatabaseManager 
{
	private static Logger log = Logger.getLogger(JobEngineDatabaseManager.class);
	private static Connection connection;
	static
	{
		try
		{
			log.info("Loading the Postgres Driver..!");
			Class.forName(IntegrationConstants.JOBENGINE_JDBC_DRIVER);
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
				connection = DriverManager.getConnection(IntegrationConstants.JOBENGINE_JDBC_URL, IntegrationConstants.JOBENGINE_JDBC_USER, IntegrationConstants.JOBENGINE_JDBC_PASS);
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