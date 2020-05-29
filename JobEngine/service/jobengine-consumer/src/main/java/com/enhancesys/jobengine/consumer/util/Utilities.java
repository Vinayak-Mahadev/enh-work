package com.enhancesys.jobengine.consumer.util;

import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.enhancesys.common.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities 
{

	private final String responceTemplate = Constants._JOB_CONSUMER_SERVICE_RESPONCE_TEMP!=null ? Constants._JOB_CONSUMER_SERVICE_RESPONCE_TEMP : "{\"payload\":null,\"status-code\":null,\"status-msg\":null,\"responce-date\":null}";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public final JSONObject getResponceJSON() 
	{
		JSONObject json = null;
		
		try 
		{
			json = new JSONObject(responceTemplate);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	} 



	public final JSONObject getSuccessResponceJSON(Object obj) 
	{
		JSONObject json = null;
		String jsonStr  = null;
		Object childJson = null;
		
		try 
		{
			jsonStr = objectMapper.writeValueAsString(obj);
			if(obj instanceof List<?>) {
				childJson =  new JSONArray(jsonStr);
			}
			else {
				childJson =  new JSONObject(jsonStr);
			}

			json = new JSONObject(responceTemplate);
			json.put("payload", childJson);
			json.put("status-msg", "SUCCESS");
			json.put("responce-date", new Date());
			json.put("status-code", 200l);
		} 
		catch (Exception e) 
		{
			json = getInternalProblemResponceJSON(null);
			e.printStackTrace();
		}
		finally 
		{
			jsonStr  = null;
			childJson = null;
		}
		return json;
	} 

	public final JSONObject getFailResponceJSON(Object obj)
	{
		JSONObject json = null;
	
		try 
		{
			json = new JSONObject(responceTemplate);
			json.put("payload", obj);
			json.put("status-msg", "No records found");
			json.put("responce-date", new Date());
			json.put("status-code", 204l);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return json;
	} 	

	public final JSONObject getFailResponceJSONWithCustomStatusMsg(Object obj, String statusMsg)
	{
		
		JSONObject json = null;
		
		try 
		{
			json = new JSONObject(responceTemplate);
			json.put("payload", obj);
			json.put("status-msg", statusMsg);
			json.put("responce-date", new Date());
			json.put("status-code", 204l);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	} 

	public final JSONObject getSuccessResponceJSONWithCustomStatusMsg(Object obj, String statusMsg)
	{
		JSONObject json = null;
		
		try 
		{
			json = new JSONObject(responceTemplate);
			json.put("payload", obj);
			json.put("status-msg", statusMsg);
			json.put("responce-date", new Date());
			json.put("status-code", 200l);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return json;
	} 

	public final JSONObject getInternalProblemResponceJSON(Object obj)
	{
		JSONObject json = null;
		
		try 
		{
			json = new JSONObject(responceTemplate);
			json.put("payload", obj);
			json.put("status-msg", " Internal Server Error");
			json.put("responce-date", new Date());
			json.put("status-code", 500l);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	} 

	public final JSONObject getBadRequestResponceJSON(Object obj)
	{
		JSONObject json = null;
		
		try 
		{
			json = new JSONObject(responceTemplate);
			json.put("payload", obj);
			json.put("status-msg", "Bad Request");
			json.put("responce-date", new Date());
			json.put("status-code", 400l);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return json;
	} 
}
