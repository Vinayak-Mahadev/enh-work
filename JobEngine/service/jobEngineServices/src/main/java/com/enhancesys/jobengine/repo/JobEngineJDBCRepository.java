package com.enhancesys.jobengine.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobcommon.JobEngineDatabaseManager;
import com.enhancesys.jobcommon.QueryConstants;
import com.enhancesys.jobcommon.beans.Module;
import com.enhancesys.jobcommon.beans.ModuleAttribute;

/**
 * <b>Purpose:</b><br>
 * Property loading purpose<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * Implementation of JobEngineServiceRepository for Beans and other properties also with help of Jdbc
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

public class JobEngineJDBCRepository implements JobEngineServiceRepository
{

	private static Logger log = Logger.getLogger(JobEngineJDBCRepository.class);


	public JobEngineJDBCRepository() 
	{
		log.info("You are using JobEngineJDBCRepository");
	}


	@Override
	public Module getModule(Long moduleId) throws Exception 
	{
		Module module = null;
		try
		{
			module = new Module();
			module.setName("test module");
			module.setModuleId(moduleId);
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage(), e);
		}
		return null;
	}


	@Override
	public ModuleAttribute getModuleAttribute(Long attributeId) throws Exception 
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Map<String, String> getModuleAttributesNameValue(Long moduleId) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Map<String, String> attributeValues = new HashMap<String, String>();
		try
		{
			connection = JobEngineDatabaseManager.getConnection();
			statement = connection.prepareStatement(QueryConstants.getModuleAttrValQry);
			statement.setLong(1, moduleId);

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


	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getJobParameter(Long moduleId) throws Exception {


		Map<String, String> attributeValues = null;
		JSONObject jobParameter = null;
		try
		{
			attributeValues = getModuleAttributesNameValue(moduleId);
			jobParameter = new JSONObject();
			jobParameter.put("job-id", attributeValues.get("job-id"));
			jobParameter.put("template-id", attributeValues.get("template-id"));

		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			attributeValues = null;
		}
		return jobParameter;
	}


}
