package com.enhancesys.integration.services.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;
import net.treetechnologies.entities.masters.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.enhancesys.common.configuration.PropertiesLoader;
import com.enhancesys.entities.integration.InterfaceFileSummary;
import com.enhancesys.entities.integration.InterfaceFileSummaryDetails;
import com.enhancesys.entities.integration.Interfaces;
import com.enhancesys.integration.services.interfaces.CursorBean;
import com.enhancesys.integration.services.interfaces.FilePropertiesBean;
import com.enhancesys.integration.services.interfaces.IntegrationConstants;
import com.enhancesys.integration.services.interfaces.IntegrationManagement;
import com.enhancesys.integration.services.interfaces.ServerBean;
import com.enhancesys.integration.services.interfaces.converter.LookupMappingLocal;
import com.enhancesys.integration.services.interfaces.processor.PullDataToFile;
import com.enhancesys.integration.services.springCamel.dataEngine.interfaces.DataConstants;
import com.enhancesys.integration.services.springCamel.util.MongoTemplate;
import com.enhancesys.integration.services.util.CursorUtil;
import com.enhancesys.integration.services.util.Utility;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local({ PullDataToFile.class })
public class OutletSalesDump 
{
	@PersistenceContext(unitName = "com.enhancesys.dev")
	EntityManager entityManager;

	@EJB
	IntegrationManagement integrationManagement;

	@EJB
	LookupMappingLocal lookupMapping;
	
