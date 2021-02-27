package com.enhancesys.integration.snoc.services.layers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enhancesys.integration.snoc.beans.CreateOrUpdateSOBean;
import com.enhancesys.integration.snoc.beans.FilePropertiesBean;
import com.enhancesys.integration.snoc.beans.ReprocessFileBean;
import com.enhancesys.integration.snoc.beans.ResponseBean;
import com.enhancesys.integration.snoc.beans.UpdateSOResponseBean;
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
import com.enhancesys.integration.snoc.services.repositories.SnocRepository;

@Service
public class SnocServices
{

	@Autowired
	private final SnocRepository snocEntityManagarRepository;
	
	@Autowired
	private final SnocRepository snocJDBCRepository;
	
	public SnocServices(final SnocRepository snocJDBCRepository, final SnocRepository snocEntityManagarRepository)
	{
		this.snocEntityManagarRepository = snocEntityManagarRepository;
		this.snocJDBCRepository = snocJDBCRepository;
		System.out.println("SnocServices activated");
		System.out.println("snocEntityManagarRepository ::  " + snocEntityManagarRepository);
		System.out.println("snocJDBCRepository ::  " + snocJDBCRepository);
	}
	

	public Module getModuleByName(String name) throws ApplicationException {

		return null;
	}


	public Module getModule(Long moduleId) throws ApplicationException {

		return snocEntityManagarRepository.getModule(moduleId);
	}


	public Interfaces getInterfaceById(Long interfaceId) throws ApplicationException {

		return snocEntityManagarRepository.getInterfaceById(interfaceId);
	}


	public List<Interfaces> getInterfacesByTransactionType(Long transactionType) throws ApplicationException {

		return snocEntityManagarRepository.getInterfacesByTransactionType(transactionType);
	}


	public Set<InterfaceAttribute> getInterfaceAttributes(Long interfaceId) throws ApplicationException {

		return snocJDBCRepository.getInterfaceAttributes(interfaceId);
	}


	public InterfaceSummary getInterfaceSummary(Long transactionId) throws ApplicationException {

		return null;
	}


	public List<InterfaceSummary> getInterfaceSummariesByStatus(Long status) throws ApplicationException {

		return null;
	}


	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException {

		return null;
	}


	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException {

		return null;
	}


	public InterfaceSummary updateInterfaceSummaryStatus(Long transactionId, Long status) throws ApplicationException {

		return null;
	}


	public InterfaceFailure getInterfaceFailure(Long transactionFailureId) throws ApplicationException {

		return null;
	}


