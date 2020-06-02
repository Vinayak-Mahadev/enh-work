package com.enhancesys.jobengine.job.services.processor;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.mongo.util.MongoDataUtil;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.GenericProcessorException;
import com.enhancesys.jobengine.job.services.util.Util;

public class ExcelDataWriter extends JobProcessor
{
	@Autowired
	MongoDataUtil mongoDataUtil;
	
	@Autowired
	Util util;
	
	private JSONObject outputConf = null;
	private Object requestUpdateConf = null;
	private SXSSFWorkbook workbook = null;
	private Sheet sheet = null;
	private CellStyle doubleCellStyle = null;
	private CellStyle singleDecimalDoubleCellStyle = null;
	private CellStyle longCellStyle = null;
	private DataFormat dataFormat = null;
	private OutputStream outputStream = null;
	private DecimalFormat fileSeqFormat = new DecimalFormat("000");
	private File file = null;
	private List<File> fileList = null;
	private String[] headers = null;
	private String[] preHeaders = null;
	private String delimiter = null;
	private String fileName = null;
	private String zipFileName = null;
	private Long recordLimit = null;
	private long recordCount = 0l; 
	private long dataCount = 0l;
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private int rowCount = 0;
	private int fileSequence = 1;
	private boolean isMultipleSheets = false;
	
	private static Logger log = Logger.getLogger(ExcelDataWriter.class);
	
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
			
			outputConf = (JSONObject) jobConfigData.get(DataConstants.OUTPUT_CONF);
			if(outputConf.get(DataConstants.FILE_RECORD_LIMIT) != null && !outputConf.get(DataConstants.FILE_RECORD_LIMIT).toString().trim().isEmpty())
				recordLimit = Long.parseLong(outputConf.get(DataConstants.FILE_RECORD_LIMIT).toString().trim()); 
			
			if(outputConf.get(DataConstants.IS_MULTIPLE_SHEETS) != null && !outputConf.get(DataConstants.IS_MULTIPLE_SHEETS).toString().trim().isEmpty())
				isMultipleSheets = Boolean.parseBoolean(outputConf.get(DataConstants.IS_MULTIPLE_SHEETS).toString().trim()); 
			
			requestUpdateConf = jobConfigData.get(DataConstants.REQUEST_UPDATE_CONF);
			createWorkBook();
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
	
