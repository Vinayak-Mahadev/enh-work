package com.enhancesys.integration.snoc.services.rest;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.enhancesys.integration.snoc.services.interfaces.IntegrationManagement;
import com.enhancesys.integration.snoc.services.interfaces.rest.IntegrationManagementRest;
import com.enhancesys.integration.snoc.services.layers.SnocServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */


@EnableTransactionManagement
@RestController
@RequestMapping(path = "/snoc/rest/integration/services", produces = "application/json")
public class IntegrationManagementRestImpl implements IntegrationManagementRest 
{

	@Autowired
	private final SnocServices snocServices;

	protected static Logger LOGGER = Logger.getLogger(IntegrationManagementRestImpl.class);

	public IntegrationManagementRestImpl(SnocServices snocServices)
	{
		System.out.println("IntegrationManagementRestImpl activated");
		System.out.println("snocServices :: " + snocServices);
		this.snocServices = snocServices;
	}

	//@Override
	public Module getModuleByName(final String name) throws ApplicationException 
	{
		Module module = null;
		try 
		{
			
			module = snocServices.getModuleByName(name);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(module);
			System.out.println(json);
			return module;
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetModuleByNameException(e.getMessage(), e);
		}
	}

	//@Override
//	@RequestMapping({"/getModule/{moduleId}"})
//	@GetMapping("/getModule/{moduleId}")
	public Module getModule(@PathVariable("moduleId") final Long moduleId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getModule(moduleId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetModuleException(e.getMessage(), e);
		}
	}

