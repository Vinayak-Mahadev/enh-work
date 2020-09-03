package com.finevm.devtool.puttyopr;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.beans.DevToolServer;

public class DevToolPutty {

	private DevToolServer server;
	private String puttyPath;
	private String plinkPath;
	private String logFilename;
	private String logFileLocation;
	private Process process = null;
	private final static Logger logger = Logger.getLogger(DevToolPutty.class);

	public String getPlinkPath() {
		return plinkPath;
	}

	public void setPlinkPath(String plinkPath) {
		this.plinkPath = plinkPath;
	}

	public String getPuttyPath() 
	{
		return puttyPath;
	}

	public void setPuttyPath(String puttyPath)
	{
		this.puttyPath = puttyPath;
	}

	public DevToolPutty() {
		logger.info(this.getClass()+" Object created");
	}
	public DevToolPutty(String puttyPath, String plinkPath) {
		this();
		this.puttyPath = puttyPath;
		this.plinkPath = plinkPath;				
	}
	public DevToolPutty(JSONObject serverType) {
		init(serverType);
	}

	public void init(JSONObject serverType) {
		server = new DevToolServer();
		server.setId( serverType.getString("id"));
		server.setHostName(serverType.getString("hostName"));
		server.setUsername( serverType.getString("username"));
		server.setPassword(serverType.getString("password"));
		server.setPassPath(serverType.getString("passPath"));
		server.setPortNum(serverType.getInt("portNum"));
		server.setTimeOut(serverType.getInt("timeOut"));
		logger.info(this+" init...");
	}

	/**
	 * <pre>open the putty session with username, password and hostName from Server class</pre>
	 * @author VINAY
	 * @throws Exception 
	 */
	public void launch() throws Exception {
		Runtime runtime  = null;
		String  runCmd   = null;
		try 
		{
			if(server.getPassPath() != null && !server.getPassPath().trim().isEmpty()) 
				runCmd =  puttyPath+" -ssh -l "+server.getUsername()+" -i "+server.getPassPath()+" "+server.getHostName()+"";
			else
				runCmd =  puttyPath+" -ssh -l "+server.getUsername()+" -pw "+server.getPassword()+" "+server.getHostName()+"";

			logger.debug("RUN CMD  "+runCmd);

			runtime = Runtime.getRuntime();
			runtime.exec(runCmd);

			logger.info("Putty launched....");

		} 
		finally 
		{
			runtime  = null;
			runCmd   = null;
		}
	}

	public String plinkSeeProcess(String processName) throws Exception {
		Runtime runtime  = null;
		String  runCmd   = null;
		String processCmd  = "\""+"ps -ef|grep REPLACE_ELEMENT"+"\"";

		try 
		{

			logger.info("plinkSeeProcess launched....");

			if(processName != null && !processName.trim().isEmpty()) {
				processCmd = processCmd.replaceAll("REPLACE_ELEMENT", processName).trim();
			}
			else {
				logger.info("Process name not empty..");
				return null;
			}

			if(server.getPassPath() != null && !server.getPassPath().trim().isEmpty()) 
				runCmd =  plinkPath+" -ssh -l "+server.getUsername()+" -i "+server.getPassPath()+" "+server.getHostName()+" "+processCmd;
			else
				runCmd =  plinkPath+" -ssh -l "+server.getUsername()+" -pw "+server.getPassword()+" "+server.getHostName()+" "+processCmd;

			logger.debug("RUN CMD  "+runCmd);

			runtime = Runtime.getRuntime();
			process = runtime.exec(runCmd);
			//process.waitFor();
			return  getDataFromRuntimeProcess(process);

		} 
		finally 
		{
			runtime  = null;
			runCmd   = null;
		}
	}

	public String plinkKillProcess(String processName) throws Exception {
		Runtime runtime  = null;
		String  runCmd   = null;
		String killCmd  = "\""+"kill -9  ` ps -ef|grep 'REPLACE_ELEMENT*' | grep -v grep |  awk '{print $2}' ` > /dev/null 2>/dev/null"+"\"";
		try 
		{
			if(processName != null && !processName.trim().isEmpty()) {
				killCmd = killCmd.replaceAll("REPLACE_ELEMENT", processName).trim();
			}
			else {
				logger.info("Process name not empty..");
				return null;
			}
			logger.info("plinkKillProcess launched....");

			if(server.getPassPath() != null && !server.getPassPath().trim().isEmpty())
				runCmd =  plinkPath+" -ssh -l "+server.getUsername()+" -i "+server.getPassPath()+" "+server.getHostName()+" "+killCmd;
			else
				runCmd =  plinkPath+" -ssh -l "+server.getUsername()+" -pw "+server.getPassword()+" "+server.getHostName()+" "+killCmd;

			logger.debug("RUN CMD  "+runCmd);

			runtime = Runtime.getRuntime();
			process = runtime.exec(runCmd);
			process.waitFor();
			return  getDataFromRuntimeProcess(process);
		} 
		finally 
		{
			runtime  = null;
			runCmd   = null;
		}
	}
	public String plinkRunScriptProcess(String processName) throws Exception {
		Runtime runtime  = null;
		String  runCmd   = null;
		String runScriptCmd  = "REPLACE_ELEMENT";
		try 
		{
			if(processName != null && !processName.trim().isEmpty()) {
				runScriptCmd = runScriptCmd.replaceAll("REPLACE_ELEMENT", processName).trim();
			}
			else {
				logger.info("Process name not empty..");
				return null;
			}

			logger.info("plinkRunScriptProcess launched....");

			if(server.getPassPath() != null && !server.getPassPath().trim().isEmpty())
				runCmd =  plinkPath+" -ssh -l "+server.getUsername()+" -i "+server.getPassword()+" "+server.getHostName()+" "+"\""+runScriptCmd+"\"";
			else
				runCmd =  plinkPath+" -ssh -l "+server.getUsername()+" -pw "+server.getPassword()+" "+server.getHostName()+" "+"\""+runScriptCmd+"\"";

			logger.debug("RUN CMD  "+runCmd);

			runtime = Runtime.getRuntime();
			process = runtime.exec(runCmd);
			//process.waitFor();
			return  getDataFromRuntimeProcess(process);

		} 
		finally 
		{
			runtime  = null;
			runCmd   = null;
		}
	}



	public void destory()throws Exception{
		if(process != null) {
			process.destroy();
			logger.debug(process+"   :: process killed...");
			process = null;
		}
	}
	public String getLogFilename() {
		return logFilename;
	}

	public void setLogFilename(String logFilename) {
		this.logFilename = logFilename;
	}

	public String getLogFileLocation() {
		return logFileLocation;
	}

	public void setLogFileLocation(String logFileLocation) {
		this.logFileLocation = logFileLocation;
	}

	private String getDataFromRuntimeProcess(Process proc) {
		String data = "";
		try 
		{
			BufferedReader stdInput = new BufferedReader(new 
					InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new 
					InputStreamReader(proc.getErrorStream()));

			data = data + "Here is the standard output of the command:\n\n";
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				//System.out.println(s);
				data  = data + s +"\n";
			}

			data  = data + "\n\nHere is the standard error of the command (if any):\n" +"\n";
			while ((s = stdError.readLine()) != null) {
				data  = data + s +"\n";
			}

		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			data = "Failed..";
		}
		return data;
	}
}