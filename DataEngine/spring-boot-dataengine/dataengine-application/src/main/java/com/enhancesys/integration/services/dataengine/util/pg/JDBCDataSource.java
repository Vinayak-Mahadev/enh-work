package com.enhancesys.integration.services.dataengine.util.pg;

import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.enhancesys.integration.services.dataengine.util.DataConstants;

/**
 * <b>Purpose:</b><br>
 * 		Class to create the JDBCDatasource for the given parameters..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        24-09-2018          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public class JDBCDataSource extends DriverManagerDataSource 
{
	public JDBCDataSource() 
	{
		this(DataConstants.POSTGRES_JDBC_DRIVER, DataConstants.POSTGRES_JDBC_URL, DataConstants.POSTGRES_JDBC_USER, DataConstants.POSTGRES_JDBC_PASS, DataConstants.POSTGRES_JDBC_SOCKET_TIMEOUT);
	} 
	public JDBCDataSource(String driverClassName, String url, String username, String password, String socketTimeout) 
	{
		Properties connectionProperties = null;
		
		try
		{
			setDriverClassName(driverClassName);
			setUrl(url);
			setUsername(username);
			setPassword(password);
			connectionProperties = new Properties();
			connectionProperties.setProperty("socketTimeout", socketTimeout);
			setConnectionProperties(connectionProperties);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}
