package com.enhancesys.integration.snoc.services.util;

import java.util.Map;

import com.enhancesys.integration.snoc.beans.ResponseBean;




/**
 * <b>Purpose:</b><br>
 * 		Interface to provide the integration operations<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        22-07-2014          Ayyandurai
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */

public interface IntegrationUtilManagementLocal
{
//	public void updateInterfaceSummaryFailerStatus(Long transactionId) throws Exception;	
	
	public void updateInterfaceFailerStatus(ResponseBean queueResponseBean) throws Exception;
	
	public void prepareNotificationJsonData(Long interfaceId,String scenario,Map<String,Object> inputMap) throws Exception;
	
	public void SendActiveMqMsg(String requestData) throws Exception;
}