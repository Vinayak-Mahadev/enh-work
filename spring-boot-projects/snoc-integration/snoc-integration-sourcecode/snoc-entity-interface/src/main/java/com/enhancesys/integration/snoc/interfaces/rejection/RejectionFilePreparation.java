package com.enhancesys.integration.snoc.interfaces.rejection;

import com.enhancesys.integration.snoc.exception.ApplicationException;

/**
 * <b>Purpose:</b>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations Private Limited<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       Mar 2, 2015			Parthipan Rajagopal
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface RejectionFilePreparation {
	public void processRequest(Long interfaceId,Long partnerId) throws ApplicationException;
}
