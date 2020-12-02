package enh.team.interfaces.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.simple.JSONObject;

public class Utility 
{
	/**
	 * @param args
	 */
	public static Date getStartOfTheDay(Object  object) throws Exception 
	{
		//System.out.println("getStartOfTheDay:: Parsing Object : "+object);
		Date date = null;

		try
		{
			if(object!=null && !object.toString().equals(""))
			{
				DateTime dateTime  = new DateTime(object);
				dateTime=dateTime.withHourOfDay(0);
				dateTime=dateTime.withMinuteOfHour(0);
				dateTime=dateTime.withSecondOfMinute(0);
				dateTime=dateTime.withMillisOfSecond(0);
				date = new DateTime(dateTime, DateTimeZone.UTC).toDate();
			}
		}
		catch(Exception exception)
		{
			System.err.println("getStartOfTheDay:: Exception is: >>"+exception.getMessage());
			throw exception;
		}
		//System.out.println("getStartOfTheDay:: Returning Object : "+date);
		return date;
	}

	public static Date getEndOfTheDay(Object  object) throws Exception 
	{
		//System.out.println("getEndOfTheDay:: Parsing Object : "+object);
		Date date = null;

		try
		{
			if(object!=null && !object.toString().equals(""))
			{
				DateTime dateTime  = new DateTime(object);
				dateTime=dateTime.withHourOfDay(23);
				dateTime=dateTime.withMinuteOfHour(59);
				dateTime=dateTime.withSecondOfMinute(59);
				dateTime=dateTime.withMillisOfSecond(999);
				date = new DateTime(dateTime, DateTimeZone.UTC).toDate();
			}
		}
		catch(Exception exception)
		{
			System.err.println("getEndOfTheDay:: Exception is: >>"+exception.getMessage());
			throw exception;
		}
		//System.out.println("getEndOfTheDay:: Returning Object : "+date);
		return date;
	}

	public static Long getDateWithNumberFormat(Date date) throws Exception 
	{
		//		System.out.println("getDateWithNumberFormat:: Parsing Object : "+date);
		SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMdd");
		Long response = null;
		try
		{
			response = Long.parseLong(sfd.format(date));
		}
		catch(Exception exception)
		{
			System.err.println("getDateWithNumberFormat:: Exception is: >>"+exception.getMessage());
			throw exception;
		}
		//		System.out.println("getDateWithNumberFormat:: Returning Object : "+response);
		return response;
	}

