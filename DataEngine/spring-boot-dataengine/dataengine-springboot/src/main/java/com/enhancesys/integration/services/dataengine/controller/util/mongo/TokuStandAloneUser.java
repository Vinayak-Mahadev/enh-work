package com.enhancesys.integration.services.dataengine.controller.util.mongo;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SuppressWarnings("deprecation")
@Service
public class TokuStandAloneUser
{
	private static Logger LOGGER = Logger.getLogger(TokuStandAloneUser.class);

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
			LOGGER.info("Initiating TokuMx Standalone-Java Adapter.....");

			socketTimeOut = DataConstants.MONGO_CONNECTION_TIMEOUT_MAX;
			maxWaitTimeVal = (maxWaitTime != null && !maxWaitTime.toString().isEmpty()) ? Integer.parseInt(maxWaitTime.toString()) : 120000;

			socketTimeOut = DataConstants.MONGO_CONNECTION_TIMEOUT_MAX;
			socketTimeOutVal = (socketTimeOut != null && !socketTimeOut.toString().isEmpty()) ? Integer.parseInt(socketTimeOut.toString()) : 0;

			socketKeepAlive = DataConstants.MONGO_CONNECTION_KEEP_ALIVE;
			socketKeepAliveVal = (socketKeepAlive != null && !socketKeepAlive.toString().isEmpty()) ? Boolean.parseBoolean(socketKeepAlive.toString()) : false;

			autoConnectRetry = DataConstants.MONGO_CONNECTION_AUTO_CONNECT;
			autoConnectRetryVal = (autoConnectRetry != null && !autoConnectRetry.toString().isEmpty()) ? Boolean.parseBoolean(autoConnectRetry.toString()) : false;

			maxAutoConnectRetryTime = DataConstants.MONGO_CONNECTION_AUTO_CONNECT_RETRY_MAX;
			maxAutoConnectRetryTimeVal = (maxAutoConnectRetryTime != null && !maxAutoConnectRetryTime.toString().isEmpty()) ? Integer.parseInt(maxAutoConnectRetryTime.toString()) : 0;

			TokuStandAloneUser.credential = MongoCredential.createMongoCRCredential(DataConstants.MONGO_DB_R4_USER,
					DataConstants.MONGO_DB_R4_DATABASE,
					DataConstants.MONGO_DB_R4_PASSWORD.toCharArray());

			TokuStandAloneUser.clientOptions = MongoClientOptions.builder()
					.connectionsPerHost(Integer.parseInt(DataConstants.MONGO_CONNECTIONS_MAX))
					.threadsAllowedToBlockForConnectionMultiplier(Integer.parseInt(DataConstants.MONGO_CONNECTIONS_MAX) / 10)
					.maxWaitTime(maxWaitTimeVal)
					.socketKeepAlive(socketKeepAliveVal)
					.socketTimeout(socketTimeOutVal)
					.autoConnectRetry(autoConnectRetryVal)
					.maxAutoConnectRetryTime(maxAutoConnectRetryTimeVal)
					.connectTimeout(Integer.parseInt(DataConstants.MONGO_CONNECTION_TIMEOUT_MAX)).build();

			TokuStandAloneUser.adminMongoClient = new MongoClient(
					new ServerAddress(DataConstants.MONGO_DB_IP5, Integer.parseInt(DataConstants.MONGO_DB_PORT5)),
					Arrays.asList(TokuStandAloneUser.credential), 
					TokuStandAloneUser.clientOptions);

			LOGGER.info("TokuMx-Java Adapter Initiated Successfully For Toku Stand Alone User....!");

		}
		catch(NullPointerException e)
		{
			LOGGER.error("Null Pointer exception : " + e.getMessage());
		}
		catch(Exception e)
		{
			LOGGER.error("Exception : " + e.getMessage());
		}
	}

	public TokuStandAloneUser(){}
}