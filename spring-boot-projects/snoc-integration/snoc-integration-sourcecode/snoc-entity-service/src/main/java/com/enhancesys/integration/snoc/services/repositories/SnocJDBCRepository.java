package com.enhancesys.integration.snoc.services.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
import com.enhancesys.integration.snoc.entities.Status;
import com.enhancesys.integration.snoc.exception.ApplicationException;

@Component
@Qualifier("snocJDBCRepository")
public class SnocJDBCRepository implements SnocRepository
{

	public SnocJDBCRepository()
	{
		System.out.println("SnocJDBCRepository activated");
	}

	@Override
	public Module getModuleByName(String name) throws ApplicationException {

		return null;
	}

	@Override
	public Module getModule(Long moduleId) throws ApplicationException {

		return null;
	}

	@Override
	public Interfaces getInterfaceById(Long interfaceId) throws ApplicationException {

		return null;
	}

	@Override
	public List<Interfaces> getInterfaceByListOfId(List<Long> interfaceIds) throws ApplicationException
	{
		return null;
	}
	
	@Override
	public List<Interfaces> getInterfacesByTransactionType(Long transactionType) throws ApplicationException {

		return null;
	}

	@Override
	public Set<InterfaceAttribute> getInterfaceAttributes(Long interfaceId) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceSummary getInterfaceSummary(Long transactionId) throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByStatus(Long status) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceSummary updateInterfaceSummaryStatus(Long transactionId, Long status) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFailure getInterfaceFailure(Long transactionFailureId) throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceFailure> getInterfaceFailureByTransactionId(Long transactionId) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException {

		return null;
	}

