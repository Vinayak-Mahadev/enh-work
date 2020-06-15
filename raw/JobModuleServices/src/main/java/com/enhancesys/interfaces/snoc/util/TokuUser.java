package com.enhancesys.interfaces.snoc.util;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.enhancesys.snoc.utils.EncryptionUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@SuppressWarnings("deprecation")
public class TokuUser /* implements DisposableBean */
{
	private static Logger log = Logger.getLogger(TokuUser.class);
	
	private static MongoCredential credential;
	public static MongoClient adminMongoClient;
	private static MongoClientOptions clientOptions;
	public static MongoClient primaryMongoClient;
	private static boolean socketKeepAliveVal, autoConnectRetryVal;
	private static int maxWaitTimeVal, socketTimeOutVal, maxAutoConnectRetryTimeVal;
	private static Object maxWaitTime, socketKeepAlive, socketTimeOut, autoConnectRetry, maxAutoConnectRetryTime;

	static
	{
		try 
		{
			log.info("Initiating TokuMx-Java Adapter.....");
			
			maxWaitTime = PropertiesFileLoaderClass.get("tokumx.host.connections.waittime.max");
			maxWaitTimeVal = (maxWaitTime != null && !maxWaitTime.toString().isEmpty()) ? Integer.parseInt(maxWaitTime.toString()) : 120000;
			
			socketTimeOut = PropertiesFileLoaderClass.get("tokumx.host.socket.timeout.max");
			socketTimeOutVal = (socketTimeOut != null && !socketTimeOut.toString().isEmpty()) ? Integer.parseInt(socketTimeOut.toString()) : 0;

			socketKeepAlive = PropertiesFileLoaderClass.get("tokumx.host.socket.keepalive");
			socketKeepAliveVal = (socketKeepAlive != null && !socketKeepAlive.toString().isEmpty()) ? Boolean.parseBoolean(socketKeepAlive.toString()) : false;

			autoConnectRetry = PropertiesFileLoaderClass.get("tokumx.host.autoconnect.retry");
			autoConnectRetryVal = (autoConnectRetry != null && !autoConnectRetry.toString().isEmpty()) ? Boolean.parseBoolean(autoConnectRetry.toString()) : false;

			maxAutoConnectRetryTime = PropertiesFileLoaderClass.get("tokumx.host.autoconnect.retry.max");
			maxAutoConnectRetryTimeVal = (maxAutoConnectRetryTime != null && !maxAutoConnectRetryTime.toString().isEmpty()) ? Integer.parseInt(maxAutoConnectRetryTime.toString()) : 0;
			
			
			//enable below condition for toku,percona respectively.
			//if(PropertiesFileLoader.getValueAsString("application.db.configuration").equalsIgnoreCase("toku")){
				TokuUser.credential = MongoCredential.createMongoCRCredential(
					Boolean.parseBoolean(PropertiesFileLoaderClass.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil.decrypt(PropertiesFileLoaderClass.getValueAsString("tokumx.user.admin.name")) : PropertiesFileLoaderClass.getValueAsString("tokumx.user.admin.name"),
					Boolean.parseBoolean(PropertiesFileLoaderClass.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil.decrypt(PropertiesFileLoaderClass.getValueAsString("tokumx.schema.admin.name")) : PropertiesFileLoaderClass.getValueAsString("tokumx.schema.admin.name"),
					Boolean.parseBoolean(PropertiesFileLoaderClass.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil.decrypt(PropertiesFileLoaderClass.getValueAsString("tokumx.user.admin.password")).toCharArray() : PropertiesFileLoaderClass.getValueAsString("tokumx.user.admin.password").toCharArray());
				
				TokuUser.clientOptions = MongoClientOptions.builder()
					.connectionsPerHost(Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.host.connections.max").toString()))
					.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.host.connections.multiplier.max").toString()))
					.maxWaitTime(maxWaitTimeVal)
					.socketKeepAlive(socketKeepAliveVal)
					.socketTimeout(socketTimeOutVal)
					.autoConnectRetry(autoConnectRetryVal)
					.maxAutoConnectRetryTime(maxAutoConnectRetryTimeVal)
					.connectTimeout(Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.host.connect.timeout.max").toString())).build();
				
				TokuUser.adminMongoClient = new MongoClient(
					Arrays.asList(
						new ServerAddress(PropertiesFileLoaderClass.getValueAsString("tokumx.replicaset.r1.host.name1"), Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.replicaset.r1.port1.number").toString())),
						new ServerAddress(PropertiesFileLoaderClass.getValueAsString("tokumx.replicaset.r1.host.name2"), Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.replicaset.r1.port2.number").toString())),
						new ServerAddress(PropertiesFileLoaderClass.getValueAsString("tokumx.replicaset.r1.host.name3"), Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.replicaset.r1.port3.number").toString()))),
					Arrays.asList(TokuUser.credential), 
					TokuUser.clientOptions);
				
				TokuUser.primaryMongoClient = new MongoClient(
						Arrays.asList(
							new ServerAddress(PropertiesFileLoaderClass.getValueAsString("tokumx.replicaset.r1.host.name1"), Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.replicaset.r1.port1.number").toString())),
							new ServerAddress(PropertiesFileLoaderClass.getValueAsString("tokumx.replicaset.r1.host.name2"), Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.replicaset.r1.port2.number").toString())),
							new ServerAddress(PropertiesFileLoaderClass.getValueAsString("tokumx.replicaset.r1.host.name3"), Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.replicaset.r1.port3.number").toString()))),
						Arrays.asList(TokuUser.credential), 
						TokuUser.clientOptions);
				
				
				TokuUser.primaryMongoClient.setReadPreference(ReadPreference.primary());
				
				TokuUser.adminMongoClient.setReadPreference(ReadPreference.secondaryPreferred());
				
				log.info("TokuMx-Java Adapter Initiated Successfully For Replica-Set user....!");
			
			/*else
			{
				TokuUser.credential = MongoCredential.createScramSha1Credential(
					PropertiesFileLoader.getValueAsString("tokumx.user.admin.name"),
					PropertiesFileLoader.getValueAsString("tokumx.schema.admin.name"),
					PropertiesFileLoader.getValueAsString("tokumx.user.admin.password").toCharArray());
			
				TokuUser.clientOptions = MongoClientOptions.builder()
					.connectionsPerHost(Integer.parseInt(PropertiesFileLoader.get("tokumx.host.connections.max").toString()))
					.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(PropertiesFileLoader.get("tokumx.host.connections.multiplier.max").toString()))
					.maxWaitTime(maxWaitTimeVal)
					.socketKeepAlive(socketKeepAliveVal)
					.socketTimeout(socketTimeOutVal)
					.autoConnectRetry(autoConnectRetryVal)
					.maxAutoConnectRetryTime(maxAutoConnectRetryTimeVal)
					.connectTimeout(Integer.parseInt(PropertiesFileLoader.get("tokumx.host.connect.timeout.max").toString())).build();
			
				TokuUser.adminMongoClient = new MongoClient(
					Arrays.asList(
						new ServerAddress(PropertiesFileLoader.getValueAsString("tokumx.replicaset.r1.host.name1"), Integer.parseInt(PropertiesFileLoader.get("tokumx.replicaset.r1.port1.number").toString())),
						new ServerAddress(PropertiesFileLoader.getValueAsString("tokumx.replicaset.r1.host.name2"), Integer.parseInt(PropertiesFileLoader.get("tokumx.replicaset.r1.port2.number").toString())),
						new ServerAddress(PropertiesFileLoader.getValueAsString("tokumx.replicaset.r1.host.name3"), Integer.parseInt(PropertiesFileLoader.get("tokumx.replicaset.r1.port3.number").toString()))),
					Arrays.asList(TokuUser.credential), 
					TokuUser.clientOptions);
				
				TokuUser.adminMongoClient.setReadPreference(ReadPreference.secondaryPreferred());
				
				log.info("Percona Adapter Initiated Successfully For Replica-Set user....!");
			}
			*/
		}
		catch (UnknownHostException e)
		{
			log.error("MongoClient creation process failed : " + e.getMessage());
		}
		catch(NullPointerException e)
		{
			log.error("Null Pointer exception : " + e.getMessage());
		}
		catch(Exception e)
		{
			log.error("Exception : " + e.getMessage());
		}
	}
	
	public TokuUser(){}
	
	/*@Override
    public void destroy() throws Exception {
		log.info("Releasing TokuMx Java-Api Adapter Resources....");
		TokuUser.adminMongoClient.close();
		log.info("Released TokuMx Java-Api Adapter Resources Successfully....!");
	}  */	
}