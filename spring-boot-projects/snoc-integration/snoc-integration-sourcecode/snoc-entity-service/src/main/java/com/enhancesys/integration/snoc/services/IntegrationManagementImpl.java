package com.enhancesys.integration.snoc.services;

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
import com.enhancesys.integration.snoc.services.interfaces.IntegrationManagementLocal;

@javax.jws.WebService(serviceName = "IntegrationManagement", portName = "snoc",
targetNamespace = "http://com/enhancesys/entities/schema/integration/",
endpointInterface = "com.enhancesys.integration.snoc.services.interfaces.IntegrationManagement")
public class IntegrationManagementImpl implements IntegrationManagementLocal 
{

	@Override
	public Module getModuleByName(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Module getModule(Long moduleId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interfaces getInterfaceById(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interfaces> getInterfacesByTransactionType(Long transactionType) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<InterfaceAttribute> getInterfaceAttributes(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceSummary getInterfaceSummary(Long transactionId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByStatus(Long status) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceSummary updateInterfaceSummaryStatus(Long transactionId, Long status) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFailure getInterfaceFailure(Long transactionFailureId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceFailure> getInterfaceFailureByTransactionId(Long transactionId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processResponseConsumers(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTransactionId(Long interfaceId, String responseData) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummaryByRefData1(String refData1, Long refData2, Long interfaceId,
			Long status, Long refData5) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceSummary getInterfaceSummaryByRefData5(String refData5, Long status) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceSummary getInterfaceSummaryByRefData4(Long refData4) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInterfaceAttributeValue(Long interfaceId, String parameterName) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInterfaceSummaryByResponse(String responseString, Long transactionId, Long interfaceId,
			Long status, Long retryCount) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processFile(List<Long> interfaceIds) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InterfaceFileSummary getInterfaceFileSummary(Long fileId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createInterfaceSummariesByModule(InterfaceSummary interfaceSummary, Long moduleId, Long transactionType)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FilePropertiesBean> readFilePropertyBeans(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(Long interfaceId, Long statusId,
			Long partnerId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(Long fileId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode)
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReprocessFileBean getFilesForReprocess(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pullDataToFile(List<Long> interfaceIds) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(Long interfaceFileId,
			List<Long> statusIds, Long limit, Long interfaceId, List<Long> fileIds, Short retryCount)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getInterfaceSummariesCountByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepareRejectionFile(Long interfaceId, List<Long> partnerIds) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId,
			String contolFileFormat, String contolFilePath) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean sendFile(Long fileId, String fileType) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(Long interfaceFileId, String refData5,
			Long status) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(Long interfaceId, Long statusId)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(Long interfaceId,
			String scenario) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModules() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInterfaceFilesByModuleAndOrgId(Long moduleId, Long orgId, Long orgType)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verifyInventoryOrder(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInterfaceFileSummaryDetailsByFileId(Long fileId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getJsonValue(String path, String jsonString) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(String columnName, String columnValue)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBounceMailByIdandEmail(String bounceId, String bounceEmail) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(Long fileId, String fileType)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(Long detailsId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBean publishToInterface(Long moduleId, String jsonRequestData, String refId)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String syncBalanceInterface(Long moduleId, String jsonRequestData) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> getInterfaceFileSummaryDetailsByIdAndName(Long interfaceId, String columnName,
			String columnValue) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processOrderResponseConsumer(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processCallback(Long transactionId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBean notifyInterfaceOnDump(Long interfaceId, Date createdDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyDumpSummary getDailyDumpSummaryByDate(Long interfaceId, Date createdDate) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyDumpSummary> getDailyDumpSummaryByStatus(Long interfaceId, Long status)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyDumpSummary updateDailyDumpSummaryStatus(Long dumpId, Long status) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBean publishInternalRequest(Long moduleId, String jsonRequestData, String refId, Long refData3)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByRefData3(Long refData3, Long interfaceId)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResponseBean> getSummaryResponseDataByRefData3(Long refData3) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getSummaryTransIdsByRefData3(Long refData3, Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KycSyncInfo> getKycSyncAttributesByInterfaceId(Long interfaceId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeInterfaceFileSummaryDetails(Long fileDetailsId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reProcessPushDataFiles(Long interfaceId, Date requiredDate) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollbackKPIFeedInterfaces(Long interfaceId, Long fileId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepareRejectionFileById(Long interfaceId, Long fileId) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String invokePrimarySalesInterface(Long moduleId, String jsonRequestData) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String tnmServicesInterface(Long moduleId, String jsonRequestData) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CleanUpSummary getCleanUpSummary(Long cleanupId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CleanUpSummary processCleanupActivity(Long interfaceId, String userId, Long cleanupCode)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchSO(String soId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
