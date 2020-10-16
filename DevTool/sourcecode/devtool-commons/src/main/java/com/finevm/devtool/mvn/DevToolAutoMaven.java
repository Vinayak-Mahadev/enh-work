package com.finevm.devtool.mvn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;

public class DevToolAutoMaven {

	private JSONObject autoMavenConf;
	private Scanner scan = null;
	private JSONArray jsonArr =null;
	private JSONArray defaultCaseJsonArr = null;
	private int totalCases =0;
	private int choice = 0;
	private String batFileLocation = DevToolConstants._batFileautoMavenDir;
	private String shFileLocation  = DevToolConstants._shFileautoMavenDir;
	private DevToolMavenConfig mavenConfig;
	public List<JSONObject> runList ;

	private final static Logger logger = Logger.getLogger(DevToolAutoMaven.class);

	public DevToolAutoMaven()
	{
		logger.info(this.getClass().getName()+" obj created :::   "+objInfo());
	}

	public JSONObject getMavenConfigJson() {
		return mavenConfig.getJson();
	}
	public DevToolAutoMaven(JSONObject mavenProjectList)
	{
		try 
		{
			mavenConfig = new DevToolMavenConfig(mavenProjectList);
			logger.info(this.getClass().getName()+" obj created :::   "+objInfo());
		} catch (Exception e) {
			logger.error(e);
		} 
	}

	public JSONObject getMvnIds(JSONObject json){

		List<String> list = null;
		JSONObject returnObj = new JSONObject();
		if(json.keySet()!=null) {
			list = new ArrayList<>(json.keySet());
			Collections.sort(list);
		}
		int i = 0;
		for (String name : list) {
			returnObj.put(++i+"_",name);
		}
		return returnObj;
	}

