package com.enhancesys.integration.response.services.jobengine;

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
 * 		Interface to provide the integration static response operations<br>
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
 *  1        23-08-2019          Suresh Upparu
 *    	-- Base Release
 *  </pre>
 * 
 * <br>
 */

@WebService(targetNamespace="http://com/enhancesys/integration/response/services/jobengine/JobEngineServices")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
public interface JobEngineServices
{
	@WebMethod(operationName = "getStaticResponse")
	public @WebResult(name = "getStaticResponse") String getStaticResponse(@WebParam(name = "moduleId") Long moduleId, @WebParam(name = "requestData") String requestData) throws Exception;
}