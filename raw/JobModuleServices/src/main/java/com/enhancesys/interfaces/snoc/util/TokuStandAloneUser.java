package com.enhancesys.interfaces.snoc.util;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.enhancesys.snoc.utils.EncryptionUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SuppressWarnings("deprecation")
@Service
public class TokuStandAloneUser
{
	private static Logger log = Logger.getLogger(TokuStandAloneUser.class);
	
	private static MongoCredential credential;
	public static MongoClient adminMongoClient;
	private static MongoClientOptions clientOptions;
	private static boolean socketKeepAliveVal, autoConnectRetryVal;
	private static int maxWaitTimeVal, socketTimeOutVal, maxAutoConnectRetryTimeVal;
	private static Object maxWaitTime, socketKeepAlive, socketTimeOut, autoConnectRetry, maxAutoConnectRetryTime;
	
	static
	{
		try
		{
			log.info("Initiating TokuMx Standalone-Java Adapter.....");
			
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
			
			//if(PropertiesFileLoader.getValueAsString("application.db.configuration").equalsIgnoreCase("toku")){
				TokuStandAloneUser.credential = MongoCredential.createMongoCRCredential(
					Boolean.parseBoolean(PropertiesFileLoaderClass.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil.decrypt(PropertiesFileLoaderClass.getValueAsString("toku.standalone.user.snocReport.name")) : PropertiesFileLoaderClass.getValueAsString("toku.standalone.user.snocReport.name"),
					Boolean.parseBoolean(PropertiesFileLoaderClass.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil.decrypt(PropertiesFileLoaderClass.getValueAsString("toku.standalone.schema.snocReport.name")) : PropertiesFileLoaderClass.getValueAsString("toku.standalone.schema.snocReport.name"),
					Boolean.parseBoolean(PropertiesFileLoaderClass.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil.decrypt(PropertiesFileLoaderClass.getValueAsString("toku.standalone.user.snocReport.password")).toCharArray() : PropertiesFileLoaderClass.getValueAsString("toku.standalone.user.snocReport.password").toCharArray());
				
				TokuStandAloneUser.clientOptions = MongoClientOptions.builder()
					.connectionsPerHost(Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.host.connections.max").toString()))
					.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.host.connections.multiplier.max").toString()))
					.maxWaitTime(maxWaitTimeVal)
					.socketKeepAlive(socketKeepAliveVal)
					.socketTimeout(socketTimeOutVal)
					.autoConnectRetry(autoConnectRetryVal)
					.maxAutoConnectRetryTime(maxAutoConnectRetryTimeVal)
					.connectTimeout(Integer.parseInt(PropertiesFileLoaderClass.get("tokumx.host.connect.timeout.max").toString())).build();
				
				TokuStandAloneUser.adminMongoClient = new MongoClient(
					new ServerAddress(PropertiesFileLoaderClass.getValueAsString("toku.standalone.host.name"), Integer.parseInt(PropertiesFileLoaderClass.getValueAsString("toku.standalone.port.number"))),
					Arrays.asList(TokuStandAloneUser.credential), 
					TokuStandAloneUser.clientOptions);
			
				log.info("TokuMx-Java Adapter Initiated Successfully For Toku Stand Alone User....!");
			
			/*else
			{
				TokuStandAloneUser.credential = MongoCredential.createScramSha1Credential(
					PropertiesFileLoader.getValueAsString("toku.standalone.user.snocReport.name"),
					PropertiesFileLoader.getValueAsString("toku.standalone.schema.snocReport.name"),
					PropertiesFileLoader.getValueAsString("toku.standalone.user.snocReport.password").toCharArray());
				
				TokuStandAloneUser.clientOptions = MongoClientOptions.builder()
					.connectionsPerHost(Integer.parseInt(PropertiesFileLoader.get("toku.standalone.host.connections.max").toString()))
					.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(PropertiesFileLoader.get("tokumx.host.connections.multiplier.max").toString()))
					.maxWaitTime(maxWaitTimeVal)
					.socketKeepAlive(socketKeepAliveVal)
					.socketTimeout(socketTimeOutVal)
					.autoConnectRetry(autoConnectRetryVal)
					.maxAutoConnectRetryTime(maxAutoConnectRetryTimeVal)
					.connectTimeout(Integer.parseInt(PropertiesFileLoader.get("tokumx.host.connect.timeout.max").toString())).build();
					
				TokuStandAloneUser.adminMongoClient = new MongoClient(
					new ServerAddress(PropertiesFileLoader.getValueAsString("toku.standalone.host.name"), Integer.parseInt(PropertiesFileLoader.getValueAsString("toku.standalone.port.number"))),
					Arrays.asList(TokuStandAloneUser.credential), 
					TokuStandAloneUser.clientOptions);
				
				log.info("TokuMx-Java Adapter Initiated Successfully For Toku Stand Alone User....!");
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
	
	public TokuStandAloneUser(){}
}