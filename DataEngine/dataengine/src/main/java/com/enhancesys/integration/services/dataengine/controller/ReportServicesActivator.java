package com.enhancesys.integration.services.dataengine.controller;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enhancesys.integration.services.dataengine.controller.util.ReportServiceUtility;
import com.enhancesys.integration.services.dataengine.controller.util.ReportServicesHelper;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.IntegrationReportServiceException;
import com.mongodb.BasicDBObject;

@Component
public class ReportServicesActivator implements ReportServices
{

	@Autowired
	private ReportServiceUtility reportServiceUtility;

	@Autowired
	private ReportServicesHelper reportServicesHelper;

	@Autowired
	private ReportServicesDeligator reportServicesDeligator;

	private static Logger LOGGER = Logger.getLogger(ReportServicesActivator.class);

	public ReportServicesActivator()
	{
		LOGGER.info("ReportServicesActivator activated..");
	}
	
	@SuppressWarnings("static-access")
	@Override
	public String processJobData(String request) 
	{
		String response = null;
		long requestId = 0L;
		String fileName = null;

		try
		{
			LOGGER.info("[ReportServicesActivator - processJobData] - Request : [ " + request + " ]");
			requestId = reportServiceUtility.getRequestId();

//			response = Utilities.validateBsonSchema(request, SNOCBsonSchemaDefinations.PROCESS_JOB_DATA);
//			if(!DataConstants.V_SUCCESS.equals(response)) 
//			{
//				throw new IntegrationReportServiceException(response, DataConstants.EX_CODE_SCHEMA_VALIDATION);
//			}

			fileName = reportServicesHelper.getFileNameFromRequest(request);
			
			BasicDBObject requestObj = reportServicesDeligator.processJobData(requestId, fileName);

			response = reportServiceUtility.prepareSuccessResponseJson(requestId, requestObj);

		}
		catch(NullPointerException e)
		{
			LOGGER.error("[ReportServicesActivator - processJobData]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=reportServiceUtility.prepareExceptionResponseJson(requestId, DataConstants.EX_MSG_NULL_POINTER, DataConstants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationReportServiceException e)
		{
			LOGGER.error("[ReportServicesActivator - processJobData]:: Exception Message is: >> "+e.getMessage());
			response=reportServiceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[ReportServicesActivator - processJobData] :: Exception Message is: >> "+e.getMessage());
			response=reportServiceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), DataConstants.EX_CODE_DEFAULT_ERROR);
		}
		finally 
		{
			LOGGER.info("processData :: Response >> " + response);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String welcome() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "Welcome to DataEngine Services");
		return jsonObject + "";
	}


}