package com.enhancesys.integration.snoc.interfaces.notification;

import java.util.Map;

import com.enhancesys.integration.snoc.beans.ResponseBean;
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
 * 1       Mar 2, 2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface NotificationInterface {
	public ResponseBean processRequest(Map<String,Object> notificationMap, Long transactionId, String targetAddress) throws ApplicationException;
}
