package com.enhancesys.integration.snoc.interfaces.converter;

import com.enhancesys.integration.snoc.exception.ApplicationException;
/**
 * <b>Purpose:</b><br>
 * Interface to provide the data convertion operations for the multiple systems..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        03-07-2020          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

public interface DataConverter
{
	public String processRequest(Long interfaceId, String requestData,String templateName) throws ApplicationException;
}