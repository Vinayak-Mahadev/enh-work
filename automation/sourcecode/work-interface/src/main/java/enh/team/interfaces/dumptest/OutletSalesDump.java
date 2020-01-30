package enh.team.interfaces.dumptest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import enh.team.interfaces.dumptest.util.CursorBean;
import enh.team.interfaces.dumptest.util.CursorUtil;
import enh.team.interfaces.dumptest.util.DataConstants;
import enh.team.interfaces.dumptest.util.FilePropertiesBean;
import enh.team.interfaces.dumptest.util.IntegrationConstants;
import enh.team.interfaces.dumptest.util.MongoTemplate;
import enh.team.interfaces.dumptest.util.Utility;



public class OutletSalesDump 
{



	@SuppressWarnings({ "unchecked"})
	public static  void processRequest(Long interfaceId, FilePropertiesBean filePropertieBean, Connection connection) throws Exception
	{

		System.out.println("OutletSalesDump Entry processRequest..");

		File file = null;
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		JSONObject dataObj = null;
		JSONObject fieldsConfiguration = null;
		JSONObject lookupConf = null;
		JSONObject outputConf = null;
		String fileName = null;
		Long totalCount = null;
		Long startTime = null;

		JSONArray collectionConfArr = null;
		JSONObject jsonConfig = null;
		String jsonFileName = null;
		FileReader reader = null;
		JSONParser parser = null;
		Map<String, CursorBean> pgCursorMap = null;
		Map<String, CursorBean> mongoCursorMap = null;
		Map<String, MongoTemplate> mongoTemplateMap = null;
		Map<String, JSONObject> collectionConfMap = null;
		Map<String, DateFormat> dateFormatMap = null;
		Map<String, List<JSONObject>> lastFetchedObjectMap = null;
		Map<String, List<JSONObject>> lastFetchedMongoMap = null;
		CursorUtil cursorUtil = null;

		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		CursorBean cursorBean = null;
		DBCursor cursor = null;
		JSONObject collectionObj = null;
		//		DateFormat dateFormat = null;
		//		Object fieldValue = null;
		String[] fields = null;
		String[] collectionFields = null;
		String outputFileData = null;

		try
		{
			startTime = System.currentTimeMillis();

			jsonFileName = filePropertieBean.getProcessName();

			parser  = new JSONParser();
			reader = new FileReader(jsonFileName);
			jsonConfig =  (JSONObject) (parser.parse(reader));
			reader.close();


			fileName = filePropertieBean.getFileName()+filePropertieBean.getRemoteFileFormat();

			file = new File(fileName);
			fileOutputStream = new FileOutputStream(file);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, IntegrationConstants.FILE_ENCODE_FORMAT);

			outputStreamWriter.write(filePropertieBean.getFileHeaders());
			outputStreamWriter.write(System.getProperty("line.separator"));

			System.out.println("Interface : " + interfaceId + " : File : " + file.getName());

			totalCount = 0L;
			cursorUtil = new CursorUtil();
			collectionConfArr = (JSONArray) jsonConfig.get(DataConstants.PRIMARY);
			lookupConf = (JSONObject) jsonConfig.get(DataConstants.LOOKUP);
			fieldsConfiguration = (JSONObject) jsonConfig.get(DataConstants.CONFIGURATION);
			outputConf =  (JSONObject) jsonConfig.get(DataConstants.OUTPUT_CONF);
			fields = outputConf.get(DataConstants.FIELDS).toString().split(",");

			lastFetchedObjectMap = new HashMap<String, List<JSONObject>>();
			lastFetchedMongoMap = new HashMap<String, List<JSONObject>>();
			dateFormatMap = new HashMap<String, DateFormat>();

			collectionConfMap = cursorUtil.getCollectionConfs(collectionConfArr);

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


					for(Map.Entry<String, JSONObject> collection : collectionConfMap.entrySet())
					{
						if(!collection.getKey().equalsIgnoreCase(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString()))
						{
							collectionObj = collection.getValue();
							collectionFields = collectionObj.get(DataConstants.FIELDS).toString().split(",");
							if(collectionObj.get(DataConstants.IS_POSTGRES) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_POSTGRES).toString()))
							{
								dataObj = cursorUtil.getPGValues(collection.getKey(), pgCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, fieldsConfiguration, dateFormatMap, lastFetchedObjectMap);
							}
							else 
							{
								if(collectionObj.get(DataConstants.IS_LOOKUP) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_LOOKUP).toString()))
									dataObj = cursorUtil.getMongoValues(dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, dateFormatMap);
								else
									dataObj = cursorUtil.getMongoValues(collection.getKey(), mongoCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, dateFormatMap, lastFetchedMongoMap);
							}
						}
					}

					outputFileData = "";
					for(String field : fields)
					{
						outputFileData += dataObj.get(field) != null ? dataObj.get(field) : ""  + filePropertieBean.getCsvDelimeter();
					}
					if(!outputFileData.replace(filePropertieBean.getCsvDelimeter(), "").trim().isEmpty())
					{
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


					for(Map.Entry<String, JSONObject> collection : collectionConfMap.entrySet())
					{
						if(!collection.getKey().equalsIgnoreCase(((JSONObject) collectionConfArr.get(0)).get(DataConstants.COLLECTION_NAME).toString()))
						{
							collectionObj = collection.getValue();
							collectionFields = collectionObj.get(DataConstants.FIELDS).toString().split(",");
							if(collectionObj.get(DataConstants.IS_POSTGRES) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_POSTGRES).toString()))
							{
								dataObj = cursorUtil.getPGValues(collection.getKey(), pgCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, fieldsConfiguration, dateFormatMap, lastFetchedObjectMap);
							}
							else 
							{
								if(collectionObj.get(DataConstants.IS_LOOKUP) != null && "true".equalsIgnoreCase(collectionObj.get(DataConstants.IS_LOOKUP).toString()))
									dataObj = cursorUtil.getMongoValues(dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, dateFormatMap);
								else
									dataObj = cursorUtil.getMongoValues(collection.getKey(), mongoCursorMap, (JSONObject) collectionObj.get(DataConstants.MAP_KEY), dataObj, collectionFields, lookupConf, mongoTemplateMap, fieldsConfiguration, dateFormatMap, lastFetchedMongoMap);
							}
						}
					}

					outputFileData = "";
					for(String field : fields)
					{
						outputFileData += (dataObj.get(field) != null ? dataObj.get(field) : "")  + filePropertieBean.getCsvDelimeter();
					}
					if(!outputFileData.replace(filePropertieBean.getCsvDelimeter(), "").trim().isEmpty())
					{
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


		}

		catch(Exception exception)
		{
			exception.printStackTrace();
			System.out.println("OutletSalesDump - Exception :: " + exception.getMessage()+ exception);
			throw exception;
		}
		finally
		{
			file = null;
			if(fileOutputStream != null)
				fileOutputStream.close();
			fileOutputStream = null;
			if(outputStreamWriter != null)
				outputStreamWriter.close();
			outputStreamWriter = null;
			dataObj = null;
			fieldsConfiguration = null;
			//			fieldConf = null;
			//			collectionConf = null;
			outputConf = null;
			fileName = null;
			totalCount = null;
			filePropertieBean = null;
			collectionConfArr = null;
			jsonConfig = null;
			jsonFileName = null;
			reader = null;
			parser = null;
			pgCursorMap = null;
			mongoCursorMap = null;
			mongoTemplateMap = null;
			collectionConfMap = null;
			dateFormatMap = null;
			cursorUtil = null;
			connection = null;
			resultSet = null;
			metaData = null;
			cursorBean = null;
			cursor = null;
			//			dateFormat = null;
			//			fieldValue = null;
			fields = null;
			outputFileData = null;
			lastFetchedObjectMap = null;
			lastFetchedMongoMap = null;
			collectionObj = null;
			collectionFields = null;


			System.out.println("OutletSalesDump : Exit processRequest.. "+(System.currentTimeMillis() - startTime));
		}

		startTime = null;

	}
}
