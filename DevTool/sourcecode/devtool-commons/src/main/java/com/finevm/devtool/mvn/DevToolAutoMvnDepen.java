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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;

public class DevToolAutoMvnDepen {

	private JSONObject autoMavenConf;
	private Scanner scan = null;
	private JSONArray jsonArr =null;
	private JSONArray defaultCaseJsonArr = null;
	private int totalCases =0;
	private int choice = 0;
	private String batFileLocation = DevToolConstants._batFileautoMavenDir;
	private String shFileLocation  = DevToolConstants._shFileautoMavenDir;
	private DevToolMavenConfig mavenConfig;
	private JSONObject autoMvnDepConf;
	public List<JSONObject> runList ;

	private final static Logger logger = Logger.getLogger(DevToolAutoMvnDepen.class);

	public DevToolAutoMvnDepen()
	{
		logger.info(this.getClass().getName()+" obj created :::   "+objInfo());
	}

	public JSONObject getMavenConfigJson() {
		return mavenConfig.getJson();
	}
	public DevToolAutoMvnDepen(JSONObject mavenProjectList)
	{
		try 
		{
			mavenConfig = new DevToolMavenConfig(mavenProjectList);
			autoMvnDepConf = mavenConfig.getJson();
			logger.info(this.getClass().getName()+" obj created :::   "+objInfo());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} 
	}

	public JSONObject getMvnProject(String key, String artifactId)
	{
		JSONObject json = null;
		JSONArray jsonArray = null;
		try 
		{
			jsonArray = autoMvnDepConf.getJSONArray(key);
			//			logger.info("key :: autoMvnDepConf :: "+ key + " :: " + autoMvnDepConf);
			if(jsonArray != null)
			{
				for (Object object : jsonArray) 
				{
					json = (JSONObject) object;
					if(json.get(DevToolConstants._artifactId) != null && json.getString(DevToolConstants._artifactId).equalsIgnoreCase(artifactId))
						return json;
					else
						json = null;
				}
			}
		} 
		catch (Exception e) 
		{
			jsonArray = null;
			json = null;
		}
		return json;
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


	public String initXlsxSheet(String xlsxFileLoc, String sheetName, JSONObject writeJson) throws IOException 
	{

		File file = null;
		FileOutputStream outputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet parentSheet = null;
		XSSFSheet childSheet = null;
		XSSFSheet allSheet = null;
		XSSFSheet propsSheet = null;
		Row propsRow = null;
		Row parentRow = null;
		Row childRow = null;
		Row allRow = null;
		JSONArray allDependencies = null;
		JSONArray allProps = null;
		JSONArray parentDependencies = null;
		JSONArray childDependencies = null;
		List<String> depenHeaderKeyList = new ArrayList<String>(7);
		List<String> proHeaderKeyList = new ArrayList<String>(3);
		try 
		{
			file = new File(xlsxFileLoc + sheetName + ".xlsx");
			outputStream = new FileOutputStream(file);

			workbook = new XSSFWorkbook();
			//Get first sheet from the workbook
			depenHeaderKeyList.add(DevToolConstants._module);
			depenHeaderKeyList.add(DevToolConstants._groupId);
			depenHeaderKeyList.add(DevToolConstants._artifactId);
			depenHeaderKeyList.add(DevToolConstants._version);
			depenHeaderKeyList.add(DevToolConstants._scope);
			depenHeaderKeyList.add(DevToolConstants._type);
			depenHeaderKeyList.add(DevToolConstants._exclusions);

			proHeaderKeyList.add(DevToolConstants._module);
			proHeaderKeyList.add(DevToolConstants._name);
			proHeaderKeyList.add(DevToolConstants._value);

			allProps = new JSONArray();
			allDependencies = new JSONArray();
			parentDependencies = new JSONArray();

			propsSheet = workbook.createSheet("props");
			propsRow = propsSheet.createRow(0);
			writeHeader(workbook, propsRow, proHeaderKeyList);// all header


			allSheet = workbook.createSheet("all");// all sheet
			allRow = allSheet.createRow(0);
			writeHeader(workbook, allRow, depenHeaderKeyList);// all header

			parentSheet = workbook.createSheet(sheetName);// create parent sheet
			parentRow = parentSheet.createRow(0);
			writeHeader(workbook, parentRow, depenHeaderKeyList);

			if(writeJson.get(DevToolConstants._properties) != null)
			{
				JSONObject json = new JSONObject();
				json.put(DevToolConstants._module, sheetName);
				json.put(DevToolConstants._properties, (JSONObject) writeJson.get(DevToolConstants._properties));
				allProps.put(json);
			}

			if(writeJson.get(DevToolConstants._dependencies) != null)
				parentDependencies = writeJson.getJSONArray(DevToolConstants._dependencies);

			for (int i = 0; i < parentDependencies.length(); i++) 
			{
				JSONObject parentDep = (JSONObject) parentDependencies.get(i);
				parentDep.put(DevToolConstants._module, sheetName);
				allDependencies.put(parentDep);

				parentRow = parentSheet.createRow(i+1);
				for (int j = 0; j < depenHeaderKeyList.size(); j++)
					writeCell(workbook, parentRow, j, parentDep, depenHeaderKeyList.get(j));
			}
			autoSizeColumns(parentSheet, depenHeaderKeyList);

			if(writeJson.get(DevToolConstants._moduleDetails) != null && writeJson.getJSONArray(DevToolConstants._moduleDetails) != null)
			{
				JSONArray jsonArr = writeJson.getJSONArray(DevToolConstants._moduleDetails);
				for (int i = 0; i < jsonArr.length(); i++) 
				{
					JSONObject jsonObject = null;
					if(jsonArr.get(i) != null)
						jsonObject = (JSONObject) jsonArr.get(i);

					if(jsonObject != null && jsonObject.get(DevToolConstants._artifactId) != null && jsonObject.get(DevToolConstants._dependencies) != null)
					{
						childSheet = workbook.createSheet(jsonObject.getString(DevToolConstants._artifactId));
						childRow   = childSheet.createRow(0);
						childRow = writeHeader(workbook,childRow, depenHeaderKeyList);

						if(jsonObject.get(DevToolConstants._properties) != null)
						{
							JSONObject json = new JSONObject();
							json.put(DevToolConstants._module, jsonObject.getString(DevToolConstants._artifactId));
							json.put(DevToolConstants._properties, (JSONObject) jsonObject.get(DevToolConstants._properties));
							allProps.put(json);
						}

						childDependencies = jsonObject.getJSONArray(DevToolConstants._dependencies);

						for (int c = 0; c < childDependencies.length(); c++) 
						{
							JSONObject childDepenJson = (JSONObject) childDependencies.get(c);
							childDepenJson.put(DevToolConstants._module, jsonObject.getString(DevToolConstants._artifactId));
							allDependencies.put(childDepenJson);								
							childRow = childSheet.createRow(c+1);
							for (int h = 0; h < depenHeaderKeyList.size(); h++)
								writeCell(workbook, childRow, h, childDepenJson, depenHeaderKeyList.get(h));
						}
						autoSizeColumns(childSheet, depenHeaderKeyList);
					}

				}

				for (int i = 0; i < allDependencies.length(); i++) 
				{
					allRow = allSheet.createRow(i+1);
					JSONObject json = (JSONObject) allDependencies.get(i);
					for (int j = 0; j < depenHeaderKeyList.size(); j++)
						writeCell(workbook, allRow, j, json, depenHeaderKeyList.get(j));
				}
				autoSizeColumns(allSheet, depenHeaderKeyList);
			}

			int propsRowCount = 0;
			for (int i = 0; i < allProps.length(); i++) 
			{
				JSONObject json = allProps.getJSONObject(i);
				JSONObject props = json.getJSONObject(DevToolConstants._properties);
				List<String> list = new ArrayList<String>(props.keySet());
				Collections.sort(list);

				for (String key : list) 
				{
					JSONObject childJson = new JSONObject();
					childJson.put(DevToolConstants._module, json.getString(DevToolConstants._module));
					childJson.put(DevToolConstants._name, key);
					childJson.put(DevToolConstants._value, props.getString(key));
					propsRowCount++;
					propsRow = propsSheet.createRow(propsRowCount);
					for (int j = 0; j < proHeaderKeyList.size(); j++) 
						writePropsCell(workbook, propsRow, j, childJson, proHeaderKeyList.get(j));
				}
			}
			autoSizeColumns(propsSheet, proHeaderKeyList);

			workbook.write(outputStream);
			return file.getAbsolutePath();
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage(), e);
		}
		finally 
		{
			outputStream.close();
			file = null;
			outputStream = null;
			workbook = null;
			parentSheet = null;
			childSheet = null;
			allSheet = null;
			parentRow = null;
			childRow = null;
			allRow = null;
			allDependencies = null;
			parentDependencies = null;
			childDependencies = null;
		}

		return null;
	}

