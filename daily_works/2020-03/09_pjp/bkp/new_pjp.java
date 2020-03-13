package com.enhancesys.integration.services.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.enhancesys.common.configuration.PropertiesLoader;
import com.enhancesys.entities.integration.InterfaceFileSummary;
import com.enhancesys.entities.integration.InterfaceFileSummaryDetails;
import com.enhancesys.entities.integration.Interfaces;
import com.enhancesys.integration.services.interfaces.FilePropertiesBean;
import com.enhancesys.integration.services.interfaces.IntegrationConstants;
import com.enhancesys.integration.services.interfaces.IntegrationManagement;
import com.enhancesys.integration.services.interfaces.ServerBean;
import com.enhancesys.integration.services.interfaces.converter.LookupMappingLocal;
import com.enhancesys.integration.services.interfaces.processor.PullDataToFile;
import com.enhancesys.integration.services.util.TokuR4User;
import com.enhancesys.integration.services.util.TokuStandAloneUser;
import com.enhancesys.integration.services.util.TokuUser;
import com.enhancesys.integration.services.util.Utility;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * <b>Purpose:</b><br>
 * 		Implementation of PJPPlannedCallsDump from SNOC system..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        05-02-2020		   Suresh Upparu
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local({ PullDataToFile.class })
public class PJPPlannedCallsDump 
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
			TLogger.debug("PJPPlannedCallsDump Entry processRequest..");

		Interfaces interfaces = null;
		InterfaceFileSummary interfaceFileSummary = null;
		InterfaceFileSummaryDetails fileSummaryDetails = null;
		Status status = null;
		BasicDBObject queryObject = null;
		BasicDBObject sortObject = null;
		List<FilePropertiesBean> propertiesBeans = null;
		SimpleDateFormat fileDateFormat = null;
		File file = null;
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		JSONObject sendServerjsonObject = null;
		JSONObject localServerjsonObject = null;
		String fileName = null;
		String finalName = null;
		Long totalCount = null;
		Long currentFileId = null;
		Long startTime = null;
		Long endTime = null;
		FilePropertiesBean bean = null;
		DBCursor outletOrgCursor = null;
		DBCursor outletHierarchyCursor = null;
		DB db = null;
		DBCollection orgCollection = null;
		DBCollection outletHierarchyCollection = null;
		DBCollection userOrgMappingColl = null;
		DBCollection userColl = null;
		BasicDBObject projectQry = null;
		DBObject outletOrgObject = null;
		DBObject tempObject = null;
		DBObject lastFetchedObject = null;
		DBObject resultObject = null;
		BasicDBList resultList = null;
		JSONArray jsonArr = null;
//		Long cacheSize = null;
		Date startDate = null;
		Calendar cal = null;
//		SimpleDateFormat yyyyMMddHHmmssSSS = null;
//		File outletOrgSortFile = null;
//		File outletHierarchySortFile = null;
		FileReader fileReader = null;
		FileReader hierarchyFileReader = null;
		BufferedReader bufferReader = null;
		BufferedReader hierarchyBufferReader = null;
//		String dataLine = null;
//		String hierarchyDataLine = null;
		Long outletId = null;
		Long outletHierarchyId = null;
		DBCursor statusDescCurser =  null;
		try
		{
			startTime = System.currentTimeMillis();
			startDate = new Date();
			interfaces = integrationManagement.getInterfaceById(interfaceId);
			if(interfaces == null)
			{
				TLogger.error("Interface does not exist for the id :: " + interfaceId);
				throw new Exception("Interface does not exist for the id :: " + interfaceId, null);
			}
			
			propertiesBeans = integrationManagement.readFilePropertyBeans(interfaceId);
			bean = propertiesBeans.get(0);
			
			status = entityManager.find(Status.class, IntegrationConstants.FILE_INQUEUE_STATUS);
			if(status == null)
			{
				TLogger.error("Status does not exist for the id :: " + IntegrationConstants.FILE_INQUEUE_STATUS);
				throw new Exception("Status does not exist for the id :: " + IntegrationConstants.FILE_INQUEUE_STATUS, null);
			}
			if(bean.getFileHeaders() == null)
			{
				TLogger.error("Please configure File Headers for the interface :: " + interfaceId);
				throw new Exception("Please configure File Headers for the interface :: " + interfaceId, null);
			}
			
			if(TokuR4User.adminMongoClient == null)
				new TokuR4User();
			
			if(TokuStandAloneUser.adminMongoClient == null)
				new TokuStandAloneUser();
			
			if(TokuUser.adminMongoClient == null)
				new TokuUser();
			
			cal = Calendar.getInstance();
			if(requiredDate != null)
			{
				cal.setTime(requiredDate);
				cal.add(Calendar.DATE, -1);
			}
			else
				cal.add(Calendar.DATE, -(Integer.parseInt(bean.getDateDuration())));
			
			fileName = bean.getFileName();
			if(bean.getRemoteFileNameFormat() != null && !bean.getRemoteFileNameFormat().isEmpty())
			{
				fileDateFormat = new SimpleDateFormat(bean.getRemoteFileNameFormat());
				fileName = fileName +  "_" + fileDateFormat.format(requiredDate != null ? requiredDate : new Date());
			}
			if(bean.getFileNameSequence() != null && !bean.getFileNameSequence().isEmpty())
			{
				fileName = fileName+bean.getFileNameSequence();
			}
			finalName = bean.getLocalDir() + File.separator + fileName +"." + bean.getRemoteFileFormat();
			file = new File(finalName);
			fileOutputStream = new FileOutputStream(file);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, IntegrationConstants.FILE_ENCODE_FORMAT);
			
			outputStreamWriter.write(bean.getFileHeaders());
			outputStreamWriter.write(System.getProperty("line.separator"));
			
			TLogger.debug("Interface : " + interfaceId + " : File : " + file.getName());
			
			totalCount = 0L;
//			int limit = Integer.parseInt(PropertiesLoader.getValueFor("DAILY_DUMP_QUERY_LIMIT"));
//			cacheSize = Long.parseLong(PropertiesLoader.getValueFor("LOOKUP_CACHE_SIZE"));
			
//			yyyyMMddHHmmssSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			
			// R1 & R2 db collections creation..
			//statusDesc
			db = TokuUser.adminMongoClient.getDB("snoc");
			queryObject = new BasicDBObject();
			queryObject.put("transitionTypeId", "52");
			projectQry = new BasicDBObject();
			projectQry.put("description", 1);
			projectQry.put("s_id", 1);
			statusDescCurser =  db.getCollection("genericMasterValueCollection").find(queryObject, projectQry);
			for (DBObject obj : statusDescCurser) {
				BasicDBObject basicDbObj = (BasicDBObject) obj;
				statusDesc.put(basicDbObj.getLong("s_id"), basicDbObj.getString("description"));
			}
			userOrgMappingColl = db.getCollection("user_org_mapping");
			userColl = db.getCollection("user");
			orgCollection = db.getCollection("organization");
			
			// R5 db collections creation..
