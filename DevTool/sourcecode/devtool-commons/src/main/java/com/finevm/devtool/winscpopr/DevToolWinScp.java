package com.finevm.devtool.winscpopr;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.beans.DevToolServer;

public class DevToolWinScp {

	private final DevToolServer server = new DevToolServer();
	private String winscpPath;
	private String ftpsORsftp;

	private Process process = null;
	private final static Logger logger = Logger.getLogger(DevToolWinScp.class);
	public DevToolWinScp(JSONObject serverType, String winscpPath, String ftpsORsftp) {

		init(serverType, winscpPath, ftpsORsftp);
	}

	public DevToolWinScp() {
		super();
		logger.debug("DevToolWinScp obj created..");
	}

	public void init(JSONObject serverType, String winscpPath, String ftpsORsftp) {
		server.setId( serverType.getString("id"));
		server.setHostName(serverType.getString("hostName"));
		server.setUsername( serverType.getString("username"));
		server.setPassword(serverType.getString("password"));
		server.setPortNum(serverType.getInt("portNum"));
		server.setTimeOut(serverType.getInt("timeOut"));
		server.setPassPath(serverType.getString("passPath"));

		this.ftpsORsftp = ftpsORsftp.trim();
		this.winscpPath = winscpPath.trim();
		logger.debug("Winscp inited  with conf:: "+toString());
	}


	/**
	 * <pre>open the winscp session with username, password and hostName from Server class</pre>
	 * @throws IOException
	 * @author VINAY
	 */
	public Process launch() throws IOException {
		try 
		{
			Runtime runtime  = null;
			String  runCmd   = null;
			if(server.getPassPath() != null && !server.getPassPath().trim().isEmpty()) 
				runCmd =  winscpPath+" "+"scp"+"://"+server.getUsername().trim()+"@"+server.getHostName().trim()+":"+server.getPortNum()+"/ /privatekey="+server.getPassPath().trim();
			else
				runCmd =  winscpPath+" "+ftpsORsftp+"://"+server.getUsername().trim()+":"+server.getPassword().trim()+"@"+server.getHostName().trim()+"";

			logger.debug("RUN CMD  "+runCmd);
			runtime = Runtime.getRuntime();

			process = runtime.exec(runCmd);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return process;
	}


	public void destory()throws Exception{
		process.destroy();
	}
	public String getFtpsORsftp() {
		return ftpsORsftp;
	}
	public void setFtpsORsftp(String ftpsORsftp) {
		this.ftpsORsftp = ftpsORsftp;
	}
	public String getWinScpPath() {
		return winscpPath;
	}
	public void setWinScpPath(String winscpPath){
		this.winscpPath = winscpPath;
	}

	@Override
	public String toString() {
		return "DevToolWinScp [server=" + server + ", winscpPath=" + winscpPath + ", ftpsORsftp=" + ftpsORsftp + "]";
	}


}
