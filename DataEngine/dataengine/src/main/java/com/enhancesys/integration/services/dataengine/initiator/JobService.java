package com.enhancesys.integration.services.dataengine.initiator;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.enhancesys.integration.services.dataengine.job.JobEngine;
import com.enhancesys.integration.services.dataengine.springboot.DataEngineApplication;
import com.enhancesys.integration.services.dataengine.util.DataConstants;


/**
 * <pre>
 * <b>Purpose:</b>
 * 
 * Invoking JOBENGINE through Web / local supporter class
 * 
 * <b>DesignReference:</b>
 * 
 * <b>CopyRights:</b>
 *    Enhancesys Innovations 2020<br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <b>Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-09-2020          Vinayak-Mahadev
 *      -- Base Release
 *</pre>
 */

public class JobService 
{
	private static Logger LOGGER = Logger.getLogger(JobService.class);

	/**
	 * <b>Example : jobParameters</b>
	 * <pre>
	 * {
	 *   "template-id": {
	 *   "type": "string"
	 *     },
	 *     "template-name": {
	 *   "type": "string"
	 *     },
	 *     "job-id": {
	 *   "type": "string"
	 *     },
	 *     "job-data": {
	 *   "type": "object",
	 *   "optional": true
	 *     }
	 *  }
	 * </pre>
	 * @param jobParameters
	 */
	public static void proceeRequest(JSONObject jobParameters) 
	{
		LOGGER.info("Entry processRequest :: JobParameters :: " + jobParameters);

		ApplicationContext context = null;
		JobEngine jobEngine = null;
		File file = null;
		FileInputStream inputStream = null;
		String configurationPath = null;
		try
		{
			if(DataEngineApplication.context() != null)
				context = DataEngineApplication.context();
			if(context == null)
			{
				configurationPath = DataConstants._JOB_CONF_SPRING_BEANS_PATH;
				file = new File(configurationPath);

				if(file.exists())
				{
					context = new FileSystemXmlApplicationContext("file:" + configurationPath);
					LOGGER.debug("Spring conf inited with FileSystemXmlApplicationContext :  configurationPath :: " + configurationPath);	
				}
				else
				{
					LOGGER.info("Spring App Context not found :: configurationPath :: " + configurationPath);
					return;				
				}
			}
			LOGGER.info("Spring App Context : " + context);

			jobEngine = (JobEngine) context.getBean("jobEngine");

			LOGGER.info("JobEngine init process going to start:: BEAN ID :: " + jobEngine);

			jobEngine.init(jobParameters);

			jobEngine.stop();

			LOGGER.info("Job Done..");
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage() , exception);
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
			LOGGER.info("Exit processRequest..");
		}
	}
}
