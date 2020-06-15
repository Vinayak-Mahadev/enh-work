package com.enhancesys.interfaces.snoc.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

@Service
public class TokuDAO {

	private static Logger log = Logger.getLogger(TokuDAO.class);
	
	public TokuDAO(){}
	
	/*
	 * Supports to TokuMX Only
	 * Not Supports to MongoDB
	 */
	public CommandResult beginTransaction(MongoClient mongoClient, String schemaName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			schema.requestStart();
			return schema.command(Constants.T_BEGIN_TRANSACTION);
		}catch(NullPointerException nullPointerException){
			log.error("beginTransaction:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("beginTransaction:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("beginTransaction:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/*
	 * Supports to TokuMX Only
	 * Not Supports to MongoDB
	 */
	public CommandResult commitTransaction(MongoClient mongoClient, String schemaName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			CommandResult cmdRslt = schema.command(Constants.T_COMMIT_TRANSACTION);
			schema.requestDone();
			return cmdRslt;
		}catch(NullPointerException nullPointerException){
			log.error("commitTransaction:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("commitTransaction:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("commitTransaction:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/*
	 * Supports to TokuMX Only
	 * Not Supports to MongoDB
	 */
	public CommandResult rollbackTransaction(MongoClient mongoClient, String schemaName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			CommandResult cmdRslt =schema.command(Constants.T_ROLLBACK_TRANSACTION);
			schema.requestDone();
			return cmdRslt;
		}catch(NullPointerException nullPointerException){
			log.error("rollbackTransaction:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("rollbackTransaction:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("rollbackTransaction:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public BulkWriteOperation initializeUnorderedBulkOperation(MongoClient mongoClient, String schemaName, String collectionName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.initializeUnorderedBulkOperation();
		}catch(NullPointerException nullPointerException){
			log.error("insertDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("insertDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("insertDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public BulkWriteOperation initializeOrderedBulkOperation(MongoClient mongoClient, String schemaName, String collectionName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.initializeOrderedBulkOperation();
		}catch(NullPointerException nullPointerException){
			log.error("insertDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("insertDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("insertDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public BulkWriteResult executeBulkOperation(BulkWriteOperation bulkWriteOperation) throws Exception{
		try{
			return bulkWriteOperation.execute();
		}catch(NullPointerException nullPointerException){
			log.error("insertDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("insertDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("insertDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult insertDocumentObject(MongoClient mongoClient, String schemaName, String collectionName, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.insert(documnetObject);
		}catch(NullPointerException nullPointerException){
			log.error("insertDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("insertDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("insertDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	
	public WriteResult insertDocumentsList(MongoClient mongoClient, String schemaName, String collectionName, List<DBObject> documentsList) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.insert(documentsList);
		}catch(NullPointerException nullPointerException){
			log.error("insertDocumentsList:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("insertDocumentsList:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("insertDocumentsList:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult upsertDocumentObject(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.update(queryObject, documnetObject, true, false);
		}catch(NullPointerException nullPointerException){
			log.error("upsertDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("upsertDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("upsertDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public DBObject findAndModifyDocument(MongoClient mongoClient, String schemaName, String collectionName,DBObject queryObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        //return collection.findAndModify(queryObject,documnetObject);
			return collection.findAndModify(queryObject, null, null, false, documnetObject, true, false);
		}catch(NullPointerException nullPointerException){
			log.error("findAndModifyDocument:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("findAndModifyDocument:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("findAndModifyDocument:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult updateDocumentObject(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.update(queryObject, documnetObject);
		}catch(NullPointerException nullPointerException){
			log.error("updateDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("updateDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("updateDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult updateByModifyObjectfromArray(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.update(queryObject, documnetObject);
		}catch(NullPointerException nullPointerException){
			log.error("updateByModifyObjectfromArray:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("updateByModifyObjectfromArray:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("updateByModifyObjectfromArray:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult updateByAddingObjectToArray(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject, boolean upsert,boolean muilti) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.update(queryObject, documnetObject, upsert, muilti);
		}catch(NullPointerException nullPointerException){
			log.error("updateByAddingObjectToArray:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("updateByAddingObjectToArray:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("updateByAddingObjectToArray:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult updateByRemoveObjectFromArray(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject, boolean upsert,boolean muilti) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.update(queryObject, documnetObject, upsert, muilti);
		}catch(NullPointerException nullPointerException){
			log.error("updateByRemoveObjectFromArray:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("updateByRemoveObjectFromArray:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("updateByRemoveObjectFromArray:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	//added by sai
	public WriteResult updateMultiDocumentObject(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.update(queryObject, documnetObject,false,true);
		}catch(NullPointerException nullPointerException){
			log.error("updateDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage(), nullPointerException);
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("updateDocumentObject:: MongoException Message is: >> "+mongoException.getMessage(), mongoException);
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("updateDocumentObject:: Exception Message is: >> "+exception.getMessage(), exception);
			throw exception;
		}
	}
	
	public DBObject getDocumentbyId(MongoClient mongoClient, String schemaName, String collectionName, DBObject idObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.findOne(idObject);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentbyId:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentbyId:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentbyId:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public DBCursor getDocumentsbyQuery(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.find(queryObject);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/**
	 * 
	 * Note: As of now, We are using 'TokuSeqUser' mongoClient inside this component. So if any mongoClient you pass, it will use 'TokuSeqUser' mongoClient. 
	 * 
	 * @param mongoClient [Constant: TokuSeqUser]
	 * @param collectionName
	 * @param idObject
	 * @param documnetObject
	 * @return DBObject
	 * @throws Exception
	 * 
	 * 
	 */
	public DBObject nextSequence(MongoClient mongoClient, String collectionName, DBObject idObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = TokuSeqUser.mongoClient.getDB(Constants.S_SNOC);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.findAndModify(idObject, null, null, false, documnetObject, true, false);
		}catch(NullPointerException nullPointerException){
			log.error("nextSequence:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("nextSequence:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("nextSequence:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public DBObject nextSequence(String schemaName , String collectionName, DBObject idObject, DBObject documnetObject) throws Exception
	{
		try
		{
			DB schema = TokuSeqUser.mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.findAndModify(idObject, null, null, false, documnetObject, true, false);
		}
		catch(MongoException mongoException)
		{
			log.error("nextSequence:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}
		catch(Exception exception)
		{
			log.error("nextSequence:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/*public DBObject nextSequence(MongoClient mongoClient, String schemaName, String collectionName, DBObject idObject, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.findAndModify(idObject, null, null, false, documnetObject, true, false);
		}catch(NullPointerException nullPointerException){
			log.error("nextSequence:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("nextSequence:: MongoException Message is: >> "+mongoException.getMessage());
			throw new IntegrationServiceException(mongoException.getMessage(), Constants.E_MONGO_EXCEPTION_CODE);
		}catch(Exception exception){
			log.error("nextSequence:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}*/

	public DBCursor getDocumentsbyQueryAndFilter(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject filterObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.find(queryObject, filterObject);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public DBCursor getDocumentsbyQueryAndFilterwithPagination(MongoClient mongoClient, String schemaName, String collectionName, BasicDBObject queryObject, BasicDBObject filterObject, BasicDBObject sortObject, int skip, int limit) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			/*if (Objects.equals(GridFSDBFile.class, collection.getObjectClass()))
				collection.setObjectClass(null); */
			return collection.find(queryObject, filterObject).sort(sortObject).skip(skip).limit(limit);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public DBCursor getDocumentsbyQueryAndSort(MongoClient mongoClient, String schemaName, String collectionName, BasicDBObject queryObject, BasicDBObject sortObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			/*if (Objects.equals(GridFSDBFile.class, collection.getObjectClass()))
				collection.setObjectClass(null); */
			return collection.find(queryObject).sort(sortObject);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	/**
	 * @author Soumodip
	 * @param mongoClient
	 * @param schemaName
	 * @param collectionName
	 * @param queryObject
	 * @param filterObject
	 * @param sortObject
	 * @param skip
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public DBCursor getDocumentsbyQuerywithPagination(MongoClient mongoClient, String schemaName, String collectionName,
			DBObject queryObject,DBObject sortObject, int skip, int limit) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.find(queryObject).sort(sortObject).skip(skip).limit(limit);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public DBCursor getDocumentsbyQueryAndKeys(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject keys) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.find(queryObject, keys);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult removeDocumnetsByQuery(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.remove(queryObject);
		}catch(NullPointerException nullPointerException){
			log.error("removeDocumnetsByQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("removeDocumnetsByQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("removeDocumnetsByQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	
	public long getDocumentCount(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
	        return collection.find(queryObject).count();
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentCount:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentCount:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentCount:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public String aggregationString(MongoClient mongoClient, String schemaName,String collectionName, List<DBObject> pipeline) throws Exception {
		AggregationOutput aggregationOutput=null;
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			aggregationOutput=collection.aggregate(pipeline);
			
		}catch(NullPointerException nullPointerException){
			log.error("aggregation:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("aggregation:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("aggregation:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
		return aggregationOutput.results().toString();
	}
	
	public AggregationOutput aggregation(MongoClient mongoClient, String schemaName,String collectionName, List<DBObject> pipeline) throws Exception {
		AggregationOutput aggregationOutput=null;
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			aggregationOutput=collection.aggregate(pipeline);
			
		}catch(NullPointerException nullPointerException){
			log.error("aggregation:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("aggregation:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("aggregation:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
		return aggregationOutput;
	}
	/**
	 * @author Soumodip
	 * @param mongoClient
	 * @param schemaName
	 * @param collectionName
	 * @param key
	 * @param queryObject
	 * @return
	 * @throws Exception
	 */
	public List<?> getDistinct(MongoClient mongoClient, String schemaName, String collectionName, String key,DBObject queryObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.distinct(key,queryObject);
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	
	public Iterable<DBObject> getAggregation(MongoClient mongoClient, String schemaName,String collectionName, List<DBObject> pipeline) throws Exception {
		AggregationOutput aggregationOutput=null;
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			aggregationOutput=collection.aggregate(pipeline);
			
		}catch(NullPointerException nullPointerException){
			log.error("aggregation:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("aggregation:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("aggregation:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
		return aggregationOutput.results();
	}
	
	public DBObject getDataByFunction(MongoClient mongoClient, String functionWithParam) throws Exception {
		DBObject dbObj = null;
		try{
			DB schema = mongoClient.getDB("admin");
			dbObj = (DBObject)schema.eval("return "+functionWithParam);
			
		}catch(NullPointerException nullPointerException){
			log.error("aggregation:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("aggregation:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("aggregation:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
		return dbObj;
	}
	
	public DBObject getDocumentByIdWithFilter(MongoClient mongoClient, String schemaName, String collectionName, DBObject queryObject, DBObject filterObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			   return collection.findOne(queryObject,filterObject);
			   
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public int getDocumentCountbyQueryAndKeys(MongoClient mongoClient,
			String schemaName, String collectionName, DBObject queryObject,
			DBObject keys) throws Exception {
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.find(queryObject, keys).count();
		}catch(NullPointerException nullPointerException){
			log.error("getDocumentsbyQuery:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("getDocumentsbyQuery:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("getDocumentsbyQuery:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/*
	 * Supports to TokuMX Only
	 * Not Supports to MongoDB
	 */
	public CommandResult beginStandAloneDBTransaction(MongoClient mongoClient, String schemaName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			return schema.command(Constants.T_BEGIN_TRANSACTION);
		}catch(NullPointerException nullPointerException){
			log.error("beginTransaction:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("beginTransaction:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("beginTransaction:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/*
	 * Supports to TokuMX Only
	 * Not Supports to MongoDB
	 */
	public CommandResult commitStandAloneDBTransaction(MongoClient mongoClient, String schemaName) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			return schema.command(Constants.T_COMMIT_TRANSACTION);
		}catch(NullPointerException nullPointerException){
			log.error("commitTransaction:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("commitTransaction:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("commitTransaction:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	/*
	 * Supports to TokuMX Only
	 * Not Supports to MongoDB
	 */
	public CommandResult rollbackStandAloneDBTransaction(MongoClient mongoClient, String schemaName) throws Exception
	{
		try{
			DB schema = mongoClient.getDB(schemaName);
			return schema.command(Constants.T_ROLLBACK_TRANSACTION);
		}catch(NullPointerException nullPointerException){
			log.error("rollbackTransaction:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("rollbackTransaction:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("rollbackTransaction:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
	
	public WriteResult saveDocumentObject(MongoClient mongoClient, String schemaName, String collectionName, DBObject documnetObject) throws Exception{
		try{
			DB schema = mongoClient.getDB(schemaName);
			DBCollection collection = schema.getCollection(collectionName);
			return collection.save(documnetObject);
		}catch(NullPointerException nullPointerException){
			log.error("insertDocumentObject:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage());
			throw new IntegrationServiceException(nullPointerException.getMessage(), Constants.EX_CODE_NULL_POINTER);
		}catch(MongoException mongoException){
			log.error("insertDocumentObject:: MongoException Message is: >> "+mongoException.getMessage());
			throw new MongoException(Constants.E_MONGO_EXCEPTION_CODE, mongoException.getMessage());
		}catch(Exception exception){
			log.error("insertDocumentObject:: Exception Message is: >> "+exception.getMessage());
			throw exception;
		}
	}
}

