package com.enhancesys.integration.services.dataengine.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class JobConfiguration 
{

	private JSONObject _templateConfig_;
	private JSONObject _jobConfig_;
	private static Logger LOGGER = Logger.getLogger(JobConfiguration.class);

	@SuppressWarnings("unchecked")
	public void readConfiguration(String templateName, String templateId, String jobId, Object requestJson, JSONObject jobParameters)
	{
		LOGGER.info("Entry readConfiguration..");

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
			if("File".equalsIgnoreCase(DataConstants.TEMPLATE_SOURCE))
			{

				if(templateName != null) 
				{
					if(DataConstants.CUSTOM_TEMPLATE_CONFIG_PATH == null || DataConstants.CUSTOM_TEMPLATE_CONFIG_PATH.trim().isEmpty())
					{
						LOGGER.error("Please configure custom template Path..");
						throw new GenericProcessorException("Please configure custom template Path..");
					}
					templateName = DataConstants.CUSTOM_TEMPLATE_CONFIG_PATH.trim() + templateName.trim();
					LOGGER.info("Custom Template Configuration from " + templateName);
				}
				else if(DataConstants.TEMPLATE_CONFIG_PATH == null || DataConstants.TEMPLATE_CONFIG_PATH.trim().isEmpty())
				{
					LOGGER.error("Please configure Template Path..");
					throw new GenericProcessorException("Please configure Template Path..");
				}
				else
					templateName = DataConstants.TEMPLATE_CONFIG_PATH;

				file = new File(templateName);
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
					LOGGER.error("Please configure Template Configuration..");
					throw new GenericProcessorException("Please configure Template Configuration..");
				}

				parser = new JSONParser();
				config = (JSONObject) parser.parse(buffer.toString());
				_templateConfig_ = (JSONObject) config.get(templateId);
				if(_templateConfig_ == null || _templateConfig_.toString().trim().isEmpty())
				{
					LOGGER.error("Please configure Template for : " + templateId);
					throw new GenericProcessorException("Please configure Template for : " + templateId);
				}
			}

			if("File".equalsIgnoreCase(DataConstants.JOB_SOURCE))
			{
				if(DataConstants.JOB_DUMP_TEMPLATE == null || DataConstants.JOB_DUMP_TEMPLATE.trim().isEmpty())
				{
					LOGGER.error("Please configure Template Path..");
					throw new GenericProcessorException("Please configure Template Path..");
				}
				LOGGER.info("jobId :: " + jobId);
				file = new File(DataConstants.JOB_DUMP_TEMPLATE + jobId + ".json");
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
					//					log.info("requestJson  :  " + reqObject);
					for(Object key : reqObject.keySet())
					{
						string = string.replace("$" + key.toString(), reqObject.get(key).toString());
					}
					//					log.info("converted request string  :  " + string);
				}
				if(string.trim().isEmpty())
				{
					LOGGER.error(jobId + ".json File does not contain the Configuration Data..");
					throw new GenericProcessorException(jobId + ".json File does not contain the Configuration Data..");
				}
				parser = new JSONParser();
				_jobConfig_ = (JSONObject) parser.parse(string);
				
				LOGGER.info("\n_jobConfig_ :: " + _jobConfig_ +"\n");

				if(_jobConfig_ != null  && jobParameters.containsKey(DataConstants.REQUEST_UPDATE_CONF) && jobParameters.get(DataConstants.REQUEST_UPDATE_CONF) != null)
					_jobConfig_.put(DataConstants.REQUEST_UPDATE_CONF, jobParameters.get(DataConstants.REQUEST_UPDATE_CONF));

				if(_jobConfig_ != null && jobParameters.containsKey(DataConstants.STORE_FILE_CONF) && jobParameters.get(DataConstants.STORE_FILE_CONF) != null)
					_jobConfig_.put(DataConstants.STORE_FILE_CONF, jobParameters.get(DataConstants.STORE_FILE_CONF));

			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
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

			LOGGER.info("Exit readConfiguration..");
		}
	}

	public JSONObject getTemplateConfigData(String contextPath)
	{
		return _templateConfig_;
	}

	public JSONObject getJobConfigData(String keyValue)
	{
		if(keyValue != null)
			return (JSONObject) _jobConfig_.get(keyValue);
		return _jobConfig_;
	}
}