package com.enhancesys.integration.services.dataengine.initiator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.enhancesys.integration.services.dataengine.util.DataConstants;

public class JobServicesInitiator 
{
	private static Logger LOGGER = Logger.getLogger(JobServicesInitiator.class);

	// Log configuration
	static 
	{
		File file = null;
		try 
		{
			if(DataConstants._JOB_LOG_PATH != null)
				file = new File(DataConstants._JOB_LOG_PATH);
			if(file.exists() && file.isFile() && file.canRead())
				PropertyConfigurator.configure(DataConstants._JOB_LOG_PATH);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			file = null;
		}
	}

	public static void main(String[] args) 
	{
		String filepath = null;
		String jsonStr = null;
		String line = null;
		StringBuffer buffer = null;
		File file = null;
		FileInputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		JSONParser parser = null;
		JSONObject jobParameters = null;
		Double startTime = Double.valueOf(System.currentTimeMillis());
		Double endTime = null;
		try
		{
			if(args != null)
			{

				filepath = args[0];
				if(filepath == null || (filepath != null && filepath.trim().isEmpty())) {
					LOGGER.error("File not found given input :: " + filepath);
					return;
				}

				try 
				{
					file  = new File(filepath);
					if(!file.exists()) 
					{
						LOGGER.error("File not present given location :: " + filepath);
						return;
					}

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
					jsonStr = buffer.toString();

					parser = new JSONParser();
					jobParameters = (JSONObject) parser.parse(jsonStr);

					LOGGER.info("JobServicesInitiator started with jobParameters from file :: " + filepath + "\n\n\n");
					// invoke jobEngine with custom input data
					JobService.proceeRequest(jobParameters);	
				} 
				catch (Exception e) 
				{
					LOGGER.error("Exeception while processing file :: " + filepath , e);
				}
			}
			else
				LOGGER.error("Please pass the file path");
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			endTime = Double.valueOf(System.currentTimeMillis());
			LOGGER.info("\n\nTotal Time required for JobServices :: " + Float.valueOf(( (endTime - startTime) / 60000)+"") + " mins  :: Filepath :: " + filepath + "\n\n\n");
			filepath = null;
			jsonStr = null;
			line = null;
			buffer = null;
			file = null;
			bufferedReader = null;
			inputStreamReader = null;
			inputStream = null;
			parser = null;
			jobParameters = null;
		}
	}
}
