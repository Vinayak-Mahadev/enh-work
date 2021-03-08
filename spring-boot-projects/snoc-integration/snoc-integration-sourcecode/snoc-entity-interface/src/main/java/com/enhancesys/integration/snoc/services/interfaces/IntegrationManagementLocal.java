package com.enhancesys.integration.snoc.services.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

 * </pre>
 * 
 * <br>
 */

public interface IntegrationManagementLocal
{
	public Module getModuleByName(String name) throws ApplicationException;
	
	public Module getModule(Long moduleId) throws ApplicationException;
	
	public Interfaces getInterfaceById(Long interfaceId) throws ApplicationException;
	
	public List<Interfaces> getInterfacesByTransactionType(Long transactionType) throws ApplicationException;
	
	public Set<InterfaceAttribute> getInterfaceAttributes(Long interfaceId) throws ApplicationException;
	
	public InterfaceSummary getInterfaceSummary(Long transactionId) throws ApplicationException;

	public List<InterfaceSummary> getInterfaceSummariesByStatus(Long status) throws ApplicationException;
	
	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException;

	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException;
	
	public InterfaceSummary updateInterfaceSummaryStatus(Long transactionId, Long status) throws ApplicationException;
	
	public InterfaceFailure getInterfaceFailure(Long transactionFailureId) throws ApplicationException;
	
	public List<InterfaceFailure> getInterfaceFailureByTransactionId(Long transactionId) throws ApplicationException;
	
	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException;
	
	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners) throws ApplicationException;
	
	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException; 
	  
	public void processResponseConsumers(Long interfaceId) throws ApplicationException;
	
	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException;
	
	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException;
	
	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode) throws ApplicationException;
	
	public ReprocessFileBean getFilesForReprocess(Long interfaceId) throws ApplicationException;
	
	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException;
	
	public String getTransactionId(Long interfaceId, String responseData) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummaryByRefData1(String refData1, Long refData2, Long interfaceId, Long status, Long refData5) throws ApplicationException;
	
	public InterfaceSummary getInterfaceSummaryByRefData5(String refData5, Long status) throws ApplicationException;
	
	public InterfaceSummary getInterfaceSummaryByRefData4(Long refData4) throws ApplicationException;
	
	public String getInterfaceAttributeValue(Long interfaceId, String parameterName) throws ApplicationException;
	
	public void updateInterfaceSummaryByResponse(String responseString,Long transactionId, Long interfaceId, Long status, Long retryCount)throws ApplicationException;
	 
	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId) throws ApplicationException;

	public void processFile(List<Long> interfaceIds) throws ApplicationException;

	public InterfaceFileSummary getInterfaceFileSummary(Long fileId) throws ApplicationException;
	
	public InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException;
	
	public InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException;
	
	public InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException;
	
	public List<FilePropertiesBean> readFilePropertyBeans(Long interfaceId) throws ApplicationException;
	
	public InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException;
	
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(Long fileId) throws ApplicationException;
	
	public List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(Long interfaceId, Long statusId, Long partnerId) throws ApplicationException;
	
	public void pullDataToFile(List<Long> interfaceIds) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds) throws ApplicationException;
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(Long interfaceFileId, List<Long> statusIds,Long limit,Long interfaceId, List<Long> fileIds,Short retryCount) throws ApplicationException;
	public Long getInterfaceSummariesCountByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds) throws ApplicationException;
	
	public void prepareRejectionFile(Long interfaceIds, List<Long> partnerIds) throws ApplicationException;
	
	public void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId, String contolFileFormat, String contolFilePath) throws ApplicationException;
	
	
	public ResponseBean sendFile(Long fileId, String fileType) throws ApplicationException;
	
	public InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification) throws ApplicationException;

	public InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification) throws ApplicationException;
	
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(Long interfaceId, Long statusId) throws ApplicationException;
	
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(Long interfaceId,String scenario) throws ApplicationException;
	
	public String getModules() throws ApplicationException;
	
	public String getInterfaceFilesByModuleAndOrgId(Long moduleId,Long orgId,Long orgType) throws ApplicationException;
	
	public void verifyInventoryOrder(Long interfaceId) throws ApplicationException;
	
	public String getInterfaceFileSummaryDetailsByFileId(Long fileId) throws ApplicationException;
	
	public Object getJsonValue(String path, String jsonString) throws Exception;
	
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(String columnName,String columnValue) throws ApplicationException;
	
	public void  deleteBounceMailByIdandEmail(String BounceId,String BounceEmail)  throws ApplicationException;
	
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(Long fileId,String fileType) throws ApplicationException;
	
	public InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(Long detailsId) throws ApplicationException;
	
	public ResponseBean publishToInterface(Long moduleId, String jsonRequestData, String refId) throws ApplicationException;
	
	public String syncBalanceInterface(Long moduleId, String jsonRequestData) throws ApplicationException;
	
	public List<Object> getInterfaceFileSummaryDetailsByIdAndName(Long interfaceId, String columnName,String columnValue) throws ApplicationException;
	
	public void processOrderResponseConsumer(Long interfaceId) throws ApplicationException;
	
	public void processCallback(Long transactionId) throws ApplicationException;
	
	public InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException;
	
	public DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException;
	
	public ResponseBean notifyInterfaceOnDump(Long interfaceId, Date createdDate) throws ApplicationException;
	
	public DailyDumpSummary getDailyDumpSummaryByDate(Long interfaceId, Date createdDate) throws ApplicationException;
	
	public List<DailyDumpSummary> getDailyDumpSummaryByStatus(Long interfaceId, Long status) throws ApplicationException;
	
	public DailyDumpSummary updateDailyDumpSummaryStatus(Long dumpId, Long status) throws ApplicationException;
	
	public ResponseBean publishInternalRequest(Long moduleId, String jsonRequestData, String refId, Long refData3) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummariesByRefData3(Long refData3, Long interfaceId) throws ApplicationException;
	
	public List<ResponseBean> getSummaryResponseDataByRefData3(Long refData3) throws ApplicationException;
	
	public List<Long> getSummaryTransIdsByRefData3(Long refData3, Long interfaceId) throws ApplicationException;
	
	public List<KycSyncInfo> getKycSyncAttributesByInterfaceId(Long interfaceId) throws ApplicationException;
	
	public String searchSO(String soId) throws ApplicationException;
	
	public void removeInterfaceFileSummaryDetails(Long fileDetailsId) throws ApplicationException;
	
	public void reProcessPushDataFiles(Long interfaceId, Date requiredDate) throws ApplicationException;
	
	public void rollbackKPIFeedInterfaces(Long interfaceId, Long fileId) throws ApplicationException;
	
	public void prepareRejectionFileById(Long interfaceId, Long fileId) throws ApplicationException;
	
	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(Long interfaceFileId, String refData5, Long status) throws ApplicationException;
	
	public String invokePrimarySalesInterface(Long moduleId, String jsonRequestData) throws ApplicationException;
	
	public String tnmServicesInterface(Long moduleId, String jsonRequestData) throws ApplicationException;
	
	public CleanUpSummary getCleanUpSummary(Long cleanupId) throws ApplicationException;
	
	public CleanUpSummary processCleanupActivity(Long interfaceId, String userId, Long cleanupCode) throws ApplicationException;

	public void createInterfaceSummariesByModule(InterfaceSummary interfaceSummary, Long moduleId, Long transactionType) throws ApplicationException;
	
}