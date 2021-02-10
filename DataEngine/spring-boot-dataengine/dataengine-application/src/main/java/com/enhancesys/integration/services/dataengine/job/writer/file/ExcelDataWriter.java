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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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

public class ExcelDataWriter extends JobProcessor implements DataWriter
{
	@Autowired
	MongoDataUtil mongoDataUtil;

	@Autowired
	Utilities util;

	@Autowired
	ExpressionProcessor expressionProcessor;

	@Autowired
	MathematicalProcessor mathematicalProcessor;

	private SXSSFWorkbook workbook = null;
	private Sheet sheet = null;
	private CellStyle doubleCellStyle = null;
	private CellStyle singleDecimalDoubleCellStyle = null;
	private CellStyle longCellStyle = null;
	private DataFormat dataFormat = null;
	private OutputStream outputStream = null;
	private DecimalFormat fileSeqFormat_000 = new DecimalFormat("000");
	private File file = null;
	private List<File> fileList = null;
	private String[] headers = null;
	private String[] preHeaders = null;
	private String delimiter = null;
	private String fileName = null;
	private String zipFileName = null;
	private long recordCount = 0l; 
	private long dataCount = 0l;
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private int rowCount = 0;
	private int fileSequence = 1;
	private boolean isMultipleSheets = false;

	private static Logger LOGGER = Logger.getLogger(ExcelDataWriter.class);

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

			createWorkBook();
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

	private void createWorkBook()
	{
		DateFormat fileFormat = null;
		String fileNamePattern = null;
		Row headerRow = null;

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

			file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
			if(_recordLimit_ != null && _recordLimit_.longValue() > 0l && !isMultipleSheets)
				file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat_000.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());

			fileList = new ArrayList<File>();
			fileList.add(file);

