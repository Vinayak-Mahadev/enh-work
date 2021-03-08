package com.enhancesys.integration.snoc.services.layers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enhancesys.integration.snoc.beans.CreateOrUpdateSOBean;
import com.enhancesys.integration.snoc.beans.FileBean;
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
import com.enhancesys.integration.snoc.props.PropertiesLoader;
import com.enhancesys.integration.snoc.services.interfaces.IntegrationManagement;
import com.enhancesys.integration.snoc.services.repositories.SnocRepository;
import com.enhancesys.integration.snoc.services.util.IntegrationConstants;
import com.enhancesys.integration.snoc.services.util.IntegrationUtilManagement;

/**
 * <b>Purpose:</b>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations Private Limited<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       Mar 2, 2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */

@Service
public class SnocServices
{

	private static Logger LOGGER = Logger.getLogger(SnocServices.class);

	@Autowired
	private final SnocRepository snocEntityManagarRepository;

	@Autowired
	private final SnocRepository snocJDBCRepository;

	@Autowired
	private IntegrationUtilManagement integrationUtilManagementLocal;

	public SnocServices(final SnocRepository snocJDBCRepository, final SnocRepository snocEntityManagarRepository)
	{
		this.snocEntityManagarRepository = snocEntityManagarRepository;
		this.snocJDBCRepository = snocJDBCRepository;
		LOGGER.info("SnocServices activated :: snocEntityManagarRepository :: snocJDBCRepository :: " + snocEntityManagarRepository + " :: " + snocJDBCRepository);
	}


	public Module getModuleByName(String name) throws ApplicationException 
	{
		return snocEntityManagarRepository.getModuleByName(name);
	}


	public Module getModule(Long moduleId) throws ApplicationException 
	{
		return snocEntityManagarRepository.getModule(moduleId);
	}


	public Interfaces getInterfaceById(Long interfaceId) throws ApplicationException 
	{
		return snocEntityManagarRepository.getInterfaceById(interfaceId);
	}


	public List<Interfaces> getInterfacesByTransactionType(Long transactionType) throws ApplicationException 
	{
		return snocEntityManagarRepository.getInterfacesByTransactionType(transactionType);
	}


	public Set<InterfaceAttribute> getInterfaceAttributes(Long interfaceId) throws ApplicationException 
	{
		return snocJDBCRepository.getInterfaceAttributes(interfaceId);
	}


	public InterfaceSummary getInterfaceSummary(Long transactionId) throws ApplicationException 
	{
		return null;
	}


	public List<InterfaceSummary> getInterfaceSummariesByStatus(Long status) throws ApplicationException 
	{
		return null;
	}


	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException 
	{
		return null;
	}


	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException 
	{
		return null;
	}


	public InterfaceSummary updateInterfaceSummaryStatus(Long transactionId, Long status) throws ApplicationException 
	{
		return null;
	}


	public InterfaceFailure getInterfaceFailure(Long transactionFailureId) throws ApplicationException 
	{
		return null;
	}


