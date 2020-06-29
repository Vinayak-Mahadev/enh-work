package com.enhancesys.jobengine.repo;

import java.util.Map;

import org.json.simple.JSONObject;

import com.enhancesys.jobcommon.beans.Module;
import com.enhancesys.jobcommon.beans.ModuleAttribute;

/**
 * <b>Purpose:</b><br>
 * Property loading purpose<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * Generic methods for Beans and other properties also with help of different type of implementation.. 
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

public interface JobEngineServiceRepository 
{
	public Module getModule(Long moduleId) throws Exception;

	public ModuleAttribute getModuleAttribute(Long attributeId) throws Exception;
	
	public Map<String, String> getModuleAttributesNameValue(Long moduleId) throws Exception;
	
	public JSONObject getJobParameter(Long moduleId) throws Exception;

}