	private void createWorkBook()
	{
		DateFormat fileFormat = null;
		String fileNamePattern = null;
		Row headerRow = null;
		
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
			
			file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + outputConf.get(DataConstants.EXTENSION).toString());
			if(recordLimit != null && recordLimit.longValue() > 0l && !isMultipleSheets)
				file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + outputConf.get(DataConstants.EXTENSION).toString());
			
			fileList = new ArrayList<File>();
			fileList.add(file);

			delimiter = outputConf.get(DataConstants.FIELD_DELIMITER).toString();
			preHeaders = outputConf.get(DataConstants.PRE_HEADER) != null ? outputConf.get(DataConstants.PRE_HEADER).toString().split(delimiter) : null;
			headers = outputConf.get(DataConstants.FILE_HEADERS).toString().split(delimiter);
			if(jobParameters.get(DataConstants.LANGUAGE) != null && !jobParameters.get(DataConstants.LANGUAGE).toString().trim().isEmpty() 
					&& outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS) != null 
					&& !outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().trim().isEmpty())
			{
				if(DataConstants.LANG_UKRAINIAN.equalsIgnoreCase(jobParameters.get(DataConstants.LANGUAGE).toString()))
					headers = outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().split(delimiter);
				else if(DataConstants.LANG_INDONESIAN.equalsIgnoreCase(jobParameters.get(DataConstants.LANGUAGE).toString()))
					headers = outputConf.get(jobParameters.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().split(delimiter);
			}
				
			workbook = new SXSSFWorkbook();
			log.error("sheetName ::: "+ fileSeqFormat.format(fileSequence));
			
			if(isMultipleSheets)
				sheet = workbook.createSheet(fileSeqFormat.format(fileSequence));
			else
				sheet = workbook.createSheet(fileName);
			
			dataFormat = workbook.createDataFormat();
			
			doubleCellStyle = workbook.createCellStyle();
			doubleCellStyle.setDataFormat(dataFormat.getFormat("0.00"));
			
			singleDecimalDoubleCellStyle = workbook.createCellStyle();
			singleDecimalDoubleCellStyle.setDataFormat(dataFormat.getFormat("0.0"));
			
			longCellStyle = workbook.createCellStyle();
			longCellStyle.setDataFormat(dataFormat.getFormat("0"));
			
			outputStream = new FileOutputStream(file);
			
			if(preHeaders != null)
			{
				headerRow = sheet.createRow(rowCount++);
				
				for(int i = 0; i < preHeaders.length ; i++)
				{
					headerRow.createCell(i).setCellValue(preHeaders[i]);
					sheet.autoSizeColumn(i);
				}
				
				sheet.createRow(rowCount++);
			}
			
			headerRow = sheet.createRow(rowCount++);
			
			for(int i = 0; i < headers.length ; i++)
			{
				headerRow.createCell(i).setCellValue(headers[i]);
				sheet.autoSizeColumn(i);
			}
		}
		catch(Exception exception)
		{
			try 
			{
				mongoDataUtil.updateStatus(jobParameters.get("request-id"), requestUpdateConf, DataConstants.STATUS_CANCELLED);
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
			headerRow = null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		log.info(pipeLineName + " : Entry process.." + processorName + " :jobCount: " + jobCount);
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
		String dataField = "";
		Row row = null;
		Row headerRow = null;
		Cell cell = null;
	
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
			
//			log.error("dataObject : " + jobObject);
			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
					int count = 0;
					payloadObject = (JSONObject) jobObject.get(DataConstants.PAYLOAD);
					jsonArray = (JSONArray) payloadObject.get(DataConstants.DATA_LIST);
					recordCount = recordCount + jsonArray.size();
					outer : for(Object object : jsonArray)
					{
						dataObject = ((JSONObject) object);
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
							fileSequence += 1;
							
							if(isMultipleSheets)
							{
								if(workbook != null)
								{
									log.error("sheetName ::: "+fileSeqFormat.format(fileSequence));
									sheet = workbook.createSheet(fileSeqFormat.format(fileSequence));
								}
							}
							else
							{
								if(workbook != null)
									workbook.write(outputStream);

								if(outputStream != null)
									outputStream.close();

								if(workbook != null)
								{
									workbook.dispose();
									workbook.close();
								}
								
								file = new File(outputConf.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + outputConf.get(DataConstants.EXTENSION).toString());
								fileList.add(file);
								workbook = new SXSSFWorkbook();
								sheet = workbook.createSheet(fileName);
								outputStream = new FileOutputStream(file);
							}
							
							rowCount = 0;
							if(preHeaders != null)
							{
								headerRow = sheet.createRow(rowCount++);

								for(int i = 0; i < preHeaders.length ; i++)
								{
									headerRow.createCell(i).setCellValue(preHeaders[i]);
									sheet.autoSizeColumn(i);
								}

								sheet.createRow(rowCount++);
							}

							headerRow = sheet.createRow(rowCount++);

							for(int i = 0; i < headers.length ; i++)
							{
								headerRow.createCell(i).setCellValue(headers[i]);
								sheet.autoSizeColumn(i);
							}

							dataCount = 0l;
						}
						dataCount += 1;
						row = sheet.createRow(rowCount++);
						int j = 0;
//						log.error("dataObject : " + dataObject);
						for(Object obj : fieldConf)
						{
							fieldObject = (JSONObject) obj;
							log.info("FieldObject : " + fieldObject);
							value = getFieldValue(fieldObject.get(DataConstants.PATH).toString(), dataObject);
							if(!(value instanceof JSONObject) && !(value instanceof JSONArray))
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
										dataField = dataField + format.format(dataObject.get(fieldObject.get(DataConstants.PATH).toString()));
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
											dataField = format.format(fromFormat.parse(value.toString()));
										}
										else
											dataField = value.toString();
									}
								}
								else //if(value != null && !value.toString().trim().isEmpty())
								{
									dataField = value.toString();
								}
							}
							
//							log.error("value : " + value + " : DataField : " + dataField + " : " + value.getClass());
							if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
//								log.error("dataField : " + dataField);
								cell = row.createCell(j);
								if(dataField != null && !dataField.trim().isEmpty() && !"NaN".equalsIgnoreCase(dataField.trim()) && !"Infinity".equalsIgnoreCase(dataField.trim()))
								{
									cell.setCellValue(Long.parseLong(dataField));
									cell.setCellStyle(longCellStyle);
								}
							}
							else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								cell = row.createCell(j);