	public JSONObject getMvnProjectArtifacts(JSONObject json, String key)
	{
		JSONObject modules = null;
		JSONObject childJson = null;
		JSONObject list = new JSONObject();
		int i = 0;
		try 
		{
			for (Object object : json.getJSONArray(key)) 
			{
				modules  = (JSONObject) object;
				childJson = new JSONObject();
				if(modules.has(DevToolConstants._java_home) && modules.get(DevToolConstants._java_home) != null)
					childJson.put(DevToolConstants._java_home, modules.getString(DevToolConstants._java_home));
				else
					childJson.put(DevToolConstants._java_home," ");

				childJson.put(DevToolConstants._artifactId, modules.getString(DevToolConstants._artifactId));
				childJson.put(DevToolConstants._projectLoc, modules.getString(DevToolConstants._projectLoc));
				childJson.put(DevToolConstants._id, ++i);
				list.put(i+"_",childJson);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			modules = null;
			childJson = null;
			i = 0;			
		}
		return list;
	}

	public String prepareRunFile(JSONObject json, String mvncmd) throws Exception 
	{
		String fileName = null;
		String 	cmdData = null;
		String lastMsg  = null;
		String  childPath = null;
		String  DRIVE_ID = null;
		String end = null;
		String javaHomeCmd = null;
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try 
		{
			if(isWindows()) 
			{
				cmdData = "REM This is a comment! For "+json.get(DevToolConstants._artifactId)+"\n\n";
				lastMsg = "REM -- This file created by Fine-VM software...\nREM For more details vinayakmahadev.nm@gmail.com";

				System.out.println(json);

				if(json.get(DevToolConstants._java_home) != null && !json.getString(DevToolConstants._java_home).trim().isEmpty()) 
				{
					javaHomeCmd = "SET "+DevToolConstants._java_home+"="+ json.getString(DevToolConstants._java_home);
					cmdData = cmdData + javaHomeCmd +"\n\n";
				}

				childPath = json.getString(DevToolConstants._projectLoc);
				DRIVE_ID = json.getString(DevToolConstants._projectLoc).substring(0,2);
				end = ("exit")+"\n\n";
				fileName = getBatFileLocation() + json.get(DevToolConstants._artifactId)+".bat";


				cmdData = cmdData + DRIVE_ID;
				cmdData = cmdData + " && cd ";
				cmdData = cmdData + "/d \""+childPath.trim()+"\" ";
				cmdData = cmdData + " && "+mvncmd + " && ";
				cmdData = cmdData + end;

				cmdData = cmdData + lastMsg;

				file = new File(fileName);
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);
				bos.write(cmdData.getBytes());
				System.out.println(cmdData);
			}
			else 
			{
				// Need to implement 	
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(bos != null)
				bos.close();

			file = null;
			fos = null;
			bos = null;
			cmdData   = null;
			childPath = null;
			DRIVE_ID  = null;
			end        = null;
			bos = null;
			lastMsg = null;
			javaHomeCmd = null;
		}

		return fileName;
	} 

	public void info() {

		totalCases = autoMavenConf.getJSONArray("cases").length();
		jsonArr = autoMavenConf.getJSONArray("cases");

		defaultCaseJsonArr = autoMavenConf.getJSONArray("default_case_list");
		choice = 0;

		runList = sortJSONArray(jsonArr,"no");


		logger.info("-------------------------------------------------------\n"
				+ "TOTAL_CASES  :  "+totalCases+""
				+ "\n-------------------------------------------------------\n");

		for (JSONObject runJsonObj : runList) {

			logger.info(runJsonObj.get("no")+".   "+ runJsonObj.get("name"));
		}
		logger.info("\n-------------------------------------------------------"
				+ "\n                              DEFAULT :  "+autoMavenConf.getJSONArray("default_case_list")+"\n\n");


	}

	public Process run()  throws Exception{

		System.out.print("ENTER YOUR OPTION :    ");
		scan = new Scanner(System.in);
		try {
			choice = scan.nextInt();
		} catch (java.util.InputMismatchException e) {
			logger.info("Mismatched..! So DEFAULT CASE STARTED ");
			for (Object obj : defaultCaseJsonArr) {
				choice = Integer.parseInt(obj.toString());

				Thread.sleep(1500);
				logger.info("STARTED AS "+runList.get(choice).get("name"));
				return runBatFile(prepareBatFile(runList.get(choice),choice));

			}
		} finally{
			if(scan != null)
				scan.close();
		}

		logger.info("YOUR CHOICE IS  :  "+ choice);
		try {

			Thread.sleep(1500);
			logger.info("STARTED AS "+runList.get(choice).get("name"));

			return runBatFile(prepareBatFile(runList.get(choice),choice));
		} finally{
			if(scan != null)
				scan.close();
		}
	}

	public String runCmdWithChoice(String choiceFromOutside)  throws Exception{


		try {
			choice = Integer.parseInt(choiceFromOutside);

		} catch (Exception e) {
			logger.info("Mismatched..! So DEFAULT CASE STARTED ");
			for (Object obj : defaultCaseJsonArr) {
				choice = Integer.parseInt(obj.toString());

				Thread.sleep(1000);
				logger.info("STARTED AS "+runList.get(choice).get("name"));
				return runBatFileCmd(prepareBatFile(runList.get(choice),choice));

			}
		} 

		logger.info("YOUR CHOICE IS  :  "+ choice);
		try {

			Thread.sleep(1500);
			logger.info("STARTED AS "+runList.get(choice).get("name"));

			return runBatFileCmd(prepareBatFile(runList.get(choice),choice));
		} finally{
			if(scan != null)
				scan.close();
		}
	}

	public Process runWithChoice(String choiceFromOutside)  throws Exception{


		try {
			choice = Integer.parseInt(choiceFromOutside);

		} catch (Exception e) {
			logger.info("Mismatched..! So DEFAULT CASE STARTED ");
			for (Object obj : defaultCaseJsonArr) {
				choice = Integer.parseInt(obj.toString());

				Thread.sleep(1000);
				logger.info("STARTED AS "+runList.get(choice).get("name"));
				return runBatFile(prepareBatFile(runList.get(choice),choice));

			}
		} 

		logger.info("YOUR CHOICE IS  :  "+ choice);
		try {

			Thread.sleep(1500);
			logger.info("STARTED AS "+runList.get(choice).get("name"));

			return runBatFile(prepareBatFile(runList.get(choice),choice));
		} finally{
			if(scan != null)
				scan.close();
		}
	}

	public Process runBatFile(String path) throws IOException{
		return Runtime.getRuntime().exec("cmd /c start /wait " +path);
	}

	public String runBatFileCmd(String path) throws IOException{
		return "cmd /c start /wait " +path;
	}


	public String prepareBatFile(JSONObject runJson, int choice)  throws Exception{
		BufferedOutputStream bos = null;

		String 	cmdData = "REM This is a comment! For "+runJson.get("name")+"\n\n";
		String 	childCmd = "";
		String  childPath = "";
		String  DRIVE_ID = "";
		String end = "&& EXIT";
		//		end = "";
		String fileName = batFileLocation + runJson.get("no")+".bat";


		try {

			for (int i = 0; i < runJson.getInt("total_cmds"); i++) {

				childPath = runJson.getJSONArray("paths").getString(i);
				childCmd  = runJson.getJSONArray("cmds").getString(i);

				DRIVE_ID  = " "+childPath.substring(0, 2)+" ";
				cmdData = cmdData + DRIVE_ID;
				cmdData = cmdData + " && cd ";
				cmdData = cmdData + " "+childPath+" ";
				cmdData = cmdData + " && ";
				cmdData = cmdData + childCmd;
			}
			cmdData = cmdData + end;

			bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
			bos.write(cmdData.getBytes());

			return fileName;


		} finally {
			if(bos!=null)
				bos.close();

		}
	}


	public String prepareSHFile(JSONObject runJson, int choice)  throws Exception{
		BufferedOutputStream bos = null;

		String 	cmdData = "REM This is a comment! For "+runJson.get("name")+"\n\n";
		String 	childCmd = "";
		String  childPath = "";
		String  DRIVE_ID = "";
		String end = ("EXIT");
		String fileName = batFileLocation + runJson.get("no")+".sh";


		try {

			for (int i = 0; i < runJson.getInt("total_cmds"); i++) {

				childPath = runJson.getJSONArray("paths").getString(i);
				childCmd  = runJson.getJSONArray("cmds").getString(i);

				DRIVE_ID  = " "+childPath.substring(0, 2)+" ";
				cmdData = cmdData + DRIVE_ID;
				cmdData = cmdData + " && cd ";
				cmdData = cmdData + " "+childPath+" ";
				cmdData = cmdData + " && ";
				cmdData = cmdData + childCmd;
				cmdData = cmdData + " && ";
			}
			cmdData = cmdData + end;


			bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
			bos.write(cmdData.getBytes());





			return fileName;


		} finally {
			if(bos!=null)
				bos.close();

		}
	}





	/**
	 * <pre>Sorting JSONArray with key Using Collections class..</pre>
	 * @param jsonArr
	 * @param key
	 * @return
	 * @author VINAY
	 */


	public List<JSONObject> sortJSONArray(JSONArray jsonArr,final String key) {
		List<JSONObject> jsonValues = new ArrayList<JSONObject>();
		for (int i = 0; i < jsonArr.length(); i++) {
			jsonValues.add(jsonArr.getJSONObject(i));
		}
		Collections.sort( jsonValues, new Comparator<JSONObject>() {

			//You can change "Name" with "ID" if you want to sort by ID
			@Override
			public int compare(JSONObject a, JSONObject b) {
				String valA = new String();
				String valB = new String();

				try {
					valA =  a.get(key)+"";
					valB =  b.get(key)+"";
				} 
				catch (JSONException e) {
					e.printStackTrace();
				}

				return valA.compareTo(valB);

			}
		});
		return jsonValues;	
	}

	public String getBatFileLocation() {
		return batFileLocation;
	}

	public void setBatFileLocation(String batFileLocation) {
		this.batFileLocation = batFileLocation;
	}

	private static String OS = null;

	public static String getOsName()
	{
		if(OS == null) { OS = System.getProperty("os.name"); }
		logger.info("System os :: "+OS);
		return OS;
	}
	public static boolean isWindows()
	{
		return getOsName().toLowerCase().contains("windows");
	}

	public String getShFileLocation() {
		return shFileLocation;
	}

	public void setShFileLocation(String shFileLocation) {
		this.shFileLocation = shFileLocation;
	}

	public String objInfo() {
		return "DevToolAutoMaven [autoMavenConf=" + autoMavenConf + ", jsonArr=" + jsonArr + ", defaultCaseJsonArr="
				+ defaultCaseJsonArr + ", totalCases=" + totalCases + ", batFileLocation=" + batFileLocation
				+ ", shFileLocation=" + shFileLocation + ", runList=" + runList + "]";
	}


}
