package com.enhancesys.integration.snoc.services.interfaces.rest;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
 * 		Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        04-07-2019          Vinayak Mahadev
 *  -- Base Release
 *    
 * </pre>
 * 
 * <br>
 */

@RequestMapping("/snoc/rest/integration/services")
public interface IntegrationManagementRest
{
	
	@RequestMapping(path = "/getModuleByName/{name}", method = RequestMethod.GET)
	public Module getModuleByName(@PathVariable("name") String name) throws ApplicationException;
	
	@RequestMapping(path = "/getModule/{moduleId}", method = RequestMethod.GET)
	public Module getModule(@PathVariable("moduleId") Long moduleId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceById/{interfaceId}", method = RequestMethod.GET)
	public Interfaces getInterfaceById(Long interfaceId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfacesByTransactionType/{transactionType}", method = RequestMethod.GET)
	public List<Interfaces> getInterfacesByTransactionType(@PathVariable("transactionType") Long transactionType) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceAttributes/{interfaceId}", method = RequestMethod.GET)
	public Set<InterfaceAttribute> getInterfaceAttributes(@PathVariable("interfaceId") Long interfaceId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceSummary/{transactionId}", method = RequestMethod.GET)
	public InterfaceSummary getInterfaceSummary(@PathVariable("transactionId") Long transactionId) throws ApplicationException;

	@RequestMapping(value = "/getInterfaceSummariesByStatus/{status}", method = RequestMethod.GET)
	public List<InterfaceSummary> getInterfaceSummariesByStatus(@PathVariable("status") Long status) throws ApplicationException;
	
	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException;

	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException;
	
	@RequestMapping(value = "/updateInterfaceSummaryStatus/{transactionId}/{status}", method = RequestMethod.PUT)
	public InterfaceSummary updateInterfaceSummaryStatus(@PathVariable("transactionId")Long transactionId, @PathVariable("status")Long status) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFailure/{transactionFailureId}", method = RequestMethod.GET)
	public InterfaceFailure getInterfaceFailure(@PathVariable("transactionFailureId")Long transactionFailureId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFailureByTransactionId/{transactionId}", method = RequestMethod.GET)
	public List<InterfaceFailure> getInterfaceFailureByTransactionId(@PathVariable("transactionId")Long transactionId) throws ApplicationException;
	
	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException;
	
	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners) throws ApplicationException;
	
	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException; 
	  
	public void processResponseConsumers(Long interfaceId) throws ApplicationException;
	
	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException;
	
	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException;
	
	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode) throws ApplicationException;
	
	@RequestMapping(value = "/getFilesForReprocess/{interfaceId}", method = RequestMethod.GET)
	public ReprocessFileBean getFilesForReprocess(@PathVariable("interfaceId") Long interfaceId) throws ApplicationException;
	
	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException;
	
	@RequestMapping(value = "/getTransactionId/{interfaceId}/{responseData}", method = RequestMethod.GET)
	public String getTransactionId(@PathVariable("interfaceId")Long interfaceId, @PathVariable("responseData")String responseData) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceSummaryByRefData1/{refData1}/{refData2}/interfaceId/{status}{refData5}", method = RequestMethod.GET)
	public List<InterfaceSummary> getInterfaceSummaryByRefData1(@PathVariable("refData1")String refData1, @PathVariable("refData2")Long refData2, @PathVariable("interfaceId")Long interfaceId, @PathVariable("status")Long status, @PathVariable("refData5")Long refData5) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceSummaryByRefData5/{refData5}/{status}", method = RequestMethod.GET)
	public InterfaceSummary getInterfaceSummaryByRefData5(@PathVariable("refData5")String refData5, @PathVariable("status")Long status) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceSummaryByRefData4/{refData4}", method = RequestMethod.GET)
	public InterfaceSummary getInterfaceSummaryByRefData4(@PathVariable("refData4") Long refData4) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceAttributeValue/{interfaceId}/{parameterName}", method = RequestMethod.GET)
	public String getInterfaceAttributeValue(@PathVariable("interfaceId") Long interfaceId, @PathVariable("parameterName")String parameterName) throws ApplicationException;
	
	public void updateInterfaceSummaryByResponse(String responseString,Long transactionId, Long interfaceId, Long status, Long retryCount)throws ApplicationException;
	 
	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId) throws ApplicationException;

	public void processFile(List<Long> interfaceIds) throws ApplicationException;

	@RequestMapping(value = "/getInterfaceFileSummary/{fileId}", method = RequestMethod.GET)
	public InterfaceFileSummary getInterfaceFileSummary(@PathVariable("fileIds") Long fileId) throws ApplicationException;
	
	public InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException;
	
	public InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException;
	
	public InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException;
	
	@RequestMapping(value = "/readFilePropertyBeans/{interfaceId}", method = RequestMethod.GET)
	public List<FilePropertiesBean> readFilePropertyBeans(@PathVariable("interfaceId")Long interfaceId) throws ApplicationException;
	
	public InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFileSummaryDetails/{fileId}", method = RequestMethod.GET)
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(@PathVariable("fileId")Long fileId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFileSummaryByInterfaceIdAndStatus/{interfaceId}/{statusId}/{partnerId}", method = RequestMethod.GET)
	public List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(@PathVariable("interfaceId")Long interfaceId, @PathVariable("statusId")Long statusId, @PathVariable("partnerId")Long partnerId) throws ApplicationException;
	
	public void pullDataToFile(List<Long> interfaceIds) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(Long interfaceFileId, List<Long> statusIds,Long limit,Long interfaceId, List<Long> fileIds,Short retryCount) throws ApplicationException;
	
	public Long getInterfaceSummariesCountByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds) throws ApplicationException;
	
	public void prepareRejectionFile(Long interfaceIds, List<Long> partnerIds) throws ApplicationException;
	
	public void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId, String contolFileFormat, String contolFilePath) throws ApplicationException;
	
