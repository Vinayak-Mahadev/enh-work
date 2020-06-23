package com.enhancesys.integration.response.services;

public class Test {

	public static void main(String[] args)
	{
		JobEngineServicesUtil jobEngineServicesUtil = new JobEngineServicesUtil();

		try
		{
			jobEngineServicesUtil.processModule(null);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
