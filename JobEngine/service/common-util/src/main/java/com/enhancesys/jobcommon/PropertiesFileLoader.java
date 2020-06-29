package com.enhancesys.jobcommon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <b>Purpose:</b><br>
 * Property loading purpose<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-05-2010		   Vinayak Mahadev (vinay.nagaraj@enhancesys.com)
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */


public class PropertiesFileLoader 
{
	private static Logger log = Logger.getLogger(PropertiesFileLoader.class);
	public static String getValueFor(String key)
	{
		try
		{
			if(properties == null)
			{
				loadProperties();
			}
			return ((String)properties.get(key)).trim();
		}
		catch(Exception e)
		{
			log.error("Key not found in the properties file. Key :" + key, e );
		}
		return "";
	} 
	
	public static String getErrorCodeFor(String key)
	{
		try
		{
			if(properties == null)
			{
				loadProperties();
			}
			return ((String)properties1.get(key)).trim();
		}
		catch(Exception e)
		{
			log.error("Key not found in the properties file. Key :" + key, e );
		}
		return "";
	} 
	
	public static String getErrorDescriptionFor(String key)
	{
		try
		{
			if(properties == null)
			{
				loadProperties();
			}
			return ((String)properties2.get(key)).trim();
		}
		catch(Exception e)
		{
			log.error("Key not found in the properties file. Key :" + key, e );
		}
		return "";
	} 
	
	private static void loadProperties()
	{
		try
		{
			InputStream inputStream = null;
			InputStream inputStream1 = null;
			InputStream inputStream2 = null;
			if(path.equals(""))
			{
				inputStream = new FileInputStream(readFile());
			}
			properties = new Properties();
			properties1 = new Properties();
			properties2 = new Properties();
			properties.load(inputStream);

			if(properties.get("interface_error_code_mapping") != null)
			{
				inputStream1 = new FileInputStream(properties.get("interface_error_code_mapping").toString());
				properties1.load(inputStream1);
			}
			if(properties.get("interface_error_code_description") != null)
			{
				inputStream2 = new FileInputStream(properties.get("interface_error_code_description").toString());
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
				properties2.load(br);
			}
		}
		catch(Exception e)
		{
			log.error("Unexpected error while loading the properties file",e);
		}
	}
	
	private static String readFile()
	{
		try
		{
			log.info("Reading Properties file..."); 
			path = Constants._JOB_CONFIG_PATH + "common.properties";
		}
		catch(Exception e)
		{
			log.error("Unexpected error while reading the configution path ",e);
		}
		return path;
	}
	
	static String  path = "";
	static Properties properties = null;
	static Properties properties1 = null;
	static Properties properties2 = null;
}
