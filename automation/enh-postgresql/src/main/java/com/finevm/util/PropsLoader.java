package com.finevm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropsLoader 
{

	static Properties properties = null;
	public static void loadProperties( String configFileName)
	{
		InputStream fis = null;
		File file       = null;
		try
		{
			properties     = new Properties();
			file = new File((configFileName));
			if(file.exists())
			{
				fis = new  FileInputStream(file);
				properties.load(fis);
				System.out.println("Pros data loaded");
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
				if(fis!=null)
					fis.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}


	public static String getProperties(String param) 
	{
		return properties.get(param).toString();
	}


}