	public ResponseBean sendFile(Long fileId, String fileType) throws ApplicationException;
	
	public InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification) throws ApplicationException;

	public InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceNotificationByInterfaceIdAndStatus/{interfaceId}/{statusId}", method = RequestMethod.GET)
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(@PathVariable("interfaceId")Long interfaceId, @PathVariable("statusId")Long statusId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceNotificationByInterfaceIdAndScenario/{interfaceId}/{scenario}", method = RequestMethod.GET)
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(@PathVariable("interfaceId")Long interfaceId, @PathVariable("scenario")String scenario) throws ApplicationException;
	
	@RequestMapping(value = "/getModules", method = RequestMethod.GET)
	public String getModules() throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFilesByModuleAndOrgId", method = RequestMethod.GET)
	public String getInterfaceFilesByModuleAndOrgId(@PathVariable("moduleId")Long moduleId,@PathVariable("orgId")Long orgId,@PathVariable("orgType")Long orgType) throws ApplicationException;
	
	public void verifyInventoryOrder(Long interfaceId) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFilesByModuleAndOrgId/{fileId}", method = RequestMethod.GET)
	public String getInterfaceFileSummaryDetailsByFileId(@PathVariable("fileId")Long fileId) throws ApplicationException;
	
	public Object getJsonValue(String path, String jsonString) throws Exception;
	
	@RequestMapping(value = "/getInterfaceFileSummaryDetailsByName/{columnName}/{columnValue}", method = RequestMethod.GET)
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(@PathVariable("columnName")String columnName, @PathVariable("columnValue")String columnValue) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFilesByModuleAndOrgId/{columnName}/{BounceEmail}", method = RequestMethod.GET)
	public void  deleteBounceMailByIdandEmail(@PathVariable("BounceId")String BounceId, @PathVariable("BounceEmail") String BounceEmail)  throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFileSummaryDetailsByFileType/{fileId}/{fileType}", method = RequestMethod.GET)
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(@PathVariable("fileId")Long fileId,@PathVariable("fileType")String fileType) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceFileSummaryDetailsByFileType/{detailsId}", method = RequestMethod.GET)
	public InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(@PathVariable("detailsId")Long detailsId) throws ApplicationException;
	
	public ResponseBean publishToInterface(Long moduleId, String jsonRequestData, String refId) throws ApplicationException;
	
	public String syncBalanceInterface(Long moduleId, String jsonRequestData) throws ApplicationException;
	
	public List<Object> getInterfaceFileSummaryDetailsByIdAndName(Long interfaceId, String columnName,String columnValue) throws ApplicationException;
	
	public void processOrderResponseConsumer(Long interfaceId) throws ApplicationException;
	
	public void processCallback(Long transactionId) throws ApplicationException;
	
	public InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException;
	
	public DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException;
	
	public ResponseBean notifyInterfaceOnDump(Long interfaceId, Date createdDate) throws ApplicationException;
	
	@RequestMapping(value = "/getDailyDumpSummaryByDate/{interfaceId}/{createdDate}", method = RequestMethod.GET)
	public DailyDumpSummary getDailyDumpSummaryByDate(@PathVariable("interfaceId")Long interfaceId,@PathVariable("createdDate") Date createdDate) throws ApplicationException;
	
	@RequestMapping(value = "/getDailyDumpSummaryByStatus/{interfaceId}/{status}", method = RequestMethod.GET)
	public List<DailyDumpSummary> getDailyDumpSummaryByStatus(@PathVariable("interfaceId")Long interfaceId, @PathVariable("status")Long status) throws ApplicationException;
	
	@RequestMapping(value = "/updateDailyDumpSummaryStatus/{dumpId}/{status}", method = RequestMethod.PUT)
	public DailyDumpSummary updateDailyDumpSummaryStatus(@PathVariable("dumpId")Long dumpId, @PathVariable("status")Long status) throws ApplicationException;
	
	public ResponseBean publishInternalRequest(Long moduleId, String jsonRequestData, String refId, Long refData3) throws ApplicationException;
	
	@RequestMapping(value = "/getInterfaceSummariesByRefData3/{refData3}/{interfaceId}", method = RequestMethod.GET)
	public List<InterfaceSummary> getInterfaceSummariesByRefData3( @PathVariable("refData3")Long refData3,  @PathVariable("interfaceId")Long interfaceId) throws ApplicationException;
	
	@RequestMapping(value = "/getSummaryResponseDataByRefData3/{refData3}", method = RequestMethod.GET)
	public List<ResponseBean> getSummaryResponseDataByRefData3(@PathVariable("refData3")Long refData3) throws ApplicationException;
	
	@RequestMapping(value = "/getSummaryTransIdsByRefData3/{refData3}/{interfaceId}", method = RequestMethod.GET)
	public List<Long> getSummaryTransIdsByRefData3(@PathVariable("refData3")Long refData3, @PathVariable("interfaceId")Long interfaceId) throws ApplicationException;
	
	@RequestMapping(value = "/getKycSyncAttributesByInterfaceId/{interfaceId}", method = RequestMethod.GET)
	public List<KycSyncInfo> getKycSyncAttributesByInterfaceId(Long interfaceId) throws ApplicationException;
	
	public String searchSO(String soId) throws ApplicationException;
	
	public void removeInterfaceFileSummaryDetails(Long fileDetailsId) throws ApplicationException;
	
	public void reProcessPushDataFiles(Long interfaceId, Date requiredDate) throws ApplicationException;
	
	public void rollbackKPIFeedInterfaces(Long interfaceId, Long fileId) throws ApplicationException;
	
	public void prepareRejectionFileById(Long interfaceId, Long fileId) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(Long interfaceFileId, String refData5, Long status) throws ApplicationException;
	
	public String invokePrimarySalesInterface(Long moduleId, String jsonRequestData) throws ApplicationException;
	
	public String tnmServicesInterface(Long moduleId, String jsonRequestData) throws ApplicationException;
	
	@RequestMapping(value = "/getCleanUpSummary/{cleanupId}", method = RequestMethod.GET)
	public CleanUpSummary getCleanUpSummary(@PathVariable("cleanupId")Long cleanupId) throws ApplicationException;
	
	public CleanUpSummary processCleanupActivity(Long interfaceId, String userId, Long cleanupCode) throws ApplicationException;

	public void createInterfaceSummariesByModule(InterfaceSummary interfaceSummary, Long moduleId, Long transactionType) throws ApplicationException;
	
}