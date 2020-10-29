package com.enhancesys.integration.services.dataengine.controller.util;

import org.springframework.stereotype.Component;

import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

@Component
public class ReportServicesHelper
{
	public static BasicDBObject getInterfaceFromRequest(String request) throws Exception
	{
		return (BasicDBObject) ((BasicDBObject) ((BasicDBObject) 
				((BasicDBObject) JSON.parse(request)).get(DataConstants.F_ENVELOPE)).get(DataConstants.F_PAYLOAD)).get(DataConstants.F_INTERFACE);
	}

	public static BasicDBObject getHeaderFromRequest(String request) throws Exception
	{
		return ((BasicDBObject)((BasicDBObject)((BasicDBObject) JSON.parse(request)).get(DataConstants.F_ENVELOPE)).get(DataConstants.F_HEADER));
	}

	public static String getFileNameFromRequest(String request) throws Exception
	{
		return ((BasicDBObject) JSON.parse(request)).get(DataConstants.F_FILENAME).toString();
	}

}