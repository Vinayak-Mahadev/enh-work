package com.enhancesys.jobengine.services;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.enhancesys.jobengine.services.job.JobEngine;

public class JobServcies 
{
	private static Logger log = Logger.getLogger(JobServcies.class);
	
	public static void proceeRequest(JSONObject jobParameters) 
	{
		log.error("Entry processRequest.." + jobParameters);
		
		ApplicationContext context = null;
		JobEngine jobEngine = null;
		File file = null;
		FileInputStream inputStream = null;
		JSONArray jobsArray = null;
		StringBuffer buffer = null;
		String configurationPath = null;
		String envPath = null;
		
		try
		{
//			context = new ClassPathXmlApplicationContext("spring-config.xml");
//			log.info("jobParameters : " + jobParameters);
//			log.info("file Path : " + System.getProperty("user.dir") + File.separator + "spring-config.xml");
			
			configurationPath = System.getProperty("user.dir") + File.separator + "spring-config.xml";
			file = new File(configurationPath);
			if(file.exists())
				context = new FileSystemXmlApplicationContext("file:" + configurationPath);
			else
			{
				envPath = System.getenv("APPSERVER_CONF_PATH");
				configurationPath = envPath + File.separator + "reports/spring-config.xml";
				if(envPath.trim().endsWith("/"))
					configurationPath = envPath + "reports/spring-config.xml";
				
				file = new File(configurationPath);
				if(!file.exists())
				{
					log.error("\nERROR :: Please configure spring-config file..");
					System.exit(0);
				}
				context = new FileSystemXmlApplicationContext("file:" + configurationPath);
			}
			
//			log.info("Spring App Context : " + context);
			log.info("Spring App Context : " + context);
			jobEngine = (JobEngine) context.getBean("jobEngine");
			if(jobParameters == null)
			{
				file = new File(System.getProperty("user.dir") + File.separator + "dumps.conf");
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
//			log.info("Done..");
			jobEngine.stop();
			log.info("Job Done..");
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
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
			configurationPath = null;
			envPath = null;
			
			log.info("Exit processRequest..");
		}
	}
}