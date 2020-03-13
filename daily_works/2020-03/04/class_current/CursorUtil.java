package com.enhancesys.integration.services.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.treetechnologies.common.exception.ApplicationException;
import net.treetechnologies.common.logger.TLogger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.enhancesys.common.configuration.PropertiesLoader;
import com.enhancesys.integration.services.interfaces.CursorBean;
import com.enhancesys.integration.services.interfaces.IntegrationConstants;
import com.enhancesys.integration.services.interfaces.IntegrationManagement;
import com.enhancesys.integration.services.springCamel.dataEngine.interfaces.DataConstants;
import com.enhancesys.integration.services.springCamel.util.GenericProcessorException;
import com.enhancesys.integration.services.springCamel.util.MongoTemplate;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * <b>Purpose:</b><br>
 * 		Implementation of Cursor utility operations..<br>
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
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        29-12-2019		   Suresh Upparu
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */
public class CursorUtil 
{
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @return Connection
	 * @throws ApplicationException
	 */
	public Connection getPGConnection() throws ApplicationException
	{
		Connection sqlConnection = null;
		try
		{
			Class.forName(IntegrationConstants.INTERFACE_JDBC_DRIVER);
			sqlConnection = DriverManager.getConnection(IntegrationConstants.INTERFACE_JDBC_URL, IntegrationConstants.INTERFACE_JDBC_USER, IntegrationConstants.INTERFACE_JDBC_PASS);
			return sqlConnection;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new IntegrationManagement.InterfaceServiceException("Unhandled Exception : " + exception.getMessage(), exception);
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @return Map<String, MongoTemplate>
	 * @throws ApplicationException
	 */
	public Map<String, MongoTemplate> getMongoTemplates() throws ApplicationException
	{
		Map<String, MongoTemplate> mongoTemplateMap = null;
		MongoTemplate mongoTemplateR1 = null;
		MongoTemplate mongoTemplateR4 = null;
		MongoTemplate mongoTemplateR5 = null;
		String username = null;
		String password = null;
		String schemaName = null;
		String maxConnection = null;
		String connectionTimeOut = null;
		String replicas = null;
		
		try
		{
			mongoTemplateMap = new HashMap<String, MongoTemplate>();
			username = PropertiesLoader.getValueFor("MONGO_DB_USER");
			password = PropertiesLoader.getValueFor("MONGO_DB_PASSWORD");
			schemaName = PropertiesLoader.getValueFor("MONGO_DB_DATABASE");
			maxConnection = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
			connectionTimeOut = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
			replicas = PropertiesLoader.getValueFor("MONGO_DB_IP1") + ":" + PropertiesLoader.getValueFor("MONGO_DB_PORT1") + "," +
						PropertiesLoader.getValueFor("MONGO_DB_IP2") + ":" + PropertiesLoader.getValueFor("MONGO_DB_PORT2") + "," +
						PropertiesLoader.getValueFor("MONGO_DB_IP3") + ":" + PropertiesLoader.getValueFor("MONGO_DB_PORT3");
			mongoTemplateR1 = new MongoTemplate(username, password, schemaName , maxConnection, connectionTimeOut, replicas);
			mongoTemplateMap.put("snoc-r1", mongoTemplateR1);
			
			username = PropertiesLoader.getValueFor("MONGO_DB_R4_USER");
			password = PropertiesLoader.getValueFor("MONGO_DB_R4_PASSWORD");
			schemaName = PropertiesLoader.getValueFor("MONGO_DB_R4_DATABASE");
			maxConnection = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
			connectionTimeOut = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
			replicas = PropertiesLoader.getValueFor("MONGO_DB_IP4") + ":" + PropertiesLoader.getValueFor("MONGO_DB_PORT4");
			mongoTemplateR4 = new MongoTemplate(username, password, schemaName, maxConnection, connectionTimeOut, replicas);
			mongoTemplateMap.put("snoc-r4", mongoTemplateR4);

			username = PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_USER");
			password = PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_PASSWORD");
			schemaName = PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_DATABASE");
			maxConnection = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
			connectionTimeOut = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
			replicas = PropertiesLoader.getValueFor("MONGO_DB_IP5") + ":" + PropertiesLoader.getValueFor("MONGO_DB_PORT5");
			mongoTemplateR5 = new MongoTemplate(username, password, schemaName, maxConnection, connectionTimeOut, replicas);
			mongoTemplateMap.put("snoc-r5", mongoTemplateR5);
			
			return mongoTemplateMap;
		}
		catch (Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new IntegrationManagement.InterfaceServiceException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			mongoTemplateMap = null;
			mongoTemplateR1 = null;
			mongoTemplateR4 = null;
			mongoTemplateR5 = null;
			username = null;
			password = null;
			schemaName = null;
			maxConnection = null;
			connectionTimeOut = null;
			replicas = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param cursorConf
	 * @param connection
	 * @return Map<String, CursorBean>
	 * @throws ApplicationException
	 */
	public Map<String, CursorBean> getPGCursor(JSONArray cursorConf, Connection connection) throws ApplicationException
	{
		TLogger.debug("Entry getPGCursor..");
		PreparedStatement statement = null;
		ResultSet cursor = null;
		JSONObject collectionObj = null; 
		CursorBean cursorBean = null;
		Map<String, CursorBean> pgCursors = null;
		
		try
		{
			connection.setAutoCommit(false);
			pgCursors = new HashMap<String, CursorBean>();
			
			for(Object object : cursorConf)
			{
				collectionObj = (JSONObject) object;
				
				if(collectionObj.get(DataConstants.IS_POSTGRES) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_POSTGRES).toString()))
				{
					statement = connection.prepareStatement(collectionObj.get(DataConstants.QUERY).toString());
					statement.setFetchSize(100);
					cursorBean = new CursorBean();
					cursor = statement.executeQuery();
					cursorBean.setPgCursor(cursor);
					cursorBean.setMetaData(cursor.getMetaData());
					pgCursors.put(collectionObj.get(DataConstants.COLLECTION_NAME).toString(), cursorBean);
				}
			}
			
			return pgCursors;
		}
		catch(SQLException sqlException)
		{
			TLogger.error("SQL Exception : " + sqlException.getMessage(), sqlException);
			throw new GenericProcessorException("SQL Exception : " + sqlException.getMessage(), sqlException);
		}
		catch (Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			collectionObj = null; 
			cursorBean = null;
			pgCursors = null;
			TLogger.debug("Exit getPGCursor..");
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param cursorConf
	 * @param mongoTemplateMap
	 * @return Map<String, CursorBean>
	 * @throws ApplicationException
	 */
	public Map<String, CursorBean> getMongoCursor(JSONArray cursorConf, Map<String, MongoTemplate> mongoTemplateMap) throws ApplicationException
	{
		TLogger.debug("Entry getMongoCursor..");
		JSONObject configObject = null; 
		CursorBean cursorBean = null;
		DBCursor cursor = null;
		DBCollection collection = null;
		JSONObject paramObject = null;
		Calendar calendar = null;
		BasicDBObject dateQuery = null;
		BasicDBObject queryObject = null;
		BasicDBObject projectionObject = null;
		MongoTemplate mongoTemplate = null;
		DateFormat dateFormat = null;
		String collectionName = null;
		Map<String, CursorBean> mongoCursors = null;
		boolean isQueryFirst = false;
		
		try
		{
			mongoCursors = new HashMap<String, CursorBean>();
			
			for(Object object : cursorConf)
			{
				configObject = (JSONObject) object;
				
				if(configObject.get(DataConstants.IS_POSTGRES) == null || configObject.get(DataConstants.IS_POSTGRES).toString().trim().isEmpty() || "false".equalsIgnoreCase(configObject.get(DataConstants.IS_POSTGRES).toString()))
				{
					collectionName = configObject.get(DataConstants.COLLECTION_NAME).toString();
					TLogger.debug("Collection : " + collectionName + " query execution starting..");
					
					queryObject = new BasicDBObject();
					
					if(configObject.get(DataConstants.IS_QUERY_FIRST) != null && !configObject.get(DataConstants.IS_QUERY_FIRST).toString().trim().isEmpty() 
							&& "true".equalsIgnoreCase(configObject.get(DataConstants.IS_QUERY_FIRST).toString().trim()))
					{
						isQueryFirst = true;
						if(configObject.get(DataConstants.QUERY) != null && !configObject.get(DataConstants.QUERY).toString().trim().isEmpty())
							queryObject = (BasicDBObject) JSON.parse(configObject.get(DataConstants.QUERY).toString());
					}
					
					if(configObject.containsKey(DataConstants.PARAMETERS))
					{
						for(Object param : (JSONArray) configObject.get(DataConstants.PARAMETERS))
						{
							paramObject = (JSONObject) param;
							if(paramObject != null && paramObject.keySet().size() > 0)
							{
								if(paramObject.get(DataConstants.PARAM_TYPE) != null)
								{
									if(DataConstants.DATE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
									{
										calendar = Calendar.getInstance();
										calendar.add(Calendar.DATE, - Integer.parseInt(paramObject.get(DataConstants.PARAM_VALUE).toString()));
										dateQuery = new BasicDBObject("$gte", Utility.getStartOfTheDay(calendar.getTime())).append("$lte", Utility.getEndOfTheDay(calendar.getTime()));
										queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
									}
									else if(paramObject.get(DataConstants.DATE_FORMAT) != null && !paramObject.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty() 
											&& DataConstants.DATE_NUM.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
									{
										calendar = Calendar.getInstance();
										calendar.add(Calendar.DATE, - Integer.parseInt(paramObject.get(DataConstants.PARAM_VALUE).toString()));
										dateFormat = new SimpleDateFormat(paramObject.get(DataConstants.DATE_FORMAT).toString().trim());
										queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), Long.valueOf(dateFormat.format(calendar.getTime())));
									}
								}
								else
								{
									queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), paramObject.get(DataConstants.PARAM_VALUE));
								}
							}
						}
					}
					
					if(!isQueryFirst)
					{
						if(configObject.get(DataConstants.QUERY) != null && !configObject.get(DataConstants.QUERY).toString().trim().isEmpty())
							queryObject.putAll(((BasicDBObject) JSON.parse(configObject.get(DataConstants.QUERY).toString())).toMap());
					}
					
					projectionObject = new BasicDBObject();
					if(configObject.get(DataConstants.PROJECTION) != null)
						projectionObject = (BasicDBObject) JSON.parse(configObject.get(DataConstants.PROJECTION).toString());
					
					mongoTemplate = mongoTemplateMap.get(configObject.get(DataConstants.CONNECTION_ID).toString());
					collection = mongoTemplate.getCollection(configObject.get(DataConstants.SCHEMA_NAME).toString(), configObject.get(DataConstants.COLLECTION_NAME).toString());
					
					if(configObject.get(DataConstants.COLLECTION_NAME_PATTERN) != null && !configObject.get(DataConstants.COLLECTION_NAME_PATTERN).toString().trim().isEmpty())
					{
						dateFormat = new SimpleDateFormat(configObject.get(DataConstants.COLLECTION_NAME_PATTERN).toString().trim());
						if(calendar == null)
						{
							calendar = Calendar.getInstance();
							calendar.add(Calendar.DATE, -1);
						}
						
						collectionName += Integer.parseInt(dateFormat.format(calendar.getTime()));
					}
				
					collection = mongoTemplate.getCollection(configObject.get(DataConstants.SCHEMA_NAME).toString(), collectionName);
					TLogger.debug("Query Object : " + queryObject);
					if(configObject.get(DataConstants.SORT_BY) != null && !configObject.get(DataConstants.SORT_BY).toString().trim().isEmpty())
						cursor = collection.find(queryObject, projectionObject).addOption(Bytes.QUERYOPTION_NOTIMEOUT).sort((DBObject)JSON.parse(configObject.get(DataConstants.SORT_BY).toString()));
					else
						cursor = collection.find(queryObject, projectionObject).batchSize(Integer.parseInt(PropertiesLoader.getValueFor("DAILY_DUMP_QUERY_LIMIT")));
					if(cursor != null && cursor.size() > 0)
					{
						cursorBean = new CursorBean();
						cursorBean.setMongoCursor(cursor);
						mongoCursors.put(configObject.get(DataConstants.COLLECTION_NAME).toString(), cursorBean);
					}
					
					TLogger.debug("Collection : " + collectionName + " query execution completed.. mongoCursors size : " + mongoCursors.size());
				}
			}
			
			return mongoCursors;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			configObject = null; 
			cursorBean = null;
			cursor = null;
			collection = null;
			paramObject = null;
			calendar = null;
			dateQuery = null;
			queryObject = null;
			projectionObject = null;
			dateFormat = null;
			collectionName = null;
			mongoCursors = null;
			TLogger.debug("Exit getMongoCursor..");
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param collectionName
	 * @param pgCursorMap
	 * @param queryField
	 * @param queryValue
	 * @param fetchField
	 * @return Object
	 * @throws ApplicationException
	 */
	public Object getPGValue(String collectionName, Map<String, CursorBean> pgCursorMap, JSONObject mapKeyObj, JSONObject inputDataObj, String fetchField) throws ApplicationException
	{
		CursorBean cursorBean = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		JSONObject dataObject = null;
		Object childKey = null;
		Object localKey = null;
		Object keyType = null;
		
		try
		{
			cursorBean = pgCursorMap.get(collectionName);
			if(cursorBean == null || cursorBean.getPgCursor() == null)
				return null;
			
			resultSet = cursorBean.getPgCursor();
			metaData = cursorBean.getMetaData();
			while(resultSet.next())
			{
				dataObject = Utility.convertToJSON(resultSet, metaData);
				
				childKey = mapKeyObj.get(DataConstants.KEY);
				localKey = mapKeyObj.get(DataConstants.LOCAL_KEY);
				keyType = mapKeyObj.get(DataConstants.TYPE) != null ? mapKeyObj.get(DataConstants.TYPE).toString() : "String";
				
				if(dataObject.get(childKey) == null || dataObject.get(childKey).toString().trim().isEmpty())
					return null;
				if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType.toString().trim()))
				{
					if(Long.parseLong(dataObject.get(childKey).toString().trim()) == Long.parseLong(inputDataObj.get(localKey.toString().trim()).toString().trim()))
						return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;
				}
				if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(keyType.toString().trim()))
				{
					if(Double.parseDouble(dataObject.get(childKey).toString().trim()) == Double.parseDouble(inputDataObj.get(localKey.toString().trim()).toString().trim()))
						return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;
				}
				else if(dataObject.get(childKey).toString().trim().equalsIgnoreCase(inputDataObj.get(localKey.toString().trim()).toString().trim()))
					return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;
					
				/*if(dataObject.get(queryField) == null || dataObject.get(queryField).toString().trim().isEmpty())
					return null;
				if(dataObject.get(queryField).toString().trim().equalsIgnoreCase(queryValue.toString().trim()))
					return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;*/
			}
			return null;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dataObject = null;
			metaData = null;
			resultSet = null;
			cursorBean = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getPGValues(String collectionName, Map<String, CursorBean> pgCursorMap, JSONObject mapKeyObj, JSONObject dataObj, String[] fetchFields, JSONObject fieldsConfiguration, Map<String, List<JSONObject>> lastFetchedObjectMap) throws ApplicationException
	{
		CursorBean cursorBean = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		JSONObject dbObject = null;
		Object childKey = null;
		Object localKey = null;
		Object keyType = null;
		JSONObject fieldConf = null;
		String field = null;
//		DateFormat dateFormat = null;
		List<JSONObject> lastFetchedList = null;
		Long cursorValue = null;
		Long objectValue = null;
		
//		TLogger.debug("collectionName :: " + collectionName + " dataObj :: " + dataObj + " fetchFields :: " + fetchFields);
		try
		{
			cursorBean = pgCursorMap.get(collectionName);
			if(cursorBean == null || cursorBean.getPgCursor() == null)
				return null;
			
			resultSet = cursorBean.getPgCursor();
			metaData = cursorBean.getMetaData();
			
//			TLogger.debug("mapKeyObj : " + mapKeyObj);
			lastFetchedList = lastFetchedObjectMap.get(collectionName);
			if(lastFetchedList != null && !lastFetchedList.isEmpty())
			{
				for(JSONObject lastFetchedDbObject : lastFetchedList)
				{
//					TLogger.debug("lastFetchedDbObject : " + lastFetchedDbObject);
					boolean matchFlag = false;
					
					childKey = mapKeyObj.get(DataConstants.KEY);
					localKey = mapKeyObj.get(DataConstants.LOCAL_KEY);
					keyType = mapKeyObj.get(DataConstants.TYPE) != null ? mapKeyObj.get(DataConstants.TYPE).toString() : "String";
					
					if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType.toString().trim()))
					{
						cursorValue = Long.parseLong(lastFetchedDbObject.get(childKey).toString().trim());
						objectValue = Long.parseLong(dataObj.get(localKey.toString().trim()).toString().trim());
						
//						TLogger.debug(cursorValue + " : " + objectValue);
						if(cursorValue.longValue() > objectValue.longValue())
							return dataObj;
						else if(cursorValue.longValue() == objectValue.longValue())
							matchFlag = true;
					}
					else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(keyType.toString().trim()) &&
							Double.parseDouble(lastFetchedDbObject.get(childKey).toString().trim()) == Double.parseDouble(dataObj.get(localKey.toString().trim()).toString().trim()))
						matchFlag = true;
					else
					{
						int compareValue = lastFetchedDbObject.get(childKey).toString().trim().compareTo(dataObj.get(localKey.toString().trim()).toString().trim());
						if(compareValue == 1)
						{
							lastFetchedDbObject = dbObject;
							return dataObj;
						}
						else if(compareValue == 0)
							matchFlag = true;
					}
					
//					TLogger.debug("matchFlag : " + matchFlag);
					if(matchFlag)
					{
						for(String fetchField : fetchFields)
						{
//							TLogger.debug("fetchField :: " + fetchField);
							fieldConf = (JSONObject) fieldsConfiguration.get(fetchField);
//							TLogger.debug("fieldConf : " + fieldConf);
							field = fieldConf.get(DataConstants.FIELD).toString().trim();
							if(!field.trim().isEmpty())
								dataObj.put(fetchField, lastFetchedDbObject.get(field) != null ? lastFetchedDbObject.get(field).toString().trim() : "");
							/*else if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
									&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
							{
								dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
								if(dateFormat == null)
								{
									dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
									dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
								}
								dataObj.put(fetchField, dateFormat.format(new Date()));
							}*/
						}
						lastFetchedList.remove(lastFetchedDbObject);
						lastFetchedObjectMap.put(collectionName, lastFetchedList);
						return dataObj;
					}
				}
			}
			
			while(resultSet.next())
			{
				boolean matchFlag = false;
				dbObject = Utility.convertToJSON(resultSet, metaData);
//				TLogger.debug("PG dbObject :: " + dbObject);
				
				childKey = mapKeyObj.get(DataConstants.KEY);
				localKey = mapKeyObj.get(DataConstants.LOCAL_KEY);
				keyType = mapKeyObj.get(DataConstants.TYPE) != null ? mapKeyObj.get(DataConstants.TYPE).toString() : "String";
				
				if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType.toString().trim()))
				{
					cursorValue = Long.parseLong(dbObject.get(childKey).toString().trim());
					objectValue = Long.parseLong(dataObj.get(localKey.toString().trim()).toString().trim());
					
//					TLogger.debug(cursorValue + " : " + objectValue);
					if(cursorValue.longValue() > objectValue.longValue())
					{
						lastFetchedList = lastFetchedObjectMap.get(collectionName);
						if(lastFetchedList == null)
							lastFetchedList = new ArrayList<JSONObject>();
						
						lastFetchedList.add(dbObject);
						lastFetchedObjectMap.put(collectionName, lastFetchedList);
						return dataObj;
					}
					else if(cursorValue.longValue() == objectValue.longValue())
						matchFlag = true;
				}
				else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(keyType.toString().trim()) &&
						Double.parseDouble(dbObject.get(childKey).toString().trim()) == Double.parseDouble(dataObj.get(localKey.toString().trim()).toString().trim()))
					matchFlag = true;
				else
				{
					int compareValue = dbObject.get(childKey).toString().trim().compareTo(dataObj.get(localKey.toString().trim()).toString().trim());
					if(compareValue == 1)
					{
						lastFetchedList = lastFetchedObjectMap.get(collectionName);
						if(lastFetchedList == null)
							lastFetchedList = new ArrayList<JSONObject>();
						
						lastFetchedList.add(dbObject);
						lastFetchedObjectMap.put(collectionName, lastFetchedList);
						return dataObj;
					}
					else if(compareValue == 0)
						matchFlag = true;
				}
//				TLogger.debug("matchFlag : " + matchFlag);
				if(matchFlag)
				{
					for(String fetchField : fetchFields)
					{
//						TLogger.debug("fetchField :: " + fetchField);
						fieldConf = (JSONObject) fieldsConfiguration.get(fetchField);
//						TLogger.debug("fieldConf : " + fieldConf);
						field = fieldConf.get(DataConstants.FIELD).toString().trim();
						if(!field.trim().isEmpty())
							dataObj.put(fetchField, dbObject.get(field) != null ? dbObject.get(field).toString().trim() : "");
						/*else if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
								&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
						{
							dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
							if(dateFormat == null)
							{
								dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
								dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
							}
							dataObj.put(fetchField, dateFormat.format(new Date()));
						}*/
					}
					return dataObj;
				}
			}
