package com.enhancesys.jobcommon;


public interface Constants 
{

	public static final String _PROJECT_LOC = Env.PROJECT_LOC;
	public static final String _JOB_CONF_PATH = Env.PROJECT_LOC;
	public static final String _JOB_CONF_SPRING_BEANS_PATH = "/home/appuser/snoc/snocconf/jobengineconf/beans/spring-config.xml";
	public static final String _TEMPLATE_CONFIG_PATH = Env.PROJECT_LOC + "/JobTemplateConfig.json";
	public static final String _JOB_CONFIG_PATH = Env.PROJECT_LOC + "common/";
	public static final String _JOB_DUMP_TEMPLATE = Env.PROJECT_LOC + "dump-templates/";
	
	

	//---------------------------------- hibernate conf start----------------------------------------------//

	public static final String _DRIVER   = "driver";
	public static final String _URL      = "url";
	public static final String _USERNAME = "user";
	public static final String _PASSWORD = "password";
	public static final String _SHOW_SQL = "hibernate.show_sql";
	public static final String _DIALECT  = "hibernate.dialect";
	public static final String _METADATA_DEFAULTS = "hibernate.temp.use_jdbc_metadata_defaults";
	public static final String _HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

	//---------------------------------- hibernate conf end----------------------------------------------//
	
	public static final String _RDBMS   = "RDBMS";
	public static final String _MongoDB   = "MongoDB";
	
	public static final String _jobengineSchema   = "jobengine";
	
}
