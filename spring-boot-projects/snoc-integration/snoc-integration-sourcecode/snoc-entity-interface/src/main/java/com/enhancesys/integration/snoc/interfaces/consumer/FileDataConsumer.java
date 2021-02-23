package com.enhancesys.integration.snoc.interfaces.consumer;

import com.enhancesys.integration.snoc.exception.ApplicationException;
/**
 * <b>Purpose:</b><br>
 * Interface to provide the file data consumer operations for the multiple systems..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        03-07-2014          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

public interface FileDataConsumer
{
	public void processRequest(Long interfaceId) throws ApplicationException;
}