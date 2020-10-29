package com.enhancesys.integration.services.dataengine.util;

/**
 * <b>Purpose:</b><br>
 * 		Interface DataConstants to declare the required constants for the data engine..<br>
 * <br>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2018<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 *  Sl No   Modified Date        Author</b>
 *  ==============================================
 *  1        24-08-2018          Vinayak Mahadev
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface DataConstants
{

	static class Env
	{
		static final  String PROJECT_LOC = System.getenv("APPSERVER_CONF_PATH") + "/jobengineconf";
	}


	public static final String _PROJECT_LOC = Env.PROJECT_LOC;
	public static final String _JOB_CONF_PATH = _PROJECT_LOC;
	public static final String _JOB_CONF_SPRING_BEANS_PATH = _PROJECT_LOC + "/beans/spring-config.xml";
	public static final String _TEMPLATE_CONFIG_PATH = _PROJECT_LOC + "/JobTemplateConfig.json";
	public static final String _JOB_CONFIG_PATH = _PROJECT_LOC + "/common/";
	public static final String _JOB_CONFIG_COMMON_FILE = _JOB_CONFIG_PATH + "jobConfiguration.properties";
	public static final String _JOB_DUMP_TEMPLATE = _PROJECT_LOC + "/dump-templates/";
	public static final String _JOB_LOG_PATH = _PROJECT_LOC + "/common/log4j.properties";
	public static final String _JOB_SORT_SCRIPTS_PATH = _PROJECT_LOC + "/sorting-scripts/";
	public static final String _JOB_SORT_SCRIPTS_CONF_FILE_PATH = _PROJECT_LOC + "/sorting-scripts/files/";

	public static final String _JOB_PROCESS_FILE_PATH                    = _PROJECT_LOC + "/job_data/";
	public static final String _JOB_PROCESS_RUN_SCRIPT                   = _PROJECT_LOC + "/run-scripts/JobServicesWebInitiator.sh";
	public static final String _JOB_PROCESS_KILL_SCRIPT                  = _PROJECT_LOC + "/run-scripts/KILL_JobServices.sh";
	
	
	public static final String REPORT_SERVICE_BASE_PATH                  = "/enhancesys/reportService/interface";
	public static final long   REPORT_SERVICE_JOB_TIME_LIMIT             = 120;
	public static final String REPORT_SERVICE_PROS_FILE                  = _PROJECT_LOC + "/common/jobConfiguration.properties";
	public static final String REPORT_SERVICE_CONNECTION_ID              = PropertiesLoader.getValueFor("REPORT_SERVICE_CONNECTION_ID");
	public static final String REPORT_SERVICE_SCHEMA                     = PropertiesLoader.getValueFor("REPORT_SERVICE_SCHEMA");
	public static final String REPORT_SERVICE_COLLECTION                 = PropertiesLoader.getValueFor("REPORT_SERVICE_COLLECTION");
	public static final String REPORT_SERVICE_COLLECTION_ID              = PropertiesLoader.getValueFor("REPORT_SERVICE_COLLECTION_ID");
	public static final String REPORT_SERVICE_STORE_FILE_CONNECTION_ID   = PropertiesLoader.getValueFor("REPORT_SERVICE_STORE_FILE_CONNECTION_ID");
	public static final String REPORT_SERVICE_STORE_FILE_SCHEMA          = PropertiesLoader.getValueFor("REPORT_SERVICE_STORE_FILE_SCHEMA");

	// from JOB_SOURCE

	public static final String GIT_BASH_PATH          = PropertiesLoader.getValueFor("GIT_BASH_PATH");
	public static final String JOB_SOURCE             = PropertiesLoader.getValueFor("JOB_SOURCE");
	public static final String TEMPLATE_SOURCE        = PropertiesLoader.getValueFor("TEMPLATE_SOURCE");
	public static final String LOOKUP_CACHE_SIZE      = PropertiesLoader.getValueFor("LOOKUP_CACHE_SIZE");
	public static final String MONGO_QUERY_BATCH_SIZE = PropertiesLoader.getValueFor("MONGO_QUERY_BATCH_SIZE");
	public static final String TEMPLATE_CONFIG_PATH   = PropertiesLoader.getValueFor("TEMPLATE_CONFIG_PATH");
	public static final String JOB_DUMP_TEMPLATE      = PropertiesLoader.getValueFor("JOB_DUMP_TEMPLATE");
	public static final String OUT_FILE_PATH          = PropertiesLoader.getValueFor("OUT_FILE_PATH");
	public static final String CUSTOM_TEMPLATE_CONFIG_PATH        = PropertiesLoader.getValueFor("CUSTOM_TEMPLATE_CONFIG_PATH");

	public static final String POSTGRES_JDBC_DRIVER          = PropertiesLoader.getValueFor("POSTGRES_JDBC_DRIVER");
	public static final String POSTGRES_JDBC_URL             = PropertiesLoader.getValueFor("POSTGRES_JDBC_URL");
	public static final String POSTGRES_JDBC_USER            = PropertiesLoader.getValueFor("POSTGRES_JDBC_USER");
	public static final String POSTGRES_JDBC_PASS            = PropertiesLoader.getValueFor("POSTGRES_JDBC_PASS");
	public static final String POSTGRES_JDBC_SOCKET_TIMEOUT  = PropertiesLoader.getValueFor("POSTGRES_JDBC_SOCKET_TIMEOUT");

	public static final String MONGO_DB_IP1    = PropertiesLoader.getValueFor("MONGO_DB_IP1");
	public static final String MONGO_DB_IP2    = PropertiesLoader.getValueFor("MONGO_DB_IP2");
	public static final String MONGO_DB_IP3    = PropertiesLoader.getValueFor("MONGO_DB_IP3");
	public static final String MONGO_DB_IP4    = PropertiesLoader.getValueFor("MONGO_DB_IP4");
	public static final String MONGO_DB_IP5    = PropertiesLoader.getValueFor("MONGO_DB_IP5");

	public static final String MONGO_DB_PORT1  = PropertiesLoader.getValueFor("MONGO_DB_PORT1");
	public static final String MONGO_DB_PORT2  = PropertiesLoader.getValueFor("MONGO_DB_PORT2");
	public static final String MONGO_DB_PORT3  = PropertiesLoader.getValueFor("MONGO_DB_PORT3");
	public static final String MONGO_DB_PORT4  = PropertiesLoader.getValueFor("MONGO_DB_PORT4");
	public static final String MONGO_DB_PORT5  = PropertiesLoader.getValueFor("MONGO_DB_PORT5");

	public static final String MONGO_DB_USER                = PropertiesLoader.getValueFor("MONGO_DB_USER");
	public static final String MONGO_DB_PASSWORD            = PropertiesLoader.getValueFor("MONGO_DB_PASSWORD");
	public static final String MONGO_DB_DATABASE            = PropertiesLoader.getValueFor("MONGO_DB_DATABASE");
	public static final String MONGO_CONNECTIONS_MAX        = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
	public static final String MONGO_CONNECTION_TIMEOUT_MAX = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
	public static final String MONGO_CONNECTION_KEEP_ALIVE = "false";
	public static final String MONGO_CONNECTION_AUTO_CONNECT = "false";
	public static final String MONGO_CONNECTION_AUTO_CONNECT_RETRY_MAX = "0";
	
	public static final String MONGO_R_123                        = "snoc-r1";
	public static final String MONGO_DB_USER_R_123                = PropertiesLoader.getValueFor("MONGO_DB_USER");
	public static final String MONGO_DB_PASSWORD_R_123            = PropertiesLoader.getValueFor("MONGO_DB_PASSWORD");
	public static final String MONGO_DB_DATABASE_R_123            = PropertiesLoader.getValueFor("MONGO_DB_DATABASE");
	public static final String MONGO_CONNECTIONS_MAX_R123         = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
	public static final String MONGO_CONNECTION_TIMEOUT_MAX_R123  = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
	public static final String MONGO_DB_REPLICAS_R123             = MONGO_DB_IP1 + ":" + MONGO_DB_PORT1 + "," + MONGO_DB_IP2 + ":" + MONGO_DB_PORT2 + "," + MONGO_DB_IP3 + ":" + MONGO_DB_PORT3;

	public static final String MONGO_R_4                          = "snoc-r4";
	public static final String MONGO_DB_R4_USER                   = PropertiesLoader.getValueFor("MONGO_DB_R4_USER");
	public static final String MONGO_DB_R4_PASSWORD               = PropertiesLoader.getValueFor("MONGO_DB_R4_PASSWORD");
	public static final String MONGO_DB_R4_DATABASE               = PropertiesLoader.getValueFor("MONGO_DB_R4_DATABASE");
	public static final String MONGO_CONNECTIONS_MAX_R4           = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
	public static final String MONGO_CONNECTION_TIMEOUT_MAX_R4    = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
	public static final String MONGO_DB_REPLICAS_R4               = MONGO_DB_IP4 + ":" + MONGO_DB_PORT4;

	public static final String MONGO_R_5                          = "snoc-r5";
	public static final String MONGO_DB_STANDALONE_USER           = PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_USER");
	public static final String MONGO_DB_STANDALONE_PASSWORD       = PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_PASSWORD");
	public static final String MONGO_DB_STANDALONE_DATABASE       = PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_DATABASE");
	public static final String MONGO_CONNECTIONS_MAX_R5           = PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX");
	public static final String MONGO_CONNECTION_TIMEOUT_MAX_R5    = PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX");
	public static final String MONGO_DB_REPLICAS_R5               = MONGO_DB_IP5 + ":" + MONGO_DB_PORT5;



	public static final String CONNECTION_ID = "connection-id";
	public static final String SCHEMA_NAME = "schema-name";
	public static final String COLLECTION_NAME = "collection-name";
	public static final String COLLECTION_NAME_PATTERN = "collection-name-pattern";

	public static final String PRIMARY = "Primary";
	public static final String PRIMARY_COLLECTION = "primary-collection";
	public static final String QUERY = "query";
	public static final String PARAMETERS = "parameters";
	public static final String PARAM_NAME = "param-name";
	public static final String PARAM_VALUE = "param-value";
	public static final String PARAM_TYPE = "param-type";
	public static final String MAP_KEY = "map-key";
	public static final String MAP_TO_PARENT = "map-to-parent";
	public static final String RELATIONS = "relations";
	public static final String SORT_BY = "sort-by";
	public static final String PROJECTION = "projection";
	public static final String ALIAS = "alias";

	public static final String CONFIGURATION = "Configuration";
	public static final String FIELD = "field";
	public static final String DEFAULT_VALUE = "default-value";
	public static final String DATE_FORMAT = "date-format";
	public static final String TIME_FLAG = "time-flag";
	public static final String ADD_TIME = "add-time";
	public static final String FROM_DATE_FORMAT = "from-date-format";
	public static final String TO_DATE_FORMAT = "to-date-format";
	public static final String EXPRESSION = "expression";
	public static final String EXPRESSION_FIELDS = "expression-fields";
	public static final String EXPRESSION_TYPE = "expression-type";
	public static final String CONDITIONAL = "conditional";

	public static final String LOOKUP = "Lookup";
	public static final String NAME = "name";
	public static final String MAPPING_FIELD = "mapping-field";
	public static final String LOOKUP_FIELD = "lookup-field";
	public static final String LOOKUP_FIELD_ON_MATCH = "lookup-field-on-match";
	public static final String MATCH_VALUE = "match-value";
	public static final String F_LOOKUP = "lookup";

	public static final String MERGE_CONDITION = "merge-condition";
	public static final String VALIDATE_KEY = "validate-key";
	public static final String INVOKE_METHOD = "invoke-method";

	public static final String PRIMARY_CONFIG = "primaryConfig";
	public static final String CONFIGURATION_CONFIG = "configurationConfig";
	public static final String CURSOR_MAP = "cursorMap";

	public static final String OUTPUT_CONF = "OutputConf";
	public static final String EXCLUDE_FIELDS = "exclude_fields";
	public static final String MONGO = "mongo";
	public static final String POSTGRES = "postgres";
	public static final String FILE = "file";
	public static final String QUEUE = "queue";
	public static final String INITIAL_CONTEXT_FACTORY = "initial-context-factory";
	public static final String PROVIDER_URL = "provider-url";
	public static final String CONNECTION_FACTORY = "connection-factory";
	public static final String QUEUE_NAME = "queue-name";
	public static final String USER = "user";
	public static final String PASSWORD = "password";
	public static final String DRIVER_CLASS = "driver-class";
	public static final String URL = "url";
	public static final String QUERY_PARAM_CONF = "query-param-conf";
	public static final String POSITION = "position";
	public static final String PARAM = "param";
	public static final String EXTENSION = "extension";
	public static final String ZIP_EXTENSION = "zip-extension";
	public static final String FILE_PATH = "file-path";
	public static final String FILE_NAME = "file-name";
	public static final String FILE_NAME_PATTERN = "file-name-pattern";
	public static final String FILE_HEADERS = "file-headers";
	public static final String FIELD_DELIMITER = "field-delimiter";
	public static final String FIELD_CONF = "field-conf";
	public static final String PATH = "path";
	public static final String PARENT_KEY = "parent-key";

	public static final String CONDITION = "condition";
	public static final String COND_FIELD = "cond-field";
	public static final String COND_VALUE = "cond-value";
	public static final String COND_OPERATION = "cond-operation";
	public static final String EQUALS = "equals";
	public static final String NOT_EQUALS = "not-equals";
	public static final String IN = "in";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String DATE_DURATION = "date-duration"; 
	public static final String CONCAT = "concat";
	public static final String OPERATION = "operation";
	public static final String LOCAL_KEY = "local-key";
	public static final String SORT_FILE_PATH = "sort-filepath";
	public static final String SORT_FILE_NAME = "sort-file-name";
	public static final String SORT_CONF = "SortConf";
	public static final String MANDATORY = "mandatory";
	public static final String FETCH_ON_CONDITION = "fetch-on-condition";
	public static final String FETCH_FIELD = "fetch-field";
	public static final String APPEND = "append";
	public static final String SKIP_ON_NOT_EXIST = "skip-on-not-exist";
	public static final String SECONDARY_MATCH = "secondary-match";
	public static final String MANDATORY_FIELDS = "mandatory-fields";
	public static final String WIRTE_BATCH_SIZE = "write-batch-size";
	public static final String PROPERTIES = "properties";
	public static final String QUERY_FIELDS = "query-fields";
	public static final String QUERY_OPERATION = "query-opertaion";
	public static final String OPERATION_FIELDS = "operation-fields";
	public static final String OPERATION_TYPE = "operation-type";
	public static final String COUNT = "count";
	public static final String MINUS = "minus";
	public static final String SUM = "sum";
	public static final String LESS_THAN = "less-than";
	public static final String GREATER_THAN = "greater-than";
	public static final String LESS_THAN_EQUALS = "less-than-equals";
	public static final String GREATER_THAN_EQUALS = "greater-than-equals";

	public static final String APP_CONTEXT = "appContext";
	public static final String INTERFACE_ID = "interfaceId";
	public static final String PROCESS_NAME = "processName";
	public static final String DATA_BEAN = "dataBean";
	public static final String DATA_CONFIG_OBJ = "dataConfigObject";

	public static final String REQUEST_DATA = "requestData";
	public static final String PAYLOAD = "payload";
	public static final String BATCH_ID = "batchId";
	public static final String DATA_LIST = "dataList";
	public static final String ENVELOPE = "envelope";
	public static final String HEADER = "header";
	public static final String UID = "uid";
	public static final String REQ_ID = "req_id";
	public static final String REQ_USER = "user";
	public static final String EVENT_TYPE = "event_type";
	public static final String F_OPERATOR_ID = "operator_id";

	public static final String DATE_TYPE = "date";
	public static final String DATE_NUM = "date-num";
	public static final String STOP_PROCESS = "stop-process";
	public static final String LIMIT = "limit";
	public static final String BATCH_SIZE = "batch-size";
	public static final String MERGE_TYPE = "merge-type";
	public static final String MERGE_TO = "merge-to";
	public static final String INCLUDE_PARENT = "include_parent";
	public static final String ADD_TO_PARENT = "add_to_parent"; //requires merge-to
	public static final String KEY = "key";
	public static final String TYPE = "type";
	public static final String STRING_TYPE = "String";
	public static final String INTEGER_TYPE = "Integer";
	public static final String LONG_TYPE = "Long";
	public static final String FLOAT_TYPE = "Float";
	public static final String DOUBLE_TYPE = "Double"; 
	public static final String BOOLEAN_TYPE = "Boolean"; 
	public static final String OID_TYPE = "Oid";
	public static final String CONDITIONAL_TYPE = "Conditional";
	public static final String MATHEMATICAL_TYPE = "Mathematical";
	public static final String DELETE_FILE_LOCAL = "delete-file-local";
	public static final String PRE_HEADER = "pre-header";
	public static final String STORE_FILE_CONF = "StoreFileConf";
	public static final String REQUEST_UPDATE_CONF = "RequestUpdateConf";
	public static final String EXPRESSION_CONF = "expression-conf";
	public static final String MATH_CONF_DATA = "math-conf-data";
	public static final String COLLECTION_DATA_CONF = "collection-data-conf";
	public static final String LANGUAGE = "language";
	public static final String LANG_UKRAINIAN = "uk";
	public static final String LANG_INDONESIAN = "in";
	public static final String FILE_RECORD_LIMIT = "file-record-limit";
	public static final String IS_MULTIPLE_SHEETS = "is-multiple-sheets";
	public static final String REPORT_NAME = "report-name";
	public static final String IF = "if";
	public static final String ELSE = "else";
	public static final String IS_ENABLE = "is-enable";
	public static final String REQUEST_ID = "request-id";
	public static final String ERROR_CODE = "1001";
	public static final String SUCCESS = "Success";

	public static final String MAPPING_FIELD_TYPE = "mapping-field-type";
	public static final String NUMBER_TYPE = "Number";
	public static final String CACHE_REQUIRED = "cache-required";

	public static final String PRAMS_MAP = "PARAMS_MAP";
	public static final String PG_QUERY = "QUERY";

	public static final String MULTIPLE_ROW_CONF = "multiple-row-conf";
	public static final String AGGREGATE_QUERY = "aggregate-query";
	public static final String NUMBER_OF_DECIMALS = "number-of-decimals";
	public static final String PARAM_VALUE_TYPE = "param-value-type";
	public static final String REPLACE = "replace";
	public static final String TIME_FORMAT_TYPE = "time-format-type";
	public static final String START_OF_THE_DAY = "start-of-the-day";
	public static final String END_OF_THE_DAY = "end-of-the-day";
	public static final String QUERY_CONF = "query-conf";



	public static final long STATUS_ACTIVE = 174L;
	public static final long STATUS_COMPLETED = 173L;
	public static final long STATUS_NO_DATA = 3002L;
	public static final long STATUS_CANCELLED = 771L;
	public static final long STATUS_IN_PROGRESS = 351L;


	// Envelop & Header
	public static final String F_UID = "uid";
	public static final String F_SOURCE = "src";
	public static final String F_UTYPE = "utype";
	public static final String F_CHANNEL = "chnl";
	public static final String F_PASSWORD = "pwd";
	public static final String F_TIME_ZONE = "tz";
	public static final String F_LOCALE = "locale";
	public static final String F_HEADER = "header";
	public static final String F_USER_NAME = "uname";
	public static final String F_PAYLOAD = "payload";
	public static final String F_INTERFACE = "interface";
	public static final String F_ENVELOPE = "envelope";
	public static final String F_REQUEST_TYPE = "rqst"; 
	public static final String F_FILENAME = "FileName"; 

	//Fields 
	public static final String F_START_DATE = "start_date";
	public static final String F_END_DATE = "end_date";
	public static final String F_SEQ = "seq";
	public static final String _ID = "_id";
	public static final String F_ID = "id";
	public static final String F_TRANS_ID = "trans_id";
	public static final String F_REQUEST_ID = "request-id";
	public static final String F_INTERFACE_ID = "interface_id";
	public static final String F_INTERFACE_ID_NUMBER = "interface_id_n";
	public static final String F_INTERFACE_TYPE = "interface_type";
	public static final String F_INTERFACE_TYPE_NUMBER = "interface_type_n";
	public static final String F_NAME = "name";
	public static final String F_NAME_VALUE = "name_v";
	public static final String F_VALUE_VALUE = "value_v";
	public static final String F_FILE_TYPE_VALUE = "file_type_v";
	public static final String FILE_TYPE = "file_type";
	public static final String F_FILE_NAME = "file_name";
	public static final String F_FILE_EXTENSION = "file_ext";
	public static final String F_ACTUAL_FILE_NAME = "actualFileName";
	public static final String F_FILE_NAME_VALUE = "file_name_v";
	public static final String F_CONTROL_FILE_NAME_VALUE = "ctrl_file_name_v";
	public static final String F_CONTROL_FILE_NAME ="controlFileName";
	public static final String F_MODULE_ID_NUMBER = "module_id_n"; 
	public static final String F_ORG_TYPE = "org_type"; 
	public static final String F_ORG_ID = "org_id"; 
	public static final String F_MODULE_ID = "module_id"; 
	public static final String F_FIELD_ID = "field_id"; 
	public static final String F_FILE_ID = "file_id"; 
	public static final String F_FILE_TYPE = "fileType";
	public static final String F_FILE_PATH = "file_path";
	public static final String F_FILE_DATA = "file_data";
	public static final String F_FILE_DETAILS_ID_NUMBER = "file_details_id_n";
	public static final String F_FILE_ID_NUMBER = "file_id_n"; 
	public static final String F_TOTAL_COUNT_NUMBER = "total_count_n"; 
	public static final String F_MESSAGE_VALUE = "message_v"; 
	public static final String F_ERROR_COUNT_NUMBER = "error_count_n"; 
	public static final String F_SUCCESS_COUNT_NUMBER = "success_count_n"; 
	public static final String F_UPLOADED_BY_NUMBER = "uploaded_by_n"; 
	public static final String F_PROCESSED_ON_DATE = "processed_on_dt"; 
	public static final String F_UPLOADED_ON_DATE = "uploaded_on_dt";
	public static final String F_UPDATED_DATE = "updtd_dt";
	public static final String F_STATUS_NUMBER = "status_n";
	public static final String F_MESSAGE = "message"; 
	public static final String F_TOTAL_COUNT = "totalCount"; 
	public static final String F_ERROR_COUNT = "errorCount"; 
	public static final String F_SUCCESS_COUNT = "successCount"; 
	public static final String F_FILTER_COUNT = "filterCount"; 
	public static final String F_UPLOADED_BY = "updloadedBy"; 
	public static final String F_PROCESSED_ON = "processedOn"; 
	public static final String F_UPLOADED_ON = "updloadedOn"; 
	public static final String F_STATUS = "status"; 
	public static final String F_JOB_REQUEST = "job-request";
	public static final String F_JOB_ID = "job-id";
	public static final String F_JOB_DATA_FILE_NAME = "job-data-file-name";
	public static final String F_DELETE_JOB_DATA_FILE = "delete-job-data-file";
	public static final String F_REQUEST_UPDATE_CONF = "RequestUpdateConf";
	public static final String F_STORE_FILE_CONF = "StoreFileConf";
	public static final String F_DOT_JSON = ".json"; 
	public static final String F_DELETE_FILE_LOCAL = "delete-file-local";
	public static final String F_STORE_FILE_ENABLE = "store-file-enable"; 
	public static final String F_JOB_TIME_LIMIT = "job-time-limit"; 
	public static final String F_JOB_DETAILS = "job-details"; 

	//Query fields

	/* ***** Response Messages **** */
	public static final String V_SUCCESS = "Success";
	public static final String V_FAILURE = "Failure";

	/* ***** Error Codes **** */

	public static final int EX_CODE_SUCCESS = 100;	
	public static final int EX_DOWNLOAD_REQUEST_ACCEPTED_CODE = 102;
	public static final int EX_UPLOAD_REQUEST_ACCEPTED_CODE = 103;
	public static final int EX_CODE_DEFAULT_ERROR = -101;
	public static final int EX_CODE_NULL_POINTER = 200;
	public static final int EX_CODE_SCHEMA_VALIDATION = 201;
	public static final int EX_CODE_NO_DATA_FOUND = 1013;
	public static final int EX_CODE_INVALID_FILE_NAME = 1014;
	public static final int EX_CODE_FILE_NAME_EXISTS = 1015;
	public static final int EX_CODE_NO_CONNECTION = 404;
	public static final int EX_USER_VALIDATION_CODE = 1201;

	/* *** Error Messages *** */
	public static final String EX_CODE_NO_CONNECTION_MSG = "Connection not created..!";
	public static final String EX_USER_VALIDATION_MSG = "User validation is failed..!";
	public static final String EX_MSG_NULL_POINTER = "Null Pointer Exception..!";
	public static final String EX_MSG_SCHEMA_VALIDATION = "Request String is invalid..!";
	public static final String EX_MSG_NO_DATA_FOUND = "No data found...!";
	public static final String EX_MSG_INVALID_FILE_NAME = "Invalid file name...!";
	public static final String EX_MSG_FILE_NAME_EXISTS = "File name already present.";

	/***** Schemas Names****/
	public static final String S_SNOC = "snoc";
	public static final String S_SNOC_REPORT = "snoc_report";
	public static final String S_INTERFACE = "interface";

	/***** Collection Names****/
	public static final String C_NOTIFICATION = "notification";
	public static final String C_CHUNKS = "fs.chunks";
	public static final String C_FILES = "fs.files";
	public static final String C_COUNTERS = "counters";
	public static final String C_SOAP_REQUEST_MAPPING = "soap_request_mapping";
	public static final String C_TRANSACTION_SUMMARY = "transaction_summary";
	public static final String C_TRANSACTION_SUMMARY_DETAILS = "transaction_summary_details";
	public static final String C_STATUS_MASTER = "status_master";

	/***** Mongo query constants****/
	public static final String SET_OPERATOR = "$set";
	public static final String M_SET = "$set";
	public static final String M_ADD_TO_SET = "$addToSet";
	public static final String M_PULL = "$pull";
	public static final String INCEREMENT_OPERATOR = "$inc";
	public static final String MONGODB_OR_OPERATION = "$or";
	public static final int INCEREMENT_VALUE_BY_ONE = 1;

	/****** DataBase Constants *****/
	public static final String T_BEGIN_TRANSACTION = "beginTransaction";
	public static final String T_COMMIT_TRANSACTION = "commitTransaction";
	public static final String T_ROLLBACK_TRANSACTION = "rollbackTransaction";


	/*
	 * Exception Handling constants
	 */

	public static final String E_BSON_SCHEMA_VALIDATION_MSG = "Request String is invalid..!";
	public static final String E_BSON_SCHEMA_VALIDATION_MSG_ORG_ID_OR_NODE_ID = "Either org_id or node_id is Manidatory..!";

	public static final String E_NULL_POINTER_MSG = "Null Pointer Exception..!";

	public static final String E_SUCCESS_DEFAULT_MSG = "Operation Successful...!";
	public static final String E_REQUEST_ACCEPTED = "Request Accepted with reference id $ref_id, Please download from Download Reports Menu..!";
	public static final int E_REQUEST_ACCEPTED_CODE = 102;
	public static final String E_USER_VALIDATION_MSG = "User Is Not Valid To Do This Transaction..!";
	public static final int E_USER_VALIDATION_CODE = 1201;
	public static final int E_MONGO_EXCEPTION_CODE = -203;
	public static final int E_NUMBER_FORMAT_CODE = -204;
	public static final String E_NO_NODE_MSG = "No Nodes Found For Given Organization..!";
	public static final int E_NO_NODE_CODE = 1101;
	public static final int E_INVALID_FILE_TYPE = 1401;
	public static final String E_MISSING_UNIQUE_ID_MSG1 = "Unique Id [";
	public static final String E_MISSING_UNIQUE_ID_MSG2 = "] Missing in the data object";
	public static final int E_MISSING_UNIQUE_ID = 1402;

	public static final String E_END_DATE_VALIDATION = "End Date should be greater than Start date";
	public static final String E_START_DATE_VALIDATION = "Start Date should be greater than or equal to Current date";
	public static final String E_DATE_VALIDATION = "Date should not be null";

	public static final String AMQ_BROKER1_JMS_TEMPLATE = "jmsTemplate";

	public static final int ST_ERROR = 1302;
	public static final int ST_INITIATED = 101;

	public static final int ST_CALLBACK_FAILED = 1305;

	/*
	 * Collection application_status_msg
	 */
	public static final String C_APPL_STATUS_MSG = "application_status_msg";
	public static final String F_VALUE = "value";
	public static final String F_ENGLISH = "en";
	public static final String C_LOGIN_TERMS = "login_terms";

	public static final String RESULT_FORMAT = "{"+
			"\"req_id\": \"$requestId$\","+
			"\"res_msg\": \"$responseMessage$\","+
			"\"res_code\": $responseCode$"+
			"$response$"+
			"}";

	/*
	 * Status Ids
	 */
	public static final String ST_FILE_RECEIEVED = "1321";
	public static final String ST_FILE_UPLOADED = "1322";
	public static final String ST_FILE_PROCESSED = "1323";
	public static final String ST_FILE_COMPLETED = "1324";
	public static final String ST_FILE_REJECTED = "1325";
	public static final String ST_FILE_INQUEUE = "1326";
	public static final String ST_FILE_PARTIALLY_REJECTED = "1327";
	public static final String ST_FILE_PARTIALLY_UPLOADED = "1328";

	public static final String CSV_DELIMITER_1 = "\\|";

}
