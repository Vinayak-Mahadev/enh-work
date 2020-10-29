package com.enhancesys.integration.services.dataengine.job.writer.file;

import java.awt.Color;
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
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFDataWriter extends JobProcessor implements DataWriter
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
	private Document document = null;
	private Font fontBold = null;
	private Font fontNormal = null;
	private PdfPTable table = null;
	private Paragraph emptyLine = null;
	private String zipFileName = null;
	private String fileName = null;
	private String delimiter = null;
	private String preHeaders = null;
	private String[] headers = null;
	private long recordCount = 0l; 
	private long jobCount;
	private long sleepTime;
	private long delayCount;
	private long dataCount = 0l;
	private int fileSequence = 1;
	private static Logger LOGGER = Logger.getLogger(PDFDataWriter.class);


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

		Paragraph paragraph = null;
		PdfPCell header = null;
		DateFormat fileFormat = null;
		String fileNamePattern = null;
		String envPath = null;

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

			String temp_delimiter = delimiter;
			if(delimiter.equals("|"))
				temp_delimiter = "\\|";

			preHeaders = _outputConf_.get(DataConstants.PRE_HEADER) != null ? _outputConf_.get(DataConstants.PRE_HEADER).toString() : null;
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

			envPath = System.getenv("APPSERVER_CONF_PATH");

			emptyLine = new Paragraph("");
			fontBold = FontFactory.getFont((envPath + File.separator + "fonts/TIMES.TTF"), "Identity-H", BaseFont.EMBEDDED, 5, Font.BOLD, Color.BLACK);
			fontNormal = FontFactory.getFont((envPath + File.separator + "fonts/TIMES.TTF"), "Identity-H", BaseFont.EMBEDDED, 5);
			if(envPath.trim().endsWith("/"))
			{
				fontBold = FontFactory.getFont((envPath + "fonts/TIMES.TTF"), "Identity-H", BaseFont.EMBEDDED, 5, Font.BOLD, Color.BLACK);
				fontNormal = FontFactory.getFont((envPath + "fonts/TIMES.TTF"), "Identity-H", BaseFont.EMBEDDED, 5);
			}

			outputStream = new FileOutputStream(file);
			document = new Document();
			PdfWriter.getInstance(document, outputStream);
			document.open();
			if(_outputConf_.get(DataConstants.REPORT_NAME) != null && !_outputConf_.get(DataConstants.REPORT_NAME).toString().trim().isEmpty())
			{
				paragraph = new Paragraph(_outputConf_.get(DataConstants.REPORT_NAME).toString().trim(), fontBold);
				paragraph.setAlignment(Element.ALIGN_CENTER);
				document.add(paragraph);
				document.add(emptyLine);
			}

			if(preHeaders != null && !preHeaders.trim().isEmpty())
			{
				paragraph = new Paragraph(preHeaders.trim(), fontNormal);
				document.add(paragraph);
				document.add(emptyLine);
			}

			table = new PdfPTable(headers.length);
			table.setWidthPercentage(110);
			for (String column : headers) 
			{
				paragraph = new Paragraph(column, fontBold);
				header = new PdfPCell(paragraph);
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBackgroundColor(Color.LIGHT_GRAY);
				table.addCell(header);
			}

			table.setHeaderRows(1);

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
			paragraph = null;
			header = null;
			fileFormat = null;
			fileNamePattern = null;
			envPath = null;
			if(file != null)
				LOGGER.info("File Name :: " + file.getAbsolutePath());
			LOGGER.info("Exit createFile..");
		}
	}

	@SuppressWarnings("unchecked")
	public boolean process(JSONObject jobObject) throws GenericProcessorException
	{
		//		log.info("Entry process.. " + jobObject);
		LOGGER.debug("Entry process :: "+ _pipeLineName_ + " : Entry process.." + _processorName_ + " :jobCount: " + jobCount);
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
		Paragraph paragraph = null;
		PdfPCell header = null;
		File file = null;
		String dataField = "";
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

			if(jobObject != null)
			{
				if(jobObject.get(DataConstants.PAYLOAD) != null)
				{
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
							document.add(table);
							document.close();

							if(outputStream != null)
								outputStream.close();

							fileSequence += 1;
							file = new File(_outputConf_.get(DataConstants.FILE_PATH).toString() + File.separator + fileName + "_" + fileSeqFormat.format(fileSequence) + "." + _outputConf_.get(DataConstants.EXTENSION).toString());
							fileList.add(file);

							outputStream = new FileOutputStream(file);
							document = new Document();
							PdfWriter.getInstance(document, outputStream);
							document.open();
							if(_outputConf_.get(DataConstants.REPORT_NAME) != null && !_outputConf_.get(DataConstants.REPORT_NAME).toString().trim().isEmpty())
							{
								paragraph = new Paragraph(_outputConf_.get(DataConstants.REPORT_NAME).toString().trim(), fontBold);
								paragraph.setAlignment(Element.ALIGN_CENTER);
								document.add(paragraph);
								document.add(emptyLine);
							}

							if(preHeaders != null && !preHeaders.trim().isEmpty())
							{
								paragraph = new Paragraph(preHeaders.trim(), fontNormal);
								document.add(paragraph);
								document.add(emptyLine);
							}

							table = new PdfPTable(headers.length);
							table.setWidthPercentage(110);
							for (String column : headers) 
							{
								paragraph = new Paragraph(column, fontBold);
								header = new PdfPCell(paragraph);
								header.setHorizontalAlignment(Element.ALIGN_CENTER);
								header.setBackgroundColor(Color.LIGHT_GRAY);
								table.addCell(header);
							}

							table.setHeaderRows(1);
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
									dataField = dataField + value.toString().trim() + delimiter;
								}
								else
									dataField = value.toString();
							}
							else if(DataConstants.BOOLEAN_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								String tempVal = "";

								if(value != null && value.toString().trim().equalsIgnoreCase("true"))
									tempVal = fieldObject.get(DataConstants.IF).toString();
								else
									tempVal = fieldObject.get(DataConstants.ELSE).toString();
								dataField = dataField + tempVal;
							}
							else if(DataConstants.LONG_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								//								log.error("dataField : " + dataField);
								long tempVal = 0;
								if(dataField != null && !dataField.trim().isEmpty() && !"NaN".equalsIgnoreCase(dataField.trim()) && !"Infinity".equalsIgnoreCase(dataField.trim()))
								{
									tempVal = (Long.parseLong(dataField));
								}
								dataField = dataField + tempVal;
							}
							else if(DataConstants.DOUBLE_TYPE.equalsIgnoreCase(fieldObject.get(DataConstants.TYPE).toString()))
							{
								double tempVal = 0.0;
								if(dataField != null && !dataField.trim().isEmpty() && !"NaN".equalsIgnoreCase(dataField.trim()) && !"Infinity".equalsIgnoreCase(dataField.trim()))
								{
									tempVal = (Double.parseDouble(dataField));
									if(fieldObject.get(DataConstants.NUMBER_OF_DECIMALS) != null 
											&& !fieldObject.get(DataConstants.NUMBER_OF_DECIMALS).toString().trim().isEmpty()
											&& "1".equalsIgnoreCase(fieldObject.get(DataConstants.NUMBER_OF_DECIMALS).toString().trim()))
									{}
								}
								dataField = dataField + tempVal;
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
									dataField = dataField + tempVal;
								}
								else
									dataField = dataField + tempVal;
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
									dataField = dataField + tempVal.toString();
								}
								else
									dataField = dataField + tempVal.toString();
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
										dataField = dataField + format.format(dataObject.get(fieldObject.get(DataConstants.PATH).toString())) + delimiter;
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
											dataField = format.format(fromFormat.parse(value.toString())) + delimiter;
										}
										else
											dataField = value.toString();
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
										dataField = value.toString().trim();
									}
									else
										dataField = value.toString();
								}
							}

							paragraph = new Paragraph(dataField, fontNormal);
							table.addCell(paragraph);
							dataField = "";
						}
						batchRecordCount ++;
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
			paragraph = null;
			header = null;
			file = null;
			dataField = null;
			values = null; 
			values = null; 
			temp = null;
			jsonData = null;
			jsonArr = null;
			LOGGER.debug(_pipeLineName_ + " : Exit process.." + _processorName_);
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
			document.add(table);
			document.close();

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
				if(document != null)
					document.close();
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
			document = null;
			fontBold = null;
			fontNormal = null;
			table = null;
			emptyLine = null;
			zipFileName = null;
			fileName = null;
			delimiter = null;
			preHeaders = null;
			headers = null;
		}
	}
}