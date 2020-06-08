package com.enhancesys.jobengine.consumer.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.enhancesys.jobengine.job.services.JobServcies;

public class JobEngineServices 
{

	public static void main(String args[]) 
	{
		System.out.println("JobTestMain");
		try
		{
			//JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"FossApprovalReport\",\"template-id\":\"Sample-feed\"}"));
			//JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"postgres_Sample\",\"template-id\":\"Productive-outlet\"}"));
			JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"Sample-OrgDetailsReport\",\"template-id\":\"OrgDetails-feed\"}"));
			//JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"Sample-csv-output\",\"template-id\":\"Jasper-feed\"}"));
			//JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"postgres_Sample\",\"template-id\":\"DataList-Outlet\"}"));
			//JobServcies.proceeRequest((JSONObject) new JSONParser().parse("{\"job-id\":\"postgres_Sample\",\"template-id\":\"Sample-report-feed\"}"));
			//JobServcies.proceeRequest(null);
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
