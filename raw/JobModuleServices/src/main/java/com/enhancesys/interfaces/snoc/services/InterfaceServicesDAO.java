package com.enhancesys.interfaces.snoc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.enhancesys.interfaces.snoc.util.TokuDAO;
import com.enhancesys.interfaces.snoc.util.TokuStandAloneUser;
import com.enhancesys.interfaces.snoc.util.TokuUser;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

@Component
public class InterfaceServicesDAO
{
	@Autowired
	TokuDAO tokuDAO;

	InterfaceServicesDAO() {}

	public WriteResult insertDocumentObject(String schemaName, String collectionName, DBObject documnetObject) throws Exception
	{
		return tokuDAO.insertDocumentObject(TokuUser.adminMongoClient, schemaName, collectionName, documnetObject);
	}
	
	public WriteResult insertDocumentsList(String schemaName, String collectionName, List<DBObject> documentsList) throws Exception
	{
		return tokuDAO.insertDocumentsList(TokuUser.adminMongoClient, schemaName, collectionName, documentsList);
	}

	public WriteResult removeDocuments(String schemaName, String collectionName, DBObject queryObject) throws Exception
	{
		return tokuDAO.removeDocumnetsByQuery(TokuUser.adminMongoClient, schemaName, collectionName, queryObject);
	}
	
	public String aggregationString(String schemaName, String collectionName, List<DBObject> queryObject) throws Exception
	{
		return tokuDAO.aggregationString(TokuUser.adminMongoClient, schemaName, collectionName, queryObject);
	}
	
	public AggregationOutput aggregationDocumentObject(MongoClient mongoClient ,String schemaName, String collectionName, List<DBObject> queryObject) throws Exception
	{
		return tokuDAO.aggregation(mongoClient, schemaName, collectionName, queryObject);
	}
	
	public WriteResult updateDocumentObject(String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception
	{
		return tokuDAO.updateDocumentObject(TokuUser.adminMongoClient, schemaName, collectionName, queryObject, documnetObject);
	}
	
	public WriteResult updateStandaloneDocumentObject(String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception
	{
		return tokuDAO.updateDocumentObject(TokuStandAloneUser.adminMongoClient, schemaName, collectionName, queryObject, documnetObject);
	}

	public WriteResult upsertDocumentObject(String schemaName, String collectionName, DBObject queryObject, DBObject documnetObject) throws Exception
	{
		return tokuDAO.upsertDocumentObject(TokuUser.adminMongoClient, schemaName, collectionName, queryObject, documnetObject);
	}

	public DBObject getDocumentbyId(String schemaName, String collectionName, DBObject idObject) throws Exception
	{
		return tokuDAO.getDocumentbyId(TokuUser.adminMongoClient, schemaName, collectionName, idObject);
	}

	public DBCursor getDocumentsbyQuery(String schemaName, String collectionName, DBObject queryObject) throws Exception
	{
		return tokuDAO.getDocumentsbyQuery(TokuUser.adminMongoClient, schemaName, collectionName, queryObject);
	}
	
	public DBCursor getDocumentsByQueryNFilter(String schemaName, String collectionName, BasicDBObject query, BasicDBObject filterQuery) throws Exception 
	{
		return tokuDAO.getDocumentsbyQueryAndFilter(TokuUser.adminMongoClient, schemaName, collectionName, query, filterQuery);
	}
	
	public CommandResult beginTransaction(String schemaName) throws Exception
	{
		return tokuDAO.beginTransaction(TokuUser.adminMongoClient, schemaName);
	}

	public CommandResult commitTransaction(String schemaName) throws Exception
	{
		return tokuDAO.commitTransaction(TokuUser.adminMongoClient, schemaName);
	}

	public CommandResult rollbackTransaction(String schemaName) throws Exception
	{
		return tokuDAO.rollbackTransaction(TokuUser.adminMongoClient, schemaName);
	}

	public WriteResult updateMultiDocuments(String schemaName, String collectionName, DBObject queryObject,DBObject documnetObject) throws Exception
	{		
		return tokuDAO.updateMultiDocumentObject(TokuUser.adminMongoClient, schemaName, collectionName, queryObject, documnetObject);		
	}
	
	public long nextSequence(String idName) throws Exception
	{
		DBObject dbObject = tokuDAO.nextSequence(TokuUser.adminMongoClient, Constants.C_COUNTERS, 
			new BasicDBObject(Constants._ID, idName), new BasicDBObject(Constants.INCEREMENT_OPERATOR, 
			new BasicDBObject(Constants.F_SEQ, Constants.INCEREMENT_VALUE_BY_ONE)));

		return Long.parseLong(String.valueOf(dbObject.get(Constants.F_SEQ)));
	}
	
	public long getProperSequence(String idName, int incrementValue) throws Exception
	{
		DBObject dbObject = tokuDAO.nextSequence(TokuUser.adminMongoClient, Constants.C_COUNTERS, 
			new BasicDBObject(Constants._ID, idName), new BasicDBObject(Constants.INCEREMENT_OPERATOR, 
			new BasicDBObject(Constants.F_SEQ, incrementValue)));

		return Long.parseLong(String.valueOf(dbObject.get(Constants.F_SEQ)));
	}
}