//			db = TokuStandAloneUser.adminMongoClient.getDB("daily_dump");
			db = TokuStandAloneUser.adminMongoClient.getDB("daily_dump_snapshot");
			outletHierarchyCollection = db.getCollection("outlet_hierachy_dump");
			
			TLogger.debug("Outlet org hierarchy data query Starting..");
			projectQry = new BasicDBObject();
			projectQry.append("region_name", 1);
			projectQry.append("sub_area_name", 1);
			projectQry.append("sales_area_name", 1);
			projectQry.append("cluster_name", 1);
			projectQry.append("micro_cluster_name", 1);
			projectQry.append("mpc_short_code", 1);
			projectQry.append("supervisor_code", 1);
			projectQry.append("cso_code", 1);
			projectQry.append("outlet_org_id", 1);
			sortObject = new BasicDBObject();
			sortObject.put("outlet_org_id", 1);
			outletHierarchyCursor = outletHierarchyCollection.find(new BasicDBObject(), projectQry).addOption(Bytes.QUERYOPTION_NOTIMEOUT).sort(sortObject);
			TLogger.debug("Outlet org hierarchy data query Completed.. outletHierarchyCursor Size : " + outletHierarchyCursor.size());
//			outletHierarchySortFile = sortData(outletHierarchyCursor, sortObject, "outlet_hierachy_dump", yyyyMMddHHmmssSSS);
//			
//			hierarchyFileReader = new FileReader(outletHierarchySortFile);
//			hierarchyBufferReader = new BufferedReader(hierarchyFileReader);
			
			TLogger.debug("Outlet organization data query Starting..");
			queryObject = new BasicDBObject();
			queryObject.put("org_type", 6L);
			queryObject.put("sub_org_type", 66L);
			queryObject.put("status", 174L);
			TLogger.debug("Query Object : " + queryObject);
			projectQry = new BasicDBObject();
			projectQry.append("_id", 1);
			projectQry.append("org_id", 1);
			projectQry.append("ref_code", 1);
			sortObject = new BasicDBObject();
			sortObject.put("_id", 1);
			outletOrgCursor = orgCollection.find(queryObject, projectQry).addOption(Bytes.QUERYOPTION_NOTIMEOUT).sort(sortObject);
			TLogger.debug("Outlet orginaization data query Completed.. outletOrgCursor Size : " + outletOrgCursor.size());
//			outletOrgSortFile = sortData(outletOrgCursor, sortObject, "organization", yyyyMMddHHmmssSSS);
			
