package com.enhancesys.integration.snoc.interfaces.callback;

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
 * 1       May 15, 2015			Parthipan Rajagopal
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface CallbackProcessor {
	public ResponseBean processRequest(String paramString1, String paramString2)
		    throws ApplicationException;
}