	@Override
	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners)
			throws ApplicationException {


	}

	@Override
	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException {


	}

	@Override
	public void processResponseConsumers(Long interfaceId) throws ApplicationException {


	}

	@Override
	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException {


	}

	@Override
	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException {


	}

	@Override
	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode)
			throws ApplicationException {


	}

	@Override
	public ReprocessFileBean getFilesForReprocess(Long interfaceId) throws ApplicationException {

		return null;
	}

	@Override
	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException {


	}

	@Override
	public String getTransactionId(Long interfaceId, String responseData) throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummaryByRefData1(String refData1, Long refData2, Long interfaceId,
			Long status, Long refData5) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceSummary getInterfaceSummaryByRefData5(String refData5, Long status) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceSummary getInterfaceSummaryByRefData4(Long refData4) throws ApplicationException {

		return null;
	}

	@Override
	public String getInterfaceAttributeValue(Long interfaceId, String parameterName) throws ApplicationException {

		return null;
	}

	@Override
	public void updateInterfaceSummaryByResponse(String responseString, Long transactionId, Long interfaceId,
			Long status, Long retryCount) throws ApplicationException {


	}

	@Override
	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId)
			throws ApplicationException {


	}

	@Override
	public void processFile(List<Long> interfaceIds) throws ApplicationException {


	}

	@Override
	public InterfaceFileSummary getInterfaceFileSummary(Long fileId) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary)
			throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary)
			throws ApplicationException {

		return null;
	}

	@Override
	public List<FilePropertiesBean> readFilePropertyBeans(Long interfaceId) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(Long fileId) throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(Long interfaceId, Long statusId,
			Long partnerId) throws ApplicationException {

		return null;
	}

	@Override
	public void pullDataToFile(List<Long> interfaceIds) throws ApplicationException {


	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(Long interfaceFileId,
			List<Long> statusIds, Long limit, Long interfaceId, List<Long> fileIds, Short retryCount)
					throws ApplicationException {

		return null;
	}

	@Override
	public Long getInterfaceSummariesCountByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException {

		return null;
	}

	@Override
	public void prepareRejectionFile(Long interfaceIds, List<Long> partnerIds) throws ApplicationException {


	}

	@Override
	public void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId,
			String contolFileFormat, String contolFilePath) throws ApplicationException {


	}

	@Override
	public ResponseBean sendFile(Long fileId, String fileType) throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(Long interfaceId, Long statusId)
			throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(Long interfaceId,
			String scenario) throws ApplicationException {

		return null;
	}

	@Override
	public String getModules() throws ApplicationException {

		return null;
	}

	@Override
	public String getInterfaceFilesByModuleAndOrgId(Long moduleId, Long orgId, Long orgType)
			throws ApplicationException {

		return null;
	}

	@Override
	public void verifyInventoryOrder(Long interfaceId) throws ApplicationException {


	}

	@Override
	public String getInterfaceFileSummaryDetailsByFileId(Long fileId) throws ApplicationException {

		return null;
	}

	@Override
	public Object getJsonValue(String path, String jsonString) throws Exception {

		return null;
	}

	@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(String columnName, String columnValue)
			throws ApplicationException {

		return null;
	}

	@Override
	public void deleteBounceMailByIdandEmail(String BounceId, String BounceEmail) throws ApplicationException {


	}

	@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(Long fileId, String fileType)
			throws ApplicationException {

		return null;
	}

	@Override
	public InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(Long detailsId) throws ApplicationException {

		return null;
	}

	@Override
	public ResponseBean publishToInterface(Long moduleId, String jsonRequestData, String refId)
			throws ApplicationException {

		return null;
	}

	@Override
	public String syncBalanceInterface(Long moduleId, String jsonRequestData) throws ApplicationException {

		return null;
	}

	@Override
	public List<Object> getInterfaceFileSummaryDetailsByIdAndName(Long interfaceId, String columnName,
			String columnValue) throws ApplicationException {

		return null;
	}

	@Override
	public void processOrderResponseConsumer(Long interfaceId) throws ApplicationException {


	}

	@Override
	public void processCallback(Long transactionId) throws ApplicationException {


	}

	@Override
	public InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException {

		return null;
	}

	@Override
	public DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException {

		return null;
	}

	@Override
	public ResponseBean notifyInterfaceOnDump(Long interfaceId, Date createdDate) throws ApplicationException {

		return null;
	}

	@Override
	public DailyDumpSummary getDailyDumpSummaryByDate(Long interfaceId, Date createdDate) throws ApplicationException {

		return null;
	}

	@Override
	public List<DailyDumpSummary> getDailyDumpSummaryByStatus(Long interfaceId, Long status)
			throws ApplicationException {

		return null;
	}

	@Override
	public DailyDumpSummary updateDailyDumpSummaryStatus(Long dumpId, Long status) throws ApplicationException {

		return null;
	}

	@Override
	public ResponseBean publishInternalRequest(Long moduleId, String jsonRequestData, String refId, Long refData3)
			throws ApplicationException {

		return null;
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummariesByRefData3(Long refData3, Long interfaceId)
			throws ApplicationException {

		return null;
	}

	@Override
	public List<ResponseBean> getSummaryResponseDataByRefData3(Long refData3) throws ApplicationException {

		return null;
	}

	@Override
	public List<Long> getSummaryTransIdsByRefData3(Long refData3, Long interfaceId) throws ApplicationException {

		return null;
	}

	@Override
	public List<KycSyncInfo> getKycSyncAttributesByInterfaceId(Long interfaceId) throws ApplicationException {

		return null;
	}

	@Override
	public String searchSO(String soId) throws ApplicationException {

		return null;
	}

	@Override
	public void removeInterfaceFileSummaryDetails(Long fileDetailsId) throws ApplicationException {


	}

	@Override
	public void reProcessPushDataFiles(Long interfaceId, Date requiredDate) throws ApplicationException {


	}

	@Override
	public void rollbackKPIFeedInterfaces(Long interfaceId, Long fileId) throws ApplicationException {


	}

	@Override
	public void prepareRejectionFileById(Long interfaceId, Long fileId) throws ApplicationException {


	}

	@Override
	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(Long interfaceFileId, String refData5,
			Long status) throws ApplicationException {

		return null;
	}

	@Override
	public String invokePrimarySalesInterface(Long moduleId, String jsonRequestData) throws ApplicationException {

		return null;
	}

	@Override
	public String tnmServicesInterface(Long moduleId, String jsonRequestData) throws ApplicationException {

		return null;
	}

	@Override
	public CleanUpSummary getCleanUpSummary(Long cleanupId) throws ApplicationException {

		return null;
	}

	@Override
	public CleanUpSummary processCleanupActivity(Long interfaceId, String userId, Long cleanupCode)
			throws ApplicationException {

		return null;
	}

	@Override
	public void createInterfaceSummariesByModule(InterfaceSummary interfaceSummary, Long moduleId, Long transactionType)
			throws ApplicationException {


	}

	@Override
	public UpdateSOResponseBean updateSOStatus(CreateOrUpdateSOBean updateSOStatus) throws ApplicationException {

		return null;
	}

	@Override
	public Status getStatusByID(Long status) throws ApplicationException {
		return null;
	}
}
