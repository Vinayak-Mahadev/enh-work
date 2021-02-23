package com.finevm.util;

import org.json.JSONObject;



/**
 * <p>This is bean</p>
 * 
 * @author Vinayak-Mahadev
 *
 */
public class Server {

	private String id;
	private String hostName;
	private String username;
	private String password;
	private String passPath;
	private Integer portNum;
	private Integer timeOut;

	public Server(){
		init(null);
	}


	private void init(JSONObject server) {
		if(server!=null) {
			id       = server.getString("id");
			hostName = server.getString("hostName");
			username = server.getString("username");
			password = server.getString("password");
			portNum  = server.getInt("portNum");
			timeOut  = server.getInt("timeOut");
			if(server.keySet().contains("passPath"))
				passPath = server.getString("passPath");
		}
	}

	public Server(JSONObject serverType){
		init(serverType);
	}

	public Server(JSONObject serverList, String serverName){
		init(serverList.getJSONObject(serverName));
	}

	public Server(String serverList, String serverName){
		JSONObject json = new JSONObject(serverList);
		init(json.getJSONObject(serverName));
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public String getPassPath() {
		return passPath;
	}

	public void setPassPath(String passPath) {
		this.passPath = passPath;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", hostName=" + hostName + ", username=" + username + ", password=" + password
				+ ", passPath=" + passPath + ", portNum=" + portNum + ", timeOut=" + timeOut + "]";
	}




}
