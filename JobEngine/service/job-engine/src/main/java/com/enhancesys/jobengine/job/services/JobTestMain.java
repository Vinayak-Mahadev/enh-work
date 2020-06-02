package com.enhancesys.jobengine.job.services;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobTestMain 
{
	private static Logger log = Logger.getLogger(JobTestMain.class);

	public static void main(String args[]) 
	{
		System.out.println("JobTestMain");
		try
		{
//			if(args != null  )
//			{
//				ReportServices.proceeRequest(null);
//			}
//			else
				JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"postgres_Sample\",\"template-id\":\"Productive-outlet\"}"));
			//JobServcies.proceeRequest(null);
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			System.out.println("JobTestMain");
		}
	}
}