	public List<InterfaceFailure> getInterfaceFailureByTransactionId(Long transactionId) throws ApplicationException {

		return null;
	}


	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException {

		return null;
	}


	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners)
			throws ApplicationException {

		
	}


	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException {

		
	}


	public void processResponseConsumers(Long interfaceId) throws ApplicationException {

		
	}


	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException {

		
	}


	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException {

		
	}


	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode)
			throws ApplicationException {

		
	}


	public ReprocessFileBean getFilesForReprocess(Long interfaceId) throws ApplicationException {

		return null;
	}


	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException {

		
	}


	public String getTransactionId(Long interfaceId, String responseData) throws ApplicationException {

		return null;
	}


	public List<InterfaceSummary> getInterfaceSummaryByRefData1(String refData1, Long refData2, Long interfaceId,
			Long status, Long refData5) throws ApplicationException {

		return null;
	}


	public InterfaceSummary getInterfaceSummaryByRefData5(String refData5, Long status) throws ApplicationException {

		return null;
	}


	public InterfaceSummary getInterfaceSummaryByRefData4(Long refData4) throws ApplicationException {

		return null;
	}


	public String getInterfaceAttributeValue(Long interfaceId, String parameterName) throws ApplicationException {

		return null;
	}


	public void updateInterfaceSummaryByResponse(String responseString, Long transactionId, Long interfaceId,
			Long status, Long retryCount) throws ApplicationException {

		
	}


	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId)
			throws ApplicationException {

		
	}


	public void processFile(List<Long> interfaceIds) throws ApplicationException {

		
	}


	public InterfaceFileSummary getInterfaceFileSummary(Long fileId) throws ApplicationException {

		return null;
	}


	public InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary)
			throws ApplicationException {

		return null;
	}


	public InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException {

		return null;
	}


	public InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary)
			throws ApplicationException {

		return null;
	}


	public List<FilePropertiesBean> readFilePropertyBeans(Long interfaceId) throws ApplicationException {

		return null;
	}


	public InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException {

		return null;
	}


	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(Long fileId) throws ApplicationException {

		return null;
	}


	public List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(Long interfaceId, Long statusId,
			Long partnerId) throws ApplicationException {

		return null;
	}


	public void pullDataToFile(List<Long> interfaceIds) throws ApplicationException {

		
	}


	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException {

		return null;
	}


	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(Long interfaceFileId,
			List<Long> statusIds, Long limit, Long interfaceId, List<Long> fileIds, Short retryCount)
			throws ApplicationException {

		return null;
	}


	public Long getInterfaceSummariesCountByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException {

		return null;
	}


	public void prepareRejectionFile(Long interfaceIds, List<Long> partnerIds) throws ApplicationException {

		
	}


	public void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId,
			String contolFileFormat, String contolFilePath) throws ApplicationException {

		
	}


	public Boolean sendFile(Long fileId, String fileType) throws ApplicationException {

		return null;
	}


	public InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException {

		return null;
	}


	public InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException {

		return null;
	}


	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(Long interfaceId, Long statusId)
			throws ApplicationException {

		return null;
	}


	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(Long interfaceId,
			String scenario) throws ApplicationException {

		return null;
	}


	public String getModules() throws ApplicationException {

		return null;
	}


	public String getInterfaceFilesByModuleAndOrgId(Long moduleId, Long orgId, Long orgType)
			throws ApplicationException {

		return null;
	}


	public void verifyInventoryOrder(Long interfaceId) throws ApplicationException {

		
	}


	public String getInterfaceFileSummaryDetailsByFileId(Long fileId) throws ApplicationException {

		return null;
	}


	public Object getJsonValue(String path, String jsonString) throws Exception {

		return null;
	}


	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(String columnName, String columnValue)
			throws ApplicationException {

		return null;
	}


	public void deleteBounceMailByIdandEmail(String BounceId, String BounceEmail) throws ApplicationException {

		
	}


	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(Long fileId, String fileType)
			throws ApplicationException {

		return null;
	}


	public InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(Long detailsId) throws ApplicationException {

		return null;
	}


	public ResponseBean publishToInterface(Long moduleId, String jsonRequestData, String refId)
			throws ApplicationException {

		return null;
	}


	public String syncBalanceInterface(Long moduleId, String jsonRequestData) throws ApplicationException {

		return null;
	}


	public List<Object> getInterfaceFileSummaryDetailsByIdAndName(Long interfaceId, String columnName,
			String columnValue) throws ApplicationException {

		return null;
	}


	public void processOrderResponseConsumer(Long interfaceId) throws ApplicationException {

		
	}


	public void processCallback(Long transactionId) throws ApplicationException {

		
	}


	public InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException {

		return null;
	}


	public DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException {

		return null;
	}


	public ResponseBean notifyInterfaceOnDump(Long interfaceId, Date createdDate) throws ApplicationException {

		return null;
	}


	public DailyDumpSummary getDailyDumpSummaryByDate(Long interfaceId, Date createdDate) throws ApplicationException {

		return null;
	}


	public List<DailyDumpSummary> getDailyDumpSummaryByStatus(Long interfaceId, Long status)
			throws ApplicationException {

		return null;
	}


	public DailyDumpSummary updateDailyDumpSummaryStatus(Long dumpId, Long status) throws ApplicationException {

		return null;
	}


	public ResponseBean publishInternalRequest(Long moduleId, String jsonRequestData, String refId, Long refData3)
			throws ApplicationException {

		return null;
	}


	public List<InterfaceSummary> getInterfaceSummariesByRefData3(Long refData3, Long interfaceId)
			throws ApplicationException {

		return null;
	}


	public List<ResponseBean> getSummaryResponseDataByRefData3(Long refData3) throws ApplicationException {

		return null;
	}


	public List<Long> getSummaryTransIdsByRefData3(Long refData3, Long interfaceId) throws ApplicationException {

		return null;
	}


	public List<KycSyncInfo> getKycSyncAttributesByInterfaceId(Long interfaceId) throws ApplicationException {

		return null;
	}


	public String searchSO(String soId) throws ApplicationException {

		return null;
	}


	public void removeInterfaceFileSummaryDetails(Long fileDetailsId) throws ApplicationException {

		
	}


	public void reProcessPushDataFiles(Long interfaceId, Date requiredDate) throws ApplicationException {

		
	}


	public void rollbackKPIFeedInterfaces(Long interfaceId, Long fileId) throws ApplicationException {

		
	}


	public void prepareRejectionFileById(Long interfaceId, Long fileId) throws ApplicationException {

		
	}


	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(Long interfaceFileId, String refData5,
			Long status) throws ApplicationException {

		return null;
	}


	public String invokePrimarySalesInterface(Long moduleId, String jsonRequestData) throws ApplicationException {

		return null;
	}


	public String tnmServicesInterface(Long moduleId, String jsonRequestData) throws ApplicationException {

		return null;
	}


	public CleanUpSummary getCleanUpSummary(Long cleanupId) throws ApplicationException {

		return null;
	}


	public CleanUpSummary processCleanupActivity(Long interfaceId, String userId, Long cleanupCode)
			throws ApplicationException {

		return null;
	}


	public void createInterfaceSummariesByModule(InterfaceSummary interfaceSummary, Long moduleId, Long transactionType)
			throws ApplicationException {

		
	}


	public UpdateSOResponseBean updateSOStatus(CreateOrUpdateSOBean updateSOStatus) throws ApplicationException {

		return null;
	}

}