	/**
	 * 
	 * @param confList
	 * @param split1
	 * @param line
	 * @return JSONObject
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject basicValidation(List<String> validationConfList, List<String> dataList, String line) 
	{
		//		System.out.println("Entry basicValidation..");
		JSONObject jsonObject = null;
		SimpleDateFormat simpleDateFormat = null;
		List<String> confs = null;

		try
		{
			jsonObject = new JSONObject();
			//			System.out.println("Conf Size : " + validationConfList.size() + " : Data Size : " + dataList.size());
			//			System.out.println(validationConfList + " : " + dataList);
			if(validationConfList.size() != dataList.size())
			{
				//				Record structure error
				jsonObject.put(STATUS_STR, FAIL_STR);
				jsonObject.put(ERROR_CODE_STR, COLUMNS_MISMATCH_ERR_CODE);
				jsonObject.put(ERROR_MSG_STR, COLUMNS_MISMATCH_ERR_MSG + line);
				return jsonObject;
			}

			for(int i = 0; i < validationConfList.size(); i++)
			{
				confs = Arrays.asList(validationConfList.get(i).split(";"));
				if("M".equalsIgnoreCase(confs.get(1)) && (dataList.get(i) == null || dataList.get(i).trim().isEmpty()))
				{
					//					Mandatory missing
					jsonObject.put(STATUS_STR, FAIL_STR);
					jsonObject.put(ERROR_CODE_STR, MANDATE_FIELD_MISSING_ERR_CODE);
					jsonObject.put(ERROR_MSG_STR, MANDATE_FIELD_MISSING_ERR_MSG + confs.get(0));
					return jsonObject;
				}
				if(dataList.get(i) != null && !dataList.get(i).trim().isEmpty() && confs.size() > 3)
				{
					if("R".equalsIgnoreCase(confs.get(2)) && !dataList.get(i).matches(confs.get(3)))
					{
						//						Regular Expression
						jsonObject.put(STATUS_STR, FAIL_STR);
						jsonObject.put(ERROR_CODE_STR, INVALID_DATA_ERR_CODE);
						jsonObject.put(ERROR_MSG_STR, INVALID_DATA_ERR_MSG + confs.get(0) + " = " + dataList.get(i));
						return jsonObject;
					}
					if(confs.get(2).equalsIgnoreCase("D"))
					{
						//						Date Format Validation
						simpleDateFormat = new SimpleDateFormat(confs.get(3));
						simpleDateFormat.setLenient(false);
						try
						{
							simpleDateFormat.parse(dataList.get(i));
						}
						catch(Exception e)
						{
							jsonObject.put(STATUS_STR, FAIL_STR);
							jsonObject.put(ERROR_CODE_STR, INVALID_DATA_ERR_CODE);
							jsonObject.put(ERROR_MSG_STR, INVALID_DATA_ERR_MSG + confs.get(0) + " = " + dataList.get(i));
							return jsonObject;
						}
					}
					else if(confs.get(2).equalsIgnoreCase("RD"))
					{
						if(!dataList.get(i).matches(confs.get(3)))
						{
							//							Regular Expression
							jsonObject.put(STATUS_STR, FAIL_STR);
							jsonObject.put(ERROR_CODE_STR, INVALID_DATA_ERR_CODE);
							jsonObject.put(ERROR_MSG_STR, INVALID_DATA_ERR_MSG + confs.get(0) + " = " + dataList.get(i));
							return jsonObject;
						}

						//						Date Format Validation
						simpleDateFormat = new SimpleDateFormat(confs.get(4));
						simpleDateFormat.setLenient(false);
						try
						{
							simpleDateFormat.parse(dataList.get(i));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							jsonObject.put(STATUS_STR, FAIL_STR);
							jsonObject.put(ERROR_CODE_STR, INVALID_DATA_ERR_CODE);
							jsonObject.put(ERROR_MSG_STR, INVALID_DATA_ERR_MSG + confs.get(0) + " = " + dataList.get(i));
							return jsonObject;
						}
					}
					else if(confs.get(2).equalsIgnoreCase("DNF"))
					{

						if(!dataList.get(i).matches(confs.get(3)))
						{
							//							Regular Expression
							jsonObject.put(STATUS_STR, FAIL_STR);
							jsonObject.put(ERROR_CODE_STR, INVALID_DATA_ERR_CODE);
							jsonObject.put(ERROR_MSG_STR, INVALID_DATA_ERR_MSG + confs.get(0) + " = " + dataList.get(i));
							return jsonObject;
						}

						//						Date Format Validation
						simpleDateFormat = new SimpleDateFormat(confs.get(4));
						simpleDateFormat.setLenient(false);
						try
						{
							simpleDateFormat.parse(dataList.get(i));
						}
						catch(Exception e)
						{
							jsonObject.put(STATUS_STR, FAIL_STR);
							jsonObject.put(ERROR_CODE_STR, INVALID_DATA_ERR_CODE);
							jsonObject.put(ERROR_MSG_STR, INVALID_DATA_ERR_MSG + confs.get(0) + " = " + dataList.get(i));
							return jsonObject;
						}

						Date currentDate = new Date();
						Date rowDate = simpleDateFormat.parse(dataList.get(i));
						if(rowDate.after(currentDate))
						{
							jsonObject.put(STATUS_STR,  FAIL_STR);
							jsonObject.put(ERROR_CODE_STR, FUTURE_DT_ERR_CODE);
							jsonObject.put(ERROR_MSG_STR, FUTURE_DT_ERR_MSG  + confs.get(0) + " = " + dataList.get(i));							
							return jsonObject;
						}

					}
				}
				if((dataList.get(i) == null || dataList.get(i).trim().isEmpty()) && confs.size() > 3)
				{
					if(confs.get(2).equalsIgnoreCase("V"))
					{
						dataList.set(i, confs.get(3));
						jsonObject.put(LINE_UPDATE_STR, YES_STR);
					}
				}
			}

			jsonObject.put(STATUS_STR, SUCCESS_STR);
			return jsonObject;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			jsonObject.put(STATUS_STR, FAIL_STR);
			jsonObject.put(ERROR_CODE_STR, BASIC_VALIDATION_ERR_CODE);
			jsonObject.put(ERROR_MSG_STR, BASIC_VALIDATION_ERR_MSG + exception.getMessage());
			return jsonObject;
		}
		finally
		{
			jsonObject = null;
			simpleDateFormat = null;
			confs = null;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param dataList
	 * @param delimiter
	 * @return String
	 * @throws Exception
	 */
	public static String prepareLine(List<String> dataList, String delimiter) throws Exception
	{
		StringBuffer buffer = null;

		try
		{
			buffer = new StringBuffer();
			for(int i = 0; i < dataList.size(); i++)
			{
				buffer.append(dataList.get(i));
				if(i < (dataList.size() - 1))
					buffer.append(delimiter);
			}
			return buffer.toString();
		}
		catch(Exception exception)
		{
			throw exception;
		}
		finally
		{
			buffer = null;
		}
	}

