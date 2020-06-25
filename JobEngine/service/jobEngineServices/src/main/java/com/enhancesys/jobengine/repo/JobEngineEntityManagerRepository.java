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

	public  List<ModuleAttribute> getModuleAttribute(long moduleId) 
	{
		List<ModuleAttribute> moduleAttribute = null;
		try 
		{
			moduleAttribute = new ArrayList<ModuleAttribute>(getModule(moduleId).getAttributes());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return moduleAttribute;
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

}
