package com.enhancesys.jobengine.consumer.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.consumer.JobConsumerServices;
import com.enhancesys.jobengine.services.job.JobEngine;

public class JobConsumerServicesActivator implements JobConsumerServices
{

	/*@Autowired
	JobEngine jobEngine;*/
	
	public  JobConsumerServicesActivator() 
	{
		System.out.println("JobConsumer Services Activator");
	}
	
	@Override
	public String goHome(String String) {
		JSONObject response = null;
		System.out.println("Control in goHome");
		try 
		{
			response = 	new JSONObject();
			response.put("msg", "Welcome to JobConsumer Service");
			/*jobEngine.init(null);*/
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Control out goHome");
		return response.toString();
	}

}