package com.enhancesys.interfaces.snoc.services;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.enhancesys.interfaces.snoc.util.PropertiesFileLoaderClass;
import com.enhancesys.interfaces.snoc.util.TokuStandAloneUser;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;

public class InterfaceServicesDownloadProcessor implements Processor
{

	private static Logger LOGGER = Logger.getLogger(InterfaceServicesDownloadProcessor.class);

	@Autowired
	private InterfaceServicesDAO interfaceServicesDAO;
	
	@Autowired
	private InterfaceServicesDeligator interfaceServicesDeligator;
	
	@Override
	public void process(Exchange exchange) throws Exception 
	{
		BasicDBObject requestObj = (BasicDBObject) JSON.parse(exchange.getIn().getBody().toString());
		LOGGER.info("[InterfaceServicesDownloadProcessor - process ] process is started successfully...");
		
		processData(requestObj);
	}
	
	public void processData(BasicDBObject requestObj) throws Exception
	{
		String actualFile = null;
		String gzFileName = null;
		String zipFileName = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		GZIPInputStream gis = null;
		FileInputStream fis = null;
		ZipEntry zipEntry = null;
		File file = null;
		File gzFile = null;
		
		try
		{
		//	String localPath = PropertiesFileLoaderClass.getValueAsString("reportPath");
		
			/*downloadFileFromServer(requestObj.getString(Constants.F_FILE_NAME),requestObj.getString(Constants.F_FILE_PATH),
					localPath,requestObj.getString(Constants.F_FILE_EXTENSION));*/
			actualFile = requestObj.getString(Constants.F_FILE_PATH) + File.separator + requestObj.getString(Constants.F_FILE_NAME)
					+ "." + requestObj.getString(Constants.F_FILE_EXTENSION);
			
			gzFileName = actualFile + ".gz";
			gzFile = new File(gzFileName);
			if(gzFile.exists())
			{
				fis = new FileInputStream(gzFile);
				gis = new GZIPInputStream(fis);
				fos = new FileOutputStream(actualFile);
				int bytes_read;
				byte[] buffer = new byte[1024];
				 
	            while ((bytes_read = gis.read(buffer)) > 0)
	            {
	                fos.write(buffer, 0, bytes_read);
	            }
	            gis.close();
	            fos.close();
			}
			
			file = new File(actualFile);
			
			if(file.exists())
			{
				zipFileName = actualFile.substring(0, actualFile.lastIndexOf(".")) + ".zip";
				
				fos = new FileOutputStream(zipFileName);
				zos = new ZipOutputStream(fos);
				
				file = new File(actualFile);
				fis = new FileInputStream(file);
				
				zipEntry = new ZipEntry(file.getName());
				zos.putNextEntry(zipEntry);
				
				byte[] bytes = new byte[1024];
				int length;
				while ((length = fis.read(bytes)) >= 0) 
				{
					zos.write(bytes, 0, length);
				}
				zos.closeEntry();
				zos.close();
				fis.close();
				if(gzFile.exists())
					file.delete();
				file = new File(zipFileName);

				String objId = pushToGridFS("zip", requestObj.getString(Constants.F_FILE_NAME), file);	
				requestObj.put(Constants.F_FILE_EXTENSION, "zip");
				if(objId.isEmpty())
				{
					updateNotification(null , requestObj, 771L,interfaceServicesDAO);
				}
				else
				{
					updateNotification(objId, requestObj, 173L,interfaceServicesDAO);
					updateChunksReportCollection(objId);
				}
			}
			else
			{
				updateNotification(null , requestObj, 771L,interfaceServicesDAO);
			}
			
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesDownloadProcessor - processData] exception occured : "+e.getMessage());
		}
		finally 
		{
			if(file != null && file.exists())
				file.delete();
			if(zos != null)
				zos.close();
			if(fis != null)
				fis.close();
			actualFile = null;
			zipFileName = null;
			gzFile = null;
			gzFileName = null;
		}
	}
	
	private String pushToGridFS(String fileType, String fileName, File file)
	{
		String objId = "";
		try
		{
				
			DB db = TokuStandAloneUser.adminMongoClient.getDB(Constants.S_SNOC_REPORT);
			GridFS gridFS = new GridFS(db);
			GridFSInputFile gridFSInputFile = gridFS.createFile(file);
			gridFSInputFile.setFilename(fileName);
			gridFSInputFile.setContentType(fileType);
			gridFSInputFile.save();
			objId = gridFSInputFile.getId().toString();
			
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesDownloadProcessor - pushToGridFS] exception occured : "+e.getMessage());
		}
		return objId;
	}
	
	public static void updateNotification(String objId ,BasicDBObject requestObject, long status,InterfaceServicesDAO interfaceServicesDAO)
	{
		try
		{
			LOGGER.info("update notification with status>>>"+status);
			BasicDBObject notificationObj = new BasicDBObject();
			BasicDBObject set = new BasicDBObject();
			notificationObj.put("trans_id", objId==null?"":new ObjectId(objId));
			notificationObj.put("status", status);
			notificationObj.put("file_type", requestObject.containsField(Constants.F_FILE_EXTENSION) ? requestObject.getString(Constants.F_FILE_EXTENSION) : requestObject.getString(Constants.FILE_TYPE));
			notificationObj.put(Constants.F_UPDATED_DATE, new Date());
			set.put("$set", notificationObj);
			interfaceServicesDAO.updateDocumentObject(Constants.S_SNOC, Constants.C_NOTIFICATION, 
					new BasicDBObject(Constants._ID, requestObject.getLong("notification_id")), set);
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesDeligator - updateNotification] exception occured : "+e.getMessage());
			
		}
	}
	
	public void updateChunksReportCollection(String objId)
	{
		try
		{
			DBObject newDocument = new BasicDBObject();
			newDocument.put("$set", new BasicDBObject().append("uploadDate", new Date()));
			BasicDBObject searchQuery = new BasicDBObject().append("files_id", new ObjectId(objId));
			interfaceServicesDAO.updateStandaloneDocumentObject(Constants.S_SNOC_REPORT, "fs.chunks", searchQuery,newDocument);
		}
		catch (Exception e)
		{
			LOGGER.error("[InterfaceServicesDownloadProcessor - updateChunksReportCollection] exception occured : "+e.getMessage());
		}
	}
	
	public Boolean downloadFileFromServer(String fileName,String filePath,String localPath,String fileExt)
	{
		Session session = null;
	    Channel channel = null;
	    ChannelSftp channelSftp = null;
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    JSch jsch = null;
	    Boolean response = false;
	    try
	    {
	    	String host = PropertiesFileLoaderClass.getValueAsString("INTERFACE_HOST");
	    	String userName = PropertiesFileLoaderClass.getValueAsString("INTERFACE_USER");
	    	String password = PropertiesFileLoaderClass.getValueAsString("INTERFACE_PASSWORD");
	    	int portNumber = Integer.valueOf(PropertiesFileLoaderClass.getValueAsString("INTERFACE_PORT"));
	    	
	    	jsch = new JSch();
	    	session = jsch.getSession(userName, host, portNumber);
	    	session.setPassword(password);
	    	
	    	java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
	    	
			session.connect();
	    	channel = session.openChannel("sftp");
	    	channel.connect();
	    	channelSftp = (ChannelSftp) channel;
	    	channelSftp.cd(filePath);
	    	
	    	byte[] buffer = new byte[1024];
			bis = new BufferedInputStream(channelSftp.get(fileName+"."+fileExt));
			File newFile = new File(localPath+fileName+"."+fileExt);
			OutputStream os = new FileOutputStream(newFile);
			bos = new BufferedOutputStream(os);
			int readCount;
			while( (readCount = bis.read(buffer)) > 0)
			{
				bos.write(buffer, 0, readCount);
			}
			if(newFile.exists())
				response = true;
			
	    }
	    catch (SftpException exception)
	    {
	    	response = false;
	    	LOGGER.error("[InterfaceServicesDownloadProcessor - downloadFileFromServer - SftpException] exception occured : "+exception.getMessage());
        }
	    catch (Exception exception) 
	    {
        	response = false;
        	LOGGER.error("[InterfaceServicesDownloadProcessor - downloadFileFromServer - Exception] exception occured : "+exception.getMessage());
		}
	    finally
	    {
			try
			{
				if(bos!=null)
				{
					bos.close();
					bos = null;
				}
				if(bis!=null)
				{
					bis.close();
					bis = null;
				}
				if(channelSftp!=null)
					channelSftp.exit();
				if(channel!=null)
					channel.disconnect();
				if(session!=null)
					session.disconnect();
				jsch = null;
				channel = null;
				channelSftp = null;
				session = null;
				
			}
			catch (Exception exception) 
			{
		    	LOGGER.error("[InterfaceServicesDownloadProcessor - downloadFileFromServer - SftpException] exception occured : "+exception.getMessage());
	        }
		}
	    return response;
	}
	
	@SuppressWarnings("unchecked")
	public void generateGridFSChunksOffline(BasicDBObject reportObj, BasicDBObject requestObj, BasicDBObject reportConfig) throws Exception 
	{
		String objId = null;
		String fileType = null;
		String fileName = null;
		Map<String, String> column = null;
		File file = null;
		BasicDBObject confObject = null;
		try
		{
			confObject = new BasicDBObject();
			fileType = requestObj.containsField("file_type") ? requestObj.getString("file_type") : "xlsx";
			confObject.append("file_type", fileType);
			
			if (reportObj != null && !reportObj.isEmpty()) 
			{
				fileName = reportConfig.getString("report_name");
				fileName = fileName.replace(" ", "_");
				column = (Map<String, String>) JSON.parse(reportConfig.getString("headers"));

				Long ref_id = requestObj.containsField("notification_id")?requestObj.getLong("notification_id"):Calendar.getInstance().getTimeInMillis();
				String reportPath = requestObj.getString("reportPath");
				confObject.append("reportPath", reportPath);
				confObject.append("headers", column);
				confObject.append("reportHeader", reportConfig.getString("report_name").replace("_", " "));
				confObject.append("fileName", reportConfig.getString("report_name"));
				
				List<BasicDBObject> dataArray = (List<BasicDBObject>) reportObj.get("data");
				
				if(!dataArray.isEmpty())
				{
					if ("pdf".equalsIgnoreCase(fileType))
					{
						file = new File(reportPath + fileName + ref_id +".pdf");
						exportPDF(file, dataArray, confObject, column);
					} 
					else if ("csv".equalsIgnoreCase(fileType)) 
					{
						file = new File(reportPath + fileName + ref_id +".csv");
//						File file2 = new File(reportPath + fileName + ref_id +".xlsx");
						exportCsv(file, dataArray, confObject, column);
//						file2.delete();
					} 
					else 
					{
						file = new File(reportPath + fileName + ref_id +".xlsx");
						exportExcel(file, dataArray, confObject, column);
					}
				}
				
			} 
			else 
			{
//				reportServicesDAO.rollbackTransaction(Constants.S_REPORTS);
				throw new Exception("Invalid Request Object..!");
			}
			if(file != null)
			{
				objId = pushToGridFS(fileType, fileName, file);
				file.delete();
			}
//			reportServicesDAO.commitTransaction(Constants.S_REPORTS);
			updateChunksReportCollection(objId);
			updateNotification(objId, requestObj, 173L,interfaceServicesDAO);
		} 
		catch (NullPointerException nullPointerException) 
		{
			LOGGER.error("generateGridFSChunksOffline:: Null Pointer Exception Message is: >> "
					+ nullPointerException.getMessage(), nullPointerException);

//			reportServicesDAO.rollbackTransaction(Constants.S_REPORTS);
			if (requestObj.containsField("notification_id"))
			{
				updateNotification(null, requestObj, 771L,interfaceServicesDAO);
			}
		}
		catch (Exception exception) 
		{
			LOGGER.error("generateGridFSChunksOffline:: Exception Message is: >> " + exception.getMessage(), exception);

//			reportServicesDAO.rollbackTransaction(Constants.S_REPORTS);
			if (requestObj.containsField("notification_id")) 
			{
				updateNotification(null, requestObj, 771L,interfaceServicesDAO);
			}
		}
		finally
		{
			fileName = null;
			column = null;
			file = null;
		}
	}
	
	public static File exportPDF(File file, List<BasicDBObject> responseArr, BasicDBObject confObject, Map<String, String> columnCaption) 
	{
		Document document = new Document();
		Font font1 = null;
		Font font2 = null;
		try 
		{
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
			
			font1 = FontFactory.getFont(
						(confObject.getString("reportPath") + "/fonts/TIMES.TTF"), "Identity-H",
							BaseFont.EMBEDDED, 5, Font.BOLD, Color.WHITE);
			font2 = FontFactory.getFont(
						(confObject.getString("reportPath") + "/fonts/TIMES.TTF"), "Identity-H",
							BaseFont.EMBEDDED, 5);
			Paragraph headerName = new Paragraph(confObject.getString("reportHeader"), font2);
			headerName.setAlignment(Element.ALIGN_CENTER);
			document.add(headerName);
			document.add(new Paragraph(" "));
			
			Set<String> key = columnCaption.keySet();
			
			JSONArray columnKey = new JSONArray(key.toString());
			PdfPTable table = new PdfPTable(key.size());
			table.setWidthPercentage(110);
			PdfPCell header;
			for (int i = 0; i < columnKey.length(); i++)
			{
				header = new PdfPCell(new Paragraph(columnKey.getString(i), font1));
				header.setHorizontalAlignment(Element.ALIGN_CENTER);
				header.setBackgroundColor(Color.GRAY);
				table.addCell(header);
			}
			table.setHeaderRows(1);
			BasicDBObject data;
			Paragraph contents = null;
			for (int j = 0; j < responseArr.size(); j++)
			{
				data = responseArr.get(j);
				for (Map.Entry<String, String> dataEntry : columnCaption.entrySet()) 
				{
					if (data.containsField(dataEntry.getValue())) 
					{
						contents = new Paragraph(data.getString(dataEntry.getValue()), font2);
						table.addCell(contents);
					} 
					else 
					{
						table.addCell(new Paragraph("", font2));
					}
				}
			}
			document.add(table);

			LOGGER.info(file.getAbsolutePath());
			document.close();
		} 
		catch (DocumentException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportPDF " + e.getMessage(), e);
		} 
		catch (FileNotFoundException e)
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportPDF " + e.getMessage(), e);
		} 
		catch (JSONException e)
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportPDF " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportPDF " + e.getMessage(), e);
		}
		return file;

	}
	
	
	public static void exportCsv(File filecsv, File file,  List<BasicDBObject> responseArr, BasicDBObject confObject, Map<String, String> columnCaption) throws Exception 
	{
		SXSSFWorkbook wb = null;
		Sheet sheet = null;
		Set<String> key = null;
		JSONArray columnKey = null;
		Row header = null;
		BasicDBObject data = null;
		Row dataRow = null;
		Long date = null;
		int start = 0;
		FileOutputStream out = null;
		try 
		{
			wb = new SXSSFWorkbook(100); // keep 500 rows in memory, exceeding
											// rows will be flushed to disk
			sheet = wb.createSheet(confObject.getString("fileName"));

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("#,##0.00"));
			
			key = columnCaption.keySet();
			columnKey = new JSONArray(key.toString());
			
			Row top = sheet.createRow(start);
			for (int z = 0; z < columnKey.length(); z++) 
			{
				top.createCell(z);
			}
			CellRangeAddress cellRangeAddress = new CellRangeAddress(start, start, 0, columnKey.length() - 1);
			sheet.addMergedRegion(cellRangeAddress);
			Cell headerCell = CellUtil.createCell(top, 0, confObject.getString("reportHeader"));
			CellStyle style = wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font = wb.createFont();
			font.setBold(true);
			style.setFont(font);
			headerCell.setCellStyle(style);
			CellUtil.setAlignment(headerCell, wb, CellStyle.ALIGN_CENTER);
			start++;
			
			header = sheet.createRow(start + 2);
			for (int i = 0; i < columnKey.length(); i++)
			{
				header.createCell(i).setCellValue(columnKey.getString(i).toUpperCase());
				sheet.autoSizeColumn(i);
			}
			for (int j = 0; j < responseArr.size(); j++) 
			{
				dataRow = sheet.createRow(start + j + 3);
				data = responseArr.get(j);
				int i = 0;
				for (Map.Entry<String, String> dataEntry : columnCaption.entrySet()) 
				{
					if (data.containsField(dataEntry.getValue())) 
					{
						if ((data.get(dataEntry.getValue())) instanceof Double) 
						{
							Cell currentCell = dataRow.createCell(i);
							currentCell.setCellValue(
									Double.parseDouble(data.getString(dataEntry.getValue())));
							currentCell.setCellStyle(cellStyle);
						}
						else 
						{
							dataRow.createCell(i).setCellValue(data.getString(dataEntry.getValue()));
						}
					} 
					else 
					{
						dataRow.createCell(i).setCellValue("");
					}
					i++;
				}
			}
			
			date = Calendar.getInstance().getTimeInMillis();
			if (file == null) 
			{
				file = new File(confObject.getString("file_name").replace(" ", "_") + date + "/"
						+ confObject.getString("file_name").replace(" ", "_") + ".xlsx");
			}
			
			out = new FileOutputStream(file);
			wb.write(out);
			wb.dispose();
			wb.close();
			out.flush();
			out.close();

			Row row = null;
			int lastRowNum = 0;
			ArrayList<ArrayList<String>> csvData = null;
			int maxRowWidth = 0;
			DataFormatter formatter = null;
			FormulaEvaluator evaluator = null;

			Workbook wb2 = WorkbookFactory.create(file);
			Sheet mySheet = wb2.getSheetAt(0);
			csvData = new ArrayList<ArrayList<String>>();
			formatter = new DataFormatter(true);
			lastRowNum = mySheet.getLastRowNum();
			evaluator = wb2.getCreationHelper().createFormulaEvaluator();
			if (mySheet.getPhysicalNumberOfRows() > 0) 
			{
				for (int j = 0; j <= lastRowNum; j++)
				{
					row = mySheet.getRow(j);
					if (row != null)
					{
						Cell cell = null;
						int lastCellNum = 0;
						ArrayList<String> csvLine = new ArrayList<String>();
						lastCellNum = row.getLastCellNum();
						for (int i = 0; i <= lastCellNum; i++) {
							
							cell = row.getCell(i);
							if (cell == null) 
							{
								csvLine.add("");
							}
							else 
							{
								if (cell.getCellType() != Cell.CELL_TYPE_FORMULA) 
								{
									csvLine.add(formatter.formatCellValue(cell));
								}
								else
								{
									csvLine.add(formatter.formatCellValue(cell, evaluator));
								}
							}
						}
						if (lastCellNum > maxRowWidth)
						{
							maxRowWidth = lastCellNum;
						}
						csvData.add(csvLine);
					}

				}
			}

			if (filecsv == null) 
			{
				file = new File(confObject.getString("file_name").replace(" ", "_") + date + "/"
						+ confObject.getString("file_name").replace(" ", "_") + ".csv");
			}
			
			FileWriter fw = null;
			BufferedWriter bw = null;
			fw = new FileWriter(filecsv);
			bw = new BufferedWriter(fw);
			ArrayList<String> line = null;
			StringBuffer buffer = null;
			String csvLineElement = null;
			for (int i = 0; i < csvData.size(); i++) 
			{
				buffer = new StringBuffer();
				line = csvData.get(i);
				for (int j = 0; j < maxRowWidth; j++)
				{
					if (line.size() > j)
					{
						csvLineElement = line.get(j);
						if (csvLineElement != null) 
						{
							buffer.append(escapeEmbeddedCharacters(csvLineElement));
						}
					}
					if (j < (maxRowWidth - 1)) 
					{
						buffer.append(PropertiesFileLoaderClass.getValueAsString("delimeter"));
					}
				}

				bw.write(buffer.toString().trim());
				
				if (i < (csvData.size() - 1))
					bw.newLine();
			}
			bw.flush();
			bw.close();
		} 
		catch (FileNotFoundException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		catch (IOException e)
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		catch (JSONException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		finally 
		{
			if (wb != null) 
			{
				wb.dispose();
				wb.close();
				wb = null;
			}
			if (out != null)
				out.close();
			wb = null;
			sheet = null;
			key = null;
			columnKey = null;
			header = null;
			data = null;
			dataRow = null;
			date = null;
			out = null;
		}
	}
	
	public static void exportCsv(File filecsv, List<BasicDBObject> responseArr, BasicDBObject confObject, Map<String, String> columnCaption) throws Exception 
	{
		Set<String> key = null;
		JSONArray columnKey = null;
		BasicDBObject data = null;
		FileOutputStream out = null;
		try 
		{
			FileWriter fw = null;
			BufferedWriter bw = null;
			fw = new FileWriter(filecsv);
			bw = new BufferedWriter(fw);
			StringBuffer buffer = null;
			
			key = columnCaption.keySet();
			columnKey = new JSONArray(key.toString());
			
			buffer = new StringBuffer();
			buffer.append(confObject.getString("reportHeader"));
			bw.write(buffer.toString().trim());
			bw.newLine();
			
			buffer = new StringBuffer();
			for (int i = 0; i < columnKey.length(); i++) 
			{
				buffer.append(columnKey.getString(i).toUpperCase());
				if(i < columnKey.length() -1)
					buffer.append(PropertiesFileLoaderClass.getValueAsString("reportsDelimeter"));
			}
			bw.write(buffer.toString().trim());
			bw.newLine();

			for (int j = 0; j < responseArr.size(); j++) 
			{
				buffer = new StringBuffer();
				data = responseArr.get(j);
				int i = 0;
				for (Map.Entry<String, String> dataEntry : columnCaption.entrySet()) 
				{
					if (data.containsField(dataEntry.getValue())) 
					{
						buffer.append(data.getString(dataEntry.getValue()));
					} 
					else 
					{
						buffer.append("");
					}
					
					if(i < columnKey.length() -1)
						buffer.append(PropertiesFileLoaderClass.getValueAsString("reportsDelimeter"));
					i++;
				}
				bw.write(buffer.toString().trim());
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
		} 
		catch (FileNotFoundException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		catch (IOException e)
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		catch (JSONException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportCsv " + e.getMessage(), e);
		} 
		finally 
		{
			if (out != null)
				out.close();
			key = null;
			columnKey = null;
			data = null;
			out = null;
		}
	}
	
	public static void exportExcel(File file, List<BasicDBObject> responseArr, BasicDBObject confObject, Map<String, String> columnCaption) throws Exception
	{
		SXSSFWorkbook wb = null;
		Sheet sheet = null;
		Set<String> key = null;
		JSONArray columnKey = null;
		Row header = null;
		BasicDBObject data = null;
		Row dataRow = null;
		int start = 0;
		FileOutputStream out = null;
		try 
		{
			wb = new SXSSFWorkbook(100); // keep 500 rows in memory, exceeding
											// rows will be flushed to disk
			sheet = wb.createSheet(confObject.getString("fileName"));

			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("#,##0.00"));
			
			key = columnCaption.keySet();
			columnKey = new JSONArray(key.toString());
			
			Row top = sheet.createRow(start);
			for (int z = 0; z < columnKey.length(); z++) 
			{
				top.createCell(z);
			}
			CellRangeAddress cellRangeAddress = new CellRangeAddress(start, start, 0, columnKey.length() - 1);
			sheet.addMergedRegion(cellRangeAddress);
			Cell cell = CellUtil.createCell(top, 0, confObject.getString("reportHeader"));
			CellStyle style = wb.createCellStyle();
			org.apache.poi.ss.usermodel.Font font = wb.createFont();
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			CellUtil.setAlignment(cell, wb, CellStyle.ALIGN_CENTER);
			start++;
			
			header = sheet.createRow(start + 1);
			for (int i = 0; i < columnKey.length(); i++)
			{
				header.createCell(i).setCellValue(columnKey.getString(i).toUpperCase());
				sheet.autoSizeColumn(i);
			}
			for (int j = 0; j < responseArr.size(); j++) 
			{
				dataRow = sheet.createRow(start + j + 3);
				data = responseArr.get(j);
				int i = 0;
				for (Map.Entry<String, String> dataEntry : columnCaption.entrySet()) 
				{
					if (data.containsField(dataEntry.getValue())) 
					{
						if ((data.get(dataEntry.getValue())) instanceof Double) 
						{
							Cell currentCell = dataRow.createCell(i);
							currentCell.setCellValue(
									Double.parseDouble(data.getString(dataEntry.getValue())));
							currentCell.setCellStyle(cellStyle);
						}
						else 
						{
							dataRow.createCell(i).setCellValue(data.getString(dataEntry.getValue()));
						}
					} 
					else 
					{
						dataRow.createCell(i).setCellValue("");
					}
					i++;
				}
			}
			
			
			out = new FileOutputStream(file);
			wb.write(out);
			wb.dispose();
			wb.close();
			out.flush();
			out.close();
		} 
		catch (FileNotFoundException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportExcel " + e.getMessage(),e);
		} 
		catch (IOException e)
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportExcel " + e.getMessage(),e);
		} 
		catch (JSONException e) 
		{
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportExcel " + e.getMessage(),e);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			LOGGER.error("Exception occured in :[" + LOGGER.getClass().getName() + "] function: exportExcel " + e.getMessage(),e);
		} 
		finally 
		{
			if (wb != null)
			{
				wb.dispose();
				wb.close();
				wb = null;
			}
			if (out != null) 
				out.close();
			wb = null;
			sheet = null;
			key = null;
			columnKey = null;
			header = null;
			data = null;
			dataRow = null;
			out = null;
		}
	}
	
	public static String escapeEmbeddedCharacters(String field) throws Exception 
	{
		StringBuffer buffer = null;
		if (field.contains("\""))
		{
			buffer = new StringBuffer(field.replaceAll("\"", "\\\"\\\""));
			buffer.insert(0, "\"");
			buffer.append("\"");
		} 
		else
		{
			buffer = new StringBuffer(field);
			if ((buffer.indexOf(PropertiesFileLoaderClass.getValueAsString("delimeter"))) > -1
					|| (buffer.indexOf("\n")) > -1) 
			{
				buffer.insert(0, "\"");
				buffer.append("\"");
			}
		}
		return (buffer.toString().trim());
	}
}
