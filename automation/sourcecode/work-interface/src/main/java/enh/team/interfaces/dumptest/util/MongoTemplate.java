package enh.team.interfaces.dumptest.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

/**
 * <b>Purpose:</b><br>
 * 		Class MongoTemplate to create the required mongo server connection and to give the required collection related services..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        01-08-2018          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public class MongoTemplate
{
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 		1. Read the given input parameters and create the credential and clientOptions objects.
	 * 		2. Split replicas string and loop and create the server address object for each replica and set to the list.
	 * 		3. Create new mongo client with the address list, credentials and clientOptions.
	 * 		4. Set secondary as read preference to the mongo client.
	 * </pre>
	 * @param userName
	 * @param password
	 * @param schemaName
	 * @param maxConnection
	 * @param connectionTimeOut
	 * @param replicas
	 */
	public MongoTemplate(String userName, String password, String schemaName, String maxConnection, String connectionTimeOut, String replicas)
	{
		//MetricLog.startMetric("Method", null);
		
		List<ServerAddress> addresses = null;
		String[] arr = null;
				
		try
		{
			addresses = new ArrayList<ServerAddress>();
			credential = MongoCredential.createMongoCRCredential(userName,schemaName,  password.toCharArray());
			clientOptions = MongoClientOptions.builder().connectionsPerHost(Integer.parseInt(maxConnection)).connectTimeout(Integer.parseInt(connectionTimeOut)).build();
			
			for(String replica : replicas.split(","))
			{
				arr = replica.split(":");
				addresses.add(new ServerAddress(arr[0], Integer.parseInt(arr[1])));
			}
			
			adminMongoClient = new MongoClient(addresses, Arrays.asList(credential), clientOptions);
			adminMongoClient.setReadPreference(ReadPreference.secondaryPreferred());
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage()+ exception);
		}
		finally
		{
			addresses = null;
			arr = null;
			//MetricLog.stopMetric("Method", null);
		}
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 		1. Return the DB collection object for the given schema and collection.
	 * </pre>
	 * @param schema
	 * @param collection
	 * @return DBCollection
	 */
	public DBCollection getCollection(String schema, String collection)
	{
		//MetricLog.startMetric("Method", null);
		
		try
		{
			return adminMongoClient.getDB(schema).getCollection(collection);
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage()+ exception);
		}
		finally
		{
			//MetricLog.stopMetric("Method", null);
		}
		return null;
	}

	private MongoClientOptions clientOptions;
	private MongoCredential credential;
	private MongoClient adminMongoClient;
}