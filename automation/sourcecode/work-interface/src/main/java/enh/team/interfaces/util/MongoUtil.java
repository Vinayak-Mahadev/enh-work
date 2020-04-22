package enh.team.interfaces.util;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * <b>Purpose:</b><br>
 * 		Implementation of Mongo Util services..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2017<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        11-12-2017		   Suresh Upparu
 * 	-- Base Release
 * 
 * </pre>
 * 
 * <br>
 */
public class MongoUtil 
{	/**
 * 
 * @param schema
 * @param collectionName
 * @param inputObject
 * @return BasicDBObject
 * @throws Exception
 */
	public BasicDBObject findOne(String dbName, String collectionName, BasicDBObject inputObject) throws Exception
	{
		DB db = null;
		DBCollection collection = null;
		BasicDBObject outputObject = null;

		try
		{
			db = TokuUser.adminMongoClient.getDB(dbName);
			collection = db.getCollection(collectionName);
			outputObject = (BasicDBObject) collection.findOne(inputObject);

			return outputObject;
		}
		catch(Exception exception)
		{
			throw exception;
		}
		finally
		{
			db = null;
			collection = null;
			outputObject = null;
		}
	}
}