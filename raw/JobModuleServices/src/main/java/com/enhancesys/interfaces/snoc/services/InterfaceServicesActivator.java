package com.enhancesys.interfaces.snoc.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.enhancesys.interfaces.snoc.common.InterfaceUtility;
import com.enhancesys.interfaces.snoc.common.SNOCBsonSchemaDefinations;
import com.enhancesys.interfaces.snoc.util.IntegrationServiceException;
import com.enhancesys.interfaces.snoc.util.PropertiesFileLoaderClass;
import com.enhancesys.interfaces.snoc.util.Utility;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;


public class InterfaceServicesActivator implements InterfaceServices
{

	@Autowired
	private InterfaceUtility interfaceUtility;

	@Autowired
	private InterfaceServicesHelper interfaceServicesHelper;
	
	@Autowired
	private InterfaceServicesDeligator interfaceServicesDeligator;
	
	private static Logger LOGGER = Logger.getLogger(InterfaceServicesActivator.class);

	@SuppressWarnings("static-access")
	@Override
	public String getInterfaces(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getInterfaces] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_INTERFACES);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getInterfaces(interfaceObj);
			
			response = interfaceUtility.prepareSuccessResponseList(requestId, requestObj);
			
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaces]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaces]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaces] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getInterfaces :: Response >> "+response);
		return response;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public String getInterfaceByModuleId(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getInterfaceByModuleId] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_INTERFACE_FILE_DETAILS);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBObject requestObj = interfaceServicesDeligator.getInterfaceModuleById(interfaceObj);
			
			response = interfaceUtility.prepareSuccessResponseJson(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceByModuleId]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceByModuleId]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceByModuleId] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getInterfaceByModuleId :: Response >> " + response);
		return response;
	}
	
	@SuppressWarnings({ "static-access" })
	@Override
	public String getInterfaceFileDetails(String request)
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getInterfaceFileDetails] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_INTERFACE_MODULE_FILES);
			if(!Constants.V_SUCCESS.equals(response)) {
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getInterfaceFileDetails(interfaceObj);
			
			response = interfaceUtility.prepareSuccessResponseList(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceFileDetails]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceFileDetails]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceFileDetails] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getInterfaceFileDetails :: Response >> "+response);
		return response;
	}

	@SuppressWarnings({ "static-access"})
	@Override
	public String getInterfaceFileSummary(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getInterfaceFileSummary] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_INTERFACE_FILE_SUMMARY);
			if(!Constants.V_SUCCESS.equals(response))
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getInterfaceFileSummary(interfaceObj);
			
			response = interfaceUtility.prepareSuccessResponseList(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceFileSummary]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceFileSummary]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceFileSummary] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getInterfaceFileSummary :: Response >> " + response);
		return response;
	}
	
	@SuppressWarnings({ "static-access"})
	@Override
	public String downlaodFileSummary(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		BasicDBObject headerRequestObject = null;
		try
		{
			LOGGER.info("[InterfaceServicesActivator - downlaodFileSummary] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.DOWNLOAD_INTERFACE_FILE_DATA);
			if(!Constants.V_SUCCESS.equals(response))
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			headerRequestObject = interfaceServicesHelper.getHeaderFromRequest(request);
			/*boolean isValidUser = userValidator.validate(headerRequestObject);
			
			if(!isValidUser)
			{
				throw new IntegrationServiceException(Constants.EX_USER_VALIDATION_MSG,Constants.EX_USER_VALIDATION_CODE);
			}*/
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			
			Long notifId = interfaceServicesDeligator.getInterfaceFileData(Long.parseLong(headerRequestObject.get("uid").toString()),interfaceObj);
			
			if(notifId != 0l)
			{
				response=Utility.getExceptionResponse(requestId, 
						PropertiesFileLoaderClass.getValueAsString(headerRequestObject.getString("locale")+
								".interfaceModuleServices.interface_"+Constants.EX_DOWNLOAD_REQUEST_ACCEPTED_CODE).replace("$ref_id", Utility.parseString(notifId)), 
							Constants.EX_CODE_SUCCESS);
			}
			else
			{
				response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NO_DATA_FOUND, Constants.EX_CODE_NO_DATA_FOUND);
			}
			
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - downlaodFileSummary]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - downlaodFileSummary]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - downlaodFileSummary] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		return response;
	}

	@SuppressWarnings({ "static-access"})
	@Override
	public String uploadInterfaceFile(String request)
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		BasicDBObject headerRequestObject = null;
		try
		{
//			LOGGER.info("[InterfaceServicesActivator - uploadInterfaceFile] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.UPLOAD_INTERFACE_FILE_DATA);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			headerRequestObject = interfaceServicesHelper.getHeaderFromRequest(request);
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			LOGGER.info("[InterfaceServicesActivator - uploadInterfaceFile] - file_name :: " + interfaceObj.getString("file_name") + " , file_ext :: " + interfaceObj.getString("file_ext")); 
			/*boolean isValidUser = userValidator.validate(headerRequestObject);
			
			if(!isValidUser)
			{
				throw new IntegrationServiceException(Constants.EX_USER_VALIDATION_MSG,Constants.EX_USER_VALIDATION_CODE);
			}*/
			
			BasicDBObject attrData = interfaceServicesDeligator.getInterfaceAttrByInterfaceId(Long.parseLong(interfaceObj.get(Constants.F_INTERFACE_ID).toString()));
			if(attrData.getString("Remote File") != null && !attrData.getString("Remote File").isEmpty() && !interfaceObj.get(Constants.F_FILE_NAME).toString().startsWith(attrData.getString("Remote File")))
			{
				response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_INVALID_FILE_NAME, Constants.EX_CODE_INVALID_FILE_NAME);
				return response;
			}
			Long notifId = interfaceServicesDeligator.uploadInterfaceFile(Long.parseLong(headerRequestObject.get("uid").toString()),interfaceObj);
			
			if(notifId != 0l)
			{
				response=Utility.getExceptionResponse(requestId, 
						PropertiesFileLoaderClass.getValueAsString(headerRequestObject.getString("locale")+
								".interfaceModuleServices.interface_"+Constants.EX_UPLOAD_REQUEST_ACCEPTED_CODE).replace("$ref_id", Utility.parseString(notifId)), 
							Constants.EX_CODE_SUCCESS);
			}
			else
			{
				response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NO_DATA_FOUND, Constants.EX_CODE_NO_DATA_FOUND);
			}
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - uploadInterfaceFile]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - uploadInterfaceFile]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - uploadInterfaceFile] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		return response;
	}
	
	@Override
	public String getOnlineInterfaceSummaries(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getOnlineInterfaceSummaries] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_ONLINE_INTERFACE_SUMMARIES);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getOnlineInterfaceSummaries(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseList(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getOnlineInterfaceSummaries]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getOnlineInterfaceSummaries]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getOnlineInterfaceSummaries] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getOnlineInterfaceSummaries :: Response >> "+response);
		return response;
	}
	
	@Override
	public String getRequestAndResponseDataByTransactionId(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getRequestAndResponseDataByTransactionId] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_REQUEST_RESPONSE_BY_TRANSACTION_ID);
			if(!Constants.V_SUCCESS.equals(response))
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBObject requestObj = interfaceServicesDeligator.getRequestAndResponseDataByTransactionId(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseJson(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getRequestAndResponseDataByTransactionId]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getRequestAndResponseDataByTransactionId]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getRequestAndResponseDataByTransactionId] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getRequestAndResponseDataByTransactionId :: Response >> "+response);
		return response;
	}
	
	@Override
	public String getOnlineInterfaceListWrapper(String request)
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getOnlineInterfaceListWrapper] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_ONLINE_INTERFACE_LIST_WRAPPER);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getOnlineInterfaceListWrapper(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseList(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getOnlineInterfaceListWrapper]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getOnlineInterfaceListWrapper]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getOnlineInterfaceListWrapper] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getOnlineInterfaceListWrapper :: Response >> "+response);
		return response;
	}
	
	@Override
	public String reprocessInterfaceSummary(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - reprocessInterfaceSummary] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.REPROCESS_INTERFACE_SUMMARY);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBObject requestObj = interfaceServicesDeligator.reprocessInterfaceSummary(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseJson(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - reprocessInterfaceSummary]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - reprocessInterfaceSummary]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - reprocessInterfaceSummary] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("reprocessInterfaceSummary :: Response >> "+response);
		return response;
	}
	
	@SuppressWarnings({ "static-access"})
	@Override
	public String downlaodInterfaceReports(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		BasicDBObject headerRequestObject = null;
		try
		{
			LOGGER.info("[InterfaceServicesActivator - downlaodInterfaceReports] - Request : [ " + request + " ]");
			requestId = interfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.DOWNLOAD_INTERFACE_REPORTS);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			headerRequestObject = interfaceServicesHelper.getHeaderFromRequest(request);
			/*boolean isValidUser = userValidator.validate(headerRequestObject);
			
			if(!isValidUser)
			{
				throw new IntegrationServiceException(Constants.EX_USER_VALIDATION_MSG,Constants.EX_USER_VALIDATION_CODE);
			}*/
			interfaceObj = interfaceServicesHelper.getInterfaceFromRequest(request);
			
			Long notifId = interfaceServicesDeligator.getInterfaceReportData(Long.parseLong(headerRequestObject.get("uid").toString()),interfaceObj);
			
			if(notifId != 0l)
			{
				response=Utility.getExceptionResponse(requestId, 
						PropertiesFileLoaderClass.getValueAsString(headerRequestObject.getString("locale")+
								".interfaceModuleServices.interface_"+Constants.EX_DOWNLOAD_REQUEST_ACCEPTED_CODE).replace("$ref_id", Utility.parseString(notifId)), 
							Constants.EX_CODE_SUCCESS);
			}
			else
			{
				response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NO_DATA_FOUND, Constants.EX_CODE_NO_DATA_FOUND);
			}
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - downlaodInterfaceReports]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - downlaodInterfaceReports]:: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - downlaodInterfaceReports] :: Exception Message is: >> "+e.getMessage());
			response=interfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		return response;
	}
	
	@Override
	public String getInterfacesByInterfaceIds(String request)
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getInterfacesByInterfaceIds] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_INTERFACES_BY_INTERFACEIDS);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getInterfacesByInterfaceIds(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseList(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfacesByInterfaceIds]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfacesByInterfaceIds]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfacesByInterfaceIds] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getInterfacesByInterfaceIds :: Response >> "+response);
		return response;
	}
	
	@Override
	public String getKycSyncAttrByInterfaceId(String request)
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getKycSyncAttrByInterfaceId] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_KYC_SYNC_ATTR_BY_INTERFACEID);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBList requestObj = interfaceServicesDeligator.getKycSyncAttrByInterfaceId(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseList(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getKycSyncAttrByInterfaceId]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getKycSyncAttrByInterfaceId]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getKycSyncAttrByInterfaceId] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getKycSyncAttrByInterfaceId :: Response >> "+response);
		return response;
	}
	
	@Override
	public String updateKycSyncAttributes(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - updateKycSyncAttributes] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.UPDATE_KYC_SYNC_ATTRIBUTES);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBObject requestObj = interfaceServicesDeligator.updateKycSyncAttributes(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseJson(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - updateKycSyncAttributes]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - updateKycSyncAttributes]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - updateKycSyncAttributes] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("updateKycSyncAttributes :: Response >> "+response);
		return response;
	}
	
	@Override
	public String getInterfaceDetails(String request) 
	{
		String response = null;
		long requestId = 0L;
		BasicDBObject interfaceObj = new BasicDBObject();
		
		try
		{
			LOGGER.info("[InterfaceServicesActivator - getInterfaceDetails] - Request : [ " + request + " ]");
			requestId = InterfaceUtility.getRequestId();
			
			response = Utility.validateBsonSchema(request, SNOCBsonSchemaDefinations.GET_INTERFACE_DETAILS);
			if(!Constants.V_SUCCESS.equals(response)) 
			{
				throw new IntegrationServiceException(response, Constants.EX_CODE_SCHEMA_VALIDATION);
			}
			
			interfaceObj = InterfaceServicesHelper.getInterfaceFromRequest(request);
			
			BasicDBObject requestObj = interfaceServicesDeligator.getInterfaceDetails(interfaceObj);
			
			response = InterfaceUtility.prepareSuccessResponseJson(requestId, requestObj);
		}
		catch(NullPointerException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceDetails]:: Null Pointer Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, Constants.EX_MSG_NULL_POINTER, Constants.EX_CODE_NULL_POINTER);
		}
		catch(IntegrationServiceException e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceDetails]:: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), e.getCode());
		}
		catch(Exception e)
		{
			LOGGER.error("[InterfaceServicesActivator - getInterfaceDetails] :: Exception Message is: >> "+e.getMessage());
			response=InterfaceUtility.prepareExceptionResponseJson(requestId, e.getMessage(), Constants.EX_CODE_DEFAULT_ERROR);
		}
		LOGGER.info("getInterfaceDetails :: Response >> "+response);
		return response;
	}
}