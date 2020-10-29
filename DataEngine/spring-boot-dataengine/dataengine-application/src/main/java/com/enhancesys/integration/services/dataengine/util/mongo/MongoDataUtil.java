package com.enhancesys.integration.services.dataengine.util.mongo;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;

public class MongoDataUtil 
{
	@Autowired 
	private ApplicationContext applicationContext;
	
	@Autowired
	MongoConnectionUtil mongoConnectionUtil;
	
	private static Logger log = Logger.getLogger(MongoDataUtil.class);
	
	public DBCursor getCursor(JSONObject configObject, boolean sortFlag) throws GenericProcessorException
	{
		log.info("Entry getCursor..");
		
		DBCursor cursor = null;
		MongoTemplate template = null;
		DBCollection collection = null;
		BasicDBObject queryObject = null;
		BasicDBObject projectionObject = null;
		Calendar calendar = null;
		DateFormat dateFormat = null;
		String collectionName = null;
		
		try
		{
			if(configObject == null)
			{
				log.info("Configuration object is empty..");
				return null;
			}
			queryObject = new BasicDBObject();
			if(configObject.get(DataConstants.QUERY) != null && !configObject.get(DataConstants.QUERY).toString().trim().isEmpty())
				queryObject = (BasicDBObject) JSON.parse(configObject.get(DataConstants.QUERY).toString());
			if(configObject.containsKey(DataConstants.PARAMETERS))
				Utilities.prepareQueryWithParams(configObject, queryObject, null);
		
			projectionObject = new BasicDBObject();
			if(configObject.get(DataConstants.PROJECTION) != null)
				projectionObject = (BasicDBObject) JSON.parse(configObject.get(DataConstants.PROJECTION).toString());
			
			template = mongoConnectionUtil.getConnection(applicationContext, configObject.get(DataConstants.CONNECTION_ID).toString());
			if(template == null)
			{
				log.error("Mongo Connection not available.. " + configObject.get(DataConstants.CONNECTION_ID));
				throw new GenericProcessorException("Mongo Connection not available.. " + configObject.get(DataConstants.CONNECTION_ID));
			}
			collectionName = configObject.get(DataConstants.COLLECTION_NAME).toString();
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
			collection = template.getCollection(configObject.get(DataConstants.SCHEMA_NAME).toString(), collectionName);
			
			log.info("queryObject : " + queryObject);
			if(sortFlag && configObject.get(DataConstants.SORT_BY) != null && !configObject.get(DataConstants.SORT_BY).toString().trim().isEmpty())
			{
				if(configObject.get(DataConstants.LIMIT) != null)
					cursor = collection.find(queryObject, projectionObject).addOption(Bytes.QUERYOPTION_NOTIMEOUT).sort((DBObject)JSON.parse(configObject.get(DataConstants.SORT_BY).toString())).limit(Integer.parseInt(configObject.get(DataConstants.LIMIT).toString()));
				else
					cursor = collection.find(queryObject, projectionObject).addOption(Bytes.QUERYOPTION_NOTIMEOUT).sort((DBObject)JSON.parse(configObject.get(DataConstants.SORT_BY).toString()));
			}
			else
			{
				if(configObject.get(DataConstants.LIMIT) != null)
					cursor = collection.find(queryObject, projectionObject).limit(Integer.parseInt(configObject.get(DataConstants.LIMIT).toString()));
				else if(configObject.get(DataConstants.BATCH_SIZE) != null)
					cursor = collection.find(queryObject, projectionObject).batchSize(Integer.parseInt(configObject.get(DataConstants.BATCH_SIZE).toString()));
				else
					cursor = collection.find(queryObject, projectionObject).batchSize(Integer.parseInt(DataConstants.MONGO_QUERY_BATCH_SIZE));
			}
			return cursor;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			cursor = null;
			template = null;
			collection = null;
			queryObject = null;
			projectionObject = null;
			calendar = null;
			dateFormat = null;
			collectionName = null;
			
			log.info("Exit getCursor..");
		}
	}
	
