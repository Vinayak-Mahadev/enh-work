package com.enhancesys.jobengine.repo;

import java.util.Map;

import org.json.simple.JSONObject;

import com.enhancesys.jobcommon.beans.Module;
import com.enhancesys.jobcommon.beans.ModuleAttribute;

public interface JobEngineServiceRepository 
{
	public Module getModule(Long moduleId) throws Exception;

	public ModuleAttribute getModuleAttribute(Long attributeId) throws Exception;
	
	public Map<String, String> getModuleAttributesNameValue(Long moduleId) throws Exception;
	
	public JSONObject getJobParameter(Long moduleId) throws Exception;

}