	//@Override
	public Interfaces getInterfaceById(final Long interfaceId) throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceById(interfaceId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceByIdException(e.getMessage(), e);
		}
	}

	//@Override
	public List<Interfaces> getInterfacesByTransactionType(final Long transactionType) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfacesByTransactionType(transactionType);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfacesByTransactionTypeException(e.getMessage(), e);
		}
	}

	//@Override
	public Set<InterfaceAttribute> getInterfaceAttributes(final Long interfaceId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceAttributes(interfaceId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceAttributesException(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceSummary getInterfaceSummary(Long transactionId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummary(transactionId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceSummaryException(e.getMessage(), e);
		}
	}

	//@Override
	public List<InterfaceSummary> getInterfaceSummariesByStatus(Long status) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummariesByStatus(status);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceSummariesByStatusException(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceSummary createInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException 
	{
		try 
		{
			return snocServices.createInterfaceSummary(interfaceSummary);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.CreateInterfaceSummaryException(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceSummary updateInterfaceSummary(InterfaceSummary interfaceSummary) throws ApplicationException 
	{
		try 
		{
			return snocServices.updateInterfaceSummary(interfaceSummary);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.UpdateInterfaceSummaryException(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceSummary updateInterfaceSummaryStatus(Long transactionId, Long status) throws ApplicationException 
	{
		try 
		{
			return snocServices.updateInterfaceSummaryStatus(transactionId, status);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.UpdateInterfaceSummaryStatusException(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceFailure getInterfaceFailure(Long transactionFailureId) throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceFailure(transactionFailureId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceFailureException(e.getMessage(), e);
		}
	}

	//@Override
	public List<InterfaceFailure> getInterfaceFailureByTransactionId(Long transactionId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFailureByTransactionId(transactionId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceFailureByTransactionIdException(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceFailure createInterfaceFailure(InterfaceFailure interfaceFailure) throws ApplicationException
	{
		try 
		{
			return snocServices.createInterfaceFailure(interfaceFailure);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.CreateInterfaceFailureException(e.getMessage(), e);
		}
	}

	//@Override
	public void processInQueueRecords(List<Long> status, Long interfaceId, Long fileStatus, List<Long> partners) throws ApplicationException 
	{
		try 
		{
			snocServices.processInQueueRecords(status, interfaceId, fileStatus, partners);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.ProcessInQueueRecordsException(e.getMessage(), e);
		}
	}

	//@Override
	public void processInterfaceSummary(Long trasactionId, Long retryCount) throws ApplicationException 
	{
		try 
		{
			snocServices.processInterfaceSummary(trasactionId, retryCount);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.ProcessInterfaceSummaryException(e.getMessage(), e);
		}
	}

	//@Override
	public void processResponseConsumers(Long interfaceId) throws ApplicationException 
	{
		try 
		{
			snocServices.processResponseConsumers(interfaceId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.ProcessResponseConsumersException(e.getMessage(), e);
		}
	}

	//@Override
	public void processResponseAvailableRecords(Long interfaceId, Long status) throws ApplicationException 
	{
		try 
		{
			snocServices.processResponseAvailableRecords(interfaceId, status);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.ProcessResponseAvailableRecordsException(e.getMessage(), e);
		}
	}

	//@Override
	public String getTransactionId(Long interfaceId, String responseData) throws ApplicationException
	{
		try 
		{
			return snocServices.getTransactionId(interfaceId, responseData);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetTransactionIdException(e.getMessage(), e);
		}
	}

	//@Override
	public List<InterfaceSummary> getInterfaceSummaryByRefData1(String refData1, Long refData2, Long interfaceId,
			Long status, Long refData5) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummaryByRefData1(refData1, refData2, interfaceId, status, refData5);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceSummaryByRefData1Exception(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceSummary getInterfaceSummaryByRefData5(String refData5, Long status) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummaryByRefData5(refData5, status);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceSummaryByRefData5Exception(e.getMessage(), e);
		}
	}

	//@Override
	public InterfaceSummary getInterfaceSummaryByRefData4(Long refData4) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummaryByRefData4(refData4);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceSummaryByRefData4Exception(e.getMessage(), e);
		}
	}

	//@Override
	public String getInterfaceAttributeValue(Long interfaceId, String parameterName) throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceAttributeValue(interfaceId, parameterName);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.GetInterfaceAttributeValueException(e.getMessage(), e);
		}
	}

	//@Override
	public void updateInterfaceSummaryByResponse(String responseString, Long transactionId, Long interfaceId,
			Long status, Long retryCount) throws ApplicationException 
	{
		try 
		{
			snocServices.updateInterfaceSummaryByResponse(responseString, transactionId, interfaceId, status, retryCount);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.UpdateInterfaceSummaryByResponseException(e.getMessage(), e);
		}
	}

	//@Override
	public void publishInterfaceData(Long moduleId, Long refType, String refId, String requestData, Long clientId)
			throws ApplicationException 
	{
		try 
		{
			snocServices.publishInterfaceData(moduleId, refType, refId, requestData, clientId);
		} 
		catch (Exception e) 
		{
			throw new IntegrationManagement.PublishInterfaceDataException(e.getMessage(), e);
		}
	}

	//@Override
	public void processFile(List<Long> interfaceIds) throws ApplicationException 
	{
		try 
		{
			snocServices.processFile(interfaceIds);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ProcessFileException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceFileSummary getInterfaceFileSummary(Long fileId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFileSummary(fileId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceFileSummary updateInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException 
	{
		try 
		{
			return snocServices.updateInterfaceFileSummary(interfaceFileSummary);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.UpdateInterfaceFileSummaryException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceFileSummaryDetails updateInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException 
	{
		try 
		{
			return snocServices.updateInterfaceFileSummaryDetails(interfaceFileSummaryDetails);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.UpdateInterfaceFileSummaryDetailsException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceFileSummary createInterfaceFileSummary(InterfaceFileSummary interfaceFileSummary) throws ApplicationException 
	{
		try 
		{
			return snocServices.createInterfaceFileSummary(interfaceFileSummary);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.CreateInterfaceFileSummaryException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void createInterfaceSummariesByModule(InterfaceSummary interfaceSummary, Long moduleId, Long transactionType) throws ApplicationException 
	{
		try 
		{
			snocServices.createInterfaceSummariesByModule(interfaceSummary, moduleId, transactionType);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.CreateInterfaceSummariesByModuleException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<FilePropertiesBean> readFilePropertyBeans(Long interfaceId) throws ApplicationException 
	{
		try 
		{
			return snocServices.readFilePropertyBeans(interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ReadFilePropertyBeansException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceFileSummaryDetails createInterfaceFileSummaryDetails(
			InterfaceFileSummaryDetails interfaceFileSummaryDetails) throws ApplicationException 
	{
		try 
		{
			return snocServices.createInterfaceFileSummaryDetails(interfaceFileSummaryDetails);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.CreateInterfaceFileSummaryDetailsException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceFileSummary> getInterfaceFileSummaryByInterfaceIdAndStatus(Long interfaceId, Long statusId,
			Long partnerId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryByInterfaceIdAndStatus(interfaceId, statusId, partnerId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryByInterfaceIdAndStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetails(Long fileId) throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryDetails(fileId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryDetailsException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void processReceivedFiles(Long interfaceId, List<Long> partnerIds) throws ApplicationException 
	{
		try 
		{
			snocServices.processReceivedFiles(interfaceId, partnerIds);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ProcessReceivedFilesException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void reProcessFileBasedOrders(Long interfaceId, Long fileId) throws ApplicationException 
	{
		try 
		{
			snocServices.reProcessFileBasedOrders(interfaceId, fileId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ReProcessFileBasedOrdersException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void reProcessFilesBasedOnErrorCode(Long interfaceId, Long fileId, Long errorCode)
			throws ApplicationException 
	{
		try 
		{
			snocServices.reProcessFilesBasedOnErrorCode(interfaceId, fileId, errorCode);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ReProcessFilesBasedOnErrorCodeException(exception.getMessage(), exception);
		}
	}

	//@Override
	public ReprocessFileBean getFilesForReprocess(Long interfaceId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getFilesForReprocess(interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetFilesForReprocessException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void pullDataToFile(List<Long> interfaceIds) throws ApplicationException 
	{
		try 
		{
			snocServices.pullDataToFile(interfaceIds);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.PullDataToFileException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummariesByFileIdAndStatus(interfaceFileId, statusIds);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceSummariesByFileIdAndStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceSummary> getInterfaceSummariesByFileIdAndStatusWithLimit(Long interfaceFileId,
			List<Long> statusIds, Long limit, Long interfaceId, List<Long> fileIds, Short retryCount)
					throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummariesByFileIdAndStatusWithLimit(interfaceFileId, statusIds, limit, interfaceFileId, fileIds, retryCount);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceSummariesByFileIdAndStatusWithLimitException(exception.getMessage(), exception);
		}
	}

	//@Override
	public Long getInterfaceSummariesCountByFileIdAndStatus(Long interfaceFileId, List<Long> statusIds)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummariesCountByFileIdAndStatus(interfaceFileId, statusIds);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceSummariesCountByFileIdAndStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void prepareRejectionFile(Long interfaceId, List<Long> partnerIds) throws ApplicationException 
	{
		try 
		{
			snocServices.prepareRejectionFile(interfaceId, partnerIds);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.PrepareRejectionFileException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void createControlFile(String file, Long totalCount, String fileName, String fileFormat, Long currentFileId,
			String contolFileFormat, String contolFilePath) throws ApplicationException
	{
		try 
		{
			snocServices.createControlFile(file, totalCount, fileName, fileFormat, currentFileId, contolFileFormat, contolFilePath);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.CreateControlFileException(exception.getMessage(), exception);
		}
	}

	//@Override
	public ResponseBean sendFile(Long fileId, String fileType) throws ApplicationException 
	{
		try 
		{
			return snocServices.sendFile(fileId, fileType);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.SendFileException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceSummary> getInterfaceSummaryByFileIdBatchIdAndStatus(Long interfaceFileId, String refData5,
			Long status) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceSummaryByFileIdBatchIdAndStatus(interfaceFileId, refData5, status);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceSummaryByFileIdBatchIdAndStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceNotification createInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.createInterfaceNotification(interfaceNotification);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.CreateInterfaceNotificationException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceNotification updateInterfaceNotification(InterfaceNotification interfaceNotification)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.updateInterfaceNotification(interfaceNotification);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.UpdateInterfaceNotificationException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndStatus(Long interfaceId, Long statusId)
			throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceNotificationByInterfaceIdAndStatus(interfaceId, statusId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceNotificationByInterfaceIdAndStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceNotification> getInterfaceNotificationByInterfaceIdAndScenario(Long interfaceId,
			String scenario) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceNotificationByInterfaceIdAndScenario(interfaceId, scenario);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceNotificationByInterfaceIdAndScenarioException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String getModules() throws ApplicationException 
	{
		try 
		{
			return snocServices.getModules();	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetModulesException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String getInterfaceFilesByModuleAndOrgId(Long moduleId, Long orgId, Long orgType)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFilesByModuleAndOrgId(moduleId, orgId, orgType);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFilesByModuleAndOrgIdException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void verifyInventoryOrder(Long interfaceId) throws ApplicationException 
	{
		try 
		{
			snocServices.verifyInventoryOrder(interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.VerifyInventoryOrderException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String getInterfaceFileSummaryDetailsByFileId(Long fileId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryDetailsByFileId(fileId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryDetailsByFileIdException(exception.getMessage(), exception);
		}
	}

	//@Override
	public Object getJsonValue(String path, String jsonString) throws ApplicationException 
	{
		try 
		{
			return snocServices.getJsonValue(path, jsonString);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetJsonValueException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByName(String columnName, String columnValue)
			throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryDetailsByName(columnName, columnName);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryDetailsByNameException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void deleteBounceMailByIdandEmail(String bounceId, String bounceEmail) throws ApplicationException 
	{
		try 
		{
			snocServices.deleteBounceMailByIdandEmail(bounceId, bounceEmail);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.DeleteBounceMailByIdandEmailException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceFileSummaryDetails> getInterfaceFileSummaryDetailsByFileType(Long fileId, String fileType)
			throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryDetailsByFileType(fileId, fileType);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryDetailsByFileTypeException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceFileSummaryDetails getInterfaceFileSummaryDetailsById(Long detailsId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryDetailsById(detailsId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryDetailsByIdException(exception.getMessage(), exception);
		}
	}

	//@Override
	public ResponseBean publishToInterface(Long moduleId, String jsonRequestData, String refId) throws ApplicationException 
	{
		try 
		{
			return snocServices.publishToInterface(moduleId, jsonRequestData, refId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.PublishToInterfaceException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String syncBalanceInterface(Long moduleId, String jsonRequestData) throws ApplicationException 
	{
		try 
		{
			return snocServices.syncBalanceInterface(moduleId, jsonRequestData);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.SyncBalanceInterfaceExeption(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<Object> getInterfaceFileSummaryDetailsByIdAndName(Long interfaceId, String columnName, String columnValue) throws ApplicationException 
	{
		try 
		{
			return snocServices.getInterfaceFileSummaryDetailsByIdAndName(interfaceId, columnName, columnValue);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceFileSummaryDetailsByIdAndNameException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void processOrderResponseConsumer(Long interfaceId) throws ApplicationException 
	{
		try 
		{
			snocServices.processOrderResponseConsumer(interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ProcessOrderResponseConsumerException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void processCallback(Long transactionId) throws ApplicationException
	{
		try 
		{
			snocServices.processCallback(transactionId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ProcessCallbackException(exception.getMessage(), exception);
		}
	}

	//@Override
	public InterfaceSummary updateSummaryRequestData(InterfaceSummary interfaceSummary) throws ApplicationException 
	{
		try 
		{
			return snocServices.updateSummaryRequestData(interfaceSummary);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.UpdateSummaryRequestDataException(exception.getMessage(), exception);
		}
	}

	//@Override
	public DailyDumpSummary createDailyDumpSummary(DailyDumpSummary dailyDumpSummary) throws ApplicationException
	{
		try 
		{
			return snocServices.createDailyDumpSummary(dailyDumpSummary);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.CreateDailyDumpSummaryException(exception.getMessage(), exception);
		}
	}

	//@Override
	public ResponseBean notifyInterfaceOnDump(Long interfaceId, Date createdDate) throws ApplicationException
	{
		try 
		{
			return snocServices.notifyInterfaceOnDump(interfaceId, createdDate);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.NotifyInterfaceOnDumpException(exception.getMessage(), exception);
		}
	}

	//@Override
	public DailyDumpSummary getDailyDumpSummaryByDate(Long interfaceId, Date createdDate) throws ApplicationException 
	{
		try 
		{
			return snocServices.getDailyDumpSummaryByDate(interfaceId, createdDate);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetDailyDumpSummaryByDateException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<DailyDumpSummary> getDailyDumpSummaryByStatus(Long interfaceId, Long status)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.getDailyDumpSummaryByStatus(interfaceId, status);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetDailyDumpSummaryByStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public DailyDumpSummary updateDailyDumpSummaryStatus(Long dumpId, Long status) throws ApplicationException 
	{
		try 
		{
			return snocServices.updateDailyDumpSummaryStatus(dumpId, status);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.UpdateDailyDumpSummaryStatusException(exception.getMessage(), exception);
		}
	}

	//@Override
	public ResponseBean publishInternalRequest(Long moduleId, String jsonRequestData, String refId, Long refData3)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.publishInternalRequest(moduleId, jsonRequestData, refId, refData3);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.PublishInternalRequestException(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<InterfaceSummary> getInterfaceSummariesByRefData3(Long refData3, Long interfaceId)
			throws ApplicationException
	{
		try 
		{
			return snocServices.getInterfaceSummariesByRefData3(refData3, interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetInterfaceSummariesByRefData3Exception(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<ResponseBean> getSummaryResponseDataByRefData3(Long refData3) throws ApplicationException 
	{
		try 
		{
			return snocServices.getSummaryResponseDataByRefData3(refData3);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetSummaryResponseDataByRefData3Exception(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<Long> getSummaryTransIdsByRefData3(Long refData3, Long interfaceId) throws ApplicationException
	{
		try 
		{
			return snocServices.getSummaryTransIdsByRefData3(refData3, interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetSummaryTransIdsByRefData3Exception(exception.getMessage(), exception);
		}
	}

	//@Override
	public List<KycSyncInfo> getKycSyncAttributesByInterfaceId(Long interfaceId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getKycSyncAttributesByInterfaceId(interfaceId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.GetKycSyncAttributesByInterfaceIdException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void removeInterfaceFileSummaryDetails(Long fileDetailsId) throws ApplicationException
	{
		try 
		{
			snocServices.removeInterfaceFileSummaryDetails(fileDetailsId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.RemoveInterfaceFileSummaryDetailsException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void reProcessPushDataFiles(Long interfaceId, Date requiredDate) throws ApplicationException 
	{
		try 
		{
			snocServices.reProcessPushDataFiles(interfaceId, requiredDate);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ReProcessPushDataFilesException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void rollbackKPIFeedInterfaces(Long interfaceId, Long fileId) throws ApplicationException 
	{
		try 
		{
			snocServices.rollbackKPIFeedInterfaces(interfaceId, fileId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.RollbackKPIFeedInterfacesException(exception.getMessage(), exception);
		}
	}

	//@Override
	public void prepareRejectionFileById(Long interfaceId, Long fileId) throws ApplicationException 
	{
		try 
		{
			snocServices.prepareRejectionFileById(interfaceId, fileId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.PrepareRejectionFileByIdException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String invokePrimarySalesInterface(Long moduleId, String jsonRequestData) throws ApplicationException 
	{
		try 
		{
			return snocServices.invokePrimarySalesInterface(moduleId, jsonRequestData);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.InvokePrimarySalesInterfaceException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String tnmServicesInterface(Long moduleId, String jsonRequestData) throws ApplicationException 
	{
		try 
		{
			return snocServices.tnmServicesInterface(moduleId, jsonRequestData);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.TNMServicesInterfaceException(exception.getMessage(), exception);
		}
	}

	//@Override
	public CleanUpSummary getCleanUpSummary(Long cleanupId) throws ApplicationException 
	{
		try 
		{
			return snocServices.getCleanUpSummary(cleanupId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.TNMServicesInterfaceException(exception.getMessage(), exception);
		}
	}

	//@Override
	public CleanUpSummary processCleanupActivity(Long interfaceId, String userId, Long cleanupCode)
			throws ApplicationException 
	{
		try 
		{
			return snocServices.processCleanupActivity(interfaceId, userId, cleanupCode);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.ProcessCleanupActivityException(exception.getMessage(), exception);
		}
	}

	//@Override
	public String searchSO(String soId) throws ApplicationException 
	{
		try 
		{
			return snocServices.searchSO(soId);	
		} 
		catch (Exception exception) 
		{
			throw new IntegrationManagement.SearchSOException(exception.getMessage(), exception);
		}
	}

}
