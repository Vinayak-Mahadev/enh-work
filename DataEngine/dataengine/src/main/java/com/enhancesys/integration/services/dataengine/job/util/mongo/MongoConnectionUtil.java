package com.enhancesys.integration.services.dataengine.job.util.mongo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;



/**
 * <b>Purpose:</b><br>
 * 		Class MongoConnectionUtil to give the mongo connection object for the given connection id..<br>
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
 *  1        10-08-2018          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public class MongoConnectionUtil
{
	private static MongoConnectionUtil connectionUtil = new MongoConnectionUtil();
	private static Map<String, MongoTemplate> connectionMap = new HashMap<String, MongoTemplate>();
	private static Logger log = Logger.getLogger(MongoConnectionUtil.class);
	
	public MongoConnectionUtil()
	{
		
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre> 
	 * @return
	 */
	public static MongoConnectionUtil getInstance()
	{
		return connectionUtil;
	}
	
	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param appContext
	 * @param connectionId
	 * @return MongoTemplate
	 * @throws GenericProcessorException
	 */
	public MongoTemplate getConnection(ApplicationContext appContext, String connectionId) throws GenericProcessorException
	{
		MongoTemplate mongoTemplate = null;
		
		try
		{
			if(!connectionMap.containsKey(connectionId) || connectionMap.get(connectionId) == null)
			{
				mongoTemplate = (MongoTemplate) appContext.getBean(connectionId);
				connectionMap.put(connectionId, mongoTemplate);
			}
			return connectionMap.get(connectionId);
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			mongoTemplate = null;
		}
	}
}