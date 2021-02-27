package com.enhancesys.integration.snoc.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.WebFault;

import com.enhancesys.integration.snoc.beans.FilePropertiesBean;
import com.enhancesys.integration.snoc.beans.ReprocessFileBean;
import com.enhancesys.integration.snoc.beans.ResponseBean;
import com.enhancesys.integration.snoc.entities.CleanUpSummary;
import com.enhancesys.integration.snoc.entities.DailyDumpSummary;
import com.enhancesys.integration.snoc.entities.InterfaceAttribute;
import com.enhancesys.integration.snoc.entities.InterfaceFailure;
import com.enhancesys.integration.snoc.entities.InterfaceFileSummary;
import com.enhancesys.integration.snoc.entities.InterfaceFileSummaryDetails;
import com.enhancesys.integration.snoc.entities.InterfaceNotification;
import com.enhancesys.integration.snoc.entities.InterfaceSummary;
import com.enhancesys.integration.snoc.entities.Interfaces;
import com.enhancesys.integration.snoc.entities.KycSyncInfo;
import com.enhancesys.integration.snoc.entities.Module;
import com.enhancesys.integration.snoc.exception.ApplicationException;


/**
 * <b>Purpose:</b><br>
 * 		Interface to provide the integration operations<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        04-07-2014          Suresh Upparu
 *    	-- Base Release
 *    
 * 2		16-10-2014			Ramana Rao K	
 * 	-- Services are changed based on the new tables and its naming conventions
 * 
 * 3		20-10-2014			Ramana Rao K
 * 	--Added processFile and prepareFile services to process the files from the local directory and to create the files 
 * 		based on the given input request.
 * </pre>
 * 
 * <br>
 */

@SuppressWarnings("serial")
@WebService(targetNamespace="http://com/enhancesys/integration/snoc/services/interfaces/IntegrationManagement")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
//@Transactional(rollbackFor = ApplicationException.class)
public interface IntegrationManagement
{
	@WebMethod(operationName = "getModuleByName")
	public @WebResult(name = "getModuleByName") Module getModuleByName(@WebParam(name = "name") String name) throws ApplicationException;

