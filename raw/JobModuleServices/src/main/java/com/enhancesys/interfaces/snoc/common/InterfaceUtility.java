package com.enhancesys.interfaces.snoc.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

@Component
public class InterfaceUtility
{
	
	private static int count = 6000;
	private static final int maxCount = 6999;
	private static final int sleepTimeForNextIteartion = 1000;

	public static long getRequestId() throws Exception
	{
		InterfaceUtility.count++;
			
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyMMddhhmmssSSS");

		if (InterfaceUtility.count > maxCount)
		{
			try
			{
				Thread.sleep(sleepTimeForNextIteartion);
			}
			catch (InterruptedException interruptedException)
			{
				
			}
			finally
			{
				InterfaceUtility.count = 6001;
			}
		}
		
		return Long.parseLong(dateFormatter.format(new Date()) + String.format("%04d", InterfaceUtility.count));
	}
	
	public static String prepareSuccessResponseJson(long requestId,BasicDBObject data)
	{
		String response = null;
				
		try
		{
			BasicDBObject responseObject = new BasicDBObject();
			
			responseObject.append("req_id", requestId);
			responseObject.append("res_code", 100L);
			responseObject.append("res_msg", "Request process succesfully...");
			responseObject.append("payload", data);
			
			response = responseObject.toString();
		}
		catch(Exception exception)
		{
			
		}
		
		return response;
	}
	
	public static String prepareSuccessResponseList(long requestId, BasicDBList data)
	{
		String response = null;
				
		try
		{
			BasicDBObject responseObject = new BasicDBObject();
			
			responseObject.append("req_id", requestId);
			responseObject.append("res_code", 100L);
			responseObject.append("res_msg", "Request process successfully...");
			responseObject.append("payload", data);
			
			response = responseObject.toString();
		}
		catch(Exception exception)
		{
			
		}
		
		return response;
	}
	
	public static String prepareExceptionResponseJson(long requestId, String errorMsg, int errorCode)
	{
		String response = null;
				
		try
		{
			BasicDBObject responseObject = new BasicDBObject();
			
			responseObject.append("req_id", requestId);
			responseObject.append("res_code", errorCode);
			responseObject.append("res_msg", errorMsg);
			
			response = responseObject.toString();
		}
		catch(Exception exception)
		{
			
		}
		
		return response;
	}
	
	public static byte[] decodeToBytes(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

	public static String encodeToString(byte[] imageByteArray) {
		return Base64.encodeBase64String(imageByteArray);
	}
}