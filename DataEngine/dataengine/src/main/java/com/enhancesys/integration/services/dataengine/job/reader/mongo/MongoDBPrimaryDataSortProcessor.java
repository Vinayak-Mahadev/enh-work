package com.enhancesys.integration.services.dataengine.job.reader.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.reader.DBProcessor;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoDataUtil;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoTimeoutException;
import com.mongodb.util.JSON;

public class MongoDBPrimaryDataSortProcessor extends DBProcessor 
{
	@Autowired
	MongoDataUtil mongoDataUtil;

	private DBCursor cursor = null;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount = 0;
	private int collectionPosition = 0;
	private JSONObject collectionConf = null;
	private BufferedReader bufferReader = null;
	private FileReader fileReader = null;
	File file = null;
	private File fileCopy = null;
	private static Logger LOGGER = Logger.getLogger(MongoDBPrimaryDataSortProcessor.class);

	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		LOGGER.info("Entry init..");
		try
		{
			super.init(pipeLineName, processorName, _processorConfig_, jobConfiguration, jobParameters);
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) _collectionArr_.get(collectionPosition);
			cursor = mongoDataUtil.getCursor(collectionConf, false);
			LOGGER.info(pipeLineName + " : " + processorName + " :  Cursor Size : " + cursor.size());

			if(cursor.count() == 0)
			{
				throw new Exception("No data found in primary collection");
			}

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
		catch (MongoTimeoutException exception) 
		{
			LOGGER.error("Not able to connect mongo : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Not able to connect mongo : " + exception.getMessage(), exception);
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			LOGGER.info("Exit init..");
		}
	}

	@SuppressWarnings("unchecked")
	private void sortData()
	{
		LOGGER.info("Entry sortData..");

		FileOutputStream outputStream = null;
		JSONObject sortConfig = null;
		DBObject dbObject = null;
		Set<String> sortColumns = null;
		DateFormat format = null;
		Process process = null;
		String fName = null;
		String fileName  = null;                                             
		String delimiter = null; 
		String dataLine = null;
		Object value = null;
		String command = null;
		try
		{
			sortConfig = (JSONObject) _jobConfigData_.get(DataConstants.SORT_CONF);
			format = new SimpleDateFormat(sortConfig.get(DataConstants.FILE_NAME_PATTERN).toString());
			if(sortConfig.get(DataConstants.FILE_NAME) != null)
				fName = sortConfig.get(DataConstants.FILE_NAME).toString();
			else
				fName = collectionConf.get(DataConstants.COLLECTION_NAME).toString();

			fileName = DataConstants._JOB_SORT_SCRIPTS_CONF_FILE_PATH + fName + "_" + format.format(new Date()) + "." + sortConfig.get(DataConstants.EXTENSION).toString();

			delimiter = sortConfig.get(DataConstants.FIELD_DELIMITER).toString();
			if(collectionConf.get(DataConstants.SORT_BY) == null || collectionConf.get(DataConstants.SORT_BY).toString().trim().isEmpty())
				return;

			sortColumns = ((JSONObject)collectionConf.get(DataConstants.SORT_BY)).keySet();
			LOGGER.info("SortColumns : " + sortColumns);

			file = new File(fileName);
			outputStream = new FileOutputStream(file);
			while(cursor.hasNext())
			{
				dataLine = "";
				dbObject = cursor.next();
				for(String column : sortColumns)
				{
					if(column.contains("."))
					{
						value = Utilities.getFieldValue(column, dbObject);
						dataLine = dataLine + (value != null ? value.toString() : "") + delimiter;
					}
					else
						dataLine = dataLine + (dbObject.get(column) != null ? dbObject.get(column).toString() : "") + delimiter;
				}
				dataLine = dataLine + JSON.serialize(dbObject) + System.getProperty("line.separator"); 
				outputStream.write(dataLine.getBytes());
			}
			outputStream.close();
			cursor.close();

			fileCopy = new File(fileName + ".cp");
			FileUtils.copyFile(new File(fileName), fileCopy);

			LOGGER.info("Sort file script is initiated for file :: " + fileCopy.getPath());
			
			command = DataConstants._JOB_SORT_SCRIPTS_PATH  + sortConfig.get(DataConstants.SORT_FILE_NAME + "_" + collectionPosition).toString() + " " + fileCopy.getPath() + " " + fileCopy.getPath();

			if(System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) // sort with git bash
			{
				if(!DataConstants.GIT_BASH_PATH.isEmpty())
				{
					LOGGER.info("Runtime cmd :: " + DataConstants.GIT_BASH_PATH + " " +  command);
					process = Runtime.getRuntime().exec(new String[] {DataConstants.GIT_BASH_PATH , command});
					LOGGER.info("Process : waitFor() :: " + process.waitFor());
				}
				else
					LOGGER.info("Script sorting not happen..");
			}
			else//Sort the file at unix level using the sh file..
			{
				LOGGER.info("Runtime cmd :: " + command);
				process = Runtime.getRuntime().exec(command);
				LOGGER.info("Process : waitFor() :: " + process.waitFor());
			}

			fileReader = new FileReader(fileCopy);
			bufferReader = new BufferedReader(fileReader);
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(outputStream != null)
					outputStream.close();
			}
			catch(Exception exception2)
			{
				exception2.printStackTrace();
			}
			outputStream = null;
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
			command = null;
			LOGGER.info("Exit sortData..");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fetchData(JSONArray keyField, final JSONObject fetchedData) throws NoMoreDataException 
	{
		LOGGER.debug("Entry fetchData..");

		JSONObject dbObject = null;
		JSONObject mapKeyConf = null;
		JSONArray jsonArray = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONObject mapKeyObject = null;
		//		DBObject tempObject = null;
		Object mapKey = null;
		Object localKey = null;
		String keyType = null; 
		List<Object> values = null;
		String dataLine = null;

		try
		{
			if(bufferReader != null && (dataLine = bufferReader.readLine()) != null)
			{
				dataLine = dataLine.substring(dataLine.lastIndexOf("|") + 1, dataLine.length());
				//				tempObject = (DBObject) JSON.parse(dataLine);
				dbObject = (JSONObject) new JSONParser().parse(dataLine);
				jsonArray = new JSONArray();
				//				testObject = new JSONObject();
				//				dbObject.put("test", testObject);
				LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : Primary : " + dbObject);
				jsonArray.add(dbObject);
				fetchedData.put(DataConstants.DATA_LIST, jsonArray);
				//				log.info(pipeLineName + " : " + processorName + " : Data : " + fetchedData);
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
					LOGGER.info(_pipeLineName_ + " : " + _processorName_ + " : keyField : " + keyField);
				}
			}
			else
			{
				LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
				if(bufferReader != null) {
					bufferReader.close();
					bufferReader = null;
				}
				throw new NoMoreDataException(_pipeLineName_ + " : " + _processorName_ + " : No more data exists in the cursor..");
			}

			try
			{
				Thread.sleep(sleepTime);
			}
			catch (InterruptedException interruptedException)
			{
				LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " :  Interrupted Exception : " + interruptedException.getMessage());
			}

			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
		}
		catch(NoMoreDataException noMoreDataException)
		{
			throw noMoreDataException;
		} 
		catch (Exception exception) 
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
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
			LOGGER.debug("Exit fetchData..");
		}
	}

	@Override
	public void stop() 
	{
		try
		{
			if(fileReader != null)
				fileReader.close();
			if(bufferReader != null)
				bufferReader.close();
			if(file.exists())
				file.delete();
			if(fileCopy.exists())
				fileCopy.delete();
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
	}
}