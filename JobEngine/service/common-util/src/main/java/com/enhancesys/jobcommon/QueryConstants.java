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

	public static final String getStatusQry = "select * from jobengine.sd_status where status_n = ?";
	public static final String getStatusByNameQry = "select * from jobengine.sd_status where name_v = ?";
	public static final String getModuleQry = "select * from jobengine.ms_module where module_id_n = ?";
	public static final String getModuleByNameQry = "select * from jobengine.ms_module where name_v = ?";
	public static final String getModuleAttrQry = "select * from jobengine.ms_module_attr where attribute_id_n = ?";
	public static final String getModuleAttrValQry = "select name_v, value_v from jobengine.ms_module_attr where module_id_n = ?";

}
