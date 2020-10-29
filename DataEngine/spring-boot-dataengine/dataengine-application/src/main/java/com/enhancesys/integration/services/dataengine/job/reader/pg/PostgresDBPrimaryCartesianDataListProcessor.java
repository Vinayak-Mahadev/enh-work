package com.enhancesys.integration.services.dataengine.job.reader.pg;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.reader.DBProcessor;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;
import com.enhancesys.integration.services.dataengine.util.pg.JdbcConnectionUtil;

public class PostgresDBPrimaryCartesianDataListProcessor extends DBProcessor
{
	@Autowired
	JdbcConnectionUtil jdbcConnectionUtil;
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet cursor = null;
	private long sleepTime;
	private long delayCount = 0;
	private int collectionPosition = 0;
	private JSONObject collectionConf = null;
	private List<List<List<String>>> dbObjectList = null; 
	private List<List<String>> dataList = null;
	private Integer position = 0; 
	private int columnCount = 0;
	private static Logger log = Logger.getLogger(PostgresDBPrimaryDataSortProcessor.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, _processorConfig_, jobConfiguration, jobParameters);
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) _collectionArr_.get(collectionPosition);
			
			if(primaryConfig.get("sleep_time") != null && !primaryConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) primaryConfig.get("sleep_time");
			else
				this.sleepTime = 0;
			
			if(primaryConfig.get("delay_count") != null && !primaryConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) primaryConfig.get("delay_count");
			else
				this.delayCount = 0;
			
			readData();
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
	
	private void readData()
	{
		log.info("Entry readData..");
		
		FileOutputStream outputStream = null;
		JSONArray queries = null;
		List<String> dbObject = null;
		PreparedStatement localStatement = null;
		ResultSet localCursor = null;
		List<List<String>> dbObjects = null;
		
		try
		{
			queries = (JSONArray) collectionConf.get(DataConstants.QUERY);
			connection = jdbcConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			int cursorCount = 0;
			dbObjectList = new ArrayList<List<List<String>>>();
			for(Object query : queries)
			{
				if(cursorCount == 0)
				{
					statement = connection.prepareStatement(query.toString());
					statement.setFetchSize(100);
					cursor = statement.executeQuery();
					columnCount = cursor.getMetaData().getColumnCount();
//					log.info(pipeLineName + " : " + processorName + " : Cursor " + cursorCount + " : " + cursor);
				}
				else
				{
					localStatement = connection.prepareStatement(query.toString());
					localStatement.setFetchSize(100);
					localCursor = localStatement.executeQuery();
					dbObjects = new ArrayList<List<String>>();
					while(localCursor.next())
					{
						dbObject = Utilities.convertToDataList(localCursor, localCursor.getMetaData().getColumnCount());
						dbObjects.add(dbObject);
					}
//					log.info(pipeLineName + " : " + processorName + " : Cursor " + cursorCount + " : " + dbObjects.size());
					if(!dbObjects.isEmpty())
					{
						dbObjectList.add(dbObjects);
					}
					localCursor.close();
					localStatement.close();
				}
				cursorCount ++;
			}
			
			//Preparing combinations..
			if(localCursor != null)
				localCursor.close();
			if(localStatement != null)
				localStatement.close();
			
			if(cursor.next())
			{
				dataList = prepareCombinations();
			}
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(localCursor != null)
					localCursor.close();
				if(localStatement != null)
					localStatement.close();
//				if(connection != null)
//					connection.close();
				if(outputStream != null)
					outputStream.close();
			}
			catch(Exception exception2)
			{
				exception2.printStackTrace();
			}
			outputStream = null;
			dbObject = null;
			dbObjects = null;
//			dbObjectList = null;
			
			log.info("Exit readData..");
		}
	}
	
	private List<List<String>> prepareCombinations()
	{
		List<List<String>> dataList = null;
		List<String> dbObject = null;
		List<String> tempObject = null;
		
		try
		{
			tempObject = Utilities.convertToDataList(cursor, columnCount);
			dataList = new ArrayList<List<String>>();
			if(dbObjectList.size() == 1 || dbObjectList.size() > 1)
			{
				for(List<String> tempObject2 : dbObjectList.get(0))
				{
					if(dbObjectList.size() == 2 || dbObjectList.size() > 2)
					{
						for(List<String> tempObject3 : dbObjectList.get(1))
						{
							dbObject = new ArrayList<String>();
							dbObject.addAll(tempObject);
							dbObject.addAll(tempObject2);
							dbObject.addAll(tempObject3);
							dataList.add(dbObject);
						}
					}
					else
					{
						dbObject = new ArrayList<String>();
						dbObject.addAll(tempObject);
						dbObject.addAll(tempObject2);
						dataList.add(dbObject);
					}
				}
			}
			else
			{
				dataList.add(tempObject);
			}
			
			return dataList;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			dataList = null;
			dbObject = null;
			tempObject = null;
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException
	{
//		log.info("Entry fetchData..");
//		log.error("Entry fetchData..");
		
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
				if(dataList == null || position >= dataList.size())
				{
					try 
					{
						if(cursor.next())
						{
							dataList.clear();
							dataList = null;
							dataList = prepareCombinations();
							position = 0;
						}
						else
						{
							log.error(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
							throw new NoMoreDataException(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
						}
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					catch(NoMoreDataException noMoreDataException)
					{
						throw noMoreDataException;
					}
				}
				if(dataList.get(position) != null)
				{
					dbObject = new ArrayList<String>();
					dbObject.addAll(dataList.get(position));
					position ++;
					jsonArray = new JSONArray();
					log.info(_pipeLineName_ + " : " + _processorName_ + " : Primary : " + dbObject);
//					log.info(pipeLineName + " : " + processorName + " : Primary : " + dbObject);
					jsonArray.add(dbObject);
					fetchedData.put(DataConstants.DATA_LIST, jsonArray);
//					log.info(pipeLineName + " : " + processorName + " : Data : " + fetchedData);
					/*keyField.clear();
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
//					log.info(pipeLineName + " : " + processorName + " : keyField : " + keyField);
						log.info(pipeLineName + " : " + processorName + " : keyField : " + keyField);
					}*/
				}
				else
				{
					log.error(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
					throw new NoMoreDataException(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
				}
			} 
			catch (NumberFormatException e) 
			{
				e.printStackTrace();
			}
			catch (NoMoreDataException noMoreDataException)
			{
				throw noMoreDataException;
			}
			/*catch (SQLException e) 
			{
				e.printStackTrace();
			} 
			catch (GenericProcessorException e) 
			{
				e.printStackTrace();
			}*/
			
			try
			{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException interruptedException)
			{
				log.error(_pipeLineName_ + " : " + _processorName_ + " :  Interrupted Exception : " + interruptedException.getMessage());
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
			
//			log.info("Exit fetchData..");
//			log.error("Exit fetchData..");
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
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}