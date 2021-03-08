package com.enhancesys.integration.snoc.interfaces.processor;

import java.util.Date;

import com.enhancesys.integration.snoc.exception.ApplicationException;

/**
 * <b>Purpose:</b><br>
 * 		Declaration of PullDataToFile services..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       23-10-2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface PullDataToFile 
{
	public void processRequest(Long interfaceId, Date requiredDate) throws ApplicationException;
}
