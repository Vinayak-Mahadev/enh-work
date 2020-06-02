package com.enhancesys.jobengine.job.services;

import java.io.File;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.enhancesys.jobengine.job.services.job.JobEngine;

public class ReportServices 
{
	private static Logger log = Logger.getLogger(ReportServices.class);
	
	/**
	 * {\"job-id\": \"Sample\", \"template-id\": \"Sample-feed\"}
	 * @param jobParameters
	 */
	public static void proceeRequest(JSONObject jobParameters)
	{
		log.error("Entry processRequest.." + jobParameters);
		
		ApplicationContext context = null;
		JobEngine jobEngine = null;
		File file = null;
		String configurationPath = null;
		String envPath = null;
		
		try
		{
			log.error("Report Job Started..");
			
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
			
//			System.out.println("Spring App Context : " + context);
			log.info("Spring App Context : " + context);
			jobEngine = (JobEngine) context.getBean("jobEngine");
			if(jobParameters == null)
			{
//				System.out.println("Job Params are empty..");
				log.error("Job Params are empty..");
				return;
			}
			jobEngine.init(jobParameters);
			log.info("Report Job Done..");
			log.error("Report Job Done..");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			context = null;
			jobEngine = null;
			jobParameters = null;
			file = null;
			configurationPath = null;
			envPath = null;
			
			log.info("Exit processRequest..");
		}
	}
}
