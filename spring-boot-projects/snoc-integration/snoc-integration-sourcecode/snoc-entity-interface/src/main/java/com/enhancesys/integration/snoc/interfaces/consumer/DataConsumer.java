package com.enhancesys.integration.snoc.interfaces.consumer;

import com.enhancesys.integration.snoc.beans.ResponseBean;
import com.enhancesys.integration.snoc.exception.ApplicationException;
/**
 * <b>Purpose:</b><br>
 * Interface to provide the data consumer operations for the multiple systems..<br>
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
 *  1        03-07-2020          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

public interface DataConsumer
{
	public ResponseBean processRequest(Long interfaceId, String queueName) throws ApplicationException;
}