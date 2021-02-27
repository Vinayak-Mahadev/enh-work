package com.enhancesys.integration.snoc.services.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.enhancesys.integration.snoc.beans.ResponseBean;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IntegrationUtilManagementImpl implements IntegrationUtilManagement
{

	private static Logger LOGGER = Logger.getLogger(IntegrationUtilManagementImpl.class);
	
	@Override
	public void updateInterfaceFailerStatus(ResponseBean queueResponseBean) throws Exception {
		
	}

	@Override
	public void prepareNotificationJsonData(Long interfaceId, String scenario, Map<String, Object> inputMap)
			throws Exception {
		
	}

	@Override
	public void SendActiveMqMsg(String requestData) throws Exception 
	{
		
	}

	@Override
	public void sendNotificationForFileValidation(String input) 
	{

		Map<String,Object> notifiactionMap = null;
		Map<String,Object> inputMap = new HashMap<String, Object>();
		List<Map<String,Object>> notificationList = null;
		Map<String, Object> finalInputMap = null;
		try
		{
			notificationList = new ArrayList<Map<String,Object>>();
			finalInputMap = new HashMap<String, Object>();
			notifiactionMap = new LinkedHashMap<String,Object>();
			notifiactionMap.put("subject", IntegrationConstants.DWH_FV_SUBJECT);
			notifiactionMap.put("templateName", IntegrationConstants.DWH_FV_TEMPLATE);
			notifiactionMap.put("to", IntegrationConstants.DWH_FV_TO_ADDRESS);
			notifiactionMap.put("cc", "");
			notifiactionMap.put("bcc", "");
			notifiactionMap.put("media", IntegrationConstants.DWH_FV_MEDIA);
			notifiactionMap.put("language",IntegrationConstants.DWH_FV_LANG);

			inputMap = new HashMap<String, Object>();
			inputMap.put("fileNames",input);
			notifiactionMap.put("data", inputMap);
			notificationList.add(notifiactionMap);

			if(notificationList.size() > 0)
			{
				finalInputMap.put("notifications", notificationList);                        
				SendActiveMqMsg(new ObjectMapper().writeValueAsString(finalInputMap));
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Error occured : ",exception);
		}

	
	}

}
