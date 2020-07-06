package com.enhancesys.jobengine.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobcommon.JobEngineDatabaseManager;
import com.enhancesys.jobcommon.QueryConstants;
import com.enhancesys.jobcommon.beans.Module;
import com.enhancesys.jobcommon.beans.ModuleAttribute;
import com.enhancesys.jobcommon.beans.ModuleFileSummary;
import com.enhancesys.jobcommon.beans.ModuleFileSummaryDetails;
import com.enhancesys.jobcommon.beans.Status;

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
	public Status getStatus(Long statusId) throws Exception 
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Status status = null;
		try
		{
			connection = JobEngineDatabaseManager.getConnection();
			statement = connection.prepareStatement(QueryConstants.getStatusQry);
			statement.setLong(1, statusId);

			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				status = new Status();
				status.setStatusId(statusId);
				status.setName(resultSet.getString("name_v"));
				status.setEndDate(resultSet.getDate("end_date_dt"));
				status.setStartDate(resultSet.getDate("start_date_dt"));
			}

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
		return status;
	}

	@Override
	public Module getModule(Long moduleId) throws Exception 
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Module module = null;
		try
		{
			connection = JobEngineDatabaseManager.getConnection();
			statement = connection.prepareStatement(QueryConstants.getModuleQry);
			statement.setLong(1, moduleId);

			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				module = new Module();
				module.setModuleId(moduleId);
				module.setName(resultSet.getString("name_v"));
				module.setModuleType(resultSet.getLong("module_type_n"));
				module.setTransactionType(resultSet.getLong("trans_type_n"));
				module.setSequence(resultSet.getLong("seq_n"));
				module.setConverter(resultSet.getString("converter_v"));
				module.setPublisher(resultSet.getString("publisher_v"));
				module.setResponseProcessor(resultSet.getString("response_processor_v"));
				module.setLastUpdatedTime(resultSet.getDate("last_updated_time_dt"));
			}

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
		return module;
	}

	@Override	
	public ModuleAttribute getModuleAttribute(Long attributeId) throws Exception 
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ModuleAttribute moduleAttribute = null;
		try
		{
			connection = JobEngineDatabaseManager.getConnection();
			statement = connection.prepareStatement(QueryConstants.getModuleAttrQry);
			statement.setLong(1, attributeId);

			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				moduleAttribute = new ModuleAttribute();
				moduleAttribute.setAttributeId(resultSet.getLong("attribute_id_n"));
				moduleAttribute.setName(resultSet.getString("name_v"));
				moduleAttribute.setValue(resultSet.getString("value_v"));
				moduleAttribute.setLastUpdatedTime(resultSet.getDate("last_updated_time_dt"));

				moduleAttribute.getModule();
			}

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
		return moduleAttribute;
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

	@Override
	public Status getStatusByName(String statusName) throws Exception 
	{

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Status status = null;
		try
		{
			connection = JobEngineDatabaseManager.getConnection();
			statement = connection.prepareStatement(QueryConstants.getStatusByNameQry);
			statement.setString(1, statusName);

			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				status = new Status();
				status.setStatusId(resultSet.getLong("status_n"));
				status.setName(resultSet.getString(statusName));
				status.setEndDate(resultSet.getDate("end_date_dt"));
				status.setStartDate(resultSet.getDate("start_date_dt"));
			}

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
		return status;
	}

	@Override
	public Module getModuleByName(String moduleName) throws Exception 
	{


		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Module module = null;
		try
		{
			connection = JobEngineDatabaseManager.getConnection();
			statement = connection.prepareStatement(QueryConstants.getModuleByNameQry);
			statement.setString(1, moduleName);

			resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				module = new Module();
				module.setModuleId(resultSet.getLong("module_id_n"));
				module.setName(resultSet.getString(moduleName));
				module.setModuleType(resultSet.getLong("module_type_n"));
				module.setTransactionType(resultSet.getLong("trans_type_n"));
				module.setSequence(resultSet.getLong("seq_n"));
				module.setConverter(resultSet.getString("converter_v"));
				module.setPublisher(resultSet.getString("publisher_v"));
				module.setResponseProcessor(resultSet.getString("response_processor_v"));
				module.setLastUpdatedTime(resultSet.getDate("last_updated_time_dt"));
			}

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
		return module;
	
	}

	@Override
	public Map<Long, ModuleAttribute> getModuleAttributes(long moduleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModuleAttribute> getModuleAttribute(long moduleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleFileSummaryDetails getModuleFileSummaryDetails(Long fileId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleFileSummary getModuleFileSummary(Long fileId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleFileSummary getModuleFileSummaryByFileName(String fileName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
