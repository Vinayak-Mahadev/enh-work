package com.enhancesys.jobcommon;


public interface Constants 
{

	public static final String _PROJECT_LOC = Env.PROJECT_LOC;
	public static final String _JOB_CONF_PATH = Env.PROJECT_LOC;
	public static final String _TEMPLATE_CONFIG_PATH = Env.PROJECT_LOC + "/JobTemplateConfig.json";
	public static final String _JOB_CONFIG_PATH = Env.PROJECT_LOC + "common/";
	public static final String _JOB_DUMP_TEMPLATE = Env.PROJECT_LOC + "dump-templates/";
	
	public static final  String TEST_PRO_ID_N_for_JSON = "proId";
	public static final  String TEST_F_NAME_V_for_JSON = "fName";
	public static final  String TEST_L_NAME_V_for_JSON = "lName";

	public static final  String ORG_ORG_ID_N_for_JSON = "org_id";
	public static final  String ORG_ORG_REF_ID_N_for_JSON = "ref_id";
	public static final  String ORG_NAME_V_for_JSON = "org_name";
	public static final  String ORG_DESCRIPTION_V_for_JSON = "org_desc";
	public static final  String ORG_LAST_UPDATED_TIME_DT_for_JSON = "updated_dt";
	public static final  String ORG_CREATED_TIME_DT_for_JSON = "create_dt";
	public static final  String ORG_CLOSED_TIME_DT_for_JSON = "closed_dt";
	public static final  String ORG_SHOP_LIST_for_JSON = "shops";

	public static final  String SHOP_SHOP_ID_N_for_JSON = "shop_id";
	public static final  String SHOP_ORG_ID_N_for_JSON = "org_id";
	public static final  String SHOP_SHOP_TYPE_N_for_JSON = "shop_type";
	public static final  String SHOP_NAME_V_for_JSON = "name";
	public static final  String SHOP_VALUE_V_for_JSON = "desc";
	public static final  String SHOP_LAST_UPDATED_TIME_DT_for_JSON = "updated_dt";
	public static final  String SHOP_CREATED_TIME_DT_for_JSON = "create_dt";
	public static final  String SHOP_CLOSED_TIME_DT_for_JSON = "closed_dt";
	public static final  String SHOP_ITEM_LIST_for_JSON = "items";

	public static final  String ITEM_ITEM_for_JSON = "item";
	public static final  String ITEM_ITEM_ID_N_for_JSON = "id";
	public static final  String ITEM_SHOP_ID_N_for_JSON = "shop_id";
	public static final  String ITEM_NAME_V_for_JSON = "name";
	public static final  String ITEM_VALUE_V_for_JSON = "desc";
	public static final  String ITEM_LAST_UPDATED_TIME_DT_for_JSON = "updated_dt";


	//---------------------------------- hibernate conf start----------------------------------------------//

	public static final String _DRIVER   = "driver";
	public static final String _URL      = "url";
	public static final String _USERNAME = "user";
	public static final String _PASSWORD = "password";
	public static final String _SHOW_SQL = "hibernate.show_sql";
	public static final String _DIALECT  = "hibernate.dialect";
	public static final String _METADATA_DEFAULTS = "hibernate.temp.use_jdbc_metadata_defaults";
	public static final String _HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

	// hibernate conf for JOB_CONSUMER_SERVICE
	public static final String _PREFIX_FOR_JOB_CONSUMER = "jobengine.consumer.service.";
	public static final String _SET_PACKAGES_TO_SCAN_FOR_JOB_CONSUMER_2 = "com.enhancesys.jobengine.consumer.entities";
	public static final String _SET_PACKAGES_TO_SCAN_FOR_JOB_CONSUMER_1 = "com.enhancesys.jobengine.consumer.model";
	public static final String _JOB_CONSUMER_SERVICE_DATASOURCE_CONFIG_LOC = Env.PROJECT_LOC+"common/jobengine/HibernateConfiguration.properties";
	public static final String _JOB_CONSUMER_SERVICE_RESPONCE_TEMP = "{\"payload\":null,\"status-code\":null,\"status-msg\":null,\"responce-date\":null}";
	public static final String _JOB_CONSUMER_SERVICE_LOG4J_CONFIGURATION_FILE = Env.PROJECT_LOC+"logs/ztestLog4j.properties";
	public static final String _JOB_CONSUMER_SERVICE_LOG4J_INTERVAL= "300000";

}
