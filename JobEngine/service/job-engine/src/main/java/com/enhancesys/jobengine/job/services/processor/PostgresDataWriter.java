package com.enhancesys.jobengine.job.services.processor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.postgresql.PGConnection;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;

public class PostgresDataWriter extends JobProcessor
{
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private Connection connection = null;
	private JSONObject outputConf = null;
	private static Logger log = Logger.getLogger(PostgresDataWriter.class);
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		log.info("Entry init");
		
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
			
			outputConf = (JSONObject) jobConfigData.get(DataConstants.OUTPUT_CONF);
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
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		log.info(pipeLineName + " : Entry process.." + processorName + " :jobCount: " + jobCount);
		JSONObject payloadObject = null;
		JSONObject paramObject = null;
		JSONObject dataObject = null;
		JSONArray jsonArray = null;
		JSONArray excludeFields = null;
		JSONArray queryParamConf = null; 
		PreparedStatement statement = null;
		LargeObjectManager largeObjectManager = null;
		LargeObject largeObject = null;
		InputStream inputStream = null;
		DateFormat format = null;
		DateFormat fromFormat = null;
		Integer position = null;
		String value = null;
		byte[] buffer = null;
		
		try
		{
			if(outputConf == null)
			{
				log.error(pipeLineName + " : " + processorName + " : Output configuration not exist..");
				throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Output configuration not exist..");
			}
			if(!DataConstants.POSTGRES.equalsIgnoreCase(outputConf.get(DataConstants.TYPE).toString()))
			{
				log.error(pipeLineName + " : " + processorName + " : Output type should be postgres..");
				throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Output type should be postgres..");
			}
			
			if(connection == null)
			{
				Class.forName(outputConf.get(DataConstants.DRIVER_CLASS).toString());
				connection = DriverManager.getConnection(outputConf.get(DataConstants.URL).toString(), outputConf.get(DataConstants.USER).toString(), outputConf.get(DataConstants.PASSWORD).toString());
			}

			excludeFields = (JSONArray) outputConf.get(DataConstants.EXCLUDE_FIELDS);
			queryParamConf = (JSONArray) outputConf.get(DataConstants.QUERY_PARAM_CONF);
			statement = connection.prepareStatement(outputConf.get(DataConstants.QUERY).toString());
			
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					int count = 0;
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					for(Object object : jsonArray)
					{
						dataObject = ((JSONObject) object);
						if(excludeFields != null)
						{
							for(Object field : excludeFields)
							{
								dataObject.remove(field);
							}
						}
						for(Object obj : queryParamConf)
						{
							paramObject = (JSONObject) obj;
							position = Integer.parseInt(paramObject.get(DataConstants.POSITION).toString());
							value = dataObject.get(paramObject.get(DataConstants.PARAM).toString()).toString();
							if(DataConstants.INTEGER_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
								statement.setLong(position, Integer.parseInt(value));
							else if(DataConstants.LONG_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
								statement.setLong(position, Long.parseLong(value));
							else if(DataConstants.FLOAT_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
								statement.setFloat(position, Float.parseFloat(value));
							else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
								statement.setDouble(position, Double.parseDouble(value));
							else if(DataConstants.STRING_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
								statement.setString(position, value);
							else if(DataConstants.OID_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
							{
								largeObjectManager = ((PGConnection) connection).getLargeObjectAPI();
								int oid = largeObjectManager.create(LargeObjectManager.READ | LargeObjectManager.WRITE);
			    				largeObject = largeObjectManager.open(oid, LargeObjectManager.WRITE);
			    			
			    				inputStream = new ByteArrayInputStream(value.toString().getBytes());
			                    buffer = new byte[2048];
			    				int stream;    			
			    				
			    				while ((stream = inputStream.read(buffer, 0, 2048)) > 0) 
			    				{
			    					largeObject.write(buffer, 0, stream);
			    				}

			    				if (largeObject != null) 
			    				{
			    					largeObject.close();
			    				}
			    				
			    				statement.setInt(position, oid);
			    				inputStream.close();
			    				largeObject = null;
			    				inputStream = null;
			    				largeObjectManager = null;
							}
							else if(DataConstants.DATE_TYPE.equalsIgnoreCase(paramObject.get(DataConstants.TYPE).toString()))
							{
								if(dataObject.get(paramObject.get(DataConstants.PARAM).toString()) instanceof Date)
								{
									if(paramObject.get(DataConstants.DATE_FORMAT) == null || paramObject.get(DataConstants.DATE_FORMAT).toString().trim().isEmpty())
									{
										log.error(pipeLineName + " : " + processorName + " : Date Format should not be empty..");
										throw new GenericProcessorException(pipeLineName + " : " + processorName + " : Date Format should not be empty..");
									}
									if(format == null)
										format = new SimpleDateFormat(paramObject.get(DataConstants.DATE_FORMAT).toString());
									statement.setString(position, format.format(dataObject.get(paramObject.get(DataConstants.PARAM).toString())));
								}
								else
								{
									if(paramObject.get(DataConstants.FROM_DATE_FORMAT) != null && !paramObject.get(DataConstants.FROM_DATE_FORMAT).toString().trim().isEmpty())
									{
										if(fromFormat == null)
											fromFormat = new SimpleDateFormat(paramObject.get(DataConstants.FROM_DATE_FORMAT).toString());
									}
									if(fromFormat != null)
									{
										if(paramObject.get(DataConstants.TO_DATE_FORMAT) != null && !paramObject.get(DataConstants.TO_DATE_FORMAT).toString().trim().isEmpty())
										{
											log.error(pipeLineName + " : " + processorName + " : To Date Format should not be empty..");
											throw new GenericProcessorException(pipeLineName + " : " + processorName + " : To Date Format should not be empty..");
										}
										if(format == null)
											format = new SimpleDateFormat(paramObject.get(DataConstants.TO_DATE_FORMAT).toString());
										statement.setString(position, format.format(fromFormat.parse(value)));
									}
									else
										statement.setString(position, value);
								}
							}
						}
						count ++;
						statement.addBatch();
						if(count == 1000)
						{
							int[] rowsUpdated = statement.executeBatch();
							log.info(rowsUpdated.length + " Rows Inserted..");
							count = 0;
						}
					}
					if(count > 0)
					{
						int[] rowsUpdated = statement.executeBatch();
						log.info(rowsUpdated.length + " Rows Inserted..");
						count = 0;
					}
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
			log.info(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
			try 
			{
				if(largeObject != null)
					largeObject.close();
				
				if(inputStream != null)
					inputStream.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}

			payloadObject = null;
			paramObject = null;
			dataObject = null;
			jsonArray = null;
			excludeFields = null;
			queryParamConf = null; 
			statement = null;
			largeObjectManager = null;
			largeObject = null;
			inputStream = null;
			format = null;
			fromFormat = null;
			position = null;
			value = null;
			buffer = null;
			log.info(pipeLineName + " : Exit process.." + processorName);
		}
		return false;
	}
	
	public void stop()
	{
		
	}
}