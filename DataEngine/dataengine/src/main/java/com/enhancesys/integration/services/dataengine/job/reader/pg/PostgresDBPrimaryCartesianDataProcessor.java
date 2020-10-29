package com.enhancesys.integration.services.dataengine.job.reader.pg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.reader.DBProcessor;
import com.enhancesys.integration.services.dataengine.job.util.pg.JdbcConnectionUtil;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class PostgresDBPrimaryCartesianDataProcessor extends DBProcessor
{
	@Autowired
	JdbcConnectionUtil jdbcConnectionUtil;
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet cursor = null;
	private ResultSetMetaData metaData = null;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount = 0;
	private int collectionPosition = 0;
	private JSONObject collectionConf = null;
	@SuppressWarnings("unused")
	private BufferedReader bufferReader = null;
	@SuppressWarnings("unused")
	private FileReader fileReader = null;
	private List<List<BasicDBObject>> dbObjectList = null; 
	private List<BasicDBObject> dataList = null;
	private Integer position = 0; 
	private static Logger log = Logger.getLogger(PostgresDBPrimaryDataSortProcessor.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, _processorConfig_, jobConfiguration, jobParameters);
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) _collectionArr_.get(collectionPosition);
			
			if(primaryConfig.get("job_count") != null && !primaryConfig.get("job_count").toString().trim().isEmpty())
				this.jobCount = (Long) primaryConfig.get("job_count");
			else
				this.jobCount = -1;
			
			if(primaryConfig.get("sleep_time") != null && !primaryConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) primaryConfig.get("sleep_time");
			else
				this.sleepTime = 0;
			
			if(primaryConfig.get("delay_count") != null && !primaryConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) primaryConfig.get("delay_count");
			else
				this.delayCount = 0;
			
			sortData();
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
	
	@SuppressWarnings({ "unused" })
	private void sortData()
	{
		log.info("Entry sortData..");
		
		FileOutputStream outputStream = null;
		File file = null;
		File fileCopy = null;
		JSONObject sortConfig = null;
		JSONArray queries = null;
		BasicDBObject dbObject = null;
		Set<String> sortColumns = null;
		DateFormat format = null;
		Process process = null;
		String fName = null;
		String fileName  = null;                                             
		String delimiter = null; 
		String dataLine = null;
		PreparedStatement localStatement = null;
		ResultSet localCursor = null;
		ResultSetMetaData localMetaData = null;
		List<BasicDBObject> dbObjects = null;
		
		try
		{
			/*sortConfig = (JSONObject) jobConfigData.get(DataConstants.SORT_CONF);
			format = new SimpleDateFormat(sortConfig.get(DataConstants.FILE_NAME_PATTERN).toString());
			if(sortConfig.get(DataConstants.FILE_NAME) != null)
				fName = sortConfig.get(DataConstants.FILE_NAME).toString();
			else
				fName = collectionConf.get(DataConstants.COLLECTION_NAME).toString();
			fileName = sortConfig.get(DataConstants.FILE_PATH).toString() + fName + "_" + format.format(new Date()) + "." + sortConfig.get(DataConstants.EXTENSION).toString();
			delimiter = sortConfig.get(DataConstants.FIELD_DELIMITER).toString();
			if(collectionConf.get(DataConstants.SORT_BY) == null || collectionConf.get(DataConstants.SORT_BY).toString().trim().isEmpty())
				return;
			
			sortColumns = ((JSONObject)collectionConf.get(DataConstants.SORT_BY)).keySet();
//			log.info("SortColumns : " + sortColumns);
			log.info("SortColumns : " + sortColumns);
			
			file = new File(fileName);
			outputStream = new FileOutputStream(file);*/
			
			queries = (JSONArray) collectionConf.get(DataConstants.QUERY);
			connection = jdbcConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			int cursorCount = 0;
//			int totalCount = 1;
			dbObjectList = new ArrayList<List<BasicDBObject>>();
			for(Object query : queries)
			{
				if(cursorCount == 0)
				{
					statement = connection.prepareStatement(query.toString());
					statement.setFetchSize(100);
					cursor = statement.executeQuery();
					metaData = cursor.getMetaData();
//					log.info(pipeLineName + " : " + processorName + " : Cursor " + cursorCount + " : " + cursor);
				}
				else
				{
					localStatement = connection.prepareStatement(query.toString());
					localStatement.setFetchSize(100);
					localCursor = localStatement.executeQuery();
					localMetaData = localCursor.getMetaData();
					dbObjects = new ArrayList<BasicDBObject>();
					while(localCursor.next())
					{
						dbObject = Utilities.convertToDBObject(localCursor, localMetaData);
						dbObjects.add(dbObject);
					}
//					log.info(pipeLineName + " : " + processorName + " : Cursor " + cursorCount + " : " + dbObjects.size());
					if(!dbObjects.isEmpty())
					{
						dbObjectList.add(dbObjects);
//						totalCount = totalCount * dbObjects.size();
					}
					localCursor.close();
					localStatement.close();
				}
				cursorCount ++;
			}
			
//			log.info("totalCount : " + totalCount);
			//Preparing combinations..
			/*for(BasicDBObject obj1: dbObjectList.get(0))
			{
				if(dbObjectList.size() == 2 || dbObjectList.size() > 2)
				{
					for(BasicDBObject obj2 : dbObjectList.get(1))
					{
						if(dbObjectList.size() == 3 || dbObjectList.size() > 3)
						{
							for(BasicDBObject obj3 : dbObjectList.get(2))
							{
								dbObject = new BasicDBObject();
								dbObject.putAll((DBObject) obj1);
								dbObject.putAll((DBObject) obj2);
								dbObject.putAll((DBObject) obj3);
								dataLine = prepareDataLine(dbObject, sortColumns, delimiter);
								outputStream.write(dataLine.getBytes());
							}
						}
						else
						{
							dbObject = new BasicDBObject();
							dbObject.putAll((DBObject) obj1);
							dbObject.putAll((DBObject) obj2);
							dataLine = prepareDataLine(dbObject, sortColumns, delimiter);
							outputStream.write(dataLine.getBytes());
						}
					}
				}
				else
				{
					dataLine = prepareDataLine(obj1, sortColumns, delimiter);
					outputStream.write(dataLine.getBytes());
				}
			}
			
			outputStream.close();*/
			if(localCursor != null)
				localCursor.close();
			if(localStatement != null)
				localStatement.close();
//			connection.close();
			
			if(cursor.next())
			{
				dataList = prepareCombinations();
			}
			
			/*fileCopy = new File(fileName + ".cp");
			FileUtils.copyFile(new File(fileName), fileCopy);

			//Sort the file at unix level using the sh file..
			log.info("Sort file script is initiated for file :: " + fileCopy.getPath());
			log.info("Script file : " + sortConfig.get(DataConstants.SORT_FILE_PATH + "_" + collectionPosition));
			process = Runtime.getRuntime().exec(sortConfig.get(DataConstants.SORT_FILE_PATH + "_" + collectionPosition).toString() + " " + fileCopy.getPath() + " " + fileCopy.getPath());
			log.info("Process : waitFor() :: " + process.waitFor());
			
			fileReader = new FileReader(fileCopy);
			bufferReader = new BufferedReader(fileReader);*/
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
			file = null;
			fileCopy = null;
			sortConfig = null;
			dbObject = null;
			sortColumns = null;
			format = null;
			process = null;
			fName = null;
			fileName  = null;                                             
			delimiter = null; 
			dataLine = null;
			localMetaData = null;
			dbObjects = null;
//			dbObjectList = null;
			
			log.info("Exit sortData..");
		}
	}
	
	private List<BasicDBObject> prepareCombinations()
	{
		List<BasicDBObject> dataList = null;
		BasicDBObject dbObject = null;
		BasicDBObject tempObject = null;
		
		try
		{
			tempObject = Utilities.convertToDBObject(cursor, metaData);
			dataList = new ArrayList<BasicDBObject>();
			if(dbObjectList.size() == 1 || dbObjectList.size() > 1)
			{
				for(BasicDBObject tempObject2 : dbObjectList.get(0))
				{
					if(dbObjectList.size() == 2 || dbObjectList.size() > 2)
					{
						for(BasicDBObject tempObject3 : dbObjectList.get(1))
						{
							dbObject = new BasicDBObject();
							dbObject.putAll((DBObject) tempObject);
							dbObject.putAll((DBObject) tempObject2);
							dbObject.putAll((DBObject) tempObject3);
							dataList.add(dbObject);
						}
					}
					else
					{
						dbObject = new BasicDBObject();
						dbObject.putAll((DBObject) tempObject);
						dbObject.putAll((DBObject) tempObject2);
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
	
	/*private String prepareDataLine(BasicDBObject dbObject, Set<String> sortColumns, String delimiter)
	{
		String dataLine = "";
		Object value = null;
		
		try
		{
			for(String column : sortColumns)
			{
				if(column.contains("."))
				{
					value = Util.getFieldValue(column, dbObject);
					dataLine = dataLine + (value != null ? value.toString() : "") + delimiter;
				}
				else
					dataLine = dataLine + (dbObject.get(column) != null ? dbObject.get(column).toString() : "") + delimiter;
			}
			dataLine = dataLine + dbObject.toString() + System.getProperty("line.separator"); 
			return dataLine;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		return dataLine;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException
	{
//		log.info("Entry fetchData..");
		log.error("Entry fetchData..");
		
		JSONObject dbObject = null;
		JSONObject mapKeyConf = null;
		JSONArray jsonArray = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONObject mapKeyObject = null;
//		BasicDBObject tempObject = null;
		Object mapKey = null;
		Object localKey = null;
		String keyType = null; 
		List<Object> values = null;
//		String dataLine = null;
		
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
					dbObject = new JSONObject();
					dbObject.putAll(dataList.get(position));
					position ++;
					jsonArray = new JSONArray();
					log.info(_pipeLineName_ + " : " + _processorName_ + " : Primary : " + dbObject);
					jsonArray.add(dbObject);
					fetchedData.put(DataConstants.DATA_LIST, jsonArray);
//					log.info(pipeLineName + " : " + processorName + " : Data : " + fetchedData);
					keyField.clear();
					mapKeyConf = (JSONObject) ((JSONObject) _collectionArr_.get(collectionPosition)).get(DataConstants.MAP_KEY);
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
								if(localKeyArray.get(i).toString().contains("."))
								{
									try 
									{
										Object value = null;
										values = Utilities.getFieldValue(localKeyArray.get(i).toString(), dbObject);
										if(values != null && !values.isEmpty())
											 value = values.iterator().next();
										if(value != null)
										{
											if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
												mapKeyObject.put(mapKeyArray.get(i).toString(), value.toString());
											
											if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
												mapKeyObject.put(mapKeyArray.get(i).toString(), Long.parseLong(value.toString()));
										}
										else
											mapKeyObject = null;
									} 
									catch (Exception e) 
									{
										e.printStackTrace();
									}
								}
								else if(dbObject.get(localKeyArray.get(i).toString()) != null)
								{
									if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
										mapKeyObject.put(mapKeyArray.get(i).toString(), dbObject.get(localKeyArray.get(i).toString()).toString());
									if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
										mapKeyObject.put(mapKeyArray.get(i).toString(), Long.parseLong(dbObject.get(localKeyArray.get(i).toString()).toString()));
								}
								else
									mapKeyObject = null;
							}
							
							if(mapKeyObject != null)
								keyField.add(mapKeyObject);
						}
						else
						{
							if(localKey.toString().contains("."))
							{
								try 
								{
									Object value = null; 
									values = Utilities.getFieldValue(localKey.toString(), dbObject);
									if(values != null && !values.isEmpty())
										value = values.iterator().next();
									if(value != null)
									{
										if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
											mapKeyObject.put(mapKey.toString(), value.toString());
										
										if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
											mapKeyObject.put(mapKey.toString(), Long.parseLong(value.toString()));
									}
									else
										mapKeyObject = null;
								} 
								catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
							else if(dbObject.get(localKey.toString()) != null)
							{
								if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
									mapKeyObject.put(mapKey.toString(), dbObject.get(localKey.toString()).toString());
								if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
									mapKeyObject.put(mapKey.toString(), Long.parseLong(dbObject.get(localKey.toString()).toString()));
							}
							else
								mapKeyObject = null;
							
							if(mapKeyObject != null)
								keyField.add(mapKeyObject);
						}
						log.info(_pipeLineName_ + " : " + _processorName_ + " : keyField : " + keyField);
					}
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
//			tempObject = null;
			mapKey = null;
			localKey = null;
			keyType = null;
//			dataLine = null;
			
//			log.info("Exit fetchData..");
			log.error("Exit fetchData..");
		}
	}
	
	public void stop()
	{
		try 
		{
			/*if(fileReader != null)
				fileReader.close();
			if(bufferReader != null)
				bufferReader.close();*/
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