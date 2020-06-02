package com.enhancesys.jobengine.job.services.processor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.mongo.util.MongoDataUtil;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;
import com.enhancesys.jobengine.job.services.util.Util;

public class CSVDataListWriter extends JobProcessor
{
	@Autowired
	MongoDataUtil mongoDataUtil;
	
	@Autowired
	Util util;
	
	private JSONObject outputConf = null;
	private OutputStream outputStream = null;
	private Object requestUpdateConf = null;
	private DecimalFormat fileSeqFormat = new DecimalFormat("000");
	private File file = null;
	private List<File> fileList = null;
	private String fileName = null;
	private String zipFileName = null;
	private String delimiter = null;
	private String headers = null;
	private String preHeaders = null;
	private Long recordLimit = null;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private long writeBatchSize;
	private long recordCount = 0l;
	private long dataCount = 0l;
	private int fileSequence = 1;
	
	private static Logger log = Logger.getLogger(CSVDataListWriter.class);
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfigurationReader, jobParameters);
			if(processorConfig.get("job_count") != null && !processorConfig.get("job_count").toString().trim().isEmpty())
				this.jobCount = (Long) processorConfig.get("job_count");
			else
				this.jobCount = -1;
			
			if(processorConfig.get("sleep_time") != null && !processorConfig.get("sleep_time").toString().trim().isEmpty())
				this.sleepTime = (Long) processorConfig.get("sleep_time");
			else
				this.sleepTime = 0;
			
			if(processorConfig.get("delay_count") != null && !processorConfig.get("delay_count").toString().trim().isEmpty())
				this.delayCount = (Long) processorConfig.get("delay_count");
			else
				this.delayCount = 0;
			
			if(processorConfig.get("write_batch_size") != null && !processorConfig.get("write_batch_size").toString().trim().isEmpty())
				this.writeBatchSize = (Long) processorConfig.get("write_batch_size");
			else
				this.writeBatchSize = 10;
			
			outputConf = (JSONObject) jobConfigData.get(DataConstants.OUTPUT_CONF); 
			if(outputConf.get(DataConstants.FILE_RECORD_LIMIT) != null && !outputConf.get(DataConstants.FILE_RECORD_LIMIT).toString().trim().isEmpty())
				recordLimit = Long.parseLong(outputConf.get(DataConstants.FILE_RECORD_LIMIT).toString().trim()); 
			requestUpdateConf = jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF);
			
			createFile();
		}
		catch (Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
			throw new GenericProcessorException("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			log.info("Exit init..");
		}
	}
	
	private void createFile()
	{
		log.info("Entry createFile..");
		
		DateFormat fileFormat = null;
		String fileNamePattern = null;

		try
		{
			fileName = outputConf.get(DataConstants.FILE_NAME).toString();
			if(outputConf.get(DataConstants.FILE_NAME_PATTERN) != null && !outputConf.get(DataConstants.FILE_NAME_PATTERN).toString().trim().isEmpty())
			{
				if(fileFormat == null)
				{
					fileFormat = new SimpleDateFormat(outputConf.get(DataConstants.FILE_NAME_PATTERN).toString());
					fileNamePattern = fileFormat.format(new Date());
				}
				fileName = fileName + "_" + fileNamePattern;
			}
			
			if(outputConf.get(DataConstants.ZIP_EXTENSION) != null && !outputConf.get(DataConstants.ZIP_EXTENSION).toString().trim().isEmpty())
				zipFileName = outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + outputConf.get(DataConstants.ZIP_EXTENSION).toString();
			
			if(recordLimit != null && recordLimit.longValue() > 0l)
				file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + outputConf.get(DataConstants.EXTENSION).toString());
			else
				file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + outputConf.get(DataConstants.EXTENSION).toString());

			fileList = new ArrayList<File>();
			fileList.add(file);
			
