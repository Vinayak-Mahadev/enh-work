package com.finevm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.finevm.enh.util.IWorkConstants;

public final class PropsLoader {
	private static Map<String, String> confMap = null;

	private static Map<String, String> loadProperties()
	{
		Properties properties = null;

		String configFileName = null;
		InputStream fis = null;
		File file       = null;
		try
		{


			properties     = new Properties();
			configFileName = IWorkConstants.INTERFACE_WORK_SOURCECODE_LOC;
			file = new File((configFileName));
			if(file.exists())
			{
				fis = new  FileInputStream(file);
				properties.load(fis);
			}

			for (Iterator<Object> localIterator = properties.keySet().iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();

			confMap.put(key.toString(), properties.getProperty(key.toString()));
			}



		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			file = null;
			properties = null;
			configFileName = null;
			try 
			{
				if(fis!=null)
					fis.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return confMap;
	}


	public static Properties loadProperties(String configFileName)
	{
		Properties properties = null;
		InputStream fis = null;
		File file       = null;
		try
		{

			//System.out.println("configFileName location :: "+configFileName);
			properties     = new Properties();
			file = new File((configFileName));
			if(file.exists())
			{
				fis = new  FileInputStream(file);
				properties.load(fis);
			}
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			file = null;
			configFileName = null;
			try 
			{
				if(fis != null)
					fis.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}


	public static String getProperties(String param) {
		if((confMap == null)){
			confMap = new HashMap<String, String>();
			confMap =   loadProperties();
		}
		return confMap.get(param);
	}


}
