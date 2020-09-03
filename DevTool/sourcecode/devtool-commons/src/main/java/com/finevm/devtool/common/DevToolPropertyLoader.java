package com.finevm.devtool.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class DevToolPropertyLoader 
{
	public static Properties properties; 
	private static String batFileautoMavenDir;
	private static String shFileautoMavenDir;
	private static  String DATA = DevToolEnvironment._PROPS_DATA;

	private static DevToolPropertyLoader propsLoader;

	public DevToolPropertyLoader() throws Exception {
		DevToolPropertyLoader.properties = loadProperty();
	}

	public static String getProperty(String property) {
		if(DevToolPropertyLoader.properties == null){
			try 
			{
				new DevToolPropertyLoader();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return DevToolPropertyLoader.properties.getProperty(property);
	}

	public static Properties getProperty() throws Exception {

		if(propsLoader ==null){
			new DevToolPropertyLoader();
		}
		return properties;	
	}

	private Properties loadProperty() throws Exception 
	{

		String userHome     = DevToolEnvironment._userHome;
		String userName     = DevToolEnvironment._userName;
		String fineVmDir    =  userHome+"\\FineVM\\";
		String info         =  userHome+"\\FineVM\\README.txt";
		String autoMavenDir =  userHome+"\\FineVM\\autoMaven\\";
		batFileautoMavenDir =  userHome+"\\FineVM\\autoMaven\\bat\\";
		shFileautoMavenDir =  userHome+"\\FineVM\\autoMaven\\sh\\";
		String propertyFile =  userHome+"\\FineVM\\common.properties";
		String logLoc =  userHome+"\\FineVM\\log";
		String logFile =  logLoc+"\\DevTool.log";
		File   file         = null;
		BufferedOutputStream bos = null;
		Properties properties = new Properties();

		try 
		{

			file   = new File(fineVmDir);
			if(!file.isDirectory())
			{
				if(prompt("Welcome  "+userName+"..! \n\n\n\n"
						+ "FineVM tool help teams work together on a whole new level.\n"
						+ "We hope,\n"
						+ "It will help to slove your daily problems and it will boost your work speed...\n\n\n"

						+ "  ")){
					Thread.sleep(1000);	
					System.out.println("FineVM Config dir create  :::   "+file.mkdir());
				}
				else {
					return null;
				}
			}
			file   = new File(autoMavenDir);
			if(!file.isDirectory())
				System.out.println("AutoMvn dir create  :::   "+file.mkdir());
			file   = new File(batFileautoMavenDir);
			if(!file.isDirectory())
				System.out.println("AutoMvn batFile Dir create  :::   "+file.mkdir());
			file   = new File(shFileautoMavenDir);
			if(!file.isDirectory())
				System.out.println("AutoMvn shFile Dir create  :::   "+file.mkdir());
			file = new File(logLoc);
			if(!file.isDirectory())
				file.mkdirs();
			file = new File(propertyFile);

			if(!file.isFile()){
				System.out.println("FineVM Config file going to create...");
				if(prompt("FineVM Config file going to create...")){
					bos = new BufferedOutputStream(new FileOutputStream(file));

					bos.write(DATA.getBytes());
					bos.close();
					System.out.println("FineVM Config File created  :::   "+file.isFile());
					log("Configuration file successfully created \n\n Please check user dir if FineVM dir present or not");
				}
			}


			properties.load(new FileInputStream(new File(propertyFile)));
			properties.setProperty("AUTO_MAVEN_BAT_LOC",batFileautoMavenDir);
			properties.setProperty("AUTO_MAVEN_SH_LOC",shFileautoMavenDir);


			file = new File(info);
			if(!file.exists())
			{
				final String readMe     =  DevToolEnvironment._README;

				bos  = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(readMe.getBytes());
				bos.flush();
			}

			Logger logger = Logger.getLogger(this.getClass());

			Properties props = new Properties();

			props.put("log4j.rootLogger", "DEBUG, stdout,file"); 
			props.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
			props.put("log4j.appender.stdout.Target", "System.out");
			props.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
			props.put("log4j.appender.stdout.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
			props.put("log4j.appender.file", "org.apache.log4j.RollingFileAppender");
			props.put("log4j.appender.file.MaxFileSize", "5MB");
			props.put("log4j.appender.file.MaxBackupIndex", "10");
			props.put("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
			props.put("log4j.appender.file.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
			props.put("log4j.appender.file.File", logFile);


			PropertyConfigurator.configure(props);

			logger.info("Wow! log4j configured!");

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			log("Configuration failed");
			return null;
		}
		finally 
		{
			if(bos!=null)
				bos.close();

			bos          =  null;
			userHome     =  null;
			fineVmDir    =  null;
			autoMavenDir =  null;
			file         =  null;
		}


		return properties;
	}

	private void log(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}

	private boolean  prompt(String msg) {

		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, msg+"\n\nDo you want to continue?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			System.out.println("No button clicked");
			return false;
		} else if (response == JOptionPane.YES_OPTION) {
			System.out.println("Yes button clicked");
			return true;
		} else if (response == JOptionPane.CLOSED_OPTION) {
			System.out.println("JOptionPane closed");
			return false;
		}
		return false;
	}

	public static String getBatFileautoMavenDir() {
		return batFileautoMavenDir;
	}

	public static String getShFileautoMavenDir() {
		return shFileautoMavenDir;
	}

	public static Properties getProperties() {
		return properties;
	}

	public static DevToolPropertyLoader get_version_1() {
		return propsLoader;
	}

	public static String getDATA() {
		return DATA;
	}

}
