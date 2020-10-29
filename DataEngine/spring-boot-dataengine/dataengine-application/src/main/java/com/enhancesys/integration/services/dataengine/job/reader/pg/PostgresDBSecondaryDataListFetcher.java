package com.enhancesys.integration.services.dataengine.job.reader.pg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

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

public class PostgresDBSecondaryDataListFetcher extends DBProcessor
{
	@Autowired
	JdbcConnectionUtil jdbcConnectionUtil;
	
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet cursor = null;
	private List<String> lastFetchedObject = null;
	private JSONObject collectionConf = null;
	private long sleepTime;
	private long delayCount;
	private int collectionPosition = 0;
	private int columnCount = 0;
	private String name = null;
	private static Logger log = Logger.getLogger(PostgresDBSecondaryDataListFetcher.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		
		try
		{
			super.init(pipeLineName, processorName, _processorConfig_, jobConfiguration, jobParameters);
			name = primaryConfig.get("name").toString();
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) _collectionArr_.get(collectionPosition);

			connection = jdbcConnectionUtil.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(collectionConf.get(DataConstants.QUERY).toString());
			statement.setFetchSize(100);
			cursor = statement.executeQuery();
			columnCount = cursor.getMetaData().getColumnCount();
			log.info(name + " : column count : " + columnCount);
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : Cursor : " + cursor);
			
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
			log.error(exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info("Exit init..");
		}
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException
	{
//		log.info("Entry fetchData..");
//		log.error(name + " : Entry fetchData..");
		
		JSONArray jsonArray = null;
		List<String> parentObject = null;
		JSONObject mapKeyObject = null;
		JSONObject mapKeyConf = null;
		JSONObject parentKeyObj = null;
		JSONObject parentKey = null;
		JSONArray localKeyArray = null;
		JSONArray mapKeyArray = null;
		JSONArray tempKeyField = null;
		JSONArray tempJsonArray = null;
		Set<String> keySet = null;
		String lastFetchedObjectValue = null;
		String passedValue = null;
//		String mergeTo = null;
		String mergeType = null;
		String previousKeyType = null;
		String keyType = null;
		Object mapKey = null;
		Object localKey = null;
		boolean flag = false;
		
		try
		{
			log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : inside..");
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : inside..");
			jsonArray = (JSONArray) fetchedData.get(DataConstants.DATA_LIST);
//			log.info(name + " : jsonArray : " + jsonArray);
			if(jsonArray == null)
				return;
			tempJsonArray = new JSONArray();
			tempJsonArray.addAll(jsonArray);
//			mergeTo = (collectionConf.get(DataConstants.MERGE_TO) != null && !collectionConf.get(DataConstants.MERGE_TO).toString().trim().isEmpty()) ? collectionConf.get(DataConstants.MERGE_TO).toString() : null;
			mergeType = (collectionConf.get(DataConstants.MERGE_TYPE) != null && !collectionConf.get(DataConstants.MERGE_TYPE).toString().trim().isEmpty()) ? collectionConf.get(DataConstants.MERGE_TYPE).toString() : null;
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : JSON Arr Size.." + jsonArray.size());
			if(jsonArray.size() == 1)
			{
				if(DataConstants.INCLUDE_PARENT.equalsIgnoreCase(mergeType))
					parentObject = (List<String>) jsonArray.remove(0);
				else
					parentObject = (List<String>) jsonArray.get(0);
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
//			int keyFieldSize = keyField.size();
			
			/*if(keyField == null || keyField.size() == 0)
				return;*/
			
			outer : while(true)
			{
//				if("Secondary 2".equals(name))
//					log.info(name + " : Key Field : " + keyField);
//					log.info: lastFetchedObject : " + lastFetchedObject);
				if(lastFetchedObject != null)
				{
					if(parentObject == null)
						parentObject = (List<String>) jsonArray.remove(0);

					lastFetchedObjectValue = "";
					passedValue = "";
					localKeyArray = (JSONArray) localKey;
					mapKeyArray = (JSONArray) mapKey;
					for(int i = 0; i < localKeyArray.size(); i ++)
					{
						if(passedValue.trim().isEmpty())
							passedValue = parentObject.get(Integer.parseInt(localKeyArray.get(i).toString())); 
						else
							passedValue = passedValue + parentObject.get(Integer.parseInt(localKeyArray.get(i).toString()));
						
						if(lastFetchedObjectValue.trim().isEmpty())
							lastFetchedObjectValue = lastFetchedObject.get(Integer.parseInt(mapKeyArray.get(i).toString()));
						else
							lastFetchedObjectValue = lastFetchedObjectValue + lastFetchedObject.get(Integer.parseInt(mapKeyArray.get(i).toString()));
					}
					
					log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : compareValue : " + passedValue + " : currObjectKeyVal : " + lastFetchedObjectValue);
					
					if(lastFetchedObjectValue == null || lastFetchedObjectValue.trim().isEmpty())
						break outer;
					int compareResult = -1;

					if(DataConstants.LONG_TYPE.equalsIgnoreCase(previousKeyType))
					{
						long objValue = Long.parseLong(lastFetchedObjectValue);
						long matchValue = Long.parseLong(passedValue);
						if(objValue == matchValue)
							compareResult = 0;
						else if(objValue > matchValue)
							compareResult = 1;
					}
					else if(DataConstants.STRING_TYPE.equalsIgnoreCase(previousKeyType))
						compareResult = lastFetchedObjectValue.compareTo(passedValue);

					log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : CompareResult : " + compareResult);
					
					log.info(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : CompareResult : " + compareResult);

					if(compareResult > 0)
						break outer;
					else if(compareResult == 0)
					{
						if(DataConstants.INCLUDE_PARENT.equalsIgnoreCase(mergeType))
						{
							if(parentObject != null)
							{
//								log.info(pipeLineName + " : " + processorName + " : " + name + " : " + lastFetchedObject);
								parentObject.addAll(lastFetchedObject);
								jsonArray.add(parentObject);
								flag = true;
							}
						}
					}
				}
				
				try 
				{
					if(cursor.next())
					{
						lastFetchedObject = Utilities.convertToDataList(cursor, columnCount);
//						log.info(name + " : " + lastFetchedObject);
					}
					else
					{
//						log.info(name + "Breaking loop on No Data..");
						break;
					}
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
					log.error(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : Interrupted Exception : " + interruptedException.getMessage());
				}
				
				for(long i = 0; i < delayCount; i++)
				{
					for(long j = 0; j < delayCount; j++);
				}
			}
			
//			log.info(name + " : Falg : " + flag + " : skipOnNotExist : " + skipOnNotExist);
			if(DataConstants.TRUE.equalsIgnoreCase(_skipOnNotExist_))
			{
				if(!flag)
				{
					if(jsonArray.isEmpty())
					{
						for(int count = 0; count < columnCount; count ++)
						{
							parentObject.add("");
						}
						jsonArray.add(parentObject);
//						log.error("adding empty values.." + jsonArray.size());
					}
//					else
//						log.error("jsonArray is having more than one element.." + jsonArray.size());
				}
				else
				{
					if(!tempKeyField.isEmpty())
					{
						keyField.clear();
						keyField.addAll(tempKeyField);
					}
					log.error(_pipeLineName_ + " : " + _processorName_ + " : " + name +  " : " + keyField + " : Data Arr : " + jsonArray.size());
					if(DataConstants.TRUE.equalsIgnoreCase(_secondaryMatch_))
						fetchedData.put(DataConstants.SECONDARY_MATCH, DataConstants.TRUE);
				}
			}
			else 
			{
				if(!flag)
				{
					if(tempJsonArray.size() == 1)
					{
						parentObject = (List<String>) tempJsonArray.remove(0);
						for(int count = 0; count < columnCount; count ++)
						{
							parentObject.add("");
						}
						tempJsonArray.add(parentObject);
//						log.error("adding empty values.." + tempJsonArray.size());
					}
//					else
//						log.error("jsonArray is having more than one element.." + tempJsonArray.size());

					fetchedData.put(DataConstants.DATA_LIST, tempJsonArray);
//					log.info(pipeLineName + " : " + processorName + " : " + name +  " : " + keyField + " : Data Arr : " + tempJsonArray.size());
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
		catch(Exception exception)
		{
			log.error(exception.getMessage(), exception);
			throw new NoMoreDataException(exception.getMessage(), exception);
		}
		finally
		{
			jsonArray = null;
			parentObject = null;
			mapKeyObject = null;
			localKeyArray = null;
			mapKeyArray = null;
			tempKeyField = null;
			keySet = null;
			lastFetchedObjectValue = null;
			passedValue = null;
//			mergeTo = null;
			mergeType = null;
			previousKeyType = null;
			mapKey = null;
			localKey = null;
			tempJsonArray = null;
			
//			log.info("Exit fetchData..");
//			log.error(name + " : Exit fetchData..");
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
	
	/*@SuppressWarnings("unchecked")
	private void mergeObject(JSONObject parentObject, DBObject lastFetchedObject, String mergePath) throws NoMoreDataException
	{
//		log.info("Entry mergeObject..");
		
		JSONObject leafObject = null;
		JSONArray dataArray = null;
		JSONArray tempArray = null;
		Object tempObject = null;
		String[] paths = null;
		
		try
		{
			leafObject = parentObject;
			log.info(pipeLineName + " : " + processorName + " : " + name +  " Path : " + mergePath);
//			System.out.println(pipeLineName + " : " + processorName + " : " + name +  " Path : " + mergePath);
			if(mergePath.contains("."))
			{
				paths = mergePath.split("\\.");
//				log.info(pipeLineName + " : " + processorName + " : " + name +  " Paths : " + paths.length);
//				System.out.println(pipeLineName + " : " + processorName + " : " + name +  " Paths : " + paths.length);
				for(int i = 0; i < paths.length; i++)
				{
					tempObject = leafObject.get(paths[i]); 
					if(tempObject == null)
					{
						if(i == (paths.length - 1))
						{
//							System.out.println("inside null..");
							if(dataArray == null)
							{
								dataArray = new JSONArray();
								dataArray.add(lastFetchedObject);
								leafObject.put(paths[i], dataArray);
							}
						}
						else
						{
							log.error(pipeLineName + " : " + processorName + " : " + name +  " Specified node " + paths[i] + " not found in " + mergePath);
							throw new NoMoreDataException(pipeLineName + " : " + processorName + " Specified node " + paths[i] + " not found in " + mergePath);
						}
					}
					else if(i == (paths.length - 1))
					{
//						System.out.println("inside not null..");
						dataArray = (JSONArray) parentObject.get(paths[i]);
						dataArray.add(lastFetchedObject);
						leafObject.put(paths[i], dataArray);
					}
					else
					{
//						log.info("TempObject : " + tempObject);
//						System.out.println("TempObject : " + tempObject);
						if(tempObject instanceof JSONObject)
							leafObject = (JSONObject) tempObject;
						else
						{
							tempArray = (JSONArray) tempObject;
							if(tempArray.size() == 1)
							{
								leafObject = (JSONObject) tempArray.get(0);
//								log.info("Leaf Object : " + leafObject + " Path : " + paths[i]);
//								System.out.println("Leaf Object : " + leafObject + " Path : " + paths[i]);
							}
							/*for(Object obj : tempArray)
							{
								obj.
							}*/
						/*}
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
			
//			System.out.println(pipeLineName + " : " + processorName + " : " + name +  " Parent Object : " + parentObject);
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
			
//			log.info("Exit mergeObject..");
		}
	}*/
	
	@SuppressWarnings({ "unchecked", "unused" })
	private List<String> getParentObject(JSONArray jsonArray, JSONObject mapKeyObject)
	{
		List<String> parentObject = null;
		List<String> tempObject = null;
		Set<String> keys = null;
		
		try
		{
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : mapKeyObject : " + mapKeyObject);
//			System.out.println(pipeLineName + " : " + processorName + " : " + name +  " : mapKeyObject : " + mapKeyObject);
			keys = mapKeyObject.keySet();
			outer : for(int i = 0; i < jsonArray.size(); i++)
			{
				tempObject = (List<String>) jsonArray.get(i);
				for(String key : keys)
				{
					if(tempObject.get(Integer.parseInt(key)) != null && tempObject.get(Integer.parseInt(key)).toString().equalsIgnoreCase(mapKeyObject.get(key).toString()))
					{
						parentObject = (List<String>) jsonArray.remove(i);;
						break outer;
					}
				}
			}
//			System.out.println(pipeLineName + " : " + processorName + " : " + name +  " : parentObject : " + parentObject);
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : parentObject : " + parentObject);
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