//			TLogger.debug("Return PG Object :: " + dataObj);
			return dataObj;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dbObject = null;
			metaData = null;
			resultSet = null;
			cursorBean = null;
			fieldConf = null;
			field = null;
			lastFetchedList = null;
			cursorValue = null;
			objectValue = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param collectionName
	 * @param mongoCursorMap
	 * @param mapKeyObj
	 * @param inputDataObj
	 * @param fieldConf
	 * @param lookupConf
	 * @param mongoTemplateMap
	 * @return Object
	 * @throws ApplicationException
	 */
	/*@SuppressWarnings("unchecked")
	public Object getMongoValue(String collectionName, Map<String, CursorBean> mongoCursorMap, JSONObject mapKeyObj, JSONObject inputDataObj, JSONObject fieldConf, JSONObject lookupConf, Map<String, MongoTemplate> mongoTemplateMap) throws ApplicationException
	{
		CursorBean cursorBean = null;
		DBCursor cursor = null;
		JSONObject dataObject = null;
		Object childKey = null;
		Object localKey = null;
		Object keyType = null;
		String fetchField = null;
//		TLogger.debug("collectionName :: " + collectionName + " inputDataObj :: " + inputDataObj + " fieldConf :: " + fieldConf);
		try
		{
			cursorBean = mongoCursorMap.get(collectionName);
			if(cursorBean == null || cursorBean.getMongoCursor() == null)
				return null;
			
			fetchField = fieldConf.get(DataConstants.FIELD).toString().trim();
			cursor = cursorBean.getMongoCursor();
			while(cursor.hasNext())
			{
				dataObject = new JSONObject();
				dataObject.putAll((BasicDBObject) cursor.next());
//				TLogger.debug("dataObject :: " + dataObject);
				if(mapKeyObj == null)
					return null;
				
				childKey = mapKeyObj.get(DataConstants.KEY);
				localKey = mapKeyObj.get(DataConstants.LOCAL_KEY);
				keyType = mapKeyObj.get(DataConstants.TYPE) != null ? mapKeyObj.get(DataConstants.TYPE).toString() : "String";
//				TLogger.debug("childKey : " + childKey + " localKey : " + localKey + " keyType : " + keyType);
				if(dataObject.get(childKey) == null || dataObject.get(childKey).toString().trim().isEmpty())
					return null;
				
				if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType.toString().trim()))
				{
					if(Long.parseLong(dataObject.get(childKey).toString().trim()) == Long.parseLong(inputDataObj.get(localKey.toString().trim()).toString().trim()))
					{
						if(fieldConf.get(DataConstants.F_LOOKUP) != null && !fieldConf.get(DataConstants.F_LOOKUP).toString().trim().isEmpty())
							return findLookup(fieldConf, lookupConf, mongoTemplateMap, dataObject.get(fetchField), fetchField);
						return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;
					}
				}
				if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(keyType.toString().trim()))
				{
					if(Double.parseDouble(dataObject.get(childKey).toString().trim()) == Double.parseDouble(inputDataObj.get(localKey.toString().trim()).toString().trim()))
					{
						if(fieldConf.get(DataConstants.F_LOOKUP) != null && !fieldConf.get(DataConstants.F_LOOKUP).toString().trim().isEmpty())
							return findLookup(fieldConf, lookupConf, mongoTemplateMap, dataObject.get(fetchField), fetchField);
						return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;
					}
				}
				else if(dataObject.get(childKey).toString().trim().equalsIgnoreCase(inputDataObj.get(localKey.toString().trim()).toString().trim()))
				{
					if(fieldConf.get(DataConstants.F_LOOKUP) != null && !fieldConf.get(DataConstants.F_LOOKUP).toString().trim().isEmpty())
						return findLookup(fieldConf, lookupConf, mongoTemplateMap, dataObject.get(fetchField), fetchField);
					return dataObject.get(fetchField) != null ? dataObject.get(fetchField).toString().trim() : null;
				}
			}
			return null;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			cursorBean = null;
			cursor = null;
			dataObject = null;
			childKey = null;
			localKey = null;
			keyType = null;
			fetchField = null;
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public JSONObject getMongoValues(String collectionName, Map<String, CursorBean> mongoCursorMap, JSONObject mapKeyObj, JSONObject dataObj, String[] fetchFields, JSONObject lookupConf, Map<String, MongoTemplate> mongoTemplateMap, JSONObject fieldsConfiguration, Map<String, List<JSONObject>> lastFetchedMongoMap, Map<String, DBObject> lookupValuesMap) throws ApplicationException
	{
		CursorBean cursorBean = null;
		DBCursor cursor = null;
		JSONObject dbObject = null;
		Object childKey = null;
		Object localKey = null;
		Object keyType = null;
		JSONObject fieldConf = null;
		String field = null;
//		DateFormat dateFormat = null;
		List<JSONObject> lastFetchedList = null;
		Long cursorValue = null;
		Long objectValue = null;
		
//		TLogger.debug("collectionName :: " + collectionName + " inputDataObj :: " + dataObj + " fetchFields :: " + fetchFields);
		try
		{
			cursorBean = mongoCursorMap.get(collectionName);
			if(cursorBean == null || cursorBean.getMongoCursor() == null)
				return dataObj;
			
			lastFetchedList = lastFetchedMongoMap.get(collectionName);
			if(lastFetchedList != null && !lastFetchedList.isEmpty())
			{
				for(JSONObject lastFetchedDbObject : lastFetchedList)
				{
//					TLogger.debug("lastFetchedDbObject : " + lastFetchedDbObject);
					boolean match = false;
					
					childKey = mapKeyObj.get(DataConstants.KEY);
					localKey = mapKeyObj.get(DataConstants.LOCAL_KEY);
					keyType = mapKeyObj.get(DataConstants.TYPE) != null ? mapKeyObj.get(DataConstants.TYPE).toString() : "String";
					
					if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType.toString().trim()))
					{
						cursorValue = Long.parseLong(lastFetchedDbObject.get(childKey).toString().trim());
						objectValue = Long.parseLong(dataObj.get(localKey.toString().trim()).toString().trim());
						
//						TLogger.debug(cursorValue + " : " + objectValue);
						if(cursorValue.longValue() > objectValue.longValue())
							return dataObj;
						else if(cursorValue.longValue() == objectValue.longValue())
							match = true;
					}
					if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(keyType.toString().trim()) &&
							Double.parseDouble(lastFetchedDbObject.get(childKey).toString().trim()) == Double.parseDouble(dataObj.get(localKey.toString().trim()).toString().trim()))
						match = true;
					else
					{
						int compareValue = lastFetchedDbObject.get(childKey).toString().trim().compareTo(dataObj.get(localKey.toString().trim()).toString().trim());
						if(compareValue == 1)
						{
							lastFetchedDbObject = dbObject;
							return dataObj;
						}
						else if(compareValue == 0)
							match = true;
					}
					
//					TLogger.debug("match : " + match);
					if(match)
					{
						for(String fetchField : fetchFields)
						{
//							TLogger.debug("fetchField :: " + fetchField);
							fieldConf = (JSONObject) fieldsConfiguration.get(fetchField);
							field = fieldConf.get(DataConstants.FIELD).toString().trim();
							if(!field.trim().isEmpty())
							{
								if(fieldConf.get(DataConstants.F_LOOKUP) != null && !fieldConf.get(DataConstants.F_LOOKUP).toString().trim().isEmpty() && !lastFetchedDbObject.get(field).toString().trim().isEmpty())
									dataObj.put(fetchField, findLookup(fieldConf, lookupConf, mongoTemplateMap, lastFetchedDbObject.get(field), field, lookupValuesMap));
								else
									dataObj.put(fetchField, lastFetchedDbObject.get(field) != null ? lastFetchedDbObject.get(field).toString().trim() : "");
							}
							/*else if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
									&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
							{
								dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
								if(dateFormat == null)
								{
									dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
									dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
								}
								dataObj.put(fetchField, dateFormat.format(new Date()));
							}*/
						}
						lastFetchedList.remove(lastFetchedDbObject);
						lastFetchedMongoMap.put(collectionName, lastFetchedList);
						return dataObj;
					}
				}
			}
			
			cursor = cursorBean.getMongoCursor();
			while(cursor.hasNext())
			{
				boolean match = false;
				dbObject = new JSONObject();
				dbObject.putAll((BasicDBObject) cursor.next());
//				TLogger.debug("Mongo dbObject :: " + dbObject);
				if(mapKeyObj == null)
					return dataObj;
				
				childKey = mapKeyObj.get(DataConstants.KEY);
				localKey = mapKeyObj.get(DataConstants.LOCAL_KEY);
				keyType = mapKeyObj.get(DataConstants.TYPE) != null ? mapKeyObj.get(DataConstants.TYPE).toString() : "String";
//				TLogger.debug("childKey : " + childKey + " localKey : " + localKey + " keyType : " + keyType);
				if(dbObject.get(childKey) == null || dbObject.get(childKey).toString().trim().isEmpty())
					return dataObj;
				
				if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType.toString().trim()))
				{
					cursorValue = Long.parseLong(dbObject.get(childKey).toString().trim());
					objectValue = Long.parseLong(dataObj.get(localKey.toString().trim()).toString().trim());
					
//					TLogger.debug(cursorValue + " : " + objectValue);
					if(cursorValue.longValue() > objectValue.longValue())
					{
						lastFetchedList = lastFetchedMongoMap.get(collectionName);
						if(lastFetchedList == null)
							lastFetchedList = new ArrayList<JSONObject>();
						
						lastFetchedList.add(dbObject);
						lastFetchedMongoMap.put(collectionName, lastFetchedList);
						return dataObj;
					}
					else if(cursorValue.longValue() == objectValue.longValue())
						match = true;
				}
				else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(keyType.toString().trim())  &&
						Double.parseDouble(dbObject.get(childKey).toString().trim()) == Double.parseDouble(dataObj.get(localKey.toString().trim()).toString().trim()))
					match = true;
				else
				{
					int compareValue = dbObject.get(childKey).toString().trim().compareTo(dataObj.get(localKey.toString().trim()).toString().trim());
					if(compareValue == 1)
					{
						lastFetchedList = lastFetchedMongoMap.get(collectionName);
						if(lastFetchedList == null)
							lastFetchedList = new ArrayList<JSONObject>();
						
						lastFetchedList.add(dbObject);
						lastFetchedMongoMap.put(collectionName, lastFetchedList);
						return dataObj;
					}
					else if(compareValue == 0)
						match = true;
				}
				
