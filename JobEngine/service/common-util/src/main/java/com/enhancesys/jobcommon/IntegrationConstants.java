package com.enhancesys.jobcommon;

public interface IntegrationConstants 
{
	
	public static final String JOBENGINE_JDBC_DRIVER =  PropertiesFileLoader.getValueFor("JOBENGINE_JDBC_DRIVER");

	public static final String JOBENGINE_JDBC_URL    =  PropertiesFileLoader.getValueFor("JOBENGINE_JDBC_URL");

	public static final String JOBENGINE_JDBC_USER   =  PropertiesFileLoader.getValueFor("JOBENGINE_JDBC_USER");

	public static final String JOBENGINE_JDBC_PASS   =  PropertiesFileLoader.getValueFor("JOBENGINE_JDBC_PASS");
	

}