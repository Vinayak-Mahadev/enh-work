package com.enhancesys.postgres.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.finevm.util.RDBMS;

public class App 
{
	public static void main(String[] args) 
	{
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try 
		{
			connection = RDBMS.getDBConnection("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/snoc2", "postgres", "postgres");
			statement = connection.prepareStatement("select * from interface.ms_interface order by 1;");
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
			{
				System.out.println(resultSet.getString(1) + "  " + resultSet.getString(2));
			}
			resultSet.close();
			statement.close();
			if(connection != null)
				connection.close();	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		finally 
		{
			System.gc();
		}
	}
}
