package com.enhancesys.integration.snoc.interfaces.cleanup;

import com.enhancesys.integration.snoc.exception.ApplicationException;

/**
 * <b>Purpose:</b><br>
 * 		Declaration of processCleanUp services..<br>
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
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       05-09-2020			Vinayak-Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface CleanUpProcessor 
{
	public void processRequest(Long interfaceId, Long cleanupId, String userId, Long cleanupCode) throws ApplicationException;
}
