package com.finevm.enh.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

public class MongoDB {

	private static final JSONObject MONGO_USERS = IWorkConstants.MONGODB_LIST;

	private static final  Map<String, MongoClient> MONGO_CLIENTS_MAP = new HashMap<String, MongoClient>();

	private  MongoDB(){

	}

	private static MongoClient init(JSONObject mongodbConAttr) throws Exception {

		String url      = null; 
		String source   = null; 
		String username = null; 
		char[] password = null; 
		int    portNum  = 0; 
		MongoCredential credential  = null;
		MongoClientOptions clientOptions = null;
		MongoClient adminMongoClient =null ;
		try
		{
			if(mongodbConAttr!=null) {
				url      = mongodbConAttr.getString("url");                    
				source   = mongodbConAttr.getString("source");                 
				username = mongodbConAttr.getString("username");               
				password = mongodbConAttr.getString("password").toCharArray(); 
				portNum  = mongodbConAttr.getInt("portNum");     

				credential       = MongoCredential.createMongoCRCredential(username, source, password);
				clientOptions    = MongoClientOptions.builder().connectionsPerHost(100).connectTimeout(12000).build();

				adminMongoClient = new MongoClient
						(
								Arrays.asList(
										//new ServerAddress(url, 22020),
										//new ServerAddress(url, 22022),
										new ServerAddress(url, portNum)
										),
								Arrays.asList(credential), 
								clientOptions
								);
				adminMongoClient.setReadPreference(ReadPreference.secondaryPreferred());
				adminMongoClient.getDatabaseNames();
				System.out.println("Connected to  "+url+":"+portNum+" database successfully"); 

				return adminMongoClient;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally {
			url      = null;    
			source   = null;    
			username = null;    
			password = null;    
			credential  = null;
			clientOptions = null;

		}


		return null;
	}

	public static MongoClient getMongoClient(JSONObject mongodbConAttr) throws Exception {
		return init(mongodbConAttr);
	}

	public static MongoClient getMongoClient(String mongoUrlPort) throws Exception {

		if(MONGO_CLIENTS_MAP.containsKey(mongoUrlPort.toString())) {
			System.out.println("Existing connection given :  connection id : "+mongoUrlPort);
		}
		else
		{
			MONGO_CLIENTS_MAP.put(mongoUrlPort.toString(), init(
					MONGO_USERS.get(mongoUrlPort.toString()) != null 
					? MONGO_USERS.getJSONObject(mongoUrlPort.toString()) 
							: null));
		}
		return MONGO_CLIENTS_MAP.get(mongoUrlPort.toString());
	}

	public static MongoClient getMongoClient(PropType mongoUrlPort) throws Exception {
		return getMongoClient(mongoUrlPort.toString());
	}

	public static MongoClient getMongoClient(JSONObject mongodbConAttrList, PropType mongoUrlPort) throws Exception {
		return getMongoClient(mongodbConAttrList, mongoUrlPort.toString());
	}

	public static MongoClient getMongoClient(JSONObject mongodbConAttrList, String mongoUrlPort) throws Exception {

		if(MONGO_CLIENTS_MAP.containsKey(mongoUrlPort.toString())) {
			System.out.println("Existing connection given :  connection id : "+mongoUrlPort);
		}
		else
		{
			MONGO_CLIENTS_MAP.put(mongoUrlPort.toString(), init(
					mongodbConAttrList.get(mongoUrlPort.toString()) != null 
					? mongodbConAttrList.getJSONObject(mongoUrlPort.toString()) 
							: null));
		}
		return MONGO_CLIENTS_MAP.get(mongoUrlPort.toString());
	}

	/**
	 * <pre>This method close all given MongoClient connections
	 *and it will remove MongoClient reference from MONGOCLIENT_MAP </pre>
	 */
	public static void closeAllConnection(){
		try {


			Set<String> keys = MONGO_CLIENTS_MAP.keySet();
			for (String key : keys) {
				closeConnection(key);
			}
			System.out.println(MONGO_CLIENTS_MAP);

		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public static void closeConnection(String mongoUrlPort) {
		try {


			if(MONGO_CLIENTS_MAP.get(mongoUrlPort)!=null) {
				MONGO_CLIENTS_MAP.get(mongoUrlPort).close();
				MONGO_CLIENTS_MAP.put(mongoUrlPort, null);
				//	MONGO_CLIENTS_MAP.remove(mongoUrlPort);
				System.out.println("connection closed for id : "+mongoUrlPort);
			}
			else {
				System.out.println("No connection present for id : "+mongoUrlPort);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(PropType mongoUrlPort) {
		closeConnection(mongoUrlPort.toString());
	}

}
