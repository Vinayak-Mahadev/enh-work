package enh.team.interfaces.dumptest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;



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
 * Tree Technologies 2012<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        22-08-2012          Vedanth
 *      -- Base Release
 */

public class PropertiesLoader 
{

	public static String getValueFor(String key)
	{
		try
		{
			if(properties == null)
			{
				loadProperties();
			}
			return (String)properties.get(key);
		}
		catch(Exception e)
		{
			System.out.println("Key not found in the properties file. Key :" + key+ e );
			e.printStackTrace();
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
			return (String)properties1.get(key);
		}
		catch(Exception e)
		{
			System.out.println("Key not found in the properties file. Key :" + key+ e );
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
			return (String)properties2.get(key);
		}
		catch(Exception e)
		{
			System.out.println("Key not found in the properties file. Key :" + key+ e );
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

			inputStream = new FileInputStream(new File("E:\\interface\\backend\\indo-d\\snd-integration\\conf\\commonForTest.properties"));
			inputStream1 = new FileInputStream(new File("E:\\interface\\backend\\indo-d\\snd-integration\\conf\\errorCode.properties"));
			inputStream2 = new FileInputStream(new File("E:\\interface\\backend\\indo-d\\snd-integration\\conf\\errorMessage_UK.properties"));
			properties = new Properties();
			properties1 = new Properties();
			properties2 = new Properties();
			properties.load(inputStream);
			properties1.load(inputStream1);
			properties2.load(inputStream2);
		}
		catch(Exception e)
		{
			System.out.println("Unexpected error while loading the properties file"+e);
		}
	}



	static String  path = "";
	static Properties properties = null;
	static Properties properties1 = null;
	static Properties properties2 = null;
}
