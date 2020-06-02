package com.enhancesys.jobcommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


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
