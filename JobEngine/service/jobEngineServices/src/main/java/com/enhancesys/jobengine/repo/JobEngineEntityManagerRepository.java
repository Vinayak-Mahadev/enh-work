package com.enhancesys.jobengine.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobcommon.beans.EntityOperations;
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
 * Implementation of JobEngineServiceRepository for Beans and other properties also with help of entity manager 
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

public class JobEngineEntityManagerRepository implements JobEngineServiceRepository
{
	private static Logger log = Logger.getLogger(JobEngineEntityManagerRepository.class);

	private EntityManager entityManager;

	public JobEngineEntityManagerRepository() 
	{
		entityManager = EntityOperations.getEntityManager(null);
		log.info("You are using JobEngineEntityManagerRepository");
	}


	@Override
	public Status getStatus(Long statusId) throws Exception {
		Status status = null;
		try
		{
			status =  entityManager.find(Status.class, statusId);
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage(), e);
		}
		return status;
	}

	@Override
	public Status getStatusByName(String statusName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module getModule(Long moduleId) throws Exception 
	{
		Module module = null;
		try
		{
			module =  entityManager.find(Module.class, moduleId);
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage(), e);
		}
		return module;
	}

	@Override
	public Module getModuleByName(String moduleName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleAttribute getModuleAttribute(Long attributeId) throws Exception 
	{
		ModuleAttribute moduleAttr = null;
		try
		{
			moduleAttr =  entityManager.find(ModuleAttribute.class, attributeId);
		} 
		catch (Exception e) 
		{
			log.error(e.getMessage(), e);
		}
		return moduleAttr;
	}

	@Override
	public  List<ModuleAttribute> getModuleAttribute(long moduleId) throws Exception
	{
		List<ModuleAttribute> moduleAttribute = null;
		try 
		{
			moduleAttribute = new ArrayList<ModuleAttribute>(getModule(moduleId).getAttributes());
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		return moduleAttribute;
	}

	@Override
	public Map<Long, ModuleAttribute> getModuleAttributes(long moduleId) throws Exception 
	{
		Map<Long, ModuleAttribute> moduleAttributesMap = new HashMap<Long, ModuleAttribute>();

		try 
		{
			for (ModuleAttribute moduleAttribute : getModule(moduleId).getAttributes())
			{
				moduleAttributesMap.put(moduleAttribute.getAttributeId(), moduleAttribute);
			}	
		}
		catch (Exception e) 
		{
			log.error(e.getMessage(), e);
		}

		return moduleAttributesMap;
	}

	@Override
	public Map<String, String> getModuleAttributesNameValue(Long moduleId) throws Exception 
	{
		Map<String, String> attributeValues = new HashMap<String, String>();
		List<ModuleAttribute> moduleAttribute = null;
		try
		{
			moduleAttribute = getModuleAttribute(moduleId.longValue());

			for (ModuleAttribute moduleAttr : moduleAttribute)
			{
				attributeValues.put(moduleAttr.getName(), moduleAttr.getValue());
			}

		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			moduleAttribute = null;
		}
		return attributeValues;
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











//////////////////////////////////////////////////////////////////////////////////////////////////////////




