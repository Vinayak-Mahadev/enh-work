package com.enhancesys.jobengine.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

/**
 * <b>Purpose:</b><br>
 * 		JobEngine invoker<br>
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
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-05-2010		   Vinayak Mahadev (vinay.nagaraj@enhancesys.com)
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */

@WebService(targetNamespace="http://com/enhancesys/jobengine/web/JobEngineServices")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface JobEngineServices
{
	@WebMethod(operationName = "getResponse")
	public @WebResult(name = "getResponse") String getResponse(@WebParam(name = "moduleId") Long moduleId, @WebParam(name = "requestData") String requestData) throws Exception;
	
	@WebMethod(operationName = "processModule")
	public @WebResult(name = "processModule") String processModule(@WebParam(name = "moduleId") Long moduleId, @WebParam(name = "requestData") String requestData) throws Exception;
}