//				TLogger.debug("match : " + match);
				if(match)
				{
					for(String fetchField : fetchFields)
					{
//						TLogger.debug("fetchField :: " + fetchField);
						fieldConf = (JSONObject) fieldsConfiguration.get(fetchField);
						field = fieldConf.get(DataConstants.FIELD).toString().trim();
						if(!field.trim().isEmpty())
						{
							if(fieldConf.get(DataConstants.F_LOOKUP) != null && !fieldConf.get(DataConstants.F_LOOKUP).toString().trim().isEmpty() && dbObject.get(field) != null && !dbObject.get(field).toString().trim().isEmpty())
								dataObj.put(fetchField, findLookup(fieldConf, lookupConf, mongoTemplateMap, dbObject.get(field), field, lookupValuesMap));
							else
								dataObj.put(fetchField, dbObject.get(field) != null ? dbObject.get(field).toString().trim() : "");
						}
						/*else if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
								&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
						{
							dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
							if(dateFormat == null)
							{
								dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
								dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
							}
							dataObj.put(fetchField, dateFormat.format(new Date()));
						}*/
					}
//					TLogger.debug("Return Data Obj :: " + dataObj);
					return dataObj;
				}
			}
//			TLogger.debug("Return Data Obj :: " + dataObj);
			return dataObj;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			cursorBean = null;
			cursor = null;
			dbObject = null;
			childKey = null;
			localKey = null;
			keyType = null;
			fieldConf = null;
			field = null;
