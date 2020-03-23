package com.finevm.enh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.JSONObject;


public class RDBMS {


	private static RDBMS rdbms;
	private static Connection con;
	private static int db_connection_count = 1* 1000 * 60;
	private static String username;     
	private static String password ;    
	private static String url       ;   
	private static String classForName; 

	private JSONObject serverList = null;

	private RDBMS()
	{
	}

	static synchronized RDBMS GetDB_Class() {
		if (rdbms == null) {
			rdbms = new RDBMS();
		}
		return rdbms;
	}

	public static Connection getConnection(JSONObject serverList, PropType rDBMSType) {
		GetDB_Class();
		return getDBConnection(serverList, rDBMSType);
	}

	public Connection getSingleDBConnection(JSONObject serverList, String rDBMSType)
	{
		if(this.serverList == null)
			this.serverList = serverList;
		if (con == null) {
			try {


				if(rDBMSType != null && this.serverList != null){

					username     = this.serverList.getJSONObject(rDBMSType).getString("username");
					password     = this.serverList.getJSONObject(rDBMSType).getString("password");
					url          = this.serverList.getJSONObject(rDBMSType).getString("url");
					classForName = this.serverList.getJSONObject(rDBMSType).getString("driverName");

				}
				if(username == null && password == null && url == null && classForName == null)
				{
					System.out.println("Making Connection problem because wrong url,username,password");
					return null;
				}
				else
				{
					Class.forName(classForName);
					con = DriverManager.getConnection(url, username, password);
					System.out.println("=> DATABASE Authentication <=\n	URL : "+url+"\n	USERNAME : "+username+"\n	PASSWORD : "+password+"\n");
					System.out.println("<==   RDBMS Connection made    ==> ");
				}

			} 
			catch (ClassNotFoundException e) {
				System.out.println("jdbc Driver not present in our project");
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				System.out.println("Making Connection problem because wrong url,username,password");
				e.printStackTrace();
				return null;
			}
		}

		if(con != null)
			System.out.println("<==   RDBMS connection is given to you ==> " + ++db_connection_count + "  time");
		else 
			db_connection_count = 0;


		return con;
	}


	public static Connection getDBConnection(PropType rDBMSType) {
		if(rDBMSType!=null)
			return getDBConnection(IWorkConstants.RDBMS_LIST.get(rDBMSType.toString()) != null ? IWorkConstants.RDBMS_LIST.getJSONObject( rDBMSType.toString()) : null);
		return null;
	}
	public static Connection getDBConnection(String rDBMSType) {
		if(rDBMSType!=null)
			return getDBConnection(IWorkConstants.RDBMS_LIST.get(rDBMSType.toString())!=null ? IWorkConstants.RDBMS_LIST.getJSONObject( rDBMSType) : null);
		return null;
	}
	public static Connection getDBConnection(JSONObject serverList, PropType rDBMSType) {
		if(rDBMSType!=null && serverList!=null)
			return getDBConnection(serverList.get(rDBMSType.toString())!=null ? serverList.getJSONObject( rDBMSType.toString()) : null);
		return null;
	}
	public static Connection getDBConnection(JSONObject serverList, String rDBMSType)
	{
		if(rDBMSType!=null && serverList!=null)
			return getDBConnection(serverList.get(rDBMSType.toString())!=null ? serverList.getJSONObject( rDBMSType) : null);
		return null;
	}

	public static Connection getDBConnection(JSONObject serverAttr) {
		Connection con = null;
		try 
		{
			if(serverAttr != null){

				username     = serverAttr.getString("username");
				password     = serverAttr.getString("password");
				url          = serverAttr.getString("url");
				classForName = serverAttr.getString("driverName");

			}
			if(username == null && password == null && url == null && classForName == null)
			{
				System.out.println("Making Connection problem because wrong url,username,password");
				return null;
			}
			else
			{
				Class.forName(classForName);
				System.out.println("=> DATABASE Authentication <=\n	URL : "+url+"\n	USERNAME : "+username+"\n	PASSWORD : "+password+"\n");
				con = DriverManager.getConnection(url, username, password);
				if(con == null)
					System.out.println("<==   Failed to connect RDBMS Connection  ==> ");
				else
					System.out.println("<==   RDBMS Connection made    ==> ");
			}

		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println(" RDBMS -- jdbc Driver not present in our project");
			e.printStackTrace();
			return null;
		} 
		catch (Exception e) 
		{
			System.out.println("Making RDBMS Connection problem because wrong url,username,password");
			e.printStackTrace();
			return null;
		}


		return con;
	}



	public static void closeSingleDBConnection() {

		if(RDBMS.con !=null ) {
			try {
				RDBMS.con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		RDBMS.con = null;
		RDBMS.rdbms = null;
		RDBMS.db_connection_count = 0;


	}
}