	/**
	 * 
	 * @param srcFile
	 * @param destFile
	 * @return boolean
	 */
	public static boolean backupFile(File srcFile, File destFile) 
	{
		System.out.println("Entry backupFile..");

		InputStream inputStream = null;
		OutputStream outputStream = null;
		byte[] buffer = null;
		int length = 0;

		try 
		{
			inputStream = new FileInputStream(srcFile);
			outputStream = new FileOutputStream(destFile);
			buffer = new byte[1024];

			while ((length = inputStream.read(buffer)) > 0) 
			{
				outputStream.write(buffer, 0, length);
			}

			if (inputStream != null) 
			{
				inputStream.close();
			}

			if (outputStream != null) 
			{
				outputStream.close();
			}

			return true;
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
			return false;
		} 
		finally 
		{
			inputStream = null;
			outputStream = null;
			buffer = null;

			System.out.println("Exit backupFile..");
		}
	}
	public static String actualSerial(String operatorCode, String serial, String checkSum)
	{
		String actualSerial = null;
		int actuaLength = ACTUAL_SERIAL_LENGTH;
		int serialLength = serial.length();
		if(serialLength != actuaLength)
		{
			int diffLength = actuaLength - serialLength;
			for(int i=0; i<diffLength; i++)
			{
				operatorCode = operatorCode+"0";
			}
		}
		actualSerial = operatorCode+serial+checkSum;
		return actualSerial;
	}
	public static Map<String,List<List<Long>>> groupNumericLongSerials(List<String> inputList)
	{
		Map<String,Set<Long>> map = new HashMap<String,Set<Long>>();
		Map<String,List<List<Long>>> resultMap = new HashMap<String,List<List<Long>>>();
		Set<String> keySet = new HashSet<String>();
		for(String str:inputList)
		{
			String key = str.substring(0, 4)+"~"+str.substring(19,20);
			keySet.add(key);
		}
		for(String mapkey : keySet)
		{
			Set<Long> serialSet = new HashSet<Long>();
			for(String str: inputList)
			{
				String key = str.substring(0, 4)+"~"+str.substring(19,20);
				if(mapkey.equalsIgnoreCase(key))
				{
					serialSet.add(Long.parseLong(str.substring(4,19)));
				}
				map.put(mapkey, serialSet);
			}
		}
		//		System.out.println("Final Map : "+map);

		for(Map.Entry<String, Set<Long>> entry: map.entrySet())
		{
			List<Long> list= new ArrayList<Long>();
			List<List<Long>>lList=new ArrayList<List<Long>>();
			List<Long> sList = new ArrayList<Long>(2);
			int i=0;
			int start=0;

			list.addAll(entry.getValue());
			Collections.sort(list);

			for (i = 1; i < list.size(); i++) 
			{
				if (list.get(i - 1) + 1 != list.get(i)) 
				{
					sList.add(list.get(start));
					sList.add(list.get(i - 1));
					lList.add(sList);
					sList = new ArrayList<Long>(2);
					start = i;
				}
			}
			sList.add(list.get(start)); // for last range
			sList.add(list.get(list.size() - 1));
			lList.add(sList);
			resultMap.put(entry.getKey(), lList);
		}
		//		System.out.println("Result Map for Request : "+resultMap);

		return resultMap;
	}

