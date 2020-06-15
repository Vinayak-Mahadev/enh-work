package enh.team.interfaces.soapservice;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.camel.util.FileUtil;


public class PrepareCTL
{


	public static void main(String[] args)
			throws Exception
	{
	/*  PrepareCTL prepareCTL = new PrepareCTL();
		String inputFileFolder = "E:\\interface\\backend\\ControlFileGeneration\\";
		String outputFileFolder = "E:\\interface\\backend\\ControlFileGeneration\\raw\\";
		String moveOrgnlFiles = "E:\\interface\\backend\\ControlFileGeneration\\bkp\\";
		prepareCTL.prepareCTL(inputFileFolder, outputFileFolder, moveOrgnlFiles);
	*/
	}

	public  void prepareCTL( String inputFilesFolder, String outputFilesFolder, String moveOrgnlFilesFolder) throws Exception
	{
		SimpleDateFormat dateFormat = null;
		MessageDigest md = null;
		File folder = null;
		String fileNameWOformat = null;
		BufferedReader reader = null;
		FileInputStream fis = null;
		StringBuffer sb = null;
		String record = null;
		File ctlFile = null;
		FileOutputStream fileOutputStream = null;
		String file = null;
		try
		{
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			md = MessageDigest.getInstance("MD5");
			folder = new File(inputFilesFolder);
			for(File csvFile : folder.listFiles()) 
			{

				if ((!csvFile.isDirectory()) && (csvFile.getName().endsWith(".csv")))
				{
					fileNameWOformat = csvFile.getName().substring(0, csvFile.getName().lastIndexOf("."));
					//System.out.println("File WO-->" + fileNameWOformat);
					reader = new BufferedReader(new FileReader(csvFile));
					int lines = 0;
					while (reader.readLine() != null)
						lines++;
					reader.close();

					//System.out.println("last modified-->" + dateFormat.format(new Date(csvFile.lastModified())));
					//System.out.println("records-->" + lines);
					//System.out.println("length-->" + csvFile.length());
					//System.out.println("fileName-->" + csvFile.getName());
					fis = new FileInputStream(csvFile);

					byte[] dataBytes = new byte[1024];
					int nread = 0;
					while ((nread = fis.read(dataBytes)) != -1) {
						md.update(dataBytes, 0, nread);
					}
					byte[] mdbytes = md.digest();
					sb = new StringBuffer();
					for (int i = 0; i < mdbytes.length; i++) {
						sb.append(Integer.toString((mdbytes[i] & 0xFF) + 256, 16).substring(1));
					}
					//System.out.println("Digest(in hex format):: " + sb.toString());
					record = dateFormat.format(new Date(csvFile.lastModified())) + "\t" + lines + "\t" + csvFile.length() + "\t" + sb.toString() + "\t" + csvFile.getName();
					ctlFile = new File(inputFilesFolder + fileNameWOformat + ".ctl");
					fileOutputStream = new FileOutputStream(ctlFile, false);
					fileOutputStream.write(record.getBytes());
					fileOutputStream.close();
					file = inputFilesFolder + csvFile.getName();
					compressGzipFile(file, outputFilesFolder + csvFile.getName() + ".gz");
					compressGzipFile(inputFilesFolder + ctlFile.getName(), outputFilesFolder + ctlFile.getName() + ".gz");

					if(moveOrgnlFilesFolder != null && !moveOrgnlFilesFolder.trim().isEmpty()) {
						FileUtil.copyFile(csvFile, new File(moveOrgnlFilesFolder + csvFile.getName()));
						FileUtil.copyFile(ctlFile, new File(moveOrgnlFilesFolder + ctlFile.getName()));

						if(reader != null)
							reader.close();
						if(fis != null)
							fis.close();
						if(fileOutputStream != null)
							fileOutputStream.close();

						csvFile.delete();					
						ctlFile.delete();
					}
				}

			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if(reader != null)
				reader.close();
			if(fis != null)
				fis.close();
			if(fileOutputStream != null)
				fileOutputStream.close();
			dateFormat = null;
			reader = null;
			md = null;
			folder = null;
			fileNameWOformat = null;
			fis = null;
			sb = null;
			record = null;
			ctlFile = null;
			fileOutputStream = null;
			file = null;
		}

	}

	protected  void decompressGzipFile(String gzipFile, String newFile)
	{
		FileInputStream fis = null;
		GZIPInputStream gis = null;
		FileOutputStream fos = null;

		try
		{
			fis = new FileInputStream(gzipFile);
			gis = new GZIPInputStream(fis);
			fos = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = gis.read(buffer)) != -1)
			{
				fos.write(buffer, 0, len);
			}
			fos.close();
			gis.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			fis = null;
			gis = null;
			fos = null;	
		}
	}

	protected  void compressGzipFile(String file, String gzipFile) throws IOException
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		GZIPOutputStream gzipOS = null;

		try 
		{
			fis = new FileInputStream(file);
			fos = new FileOutputStream(gzipFile);
			gzipOS = new GZIPOutputStream(fos);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) != -1)
			{
				gzipOS.write(buffer, 0, len);
			}
			gzipOS.close();
			fos.close();
			fis.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
				fis.close();
			if(gzipOS != null)
				gzipOS.close();
			if(fos != null)
				fos.close();

			fis = null;
			fos = null;
			gzipOS = null;

		}
	}

	protected  String byteToString(byte[] requestData)
			throws Exception
	{
		StringBuffer responseData = null;
		try
		{
			System.out.println("byteToString service received requestData-->" + requestData);
			responseData = new StringBuffer();

			if (requestData.length > 0)
			{
				byte[] arrayOfByte = requestData; int j = requestData.length; for (int i = 0; i < j; i++) { byte data = arrayOfByte[i];

				responseData.append((char)data);
				}
			}
			return responseData.toString();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			responseData = null;
		}
	}

	public  String format(Calendar calendar)
	{
		if (calendar == null) {
			return null;
		}
		int year = calendar.get(1) - 2000;
		int month = calendar.get(2) + 1;
		int day = calendar.get(5);
		int hour = calendar.get(11);
		int minute = calendar.get(12);
		int second = calendar.get(13);
		int tenthsOfSecond = calendar.get(14) / 100;
		System.out.println("calendar.getTimeZone()-->" + calendar.getTimeZone());
		int rawOffset = calendar.getTimeZone().getRawOffset();
		char sign;
		if (rawOffset > 0)
			sign = '+';
		else {
			sign = '-';
		}

		int timeDiff = Math.abs(rawOffset) / 900000;
		return format(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day), Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second), tenthsOfSecond, timeDiff, Character.valueOf(sign));
	}

	public  final String format(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second, int tenthsOfSecond, int timeDiff, Character sign) {
		Object[] args = { year, month, day, hour, minute, second, Integer.valueOf(tenthsOfSecond), Integer.valueOf(timeDiff), sign };
		return MessageFormat.format("{0,number,00}{1,number,00}{2,number,00}{3,number,00}{4,number,00}{5,number,00}{6,number,0}{7,number,00}{8}", args);
	}
}