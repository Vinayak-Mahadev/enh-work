package com.enhancesys.jobcommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


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

public class PropertyLoader {

	public static Map<String, String> loadProperties(final String configFileName)
	{
		final Map<String, String> confMap = new HashMap<String, String>();
		Properties properties = null;
		Object key =  null;
		File file       = null;
		InputStream fis = null;

		try
		{


			properties     = new Properties();
			file = new File((configFileName));

			if(file.exists())
			{
				fis = new  FileInputStream(file);
				properties.load(fis);
			}
			for (Iterator<Object> localIterator = properties.keySet().iterator(); localIterator.hasNext(); ) 
			{
				key = localIterator.next();

				if(properties.getProperty(key.toString()) != null && !properties.getProperty(key.toString()).trim().isEmpty())
					confMap.put(key.toString(), properties.getProperty(key.toString()).trim());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			key =  null;
			file = null;
			properties = null;
			if(fis!=null) 
			{
				try 
				{
					fis.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return confMap;
	}



}
