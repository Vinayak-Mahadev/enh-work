package com.enhancesys.jobengine.services.mongo.processor;

import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.services.mongo.util.MongoDataUtil;
import com.enhancesys.jobengine.services.processor.DBProcessor;
import com.enhancesys.jobengine.services.util.DataConstants;
import com.enhancesys.jobengine.services.util.GenericProcessorException;
import com.enhancesys.jobengine.services.util.NoMoreDataException;
import com.enhancesys.jobengine.services.util.Util;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDBSecondaryDataFetcher extends DBProcessor
{
	@Autowired
	MongoDataUtil mongoDataUtil;
	
	private DBCursor cursor = null;
	private DBObject lastFetchedObject = null;
	private JSONObject collectionConf = null;
	private JSONObject aliasObject = null;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private int collectionPosition = 0;
	private String name = null;
	private static Logger log = Logger.getLogger(MongoDBSecondaryDataFetcher.class);
	
	public void init(String pipeLineName, String processorName, JSONObject primaryConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		super.init(pipeLineName, processorName, processorConfig, jobConfigurationReader, jobParameters);
		
		try
		{
			name = primaryConfig.get("name").toString();
			collectionPosition = Integer.parseInt(primaryConfig.get("collection_position").toString());
			collectionConf = (JSONObject) collectionArr.get(collectionPosition);
			if(collectionConf.get(DataConstants.ALIAS) != null && !collectionConf.get(DataConstants.ALIAS).toString().isEmpty())
				aliasObject = (JSONObject) collectionConf.get(DataConstants.ALIAS);

			cursor = mongoDataUtil.getCursor(collectionConf, true);
//			log.info(pipeLineName + " : " + processorName + " : " + name +  " : Cursor Size : " + cursor.size());
			
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
		boolean flag = false;
		
		try
		{
			log.info(pipeLineName + " : " + processorName + " : " + name +  " : inside..");
			parser = new JSONParser();
			jsonArray = (JSONArray) fetchedData.get(DataConstants.DATA_LIST);
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

			mapKeyConf = (JSONObject) ((JSONObject) collectionArr.get(collectionPosition)).get(DataConstants.MAP_KEY);
			parentKey = (JSONObject) ((JSONObject) collectionArr.get(collectionPosition)).get(DataConstants.PARENT_KEY);
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
//				log.info(name + " : Key Field : " + keyField);
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
//							log.info(name + " : " + mKey + " : " + lastFetchedObject);
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
									if(Util.getFieldValue(mKey, lastFetchedObject) == null)
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
							}
							
							if(currObjectKeyVal.trim().isEmpty())
							{
								try 
								{
									currObjectKeyVal = mKey.contains(".") ? Util.getFieldValue(mKey, lastFetchedObject).toString() : lastFetchedObject.get(mKey).toString().trim();
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

						log.info(pipeLineName + " : " + processorName + " : " + name +  " : compareValue : " + compareValue + " : currObjectKeyVal : " + currObjectKeyVal);
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

						log.info(pipeLineName + " : " + processorName + " : " + name +  " : CompareResult : " + compareResult);

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
							
//							log.info(name + " : Going to merge : " + lastFetchedObject);
							
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
														Object value = Util.getFieldValue(localKeyArray.get(j).toString(), lastFetchedObject);
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
												Object value = Util.getFieldValue(localKey.toString(), lastFetchedObject);
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
										log.info(pipeLineName + " : " + processorName + " : " + name + " : " + lastFetchedObject);
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
				
				if(cursor.hasNext())
				{
					lastFetchedObject = (BasicDBObject) cursor.next();
//					log.info(name + " : fetching object from cursor : ");
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
					log.info("Breaking loop on No Data..");
					break;
				}
				
				try
				{
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException interruptedException)
				{
					log.error(pipeLineName + " : " + processorName + " : " + name +  " : Interrupted Exception : " + interruptedException.getMessage());
				}
				
				for(long i = 0; i < delayCount; i++)
				{
					for(long j = 0; j < delayCount; j++);
				}
			}
			
			if(DataConstants.TRUE.equalsIgnoreCase(skipOnNotExist))
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
					log.info(pipeLineName + " : " + processorName + " : " + name +  " : " + keyField + " : Data Arr : " + jsonArray.size());
					if(DataConstants.TRUE.equalsIgnoreCase(secondaryMatch))
						fetchedData.put(DataConstants.SECONDARY_MATCH, DataConstants.TRUE);
				}
			}
			else 
			{
				if(!flag)
				{
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
					log.info(pipeLineName + " : " + processorName + " : " + name +  " : " + keyField + " : Data Arr : " + jsonArray.size());
					if(DataConstants.TRUE.equalsIgnoreCase(secondaryMatch))
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
			tempJsonArray = null;
			
			log.info("Exit fetchData..");
		}
	}
	
	public void stop()
	{
		if(cursor != null)
			cursor.close();
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
			log.info(pipeLineName + " : " + processorName + " : " + name +  " Path : " + mergePath);
			if(mergePath.contains("."))
			{
				paths = mergePath.split("\\.");
				log.info(pipeLineName + " : " + processorName + " : " + name +  " Paths : " + paths.length);
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
							log.error(pipeLineName + " : " + processorName + " : " + name +  " Specified node " + paths[i] + " not found in " + mergePath);
							throw new NoMoreDataException(pipeLineName + " : " + processorName + " Specified node " + paths[i] + " not found in " + mergePath);
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
			log.info(pipeLineName + " : " + processorName + " : " + name +  " : mapKeyObject : " + mapKeyObject);
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
			log.info(pipeLineName + " : " + processorName + " : " + name +  " : parentObject : " + parentObject);
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