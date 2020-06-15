package com.enhancesys.interfaces.snoc.services;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.enhancesys.interfaces.snoc.common.QueryConstants;
import com.enhancesys.interfaces.snoc.util.IntegrationServiceException;
import com.enhancesys.interfaces.snoc.util.InterfaceServiceThread;
import com.enhancesys.interfaces.snoc.util.PropertiesFileLoaderClass;
import com.enhancesys.interfaces.snoc.util.TokuUser;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Component
public class InterfaceServicesDeligator 
{
	private static Logger LOGGER = Logger.getLogger(InterfaceServicesDeligator.class);

	@Autowired
	NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public InterfaceServicesDAO interfaceServicesDAO;
	
	@Autowired
	private InterfaceServicesDownloadProcessor downloadProcessor;
	
	@Autowired
	private InterfaceServicesUploadProcessor uploadProcessor;
	
	@SuppressWarnings("unchecked")
	public BasicDBList getInterfaces(BasicDBObject requestObject) throws Exception 
	{
		BasicDBList responseObj = new BasicDBList();	
		String property = null;
		List<String> interfacesList = null;
		String sql = null;
		HashMap<String, Object> summaryDtlsMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		BasicDBObject interfaces = null;
		try
		{
			interfacesList = new ArrayList<>();
			property = PropertiesFileLoaderClass.getValueAsString("INTERFACE_FILTERATION_BY_ORG_TYPE_" + Long.parseLong(requestObject.get("org_type").toString()));
			if(property != null && !property.contains("INTERFACE_FILTERATION_BY_ORG_TYPE_"))
			{
				interfacesList =  Arrays.asList(property.split(","));
			}
			else 
			{
				return responseObj;
			}
			sql = QueryConstants.Q_GET_INTERFACE_BY_INTERFACE_TYPE;
			
			summaryDtlsMap = new HashMap<>();
			summaryDtlsMap.put(Constants.QF_INTERFACE_TYPE_ID, Long.parseLong(requestObject.get(Constants.F_INTERFACE_TYPE).toString()));
			
			result = namedParamJdbcTemplate.queryForList(sql, summaryDtlsMap);
			if(result != null && !result.isEmpty())
			{
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					interfaces = new BasicDBObject();
					if(!interfacesList.isEmpty() && interfacesList.contains(String.valueOf(row.get(Constants.F_INTERFACE_ID_NUMBER))))
					{
						interfaces.append(Constants.F_ID, Long.parseLong(row.get(Constants.F_INTERFACE_ID_NUMBER).toString()));
						interfaces.append(Constants.F_NAME, row.get(Constants.F_NAME_VALUE).toString());
						
						if(row.get(Constants.F_TRANS_TYPE_NUMBER).toString().equalsIgnoreCase(PropertiesFileLoaderClass.getValueAsString("RECEIVE_TRANS_TYPE").trim()))
							interfaces.append(Constants.F_UPLOAD, true);
						else
							interfaces.append(Constants.F_UPLOAD, false);
						
						responseObj.add(interfaces);
					}
					else
						continue;
				}
			}	
			return responseObj;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaces]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaces]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			responseObj = new BasicDBList();	
			property = null;
			interfacesList = null;
			sql = null;
			summaryDtlsMap = null;
			result = null;
			row = null;
			interfaces = null;
		}
	}

	@SuppressWarnings("unchecked")
	public Long getInterfaceIdByModuleAndInterfaceType(Long module,Long type) throws Exception 
	{
		Long response = 0l;	
		String sql = null;
		HashMap<String, Object> summaryDtlsMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		try
		{
			sql = QueryConstants.Q_GET_INTERFACE_ID_BY_MODULE_AND_TYPE;
			
			summaryDtlsMap = new HashMap<>();
			summaryDtlsMap.put(Constants.QF_MODULE_ID, module);
			summaryDtlsMap.put(Constants.QF_TRANS_TYPE_ID, type);
			
			result = namedParamJdbcTemplate.queryForList(sql, summaryDtlsMap);
			if(result!=null && !result.isEmpty())
			{
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					response =  Long.parseLong(row.get(Constants.F_INTERFACE_ID_NUMBER).toString());
				}
			}	
			return response;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceIdByModuleAndInterfaceType]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceIdByModuleAndInterfaceType]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			response = null;	
			sql = null;
			summaryDtlsMap = null;
			result = null;
			row = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBObject getInterfaceModuleById(BasicDBObject requestObject) throws Exception 
	{
		BasicDBObject responseObj = null;	
		String sql = null;
		HashMap<String, Object> summaryDtlsMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null; 
				
		try
		{
			sql = QueryConstants.Q_GET_MODELUE_INTERFACE_BY_ID;
			
			summaryDtlsMap = new HashMap<>();
			summaryDtlsMap.put(Constants.QF_MODULE_ID, Long.parseLong(requestObject.get(Constants.F_MODULE_ID).toString()));
			
			responseObj = new BasicDBObject();
			result = namedParamJdbcTemplate.queryForList(sql, summaryDtlsMap);
			if(result!=null && !result.isEmpty())
			{
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					responseObj.append(Constants.F_INTERFACE_ID, Long.parseLong(row.get(Constants.F_INTERFACE_ID_NUMBER).toString()));
					responseObj.append(Constants.F_INTERFACE_TYPE, Long.parseLong(row.get(Constants.F_INTERFACE_TYPE_NUMBER).toString()));
					responseObj.append(Constants.F_MODULE_ID, Long.parseLong(row.get(Constants.F_MODULE_ID_NUMBER).toString()));
					responseObj.append(Constants.F_NAME, row.get(Constants.F_NAME_VALUE).toString());
				}
			}	
			return responseObj;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceModuleById]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfcaeModuleById]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			responseObj = null;	
			sql = null;
			summaryDtlsMap = null;
			result = null;
			row = null; 
		}
	}

	/**<pre>This method find out Interface File Details with using interface id from database</pre>
	 * <p>requestObject have file id the data get by file id in db otherwise using Interface id data will be get </p>
	 * <p>If requestObject have start_date and end_date,
	 * filename and status id --> Create query with interface_id and available fields</p>
	 * <p>Else --> Create query with only interface_id</p>
	 * @param  requestObject
	 * @return responseObj
	 * @throws Exception
	 */
	public BasicDBList getInterfaceFileDetails(BasicDBObject requestObject) throws Exception 
	{
		Connection connection = null;
		PreparedStatement queryStatement = null;
		ResultSet result = null;
		String sql = null;
		BasicDBObject fileSummaries = null;
		BasicDBList responseObj = null;
		
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			responseObj = new BasicDBList();
			sql = QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS;

			if(requestObject.containsField(Constants.F_FILE_ID))
			{
				sql = sql +  QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_FILE_ID;
				sql = sql +  QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_ORDER_BY_FILE_ID;
				queryStatement = connection.prepareStatement(sql);
				queryStatement.setLong(1, Long.parseLong(requestObject.get(Constants.F_INTERFACE_ID).toString()));
				queryStatement.setLong(2, Long.parseLong(requestObject.get(Constants.F_FILE_ID).toString()));					
			}
			else
			{ 
				boolean dates = false;
				boolean filename = false;
				boolean status = false;
				String start_date = null;
				String end_date = null;
				//Fetching Query
				if((requestObject.containsField(Constants.F_START_DATE)) && (requestObject.containsField(Constants.F_END_DATE)))
				{
					start_date = requestObject.get(Constants.F_START_DATE).toString();
					end_date   = requestObject.get(Constants.F_END_DATE).toString();
//					Processed Date need to be considered for Outgoing feeds date search in offline interfaces screen..
					sql = sql + QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_DATE;
					dates = true;
				} 
				if(requestObject.containsField(Constants.F_FILE_NAME))
				{
					sql = sql + QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_FILE_NAME;
					filename = true;
				}
				if(requestObject.containsField(Constants.F_STATUS))
				{
					sql = sql + QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_STATUS_NAME;
					status = true;
				}

				sql = sql +  QueryConstants.Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_ORDER_BY_FILE_ID;
				queryStatement = connection.prepareStatement(sql);
				queryStatement.setLong(1, Long.parseLong(requestObject.get(Constants.F_INTERFACE_ID).toString()));
				//preparing query
				if(dates && filename && status)
				{
					queryStatement.setString(2, start_date);
					queryStatement.setString(3, end_date);
					queryStatement.setString(4, start_date);
					queryStatement.setString(5, end_date);
					queryStatement.setString(6, requestObject.getString(Constants.F_FILE_NAME));
					queryStatement.setString(7, requestObject.get(Constants.F_STATUS).toString());
				}
				else if(dates && filename)
				{
					queryStatement.setString(2, start_date);
					queryStatement.setString(3, end_date);
					queryStatement.setString(4, start_date);
					queryStatement.setString(5, end_date);
					queryStatement.setString(6, requestObject.getString(Constants.F_FILE_NAME));
				}
				else if(dates && status)
				{
					queryStatement.setString(2, start_date);
					queryStatement.setString(3, end_date);
					queryStatement.setString(4, start_date);
					queryStatement.setString(5, end_date);
					queryStatement.setString(6, requestObject.get(Constants.F_STATUS).toString());

				}
				else if(filename && status)
				{
					queryStatement.setString(2, requestObject.getString(Constants.F_FILE_NAME).toLowerCase());
					queryStatement.setString(3, requestObject.get(Constants.F_STATUS).toString());

				}
				else if(dates)
				{
					queryStatement.setString(2, start_date);
					queryStatement.setString(3, end_date);
					queryStatement.setString(4, start_date);
					queryStatement.setString(5, end_date);
				}
				else if (filename)
				{
					queryStatement.setString(2, requestObject.getString(Constants.F_FILE_NAME));
				}
				else if (status)
				{
					queryStatement.setString(2, requestObject.get(Constants.F_STATUS).toString());
				}
			}
			result = queryStatement.executeQuery();
			while(result.next())
			{
				fileSummaries = new BasicDBObject();
				fileSummaries.append(Constants.F_ID, result.getObject(Constants.F_FILE_ID_NUMBER).toString());

				if((result.getObject(Constants.F_STATUS_NUMBER).toString().equals(Constants.ST_FILE_REJECTED)))
					fileSummaries.append(Constants.F_MESSAGE, result.getObject(Constants.F_MESSAGE_VALUE).toString());
				else
					fileSummaries.append(Constants.F_MESSAGE, "");

				fileSummaries.append(Constants.F_TOTAL_COUNT, result.getObject(Constants.F_TOTAL_COUNT_NUMBER).toString());
				fileSummaries.append(Constants.F_FILE_NAME, result.getString(Constants.F_FILE_NAME_VALUE).toString());
				fileSummaries.append(Constants.F_ERROR_COUNT, result.getObject(Constants.F_ERROR_COUNT_NUMBER).toString());
				fileSummaries.append(Constants.F_SUCCESS_COUNT, result.getObject(Constants.F_SUCCESS_COUNT_NUMBER).toString());
				fileSummaries.append(Constants.F_UPLOADED_BY, (result.getObject(Constants.F_UPLOADED_BY_NUMBER) != null) ? result.getObject(Constants.F_UPLOADED_BY_NUMBER).toString() : "");
				fileSummaries.append(Constants.F_PROCESSED_ON, result.getObject(Constants.F_PROCESSED_ON_DATE) != null ? result.getObject(Constants.F_PROCESSED_ON_DATE).toString() : "");
				fileSummaries.append(Constants.F_UPLOADED_ON, result.getObject(Constants.F_UPLOADED_ON_DATE) != null ? result.getObject(Constants.F_UPLOADED_ON_DATE).toString() : "");
				fileSummaries.append(Constants.F_STATUS, result.getObject(Constants.F_NAME_VALUE) != null ? result.getObject(Constants.F_NAME_VALUE).toString() : "");
				fileSummaries.append(Constants.F_FILTER_COUNT, result.getObject(Constants.F_FILTER_COUNT_NUMBER) != null ? result.getObject(Constants.F_FILTER_COUNT_NUMBER).toString() : "");
				responseObj.add(fileSummaries);
			}	
			result.close();
			queryStatement.close();

			return responseObj;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileDetails]:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileDetails]:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
		finally
		{
			if(connection != null)
				connection.close();
			if(result != null)
				result.close();
			if(queryStatement != null)
				queryStatement.close();
			connection = null;
			queryStatement = null;
			result = null;
			responseObj = null;
			fileSummaries = null;
			sql = null;
		}
	}
	
	/*public String getStatusName(Long statusId)
	{
		String name = "";
		try
		{
			Status Map
			HashMap<String,Long> statusMap = new HashMap<String,Long>();
			statusMap.put(Constants.QF_STATUS_ID, statusId);
			
			String sql = QueryConstants.Q_GET_STATUS_NAME;
			
			String statusName = namedParamJdbcTemplate.queryForObject(sql, statusMap, String.class);
			if(statusName != null && !statusName.isEmpty())
				name = statusName;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getStatusName]:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getStatusName]:: Exception Message is: >> "+exception.getMessage());
			
		}
		return name;
	}*/
	
	/*public Object getInterfaceAttributeFiledData(Long interfaceId,String key,Object keyValue,String value)
	{
		Object name = "";
		String sqlExtension = "";
		try
		{
			if(keyValue instanceof String)
			{
				sqlExtension = sqlExtension+key.toUpperCase()+"="+"'"+keyValue.toString()+"'";
			}
			else if(keyValue instanceof Long)
			{
				sqlExtension = sqlExtension+key.toUpperCase()+"="+Long.parseLong(keyValue.toString());
			}
			
			attribute Map
			HashMap<String,Long> attributeMap = new HashMap<String,Long>();
			attributeMap.put(Constants.QF_INTERFACE_ID, interfaceId);
			
			String sql = QueryConstants.Q_GET_INTERFACE_ATTRIBUTE_VALUE_BY_KEY;
			
			sql = sql+ sqlExtension;
			
			Object attributeData = namedParamJdbcTemplate.queryForObject(sql, attributeMap, Object.class);
			if(attributeData!=null && ((BasicDBObject)attributeData).containsField(value))
			{
				name = ((BasicDBObject)attributeData).get(value);
			}
			
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceAttributeFiledData]:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceAttributeFiledData]:: Exception Message is: >> "+exception.getMessage());
			
		}
		return name;
	}*/
	
	@SuppressWarnings("unchecked")
	public BasicDBList getInterfaceFileSummary(BasicDBObject requestObject) throws Exception
	{
		BasicDBList responseObj = null;
		String sql = null;
		HashMap<String,Long> summaryDtlsMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		String status = null;
		BasicDBObject modules = null;
		Map<String,Object> summaryMap = null;
		BasicDBObject serverData = null;
		List<String> pullMongoInterfaceList = null;
		boolean rejectedPullMongoFile = false;
		try
		{
			responseObj = new BasicDBList();
			pullMongoInterfaceList = Arrays.asList((PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_INTERFACE_LIST_STR").split(",")));
			sql = QueryConstants.Q_GET_TR_FILE_SUMMARY_DETAILS;
			
			summaryDtlsMap = new HashMap<String,Long>();
			summaryDtlsMap.put(Constants.QF_FILE_ID, Long.parseLong(requestObject.get(Constants.F_FILE_ID).toString()));
			
			result = namedParamJdbcTemplate.queryForList(sql, summaryDtlsMap);
			if(result != null && !result.isEmpty())
			{
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					status = "0";
					modules = new BasicDBObject();
					modules.append(Constants.F_ID, row.get(Constants.F_FILE_DETAILS_ID_NUMBER) != null ? row.get(Constants.F_FILE_DETAILS_ID_NUMBER).toString() : "");
					modules.append(Constants.F_ACTUAL_FILE_NAME, row.get(Constants.F_FILE_NAME_VALUE) != null ? row.get(Constants.F_FILE_NAME_VALUE).toString() : "");
					modules.append(Constants.F_CONTROL_FILE_NAME, row.get(Constants.F_CONTROL_FILE_NAME_VALUE) != null ? row.get(Constants.F_CONTROL_FILE_NAME_VALUE).toString() : "");
					modules.append(Constants.F_TOTAL_COUNT, row.get(Constants.F_TOTAL_COUNT_NUMBER) != null ? row.get(Constants.F_TOTAL_COUNT_NUMBER).toString() : "0");
					modules.append(Constants.F_SUCCESS_COUNT, row.get(Constants.F_SUCCESS_COUNT_NUMBER) != null ? row.get(Constants.F_SUCCESS_COUNT_NUMBER).toString() : "0");
					modules.append(Constants.F_ERROR_COUNT, row.get(Constants.F_ERROR_COUNT_NUMBER) != null ? row.get(Constants.F_ERROR_COUNT_NUMBER).toString() : "0");
					modules.append(Constants.F_FILE_TYPE, row.get(Constants.F_FILE_TYPE_VALUE) != null ? row.get(Constants.F_FILE_TYPE_VALUE).toString() : "");	
					modules.append(Constants.F_DESTINATION_DIRECTION, "");
					summaryMap = getSummaryByFileId(Long.parseLong(requestObject.get(Constants.F_FILE_ID).toString()));
					if(!summaryMap.isEmpty())
					{
						serverData = (BasicDBObject) JSON.parse(summaryMap.get(Constants.F_LOCAL_SERVER_VALUE).toString());
						status = summaryMap.get(Constants.F_STATUS_NUMBER) != null ? summaryMap.get(Constants.F_STATUS_NUMBER).toString() : "0";
						if(Constants.F_REJECTED_FILE_TYPE.equalsIgnoreCase(modules.get(Constants.F_FILE_TYPE).toString()))
						{
							if(status.equalsIgnoreCase(PropertiesFileLoaderClass.get("FILE_RECEIVED_STATUS").toString()) 
									|| status.equals(PropertiesFileLoaderClass.get("FILE_INQUEUE_STATUS").toString()))
							{
								modules.append(Constants.F_DESTINATION_DIRECTION, serverData.get(Constants.F_REJECTED_DIRECTION));
							}
							else
							{
								modules.append(Constants.F_DESTINATION_DIRECTION, serverData.get(Constants.F_REJECTED_BACK_UP_DIRECTION));
							}
						}
						else if(Constants.F_FILTER_FILE_TYPE.equalsIgnoreCase(modules.get(Constants.F_FILE_TYPE).toString()))
						{
							modules.append(Constants.F_DESTINATION_DIRECTION, serverData.get(Constants.F_FILTER_BACKUP_DIRECTION));
						}
						else
						{
							rejectedPullMongoFile = (status.equalsIgnoreCase(PropertiesFileLoaderClass.get("FILE_REJECTED_STATUS").toString()) && pullMongoInterfaceList.contains(summaryMap.get(Constants.F_INTERFACE_ID_NUMBER).toString())) ? true : false;
							if(status.equalsIgnoreCase(PropertiesFileLoaderClass.get("FILE_RECEIVED_STATUS").toString()) 
									|| status.equals(PropertiesFileLoaderClass.get("FILE_INQUEUE_STATUS").toString()) || rejectedPullMongoFile)
							{
								modules.append(Constants.F_DESTINATION_DIRECTION, serverData.get(Constants.F_DIRECTION));
							}
							else
							{
								modules.append(Constants.F_DESTINATION_DIRECTION, serverData.get(Constants.F_BACK_UP_DIRECTION));
							}
						}
					}
					responseObj.add(modules);
				}
			}		
			return responseObj;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileSummary]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileSummary]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			responseObj = null;
			sql = null;
			summaryDtlsMap = null;
			result = null;
			row = null;
			status = null;
			modules = null;
			summaryMap = null;
			serverData = null;
			pullMongoInterfaceList = null;
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,Object> getSummaryByFileId(Long fileId)
	{
		Map<String,Object> response = null;
		HashMap<String,Long> attributeMap = null;
		String sql = null;
		List<Map<String,Object>> attributeData = null;
		try
		{
			response = new HashMap();
			/*attribute Map*/
			attributeMap = new HashMap<String,Long>();
			attributeMap.put(Constants.QF_FILE_ID, fileId);
			
			sql = QueryConstants.Q_GET_TR_FILE_SUMMARY;
			
			attributeData = namedParamJdbcTemplate.queryForList(sql, attributeMap);
			if(attributeData != null && !attributeData.isEmpty())
			{
				return attributeData.get(0);
			}
			return response;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getSummaryByFileId]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getSummaryByFileId]:: Exception Message is: >> " + exception.getMessage());
		}
		finally
		{
			response = null;
			attributeMap = null;
			sql = null;
			attributeData = null;
		}
		return response;
	}
	
	@SuppressWarnings({"unchecked"})
	public BasicDBObject getInterfaceAttrByInterfaceId(Long interfaceId)
	{
		BasicDBObject response = null;
		HashMap<String,Long> attributeMap = null;
		String sql = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;

		try
		{
			/*attribute Map*/
			attributeMap = new HashMap<String,Long>();
			attributeMap.put(Constants.QF_INTERFACE_ID, interfaceId);
			
			sql = QueryConstants.Q_GET_INTERFACE_ATTRIBUTE;
			
			result = namedParamJdbcTemplate.queryForList(sql, attributeMap);
			if(result!=null && !result.isEmpty())
			{
				response = new BasicDBObject(); 
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					response.append(String.valueOf(row.get(Constants.F_NAME_VALUE)), String.valueOf(row.get(Constants.F_VALUE_VALUE)));
				}
			}	
			return response;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceAttrByFileId]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceAttrByFileId]:: Exception Message is: >> " + exception.getMessage());
		}
		finally
		{
			response = null;
			attributeMap = null;
			sql = null;
			result = null;
			row = null;
		}
		return response;
	}
	
	public Long getInterfaceFileData(Long uid,BasicDBObject requestObject) throws Exception
	{
		Long notifId = 0l;
		InterfaceServiceThread interfaceServiceThread = null;
		Thread interfaceThread = null;
		try
		{
			notifId = createNotification(uid,requestObject);
			
			if(notifId != 0)
			{
				requestObject.append("method_call", "getInterfaceFileData");
				
				if(requestObject.containsField("mode") && requestObject.getString("mode").equalsIgnoreCase("online"))
				{
					downloadProcessor.processData(requestObject);
				}
				else
				{
					interfaceServiceThread = new InterfaceServiceThread(requestObject,this);
					interfaceThread = new Thread(interfaceServiceThread);
					interfaceThread.start();
				}
			}
			return notifId; 
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileData]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileData]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			notifId = null;
			interfaceServiceThread = null;
			interfaceThread = null;
		}
	}
	
	public Long uploadInterfaceFile(Long uid, BasicDBObject requestObject) throws Exception
	{
		Long notifId = null;
		String sqlQry = null;
		HashMap<String, Object> queryMap = null;
		String fileName = null;
		List<Map<String,Object>> result = null;
		InterfaceServiceThread interfaceServiceThread = null;
		Thread interfaceThread = null;
		Long startTime = null;
		String reqStr = null;
		try
		{
			sqlQry = QueryConstants.Q_CHECK_FILE_NAME_EXISTS;
			fileName = requestObject.getString(Constants.F_FILE_NAME) + ".csv";
			queryMap = new HashMap<String,Object>();
			queryMap.put("fileName", fileName);
			result = namedParamJdbcTemplate.queryForList(sqlQry, queryMap);
			if(result != null && result.size() > 0)
			{
				throw new IntegrationServiceException(Constants.EX_MSG_FILE_NAME_EXISTS, Constants.EX_CODE_FILE_NAME_EXISTS);
			}
			notifId = createNotification(uid, requestObject);
			if(notifId != 0)
			{
				requestObject.append("method_call", "uploadInterfaceFile");
				startTime = System.currentTimeMillis();
				reqStr = requestObject.getString(Constants.F_FILE_DATA);
				LOGGER.info("Time Taken to read fileData :: " + (System.currentTimeMillis() - startTime));
				if(reqStr != null && !reqStr.isEmpty())
				{
					if(requestObject.containsField("mode") && requestObject.getString("mode").equalsIgnoreCase("online"))
					{
						uploadProcessor.processData(requestObject);
					}
					else
					{
						interfaceServiceThread = new InterfaceServiceThread(requestObject, this);
						interfaceThread = new Thread(interfaceServiceThread);
						interfaceThread.start();					
					}
				}
				else
				{
					InterfaceServicesDownloadProcessor.updateNotification(null , requestObject, 771L, interfaceServicesDAO);
					notifId = 0l;
				}
			}
			return notifId; 
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - uploadInterfaceFile]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(IntegrationServiceException integrationServiceException)
		{
			LOGGER.error("[InterfaceServicesDeligator - uploadInterfaceFile]:: IntegrationServiceException Message is: >> " + integrationServiceException.getMessage());
			throw integrationServiceException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - uploadInterfaceFile]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			notifId = null;
			sqlQry = null;
			queryMap = null;
			fileName = null;
			result = null;
			interfaceServiceThread = null;
			interfaceThread = null;
			startTime = null;
			reqStr = null;
		}
	}
		
	public long createNotification(Long uid,BasicDBObject requestObject) throws Exception
	{
		long nextSeq = 0;
		try
		{
			nextSeq = interfaceServicesDAO.nextSequence("notification");
			requestObject.put("notification_id", nextSeq);
			DBObject notificationObj = new BasicDBObject();
			notificationObj.put(Constants._ID, nextSeq);
			notificationObj.put("type", "report");
			notificationObj.put("feature_code", requestObject.containsField(Constants.F_FILE_NAME) ? requestObject.getString(Constants.F_FILE_NAME) : requestObject.getString("reportId"));
			notificationObj.put("description", "Offline Reports");
			notificationObj.put("ent_tp", 750l);
			notificationObj.put("trans_id", "");
			notificationObj.put("ent_no", requestObject.getLong("node_id"));
			notificationObj.put("org_id", requestObject.getLong("org_id"));
			notificationObj.put("notification_dt", new Date());
			notificationObj.put("report_notification_dt", new Date());
			notificationObj.put("read", true);
			notificationObj.put("file_type", requestObject.containsField(Constants.F_FILE_EXTENSION) ? requestObject.getString(Constants.F_FILE_EXTENSION) : requestObject.getString(Constants.FILE_TYPE));
			notificationObj.put("clients", new ArrayList<Long>());
			notificationObj.put("crtd_by", uid);
			notificationObj.put("crtd_dt", new Date());
			notificationObj.put("status", 169l);
			notificationObj.put("src", "Interface");
			interfaceServicesDAO.insertDocumentObject(Constants.S_SNOC, Constants.C_NOTIFICATION, notificationObj);
			LOGGER.info("[InterfaceServicesDeligator - createNotification] notification created successfully ...!");
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesDeligator - createNotification]:: Exception Message is: >> " + e.getMessage());
		}
		return nextSeq;
	}
	
	public void getInterfaceFileData(BasicDBObject requestObj) throws Exception{
		
		try{
			LOGGER.info("[InterfaceServicesDeligator - getInterfaceFileData] is invoked ");
			
			downloadProcessor.processData(requestObj);
			
		}catch (Exception e) {
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceFileData] exception occured : " + e.getMessage());
			InterfaceServicesDownloadProcessor.updateNotification(null, requestObj, 771L, interfaceServicesDAO);
		}
	}
	
	public void uploadInterfaceFile(BasicDBObject requestObj) throws Exception
	{
		try
		{
			LOGGER.info("[InterfaceServicesDeligator - uploadInterfaceFile] is invoked ");
			
			uploadProcessor.processData(requestObj);
			
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesDeligator - uploadInterfaceFile] exception occured : " + e.getMessage());
			InterfaceServicesDownloadProcessor.updateNotification(null , requestObj, 771L, interfaceServicesDAO);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBList getOnlineInterfaceSummaries(BasicDBObject requestObject) throws Exception 
	{
		BasicDBList summaryList = null;	
		SimpleDateFormat sfd = null;
		HashMap<String, Object> qryMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		BasicDBObject summaryObj = null;
		List<String> postgresInterfacesList = null;
		List<String> mongoInterfaceList = null;
		List<String> mongoInterfaceNumberSearchList = null;
		BasicDBObject queryObj = null;
		DBCursor interfaceCursor = null;
		Date startDate = null;
		Date endDate = null;
		HashMap<Long, String> statusMap = null;
		String postgresInterfaceProperty = null;
		String mongoInterfaceProperty = null;
		String mongoInterfaceWithNumberSearch = null;
		String sqlQry = null;
		SimpleDateFormat responseFormat = null;
		String queryMapping = null;
		
		try
		{
			summaryList = new BasicDBList();
			sfd = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
			startDate = sfd.parse(requestObject.getString("start_date_time"));
			endDate = sfd.parse(requestObject.getString("end_date_time"));
			postgresInterfaceProperty = PropertiesFileLoaderClass.getValueAsString("POSTGRES_ONLINE_INTERFACE_LIST");
			mongoInterfaceProperty = PropertiesFileLoaderClass.getValueAsString("MONGO_ONLINE_INTERFACE_LIST");
			mongoInterfaceWithNumberSearch = PropertiesFileLoaderClass.getValueAsString("MONGO_ONLINE_INTERFACE_NUMBER_QUERY_SEARCHLIST");
			if(postgresInterfaceProperty != null)
			{
				postgresInterfacesList = new ArrayList<String>();
				postgresInterfacesList =  Arrays.asList(postgresInterfaceProperty.split(","));
			}
			if(mongoInterfaceProperty != null)
			{
				mongoInterfaceList = new ArrayList<String>();
				mongoInterfaceList =  Arrays.asList(mongoInterfaceProperty.split(","));
			}
			
			if(postgresInterfacesList != null && postgresInterfacesList.contains(requestObject.get("interface_id").toString()))
			{
				sqlQry = QueryConstants.Q_GET_ONLINE_INTERFACE_SUMMARY_1;
				qryMap = new HashMap<>();
				qryMap.put("interfaceId", Long.parseLong(requestObject.get("interface_id").toString()));
				qryMap.put("startDate", requestObject.getString("start_date_time"));
				qryMap.put("endDate", requestObject.getString("end_date_time"));
				qryMap.put("errorStatus", Constants.ST_ERROR);
				qryMap.put("callBackFailedStatus", Constants.ST_CALLBACK_FAILED);
				
				if(requestObject.containsField("transaction_id") && requestObject.get("transaction_id") != null && !requestObject.getString("transaction_id").isEmpty())
				{
					sqlQry += " " + QueryConstants.Q_GET_ONLINE_INTERFACE_SUMMARY_2;
					qryMap.put("transactionId", requestObject.getLong("transaction_id"));
				}
				if(requestObject.containsField("ref_data") && requestObject.get("ref_data") != null && !requestObject.getString("ref_data").isEmpty())
				{
					sqlQry += " " + QueryConstants.Q_GET_ONLINE_INTERFACE_SUMMARY_3;
					qryMap.put("refData", requestObject.getString("ref_data"));
				}
				result = namedParamJdbcTemplate.queryForList(sqlQry, qryMap);
				if(result != null && !result.isEmpty())
				{
					for(Object obj : result)
					{
						row = (Map<String, Object>) obj;
						summaryObj = new BasicDBObject();
						for (String key : row.keySet())
						{
							summaryObj.append(key, row.get(key) != null ? row.get(key) : "");
						}
						summaryList.add(summaryObj);
					}
				}
			}
			else if(mongoInterfaceList != null && mongoInterfaceList.contains(requestObject.get("interface_id").toString()))
			{
				responseFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
				queryObj = new BasicDBObject();
				interfaceCursor = interfaceServicesDAO.getDocumentsbyQuery(Constants.S_INTERFACE, Constants.C_STATUS_MASTER, queryObj);
				statusMap = new HashMap<Long,String>();
				for (DBObject dbObject : interfaceCursor) 
				{
					statusMap.put(Long.parseLong(dbObject.get(Constants._ID).toString()), dbObject.get("description").toString());
				}
				queryObj = new BasicDBObject();
				queryObj.append("interface_id", Long.parseLong(requestObject.get("interface_id").toString()));
				queryObj.append("crtd_dt", new BasicDBObject("$gte", startDate).append("$lte", endDate));
				if(requestObject.containsField("transaction_id") && requestObject.get("transaction_id") != null && !requestObject.getString("transaction_id").isEmpty())
				{
					queryObj.append(Constants._ID, requestObject.getLong("transaction_id"));
				}
				if(requestObject.containsField("ref_data") && requestObject.get("ref_data") != null && !requestObject.getString("ref_data").isEmpty())
				{
					if(mongoInterfaceWithNumberSearch != null)
					{
						mongoInterfaceNumberSearchList = new ArrayList<String>();
						mongoInterfaceNumberSearchList =  Arrays.asList(mongoInterfaceWithNumberSearch.split(","));
					}
					queryMapping = PropertiesFileLoaderClass.getValueAsString("MONGO_ONLINE_INTERFACE_QUERY_MAPPING_"+requestObject.get("interface_id").toString());
					if(mongoInterfaceNumberSearchList != null && mongoInterfaceNumberSearchList.contains(requestObject.get("interface_id").toString()))
					{
						try 
						{
							queryObj.put(queryMapping, Long.parseLong(requestObject.get("ref_data").toString()));
						} 
						catch (Exception e) 
						{
							return summaryList;
						}
					}
					else
					{
						queryObj.put(queryMapping, new BasicDBObject("$regex", "^" + requestObject.get("ref_data") + "$").append("$options", "i"));
					}
				}
				interfaceCursor = interfaceServicesDAO.getDocumentsbyQuery(Constants.S_INTERFACE, Constants.C_TRANSACTION_SUMMARY, queryObj);
				for (DBObject dbObject : interfaceCursor) 
				{
					summaryObj = new BasicDBObject();
					summaryObj.append("trans_id_n", dbObject.get(Constants._ID));
					summaryObj.append("interface_id_n", dbObject.get("interface_id"));
					summaryObj.append("request_time_dt", dbObject.get("crtd_dt") != null ? responseFormat.format(dbObject.get("crtd_dt")) : "");
					summaryObj.append("response_time_dt", dbObject.get("updtd_dt") != null ? responseFormat.format(dbObject.get("updtd_dt")) : "");
					summaryObj.append("status_n", dbObject.get("status"));
					summaryObj.append("status_desc", statusMap.get(Long.parseLong(dbObject.get("status").toString())) != null ? statusMap.get(Long.parseLong(dbObject.get("status").toString())) : "");
					summaryObj.append("ext_sys_status", dbObject.get("error_code") != null ? "Failure" : "Success");
					summaryObj.append("re_process", 0);
					summaryList.add(summaryObj);
				}
			}
			return summaryList;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getOnlineInterfaceSummaries]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getOnlineInterfaceSummaries]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			summaryList = null;	
			sfd = null;
			qryMap = null;
			result = null;
			row = null;
			summaryObj = null;
			postgresInterfacesList = null;
			mongoInterfaceList = null;
			queryObj = null;
			interfaceCursor = null;
			startDate = null;
			endDate = null;
			postgresInterfaceProperty = null;
			mongoInterfaceProperty = null;
			mongoInterfaceWithNumberSearch = null;
			sqlQry = null;
			responseFormat = null;
			queryMapping = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBObject getRequestAndResponseDataByTransactionId(BasicDBObject requestObject) throws Exception 
	{
		BasicDBObject responseObject = null;	
		HashMap<String, Object> qryMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		List<String> postgresInterfacesList = null;
		List<String> mongoInterfaceList = null;
		BasicDBObject queryObj = null;
		DBObject interfaceObject = null;
		String postgresInterfaceProperty = null;
		String mongoInterfaceProperty = null;
		JSONParser parser = null;
		JSONObject requestSecurityDtlsPathConfig = null;
		String sqlQry = null;
		
		try
		{
			responseObject = new BasicDBObject();
			
			postgresInterfaceProperty = PropertiesFileLoaderClass.getValueAsString("POSTGRES_ONLINE_INTERFACE_LIST");
			mongoInterfaceProperty = PropertiesFileLoaderClass.getValueAsString("MONGO_ONLINE_INTERFACE_LIST");
			parser = new JSONParser();
			requestSecurityDtlsPathConfig = (JSONObject) parser.parse(PropertiesFileLoaderClass.getValueAsString("requestSecurityDtlsPathConfig"));
			
			if(postgresInterfaceProperty != null)
			{
				postgresInterfacesList = new ArrayList<String>();
				postgresInterfacesList =  Arrays.asList(postgresInterfaceProperty.split(","));
			}
			if(mongoInterfaceProperty != null)
			{
				mongoInterfaceList = new ArrayList<String>();
				mongoInterfaceList =  Arrays.asList(mongoInterfaceProperty.split(","));
			}
			if(postgresInterfacesList != null && postgresInterfacesList.contains(requestObject.get("interface_id").toString()))
			{
				sqlQry = QueryConstants.Q_GET_REQUEST_RESPONSE_BY_TRANSACTION_ID;
				qryMap = new HashMap<>();
				qryMap.put("transactionId", Long.parseLong(requestObject.get("transaction_id").toString()));
				result = namedParamJdbcTemplate.queryForList(sqlQry, qryMap);
				if(result != null && !result.isEmpty())
				{
					for(Object obj : result)
					{
						row = (Map<String, Object>) obj;
						for (String key : row.keySet())
						{
							LOGGER.debug("key :: "+key);
							LOGGER.debug("condition :: "+(requestSecurityDtlsPathConfig.containsKey(requestObject.get("interface_id").toString()) && key.equalsIgnoreCase("request_data_b")));
							if(requestSecurityDtlsPathConfig.containsKey(requestObject.get("interface_id").toString()) && key.equalsIgnoreCase("request_data_b"))
								responseObject.append(key, row.get(key) != null ? maskSecurityValues(row.get(key).toString(), Long.parseLong(requestObject.get("interface_id").toString()), requestSecurityDtlsPathConfig) : "");
							else
								responseObject.append(key, row.get(key) != null ? row.get(key).toString() : "");
						}
					}
				}
			}
			else if(mongoInterfaceList != null && mongoInterfaceList.contains(requestObject.get("interface_id").toString()))
			{
				queryObj = new BasicDBObject();
				queryObj.append("transaction_id", Long.parseLong(requestObject.get("transaction_id").toString()));
				interfaceObject = interfaceServicesDAO.getDocumentbyId(Constants.S_INTERFACE, Constants.C_TRANSACTION_SUMMARY_DETAILS, queryObj);
				if(interfaceObject != null)
				{
					responseObject.append("orgnl_request_data_b", interfaceObject.get("pre_req") != null ? interfaceObject.get("pre_req") : "");
					responseObject.append("request_data_b", interfaceObject.get("post_req") != null ? interfaceObject.get("post_req") : "");
					responseObject.append("orgnl_response_data_b", interfaceObject.get("pre_res") != null ? interfaceObject.get("pre_res") : "");
					responseObject.append("response_data_b", interfaceObject.get("post_res") != null ? interfaceObject.get("post_res") : "");
				}
				else
				{
					queryObj = new BasicDBObject();
					queryObj.append(Constants._ID, Long.parseLong(requestObject.get("transaction_id").toString()));
					interfaceObject = interfaceServicesDAO.getDocumentbyId(Constants.S_INTERFACE, Constants.C_TRANSACTION_SUMMARY, queryObj);
					if(interfaceObject != null)
					{
						responseObject.append("orgnl_request_data_b", interfaceObject.get("request_data") != null ? interfaceObject.get("request_data") : "");
						responseObject.append("request_data_b", "");
						responseObject.append("orgnl_response_data_b", "");
						responseObject.append("response_data_b", "");
					}
				}
			}
			return responseObject;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getOnlineInterfaceSummaries]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getOnlineInterfaceSummaries]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			responseObject = null;	
			qryMap = null;
			result = null;
			row = null;
			postgresInterfacesList = null;
			mongoInterfaceList = null;
			queryObj = null;
			interfaceObject = null;
			postgresInterfaceProperty = null;
			mongoInterfaceProperty = null;
			parser = null;
			requestSecurityDtlsPathConfig = null;
			sqlQry = null;
		}
	}
	
	public BasicDBList getOnlineInterfaceListWrapper(BasicDBObject requestObject) throws Exception 
	{
		BasicDBList responseList = null;	
		try
		{
			responseList = new BasicDBList();
			responseList.addAll(getInterfaces(requestObject));
			responseList.addAll(getMongoInterfaceList((BasicDBList) requestObject.get("ext_system")));
			return responseList;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getOnlineInterfaceListWrapper]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getOnlineInterfaceListWrapper]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			responseList = null;	
		}
	}
	
	public BasicDBList getMongoInterfaceList(BasicDBList extSystem) throws Exception 
	{
		BasicDBList responseList = null;	
		BasicDBObject responseObj = null;
		DBCursor interfaceCursor = null;
		BasicDBObject query = null;
		try
		{
			responseList = new BasicDBList();
			query = new BasicDBObject();
			query.append("system_name", new BasicDBObject("$in",extSystem));
			interfaceCursor = interfaceServicesDAO.getDocumentsbyQuery(Constants.S_INTERFACE, Constants.C_SOAP_REQUEST_MAPPING, query);
			for (DBObject dbObject : interfaceCursor) 
			{
				responseObj = new BasicDBObject();
				responseObj.append(Constants.F_ID, dbObject.get(Constants._ID));
				responseObj.append(Constants.F_NAME, dbObject.get("interface_code") + " - " + dbObject.get("interface_description"));
				responseObj.append(Constants.F_UPLOAD, false);
				responseList.add(responseObj);
			}
			
			return responseList;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getMongoInterfaceList]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getMongoInterfaceList]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			responseList = null;	
			responseObj = null;
			interfaceCursor = null;
			query = null;
		}
	}
	
	public BasicDBObject reprocessInterfaceSummary(BasicDBObject requestObject) throws Exception 
	{
		HashMap<String, Object> qryMap = null;
		String queryString = null;
		Long status = null;
		try
		{
			queryString = QueryConstants.Q_REPROCESS_INTERFACE_SUMMARY;
			qryMap = new HashMap<String, Object>();
			status = Long.parseLong(PropertiesFileLoaderClass.getValueAsString("REPROCESS_INTERFACE_SUMMARY_STATUS_MAPPING_" + requestObject.getLong("status_n")));
			qryMap.put("status", status);
			qryMap.put("transactionId", requestObject.getLong("trans_id_n"));
			int rowsUpdated = namedParamJdbcTemplate.update(queryString, qryMap);
			LOGGER.debug(rowsUpdated +"Rows Updated..");
			requestObject.append("rows_updated", rowsUpdated);
			return requestObject;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - reprocessInterfaceSummary]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - reprocessInterfaceSummary]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			qryMap = null;	
			queryString = null;
			status = null;
		}
	}
	
	public Long getInterfaceReportData(Long uid,BasicDBObject requestObject) throws Exception 
	{
		Long notifId = 0l;
		InterfaceServiceThread interfaceServiceThread = null;
		Thread interfaceThread = null;
		try
		{
			notifId = createNotification(uid, requestObject);
			
			if(notifId != 0){
				
				requestObject.append("method_call", "getInterfaceReportData");
				
				if(requestObject.containsField("mode") && requestObject.getString("mode").equalsIgnoreCase("online"))
				{
					downloadProcessor.processData(requestObject);
				}
				else
				{
					interfaceServiceThread = new InterfaceServiceThread(requestObject, this);
					interfaceThread = new Thread(interfaceServiceThread);
					interfaceThread.start();
				}
			}
			return notifId; 
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceReportData]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceReportData]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			notifId = null;
			interfaceServiceThread = null;
			interfaceThread = null;
		}
	}
	
	public void getInterfaceReportData(BasicDBObject requestObj) throws Exception
	{
		BasicDBObject mainConfig = null;
		BasicDBObject reportConfig = null;
		BasicDBList responseList = null;
		BasicDBObject responseObject = null;
		String reportId = null;
		
		try
		{
			mainConfig = (BasicDBObject) JSON.parse(PropertiesFileLoaderClass.getValueAsString("INTERFACE_OFFLINE_REPORTS_CONF"));
			reportId = requestObj.getString("reportId");
			reportConfig = (BasicDBObject) mainConfig.get(reportId);
			if(reportId.equalsIgnoreCase("surroundingSystemSumReport"))
				responseList = getSurroundingSystemSumReport(requestObj, reportConfig);
			else if(reportId.equalsIgnoreCase("surroundingSystemDetail"))
				responseList = getSurroundingSystemDetail(requestObj, reportConfig);
			else if(reportId.equalsIgnoreCase("surroundingSystemSumFailed"))
				responseList = getSurroundingSystemSumFailed(requestObj, reportConfig);
			
			if(responseList.isEmpty())
				return;
			responseObject = new BasicDBObject();
			responseObject.append("data", responseList);
			
			downloadProcessor.generateGridFSChunksOffline(responseObject, requestObj, reportConfig);
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceReportData] exception occured : " + e.getMessage());
			InterfaceServicesDownloadProcessor.updateNotification(null, requestObj, 771L, interfaceServicesDAO);
		}
		finally
		{
			mainConfig = null;
			reportConfig = null;
			responseList = null;
			responseObject = null;
			reportId = null;
		}
	}
	
	/*@SuppressWarnings("unchecked")
	public BasicDBList getSurroundingSystemSumReport(BasicDBObject requestObj, BasicDBObject reportConfig)
	{
		BasicDBList responseList = null;
		BasicDBObject responseObj = null;
		ArrayList<Long> interfaceIds = null;
		ArrayList<Long> statusIds = null;
		HashMap<String, Object> qryMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		ArrayList<Long> pullMongoInterfaceList = null;
		ArrayList<Long> pullMongoStatusList = null;
		String sqlQuery = null;
		try
		{
			responseList = new BasicDBList();
			statusIds = (ArrayList<Long>) JSON.parse(PropertiesFileLoaderClass.getValueAsString("STATUS_FOR_OFFLINE_REPORTS"));
			interfaceIds = (ArrayList<Long>) requestObj.get("interfaceIds");
			pullMongoInterfaceList = (ArrayList<Long>) JSON.parse(PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_INTERFACE_LIST"));
			pullMongoStatusList = (ArrayList<Long>) JSON.parse(PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_STATUS_LIST"));

			sqlQuery = reportConfig.getString("data_query");
			
			qryMap = new HashMap<String, Object>();
			qryMap.put("statusIds", statusIds);
			qryMap.put("startDate", requestObj.getString("startDate"));
			qryMap.put("endDate", requestObj.getString("endDate"));
			qryMap.put("interfaceIds", interfaceIds);
			qryMap.put("dumpInterfaceIds", pullMongoInterfaceList);
			qryMap.put("dumpStatusIds", pullMongoStatusList);
			result = jdbcTemplate.queryForList(sqlQuery, qryMap);
			if(result.isEmpty())
			{
				InterfaceServicesDownloadProcessor.updateNotification(null , requestObj, 3002L,interfaceServicesDAO);
				return responseList;
			}
			for(Object obj : result)
			{
				responseObj = new BasicDBObject();
				row = (Map<String, Object>) obj;
				for (String key : row.keySet())
				{
					responseObj.append(key, row.get(key) != null ? row.get(key).toString() : "");
				}
				responseObj.append("invalid_format", 0);
				responseObj.append("master_validtion_error", 0);
				responseObj.append("invalid_other", 0);
				responseList.add(responseObj);
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getSurroundingSystemSumReport] exception occured : "+exception.getMessage());
		}
		finally
		{
			responseObj = null;
			interfaceIds = null;
			statusIds = null;
			qryMap = null;
			result = null;
			row = null;
			pullMongoInterfaceList = null;
			pullMongoStatusList = null;
			sqlQuery = null;
		}
		return responseList;
	}*/
	
	@SuppressWarnings("unchecked")
	public BasicDBList getSurroundingSystemSumReport(BasicDBObject requestObj, BasicDBObject reportConfig)
	{
		Connection connection = null;
		PreparedStatement queryStatement = null;
		BasicDBList responseList = null;
		BasicDBObject responseObj = null;
		ArrayList<Long> interfaceIds = null;
		String interfaceIdStr = null;
		String statusIds = null;
		ResultSet result = null;
		String pullMongoInterfaceList = null;
		String pullMongoStatusList = null;
		String sqlQuery = null;
		Integer fetchSize = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			responseList = new BasicDBList();
			statusIds = PropertiesFileLoaderClass.getValueAsString("STATUS_FOR_OFFLINE_REPORTS").trim();
			interfaceIds = (ArrayList<Long>) requestObj.get("interfaceIds");
			interfaceIdStr = interfaceIds.toString().replace("[", "").replace("]", "").replace(" ", "");
			pullMongoInterfaceList = PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_INTERFACE_LIST_STR").trim();
			pullMongoStatusList = PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_STATUS_LIST").trim();
			fetchSize = Integer.parseInt(PropertiesFileLoaderClass.getValueAsString("FETCH_SIZE_FOR_REPORTS"));

			sqlQuery = reportConfig.getString("data_query");
			sqlQuery = sqlQuery.replace("interfaceIds", interfaceIdStr)
						.replace("dumpInterfaceIds", pullMongoInterfaceList)
						.replace("dumpStatusIds", pullMongoStatusList)
						.replace("statusIds", statusIds)
						.replace("startDate", requestObj.getString("startDate"))
						.replace("endDate", requestObj.getString("endDate"));
			
			connection.setAutoCommit(false);
			queryStatement = connection.prepareStatement(sqlQuery);
			queryStatement.setFetchSize(fetchSize);
			result = queryStatement.executeQuery();
			
			while(result.next())
			{
				responseObj = new BasicDBObject();
				
				responseObj.append("file_date", result.getObject("file_date") != null ? result.getObject("file_date").toString() : "");
				responseObj.append("interface_name", result.getObject("interface_name") != null ? result.getObject("interface_name").toString() : "");
				responseObj.append("filetype", result.getObject("filetype") != null ? result.getObject("filetype").toString() : "");
				responseObj.append("file_qty", result.getObject("file_qty") != null ? result.getObject("file_qty").toString() : "");
				responseObj.append("total", result.getObject("total") != null ? result.getObject("total").toString() : "");
				responseObj.append("success", result.getObject("success") != null ? result.getObject("success").toString() : "");
				responseObj.append("failure", result.getObject("failure") != null ? result.getObject("failure").toString() : "");
				responseObj.append("invalid_format", 0);
				responseObj.append("master_validtion_error", 0);
				responseObj.append("invalid_other", 0);
				responseList.add(responseObj);
			}
			result.close();
			queryStatement.close();
			
			if(responseList.isEmpty())
			{
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObj, 3002L, interfaceServicesDAO);
			}
			return responseList;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getSurroundingSystemSumReport] exception occured : " + exception.getMessage());
		}
		finally
		{
			try 
			{
				if(result != null)
					result.close();
				if(queryStatement != null)
					queryStatement.close();
				if(connection != null)
					connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			fetchSize = null;
			connection = null;
			responseObj = null;
			interfaceIds = null;
			interfaceIdStr = null;
			statusIds = null;
			queryStatement = null;
			result = null;
			pullMongoInterfaceList = null;
			pullMongoStatusList = null;
			sqlQuery = null;
		}
		return responseList;
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBList getSurroundingSystemDetail(BasicDBObject requestObj, BasicDBObject reportConfig)
	{
		Connection connection = null;
		PreparedStatement queryStatement = null;
		BasicDBList responseList = null;
		BasicDBObject responseObj = null;
		ArrayList<Long> interfaceIds = null;
		String interfaceIdStr = null;
		String statusIds = null;
		ResultSet result = null;
		String pullMongoInterfaceList = null;
		String pullMongoStatusList = null;
		String sqlQuery = null;
		Integer fetchSize = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			responseList = new BasicDBList();
			statusIds = PropertiesFileLoaderClass.getValueAsString("STATUS_FOR_OFFLINE_REPORTS").trim();
			interfaceIds = (ArrayList<Long>) requestObj.get("interfaceIds");
			interfaceIdStr = interfaceIds.toString().replace("[", "").replace("]", "").replace(" ", "");
			pullMongoInterfaceList = PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_INTERFACE_LIST_STR").trim();
			pullMongoStatusList = PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_STATUS_LIST").trim();
			fetchSize = Integer.parseInt(PropertiesFileLoaderClass.getValueAsString("FETCH_SIZE_FOR_REPORTS"));
			
			sqlQuery = reportConfig.getString("data_query");
			sqlQuery = sqlQuery.replace("statusIds", statusIds)
					.replace("startDate", requestObj.getString("startDate"))
					.replace("endDate", requestObj.getString("endDate"))
					.replace("interfaceIds", interfaceIdStr)
					.replace("dumpInterfaceIds", pullMongoInterfaceList)
					.replace("dumpStatusIds", pullMongoStatusList);

			connection.setAutoCommit(false);
			queryStatement = connection.prepareStatement(sqlQuery);
			queryStatement.setFetchSize(fetchSize);
			result = queryStatement.executeQuery();
			
			while(result.next())
			{
				responseObj = new BasicDBObject();
				
				responseObj.append("start_date", result.getObject("start_date") != null ? result.getObject("start_date").toString() : "");
				responseObj.append("end_date", result.getObject("end_date") != null ? result.getObject("end_date").toString() : "");
				responseObj.append("interface_name", result.getObject("interface_name") != null ? result.getObject("interface_name").toString() : "");
				responseObj.append("filetype", result.getObject("filetype") != null ? result.getObject("filetype").toString() : "");
				responseObj.append("path", result.getObject("path") != null ? result.getObject("path").toString() : "");
				responseObj.append("file_name_v", result.getObject("file_name_v") != null ? result.getObject("file_name_v").toString() : "");
				responseObj.append("success", result.getObject("success") != null ? result.getObject("success").toString() : "");
				responseObj.append("failure", result.getObject("failure") != null ? result.getObject("failure").toString() : "");
				responseObj.append("invalid_format", 0);
				responseObj.append("master_validtion_error", 0);
				responseObj.append("invalid_other", 0);
				responseList.add(responseObj);
			}
			result.close();
			queryStatement.close();
			
			if(responseList.isEmpty())
			{
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObj, 3002L, interfaceServicesDAO);
			}
			return responseList;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getSurroundingSystemDetail] exception occured : " + exception.getMessage());
		}
		finally
		{
			try 
			{
				if(result != null)
					result.close();
				if(queryStatement != null)
					queryStatement.close();
				if(connection != null)
					connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			fetchSize = null;
			connection = null;
			responseObj = null;
			interfaceIds = null;
			interfaceIdStr = null;
			statusIds = null;
			queryStatement = null;
			result = null;
			pullMongoInterfaceList = null;
			pullMongoStatusList = null;
			sqlQuery = null;
		}
		return responseList;
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBList getSurroundingSystemSumFailed(BasicDBObject requestObj, BasicDBObject reportConfig)
	{
		Connection connection = null;
		PreparedStatement queryStatement = null;
		BasicDBList responseList = null;
		BasicDBObject responseObj = null;
		ArrayList<Integer> interfaceIds = null;
		String statusIds = null;
		ResultSet result = null;
		ArrayList<Long> pullMongoInterfaceList = null;
		ArrayList<Long> kpiRelatedInterfaceList = null;
		ArrayList<Integer> pullMongoIdentifiedList = null;
		ArrayList<Integer> kpiRelatedIdentifiedList = null;
		ArrayList<Long> mongoRelatedInterfaceList = null;
		ArrayList<Integer> mongoRelatedIdentifiedList = null;
		BasicDBObject mongoConfig = null;
		AggregationOutput aggregationResult = null;
		BasicDBObject matchObj = null;
		BasicDBObject groupObj = null;
		List<DBObject> pipeline = null;
		BasicDBObject basicDBObject = null;
		String queryString = "";
		Integer fetchSize = null;
		BasicDBObject cloneObj = null;
		
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			responseList = new BasicDBList();
			statusIds = PropertiesFileLoaderClass.getValueAsString("STATUS_FOR_OFFLINE_REPORTS").trim();
			interfaceIds = (ArrayList<Integer>) requestObj.get("interfaceIds");
			pullMongoInterfaceList = (ArrayList<Long>) JSON.parse(PropertiesFileLoaderClass.getValueAsString("PULL_MONGO_INTERFACE_LIST"));
			kpiRelatedInterfaceList = (ArrayList<Long>) JSON.parse(PropertiesFileLoaderClass.getValueAsString("KPI_RELATED_INTERFACE_LIST"));
			mongoRelatedInterfaceList = (ArrayList<Long>) JSON.parse(PropertiesFileLoaderClass.getValueAsString("MONGO_RELATED_INTERFACE_LIST"));
			fetchSize = Integer.parseInt(PropertiesFileLoaderClass.getValueAsString("FETCH_SIZE_FOR_REPORTS"));
			pullMongoIdentifiedList = new ArrayList<Integer>();
			kpiRelatedIdentifiedList = new ArrayList<Integer>();
			mongoRelatedIdentifiedList = new ArrayList<Integer>();
			
			for (Integer interfaceId : interfaceIds)
			{
				if(pullMongoInterfaceList.contains(interfaceId))
				{
					pullMongoIdentifiedList.add(interfaceId);
				}
				else if(kpiRelatedInterfaceList.contains(interfaceId))
				{
					kpiRelatedIdentifiedList.add(interfaceId);
				}
				else if(mongoRelatedInterfaceList.contains(interfaceId))
				{
					mongoRelatedIdentifiedList.add(interfaceId);
				}
				else
				{
					if(queryString != null && !queryString.isEmpty())
					{
						queryString += " union " + reportConfig.getString(interfaceId.toString());
					}
					else
						queryString += reportConfig.getString(interfaceId.toString());
				}
			}
			if(!pullMongoIdentifiedList.isEmpty())
			{
				if(queryString != null && !queryString.isEmpty())
				{
					queryString += " union " + reportConfig.getString("pullMongoDataQuery");
				}
				else
					queryString += reportConfig.getString("pullMongoDataQuery");
				queryString = queryString.replace("interfaceIds", pullMongoIdentifiedList.toString().replace("[", "").replace("]", "").replace(" ", ""));
			}
			
			if(queryString != null && !queryString.isEmpty())
			{
				queryString += " order by 3";
			}
			if(!queryString.trim().isEmpty())
			{
				queryString = queryString.replace("statusIds", statusIds)
											.replace("startDate", requestObj.getString("startDate"))
												.replace("endDate", requestObj.getString("endDate"));
				
				queryStatement = connection.prepareStatement(queryString);
				queryStatement.setFetchSize(fetchSize);
				result = queryStatement.executeQuery();
				
				while(result.next())
				{
					responseObj = new BasicDBObject();
					responseObj.append("start_date", result.getObject("start_date") != null ? result.getObject("start_date").toString() : "");
					responseObj.append("end_date", result.getObject("end_date") != null ? result.getObject("end_date").toString() : "");
					responseObj.append("interface_name", result.getObject("interface_name") != null ? result.getObject("interface_name").toString() : "");
					responseObj.append("filetype", result.getObject("filetype") != null ? result.getObject("filetype").toString() : "");
					responseObj.append("value_v", result.getObject("value_v") != null ? result.getObject("value_v").toString() : "");
					responseObj.append("file_name_v", result.getObject("file_name_v") != null ? result.getObject("file_name_v").toString() : "");
					responseObj.append("result_error_code_n", result.getObject("result_error_code_n") != null ? result.getObject("result_error_code_n").toString() : "");
					responseObj.append("result_error_message_v", result.getObject("result_error_message_v") != null ? result.getObject("result_error_message_v").toString() : "");
					responseObj.append("count", result.getObject("count") != null ? result.getObject("count").toString() : "");
					responseList.add(responseObj);
				}	
				result.close();
				queryStatement.close();
			}
			
			if(!kpiRelatedIdentifiedList.isEmpty())
			{
				/*queryString = reportConfig.getString("kpiRelatedInterfacesQuery_1");
				
				queryString.replace("interfaceIds", kpiRelatedIdentifiedList.toString().replace("[", "").replace("]", "").replace(" ", ""));
				queryString.replace("startDate", requestObj.getString("startDate"));
				queryString.replace("endDate", requestObj.getString("endDate"));
				
				queryStatement = connection.prepareStatement(queryString);
				queryStatement.setFetchSize(100);
				result = queryStatement.executeQuery();
				
				if(!result.isEmpty())
				{
					for(Object obj : result)
					{
						responseObj = new BasicDBObject();
						row = (Map<String, Object>) obj;
						for (String key : row.keySet())
						{
							responseObj.append(key, row.get(key) != null ? row.get(key).toString() : "");
						}
						
						BasicDBObject attrData = getInterfaceAttrByInterfaceId(Long.parseLong(responseObj.get(Constants.F_INTERFACE_ID).toString()));
						int headerLength = attrData.getString("Rejection File Headers").split(Constants.CSV_DELIMITER_1).length;
						Map<String,Integer> errorCodeMessageMap = new HashMap<String,Integer>();
						
						intSummaryQuery = QueryConstants.Q_GET_ORGNL_REQUEST_BLOB_DATA;
						intSummaryQryMap = new HashMap<String, Object>();
						intSummaryQryMap.put("ref_data3_n", Long.parseLong(responseObj.get("file_id").toString()));
						intSummaryQryMap.put("status_n", Long.parseLong(PropertiesFileLoaderClass.getValueAsString("ERROR_STATUS")));
						intSummaryresult = jdbcTemplate.queryForList(intSummaryQuery, intSummaryQryMap);
						for(Map<String, Object> blobIdRow : intSummaryresult)
						{
							String blobData = (String) blobIdRow.get("convert_from");
							String blobStr[] = blobData.split(System.lineSeparator());
							
							for(String record : blobStr)
							{
								if(headerLength == record.split(Constants.CSV_DELIMITER_1).length)
								{
									String errorCodeMessage = record.split(Constants.CSV_DELIMITER_1)[headerLength-2] + "~" + record.split("\\|")[headerLength-1];
									if(errorCodeMessageMap.containsKey(errorCodeMessage))
										errorCodeMessageMap.put(errorCodeMessage, errorCodeMessageMap.get(errorCodeMessage)+1);
									else
										errorCodeMessageMap.put(errorCodeMessage, 1);
								}
							}
						}
						
						for(Map.Entry<String, Integer> entry : errorCodeMessageMap.entrySet())
						{
							BasicDBObject cloneObj = (BasicDBObject) responseObj.clone();
							cloneObj.put("result_error_code_n", entry.getKey().split("~")[0]);
							cloneObj.put("result_error_message_v", entry.getKey().split("~")[1]);
							cloneObj.put("count", entry.getValue());
							responseList.add(cloneObj);
						}
					}
				}*/
				
				queryString = reportConfig.getString("kpiRelatedInterfacesQuery_2");
				
				queryString = queryString.replace("interfaceIds", kpiRelatedIdentifiedList.toString().replace("[", "").replace("]", "").replace(" ", ""))
											.replace("startDate", requestObj.getString("startDate"))
												.replace("endDate", requestObj.getString("endDate"));
				
				queryStatement = connection.prepareStatement(queryString);
				queryStatement.setFetchSize(fetchSize);
				result = queryStatement.executeQuery();

				while(result.next())
				{
					responseObj = new BasicDBObject();
					responseObj.append("start_date", result.getObject("start_date") != null ? result.getObject("start_date").toString() : "");
					responseObj.append("end_date", result.getObject("end_date") != null ? result.getObject("end_date").toString() : "");
					responseObj.append("interface_name", result.getObject("interface_name") != null ? result.getObject("interface_name").toString() : "");
					responseObj.append("filetype", result.getObject("filetype") != null ? result.getObject("filetype").toString() : "");
					responseObj.append("file_name_v", result.getObject("file_name_v") != null ? result.getObject("file_name_v").toString() : "");
					responseObj.append("value_v", result.getObject("value_v") != null ? result.getObject("value_v").toString() : "");
					responseObj.append("result_error_code_n", result.getObject("result_error_code_n") != null ? result.getObject("result_error_code_n").toString() : "");
					responseObj.append("result_error_message_v", result.getObject("result_error_message_v") != null ? result.getObject("result_error_message_v").toString() : "");
					responseObj.append("count", result.getObject("count") != null ? result.getObject("count").toString() : "");
					responseList.add(responseObj);
				}
				result.close();
				queryStatement.close();
			
			}
			if(!mongoRelatedIdentifiedList.isEmpty())
			{
				queryString = reportConfig.getString("mongoRelatedInterfacesQuery");
				
				queryString = queryString.replace("interfaceIds", mongoRelatedIdentifiedList.toString().replace("[", "").replace("]", "").replace(" ", ""))
											.replace("statusIds", statusIds)
												.replace("startDate", requestObj.getString("startDate"))
													.replace("endDate", requestObj.getString("endDate"));
				
				queryStatement = connection.prepareStatement(queryString);
				queryStatement.setFetchSize(fetchSize);
				result = queryStatement.executeQuery();
				
				while(result.next())
				{
					responseObj = new BasicDBObject();
					
					responseObj.append("start_date", result.getObject("start_date") != null ? result.getObject("start_date").toString() : "");
					responseObj.append("end_date", result.getObject("end_date") != null ? result.getObject("end_date").toString() : "");
					responseObj.append("file_id", result.getObject("file_id") != null ? result.getObject("file_id").toString() : "");
					responseObj.append("interface_id", result.getObject("interface_id") != null ? result.getObject("interface_id").toString() : "");
					responseObj.append("interface_name", result.getObject("interface_name") != null ? result.getObject("interface_name").toString() : "");
					responseObj.append("filetype", result.getObject("filetype") != null ? result.getObject("filetype").toString() : "");
					responseObj.append("value_v", result.getObject("value_v") != null ? result.getObject("value_v").toString() : "");
					responseObj.append("file_name_v", result.getObject("file_name_v") != null ? result.getObject("file_name_v").toString() : "");
					
					BasicDBObject attrData = getInterfaceAttrByInterfaceId(Long.parseLong(responseObj.get(Constants.F_INTERFACE_ID).toString()));
					mongoConfig = (BasicDBObject) JSON.parse(attrData.getString("Mongo Config"));
					
					matchObj = new BasicDBObject();
					matchObj.append("file_id", Long.parseLong(responseObj.get("file_id").toString()));
					matchObj.append("status", 770l);
					matchObj.append("err_msg", new BasicDBObject().append("$ne", null));
					matchObj.append("err_code", new BasicDBObject().append("$ne", null));
					
					groupObj = new BasicDBObject();
					groupObj.append("_id", new BasicDBObject().append("err_code", "$err_code").append("err_msg", "$err_msg"));
					groupObj.append("count", new BasicDBObject().append("$sum", 1));
					
					pipeline = new ArrayList<DBObject>();
					pipeline.add(new BasicDBObject().append("$match", matchObj));
					pipeline.add(new BasicDBObject().append("$group", groupObj));
					
					aggregationResult = interfaceServicesDAO.aggregationDocumentObject(TokuUser.adminMongoClient, mongoConfig.getString("schema"), mongoConfig.getString("collection"), pipeline);
					for(DBObject dbObject : aggregationResult.results())
					{
						basicDBObject = (BasicDBObject) dbObject;
						cloneObj = (BasicDBObject) responseObj.clone();
						cloneObj.put("result_error_code_n", ((BasicDBObject) basicDBObject.get("_id")).get("err_code"));
						cloneObj.put("result_error_message_v", ((BasicDBObject) basicDBObject.get("_id")).get("err_msg"));
						cloneObj.put("count", basicDBObject.get("count"));
						responseList.add(cloneObj);
					}
				}
				result.close();
				queryStatement.close();
			}
			if(responseList.isEmpty())
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObj, 3002L, interfaceServicesDAO);
			
			return responseList;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getSurroundingSystemSumFailed] exception occured : " + exception.getMessage());
		}
		finally
		{
			try 
			{
				if(result != null)
					result.close();
				if(queryStatement != null)
					queryStatement.close();
				if(connection != null)
					connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			fetchSize = null;
			connection = null;
			queryStatement = null;
			responseList = null;
			responseObj = null;
			interfaceIds = null;
			statusIds = null;
			result = null;
			pullMongoInterfaceList = null;
			kpiRelatedInterfaceList = null;
			pullMongoIdentifiedList = null;
			kpiRelatedIdentifiedList = null;
			mongoRelatedInterfaceList = null;
			mongoRelatedIdentifiedList = null;
			mongoConfig = null;
			aggregationResult = null;
			matchObj = null;
			groupObj = null;
			pipeline = null;
			basicDBObject = null;
			cloneObj = null;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBList getInterfacesByInterfaceIds(BasicDBObject requestObject) throws Exception 
	{
		BasicDBList summaryList = null;	
		HashMap<String, Object> qryMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		BasicDBObject summaryObj = null;
		try
		{
			summaryList = new BasicDBList();
			String sqlQry = QueryConstants.Q_GET_INTERFACES_BY_INTERFACE_IDS;
			qryMap = new HashMap<>();
			qryMap.put("interfaceIds",requestObject.get("interface_ids"));
			
			result = namedParamJdbcTemplate.queryForList(sqlQry, qryMap);
			if(result != null && !result.isEmpty())
			{
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					summaryObj = new BasicDBObject();
					for (String key : row.keySet())
					{
						summaryObj.append(key, row.get(key) != null ? row.get(key) : "");
					}
					summaryList.add(summaryObj);
				}
			}
			return summaryList;
		}
		catch(NullPointerException nullPointerException){
			LOGGER.error("[InterfaceServicesDeligator - getInterfacesByInterfaceIds]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}catch(Exception exception){
			LOGGER.error("[InterfaceServicesDeligator - getInterfacesByInterfaceIds]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			summaryList = null;	
			qryMap = null;
			result = null;
			row = null;
			summaryObj = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public BasicDBList getKycSyncAttrByInterfaceId(BasicDBObject requestObject) throws Exception 
	{
		BasicDBList summaryList = null;	
		HashMap<String, Object> qryMap = null;
		List<Map<String,Object>> result = null;
		Map<String,Object> row = null;
		BasicDBObject summaryObj = null;
		String sqlQry = null;
		try
		{
			summaryList = new BasicDBList();
			sqlQry = QueryConstants.Q_GET_KYC_SYNC_ATTR_BY_INTERFACEID;
			qryMap = new HashMap<>();
			qryMap.put("interfaceId", requestObject.get("interface_id"));
			
			result = namedParamJdbcTemplate.queryForList(sqlQry, qryMap);
			if(result != null && !result.isEmpty())
			{
				for(Object obj : result)
				{
					row = (Map<String, Object>) obj;
					summaryObj = new BasicDBObject();
					for (String key : row.keySet())
					{
						summaryObj.append(key, row.get(key) != null ? row.get(key) : "");
					}
					summaryList.add(summaryObj);
				}
			}
			return summaryList;
		}
		catch(NullPointerException nullPointerException){
			LOGGER.error("[InterfaceServicesDeligator - getKycSyncAttrByInterfaceId]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}catch(Exception exception){
			LOGGER.error("[InterfaceServicesDeligator - getKycSyncAttrByInterfaceId]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			summaryList = null;	
			qryMap = null;
			result = null;
			row = null;
			summaryObj = null;
			sqlQry = null;
		}
	}
	
	public BasicDBObject updateKycSyncAttributes(BasicDBObject requestObject) throws Exception 
	{
		HashMap<String, Object> qryMap = null;
		String queryString = null;
		BasicDBList eanbleSyncIds = null;
		BasicDBList disableSyncIds = null;
		BasicDBList kycRequestDtlsList = null;
		BasicDBObject basicDBObject = null;
		try
		{
			kycRequestDtlsList = (BasicDBList) requestObject.get("kyc_attr_dtls");
			int rowsUpdated = 0;
			
			eanbleSyncIds = new BasicDBList();
			disableSyncIds = new BasicDBList();
			for (Object object : kycRequestDtlsList)
			{
				basicDBObject = (BasicDBObject) object;
				if(basicDBObject.getString("kyc_flag_n").equals("1"))
				{
					eanbleSyncIds.add(basicDBObject.getLong("sync_id_n"));
				}
				else if(basicDBObject.getString("kyc_flag_n").equals("0"))
				{
					disableSyncIds.add(basicDBObject.getLong("sync_id_n"));
				}
			}
			
			queryString = QueryConstants.Q_UPDATE_KYC_SYNC_ATTRIBUTES;
			
			if(eanbleSyncIds.size() > 0)
			{
				qryMap = new HashMap<String, Object>();
				qryMap.put("kycFlag", 1);
				qryMap.put("lastUpdatedDate", new Date());
				qryMap.put("syncIds", eanbleSyncIds);
				rowsUpdated = rowsUpdated + namedParamJdbcTemplate.update(queryString, qryMap);
			}
			
			if(disableSyncIds.size() > 0)
			{
				qryMap = new HashMap<String, Object>();
				qryMap.put("kycFlag", 0);
				qryMap.put("lastUpdatedDate", new Date());
				qryMap.put("syncIds", disableSyncIds);
				rowsUpdated = rowsUpdated + namedParamJdbcTemplate.update(queryString, qryMap);
			}
			LOGGER.debug(rowsUpdated +"Rows Updated..");
			requestObject.append("rows_updated", rowsUpdated);
			return requestObject;
		}
		catch(NullPointerException nullPointerException){
			LOGGER.error("[InterfaceServicesDeligator - updateKycSyncAttributes]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}catch(Exception exception){
			LOGGER.error("[InterfaceServicesDeligator - updateKycSyncAttributes]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			qryMap = null;	
			kycRequestDtlsList = null;
			basicDBObject = null;
			queryString = null;
			eanbleSyncIds = null;
			disableSyncIds = null;
		}
	}
	public BasicDBObject getInterfaceDetails(BasicDBObject requestObject) throws Exception 
	{
		BasicDBObject responseObj = null;
		Connection connection = null;
		PreparedStatement queryStatement = null;
		ResultSet resultSet = null;
		String detailsAttr = null;
		
		try
		{
			detailsAttr = "";
			responseObj = new BasicDBObject();
			connection = jdbcTemplate.getDataSource().getConnection();
			queryStatement = connection.prepareStatement(QueryConstants.Q_GET_INTERFACE_DETAILS_ATTR);
			queryStatement.setLong(1, Long.parseLong(requestObject.get("interface_id").toString()));
			
			resultSet = queryStatement.executeQuery();
			while(resultSet.next())
			{
				if(resultSet.getString("description_v") != null)
					detailsAttr = resultSet.getString("description_v");
			}
			resultSet.close();
			queryStatement.close();
			responseObj.put("details", detailsAttr);
			
			return responseObj;
		}
		catch(NullPointerException nullPointerException)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceDetails]:: Null Pointer Exception Message is: >> " + nullPointerException.getMessage());
			throw nullPointerException;
		}
		catch(Exception exception)
		{
			LOGGER.error("[InterfaceServicesDeligator - getInterfaceDetails]:: Exception Message is: >> " + exception.getMessage());
			throw exception;
		}
		finally
		{
			if(connection != null)
				connection.close();
			if(resultSet != null)
				resultSet.close();
			if(queryStatement != null)
				queryStatement.close();
			connection = null;
			resultSet = null;
			queryStatement = null;
			detailsAttr = null;
			responseObj = null;
		}
	}
	
	public String maskSecurityValues(String requestData, Long interfaceId, JSONObject configuration)
	{
		Document document = null;
		String convertedRequest = null;
		XPathFactory factory = null;
		XPath xPath = null;
		XPathExpression expression = null;
		Object result = null;
		NodeList nodeList = null;
		Node node = null;
		JSONArray securityValPaths = null;
		
		try
		{
			document = convertStringToDocument(requestData);
			securityValPaths = (JSONArray) configuration.get(interfaceId.toString());
			for(Object securityValPath : securityValPaths)
			{
				factory = XPathFactory.newInstance();
				xPath = factory.newXPath();
				expression = xPath.compile(securityValPath.toString());
				result = expression.evaluate(document, XPathConstants.NODESET);
				nodeList = (NodeList) result;
				if(nodeList == null)
					continue;
				node = nodeList.item(0);
				node.setTextContent("*********");
				//System.out.println("result : " + node.getTextContent());
				LOGGER.debug("result : " + node.getTextContent());
			}
			convertedRequest = documentToString(document);
//			System.out.println(convertedRequest);
			return convertedRequest;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			document = null;
			convertedRequest = null;
			factory = null;
			xPath = null;
			expression = null;
			result = null;
			nodeList = null;
			node = null;
			securityValPaths = null;
		}
		return null;
	}

	public static Document convertStringToDocument(String xmlStr) throws SAXException, IOException, ParserConfigurationException 
	{
		try
		{
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlStr))); 
		}
		catch(ParserConfigurationException parserConfigurationException)
		{
			throw parserConfigurationException;
		}
		catch(SAXException saxException)
		{
			throw saxException;
		}
		catch(IOException ioException)
		{
			throw ioException;
		}
	}

	public String documentToString(Document document) throws TransformerException 
	{
		Node node = null;
		NodeList nodeList = null; 
		Transformer transformer = null; 
		StringWriter writer = null;
		
		try
		{
			writer = new StringWriter();
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
			if(document != null )
			{
				nodeList = document.getChildNodes();
				for(int i = 0; i < nodeList.getLength(); i ++)
				{
					node = nodeList.item(i);
					deleteNullNode(node);
				}
				
				transformer.transform(new DOMSource(document), new StreamResult(writer));
			}
			return writer.toString();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			node = null;
			nodeList = null; 
			transformer = null; 
			writer = null;
		}
		return null;
	}
	public void deleteNullNode(Node node)
	{
		NodeList nodeList = null;
		
		try
		{
			nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i ++)
	        {
	            if(nodeList.item(i).getNodeType() == Node.TEXT_NODE && nodeList.item(i).getNodeValue() == null)
	            	nodeList.item(i).getParentNode().removeChild(nodeList.item(i));
	            else
	                deleteNullNode(nodeList.item(i));
	        }
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			nodeList = null;
		}
	}

}
