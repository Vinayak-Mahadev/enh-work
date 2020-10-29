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
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobProcessor;
import com.enhancesys.integration.services.dataengine.job.components.DataWriter;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoDataUtil;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public class JSONDataWriter extends JobProcessor implements DataWriter
{
	@Autowired
	MongoDataUtil mongoDataUtil;
	
	@Autowired
	Utilities util;
	
	private int fileSequence = 1;
	@SuppressWarnings("unused")
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private long writeBatchSize;
	private File file = null;
	private OutputStream outputStream = null;
	private long recordCount = 0l; 
	private DecimalFormat fileSeqFormat = new DecimalFormat("000");
	private List<File> fileList = null;
	private String zipFileName = null;
	private String fileName = null;
	private static Logger log = Logger.getLogger(JSONDataWriter.class);
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init..");
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
//			if(outputConf.get(DataConstants.ZIP_EXTENSION) != null && !outputConf.get(DataConstants.ZIP_EXTENSION).toString().trim().isEmpty())
			zipFileName = _outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + _outputConf_.get(DataConstants.ZIP_EXTENSION).toString();
			
			if(_recordLimit_ != null && _recordLimit_.longValue() > 0l)
				file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
			else
				file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
			
			fileList = new ArrayList<File>();
			fileList.add(file);
			
			outputStream = new FileOutputStream(file);
			outputStream.write("[".getBytes());
		}
		catch(Exception exception)
		{
			try 
			{
				mongoDataUtil.updateStatus(_requestId_, _requestUpdateConf_, DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				log.error("Unhandled Exception : " + excep.getMessage(), excep);
			}
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
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
		log.error("Entry process.. " + jobObject);
//		log.info(pipeLineName + " : Entry process.." + processorName + " :jobCount: " + jobCount);
		JSONObject payloadObject = null;
		JSONObject dataObject = null;
		JSONArray jsonArray = null;
		JSONArray excludeFields = null;
		JSONArray mandatoryFields = null;
		String dataLine = "";
		File file = null;
		
		try
		{
			if(_outputConf_ == null)
			{
				log.error(_pipeLineName_ + " : " + _processorName_ + " : Output configuration not exist..");
				throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Output configuration not exist..");
			}
			if(!DataConstants.FILE.equalsIgnoreCase(_outputConf_.get(DataConstants.TYPE).toString()))
			{
				log.error(_pipeLineName_ + " : " + _processorName_ + " : Output type should be file..");
				throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : Output type should be file..");
			}
			
			excludeFields = (JSONArray) _outputConf_.get(DataConstants.EXCLUDE_FIELDS);
			mandatoryFields = (JSONArray) _outputConf_.get(DataConstants.MANDATORY_FIELDS);
			
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					int writeBatchCount = 0;
					int batchRecordCount = 0;
					int dataCount = 0;
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					recordCount = recordCount + jsonArray.size();
					outer : for(Object object : jsonArray)
					{
//						dataCount ++;
						dataObject = ((JSONObject) object);
//						log.info("dataObject : " + dataObject);
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
						if(_recordLimit_ != null && _recordLimit_.longValue() > 0l && recordCount == _recordLimit_.longValue())
						{
							if(outputStream != null)
							{
								outputStream.write("]".getBytes());
								outputStream.close();
							}
							fileSequence += 1;
							file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
							fileList.add(file);
							
							outputStream = new FileOutputStream(file);
							outputStream.write("[".getBytes());
							dataLine = "";
							writeBatchCount = 0;
							dataCount = 0;
						}
						dataCount += 1;
						
						writeBatchCount ++;
						batchRecordCount ++;
						if(jobObject.get("status") != null && "Completed".equalsIgnoreCase(jobObject.get("status").toString()) && dataCount == jsonArray.size())
							dataLine = dataLine + dataObject.toString();
						else
							dataLine = dataLine + dataObject.toString() + ",\n";
						
//						log.info("dataLine : " + dataLine);
						if(writeBatchCount == writeBatchSize)
						{
							outputStream.write(dataLine.getBytes());
							dataLine = "";
							writeBatchCount = 0;
						}
					}
					
					if(!dataLine.trim().isEmpty())
						outputStream.write(dataLine.getBytes());

					log.info(_pipeLineName_ + " : " + _processorName_ + " : Batch Record Count : " + batchRecordCount);
				}
				else
					jobObject.put("status", "Completed");
			}
			
			log.info(_pipeLineName_ + " : " + _processorName_ + " : DataObject : " + jobObject);
			for(long i = 0; i < delayCount; i++)
			{
				for(long j = 0; j < delayCount; j++)
					continue;
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
			log.error(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			payloadObject = null;
			dataObject = null;
			jsonArray = null;
			mandatoryFields = null;
			excludeFields = null;
			file = null;
			dataLine = null;
//			log.info(pipeLineName + " : Exit process.." + processorName);
			log.error("Exit process.. ");
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
			{
				outputStream.write("]".getBytes());
				outputStream.close();
			}
			
			try
			{
				log.info("recordCount : " + recordCount);
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
					
					if(_outputConf_.get(DataConstants.STORE_FILE_CONF) != null && !_outputConf_.get(DataConstants.STORE_FILE_CONF).toString().trim().isEmpty())
					{
						storeFileConf = (JSONObject) _outputConf_.get(DataConstants.STORE_FILE_CONF);
						if(storeFileConf != null)
						{
							if(zipFileName != null)
							{
								mongoDataUtil.storeFile(storeFileConf.get(DataConstants.CONNECTION_ID).toString(), storeFileConf.get(DataConstants.SCHEMA_NAME).toString(), _outputConf_.get(DataConstants.ZIP_EXTENSION).toString(), zipFile.getName(), zipFile, _jobParameters_.get("request-id"), _requestUpdateConf_, DataConstants.STATUS_COMPLETED);
								if(zipFile!=null)
									zipFile.delete();
							}
							else
							{
								mongoDataUtil.storeFile(storeFileConf.get(DataConstants.CONNECTION_ID).toString(), storeFileConf.get(DataConstants.SCHEMA_NAME).toString(), _outputConf_.get(DataConstants.EXTENSION).toString(), fileName, file, _jobParameters_.get("request-id"), _requestUpdateConf_, DataConstants.STATUS_COMPLETED);
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
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{

			try
			{
				if(outputStream != null)
					outputStream.close();
			}
			catch(IOException e)
			{
				log.error("Exception : " + e.getMessage(), e);
			}
			
			storeFileConf = null;
			zipFile = null;
			
			outputStream = null;
			fileSeqFormat = null;
			file = null;
			fileList = null;
			zipFileName = null;
			fileName = null;
		}
	}
}