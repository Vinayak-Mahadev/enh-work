package com.enhancesys.jobengine.services;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JobTestMain 
{
	private static Logger log = Logger.getLogger(JobTestMain.class);
	
	public static void main(String[] args) 
	{
		try
		{
			if(args != null && args.length > 0 && args[0] != null)
			{
				if("dump".equalsIgnoreCase(args[0]))
					JobServcies.proceeRequest(null);
				else if("report".equalsIgnoreCase(args[0]))
					ReportServices.proceeRequest(null);
			}
			else
				JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"postgres_Sample\",\"template-id\":\"Productive-outlet\"}"));
				//JobServcies.proceeRequest(null);
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
		}
	}
}