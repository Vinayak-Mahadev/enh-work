package com.enhancesys.jobengine.repo;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobengine.beans.Module;
import com.enhancesys.jobengine.beans.ModuleAttributes;

public class JobEngineMongoRepository implements JobEngineServiceRepository{

	private static Logger log = Logger.getLogger(JobEngineMongoRepository.class);

	public JobEngineMongoRepository() {
		log.info("You are using JobEngineMongoRepository");
	}
	
	@Override
	public Module getModule(Long moduleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleAttributes getModuleAttribute(Long attributeId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getModuleAttributesNameValue(Long moduleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject getJobParameter(Long moduleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
