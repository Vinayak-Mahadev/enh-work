package com.finevm.enh.util;

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
	private Integer portNum;
	private Integer timeOut;

	public Server(){
		init(null);
	}

	public Server(PropType propType){
		if(IWorkConstants.SERVER_LIST!=null) 
			init(IWorkConstants.SERVER_LIST.getJSONObject(propType.toString()));
	}

	public Server(String propType){
		if(IWorkConstants.SERVER_LIST!=null) 
			init(IWorkConstants.SERVER_LIST.getJSONObject(propType));
	}

	private void init(JSONObject server) {
		if(server!=null) {
			id       = server.getString("id");
			hostName = server.getString("hostName");
			username = server.getString("username");
			password = server.getString("password");
			portNum  = server.getInt("portNum");
			timeOut  = server.getInt("timeOut");
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

	@Override
	public String toString() {
		return "Server [id=" + id + ", hostName=" + hostName + ", username=" + username + ", password=" + "*******"
				+ ", portNum=" + portNum + "]";
	}



}
