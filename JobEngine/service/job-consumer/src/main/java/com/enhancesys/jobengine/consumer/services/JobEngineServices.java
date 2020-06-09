package com.enhancesys.jobengine.consumer.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.enhancesys.jobengine.consumer.model.Module;
import com.enhancesys.jobengine.job.services.JobServcies;

@Service
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

	public JSONObject processModule(Module module) 
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
