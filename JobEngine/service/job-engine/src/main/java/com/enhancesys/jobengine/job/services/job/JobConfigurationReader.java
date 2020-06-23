package com.enhancesys.jobengine.job.services.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.job.services.util.GenericProcessorException;
import com.enhancesys.jobengine.job.services.util.PropertiesLoader;

public class JobConfigurationReader 
{
	@Autowired
	PropertiesLoader propertiesLoader;

	private JSONObject templateConfig = null;
	private JSONObject jobConfig = null;
	private static Logger log = Logger.getLogger(JobConfigurationReader.class);

	public void readConfiguration(String templateId, String jobId, Object requestJson)
	{
		log.info("Entry readConfiguration..");

		JSONObject config = null;
		File file = null;
		FileInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer buffer = null;
		JSONParser parser = null;
		JSONObject reqObject = null;
		String string = null;
		String line = null;

		try
		{
			if("File".equalsIgnoreCase(PropertiesLoader.getValueFor("TEMPLATE_SOURCE")))
			{

				if(PropertiesLoader.getValueFor("TEMPLATE_CONFIG_PATH") == null || PropertiesLoader.getValueFor("TEMPLATE_CONFIG_PATH").trim().isEmpty())
				{
					log.error("Please configure Template Path..");
					throw new GenericProcessorException("Please configure Template Path..");
				}
				file = new File(PropertiesLoader.getValueFor("TEMPLATE_CONFIG_PATH"));
				//file = new File(Constants._TEMPLATE_CONFIG_PATH);
				
				inputStream = new FileInputStream(file);
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader);

				buffer = new StringBuffer();
				line = null;
				while ((line= bufferedReader.readLine()) != null)
				{
					buffer.append(line);
				}

				inputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
				if(buffer.toString().trim().isEmpty())
				{
					log.error("Please configure Template Configuration..");
					log.error("Please configure Template Configuration..");
					throw new GenericProcessorException("Please configure Template Configuration..");
				}

				parser = new JSONParser();
				config = (JSONObject) parser.parse(buffer.toString());
				templateConfig = (JSONObject) config.get(templateId);
				if(templateConfig == null || templateConfig.toString().trim().isEmpty())
				{
					log.error("Please configure Template for : " + templateId);
					log.error("Please configure Template for : " + templateId);
					throw new GenericProcessorException("Please configure Template for : " + templateId);
				}
			}

			if("File".equalsIgnoreCase(PropertiesLoader.getValueFor("JOB_SOURCE")))
			{
				if(PropertiesLoader.getValueFor("JOB_DUMP_TEMPLATE") == null || PropertiesLoader.getValueFor("JOB_DUMP_TEMPLATE").trim().isEmpty())
				{
					log.error("Please configure Template Path..");
					log.error("Please configure Template Path..");
					throw new GenericProcessorException("Please configure Template Path..");
				}
				log.info("jobId :: " + jobId);
				file = new File(PropertiesLoader.getValueFor("JOB_DUMP_TEMPLATE") + jobId + ".json");
				//file = new File(Constants._JOB_CONFIG_PATH + jobId + ".json");
				inputStream = new FileInputStream(file);
				inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(inputStreamReader);

				buffer = new StringBuffer();
				line = null;
				while ((line= bufferedReader.readLine()) != null)
				{
					buffer.append(line);
				}

				inputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
				string = buffer.toString();
				if(requestJson != null && !requestJson.toString().trim().isEmpty())
				{
					reqObject = (JSONObject) requestJson;
					for(Object key : reqObject.keySet())
					{
						string = string.replace("$" + key.toString(), reqObject.get(key).toString());
					}
				}
				if(string.trim().isEmpty())
				{
					log.error(jobId + ".json File does not contain the Configuration Data..");
					throw new GenericProcessorException(jobId + ".json File does not contain the Configuration Data..");
				}
				parser = new JSONParser();
				jobConfig = (JSONObject) parser.parse(string);
			}
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
				if(inputStreamReader != null)
					inputStreamReader.close();
				if(bufferedReader != null)
					bufferedReader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			config = null;
			file = null;
			inputStream = null;
			inputStreamReader = null;
			bufferedReader = null;
			buffer = null;
			parser = null;
			line = null;

			log.info("Exit readConfiguration..");
		}
	}

	public JSONObject getTemplateConfigData(String contextPath)
	{
		return templateConfig;
	}

	public JSONObject getJobConfigData(String keyValue)
	{
		if(keyValue != null)
			return (JSONObject) jobConfig.get(keyValue);
		return jobConfig;
	}
}