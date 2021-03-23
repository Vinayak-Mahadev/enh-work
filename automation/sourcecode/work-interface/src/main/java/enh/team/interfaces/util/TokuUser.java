package enh.team.interfaces.util;

import java.net.UnknownHostException;
import java.util.Arrays;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@SuppressWarnings("unused")
public class TokuUser
{
	private static MongoClientOptions clientOptions;
	private static MongoCredential credential;
	public static MongoClient adminMongoClient;

	static{
		try {
			createConnection();
		}
		catch(Exception exception){
			System.out.println("Exception Message is: >> "+exception.getMessage());
		}
	}

	public static void createConnection(){

		try {
			System.out.println("Initiating TokuMx-Java Adapter.....");
			TokuUser.credential = MongoCredential.createMongoCRCredential(MONGO_DB_USER,MONGO_DB_DATABASE, MONGO_DB_PASSWORD.toCharArray());
			TokuUser.clientOptions = MongoClientOptions.builder().connectionsPerHost(MONGO_CONNECTIONS_MAX).connectTimeout(MONGO_CONNECTION_TIMEOUT_MAX).build();
			TokuUser.adminMongoClient = new MongoClient(Arrays.asList(
					new ServerAddress(MONGO_DB_IP1, MONGO_DB_PORT1),
					new ServerAddress(MONGO_DB_IP2, MONGO_DB_PORT2),
					new ServerAddress(MONGO_DB_IP3, MONGO_DB_PORT3)),
					Arrays.asList(TokuUser.credential), TokuUser.clientOptions);
			TokuUser.adminMongoClient.setReadPreference(ReadPreference.secondaryPreferred());
			System.out.println("TokuMx-Java Adapter Initiated Successfully For Replica-Set user....!");
		} catch (UnknownHostException unknownHostException) {
			System.out.println("static:: The Process of MongoClient Creation Failed, Due to: "+unknownHostException.getMessage() +"::cause::"+ unknownHostException);
		}catch(NullPointerException nullPointerException){
			System.out.println("static:: Null Pointer Exception Message is: >> "+nullPointerException.getMessage() +"::cause::"+nullPointerException);
		}catch(Exception exception){
			System.out.println("static:: Exception Message is: >> "+exception.getMessage() +"::cause::"+exception);
		}
	}

	public TokuUser()
	{
		createConnection();
	}

	/*@Override
    public void destroy() throws Exception {
		log.info("Releasing TokuMx Java-Api Adapter Resources....");
		TokuUser.adminMongoClient.close();
		log.info("Released TokuMx Java-Api Adapter Resources Successfully....!");
	}  */


	private final static String MONGO_DB_IP1="mongor1.enhancesys.com";
	private final static String MONGO_DB_IP2="mongor2.enhancesys.com";
	private final static String MONGO_DB_IP3="mongor3.enhancesys.com";
	private final static String MONGO_DB_IP4="mongor4.enhancesys.com";
	private final static String MONGO_DB_IP5="mongodump.enhancesys.com";
	private final static int MONGO_DB_PORT1=22020;
	private final static int MONGO_DB_PORT2=22022;
	private final static int MONGO_DB_PORT3=22024;
	private final static int MONGO_DB_PORT4=22026;
	
	private final static int MONGO_DB_PORT5=22030;
	private final static String MONGO_DB_USER="admin";
	private final static String MONGO_DB_DATABASE="admin";
	private final static String MONGO_DB_PASSWORD="admin";
	private final static int MONGO_CONNECTIONS_MAX = 100;
	private final static int MONGO_CONNECTION_TIMEOUT_MAX = 120000;

	private final static String MONGO_DB_STANDALONE_USER="admin";
	private final static String MONGO_DB_STANDALONE_DATABASE="admin";
	private final static String MONGO_DB_STANDALONE_PASSWORD="admin";

	private final static String MONGO_DB_R4_USER="admin";
	private final static String MONGO_DB_R4_DATABASE="admin";
	private final static String MONGO_DB_R4_PASSWORD="admin";

}
