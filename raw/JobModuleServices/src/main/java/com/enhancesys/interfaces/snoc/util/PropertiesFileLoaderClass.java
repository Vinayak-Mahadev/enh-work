package com.enhancesys.interfaces.snoc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * <b>Purpose:</b><br>
 * PropertiesFileLoader.java <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * snoc-Customer-Sevices-SNAPSHOT<br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys 2016<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b> 
 * Sl No Modified Date Author</b> 
 * ==============================================
 *   07-Apr-2016 soumodip -- Base Release
 * 
 * </pre>
 * 
 * <br>
 */
@Component
public class PropertiesFileLoaderClass {

	private static Logger log = Logger.getLogger(PropertiesFileLoaderClass.class);

	static Properties properties;

	static 
	{
		PropertiesFileLoaderClass.loadProperties();
	}

	public static void reset() {
		PropertiesFileLoaderClass.loadProperties();
	}

	private static void loadProperties() 
	{
		PropertiesFileLoaderClass.properties = new Properties();
		FileInputStream inputStream = null;
		try 
		{
			File file = new File(System.getenv("INTERFACE_CONF_PATH"));
			if (file.canRead()) 
			{
				inputStream = new FileInputStream(System.getenv("INTERFACE_CONF_PATH"));
				PropertiesFileLoaderClass.properties.load(new InputStreamReader(inputStream, "UTF-8"));
			}
		} 
		catch (IOException ioException){
		    log.error("static:: Exception occured While loading Properties into Applciation Container, and Message is: >> "+ioException.getMessage());
		}catch (Exception exception){
		    log.error("static:: Exception occured While loading Properties into Applciation Container, and Message is: >> "+exception.getMessage());
		}
		
	}

	public static String getValueAsString(String key) throws Exception 
	{
		String keyValue = null;
		try {
			if (key != null) 
			{
				if (PropertiesFileLoaderClass.properties.containsKey(key)) 
				{
					keyValue = PropertiesFileLoaderClass.properties.get(key).toString();
				}
			} 
			else 
			{
				throw new Exception("Invalid key[null] is passed to look-up in properties file..!");
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		return keyValue;
	}
	
	public static Object get(String key) throws Exception 
	{
		Object keyValue = null;
		try 
		{
			if (key != null) 
			{
				if (PropertiesFileLoaderClass.properties.containsKey(key)) 
				{
					keyValue = PropertiesFileLoaderClass.properties.get(key);
				} 
			} 
			else 
			{
				throw new Exception("Invalid key[null] is passed to look-up in properties file..!");
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		return keyValue;
	}
}
