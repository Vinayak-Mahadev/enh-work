package com.enhancesys.integration.services.dataengine.util.pg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.enhancesys.integration.services.dataengine.util.DataConstants;

public class JdbcUtility 
{
	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	protected DataSource dataSource;

	private static Logger LOGGER = Logger.getLogger(JdbcUtility.class);

	@SuppressWarnings("unchecked")
	public 	List<JsonNode> getDBData(Object request)throws Exception
	{
		List<JsonNode> resultList = null;
		try
		{
			LOGGER.info("jdbcTemplate :: "+jdbcTemplate);
			ObjectNode jsonObject = (ObjectNode)request;
			ObjectNode paramsNode = (ObjectNode)jsonObject.get(DataConstants.PRAMS_MAP);
			String query = ((JsonNode) jsonObject.get(DataConstants.PG_QUERY)).asText();
			HashMap<String,Object> paramsMap = new ObjectMapper().readValue(paramsNode, HashMap.class);
			resultList = jdbcTemplate.query(query, paramsMap,new JsonNodeRowMapper(new ObjectMapper()));
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			LOGGER.error(" Exception occurred in JDBCUtility :: " + exception.getMessage());
			throw exception;
		}
		return resultList;
	}

	public ResultSet getResult(String query)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try 
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} 
		catch (SQLException e) 
		{
			LOGGER.error(e.getMessage(), e);
		}
		finally
		{
			try 
			{
				if(connection!=null && !connection.isClosed())
				{
					connection.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return resultSet;
	}

	public int insertData(String query)
	{
		Connection connection = null;
		Statement statement = null;
		int affectedRowCount = 0;
		try
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			affectedRowCount = statement.executeUpdate(query);
		} 
		catch (SQLException e) 
		{
			LOGGER.error(e.getMessage(), e);
		}
		finally
		{
			try 
			{
				if(connection!=null && !connection.isClosed())
				{
					connection.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return affectedRowCount;
	}
}