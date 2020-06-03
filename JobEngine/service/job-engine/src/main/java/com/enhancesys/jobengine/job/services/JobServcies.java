package com.enhancesys.jobengine.job.services;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.enhancesys.jobcommon.Constants;
import com.enhancesys.jobengine.job.services.job.JobEngine;

public class JobServcies 
{
	private static Logger log = Logger.getLogger(JobServcies.class);

	public static void proceeRequest(JSONObject jobParameters) 
	{
		System.out.println("Entry processRequest.." + jobParameters);

		ApplicationContext context = null;
		JobEngine jobEngine = null;
		File file = null;
		FileInputStream inputStream = null;
		JSONArray jobsArray = null;
		StringBuffer buffer = null;
		//		String configurationPath = null;
		//		String envPath = null;

		try
		{
			
			context = new FileSystemXmlApplicationContext(Constants._JOB_CONF_PATH + "beans/spring-config.xml");
			
			System.out.println("jobParameters : " + jobParameters);
			System.out.println("Spring App Context : " + context);
			
			log.info("jobParameters : " + jobParameters);
			log.info("Spring App Context : " + context);
			
			jobEngine = (JobEngine) context.getBean("jobEngine");

			System.out.println("jobEngine :: " + jobEngine);
			
			if(jobParameters == null)
			{
				file = new File(Constants._JOB_CONFIG_PATH + "dumps.json");
				inputStream = new FileInputStream(file);
				buffer = new StringBuffer();
				int s = 0;
				while((s=inputStream.read()) != -1)
				{
					buffer.append((char) s);
				}
				inputStream.close();
				jobsArray = (JSONArray) new JSONParser().parse(buffer.toString());
				System.out.println("No. of Jobs configured : " + jobsArray.size());
				for(Object jobObj : jobsArray)
				{
					jobParameters = (JSONObject) jobObj;
					jobEngine.init(jobParameters);
				}
			}
			else
				jobEngine.init(jobParameters);
			//			System.out.println("Done..");
			jobEngine.stop();
			System.out.println("Job Done..");
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{

			try
			{
				if(inputStream != null)
					inputStream.close();
			}
			catch(Exception exception2)
			{
				exception2.printStackTrace();
			}
			context = null;
			jobEngine = null;
			file = null;
			inputStream = null;
			jobsArray = null;
			buffer = null;
			//			configurationPath = null;
			//			envPath = null;

			System.out.println("Exit processRequest..");
		}
	}
}