	private void writePropsCell(XSSFWorkbook workbook, Row row, int cellId, JSONObject json, String key)
	{
		Cell cell = row.createCell(cellId);
		XSSFCellStyle  style = workbook.createCellStyle();
		XSSFFont  font = workbook.createFont();
		font.setBold(false);
		style.setFont(font);
		if(json != null && json.has(key) && json.get(key) != null)
			cell.setCellValue(json.get(key).toString());
		else
			cell.setCellValue("");
		cell.setCellStyle(style);
	}

	private void writeCell(XSSFWorkbook workbook, Row row, int cellId, JSONObject json, String key)
	{
		Cell cell = row.createCell(cellId);
		XSSFCellStyle  style = workbook.createCellStyle();
		XSSFFont  font = workbook.createFont();
		font.setBold(false);
		style.setFont(font);
		if(json != null && json.has(key) && json.get(key) != null)
			cell.setCellValue(json.get(key).toString());
		else
			cell.setCellValue("");
		cell.setCellStyle(style);
	}

	private Row writeHeader(XSSFWorkbook workbook, Row row, List<String> header)
	{
		Cell cell = null;
		XSSFCellStyle  style = workbook.createCellStyle();
		XSSFFont  font = workbook.createFont();
		font.setFontHeightInPoints((short)11);
		font.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		font.setBoldweight(XSSFFont.COLOR_NORMAL);
		font.setBold(true);
		font.setColor(HSSFColor.DARK_BLUE.index);
		style.setFont(font);
		for (int h = 0; h < header.size(); h++)
		{
			cell = row.createCell(h);
			cell.setCellValue(header.get(h));
			cell.setCellStyle(style);
		}
		return row;
	}

	private void autoSizeColumns(XSSFSheet sheet, List<String> header)
	{
		for (int i = 0; i < header.size(); i++) 
			sheet.autoSizeColumn(i);
	}
}
