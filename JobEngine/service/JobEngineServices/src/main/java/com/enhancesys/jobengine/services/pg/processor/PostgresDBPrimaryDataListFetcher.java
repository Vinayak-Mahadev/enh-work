package com.enhancesys.jobengine.services.pg.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.services.pg.util.JdbcConnectionUtil;
import com.enhancesys.jobengine.services.processor.DBProcessor;
import com.enhancesys.jobengine.services.util.DataConstants;
import com.enhancesys.jobengine.services.util.GenericProcessorException;
import com.enhancesys.jobengine.services.util.NoMoreDataException;
import com.enhancesys.jobengine.services.util.Util;

public class PostgresDBPrimaryDataListFetcher extends DBProcessor
{
	@Autowired
	JdbcConnectionUtil jdbcConnectionUtil;
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet cursor = null;
	private long sleepTime;
	private long delayCount = 0;
	private int collectionPosition = 0;
	private int columnCount = 0;
	private static Logger log = Logger.getLogger(PostgresDBPrimaryDataListFetcher.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfigurationReader, jobParameters);
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			connection = jdbcConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(((JSONObject) collectionArr.get(collectionPosition)).get(DataConstants.QUERY).toString());
			statement.setFetchSize(100);
			cursor = statement.executeQuery();
			columnCount = cursor.getMetaData().getColumnCount();
//			log.info(pipeLineName + " : " + processorName + " :  Cursor : " + cursor);
			
			if(primaryConfig.get("sleep_time") != null && !primaryConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) primaryConfig.get("sleep_time");
			else
				this.sleepTime = 0;
			
			if(primaryConfig.get("delay_count") != null && !primaryConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) primaryConfig.get("delay_count");
			else
				this.delayCount = 0;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info("Exit init..");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException
	{
//		log.info("Entry fetchData..");
		
		List<String> dbObject = null;
		JSONObject mapKeyConf = null;
		JSONArray jsonArray = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONObject mapKeyObject = null;
		Object mapKey = null;
		Object localKey = null;
		String keyType = null; 
		
		try
		{
			try 
			{
				if(cursor.next())
				{
					dbObject = Util.convertToDataList(cursor, columnCount);
//					log.info("dbObject : " + dbObject);
					jsonArray = new JSONArray();
//					log.info(pipeLineName + " : " + processorName + " : Primary : " + dbObject);
					jsonArray.add(dbObject);
					fetchedData.put(DataConstants.DATA_LIST, jsonArray);
//				log.info(pipeLineName + " : " + processorName + " : Data : " + fetchedData);
					keyField.clear();
					mapKeyConf = (JSONObject) ((JSONObject) collectionArr.get(collectionPosition)).get(DataConstants.MAP_KEY);
					if(mapKeyConf != null)
					{
						mapKey = mapKeyConf.get(DataConstants.KEY);
						localKey = mapKeyConf.get(DataConstants.LOCAL_KEY);
						keyType = mapKeyConf.get(DataConstants.TYPE) != null ? mapKeyConf.get(DataConstants.TYPE).toString() : "String";
						mapKeyObject = new JSONObject();
						if(localKey instanceof JSONArray)
						{
							localKeyArray = (JSONArray) localKey;
							mapKeyArray = (JSONArray) mapKey;
							
							for(int i = 0; i < localKeyArray.size(); i++)
							{
								if(dbObject.get(Integer.parseInt(localKeyArray.get(i).toString())) != null)
								{
									if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
										mapKeyObject.put(mapKeyArray.get(i).toString(), dbObject.get(Integer.parseInt(localKeyArray.get(i).toString())).toString());
									if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
										mapKeyObject.put(mapKeyArray.get(i).toString(), Long.parseLong(dbObject.get(Integer.parseInt(localKeyArray.get(i).toString())).toString()));
								}
								else
									mapKeyObject = null;
							}
							
							if(mapKeyObject != null)
								keyField.add(mapKeyObject);
						}
						else
						{
							if(dbObject.get(Integer.parseInt(localKey.toString())) != null)
							{
								if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
									mapKeyObject.put(mapKey.toString(), dbObject.get(Integer.parseInt(localKey.toString())).toString());
								if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
									mapKeyObject.put(mapKey.toString(), Long.parseLong(dbObject.get(Integer.parseInt(localKey.toString())).toString()));
							}
							else
								mapKeyObject = null;
							
							if(mapKeyObject != null)
								keyField.add(mapKeyObject);
						}
						log.info(pipeLineName + " : " + processorName + " : keyField : " + keyField);
					}
				}
				else
				{
					log.error(pipeLineName + " : " + processorName + " : No more data exists in the cursor..");
					throw new NoMoreDataException(pipeLineName + " : " + processorName + " : No more data exists in the cursor..");
				}
			} 
			catch (NumberFormatException e) 
			{
				e.printStackTrace();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			} 
			catch (GenericProcessorException e) 
			{
				e.printStackTrace();
			}
			
			try
			{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException interruptedException)
			{
				log.error(pipeLineName + " : " + processorName + " :  Interrupted Exception : " + interruptedException.getMessage());
			}
			
			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
		}
		catch(NoMoreDataException noMoreDataException)
		{
			log.error(noMoreDataException.getMessage(), noMoreDataException);
			throw noMoreDataException;
		}
		finally
		{
			dbObject = null;
			mapKeyConf = null;
			jsonArray = null;
			localKeyArray = null;
			mapKeyArray = null;
			mapKeyObject = null;
			mapKey = null;
			localKey = null;
			keyType = null;
			
			log.info("Exit fetchData..");
		}
	}
	
	public void stop()
	{
		try 
		{
			if(cursor != null)
				cursor.close();
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}