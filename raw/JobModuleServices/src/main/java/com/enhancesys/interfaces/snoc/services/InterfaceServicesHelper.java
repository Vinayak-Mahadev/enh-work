package com.enhancesys.interfaces.snoc.services;

import org.springframework.stereotype.Component;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

@Component
public class InterfaceServicesHelper
{
	public static BasicDBObject getInterfaceFromRequest(String request) throws Exception
	{
		return (BasicDBObject) ((BasicDBObject) ((BasicDBObject) 
			((BasicDBObject) JSON.parse(request)).get(Constants.F_ENVELOPE)).get(Constants.F_PAYLOAD)).get(Constants.F_INTERFACE);
	}
	
	public static BasicDBObject getHeaderFromRequest(String request) throws Exception
	{
		return ((BasicDBObject)((BasicDBObject)((BasicDBObject) JSON.parse(request)).get(Constants.F_ENVELOPE)).get(Constants.F_HEADER));
	}
}