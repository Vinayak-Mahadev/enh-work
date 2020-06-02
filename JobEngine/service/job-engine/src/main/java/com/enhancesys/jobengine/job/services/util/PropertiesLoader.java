package com.enhancesys.jobengine.job.services.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * <b>Purpose:</b><br>
 * 
 * Utility class to load the common properties file
 * 
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-10-2018          Suresh Upparu
 *      -- Base Release
 */

public class PropertiesLoader 
{
	private static Properties properties = null;
	private static Logger log = Logger.getLogger(PropertiesLoader.class);
	
	public PropertiesLoader()
	{
		if(properties == null)
			loadProperties();
	}

	private void loadProperties()
	{
		log.info("Entry loadProperties..");
		
		File file = null;
		String configurationPath = null;
		String envPath = null;
		
		try 
		{
			properties = new Properties();
			
			configurationPath = System.getProperty("user.dir") + File.separator + "jobConfiguration.properties";
			file = new File(configurationPath);
			if(!file.exists())
			{
				envPath = System.getenv("APPSERVER_CONF_PATH");
				configurationPath = envPath + File.separator + "jobengineconf/common/jobConfiguration.properties";
				if(envPath.trim().endsWith("/"))
					configurationPath = envPath + "reports/jobConfiguration.properties";

				file = new File(configurationPath);
				if(!file.exists())
				{
					log.error("\nERROR :: Please configure jobConfiguration.properties file..");
					System.exit(0);
				}
			}
			
//			configurationPath = "E:/Documents/Team/Docs/Yugi/Reliance/2018-10/Amjath/Data Engine/jobConfiguration.properties";
			
			log.info("Configuration Path : " + configurationPath);
			properties.load(new FileInputStream(file));
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			file = null;
			configurationPath = null;
			envPath = null;
			log.info("Exit loadProperties..");
		}
	}
	
	public String getValueFor(String key)
	{
		try
		{
			if(properties == null)
				loadProperties();

			return (String) properties.get(key);
		}
		catch(Exception exception)
		{
			log.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		return "";
	} 
}