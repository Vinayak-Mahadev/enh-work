package com.enhancesys.jobengine.consumer.controller;

import org.json.JSONObject;

import com.enhancesys.jobengine.consumer.JobConsumerServices;
import com.enhancesys.jobengine.consumer.model.Module;

public class JobConsumerServicesActivator implements JobConsumerServices
{

	
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
	
	@Override
	public JSONObject processModule(Long moduleId) 
	{
		try 
		{
			System.out.println("Need to implement code...");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}