//			fileReader = new FileReader(outletOrgSortFile);
//			bufferReader = new BufferedReader(fileReader);
			
			outer : while(outletOrgCursor.hasNext())
			{
				boolean flag = true;
				outletOrgObject = outletOrgCursor.next();
				outletId = Long.parseLong(outletOrgObject.get("org_id").toString());
				if(lastFetchedObject != null)
				{
					outletHierarchyId = Long.parseLong(lastFetchedObject.get("outlet_org_id").toString());
					if(outletId.longValue() < outletHierarchyId.longValue())
						continue;
					else if(outletId.longValue() == outletHierarchyId.longValue())
					{
						outletOrgObject.putAll(lastFetchedObject);
						findHierarchyData(outletOrgObject, userOrgMappingColl, userColl, orgCollection);
						lastFetchedObject = null;
						
						resultList = findBeatData(TokuUser.adminMongoClient.getDB("beat"), outletOrgObject);
						if(resultList == null || resultList.isEmpty())
						{
							resultList = new BasicDBList();
							resultList.add(outletOrgObject);
						}
						
						flag = false;
//						break;
					}
					/*else if(outletId.longValue() > outletHierarchyId.longValue())
					{
//						continue to hierarchyDataLine while;
						flag = true;
					}*/
				}
				
				if(flag)
				{
					while(outletHierarchyCursor.hasNext())
					{
						tempObject = outletHierarchyCursor.next();
						if(tempObject.get("outlet_org_id") == null)
							continue;
						outletHierarchyId = Long.parseLong(tempObject.get("outlet_org_id").toString());
						
						if(outletId.longValue() < outletHierarchyId.longValue())
						{
							lastFetchedObject = tempObject;
							continue outer;
						}
						else if(outletId.longValue() > outletHierarchyId.longValue())
							continue;
						else
						{
							outletOrgObject.putAll(tempObject);
							findHierarchyData(outletOrgObject, userOrgMappingColl, userColl, orgCollection);	
							
							resultList = findBeatData(TokuUser.adminMongoClient.getDB("beat"), outletOrgObject);
							if(resultList == null || resultList.isEmpty())
							{
								resultList = new BasicDBList();
								resultList.add(outletOrgObject);
							}
							break;
						}
					}
				}
				
				for(Object object : resultList)
				{
					resultObject = (DBObject) object;
					if(resultObject.get("frequency") != null)
					{
						if(resultObject.get("frequency").toString().trim().equals("1")) 
							resultObject.put("frequency", "Daily");
						else if(resultObject.get("frequency").toString().trim().equals("2")) 
							resultObject.put("frequency", "Weekly");
						else if(resultObject.get("frequency").toString().trim().equals("3")) 
							resultObject.put("frequency", "Monthly");
						else if(resultObject.get("frequency").toString().trim().equals("4")) 
							resultObject.put("frequency", "Yearly");
					}

					outputStreamWriter.write(
							(resultObject.get("region_name") != null ? resultObject.get("region_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("sub_area_name") != null ? resultObject.get("sub_area_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("sales_area_name") != null ? resultObject.get("sales_area_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("cluster_name") != null ? resultObject.get("cluster_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("micro_cluster_name") != null ? resultObject.get("micro_cluster_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("mpc_short_code") != null ? resultObject.get("mpc_short_code") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("supervisor_code") != null ? resultObject.get("supervisor_code") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("supervisor_name") != null ? resultObject.get("supervisor_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("cso_code") != null ? resultObject.get("cso_code") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("cso_name") != null ? resultObject.get("cso_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("beat_id") != null ? resultObject.get("beat_id") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("beat_name") != null ? resultObject.get("beat_name") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("start_date") != null ? resultObject.get("start_date") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("end_date") != null ? resultObject.get("end_date") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("created_by") != null ? resultObject.get("created_by") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("creation_date") != null ? resultObject.get("creation_date") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("frequency") != null ? resultObject.get("frequency") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("modify_date") != null ? resultObject.get("modify_date") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("status") != null ? resultObject.get("status") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("ref_code") != null ? resultObject.get("ref_code") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("plan_date") != null ? resultObject.get("plan_date") : "") + bean.getCsvDelimeter()
							+ (resultObject.get("plan_day") != null ? resultObject.get("plan_day") : "") + System.getProperty("line.separator")
							);
					totalCount ++;
				}
				
				resultList = new BasicDBList();
			}
			
			TLogger.debug("Total Number of Records : " + totalCount);
			
			/*if(hierarchyBufferReader != null)
				hierarchyBufferReader.close();
			if(hierarchyFileReader != null)
				hierarchyFileReader.close();
			if(bufferReader != null)
				bufferReader.close();
			if(fileReader != null)
				fileReader.close();*/
			if (outputStreamWriter != null)
				outputStreamWriter.close();
			if(fileOutputStream != null)
				fileOutputStream.close();
			
//			outletHierarchySortFile.delete();
//			outletOrgSortFile.delete();
			
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

			currentFileId = fileSummaryDetails.getFileDetailsId();
			
			integrationManagement.createControlFile(file.getPath(), totalCount, fileName, bean.getRemoteFileFormat(), currentFileId, bean.getControlFileFormat(), bean.getLocalControlDir());
		}
		catch(NullPointerException nullPointerException)
		{
			TLogger.error("[PJPPlannedCallsDump] - NullPointerException :: " + nullPointerException.getMessage(), nullPointerException);
			throw nullPointerException;
		}
		catch(ApplicationException applicationException)
		{
			TLogger.error("[PJPPlannedCallsDump] - Application Exception :: " + applicationException.getMessage(), applicationException);
			throw applicationException;
		}
		catch(Exception exception)
		{
			TLogger.error("[PJPPlannedCallsDump] - Exception :: " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			interfaces = null;
			interfaceFileSummary = null;
			fileSummaryDetails = null;
			status = null;
			queryObject = null;
			sortObject = null;
			propertiesBeans = null;
			fileDateFormat = null;
			file = null;
			if(outletOrgCursor != null)
				outletOrgCursor.close();
			if(outletHierarchyCursor != null)
				outletHierarchyCursor.close();
			if(outputStreamWriter != null)
				outputStreamWriter.close();
			if(fileOutputStream != null)
				fileOutputStream.close();
			outputStreamWriter = null;
			fileOutputStream = null;
			sendServerjsonObject = null;
			localServerjsonObject = null;
			outletOrgCursor = null;
			outletHierarchyCursor = null;
			fileName = null;
			finalName = null;
			totalCount = null;
			currentFileId = null;
			db = null;
			orgCollection = null;
			projectQry = null;
			tempObject = null;
			lastFetchedObject = null;
			resultObject = null;
			resultList = null;
			jsonArr = null;
//			cacheSize = null;
			endTime = System.currentTimeMillis();
			startDate = null;
			cal = null;
//			yyyyMMddHHmmssSSS = null;
//			outletOrgSortFile = null;
//			outletHierarchySortFile = null;
			if(fileReader != null)
				fileReader.close();
			fileReader = null;
			if(hierarchyFileReader != null)
				hierarchyFileReader.close();
			hierarchyFileReader = null;
			if(bufferReader != null)
				bufferReader.close();
			bufferReader = null;
			if(hierarchyBufferReader != null)
				hierarchyBufferReader.close();
			hierarchyBufferReader = null;
//			dataLine = null;
//			hierarchyDataLine = null;
			outletId = null;
			outletHierarchyId = null;
			statusDescCurser =  null;
			
			if(TLogger.debugIsEnabled())
				TLogger.debug("PJPPlannedCallsDump : Exit processRequest.. "+(endTime - startTime));
			
			endTime = null;
			startTime = null;
		}
	}
	
	/*private File sortData(DBCursor cursor, DBObject sortBy, String collectionName, SimpleDateFormat format) throws Exception
	{
		File file = null;
		File fileCopy = null;
		OutputStream outputStream = null;
		DBObject dbObject = null;
		Process process = null;
		String fileName = null;
		String delimiter = "|";
		String dataLine = null;
		String sortScript = null;
		
		try
		{
			if(sortBy == null || sortBy.toString().trim().isEmpty())
				return null;

			fileName = IntegrationConstants.DUMPS_FILE_SORT_SCRIPTS_PATH + collectionName + "_" + format.format(new Date()) + ".csv";
			
			TLogger.debug("SortColumns : " + sortBy);
			
			file = new File(fileName);
			outputStream = new FileOutputStream(file);
			while(cursor.hasNext())
			{
				dataLine = "";
				dbObject = cursor.next();
				for(String column : sortBy.keySet())
				{
					dataLine = dataLine + (dbObject.get(column) != null ? dbObject.get(column).toString() : "") + delimiter;
				}
				dataLine = dataLine + JSON.serialize(dbObject) + System.getProperty("line.separator"); 
				outputStream.write(dataLine.getBytes());
			}
			outputStream.close();
			cursor.close();
			
			fileCopy = new File(fileName + ".cp");
			FileUtils.copyFile(new File(fileName), fileCopy);
			
			sortScript = IntegrationConstants.DUMPS_FILE_SORT_SCRIPTS_PATH + "collection" + sortBy.keySet().size() + ".sh";
			
			//Sort the file at unix level using the sh file..
			TLogger.debug("Sort file script initiated for file :: " + fileCopy.getPath());
			TLogger.debug("Script file : " + sortScript);
			process = Runtime.getRuntime().exec(sortScript + " " + fileCopy.getPath() + " " + fileCopy.getPath());
			TLogger.debug("Process : waitFor() :: " + process.waitFor());
			
			return fileCopy;
		}
		catch(Exception exception)
		{
			TLogger.error("[PJPPlannedCallsDump] - Exception :: " + exception.getMessage(), exception);
			throw exception; 
		}
		finally
		{
			file = null;
			fileCopy = null;
			outputStream = null;
			dbObject = null;
			process = null;
			fileName = null;
			delimiter = null;
			dataLine = null;
			sortScript = null;
		}
	}*/
	
	private void findHierarchyData(DBObject outletOrgObject, DBCollection userOrgMappingColl, DBCollection userColl, DBCollection orgColl) throws Exception
	{
//		TLogger.debug("Entrty findHierarchyData.. " + outletOrgObject.get("org_id"));
		BasicDBObject queryObject = null;
		BasicDBObject projectQry = null;
		DBObject dbObject = null;
		DBObject userOrgMapping = null;
		DBObject userObject = null;
		DBObject orgObject = null;
		List<Long> positionIds = null;
		String firstName = null;
		String lastName = null;
		
		try
		{
			outletOrgObject.put("cso_name", "");
			outletOrgObject.put("supervisor_name", "");
			positionIds = new ArrayList<Long>();
			positionIds.add(1031L);
			queryObject = new BasicDBObject();
			queryObject.put("dest_org_id", outletOrgObject.get("org_id")); // index is not there, need to create index..
			queryObject.put("position_id", new BasicDBObject("$in", positionIds));
			userOrgMapping = userOrgMappingColl.findOne(queryObject);
			if(userOrgMapping != null)
			{
				queryObject = new BasicDBObject();
				queryObject.put("_id", userOrgMapping.get("user_id"));
				userObject = userColl.findOne(queryObject);
				
				if(userObject != null) 
				{
					firstName = userObject.get("first_name") != null ? userObject.get("first_name").toString() : "";
					lastName = userObject.get("last_name") != null ? userObject.get("last_name").toString() : "";
					outletOrgObject.put("cso_name", firstName + " " + lastName);
					outletOrgObject.put("cso_code", userObject.get("uname") != null ? userObject.get("uname").toString() : "");
				}
			
				queryObject = new BasicDBObject();
				queryObject.put("_id", userOrgMapping.get("src_org_id"));
				orgObject = orgColl.findOne(queryObject);
				if(orgObject != null)
					outletOrgObject.put("mpc_short_code", orgObject.get("ref_code") != null ? orgObject.get("ref_code").toString() : "");
			}
			
			if(outletOrgObject.get("supervisor_code") != null && !outletOrgObject.get("supervisor_code").toString().trim().isEmpty())
			{
				queryObject = new BasicDBObject();
				queryObject.put("uname", outletOrgObject.get("supervisor_code").toString()); // index is not there, need to create index..
				projectQry = new BasicDBObject();
				projectQry.put("first_name", 1);
				projectQry.put("last_name", 1);
				dbObject = userColl.findOne(queryObject, projectQry);
				if(dbObject != null) 
				{
					firstName = dbObject.get("first_name") != null ? dbObject.get("first_name").toString() : "";
					lastName = dbObject.get("last_name") != null ? dbObject.get("last_name").toString() : "";
					outletOrgObject.put("supervisor_name", firstName + " " + lastName);
				}
			}
		}
		catch(Exception exception)
		{
			TLogger.error("[PJPPlannedCallsDump] - Exception :: " + exception.getMessage(), exception);
			throw exception; 
		}
		finally
		{
			queryObject = null;
			projectQry = null;
			dbObject = null;
			firstName = null;
			lastName = null;
//			TLogger.debug("Exit findHierarchyData .. " + outletOrgObject.get("org_id")); // 500 ms its taking..
		}
	}
	
	private BasicDBList findBeatData(DB db, DBObject outletOrgObject) throws Exception
	{
		DBCollection collection = null;
		BasicDBObject queryObject = null;
		BasicDBObject projObject = null;
		BasicDBObject dataObject = null;
		BasicDBObject dbObject = null;
		BasicDBObject startDateRangeObject = null;
		BasicDBObject endDateRangeObject = null;
		BasicDBObject dateRangeObject = null;
		BasicDBObject scheduleObj = null; 
		BasicDBObject schdlrData = null;
		BasicDBObject scheduleModeObj = null; 
		BasicDBObject tempDataObject = null; 
		BasicDBList resultList = null;
		BasicDBList beatScheduleList = null;
		BasicDBList dateConditions = null;
		DBCursor cursor = null;
		Calendar calendar = null;
		Date endDate = null;
		Set<Long> beats = null;
		List<Date> scheduleDates = null;
		Set<Long> statusList = null;
		try
		{
			resultList = new BasicDBList();

			collection = db.getCollection("beat_defn");
			projObject = new BasicDBObject();
			projObject.put("name", 1);
			projObject.put("_id", 1);
			projObject.put("start_dt", 1);
			projObject.put("end_dt", 1);
			projObject.put("crtd_by", 1);
			projObject.put("crtd_dt", 1);
			projObject.put("schedule", 1);
			projObject.put("updtd_dt", 1);
			projObject.put("status", 1);
			queryObject = new BasicDBObject();
			queryObject.put("retailers.org_id", outletOrgObject.get("org_id"));
			statusList = new HashSet<Long>();
			statusList.add(174l);
//			statusList.add(169l);
			queryObject.put("status", new BasicDBObject("$in", statusList));

			endDate = getCurrentMonthEnd();
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, 1);

			dateRangeObject = new BasicDBObject();
			dateRangeObject.append("$gte", Utility.getStartOfTheDay(calendar.getTime()));
			dateRangeObject.append("$lte", Utility.getEndOfTheDay(endDate));
			startDateRangeObject = new BasicDBObject();
			startDateRangeObject.put("start_dt", dateRangeObject);

			dateRangeObject = new BasicDBObject();
			dateRangeObject.append("$gte", Utility.getStartOfTheDay(calendar.getTime()));
			dateRangeObject.append("$lte", Utility.getEndOfTheDay(endDate));
			endDateRangeObject = new BasicDBObject();
			endDateRangeObject.put("end_dt", dateRangeObject);

			dateConditions = new BasicDBList();
			dateConditions.add(startDateRangeObject);
			dateConditions.add(endDateRangeObject);

			queryObject.put("$or", dateConditions);
			//TLogger.debug("queryObject beat_defn :: "+queryObject);
			cursor = collection.find(queryObject, projObject);
			
			while(cursor.hasNext())
			{
				dataObject = new BasicDBObject();
				dataObject.putAll(outletOrgObject);
				dbObject = (BasicDBObject) cursor.next();
				dataObject.put("beat_id", dbObject.get("_id"));
				dataObject.put("beat_name", dbObject.get("name"));
				dataObject.put("created_by", dbObject.get("crtd_by"));

				if(dbObject.get("start_dt") != null)
					dataObject.put("start_date", dd_MM_yyyy.format(dbObject.get("start_dt")));
				
				if(dbObject.get("end_dt") != null)
					dataObject.put("end_date", dd_MM_yyyy.format(dbObject.get("end_dt")));
				
				if(dbObject.get("crtd_dt") != null)
					dataObject.put("creation_date", dd_MM_yyyy.format(dbObject.get("crtd_dt")));
				
				if(dbObject.get("updtd_dt") != null)
					dataObject.put("modify_date", dd_MM_yyyy.format(dbObject.get("updtd_dt")));
				
				dataObject.put("status", statusDesc.get(dbObject.getLong("status")));

				dataObject.put("plan_date", ""); // ?
				dataObject.put("plan_day", ""); // ?
				dataObject.put("frequency", "");//?
				//dataObject.put("data_source", "beat_defn");
				if(dbObject.get("schedule") != null) 
				{
					scheduleObj = (BasicDBObject) dbObject.get("schedule");
					//					TLogger.debug("beat_defn scheduleObj : " + scheduleObj);
					if(scheduleObj != null && scheduleObj.get("schdlr_data") != null) 
					{
						schdlrData = (BasicDBObject) scheduleObj.get("schdlr_data");
						dataObject.put("frequency", schdlrData.get("frequency"));
						//						TLogger.debug("beat_defn  frequency   :::"+schdlrData.get("frequency"));
					}

					scheduleModeObj = new BasicDBObject();
					scheduleModeObj.put("start_dt", dbObject.get("start_dt"));
					scheduleModeObj.put("end_dt", dbObject.get("end_dt"));
					scheduleModeObj.put("schedule", scheduleObj);

					if(scheduleObj!=null && scheduleObj.get("schdlr_mode")!=null) {
						if(DAILY_MODE == scheduleObj.getInt("schdlr_mode")) {
							scheduleDates = processDailyMode(scheduleModeObj);
						}
						else if(WEEKLY_MODE == scheduleObj.getInt("schdlr_mode")) {
							scheduleDates = processWeeklyMode(scheduleModeObj);
						}
						else if(MONTHLY_MODE == scheduleObj.getInt("schdlr_mode")) {
							scheduleDates = processMonthlyMode(scheduleModeObj);
						}
						else if(YEARLY_MODE == scheduleObj.getInt("schdlr_mode")) {
							scheduleDates = processYearlyMode(scheduleModeObj);
						}
						else {
							scheduleDates = null;
						}
					}
					//	TLogger.debug("scheduleDates :: "+scheduleDates);
				}

				if(scheduleDates != null && !scheduleDates.isEmpty()) 
				{
					for (Date scheduleDate : scheduleDates) 
					{
						tempDataObject = new BasicDBObject();
						tempDataObject.putAll(dataObject.toMap());
						tempDataObject.put("plan_date", dd_MM_yyyy.format(scheduleDate)); // ?
						tempDataObject.put("plan_day", dayFormat.format(scheduleDate)); // ?
						//TLogger.debug("beat_defn  beat_id   ::: "+dataObject.get("beat_id")+"    dataObject :::  "+ dataObject);
						resultList.add(tempDataObject);
					}
				}
				else {
					//TLogger.debug("beat_defn  beat_id   ::: "+dataObject.get("beat_id")+"    dataObject :::  "+ dataObject);
					resultList.add(dataObject);
				}
				//				TLogger.debug("beat_defn  beat_id:::   "+dbObject.get("_id")+"       dbObject"+ dataObject);
			}
			cursor.close();

			beats = new HashSet<Long>();
			beatScheduleList = new BasicDBList();
			collection = db.getCollection("beat_schedule");
			queryObject = new BasicDBObject();
			queryObject.put("org_id", outletOrgObject.get("org_id"));
			dateRangeObject = new BasicDBObject();
			dateRangeObject.put("$gte", Long.parseLong(yyyyMMdd.format(getCurrentMonthStart())));
			dateRangeObject.put("$lte", Long.parseLong(yyyyMMdd.format(new Date())));
			queryObject.put("schedule_dt_num", dateRangeObject);
			queryObject.put("status", new BasicDBObject("$ne", 171L));
			projObject = new BasicDBObject();
			projObject.put("beat_id", 1);
			projObject.put("created_dt", 1);
			projObject.put("crtd_by", 1);
			projObject.put("status", 1);
			projObject.put("schedule_dt", 1);
			projObject.put("schedule_dt_num", 1);
			projObject.put("org_id", 1);
			//TLogger.debug("beat_schedule queryObject:: "+queryObject);
			cursor = collection.find(queryObject, projObject);
			while(cursor.hasNext())
			{
				dataObject = new BasicDBObject();
				dataObject.putAll(outletOrgObject);
				dbObject = (BasicDBObject) cursor.next();
				beats.add(dbObject.getLong("beat_id"));
				dataObject.put("beat_id", dbObject.get("beat_id"));
				dataObject.put("creation_date", dd_MM_yyyy.format(dbObject.get("created_dt")));
				dataObject.put("created_by", dbObject.get("crtd_by"));
				dataObject.put("status", "Active");
				dataObject.put("plan_date", dd_MM_yyyy.format(dbObject.get("schedule_dt")));
				dataObject.put("plan_day", dayFormat.format(dbObject.get("schedule_dt")));
				dataObject.put("beat_name", ""); // ?
				dataObject.put("start_date", ""); // ?
				dataObject.put("end_date", ""); // ?
				dataObject.put("frequency", ""); // ?
				dataObject.put("modify_date", ""); // ?
				//dataObject.put("data_source", "beat_schedule");
				beatScheduleList.add(dataObject);
			}
			cursor.close();
			//TLogger.debug("beat_schedule beatScheduleList:: "+beatScheduleList);			
			if(!beatScheduleList.isEmpty())
			{
				collection = db.getCollection("beat_defn");
				projObject = new BasicDBObject();
				projObject.put("name", 1);
				projObject.put("_id", 1);
				projObject.put("start_dt", 1);
				projObject.put("end_dt", 1);
				projObject.put("crtd_by", 1);
				projObject.put("crtd_dt", 1);
				projObject.put("schedule", 1);
				projObject.put("updtd_dt", 1);
				projObject.put("status", 1);
				queryObject = new BasicDBObject();
				queryObject.put("status", new BasicDBObject("$ne", 171L));
				queryObject.put("_id", new BasicDBObject("$in", beats));
				cursor = collection.find(queryObject, projObject);
				
				while(cursor.hasNext())
				{
					dbObject = (BasicDBObject) cursor.next();
					for(Object object : beatScheduleList)
					{
						dataObject = (BasicDBObject) object;
						if(dataObject.getLong("beat_id") == dbObject.getLong("_id"))
						{
							dataObject.put("beat_name", dbObject.get("name"));
							dataObject.put("start_date", dd_MM_yyyy.format(dbObject.get("start_dt")));
							dataObject.put("end_date", dd_MM_yyyy.format(dbObject.get("end_dt")));
							dataObject.put("created_by", dbObject.get("crtd_by"));
							dataObject.put("creation_date", dd_MM_yyyy.format(dbObject.get("crtd_dt")));
							dataObject.put("modify_date", dd_MM_yyyy.format(dbObject.get("updtd_dt")));
//							dataObject.put("status", dbObject.get("status"));
							dataObject.put("status", statusDesc.get(dbObject.getLong("status")));
							if(dbObject.get("schedule") != null)
							{
								scheduleObj = (BasicDBObject) dbObject.get("schedule");
								//TLogger.debug("beat_schedule =>  beat_defn scheduleObj : " + scheduleObj);
								if(scheduleObj != null && scheduleObj.get("schdlr_data") != null) 
								{
									schdlrData = (BasicDBObject) scheduleObj.get("schdlr_data");
									dataObject.put("frequency", schdlrData.get("frequency"));
								}
							}
							//TLogger.debug("beat_schedule  beat_id   ::: "+dbObject.get("_id")+"    dataObject :::  "+ dataObject);
							//TLogger.debug("beat_schedule  beat_id   ::: "+dbObject.get("_id")+"    dbObject :::  "+ dbObject);
							resultList.add(dataObject);
						}
					//	TLogger.debug("beat_schedule  dbObject   ::: "+dbObject);
					}
				}
				cursor.close();
			}
			beatScheduleList.clear();
			beats.clear();
			
			return resultList;
		}
		catch (Exception exception)
		{
			TLogger.error("[PJPPlannedCallsDump] - Exception :: " + exception.getMessage(), exception);
			throw exception; 
		}
		finally
		{
			collection = null;
			queryObject = null;
			projObject = null;
			dataObject = null;
			dbObject = null;
			dateRangeObject = null;
			startDateRangeObject = null;
			endDateRangeObject = null;
			scheduleObj = null; 
			schdlrData = null;
			scheduleModeObj = null; 
			tempDataObject = null; 
			resultList = null;
			beatScheduleList = null;
			dateConditions = null;
			cursor = null;
			calendar = null;
			beats = null;
			scheduleDates = null;
			statusList = null;
		}
	}
	
	private Date getCurrentMonthStart() throws Exception
	{
		Calendar calendar = null;
		try
		{
			calendar = Calendar.getInstance();
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	        return calendar.getTime();
		}
		catch(Exception exception)
		{
			TLogger.error("[PJPPlannedCallsDump] - Exception :: " + exception.getMessage(), exception);
			throw exception; 
		}
		finally
		{
			calendar = null;
		}
	}
	
	private Date getCurrentMonthEnd() throws Exception
	{
		Calendar calendar = null;
		try
		{
			calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			 return calendar.getTime();
		}
		catch(Exception exception)
		{
			TLogger.error("[PJPPlannedCallsDump] - Exception :: " + exception.getMessage(), exception);
			throw exception; 
		}
		finally
		{
			calendar = null;
		}
	}
	
	private  List<Date> processDailyMode(BasicDBObject scheduleObj)
	{

		List<Date> futureDates = null;
		Set<Integer> days = null;
		Date start_date=null;;
		Date end_date=null;
		LocalDate start=null;
		LocalDate end=null;
		BasicDBObject schedule=null;
		BasicDBObject scheduleData=null;
		BasicDBList repeatDays=null;
		Iterator<Object> repeatDaysIterator=null;
		LocalDate firstDay = null;

		try 
		{
			configuredDate = new LocalDate().plusDays(BEAT_SCHEDULE_DUE_IN_DAYS);

			futureDates=new ArrayList<Date>();
			days=new HashSet<Integer>();

			start_date=getDate(scheduleObj.getString("start_dt"));
			end_date=getDate(scheduleObj.getString("end_dt"));
			start=LocalDate.fromDateFields(start_date);
			end=LocalDate.fromDateFields(end_date);

			schedule=(BasicDBObject) JSON.parse(scheduleObj.getString("schedule")) ;
			scheduleData=(BasicDBObject) schedule.get("schdlr_data");
			int daily_mode=scheduleData.getInt("daily_mode");
			if(daily_mode==F_DAILY_BY_FRQ)
			{
				int frequency=scheduleData.getInt("frequency");
				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusDays(frequency)) 
				{
					if(!date.isBefore(start) && !date.isAfter(end))
					{
						futureDates.add(date.toDate());
					}
				}
				//TLogger.debug("daily_mode==>1::"+futureDates);
			}
			else if (daily_mode==F_DAILY_BY_DAY) 
			{
				repeatDays=(BasicDBList) scheduleData.get("repeat_every_days");
				repeatDaysIterator=repeatDays.iterator();
				while(repeatDaysIterator.hasNext())
				{
					days.add((Integer)repeatDaysIterator.next());
				}

				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusDays(1)) 
				{
					for(Iterator<Integer> daysIterator=days.iterator();daysIterator.hasNext();)
					{	
						int dayOfWeek=daysIterator.next();
						if(date.getDayOfWeek()==dayOfWeek)
						{
							firstDay = date.withDayOfWeek(dayOfWeek);
							if(!firstDay.isBefore(start) && !firstDay.isAfter(end))
							{
								futureDates.add(firstDay.toDate());
							}
						}	
					}	
				}
				//TLogger.debug("daily_mode==>2::"+futureDates);
			}
		}
		catch (ParseException e) {
			TLogger.error("ParseException occured in :["+this.getClass().getName()+"] function:processDailyMode"+e.getMessage(),e);
		} catch (Exception e) {
			TLogger.error("Exception occured in :["+this.getClass().getName()+"] function:processDailyMode"+e.getMessage(),e);
		}
		finally {
			days = null;
			start_date=null;;
			end_date=null;
			start=null;
			end=null;
			schedule=null;
			scheduleData=null;
			repeatDays=null;
			repeatDaysIterator=null;
			firstDay = null;

		}
		return futureDates;
	}

	private List<Date> processWeeklyMode(BasicDBObject scheduleObj)
	{
		List<Date> futureDates = null;
		Set<Integer> weeks= null;
		Set<Integer> days= null;
		Date start_date= null;
		Date end_date= null;
		LocalDate start= null;
		LocalDate end= null;
		BasicDBObject schedule= null;
		BasicDBObject scheduleData= null;
		BasicDBList repeatDays= null;
		Iterator<Object> repeatDaysIterator= null;
		BasicDBList repeatWeeks=null;
		Iterator<Object> repeatWeeksIterator=null;
		LocalDate firstDay = null;
		try 
		{
			configuredDate = new LocalDate().plusDays(BEAT_SCHEDULE_DUE_IN_DAYS);

			futureDates=new ArrayList<Date>();
			weeks=new HashSet<Integer>();
			days=new HashSet<Integer>();

			start_date=getDate(scheduleObj.getString("start_dt"));
			end_date=getDate(scheduleObj.getString("end_dt"));

			start=LocalDate.fromDateFields(start_date);
			end=LocalDate.fromDateFields(end_date);

			schedule=(BasicDBObject) JSON.parse(scheduleObj.getString("schedule")) ;
			scheduleData=(BasicDBObject) schedule.get("schdlr_data");
			int weekly_mode=scheduleData.getInt("weekly_mode");
			if(weekly_mode==F_WEEKLY_BY_FRQ)
			{
				int weekFrequency=scheduleData.getInt("frequency");
				repeatDays=(BasicDBList) scheduleData.get("repeat_every_days");
				repeatDaysIterator=repeatDays.iterator();
				while(repeatDaysIterator.hasNext())
				{
					days.add((Integer)repeatDaysIterator.next());
				}

				//				LocalDate date=start;
				for (LocalDate date = start; !date.isAfter(configuredDate); ) 
				{
					date = date.withDayOfWeek(DateTimeConstants.MONDAY);

					for (Iterator<Integer> daysitIterator = days.iterator(); daysitIterator.hasNext();) 
					{
						int dayOfWeek = daysitIterator.next(); 	
						/*if(start.getDayOfWeek()==dayOfWeek)futureDates.add(start.toDate()); 
						if(end.getDayOfWeek() == dayOfWeek)futureDates.add(end.toDate()); */
						LocalDate ld = date.withDayOfWeek(dayOfWeek);
						if ((ld.getDayOfWeek() == dayOfWeek) && (!ld.isBefore(start) && !ld.isAfter(end))) 
						{
							futureDates.add(ld.toDate());
						}
					}
					date = date.plusWeeks(weekFrequency);

				}

				//TLogger.debug("weekly_mode==>1"+futureDates);
			}
			else if(weekly_mode==F_WEEKLY_BY_WEEK_NUMBER)
			{
				repeatWeeks=(BasicDBList) scheduleData.get("repeat_every_weeks");
				repeatWeeksIterator=repeatWeeks.iterator();
				while(repeatWeeksIterator.hasNext())
				{
					weeks.add((Integer)repeatWeeksIterator.next());
				}
				repeatDays=(BasicDBList) scheduleData.get("repeat_every_days");
				repeatDaysIterator=repeatDays.iterator();
				while(repeatDaysIterator.hasNext())
				{
					days.add((Integer)repeatDaysIterator.next());
				}

				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusYears(1)) 
				{
					for(Iterator<Integer> weeksIterator=weeks.iterator();weeksIterator.hasNext();)
					{
						int weekOfYear=weeksIterator.next();
						for(Iterator<Integer> daysitIterator=days.iterator();daysitIterator.hasNext();)
						{
							firstDay = date.withWeekOfWeekyear(weekOfYear).withDayOfWeek(daysitIterator.next());
							if(!firstDay.isBefore(start) && !firstDay.isAfter(end))
							{
								futureDates.add(firstDay.toDate());
							}
						}
					}
				}
				//TLogger.debug("weekly_mode==>2"+futureDates);
			}
		}
		catch (ParseException e) {
			TLogger.error("ParseException occured in :["+this.getClass().getName()+"] function:processWeeklyMode"+e.getMessage(),e);
		} catch (Exception e) {
			TLogger.error("Exception occured in :["+this.getClass().getName()+"] function:processWeeklyMode"+e.getMessage(),e);
		}
		finally {
			weeks= null;
			days= null;
			start_date= null;
			end_date= null;
			start= null;
			end= null;
			schedule= null;
			scheduleData= null;
			repeatDays= null;
			repeatDaysIterator= null;
			repeatWeeks=null;
			repeatWeeksIterator=null;
			firstDay = null;
		}
		return futureDates;
	}

	private List<Date> processYearlyMode(BasicDBObject scheduleObj)
	{
		List<Date> futureDates = null;

		Date start_date=null;
		Date end_date=null;
		LocalDate start=null;
		LocalDate end=null;
		LocalDate firstDay = null;
		BasicDBObject schedule=null;
		BasicDBObject scheduleData=null;
		try 
		{
			futureDates=new ArrayList<Date>();

			start_date=getDate(scheduleObj.getString("start_dt"));
			end_date=getDate(scheduleObj.getString("end_dt"));
			start=LocalDate.fromDateFields(start_date);
			end=LocalDate.fromDateFields(end_date);
			schedule=(BasicDBObject) JSON.parse(scheduleObj.getString("schedule")) ;
			scheduleData=(BasicDBObject) schedule.get("schdlr_data");
			int frequency=scheduleData.getInt("frequency");
			int month=scheduleData.getInt("month");
			int reqDate=scheduleData.getInt("date");
			for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusYears(frequency)) 
			{
				firstDay = date.withMonthOfYear(month).withDayOfMonth(reqDate).withYear(date.getYear());
				if(!firstDay.isBefore(start) && !firstDay.isAfter(end))
				{
					futureDates.add(firstDay.toDate());
				}
			}
			//TLogger.debug("processYearlyMode::"+futureDates);
		}
		catch (ParseException e) {
			TLogger.error("ParseException occured in :["+this.getClass().getName()+"] function:processYearlyMode"+e.getMessage(),e);
		} catch (Exception e) {
			TLogger.error("Exception occured in :["+this.getClass().getName()+"] function:processYearlyMode"+e.getMessage(),e);
		}
		finally {
			start_date=null;
			end_date=null;
			start=null;
			end=null;
			firstDay = null;
			schedule=null;
			scheduleData=null;
		}
		return futureDates;
	}

	private List<Date> processMonthlyMode(BasicDBObject scheduleObj)
	{
		List<Date> futureDates = null;
		Set<Integer> months = null;
		Set<Integer> monthDates = null;
		Date start_date = null;
		Date end_date = null;
		LocalDate start = null;
		LocalDate end = null;
		BasicDBObject schedule = null;
		BasicDBObject scheduleData = null;
		BasicDBList repeatDates = null;
		BasicDBList repeatEveryMonths = null;
		BasicDBList repeatDays= null;
		Iterator<Object> repeatDatesIterator = null;
		LocalDate firstDay = null;
		Iterator<Object> repeatEveryMonthsIterator = null;
		List<Integer> daysOfMonths = null;
		Map<Integer,List<Integer>> ex = null;
		Iterator<Object> repeatDaysIterator=null;
		try 
		{
			configuredDate = new LocalDate().plusDays(BEAT_SCHEDULE_DUE_IN_DAYS);
			futureDates=new ArrayList<Date>();
			months=new HashSet<Integer>();
			monthDates=new HashSet<Integer>();

			start_date=getDate(scheduleObj.getString("start_dt"));
			end_date=getDate(scheduleObj.getString("end_dt"));
			start=LocalDate.fromDateFields(start_date);
			end=LocalDate.fromDateFields(end_date);

			schedule=(BasicDBObject) JSON.parse(scheduleObj.getString("schedule")) ;
			scheduleData=(BasicDBObject) schedule.get("schdlr_data");
			int monthly_mode=scheduleData.getInt("monthly_mode");
			int monthlySubMode=scheduleData.getInt("monthly_sub_mode");
			if(monthly_mode==F_MONTHLY_BY_DATE_BY_FRQ && monthlySubMode==F_MONTHLY_BY_FRQ)
			{
				int frequency=scheduleData.getInt("frequency");
				repeatDates=(BasicDBList) scheduleData.get("repeat_days");
				repeatDatesIterator=repeatDates.iterator();
				while(repeatDatesIterator.hasNext())
				{
					monthDates.add((Integer)repeatDatesIterator.next());
				}
				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusMonths(frequency)) 
				{
					for(Iterator<Integer> monthDatesIterator=monthDates.iterator();monthDatesIterator.hasNext();)
					{	
						firstDay = date.withDayOfMonth(monthDatesIterator.next());
						if(!firstDay.isBefore(start) && !firstDay.isAfter(end))
						{
							futureDates.add(firstDay.toDate());
						}
					}	
				}
				//TLogger.debug("monthly_mode==1 && monthlySubMode==1::"+futureDates);

			}
			else if(monthly_mode==F_MONTHLY_BY_DATE_BY_MONTH && monthlySubMode==F_MONTHLY_BY_MONTH)
			{
				repeatEveryMonths=(BasicDBList) scheduleData.get("repeat_every_months");
				repeatEveryMonthsIterator=repeatEveryMonths.iterator();
				while(repeatEveryMonthsIterator.hasNext())
				{
					months.add((Integer)repeatEveryMonthsIterator.next());
				}
				repeatDates=(BasicDBList) scheduleData.get("repeat_days");
				repeatDatesIterator=repeatDates.iterator();
				while(repeatDatesIterator.hasNext())
				{
					monthDates.add((Integer)repeatDatesIterator.next());
				}

				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusYears(1)) 
				{
					for(Iterator<Integer> monthsIterator=months.iterator();monthsIterator.hasNext();)
					{
						int month=monthsIterator.next();
						for(Iterator<Integer> monthDatesIterator=monthDates.iterator();monthDatesIterator.hasNext();)
						{	
							firstDay = date.withMonthOfYear(month).withDayOfMonth(monthDatesIterator.next());
							if(!firstDay.isBefore(start) && !firstDay.isAfter(end))
							{
								futureDates.add(firstDay.toDate());
							}
						}	
					}		
				}
				//TLogger.debug("monthly_mode==1 && monthlySubMode==2::"+futureDates);
			}
			else if(monthly_mode==F_MONTHLY_BY_DAY_BY_FRQ && monthlySubMode==F_MONTHLY_BY_FRQ)
			{
				repeatDays=(BasicDBList) scheduleData.get("repeat_days");
				int frequency=scheduleData.getInt("frequency");
				ex = new HashMap<Integer, List<Integer>>();
				repeatDaysIterator=repeatDays.iterator();
				while(repeatDaysIterator.hasNext())
				{
					BasicDBObject  repeatDay=(BasicDBObject) repeatDaysIterator.next();
					if(!ex.isEmpty() && ex.containsKey(repeatDay.getInt("row"))){
						List<Integer> dw = ex.get(repeatDay.getInt("row"));
						dw.add(repeatDay.getInt("col"));
					}
					else{
						List<Integer> dw = new ArrayList<Integer>();
						dw.add(repeatDay.getInt("col"));
						ex.put(repeatDay.getInt("row"), dw);
					}
				}

				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusYears(1)) 
				{
					for(int month=start.getMonthOfYear();month<=TOTAL_NO_OF_MONTHS;month+=frequency)
					{
						for(Iterator<Integer> repeatDaysItr=ex.keySet().iterator();repeatDaysItr.hasNext();)
						{	
							int weekOfMonth=repeatDaysItr.next();
							daysOfMonths=ex.get(weekOfMonth);
							for(Iterator<Integer> dayOfMon=daysOfMonths.iterator();dayOfMon.hasNext();)
							{	
								firstDay = nthWeekdayOfMonth(dayOfMon.next(),month,date.getYear(),weekOfMonth,date.getDayOfMonth());
								if(!firstDay.isBefore(start) && !firstDay.isAfter(end))
								{
									futureDates.add(firstDay.toDate());
								}
							}	
						}	
					}		
				}
				//TLogger.debug("monthly_mode==2 && monthlySubMode==1::"+futureDates);
			}

			else if(monthly_mode==F_MONTHLY_BY_DAY_BY_MONTH && monthlySubMode==F_MONTHLY_BY_MONTH)
			{
				repeatDays=(BasicDBList) scheduleData.get("repeat_days");
				repeatEveryMonths=(BasicDBList) scheduleData.get("repeat_every_months");
				ex = new HashMap<Integer, List<Integer>>();
				repeatDaysIterator=repeatDays.iterator();
				while(repeatDaysIterator.hasNext())
				{
					BasicDBObject  repeatDay=(BasicDBObject) repeatDaysIterator.next();
					if(!ex.isEmpty() && ex.containsKey(repeatDay.getInt("row"))){
						List<Integer> dw = ex.get(repeatDay.getInt("row"));
						dw.add(repeatDay.getInt("col"));
					}
					else{
						List<Integer> dw = new ArrayList<Integer>();
						dw.add(repeatDay.getInt("col"));
						ex.put(repeatDay.getInt("row"), dw);
					}
				}


				for (LocalDate date = start; !date.isAfter(configuredDate); date = date.plusYears(1)) 
				{
					for(int repeatEveryMonthsSize=0;repeatEveryMonthsSize<repeatEveryMonths.size();repeatEveryMonthsSize++)
					{
						for(Iterator<Integer> repeatDaysItr=ex.keySet().iterator();repeatDaysItr.hasNext();)
						{	
							int weekOfMonth=(Integer) repeatDaysItr.next();
							daysOfMonths=ex.get(weekOfMonth);
							for(Iterator<Integer> dayOfMon=daysOfMonths.iterator();dayOfMon.hasNext();)
							{	
								firstDay = nthWeekdayOfMonth(dayOfMon.next(),(Integer)repeatEveryMonths.get(repeatEveryMonthsSize),date.getYear(),weekOfMonth,date.getDayOfMonth());
								if(!firstDay.isBefore(start) && !firstDay.isAfter(end)) 
								{
									futureDates.add(firstDay.toDate());
								}
							}	
						}	
					}		
				}
				//TLogger.debug("monthly_mode==2 && monthlySubMode==2::"+futureDates);
			}
		}
		catch (ParseException e) {
			TLogger.error("ParseException occured in :["+this.getClass().getName()+"] function:processYearlyMode"+e.getMessage(),e);
		} catch (Exception e) {
			TLogger.error("Exception occured in :["+this.getClass().getName()+"] function:processYearlyMode"+e.getMessage(),e);
		}
		finally {
			months = null;
			monthDates = null;
			start_date = null;
			end_date = null;
			start = null;
			end = null;
			schedule = null;
			scheduleData = null;
			repeatDates = null;
			repeatEveryMonths = null;
			repeatDays= null;
			repeatDatesIterator = null;
			firstDay = null;
			repeatEveryMonthsIterator = null;
			daysOfMonths = null;
			ex = null;
			repeatDaysIterator=null;

		}
		return futureDates;
	}

	/*
	 * @param dayOfWeek
	 *            The day of the week to calculate the day for (In the range of [1,7], where 1 is Monday.
	 * @param month
	 *            The month to calculate the day for.
	 * @param year
	 *            The year to calculate the day for.
	 * @param n
	 *            The occurrence of the weekday to calculate. (ie. 1st, 2nd,3rd)
	 *            
	 * @return A date with the nth occurrence of the day of week,for the given month and year
	 */
	private LocalDate nthWeekdayOfMonth(int dayOfWeek, int month, int year, int n,int dayOfMonth) {
		LocalDate start = new LocalDate(year, month, 1);
		LocalDate date = start.withDayOfWeek(dayOfWeek);
		return (date.isBefore(start)) ? date.plusWeeks(n) : date.plusWeeks(n - 1);
	}

	/**
	 *  
	 *  Expecting Formats:
	 * 1. Java.Util.Date Object
	 * 2. UTC Format: "2014-09-14T07:10:32.897Z"-------["yyyy-MM-dd'T'kk:mm:ss.SSS'Z'"] 
	 * 3. Java.Util.Date Object In string Format: "Mon Oct 06 16:34:04 IST 2014"--------["E MMM dd kk:mm:ss Z yyyy"]
	 * 
	 * @return Java.Util.Date or null
	 */
	private Date getDate(Object  object) throws Exception{
		//TLogger.debug("getDate:: Parsing Object : "+object);
		Date date = null;
		try{
			if(object != null && !object.toString().trim().isEmpty()){
				try{
					date  = new DateTime(object, DateTimeZone.UTC).toDate();
				}catch(IllegalArgumentException argumentException){
					date = new SimpleDateFormat("E MMM dd kk:mm:ss Z yyyy").parse(object.toString());
				}
			}
		}catch(Exception exception){
			TLogger.error("getDate:: Exception is: >>"+exception.getMessage());
			throw exception;
		}
		//TLogger.debug("getDate:: Returning Date Object : "+date);
		return date;
	}

	private Map<Long, String> statusDesc = new HashMap<Long, String>();
	private SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat dd_MM_yyyy = new SimpleDateFormat("dd-MM-yyyy");
	private LocalDate configuredDate = null;

	private final int BEAT_SCHEDULE_DUE_IN_DAYS = 30;
	//Daily Modes
	private final int F_DAILY_BY_FRQ = 1;
	private final int F_DAILY_BY_DAY = 2;
	//Weekly Modes
	public final int F_WEEKLY_BY_FRQ = 1;
	public final int F_WEEKLY_BY_WEEK_NUMBER = 2;
	//Monthly Modes
	private final int F_MONTHLY_BY_FRQ = 1;
	private final int F_MONTHLY_BY_MONTH = 2;
	private final int F_MONTHLY_BY_DATE_BY_FRQ = 1;
	private final int F_MONTHLY_BY_DATE_BY_MONTH = 1;
	private final int F_MONTHLY_BY_DAY_BY_FRQ = 2;
	private final int F_MONTHLY_BY_DAY_BY_MONTH = 2;
	
	private final int TOTAL_NO_OF_MONTHS=12;
	
	private final int DAILY_MODE = 1;
	private final int WEEKLY_MODE = 2;
	private final int MONTHLY_MODE = 3;
	private final int YEARLY_MODE = 4;
	
}