			delimiter = _outputConf_.get(DataConstants.FIELD_DELIMITER).toString();
			String temp_delimiter = delimiter;
			if(delimiter.equals("|"))
				temp_delimiter = "\\|";
			preHeaders = _outputConf_.get(DataConstants.PRE_HEADER) != null ? _outputConf_.get(DataConstants.PRE_HEADER).toString().split(temp_delimiter) : null;
			headers = _outputConf_.get(DataConstants.FILE_HEADERS).toString().split(temp_delimiter);
			if(_jobParameters_.get(DataConstants.LANGUAGE) != null && !_jobParameters_.get(DataConstants.LANGUAGE).toString().trim().isEmpty() 
					&& _outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS) != null 
					&& !_outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().trim().isEmpty())
			{
				if(DataConstants.LANG_UKRAINIAN.equalsIgnoreCase(_jobParameters_.get(DataConstants.LANGUAGE).toString()))
					headers = _outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().split(temp_delimiter);
				else if(DataConstants.LANG_INDONESIAN.equalsIgnoreCase(_jobParameters_.get(DataConstants.LANGUAGE).toString()))
					headers = _outputConf_.get(_jobParameters_.get(DataConstants.LANGUAGE).toString() + "-" + DataConstants.FILE_HEADERS).toString().split(temp_delimiter);
			}

			workbook = new SXSSFWorkbook();

			LOGGER.info("sheetName ::: "+ fileSeqFormat_000.format(fileSequence));

			if(isMultipleSheets)
				sheet = workbook.createSheet(fileSeqFormat_000.format(fileSequence));
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
			headerRow = null;
		}
	}

	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		LOGGER.debug(_pipeLineName_ + " : Entry process.." + _processorName_ + " :jobCount: " + jobCount);
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
							fileSequence += 1;

							if(isMultipleSheets)
							{
								if(workbook != null)
								{
									LOGGER.info("sheetName ::: "+fileSeqFormat_000.format(fileSequence));
									sheet = workbook.createSheet(fileSeqFormat_000.format(fileSequence));
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

								file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat_000.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
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
							LOGGER.debug("FieldObject : " + fieldObject);
							value = getFieldValue(fieldObject.get(DataConstants.PATH).toString(), dataObject);
							if(!(value instanceof JSONObject) && !(value instanceof JSONArray))
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
												LOGGER.error(_pipeLineName_ + " : " + _processorName_ + " : To Date Format should not be empty..");
												throw new GenericProcessorException(_pipeLineName_ + " : " + _processorName_ + " : To Date Format should not be empty..");
											}
											if(format == null)
												format = new SimpleDateFormat(fieldObject.get(DataConstants.TO_DATE_FORMAT).toString());
											dataField = format.format(fromFormat.parse(value.toString()));
										}
										else
											dataField = value.toString();
									}
								}
								if(value != null && !value.toString().trim().isEmpty())
								{
									dataField = value.toString();
								}
								else
								{
									dataField = "";
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
							else if(DataConstants.BOOLEAN_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								String tempVal = "";

								if(value != null && value.toString().trim().equalsIgnoreCase("true"))
									tempVal = fieldObject.get(DataConstants.IF).toString();
								else
									tempVal = fieldObject.get(DataConstants.ELSE).toString();
								row.createCell(j).setCellValue(tempVal);
							}
							else if(DataConstants.CONDITIONAL_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								String tempVal = "";
								if(fieldObject.get(DataConstants.EXPRESSION_CONF) != null && value != null && !value.toString().isEmpty())
								{
									try 
									{
										if(fieldObject.get(DataConstants.OPTIONAL) != null && fieldObject.get(DataConstants.OPTIONAL).toString().isEmpty())
										{
											if(fieldObject.get(DataConstants.LOOKUP_FIELD) != null)
												tempVal = dataObject.get(fieldObject.get(DataConstants.LOOKUP_FIELD).toString()).toString();
										}
										else
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
											{
												if(fieldObject.get(DataConstants.IF) != null)
													tempVal = fieldObject.get(DataConstants.IF).toString();
												else
													if(fieldObject.get(DataConstants.LOOKUP_FIELD) != null)
														tempVal = dataObject.get(fieldObject.get(DataConstants.LOOKUP_FIELD).toString()).toString();
													else 
													{
														sheet.removeRow(row);
														--recordCount;// no need to write data
														continue outer;
													}
											}
											else 
											{
												if(fieldObject.get(DataConstants.ELSE) != null && !fieldObject.get(DataConstants.ELSE).toString().isEmpty())
													tempVal = fieldObject.get(DataConstants.ELSE).toString();
												else 
												{
													sheet.removeRow(row);
													--recordCount;// no need to write data
													continue outer;
												}
											}
										}
									} 
									catch (Exception e) 
									{
										tempVal = "";
									}
									dataField = tempVal.toString();
								}
								else
									dataField = tempVal.toString();
								row.createCell(j).setCellValue(dataField);
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
									dataField = tempVal.toString();
								}
								else
									dataField = tempVal.toString();

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
					LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : Record Count : " + count);
				}
				else
					jobObject.put("status", "Completed");
			}
			//			workbook.write(outputStream);

			LOGGER.debug(_pipeLineName_ + " : " + _processorName_ + " : DataObject : " + jobObject);
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
				LOGGER.error("Exception Occured : " + e.getMessage());
				throw new GenericProcessorException("Exception Occured : " + e.getMessage());
			}
		}
		catch(Exception exception)
		{
			try 
			{
				mongoDataUtil.updateStatus(_requestId_, _requestUpdateConf_, DataConstants.STATUS_CANCELLED);
			} 
			catch (GenericProcessorException excep) 
			{
				LOGGER.error("Unhandled Exception : " + excep.getMessage(), excep);
			}

			jobObject.put("status", "Failure");
			LOGGER.error(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
			throw new GenericProcessorException(_pipeLineName_ + " : Exception Occured : " + exception.getMessage(), exception);
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
			dataToEvaluate = null;
			temp = null;
			jsonData = null;
			jsonArr = null;
			LOGGER.debug(_pipeLineName_ + " : Exit process.." + _processorName_);
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
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
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
		File zipFile = null;
		boolean deleteFileInLocal = true;
		boolean isStoreEnable = false;
		boolean isException = false;

		try 
		{
			LOGGER.info("inside ExcelDataWriter Stop..");
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

					if(_storeFileConf_ != null  && !_storeFileConf_.toString().trim().isEmpty())
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
		catch (IOException e) 
		{
			LOGGER.error("Exception : " + e.getMessage(), e);
			isException = true;
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
			catch (IOException e) 
			{
				LOGGER.error("Exception : " + e.getMessage(), e);
			}

			zipFile = null;

			workbook = null;
			sheet = null;
			outputStream = null;
			fileSeqFormat_000 = null;
			file = null;
			fileList = null;
			headers = null;
			preHeaders = null;
			delimiter = null;
			fileName = null;
			zipFileName = null;
		}
	}
}
