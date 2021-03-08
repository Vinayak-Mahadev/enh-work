package com.enhancesys.integration.snoc.props;

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
 *    Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        10-10-2019          Vinayak Mahadev
 *      -- Base Release
 */

public final class PropertiesLoader 
{
	private static Properties properties = null;
	private static Logger LOGGER = Logger.getLogger(PropertiesLoader.class);

	public PropertiesLoader()
	{
		if(properties == null)
			loadProperties();
	}

	private static void loadProperties()
	{
		LOGGER.info("Entry loadProperties..");

		File file = null;

		try 
		{
			properties = new Properties();

			file = new File(DataConstants._CONFIG_COMMON_FILE);

			LOGGER.info("Configuration Path : " + DataConstants._CONFIG_COMMON_FILE);
			properties.load(new FileInputStream(file));
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			file = null;
			LOGGER.info("Exit loadProperties..");
		}
	}

	public static String getValueFor(String key)
	{
		try
		{
			if(properties == null)
				loadProperties();
			if(properties != null && properties.get(key) != null)
				return properties.get(key).toString().trim();
		}
		catch(Exception exception)
		{
			LOGGER.error("Unhandled Exception : " + exception.getMessage(), exception);
		}
		return null;
	}

	public static String getErrorDescriptionFor(Long filesCreated) {
		return null;
	} 
}