	@WebMethod(operationName = "getModule")
	public @WebResult(name = "getModule") Module getModule(@WebParam(name = "moduleId") Long moduleId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceById")
	public @WebResult(name = "getInterfaceById") Interfaces getInterfaceById(@WebParam(name = "interfaceId") Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "getInterfacesByTransactionType")
	public @WebResult(name = "getInterfacesByTransactionType") List<Interfaces> getInterfacesByTransactionType(@WebParam(name = "transactionType") Long transactionType) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceAttributes")
	public @WebResult(name = "getInterfaceAttributes") Set<InterfaceAttribute> getInterfaceAttributes(@WebParam(name = "interfaceId") Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummary")
	public @WebResult(name = "getInterfaceSummary") InterfaceSummary getInterfaceSummary(@WebParam(name = "transactionId") Long transactionId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummariesByStatus")
	public @WebResult(name = "getInterfaceSummariesByStatus") List<InterfaceSummary> getInterfaceSummariesByStatus(@WebParam(name = "status") Long status) throws ApplicationException;

	@WebMethod(operationName = "createInterfaceSummary")
	public @WebResult(name = "createInterfaceSummary") InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException;

	@WebMethod(operationName = "updateInterfaceSummary")
	public @WebResult(name = "updateInterfaceSummary") InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException;

	@WebMethod(operationName = "updateInterfaceSummaryStatus")
	public @WebResult(name = "updateInterfaceSummaryStatus") InterfaceSummary updateInterfaceSummaryStatus(@WebParam(name="transactionId") Long transactionId, @WebParam(name="status") Long status) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFailure")
	public @WebResult(name = "getInterfaceFailure") InterfaceFailure getInterfaceFailure(@WebParam(name = "transactionFailureId") Long transactionFailureId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFailureByTransactionId")
	public @WebResult(name = "getInterfaceFailureByTransactionId") List<InterfaceFailure> getInterfaceFailureByTransactionId(@WebParam(name = "transactionId") Long transactionId) throws ApplicationException;

	@WebMethod(operationName = "createInterfaceFailure")
	public @WebResult(name = "createInterfaceFailure") InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException;

	@WebMethod(operationName = "processInQueueRecords") 
	public @WebResult(name = "processInQueueRecords") void processInQueueRecords(@WebParam(name = "statusList") List<Long> status, @WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "fileStatus") Long fileStatus, @WebParam(name = "partners") List<Long> partners) throws ApplicationException;

	@WebMethod(operationName = "processInterfaceSummary") 
	public @WebResult(name = "processInterfaceSummary") void processInterfaceSummary(@WebParam(name = "transactionId") Long trasactionId,@WebParam(name = "retryCount") Long retryCount) throws ApplicationException; 

	@WebMethod(operationName = "processResponseConsumers") 
	public @WebResult(name = "processResponseConsumers") void processResponseConsumers(@WebParam(name = "interfaceId") Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "processResponseAvailableRecords") 
	public @WebResult(name = "processResponseAvailableRecords") void processResponseAvailableRecords(@WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "status") Long status) throws ApplicationException; 

	@WebMethod(operationName = "getTransactionId")
	public @WebResult(name = "getTransactionId") String getTransactionId(@WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "responseData") String responseData) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummaryByRefData1")
	public @WebResult(name = "getInterfaceSummaryByRefData1") List<InterfaceSummary> getInterfaceSummaryByRefData1(@WebParam(name = "refData1") String refData1, @WebParam(name = "refData2") Long refData2, @WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "status") Long status, @WebParam(name = "refData5") Long refData5) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummaryByRefData5")
	public @WebResult(name = "getInterfaceSummaryByRefData5") InterfaceSummary getInterfaceSummaryByRefData5(@WebParam(name = "refData5") String refData5, @WebParam(name = "status") Long status) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummaryByRefData4")
	public @WebResult(name = "getInterfaceSummaryByRefData4") InterfaceSummary getInterfaceSummaryByRefData4(@WebParam(name = "refData4") Long refData4) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceAttributeValue")
	public @WebResult(name = "getInterfaceAttributeValue") String getInterfaceAttributeValue(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "parameterName")String parameterName) throws ApplicationException;

	@WebMethod(operationName = "updateInterfaceSummaryByResponse")
	public @WebResult(name = "updateInterfaceSummaryByResponse") void updateInterfaceSummaryByResponse(@WebParam(name = "responseString")String responseString, @WebParam(name = "transactionId") Long transactionId, @WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "status") Long status, @WebParam(name = "retryCount") Long retryCount)throws ApplicationException;
	/*
	@WebMethod(operationName = "publishInterfaceSummary")
	public @WebResult(name = "publishInterfaceSummary") void publishInterfaceSummary(@WebParam(name = "orderTypeId") Long orderTypeId, @WebParam(name = "orderItemId") Long orderItemId, @WebParam(name = "orderItemXml") String orderItemXml) throws ApplicationException;
	 */
	@WebMethod(operationName = "publishInterfaceData")
	public @WebResult(name = "publishInterfaceData") void publishInterfaceData(@WebParam(name = "moduleId") Long moduleId, @WebParam(name = "refType") Long refType, @WebParam(name = "refId") String refId, @WebParam(name = "requestData") String requestData, @WebParam(name = "clientId") Long clientId) throws ApplicationException;

	@WebMethod(operationName = "processFile")
	public @WebResult(name = "processFile") void processFile(@WebParam(name = "interfaceIds") List<Long> interfaceIds) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummary")
	public @WebResult(name = "getInterfaceFileSummary") InterfaceFileSummary getInterfaceFileSummary(@WebParam(name = "fileId") Long fileId) throws ApplicationException;

	@WebMethod(operationName = "updateInterfaceFileSummary")
	public @WebResult(name = "updateInterfaceFileSummary") InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException;

	@WebMethod(operationName = "updateInterfaceFileSummaryDetails")
	public @WebResult(name = "updateInterfaceFileSummaryDetails")  InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException;

	@WebMethod(operationName = "createInterfaceFileSummary")
	public @WebResult(name = "createInterfaceFileSummary") InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException;

	@WebMethod(operationName = "createInterfaceSummariesByModule")
	public @WebResult(name = "createInterfaceSummariesByModule") void createInterfaceSummariesByModule(@WebParam(name = "interfaceSummary") InterfaceSummary interfaceSummary, @WebParam(name = "moduleId") Long moduleId, @WebParam(name = "transactionType") Long transactionType) throws ApplicationException;

	@WebMethod(operationName = "readFilePropertyBeans")
	public @WebResult(name = "readFilePropertyBeans") List<FilePropertiesBean> readFilePropertyBeans(@WebParam(name = "interfaceId") Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "createInterfaceFileSummaryDetails")
	public @WebResult(name = "createInterfaceFileSummaryDetails") InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(@WebParam(name = "interfaceFileSummaryDetails") InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummaryByInterfaceId")
	public @WebResult(name = "getInterfaceFileSummaryByInterfaceId") List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(@WebParam(name = "interfaceId") Long interfaceId,@WebParam(name = "statusId") Long statusId, @WebParam(name = "partnerId") Long partnerId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummaryDetails")
	public @WebResult(name = "getInterfaceFileSummaryDetails") List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(@WebParam(name = "fileId") Long fileId) throws ApplicationException;

	@WebMethod(operationName = "processReceivedFiles")
	public @WebResult(name = "processReceivedFiles") void processReceivedFiles(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "partnerIds")List<Long> partnerIds) throws ApplicationException;

	@WebMethod(operationName = "reProcessFileBasedOrders")
	public @WebResult(name = "reProcessFileBasedOrders") void reProcessFileBasedOrders(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "fileId")Long fileId) throws ApplicationException;

	@WebMethod(operationName = "reProcessFilesBasedOnErrorCode")
	public @WebResult(name = "reProcessFilesBasedOnErrorCode") void reProcessFilesBasedOnErrorCode(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "fileId")Long fileId, @WebParam(name = "errorCode")Long errorCode) throws ApplicationException;

	@WebMethod(operationName = "getFilesForReprocess")
	public @WebResult(name = "getFilesForReprocess") ReprocessFileBean getFilesForReprocess(@WebParam(name = "interfaceId")Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "pullDataToFile")
	public @WebResult(name = "pullDataToFile") void pullDataToFile(@WebParam(name = "interfaceIds") List<Long> interfaceIds) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummariesByFileIdAndStatus")
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId,List<Long> statusIds) throws ApplicationException;
	@WebMethod(operationName = "getInterfaceSummariesByFileIdAndStatusWithLimit")
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(@WebParam(name = "interfaceFileId")Long interfaceFileId, @WebParam(name = "statusIds")List<Long> statusIds,@WebParam(name = "limit")Long limit, @WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "fileIds")List<Long> fileIds,@WebParam(name = "retryCount")Short retryCount) throws ApplicationException;
	@WebMethod(operationName = "getInterfaceSummariesCountByFileIdAndStatus")
	public Long getInterfaceSummariesCountByFileIdAndStatus(@WebParam(name = "interfaceFileId")Long interfaceFileId, @WebParam(name = "statusIds")List<Long> statusIds) throws ApplicationException;

	@WebMethod(operationName = "prepareRejectionFile")
	public @WebResult(name = "prepareRejectionFile") void prepareRejectionFile(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "partnerIds")List<Long> partnerIds) throws ApplicationException;

	@WebMethod(operationName = "createControlFile")
	public @WebResult(name = "createControlFile") void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId, String contolFileFormat, String contolFilePath) throws ApplicationException;

	@WebMethod(operationName = "sendFile")
	public @WebResult(name = "ResponseBean") ResponseBean sendFile(Long fileId, String fileType) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummaryFileIdBatchIdAndStatus")
	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(@WebParam(name = "interfaceFileId")Long interfaceFileId, @WebParam(name = "refData5")String refData5, @WebParam(name = "status")Long status) throws ApplicationException;


	/*Interface Notification*/
	@WebMethod(operationName = "createInterfaceNotification")
	public @WebResult(name = "createInterfaceNotification")  InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification) throws ApplicationException;


	@WebMethod(operationName = "UpdateInterfaceNotification")
	public @WebResult(name = "UpdateInterfaceNotification") InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceNotificationByInterfaceIdAndStatus")
	public @WebResult(name = "getInterfaceNotificationByInterfaceIdAndStatus")  List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(@WebParam(name = "interfaceId") Long interfaceId,@WebParam(name = "statusId")  Long statusId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceNotificationByInterfaceIdAndScenario")
	public @WebResult(name = "getInterfaceNotificationByInterfaceIdAndScenario")  List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(@WebParam(name = "interfaceId") Long interfaceId,@WebParam(name = "scenario")  String scenario) throws ApplicationException;

	@WebMethod(operationName = "getModules")
	public String getModules() throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFilesByModuleAndOrgId")
	public String getInterfaceFilesByModuleAndOrgId(@WebParam(name="moduleId")Long moduleId,@WebParam(name="orgId")Long orgId,@WebParam(name="orgType") Long orgType) throws ApplicationException;

	@WebMethod(operationName = "verifyInventoryOrder")
	public void verifyInventoryOrder(@WebParam(name="interfaceId")Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummaryDetailsByFileId")
	public String getInterfaceFileSummaryDetailsByFileId(@WebParam(name="fileId")Long fileId) throws ApplicationException;

	@WebMethod(operationName = "getJsonValue")
	public Object getJsonValue(@WebParam(name="path")String path, @WebParam(name="jsonString")String jsonString) throws Exception;

	@WebMethod(operationName = "getInterfaceFileSummaryDetailsByName")
	public @WebResult(name = "getInterfaceFileSummaryDetailsByName") List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(@WebParam(name = "columnName")String columnName,@WebParam(name = "columnValue")String columnValue) throws ApplicationException;

	@WebMethod(operationName = "deleteBounceMailByIdandEmail")
	public @WebResult(name = "deleteBounceMailByIdandEmail") void deleteBounceMailByIdandEmail(@WebParam(name = "bounceId")String bounceId,@WebParam(name = "bounceEmail")String bounceEmail) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummaryDetailsByFileType")
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(@WebParam(name = "fileId")Long fileId,@WebParam(name = "fileType")String fileType) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummaryDetailsById")
	public @WebResult(name = "getInterfaceFileSummaryDetailsById")InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(@WebParam(name = "detailsId")Long detailsId) throws ApplicationException;

	@WebMethod(operationName = "publishToInterface")
	public @WebResult(name = "publishToInterface")ResponseBean publishToInterface(@WebParam(name = "moduleId")Long moduleId, @WebParam(name = "jsonRequestData")String jsonRequestData, @WebParam(name = "refId")String refId) throws ApplicationException;

	@WebMethod(operationName = "syncBalanceInterface")
	public @WebResult(name = "syncBalanceInterface")String syncBalanceInterface(@WebParam(name = "moduleId")Long moduleId, @WebParam(name = "jsonRequestData")String jsonRequestData) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceFileSummaryDetailsByIdAndName")
	public @WebResult(name = "getInterfaceFileSummaryDetailsByIdAndName")List<Object> getInterfaceFileSummaryDetailsByIdAndName(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "columnName")String columnName, @WebParam(name = "columnValue")String columnValue) throws ApplicationException;

	@WebMethod(operationName = "processOrderResponseConsumer")
	public @WebResult(name = "processOrderResponseConsumer")void processOrderResponseConsumer(@WebParam(name = "interfaceId")Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "processCallback")
	public @WebResult(name = "processCallback")void processCallback(@WebParam(name = "transactionId")Long transactionId) throws ApplicationException;

	@WebMethod(operationName = "updateSummaryRequestData")
	public @WebResult(name = "updateSummaryRequestData")InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException;

	@WebMethod(operationName = "createDailyDumpSummary")
	public @WebResult(name = "createDailyDumpSummary")DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException;

	@WebMethod(operationName = "notifyInterfaceOnDump")
	public @WebResult(name = "notifyInterfaceOnDump")ResponseBean notifyInterfaceOnDump(@WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "createdDate")Date createdDate) throws ApplicationException;

	@WebMethod(operationName = "getDailyDumpSummaryByDate")
	public @WebResult(name = "getDailyDumpSummaryByDate")DailyDumpSummary getDailyDumpSummaryByDate(@WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "createdDate")Date createdDate) throws ApplicationException;

	@WebMethod(operationName = "getDailyDumpSummaryByStatus")
	public @WebResult(name = "getDailyDumpSummaryByStatus")List<DailyDumpSummary> getDailyDumpSummaryByStatus(@WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "status") Long status) throws ApplicationException;

	@WebMethod(operationName = "updateDailyDumpSummaryStatus")
	public @WebResult(name = "updateDailyDumpSummaryStatus")DailyDumpSummary updateDailyDumpSummaryStatus(@WebParam(name = "dumpId")Long dumpId, @WebParam(name = "status")Long status) throws ApplicationException;

	@WebMethod(operationName = "publishInternalRequest")
	public @WebResult(name = "publishInternalRequest")ResponseBean publishInternalRequest(@WebParam(name = "moduleId")Long moduleId, @WebParam(name = "jsonRequestData")String jsonRequestData, @WebParam(name = "refId")String refId, @WebParam(name = "refData3")Long refData3) throws ApplicationException;

	@WebMethod(operationName = "getInterfaceSummariesByRefData3")
	public @WebResult(name = "getInterfaceSummariesByRefData3")List<InterfaceSummary> getInterfaceSummariesByRefData3(@WebParam(name = "refData3")Long refData3, @WebParam(name = "interfaceId")Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "getSummaryResponseDataByRefData3")
	public @WebResult(name = "getSummaryResponseDataByRefData3")List<ResponseBean> getSummaryResponseDataByRefData3(@WebParam(name = "refData3")Long refData3) throws ApplicationException;

	@WebMethod(operationName = "getSummaryTransIdsByRefData3")
	public @WebResult(name = "getSummaryTransIdsByRefData3")List<Long> getSummaryTransIdsByRefData3(@WebParam(name = "refData3")Long refData3, @WebParam(name = "interfaceId")Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "getKycSyncAttributesByInterfaceId")
	public @WebResult(name = "getKycSyncAttributesByInterfaceId")List<KycSyncInfo> getKycSyncAttributesByInterfaceId(@WebParam(name = "interfaceId") Long interfaceId) throws ApplicationException;

	@WebMethod(operationName = "removeInterfaceFileSummaryDetails")
	public @WebResult(name = "removeInterfaceFileSummaryDetails") void removeInterfaceFileSummaryDetails(@WebParam(name = "fileDetailsId") Long fileDetailsId) throws ApplicationException;

	@WebMethod(operationName = "reProcessPushDataFiles")
	public @WebResult(name = "reProcessPushDataFiles") void reProcessPushDataFiles(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "requiredDate")Date requiredDate) throws ApplicationException;

	@WebMethod(operationName = "rollbackKPIFeedInterfaces")
	public @WebResult(name = "rollbackKPIFeedInterfaces") void rollbackKPIFeedInterfaces(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "fileId")Long fileId) throws ApplicationException;

	@WebMethod(operationName = "prepareRejectionFileById")
	public @WebResult(name = "prepareRejectionFileById") void prepareRejectionFileById(@WebParam(name = "interfaceId")Long interfaceId, @WebParam(name = "fileId")Long fileId) throws ApplicationException;

	@WebMethod(operationName = "invokePrimarySalesInterface")
	public @WebResult(name = "invokePrimarySalesInterface") String invokePrimarySalesInterface(@WebParam(name = "moduleId") Long moduleId, @WebParam(name = "jsonRequestData") String jsonRequestData) throws ApplicationException;

	@WebMethod(operationName = "tnmServicesInterface")
	public @WebResult(name = "tnmServicesInterface") String tnmServicesInterface(@WebParam(name = "moduleId") Long moduleId, @WebParam(name = "jsonRequestData") String jsonRequestData) throws ApplicationException;

	@WebMethod(operationName = "getCleanUpSummary")
	public @WebResult(name = "getCleanUpSummary") CleanUpSummary getCleanUpSummary(@WebParam(name = "cleanupId") Long cleanupId) throws ApplicationException;

	@WebMethod(operationName = "processCleanupActivity")
	public @WebResult(name = "processCleanupActivity") CleanUpSummary processCleanupActivity(@WebParam(name = "interfaceId") Long interfaceId, @WebParam(name = "userId") String userId, @WebParam(name = "cleanupCode") Long cleanupCode) throws ApplicationException;

	/* ===================================================================================================================== */

	@WebFault(name = "reProcessFileOrdersException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class reProcessFileOrdersException extends ApplicationException
	{
		public reProcessFileOrdersException()
		{
			super();
		}

		public reProcessFileOrdersException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CSVtoJSONConvertionException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CSVtoJSONConvertionException extends ApplicationException
	{
		public CSVtoJSONConvertionException()
		{
			super();
		}

		public CSVtoJSONConvertionException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "MongoRefDataQueryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class MongoRefDataQueryException extends ApplicationException
	{
		public MongoRefDataQueryException()
		{
			super();
		}

		public MongoRefDataQueryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "receiveFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class receiveFileException extends ApplicationException
	{
		public receiveFileException()
		{
			super();
		}

		public receiveFileException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryDetailsException extends ApplicationException
	{
		public GetInterfaceFileSummaryDetailsException()
		{
			super();
		}

		public GetInterfaceFileSummaryDetailsException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetModuleByNameException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetModuleByNameException extends ApplicationException
	{
		public GetModuleByNameException()
		{
			super();
		}

		public GetModuleByNameException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetModuleException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetModuleException extends ApplicationException
	{
		public GetModuleException()
		{
			super();
		}

		public GetModuleException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceByIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceByIdException extends ApplicationException
	{
		public GetInterfaceByIdException()
		{
			super();
		}

		public GetInterfaceByIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfacesByTransactionTypeException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfacesByTransactionTypeException extends ApplicationException
	{
		public GetInterfacesByTransactionTypeException()
		{
			super();
		}

		public GetInterfacesByTransactionTypeException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceAttributesException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceAttributesException extends ApplicationException
	{
		public GetInterfaceAttributesException()
		{
			super();
		}

		public GetInterfaceAttributesException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummaryException extends ApplicationException
	{
		public GetInterfaceSummaryException()
		{
			super();
		}

		public GetInterfaceSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}


	@WebFault(name = "GetInterfaceSummariesByStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummariesByStatusException extends ApplicationException
	{
		public GetInterfaceSummariesByStatusException()
		{
			super();
		}

		public GetInterfaceSummariesByStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateInterfaceSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateInterfaceSummaryException extends ApplicationException
	{
		public CreateInterfaceSummaryException()
		{
			super();
		}

		public CreateInterfaceSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateInterfaceFileSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateInterfaceFileSummaryException extends ApplicationException
	{
		public CreateInterfaceFileSummaryException()
		{
			super();
		}

		public CreateInterfaceFileSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateInterfaceSummariesByModuleException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateInterfaceSummariesByModuleException extends ApplicationException
	{
		public CreateInterfaceSummariesByModuleException()
		{
			super();
		}

		public CreateInterfaceSummariesByModuleException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateInterfaceFileSummaryDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateInterfaceFileSummaryDetailsException extends ApplicationException
	{
		public CreateInterfaceFileSummaryDetailsException()
		{
			super();
		}

		public CreateInterfaceFileSummaryDetailsException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryByInterfaceIdAndStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryByInterfaceIdAndStatusException extends ApplicationException
	{
		public GetInterfaceFileSummaryByInterfaceIdAndStatusException()
		{
			super();
		}

		public GetInterfaceFileSummaryByInterfaceIdAndStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessReceivedFilesException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessReceivedFilesException extends ApplicationException
	{
		public ProcessReceivedFilesException()
		{
			super();
		}

		public ProcessReceivedFilesException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReProcessFileBasedOrdersException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReProcessFileBasedOrdersException extends ApplicationException
	{
		public ReProcessFileBasedOrdersException()
		{
			super();
		}

		public ReProcessFileBasedOrdersException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReProcessFilesBasedOnErrorCodeException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReProcessFilesBasedOnErrorCodeException extends ApplicationException
	{
		public ReProcessFilesBasedOnErrorCodeException()
		{
			super();
		}

		public ReProcessFilesBasedOnErrorCodeException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PullDataToFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PullDataToFileException extends ApplicationException
	{
		public PullDataToFileException()
		{
			super();
		}

		public PullDataToFileException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateInterfaceSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateInterfaceSummaryException extends ApplicationException
	{
		public UpdateInterfaceSummaryException()
		{
			super();
		}

		public UpdateInterfaceSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateInterfaceSummaryStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateInterfaceSummaryStatusException extends ApplicationException
	{
		public UpdateInterfaceSummaryStatusException()
		{
			super();
		}

		public UpdateInterfaceSummaryStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFailureException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFailureException extends ApplicationException
	{
		public GetInterfaceFailureException()
		{
			super();
		}

		public GetInterfaceFailureException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFailureByTransactionIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFailureByTransactionIdException extends ApplicationException
	{
		public GetInterfaceFailureByTransactionIdException()
		{
			super();
		}

		public GetInterfaceFailureByTransactionIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateInterfaceFailureException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateInterfaceFailureException extends ApplicationException
	{
		public CreateInterfaceFailureException()
		{
			super();
		}

		public CreateInterfaceFailureException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessInterfaceSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessInterfaceSummaryException extends ApplicationException
	{
		public ProcessInterfaceSummaryException()
		{
			super();
		}

		public ProcessInterfaceSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessInQueueRecordsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessInQueueRecordsException extends ApplicationException
	{
		public ProcessInQueueRecordsException()
		{
			super();
		}

		public ProcessInQueueRecordsException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PublishInterfaceDataException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PublishInterfaceDataException extends ApplicationException
	{
		public PublishInterfaceDataException()
		{
			super();
		}

		public PublishInterfaceDataException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PublishInterfaceRecordsException") 
	//@javax.ejb.ApplicationException(rollback = true) 
	public static class PublishInterfaceRecordsException extends ApplicationException 
	{ 
		public PublishInterfaceRecordsException() 
		{ 
			super(); 
		} 

		public PublishInterfaceRecordsException(String message, Throwable cause) 
		{ 
			super(message, cause); 
		}	 
	} 

	@WebFault(name = "ProcessResponseConsumersException") 
	//@javax.ejb.ApplicationException(rollback = true) 
	public static class ProcessResponseConsumersException extends ApplicationException 
	{ 
		public ProcessResponseConsumersException() 
		{ 
			super(); 
		} 

		public ProcessResponseConsumersException(String message, Throwable cause) 
		{ 
			super(message, cause); 
		} 
	}

	@WebFault(name = "ProcessResponseAvailableRecordsException") 
	//@javax.ejb.ApplicationException(rollback = true) 
	public static class ProcessResponseAvailableRecordsException extends ApplicationException 
	{ 
		public ProcessResponseAvailableRecordsException() 
		{ 
			super(); 
		} 

		public ProcessResponseAvailableRecordsException(String message, Throwable cause) 
		{ 
			super(message, cause); 
		} 
	} 

	@WebFault(name = "UpdateInterfaceSummaryByResponseException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateInterfaceSummaryByResponseException extends ApplicationException
	{
		public UpdateInterfaceSummaryByResponseException()
		{
			super();
		}

		public UpdateInterfaceSummaryByResponseException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetTransactionIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetTransactionIdException extends ApplicationException
	{
		public GetTransactionIdException()
		{
			super();
		}

		public GetTransactionIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummaryByRefData1Exception")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummaryByRefData1Exception extends ApplicationException
	{
		public GetInterfaceSummaryByRefData1Exception()
		{
			super();
		}

		public GetInterfaceSummaryByRefData1Exception(String message, Throwable cause)
		{
			super(message, cause);
		}
	}


	@WebFault(name = "GetApplicationInterfaceAttributeException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetApplicationInterfaceAttributeException extends ApplicationException
	{
		public GetApplicationInterfaceAttributeException()
		{
			super();
		}

		public GetApplicationInterfaceAttributeException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "SaveUploadDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class SaveUploadDetailsException extends ApplicationException 
	{
		public SaveUploadDetailsException() 
		{
			super();
		}

		public SaveUploadDetailsException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetUploadDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetUploadDetailsException extends ApplicationException 
	{
		public GetUploadDetailsException() 
		{
			super();
		}

		public GetUploadDetailsException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpadateUploadDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpadateUploadDetailsException extends ApplicationException 
	{
		public UpadateUploadDetailsException() 
		{
			super();
		}

		public UpadateUploadDetailsException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ConfigureDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ConfigureDetailsException extends ApplicationException 
	{
		public ConfigureDetailsException() 
		{
			super();
		}

		public ConfigureDetailsException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetConfiguredDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetConfiguredDetailsException extends ApplicationException 
	{
		public GetConfiguredDetailsException() 
		{
			super();
		}

		public GetConfiguredDetailsException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateConfiguredDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateConfiguredDetailsException extends ApplicationException 
	{
		public UpdateConfiguredDetailsException() 
		{
			super();
		}

		public UpdateConfiguredDetailsException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "mandatoryFieldMissingException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class MandatoryFieldMissingException extends ApplicationException 
	{
		public MandatoryFieldMissingException() 
		{
			super();
		}

		public MandatoryFieldMissingException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "InvalidDataException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class InvalidDataException extends ApplicationException 
	{
		public InvalidDataException() 
		{
			super();
		}

		public InvalidDataException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessFileException extends ApplicationException 
	{
		public ProcessFileException() 
		{
			super();
		}

		public ProcessFileException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PrepareFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PrepareFileException extends ApplicationException 
	{
		public PrepareFileException() 
		{
			super();
		}

		public PrepareFileException(String message, Throwable cause) 
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateInterfaceFileSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateInterfaceFileSummaryException extends ApplicationException
	{
		public UpdateInterfaceFileSummaryException()
		{
			super();
		}

		public UpdateInterfaceFileSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateInterfaceFileSummaryDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateInterfaceFileSummaryDetailsException extends ApplicationException
	{
		public UpdateInterfaceFileSummaryDetailsException()
		{
			super();
		}

		public UpdateInterfaceFileSummaryDetailsException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryException extends ApplicationException
	{
		public GetInterfaceFileSummaryException()
		{
			super();
		}

		public GetInterfaceFileSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryByInterfaceIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryByInterfaceIdException extends ApplicationException
	{
		public GetInterfaceFileSummaryByInterfaceIdException()
		{
			super();
		}

		public GetInterfaceFileSummaryByInterfaceIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PullDataException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PullDataException extends ApplicationException
	{
		public PullDataException()
		{
			super();
		}

		public PullDataException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "DateParseException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class DateParseException extends ApplicationException
	{
		public DateParseException()
		{
			super();
		}

		public DateParseException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummaryByRefData5Exception")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummaryByRefData5Exception extends ApplicationException
	{
		public GetInterfaceSummaryByRefData5Exception()
		{
			super();
		}

		public GetInterfaceSummaryByRefData5Exception(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummaryByRefData4Exception")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummaryByRefData4Exception extends ApplicationException
	{
		public GetInterfaceSummaryByRefData4Exception()
		{
			super();
		}

		public GetInterfaceSummaryByRefData4Exception(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceAttributeValueException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceAttributeValueException extends ApplicationException
	{
		public GetInterfaceAttributeValueException()
		{
			super();
		}

		public GetInterfaceAttributeValueException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetExternalPartyIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetExternalPartyIdException extends ApplicationException
	{
		public GetExternalPartyIdException()
		{
			super();
		}

		public GetExternalPartyIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReadFilePropertyBeansException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReadFilePropertyBeansException extends ApplicationException
	{
		public ReadFilePropertyBeansException()
		{
			super();
		}

		public ReadFilePropertyBeansException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	/*@WebFault(name = "IntrefaceSummaryNotExistsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class IntrefaceSummaryNotExistsException extends ApplicationException
	{
		public IntrefaceSummaryNotExistsException()
		{
			super();
		}

		public IntrefaceSummaryNotExistsException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}*/

	@WebFault(name = "PrepareRejectionFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PrepareRejectionFileException extends ApplicationException
	{
		public PrepareRejectionFileException()
		{
			super();
		}

		public PrepareRejectionFileException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummariesByFileIdAndStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummariesByFileIdAndStatusException extends ApplicationException
	{
		public GetInterfaceSummariesByFileIdAndStatusException()
		{
			super();
		}

		public GetInterfaceSummariesByFileIdAndStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummariesByFileIdAndStatusWithLimitException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummariesByFileIdAndStatusWithLimitException extends ApplicationException
	{
		public GetInterfaceSummariesByFileIdAndStatusWithLimitException()
		{
			super();
		}

		public GetInterfaceSummariesByFileIdAndStatusWithLimitException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummariesByFileIdAndStatusWithLimitException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummariesCountByFileIdAndStatusException extends ApplicationException
	{
		public GetInterfaceSummariesCountByFileIdAndStatusException()
		{
			super();
		}

		public GetInterfaceSummariesCountByFileIdAndStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}


	@WebFault(name = "CreateControlFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateControlFileException extends ApplicationException
	{
		public CreateControlFileException()
		{
			super();
		}

		public CreateControlFileException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "SendFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class SendFileException extends ApplicationException
	{
		public SendFileException()
		{
			super();
		}

		public SendFileException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummaryByFileIdBatchIdAndStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummaryByFileIdBatchIdAndStatusException extends ApplicationException
	{
		public GetInterfaceSummaryByFileIdBatchIdAndStatusException()
		{
			super();
		}

		public GetInterfaceSummaryByFileIdBatchIdAndStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateInterfaceNotificationException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateInterfaceNotificationException extends ApplicationException
	{
		public CreateInterfaceNotificationException()
		{
			super();
		}

		public CreateInterfaceNotificationException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateInterfaceNotificationException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateInterfaceNotificationException extends ApplicationException
	{
		public UpdateInterfaceNotificationException()
		{
			super();
		}

		public UpdateInterfaceNotificationException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceNotificationByInterfaceIdAndStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceNotificationByInterfaceIdAndStatusException extends ApplicationException
	{
		public GetInterfaceNotificationByInterfaceIdAndStatusException()
		{
			super();
		}

		public GetInterfaceNotificationByInterfaceIdAndStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceNotificationByInterfaceIdAndScenarioException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceNotificationByInterfaceIdAndScenarioException extends ApplicationException
	{
		public GetInterfaceNotificationByInterfaceIdAndScenarioException()
		{
			super();
		}

		public GetInterfaceNotificationByInterfaceIdAndScenarioException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetModulesException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetModulesException extends ApplicationException
	{
		public GetModulesException()
		{
			super();
		}

		public GetModulesException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFilesByModuleAndOrgIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFilesByModuleAndOrgIdException extends ApplicationException
	{
		public GetInterfaceFilesByModuleAndOrgIdException()
		{
			super();
		}

		public GetInterfaceFilesByModuleAndOrgIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "VerifyInventoryOrderException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class VerifyInventoryOrderException extends ApplicationException
	{
		public VerifyInventoryOrderException()
		{
			super();
		}

		public VerifyInventoryOrderException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryDetailsByFileIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryDetailsByFileIdException extends ApplicationException
	{
		public GetInterfaceFileSummaryDetailsByFileIdException()
		{
			super();
		}

		public GetInterfaceFileSummaryDetailsByFileIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}


	@WebFault(name = "GetJsonValueException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetJsonValueException extends ApplicationException
	{
		public GetJsonValueException()
		{
			super();
		}

		public GetJsonValueException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PrepareEmptyFileException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PrepareEmptyFileException extends ApplicationException
	{
		public PrepareEmptyFileException()
		{
			super();
		}

		public PrepareEmptyFileException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}
	@WebFault(name = "GetInterfaceFileSummaryDetailsByNameException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryDetailsByNameException extends ApplicationException
	{
		public GetInterfaceFileSummaryDetailsByNameException()
		{
			super();
		}

		public GetInterfaceFileSummaryDetailsByNameException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "DeleteBounceMailByIdandEmailException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class DeleteBounceMailByIdandEmailException extends ApplicationException
	{
		public DeleteBounceMailByIdandEmailException()
		{
			super();
		}

		public DeleteBounceMailByIdandEmailException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "BounceEmailException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class BounceEmailException extends ApplicationException
	{
		public BounceEmailException()
		{
			super();
		}

		public BounceEmailException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryDetailsByFileTypeException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryDetailsByFileTypeException extends ApplicationException
	{
		public GetInterfaceFileSummaryDetailsByFileTypeException()
		{
			super();
		}

		public GetInterfaceFileSummaryDetailsByFileTypeException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "StockDumpMergerException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class StockDumpMergerException extends ApplicationException
	{
		public StockDumpMergerException()
		{
			super();
		}

		public StockDumpMergerException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryDetailsByIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryDetailsByIdException extends ApplicationException
	{
		public GetInterfaceFileSummaryDetailsByIdException()
		{
			super();
		}

		public GetInterfaceFileSummaryDetailsByIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "SerialExpiryMergerException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class SerialExpiryMergerException extends ApplicationException
	{
		public SerialExpiryMergerException()
		{
			super();
		}

		public SerialExpiryMergerException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PublishToInterfaceException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PublishToInterfaceException extends ApplicationException
	{
		public PublishToInterfaceException()
		{
			super();
		}

		public PublishToInterfaceException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "SyncBalanceInterfaceExeption")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class SyncBalanceInterfaceExeption extends ApplicationException
	{
		public SyncBalanceInterfaceExeption()
		{
			super();
		}

		public SyncBalanceInterfaceExeption(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ConvertRequestException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ConvertRequestException extends ApplicationException
	{
		public ConvertRequestException()
		{
			super();
		}

		public ConvertRequestException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "DataConverterException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class DataConverterException extends ApplicationException
	{
		public DataConverterException()
		{
			super();
		}

		public DataConverterException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UserSyncMergerException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UserSyncMergerException extends ApplicationException
	{
		public UserSyncMergerException()
		{
			super();
		}

		public UserSyncMergerException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProductCreationException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProductCreationException extends ApplicationException
	{
		public ProductCreationException()
		{
			super();
		}

		public ProductCreationException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceFileSummaryDetailsByIdAndNameException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceFileSummaryDetailsByIdAndNameException extends ApplicationException
	{
		public GetInterfaceFileSummaryDetailsByIdAndNameException()
		{
			super();
		}

		public GetInterfaceFileSummaryDetailsByIdAndNameException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessOrderResponseConsumerException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessOrderResponseConsumerException extends ApplicationException
	{
		public ProcessOrderResponseConsumerException()
		{
			super();
		}

		public ProcessOrderResponseConsumerException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}


	@WebFault(name = "ProcessCallbackException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessCallbackException extends ApplicationException
	{
		public ProcessCallbackException()
		{
			super();
		}

		public ProcessCallbackException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateSummaryRequestDataException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateSummaryRequestDataException extends ApplicationException
	{
		public UpdateSummaryRequestDataException()
		{
			super();
		}

		public UpdateSummaryRequestDataException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "CreateDailyDumpSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class CreateDailyDumpSummaryException extends ApplicationException
	{
		public CreateDailyDumpSummaryException()
		{
			super();
		}

		public CreateDailyDumpSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "NotifyInterfaceOnDumpException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class NotifyInterfaceOnDumpException extends ApplicationException
	{
		public NotifyInterfaceOnDumpException()
		{
			super();
		}

		public NotifyInterfaceOnDumpException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetDailyDumpSummaryByDateException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetDailyDumpSummaryByDateException extends ApplicationException
	{
		public GetDailyDumpSummaryByDateException()
		{
			super();
		}

		public GetDailyDumpSummaryByDateException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetDailyDumpSummaryByStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetDailyDumpSummaryByStatusException extends ApplicationException
	{
		public GetDailyDumpSummaryByStatusException()
		{
			super();
		}

		public GetDailyDumpSummaryByStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "UpdateDailyDumpSummaryStatusException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class UpdateDailyDumpSummaryStatusException extends ApplicationException
	{
		public UpdateDailyDumpSummaryStatusException()
		{
			super();
		}

		public UpdateDailyDumpSummaryStatusException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PublishInternalRequestException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PublishInternalRequestException extends ApplicationException
	{
		public PublishInternalRequestException()
		{
			super();
		}

		public PublishInternalRequestException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetInterfaceSummariesByRefData3Exception")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetInterfaceSummariesByRefData3Exception extends ApplicationException
	{
		public GetInterfaceSummariesByRefData3Exception()
		{
			super();
		}

		public GetInterfaceSummariesByRefData3Exception(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "OrgSyncMergerException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class OrgSyncMergerException extends ApplicationException
	{
		public OrgSyncMergerException()
		{
			super();
		}

		public OrgSyncMergerException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReProcessOrgSyncException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReProcessOrgSyncException extends ApplicationException
	{
		public ReProcessOrgSyncException()
		{
			super();
		}

		public ReProcessOrgSyncException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReProcessUserSyncException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReProcessUserSyncException extends ApplicationException
	{
		public ReProcessUserSyncException()
		{
			super();
		}

		public ReProcessUserSyncException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetSummaryTransIdsByRefData3Exception")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetSummaryTransIdsByRefData3Exception extends ApplicationException
	{
		public GetSummaryTransIdsByRefData3Exception()
		{
			super();
		}

		public GetSummaryTransIdsByRefData3Exception(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetKycSyncAttributesByInterfaceIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetKycSyncAttributesByInterfaceIdException extends ApplicationException
	{
		public GetKycSyncAttributesByInterfaceIdException()
		{
			super();
		}

		public GetKycSyncAttributesByInterfaceIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "InterfaceServiceException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class InterfaceServiceException extends ApplicationException
	{
		public InterfaceServiceException()
		{
			super();
		}

		public InterfaceServiceException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebMethod(operationName = "searchSO")
	public @WebResult(name = "searchSO")String searchSO(@WebParam(name = "soId") String soId) throws ApplicationException;

	@WebFault(name = "SearchSOException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class SearchSOException extends ApplicationException
	{
		public SearchSOException()
		{
			super();
		}

		public SearchSOException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "RemoveInterfaceFileSummaryDetailsException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class RemoveInterfaceFileSummaryDetailsException extends ApplicationException
	{
		public RemoveInterfaceFileSummaryDetailsException()
		{
			super();
		}

		public RemoveInterfaceFileSummaryDetailsException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReProcessPushDataFilesException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReProcessPushDataFilesException extends ApplicationException
	{
		public ReProcessPushDataFilesException()
		{
			super();
		}

		public ReProcessPushDataFilesException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "RollbackKPIFeedInterfacesException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class RollbackKPIFeedInterfacesException extends ApplicationException
	{
		public RollbackKPIFeedInterfacesException()
		{
			super();
		}
		public  RollbackKPIFeedInterfacesException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "PrepareRejectionFileByIdException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class PrepareRejectionFileByIdException extends ApplicationException
	{
		public PrepareRejectionFileByIdException()
		{
			super();
		}
		public  PrepareRejectionFileByIdException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ReprocessKPIAggregationException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ReprocessKPIAggregationException extends ApplicationException
	{
		public ReprocessKPIAggregationException()
		{
			super();
		}
		public  ReprocessKPIAggregationException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetFilesForReprocessException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetFilesForReprocessException extends ApplicationException
	{
		public GetFilesForReprocessException()
		{
			super();
		}
		public  GetFilesForReprocessException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetSummaryResponseDataByRefData3Exception")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetSummaryResponseDataByRefData3Exception extends ApplicationException
	{
		public GetSummaryResponseDataByRefData3Exception()
		{
			super();
		}
		public  GetSummaryResponseDataByRefData3Exception(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "InvokePrimarySalesInterfaceException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class InvokePrimarySalesInterfaceException extends ApplicationException
	{
		public InvokePrimarySalesInterfaceException()
		{
			super();
		}
		public  InvokePrimarySalesInterfaceException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "TNMServicesInterfaceException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class TNMServicesInterfaceException extends ApplicationException
	{
		public TNMServicesInterfaceException()
		{
			super();
		}
		public  TNMServicesInterfaceException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessSummaryRequestException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessSummaryRequestException extends ApplicationException
	{
		public ProcessSummaryRequestException()
		{
			super();
		}
		public  ProcessSummaryRequestException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "GetCleanUpSummaryException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class GetCleanUpSummaryException extends ApplicationException
	{
		public GetCleanUpSummaryException()
		{
			super();
		}

		public GetCleanUpSummaryException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

	@WebFault(name = "ProcessCleanupActivityException")
	//@javax.ejb.ApplicationException(rollback = true)
	public static class ProcessCleanupActivityException extends ApplicationException
	{
		public ProcessCleanupActivityException()
		{
			super();
		}
		public  ProcessCleanupActivityException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}

}