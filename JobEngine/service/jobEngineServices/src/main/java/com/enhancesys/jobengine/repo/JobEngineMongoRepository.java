package com.enhancesys.jobengine.repo;

import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.enhancesys.jobcommon.beans.Module;
import com.enhancesys.jobcommon.beans.ModuleAttribute;

/**
 * <b>Purpose:</b><br>
 * Property loading purpose<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * Implementation of JobEngineServiceRepository for Beans and other properties also with help of mongodb 
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
	public ModuleAttribute getModuleAttribute(Long attributeId) throws Exception {
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