//								log.error("dataField : " + dataField);
								if(dataField != null && !dataField.trim().isEmpty() && !"NaN".equalsIgnoreCase(dataField.trim()) && !"Infinity".equalsIgnoreCase(dataField.trim()))
								{
									cell.setCellValue(Double.parseDouble(dataField));
									cell.setCellStyle(doubleCellStyle);
									if(fieldObject.get(DataConstants.NUMBER_OF_DECIMALS) != null 
											&& !fieldObject.get(DataConstants.NUMBER_OF_DECIMALS).toString().trim().isEmpty()
											&& "1".equalsIgnoreCase(fieldObject.get(DataConstants.NUMBER_OF_DECIMALS).toString().trim()))
										cell.setCellStyle(singleDecimalDoubleCellStyle);
								}
							}
							else
								row.createCell(j).setCellValue(dataField);
							j++;
							dataField = "";
						}
						count ++;
					}
					log.info(pipeLineName + " : " + processorName + " : Record Count : " + count);
				}
				else
					jobObject.put("status", "Completed");
			}
//			workbook.write(outputStream);
			
			log.info(pipeLineName + " : " + processorName + " : DataObject : " + jobObject);
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
			try 
			{
				mongoDataUtil.updateStatus(jobParameters.get("request-id"), requestUpdateConf, DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				log.error("Unhandled Exception : " + excep.getMessage(), excep);
			}

			jobObject.put("status", "Failure");
			log.error(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(pipeLineName + " : Exception Occured : " + exception.getMessage(), exception);
		}
		finally
		{
/*			if("Completed".equalsIgnoreCase(jobObject.get("status").toString()))
				stop();*/
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
			dataField = "";
			row = null;
			headerRow = null;
			cell = null;
			log.info(pipeLineName + " : Exit process.." + processorName);
		}
		return false;
	}
	
	private Object getFieldValue(String jsonPath, JSONObject requestJson) throws Exception
	{
		JSONObject tempJson = null;
		String[] arr = null;
		
		try
		{
			tempJson = requestJson;
			arr = jsonPath.split("\\.");
			for(int i = 0; i < arr.length; i ++)
			{
//				log.error("arr[i]" + arr[i] + " :: tempJson.get(arr[i]) : " + tempJson.get(arr[i]) + " : " + tempJson.get(arr[i]).getClass());
				if(tempJson.get(arr[i]) == null || tempJson.get(arr[i]).toString().trim().isEmpty())
				{
					return "";
				}
				if(tempJson.get(arr[i]) instanceof JSONObject)
				{
//					log.debug("insdie object..");
					tempJson = (JSONObject) tempJson.get(arr[i]);
					if(i == arr.length - 1)
						return tempJson;
				}
				if(tempJson.get(arr[i]) instanceof JSONArray)
				{
//					log.debug("inside array..");
					return (JSONArray) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof String)
				{
//					log.debug("insdie string..");
					return (String) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof Long)
				{
//					log.debug("insdie long.." + tempJson.get(arr[i]));
					return ((Long) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Integer)
				{
//					log.debug("insdie integer.." + tempJson.get(arr[i]));
					return ((Integer) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Double)
				{
//					log.debug("insdie double.." + tempJson.get(arr[i]));
					return ((Double) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Date)
				{
					return (Date) tempJson.get(arr[i]);
				}
			}
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			tempJson = null;
			arr = null;
		}
		return null;
	}
	
	public void stop()
	{
		JSONObject storeFileConf = null;
		File zipFile = null;

		try 
		{
			log.info("inside ExcelDataWriter Stop..");
			if(workbook != null)
				workbook.write(outputStream);
			
			if(outputStream != null)
				outputStream.close();
			
			if(workbook != null)
			{
				workbook.dispose();
				workbook.close();
			}
			
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
		catch (IOException e) 
		{
			log.error("Exception : " + e.getMessage(), e);
		}
		finally
		{
			try 
			{
				if(outputStream != null)
					outputStream.close();
			
				if(workbook != null)
				{
					workbook.dispose();
					workbook.close();
				}
			} 
			catch (IOException e) 
			{
				log.error("Exception : " + e.getMessage(), e);
			}
			
			storeFileConf = null;
			zipFile = null;
			
			outputConf = null;
			requestUpdateConf = null;
			workbook = null;
			sheet = null;
			outputStream = null;
			fileSeqFormat = null;
			file = null;
			fileList = null;
			headers = null;
			preHeaders = null;
			delimiter = null;
			fileName = null;
			zipFileName = null;
			recordLimit = null;
		}
	}
}