//			dateFormat = null;
			lastFetchedList = null;
			cursorValue = null;
			objectValue = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param mongoCursorMap
	 * @param dataObj
	 * @param fetchFields
	 * @param lookupConf
	 * @param mongoTemplateMap
	 * @param fieldsConfiguration
	 * @param dateFormatMap
	 * @return JSONObject
	 * @throws ApplicationException
	 */
	@SuppressWarnings("unchecked")
	public JSONObject getMongoValues(JSONObject dataObj, String[] fetchFields, JSONObject lookupConf, Map<String, MongoTemplate> mongoTemplateMap, JSONObject fieldsConfiguration, Map<String, DBObject> lookupValuesMap) throws ApplicationException
	{
		JSONObject fieldConf = null;
		String field = null;
//		DateFormat dateFormat = null;
//		TLogger.debug("collectionName :: " + collectionName + " inputDataObj :: " + dataObj + " fetchFields :: " + fetchFields);
		try
		{
			for(String fetchField : fetchFields)
			{
//				TLogger.debug("fetchField :: " + fetchField);
				fieldConf = (JSONObject) fieldsConfiguration.get(fetchField);
				field = fieldConf.get(DataConstants.FIELD).toString().trim();
				if(!field.trim().isEmpty())
				{
					if(fieldConf.get(DataConstants.F_LOOKUP) != null && !fieldConf.get(DataConstants.F_LOOKUP).toString().trim().isEmpty() 
							&& dataObj.get(field) != null && !dataObj.get(field).toString().trim().isEmpty())
						dataObj.put(fetchField, findLookup(fieldConf, lookupConf, mongoTemplateMap, dataObj.get(field), field, lookupValuesMap));
					else
						dataObj.put(fetchField, dataObj.get(field));
				}
				/*else if(fieldConf.get(DataConstants.FIELD).toString().trim().isEmpty() 
						&& fieldConf.get(DataConstants.DATE_FORMAT) != null && !fieldConf.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
				{
					dateFormat = dateFormatMap.get(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
					if(dateFormat == null)
					{
						dateFormat = new SimpleDateFormat(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim());
						dateFormatMap.put(fieldConf.get(DataConstants.DATE_FORMAT).toString().trim(), dateFormat);
					}
					dataObj.put(fetchField, dateFormat.format(new Date()));
				}*/
			}
//			TLogger.debug("Return Data Obj :: " + dataObj);
			return dataObj;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			fieldConf = null;
			field = null;
//			dateFormat = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param fieldConf
	 * @param lookupConf
	 * @param mongoTemplateMap
	 * @param value
	 * @param fetchField
	 * @return String
	 * @throws ApplicationException
	 */
	private String findLookup(JSONObject fieldConf, JSONObject lookupConf, Map<String, MongoTemplate> mongoTemplateMap, Object value, String fetchField, Map<String, DBObject> lookupValuesMap) throws ApplicationException
	{
		JSONObject fieldLookup = null;
		DBObject lookupDBObject = null;
		Object fieldValue = null;
		String lookupMapKey = null;
//		TLogger.debug("fieldConf : " + fieldConf + " value : " + value + " fetchField : " + fetchField);
		try
		{
			fieldValue = value;
			for(Object fieldLookupObj : (JSONArray) fieldConf.get(DataConstants.F_LOOKUP))
			{
				fieldLookup = (JSONObject) fieldLookupObj;
				lookupMapKey = fieldLookup.get(DataConstants.NAME).toString() + "~" + fetchField + "~" + fieldLookup.get(DataConstants.MAPPING_FIELD).toString() + "~" + fieldValue;
				TLogger.debug("lookupMapKey :: " + lookupMapKey);
				lookupDBObject = getLookupObject((JSONObject)lookupConf.get(fieldLookup.get(DataConstants.NAME).toString().trim()), fieldLookup, lookupValuesMap, lookupMapKey, fieldValue, mongoTemplateMap);
//				TLogger.debug("lookupDBObject : " + lookupDBObject);
				if(lookupDBObject != null)
				{
					if(fieldLookup.get(DataConstants.LOOKUP_FIELD) instanceof JSONArray)
					{
						fieldValue = "";
						String tempVal = null;
						for(Object lkpField : (JSONArray) fieldLookup.get(DataConstants.LOOKUP_FIELD))
						{
							tempVal = lookupDBObject.get(lkpField.toString()) != null ? lookupDBObject.get(lkpField.toString()).toString() : "";
							if(!tempVal.trim().isEmpty())
								fieldValue = fieldValue + tempVal + fieldLookup.get(DataConstants.FIELD_DELIMITER).toString();
						}
						tempVal = null;
						if(fieldValue != null)
						{
							fieldValue = fieldValue.toString().trim();
							if(fieldValue.toString().endsWith(fieldLookup.get(DataConstants.FIELD_DELIMITER).toString()))
								fieldValue = fieldValue.toString().substring(0, fieldValue.toString().lastIndexOf(fieldLookup.get(DataConstants.FIELD_DELIMITER).toString()));
						}
					}
					else
						fieldValue = lookupDBObject.get(fieldLookup.get(DataConstants.LOOKUP_FIELD).toString());
				}
				else
					return null;
			}
//			TLogger.debug(" fieldValue " + fieldValue != null ? fieldValue.toString().trim() : null);
			return fieldValue != null ? fieldValue.toString().trim() : null;
		}
		catch (Exception exception) 
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			fieldLookup = null;
			lookupDBObject = null;
			lookupMapKey = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param jsonConfig
	 * @return Map<String, JSONObject>
	 * @throws ApplicationException
	 */
	public Map<String, JSONObject> getCollectionConfs(JSONArray jsonConfig) throws ApplicationException
	{
		JSONObject collectionConf = null;
		Map<String, JSONObject> collectionConfMap = null;
		
		try
		{
			collectionConfMap = new HashMap<String, JSONObject>();
			for(Object object : jsonConfig)
			{
				collectionConf = (JSONObject) object;
				collectionConfMap.put(collectionConf.get(DataConstants.COLLECTION_NAME).toString().trim(), collectionConf);
			}
			return collectionConfMap;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			collectionConf = null;
			collectionConfMap = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param lookupObject
	 * @param fieldLookupObject
	 * @param lookupMap
	 * @param lookupMapKey
	 * @param value
	 * @param mongoTemplateMap
	 * @return DBObject
	 * @throws GenericProcessorException
	 */
	private DBObject getLookupObject(JSONObject lookupObject, JSONObject fieldLookupObject, Map<String, DBObject> lookupValuesMap, String lookupMapKey, Object value, Map<String, MongoTemplate> mongoTemplateMap) throws GenericProcessorException
	{
		DBObject lookupDBObject = null;
		BasicDBObject queryObject = null;
		Long cacheSize = null;
		MongoTemplate template = null;
		DBCollection collection = null;
		
		try
		{
			TLogger.debug(lookupMapKey + " : lookupMap.containsKey(lookupMapKey) : " + lookupValuesMap.containsKey(lookupMapKey) + "lookupValuesMap size : " + lookupValuesMap.size());
			if(lookupValuesMap.containsKey(lookupMapKey))
			{
				lookupDBObject = lookupValuesMap.get(lookupMapKey);
				TLogger.debug("Value from Map..");
			}
			else
			{
				TLogger.debug("Value from Collection..");
				cacheSize = Long.parseLong(PropertiesLoader.getValueFor("LOOKUP_CACHE_SIZE"));
				queryObject = new BasicDBObject();
				if(fieldLookupObject.get(DataConstants.QUERY) != null)
					queryObject = (BasicDBObject) JSON.parse(fieldLookupObject.get(DataConstants.QUERY).toString());
				if(lookupObject.get(DataConstants.PARAMETERS) != null)
					prepareQueryWithParams(lookupObject, queryObject);
				if(fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE) != null && fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE).toString().equalsIgnoreCase(DataConstants.LONG_TYPE))
					queryObject.put(fieldLookupObject.get(DataConstants.MAPPING_FIELD).toString(), Long.parseLong(value.toString()));
				else
					queryObject.put(fieldLookupObject.get(DataConstants.MAPPING_FIELD).toString(), value);
				
				/*if(collection == null)
				{
					log.info("lookupObject : " + lookupObject + " : applicationContext : " + applicationContext + " : mongoConnectionUtil : " + mongoConnectionUtil);
					
					collection = template.getCollection(lookupObject.get(DataConstants.SCHEMA_NAME).toString(), lookupObject.get(DataConstants.COLLECTION_NAME).toString());
				}*/
				template = mongoTemplateMap.get(lookupObject.get(DataConstants.CONNECTION_ID).toString());
				collection = template.getCollection(lookupObject.get(DataConstants.SCHEMA_NAME).toString(), lookupObject.get(DataConstants.COLLECTION_NAME).toString());
//				TLogger.debug(collection + " Lookup query : " + queryObject);
				if(fieldLookupObject.get(DataConstants.LOOKUP_FUNCTION) != null && fieldLookupObject.get(DataConstants.LOOKUP_FUNCTION).toString().trim().equalsIgnoreCase(DataConstants.COUNT))
				{
					int count = collection.find(queryObject).count();
//					TLogger.debug("count : " + count);
					lookupDBObject = new BasicDBObject();
					lookupDBObject.put(fieldLookupObject.get(DataConstants.LOOKUP_FIELD).toString().trim(), count);
				}
				else 
					lookupDBObject = collection.findOne(queryObject);
				/*if(lookupObject.get(DataConstants.CACHE_REQUIRED) != null && !lookupObject.get(DataConstants.CACHE_REQUIRED).toString().trim().isEmpty() 
						&& "True".equalsIgnoreCase(lookupObject.get(DataConstants.CACHE_REQUIRED).toString().trim()))*/
				
//				if(lookupDBObject != null)
//				{
					TLogger.debug("Adding lookupObject to Cache.." + lookupMapKey);
					if(lookupValuesMap.size() >= cacheSize)
						lookupValuesMap.remove(lookupValuesMap.keySet().iterator().next());
					lookupValuesMap.put(lookupMapKey, lookupDBObject);
//				}
			}
			
//			log.info("lookupDBObject from DB : " + lookupDBObject);
			return lookupDBObject;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			lookupDBObject = null;
			queryObject = null;
			cacheSize = null;
			template = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param lookupObject
	 * @param fieldLookupObject
	 * @param dataObject
	 * @param fieldArr
	 * @param mongoTemplateMap
	 * @return DBObject
	 * @throws GenericProcessorException
	 */
	@SuppressWarnings("unused")
	private DBObject getLookupObject(JSONObject lookupObject, JSONObject fieldLookupObject, JSONObject dataObject, JSONArray fieldArr, Map<String, MongoTemplate> mongoTemplateMap) throws GenericProcessorException
	{
		DBObject lookupDBObject = null;
		BasicDBObject queryObject = null;
		MongoTemplate template = null;
		DBCollection collection = null;
		JSONArray mappingArr = null;
		
		try
		{
			if(fieldLookupObject.get(DataConstants.MAPPING_FIELD) instanceof JSONArray)
				mappingArr = (JSONArray) fieldLookupObject.get(DataConstants.MAPPING_FIELD);
			
//			TLogger.info("mappingArr : " + mappingArr + " : fieldArr : " + fieldArr);
			if(mappingArr.size() != fieldArr.size())
				return null;
			
			queryObject = new BasicDBObject();
			if(fieldLookupObject.get(DataConstants.QUERY) != null)
				queryObject = (BasicDBObject) JSON.parse(fieldLookupObject.get(DataConstants.QUERY).toString());
			if(lookupObject.get(DataConstants.PARAMETERS) != null)
				prepareQueryWithParams(lookupObject, queryObject);
			if(fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE) != null && fieldLookupObject.get(DataConstants.MAPPING_FIELD_TYPE).toString().equalsIgnoreCase(DataConstants.LONG_TYPE))
			{
				for(int i = 0; i < mappingArr.size(); i ++)
				{
					queryObject.put(mappingArr.get(i).toString(), Long.parseLong(dataObject.get(fieldArr.get(i).toString()).toString()));
				}
			}
			else
			{
				for(int i = 0; i < mappingArr.size(); i ++)
				{
					queryObject.put(mappingArr.get(i).toString(), dataObject.get(fieldArr.get(i).toString()));
				}
			}
			
			template = mongoTemplateMap.get(lookupObject.get(DataConstants.CONNECTION_ID).toString());
			collection = template.getCollection(lookupObject.get(DataConstants.SCHEMA_NAME).toString(), lookupObject.get(DataConstants.COLLECTION_NAME).toString());
//			TLogger.debug(collection + "Lookup query : " + queryObject);
			if(fieldLookupObject.get(DataConstants.LOOKUP_FUNCTION) != null && fieldLookupObject.get(DataConstants.LOOKUP_FUNCTION).toString().trim().equalsIgnoreCase(DataConstants.COUNT))
			{
				int count = collection.find(queryObject).count();
//				TLogger.debug("count : " + count);
				lookupDBObject = new BasicDBObject();
				lookupDBObject.put(fieldLookupObject.get(DataConstants.LOOKUP_FIELD).toString().trim(), count);
			}
			else 
				lookupDBObject = collection.findOne(queryObject);
//			log.info("lookupDBObject from DB : " + lookupDBObject);
			return lookupDBObject;
		}
		catch(Exception exception)
		{
			TLogger.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			lookupDBObject = null;
			queryObject = null;
			template = null;
			mappingArr = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void prepareQueryWithParams(JSONObject configObject, BasicDBObject queryObject) throws GenericProcessorException
	{
		JSONObject paramObject = null;
		JSONArray paramValues = null;
		JSONArray valueArr = null;
		BasicDBObject dateQuery = null;
		DateFormat dateFormat = null;
		DateFormat fromFormat = null;
		DateFormat toFormat = null;
		Date startDate = null;
		Date endDate = null;
		Calendar calendar = null;
		Calendar calendar2 = null;
		
		try
		{
			for(Object param : (JSONArray) configObject.get(DataConstants.PARAMETERS))
			{
				paramObject = (JSONObject) param;
				if(paramObject != null && paramObject.keySet().size() > 0)
				{
					if((paramObject.get(DataConstants.MANDATORY) != null && DataConstants.FALSE.equalsIgnoreCase(paramObject.get(DataConstants.MANDATORY).toString().trim())) 
							&& (paramObject.get(DataConstants.PARAM_VALUE) == null || paramObject.get(DataConstants.PARAM_VALUE).toString().trim().isEmpty()))
						continue;
					if(paramObject.get(DataConstants.PARAM_TYPE) != null)
					{
						if(DataConstants.DATE_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
						{
							if(paramObject.get(DataConstants.FROM_DATE_FORMAT) != null && !paramObject.get(DataConstants.FROM_DATE_FORMAT).toString().trim().isEmpty())
							{
								fromFormat = new SimpleDateFormat(paramObject.get(DataConstants.FROM_DATE_FORMAT).toString());
								if(fromFormat != null)
								{
									if(paramObject.get(DataConstants.TO_DATE_FORMAT) == null || paramObject.get(DataConstants.TO_DATE_FORMAT).toString().trim().isEmpty())
									{
										TLogger.error("To Date Format should not be empty..");
										throw new GenericProcessorException("To Date Format should not be empty..", null);
									}
									toFormat = new SimpleDateFormat(paramObject.get(DataConstants.TO_DATE_FORMAT).toString());
									
									if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
									{
										paramValues = (JSONArray) paramObject.get(DataConstants.PARAM_VALUE);
										if(paramValues.size() != 2)
										{
											TLogger.error("Date params should be 2 always.." + paramValues);
											throw new GenericProcessorException("Date params should be 2 always.." + paramValues, null);
										}
										
										startDate = toFormat.parse(toFormat.format(fromFormat.parse(paramValues.get(0).toString())));
										endDate = toFormat.parse(toFormat.format(fromFormat.parse(paramValues.get(1).toString())));
										
										dateQuery = new BasicDBObject("$gte", Utility.getStartOfTheDay(startDate)).append("$lte", Utility.getEndOfTheDay(endDate));
										queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
									}
									else
									{
										startDate = toFormat.parse(toFormat.format(fromFormat.parse(paramObject.get(DataConstants.PARAM_VALUE).toString())));
										endDate = toFormat.parse(toFormat.format(fromFormat.parse(paramObject.get(DataConstants.PARAM_VALUE).toString())));
										
										dateQuery = new BasicDBObject("$gte", Utility.getStartOfTheDay(startDate)).append("$lte", Utility.getEndOfTheDay(endDate));
										queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
									}
								}
							}
							else
							{
								if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
								{
									paramValues = (JSONArray) paramObject.get(DataConstants.PARAM_VALUE);
									if(paramValues.size() != 2)
									{
										TLogger.error("Date params should be 2 always.." + paramValues);
										throw new GenericProcessorException("Date params should be 2 always.." + paramValues, null);
									}
									
									calendar = Calendar.getInstance();
									calendar2 = Calendar.getInstance();
									calendar.add(Calendar.DATE, - Integer.parseInt(paramValues.get(0).toString()));
									calendar2.add(Calendar.DATE, - Integer.parseInt(paramValues.get(1).toString()));
									dateQuery = new BasicDBObject("$gte", Utility.getStartOfTheDay(calendar.getTime())).append("$lte", Utility.getEndOfTheDay(calendar2.getTime()));
									queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
								}
								else
								{
									calendar = Calendar.getInstance();
									calendar.add(Calendar.DATE, - Integer.parseInt(paramObject.get(DataConstants.PARAM_VALUE).toString()));
									dateQuery = new BasicDBObject("$gte", Utility.getStartOfTheDay(calendar.getTime())).append("$lte", Utility.getEndOfTheDay(calendar.getTime()));
									queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
								}
							}
						}
						else if(paramObject.get(DataConstants.DATE_FORMAT) != null && !paramObject.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty() 
								&& DataConstants.DATE_NUM.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
						{
							if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
							{
								paramValues = (JSONArray) paramObject.get(DataConstants.PARAM_VALUE);
								if(paramValues.size() != 2)
								{
									TLogger.error("Date params should be 2 always.." + paramValues);
									throw new GenericProcessorException("Date params should be 2 always.." + paramValues, null);
								}
								
								calendar = Calendar.getInstance();
								calendar2 = Calendar.getInstance();
								calendar.add(Calendar.DATE, - Integer.parseInt(paramValues.get(0).toString()));
								calendar2.add(Calendar.DATE, - Integer.parseInt(paramValues.get(1).toString()));
								dateFormat = new SimpleDateFormat(paramObject.get(DataConstants.DATE_FORMAT).toString().trim());
								dateQuery = new BasicDBObject("$gte", Long.valueOf(dateFormat.format(calendar.getTime()))).append("$lte", Long.valueOf(dateFormat.format(calendar2.getTime())));
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), dateQuery);
							}
							else
							{
								calendar = Calendar.getInstance();
								calendar.add(Calendar.DATE, - Integer.parseInt(paramObject.get(DataConstants.PARAM_VALUE).toString()));
								dateFormat = new SimpleDateFormat(paramObject.get(DataConstants.DATE_FORMAT).toString().trim());
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), Long.valueOf(dateFormat.format(calendar.getTime())));
							}
						}
						else if(DataConstants.LONG_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.PARAM_TYPE).toString()))
						{
							if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
							{
								valueArr = new JSONArray();
								for(Object pValue : (JSONArray) paramObject.get(DataConstants.PARAM_VALUE))
								{
									valueArr.add(Long.parseLong(pValue.toString().trim()));
								}
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), new BasicDBObject("$in", valueArr));
							}
							else
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), Long.parseLong(paramObject.get(DataConstants.PARAM_VALUE).toString()));
						}
						else
						{
							if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), new BasicDBObject("$in", (JSONArray) paramObject.get(DataConstants.PARAM_VALUE)));
							else
								queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), paramObject.get(DataConstants.PARAM_VALUE));
						}
					}
					else
					{
						if(paramObject.get(DataConstants.PARAM_VALUE) instanceof JSONArray)
							queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), new BasicDBObject("$in", (JSONArray) paramObject.get(DataConstants.PARAM_VALUE)));
						else
							queryObject.put(paramObject.get(DataConstants.PARAM_NAME).toString(), paramObject.get(DataConstants.PARAM_VALUE));
					}
				}
			}
		}
		catch(Exception exception)
		{
			TLogger.error(exception.getMessage(), exception);
			throw new GenericProcessorException(exception.getMessage(), exception);
		}
		finally
		{
			paramObject = null;
			paramValues = null;
			valueArr = null;
			dateQuery = null;
			dateFormat = null;
			fromFormat = null;
			toFormat = null;
			startDate = null;
			endDate = null;
			calendar = null;
			calendar2 = null;
		}
	}
	
//	private Map<String, DBObject> lookupValuesMap = new HashMap<String, DBObject>();
}