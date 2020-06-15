package com.enhancesys.interfaces.snoc.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import com.enhancesys.interfaces.snoc.common.Constants;
import com.enhancesys.schema.impl.JSONValidator;
import com.enhancesys.schema.impl.validators.PropertiesValidator;

public class Utility {

	private static Pattern patternInteger = Pattern.compile("\\d*");
	
	public static String parseString(Object strData) 
	{
		return (strData != null ? String.valueOf(strData) : "");

	}

	/**
	 * Proper String to Integer parse
	 * 
	 * @param Value
	 * @return
	 */
	public static int parseInt(Object value) 
	{
		int i = 0;
		if (value != null)
		{
			return Integer.parseInt(parseString(value));
		}
		return i;
	}
	
	public static boolean isInteger(String strValue) 
	{
		return patternInteger.matcher(strValue).matches();
	}
	/**
	 * Proper String to Long parse
	 * 
	 * @param Value
	 * @return
	 */
	public static long parseLong(Object value)
	{
		long i = 0;
		if (value != null)
		{
			return Long.parseLong(!parseString(value).isEmpty() ? parseString(value) : "0");
		}
		return i;
	}
	
	/**
	 * Proper String to double parse
	 * 
	 * @param Value
	 * @return
	 */
	public static double parseDouble(Object value)
	{
		long i = 0;
		if (value != null)
		{
			return Double.parseDouble(!parseString(value).isEmpty() ? parseString(value) : "0");
		}
		return i;
	}

	/**
	 * @class StringUtility
	 * @method stringToInt
	 * @param value
	 *            ex : s123 = 123
	 * @return
	 */
	public static int parseInt(String value)
	{
		String leftNumber = "";
		for (int i = 0; i < value.trim().length(); i++) 
		{
			try 
			{
				int val = Integer.parseInt(String.valueOf(value.charAt(i)));
				leftNumber = leftNumber + String.valueOf(val);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return Integer.parseInt(leftNumber);
	}																	

	/**
	 * The following operation will replace the string with special character
	 * with given size
	 * 
	 * Replace's the end character
	 */
	public static String replaceSpecialChar(String string, String specialChar, int size) 
	{
		if (string != null && string.length() > size) 
		{
			String strAfter = "";
			for(int i = 0; i < size; i++) 
			{
				strAfter = strAfter + specialChar;
			}
			string = string.substring(0, string.length() - size) + strAfter;
			return string;
		}
		return string;
	}
	
	/**
	 * Proper String to double parse
	 * 
	 * @param Value
	 * @return
	 */
	public static boolean parseBoolean(Object value)
	{
		boolean flag = false;
		if (value != null)
		{
			return Boolean.parseBoolean(parseString(!"".equalsIgnoreCase(value+"")?value:false));
		}
		return flag;
	}

	public static String validateBsonSchema(String request, String bsonSchema)
	{
		List<String> validationErrors = new ArrayList<String>();
		String response = Constants.V_SUCCESS;
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			
			validationErrors = new PropertiesValidator(
				mapper.readTree(bsonSchema).get(PropertiesValidator.PROPERTY), 
				mapper).validate(mapper.readTree(request), JSONValidator.AT_ROOT
			);
		}
		catch(JsonParseException jsonParseException)
		{	
			validationErrors.add(jsonParseException.getMessage());
		}
		catch(IOException ioException)
		{	
			validationErrors.add(ioException.getMessage());
		}
		finally
		{
			if(validationErrors.size() > 0)
			{
				for(int errorCount = 0; errorCount < validationErrors.size(); errorCount++)
				{
					if(errorCount == 0)
					{
						response = validationErrors.get(errorCount);
					}
					else
					{
						response += "~~" + validationErrors.get(errorCount);
					}
				}
			}
		}
		return response;
	}
	public static String getExceptionResponse(long requestId, String response, long responseCode) 
	{
		return Constants.RESULT_FORMAT
				.replace("$requestId$", String.valueOf(requestId))
				.replace("$responseMessage$", response)
				.replace("$responseCode$", String.valueOf(responseCode))
				.replace("$response$", "");
	}
	
	public static String getSuccessResponse(long requestId, String response, long responseCode, String payload) throws Exception
	{
		String result = new String();
		try
		{
			result=Constants.RESULT_FORMAT
			.replace("$requestId$", String.valueOf(requestId))
			.replace("$responseMessage$", response)
			.replace("$responseCode$", String.valueOf(responseCode))
			.replace("$response$", "," + payload);
		}
		catch(Exception exception)
		{
			throw exception;
		}
		return result;
	}
}