	public AggregationOutput getAggregateCursor(JSONObject configObject, boolean sortFlag) throws GenericProcessorException
	{
		log.info("Entry getAggregateCursor..");
		
		AggregationOutput aggregationOutput = null;
		List<DBObject> pipeline = null;
		Object aggregateObj = null;
		MongoTemplate template = null;
		DBCollection collection = null;
		Calendar calendar = null;
		DateFormat dateFormat = null;
		String collectionName = null;
		
		try
		{
			if(configObject == null)
			{
				log.info("Configuration object is empty..");
				return null;
			}
			
			pipeline = new ArrayList<DBObject>();
			log.info("configObject.get(DataConstants.AGGREGATE_QUERY) : " + configObject.get(DataConstants.AGGREGATE_QUERY));
			if(configObject.get(DataConstants.AGGREGATE_QUERY) != null && !configObject.get(DataConstants.AGGREGATE_QUERY).toString().trim().isEmpty())
			{
				aggregateObj = JSON.parse(configObject.get(DataConstants.AGGREGATE_QUERY).toString());
				log.info("aggregateObj : " + aggregateObj.getClass());
				if(aggregateObj instanceof BasicDBList)
				{
					for(Object obj : (BasicDBList) aggregateObj)
					{
						pipeline.add((BasicDBObject)obj);
					}
				}
				else if(aggregateObj instanceof JSONArray)
				{
					for(Object obj : (JSONArray)aggregateObj)
					{
						pipeline.add((BasicDBObject) obj);
					}
				}
				else if(aggregateObj instanceof JSONObject || aggregateObj instanceof BasicDBObject)
				{
					pipeline.add((BasicDBObject) aggregateObj);
				}
			}
			log.info("pipeline : " + pipeline);

			template = mongoConnectionUtil.getConnection(applicationContext, configObject.get(DataConstants.CONNECTION_ID).toString());
			if(template == null)
			{
				log.error("Mongo Connection not available.. " + configObject.get(DataConstants.CONNECTION_ID));
				throw new GenericProcessorException("Mongo Connection not available.. " + configObject.get(DataConstants.CONNECTION_ID));
			}
			collectionName = configObject.get(DataConstants.COLLECTION_NAME).toString();
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
			collection = template.getCollection(configObject.get(DataConstants.SCHEMA_NAME).toString(), collectionName);
			aggregationOutput = collection.aggregate(pipeline);

			return aggregationOutput;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			template = null;
			collection = null;
			calendar = null;
			dateFormat = null;
			collectionName = null;
			
			log.info("Exit getAggregateCursor..");
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param connectionId
	 * @param fileType
	 * @param fileName
	 * @param file
	 * @param requestId
	 * @param requestUpdateConf
	 * @return 
	 * @throws GenericProcessorException
	 */
	public void storeFile(String connectionId, String schema, String fileType, String fileName, File file, Object requestId, Object requestUpdateConf, Long status) throws GenericProcessorException
	{
		MongoTemplate mongoTemplate = null;
		DB db = null;
		GridFS gridFS = null;
		GridFSInputFile gridFSInputFile = null;
		Object objectId = null;
		
		try
		{
			mongoTemplate = mongoConnectionUtil.getConnection(applicationContext, connectionId);
			db = mongoTemplate.getDB(schema);
			gridFS = new GridFS(db);
			gridFSInputFile = gridFS.createFile(file);
			gridFSInputFile.setFilename(fileName);
			gridFSInputFile.setContentType(fileType);
			gridFSInputFile.save();
			objectId = gridFSInputFile.getId();
//			log.info("File chunks object id : " + objectId);
			
			if(requestId != null && !requestId.toString().trim().isEmpty() && requestUpdateConf != null && !requestUpdateConf.toString().trim().isEmpty())
				updateRequest(requestId, requestUpdateConf, objectId, status, fileType, true);
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			mongoTemplate = null;
			db = null;
			gridFS = null;
			gridFSInputFile = null;
			objectId = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 	
	 * </pre> 
	 * @param requestId
	 * @param requestUpdateConf
	 * @param chunksFileId
	 * @param status
	 * @throws GenericProcessorException
	 */
	public void updateRequest(Object requestId, Object requestUpdateConf, Object chunksFileId, Long status, String fileType, boolean storeFileFlag) throws GenericProcessorException
	{
		MongoTemplate mongoTemplate = null;
		DBCollection collection = null;
		BasicDBObject queryObject = null;
		BasicDBObject dbObject = null;
		JSONObject requestUpdateConfig = null;
		
		try
		{
			if(requestId != null && !requestId.toString().trim().isEmpty() && requestUpdateConf != null && !requestUpdateConf.toString().trim().isEmpty())
			{
				requestUpdateConfig = (JSONObject) requestUpdateConf;
				mongoTemplate = mongoConnectionUtil.getConnection(applicationContext, requestUpdateConfig.get(DataConstants.CONNECTION_ID).toString());
				collection = mongoTemplate.getCollection(requestUpdateConfig.get(DataConstants.SCHEMA_NAME).toString(), requestUpdateConfig.get(DataConstants.COLLECTION_NAME).toString());
				queryObject = new BasicDBObject();
				queryObject.put(DataConstants.F_ID, Long.parseLong(requestId.toString()));
//				log.info("queryObject : " + queryObject);
				dbObject = (BasicDBObject) collection.findOne(queryObject);
//				log.info("dbObejct : " + dbObject);
				if(dbObject == null)
					log.error("Record doesn't exist with given id : " + requestId.toString() + " in collection : " + requestUpdateConfig.get(DataConstants.COLLECTION_NAME));
				else
				{
					dbObject.put(DataConstants.F_STATUS, status);
					dbObject.put(DataConstants.F_UPDATED_DATE, new Date());
					dbObject.put(DataConstants.F_FILE_TYPE, fileType);
					if(storeFileFlag)
					{
						if(chunksFileId != null)
							dbObject.put(requestUpdateConfig.get(DataConstants.FIELD).toString(), chunksFileId);
						else
							dbObject.put(DataConstants.F_STATUS, DataConstants.STATUS_CANCELLED);
					}
					
					collection.update(queryObject, dbObject);
				}
			}
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			mongoTemplate = null;
			collection = null;
			queryObject = null;
			dbObject = null;
			requestUpdateConfig = null;
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 	
	 * </pre>
	 * @param requestId
	 * @param requestUpdateConf
	 * @param status
	 * @throws GenericProcessorException
	 */
	public void updateStatus(Object requestId, Object requestUpdateConf, Long status) throws GenericProcessorException
	{
		MongoTemplate mongoTemplate = null;
		DBCollection collection = null;
		BasicDBObject queryObject = null;
		BasicDBObject dbObject = null;
		JSONObject requestUpdateConfig = null;
		
		try
		{
			log.info("UpdateStatus : " + requestId + " : Status : " + status + "   requestUpdateConf :: " + requestUpdateConf);
			if(requestId != null && !requestId.toString().trim().isEmpty() && requestUpdateConf != null && !requestUpdateConf.toString().trim().isEmpty())
			{
				requestUpdateConfig = (JSONObject) requestUpdateConf;
				mongoTemplate = mongoConnectionUtil.getConnection(applicationContext, requestUpdateConfig.get(DataConstants.CONNECTION_ID).toString());
				collection = mongoTemplate.getCollection(requestUpdateConfig.get(DataConstants.SCHEMA_NAME).toString(), requestUpdateConfig.get(DataConstants.COLLECTION_NAME).toString());
				queryObject = new BasicDBObject();
				queryObject.put(DataConstants.F_ID, Long.parseLong(requestId.toString()));
//				log.info("queryObject : " + queryObject);
				dbObject = (BasicDBObject) collection.findOne(queryObject);
//				log.info("dbObejct : " + dbObject);
				if(dbObject == null)
					log.error("Record doesn't exist with given id : " + requestId.toString() + " in collection : " + requestUpdateConfig.get(DataConstants.COLLECTION_NAME));
				else
				{
					dbObject.put(DataConstants.F_STATUS, status);
					dbObject.put(DataConstants.F_UPDATED_DATE, new Date());
					collection.update(queryObject, dbObject);
				}
			}
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 	
	 * </pre>
	 * @param queryObject
	 * @param dbObject
	 * @param connectionId
	 * @param schema
	 * @param collectionName
	 * @throws GenericProcessorException
	 */
	public void updateDocument(BasicDBObject queryObject, DBObject dbObject, String connectionId, String schema, String collectionName) throws GenericProcessorException
	{
		DBCollection collection = null;
		MongoTemplate mongoTemplate = null;
		
		try
		{
			mongoTemplate = mongoConnectionUtil.getConnection(applicationContext, connectionId);
			collection = mongoTemplate.getCollection(schema, collectionName);
			collection.update(queryObject, dbObject);
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			collection = null;
			mongoTemplate = null;
		}
	}
}