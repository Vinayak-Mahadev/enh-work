package com.enhancesys.integration.services.dataengine.job.reader.pg;

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
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.reader.DBProcessor;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;
import com.enhancesys.integration.services.dataengine.util.pg.JdbcConnectionUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class PostgresDBSecondaryDataSortProcessor extends DBProcessor
{
	@Autowired
	JdbcConnectionUtil jdbcConnectionUtil;
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet cursor = null;
	private ResultSetMetaData metaData = null;
	private BufferedReader bufferReader = null;
	private FileReader fileReader = null;
	private DBObject lastFetchedObject = null;
	private JSONObject collectionConf = null;
	private JSONObject aliasObject = null;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private int collectionPosition = 0;
	private String name = null;
	private static Logger log = Logger.getLogger(PostgresDBSecondaryDataSortProcessor.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, _processorConfig_, jobConfiguration, jobParameters);
			name = primaryConfig.get("name").toString();
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) _collectionArr_.get(collectionPosition);
			if(collectionConf.get(DataConstants.ALIAS) != null && !collectionConf.get(DataConstants.ALIAS).toString().isEmpty())
				aliasObject = (JSONObject) collectionConf.get(DataConstants.ALIAS);

			connection = jdbcConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(collectionConf.get(DataConstants.QUERY).toString());
			statement.setFetchSize(100);
			cursor = statement.executeQuery();
			metaData = cursor.getMetaData();
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : Cursor : " + cursor);
			
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
		log.info("Entry init..");
		
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
			sortConfig = (JSONObject) _jobConfigData_.get(DataConstants.SORT_CONF);
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
				dbObject = Utilities.convertToDBObject(cursor, metaData);
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
			log.info("Script file : " + sortConfig.get(DataConstants.SORT_FILE_PATH + "_" + collectionPosition).toString());
			process = Runtime.getRuntime().exec(sortConfig.get(DataConstants.SORT_FILE_PATH + "_" + collectionPosition).toString() + " " + fileCopy.getPath() + " " + fileCopy.getPath());
			log.info("Process : waitFor() :: " + process.waitFor());
			
			fileReader = new FileReader(fileCopy);
			bufferReader = new BufferedReader(fileReader);
		}
		catch(Exception exception)
		{
			log.error(exception.getMessage(), exception);
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
		
		JSONArray jsonArray = null;
//		JSONArray dataArray = null;
		JSONObject parentObject = null;
		JSONObject mapKeyObject = null;
		JSONObject mapKeyConf = null;
		JSONObject parentKeyObj = null;
		JSONObject parentKey = null;
		JSONObject childObject = null;
		JSONParser parser = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONArray tempKeyField = null;
		JSONArray tempJsonArray = null;
		Set<String> keySet = null;
		String currObjectKeyVal = null;
		String compareValue = null;
		String mergeTo = null;
		String mergeType = null;
		String previousKeyType = null;
		String keyType = null;
		Object mapKey = null;
		Object localKey = null;
		String dataLine = null;
		boolean flag = false;
		
		try
		{
			log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : inside..");
			parser = new JSONParser();
			jsonArray = (JSONArray) fetchedData.get(DataConstants.DATA_LIST);
			log.info(name + " : jsonArray : " + jsonArray);
			if(jsonArray == null)
				return;
			tempJsonArray = new JSONArray();
			tempJsonArray.addAll(jsonArray);
			mergeTo = (collectionConf.get(DataConstants.MERGE_TO) != null && !collectionConf.get(DataConstants.MERGE_TO).toString().trim().isEmpty()) ? collectionConf.get(DataConstants.MERGE_TO).toString() : null;
			mergeType = (collectionConf.get(DataConstants.MERGE_TYPE) != null && !collectionConf.get(DataConstants.MERGE_TYPE).toString().trim().isEmpty()) ? collectionConf.get(DataConstants.MERGE_TYPE).toString() : null;
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : JSON Arr Size.." + jsonArray.size());
			if(jsonArray.size() == 1)
			{
				if(DataConstants.INCLUDE_PARENT.equalsIgnoreCase(mergeType))
					parentObject = (JSONObject) jsonArray.remove(0);
				else
					parentObject = (JSONObject) jsonArray.get(0);
			}

			mapKeyConf = (JSONObject) ((JSONObject) _collectionArr_.get(collectionPosition)).get(DataConstants.MAP_KEY);
			parentKey = (JSONObject) ((JSONObject) _collectionArr_.get(collectionPosition)).get(DataConstants.PARENT_KEY);
			if(mapKeyConf != null)
			{
				mapKey = mapKeyConf.get(DataConstants.KEY);
				localKey = mapKeyConf.get(DataConstants.LOCAL_KEY);
				keyType = mapKeyConf.get(DataConstants.TYPE) != null ? mapKeyConf.get(DataConstants.TYPE).toString() : "String";
			}
			tempKeyField = new JSONArray();
			int keyFieldSize = keyField.size();
			
			if(keyField == null || keyField.size() == 0)
				return;
			outer : while(true)
			{
				/*if("Secondary 2".equals(name))
					log.info(name + " : Key Field : " + keyField);*/
				if(lastFetchedObject != null)
				{
					for(int i = 0; i < keyFieldSize; i++)
					{
						mapKeyObject = (JSONObject) keyField.get(i);
						keySet = mapKeyObject.keySet();
						currObjectKeyVal = "";
						compareValue = "";
						for(String mKey : keySet)
						{
//							if("Secondary 2".equals(name))
//								log.info(name + " : " + mKey + " : " + lastFetchedObject);
							if(previousKeyType == null)
							{
								if(mapKeyObject.get(mKey) instanceof Long)
									previousKeyType = DataConstants.LONG_TYPE;
								if(mapKeyObject.get(mKey) instanceof String)
									previousKeyType = DataConstants.STRING_TYPE;
							}

							try 
							{
								if(mKey.contains("."))
								{
									if(Utilities.getFieldValue(mKey, lastFetchedObject) == null)
									{
										log.info("Breaking...");
										break outer;
									}
								}
								else if(lastFetchedObject.get(mKey) == null || lastFetchedObject.get(mKey).toString().trim().isEmpty())
								{
									log.info("Breaking...");
									break outer;
								}
							} 
							catch (Exception e) 
							{
								log.error(e.getMessage(), e);
								e.printStackTrace();
							}
							
							if(currObjectKeyVal.trim().isEmpty())
							{
								try 
								{
									currObjectKeyVal = mKey.contains(".") ? Utilities.getFieldValue(mKey, lastFetchedObject).toString() : lastFetchedObject.get(mKey).toString().trim();
								} 
								catch (Exception e) 
								{
									log.error(e.getMessage(), e);
									e.printStackTrace();
								}
							}
							else
							{
								currObjectKeyVal = currObjectKeyVal + lastFetchedObject.get(mKey).toString().trim();
							}
							if(compareValue.trim().isEmpty())
								compareValue = mapKeyObject.get(mKey).toString().trim();
							else
								compareValue = compareValue + mapKeyObject.get(mKey).toString().trim();
						}

						log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : compareValue : " + compareValue + " : currObjectKeyVal : " + currObjectKeyVal);
						if(currObjectKeyVal == null || currObjectKeyVal.trim().isEmpty())
							break outer;
						int compareResult = -1;

						if(DataConstants.LONG_TYPE.equalsIgnoreCase(previousKeyType))
						{
							long objValue = Long.parseLong(currObjectKeyVal);
							long matchValue = Long.parseLong(compareValue);
							if(objValue == matchValue)
								compareResult = 0;
							else if(objValue > matchValue)
								compareResult = 1;
						}
						else if(DataConstants.STRING_TYPE.equalsIgnoreCase(previousKeyType))
							compareResult = currObjectKeyVal.compareTo(compareValue);

						log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : CompareResult : " + compareResult);

						if(compareResult > 0)
						{
//							log.info(pipeLineName + " : " + processorName + " : Removed : " + keyField.remove(i) + " : " + keyField + " : " + keyField.size());
							if(keyFieldSize - 1 == i)
								break outer;
							else
								continue;
						}
						else if(compareResult == 0)
						{
							parentKeyObj = new JSONObject();
							for(Object pKey : parentKey.keySet())
							{
								parentKeyObj.put(parentKey.get(pKey), mapKeyObject.get(pKey));
							}
							
							/*if("Secondary 2".equals(name))
//								log.info(name + " : Going to merge : " + lastFetchedObject);*/
							
							if(mergeType != null)
							{
								if(mapKey != null && localKey != null)
								{
									mapKeyObject = new JSONObject();
									if(localKey instanceof JSONArray)
									{
										localKeyArray = (JSONArray) localKey;
										mapKeyArray = (JSONArray) mapKey;
										
										for(int j = 0; j < localKeyArray.size(); j++)
										{
											if(lastFetchedObject.get(localKeyArray.get(j).toString()) != null)
											{
												if(localKeyArray.get(j).toString().contains("."))
												{
													try 
													{
														Object value = Utilities.getFieldValue(localKeyArray.get(j).toString(), lastFetchedObject);
														if(value != null)
														{
															if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
																mapKeyObject.put(mapKeyArray.get(j).toString(), value.toString());
															
															if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
																mapKeyObject.put(mapKeyArray.get(j).toString(), Long.parseLong(value.toString()));
														}
														else
														{
															mapKeyObject = null;
															break;
														}
													} 
													catch (Exception e) 
													{
														e.printStackTrace();
													}
												}
												else
												{
													Object value = lastFetchedObject.get(localKeyArray.get(j).toString());
													if(value != null)
													{
														if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
															mapKeyObject.put(mapKeyArray.get(j).toString(), value.toString());
														if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
															mapKeyObject.put(mapKeyArray.get(j).toString(), Long.parseLong(value.toString()));
													}
													else
													{
														mapKeyObject = null;
														break;
													}
												}
											}
											else
											{
												mapKeyObject = null;
												break;
											}
										}
										
										if(mapKeyObject != null)
											tempKeyField.add(mapKeyObject);
									}
									else
									{
										if(localKey.toString().contains("."))
										{
											try 
											{
												Object value = Utilities.getFieldValue(localKey.toString(), lastFetchedObject);
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
										else if(lastFetchedObject.get(localKey.toString()) != null)
										{
											if(DataConstants.STRING_TYPE.equalsIgnoreCase(keyType))
												mapKeyObject.put(mapKey.toString(), lastFetchedObject.get(localKey.toString()).toString());
											if(DataConstants.LONG_TYPE.equalsIgnoreCase(keyType))
												mapKeyObject.put(mapKey.toString(), Long.parseLong(lastFetchedObject.get(localKey.toString()).toString()));
										}
										else
											mapKeyObject = null;
										
										if(mapKeyObject != null)
											tempKeyField.add(mapKeyObject);
									}
//									log.info(pipeLineName + " : " + processorName + " : " + name + " : keyField : " + tempKeyField);
								}
								
								if(DataConstants.INCLUDE_PARENT.equalsIgnoreCase(mergeType))
								{
									if(jsonArray.size() > 1)
										parentObject = getParentObject(jsonArray, parentKeyObj);
									if(parentObject != null)
									{
										log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name + " : " + lastFetchedObject);
										lastFetchedObject.putAll(parentObject);
										try 
										{
											childObject = (JSONObject) parser.parse(lastFetchedObject.toString());
											jsonArray.add(childObject);
											flag = true;
										} 
										catch (ParseException e) 
										{
											log.error("Exception while parsing : " + lastFetchedObject);
											e.printStackTrace();
										}
									}
								}
								if(DataConstants.ADD_TO_PARENT.equalsIgnoreCase(mergeType))
								{
									if(jsonArray.size() > 1)
										parentObject = getParentObject(jsonArray, parentKeyObj);
									if(parentObject != null && mergeTo != null)
									{
										/*if(dataArray == null)
										{
											dataArray = new JSONArray();
											parentObject.put(mergeTo.toString(), dataArray);
										}
										
										dataArray = (JSONArray) parentObject.get(mergeTo.toString());
										dataArray.add(lastFetchedObject);*/
										
										mergeObject(parentObject, lastFetchedObject, mergeTo);
										log.info("Parent Object 1 : " + parentObject);
										flag = true;
									}
								}
							}
						}
					}
				}
				
				try 
				{
					if((dataLine = bufferReader.readLine()) != null)
					{
						dataLine = dataLine.substring(dataLine.lastIndexOf("|") + 1, dataLine.length());
						lastFetchedObject = (BasicDBObject) JSON.parse(dataLine);;
						/*if("Secondary 2".equals(name))
							log.info(name + " : fetching object from cursor : ");*/
						if(aliasObject != null)
						{
							for(Object aliasKey : aliasObject.keySet())
							{
								if(aliasKey.toString().contains("."))
								{
									DBObject tempLFObject = lastFetchedObject;
									String[] keyArr = aliasKey.toString().split("\\.");
									if(keyArr.length == 2) //time being solution, need to look for permanent solution.. 
									{
										for(int i = 0; i < keyArr.length - 1; i++)
										{
											tempLFObject = (DBObject) tempLFObject.get(keyArr[i]);
										}
										
										tempLFObject.put(aliasObject.get(aliasKey).toString(), tempLFObject.get(keyArr[keyArr.length - 1]));
										tempLFObject.removeField(keyArr[keyArr.length - 1]);
									}
								}
								else
								{
									if(lastFetchedObject.containsField(aliasKey.toString()))
									{
										lastFetchedObject.put(aliasObject.get(aliasKey).toString(), lastFetchedObject.get(aliasKey.toString()));
										lastFetchedObject.removeField(aliasKey.toString());
									}
								}
							}
						}
//					log.info(pipeLineName + " : " + processorName + " : " + name +  " :  Object : " + lastFetchedObject);
					}
					else
					{
						log.info(name + "Breaking loop on No Data..");
						break;
					}
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
					log.error(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : Interrupted Exception : " + interruptedException.getMessage());
				}
				
				for(long i = 0; i < delayCount; i++)
				{
					for(long j = 0; j < delayCount; j++);
				}
			}
			
			log.info(name + " : Falg : " + flag + " : skipOnNotExist : " + _skipOnNotExist_);
			if(DataConstants.TRUE.equalsIgnoreCase(_skipOnNotExist_))
			{
				if(!flag)
					fetchedData.put(DataConstants.DATA_LIST, null);
				else
				{
					if(!tempKeyField.isEmpty())
					{
						keyField.clear();
						keyField.addAll(tempKeyField);
					}
					log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : " + keyField + " : Data Arr : " + jsonArray.size());
					if(DataConstants.TRUE.equalsIgnoreCase(_secondaryMatch_))
						fetchedData.put(DataConstants.SECONDARY_MATCH, DataConstants.TRUE);
				}
			}
			else 
			{
				if(!flag)
				{
					fetchedData.put(DataConstants.DATA_LIST, tempJsonArray);
					log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : " + keyField + " : Data Arr : " + tempJsonArray.size());
				}
				else
				{
					if(!tempKeyField.isEmpty())
					{
						keyField.clear();
						keyField.addAll(tempKeyField);
					}
					log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : " + keyField + " : Data Arr : " + jsonArray.size());
					if(DataConstants.TRUE.equalsIgnoreCase(_secondaryMatch_))
						fetchedData.put(DataConstants.SECONDARY_MATCH, DataConstants.TRUE);
				}
			}
//				log.info(pipeLineName + " : " + processorName + " : " + name +  " : " + keyField + " : Data Arr : " + jsonArray.size());
		}
		catch(NoMoreDataException exception)
		{
			log.error(exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			jsonArray = null;
			parentObject = null;
			mapKeyObject = null;
			childObject = null;
			parser = null;
			localKeyArray = null;
			mapKeyArray = null;
			tempKeyField = null;
			keySet = null;
			currObjectKeyVal = null;
			compareValue = null;
			mergeTo = null;
			mergeType = null;
			previousKeyType = null;
			mapKey = null;
			localKey = null;
			
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
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void mergeObject(JSONObject parentObject, DBObject lastFetchedObject, String mergePath) throws NoMoreDataException
	{
		log.info("Entry mergeObject..");
		
		JSONObject leafObject = null;
		JSONArray dataArray = null;
		JSONArray tempArray = null;
		Object tempObject = null;
		String[] paths = null;
		
		try
		{
			leafObject = parentObject;
			log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " Path : " + mergePath);
			if(mergePath.contains("."))
			{
				paths = mergePath.split("\\.");
				log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " Paths : " + paths.length);
				for(int i = 0; i < paths.length; i++)
				{
					tempObject = leafObject.get(paths[i]); 
					if(tempObject == null)
					{
						if(i == (paths.length - 1))
						{
//							log.info("inside null..");
							if(dataArray == null)
							{
								dataArray = new JSONArray();
								dataArray.add(lastFetchedObject);
								leafObject.put(paths[i], dataArray);
							}
						}
						else
						{
							log.error(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " Specified node " + paths[i] + " not found in " + mergePath);
							throw new NoMoreDataException(_pipeLineName_ + " : " + _processorName_ + " Specified node " + paths[i] + " not found in " + mergePath);
						}
					}
					else if(i == (paths.length - 1))
					{
//						log.info("inside not null..");
						dataArray = (JSONArray) parentObject.get(paths[i]);
						dataArray.add(lastFetchedObject);
						leafObject.put(paths[i], dataArray);
					}
					else
					{
						log.info("TempObject : " + tempObject);
						if(tempObject instanceof JSONObject)
							leafObject = (JSONObject) tempObject;
						else
						{
							tempArray = (JSONArray) tempObject;
							if(tempArray.size() == 1)
							{
								leafObject = (JSONObject) tempArray.get(0);
								log.info("Leaf Object : " + leafObject + " Path : " + paths[i]);
							}
							/*for(Object obj : tempArray)
							{
								obj.
							}*/
						}
					}
				}
			}
			else
			{
				dataArray = (JSONArray) parentObject.get(mergePath.toString());
				if(dataArray == null)
				{
					dataArray = new JSONArray();
					parentObject.put(mergePath.toString(), dataArray);
				}
				dataArray.add(lastFetchedObject);
			}
			
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " Parent Object : " + parentObject);
		}
		catch (NoMoreDataException exception) 
		{
			log.error(exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			leafObject = null;
			dataArray = null;
			tempObject = null;
			paths = null;
			
			log.info("Exit mergeObject..");
		}
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject getParentObject(JSONArray jsonArray, JSONObject mapKeyObject)
	{
		JSONObject parentObject = null;
		JSONObject tempObject = null;
		Set<String> keys = null;
		
		try
		{
			log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : mapKeyObject : " + mapKeyObject);
			keys = mapKeyObject.keySet();
			outer : for(int i = 0; i < jsonArray.size(); i++)
			{
				tempObject = (JSONObject) jsonArray.get(i);
				for(String key : keys)
				{
					if(tempObject.get(key) != null && tempObject.get(key).toString().equalsIgnoreCase(mapKeyObject.get(key).toString()))
					{
						parentObject = (JSONObject) jsonArray.remove(i);;
						break outer;
					}
				}
			}
			log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : parentObject : " + parentObject);
			return parentObject;
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			parentObject = null;
			tempObject = null;
			keys = null;
		}
		return parentObject;
	}
}