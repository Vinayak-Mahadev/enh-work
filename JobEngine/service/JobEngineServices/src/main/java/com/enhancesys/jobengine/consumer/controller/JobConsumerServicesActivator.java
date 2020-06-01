package com.enhancesys.jobengine.consumer.controller;

import org.json.JSONObject;

import com.enhancesys.jobengine.consumer.JobConsumerServices;

public class JobConsumerServicesActivator implements JobConsumerServices
{

	@Override
	public JSONObject goHome(String String) {
		JSONObject response = null;
		try 
		{
			response = 	new JSONObject();
			response.put("msg", "Welcome to JobConsumer Service");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}