//			log.info("FileName : " + file.getPath());
			delimiter = outputConf.get(DataConstants.FIELD_DELIMITER).toString();
			preHeaders = outputConf.get(DataConstants.PRE_HEADER) != null ? outputConf.get(DataConstants.PRE_HEADER).toString() : null;
			headers = outputConf.get(DataConstants.FILE_HEADERS).toString();
			if(jobParameters.get(DataConstants.LANGUAGE) != null && !jobParameters.get(DataConstants.LANGUAGE).toString().trim().isEmpty() 
					&& outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS) != null 
					&& !outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().trim().isEmpty())
			{
				if(DataConstants.LANG_UKRAINIAN.equalsIgnoreCase(jobParameters.get(DataConstants.LANGUAGE).toString()))
					headers = outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString();
				else if(DataConstants.LANG_INDONESIAN.equalsIgnoreCase(jobParameters.get(DataConstants.LANGUAGE).toString()))
					headers = outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString();
			}
			
			outputStream = new FileOutputStream(file);
			if(preHeaders != null && !preHeaders.trim().isEmpty())
				outputStream.write((preHeaders + "\n\n").getBytes());
			outputStream.write((headers + "\n").getBytes());
		}
		catch(Exception exception)
		{
			log.error(exception.getMessage(), exception);
		}
		finally
		{
			fileFormat = null;
			fileNamePattern = null;
			
			log.info("Exit createFile..");
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
//		log.error("Entry process.. ");
//		log.info(pipeLineName + " : Entry process.." + processorName + " :jobCount: " + jobCount);
		JSONObject payloadObject = null;
		JSONObject fieldObject = null;
		JSONObject dataObject = null;
		JSONArray jsonArray = null;
		JSONArray excludeFields = null;
		JSONArray mandatoryFields = null;
		JSONArray fieldConf = null; 
		DateFormat format = null;
		DateFormat fromFormat = null;
		Object value = null;
		String dataLine = "";
		List<Object> values = null; 
		List<String> dataListObject = null;
		
		try
		{
			if(outputConf == null)
			{
				log.error(pipeLineName + " : " + processorName + " : Output configuration not exist..");
				throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Output configuration not exist..");
			}
			if(!DataConstants.FILE.equalsIgnoreCase(outputConf.get(DataConstants.TYPE).toString()))
			{
				log.error(pipeLineName + " : " + processorName + " : Output type should be file..");
				throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Output type should be file..");
			}
			
			excludeFields = (JSONArray) outputConf.get(DataConstants.EXCLUDE_FIELDS);
			mandatoryFields = (JSONArray) outputConf.get(DataConstants.MANDATORY_FIELDS);
			fieldConf = (JSONArray) outputConf.get(DataConstants.FIELD_CONF);
			
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					int count = 0;
					int batchRecordCount = 0;
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					recordCount = recordCount + jsonArray.size();
					outer : for(Object object : jsonArray)
					{
						if(object instanceof List)
						{
							dataListObject = (ArrayList<String>) object;
							if(mandatoryFields != null)
							{
								for(Object field : mandatoryFields)
								{
									if(dataListObject.get(Integer.parseInt(field.toString())) == null || dataListObject.get(Integer.parseInt(field.toString())).toString().trim().isEmpty())
										continue outer;
								}
							}
							if(excludeFields != null)
							{
								for(Object field : excludeFields)
								{
									dataListObject.remove(Integer.parseInt(field.toString()));
								}
							}
							
							if(recordLimit != null && recordLimit.longValue() > 0l && dataCount == recordLimit.longValue())
							{
								if(outputStream != null)
									outputStream.close();
								
								fileSequence += 1;
								file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + outputConf.get(DataConstants.EXTENSION).toString());
								fileList.add(file);
								
								outputStream = new FileOutputStream(file);
								if(preHeaders != null && !preHeaders.trim().isEmpty())
									outputStream.write((preHeaders + "\n\n").getBytes());
								outputStream.write((headers + "\n").getBytes());
								dataLine = "";
								count = 0;
								dataCount = 0l;
							}
							dataCount += 1;
							
							for(Object obj : fieldConf)
							{
								fieldObject = (JSONObject) obj;
//								log.info("FieldObject : " + fieldObject);
								if(fieldObject.get(DataConstants.PATH) instanceof JSONArray)
									value = "";
								else
									value = dataListObject.get(Integer.parseInt(fieldObject.get(DataConstants.PATH).toString()));
								
								if(fieldObject.get(DataConstants.OPERATION) != null 
										&& DataConstants.CONCAT.equalsIgnoreCase(fieldObject.get(DataConstants.OPERATION).toString().trim()))
								{
									for(Object arrField : (JSONArray) fieldObject.get(DataConstants.PATH))
									{
										value = value + dataListObject.get(Integer.parseInt(arrField.toString())) + " ";
									}
									dataLine = dataLine + value.toString().trim() + delimiter;
								}
								else
									dataLine = dataLine + value.toString() + delimiter;
							}
							count ++;
							batchRecordCount ++;
							dataLine = dataLine.substring(0, dataLine.length() - 1) + "\n";
							
//							log.info("dataLine : " + dataLine);
							if(count == writeBatchSize)
							{
								outputStream.write(dataLine.getBytes());
								dataLine = "";
								count = 0;
							}
						}
						else
						{
							dataObject = (JSONObject) object;
//							log.info("dataObject : " + dataObject);
							if(mandatoryFields != null)
							{
								for(Object field : mandatoryFields)
								{
									if(dataObject.get(field.toString()) == null || dataObject.get(field.toString()).toString().trim().isEmpty())
										continue outer;
								}
							}
							if(excludeFields != null)
							{
								for(Object field : excludeFields)
								{
									dataObject.remove(field);
								}
							}
							
							if(recordLimit != null && recordLimit.longValue() > 0l && dataCount == recordLimit.longValue())
							{
								if(outputStream != null)
									outputStream.close();
								
								fileSequence += 1;
								file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + outputConf.get(DataConstants.EXTENSION).toString());
								fileList.add(file);
								
								outputStream = new FileOutputStream(file);
								if(preHeaders != null && !preHeaders.trim().isEmpty())
									outputStream.write((preHeaders + "\n\n").getBytes());
								outputStream.write((headers + "\n").getBytes());
								dataLine = "";
								count = 0;
								dataCount = 0l;
							}
							dataCount += 1;
							
							for(Object obj : fieldConf)
							{
								fieldObject = (JSONObject) obj;
//								log.info("FieldObject : " + fieldObject);
								if(fieldObject.get(DataConstants.PATH) instanceof JSONArray)
									value = "";
								else 
								{
									value = "";
									values = Util.getFieldValue(fieldObject.get(DataConstants.PATH).toString(), dataObject);
									if(values != null && !values.isEmpty()) 
										value = values.iterator().next(); 
//									log.info("values : " + values + " : " + value);
								}
								if(DataConstants.STRING_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()) && value instanceof String)
								{
									if(fieldObject.get(DataConstants.OPERATION) != null 
											&& DataConstants.CONCAT.equalsIgnoreCase(fieldObject.get(DataConstants.OPERATION).toString().trim()))
									{
										for(Object arrField : (JSONArray) fieldObject.get(DataConstants.PATH))
										{
											values = Util.getFieldValue(arrField.toString(), dataObject);
											if(values != null && !values.isEmpty())
												value = value + values.iterator().next().toString() + " ";
										}
										dataLine = dataLine + value.toString().trim() + delimiter;
									}
									else
										dataLine = dataLine + value.toString() + delimiter;
								}
								else if(!(value instanceof JSONObject) && !(value instanceof JSONArray))
								{
									if(DataConstants.DATE_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
									{
										if(dataObject.get(fieldObject.get(DataConstants.PATH).toString()) instanceof Date)
										{
											if(fieldObject.get(DataConstants.DATE_FORMAT) == null || fieldObject.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
											{
												log.error(pipeLineName + " : " + processorName + " : Date Format should not be empty..");
												throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Date Format should not be empty..");
											}
											if(format == null)
												format = new SimpleDateFormat(fieldObject.get(DataConstants.DATE_FORMAT).toString());
											dataLine = dataLine + format.format(dataObject.get(fieldObject.get(DataConstants.PATH).toString())) + delimiter;
										}
										else
										{
											if(fieldObject.get(DataConstants.FROM_DATE_FORMAT) != null && !fieldObject.get(DataConstants.FROM_DATE_FORMAT).toString().trim().isEmpty())
											{
												if(fromFormat == null)
													fromFormat = new SimpleDateFormat(fieldObject.get(DataConstants.FROM_DATE_FORMAT).toString());
											}
											if(fromFormat != null)
											{
												if(fieldObject.get(DataConstants.TO_DATE_FORMAT) == null || fieldObject.get(DataConstants.TO_DATE_FORMAT).toString().trim().isEmpty())
												{
													log.error(pipeLineName + " : " + processorName + " : To Date Format should not be empty..");
													throw new GenericProcessorException(pipeLineName + " : " + processorName + " : To Date Format should not be empty..");
												}
												if(format == null)
													format = new SimpleDateFormat(fieldObject.get(DataConstants.TO_DATE_FORMAT).toString());
												dataLine = dataLine + format.format(fromFormat.parse(value.toString())) + delimiter;
											}
											else
												dataLine = dataLine + value.toString() + delimiter;
										}
									}
									else
									{
										if(fieldObject.get(DataConstants.OPERATION) != null && !fieldObject.get(DataConstants.OPERATION).toString().trim().isEmpty() 
												&& DataConstants.CONCAT.equalsIgnoreCase(fieldObject.get(DataConstants.OPERATION).toString()))
										{
											for(Object arrField : (JSONArray) fieldObject.get(DataConstants.PATH))
											{
												values = Util.getFieldValue(arrField.toString(), dataObject);
												if(values != null && !values.isEmpty())
													value = value + values.iterator().next().toString() + " ";
											}
											dataLine = dataLine + value.toString().trim() + delimiter;
										}
										else
											dataLine = dataLine + value.toString() + delimiter;
									}
								}
							}
							count ++;
							batchRecordCount ++;
							dataLine = dataLine.substring(0, dataLine.length() - 1) + "\n";
							
//							log.info("dataLine : " + dataLine);
							if(count == writeBatchSize)
							{
								outputStream.write(dataLine.getBytes());
								dataLine = "";
								count = 0;
							}
						}
					}
					
					if(!dataLine.trim().isEmpty())
						outputStream.write(dataLine.getBytes());

					log.info(pipeLineName + " : " + processorName + " : Record Count : " + batchRecordCount);
				}
				else
					jobObject.put("status", "Completed");
			}
			
			log.info(pipeLineName + " : " + processorName + " : DataObject : " + jobObject);
			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++);
			}
			try
			{
				Thread.sleep(sleepTime);
			} 
			catch (InterruptedException e) 
			{
				log.error("Exception Occured : " + e.getMessage());
				throw new GenericProcessorException("Exception Occured : " + e.getMessage());
			}
		}
		catch(Exception exception)
		{
			jobObject.put("status", "Failure");
			log.error(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			payloadObject = null;
			fieldObject = null;
			dataObject = null;
			jsonArray = null;
			excludeFields = null;
			mandatoryFields = null;
			fieldConf = null; 
			format = null;
			fromFormat = null;
			value = null;
			dataLine = "";
			values = null; 
			dataListObject = null;
//			log.info(pipeLineName + " : Exit process.." + processorName);
//			log.error("Exit process.. ");
		}
		return false;
	}
	
	public void stop()
	{
		JSONObject storeFileConf = null;
		File zipFile = null;
		
		try
		{
			if(outputStream != null)
				outputStream.close();
			
			try
			{
				log.info("recordCount : " + recordCount);
				if(recordCount == 0l)
				{
					mongoDataUtil.updateRequest(jobParameters.get("request-id"), requestUpdateConf, null, DataConstants.STATUS_NO_DATA, outputConf.get(DataConstants.EXTENSION).toString(), false);
					file.delete();
				}
				else 
				{
					if(zipFileName != null)
					{
						zipFile = new File(zipFileName);
						zipFile = util.zipMultipleFiles(fileList, zipFile, true);
					}
					
					if(outputConf.get(DataConstants.STORE_FILE_CONF) != null && !outputConf.get(DataConstants.STORE_FILE_CONF).toString().trim().isEmpty())
					{
						storeFileConf = (JSONObject) outputConf.get(DataConstants.STORE_FILE_CONF);
						if(storeFileConf != null)
						{
							if(zipFileName != null)
							{
								mongoDataUtil.storeFile(storeFileConf.get(DataConstants.CONNECTION_ID).toString(), storeFileConf.get(DataConstants.SCHEMA_NAME).toString(), outputConf.get(DataConstants.ZIP_EXTENSION).toString(), zipFile.getName(), zipFile, jobParameters.get("request-id"), requestUpdateConf, DataConstants.STATUS_COMPLETED);
								if(zipFile!=null)
									zipFile.delete();
							}
							else
							{
								mongoDataUtil.storeFile(storeFileConf.get(DataConstants.CONNECTION_ID).toString(), storeFileConf.get(DataConstants.SCHEMA_NAME).toString(), outputConf.get(DataConstants.EXTENSION).toString(), fileName, file, jobParameters.get("request-id"), requestUpdateConf, DataConstants.STATUS_COMPLETED);
								file.delete();
							}
						}
					}
				}
			}
			catch (GenericProcessorException e) 
			{
				log.error("Unable to store the file.. : " + file.getPath() + " : " + e.getMessage(), e);
			}
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(outputStream != null)
					outputStream.close();
			}
			catch(Exception e)
			{
				log.error("Exception : " + e.getMessage(), e);
			}
			
			storeFileConf = null;
			zipFile = null;
					
			outputConf = null;
			outputStream = null;
			requestUpdateConf = null;
			fileSeqFormat = null;
			file = null;
			fileList = null;
			fileName = null;
			zipFileName = null;
			delimiter = null;
			headers = null;
			preHeaders = null;
			recordLimit = null;
		}
	}
}