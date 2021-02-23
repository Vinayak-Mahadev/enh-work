package com.enhancesys.integration.services.dataengine.util.condition.util;


import com.enhancesys.integration.services.dataengine.util.condition.expression.AbstractExpression.EvaluationFailedException;
import com.jayway.jsonpath.JsonPath;

/**
 * <p>
 * This class is used to Extract he value from JSON using JSON Path.
 * <p>
 *  
 * @author Gowtham J
 * @version 1.0
 * @since 1.0
 */


public class DataSourceContentParser
{
	/**
	 * The method will extract the required value from the JSON 
	 * 
	 * @param jsonContent : JSON  Data should be passed as string.
	 * @param jsonPath    : JSON  Path should be passed to traverse JSON.<br/>[Eg]office.employee.name

	 * @return Object
	 * 
	 * @author Gowtham J
	 * @version 1.0	  
	 * @throws JsonParseException 
	 * @throws EvaluationFailedException 
	 * @since 1.0
	 */
	public static Object parseJson(String jsonContent,String jsonPath) throws JsonParseException
	{
		Object expectedValue = null;
		try 
		{
			if(jsonPath.contains("{"))
			{
				jsonPath= jsonPath.replaceAll("\\{", "[");
			}
			if(jsonPath.contains("}"))
			{
				jsonPath = jsonPath.replaceAll("\\}", "]");
			}
					
			String actualJpath = "$."+jsonPath;
			expectedValue = JsonPath.read(jsonContent, actualJpath);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new JsonParseException(e.getMessage());
		}
		return expectedValue;
	}
	
	/**
	 * <p>
	 * This exception will be thrown, if the Json path is not properly formed
	 * <p>
	 *  
	 * @author Gowtham J
	 * @version 1.0
	 * @since 1.0
	 */
	@SuppressWarnings("serial")
	public static class JsonParseException extends Exception
	{
		/**
		 *	The constructor will take the error message as parameter
		 *
		 *	@param message			The error message passed by the thrower
		 *	@since					2.0
		 */
		public JsonParseException(String message)
		{
			super(message);
		}
	}

	
}
