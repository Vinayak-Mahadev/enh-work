package com.enhancesys.integration.response.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class InterfaceDBUtil
{
	private static Logger log = Logger.getLogger(InterfaceDBUtil.class);
	public Map<String, String> getInterfaceAttributes(Long interfaceId) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Map<String, String> attributeValues = null;
		try
		{
			attributeValues = new HashMap<String, String>();
			connection = InterfaceDatabaseManager.getConnection();
			statement = connection.prepareStatement(IntegrationConstants.getInterfaceAttrValQry);
			statement.setLong(1, interfaceId);
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				attributeValues.put(resultSet.getString("name_v"), resultSet.getString("value_v"));
			}
			
			return attributeValues;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception)
			{
				log.error(exception.getMessage(), exception);
			}
		}
	}
	
	
}