package com.enhancesys.jobengine.services.pg.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
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
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

public class PostgresDBPrimaryDataSortProcessor extends DBProcessor
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
	private BufferedReader bufferReader = null;
	private FileReader fileReader = null;
	private static Logger log = Logger.getLogger(PostgresDBPrimaryDataSortProcessor.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfigurationReader, jobParameters);
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) collectionArr.get(collectionPosition);
			connection = jdbcConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(collectionConf.get(DataConstants.QUERY).toString());
			statement.setFetchSize(100);
			cursor = statement.executeQuery();
			metaData = cursor.getMetaData();
//			log.info(pipeLineName + " : " + processorName + " :  Cursor : " + cursor);
			
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
			log.error(exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info("Exit init..");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void sortData()
	{
		log.info("Entry sortData..");
		
		FileOutputStream outputStream = null;
		File file = null;
		File fileCopy = null;
		JSONObject sortConfig = null;
		BasicDBObject dbObject = null;
		Set<String> sortColumns = null;
		DateFormat format = null;
		Process process = null;
		String fName = null;
		String fileName  = null;                                             
		String delimiter = null; 
		String dataLine = null;
		Object value = null;
		
		try
		{
			sortConfig = (JSONObject) jobConfigData.get(DataConstants.SORT_CONF);
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
			log.info("SortColumns : " + sortColumns);
			
			file = new File(fileName);
			outputStream = new FileOutputStream(file);
			while(cursor.next())
			{
				dataLine = "";
				dbObject = Util.convertToDBObject(cursor, metaData);
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
				outputStream.write(dataLine.getBytes());
			}
			outputStream.close();
			cursor.close();
			statement.close();
			connection.close();
			
			fileCopy = new File(fileName + ".cp");
			FileUtils.copyFile(new File(fileName), fileCopy);

			//Sort the file at unix level using the sh file..
			log.info("Sort file script is initiated for file :: " + fileCopy.getPath());
			log.info("Script file : " + sortConfig.get(DataConstants.SORT_FILE_PATH + "_" + collectionPosition));
			process = Runtime.getRuntime().exec(sortConfig.get(DataConstants.SORT_FILE_PATH + "_" + collectionPosition).toString() + " " + fileCopy.getPath() + " " + fileCopy.getPath());
			log.info("Process : waitFor() :: " + process.waitFor());
			
			fileReader = new FileReader(fileCopy);
			bufferReader = new BufferedReader(fileReader);
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(cursor != null)
					cursor.close();
				if(statement != null)
					statement.close();
				if(connection != null)
					connection.close();
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
			value = null;
			
			log.info("Exit sortData..");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException
	{
		log.info("Entry fetchData..");
		
		JSONObject dbObject = null;
		JSONObject mapKeyConf = null;
		JSONArray jsonArray = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONObject mapKeyObject = null;
		BasicDBObject tempObject = null;
		Object mapKey = null;
		Object localKey = null;
		String keyType = null; 
		List<Object> values = null;
		String dataLine = null;
		
		try
		{
			try 
			{
				if((dataLine = bufferReader.readLine()) != null)
				{
					dataLine = dataLine.substring(dataLine.lastIndexOf("|") + 1, dataLine.length());
					tempObject = (BasicDBObject) JSON.parse(dataLine);
					dbObject = new JSONObject();
					dbObject.putAll(tempObject);
					jsonArray = new JSONArray();
					log.info(pipeLineName + " : " + processorName + " : Primary : " + dbObject);
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
								if(localKeyArray.get(i).toString().contains("."))
								{
									try 
									{
										Object value = null;
										values = Util.getFieldValue(localKeyArray.get(i).toString(), dbObject);
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
									values = Util.getFieldValue(localKey.toString(), dbObject);
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
			catch (IOException e) 
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
			tempObject = null;
			mapKey = null;
			localKey = null;
			keyType = null;
			dataLine = null;
			
			log.info("Exit fetchData..");
		}
	}
	
	public void stop()
	{
		try 
		{
			if(fileReader != null)
				fileReader.close();
			if(bufferReader != null)
				bufferReader.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}