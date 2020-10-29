package com.enhancesys.integration.services.dataengine.controller;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enhancesys.integration.services.dataengine.controller.util.ReportServicesMongoDAO;
import com.enhancesys.integration.services.dataengine.initiator.JobServiceTest;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.mongodb.BasicDBObject;

@Component
public class ReportServicesDeligator 
{
	private static Logger LOGGER = Logger.getLogger(ReportServicesDeligator.class);

	@Autowired
	ReportServicesMongoDAO reportServicesMongoDAO;

	public BasicDBObject processJobData(final long requestId, final String fileName) throws Exception
	{
		BasicDBObject responseObj = new BasicDBObject();	
		LOGGER.info("processJobData :: requestId :: " + requestId + " :: fileName :: "+ fileName);
		try 
		{
			JobServiceTest.invoke(fileName);
		}
		catch (Exception e) 
		{
			LOGGER.error(e.getMessage(), e);
		}
		finally 
		{

		}

		return responseObj;
	}
	public BasicDBObject processJobData(final long requestId, final BasicDBObject requestObject) throws Exception 
	{
		LOGGER.info("processJobData :: requestId :: " + requestId + " :: requestObject :: "+ requestObject);
		BasicDBObject responseObj = new BasicDBObject();	
		String fileName = null;
		File file = null;
		Process process = null;
		String runCmd = null;
		String killCmd = null;
		BasicDBObject insertDoc = null;
		BasicDBObject requestUpdateConf = null;
		BasicDBObject storeFileConf = null;
		boolean deleteFromLocal = false;
		boolean isStorFileEnabled = true;
		boolean noJobTimeLimit = false;
		boolean deleteJobDataFile = true;
		long jobExecutionTimeLimit = DataConstants.REPORT_SERVICE_JOB_TIME_LIMIT; // in seconds
		BasicDBObject basicDBObject = null;
		try
		{

			fileName = DataConstants._JOB_PROCESS_FILE_PATH + requestObject.getString(DataConstants.F_JOB_ID) + "_" + requestId + DataConstants.F_DOT_JSON;

			if(requestObject.get(DataConstants.F_DELETE_JOB_DATA_FILE) != null)
				deleteJobDataFile = requestObject.getBoolean(DataConstants.F_DELETE_JOB_DATA_FILE);
			if(requestObject.get(DataConstants.F_DELETE_FILE_LOCAL) != null)
				deleteFromLocal = requestObject.getBoolean(DataConstants.F_DELETE_FILE_LOCAL);
			if(requestObject.get(DataConstants.F_STORE_FILE_ENABLE) != null)
				isStorFileEnabled = requestObject.getBoolean(DataConstants.F_STORE_FILE_ENABLE);
			if(requestObject.get(DataConstants.F_JOB_TIME_LIMIT) != null)
				jobExecutionTimeLimit = requestObject.getLong(DataConstants.F_JOB_TIME_LIMIT);
			else
				LOGGER.info("Job Execution :: No Time limit specified :: proceeding with default time limit :: " + jobExecutionTimeLimit + " in secs");

			if(jobExecutionTimeLimit == 0)
			{
				noJobTimeLimit = true;
				LOGGER.info("Job Execution :: Forcefully proceeding with No Time limit");
			}
			else if(jobExecutionTimeLimit >= 1000 && jobExecutionTimeLimit <= 10) 
			{
				LOGGER.info("Job Execution :: Time limit not more than 1000 or less than 10 :: proceeding with default time limit :: " + jobExecutionTimeLimit + " in secs");
				jobExecutionTimeLimit = DataConstants.REPORT_SERVICE_JOB_TIME_LIMIT; // in seconds
			}

			if(requestObject.get(DataConstants.F_REQUEST_UPDATE_CONF) != null)
				requestUpdateConf = (BasicDBObject) requestObject.get(DataConstants.F_REQUEST_UPDATE_CONF);
			else
				requestUpdateConf = Utilities.getDefaultRequestUpdateConf();

			if(requestObject.get(DataConstants.F_STORE_FILE_CONF) != null)
				storeFileConf = (BasicDBObject) requestObject.get(DataConstants.F_STORE_FILE_CONF);
			else
				storeFileConf = Utilities.getDefaultStoreFileConf(deleteFromLocal, isStorFileEnabled);

			requestObject.put(DataConstants.F_REQUEST_ID, requestId);
			requestObject.put(DataConstants.F_REQUEST_UPDATE_CONF, requestUpdateConf);
			requestObject.put(DataConstants.F_STORE_FILE_CONF, storeFileConf);


			// insert document into mongo
			insertDoc = new BasicDBObject();
			insertDoc.put(DataConstants._ID, requestId);
			insertDoc.put(DataConstants.REPORT_SERVICE_COLLECTION_ID, requestId);
			insertDoc.put(DataConstants.F_STATUS, DataConstants.ST_INITIATED);
			insertDoc.put(DataConstants.F_UPDATED_DATE, new Date());
			insertDoc.put(DataConstants.F_JOB_TIME_LIMIT, jobExecutionTimeLimit);
			insertDoc.put(DataConstants.F_JOB_REQUEST, requestObject);

			insertDoc.put(DataConstants.F_DELETE_FILE_LOCAL, deleteFromLocal);
			insertDoc.put(DataConstants.F_STORE_FILE_ENABLE, isStorFileEnabled);
			insertDoc.put(DataConstants.F_JOB_DATA_FILE_NAME, fileName);
			insertDoc.put(DataConstants.F_DELETE_JOB_DATA_FILE, deleteJobDataFile);

			reportServicesMongoDAO.insertDocumentObject(DataConstants.REPORT_SERVICE_SCHEMA, DataConstants.REPORT_SERVICE_COLLECTION, insertDoc);

			file = Utilities.writeFile(fileName, requestObject);
			if(System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows"))
			{
				LOGGER.info("InterfaceReportServices's JobEngine project not implemeted on windows platform");
			}
			else
			{
				try
				{
					runCmd =  DataConstants._JOB_PROCESS_RUN_SCRIPT + "  " + file.getAbsolutePath();
					killCmd = DataConstants._JOB_PROCESS_KILL_SCRIPT + " " + file.getAbsolutePath();
					LOGGER.info("Script cmd :: " + runCmd);
					LOGGER.info("Kill cmd :: " + killCmd);

					process = Runtime.getRuntime().exec(runCmd);

					if(jobExecutionTimeLimit == 0 && noJobTimeLimit) 
						LOGGER.info("Process waitFor :: " + process.waitFor());
					else 
					{
						LOGGER.info("Sleep Time in sec :: " + (jobExecutionTimeLimit * 1000));
						Thread.sleep(jobExecutionTimeLimit * 1000);
					} 
				} 
				catch (Exception e) 
				{
					LOGGER.error("Runtime exception found :: ", e);
				}
				finally 
				{
					Runtime.getRuntime().exec(killCmd);
					LOGGER.info("Kill cmd executed :: " + killCmd);

					basicDBObject = (BasicDBObject) reportServicesMongoDAO.getDocumentbyId(DataConstants.REPORT_SERVICE_SCHEMA, DataConstants.REPORT_SERVICE_COLLECTION, new BasicDBObject(DataConstants._ID, requestId));
					if(basicDBObject != null && basicDBObject.getLong(DataConstants.F_STATUS) == DataConstants.STATUS_IN_PROGRESS)
					{
						basicDBObject.put(DataConstants.F_STATUS, DataConstants.STATUS_CANCELLED);
						reportServicesMongoDAO.updateDocumentObject(DataConstants.REPORT_SERVICE_SCHEMA, DataConstants.REPORT_SERVICE_COLLECTION, new BasicDBObject(DataConstants._ID, requestId), basicDBObject);
					}
				}
			}
			if(basicDBObject != null)
				responseObj.put(DataConstants.F_JOB_DETAILS, basicDBObject);
			else
				responseObj.put(DataConstants.F_JOB_DETAILS, reportServicesMongoDAO.getDocumentbyId(DataConstants.REPORT_SERVICE_SCHEMA, DataConstants.REPORT_SERVICE_COLLECTION, new BasicDBObject(DataConstants._ID, requestId)));

			return responseObj;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[ReportServicesDeligator - processJobData]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[ReportServicesDeligator - processJobData]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			if(file != null)
				if(deleteJobDataFile)
					file.delete();
			file  = null;
			process = null;
			insertDoc = null;
			basicDBObject = null;
			runCmd = null;
			killCmd = null;
		}
	}
}