package com.enhancesys.integration.snoc.services;

import com.enhancesys.integration.snoc.beans.CreateOrUpdateSOBean;
import com.enhancesys.integration.snoc.beans.UpdateSOResponseBean;
import com.enhancesys.integration.snoc.exception.ApplicationException;
import com.enhancesys.integration.snoc.services.interfaces.ExternalInterfaceLocal;

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
 * 		Enhancesys Innovations 2021<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        23-01-2021          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

//@Transactional(rollbackFor = ApplicationException.class)
//@Component
//@Endpoint
@javax.jws.WebService(serviceName = "ExternalInterface", portName = "snoc",
targetNamespace = "http://com/enhancesys/entities/schema/integration/",
endpointInterface = "com.enhancesys.integration.snoc.services.interfaces.ExternalInterface")
public class ExternalInterfaceImpl implements ExternalInterfaceLocal
{

	@Override
	public UpdateSOResponseBean updateSOStatus(CreateOrUpdateSOBean createOrUpdateSOBean) throws ApplicationException
	{
		System.out.println(createOrUpdateSOBean);
		return new UpdateSOResponseBean();
	}

}