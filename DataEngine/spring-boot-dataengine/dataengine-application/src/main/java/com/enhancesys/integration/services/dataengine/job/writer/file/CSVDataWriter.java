package com.enhancesys.integration.services.dataengine.job.writer.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobProcessor;
import com.enhancesys.integration.services.dataengine.job.components.DataWriter;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.condition.ExpressionProcessor;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;
import com.enhancesys.integration.services.dataengine.util.mathematical.MathematicalProcessor;
import com.enhancesys.integration.services.dataengine.util.mongo.MongoDataUtil;

public class CSVDataWriter extends JobProcessor implements DataWriter
{
	@Autowired
	MongoDataUtil mongoDataUtil;

	@Autowired
	Utilities util;

	@Autowired
	ExpressionProcessor expressionProcessor;

	@Autowired
	MathematicalProcessor mathematicalProcessor;

	private OutputStream outputStream = null;
	private DecimalFormat fileSeqFormat = new DecimalFormat("000");
	private File file = null;
	private List<File> fileList = null;
	private String zipFileName = null;
	private String fileName = null;
	private String preHeaders = null;
	private String headers = null;
	private String delimiter = null;
	private long recordCount = 0l; 
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private long writeBatchSize;
	private long dataCount = 0l;
	private int fileSequence = 1;
	private static Logger LOGGER = Logger.getLogger(CSVDataWriter.class);

	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		LOGGER.info("Entry init..");
		try
		{
			super.init(pipeLineName, processorName, processorConfig, jobConfiguration, jobParameters);
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

			createFile();
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

	private void createFile()
	{
		LOGGER.info("Entry createFile..");

		DateFormat fileFormat = null;
		String fileNamePattern = null;

		try
		{
			fileName = _outputConf_.get(DataConstants.FILE_NAME).toString();
			if(_outputConf_.get(DataConstants.FILE_NAME_PATTERN) != null && !_outputConf_.get(DataConstants.FILE_NAME_PATTERN).toString().trim().isEmpty())
			{
				if(fileFormat == null)
				{
					fileFormat = new SimpleDateFormat(_outputConf_.get(DataConstants.FILE_NAME_PATTERN).toString());
					fileNamePattern = fileFormat.format(new Date());
				}
				fileName = fileName + "_" + fileNamePattern;
			}

			if(_outputConf_.get(DataConstants.ZIP_EXTENSION) != null && !_outputConf_.get(DataConstants.ZIP_EXTENSION).toString().trim().isEmpty())
				zipFileName = _outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + _outputConf_.get(DataConstants.ZIP_EXTENSION).toString();

			if(_recordLimit_ != null && _recordLimit_.longValue() > 0l)
				file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
			else
				file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + _outputConf_.get(DataConstants.EXTENSION).toString());

			fileList = new ArrayList<File>();
			fileList.add(file);

			delimiter = _outputConf_.get(DataConstants.FIELD_DELIMITER).toString();
			preHeaders = _outputConf_.get(DataConstants.PRE_HEADER) != null ? _outputConf_.get(DataConstants.PRE_HEADER).toString() : null;
			headers = _outputConf_.get(DataConstants.FILE_HEADERS).toString();
			if(_jobParameters_.get(DataConstants.LANGUAGE) != null && !_jobParameters_.get(DataConstants.LANGUAGE).toString().trim().isEmpty() 
					&& _outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS) != null 
					&& !_outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().trim().isEmpty())
			{
				if(DataConstants.LANG_UKRAINIAN.equalsIgnoreCase(_jobParameters_.get(DataConstants.LANGUAGE).toString()))
					headers = _outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString();
				else if(DataConstants.LANG_INDONESIAN.equalsIgnoreCase(_jobParameters_.get(DataConstants.LANGUAGE).toString()))
					headers = _outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString();
			}

			outputStream = new FileOutputStream(file);
			if(preHeaders != null && !preHeaders.trim().isEmpty())
				outputStream.write((preHeaders + "\n\n").getBytes());
			outputStream.write((headers + "\n").getBytes());

		}
		catch(Exception exception)
		{
			try 
			{
				if(_requestId_ != null && _requestUpdateConf_ != null)
					mongoDataUtil.updateStatus(_requestId_, _requestUpdateConf_, DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				LOGGER.error("Unhandled Exception : " + excep.getMessage(), excep);
			}
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			fileFormat = null;
			fileNamePattern = null;

			LOGGER.info("Exit createFile.." + file.getAbsolutePath());
		}
	}

	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		//		log.debug("Entry process.. " + jobObject);
		LOGGER.debug(_pipeLineName_ + " : Entry process.." + _processorName_ + " : jobCount: " + jobCount + " : jobObject: " + jobObject);
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
		File file = null;
		String dataLine = "";
		List<Object> values = null; 
		Map<String,Object> dataToEvaluate = null;
		String temp = null;
		JSONObject jsonData = null;
		JSONArray jsonArr = null;
		try
		{
			if(_outputConf_ == null)
			{
				LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : Output configuration not exist..");
				throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Output configuration not exist..");
			}
			if(!DataConstants.FILE.equalsIgnoreCase(_outputConf_.get(DataConstants.TYPE).toString()))
			{
				LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : Output type should be file..");
				throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Output type should be file..");
			}

			excludeFields = (JSONArray) _outputConf_.get(DataConstants.EXCLUDE_FIELDS);
			mandatoryFields = (JSONArray) _outputConf_.get(DataConstants.MANDATORY_FIELDS);
			fieldConf = (JSONArray) _outputConf_.get(DataConstants.FIELD_CONF);
			delimiter = _outputConf_.get(DataConstants.FIELD_DELIMITER).toString();

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
						dataObject = ((JSONObject) object);
						//						log.info("dataObject : " + dataObject);
						if(mandatoryFields != null)
						{
							for(Object field : mandatoryFields)
							{
								if(dataObject.get(field.toString()) == null || dataObject.get(field.toString()).toString().trim().isEmpty())
								{
									--recordCount;
									continue outer;
								}	

							}
						}
						if(excludeFields != null)
						{
							for(Object field : excludeFields)
							{
								dataObject.remove(field);
							}
						}

						if(_recordLimit_ != null && _recordLimit_.longValue() > 0l && dataCount == _recordLimit_.longValue())
						{
							if(outputStream != null)
								outputStream.close();

							fileSequence += 1;
							file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
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
							//							log.info("FieldObject : " + fieldObject);
							if(fieldObject.get(DataConstants.PATH) instanceof JSONArray)
								value = "";
							else 
							{
								value = "";
								values = Utilities.getFieldValue(fieldObject.get(DataConstants.PATH).toString(), dataObject);
								if(values != null && !values.isEmpty()) 
									value = values.iterator().next(); 
								//								log.info("values : " + values + " : " + value);
							}
							if(DataConstants.STRING_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()) && value instanceof String)
							{
								if(fieldObject.get(DataConstants.OPERATION) != null 
										&& DataConstants.CONCAT.equalsIgnoreCase(fieldObject.get(DataConstants.OPERATION).toString().trim()))
								{
									for(Object arrField : (JSONArray) fieldObject.get(DataConstants.PATH))
									{
										values = Utilities.getFieldValue(arrField.toString(), dataObject);
										if(values != null && !values.isEmpty())
											value = value + values.iterator().next().toString() + " ";
									}
									dataLine = dataLine + value.toString().trim() + delimiter;
								}
								else
									dataLine = dataLine + value.toString() + delimiter;
							}
							else if(DataConstants.BOOLEAN_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								String tempVal = "";

								if(value != null && value.toString().trim().equalsIgnoreCase("true"))
									tempVal = fieldObject.get(DataConstants.IF).toString();
								else
									tempVal = fieldObject.get(DataConstants.ELSE).toString();
								dataLine = dataLine + tempVal + delimiter;
							}
							else if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								//								log.error("dataField : " + dataField);
								long tempVal = 0;
								if(value != null && !value.toString().trim().isEmpty() && !"NaN".equalsIgnoreCase(value.toString().trim()) && !"Infinity".equalsIgnoreCase(value.toString().trim()))
								{
									tempVal = (Long.parseLong(value.toString()));
								}
								dataLine = dataLine + tempVal + delimiter;
							}
							else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								double tempVal = 0.0;
								if(value != null && !value.toString().trim().isEmpty() && !"NaN".equalsIgnoreCase(value.toString().trim()) && !"Infinity".equalsIgnoreCase(value.toString().trim()))
								{
									tempVal = (Double.parseDouble(value.toString()));
									if(fieldObject.get(DataConstants.NUMBER_OF_DECIMALS) != null 
											&& !fieldObject.get(DataConstants.NUMBER_OF_DECIMALS).toString().trim().isEmpty()
											&& "1".equalsIgnoreCase(fieldObject.get(DataConstants.NUMBER_OF_DECIMALS).toString().trim()))
									{}
								}
								dataLine = dataLine + tempVal + delimiter;
							}

							else if(DataConstants.CONDITIONAL_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								String tempVal = "";
								if(fieldObject.get(DataConstants.EXPRESSION_CONF) != null && value != null && !value.toString().isEmpty())
								{
									try 
									{
										expressionProcessor.init(fieldObject.get(DataConstants.EXPRESSION_CONF).toString());
										dataToEvaluate = new HashMap<String, Object>();
										dataToEvaluate.put(fieldObject.get(DataConstants.PATH).toString(), value.toString().trim());

										if(fieldObject.get(DataConstants.COLLECTION_DATA_CONF) != null)
										{
											jsonArr = (JSONArray) fieldObject.get(DataConstants.COLLECTION_DATA_CONF);
											for (Object object2 : jsonArr)
											{
												if(dataObject.get(object2.toString()) != null && !dataObject.get(object2.toString()).toString().isEmpty())
													dataToEvaluate.put(object2.toString(), dataObject.get(object2.toString()));
											}
										}

										if(expressionProcessor.processExpression(dataToEvaluate))
											tempVal = fieldObject.get(DataConstants.IF).toString();
										else
											tempVal = fieldObject.get(DataConstants.ELSE).toString();
									} 
									catch (Exception e) 
									{
										tempVal = "";
									}
									dataLine = dataLine + tempVal + delimiter;
								}
								else
									dataLine = dataLine + tempVal + delimiter;
							}
							else if(DataConstants.MATHEMATICAL_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								Object tempVal = "";
								if(fieldObject.get(DataConstants.EXPRESSION_CONF) != null && value != null && !value.toString().isEmpty())
								{
									temp = value.toString().trim();
									jsonData = null;
									jsonArr = null;
									try 
									{
										if(fieldObject.get(DataConstants.MATH_CONF_DATA) != null)
											jsonData = (JSONObject) fieldObject.get(DataConstants.MATH_CONF_DATA);
										else
											jsonData = new JSONObject();

										if(fieldObject.get(DataConstants.COLLECTION_DATA_CONF) != null){
											jsonArr = (JSONArray) fieldObject.get(DataConstants.COLLECTION_DATA_CONF);
											for (Object object2 : jsonArr){
												if(dataObject.get(object2.toString()) != null && !dataObject.get(object2.toString()).toString().isEmpty())
													jsonData.put(object2.toString(), Double.parseDouble(dataObject.get(object2.toString()).toString()));
											}
										}
										jsonData.put(fieldObject.get(DataConstants.PATH).toString(), Double.parseDouble(temp));
										tempVal = mathematicalProcessor.process(fieldObject.get(DataConstants.EXPRESSION_CONF).toString(), jsonData+"");
									}
									catch (Exception e) 
									{
										tempVal = "";
									}
									dataLine = dataLine + tempVal.toString() + delimiter;
								}
								else
									dataLine = dataLine + tempVal.toString() + delimiter;
							}
							else if(!(value instanceof JSONObject) && !(value instanceof JSONArray))
							{
								if(DataConstants.DATE_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
								{
									if(dataObject.get(fieldObject.get(DataConstants.PATH).toString()) instanceof Date)
									{
										if(fieldObject.get(DataConstants.DATE_FORMAT) == null || fieldObject.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
										{
											LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : Date Format should not be empty..");
											throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Date Format should not be empty..");
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
												LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : To Date Format should not be empty..");
												throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : To Date Format should not be empty..");
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
											values = Utilities.getFieldValue(arrField.toString(), dataObject);
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

						//						log.info("dataLine : " + dataLine);
						if(count == writeBatchSize)
						{
							outputStream.write(dataLine.getBytes());
							dataLine = "";
							count = 0;
						}
					}

					if(!dataLine.trim().isEmpty()) {
						outputStream.write(dataLine.getBytes());
						outputStream.flush();
					}

					LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : Batch Record Count : " + batchRecordCount);
				}
				else
					jobObject.put("status", "Completed");
			}

			LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : DataObject : " + jobObject);
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
				LOGGER.error("Exception Occured : " + e.getMessage());
				throw new GenericProcessorException("Exception Occured : " + e.getMessage());
			}
		}
		catch(Exception exception)
		{
			jobObject.put("status", "Failure");
			LOGGER.error(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
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
			file = null;
			dataLine = null;
			values = null; 
			temp = null;
			jsonData = null;
			jsonArr = null;
			LOGGER.debug(_pipeLineName_ + " : Exit process.." + _processorName_);
			//			log.debug("Exit process.. ");
		}
		return false;
	}

	public void stop()
	{
		File zipFile = null;
		boolean deleteFileInLocal = true;
		boolean isStoreEnable = false;
		boolean isException = false;
		try
		{
			if(outputStream != null)
				outputStream.close();

			try
			{
				LOGGER.info("recordCount : " + recordCount);
				if(recordCount == 0l)
				{
					mongoDataUtil.updateRequest(_requestId_, _requestUpdateConf_, null, DataConstants.STATUS_NO_DATA, _outputConf_.get(DataConstants.EXTENSION).toString(), false);
					file.delete();
				}
				else 
				{
					if(zipFileName != null)
					{
						zipFile = new File(zipFileName);
						zipFile = util.zipMultipleFiles(fileList, zipFile, true);
					}

					if(_storeFileConf_ != null && !_storeFileConf_.toString().trim().isEmpty())
					{
						LOGGER.info("storeFileConf :: " + _storeFileConf_);

						if(_storeFileConf_.get(DataConstants.IS_ENABLE) != null)
							isStoreEnable = _storeFileConf_.get(DataConstants.IS_ENABLE).toString().equalsIgnoreCase("true");
						if(_storeFileConf_.get(DataConstants.DELETE_FILE_LOCAL) != null)
							deleteFileInLocal = _storeFileConf_.get(DataConstants.DELETE_FILE_LOCAL).toString().equalsIgnoreCase("true");

						if(isStoreEnable)
							if(zipFileName != null)
								mongoDataUtil.storeFile(_storeFileConf_.get(DataConstants.CONNECTION_ID).toString(), _storeFileConf_.get(DataConstants.SCHEMA_NAME).toString(), _outputConf_.get(DataConstants.ZIP_EXTENSION).toString(), zipFile.getName(), zipFile, _jobParameters_.get("request-id"), _requestUpdateConf_, DataConstants.STATUS_COMPLETED);
							else
								mongoDataUtil.storeFile(_storeFileConf_.get(DataConstants.CONNECTION_ID).toString(), _storeFileConf_.get(DataConstants.SCHEMA_NAME).toString(), _outputConf_.get(DataConstants.EXTENSION).toString(), fileName, file, _jobParameters_.get("request-id"), _requestUpdateConf_, DataConstants.STATUS_COMPLETED);
					}

					mongoDataUtil.updateStatus(_requestId_, _requestUpdateConf_, DataConstants.STATUS_COMPLETED);
				}
			}
			catch (GenericProcessorException e) 
			{
				LOGGER.error("Unable to store the file.. : " + file.getPath() + " : " + e.getMessage(), e);
			}
		}
		catch(Exception exception)
		{
			isException = true;
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			try
			{
				if(outputStream != null)
					outputStream.close();
				if(!isException)
				{
					if(deleteFileInLocal) { 
						if(file != null) 
						{
							LOGGER.info("File deleted in local path :: " + file.getAbsolutePath());
							file.delete();
						}
						if(zipFile != null) {
							LOGGER.info("File deleted in local path :: " + zipFile.getAbsolutePath());
							zipFile.delete();
						}
					}
				}
			}
			catch(IOException e)
			{
				LOGGER.error("Exception : " + e.getMessage(), e);
			}

			zipFile = null;

			outputStream = null;
			fileSeqFormat = null;
			file = null;
			fileList = null;
			zipFileName = null;
			fileName = null;
			preHeaders = null;
			headers = null;
			delimiter = null;
		}
	}
}