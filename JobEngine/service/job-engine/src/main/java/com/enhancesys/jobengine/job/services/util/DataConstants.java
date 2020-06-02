package com.enhancesys.jobengine.job.services.util;

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
 *  1        24-08-2018          Suresh Upparu
 *    	-- Base Release
 * </pre>
 * 
 * <br>
 */
public interface DataConstants
{
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
	public static final String F_ID = "_id";
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
	public static final String OID_TYPE = "Oid";
	public static final String STORE_FILE_CONF = "store-file-conf";
	public static final String PRE_HEADER = "pre-header";
	public static final String REQUEST_UPDATE_CONF = "RequestUpdateConf";
	public static final String LANGUAGE = "language";
	public static final String LANG_UKRAINIAN = "uk";
	public static final String LANG_INDONESIAN = "in";
	public static final String FILE_RECORD_LIMIT = "file-record-limit";
	public static final String IS_MULTIPLE_SHEETS = "is-multiple-sheets";
	public static final String REPORT_NAME = "report-name";
	
	public static final String ERROR_CODE = "1001";
	public static final String SUCCESS = "Success";
    public static final long STATUS_ACTIVE = 174L;
    public static final long STATUS_COMPLETED = 173L;
    public static final long STATUS_NO_DATA = 3002L;
    public static final long STATUS_CANCELLED = 771L;
    public static final long STATUS_IN_PROGRESS = 351L;
	public static final String F_STATUS = "status";
	public static final String F_UPDATED_DATE = "updtd_dt";
	public static final String F_FILE_TYPE = "file_type";
	
	public static final String S_SNOC = "snoc";
	
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
	
}
