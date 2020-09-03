package com.finevm.devtool.beans;


import org.json.JSONObject;

public class DevToolServer {

	private String id;
	private String hostName;
	private String username;
	private String password;
	private String passPath;
	private Integer portNum;
	private Integer timeOut;


	public DevToolServer(){

	}
	

	public DevToolServer(JSONObject serverJSON){

		id       = serverJSON.getString("id");
		hostName = serverJSON.getString("hostName");
		username = serverJSON.getString("username");
		password = serverJSON.getString("password");
		passPath = serverJSON.getString("passPath");	
		portNum  = serverJSON.getInt("portNum");
		timeOut  = serverJSON.getInt("timeOut");
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
		return "Server [id=" + id + ", hostName=" + hostName + ", username=" + username + ", password=" + "*********"
				+ ", portNum=" + portNum + ", timeOut=" + timeOut + "]";
	}








}
