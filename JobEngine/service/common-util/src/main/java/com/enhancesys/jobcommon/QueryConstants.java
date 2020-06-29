package com.enhancesys.jobcommon;

/**
 * <b>Purpose:</b><br>
 * Query loading purpose<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
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

public interface QueryConstants 
{

	public static final String getModuleAttrValQry = "select NAME_V, VALUE_V from JOBENGINE.MS_MODULE_ATTR where MODULE_ID_N = ?";

}
