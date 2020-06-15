package com.enhancesys.interfaces.snoc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.enhancesys.interfaces.snoc.common.InterfaceUtility;
import com.enhancesys.interfaces.snoc.util.PropertiesFileLoaderClass;
import com.enhancesys.interfaces.snoc.util.TokuStandAloneUser;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.mongodb.util.JSON;

public class InterfaceServicesUploadProcessor implements Processor
{

	private static Logger LOGGER = Logger.getLogger(InterfaceServicesUploadProcessor.class);

	@Autowired
	private InterfaceServicesDAO interfaceServicesDAO;
	
	@Autowired
	private InterfaceServicesDeligator interfaceServicesDeligator;
	
	@Autowired
	private InterfaceUtility interfaceUtility;
	
	@Override
	public void process(Exchange exchange) throws Exception 
	{
		BasicDBObject requestObj = (BasicDBObject) JSON.parse(exchange.getIn().getBody().toString());
		LOGGER.info("[InterfaceServicesUploadProcessor - process ] process is started successfully...");
		
		processData(requestObj);
	}

	public void processData(BasicDBObject requestObj)
	{
		try
		{
			BasicDBObject attrData = interfaceServicesDeligator.getInterfaceAttrByInterfaceId(Long.parseLong(requestObj.get(Constants.F_INTERFACE_ID).toString()));
			
			if(attrData == null)
				InterfaceServicesDownloadProcessor.updateNotification(null, requestObj, 771L, interfaceServicesDAO);
			else
			{
				String objId = pushToGridFS(requestObj, requestObj.getString(Constants.F_FILE_NAME));
				
				if(!objId.isEmpty())
				{
					Boolean response = createNewFile(objId, attrData, requestObj.getString(Constants.F_FILE_NAME), requestObj.getString(Constants.F_FILE_EXTENSION));
					if(response)
					{
						updateChunksReportCollection(objId);
						InterfaceServicesDownloadProcessor.updateNotification(objId, requestObj, 173L,interfaceServicesDAO);
					}
					else
						InterfaceServicesDownloadProcessor.updateNotification(null , requestObj, 771L,interfaceServicesDAO);
				}
				else
					InterfaceServicesDownloadProcessor.updateNotification(null , requestObj, 771L,interfaceServicesDAO);
			}
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesUploadProcessor - processData] exception occured : "+e.getMessage());
			try 
			{
				InterfaceServicesDownloadProcessor.updateNotification(null , requestObj, 771L,interfaceServicesDAO);
			}
			catch (Exception exception)
			{
				LOGGER.error("[InterfaceServicesUploadProcessor - processData] exception occured : "+exception.getMessage());
			}
		}	
	}
	
	@SuppressWarnings("static-access")
	private String pushToGridFS(BasicDBObject requestObj, String fileName)
	{
		String objId = "";
		try
		{
			DB db = TokuStandAloneUser.adminMongoClient.getDB(Constants.S_SNOC_REPORT);
			GridFS gridFS = new GridFS(db);
			GridFSInputFile gridFSInputFile = gridFS.createFile(interfaceUtility.decodeToBytes(requestObj.getString(Constants.F_FILE_DATA)));
			gridFSInputFile.setFilename(fileName);
			gridFSInputFile.save();
			objId = gridFSInputFile.getId().toString();
		}
		catch (Exception e)
		{
			LOGGER.error("[InterfaceServicesUploadProcessor - pushToGridFS] exception occured : "+e.getMessage());
			try
			{
				InterfaceServicesDownloadProcessor.updateNotification(null , requestObj, 771L,interfaceServicesDAO);
			} 
			catch (Exception exception)
			{
				LOGGER.error("[InterfaceServicesUploadProcessor - pushToGridFS] exception occured : "+exception.getMessage());
			}
		}
		return objId;
	}
	
	public Boolean createNewFile(String fileId, BasicDBObject attrData, String fileName, String fileExtention)
	{
		Boolean response = false;
		File file = null;
		File csvGzFile = null;
		File ctlGzFile = null;
		ZipFile zipFile = null;
		InputStream stream = null;
		try
		{
			String localPath = PropertiesFileLoaderClass.getValueAsString("reportPath");
			String fullFileName = localPath + File.separator + fileName + "." + fileExtention;
			DB db = TokuStandAloneUser.adminMongoClient.getDB(Constants.S_SNOC_REPORT);
			file = new File(fullFileName);
			GridFS gfsPhoto = new GridFS(db);
			GridFSDBFile imageForOutput = gfsPhoto.findOne(new ObjectId(fileId));
			imageForOutput.writeTo(file);
			imageForOutput.save();
			if(fileExtention.equalsIgnoreCase("zip"))
			{
				zipFile = new ZipFile(fullFileName);
				if(zipFile.size() > 1)
				{
					response = false;
					return response;
				}
			    Enumeration<? extends ZipEntry> entries = zipFile.entries();
			    ZipEntry entry = entries.nextElement();
			    if(!entry.getName().equals(fileName + ".csv"))
			    {
			    	response = false;
					return response;
			    }
			    stream = zipFile.getInputStream(entry);
			    extractEntry(stream, localPath + entry.getName());
			    file.delete();
			    fullFileName = localPath + File.separator + fileName + ".csv";
			    fileExtention = "csv";
			    file = new File(localPath + entry.getName());
			    stream.close();
			    zipFile.close();
			}
			Boolean flag = compressGzipFile(file.getAbsolutePath(), file.getAbsolutePath()+".gz");
			
			if(flag)
			{
				csvGzFile = new File(file.getAbsolutePath()+".gz");
				
				String ctlResponse = createControlFile(fileName,fullFileName,localPath,fileExtention,attrData);
				
				if(!ctlResponse.isEmpty())
				{	
					ctlGzFile = new File(ctlResponse);
					
					flag = uploadFileToServer(csvGzFile, attrData);
					csvGzFile.delete();
					if(flag)
					{
						flag = uploadFileToServer(ctlGzFile, attrData);
						if(flag)
						{
							response = true;
							ctlGzFile.delete();
						}
					}
					else
						response = false;
				}	
			}
		}
		catch (Exception e)
		{
			response = false;
			LOGGER.error("[InterfaceServicesUploadProcessor - createNewFile] exception occured : "+e.getMessage());
		}
		finally
		{
			if(file != null && file.exists())
				file.delete();
			if(csvGzFile != null && csvGzFile.exists())
			{
				csvGzFile.delete();
				csvGzFile = null;
			}
			if(ctlGzFile != null && ctlGzFile.exists())
			{
				ctlGzFile.delete();
				ctlGzFile = null;
			}
			if(zipFile != null)
			{
				try 
				{
					zipFile.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
				
		}
		return response;
	}
	
	private static void extractEntry(InputStream is, String extractedFile) throws IOException 
	{ 
		FileOutputStream fos = null;
		try 
		{ 
			fos = new FileOutputStream(extractedFile);
			final byte[] buf = new byte[1024];
			int length;
			while ((length = is.read(buf, 0, buf.length)) >= 0)
			{ 
				fos.write(buf, 0, length);
			} 
		}
		catch (IOException ioex)
		{
			if(fos != null)
				fos.close();
			LOGGER.error("[InterfaceServicesUploadProcessor - extractEntry] exception occured : " + ioex.getMessage());
		}
		finally
		{
			if(fos != null)
				fos.close();
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
			LOGGER.error("[InterfaceServicesUploadProcessor - updateChunksReportCollection] exception occured : "+e.getMessage());
		}
	}
	
	public String createControlFile(String fileName,String fullFileName,String localPath,String fileExtention,BasicDBObject attrData)
	{
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		FileInputStream fis = null;
		File controlFile = null;
		String response = "";
		try
		{
			File file = new File(fullFileName);
			
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			fis = new FileInputStream(file);

			byte[] dataBytes = new byte[1024];

			int nread = 0;
			while ((nread = fis.read(dataBytes)) != -1) 
			{
				md.update(dataBytes, 0, nread);
			}

			byte[] mdbytes = md.digest();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < mdbytes.length; i++) 
			{
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}			
			
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String delimeter = PropertiesFileLoaderClass.getValueAsString("CONTROL_FILE_DELIMETER");
			String ctrlFileFinalName = localPath + File.separator +fileName +"." + attrData.getString("Control file format");
			controlFile = new File(ctrlFileFinalName);
			fileOutputStream = new FileOutputStream(controlFile, false);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			String data = format1.format(new Date())+delimeter+countLines(file)+delimeter+file.length()+delimeter+sb.toString()+delimeter+fileName+"."+fileExtention;
			outputStreamWriter.write(data);
			outputStreamWriter.close();
			Boolean flag = compressGzipFile(controlFile.getAbsolutePath(),controlFile.getAbsolutePath()+".gz");
			if(flag)
				response =  controlFile.getAbsolutePath()+".gz";
			controlFile.delete();
			
		}
		catch (Exception e)
		{
			LOGGER.error("[InterfaceServicesUploadProcessor - createControlFile - Exception] exception occured : "+e.getMessage());
		}
		finally
		{
			try
			{
				if(controlFile!=null)
					controlFile.delete();
				if(outputStreamWriter != null)
					outputStreamWriter.close();
				if(fileOutputStream != null)
					fileOutputStream.close();
				if(fis != null)
					fis.close();
			} 
			catch (IOException ioException) 
			{
				LOGGER.error("[InterfaceServicesUploadProcessor - createControlFile - IOException] exception occured : "+ioException.getMessage());
			}
		}
		return response;
	}
	
	public int countLines(File aFile) throws IOException 
	{
		LineNumberReader reader = null;
		try 
		{
			reader = new LineNumberReader(new FileReader(aFile));
			while (reader.readLine() != null)
				continue;
			return reader.getLineNumber();
		} 
		catch (Exception ex) 
		{
			return -1;
		} 
		finally
		{ 
			if(reader != null) 
				reader.close();
		}
	}
	
	private Boolean compressGzipFile(String file, String gzipFile) 
	{
		FileInputStream fis = null;
		GZIPOutputStream gzipOS = null;
		FileOutputStream fos = null;
		byte[] buffer = null;
		Boolean response = false;
		try
		{
			LOGGER.info("[InterfaceServicesUploadProcessor - compressGzipFile] preparing GZ file...");
			fis = new FileInputStream(file);
			fos = new FileOutputStream(gzipFile);
			gzipOS = new GZIPOutputStream(fos);
			buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer)) != -1)
			{
				gzipOS.write(buffer, 0, len);
			}
			response = true;
			
		}
		catch (Exception e) 
		{
			LOGGER.error("[InterfaceServicesUploadProcessor - compressGzipFile - Exception] exception occured : "+e.getMessage());
		}
		finally
		{
			buffer = null;
			try 
			{
				if(gzipOS != null)
					gzipOS.close();
				if(fos != null)
					fos.close();
				if(fis != null)
					fis.close();
			}
			catch (IOException ioException)
			{
				LOGGER.error("[InterfaceServicesUploadProcessor - compressGzipFile - IOException] exception occured : "+ioException.getMessage());
			}
		}
		return response;
	}

	public Boolean uploadFileToServer(File file,BasicDBObject attrData){
		Session session = null;
	    Channel channel = null;
	    ChannelSftp channelSftp = null;
	    JSch jsch = null;
	    Boolean response = true;
	    FileInputStream fis = null;
		String passPath = null;	    
	    try{
	    	String host = attrData.getString("Remote Host");
	    	String userName = attrData.getString("Remote User");
	    	String password = attrData.getString("Remote Password");
	    	int portNumber = Integer.parseInt(attrData.get("Remote Port").toString());
	    	String filePath = attrData.getString("Remote Dir");
			passPath = attrData.getString("PPK Path");
	    	jsch = new JSch();
			if(passPath != null && !passPath.trim().isEmpty())
				jsch.addIdentity(passPath);
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
	    	fis = new FileInputStream(file);
	    	channelSftp.put(fis, file.getName());
	    	fis.close();
			
	    }
	    catch (SftpException exception) 
	    {
	    	response = false;
	    	LOGGER.error("[InterfaceServicesUploadProcessor - uploadFileToServer - SftpException] exception occured : "+exception.getMessage());
        }
	    catch (Exception exception)
	    {
        	response = false;
        	LOGGER.error("[InterfaceServicesUploadProcessor - uploadFileToServer - Exception] exception occured : "+exception.getMessage());
		}
	    finally
	    {
			try
			{
				if(channelSftp!=null)
					channelSftp.exit();
				if(channel!=null)
					channel.disconnect();
				if(session!=null)
					session.disconnect();
				fis = null;
				jsch = null;
				channel = null;
				channelSftp = null;
				session = null;
				passPath = null;
			}
			catch (Exception exception)
			{
		    	LOGGER.error("[InterfaceServicesUploadProcessor - uploadFileToServer - SftpException] exception occured : "+exception.getMessage());
	        }
		}
	    return response;
	}
	
}
