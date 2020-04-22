package enh.team.interfaces.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONObject;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;


/**
 * <b>Purpose:</b><br>
 * 		Class to provide the services load cache..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        11-10-2019          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public class CacheLoader 
{
	private static Map<String, JSONObject> lookupEntities = new HashMap<String, JSONObject>();
	private static Map<String, String> qryBalAttributeMap = new HashMap<String, String>();
	private static Map<String, String> transferBalAttributeMap = new HashMap<String, String>();
	private static List<Integer> randomNumbers = new ArrayList<Integer>();
	private static SimpleDateFormat yyyyMMddHHmmssSFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
	private Connection sqlConnection = null;
	@SuppressWarnings("unused")
	private static CacheLoader cacheLoader = new CacheLoader();

	private CacheLoader()
	{
		System.out.println("Loading cache..");
		getConnection();
		loadNumbers();
		loadEntityMappings();
		loadQueryBalanceAttributes();
		loadTransferBalanceAttributes();
		closeConnection();
	}

	private void getConnection()
	{
		try
		{
			sqlConnection = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
	}

	private void closeConnection()
	{
		try
		{
			if(sqlConnection != null)
				sqlConnection.close();
		}
		catch (Exception exception) 
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{
			sqlConnection = null;
		}
	}

	private void loadNumbers()
	{
		if(randomNumbers.isEmpty())
		{
			System.out.println("Loading RandomNumbers..");
			for(int i = 1; i <= 91; i++)
			{
				randomNumbers.add(i);
			}
		}
	}

	private void loadEntityMappings()
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		JSONObject jsonObject = null;

		try
		{
			if(lookupEntities.isEmpty())
			{
				System.out.println("Loading EntityMappings..");
				if(sqlConnection == null)
				{
					System.out.println("Connection object should not be empty..");
					throw new Exception("Connection object should not be empty..", null);
				}

				sqlConnection.setAutoCommit(false);

				statement = sqlConnection.prepareStatement("select entity_type_n, entity_id_n, application_id_n, id_1_v from "+"interface"+".tr_entity_mapping");
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{
					jsonObject = new JSONObject();
					jsonObject.put("entityType", resultSet.getLong("entity_type_n"));
					jsonObject.put("entityId", resultSet.getLong("entity_id_n"));
					jsonObject.put("applicationId", resultSet.getLong("application_id_n"));
					jsonObject.put("id1v", resultSet.getString("id_1_v"));
					lookupEntities.put(resultSet.getLong("entity_type_n") +"_"+resultSet.getLong("entity_id_n")+"_"+resultSet.getLong("application_id_n"), jsonObject);
				}
			}
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception)
			{
				System.out.println(exception.getMessage() +  exception);
			}

			statement = null;
			resultSet = null;
		}
	}

	private void loadQueryBalanceAttributes()
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			if(qryBalAttributeMap.isEmpty())
			{
				System.out.println("Loading QueryBalAttributes..");
				if(sqlConnection == null)
				{
					System.out.println("Connection object should not be empty..");
					throw new Exception("Connection object should not be empty..", null);
				}

				sqlConnection.setAutoCommit(false);

				qryBalAttributeMap = new HashMap<String, String>();
				statement = sqlConnection.prepareStatement(INTERFACE_ATTR_SELECT_QUERY);
				statement.setLong(1, 1137l);
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{
					qryBalAttributeMap.put(resultSet.getString("name_v"), resultSet.getString("value_v"));
				}
			}
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception)
			{
				System.out.println(exception.getMessage() +  exception);
			}

			statement = null;
			resultSet = null;
		}
	}

	private void loadTransferBalanceAttributes()
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try
		{
			if(transferBalAttributeMap.isEmpty())
			{
				System.out.println("Loading TransferBalAttributes..");
				if(sqlConnection == null)
				{
					System.out.println("Connection object should not be empty..");
				}

				sqlConnection.setAutoCommit(false);

				transferBalAttributeMap = new HashMap<String, String>();
				statement = sqlConnection.prepareStatement(INTERFACE_ATTR_SELECT_QUERY);
				statement.setLong(1, 1136l);
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{
					transferBalAttributeMap.put(resultSet.getString("name_v"), resultSet.getString("value_v"));
				}
			}
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() + exception);
		}
		finally
		{
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception)
			{
				System.out.println(exception.getMessage() +  exception);
			}

			statement = null;
			resultSet = null;
		}
	}

	public static String getLookupEntity(Long entityType, Long entityId, Long applicationId)throws Exception
	{
		System.out.println("getLookupEntity entry ... entityType: "+ entityType+" entityId: "+ entityId +" applicationId:"+ applicationId);

		JSONObject response = null;

		try
		{
			response = lookupEntities.get(entityType +"_"+entityId+"_"+applicationId);
			if(response != null)
				return response.getString("id1v");
			return null;
		}
		catch(Exception exception)
		{
			System.out.println("Unhandled Exception : " + exception.getMessage() +  exception);
		}
		finally
		{
			response = null;
		}
		return null;
	}

	public static Map<String, String> getQryBalAttributes()
	{
		return qryBalAttributeMap;
	}

	public static Map<String, String> getTransferBalAttributes()
	{
		return transferBalAttributeMap;
	}

	public static String getTransactionId()
	{
		return randomNumbers.get(ThreadLocalRandom.current().nextInt(randomNumbers.size())) + yyyyMMddHHmmssSFormat.format(new Date());
	}

	public static final String INTERFACE_ATTR_SELECT_QUERY = "select name_v, value_v from interface.ms_interface_attr where interface_id_n = ?";
}