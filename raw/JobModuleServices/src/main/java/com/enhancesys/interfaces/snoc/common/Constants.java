package com.enhancesys.interfaces.snoc.common;



public class Constants 
{	
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
	
	//Fields 
	public static final String F_START_DATE = "start_date";
	public static final String F_END_DATE = "end_date";
	public static final String F_SEQ = "seq";
	public static final String _ID = "_id";
	public static final String F_ID = "id";
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
	public static final String F_LOCAL_SERVER_VALUE = "local_server_v";
	public static final String F_DIRECTION = "Dir";
	public static final String F_BACK_UP_DIRECTION = "BkpDir";
	public static final String F_DESTINATION_DIRECTION = "destDir";
	public static final String F_REJECTED_DIRECTION = "RejectedDir";
	public static final String F_FILTER_BACKUP_DIRECTION = "FilterBkpDir";
	public static final String F_REJECTED_BACK_UP_DIRECTION = "RejectedBkpDir";
	public static final String F_REJECTED_FILE_TYPE = "R";
	public static final String F_FILTER_FILE_TYPE = "F";
	public static final String F_SUCCESS_FILE_TYPE = "S";
	public static final String F_UPLOAD = "upload";
	public static final String F_TRANS_TYPE_NUMBER = "trans_type_n";
	public static final String F_FILTER_COUNT_NUMBER = "filter_count_n";
	
	//Query fields
	public static final String QF_MODULE_ID = "MODULE_ID";
	public static final String QF_STATUS_ID = "STATUS_ID";
	public static final String QF_INTERFACE_ID = "INTERFACE_ID";
	public static final String QF_FILE_ID = "FILE_ID";
	public static final String QF_TRANS_TYPE_ID = "TRANS_TYPE_ID";
	public static final String QF_INTERFACE_TYPE_ID = "INTERFACE_TYPE_ID";
	
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