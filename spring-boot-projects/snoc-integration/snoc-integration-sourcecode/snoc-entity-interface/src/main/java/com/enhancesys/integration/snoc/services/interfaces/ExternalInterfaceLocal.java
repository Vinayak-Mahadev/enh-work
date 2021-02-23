package com.enhancesys.integration.snoc.services.interfaces;

import com.enhancesys.integration.snoc.beans.CreateOrUpdateSOBean;
import com.enhancesys.integration.snoc.beans.UpdateSOResponseBean;
import com.enhancesys.integration.snoc.exception.ApplicationException;

/**
 * <b>Purpose:</b><br>
 * 		Interface to provide third party integration operations<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        23-07-2019          Suresh Upparu
 *  -- Base Release
 * </pre>
 * 
 * <br>
 */
public interface ExternalInterfaceLocal 
{
	public UpdateSOResponseBean updateSOStatus(CreateOrUpdateSOBean updateSOStatus) throws ApplicationException;
}