	public static  List<List<Long>> groupNumericShortSerials(List<Long> inputList)
	{
		List<Long> list = new ArrayList<Long>();
		List<List<Long>> lList = new ArrayList<List<Long>>();
		List<Long> sList = new ArrayList<Long>(2);
		int i = 0;
		int start = 0;

		list.addAll(inputList);
		Collections.sort(list);

		for (i = 1; i < list.size(); i++)
		{
			if (list.get(i - 1) + 1 != list.get(i))
			{
				sList.add(list.get(start));
				sList.add(list.get(i - 1));
				lList.add(sList);
				sList = new ArrayList<Long>(2);
				start = i;
			}
		}
		sList.add(list.get(start)); // for last range
		sList.add(list.get(list.size() - 1));
		lList.add(sList);

		//		System.out.println("Result Map for Request : " + lList);
		return lList;
	}

	public  String getMessage(String message)
	{
		return message.length() > TEMP_TABLES_ERROR_MSG_LENGTH ? message.substring(0, TEMP_TABLES_ERROR_MSG_LENGTH) : message;
	}

	public static void compressGzipFile(String file, String gzipFile) 
	{
		Long startTime = System.currentTimeMillis();
		System.out.println("Entry compressGzipFile.. Started at --> "+ new Date());

		FileInputStream fis = null;
		GZIPOutputStream gzipOS = null;
		FileOutputStream fos = null;
		byte[] buffer = null;

		try
		{
			System.out.println("compressGzipFile service received gzipFile Name-->"+gzipFile+"file Name -->"+file);
			fis = new FileInputStream(file);
			fos = new FileOutputStream(gzipFile);
			gzipOS = new GZIPOutputStream(fos);
			buffer = new byte[1024];
			int len = 0;
			while((len=fis.read(buffer)) != -1)
			{
				gzipOS.write(buffer, 0, len);
			}
			//close resources
		}
		catch (IOException e) 
		{
			e.printStackTrace();
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
			catch (IOException e)
			{
				e.printStackTrace();
			}

			gzipFile = null;
			fos = null;
			fis = null;

			System.out.println("Exit compressGzipFile service at -->"+new Date()+" --- and took "+(System.currentTimeMillis()-startTime)+" milliseconds");
			startTime = null;
		}
	}

