package com.enhancesys.integration.snoc.services.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.WebFault;

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
 *  1        23-01-2021          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

@WebService(targetNamespace = "http://com/enhancesys/entities/schema/integration/" , name = "ExternalInterface")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface ExternalInterface 
{
	static final String NAMESPACE_URI = "http://com/enhancesys/entities/schema/integration";

//	@ResponsePayload
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateOrUpdateSOBean")
	@WebMethod(operationName = "updateSOStatus")
	public @WebResult(name = "updateSOStatus") UpdateSOResponseBean updateSOStatus(CreateOrUpdateSOBean createOrUpdateSOBean) throws ApplicationException;


	/*==========================================================================================================================================*/ 
	
	@WebFault(name = "UpdateSOStatusException")
//	@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateSOStatusException extends ApplicationException
	{
		private static final long serialVersionUID = 1L;

		public UpdateSOStatusException()
		{
			super();
		}
		public  UpdateSOStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}
}