	@SuppressWarnings({ "unchecked"})
	public void processRequest(Long interfaceId, Date requiredDate) throws Exception
	{
		if(TLogger.debugIsEnabled())
		{
			TLogger.debug("OutletSalesDump :: Entry processRequest ... InterfaceId : " + interfaceId + ", RequiredDate : " + requiredDate);
		}
		Interfaces interfaces = null;
		InterfaceFileSummary interfaceFileSummary = null;
		InterfaceFileSummaryDetails fileSummaryDetails = null;
		Status status = null;
		List<FilePropertiesBean> propertiesBeans = null;
		SimpleDateFormat fileDateFormat = null;
		File file = null;
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		JSONObject sendServerjsonObject = null;
		JSONObject localServerjsonObject = null;
		JSONObject dataObj = null;
		JSONObject fieldsConfiguration = null;
//		JSONObject fieldConf = null;
		JSONObject lookupConf = null;
//		JSONObject collectionConf = null;
		JSONObject outputConf = null;
		String fileName = null;
		String finalName = null;
		Long totalCount = null;
		Long startTime = null;
		FilePropertiesBean bean = null;
		JSONArray jsonArr = null;
		JSONArray collectionConfArr = null;
		Date startDate = null;
		JSONObject jsonConfig = null;
		String jsonFileName = null;
		FileReader reader = null;
		JSONParser parser = null;
		Map<String, CursorBean> pgCursorMap = null;
		Map<String, CursorBean> mongoCursorMap = null;
		Map<String, MongoTemplate> mongoTemplateMap = null;
		Map<String, JSONObject> collectionConfMap = null;
		Map<String, List<JSONObject>> lastFetchedObjectMap = null;
		Map<String, List<JSONObject>> lastFetchedMongoMap = null;
		Map<String, DBObject> lookupValuesMap = null;
		CursorUtil cursorUtil = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		CursorBean cursorBean = null;
		DBCursor cursor = null;
		JSONObject collectionObj = null;
		DateFormat dateFormat = null;
//		Object fieldValue = null;
		String[] fields = null;
		String dumpDatefield = null;
		String dumpDate = null;
		JSONObject fieldConf = null;
		String[] collectionFields = null;
		String outputFileData = null;
		
		try
		{
			startTime = System.currentTimeMillis();
			startDate = new Date();
			interfaces = integrationManagement.getInterfaceById(interfaceId);
			
			propertiesBeans = integrationManagement.readFilePropertyBeans(interfaceId);
			bean = propertiesBeans.get(0);
			jsonFileName = bean.getProcessName();
			
			parser  = new JSONParser();
			reader = new FileReader(IntegrationConstants.DATA_PROCESSOR_CONFIG_PATH + jsonFileName + ".json");
			jsonConfig =  (JSONObject) (parser.parse(reader));
			reader.close();
			
			status = entityManager.find(Status.class, IntegrationConstants.FILE_INQUEUE_STATUS);
			
			fileName = bean.getFileName();
			if(bean.getRemoteFileNameFormat() != null && !bean.getRemoteFileNameFormat().isEmpty())
			{
				fileDateFormat = new SimpleDateFormat(bean.getRemoteFileNameFormat());
				fileName = fileName +  "_" + fileDateFormat.format(requiredDate != null ? requiredDate : new Date());
			}
			if(bean.getFileNameSequence() != null && !bean.getFileNameSequence().isEmpty())
			{
				fileName = fileName + bean.getFileNameSequence();
			}
			finalName = bean.getLocalDir() + File.separator + fileName + "." + bean.getRemoteFileFormat();
			file = new File(finalName);
			fileOutputStream = new FileOutputStream(file);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, IntegrationConstants.FILE_ENCODE_FORMAT);
			
			outputStreamWriter.write(bean.getFileHeaders());
			outputStreamWriter.write(System.getProperty("line.separator"));
			
			TLogger.debug("Interface : " + interfaceId + " : File : " + file.getName());
			
			totalCount = 0L;
			cursorUtil = new CursorUtil();
			collectionConfArr = (JSONArray) jsonConfig.get(DataConstants.PRIMARY);
			lookupConf = (JSONObject) jsonConfig.get(DataConstants.LOOKUP);
			fieldsConfiguration = (JSONObject) jsonConfig.get(DataConstants.CONFIGURATION);
			outputConf =  (JSONObject) jsonConfig.get(DataConstants.OUTPUT_CONF);
			fields = outputConf.get(DataConstants.FIELDS).toString().split(",");
			dumpDatefield = outputConf.get(DataConstants.DUMP_DATE_FIELD) != null ? outputConf.get(DataConstants.DUMP_DATE_FIELD).toString() : null;
			if(dumpDatefield != null)
			{
				fieldConf = (JSONObject) fieldsConfiguration.get(dumpDatefield);
				dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
				dumpDate = dateFormat.format(new Date());
			}
			
			lastFetchedObjectMap = new HashMap<String, List<JSONObject>>();
			lastFetchedMongoMap = new HashMap<String, List<JSONObject>>();
			lookupValuesMap = new HashMap<String, DBObject>();
			
			collectionConfMap = cursorUtil.getCollectionConfs(collectionConfArr);
			connection = cursorUtil.getPGConnection();
			pgCursorMap = cursorUtil.getPGCursor(collectionConfArr, connection);
			mongoTemplateMap = cursorUtil.getMongoTemplates();
			mongoCursorMap = cursorUtil.getMongoCursor(collectionConfArr, mongoTemplateMap);
			
			if(pgCursorMap.containsKey(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString()))
			{
				cursorBean = pgCursorMap.remove(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString());
				resultSet = cursorBean.getPgCursor();
				metaData = cursorBean.getMetaData();
				
				while(resultSet.next())
				{
					dataObj = Utility.convertToJSON(resultSet, metaData);
					/*for(Object key : fieldsConfiguration.keySet())
					{
						fieldConf = (JSONObject) fieldsConfiguration.get(key);
						if(fieldConf.get(DataConstants.COLLECTION_NAME) != null && !fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim().isEmpty())
							collectionConf = collectionConfMap.get(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim());
						
						if(pgCursorMap.get(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim()) != null)
						{
							fieldValue = cursorUtil.getPGValue(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim(), pgCursorMap, (JSONObject) collectionConf.get(DataConstants.MAP_KEY), dataObj, fieldConf.get(DataConstants.FIELD).toString().trim());
							dataObj.put(key.toString(), fieldValue != null && !fieldValue.toString().trim().isEmpty() ? fieldValue : "");
						}
						else if(mongoCursorMap.get(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim()) != null)
						{
							fieldValue = cursorUtil.getMongoValue(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim(), mongoCursorMap, (JSONObject) collectionConf.get(DataConstants.MAP_KEY), dataObj, fieldConf, lookupConf, mongoTemplateMap);
							dataObj.put(key.toString(), fieldValue != null && !fieldValue.toString().trim().isEmpty() ? fieldValue : "");
						}
						else
						{
							if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
									&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
							{
								dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
								if(dateFormat == null)
								{
									dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
									dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
								}
								
								dataObj.put(key.toString(), dateFormat.format(new Date()));
							}
						}
					}*/
					
					for(Map.Entry<String, JSONObject> collection : collectionConfMap.entrySet())
					{
						if(!collection.getKey().equalsIgnoreCase(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString()))
						{
							collectionObj = collection.getValue();
							collectionFields = collectionObj.get(DataConstants.FIELDS).toString().split(",");
							if(collectionObj.get(DataConstants.IS_POSTGRES) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_POSTGRES).toString()))
							{
								dataObj = cursorUtil.getPGValues(collection.getKey(), pgCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, fieldsConfiguration, lastFetchedObjectMap);
							}
							else 
							{
								if(collectionObj.get(DataConstants.IS_LOOKUP) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_LOOKUP).toString()))
									dataObj = cursorUtil.getMongoValues(dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, lookupValuesMap);
								else
									dataObj = cursorUtil.getMongoValues(collection.getKey(), mongoCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, lastFetchedMongoMap, lookupValuesMap);
							}
						}
					}
					
					outputFileData = "";
					for(String field : fields)
					{
						outputFileData += dataObj.get(field) != null ? dataObj.get(field) : ""  + bean.getCsvDelimeter();
					}
					if(!outputFileData.replace(bean.getCsvDelimeter(), "").trim().isEmpty())
					{
						if(dumpDate != null)
							outputFileData = dateFormat.format(new Date()) + bean.getCsvDelimeter() + outputFileData;
						totalCount++;
						outputStreamWriter.write(outputFileData.substring(0, outputFileData.length() - 1) + System.lineSeparator());
					}
				}
			}
			else
			{
				cursorBean = mongoCursorMap.remove(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString());
				cursor = cursorBean.getMongoCursor();
				while(cursor.hasNext())
				{
					dataObj = new JSONObject();
					dataObj.putAll((BasicDBObject) cursor.next());
					/*for(Object key : fieldsConfiguration.keySet())
					{
						fieldConf = (JSONObject) fieldsConfiguration.get(key);
						if(fieldConf.get(DataConstants.COLLECTION_NAME) != null && !fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim().isEmpty())
							collectionConf = collectionConfMap.get(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim());
						
						if(mongoCursorMap.get(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim()) != null)
						{
							fieldValue = cursorUtil.getMongoValue(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim(), mongoCursorMap, (JSONObject) collectionConf.get(DataConstants.MAP_KEY), dataObj, fieldConf, lookupConf, mongoTemplateMap);
							dataObj.put(key.toString(), fieldValue != null && !fieldValue.toString().trim().isEmpty() ? fieldValue : "");
						}
						else if(pgCursorMap.get(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim()) != null)
						{
							fieldValue = cursorUtil.getPGValue(fieldConf.get(DataConstants.COLLECTION_NAME).toString().trim(), pgCursorMap, (JSONObject) collectionConf.get(DataConstants.MAP_KEY), dataObj, fieldConf.get(DataConstants.FIELD).toString().trim());
							dataObj.put(key.toString(), fieldValue != null && !fieldValue.toString().trim().isEmpty() ? fieldValue : "");
						}
						else
						{
							if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
									&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
							{
								dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
								if(dateFormat == null)
								{
									dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
									dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
								}
								
								dataObj.put(key.toString(), dateFormat.format(new Date()));
							}
						}
					}*/
					
					for(Map.Entry<String, JSONObject> collection : collectionConfMap.entrySet())
					{
						if(!collection.getKey().equalsIgnoreCase(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString()))
						{
							collectionObj = collection.getValue();
							collectionFields = collectionObj.get(DataConstants.FIELDS).toString().split(",");
							if(collectionObj.get(DataConstants.IS_POSTGRES) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_POSTGRES).toString()))
							{
								dataObj = cursorUtil.getPGValues(collection.getKey(), pgCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, fieldsConfiguration, lastFetchedObjectMap);
							}
							else 
							{
								if(collectionObj.get(DataConstants.IS_LOOKUP) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_LOOKUP).toString()))
									dataObj = cursorUtil.getMongoValues(dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, lookupValuesMap);
								else
									dataObj = cursorUtil.getMongoValues(collection.getKey(), mongoCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, lastFetchedMongoMap, lookupValuesMap);
							}
						}
					}
					outputFileData = "";
					for(String field : fields)
					{
						outputFileData += (dataObj.get(field) != null ? dataObj.get(field) : "")  + bean.getCsvDelimeter();
					}
					if(!outputFileData.replace(bean.getCsvDelimeter(), "").trim().isEmpty())
					{
						if(dumpDate != null)
							outputFileData = dateFormat.format(new Date()) + bean.getCsvDelimeter() + outputFileData;
						totalCount++;
						outputStreamWriter.write(outputFileData.substring(0, outputFileData.length() - 1) + System.lineSeparator());
					}
				}
			}
			
			if (outputStreamWriter != null)
				outputStreamWriter.close();
			if(fileOutputStream != null)
				fileOutputStream.close();
			
			for(String key : pgCursorMap.keySet())
			{
				cursorBean = pgCursorMap.get(key);
				if(cursorBean.getPgCursor() != null)
					cursorBean.getPgCursor().close();
			}
			connection.close();
			
			for(String key : mongoCursorMap.keySet())
			{
				cursorBean = mongoCursorMap.get(key);
				if(cursorBean.getMongoCursor() != null)
					cursorBean.getMongoCursor().close();
			}
			
			interfaceFileSummary = new InterfaceFileSummary();
			interfaceFileSummary.setFileName(file.getName());
			interfaceFileSummary.setInterfaces(interfaces);
			interfaceFileSummary.setTotalCount(totalCount);
			interfaceFileSummary.setSuccessCount(0L);
			interfaceFileSummary.setErrorCount(0L);
			interfaceFileSummary.setStatus(status);
			interfaceFileSummary.setUploadedBy(Long.parseLong(bean.getClientId()));
			interfaceFileSummary.setValidatedOn(startDate);
			interfaceFileSummary.setProcessedOn(new Date());
			interfaceFileSummary.setMessage(PropertiesLoader.getErrorDescriptionFor(IntegrationConstants.FILES_CREATED));
			if(!bean.getServers().isEmpty())
			{
				jsonArr = new JSONArray();
				for(ServerBean serverBean : bean.getServers())
				{
					sendServerjsonObject = new JSONObject();
					sendServerjsonObject.put("Host", serverBean.getRemoteHost());
					sendServerjsonObject.put("Port", serverBean.getRemotePort());
					sendServerjsonObject.put("User", serverBean.getSecurityPrincipal());
					sendServerjsonObject.put("Pass", serverBean.getSecurityCredentials());
					sendServerjsonObject.put("PassPath", serverBean.getSecurityCredentialsPath());
					if(serverBean.getRemoteDir() != null)
						sendServerjsonObject.put("DestDir", serverBean.getRemoteDir());
					else 
						sendServerjsonObject.put("DestDir", bean.getRemoteDir());
					if(serverBean.getRemoteControlDir() != null)
						sendServerjsonObject.put("DestCtlDir", serverBean.getRemoteControlDir());
					else
						sendServerjsonObject.put("DestCtlDir", bean.getRemoteControlDir());
					jsonArr.add(sendServerjsonObject);
				}
				interfaceFileSummary.setSendServerDetails(jsonArr.toJSONString());
				jsonArr = null;
			}
			else
			{
				sendServerjsonObject = new JSONObject();
				sendServerjsonObject.put("Host", bean.getRemoteHost());
				sendServerjsonObject.put("Port", bean.getRemotePort());
				sendServerjsonObject.put("User", bean.getSecurityPrincipal());
				sendServerjsonObject.put("Pass", bean.getSecurityCredentials());
				sendServerjsonObject.put("PassPath", bean.getSecurityCredentialsPath());
				sendServerjsonObject.put("DestDir", bean.getRemoteDir());
				sendServerjsonObject.put("DestCtlDir", bean.getRemoteControlDir());
				interfaceFileSummary.setSendServerDetails(sendServerjsonObject.toJSONString());
			}
			localServerjsonObject = new JSONObject();
			localServerjsonObject.put("Dir", bean.getLocalDir());
			localServerjsonObject.put("BkpDir", bean.getLocalBackupDir());
			localServerjsonObject.put("CtlDir", bean.getLocalControlDir());
			localServerjsonObject.put("CtlBkpDir", bean.getLocalControlBackupDir());
			interfaceFileSummary.setLocalServerDetails(localServerjsonObject.toJSONString());
			interfaceFileSummary = integrationManagement.createInterfaceFileSummary(interfaceFileSummary);
			
			fileSummaryDetails = new InterfaceFileSummaryDetails();
			fileSummaryDetails.setControlFileName("");
			fileSummaryDetails.setFileName(file.getName());
			fileSummaryDetails.setFileType(IntegrationConstants.ACTUAL_FILE_TYPE_CHAR);
			fileSummaryDetails.setInterfaceFileSummary(interfaceFileSummary);
			fileSummaryDetails.setTotalCount(0L);
			fileSummaryDetails.setSuccessCount(0L);
			fileSummaryDetails.setErrorCount(0L);
			fileSummaryDetails = integrationManagement.createInterfaceFileSummaryDetails(fileSummaryDetails);

			
			integrationManagement.createControlFile(file.getPath(), totalCount, fileName, bean.getRemoteFileFormat(), fileSummaryDetails.getFileDetailsId(), bean.getControlFileFormat(), bean.getLocalControlDir());
		}
		catch(NullPointerException nullPointerException)
		{
			TLogger.error("OutletSalesDump - NullPointerException :: " + nullPointerException.getMessage(), nullPointerException);
			throw nullPointerException;
		}
		catch(ApplicationException applicationException)
		{
			TLogger.error("OutletSalesDump - Application Exception :: " + applicationException.getMessage(), applicationException);
			throw applicationException;
		}
		catch(Exception exception)
		{
			TLogger.error("OutletSalesDump - Exception :: " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			interfaces = null;
			interfaceFileSummary = null;
			fileSummaryDetails = null;
			status = null;
			propertiesBeans = null;
			fileDateFormat = null;
			file = null;
			if(fileOutputStream != null)
				fileOutputStream.close();
			fileOutputStream = null;
			if(outputStreamWriter != null)
				outputStreamWriter.close();
			outputStreamWriter = null;
			sendServerjsonObject = null;
			localServerjsonObject = null;
			dataObj = null;
			fieldsConfiguration = null;
//			fieldConf = null;
//			collectionConf = null;
			outputConf = null;
			fileName = null;
			finalName = null;
			totalCount = null;
			bean = null;
			jsonArr = null;
			collectionConfArr = null;
			startDate = null;
			jsonConfig = null;
			jsonFileName = null;
			reader = null;
			parser = null;
			pgCursorMap = null;
			mongoCursorMap = null;
			mongoTemplateMap = null;
			collectionConfMap = null;
			cursorUtil = null;
			connection = null;
			resultSet = null;
			metaData = null;
			cursorBean = null;
			cursor = null;
			dateFormat = null;
//			fieldValue = null;
			fields = null;
			outputFileData = null;
			lastFetchedObjectMap = null;
			lastFetchedMongoMap = null;
			collectionObj = null;
			collectionFields = null;
			dumpDate = null;
			dumpDatefield = null;
			fieldConf = null;
			
			if(TLogger.debugIsEnabled())
			{
				TLogger.debug("OutletSalesDump : Exit processRequest... Took : " + (System.currentTimeMillis() - startTime) + " milliseconds");
			}
			
			startTime = null;
		}
	}
}