	public static void decompressGzipFile(String gzipFile, String newFile) throws Exception
	{
		Long startTime = System.currentTimeMillis();
		System.out.println("Entry decompressGzipFile.. Started at --> "+ new Date());

		FileInputStream fis = null;
		GZIPInputStream gis = null;
		FileOutputStream fos = null;
		byte[] buffer = null;

		try
		{
			System.out.println("decompressGzipFile service received gzipFile Name-->"+gzipFile+"newFile Name -->"+newFile);
			fis = new FileInputStream(gzipFile);
			gis = new GZIPInputStream(fis);
			fos = new FileOutputStream(newFile);
			buffer = new byte[1024];
			int len;
			while((len = gis.read(buffer)) != -1)
			{
				fos.write(buffer, 0, len);
			}
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
			throw new Exception(ioException.getMessage(), ioException);
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			throw new Exception(exception.getMessage(), exception);
		}
		finally
		{
			buffer = null;
			try 
			{
				if(fos != null)
					fos.close();
				if(gis != null)
					gis.close();
				if(fis != null)
					fis.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			gis = null;
			fos = null;
			fis = null;

			System.out.println("Exit decompressGzipFile service at -->"+new Date()+" --- and took "+(System.currentTimeMillis()-startTime)+" milliseconds");
			startTime = null;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param resultSet
	 * @param metaData
	 * @return {@link org.json.JSONObject}
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject convertToJSON(ResultSet resultSet, ResultSetMetaData metaData) throws Exception
	{
		JSONObject jsonObject = null;
		String columnName = null;

		try
		{
			jsonObject = new JSONObject();
			for (int i = 1; i < metaData.getColumnCount() + 1; i++) 
			{
				//				columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, metaData.getColumnName(i));
				columnName = metaData.getColumnName(i).toLowerCase();
				/*if (metaData.getColumnType(i) == java.sql.Types.VARCHAR) 
					jsonObject.put(columnName, resultSet.getString(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.NUMERIC) 
					jsonObject.put(columnName, resultSet.getLong(columnName));
				else*/ if (metaData.getColumnType(i) == java.sql.Types.DATE) 
					jsonObject.put(columnName, resultSet.getDate(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIME)
					jsonObject.put(columnName, resultSet.getTime(columnName));
				else if (metaData.getColumnType(i) == java.sql.Types.TIMESTAMP) 
					jsonObject.put(columnName, resultSet.getTimestamp(columnName));
				else
					jsonObject.put(columnName, resultSet.getObject(columnName));
			}
			return jsonObject;
		}
		catch(Exception exception)
		{
			System.err.println(exception.getMessage() + exception);
			throw new Exception(exception.getMessage(), exception);
		}
		finally
		{
			jsonObject = null;
			columnName = null;
		}
	}

	/**
	 * @author Suresh Upparu
	 * <b>Algorithm</b>
	 * <pre>
	 * 
	 * </pre>
	 * @param localDir
	 * @param fileName
	 */
	/*public void createKPISignalFile(String localDir, String fileName)
	{
		JSch jsch = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		File srcFile = null;
		FileInputStream inputStream = null;

		try
		{
			srcFile = new File(localDir + fileName);
			srcFile.createNewFile();
			jsch = new JSch();
			session = jsch.getSession(PropertiesLoader.getValueFor("KPI_REMOTE_USER"), PropertiesLoader.getValueFor("KPI_REMOTE_HOST") , Integer.parseInt(PropertiesLoader.getValueFor("KPI_REMOTE_PORT")));
			session.setPassword(PropertiesLoader.getValueFor("KPI_REMOTE_PWD"));
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println("Session Created..");
			channel = session.openChannel("sftp");
			channel.connect();
			System.out.println("Channel Connected..");
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(PropertiesLoader.getValueFor("KPI_REMOTE_FILE_PATH"));
			inputStream = new FileInputStream(srcFile);
			channelSftp.put(inputStream, srcFile.getName());
			inputStream.close();
			channelSftp.exit();
			channel.disconnect();
			session.disconnect();
			srcFile.delete();
			System.out.println("File " + fileName + " copied to Remote KPI Server " + PropertiesLoader.getValueFor("KPI_REMOTE_HOST"));
		}
		catch(Exception exception)
		{
			System.err.println("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			if(channelSftp != null)
				channelSftp.disconnect();
			if(channel != null)
				channel.disconnect();
			if(session != null)
				session.disconnect();

			jsch = null;
			session = null;
			channel = null;
			channelSftp = null;
			srcFile = null;
			inputStream = null;
		}
	}*/
	static String FUTURE_DT_ERR_MSG = "FUTURE_DT_ERR_MSG : ";
	static String FUTURE_DT_ERR_CODE="FUTURE_DT_ERR_CODE";
	static String SUCCESS_STR = "SUCCESS_STR";
	static String LINE_UPDATE_STR = "LINE_UPDATE_STR";
	static String YES_STR = "YES_STR";
	static String INVALID_DATA_ERR_MSG = "INVALID_DATA_ERR_MSG : ";
	static String INVALID_DATA_ERR_CODE = "INVALID_DATA_ERR_CODE";
	static String MANDATE_FIELD_MISSING_ERR_CODE = "MANDATE_FIELD_MISSING_ERR_CODE";
	static String MANDATE_FIELD_MISSING_ERR_MSG = "MANDATE_FIELD_MISSING_ERR_MSG : ";
	static String COLUMNS_MISMATCH_ERR_CODE = "COLUMNS_MISMATCH_ERR_CODE";
	static String COLUMNS_MISMATCH_ERR_MSG = "COLUMNS_MISMATCH_ERR_MSG : ";
	static String STATUS_STR = "STATUS_STR";
	static String FAIL_STR = "FAIL_STR";
	static String ERROR_CODE_STR = "ERROR_CODE_STR";
	static String BASIC_VALIDATION_ERR_CODE = "BASIC_VALIDATION_ERR_CODE";
	static String ERROR_MSG_STR = "ERROR_MSG_STR";
	static String BASIC_VALIDATION_ERR_MSG = "BASIC_VALIDATION_ERR_MSG : ";
	static int TEMP_TABLES_ERROR_MSG_LENGTH=255;
	static int ACTUAL_SERIAL_LENGTH=15;

	public static Map<String, Integer> getHeaderMap(String header, String delimiter) throws Exception
	{
		Map<String, Integer> headerMap = null;
		List<String> headerList = null;
		try
		{
			headerMap = new HashMap<String, Integer>();
			headerList = Arrays.asList(header.split(delimiter));
			for(int i = 0; i<headerList.size(); i++)
			{
				headerMap.put(headerList.get(i), i);
			}
			return headerMap;
		}
		catch(Exception exception)
		{
			throw exception;
		}
		finally 
		{
			headerMap = null;
			headerList = null;
		}
	}
}