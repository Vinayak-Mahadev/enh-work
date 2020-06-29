package com.enhancesys.jobengine.job.services;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.enhancesys.jobcommon.Constants;
import com.enhancesys.jobengine.job.services.job.JobEngine;

public class JobServcies 
{
	private static Logger log = Logger.getLogger(JobServcies.class);

	public static void proceeRequest(JSONObject jobParameters) 
	{
		log.info("Entry processRequest.." + jobParameters);

		ApplicationContext context = null;
		JobEngine jobEngine = null;
		File file = null;
		FileInputStream inputStream = null;
		JSONArray jobsArray = null;
		StringBuffer buffer = null;
		String configurationPath = null;
		try
		{
			try 
			{
				configurationPath = Constants._JOB_CONF_SPRING_BEANS_PATH;
				file = new File(configurationPath);
				if(file.exists())
				{
					context = new FileSystemXmlApplicationContext("file:" + configurationPath);
					log.info("Spring conf inited with FileSystemXmlApplicationContext :  configurationPath :: " + configurationPath);	
				}

			}
			catch (Exception e) 
			{
				log.error(e.getMessage(), e);
				try 
				{
					context = new ClassPathXmlApplicationContext("spring-config.xml");
					log.info("Spring conf inited with ClassPathXmlApplicationContext :  configurationPath :: " + configurationPath);
				} 
				catch (Exception e2) 
				{
					log.error(e2.getMessage(), e2);
				}
			}			

			log.info("jobParameters : " + jobParameters);
			log.info("Spring App Context : " + context);

			jobEngine = (JobEngine) context.getBean("jobEngine");

			log.info("jobEngine :: " + jobEngine);

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
				log.info("No. of Jobs configured : " + jobsArray.size());
				for(Object jobObj : jobsArray)
				{
					jobParameters = (JSONObject) jobObj;
					jobEngine.init(jobParameters);
				}
			}
			else
				jobEngine.init(jobParameters);
			jobEngine.stop();
			log.info("Job Done..");
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage() , exception);
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

			log.info("Exit processRequest..");
		}
	}
}