	public List<InterfaceFailure> getInterfaceFailureByTransactionId(Long transactionId) throws ApplicationException 
	{
		return null;
	}


	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException 
	{
		return null;
	}


	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners) throws ApplicationException 
	{


	}


	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException
	{

	}


	public void processResponseConsumers(Long interfaceId) throws ApplicationException
	{


	}


	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException 
	{


	}


	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException
	{


	}


	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode) throws ApplicationException 
	{


	}


	public ReprocessFileBean getFilesForReprocess(Long interfaceId) throws ApplicationException 
	{

		return null;
	}


	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException 
	{


	}


	public String getTransactionId(Long interfaceId, String responseData) throws ApplicationException 
	{
		return null;
	}


	public List<InterfaceSummary> getInterfaceSummaryByRefData1(String refData1, Long refData2, Long interfaceId, Long status, Long refData5) throws ApplicationException 
	{
		return null;
	}


	public InterfaceSummary getInterfaceSummaryByRefData5(String refData5, Long status) throws ApplicationException
	{

		return null;
	}


	public InterfaceSummary getInterfaceSummaryByRefData4(Long refData4) throws ApplicationException 
	{

		return null;
	}


	public String getInterfaceAttributeValue(Long interfaceId, String parameterName) throws ApplicationException 
	{

		return null;
	}


	public void updateInterfaceSummaryByResponse(String responseString, Long transactionId, Long interfaceId, Long status, Long retryCount) throws ApplicationException 
	{


	}


	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId) throws ApplicationException 
	{


	}


	public void processFile(List<Long> interfaceIds) throws ApplicationException 
	{
		Long startTime = System.currentTimeMillis();
		ResponseBean sendFileResponse = null;
		//		Boolean flag = true;
		LOGGER.debug("Entry processFile : InterfaceIds ::  " + interfaceIds + " Started at --> " + new Date());

		InterfaceFileSummary interfaceFileSummary = null;

		List<Interfaces> interfacesList = null;
		List<FilePropertiesBean> propertiesBeans = null;

		Map<String,Object> inputMap  = null;
		StringBuilder stringBuilder  = null;
		Calendar c = null;
		Calendar c1 = null;

		List<InterfaceFileSummary> fileSummaryList = null;
		Long sTime = null;
		boolean emptyFile = false;

		try
		{
			if(interfaceIds == null || interfaceIds.isEmpty())
			{
				throw new IntegrationManagement.ProcessFileException("interface ids are must be not null", null);
			}
			interfacesList = snocEntityManagarRepository.getInterfaceByListOfId(interfaceIds);


			if(interfacesList.isEmpty())
			{
				LOGGER.error("interfaces entites are not available for interfaceIds: " + interfaceIds);
				throw new IntegrationManagement.ProcessFileException("interfaces entites are not available for interfaceIds: " + interfaceIds,null);
			}
			else
			{
				if(interfaceIds.size() == 1)
					return;
			}
			c = Calendar.getInstance();
			c1 = Calendar.getInstance();
			for(Interfaces interfaces : interfacesList)
			{
				sTime = System.currentTimeMillis();
				LOGGER.debug("Entry processFile.. " + interfaces.getInterfaceId() + " Started at --> "+ new Date());
				try
				{
					if(IntegrationConstants.SEND_TRANS_TYPE.equals(interfaces.getTransactionType()))
					{
						fileSummaryList = snocEntityManagarRepository.getInterfaceFileSummaryByInterfaceIdAndStatus(interfaces.getInterfaceId(), IntegrationConstants.FILE_INQUEUE_STATUS, null);
						if((fileSummaryList == null || fileSummaryList.isEmpty()) && IntegrationConstants.PREPARE_EMPTY_FILES_INTERFACE_LIST.contains(interfaces.getInterfaceId().toString()))
						{
							fileSummaryList = new ArrayList<InterfaceFileSummary>();
							interfaceFileSummary = prepareEmptyFiles(interfaces.getInterfaceId());
							//TLogger.debug("fileSummaryList-->"+fileSummaryList.size());
							fileSummaryList.add(interfaceFileSummary);
							emptyFile = true;
						}
						for(InterfaceFileSummary fileSummary : fileSummaryList)
						{
							try
							{
								if(IntegrationConstants.SEND_ONLY_OLD_FILES_INTERFACE_LIST.contains(interfaces.getInterfaceId().toString()) && fileSummary.getProcessedOn() != null && !emptyFile)
								{
									c1.setTime(fileSummary.getProcessedOn());
									if(c.get(Calendar.YEAR) == c1.get(Calendar.YEAR) && c.get(Calendar.MONTH) == c1.get(Calendar.MONTH) && c.get(Calendar.DATE) == c1.get(Calendar.DATE))
									{
										continue;
									}
								}
								stringBuilder = new StringBuilder();
								for(InterfaceFileSummaryDetails interfaceFileSummaryDetails2 : fileSummary.getFileSummaryDetails())
								{
									stringBuilder.append(interfaceFileSummaryDetails2.getFileName()+","+interfaceFileSummaryDetails2.getControlFileName());
									stringBuilder.append(",");
								}
								sendFileResponse = sendFile(fileSummary.getFileId(), IntegrationConstants.ACTUAL_FILE_TYPE_CHAR);
								if(!sendFileResponse.getFlag())
								{
									fileSummary.setStatus(snocEntityManagarRepository.getStatusByID(IntegrationConstants.FILE_REJECTED_STATUS));
									if(sendFileResponse.getErrorMessage() != null && !sendFileResponse.getErrorMessage().isEmpty())
										fileSummary.setMessage(sendFileResponse.getErrorMessage());
									else
										fileSummary.setMessage("File Contains Invalid Data, Rejected From Interface");
									snocEntityManagarRepository.updateInterfaceFileSummary(fileSummary);
									if(stringBuilder!=null)
										integrationUtilManagementLocal.sendNotificationForFileValidation(stringBuilder.toString());
									continue;
								}
								fileSummary.setStatus(snocEntityManagarRepository.getStatusByID(IntegrationConstants.FILE_UPLOADED_STATUS));
								fileSummary.setUploadedOn(new Date());
								fileSummary.setMessage(PropertiesLoader.getErrorDescriptionFor(IntegrationConstants.FILES_UPLOADED));
								snocEntityManagarRepository.updateInterfaceFileSummary(fileSummary);

								if(stringBuilder != null)
								{
									inputMap = new HashMap<String,Object>();
									inputMap.put("fileNames", stringBuilder.substring(0,stringBuilder.length()-1));
									integrationUtilManagementLocal.prepareNotificationJsonData(interfaces.getInterfaceId(), IntegrationConstants.FILE_TRANSFERRED_TO_SFTP_SCENARIO+"_"+fileSummary.getUploadedBy(), inputMap);
								}
							}
							catch(Exception e)
							{
								LOGGER.error("Exception on process file send filesummary loop :", e);
								continue;
							}
						}
					}
					else 
					{
						propertiesBeans = readFilePropertyBeans(interfaces.getInterfaceId());

						for(FilePropertiesBean bean : propertiesBeans)
						{
							try
							{
								interfaceFileSummary = null;
								if(bean.getIsSFTP().equalsIgnoreCase("true"))
								{
									receiveFile(bean,interfaces);
								}
								else
								{
									//ftp code goes here..
								}
							}
							catch(Exception e)
							{
								LOGGER.error("Exception on process file receive - file bean loop :", e);
								continue;
							}
						}
					}
				}
				catch(Exception e)
				{
					LOGGER.error("Exception on process file interface loop :", e);
					continue;
				}

				LOGGER.debug("Completed processFile.. " + interfaces.getInterfaceId() + " Service at --> "+ new Date()+" --- and took "+(System.currentTimeMillis()-sTime)+" milliseconds");
			}
		}
		catch(NullPointerException nullpointerException)
		{
			LOGGER.error("[nullpointerException] " + nullpointerException.getMessage(), nullpointerException);
			throw new IntegrationManagement.ProcessFileException(nullpointerException.getMessage(), nullpointerException);
		}
		catch (ApplicationException applicationException) 
		{
			LOGGER.error("[applicationException] " + applicationException.getMessage(), applicationException);
			throw applicationException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[exception] " + exception.getMessage(), exception);
			throw new IntegrationManagement.ProcessFileException(exception.getMessage(), exception);
		} 
		finally
		{
			interfaceFileSummary = null;
			interfacesList = null;
			propertiesBeans = null;
			inputMap = null; 
			stringBuilder = null; 
			sendFileResponse = null;
			sTime = null;
			c = null;
			c1 = null;
			fileSummaryList = null;
			LOGGER.debug("Exit processFile : InterfaceIds ::  " + interfaceIds + " and took " + (System.currentTimeMillis() - startTime) + " milliseconds");
			startTime = null;
		}
	}


	private void receiveFile(FilePropertiesBean bean, Interfaces interfaces) {
		// TODO Auto-generated method stub

	}


	@SuppressWarnings("unchecked")
	private InterfaceFileSummary prepareEmptyFiles(Long interfaceId) throws ApplicationException 
	{
		Long startTime = System.currentTimeMillis();
		LOGGER.debug("Entry writeToLocalFile.. Started at --> "+ new Date());
		InterfaceFileSummary interfaceFileSummary = null;
		InterfaceFileSummaryDetails fileSummaryDetails = null;
		JSONObject sendServerjsonObject = null;
		JSONObject localServerjsonObject = null;
		SimpleDateFormat dateFormat = null;
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		String fileCount = "00";
		String fileDate = "";
		Integer count = 0;
		List<FilePropertiesBean> filePropertiesBeans = null;
		Calendar c = null;
		try
		{
			filePropertiesBeans = readFilePropertyBeans(interfaceId);
			c = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			LOGGER.debug("filePropertiesBeans size-->"+filePropertiesBeans);
			for(FilePropertiesBean filePropertiesBean: filePropertiesBeans)
			{
				if(filePropertiesBean.getRemoteFileNameFormat() != null && !filePropertiesBean.getRemoteFileNameFormat().isEmpty())
				{
					dateFormat = new SimpleDateFormat(filePropertiesBean.getRemoteFileNameFormat());
					fileDate = fileDate +  "_" + dateFormat.format(c.getTime());
				}
				count++;

				fileCount += count.toString();
				if(fileCount.length() > 3)
				{
					fileCount = fileCount.substring(fileCount.length() - 3);
				}
				interfaceFileSummary = new InterfaceFileSummary();
				interfaceFileSummary.setFileName(filePropertiesBean.getFiles().iterator().next().getFileName());
				interfaceFileSummary.setInterfaces(snocEntityManagarRepository.getInterfaceById(interfaceId));
				interfaceFileSummary.setTotalCount(0L);
				interfaceFileSummary.setSuccessCount(0L);
				interfaceFileSummary.setErrorCount(0L);
				interfaceFileSummary.setStatus(snocEntityManagarRepository.getStatusByID(IntegrationConstants.FILE_INQUEUE_STATUS));
				interfaceFileSummary.setMessage(PropertiesLoader.getErrorDescriptionFor(IntegrationConstants.FILES_CREATED));
				interfaceFileSummary.setProcessedOn(new Date());
				interfaceFileSummary.setUploadedBy(Long.valueOf(filePropertiesBean.getClientId()));
				sendServerjsonObject = new JSONObject();
				sendServerjsonObject.put("Host", filePropertiesBean.getRemoteHost());
				sendServerjsonObject.put("Port", filePropertiesBean.getRemotePort());
				sendServerjsonObject.put("User", filePropertiesBean.getSecurityPrincipal());
				sendServerjsonObject.put("Pass", filePropertiesBean.getSecurityCredentials());
				sendServerjsonObject.put("DestDir", filePropertiesBean.getRemoteDir());
				sendServerjsonObject.put("DestCtlDir", filePropertiesBean.getRemoteControlDir());
				interfaceFileSummary.setSendServerDetails(sendServerjsonObject.toJSONString());
				localServerjsonObject = new JSONObject();
				localServerjsonObject.put("Dir", filePropertiesBean.getLocalDir());
				localServerjsonObject.put("BkpDir", filePropertiesBean.getLocalBackupDir());
				localServerjsonObject.put("CtlDir", filePropertiesBean.getLocalControlDir());
				localServerjsonObject.put("CtlBkpDir", filePropertiesBean.getLocalControlBackupDir());
				interfaceFileSummary.setLocalServerDetails(localServerjsonObject.toJSONString());
				interfaceFileSummary = createInterfaceFileSummary(interfaceFileSummary);
				LOGGER.debug("File bean size --> "+filePropertiesBean.getFiles().size());
				for(FileBean fileBean : filePropertiesBean.getFiles())
				{
					LOGGER.debug("Entry filebean-->"+fileBean.getFileName());
					fileSummaryDetails = new InterfaceFileSummaryDetails();
					fileSummaryDetails.setControlFileName("");
					fileSummaryDetails.setFileName(fileBean.getFileName()+fileDate+"_"+fileCount+"."+filePropertiesBean.getRemoteFileFormat());
					fileSummaryDetails.setFileType(IntegrationConstants.ACTUAL_FILE_TYPE_CHAR);
					fileSummaryDetails.setInterfaceFileSummary(interfaceFileSummary);
					fileSummaryDetails.setTotalCount(0L);
					fileSummaryDetails.setSuccessCount(0L);
					fileSummaryDetails.setErrorCount(0L);
					fileSummaryDetails = createInterfaceFileSummaryDetails(fileSummaryDetails);
					if(IntegrationConstants.INVENTORY_SALES_FILE_NAME.equals(fileBean.getFileName()))
					{
						fileOutputStream = new FileOutputStream(filePropertiesBean.getLocalDir()+"/"+fileSummaryDetails.getFileName());
						outputStreamWriter = new OutputStreamWriter(fileOutputStream,IntegrationConstants.FILE_ENCODE_FORMAT);
						outputStreamWriter.write(getInterfaceAttributeValue(interfaceId, IntegrationConstants.FIRST_FILE_HEADER_ATTR));
						outputStreamWriter.write("\n");
						//						fileOutputStream.close();
						outputStreamWriter.close();
						createControlFile(filePropertiesBean.getLocalDir()+"/"+fileSummaryDetails.getFileName(), 0L, fileSummaryDetails.getFileName().substring(0, (fileSummaryDetails.getFileName().lastIndexOf("."))), filePropertiesBean.getRemoteFileFormat(), fileSummaryDetails.getFileDetailsId(), filePropertiesBean.getControlFileFormat(), filePropertiesBean.getLocalControlDir());
					}
					else
					{
						fileOutputStream = new FileOutputStream(filePropertiesBean.getLocalDir()+"/"+fileSummaryDetails.getFileName());
						outputStreamWriter = new OutputStreamWriter(fileOutputStream,IntegrationConstants.FILE_ENCODE_FORMAT);
						outputStreamWriter.write(getInterfaceAttributeValue(interfaceId, IntegrationConstants.SECOND_FILE_HEADER_ATTR));
						outputStreamWriter.write("\n");
						//						fileOutputStream.close();
						outputStreamWriter.close();
						createControlFile(filePropertiesBean.getLocalDir()+"/"+fileSummaryDetails.getFileName(), 0L, fileSummaryDetails.getFileName().substring(0, (fileSummaryDetails.getFileName().lastIndexOf("."))), filePropertiesBean.getRemoteFileFormat(), fileSummaryDetails.getFileDetailsId(), filePropertiesBean.getControlFileFormat(), filePropertiesBean.getLocalControlDir());
					}
				}
			}
			return interfaceFileSummary;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("NullPointerException :: "+nullPointerException.getMessage(),nullPointerException);
			throw new IntegrationManagement.PrepareEmptyFileException(nullPointerException.getMessage(), nullPointerException);
		} 
		catch (IOException ioException) 
		{
			LOGGER.error("IoException :: "+ioException.getMessage(),ioException);
			throw new IntegrationManagement.PrepareEmptyFileException(ioException.getMessage(),ioException);
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception :: "+exception.getMessage(),exception);
			throw new IntegrationManagement.PrepareEmptyFileException(exception.getMessage(),exception);
		}
		finally
		{
			interfaceFileSummary = null;
			fileSummaryDetails = null;
			sendServerjsonObject = null;
			localServerjsonObject = null;
			outputStreamWriter = null;
			dateFormat = null;
			fileCount = null;
			fileDate = null;
			count = null;
			try 
			{

				if(fileOutputStream != null)
				{
					fileOutputStream.close();
				}
			} 
			catch (IOException ioException) 
			{
				LOGGER.error("IoException :: " + ioException.getMessage(), ioException);
				throw new IntegrationManagement.PrepareEmptyFileException(ioException.getMessage(), ioException);
			}

			LOGGER.debug("Exit writeToLocalFile service at -->"+new Date()+" --- and took "+(System.currentTimeMillis()-startTime)+" milliseconds");
		}
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


	public ResponseBean sendFile(Long fileId, String fileType) throws ApplicationException {

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
