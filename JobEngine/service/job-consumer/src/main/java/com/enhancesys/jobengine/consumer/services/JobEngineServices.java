package com.enhancesys.jobengine.consumer.services;

import com.enhancesys.jobengine.job.services.JobServcies;

public class JobEngineServices 
{

	public static void main(String args[]) 
	{
		System.out.println("JobTestMain");
		try
		{
			JobServcies.proceeRequest(null);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{
			System.out.println("JobTestMain");
		}
	}
}
