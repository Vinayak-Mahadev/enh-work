package com.enhancesys.integration.snoc.services.util;

import com.enhancesys.integration.snoc.props.PropertiesLoader;

public interface IntegrationConstants 
{
	/*


	public static final boolean ENCRYPT_DECRYPT_FLAG = Boolean.parseBoolean(PropertiesLoader.getValueFor("ENCRYPT_DECRYPT_FLAG"));
	
	public static final int PORT_FOR_SMS = Integer.parseInt(PropertiesLoader.getValueFor("PORT_FOR_SMS"));

	public static final int CHUNK_SMS_SIZE = Integer.parseInt(PropertiesLoader.getValueFor("CHUNK_SMS_SIZE"));

	public static final Long FILE_INQUEUE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_INQUEUE_STATUS"));

	public static final Long FILE_RECEIVED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_RECEIVED_STATUS"));

	public static final Long FILE_UPLOADED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_UPLOADED_STATUS"));

	public static final Long FILE_PROCESSED = Long.parseLong(PropertiesLoader.getValueFor("FILE_PROCESSED"));

	public static final Long FILE_COMPLETED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_COMPLETED_STATUS"));

	public static final Long FILE_REJECTED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_REJECTED_STATUS"));

	public static final Long FILE_PARTIAL_REJECT_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_PARTIAL_REJECT_STATUS"));

	public static final Long IN_QUEUE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("IN_QUEUE_STATUS"));

	public static final Long PROCESSED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("PROCESSED_STATUS"));

	public static final Long ERROR_STATUS = Long.parseLong(PropertiesLoader.getValueFor("ERROR_STATUS"));

	public static final Long RES_AVAILABLE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("RES_AVAILABLE_STATUS"));

	public static final Long COMPLETED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("COMPLETED_STATUS"));

	public static final Long QUEUE_ATTEMPT_FAILED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("QUEUE_ATTEMPT_FAILED_STATUS"));

	public static final Long CALL_BACK_FAILED_STATUS = Long.parseLong(PropertiesLoader.getValueFor("CALL_BACK_FAILED_STATUS"));

	public static final String CALL_BACK_METHOD_NAME = PropertiesLoader.getValueFor("CALL_BACK_METHOD_NAME");

	public static final String CALLBACK_RETRY_LIMIT_ATTR = PropertiesLoader.getValueFor("CALLBACK_RETRY_LIMIT_ATTR");
	
	public static final String CALLBACK_RETRY_SLEEP_TIME_ATTR = PropertiesLoader.getValueFor("CALLBACK_RETRY_SLEEP_TIME_ATTR");

	public static final String CALL_BACK_JNDI_NAME = PropertiesLoader.getValueFor("CALL_BACK_JNDI_NAME");

	public static final Long ASYNC_QUEUE_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_QUEUE_INTERFACE_TYPE"));

	public static final Long ASYNC_FILE_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_FILE_INTERFACE_TYPE"));

	public static final Long ASYNC_WS_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_WS_INTERFACE_TYPE"));

	public static final Long ASYNC_PULL_DATA_TO_FILE_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_INTERFACE_TYPE"));

	public static final Long SYNC_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("SYNC_INTERFACE_TYPE"));

	public static final Long SEND_TRANS_TYPE = Long.parseLong(PropertiesLoader.getValueFor("SEND_TRANS_TYPE"));

	public static final Long RECEIVE_TRANS_TYPE = Long.parseLong(PropertiesLoader.getValueFor("RECEIVE_TRANS_TYPE"));

	public static final Long RECEIVE_SEND_TRANS_TYPE = Long.parseLong(PropertiesLoader.getValueFor("RECEIVE_SEND_TRANS_TYPE"));

	public static final Long OUT_FILE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("OUT_FILE_TYPE"));

	public static final Long IN_FILE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("IN_FILE_TYPE"));

	public static final Long QUEUE_ERROR_CODE = Long.parseLong(PropertiesLoader.getValueFor("QUEUE_ERROR_CODE"));

	public static final Long FILE_ERROR_CODE = Long.parseLong(PropertiesLoader.getValueFor("FILE_ERROR_CODE"));

	public static final Long SYNC_ERROR_CODE = Long.parseLong(PropertiesLoader.getValueFor("SYNC_ERROR_CODE"));

	public static final Long SERVICE_ERROR_CODE = Long.parseLong(PropertiesLoader.getValueFor("SERVICE_ERROR_CODE"));

	public static final String HOST_FOR_SMS = PropertiesLoader.getValueFor("HOST_FOR_SMS");

	public static final String SMSC_USER_ID = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMSC_USER_ID")) : PropertiesLoader.getValueFor("SMSC_USER_ID");

	public static final String SMSC_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMSC_PASSWORD")): PropertiesLoader.getValueFor("SMSC_PASSWORD");

	public static final String SMSC_SRC_NO = PropertiesLoader.getValueFor("SMSC_SRC_NO");

	public static final String MSISDN_FOR_SMS = PropertiesLoader.getValueFor("MSISDN_FOR_SMS");

	public static final String SYSTEM_TYPE_SMSC = PropertiesLoader.getValueFor("SYSTEM_TYPE_SMSC");



	public static final String REMOTE_HOST_ATTR = PropertiesLoader.getValueFor("REMOTE_HOST_ATTR");

	public static final String REMOTE_PORT_ATTR = PropertiesLoader.getValueFor("REMOTE_PORT_ATTR");

	public static final String REMOTE_USER_ATTR = PropertiesLoader.getValueFor("REMOTE_USER_ATTR");

	public static final String REMOTE_PASSWORD_ATTR = PropertiesLoader.getValueFor("REMOTE_PASSWORD_ATTR");

	public static final String REMOTE_CLIENT_ID_ATTR = PropertiesLoader.getValueFor("REMOTE_CLIENT_ID_ATTR");

	public static final String REMOTE_CLIENT_NAME_ATTR = PropertiesLoader.getValueFor("REMOTE_CLIENT_NAME_ATTR");

	public static final String REMOTE_WS_USER_ATTR = PropertiesLoader.getValueFor("REMOTE_WS_USER_ATTR");

	public static final String REMOTE_WS_PASSWORD_ATTR = PropertiesLoader.getValueFor("REMOTE_WS_PASSWORD_ATTR");

	public static final String REMOTE_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_DIR_ATTR");

	public static final String REMOTE_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_BACKUP_DIR_ATTR");

	public static final String DATE_DURATION_ATTR = PropertiesLoader.getValueFor("DATE_DURATION_ATTR");

	public static final String REMOTE_CTRL_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_CTRL_DIR_ATTR");

	public static final String REMOTE_CTRL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_CTRL_BACKUP_DIR_ATTR");

	public static final String REMOTE_FILE_ATTR = PropertiesLoader.getValueFor("REMOTE_FILE_ATTR");

	public static final String REMOTE_FILE_SET_ATTR = PropertiesLoader.getValueFor("REMOTE_FILE_SET_ATTR");

	public static final String REMOTE_FILE_NAME_FORMAT_ATTR = PropertiesLoader.getValueFor("REMOTE_FILE_NAME_FORMAT_ATTR");

	public static final String REMOTE_FILE_FORMAT = PropertiesLoader.getValueFor("REMOTE_FILE_FORMAT");

	public static final String CONTROL_FILE_NAME = PropertiesLoader.getValueFor("CONTROL_FILE_NAME");

	public static final String CONTROL_FILE_FORMAT = PropertiesLoader.getValueFor("CONTROL_FILE_FORMAT");

	public static final String LOCAL_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_DIR_ATTR");

	public static final String LOCAL_CTRL_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_CTRL_DIR_ATTR");

	public static final String LOCAL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_BACKUP_DIR_ATTR");
	
	public static final String LOCAL_FILTER_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_FILTER_BACKUP_DIR_ATTR");
	
	public static final String LOCAL_FILTER_CTRL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_FILTER_CTRL_BACKUP_DIR_ATTR");

	public static final String BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("BACKUP_DIR_ATTR");

	public static final String TEMP_FILE_FORMAT = PropertiesLoader.getValueFor("TEMP_FILE_FORMAT");

	public static final String REQUEST_FILE_RECORD_LIMIT_ATTR = PropertiesLoader.getValueFor("REQUEST_FILE_RECORD_LIMIT_ATTR");

	public static final String PUBLISHER_INTERFACE_ATTR = PropertiesLoader.getValueFor("PUBLISHER_INTERFACE_ATTR");

	public static final Long WAITING_TIME = Long.parseLong(PropertiesLoader.getValueFor("WAITING_TIME"));

	public static final String HORNET_QUEUE_NAME_ATTR = PropertiesLoader.getValueFor("HORNET_QUEUE_NAME_ATTR");

	public static final String TIBCO_QUEUE_NAME_ATTR = PropertiesLoader.getValueFor("TIBCO_QUEUE_NAME_ATTR");

	public static final String ACTIVE_MQ_QUEUE_NAME_ATTR = PropertiesLoader.getValueFor("ACTIVE_MQ_QUEUE_NAME_ATTR");

	public static final String CONSUME_NO_OF_ITEMS_ATTR = PropertiesLoader.getValueFor("CONSUME_NO_OF_ITEMS_ATTR"); 

	public static final String INITIAL_CONTEXT_FACTORY_ATTR = PropertiesLoader.getValueFor("INITIAL_CONTEXT_FACTORY_ATTR");

	public static final String PROVIDER_URL_ATTR = PropertiesLoader.getValueFor("PROVIDER_URL_ATTR");

	public static final String URL_PKG_PREFIXES_ATTR = PropertiesLoader.getValueFor("URL_PKG_PREFIXES_ATTR");

	public static final String SECURITY_AUTHENTICATION_ATTR = PropertiesLoader.getValueFor("SECURITY_AUTHENTICATION_ATTR");

	public static final String REMOTE_CONNECTION_FACTORY_ATTR = PropertiesLoader.getValueFor("REMOTE_CONNECTION_FACTORY_ATTR");

	public static final String SECURITY_PRINCIPAL_ATTR = PropertiesLoader.getValueFor("SECURITY_PRINCIPAL_ATTR");

	public static final String SECURITY_CREDENTIALS_ATTR = PropertiesLoader.getValueFor("SECURITY_CREDENTIALS_ATTR");

	public static final String JMS_SERVER_ATTR = PropertiesLoader.getValueFor("JMS_SERVER_ATTR");

	public static final String HORNET_Q_SERVER = PropertiesLoader.getValueFor("HORNET_Q_SERVER");
	
	public static final String TIBCO_Q_SERVER = PropertiesLoader.getValueFor("TIBCO_Q_SERVER");

	public static final String ACTIVE_MQ_Q_SERVER = PropertiesLoader.getValueFor("ACTIVE_MQ_Q_SERVER");

	public static final String PUBLISHER_CON_FACTORY_NAME_ATTR = PropertiesLoader.getValueFor("PUBLISHER_CON_FACTORY_NAME_ATTR");

	public static final String PUBLISHER_PROTOCOL_ATTR = PropertiesLoader.getValueFor("PUBLISHER_PROTOCOL_ATTR");

	public static final String PUBLISHER_HOST_ATTR = PropertiesLoader.getValueFor("PUBLISHER_HOST_ATTR");

	public static final String PUBLISHER_PORT_ATTR = PropertiesLoader.getValueFor("PUBLISHER_PORT_ATTR");

	public static final String PUBLISHER_USER_ATTR = PropertiesLoader.getValueFor("PUBLISHER_USER_ATTR");

	public static final String PUBLISHER_PASSWORD_ATTR = PropertiesLoader.getValueFor("PUBLISHER_PASSWORD_ATTR");

	public static final String REMOTE_WS_URL_ATTR = PropertiesLoader.getValueFor("REMOTE_WS_URL_ATTR");

	public static final String SERVICE_TYPE_ATTR = PropertiesLoader.getValueFor("SERVICE_TYPE_ATTR");

	public static final String SOAP_SERVICE_TYPE = PropertiesLoader.getValueFor("SOAP_SERVICE_TYPE");

	public static final String REST_SERVICE_TYPE = PropertiesLoader.getValueFor("REST_SERVICE_TYPE");

	public static final Long INTERFACE_ENTITY_TYPE = Long.parseLong(PropertiesLoader.getValueFor("INTERFACE_ENTITY_TYPE"));

	public static final Long INTERFACE_APPLICATION_ID = Long.parseLong(PropertiesLoader.getValueFor("INTERFACE_APPLICATION_ID"));

	public static final Long INTERFACE_SUMMARY_LIMIT = Long.parseLong(PropertiesLoader.getValueFor("INTERFACE_SUMMARY_LIMIT"));

	public static final Long INTERFACE_FILE_SUMMARY_LOOKUP_ID = Long.parseLong(PropertiesLoader.getValueFor("INTERFACE_FILE_SUMMARY_LOOKUP_ID"));

	public static final String INTEGRATION_XML_REQUEST_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_XML_REQUEST_TEMPLATE_PATH");

	public static final String INTEGRATION_XML_RESPONSE_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_XML_RESPONSE_TEMPLATE_PATH");

	public static final String TEMPLATE_NAME_ATTR =  PropertiesLoader.getValueFor("TEMPLATE_NAME_ATTR");

	public static final String RETRY_COUNT_ATTR = PropertiesLoader.getValueFor("RETRY_COUNT_ATTR");

	public static final String TRANS_ID_PATH_ATTR = PropertiesLoader.getValueFor("TRANS_ID_PATH_ATTR");

	public static final String TRANS_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("TRANS_ID_IN_CONVERTER_TEMPLATE");

	public static final String EXT_REF_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("EXT_REF_ID_IN_CONVERTER_TEMPLATE");

	public static final String INTEGRATION_XML_TO_CSV_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_XML_TO_CSV_TEMPLATE_PATH");

	public static final List<String> UPLOAD_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("UPLOAD_INTERFACE_LIST").split(","));

	public static final List<String> NOTIFICATION_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("NOTIFICATION_INTERFACE_LIST").split(","));

	public static final List<String> PARTY_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("PARTY_INTERFACE_LIST").split(","));

	public static final List<String> REQ_ONLY_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("REQ_ONLY_INTERFACE_LIST").split(","));

	public static final List<String> REQ_ONLY_UPLOAD_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("REQ_ONLY_UPLOAD_INTERFACE_LIST").split(","));

	public static final List<String> ASYNC_PULL_DATA_TO_FILE_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_INTERFACE_LIST").split(","));

	public static final List<String> ASYNC_PULL_DATA_TO_FILE_FROM_MONGO_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_FROM_MONGO_INTERFACE_LIST").split(","));

	public static final List<String> JSON_CONVERTER_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("JSON_CONVERTER_INTERFACE_LIST").split(","));

	public static final List<String> INVENTORY_WRITE_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("INVENTORY_WRITE_INTERFACE_LIST").split(","));

	public static final List<String> STOCK_BALANCE_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("STOCK_BALANCE_INTERFACE_LIST").split(","));		
	public static final Long EMAIL_NOTIFICATION_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("EMAIL_NOTIFICATION_INTERFACE_ID"));

	public static final Long SMS_NOTIFICATION_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("SMS_NOTIFICATION_INTERFACE_ID"));

	public static final String RECEIVED_FILE_TYPE_ATTR = PropertiesLoader.getValueFor("RECEIVED_FILE_TYPE_ATTR");

	public static final String REQUEST_FILE_TYPE = PropertiesLoader.getValueFor("REQUEST_FILE_TYPE");

	public static final String RESPONSE_FILE_TYPE = PropertiesLoader.getValueFor("RESPONSE_FILE_TYPE");

	public static final String TRANS_ID_DELIMETER = PropertiesLoader.getValueFor("TRANS_ID_DELIMETER");

	public static final String XML_TRANSACTION_TYPE = PropertiesLoader.getValueFor("XML_TRANSACTION_TYPE");

	public static final String CSV_TRANSACTION_TYPE = PropertiesLoader.getValueFor("CSV_TRANSACTION_TYPE");

	public static final String XML_DATA_CONVERTER_ATTR = PropertiesLoader.getValueFor("XML_DATA_CONVERTER_ATTR");

	public static final String RES_TEMPLATE_TAG_ATTR = PropertiesLoader.getValueFor("RES_TEMPLATE_TAG_ATTR");

	public static final List<String> COMPLETED_STATUS_INTERFACE_IDS = Arrays.asList(PropertiesLoader.getValueFor("COMPLETED_STATUS_INTERFACE_IDS").split(","));

	public static final Long FILE_SUMMARY_STATUS = Long.parseLong(PropertiesLoader.getValueFor("FILE_SUMMARY_STATUS"));

	public static final Long INTERMEDIATE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("INTERMEDIATE_STATUS"));

	public static final String INTEGRATION_CSV_TO_JSON_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_CSV_TO_JSON_TEMPLATE_PATH");

	public static final String CONTROL_FILE_DELIMETER = PropertiesLoader.getValueFor("CONTROL_FILE_DELIMETER");

	public static final String OBJECT_CHAR = PropertiesLoader.getValueFor("OBJECT_CHAR");

	public static final String ARRAY_CHAR = PropertiesLoader.getValueFor("ARRAY_CHAR");

	public static final String DEFAULT_VAL_DELIMITER = PropertiesLoader.getValueFor("DEFAULT_VAL_DELIMITER");

	public static final String NEXT_RECORD_DELIMITER = PropertiesLoader.getValueFor("NEXT_RECORD_DELIMITER");

	public static final String FIELD_DELIMITER = PropertiesLoader.getValueFor("FIELD_DELIMITER");

	public static final String FIELD_SPLIT_DELIMITER = PropertiesLoader.getValueFor("FIELD_SPLIT_DELIMITER");

	public static final String KEY_VALUE_DELIMITER = PropertiesLoader.getValueFor("KEY_VALUE_DELIMITER");

	public static final String TIMESTAMP = PropertiesLoader.getValueFor("TIMESTAMP");

	public static final String DATE = PropertiesLoader.getValueFor("DATE");

	public static final String DEFAULT_VALUE = PropertiesLoader.getValueFor("DEFAULT_VALUE");

	public static final String PARTNER_SET = PropertiesLoader.getValueFor("PARTNER_SET");

	public static final String COMMA_DELIMITER = PropertiesLoader.getValueFor("COMMA_DELIMITER");

	public static final Integer  START_FROM = Integer.valueOf(PropertiesLoader.getValueFor("START_FROM"));

	//	public static String REMOTE_FILE_SET = PropertiesLoader.getValueFor("REMOTE_FILE_SET");

	public static final String LOCAL_CTRL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_CTRL_BACKUP_DIR_ATTR");

	public static final String INTEGRATION_CSV_REQUEST_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_CSV_REQUEST_TEMPLATE_PATH");

	public static final String ORDER_TYPE = PropertiesLoader.getValueFor("ORDER_TYPE");

	public static final String ORDER_ID = PropertiesLoader.getValueFor("ORDER_ID");

	public static final String QUERY_ATTR = PropertiesLoader.getValueFor("QUERY_ATTR");

	public static final String START_DATE_ATTR = PropertiesLoader.getValueFor("START_DATE_ATTR");

	public static final String END_DATE_ATTR = PropertiesLoader.getValueFor("END_DATE_ATTR");

	public static final String TIME_SCHEDULE_ATTR = PropertiesLoader.getValueFor("TIME_SCHEDULE_ATTR");

	public static final String FILE_FIELDS_ATTR = PropertiesLoader.getValueFor("FILE_FIELDS_ATTR");

	public static final String FILE_PRIMARY_FIELDS_ATTR = PropertiesLoader.getValueFor("FILE_PRIMARY_FIELDS_ATTR");

	public static final String CHILD_INTERFACE_ATTR = PropertiesLoader.getValueFor("CHILD_INTERFACE_ATTR");

	public static final String CSV_DELIMETER_ATTR = PropertiesLoader.getValueFor("CSV_DELIMETER_ATTR");

	public static final String JDBC_DRIVER_ATTR = PropertiesLoader.getValueFor("JDBC_DRIVER_ATTR");

	public static final String JDBC_URL_ATTR = PropertiesLoader.getValueFor("JDBC_URL_ATTR");

	public static final String JDBC_USER_ATTR = PropertiesLoader.getValueFor("JDBC_USER_ATTR");

	public static final String JDBC_PASS_ATTR = PropertiesLoader.getValueFor("JDBC_PASS_ATTR");

	public static final String REMOTE_FILE_MERGE_ATTR = PropertiesLoader.getValueFor("REMOTE_FILE_MERGE_ATTR");

	public static final String MERGE_FIRST_FILE_FIELDS_ATTR = PropertiesLoader.getValueFor("MERGE_FIRST_FILE_FIELDS_ATTR");

	public static final String MERGE_SECOND_FILE_FIELDS_ATTR = PropertiesLoader.getValueFor("MERGE_SECOND_FILE_FIELDS_ATTR");

	public static final String FIRST_CSV_FILE_MERGE_DELIMETER_ATTR = PropertiesLoader.getValueFor("FIRST_CSV_FILE_MERGE_DELIMETER_ATTR");

	public static final String SECOND_CSV_FILE_MERGE_DELIMETER_ATTR = PropertiesLoader.getValueFor("SECOND_CSV_FILE_MERGE_DELIMETER_ATTR");

	public static final String FIRST_CSV_FILE_MULTI_MERGE_DELIMETER_ATTR = PropertiesLoader.getValueFor("FIRST_CSV_FILE_MULTI_MERGE_DELIMETER_ATTR");

	public static final String SECOND_CSV_FILE_MULTI_MERGE_DELIMETER_ATTR = PropertiesLoader.getValueFor("SECOND_CSV_FILE_MULTI_MERGE_DELIMETER_ATTR");

	public static final String SECOND_FILE_VALIDATION_CONF_ATTR = PropertiesLoader.getValueFor("SECOND_FILE_VALIDATION_CONF_ATTR");

	public static final String FIRST_FILE_VALIDATION_CONF_ATTR = PropertiesLoader.getValueFor("FIRST_FILE_VALIDATION_CONF_ATTR");

	public static final String CSV_MERGER_ATTR = PropertiesLoader.getValueFor("CSV_MERGER_ATTR");

	public static final String MERGED_FILE_FIELDS_ATTR = PropertiesLoader.getValueFor("MERGED_FILE_FIELDS_ATTR");

	public static final Long REST_SERVICE_SUCCESS_CODE = Long.parseLong(PropertiesLoader.getValueFor("REST_SERVICE_SUCCESS_CODE"));

	public static final String REST_SERVICE_FAIL_CODE = PropertiesLoader.getValueFor("REST_SERVICE_FAIL_CODE");

	public static final String FILE_TRANSFER_PROCESS = PropertiesLoader.getValueFor("FILE_TRANSFER_PROCESS");

	public static final String FILE_RECORD_PROCESS = PropertiesLoader.getValueFor("FILE_RECORD_PROCESS");

	public static final String FILE_PROCESS_TYPE_ATTR = PropertiesLoader.getValueFor("FILE_PROCESS_TYPE_ATTR");

	public static final String DATE_FORMAT_ATTR = PropertiesLoader.getValueFor("DATE_FORMAT_ATTR");

	public static final String FILE_HEADERS_ATTR = PropertiesLoader.getValueFor("FILE_HEADERS_ATTR");

	public static final String FIRST_FILE_HEADER_ATTR = PropertiesLoader.getValueFor("FIRST_FILE_HEADER_ATTR");

	public static final String SECOND_FILE_HEADER_ATTR = PropertiesLoader.getValueFor("SECOND_FILE_HEADER_ATTR");

	public static final String INTERFACE_SCHEMA_NAME = PropertiesLoader.getValueFor("INTERFACE_SCHEMA_NAME");




	public static final String LOCAL_REJECTED_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_REJECTED_DIR_ATTR");

	public static final String LOCAL_REJECTED_CONTROL_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_REJECTED_CONTROL_DIR_ATTR");

	public static final String LOCAL_REJECTED_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_REJECTED_BACKUP_DIR_ATTR");

	public static final String LOCAL_REJECTED_CONTROL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_REJECTED_CONTROL_BACKUP_DIR_ATTR");

	public static final String REMOTE_REJECTED_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_REJECTED_DIR_ATTR");

	public static final String REMOTE_REJECTED_CTRL_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_REJECTED_CTRL_DIR_ATTR");

	public static final String REMOTE_REJECTED_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_REJECTED_BACKUP_DIR_ATTR");

	public static final String REMOTE_REJECTED_CONTROL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("REMOTE_REJECTED_CONTROL_BACKUP_DIR_ATTR");

	public static final String DEST_HOST_ATTR = PropertiesLoader.getValueFor("DEST_HOST_ATTR");

	public static final String DEST_PORT_ATTR = PropertiesLoader.getValueFor("DEST_PORT_ATTR");

	public static final String DEST_USER_ATTR = PropertiesLoader.getValueFor("DEST_USER_ATTR");

	public static final String DEST_PASSWORD_ATTR = PropertiesLoader.getValueFor("DEST_PASSWORD_ATTR");

	public static final String DEST_DIR_ATTR = PropertiesLoader.getValueFor("DEST_DIR_ATTR");

	public static final Long ORDER_ENTITY_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ORDER_ENTITY_TYPE"));

	public static final List<String> INVENTORY_SALES_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("INVENTORY_SALES_INTERFACE_LIST").split(","));

	public static final String DATE_TIME_FORMAT = PropertiesLoader.getValueFor("DATE_TIME_FORMAT");

	public static final String DATE_FORMAT = PropertiesLoader.getValueFor("DATE_FORMAT");

	public static final String SNOC_DATE_FORMAT = PropertiesLoader.getValueFor("SNOC_DATE_FORMAT");

	public static final String SNOC_INPUT_TIME_ZONE = PropertiesLoader.getValueFor("SNOC_INPUT_TIME_ZONE");

	public static final String ACTIVE_MQ_LOCAL_URL = PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_URL");

	public static final String ACTIVE_MQ_LOCAL_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_USER")) : PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_USER");

	public static final String ACTIVE_MQ_LOCAL_PWD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_PWD")): PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_PWD");

	public static final String ACTUAL_FILE_TYPE_CHAR = PropertiesLoader.getValueFor("ACTUAL_FILE_TYPE_CHAR");

	public static final String REJECTED_FILE_TYPE_CHAR = PropertiesLoader.getValueFor("REJECTED_FILE_TYPE_CHAR");
	
	public static final String FILTER_FILE_TYPE_CHAR = PropertiesLoader.getValueFor("FILTER_FILE_TYPE_CHAR");
	
	public static final String FILE_HEADER_ATTR = PropertiesLoader.getValueFor("FILE_HEADER_ATTR");

	public static final String LTYPE_ENTITY_MAPPING = PropertiesLoader.getValueFor("LTYPE_ENTITY_MAPPING");

	public static final String LTYPE_LOOKUP_MAPPING = PropertiesLoader.getValueFor("LTYPE_LOOKUP_MAPPING");

	public static final String LTYPE_DATE_MAPPING = PropertiesLoader.getValueFor("LTYPE_DATE_MAPPING");
	
	public static final String LTYPE_CONDITION_MAPPING = PropertiesLoader.getValueFor("LTYPE_CONDITION_MAPPING");
	
	public static final Long LOOKUP_ENTITY_TYPE = Long.parseLong(PropertiesLoader.getValueFor("LOOKUP_ENTITY_TYPE"));

	public static final Long ERROR_CODE_ENTITY_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ERROR_CODE_ENTITY_TYPE"));

	public static final Long PRODUCT_SERIALIZED_ENTITY = Long.parseLong(PropertiesLoader.getValueFor("PRODUCT_SERIALIZED_ENTITY"));

	public static final Long PRODUCT_SERIALE_TYPE_ENTITY = Long.parseLong(PropertiesLoader.getValueFor("PRODUCT_SERIALE_TYPE_ENTITY"));

	public static final Long PRODUCT_BUNDLE_TYPE_ENTITY = Long.parseLong(PropertiesLoader.getValueFor("PRODUCT_BUNDLE_TYPE_ENTITY"));

	public static final String SORT_FILE_SCRIPT = PropertiesLoader.getValueFor("SORT_FILE_SCRIPT");
	
	public static final String SORT_FILE_SCRIPT_SN = PropertiesLoader.getValueFor("SORT_FILE_SCRIPT_SN");
	
	public static final String SORT_FILE_SCRIPT_PARTNER_SN = PropertiesLoader.getValueFor("SORT_FILE_SCRIPT_PARTNER_SN");

	public static final String MONGO_DB_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_USER")) : PropertiesLoader.getValueFor("MONGO_DB_USER");

	public static final String MONGO_DB_DATABASE = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_DATABASE")) : PropertiesLoader.getValueFor("MONGO_DB_DATABASE");

	public static final String MONGO_DB_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_PASSWORD")) : PropertiesLoader.getValueFor("MONGO_DB_PASSWORD");
	
	public static final String MONGO_DB_STANDALONE_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_USER")) : PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_USER");

	public static final String MONGO_DB_STANDALONE_DATABASE = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_DATABASE")) : PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_DATABASE");

	public static final String MONGO_DB_STANDALONE_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_PASSWORD")) : PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_PASSWORD");
	
	public static final String MONGO_DB_R4_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_R4_USER")) : PropertiesLoader.getValueFor("MONGO_DB_R4_USER");

	public static final String MONGO_DB_R4_DATABASE = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_R4_DATABASE")) : PropertiesLoader.getValueFor("MONGO_DB_R4_DATABASE");

	public static final String MONGO_DB_R4_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_R4_PASSWORD")) : PropertiesLoader.getValueFor("MONGO_DB_R4_PASSWORD");

	//	public static final String MONGO_DB_IP = PropertiesLoader.getValueFor("MONGO_DB_IP");

	public static final String MONGO_DB_IP1 = PropertiesLoader.getValueFor("MONGO_DB_IP1");

	public static final String MONGO_DB_IP2 = PropertiesLoader.getValueFor("MONGO_DB_IP2");

	public static final String MONGO_DB_IP3 = PropertiesLoader.getValueFor("MONGO_DB_IP3");

	public static final String MONGO_DB_IP4 = PropertiesLoader.getValueFor("MONGO_DB_IP4");
	
	public static final String MONGO_DB_IP5 = PropertiesLoader.getValueFor("MONGO_DB_IP5");
	
	public static final Integer MONGO_DB_PORT1 = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_DB_PORT1"));

	public static final Integer MONGO_DB_PORT2 = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_DB_PORT2"));

	public static final Integer MONGO_DB_PORT3 = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_DB_PORT3"));

	public static final Integer MONGO_DB_PORT4 = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_DB_PORT4"));
	
	public static final Integer MONGO_DB_PORT5 = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_DB_PORT5"));
	
	public static final String DOUBLE_VAL = PropertiesLoader.getValueFor("DOUBLE_VAL");

	public static final Integer DOUBLE_DIVIDEND_VAL = Integer.parseInt(PropertiesLoader.getValueFor("DOUBLE_DIVIDEND_VAL"));
 
	public static final String SKIP_ORDER_TYPE = PropertiesLoader.getValueFor("SKIP_ORDER_TYPE");

	public static final String SKIP_FIELD = PropertiesLoader.getValueFor("SKIP_FIELD");



	public static final String LOOKUP = PropertiesLoader.getValueFor("LOOKUP");

	public static final String ZIP_FILE_FORMAT = PropertiesLoader.getValueFor("ZIP_FILE_FORMAT");

	public static final String VERIFY_PPK_FILE = PropertiesLoader.getValueFor("VERIFY_PPK_FILE");

	public static final String PPK_FILE_PATH = PropertiesLoader.getValueFor("PPK_FILE_PATH");

	public static final String INTERFACE_FILE_SUMMARY_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("INTERFACE_FILE_SUMMARY_ID_IN_CONVERTER_TEMPLATE");

	public static final Integer CTL_MD5_HASHKEY_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_MD5_HASHKEY_INDEX"));

	public static final Integer CTL_FILE_SIZE_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_FILE_SIZE_INDEX"));

	public static final Integer CTL_RECORD_COUNT_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_RECORD_COUNT_INDEX"));
	
	public static final Integer CTL_FILE_NAME_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_FILE_NAME_INDEX"));

	public static final String RESPONSE_ID_PATH_ATTR = PropertiesLoader.getValueFor("RESPONSE_ID_PATH_ATTR");

	public static final String RESPONSE_ID_PATH_IN_ERROR_ATTR = PropertiesLoader.getValueFor("RESPONSE_ID_PATH_IN_ERROR_ATTR");

	public static final String REJECTION_FILE_ATTR = PropertiesLoader.getValueFor("REJECTION_FILE_ATTR");

	public static final String PRIMARY_STOCK_RETURN_FILE_NAME = PropertiesLoader.getValueFor("PRIMARY_STOCK_RETURN_FILE_NAME");

	public static final String ORDER_STATUS_FIRST_FILE_NAME = PropertiesLoader.getValueFor("ORDER_STATUS_FIRST_FILE_NAME");

	public static final String STOCK_BALANCE_FIRST_FILE_NAME = PropertiesLoader.getValueFor("STOCK_BALANCE_FIRST_FILE_NAME");
	public static final String INVENTORY_SALES_FILE_NAME = PropertiesLoader.getValueFor("INVENTORY_SALES_FILE_NAME");

	public static final String MASTER_DUMP_COLLECTION_NAMES_ATTR = PropertiesLoader.getValueFor("MASTER_DUMP_COLLECTION_NAMES_ATTR");
	public static final String TARGET_ENTITY_TYPE_LIST = PropertiesLoader.getValueFor("TARGET_ENTITY_TYPE_LIST");



	public static final String HOST_FOR_EMAIL = PropertiesLoader.getValueFor("HOST_FOR_EMAIL");

	public static final String PORT_FOR_EMAIL = PropertiesLoader.getValueFor("PORT_FOR_EMAIL");

	public static final String SMTP_USER_NAME = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMTP_USER_NAME")) : PropertiesLoader.getValueFor("SMTP_USER_NAME");

	public static final String SMTP_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMTP_PASSWORD")) : PropertiesLoader.getValueFor("SMTP_PASSWORD");

	public static final String SMTP_FROM_USER_NAME = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMTP_FROM_USER_NAME")) : PropertiesLoader.getValueFor("SMTP_FROM_USER_NAME");

	public static final String NOTIFICATION_TEMPLATE_PATH = PropertiesLoader.getValueFor("NOTIFICATION_TEMPLATE_PATH");	

	public static final String QUEUE_NAMES_SET_ATTR = PropertiesLoader.getValueFor("QUEUE_NAMES_SET_ATTR");

	public static final String INTERFACE_FAILURE_QUEUE_NAME_ATTR = PropertiesLoader.getValueFor("INTERFACE_FAILURE_QUEUE_NAME_ATTR");

	public static final String ENHANCESYS_DEFAULT_NODE_ID = PropertiesLoader.getValueFor("ENHANCESYS_DEFAULT_NODE_ID");

	public static final String SNOC_SYSTEM_ORG_TYPE_ID = PropertiesLoader.getValueFor("SNOC_SYSTEM_ORG_TYPE_ID");

	public static final String ERROR_CODE_PATH_IN_RESPONSE = PropertiesLoader.getValueFor("ERROR_CODE_PATH_IN_RESPONSE");

	public static final String ERROR_MESSAGE_PATH_IN_RESPONSE = PropertiesLoader.getValueFor("ERROR_MESSAGE_PATH_IN_RESPONSE");

	public static final String EMAIL_VERIFICATION_REQUIRED = PropertiesLoader.getValueFor("EMAIL_VERIFICATION_REQUIRED");

	public static final String TARGET_ENTITY_TYPE_IDS = PropertiesLoader.getValueFor("TARGET_ENTITY_TYPE_IDS");

	public static final String TARGET_TYPE_ENTITY_TYPE_ID = PropertiesLoader.getValueFor("TARGET_TYPE_ENTITY_TYPE_ID");

	public static final String UOM_ENTITY_TYPE_ID = PropertiesLoader.getValueFor("UOM_ENTITY_TYPE_ID");

	public static final Long ACTIVE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("ACTIVE_STATUS").toString());

	public static final String FILE_NOT_EXIST_SCENARIO = PropertiesLoader.getValueFor("FILE_NOT_EXIST_SCENARIO");

	public static final String FILE_PROCESS_SUCCESS_SCENARIO = PropertiesLoader.getValueFor("FILE_PROCESS_SUCCESS_SCENARIO");

	public static final String FILE_REJECTION_FULL_SCENARIO = PropertiesLoader.getValueFor("FILE_REJECTION_FULL_SCENARIO");

	public static final String FILE_REJECTION_PARTIAL_SCENARIO = PropertiesLoader.getValueFor("FILE_REJECTION_PARTIAL_SCENARIO");

	public static final String FILE_REJECTION_AT_CTL_SCENARIO = PropertiesLoader.getValueFor("FILE_REJECTION_AT_CTL_SCENARIO");

	public static final String FILE_TRANSFERRED_TO_SFTP_SCENARIO = PropertiesLoader.getValueFor("FILE_TRANSFERRED_TO_SFTP_SCENARIO");
	
	public static final String CONNECTION_REFUSED_SCENARIO = PropertiesLoader.getValueFor("CONNECTION_REFUSED_SCENARIO");

	public static final Long NOTIFICATION_MODULE_ID = Long.parseLong(PropertiesLoader.getValueFor("NOTIFICATION_MODULE_ID"));

	//	public static final Long RESEND_STATUS = Long.parseLong(PropertiesLoader.getValueFor("RESEND_STATUS"));

	public static final String SNOC_ORDER_TYPE_PATH_ATTR = PropertiesLoader.getValueFor("SNOC_ORDER_TYPE_PATH_ATTR");

	public static final String SNOC_CANCELLED_STATUS = PropertiesLoader.getValueFor("SNOC_CANCELLED_STATUS");
	public static final String SNOC_VERIFICATION_FAILED_STATUS = PropertiesLoader.getValueFor("SNOC_VERIFICATION_FAILED_STATUS");

	public static final String SNOC_REJECTED_STATUS = PropertiesLoader.getValueFor("SNOC_REJECTED_STATUS");

	public static final String SNOC_COMPLETED_STATUS = PropertiesLoader.getValueFor("SNOC_COMPLETED_STATUS");

	public static final String SNOC_ORDER_STATUS_PATH_ATTR = PropertiesLoader.getValueFor("SNOC_ORDER_STATUS_PATH_ATTR");

	public static final String SNOC_ORDER_TYPE_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("SNOC_ORDER_TYPE_IN_CONVERTER_TEMPLATE");

	public static final String SNOC_ORDER_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("SNOC_ORDER_ID_IN_CONVERTER_TEMPLATE");

	public static final String VERIFY_URL_ATTR = PropertiesLoader.getValueFor("VERIFY_URL_ATTR");

	public static final String VERIFY_REQUST_ATTR = PropertiesLoader.getValueFor("VERIFY_REQUST_ATTR");

	public static final List<String> SEND_ONLY_OLD_FILES_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("SEND_ONLY_OLD_FILES_INTERFACE_LIST").split(","));

	public static final Long ORDER_STATUS_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("ORDER_STATUS_INTERFACE_ID"));

	public static final List<String> KS_SYS_ORG_TYPE_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("KS_SYS_ORG_TYPE_INTERFACE_LIST").split(","));

	public static final String FILE_ENCODE_FORMAT = PropertiesLoader.getValueFor("FILE_ENCODE_FORMAT");

	public static final String FILE_SORT_ORDER_ATTR = PropertiesLoader.getValueFor("FILE_SORT_ORDER_ATTR");

	public static final List<String> MERGE_SERIAL_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("MERGE_SERIAL_INTERFACE_LIST").split(","));

	public static final List<String> PREPARE_EMPTY_FILES_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("PREPARE_EMPTY_FILES_INTERFACE_LIST").split(","));

	public static final String INTEGRATION_ATTACHMENT_API_REQUEST_FILE = PropertiesLoader.getValueFor("INTEGRATION_ATTACHMENT_API_REQUEST_FILE");

	public static final String PPK_PATH_ATTR = PropertiesLoader.getValueFor("PPK_PATH_ATTR");

	public static final Long EMAIL_ATTACHMENT_LIMIT_IN_KB = Long.parseLong(PropertiesLoader.getValueFor("EMAIL_ATTACHMENT_LIMIT_IN_KB"));
	public static final String STOCK_BALANCE_VERIFY_REQUST = PropertiesLoader.getValueFor("STOCK_BALANCE_VERIFY_REQUST");
	public static final String STOCK_BALANCE_VERIFY_URL = PropertiesLoader.getValueFor("STOCK_BALANCE_VERIFY_URL");

	public static final String SORT_STOCK_BALANCE_FILE_SCRIPT = PropertiesLoader.getValueFor("SORT_STOCK_BALANCE_FILE_SCRIPT");

	public static final List<String> DUPLICATE_TRANS_CHECK_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("DUPLICATE_TRANS_CHECK_INTERFACE_LIST").split(","));

	public static final List<String> CHECK_PARTNER_CODE_LIST = Arrays.asList(PropertiesLoader.getValueFor("CHECK_PARTNER_CODE_LIST").split(","));

	public static final String MIGRATED_PARTNERS_ATTR = PropertiesLoader.getValueFor("MIGRATED_PARTNERS_ATTR");

	public static final List<String> ASYNC_PULL_DATA_TO_FILE_FROM_EXCEL_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_FROM_EXCEL_INTERFACE_LIST").split(","));

	public static final String CELLS_TO_BE_READ_FROM_CONSOLIDATED_KPI_EXCEL = PropertiesLoader.getValueFor("CELLS_TO_BE_READ_FROM_CONSOLIDATED_KPI_EXCEL");

	public static final String CONSOLIDATED_KPI_EXCEL_PATH = PropertiesLoader.getValueFor("CONSOLIDATED_KPI_EXCEL_PATH");

	public static final String CONSOLIDATED_KPI_SHEET_NAME = PropertiesLoader.getValueFor("CONSOLIDATED_KPI_SHEET_NAME");

	public static final String POS_TYPE = PropertiesLoader.getValueFor("POS_TYPE");

	public static final String POS_CATEGORY = PropertiesLoader.getValueFor("POS_CATEGORY");

	public static final String STOCK_TAKING_FILE_NAME = PropertiesLoader.getValueFor("STOCK_TAKING_FILE_NAME");
	public static final String AUDIT_FILE_NAME = PropertiesLoader.getValueFor("AUDIT_FILE_NAME");
	public static final String FORM_NAME = PropertiesLoader.getValueFor("FORM_NAME");
	public static final String _ID = PropertiesLoader.getValueFor("_ID");

	public static final String CONSOLIDATED_KPI_FILE_NAME = PropertiesLoader.getValueFor("CONSOLIDATED_KPI_FILE_NAME");	

	public static final List<String> MAPPED_ERROR_CODES_LIST = Arrays.asList(PropertiesLoader.getValueFor("MAPPED_ERROR_CODES_LIST").split(","));

	public static final String INTERFACE_JDBC_DRIVER=PropertiesLoader.getValueFor("INTERFACE_JDBC_DRIVER");

	public static final String INTERFACE_JDBC_URL=PropertiesLoader.getValueFor("INTERFACE_JDBC_URL");

	public static final String INTERFACE_JDBC_USER= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("INTERFACE_JDBC_USER")) : PropertiesLoader.getValueFor("INTERFACE_JDBC_USER");

	public static final String INTERFACE_JDBC_PASS= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("INTERFACE_JDBC_PASS")) : PropertiesLoader.getValueFor("INTERFACE_JDBC_PASS");
	
	public static final String KPI_JDBC_DRIVER=PropertiesLoader.getValueFor("KPI_JDBC_DRIVER");

	public static final String KPI_JDBC_URL=PropertiesLoader.getValueFor("KPI_JDBC_URL");

	public static final String KPI_JDBC_USER= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("KPI_JDBC_USER")) : PropertiesLoader.getValueFor("KPI_JDBC_USER");

	public static final String KPI_JDBC_PASS= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("KPI_JDBC_PASS")) : PropertiesLoader.getValueFor("KPI_JDBC_PASS");
	
	public static final String KPI_SCHEMA_NAME= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("KPI_SCHEMA_NAME")) : PropertiesLoader.getValueFor("KPI_SCHEMA_NAME");

	public static final Long MAX_ALLOWED_SN_PER_TRANSACTION = Long.parseLong(PropertiesLoader.getValueFor("MAX_ALLOWED_SN_PER_TRANSACTION"));

	public static final List<String> RETRY_ERROR_CODE_LIST = Arrays.asList(PropertiesLoader.getValueFor("RETRY_ERROR_CODE_LIST").split(","));
	
	public static final String ACTIVEMQ_INITIAL_CONTEXT_FACTORY = PropertiesLoader.getValueFor("ACTIVEMQ_INITIAL_CONTEXT_FACTORY");
	
	public static final String ACTIVEMQ_PROVIDER_URL = PropertiesLoader.getValueFor("ACTIVEMQ_PROVIDER_URL");
	
	public static final String ACTIVEMQ_NOTIFICATIONQ_NAME = PropertiesLoader.getValueFor("ACTIVEMQ_NOTIFICATIONQ_NAME");
	
	public static final String ACTIVEMQ_USER_NAME = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVEMQ_USER_NAME")) : PropertiesLoader.getValueFor("ACTIVEMQ_USER_NAME");
	
	public static final String ACTIVEMQ_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVEMQ_PASSWORD")) : PropertiesLoader.getValueFor("ACTIVEMQ_PASSWORD");
	
	public static final String INVENTORY_VALIDATION_TEMPLATE = PropertiesLoader.getValueFor("INVENTORY_VALIDATION_TEMPLATE");
	
	public static final String VALIDATE_SERIALS_URL = PropertiesLoader.getValueFor("VALIDATE_SERIALS_URL");
	
	public static final Integer HTTP_CONNECTION_TIMEOUT = Integer.parseInt(PropertiesLoader.getValueFor("HTTP_CONNECTION_TIMEOUT"));
	
	public static final Integer HTTP_READ_TIMEOUT = Integer.parseInt(PropertiesLoader.getValueFor("HTTP_READ_TIMEOUT"));
	
	
	
	//	Error Codes..
	public static final Long INTERNAL_SERVER_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INTERNAL_SERVER_ERROR"));

	public static final Long FILE_NOT_RECEIVED_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("FILE_NOT_RECEIVED_ERROR"));

	public static final Long FILE_NAME_EXIST_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("FILE_NAME_EXIST_ERROR"));

	public static final Long CONTROL_FILE_VALIDATION_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("CONTROL_FILE_VALIDATION_ERROR"));

	public static final Long INVALID_FILE_STRUCTURE_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_FILE_STRUCTURE_ERROR"));

	public static final Long DUPLICATE_RECORD_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_RECORD_ERROR"));
	
	public static final Long SKIPPED_RECORD_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("SKIPPED_RECORD_ERROR"));

	public static final Long MANDATORY_FIELD_MISSING_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("MANDATORY_FIELD_MISSING_ERROR"));

	public static final Long INVALID_DATA_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_DATA_ERROR"));

	public static final Long MAPPING_DATA_NOT_FOUND_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("MAPPING_DATA_NOT_FOUND_ERROR"));

	public static final Long INVALID_RECORD_STRUCTURE_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_RECORD_STRUCTURE_ERROR"));

	public static final Long NO_VALID_RECORD_EXIST_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("NO_VALID_RECORD_EXIST_ERROR"));

	public static final Long UNKNOWN_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("UNKNOWN_ERROR"));

	public static final Long PARTIAL_FILE_REJECTION_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("PARTIAL_FILE_REJECTION_ERROR"));

	public static final Long FULL_FILE_REJECTION_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("FULL_FILE_REJECTION_ERROR"));

	public static final Long EMAIL_ADDRESS_IS_NOT_VERIFIED_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("EMAIL_ADDRESS_IS_NOT_VERIFIED_ERROR"));

	public static final Long EMAIL_COMPOSE_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("EMAIL_COMPOSE_ERROR"));

	public static final Long  ERRORCODE_SERIAL_NOT_AVAIL=Long.parseLong(PropertiesLoader.getErrorCodeFor("ERRORCODE_SERIAL_NOT_AVAIL"));

	public static final String ERRORMSG_SERIAL_NOT_AVAIL=PropertiesLoader.getValueFor("ERRORMSG_SERIAL_NOT_AVAIL");

	public static final Long  ERRORCODE_DUP_SERIALS=Long.parseLong(PropertiesLoader.getErrorCodeFor("ERRORCODE_DUP_SERIALS"));
	
	public static final Integer FILE_SUM_ERROR_MSG_CHAR_LIMIT = Integer.parseInt(PropertiesLoader.getValueFor("FILE_SUM_ERROR_MSG_CHAR_LIMIT"));
	
	public static final Long QUERY_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("QUERY_BALANCE_INTERFACE_ID"));
	public static final Long QUERY_BALANCE_MODULE_ID = Long.parseLong(PropertiesLoader.getValueFor("QUERY_BALANCE_MODULE_ID"));
	
	public static final Long QUERY_STOCK_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("QUERY_STOCK_INTERFACE_ID"));
	public static final Long CREATE_SO_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("CREATE_SO_INTERFACE_ID"));
	public static final Long UPDATE_SO_STATUS_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("UPDATE_SO_STATUS_INTERFACE_ID"));
	public static final Long SAP_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("SAP_USER_ID"));
	
	public static final String BALANCE_RESPONSE_CONF_ATTR = PropertiesLoader.getValueFor("BALANCE_RESPONSE_CONF_ATTR");
	public static final String STATIC_RESPONSE_CONF_ATTR = PropertiesLoader.getValueFor("STATIC_RESPONSE_CONF_ATTR");
	public static final String RESPONSE_CONF_ATTR = PropertiesLoader.getValueFor("RESPONSE_CONF_ATTR");
	public static final String DUMPS_FILE_SORT_SCRIPTS_PATH = PropertiesLoader.getValueFor("DUMPS_FILE_SORT_SCRIPTS_PATH");
	
	public static final Long SITE_MAPPING_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("SITE_MAPPING_INTERFACE_ID"));
	public static final List<String> KPI_HADOOP_FEED_INTERFACES_LIST = Arrays.asList(PropertiesLoader.getValueFor("KPI_HADOOP_FEED_INTERFACES_LIST").split(","));

 //	Error Codes..
	public static final Long  INTERNAL_SERVER_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INTERNAL_SERVER_ERR_CODE"));
	public static final Long  SERVICE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("SERVICE_ERR_CODE"));
	public static final Long  COLUMNS_MISMATCH_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("COLUMNS_MISMATCH_ERR_CODE"));
	public static final Long  MANDATE_FIELD_MISSING_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("MANDATE_FIELD_MISSING_ERR_CODE"));
	public static final Long  INVALID_DATA_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_DATA_ERR_CODE"));
	public static final Long  BASIC_VALIDATION_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("BASIC_VALIDATION_ERR_CODE"));
	public static final Long  DUPLICATE_RECORD_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_RECORD_ERR_CODE"));
	public static final Long  MULTIPLE_ENTRIES_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("MULTIPLE_ENTRIES_ERR_CODE"));
	public static final Long  INVALID_ORG_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_ORG_ERR_CODE"));
	public static final Long  INACTIVE_ORG_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INACTIVE_ORG_ERR_CODE"));
	public static final Long  INVALID_OR_INACTIVE_ORG_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_OR_INACTIVE_ORG_ERR_CODE"));
	public static final Long  EXT_OPR_MAP_MISSING_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("EXT_OPR_MAP_MISSING_ERR_CODE"));
	public static final Long  ORG_IN_SAME_STATUS_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("ORG_IN_SAME_STATUS_ERR_CODE"));
	public static final Long  INVALID_USER_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_USER_ERR_CODE"));
	public static final Long  INVALID_USER_NAME_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_USER_NAME_ERR_CODE"));
	public static final Long  USER_NAME_WITHOUT_SPACES_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("USER_NAME_WITHOUT_SPACES_ERR_CODE"));
	public static final Long  USER_IN_SAME_STATUS_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("USER_IN_SAME_STATUS_ERR_CODE"));
	public static final Long  UNAVAILABLE_NODE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("UNAVAILABLE_NODE_ERR_CODE"));
	public static final Long  INVALID_OR_INACTIVE_NODE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_OR_INACTIVE_NODE_ERR_CODE"));
	public static final Long  NOT_IN_RELATION_ORGS=Long.parseLong(PropertiesLoader.getErrorCodeFor("NOT_IN_RELATION_ORGS"));
	public static final Long  NOT_IN_RELATION_USER_AND_ORG_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("NOT_IN_RELATION_USER_AND_ORG_ERR_CODE"));
	public static final Long  INVALID_PRD_CODE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_PRD_CODE_ERR_CODE"));
	public static final Long  INACTIVE_PRODUCT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INACTIVE_PRODUCT_ERR_CODE"));
	public static final Long  INACTIVE_OR_INVALID_PRD_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INACTIVE_OR_INVALID_PRD_ERR_CODE"));
	public static final Long  INVALID_PRD_CAT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_PRD_CAT_ERR_CODE"));
	public static final Long  INACTIVE_PRD_CAT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INACTIVE_PRD_CAT_ERR_CODE"));
	public static final Long  INVALID_OR_INACTIVE_PRD_CAT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_OR_INACTIVE_PRD_CAT_ERR_CODE"));
	public static final Long  INVALID_SUB_CAT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_SUB_CAT_ERR_CODE"));
	public static final Long  INVALID_BRAND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_BRAND_ERR_CODE"));
	public static final Long  MATERIAL_CODE_EXISTS_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("MATERIAL_CODE_EXISTS_ERR_CODE"));
	public static final Long  UNAVAILABLE_PRD_ID_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("UNAVAILABLE_PRD_ID_ERR_CODE"));
	public static final Long  DUPLICATE_PRD_ID_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_PRD_ID_ERR_CODE"));
	public static final Long  SERIAL_DOES_NOT_EXIST_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("SERIAL_DOES_NOT_EXIST_ERR_CODE"));
	public static final Long  INVALID_SERIAL_LENGTH_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_SERIAL_LENGTH_ERR_CODE"));
	public static final Long  ACC_NOT_EXISTS_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("ACC_NOT_EXISTS_ERR_CODE"));
	public static final Long  INACTIVE_DEFAULT_ACC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INACTIVE_DEFAULT_ACC_ERR_CODE"));
	public static final Long  INVALID_OR_INACTIVE_ACC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_OR_INACTIVE_ACC_ERR_CODE"));
	public static final Long  INVALID_TERR_ID_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_TERR_ID_ERR_CODE"));
	public static final Long  UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_CODE"));
	public static final Long  ACTOR_MAP_NOT_FOUND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("ACTOR_MAP_NOT_FOUND_ERR_CODE"));
	public static final Long  LACCI_ACTOR_MAP_NOT_FOUND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("LACCI_ACTOR_MAP_NOT_FOUND_ERR_CODE"));
	public static final Long  METRIC_MAP_NOT_FOUND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("METRIC_MAP_NOT_FOUND_ERR_CODE"));
	public static final Long  DUPLICATE_METRIC_MAP_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_METRIC_MAP_ERR_CODE"));
	public static final Long  INVALID_METRIC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_METRIC_ERR_CODE"));
	public static final Long  SOURCE_MAP_NOT_FOUND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("SOURCE_MAP_NOT_FOUND_ERR_CODE"));
	public static final Long  INSTANCE_MAPPING_NOT_FOUND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INSTANCE_MAPPING_NOT_FOUND_ERR_CODE"));
	public static final Long  DUPLICATE_INSTANCE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_INSTANCE_ERR_CODE"));
	public static final Long  DIMENSION_MAP_NOT_FOUND_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("DIMENSION_MAP_NOT_FOUND_ERR_CODE"));
	public static final Long  DUPLICATE_TRANSACTION_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_TRANSACTION_ERR_CODE"));
	public static final Long  INVALID_PAYMENT_DT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_PAYMENT_DT_ERR_CODE"));
	public static final Long  MANDATE_NOTIFY_EMAIL_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("MANDATE_NOTIFY_EMAIL_ERR_CODE"));
	public static final Long  MANDATE_NOTIFY_MSISDN_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("MANDATE_NOTIFY_MSISDN_ERR_CODE"));
	public static final Long  INVALID_ROLE_ID_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_ROLE_ID_ERR_CODE"));
	public static final Long  INCOMPLETE_TRANSACTION_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INCOMPLETE_TRANSACTION_ERR_CODE"));
	public static final Long  INVALID_COLLECTION_ORG_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_COLLECTION_ORG_ERR_CODE"));
	public static final Long  INVALID_OR_INACTIVE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_OR_INACTIVE_ERR_CODE"));
	public static final Long  INVALID_DT_FRMT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_DT_FRMT_ERR_CODE"));
	public static final Long  FUTURE_DT_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("FUTURE_DT_ERR_CODE"));
	public static final Long  REJECTED_DUE_TO_INVALID_REC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("REJECTED_DUE_TO_INVALID_REC_ERR_CODE"));
	public static final Long  INVALID_SITE_MAPPING_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_SITE_MAPPING_ERR_CODE"));
	
// 	Error Messages
	public static final String  INTERNAL_SERVER_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INTERNAL_SERVER_ERR_CODE.toString());
	public static final String  SERVICE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(SERVICE_ERR_CODE.toString());
	public static final String  COLUMNS_MISMATCH_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(COLUMNS_MISMATCH_ERR_CODE.toString());
	public static final String  MANDATE_FIELD_MISSING_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(MANDATE_FIELD_MISSING_ERR_CODE.toString());
	public static final String  INVALID_DATA_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_DATA_ERR_CODE.toString());
	public static final String  BASIC_VALIDATION_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(BASIC_VALIDATION_ERR_CODE.toString());
	public static final String  DUPLICATE_RECORD_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(DUPLICATE_RECORD_ERR_CODE.toString());
	public static final String  MULTIPLE_ENTRIES_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(MULTIPLE_ENTRIES_ERR_CODE.toString());
	public static final String  INVALID_ORG_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_ORG_ERR_CODE.toString());
	public static final String  INACTIVE_ORG_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INACTIVE_ORG_ERR_CODE.toString());
	public static final String  INVALID_OR_INACTIVE_ORG_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_OR_INACTIVE_ORG_ERR_CODE.toString());
	public static final String  EXT_OPR_MAP_MISSING_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(EXT_OPR_MAP_MISSING_ERR_CODE.toString());
	public static final String  ORG_IN_SAME_STATUS_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(ORG_IN_SAME_STATUS_ERR_CODE.toString());
	public static final String  INVALID_USER_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_USER_ERR_CODE.toString());
	public static final String  INVALID_USER_NAME_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_USER_NAME_ERR_CODE.toString());
	public static final String  USER_NAME_WITHOUT_SPACES_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(USER_NAME_WITHOUT_SPACES_ERR_CODE.toString());
	public static final String  USER_IN_SAME_STATUS_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(USER_IN_SAME_STATUS_ERR_CODE.toString());
	public static final String  UNAVAILABLE_NODE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(UNAVAILABLE_NODE_ERR_CODE.toString());
	public static final String  INVALID_OR_INACTIVE_NODE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_OR_INACTIVE_NODE_ERR_CODE.toString());
	public static final String  NOT_IN_RELATION_ORGS_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(NOT_IN_RELATION_ORGS.toString());
	public static final String  NOT_IN_RELATION_USER_AND_ORG_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(NOT_IN_RELATION_USER_AND_ORG_ERR_CODE.toString());
	public static final String  INVALID_PRD_CODE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_PRD_CODE_ERR_CODE.toString());
	public static final String  INACTIVE_PRODUCT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INACTIVE_PRODUCT_ERR_CODE.toString());
	public static final String  INACTIVE_OR_INVALID_PRD_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INACTIVE_OR_INVALID_PRD_ERR_CODE.toString());
	public static final String  INVALID_PRD_CAT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_PRD_CAT_ERR_CODE.toString());
	public static final String  INACTIVE_PRD_CAT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INACTIVE_PRD_CAT_ERR_CODE.toString());
	public static final String  INVALID_OR_INACTIVE_PRD_CAT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_OR_INACTIVE_PRD_CAT_ERR_CODE.toString());
	public static final String  INVALID_SUB_CAT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_SUB_CAT_ERR_CODE.toString());
	public static final String  INVALID_BRAND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_BRAND_ERR_CODE.toString());
	public static final String  MATERIAL_CODE_EXISTS_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(MATERIAL_CODE_EXISTS_ERR_CODE.toString());
	public static final String  UNAVAILABLE_PRD_ID_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(UNAVAILABLE_PRD_ID_ERR_CODE.toString());
	public static final String  DUPLICATE_PRD_ID_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(DUPLICATE_PRD_ID_ERR_CODE.toString());
	public static final String  SERIAL_DOES_NOT_EXIST_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(SERIAL_DOES_NOT_EXIST_ERR_CODE.toString());
	public static final String  INVALID_SERIAL_LENGTH_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_SERIAL_LENGTH_ERR_CODE.toString());
	public static final String  ACC_NOT_EXISTS_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(ACC_NOT_EXISTS_ERR_CODE.toString());
	public static final String  INACTIVE_DEFAULT_ACC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INACTIVE_DEFAULT_ACC_ERR_CODE.toString());
	public static final String  INVALID_OR_INACTIVE_ACC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_OR_INACTIVE_ACC_ERR_CODE.toString());
	public static final String  INVALID_TERR_ID_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_TERR_ID_ERR_CODE.toString());
	public static final String  UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_CODE.toString());
	public static final String  ACTOR_MAP_NOT_FOUND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(ACTOR_MAP_NOT_FOUND_ERR_CODE.toString());
	public static final String  LACCI_ACTOR_MAP_NOT_FOUND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(LACCI_ACTOR_MAP_NOT_FOUND_ERR_CODE.toString());
	public static final String  METRIC_MAP_NOT_FOUND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(METRIC_MAP_NOT_FOUND_ERR_CODE.toString());
	public static final String  DUPLICATE_METRIC_MAP_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(DUPLICATE_METRIC_MAP_ERR_CODE.toString());
	public static final String  INVALID_METRIC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_METRIC_ERR_CODE.toString());
	public static final String  SOURCE_MAP_NOT_FOUND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(SOURCE_MAP_NOT_FOUND_ERR_CODE.toString());
	public static final String  INSTANCE_MAPPING_NOT_FOUND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INSTANCE_MAPPING_NOT_FOUND_ERR_CODE.toString());
	public static final String  DUPLICATE_INSTANCE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(DUPLICATE_INSTANCE_ERR_CODE.toString());
	public static final String  DIMENSION_MAP_NOT_FOUND_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(DIMENSION_MAP_NOT_FOUND_ERR_CODE.toString());
	public static final String  DUPLICATE_TRANSACTION_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(DUPLICATE_TRANSACTION_ERR_CODE.toString());
	public static final String  INVALID_PAYMENT_DT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_PAYMENT_DT_ERR_CODE.toString());
	public static final String  MANDATE_NOTIFY_EMAIL_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(MANDATE_NOTIFY_EMAIL_ERR_CODE.toString());
	public static final String  MANDATE_NOTIFY_MSISDN_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(MANDATE_NOTIFY_MSISDN_ERR_CODE.toString());
	public static final String  INVALID_ROLE_ID_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_ROLE_ID_ERR_CODE.toString());
	public static final String  INCOMPLETE_TRANSACTION_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INCOMPLETE_TRANSACTION_ERR_CODE.toString());
	public static final String  INVALID_COLLECTION_ORG_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_COLLECTION_ORG_ERR_CODE.toString());
	public static final String  INVALID_OR_INACTIVE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_OR_INACTIVE_ERR_CODE.toString());
	public static final String  INVALID_DT_FRMT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_DT_FRMT_ERR_CODE.toString());
	public static final String  FUTURE_DT_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(FUTURE_DT_ERR_CODE.toString());
	public static final String  REJECTED_DUE_TO_INVALID_REC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(REJECTED_DUE_TO_INVALID_REC_ERR_CODE.toString());
	public static final String  INVALID_SITE_MAPPING_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_SITE_MAPPING_ERR_CODE.toString());
	
//  Error Messages.. 
	
	public static final String ERRORMSG_DUP_SERIALS=PropertiesLoader.getValueFor("ERRORMSG_DUP_SERIALS");
	
	public static final String MONO_ORG_TYPE_LIST=PropertiesLoader.getValueFor("MONO_ORG_TYPE_LIST");
	
	public static final Long BRAND_LOOKUP_TYPE=Long.parseLong(PropertiesLoader.getValueFor("BRAND_LOOKUP_TYPE"));
	
	public static final Long MONO_LOOKUP_ID=Long.parseLong(PropertiesLoader.getValueFor("MONO_LOOKUP_ID"));
	
	public static final String PRODUCT_TRANS_TYPE=PropertiesLoader.getValueFor("PRODUCT_TRANS_TYPE");
	
	public static final String SORT_STOCK_DUMP_SCRIPT = PropertiesLoader.getValueFor("SORT_STOCK_DUMP_SCRIPT");
	
	public static final Long USER_SYNC_UPLOAD_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("USER_SYNC_UPLOAD_INTERFACE_ID"));
	
	public static final Integer KPI_FEED_QUEUE_RETRY_COUNT = Integer.parseInt(PropertiesLoader.getValueFor("KPI_FEED_QUEUE_RETRY_COUNT"));
	
	public static final Integer UPLOAD_FEED_QUEUE_RETRY_COUNT = Integer.parseInt(PropertiesLoader.getValueFor("UPLOAD_FEED_QUEUE_RETRY_COUNT"));
	
	public static final String ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST = PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST");
	public static final String REPROCESS_ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST = PropertiesLoader.getValueFor("REPROCESS_ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST");
	
	public static final Long ORDER_NOT_PLACED_OR_NOT_IN_COMPLETED_STATUS_ERROR=Long.parseLong(PropertiesLoader.getErrorCodeFor("ORDER_NOT_PLACED_OR_NOT_IN_COMPLETED_STATUS_ERROR"));
	public static final String FILES_LOADED=PropertiesLoader.getErrorCodeFor("FILES_LOADED");
	public static final String FILES_CONVERTED=PropertiesLoader.getErrorCodeFor("FILES_CONVERTED");
	public static final String FILES_CREATED=PropertiesLoader.getErrorCodeFor("FILES_CREATED");
	public static final String FILES_SUCCESSFULLY_LOADED=PropertiesLoader.getErrorCodeFor("FILES_SUCCESSFULLY_LOADED");
	public static final String FILES_REJECTED_FULLY=PropertiesLoader.getErrorCodeFor("FILES_REJECTED_FULLY");
	public static final String FILES_REJECTED_PARTIALLY=PropertiesLoader.getErrorCodeFor("FILES_REJECTED_PARTIALLY");
	public static final String FILES_UPLOADED=PropertiesLoader.getErrorCodeFor("FILES_UPLOADED");
	public static final String FILES_EMPTY=PropertiesLoader.getErrorCodeFor("FILES_EMPTY");
	public static final String FILES_ROLLBACKED = PropertiesLoader.getErrorCodeFor("FILES_ROLLBACKED");
	public static final String FILES_PROCESS_DISABLED = PropertiesLoader.getErrorCodeFor("FILES_PROCESS_DISABLED");

	public static final String HOTSPOT_SUB_ORG_TYPE = PropertiesLoader.getValueFor("HOTSPOT_SUB_ORG_TYPE");

	//	public static final Long MONGO_SERVER_CONNECTION_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("MONGO_SERVER_CONNECTION_ERROR"));
	//	
	//	public static final Long MONGO_CONF_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("MONGO_CONF_ERROR"));

	public static final List<String> ERROR_COUNT_UPDATION_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("ERROR_COUNT_UPDATION_INTERFACE_LIST").split(","));
	
	public static final String SORT_FILE_SCRIPT_AF = PropertiesLoader.getValueFor("SORT_FILE_SCRIPT_AF");
	public static final String AF_SORT = PropertiesLoader.getValueFor("AF_SORT");
	public static final int  AF_MAX_SN_LIMIT = Integer.parseInt(PropertiesLoader.getValueFor("AF_MAX_SN_LIMIT"));
	public static final String FIRST_FILE_HEADER_VALIDATION_ATTR = PropertiesLoader.getValueFor("FIRST_FILE_HEADER_VALIDATION_ATTR");
	public static final String SECOND_FILE_HEADER_VALIDATION_ATTR = PropertiesLoader.getValueFor("SECOND_FILE_HEADER_VALIDATION_ATTR");
	public static final String VALIDATE_INTERFACE_TRANSACTIONS = PropertiesLoader.getValueFor("VALIDATE_INTERFACE_TRANSACTIONS");
	public static final String INTERFACE_TRANSACTIONS_VALIDATION_TEMPLATE = PropertiesLoader.getValueFor("INTERFACE_TRANSACTIONS_VALIDATION_TEMPLATE");
	public static final String VALIDATE_INTERFACE_TRANSACTIONS_URL = PropertiesLoader.getValueFor("VALIDATE_INTERFACE_TRANSACTIONS_URL");
	
	public static final String NEW_LINE = "\n";
	public static final String CSV_DELIMETER = "|";
	public static final Long RECORD_BATCH = Long.valueOf(PropertiesLoader.getValueFor("RECORD_BATCH"));
	public static final Integer FILE_WRITING_REC_BATCH = Integer.valueOf(PropertiesLoader.getValueFor("FILE_WRITING_REC_BATCH"));	
	public static final String KPI_PROD_IS_SERIAL_QRY="select IS_SERIAL_V,SERIAL_TYPE_N from "+IntegrationConstants.KPI_SCHEMA_NAME+".MS_EXT_ENTITY_ATTRIBUTES where EXT_ENTITY_ID_V =?";
	public static final String SERIAL_TABLE_SELECT_QRY="select transaction_id_v,product_code_v,start_sn_v,end_sn_v,addnl_serial1_v,addnl_serial2_v,addnl_serial3_v,addnl_serial4_v,counter_v from "+IntegrationConstants.INTERFACE_SCHEMA_NAME+".tr_serials where file_id_n=? and transaction_id_v=? and product_code_v=? and counter_v =?";
	public static final String POS_SELECT_QRY = "select position_id_n from "+IntegrationConstants.KPI_SCHEMA_NAME+".ms_org_master where node_id_n=?";
	public static final String IS_SERIAL_YES="Yes";
	public static final String IS_SERIAL_TYPE_1="1";
	public static final String IS_SERIAL_NO="NO";
	public static final String IS_SERIAL_TYPE_0="0";
	public static final String NON_SERIAL_PROD_N="N";
	public static final String SERIAL_PROD_Y="Y";
	public static final String ERROR_CODE_STR="errorCode";
	public static final String ERROR_MSG_STR="errorMessage";
	public static final String STATUS_STR="STATUS";
	public static final String FAIL_STR="FAIL";
	public static final String SUCCESS_STR="SUCCESS";
	public static final String str_3="3";
	public static final String str_7="7";
	public static final String str_4="4";
	public static final String FILTER_TRNS_TYPE_LIST = PropertiesLoader.getValueFor("TRANS_TYPE_FILTER");
	public static final String POSITION_ID_FRM_KPI_DB=PropertiesLoader.getValueFor("POSITION_ID_LIST");
	public static final List<String> INTERFACE_FILE_VALIDATION_LIST = Arrays.asList(PropertiesLoader.getValueFor("INTERFACE_FILE_VALIDATION_LIST").split(","));
	
	public static final String DWH_FV_SUBJECT = PropertiesLoader.getValueFor("DWH_FV_SUBJECT");
	public static final String DWH_FV_TEMPLATE = PropertiesLoader.getValueFor("DWH_FV_TEMPLATE");
	public static final String DWH_FV_TO_ADDRESS = PropertiesLoader.getValueFor("DWH_FV_TO_ADDRESS");
	public static final String DWH_FV_MEDIA = PropertiesLoader.getValueFor("DWH_FV_MEDIA");
	public static final String DWH_FV_LANG = PropertiesLoader.getValueFor("DWH_FV_LANG");
	public static final String QUERY_FIELD_CONF_ATTR = PropertiesLoader.getValueFor("QUERY_FIELD_CONF_ATTR");
	
	public static final List<String> DUMP_JOB_INTERFACE_IDS = Arrays.asList(PropertiesLoader.getValueFor("DUMP_JOB_INTERFACE_IDS").split(","));
	
	public static final List<String> SM_UPLOAD_ORG_USER_SYNC_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("SM_UPLOAD_ORG_USER_SYNC_INTERFACES").split(","));
	
	public static final String partnerDetailsInsertQuery = "INSERT INTO interface.partner_details( processdate, transactiondate, transactiontype, transactionid, source, destination, userid, adjustmenttype, paymenttype, reasonname, orderstatus, totalamount, productcode, quantity, price, subtotal, noofrecordsinsubfile, counter,file_id_n, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String serialDetailsInsertQuery = "INSERT INTO interface.prod_serial_details( transactionid, productcode, startserial, endserial, additionalserial1, additionalserial2, additionalserial3, additionalserial4, counter,file_id_n, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
	
	public static final String selectQuery = "select * from interface.partner_details pa left join interface.prod_serial_details se on (pa.file_id_n=se.file_id_n and pa.transactionid=se.transactionid and pa.productcode=se.productcode and pa.counter=se.counter) where pa.file_id_n=? and pa.status=? order by pa.transactiondate,pa.transactionid,pa.transactiontype,pa.counter";
	
	public static final String failedRecWriteToInsertSummary = "select * from interface.partner_details pa left join interface.prod_serial_details se on (pa.file_id_n=se.file_id_n and pa.transactionid=se.transactionid and pa.productcode=se.productcode and pa.counter=se.counter ) where pa.file_id_n=? and pa.status=?  order by pa.transactiondate,pa.transactionid,pa.transactiontype,pa.counter";
	
	//public static final String recCountInsubFileSelectQuery = "select pa.transactiontype, pa.TRANSACTIONID, pa.PRODUCTCODE, pa.NOOFRECORDSINSUBFILE, pa.counter, count(se.counter) as secFileRecCount from partner_details pa left join prod_serial_details se on(pa.file_id_n=se.file_id_n and pa.TRANSACTIONID=se.TRANSACTIONID and pa.PRODUCTCODE=se.PRODUCTCODE and pa.counter=se.counter) where pa.file_id_n=? and  pa.status=? group by pa.transactiontype, pa.TRANSACTIONID,pa.counter,pa.PRODUCTCODE,pa.NOOFRECORDSINSUBFILE order by pa.transactiontype, pa.TRANSACTIONID,pa.counter,pa.PRODUCTCODE,pa.NOOFRECORDSINSUBFILE";
	
	public static final String recCountInsubFileSelectQuery = "select pa.transactiontype, pa.TRANSACTIONID, pa.PRODUCTCODE, pa.NOOFRECORDSINSUBFILE, pa.counter, case when se.rowcount is null then 0 else se.rowcount end as secFileRecCount from interface.partner_details pa left join (select file_id_n,transactionid,productcode,counter,count(*) rowcount from interface.prod_serial_details where file_id_n=? group by file_id_n,transactionid,productcode,counter) se on( pa.file_id_n=se.file_id_n and pa.TRANSACTIONID=se.TRANSACTIONID and pa.PRODUCTCODE=se.PRODUCTCODE and pa.counter=se.counter  ) where pa.file_id_n=? and pa.status=? order by pa.transactiontype, pa.TRANSACTIONID,pa.counter,pa.PRODUCTCODE,pa.NOOFRECORDSINSUBFILE";

	public static String trInterfaceSummarySeq = "SELECT nextval('interface.tr_interface_summary_seq')";
	
	public static final String insertSummary = "INSERT INTO interface.tr_interface_summary(trans_id_n, interface_id_n, request_time_dt, due_time_dt, ack_time_dt, response_time_dt, status_n, retry_count_n, ref_data1_v, ref_data2_n, ref_data3_n, ref_data4_n, ref_data5_v,orgnl_request_data_b,request_data_b,ack_data_b,orgnl_response_data_b,response_data_b) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	public static final String insertFailure = "INSERT INTO interface.tr_interface_failure( trans_failure_id_n, trans_id_n,  ack_time_dt,  response_time_dt, error_code_n, error_message_v,ack_data_b,response_data_b) select nextval('interface.tr_interface_failure_seq'), ?, ?, ?, ?, ?, ?, ?;";
	
	public static final String partnerFileTableUpdateQuery = "update interface.partner_details set status = ?,error_code=?,error_message=? where transactionid=? and file_id_n=?";
	
	//public static final String serialFileTaleUpdateQuery = "update prod_serial_details set status=? where file_id_n=? and transactionid in (select transactionid from prod_serial_details where status=? and file_id_n=? group by transactionid ,status,file_id_n )";
	
	public static final String partnerTableFailRecUpdateFromSerial = "update interface.partner_details set status=?, error_code=?, error_message=? where TRANSACTIONID=? and file_id_n=?";
	
	public static final String kpiSerialCheckQuery = "select * from kpi.ms_ext_entity_attributes where ext_entity_id_v=?";
	
	public static final String totalCountQuery = "select count(1) as cnt from interface.tr_interface_summary where ref_data3_n =?";
	
	public static final String failureCountQuery = "select count(1) as cnt from interface.tr_interface_summary where ref_data3_n =? and status_n=?;";
	
	public static final String recCountFailedUpdateQueryInPartnerTable = "update interface.partner_details set status=?,error_code=?,error_message=? where file_id_n=? and TRANSACTIONID=?";
	
	public static final String duplicateTrSelectQuery = "select count(1) from interface.tr_interface_summary where  ref_data1_v=? and ref_data5_v=? and status_n!=?  and ref_data2_n=?";
	
	public static final String partnerTableDeleteQry = "delete from interface.partner_details where file_id_n=?";
	public static final String serialTableDeleteQry = "delete from interface.prod_serial_details where file_id_n=?";
	
	public static final String createPdtFailureQry = "select * from interface.product_details pd where pd.file_id_n=? and pd.status=?  order by pd.productid";
	
	public static final String spStockDumpInsertQry = "INSERT INTO interface.sp_stock_dump(iccid, msisdn, imsi, do_id, so_id, destination_dealer_id, branch_code, brand, product_expired_date, stock_transfer_date, program_code, program_name, source_dealer_id, file_id, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String spStockDumpSelDistQry = "select distinct(so_id) from interface.sp_stock_dump where file_id = ? and status = ? order by so_id";
	public static final String spStockDumpSelQry = "SELECT iccid, msisdn, imsi, do_id, so_id, destination_dealer_id, branch_code, brand, product_expired_date, stock_transfer_date, program_code, program_name, source_dealer_id, file_id, status, error_code, error_message, stock_dump_id FROM interface.sp_stock_dump where so_id = ? and file_id = ? and status = ? order by program_code, product_expired_date, iccid::numeric";
	public static final String spStockDumpUpdateQry = "update interface.sp_stock_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and file_id = ? and status = ?";
	public static final String spStockDumpQry = "select stock_dump_id from interface.sp_stock_dump  where stock_dump_id in (stockDumpIds) and status = ? and file_id = ?";
	public static final String spStockDumpTotalsQry = "select status, count(*) from interface.sp_stock_dump where file_id = ? group by status union select 'total', count(*) from interface.sp_stock_dump where file_id = ?";
	public static final String spStockDumpSuccessCountQry = "select count(1) from interface.sp_stock_dump where file_id = ? and status in (statusIds)";
	public static final String spStockDumpFailQry = "select iccid, msisdn, imsi, do_id, destination_dealer_id, branch_code, brand, product_expired_date, stock_transfer_date, program_code, program_name, source_dealer_id, error_code, error_message from interface.sp_stock_dump where file_id = ? and status = ?";
	public static final String spStockDumpSuccessRecordsDelQry = "delete from interface.sp_stock_dump where file_id = ? and status = ?";
	public static final String spStockDumpValidateQry = "select stock_dump_id from interface.sp_stock_dump where file_id = ? and status not in (statusIds) limit 1";
	public static final String spStockDumpStatusUpdtQry = "update interface.sp_stock_dump set status = ? where so_id in (soidset) and file_id = ?";
	public static final String spStockDumpDuplicateValidationQry = "select iccid, so_id from interface.sp_stock_dump where iccid in (select iccid from interface.sp_stock_dump where file_id = ? and source_dealer_id = ? group by iccid having count(iccid) > 1) and file_id = ? and source_dealer_id = ? order by iccid, so_id desc";
	public static final String spStockDumpStatusErrorMsgUpdtQry = "update interface.sp_stock_dump set status = ?, error_code = ?, error_message = ? where so_id in (soidset) and file_id = ?";
	public static final String voucherStockDumpDuplicateValidationQry = "select serial_number, so_id from interface.stock_dump_voucher where serial_number in (select serial_number from interface.stock_dump_voucher where file_id_n = ? and source_dealer_id = ? group by serial_number having count(serial_number) > 1) and file_id_n = ? and source_dealer_id = ? order by serial_number, so_id desc";
	public static final String voucherStockDumpStatusErrorMsgUpdtQry = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id in (soidset) and file_id_n = ?";
	public static final String spAllocDuplicateValidationQry = "select iccid, so_id,alloc_id from interface.sp_alloc_dump where iccid in (select iccid from interface.sp_alloc_dump where file_id = ? group by iccid having count(iccid) > 1) and file_id = ? order by (iccid, so_id,alloc_id) desc";
	public static final String spAllocDumpStatusErrorMsgUpdtQry = "update interface.sp_alloc_dump set status = ?, error_code = ?, error_message = ? where so_id in (soIds) and alloc_id in (allocIds) and file_id = ?";
	public static final String voucherAllocDuplicateValidationQry = "select serial_number, so_id,alloc_no from interface.alloc_dump_voucher where serial_number in (select serial_number from interface.alloc_dump_voucher where file_id_n = ? group by serial_number having count(serial_number) > 1) and file_id_n = ? order by (serial_number, so_id,alloc_no) desc";
	public static final String voucherAllocDumpStatusErrorMsgUpdtQry = "update interface.alloc_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id in (soIds) and alloc_no in (allocIds) and file_id_n = ?";
	public static final String spStockDumpPendingRecordsCount = "select count(1) from interface.sp_stock_dump where file_id = ? and status = ?";
	public static final String spAllocDumpPendingRecordsCount = "select count(1) from interface.sp_alloc_dump where file_id = ? and status = ?";
	public static final String voucherStockDumpPendingRecordsCount = "select count(1) from interface.stock_dump_voucher where file_id_n = ? and status = ?";
	public static final String voucherAllocDumpPendingRecordsCount = "select count(1) from interface.alloc_dump_voucher where file_id_n = ? and status = ?";
	
	
	public static final String spAllocDumpInsertQry = "INSERT INTO interface.sp_alloc_dump(iccid, msisdn, imsi, do_id, so_id, dealer_id, branch_code, brand, product_expired_date, so_creation_date, alloc_id, program_code, program_name, type, alloc_date, payment_date, file_id, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String spAllocDumpSelDistQry = "select distinct so_id, alloc_id from interface.sp_alloc_dump where file_id = ? and status = ? order by so_id, alloc_id";
	public static final String spAllocDumpSelQry = "select iccid, msisdn, imsi, do_id, so_id, dealer_id, branch_code, brand, product_expired_date, so_creation_date, alloc_id, program_code, program_name, type, alloc_date, payment_date, file_id, status, error_code, error_message, alloc_dump_id from interface.sp_alloc_dump where so_id = ? and alloc_id = ? and file_id = ? and status = ? order by program_code, product_expired_date, iccid::numeric";
	public static final String spAllocDumpUpdateQry = "update interface.sp_alloc_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_id = ? and file_id = ? and status = ?";
	public static final String spAllocDumpQry = "select alloc_dump_id from interface.sp_alloc_dump  where alloc_dump_id in (allocDumpIds) and status = ? and file_id = ?";
	public static final String spAllocDumpTotalsQry = "select status, count(*) from interface.sp_alloc_dump where file_id = ? group by status union select 'total', count(*) from interface.sp_alloc_dump where file_id = ?";
	public static final String spAllocDumpSuccessCountQry = "select count(1) from interface.sp_alloc_dump where file_id = ? and status in (statusIds)";
	public static final String spAllocDumpFailQry = "select iccid, msisdn, imsi, do_id, dealer_id, branch_code, brand, product_expired_date, so_creation_date, alloc_id, program_code, program_name, type, alloc_date, payment_date, error_code, error_message from interface.sp_alloc_dump where file_id = ? and status = ?";
	public static final String spAllocDumpSuccessRecordsDelQry = "delete from interface.sp_alloc_dump where file_id = ? and status = ?";
	public static final String spAllocDumpValidateQry = "select alloc_dump_id from interface.sp_alloc_dump where file_id = ? and status not in (statusIds) limit 1";
	public static final String spAllocDumpStatusUpdtQry = "update interface.sp_alloc_dump set status = ? where so_id in (soIds) and alloc_id in (allocIds) and file_id = ?";
	
	public static final String fileDelimeter = "\\|";
	public static final String delimeter = "|";
	public static final String mergeDelimeter = ":::";
	
	public static final String st_success = "1301";
	public static final String st_error = "1302";
	public static final String st_inq = "1300";
	public static final String lineSeparator = System.getProperty("line.separator");
	
	public static final Integer PARTNER_FILE_INSERTION_BATCH = Integer.parseInt(PropertiesLoader.getValueFor("PARTNER_FILE_INSERTION_BATCH"));
	public static final Integer SERIAL_FILE_INSERTION_BATCH = Integer.parseInt(PropertiesLoader.getValueFor("SERIAL_FILE_INSERTION_BATCH"));
	
	public static final List<String> TR_TYPE_FILTER = Arrays.asList(PropertiesLoader.getValueFor("TRANS_TYPE_FILTER").split(","));
	public static final List<String> POSITION_IDS = Arrays.asList(PropertiesLoader.getValueFor("POSITION_ID_LIST").split(","));
	
	public static final Long MAX_TRANS_ALLOWED_IN_FILE  = Long.valueOf(PropertiesLoader.getValueFor("MAX_TRANS_ALLOWED_IN_FILE"));
	
	public static final String SKIP_VALIDATION_PARTNER = PropertiesLoader.getValueFor("SKIP_VALIDATION_PARTNER");
	
	public static final String SORT_STOCK_FILE_SCRIPT = PropertiesLoader.getValueFor("SORT_STOCK_FILE_SCRIPT");
	
	public static final String INTERFACE_STOCK_BALANCE_SCHEMA_NAME = PropertiesLoader.getValueFor("INTERFACE_STOCK_BALANCE_SCHEMA_NAME");
	public static final String INTERFACE_STOCK_BALANCE_JDBC_URL = PropertiesLoader.getValueFor("INTERFACE_STOCK_BALANCE_JDBC_URL");
	public static final String INTERFACE_STOCK_BALANCE_JDBC_USER = PropertiesLoader.getValueFor("INTERFACE_STOCK_BALANCE_JDBC_USER");
	public static final String INTERFACE_STOCK_BALANCE_JDBC_PASS = PropertiesLoader.getValueFor("INTERFACE_STOCK_BALANCE_JDBC_PASS");
	
	public static final String IS_SFTP_ATTR = PropertiesLoader.getValueFor("IS_SFTP_ATTR");
	public static final String EXCLUDED_OPERATOR_TYPES = PropertiesLoader.getValueFor("EXCLUDED_OPERATOR_TYPES_ATTR");
	public static final String EXCLUDED_ORGANIZATION_TYPES = PropertiesLoader.getValueFor("EXCLUDED_ORG_TYPES_ATTR");
	
	public static final String FIELD_VALIDATION_CONF_ATTR = PropertiesLoader.getValueFor("FIELD_VALIDATION_CONF_ATTR");
	public static final String MAX_ALLOWED_SN_PER_ORDER = PropertiesLoader.getValueFor("MAX_ALLOWED_SN_PER_ORDER");
	public static final String DWH_USER_CODE = PropertiesLoader.getValueFor("DWH_USER_CODE");
	public static final String CSV_SPLITTER_1 = PropertiesLoader.getValueFor("CSV_SPLITTER_1");
	public static final String CSV_SPLITTER_2 = PropertiesLoader.getValueFor("CSV_SPLITTER_2");
	public static final String CSV_SPLITTER_3 = PropertiesLoader.getValueFor("CSV_SPLITTER_3");
	public static final String CSV_SPLITTER_5 = PropertiesLoader.getValueFor("CSV_SPLITTER_5");
	
	public static final String CSV_CAP_DELIMITER = PropertiesLoader.getValueFor("CSV_CAP_DELIMITER");
	public static final Long STOCK_ENTRY_ORDER_TYPE = Long.valueOf(PropertiesLoader.getValueFor("STOCK_ENTRY_ORDER_TYPE"));
	public static final String STOCK_ENTRY_ORDER_NAME = PropertiesLoader.getValueFor("STOCK_ENTRY_ORDER_NAME");
	public static final Long SALES_RETURN_ORDER_TYPE = Long.valueOf(PropertiesLoader.getValueFor("SALES_RETURN_ORDER_TYPE"));
	public static final String SALES_RETURN_ORDER_NAME = PropertiesLoader.getValueFor("SALES_RETURN_ORDER_NAME");
	public static final Long SELL_IN_ORDER_TYPE = Long.valueOf(PropertiesLoader.getValueFor("SELL_IN_ORDER_TYPE"));
	public static final String SELL_IN_ORDER_NAME = PropertiesLoader.getValueFor("SELL_IN_ORDER_NAME");
	public static final Long CHAR_PRD_SL_TYPE = Long.valueOf(PropertiesLoader.getValueFor("CHAR_PRD_SL_TYPE"));
	public static final Long NUM_PRD_SL_TYPE = Long.valueOf(PropertiesLoader.getValueFor("NUM_PRD_SL_TYPE"));
	public static final Long NUM_LONG_PRD_SL_TYPE = Long.valueOf(PropertiesLoader.getValueFor("NUM_LONG_PRD_SL_TYPE"));
	
	public static final String SP_CAT_REF_CODE = PropertiesLoader.getValueFor("SP_CAT_REF_CODE");
	 public static final String VALIDATION_CONF_DELIMITER_ATTR = PropertiesLoader.getValueFor("VALIDATION_CONF_DELIMITER_ATTR");
	 public static final String REJECTION_FILE_HEADERS_ATTR = PropertiesLoader.getValueFor("REJECTION_FILE_HEADERS_ATTR");
	 public static final String SECOND_FILE_REJECTION_HEADERS_ATTR = PropertiesLoader.getValueFor("SECOND_FILE_REJECTION_HEADERS_ATTR");
	 public static final List<String> STARTER_PACK_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("STARTER_PACK_INTERFACES").split(","));
	 public static final String SORT_ALLOC_DUMP_SCRIPT = PropertiesLoader.getValueFor("SORT_ALLOC_DUMP_SCRIPT");
	 
	 public static final Long SP_STOCK_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SP_STOCK_DUMP_INTERFACE_ID"));
	 public static final Long SP_ALLOC_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SP_ALLOC_DUMP_INTERFACE_ID"));
	 
	 public static final String SORT_PRODUCT_SCRIPT = PropertiesLoader.getValueFor("SORT_PRODUCT_SCRIPT");
	 public static final String productInsertQry = "INSERT INTO interface.product_details(productId, productName, category, subCategory, startDt, endDt, salesPrice, mrpPrice, isSerialized, serilType, serialLength, file_id_n, status, error_code, error_message, materialCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 public static final String tnmProductInsertQry = "INSERT INTO interface.product_details(productId, productName, category, subCategory, startDt, endDt, salesPrice, mrpPrice, isSerialized, serilType, serialLength, file_id_n, status, error_code, error_message, materialCode, minOrderQty, minPackageQty) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 public static final String productTblUpdtQry = "UPDATE interface.product_details set status = ?,error_code=?,error_message=? where prd_details_id=?";
	 public static final String getProductDtlsQry = "select * from interface.product_details where file_id_n = ? and status = ?  order by productid";
	 public static final String productCreationFailQry = "select productId, productname, category, subcategory, startdt, enddt, salesprice, mrpprice, isserialized, seriltype, seriallength, error_code, error_message, materialCode, minOrderQty, minPackageQty from interface.product_details where file_id_n = ? and status = ?";
	 public static final String productCreationDelQry = "delete from interface.product_details where file_id_n = ?";
	 
	 public static final String stockEntryUploadFailQry = "SELECT from_node,to_node,bill_no,bill_date,product_id,quantity,start_serial,end_serial,expiry_date,iccid_adnl_sn_1,msisdn_adnl_sn_2,imsi_adnl_sn_3,adnl_sn_4,error_code_v,error_message_v   FROM  INTERFACE.STOCK_ENTRY_UPLOAD WHERE file_id_n = ? and status_n = ?;";
	 public static final String stockEntryUploadDelQry = "DELETE FROM  INTERFACE.STOCK_ENTRY_UPLOAD WHERE file_id_n = ? and  status_n = ? ";

	 public static final String FILE_HEADER_VALIDATION_ATTR = PropertiesLoader.getValueFor("FILE_HEADER_VALIDATION_ATTR");
	 public static final Long DEFAULT_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("DEFAULT_USER_ID"));
	 public static final Long FOSS_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("FOSS_USER_ID"));
	 public static final Long IDM_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("IDM_USER_ID"));
	 public static final Long DWH_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("DWH_USER_ID"));
	 public static final Long NDB_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("NDB_USER_ID"));
	 public static final String SORT_SERIAL_EXPIRY_SCRIPT = PropertiesLoader.getValueFor("SORT_SERIAL_EXPIRY_SCRIPT");
	 public static final String serialExpryInsertQry = "INSERT INTO interface.serial_expiry( sl_no, old_prd_code, new_prd_code, expiry_dt, file_id, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	 public static final String serialExpryUpdtQry = "UPDATE interface.serial_expiry set status = ?,error_code=?,error_message=? where serial_expiry_id in (serialExpryIds)";

	 public static final String srlExpryUpdtQry = "UPDATE interface.serial_expiry set status = ?,error_code=?,error_message=? where sl_no=? and old_prd_code=? and status=?";
	 public static final String tnmSrlExpryUpdtQry = "UPDATE interface.serial_expiry set status = ?, error_code = ?, error_message = ? where sl_no = ? and new_prd_code = ? and status = ?";
	 public static final String getSerialExpryQry = "select * from interface.serial_expiry pd where pd.file_id=? and pd.status=?  order by pd.sl_no";
	 public static final String spSerialExpryTotalsQry = "select status::character varying, count(*) from interface.serial_expiry where file_id = ? group by status union select 'total', count(*) from interface.serial_expiry where file_id = ?";
	 public static final String serialExpiryFailQry = "select sl_no, old_prd_code, new_prd_code, expiry_dt, error_code, error_message from interface.serial_expiry where file_id = ? and status = ?";
	 public static final String serialExpiryDelQry = "delete from interface.serial_expiry where file_id = ?";
	 
	 public static final Long PRODUCT_CREATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("PRODUCT_CREATION_INTERFACE_ID"));
	 public static final Long STOCK_ENTRY_UPLOAD_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("STOCK_ENTRY_UPLOAD_INTERFACE_ID"));
	 public static final Long SERIAL_EXPIRY_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SERIAL_EXPIRY_INTERFACE_ID"));
	 public static final String EDIT_REMOTE_WS_URL_ATTR = PropertiesLoader.getValueFor("EDIT_REMOTE_WS_URL_ATTR");
	 
	 public static final String voucherStockDumpDistSelectQry = "select distinct dealer_id, source_dealer_id, program_code, so_id from interface.stock_dump_voucher where file_id_n = ?  and status = ?";
	 public static final String voucherStockDumpSelectQry = "select stock_dump_id,dealer_id,source_dealer_id,program_code,so_id,serial_number from interface.stock_dump_voucher where file_id_n = ? and status = ?";
	 public static final String voucherStockDumpSelDistQry = "select so_id, program_code, product_expired_date from interface.stock_dump_voucher where file_id_n = ? and status = ? group by so_id, program_code, product_expired_date";
	 public static final String voucherStockDumpSelDistExpDtQry = "select * from interface.stock_dump_voucher where so_id = ? and program_code = ? and file_id_n = ? and status = ? and product_expired_date = ?";
	 public static final String voucherStockDumpValidateSerialFaiureQry = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and program_code = ? and file_id_n = ? and status = ?";
	 public static final String voucherStockDumpSelQry = "SELECT serial_number, do_id, so_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, stock_transfer_date, program_code, program_name, source_dealer_id, file_id_n, status, error_code, error_message, stock_dump_id FROM interface.stock_dump_voucher where so_id = ?  and file_id_n = ? and status = ? order by program_code, product_expired_date, serial_number";
	 public static final String voucherStockDumpInsertQuery = "INSERT INTO interface.stock_dump_voucher( serial_number, do_id, so_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, stock_transfer_date, program_code, program_name, source_dealer_id,file_id_n, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 public static final String voucherStockDumpUpdateQry = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and file_id_n = ? and status = ?";
	 public static final String voucherStockDumpTotalsQry = "select status::character varying, count(*) from interface.stock_dump_voucher where file_id_n = ? group by status union select 'total', count(*) from interface.stock_dump_voucher where file_id_n = ?";
	 public static final String voucherStockDumpSuccessCountQry = "select count(1) from interface.stock_dump_voucher where file_id_n = ? and status in (statusIds)";
	 public static final String voucherStockDumpFailQry = "select serial_number, do_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, stock_transfer_date, program_code, program_name, source_dealer_id, error_code, error_message from interface.stock_dump_voucher where file_id_n = ? and status = ?";
	 public static final String voucherStockDumpSuccessRecordsDelQry = "delete from interface.stock_dump_voucher where file_id_n = ? and status = ?";
	 public static final String voucherStockDumpUptFailQry = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where stock_dump_id = ?";
	 public static final String voucherStockDumpValidateQry = "select stock_dump_id from interface.stock_dump_voucher where file_id_n = ? and status not in (statusIds) limit 1";
	 public static final String voucherStockDumpStatusUpdtQry = "update interface.stock_dump_voucher set status = ? where so_id in (soIdset) and file_id_n = ?";
	 
	 public static final String voucherAllocDumpDistSelectQry = "select distinct dealer_id, program_code, so_id from interface.alloc_dump_voucher where file_id_n = ?  and status = ?";
	 public static final String voucherAllocDumpSelectQry = "select alloc_dump_id,dealer_id,program_code,so_id,alloc_no,serial_number from interface.alloc_dump_voucher where file_id_n = ? and status = ?";
	 public static final String voucherAllocDumpSelDistQry = "select so_id, alloc_no, program_code, product_expired_date from interface.alloc_dump_voucher where file_id_n = ? and status = ? group by so_id, alloc_no, program_code, product_expired_date";
	 public static final String voucherAllocDumpSelDistExpDtQry = "select * from interface.alloc_dump_voucher where so_id = ? and alloc_no = ? and program_code = ? and file_id_n = ? and status = ? and product_expired_date = ?";
	 public static final String voucherAllocDumpValidateSerialFaiureQry = "update interface.alloc_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_no = ? and program_code = ? and file_id_n = ? and status = ?";
	 public static final String voucherAllocDumpInsertQuery = "INSERT INTO interface.alloc_dump_voucher( serial_number, do_id, so_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, so_creation_date, alloc_no, program_code, program_name, resource_type, alloc_date, payment_date, file_id_n, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	 public static final String voucherAllocDumpUpdateQry = "update interface.alloc_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_no = ? and file_id_n = ? and status = ?";
	 public static final String voucherAllocDumpTotalsQry = "select status::character varying, count(*) from interface.alloc_dump_voucher where file_id_n = ? group by status union select 'total', count(*) from interface.alloc_dump_voucher where file_id_n = ?";
	 public static final String voucherAllocDumpSuccessCountQry = "select count(1) from interface.alloc_dump_voucher where file_id_n = ? and status in (statusIds)";
	 public static final String voucherAllocDumpFailQry = "select serial_number, do_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, so_creation_date, alloc_no, program_code, program_name, resource_type, alloc_date, payment_date, error_code, error_message from interface.alloc_dump_voucher where file_id_n = ? and status = ?";
	 public static final String voucherAllocDumpSuccessRecordsDelQry = "delete from interface.alloc_dump_voucher where file_id_n = ? and status = ?";
	 public static final String voucherAllocDumpUptFailQry = "update interface.alloc_dump_voucher set status = ?, error_code = ?,error_message = ? where alloc_dump_id = ?";
	 public static final String voucherAllocDumpValidateQry = "select alloc_dump_id from interface.alloc_dump_voucher where file_id_n = ? and status not in (statusIds) limit 1";
	 public static final String voucherAllocDumpStatusUpdtQry = "update interface.alloc_dump_voucher set status = ? where so_id in (soIdset) and alloc_no in (allocIdSet) and file_id_n = ?";
	 
	 public static final Long VO_STOCK_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VO_STOCK_DUMP_INTERFACE_ID"));
	 public static final Long VO_ALLOC_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VO_ALLOC_DUMP_INTERFACE_ID"));
	 public static final String prdDetailsBulkUpdateQry = "UPDATE interface.product_details set status = ?,error_code=?,error_message=? where prd_details_id in (prdDetailsIds)";
	 public static final String prdDtlsTotalsQry = "select status::character varying, count(*) from interface.product_details where file_id_n = ? group by status union select 'total', count(*) from interface.product_details where file_id_n = ?";
	 
	 public static final String LTYPE_MONGO_MAPPING = PropertiesLoader.getValueFor("LTYPE_MONGO_MAPPING");
	 public static final String REF_ID_ATTR = PropertiesLoader.getValueFor("REF_ID_ATTR");
	 
	 public static final String IS_ENABLED_ATTR = PropertiesLoader.getValueFor("IS_ENABLED_ATTR");
	 
	 public static final String VALIDATE_NODE_BY_CHILD_BEFORE_PROCESS = PropertiesLoader.getValueFor("VALIDATE_NODE_BY_CHILD_BEFORE_PROCESS");
	 
	 public static final String SORT_STOCK_DUMP_VOUCHER_SCRIPT = PropertiesLoader.getValueFor("SORT_STOCK_DUMP_VOUCHER_SCRIPT");
	 public static final String SORT_ALLOC_DUMP_VOUCHER_SCRIPT = PropertiesLoader.getValueFor("SORT_ALLOC_DUMP_VOUCHER_SCRIPT");
	 
	 public static final String SYNC_OFF_ATTR = PropertiesLoader.getValueFor("SYNC_OFF_ATTR");
	 public static final List<String> SYNC_OFF_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("SYNC_OFF_INTERFACE_LIST").split(","));
	 public static final Long ORG_CREATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("ORG_CREATION_INTERFACE_ID"));
	 public static final Long SM_USER_CREATION_MODULE_ID = Long.valueOf(PropertiesLoader.getValueFor("SM_USER_CREATION_MODULE_ID"));
	 public static final Long ORDER_TYPE_ENTITY_TYPE_ID = Long.valueOf(PropertiesLoader.getValueFor("ORDER_TYPE_ENTITY_TYPE_ID"));
	 
	 public static final String VALIDATE_SERIALS_FOR_STOCK_ENTRY = PropertiesLoader.getValueFor("VALIDATE_SERIALS_FOR_STOCK_ENTRY");
	 public static final Long ICCID_ID = Long.valueOf(PropertiesLoader.getValueFor("ICCID_ID"));
	 public static final Long MSISDN_ID = Long.valueOf(PropertiesLoader.getValueFor("MSISDN_ID"));
	 public static final Long IMSI_ID = Long.valueOf(PropertiesLoader.getValueFor("IMSI_ID"));
	 
	public static final Integer MONGO_CONNECTIONS_MAX = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_CONNECTIONS_MAX"));	
	public static final Integer MONGO_CONNECTION_TIMEOUT_MAX = Integer.parseInt(PropertiesLoader.getValueFor("MONGO_CONNECTION_TIMEOUT_MAX"));
	public static final String TRANSACTIONS_DATA_ORGANIZATION_CONFIG = PropertiesLoader.getValueFor("TRANSACTIONS_DATA_ORGANIZATION_CONFIG");	
	public static final String TRANSACTIONS_DATA_ORDERS_CONFIGURATION = PropertiesLoader.getValueFor("TRANSACTIONS_DATA_ORDERS_CONFIGURATION");
	public static final String TRANSACTIONS_DATA_ORGANIZATION_LIST = PropertiesLoader.getValueFor("TRANSACTIONS_DATA_ORGANIZATION_LIST");

	public static final Long STARTER_PACK_CATEGORY_ID = Long.valueOf(PropertiesLoader.getValueFor("STARTER_PACK_CATEGORY_ID"));
	public static final Long VOUCHER_CATEGORY_ID = Long.valueOf(PropertiesLoader.getValueFor("VOUCHER_CATEGORY_ID"));
	public static final String VO_CAT_REF_CODE = PropertiesLoader.getValueFor("VO_CAT_REF_CODE");
	
	public static final Long MULTIPLY_FACTOR = Long.valueOf(PropertiesLoader.getValueFor("MULTIPLY_FACTOR"));
	
	public static final String SORT_USER_SYNC_SCRIPT = PropertiesLoader.getValueFor("SORT_USER_SYNC_SCRIPT");
	public static final String FOSS_SYSTEM = PropertiesLoader.getValueFor("FOSS_SYSTEM");
	public static final String SALDOMOBO_SYSTEM = PropertiesLoader.getValueFor("SALDOMOBO_SYSTEM");
	public static final String DWH_SYSTEM = PropertiesLoader.getValueFor("DWH_SYSTEM");
	public static final String NDB_SYSTEM = PropertiesLoader.getValueFor("NDB_SYSTEM");
	public static final String HADOOP_SYSTEM = PropertiesLoader.getValueFor("HADOOP_SYSTEM");
	public static final String INTERFACE_CHANNEL = PropertiesLoader.getValueFor("INTERFACE_CHANNEL");
	public static final String UPLOAD_CHANNEL = PropertiesLoader.getValueFor("UPLOAD_CHANNEL");
	
	public static final String SORT_ORG_SYNC_SCRIPT = PropertiesLoader.getValueFor("SORT_ORG_SYNC_SCRIPT");
	public static final String ORG_POS_ROLE_CONF = PropertiesLoader.getValueFor("orgTypePosRoleMapping");
	public static final String OPR_POS_ROLE_CONF = PropertiesLoader.getValueFor("oprTypePosRoleMapping");
	public static final String PROPS_LIST = PropertiesLoader.getValueFor("propsList");
	public static final Long ORG_SYNC_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("ORG_SYNC_INTERFACE_ID"));
	public static final Long USER_SYNC_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("USER_SYNC_INTERFACE_ID"));
	
	public static final String FILE_NAME_SEQ_ATTR = PropertiesLoader.getValueFor("FILE_NAME_SEQ_ATTR");
	public static final String PROCESSOR_URL_ATTR = PropertiesLoader.getValueFor("PROCESSOR_URL_ATTR"); 
	public static final Long INDOSAT_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("INDOSAT_USER_ID"));
	public static final String UPLOAD_SYSTEM = PropertiesLoader.getValueFor("UPLOAD_SYSTEM");
	public static final List<String> ORG_USER_SYNC_UPLOAD_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("ORG_USER_SYNC_UPLOAD_INTERFACES").split(","));
	
	public static final String SORT_DEACTIVATION_FEED_SCRIPT = PropertiesLoader.getValueFor("SORT_DEACTIVATION_FEED_SCRIPT");
	public static final String deactivationFeedInsertQuery = "INSERT INTO interface.dwh_deactivation_feed(msisdn, deactivation_date, file_id, status, error_code, error_message ) VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String USER_SYNC_DUMP_DATA_CONFIG = PropertiesLoader.getValueFor("USER_SYNC_DUMP_DATA_CONFIG");
	public static final Long USER_SYNC_DUMP_TYPE_ENTITY_TYPE_ID = Long.valueOf(PropertiesLoader.getValueFor("USER_SYNC_DUMP_TYPE_ENTITY_TYPE_ID"));
	
	public static final String deactivationFeedUpdateQry = "update interface.dwh_deactivation_feed set status = ?, error_code = ?, error_message = ? where trans_id in (transIds)";
	public static final String deactivationFeedSucessUpdateQry = "update interface.dwh_deactivation_feed set status = ?, error_code = ?, error_message = ? where msisdn in (msisdnIds) and file_id = ? and status = ?";
	
	public static final String SORT_ACTIVATION_FEED_SCRIPT = PropertiesLoader.getValueFor("SORT_ACTIVATION_FEED_SCRIPT");
	public static final String activationFeedInsertQry = "INSERT INTO interface.dwh_activation_feed(msisdn, activation_dt, lac, ci, file_id, status, error_code, error_message) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String activationFeedUpdateQry = "UPDATE interface.dwh_activation_feed SET status = ?,error_code=?,error_message=? WHERE file_id = ? and status = ?";
	public static final String activationFeedBlkUpdtQry = "UPDATE interface.dwh_activation_feed SET status = ?,error_code=?,error_message=? WHERE act_feed_id in (actFeedId)";
	public static final String activationFeedSelectQry = "SELECT *  FROM interface.dwh_activation_feed WHERE file_id = ? and status = ?";
	public static final String activationFeedTotalQry = "SELECT status, count(*) FROM interface.dwh_activation_feed where file_id = ? group by status union select 'total', count(*) from interface.dwh_activation_feed where file_id = ?";
	public static final String activationFeedSuccessQry = "SELECT count(1) FROM interface.dwh_activation_feed where file_id = ? and status = ?";
	public static final String activationFeedUpdtQry = "UPDATE interface.dwh_activation_feed SET status = ?,error_code=?,error_message=? WHERE msisdn = ? AND file_id = ? AND status = ?";
	public static final String activationFdUpdtQry = "UPDATE interface.dwh_activation_feed SET status = ?,error_code=?,error_message=? WHERE msisdn in (msisdnIds) AND file_id = ? AND status = ?";
	public static final Long ACTIVATION_FEED_BATCH_SIZE = Long.valueOf(PropertiesLoader.getValueFor("ACTIVATION_FEED_BATCH_SIZE"));
	public static final Long ACTIVATION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("ACTIVATION_FEED_INTERFACE_ID"));
	public static final Long DEACTIVATION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("DEACTIVATION_FEED_INTERFACE_ID"));
	public static final String activationFeedDelQry = "delete from interface.dwh_activation_feed where file_id = ?";
	public static final String deactivationFeedDelQry = "delete from interface.dwh_deactivation_feed where file_id = ?";
	public static final String activationFeedSuccessRecordsDelQry = "delete from interface.dwh_activation_feed where file_id = ? and status = ?";
	public static final String deactivationFeedSuccessRecordsDelQry = "delete from interface.dwh_deactivation_feed where file_id = ? and status = ?";
	public static final String deactivationFeedSuccessQry = "SELECT count(1) FROM interface.dwh_deactivation_feed where file_id = ? and status = ?";
	public static final String voucherRedemptionFeedDelQry = "delete from interface.voucher_redemption_feed where file_id = ?"; 
	public static final String SORT_VOUCHER_REDEMPTION_SCRIPT = PropertiesLoader.getValueFor("SORT_VOUCHER_REDEMPTION_SCRIPT");
	public static final String voucherRedemptionFeedInsertQry = "INSERT INTO interface.voucher_redemption_feed(date, lac_id, ci_id, vc_serial_number, redeem_amount, msisdn, file_id, status, error_code, error_message) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String voucherRedemptionFeedSelectQry = "SELECT *  FROM interface.voucher_redemption_feed WHERE file_id = ? and status = ?";
	public static final Long VOUCHER_REDEMPTION_FEED_BATCH_SIZE = Long.valueOf(PropertiesLoader.getValueFor("VOUCHER_REDEMPTION_FEED_BATCH_SIZE"));
	public static final Long VOUCHER_REDEMPTION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VOUCHER_REDEMPTION_FEED_INTERFACE_ID"));
	public static final String voucherRedemptionFeedBlkUpdtQry = "UPDATE interface.voucher_redemption_feed SET status = ?, error_code = ?, error_message = ? WHERE redeem_feed_id in (redeemFeedId)";
	public static final String voucherRedemptionFdUpdtQry = "UPDATE interface.voucher_redemption_feed SET status = ?, error_code = ?, error_message = ? WHERE vc_serial_number in (vcSerialNos) AND file_id = ? AND status = ?";
	public static final String voucherRedemptionFeedTotalQry = "SELECT status, count(*) FROM interface.voucher_redemption_feed where file_id = ? group by status union select 'total', count(*) from interface.voucher_redemption_feed where file_id = ?";
	public static final String lacciDataInsertQry = "INSERT INTO interface.lacci_data(date_active, lac_id, cell_id, description, bts_id, bts_name, bts_type,site_id,site_name,site_type,territory_id,updated_date,latitude,longitude,file_id,status, error_code, error_message) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String lacciDataSelectQry = "SELECT *  FROM interface.lacci_data WHERE file_id = ? and status = ?";
	public static final String lacciDataDistinctIdQry = "SELECT bts_id,bts_type,bts_name,site_id,site_type,site_name,array_to_string(array_agg(lacci_data_id),',') lacciDataIds FROM interface.lacci_data WHERE file_id = ? and status = ? group by bts_id, bts_type, bts_name, site_id, site_type, site_name";
	public static final String lacciDataBlkUpdtQry = "UPDATE interface.lacci_data SET status = ?, error_code = ?, error_message = ? WHERE lacci_data_id in (lacciDataId)";
	public static final String lacciDataUpdtQry = "UPDATE interface.lacci_data SET status = ?, error_code = ?, error_message = ? WHERE file_id = ? AND status = ? AND cell_id = ? AND lac_id = ?";
	public static final String lacciDataTotalQry = "SELECT status, count(*) FROM interface.lacci_data where file_id = ? group by status union select 'total', count(*) from interface.lacci_data where file_id = ?";
	public static final String lacciDataFailQry = "SELECT date_active, lac_id, cell_id, description, bts_id, bts_name, bts_type,site_id,site_name,site_type,territory_id,updated_date,latitude,longitude,error_code, error_message from interface.lacci_data where file_id = ? and status = ?";
	public static final String lacciDataBlkUpdtQryWithBatch = "UPDATE interface.lacci_data SET status = ?, error_code = ?, error_message = ?,snoc_bts_id = ?,snoc_bts_type = ?,snoc_site_id = ?,snoc_site_type = ?  WHERE lacci_data_id in (lacciDataId) and file_id = ? and status = ?";
	public static final String lacciDataBtsTypeUpdtQry = "UPDATE interface.lacci_data SET status = ?, error_code = ?, error_message = ?, snoc_bts_id = ?, snoc_bts_type = ? WHERE lacci_data_id in (lacciDataId) and file_id = ? and status = ?";
	public static final String lacciDataSiteTypeUpdtQry = "UPDATE interface.lacci_data SET status = ?, error_code = ?, error_message = ?, snoc_site_id = ?,snoc_site_type = ? WHERE lacci_data_id in (lacciDataId) and file_id = ? and status = ?";
	public static final String LACCI_DATA_MASTER_CREATE_DEFAULT_ORG_DTLS = PropertiesLoader.getValueFor("LACCI_DATA_MASTER_CREATE_DEFAULT_ORG_DTLS");
	public static final Long LACCI_DATA_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("LACCI_DATA_INTERFACE_ID"));
	public static final String SORT_LACCI_DATA_SCRIPT = PropertiesLoader.getValueFor("SORT_LACCI_DATA_SCRIPT");
	public static final String lacciDataDeleteQry = "delete from interface.lacci_data where file_id = ? and status = ?";
	public static final String siteMappingDataInsertQry = "INSERT INTO INTERFACE.TR_TEMP_SITE_MAPPING(FILE_ID_N, DATA_STRING_V, DATE_V, SITE_ID_V, LONGITUDE_V, LATITUDE_V, MICRO_CLUSTER_V, SALES_CLUSTER_V, SALES_AREA_V, AREA_V, REGION_V, JAVA_NONJAVA_V, SITE_NAME_V, SITE_POPULATION_V, STATUS_N, ERROR_CODE_N, ERROR_MESSAGE_V) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String siteMappingDataSelectQryAfterValidation = "SELECT DATE_V, SITE_ID_V, SITE_NAME_V, SITE_POPULATION_V, LONGITUDE_V, LATITUDE_V, MICRO_CLUSTER_V, SALES_CLUSTER_V, SALES_AREA_V, AREA_V, REGION_V, TEMP_ID_N FROM INTERFACE.TR_TEMP_SITE_MAPPING WHERE FILE_ID_N = ? AND ERROR_CODE_N IS NULL  order by date_v, site_id_v, micro_cluster_v;";
	public static final String siteMappingDuplicateErrorUpdate = "update interface.tr_temp_site_mapping set status_n = ?, error_code_n = ?, error_message_v = ? where temp_id_n = ?";
	public static final String siteMappingKpiFunCall = "{call kpi.insert_site_mapping_master(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	public static final String siteMappingFailureinFunCall = "update interface.tr_temp_site_mapping  set  error_code_n = ?, error_message_v = ? where file_id_n = ? and error_code_n is not null";
	public static final String interfaceFailureCountQuery = "select count(1) from interface.tr_temp_site_mapping where file_id_n = ? and error_code_n is not null";
	public static final String siteMappingDataDeleteQry = "delete from interface.tr_temp_site_mapping where file_id_n = ?;";
	public static final String kpiFailureCountQuery = "select count(1) from kpi.tr_temp_upload_master_failure where file_id_n = ?";
	public static final String tempSiteMappingDltQuery = "delete from interface.tr_temp_site_mapping where file_id_n = ?"; 
	public static final List<String> SM_SYNC_INTERFACE_IDS = Arrays.asList(PropertiesLoader.getValueFor("SM_SYNC_INTERFACE_IDS").split(","));
	public static final Long PROD_CAT_ENTITY_TYPE_ID = Long.valueOf(PropertiesLoader.getValueFor("PROD_CAT_ENTITY_TYPE_ID"));
	
	public static final String duplicateTransCheckQry = "select status_n from interface.tr_interface_summary where interface_id_n = ? and ref_data1_v = ? and status_n in (statusIds)";
	public static final String updateVoucherStockDupTransQry = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and file_id_n = ? and status = ?";
	public static final String updateVoucherAllocDupTransQry = "update interface.alloc_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_no = ? and file_id_n = ? and status = ?";
	public static final String updateSPStockDupTransQry = "update interface.sp_stock_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and file_id = ? and status = ?";
	public static final String updateSPAllocDupTransQry = "update interface.sp_alloc_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_id = ? and file_id = ? and status = ?";
	public static final String updateSPStockInvalidSerialQry = "update interface.sp_stock_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and program_code = ? and file_id = ? and status = ? and iccid = ?";
	public static final String updateSPAllocInvalidSerialQry = "update interface.sp_alloc_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_id = ? and program_code = ? and file_id = ? and status = ? and iccid = ?";
	
	public static final Long PENDING_FOR_RES_UPDATE_STATUS = Long.valueOf(PropertiesLoader.getValueFor("PENDING_FOR_RES_UPDATE_STATUS"));
	public static final String UPDATE_PAYMENT_DATE_URL = PropertiesLoader.getValueFor("UPDATE_PAYMENT_DATE_URL");
	public static final String QUEUE_CONSUMER_ATTR = PropertiesLoader.getValueFor("QUEUE_CONSUMER_ATTR");
	public static final List<String> PENDING_FOR_RESPONSE_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("PENDING_FOR_RESPONSE_INTERFACES").split(","));
	public static final String SNOC_WS_URL_ATTR = PropertiesLoader.getValueFor("SNOC_WS_URL_ATTR");
	public static final String SNOC_WS_USER_ATTR = PropertiesLoader.getValueFor("SNOC_WS_USER_ATTR");
	public static final String SNOC_WS_PASSWORD_ATTR = PropertiesLoader.getValueFor("SNOC_WS_PASSWORD_ATTR");
	public static final String SNOC_SERVICE_TYPE_ATTR = PropertiesLoader.getValueFor("SNOC_SERVICE_TYPE_ATTR");
	public static final Long SP_STOCK_DUMP_PUB_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SP_STOCK_DUMP_PUB_INTERFACE_ID"));
	public static final Long SP_ALLOC_DUMP_PUB_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SP_ALLOC_DUMP_PUB_INTERFACE_ID"));
	public static final Long VO_STOCK_DUMP_PUB_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VO_STOCK_DUMP_PUB_INTERFACE_ID"));
	public static final Long VO_ALLOC_DUMP_PUB_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VO_ALLOC_DUMP_PUB_INTERFACE_ID"));
	public static final Integer NUMERIC_LONG_SERIAL_LENGTH = Integer.parseInt(PropertiesLoader.getValueFor("NUMERIC_LONG_SERIAL_LENGTH"));
	public static final Integer ACTUAL_SERIAL_LENGTH = Integer.parseInt(PropertiesLoader.getValueFor("ACTUAL_SERIAL_LENGTH"));
	
	public static final String updateInvalidSerialsInVoucherAllocDump = "update interface.alloc_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_no = ? and program_code = ? and file_id_n = ? and status = ? and serial_number = ?";
	public static final String updateInvalidSerialsInVoucherStockDump = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and program_code = ? and file_id_n = ? and status = ? and serial_number = ?";
	public static final String updateSupplierFailureInVoucherStockDump =  "update interface.stock_dump_voucher set status = ?, error_code = ? ,error_message = ? where so_id = ? and program_code = ? and file_id_n = ? and status = ?";
	
	public static final Long LANGUAGE_ENTITY_TYPE_ID = Long.valueOf(PropertiesLoader.getValueFor("LANGUAGE_ENTITY_TYPE_ID"));
	public static final String LTYPE_COMPLEX_MONGO_MAPPING = PropertiesLoader.getValueFor("LTYPE_COMPLEX_MONGO_MAPPING");
	public static final String SKIP_COMPRESSION_FOR_SEND_FILES = PropertiesLoader.getValueFor("SKIP_COMPRESSION_FOR_SEND_FILES");
	public static final Long STATUS_ENTITY_TYPE = Long.valueOf(PropertiesLoader.getValueFor("STATUS_ENTITY_TYPE"));
	public static final Long SO_STATUS_ENTITY_TYPE = Long.valueOf(PropertiesLoader.getValueFor("SO_STATUS_ENTITY_TYPE"));
	
	public static final String NOTIFY_EMAIL_REF_CODE = PropertiesLoader.getValueFor("NOTIFY_EMAIL_REF_CODE");
	public static final String NOTIFY_SMS_REF_CODE = PropertiesLoader.getValueFor("NOTIFY_SMS_REF_CODE");
	public static final Long ERROR_MESSAGE_CHAR_LIMIT = Long.valueOf(PropertiesLoader.getValueFor("ERROR_MESSAGE_CHAR_LIMIT"));
	public static final String REF_CODE_PATH_ATTR = PropertiesLoader.getValueFor("REF_CODE_PATH_ATTR");
	public static final String ORG_TYPE_PATH_ATTR = PropertiesLoader.getValueFor("ORG_TYPE_PATH_ATTR");
	public static final Long ORG_STATUS_CHANGE_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("ORG_STATUS_CHANGE_INTERFACE_ID"));
	public static final Long ORG_MODIFICATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("ORG_MODIFICATION_INTERFACE_ID"));
	public static final Long USER_STATUS_CHANGE_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("USER_STATUS_CHANGE_INTERFACE_ID"));
	public static final Long USER_MODIFICATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("USER_MODIFICATION_INTERFACE_ID"));
	
	public static final int QUERY_LIMIT = Integer.valueOf(PropertiesLoader.getValueFor("QUERY_LIMIT"));
	public static final String EXCLUDED_SUB_ORG_TYPES_ATTR = PropertiesLoader.getValueFor("EXCLUDED_SUB_ORG_TYPES_ATTR");
	public static final String MAX_SERIAL_COUNT_ATTR = PropertiesLoader.getValueFor("MAX_SERIAL_COUNT_ATTR");
	public static final String CHANGE_FLAG_CREATE = PropertiesLoader.getValueFor("CHANGE_FLAG_CREATE");
	public static final String CHANGE_FLAG_UPDATE = PropertiesLoader.getValueFor("CHANGE_FLAG_UPDATE");
	public static final String CHANGE_FLAG_STATUS_UPDATE = PropertiesLoader.getValueFor("CHANGE_FLAG_STATUS_UPDATE");
	
	public static final Long MAX_COUNT_FOR_SERIAL_VALIDATATION = Long.parseLong(PropertiesLoader.getValueFor("MAX_COUNT_FOR_SERIAL_VALIDATATION"));
	public static final String interfaceSummaryTotalsQry = "select status_n::character varying, count(*) from interface.tr_interface_summary where ref_data3_n = ? group by status_n union select 'total', count(*) from interface.tr_interface_summary where ref_data3_n = ?";
	
	public static final String voucherAllocDumpPaymentDateQuery = "select distinct payment_date from interface.alloc_dump_voucher where so_id = ? and alloc_no = ? and file_id_n = ? order by payment_date desc";
	public static final String spAllocDumpPaymentDateQuery = "select distinct payment_date from interface.sp_alloc_dump where so_id = ? and alloc_id = ? and file_id = ? order by payment_date desc";
	public static final String ORDER_SUCCESS_STATUS = PropertiesLoader.getValueFor("ORDER_SUCCESS_STATUS");
	public static final Long ORG_TERMINATION_STATUS = Long.valueOf(PropertiesLoader.getValueFor("ORG_TERMINATION_STATUS"));
	public static final Long USER_CREATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("USER_CREATION_INTERFACE_ID"));
	public static final Long SM_ORG_STATUS_CHANGE_MODULE_ID = Long.valueOf(PropertiesLoader.getValueFor("SM_ORG_STATUS_CHANGE_MODULE_ID"));

	public static final String COLLECTION_NAME_ATTR = PropertiesLoader.getValueFor("COLLECTION_NAME_ATTR");
	public static final String SCHEMA_NAME_ATTR = PropertiesLoader.getValueFor("SCHEMA_NAME_ATTR");
	public static final String MONGO_CONNECTION_ID_ATTR = PropertiesLoader.getValueFor("MONGO_CONNECTION_ID_ATTR");
	public static final String DAILY_DUMP_CONF_ATTR = PropertiesLoader.getValueFor("DAILY_DUMP_CONF_ATTR");
	public static final String QUERY_FIELD_ATTR = PropertiesLoader.getValueFor("QUERY_FIELD_ATTR");
	public static final String FILTER_QUERY_ATTR = PropertiesLoader.getValueFor("FILTER_QUERY_ATTR");
	public static final String CSV_SPLITTER_4 = PropertiesLoader.getValueFor("CSV_SPLITTER_4");
	
	public static final List<String> DAILY_DUMP_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("DAILY_DUMP_INTERFACE_LIST").split(","));
	public static final Long BATCH_SIZE_FOR_UPDATE_VALIDATE_SERIAL = Long.parseLong(PropertiesLoader.getValueFor("BATCH_SIZE_FOR_UPDATE_VALIDATE_SERIAL"));
	public static final String REMOTE_SERVERS_ATTR = PropertiesLoader.getValueFor("REMOTE_SERVERS_ATTR");
	public static final Long TRANSACTION_DETAILS_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("TRANSACTION_DETAILS_INTERFACE_ID"));
	
	public static final List<String> PG_DAILY_DUMP_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("PG_DAILY_DUMP_INTERFACE_LIST").split(","));
	public static final String KYC_FIELD_NAME_TAG = PropertiesLoader.getValueFor("KYC_FIELD_NAME_TAG");
	
	public static final String SPRING_APP_CONFIG_PATH = PropertiesLoader.getValueFor("SPRING_APP_CONFIG_PATH");
	public static final String SEND_CONTROL_FILE_FLAG = PropertiesLoader.getValueFor("SEND_CONTROL_FILE_FLAG");
	
	public static final String DATA_PROCESSOR_CONFIG_PATH = PropertiesLoader.getValueFor("DATA_PROCESSOR_CONFIG_PATH");
	public static final String PROCESS_NAME_ATTR = PropertiesLoader.getValueFor("PROCESS_NAME_ATTR");
	
	public static final String SCHEMA_SNOC_REPORT = "snoc_report";
	public static final String COLLECTION_VOUCHER_INJECTION = "voucher_injection";
	public static final String SERVICE_BATCH_SIZE_ATTR = PropertiesLoader.getValueFor("SERVICE_BATCH_SIZE_ATTR");
	public static final Long VOUCHER_INJECTION_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("VOUCHER_INJECTION_INTERFACE_ID"));
	public static final String voucherInjectionRejectionQry = "select isum.orgnl_request_data_b, ifail.error_code_n, ifail.error_message_v from interface.tr_interface_summary isum left outer join interface.tr_interface_failure ifail on isum.trans_id_n = ifail.trans_id_n where interface_id_n = ? and ref_data3_n = ? and status_n = ?";
	public static final String voucherInjectionTotalQry = "select status_n::character varying, sum(ref_data4_n) from interface.tr_interface_summary where ref_data3_n = ? group by status_n union select 'total', sum(ref_data4_n) from interface.tr_interface_summary where ref_data3_n = ?";
	public static final String DATE_TIME_FORMAT_ATTR = PropertiesLoader.getValueFor("DATE_TIME_FORMAT_ATTR");
	
	public static final String INTERFACE_SUMMARY_STAUS_UPDATE_QUERY = "update interface.tr_interface_summary set status_n = ?, retry_count_n = ? where trans_id_n = ?";
	public static final String INTERFACE_FAILURE_SEQ_QUERY = "select nextval('interface.TR_INTERFACE_FAILURE_SEQ')";
	public static final String INTERFACE_FAILURE_INSERT_QUERY = "insert into interface.tr_interface_failure (trans_failure_id_n, trans_id_n, ack_data_b, ack_time_dt, response_data_b, response_time_dt, file_id_n, error_code_n, error_message_v) values(?,?,?,?,?,?,?,?,?)";
	public static final String INTERFACE_SUMMARY_UPDATE_QUERY = "update interface.tr_interface_summary set request_data_b = ?, ack_data_b = ?, ack_time_dt = ?, orgnl_response_data_b = ?, response_data_b = ?, response_time_dt = ?, status_n = ?, retry_count_n = ?, ref_data1_v = ?, ref_data2_n = ?, ref_data3_n = ?, ref_data4_n = ?, ref_data5_v = ?, ref_data6_v = ?, ref_data7_v = ?, ref_data8_v = ? where trans_id_n = ?";
	public static final String INTERFACE_SUMMARY_SEQ_QUERY = "select nextval('interface.TR_INTERFACE_SUMMARY_SEQ')";
	public static final String INTERFACE_SUMMARY_INSERT_QUERY = "insert into interface.tr_interface_summary (trans_id_n, interface_id_n, orgnl_request_data_b, request_data_b, request_time_dt, ack_data_b, ack_time_dt, orgnl_response_data_b, response_data_b, response_time_dt, status_n, retry_count_n, ref_data1_v, ref_data2_n, ref_data3_n, ref_data4_n, ref_data5_v, ref_data6_v, ref_data7_v, ref_data8_v) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INTERFACE_SUMMARY_SELECT_QUERY = "select summary.*, int.* from interface.ms_interface int , (select * from interface.tr_interface_summary where trans_id_n = ?) summary where int.interface_id_n = summary.interface_id_n";
	public static final String INTERFACE_FILE_SUMMARY_SELECT_QUERY = "select file_id_n, file_name_v, interface_id_n, received_server_v, send_server_v, local_server_v, total_count_n, success_count_n, error_count_n, uploaded_by_n, status_n, message_v, validated_on_dt, processed_on_dt, uploaded_on_dt, retry_count_n from interface.tr_interface_file_summary where file_id_n = ?";
	public static final String INTERFACE_FILE_SUMMARY_UPDATE_QUERY = "update interface.tr_interface_file_summary set total_count_n = ?, success_count_n = ?, error_count_n = ?, filter_count_n = ?, status_n = ?, message_v = ?, processed_on_dt = ?, last_updated_time_dt = ?, error_message_v = ?, retry_count_n = ? where file_id_n = ?";
	public static final String INTERFACE_FILE_SUMMARY_DTLS_SELECT_QUERY = "SELECT file_details_id_n, file_id_n, file_name_v, ctrl_file_name_v, file_type_v, total_count_n, success_count_n, error_count_n, last_updated_time_dt FROM interface.tr_interface_file_summary_details where file_id_n = ?";
	public static final String INTERFACE_FILE_SUM_DTLS_BY_DETAIL_ID_SEL_QUERY = "SELECT file_details_id_n, file_id_n, file_name_v, ctrl_file_name_v, file_type_v, total_count_n, success_count_n, error_count_n, last_updated_time_dt FROM interface.tr_interface_file_summary_details where file_details_id_n = ?";
	public static final String INTERFACE_ATTR_SELECT_QUERY = "select name_v, value_v from interface.ms_interface_attr where interface_id_n = ?";
	public static final String INTERFACE_FILE_SUM_SEQ_QUERY = "select nextval('interface.TR_INTERFACE_FILE_SUM_SEQ')";
	public static final String INTERFACE_FILE_SUM_INSERT_QUERY = "INSERT INTO interface.tr_interface_file_summary(file_id_n, file_name_v, interface_id_n, received_server_v, send_server_v, local_server_v, total_count_n, success_count_n, error_count_n, uploaded_by_n, status_n, message_v, validated_on_dt, processed_on_dt, uploaded_on_dt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INTERFACE_FILE_SUM_DTLS_SEQ_QUERY = "select nextval('interface.TR_INTERFACE_FILE_SUM_DET_SEQ')";
	public static final String INTERFACE_FILE_SUM_DTLS_INSERT_QUERY = "insert into INTERFACE.TR_INTERFACE_FILE_SUMMARY_DETAILS (FILE_DETAILS_ID_N, FILE_ID_N, FILE_NAME_V, CTRL_FILE_NAME_V, FILE_TYPE_V, TOTAL_COUNT_N, SUCCESS_COUNT_N, ERROR_COUNT_N) values(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INTERFACE_FILE_SUM_DTLS_UPDATE_QUERY = "update INTERFACE.TR_INTERFACE_FILE_SUMMARY_DETAILS set FILE_NAME_V = ?, CTRL_FILE_NAME_V = ?, FILE_TYPE_V = ?, TOTAL_COUNT_N = ? , SUCCESS_COUNT_N = ?, ERROR_COUNT_N = ? where FILE_DETAILS_ID_N = ?";
	
	public static final String FILE_DATA_INSERT_QUERY_ATTR = PropertiesLoader.getValueFor("FILE_DATA_INSERT_QUERY_ATTR");
	public static final String FILE_DATA_INSERT_QUERY_PARAM_CONFIG_ATTR = PropertiesLoader.getValueFor("FILE_DATA_INSERT_QUERY_PARAM_CONFIG_ATTR");
	public static final String FILE_DATA_BATCH_SIZE_ATTR = PropertiesLoader.getValueFor("FILE_DATA_BATCH_SIZE_ATTR");
	public static final String MONGO_CONFIG_ATTR = PropertiesLoader.getValueFor("MONGO_CONFIG_ATTR");

	public static final Long ORG_TO_ORG_TRANSFER_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("ORG_TO_ORG_TRANSFER_INTERFACE_ID"));
	public static final Long INDOSAT_TO_ORG_TRANSFER_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("INDOSAT_TO_ORG_TRANSFER_INTERFACE_ID"));

	public static final List<String> SELF_NOTIFY_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("SELF_NOTIFY_INTERFACE_LIST").split(","));
	
	public static final String orgToOrgTransferDistOrgIdQry = "select distinct(organization_id) from interface.org_to_org_transfer where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateOrgToOrgSnocOrgIdQry = "update interface.org_to_org_transfer set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and organization_id = ? and batch_id_n = ?";
	public static final String updateOrgToOrgSnocOrgIdErrorQry = "update interface.org_to_org_transfer set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and organization_id = ? and batch_id_n = ?";
	public static final String updateOrgToOrgBatchErrorQry = "update interface.org_to_org_transfer set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String orgToOrgIdsWithGroupByStatus = "select array_to_string(array_agg(org_to_org_trans_id),','),status from interface.org_to_org_transfer where file_id = ? and batch_id_n = ? group by status";
	public static final String orgToOrgTransferTotalQry = "select status, count(1) from interface.org_to_org_transfer where file_id = ? group by status union select 'total', count(1) from interface.org_to_org_transfer  where file_id = ?";
	
	public static final String indosatToOrgTransferDistOrgIdQry = "select distinct(organization_id) from interface.indosat_to_org_transfer  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateIndosatToOrgSnocOrgIdQry = "update interface.indosat_to_org_transfer set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and organization_id = ? and batch_id_n = ?";
	public static final String updateIndosatToOrgSnocOrgIdErrorQry = "update interface.indosat_to_org_transfer set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and organization_id = ? and batch_id_n = ?";
	public static final String updateIndosatToOrgBatchErrorQry = "update interface.indosat_to_org_transfer set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String indosatToOrgIdsWithGroupByStatus = "select array_to_string(array_agg(indosat_to_org_trans_id),','),status from interface.indosat_to_org_transfer  where file_id = ? and batch_id_n = ? group by status";
	public static final String indosatToOrgTransferTotalQry = "select status, count(1) from interface.indosat_to_org_transfer where file_id = ? group by status union select 'total', count(1) from interface.indosat_to_org_transfer where file_id = ?";
	
	public static final String PROCESSOR_CLASS_ATTR = PropertiesLoader.getValueFor("PROCESSOR_CLASS_ATTR");
	
	public static final String updateSaleRevenueLacciDetailsQry = "update interface.sale_revenue_lacci_details set status = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String saleRevenueLacciDetailsWithGroupByStatus = "select array_to_string(array_agg(sale_revenue_lacci_details_trans_id),','), status from interface.sale_revenue_lacci_details where file_id = ? and batch_id_n = ? group by status";
	public static final String updateSaleRevenueLacciDetailsBatchErrorQry = "update interface.sale_revenue_lacci_details set status = ?, error_code = ?, error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String saleRevenueLacciDetailsTotalQry = "select status, count(1) from interface.sale_revenue_lacci_details where file_id = ? group by status union select 'total', count(1) from interface.sale_revenue_lacci_details  where file_id = ?";
	
	public static final String saleRevenueOrgDistOrgQry = "SELECT distinct(org_short_code) from interface.sale_revenue_organization WHERE file_id = ? AND status = ? AND batch_id_n = ?";
	public static final String saleRevenueOrgSnocOrgIdUpdtQry = "UPDATE interface.sale_revenue_organization SET snoc_org_id_n = ?, status = ? WHERE file_id = ? AND status = ? AND org_short_code = ? AND batch_id_n = ?";
	public static final String saleRevenueOrgErrorOrgUpdtQry = "UPDATE interface.sale_revenue_organization SET status = ?, error_code = ?, error_message = ? where file_id = ? AND status = ? AND org_short_code = ? AND batch_id_n = ?";
	public static final String saleRevenueOrgBatchUpdtQry = "UPDATE interface.sale_revenue_organization SET status = ?, error_code = ?, error_message = ? WHERE file_id = ? AND status = ? AND batch_id_n = ?";
	public static final String saleRevenueOrgIdsGroupbyStatusQry = "SELECT array_to_string(array_agg(sale_revenue_org_id_n),','),status from interface.sale_revenue_organization WHERE file_id = ? AND batch_id_n = ? group by status";
	public static final String saleRevenueTotalQry = "SELECT status, count(1) FROM interface.sale_revenue_organization WHERE file_id = ? GROUP BY status UNION SELECT 'total', count(1) FROM interface.sale_revenue_organization  WHERE file_id = ?";
	
	public static final String pckgActvnADistOrgQry = "SELECT distinct(org_id) from interface.package_activation_a WHERE file_id = ? AND status = ? AND batch_id_n = ?";
	public static final String pckgActvnASnocOrgIdUpdtQry = "UPDATE interface.package_activation_a SET snoc_org_id_n = ?, status = ? WHERE file_id = ? AND status = ? AND org_id = ? AND batch_id_n = ?";
	public static final String pckgActvnAErrorOrgUpdtQry = "UPDATE interface.package_activation_a SET status = ?, error_code = ?, error_message = ? where file_id = ? AND status = ? AND org_id = ? AND batch_id_n = ?";
	public static final String pckgActvnABatchUpdtQry = "UPDATE interface.package_activation_a SET status = ?, error_code = ?, error_message = ? WHERE file_id = ? AND status = ? AND batch_id_n = ?";
	public static final String pckgActvnAOrgIdsGroupbyStatusQry = "SELECT array_to_string(array_agg(pkg_actvtn_a_id_n),','),status from interface.package_activation_a WHERE file_id = ? AND batch_id_n = ? group by status";
	public static final String pckgActvnATotalQry = "SELECT status, count(1) FROM interface.package_activation_a WHERE file_id = ? GROUP BY status UNION SELECT 'total', count(1) FROM interface.package_activation_a  WHERE file_id = ?";
	
	public static final String pckgActvnBDistOrgQry = "SELECT distinct(org_id) from interface.package_activation_b WHERE file_id = ? AND status = ? AND batch_id_n = ?";
	public static final String pckgActvnBSnocOrgIdUpdtQry = "UPDATE interface.package_activation_b SET snoc_org_id_n = ?, status = ? WHERE file_id = ? AND status = ? AND org_id = ? AND batch_id_n = ?";
	public static final String pckgActvnBErrorOrgUpdtQry = "UPDATE interface.package_activation_b SET status = ?, error_code = ?, error_message = ? where file_id = ? AND status = ? AND org_id = ? AND batch_id_n = ?";
	public static final String pckgActvnBBatchUpdtQry = "UPDATE interface.package_activation_b SET status = ?, error_code = ?, error_message = ? WHERE file_id = ? AND status = ? AND batch_id_n = ?";
	public static final String pckgActvnBOrgIdsGroupbyStatusQry = "SELECT array_to_string(array_agg(pkg_actvtn_b_id_n),','),status from interface.package_activation_b WHERE file_id = ? AND batch_id_n = ? group by status";
	public static final String pckgActvnBTotalQry = "SELECT status, count(1) FROM interface.package_activation_b WHERE file_id = ? GROUP BY status UNION SELECT 'total', count(1) FROM interface.package_activation_b  WHERE file_id = ?";
	
	public static final String usageRevenueLACCIDistOrgIdQry = "select distinct(org_id) from interface.usage_revenue_lacci_details where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateUsageRevenueLACCISnocOrgIdQry = "update interface.usage_revenue_lacci_details set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and org_id = ? and batch_id_n = ?";
	public static final String updateUsageRevenueLACCISnocOrgIdErrorQry = "update interface.usage_revenue_lacci_details set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and org_id = ? and batch_id_n = ?";
	public static final String updateUsageRevenueLACCIBatchErrorQry = "update interface.usage_revenue_lacci_details set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String usageRevenueLACCIIdsWithGroupByStatus = "select array_to_string(array_agg(usage_revenue_lacci_details_trans_id),','),status from interface.usage_revenue_lacci_details where file_id = ? and batch_id_n = ? group by status";
	public static final String usageRevenueLACCITotalQry = "select status, count(1) from interface.usage_revenue_lacci_details where file_id = ? group by status union select 'total', count(1) from interface.usage_revenue_lacci_details  where file_id = ?";
	
	public static final String sgeDistOrgIdQry = "select distinct(org_code) from interface.subscriber_generated_event where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateSgeSnocOrgIdQry = "update interface.subscriber_generated_event set snoc_org_id_n = ?,status = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateSgeSnocOrgIdErrorQry = "update interface.subscriber_generated_event set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateSgeBatchErrorQry = "update interface.subscriber_generated_event set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String sgeIdsWithGroupByStatus = "select array_to_string(array_agg(sge_id_n),','),status from interface.subscriber_generated_event where file_id = ? and batch_id_n = ? group by status";
	public static final String sgeTotalQry = "select status, count(1) from interface.subscriber_generated_event where file_id = ? group by status union select 'total', count(1) from interface.subscriber_generated_event where file_id = ?";
	
	public static final String REJECTION_FILE_QUERY_ATTR = PropertiesLoader.getValueFor("REJECTION_FILE_QUERY_ATTR");
	
	public static final String FILE_ROW_COUNT_ATTR = PropertiesLoader.getValueFor("FILE_ROW_COUNT_ATTR");
	
	public static final String transactionSummaryDistOrgIdQry = "select distinct(smorgshortcode) from interface.transaction_summary  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateTransactionSummarySnocOrgIdQry = "update interface.transaction_summary set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and smorgshortcode = ? and batch_id_n = ?";
	public static final String updateTransactionSummarySnocOrgIdErrorQry = "update interface.transaction_summary set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and smorgshortcode = ? and batch_id_n = ?";
	public static final String updateTransactionSummaryBatchErrorQry = "update interface.transaction_summary set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String transactionSummaryIdsWithGroupByStatus = "select array_to_string(array_agg(transaction_summary_trans_id),','),status from interface.transaction_summary  where file_id = ? and batch_id_n = ? group by status";
	public static final String transactionSummaryTotalQry = "select status, count(1) from interface.transaction_summary where file_id = ? group by status union select 'total', count(1) from interface.transaction_summary where file_id = ?";
	
	public static final String updateReloadADetailsQry = "update interface.reload_a set status = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String reloadADetailsWithGroupByStatus = "select array_to_string(array_agg(reload_a_trans_id),','), status from interface.reload_a where file_id = ? and batch_id_n = ? group by status";
	public static final String updateReloadADetailsBatchErrorQry = "update interface.reload_a set status = ?, error_code = ?, error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String reloadADetailsTotalQry = "select status, count(1) from interface.reload_a where file_id = ? group by status union select 'total', count(1) from interface.reload_a  where file_id = ?";
	
	public static final String updateReloadBDetailsQry = "update interface.reload_b set status = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String reloadBDetailsWithGroupByStatus = "select array_to_string(array_agg(reload_b_trans_id),','), status from interface.reload_b where file_id = ? and batch_id_n = ? group by status";
	public static final String updateReloadBDetailsBatchErrorQry = "update interface.reload_b set status = ?, error_code = ?, error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String reloadBDetailsTotalQry = "select status, count(1) from interface.reload_b where file_id = ? group by status union select 'total', count(1) from interface.reload_b  where file_id = ?";
	
	public static final String usageABDistOrgIdQry = "select distinct(org_code) from interface.usage_ab  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateUsageABSnocOrgIdQry = "update interface.usage_ab set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateUsageABSnocOrgIdErrorQry = "update interface.usage_ab set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateUsageABBatchErrorQry = "update interface.usage_ab set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String usageABIdsWithGroupByStatus = "select array_to_string(array_agg(usage_ab_trans_id),','),status from interface.usage_ab  where file_id = ? and batch_id_n = ? group by status";
	public static final String usageABTotalQry = "select status, count(1) from interface.usage_ab where file_id = ? group by status union select 'total', count(1) from interface.usage_ab where file_id = ?";
	
	public static final String addOnABDistOrgIdQry = "select distinct(org_code) from interface.addon_ab  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateAddOnABSnocOrgIdQry = "update interface.addon_ab set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateAddOnABSnocOrgIdErrorQry = "update interface.addon_ab set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateAddOnABBatchErrorQry = "update interface.addon_ab set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String addOnABIdsWithGroupByStatus = "select array_to_string(array_agg(addon_ab_trans_id),','),status from interface.addon_ab  where file_id = ? and batch_id_n = ? group by status";
	public static final String addOnABTotalQry = "select status, count(1) from interface.addon_ab where file_id = ? group by status union select 'total', count(1) from interface.addon_ab where file_id = ?";
	
	public static final String reloadABDistOrgIdQry = "select distinct(org_code) from interface.reload_ab  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateReloadABSnocOrgIdQry = "update interface.reload_ab set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateReloadABSnocOrgIdErrorQry = "update interface.reload_ab set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and org_code = ? and batch_id_n = ?";
	public static final String updateReloadABBatchErrorQry = "update interface.reload_ab set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String reloadABIdsWithGroupByStatus = "select array_to_string(array_agg(reload_ab_trans_id),','),status from interface.reload_ab  where file_id = ? and batch_id_n = ? group by status";
	public static final String reloadABTotalQry = "select status, count(1) from interface.reload_ab where file_id = ? group by status union select 'total', count(1) from interface.reload_ab where file_id = ?";
	
	public static final String reloadSellinSelloutDistOrgIdQry = "select distinct(organization_code) from interface.reload_sellin_sellout where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateReloadSellinSelloutSnocOrgIdQry = "update interface.reload_sellin_sellout set snoc_organization_id_n = ?,status = ? where file_id = ? and status = ? and organization_code = ? and batch_id_n = ?";
	public static final String updateReloadSellinSelloutSnocOrgIdErrorQry = "update interface.reload_sellin_sellout set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and organization_code = ? and batch_id_n = ?";
	public static final String updateReloadSellinSelloutBatchErrorQry = "update interface.reload_sellin_sellout set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String reloadSellinSelloutIdsWithGroupByStatus = "select array_to_string(array_agg(reload_sellin_sellout_id),','),status from interface.reload_sellin_sellout where file_id = ? and batch_id_n = ? group by status";
	public static final String reloadSellinSelloutTotalQry = "select status, count(1) from interface.reload_sellin_sellout where file_id = ? group by status union select 'total', count(1) from interface.reload_sellin_sellout  where file_id = ?";
	
	public static final String orgBalDistOrgIdQry = "select distinct (organization_id) from interface.organization_balance  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateOrgBalSnocOrgIdQry = "update interface.organization_balance set snoc_organization_id_n = ? where file_id = ? and status = ? and organization_id = ? and batch_id_n = ?";
	public static final String updateOrgBalSnocOrgIdErrorQry = "update interface.organization_balance set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and organization_id = ? and batch_id_n = ?";
	public static final String orgBalDistParentOrgIdQry = "select distinct (parent_organization_id) from interface.organization_balance  where file_id = ? and status = ? and batch_id_n = ?";
	public static final String updateOrgBalSnocParentOrgIdQry = "update interface.organization_balance set snoc_parent_organization_id_n = ?,status = ? where file_id = ? and status = ? and parent_organization_id = ? and batch_id_n = ?";
	public static final String updateOrgBalSnocParentOrgIdErrorQry = "update interface.organization_balance set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and parent_organization_id = ? and batch_id_n = ?";
	public static final String updateOrgBalBatchErrorQry = "update interface.organization_balance set status = ?,error_code = ?,error_message = ? where file_id = ? and status = ? and batch_id_n = ?";
	public static final String orgBalIdsWithGroupByStatus = "select array_to_string(array_agg(organization_balance_id),','),status from interface.organization_balance where file_id = ? and batch_id_n = ? group by status";
	public static final String orgBalTotalQry = "select status, count(1) from interface.organization_balance where file_id = ? group by status union select 'total', count(1) from interface.organization_balance  where file_id = ?";
	
	public static final String intfFileSummaryUpdateCountQry = "select status_n,sum(ref_data4_n) from interface.tr_interface_summary where ref_data3_n = ? group by status_n";
	public static final String LINE_UPDATE_STR="lineUpdate";
	public static final String YES_STR="YES";
	
	// KPI Aggregation related constants
	public static final String getKPITemplateQry = "SELECT * from kpi.ms_template_master where template_id_n = ?";
	public static final String actorMappingQry = "select * from kpi.mp_template_actor_mapping where template_id_n = ?";
	public static final String merticsMappingQry = "select * from kpi.ms_tem";
	public static final String instanceSummaryInsrtQry = "INSERT INTO kpi.tr_template_instance_summary(day_id_n, template_id_n, file_name_v, start_time_t, end_time_t, status_v) VALUES (?, ?, ?, now(), now(), 'Initiated')";
	public static final String instanceSummaryUpdtQry = "UPDATE kpi.tr_template_instance_summary SET end_time_t = now(), success_rows_n = ?, failure_rows_n = ?, status_v = ?, last_updated_time_dt = now() WHERE day_id_n = ? AND template_id_n = ? AND file_name_v = ?;";
	public static final String tmpltExcptnSummaryInsrtQry = "INSERT INTO kpi.tr_template_exception_summary(day_id_n, template_id_n, file_name_v, exception_info_v, additional_info_v) VALUES (?, ?, ?, ?, ?)";
	public static final String tmplExcptnCallString = "{ call kpi.insert_template_exception_summary(?, ?, ?, ?, ?) }";
	public static final String PRAMS_MAP = "PARAMS_MAP";
	public static final String QUERY = "QUERY";
	public static final String kpiEventTypeQry = "select * from kpi.ms_event_type_master where system_event_v = 'interfaceId';";
//	public static final String kpiEventFormatQry = "select * from kpi.mp_event_format_mapping where event_type_n = eventType";
	public static final String kpiEventFormatQry = "select m.event_type_n, m.system_type_v, f.source_type_n, f.source_key_v, f.instance_type_n, f.instance_key_v, f.date_format_v, f.date_key_v from kpi.ms_event_type_master m, kpi.mp_event_format_mapping f where m.event_type_n = f.event_type_n and m.system_event_v = 'interfaceId'";
	public static final String tempTableInsertQry = "INSERT INTO kpi.table_name(id_n, actor_type_n, actor_key_v, event_type_n, metrics_type_n, metrics_key_v, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_key_v, system_type_v, correction_v, batch_number_n, instance_type_n, instance_key_v, data_flag_n, instance_id_n, metrics_id_n, file_id_n, data_string_v) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String transTempTblInsertQry = "INSERT INTO kpi.table_name(id_n, actor_type_n, actor_key_v, event_type_n, metrics_type_n, metrics_key_v, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_key_v, system_type_v, correction_v, batch_number_n, instance_type_n, instance_key_v, data_flag_n, instance_id_n, metrics_id_n, file_id_n, data_string_v, acellid_v, bcellid_v) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String tempTableDeleteQry = "DELETE FROM kpi.table_name where file_id_n = ? and batch_number_n = ?";
	public static final String dailyTableInsertQry = "INSERT INTO kpi.daily_table(day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String dailyTableInsertQryForOrgBalance = "INSERT INTO kpi.daily_table( day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) on conflict ( day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n ) do update set dimension_1_n = ?, dimension_2_n = ?, dimension_3_n = ?, dimension_4_n = ?, dimension_5_n = ?, dimension_6_n = ?, dimension_7_n = ?, dimension_8_n = ?, dimension_9_n = ?, dimension_10_n = ?, no_of_events_n = ?;";
	public static final String dailyTableUpdateQry = "UPDATE kpi.daily_table SET dimension_1_n = ?, dimension_2_n = ?, dimension_3_n = ?, dimension_4_n = ?, dimension_5_n = ?, dimension_6_n = ?, dimension_7_n = ?, dimension_8_n = ?, dimension_9_n = ?, dimension_10_n = ?, no_of_events_n = ?, last_updated_time_dt = ( select now() ) WHERE day_id_n = ? and event_type_n = ? and actor_type_n = ? and actor_id_n = ? and metrics_type_n = ? and metrics_id_n = ? and source_type_n = ? and source_id_n = ? and data_flag_n = ? and instance_type_n = ? and instance_id_n = ?";
//	public static final String dailyTableJoinQry = "( select temp1.id_n, daily1.day_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, case when ( sum(temp1.dimension_1_n) is not null and daily1.dimension_1_n is not null ) then ( sum(temp1.dimension_1_n) + daily1.dimension_1_n ) else case when ( sum(temp1.dimension_1_n) is not null and daily1.dimension_1_n is null ) then sum(temp1.dimension_1_n) else case when ( sum(temp1.dimension_1_n) is null and daily1.dimension_1_n is not null ) is null then daily1.dimension_1_n else null end end end as dimension_1_n, case when ( sum(temp1.dimension_2_n) is not null and daily1.dimension_2_n is not null ) then ( sum(temp1.dimension_2_n) + daily1.dimension_2_n ) else case when ( sum(temp1.dimension_2_n) is not null and daily1.dimension_2_n is null ) then sum(temp1.dimension_2_n) else case when ( sum(temp1.dimension_2_n) is null and daily1.dimension_2_n is not null ) is null then daily1.dimension_2_n else null end end end as dimension_2_n, case when ( sum(temp1.dimension_3_n) is not null and daily1.dimension_3_n is not null ) then ( sum(temp1.dimension_3_n) + daily1.dimension_3_n ) else case when ( sum(temp1.dimension_3_n) is not null and daily1.dimension_3_n is null ) then sum(temp1.dimension_3_n) else case when ( sum(temp1.dimension_3_n) is null and daily1.dimension_3_n is not null ) is null then daily1.dimension_3_n else null end end end as dimension_3_n, case when ( sum(temp1.dimension_4_n) is not null and daily1.dimension_4_n is not null ) then ( sum(temp1.dimension_4_n) + daily1.dimension_4_n ) else case when ( sum(temp1.dimension_4_n) is not null and daily1.dimension_4_n is null ) then sum(temp1.dimension_4_n) else case when ( sum(temp1.dimension_4_n) is null and daily1.dimension_4_n is not null ) is null then daily1.dimension_4_n else null end end end as dimension_4_n, case when ( sum(temp1.dimension_5_n) is not null and daily1.dimension_5_n is not null ) then ( sum(temp1.dimension_5_n) + daily1.dimension_5_n ) else case when ( sum(temp1.dimension_5_n) is not null and daily1.dimension_5_n is null ) then sum(temp1.dimension_5_n) else case when ( sum(temp1.dimension_5_n) is null and daily1.dimension_5_n is not null ) is null then daily1.dimension_5_n else null end end end as dimension_5_n, case when ( sum(temp1.dimension_6_n) is not null and daily1.dimension_6_n is not null ) then ( sum(temp1.dimension_6_n) + daily1.dimension_6_n ) else case when ( sum(temp1.dimension_6_n) is not null and daily1.dimension_6_n is null ) then sum(temp1.dimension_6_n) else case when ( sum(temp1.dimension_6_n) is null and daily1.dimension_6_n is not null ) is null then daily1.dimension_6_n else null end end end as dimension_6_n, case when ( sum(temp1.dimension_7_n) is not null and daily1.dimension_7_n is not null ) then ( sum(temp1.dimension_7_n) + daily1.dimension_7_n ) else case when ( sum(temp1.dimension_7_n) is not null and daily1.dimension_7_n is null ) then sum(temp1.dimension_7_n) else case when ( sum(temp1.dimension_7_n) is null and daily1.dimension_7_n is not null ) is null then daily1.dimension_7_n else null end end end as dimension_7_n, case when ( sum(temp1.dimension_8_n) is not null and daily1.dimension_8_n is not null ) then ( sum(temp1.dimension_8_n) + daily1.dimension_8_n ) else case when ( sum(temp1.dimension_8_n) is not null and daily1.dimension_8_n is null ) then sum(temp1.dimension_8_n) else case when ( sum(temp1.dimension_8_n) is null and daily1.dimension_8_n is not null ) is null then daily1.dimension_8_n else null end end end as dimension_8_n, case when ( sum(temp1.dimension_9_n) is not null and daily1.dimension_9_n is not null ) then ( sum(temp1.dimension_9_n) + daily1.dimension_9_n ) else case when ( sum(temp1.dimension_9_n) is not null and daily1.dimension_9_n is null ) then sum(temp1.dimension_9_n) else case when ( sum(temp1.dimension_9_n) is null and daily1.dimension_9_n is not null ) is null then daily1.dimension_9_n else null end end end as dimension_9_n, case when ( sum(temp1.dimension_10_n) is not null and daily1.dimension_10_n is not null ) then ( sum(temp1.dimension_10_n) + daily1.dimension_10_n ) else case when ( sum(temp1.dimension_10_n) is not null and daily1.dimension_10_n is null ) then sum(temp1.dimension_10_n) else case when ( sum(temp1.dimension_10_n) is null and daily1.dimension_10_n is not null ) is null then daily1.dimension_10_n else null end end end as dimension_10_n, case when ( sum(temp1.no_of_events_n) is not null and daily1.no_of_events_n is not null ) then ( sum(temp1.no_of_events_n) + daily1.no_of_events_n ) else case when ( sum(temp1.no_of_events_n) is not null and daily1.no_of_events_n is null ) then sum(temp1.no_of_events_n) else case when ( sum(temp1.no_of_events_n) is null and daily1.no_of_events_n is not null ) is null then daily1.no_of_events_n else null end end end as no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n, temp1.correction_v, temp1.system_type_v from kpi.table_name temp1 left join kpi.tr_daily_upload_metrics_aggr daily1 on temp1.id_n = daily1.day_id_n and temp1.actor_type_n = daily1.actor_type_n and temp1.actor_id_n = daily1.actor_id_n and temp1.event_type_n = daily1.event_type_n and temp1.metrics_type_n = daily1.metrics_type_n and temp1.metrics_id_n = daily1.metrics_id_n and temp1.source_type_n = daily1.source_type_n and temp1.source_id_n = daily1.source_id_n and temp1.data_flag_n = daily1.data_flag_n and temp1.instance_type_n = daily1.instance_type_n and temp1.instance_id_n = daily1.instance_id_n where temp1.file_id_n = ? and temp1.batch_number_n = ? and temp1.status_flag_n = 1 and trim(temp1.correction_v) ilike 'Update' group by temp1.id_n, daily1.day_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, daily1.dimension_1_n, daily1.dimension_2_n, daily1.dimension_3_n, daily1.dimension_4_n, daily1.dimension_5_n, daily1.dimension_6_n, daily1.dimension_7_n, daily1.dimension_8_n, daily1.dimension_9_n, daily1.dimension_10_n, daily1.no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n, temp1.correction_v, temp1.system_type_v ) union ( select m.id_n, day.day_id_n, m.actor_type_n, m.actor_id_n, m.event_type_n, m.metrics_type_n, m.metrics_id_n, m.dimension_1_n as dimension_1_n, m.dimension_2_n as dimension_2_n, m.dimension_3_n as dimension_3_n, m.dimension_4_n as dimension_4_n, m.dimension_5_n as dimension_5_n, m.dimension_6_n as dimension_6_n, m.dimension_7_n as dimension_7_n, m.dimension_8_n as dimension_8_n, m.dimension_9_n as dimension_9_n, m.dimension_10_n as dimension_10_n, m.no_of_events_n, m.source_type_n, m.source_id_n, m.data_flag_n, m.instance_type_n, m.instance_id_n, m.correction_v, m.system_type_v from ( select a.* from kpi.table_name a, ( select id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, max(temp_id_n) id from kpi.table_name where file_id_n = ? and batch_number_n = ? and status_flag_n = 1 and trim(correction_v) ilike 'Replace' group by id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n ) b where a.temp_id_n = b.id ) m left join kpi.tr_daily_upload_metrics_aggr day on m.id_n = day.day_id_n and m.actor_type_n = day.actor_type_n and m.actor_id_n = day.actor_id_n and m.event_type_n = day.event_type_n and m.metrics_type_n = day.metrics_type_n and m.metrics_id_n = day.metrics_id_n and m.source_type_n = day.source_type_n and m.source_id_n = day.source_id_n and m.data_flag_n = day.data_flag_n and m.instance_type_n = day.instance_type_n and m.instance_id_n = day.instance_id_n )";
	//public static final String dailyTableJoinQry = "select temp1.id_n, daily1.day_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, case when ( sum(temp1.dimension_1_n) is not null and daily1.dimension_1_n is not null ) then ( sum(temp1.dimension_1_n) + daily1.dimension_1_n ) else case when ( sum(temp1.dimension_1_n) is not null and daily1.dimension_1_n is null ) then sum(temp1.dimension_1_n) else case when ( sum(temp1.dimension_1_n) is null and daily1.dimension_1_n is not null ) is null then daily1.dimension_1_n else null end end end as dimension_1_n, case when ( sum(temp1.dimension_2_n) is not null and daily1.dimension_2_n is not null ) then ( sum(temp1.dimension_2_n) + daily1.dimension_2_n ) else case when ( sum(temp1.dimension_2_n) is not null and daily1.dimension_2_n is null ) then sum(temp1.dimension_2_n) else case when ( sum(temp1.dimension_2_n) is null and daily1.dimension_2_n is not null ) is null then daily1.dimension_2_n else null end end end as dimension_2_n, case when ( sum(temp1.dimension_3_n) is not null and daily1.dimension_3_n is not null ) then ( sum(temp1.dimension_3_n) + daily1.dimension_3_n ) else case when ( sum(temp1.dimension_3_n) is not null and daily1.dimension_3_n is null ) then sum(temp1.dimension_3_n) else case when ( sum(temp1.dimension_3_n) is null and daily1.dimension_3_n is not null ) is null then daily1.dimension_3_n else null end end end as dimension_3_n, case when ( sum(temp1.dimension_4_n) is not null and daily1.dimension_4_n is not null ) then ( sum(temp1.dimension_4_n) + daily1.dimension_4_n ) else case when ( sum(temp1.dimension_4_n) is not null and daily1.dimension_4_n is null ) then sum(temp1.dimension_4_n) else case when ( sum(temp1.dimension_4_n) is null and daily1.dimension_4_n is not null ) is null then daily1.dimension_4_n else null end end end as dimension_4_n, case when ( sum(temp1.dimension_5_n) is not null and daily1.dimension_5_n is not null ) then ( sum(temp1.dimension_5_n) + daily1.dimension_5_n ) else case when ( sum(temp1.dimension_5_n) is not null and daily1.dimension_5_n is null ) then sum(temp1.dimension_5_n) else case when ( sum(temp1.dimension_5_n) is null and daily1.dimension_5_n is not null ) is null then daily1.dimension_5_n else null end end end as dimension_5_n, case when ( sum(temp1.dimension_6_n) is not null and daily1.dimension_6_n is not null ) then ( sum(temp1.dimension_6_n) + daily1.dimension_6_n ) else case when ( sum(temp1.dimension_6_n) is not null and daily1.dimension_6_n is null ) then sum(temp1.dimension_6_n) else case when ( sum(temp1.dimension_6_n) is null and daily1.dimension_6_n is not null ) is null then daily1.dimension_6_n else null end end end as dimension_6_n, case when ( sum(temp1.dimension_7_n) is not null and daily1.dimension_7_n is not null ) then ( sum(temp1.dimension_7_n) + daily1.dimension_7_n ) else case when ( sum(temp1.dimension_7_n) is not null and daily1.dimension_7_n is null ) then sum(temp1.dimension_7_n) else case when ( sum(temp1.dimension_7_n) is null and daily1.dimension_7_n is not null ) is null then daily1.dimension_7_n else null end end end as dimension_7_n, case when ( sum(temp1.dimension_8_n) is not null and daily1.dimension_8_n is not null ) then ( sum(temp1.dimension_8_n) + daily1.dimension_8_n ) else case when ( sum(temp1.dimension_8_n) is not null and daily1.dimension_8_n is null ) then sum(temp1.dimension_8_n) else case when ( sum(temp1.dimension_8_n) is null and daily1.dimension_8_n is not null ) is null then daily1.dimension_8_n else null end end end as dimension_8_n, case when ( sum(temp1.dimension_9_n) is not null and daily1.dimension_9_n is not null ) then ( sum(temp1.dimension_9_n) + daily1.dimension_9_n ) else case when ( sum(temp1.dimension_9_n) is not null and daily1.dimension_9_n is null ) then sum(temp1.dimension_9_n) else case when ( sum(temp1.dimension_9_n) is null and daily1.dimension_9_n is not null ) is null then daily1.dimension_9_n else null end end end as dimension_9_n, case when ( sum(temp1.dimension_10_n) is not null and daily1.dimension_10_n is not null ) then ( sum(temp1.dimension_10_n) + daily1.dimension_10_n ) else case when ( sum(temp1.dimension_10_n) is not null and daily1.dimension_10_n is null ) then sum(temp1.dimension_10_n) else case when ( sum(temp1.dimension_10_n) is null and daily1.dimension_10_n is not null ) is null then daily1.dimension_10_n else null end end end as dimension_10_n, case when ( sum(temp1.no_of_events_n) is not null and daily1.no_of_events_n is not null ) then ( sum(temp1.no_of_events_n) + daily1.no_of_events_n ) else case when ( sum(temp1.no_of_events_n) is not null and daily1.no_of_events_n is null ) then sum(temp1.no_of_events_n) else case when ( sum(temp1.no_of_events_n) is null and daily1.no_of_events_n is not null ) is null then daily1.no_of_events_n else null end end end as no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n, temp1.correction_v, temp1.system_type_v from (lookup_query) temp1 left join kpi.tr_daily_upload_metrics_aggr daily1 on temp1.id_n = daily1.day_id_n and temp1.actor_type_n = daily1.actor_type_n and temp1.actor_id_n = daily1.actor_id_n and temp1.event_type_n = daily1.event_type_n and temp1.metrics_type_n = daily1.metrics_type_n and temp1.metrics_id_n = daily1.metrics_id_n and temp1.source_type_n = daily1.source_type_n and temp1.source_id_n = daily1.source_id_n and temp1.data_flag_n = daily1.data_flag_n and temp1.instance_type_n = daily1.instance_type_n and temp1.instance_id_n = daily1.instance_id_n where temp1.file_id_n = ? and temp1.status_flag_n = 1 and trim(temp1.correction_v) ilike 'Update' group by temp1.id_n, daily1.day_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, daily1.dimension_1_n, daily1.dimension_2_n, daily1.dimension_3_n, daily1.dimension_4_n, daily1.dimension_5_n, daily1.dimension_6_n, daily1.dimension_7_n, daily1.dimension_8_n, daily1.dimension_9_n, daily1.dimension_10_n, daily1.no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n, temp1.correction_v, temp1.system_type_v";
	public static final String monthlyTableInsertQry = "INSERT INTO kpi.monthly_table(month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String monthlyTableUpdateQry = "UPDATE kpi.monthly_table SET dimension_1_n = ?, dimension_2_n = ?, dimension_3_n = ?, dimension_4_n = ?, dimension_5_n = ?, dimension_6_n = ?, dimension_7_n = ?, dimension_8_n = ?, dimension_9_n = ?, dimension_10_n = ?, no_of_events_n = ?, last_updated_time_dt = ( select now() ) WHERE month_id_n = ? and event_type_n = ? and actor_type_n = ? and actor_id_n = ? and metrics_type_n = ? and metrics_id_n = ? and source_type_n = ? and source_id_n = ? and data_flag_n = ? and instance_type_n = ? and instance_id_n = ?";
	//public static final String monthlyTableJoinQry = "( select day_master.month_id_n as id_n, monthly1.month_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, case when ( sum(temp1.dimension_1_n) is not null and monthly1.dimension_1_n is not null ) then ( sum(temp1.dimension_1_n) + monthly1.dimension_1_n ) else case when ( sum(temp1.dimension_1_n) is not null and monthly1.dimension_1_n is null ) then sum(temp1.dimension_1_n) else case when ( sum(temp1.dimension_1_n) is null and monthly1.dimension_1_n is not null ) is null then monthly1.dimension_1_n else null end end end as dimension_1_n, case when ( sum(temp1.dimension_2_n) is not null and monthly1.dimension_2_n is not null ) then ( sum(temp1.dimension_2_n) + monthly1.dimension_2_n ) else case when ( sum(temp1.dimension_2_n) is not null and monthly1.dimension_2_n is null ) then sum(temp1.dimension_2_n) else case when ( sum(temp1.dimension_2_n) is null and monthly1.dimension_2_n is not null ) is null then monthly1.dimension_2_n else null end end end as dimension_2_n, case when ( sum(temp1.dimension_3_n) is not null and monthly1.dimension_3_n is not null ) then ( sum(temp1.dimension_3_n) + monthly1.dimension_3_n ) else case when ( sum(temp1.dimension_3_n) is not null and monthly1.dimension_3_n is null ) then sum(temp1.dimension_3_n) else case when ( sum(temp1.dimension_3_n) is null and monthly1.dimension_3_n is not null ) is null then monthly1.dimension_3_n else null end end end as dimension_3_n, case when ( sum(temp1.dimension_4_n) is not null and monthly1.dimension_4_n is not null ) then ( sum(temp1.dimension_4_n) + monthly1.dimension_4_n ) else case when ( sum(temp1.dimension_4_n) is not null and monthly1.dimension_4_n is null ) then sum(temp1.dimension_4_n) else case when ( sum(temp1.dimension_4_n) is null and monthly1.dimension_4_n is not null ) is null then monthly1.dimension_4_n else null end end end as dimension_4_n, case when ( sum(temp1.dimension_5_n) is not null and monthly1.dimension_5_n is not null ) then ( sum(temp1.dimension_5_n) + monthly1.dimension_5_n ) else case when ( sum(temp1.dimension_5_n) is not null and monthly1.dimension_5_n is null ) then sum(temp1.dimension_5_n) else case when ( sum(temp1.dimension_5_n) is null and monthly1.dimension_5_n is not null ) is null then monthly1.dimension_5_n else null end end end as dimension_5_n, case when ( sum(temp1.dimension_6_n) is not null and monthly1.dimension_6_n is not null ) then ( sum(temp1.dimension_6_n) + monthly1.dimension_6_n ) else case when ( sum(temp1.dimension_6_n) is not null and monthly1.dimension_6_n is null ) then sum(temp1.dimension_6_n) else case when ( sum(temp1.dimension_6_n) is null and monthly1.dimension_6_n is not null ) is null then monthly1.dimension_6_n else null end end end as dimension_6_n, case when ( sum(temp1.dimension_7_n) is not null and monthly1.dimension_7_n is not null ) then ( sum(temp1.dimension_7_n) + monthly1.dimension_7_n ) else case when ( sum(temp1.dimension_7_n) is not null and monthly1.dimension_7_n is null ) then sum(temp1.dimension_7_n) else case when ( sum(temp1.dimension_7_n) is null and monthly1.dimension_7_n is not null ) is null then monthly1.dimension_7_n else null end end end as dimension_7_n, case when ( sum(temp1.dimension_8_n) is not null and monthly1.dimension_8_n is not null ) then ( sum(temp1.dimension_8_n) + monthly1.dimension_8_n ) else case when ( sum(temp1.dimension_8_n) is not null and monthly1.dimension_8_n is null ) then sum(temp1.dimension_8_n) else case when ( sum(temp1.dimension_8_n) is null and monthly1.dimension_8_n is not null ) is null then monthly1.dimension_8_n else null end end end as dimension_8_n, case when ( sum(temp1.dimension_9_n) is not null and monthly1.dimension_9_n is not null ) then ( sum(temp1.dimension_9_n) + monthly1.dimension_9_n ) else case when ( sum(temp1.dimension_9_n) is not null and monthly1.dimension_9_n is null ) then sum(temp1.dimension_9_n) else case when ( sum(temp1.dimension_9_n) is null and monthly1.dimension_9_n is not null ) is null then monthly1.dimension_9_n else null end end end as dimension_9_n, case when ( sum(temp1.dimension_10_n) is not null and monthly1.dimension_10_n is not null ) then ( sum(temp1.dimension_10_n) + monthly1.dimension_10_n ) else case when ( sum(temp1.dimension_10_n) is not null and monthly1.dimension_10_n is null ) then sum(temp1.dimension_10_n) else case when ( sum(temp1.dimension_10_n) is null and monthly1.dimension_10_n is not null ) is null then monthly1.dimension_10_n else null end end end as dimension_10_n, case when ( sum(temp1.no_of_events_n) is not null and monthly1.no_of_events_n is not null ) then ( sum(temp1.no_of_events_n) + monthly1.no_of_events_n ) else case when ( sum(temp1.no_of_events_n) is not null and monthly1.no_of_events_n is null ) then sum(temp1.no_of_events_n) else case when ( sum(temp1.no_of_events_n) is null and monthly1.no_of_events_n is not null ) is null then monthly1.no_of_events_n else null end end end as no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n, temp1.correction_v, temp1.system_type_v from kpi.table_name temp1 inner join kpi.ms_day_master as day_master on (temp1.id_n = day_master.day_id_n) left join kpi.tr_monthly_upload_metrics_aggr monthly1 on day_master.month_id_n = monthly1.month_id_n and temp1.actor_type_n = monthly1.actor_type_n and temp1.actor_id_n = monthly1.actor_id_n and temp1.event_type_n = monthly1.event_type_n and temp1.metrics_type_n = monthly1.metrics_type_n and temp1.metrics_id_n = monthly1.metrics_id_n and temp1.source_type_n = monthly1.source_type_n and temp1.source_id_n = monthly1.source_id_n and temp1.data_flag_n = monthly1.data_flag_n and temp1.instance_type_n = monthly1.instance_type_n and temp1.instance_id_n = monthly1.instance_id_n where temp1.file_id_n = ? and temp1.batch_number_n = ? and temp1.status_flag_n = 1 and trim(temp1.correction_v) ilike 'Update' group by day_master.month_id_n, monthly1.month_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, monthly1.dimension_1_n, monthly1.dimension_2_n, monthly1.dimension_3_n, monthly1.dimension_4_n, monthly1.dimension_5_n, monthly1.dimension_6_n, monthly1.dimension_7_n, monthly1.dimension_8_n, monthly1.dimension_9_n, monthly1.dimension_10_n, monthly1.no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n, temp1.correction_v, temp1.system_type_v ) union ( select day_master.month_id_n as id_n, month.month_id_n, m.actor_type_n, m.actor_id_n, m.event_type_n, m.metrics_type_n, m.metrics_id_n, m.dimension_1_n as dimension_1_n, m.dimension_2_n as dimension_2_n, m.dimension_3_n as dimension_3_n, m.dimension_4_n as dimension_4_n, m.dimension_5_n as dimension_5_n, m.dimension_6_n as dimension_6_n, m.dimension_7_n as dimension_7_n, m.dimension_8_n as dimension_8_n, m.dimension_9_n as dimension_9_n, m.dimension_10_n as dimension_10_n, m.no_of_events_n, m.source_type_n, m.source_id_n, m.data_flag_n, m.instance_type_n, m.instance_id_n, m.correction_v, m.system_type_v from ( select a.* from kpi.table_name a, ( select id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, max(temp_id_n) id from kpi.table_name where file_id_n = ? and batch_number_n = ? and status_flag_n = 1 and trim(correction_v) ilike 'Replace' group by id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n ) b where a.temp_id_n = b.id ) m inner join kpi.ms_day_master as day_master on (m.id_n = day_master.day_id_n) left join kpi.tr_monthly_upload_metrics_aggr month on day_master.month_id_n = month.month_id_n and m.actor_type_n = month.actor_type_n and m.actor_id_n = month.actor_id_n and m.event_type_n = month.event_type_n and m.metrics_type_n = month.metrics_type_n and m.metrics_id_n = month.metrics_id_n and m.source_type_n = month.source_type_n and m.source_id_n = month.source_id_n and m.data_flag_n = month.data_flag_n and m.instance_type_n = month.instance_type_n and m.instance_id_n = month.instance_id_n )";
	public static final String fetchActorQry = "select * from kpi.fetch_analytics_aggregation_actor(?, ?) as actorid;";
	public static final String fetchMetricQry = "select * from kpi.fetch_analytics_aggregation_metric(?, ?)";
	public static final String kpiActorMappingQry = "select * from kpi.mp_event_actor_mapping where event_type_n = ?;";
	public static final String kpiMetricMappingQry = "select * from kpi.mp_event_metric_mapping where event_type_n = ? order by metric_key_v;";
	public static final String kpiDimensioMappingQry = "select * from kpi.mp_event_dimension_mapping where event_type_n = ?;";
	public static final String kpiFrequencyMappingQry = "select * from kpi.mp_event_frequency_mapping where event_type_n = ?;";
	public static final String kpiCrossSellingMappingQry = "select * from kpi.mp_event_crossell_mapping where event_type_n = ?;";
	public static final String kpiCrossSellMpQry = "select * from kpi.mp_event_crossell_mapping where event_type_n = (select event_type_n from kpi.ms_event_type_master where system_event_v = ?);";
	public static final String kpiFetchCrossSellingQry = "select * from kpi.insert_event_crossell_aggregation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String kpiMetricLookupQry = "select * from kpi.ms_metrics_lookup_master;";
	public static final String tempTblLookUpQry = "select * from kpi.update_aggregate_lookup_master(?, ?) as updatecount;";
	public static final String tempTblDltQry = "delete from kpi.table_name where file_id_n = ? and batch_number_n = ? and status_flag_n = ?";
	public static final String tempTblSlctQry = "select *, case when actor_key_v is not null and actor_id_n = 0 then 'actor_field%Actor mapping not found for actor_field ' || actor_key_v when actor_id_n = -1 then 'actor-field%Found duplicate mapping for actor-field ' || actor_key_v when metrics_key_v is not null and metrics_id_n = 0 then 'metric_field%Metric mapping not found for metric_field ' || metrics_key_v when metrics_id_n = -1 then 'metric_field%Found duplicate mapping for metric_field ' || metrics_key_v when source_key_v is not null and source_id_n = 0 then 'source_field%Source mapping not found for source_field ' || source_key_v when source_id_n = -1 then 'source_field%Found duplicate mapping for source_field ' || source_key_v when instance_key_v is not null and instance_id_n = 0 then 'instance_field%Instance mapping not found for instance_field ' || instance_key_v when instance_id_n = -1 then 'instance_field%Found duplicate mapping for instance_field ' || instance_key_v end as failure_value from kpi.table_name where file_id_n = ? and batch_number_n = ? and status_flag_n = ?";
	public static final String tempKPIAggrInsertQry = "INSERT INTO kpi.tr_temp_upload_aggr_failure( file_id_n, batch_number_n, data_string_v, status_flag_n, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?);";
	public static final String tempKPIInsertQuery = "INSERT INTO kpi.tr_temp_upload_aggr_failure(id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, status_flag_n, system_type_v, correction_v, file_id_n, batch_number_n, data_string_v, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
	public static final String interfaceAttrSelectQry = "select value_v from interface.ms_interface_attr where interface_id_n = ? and name_v = ? order by name_v";
	public static final String tempTblSelectQry = "select * from kpi.table_name where file_id_n = ? and status_flag_n = ?";
	public static final String failureCountQry = "select count(1) from kpi.tr_temp_upload_aggr_failure where file_id_n = ?";
	public static final String failureDistinctCountQry = "select count(distinct data_string_v) from kpi.tr_temp_upload_aggr_failure where file_id_n = ?";
	public static final String tempTblDataSlctQry = "select data_string_v from kpi.table_name where file_id_n = ? and status_flag_n = ?";
	
	public static final String actorLookupMasterQry = "select actor_type_n, reference_type_n from kpi.ms_actor_lookup_master";
	public static final String metricsLookupMasterQry = "select metrics_type_n, reference_type_n from kpi.ms_metrics_lookup_master";
	
	public static final String kpiReprocessSelectQry = "select * from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ?";
	public static final String kpiLacciReprocessSelectQry = "select * from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ? and error_code = ?";
	public static final String kpiReprocessTempTblInsrtQry = "insert into kpi.table_name( id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, system_type_v, correction_v, file_id_n, data_string_v, batch_number_n ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String kpiReprocessTransTempTblInsrtQry = "insert into kpi.table_name( id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, system_type_v, correction_v, file_id_n, data_string_v, batch_number_n, acellid_v, bcellid_v) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String kpiReprocessSuccessQry = "select * from kpi.table_name where file_id_n = ? and batch_number_n = ? and status_flag_n = ?";
	public static final String kpiTempTblTotalQry = "select status_flag_n :: character varying, count(status_flag_n) from kpi.table_name where file_id_n = ? group by status_flag_n union select 'total', count(*) from kpi.table_name where file_id_n = ?";
	public static final String kpiTmpFailureTblDltQry = "delete from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ?";
	public static final String kpiLacciTmpFailureTblDltQry = "delete from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ? and error_code = ?";
	public static final String tempTblAllDltQry = "delete from kpi.table_name where file_id_n = ? ";
	public static final String TRUNCATE_TABLE_QRY = "TRUNCATE TABLE TABLE_NAME;";
	public static final String DELETE_TABLE_QRY = "delete from TABLE_NAME;";
	public static final String tempTblBatchDltQry = "delete from kpi.table_name where file_id_n = ? and batch_number_n = ?";
	public static final String freqMapQry = "select * from kpi.mp_event_frequency_mapping where event_type_n = (select event_type_n from kpi.ms_event_type_master where system_event_v = ?);";
	public static final String stTblDltQry = "delete from kpi.tr_temp_upload_metrics_orgbal_aggr_st where file_id_n = ?";
	
	//hadoop interfaces
	public static final String hadoopKpiLacciReprocessSelectQry = "select * from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ? and status_flag_n = ? and error_code = ?";
	public static final String tempKPIHadoopAggrInsertQry = "INSERT INTO kpi.tr_temp_hadoop_failure_aggr( file_id_n, batch_number_n, data_string_v, status_flag_n, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?);";
	public static final String tempKPIHadoopInsertQuery = "INSERT INTO kpi.tr_temp_hadoop_failure_aggr(id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, status_flag_n, system_type_v, correction_v, file_id_n, batch_number_n, data_string_v, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";
	public static final String failureKPIHadoopCountQry = "select count(1) from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?";
	public static final String failureKPIHadoopDistinctCountQry = "select count(distinct data_string_v) from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?";
	public static final String failureKpiHadoopCountQry = "select  count(distinct data_string_v)  from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?";
	public static final String hadoopKpiReprocessSelectQry = "select * from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ? and status_flag_n = ?";
	public static final String hadoopKpiTmpFailureTblDltQry = "delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ? and status_flag_n = ?";
	public static final String hadoopKpiLacciTmpFailureTblDltQry = "delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ? and status_flag_n = ? and error_code = ?";
	
	public static final Long KPI_ACTOR_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_ACTOR_MAPPING"));
	public static final Long KPI_METRIC_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_METRIC_MAPPING"));
	public static final Long KPI_DIMENSION_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_DIMENSION_MAPPING"));
	public static final Long KPI_FREQUENCY_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_FREQUENCY_MAPPING"));
	public static final Long KPI_INVALID_DATE = Long.valueOf(PropertiesLoader.getValueFor("KPI_INVALID_DATE"));
	public static final Long KPI_FUTURE_DATE = Long.valueOf(PropertiesLoader.getValueFor("KPI_FUTURE_DATE"));
	public static final Long KPI_SOURCE_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_SOURCE_MAPPING"));
	public static final Long KPI_INSTANCE_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_INSTANCE_MAPPING"));
	public static final Long KPI_NOT_IN_COMPLETED_STATUS = Long.valueOf(PropertiesLoader.getValueFor("KPI_NOT_IN_COMPLETED_STATUS"));
	public static final String STATUS_CHECK_INTERFACE_CONF = PropertiesLoader.getValueFor("STATUS_CHECK_INTERFACE_CONF");
	public static final String LOOKUP_CONF_ATTR = PropertiesLoader.getValueFor("LOOKUP_CONF_ATTR");
	public static final Long KPI_INVALID_METRIC = Long.valueOf(PropertiesLoader.getValueFor("KPI_INVALID_METRIC"));
	public static final String FIELD_LOOKUP_CONF_ATTR = PropertiesLoader.getValueFor("FIELD_LOOKUP_CONF_ATTR");
	public static final String CROSS_SELLING_FLAG_ATTR = PropertiesLoader.getValueFor("CROSS_SELLING_FLAG_ATTR");
	
	public static final Long TRANSFER_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("TRANSFER_BALANCE_INTERFACE_ID"));
	public static final Long TRANSFER_BALANCE_MODULE_ID = Long.parseLong(PropertiesLoader.getValueFor("TRANSFER_BALANCE_MODULE_ID"));
	public static final Long ORG_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("ORG_BALANCE_INTERFACE_ID"));
	public static final Long USAGE_REV_LACCI_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("USAGE_REV_LACCI_INTERFACE_ID"));
	public static final int TEMP_TABLES_ERROR_MSG_LENGTH = Integer.parseInt(PropertiesLoader.getValueFor("TEMP_TABLES_ERROR_MSG_LENGTH"));
	public static final Long TRANSACTION_SUMMARY_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("TRANSACTION_SUMMARY_INTERFACE_ID"));
	
	public static final String DATE_CONF_ATTR = PropertiesLoader.getValueFor("DATE_CONF_ATTR");
	
	public static final List<String> KPI_FEED_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("KPI_FEED_INTERFACE_LIST").split(","));
	public static final List<String> KPI_LACCI_FEED_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("KPI_LACCI_FEED_INTERFACE_LIST").split(","));
	public static final List<String> KPI_LACCI_FEED_ERROR_CODE_LIST = Arrays.asList(PropertiesLoader.getValueFor("KPI_LACCI_FEED_ERROR_CODE_LIST").split(","));
	
	public static final String REVERT_COLUMNS_ATTR = PropertiesLoader.getValueFor("REVERT_COLUMNS_ATTR");
	public static final String selectQryForKPIFeedsRollback = "select orgnl_request_data_b from interface.tr_interface_summary where ref_data3_n = ? and status_n = ?";
	
	public static final String ADDITIONAL_FIELDS_FILE = PropertiesLoader.getValueFor("ADDITIONAL_FIELDS_FILE");
	
	public static final Long PRIMARY_ORGANIZATION_TYPE = Long.parseLong(PropertiesLoader.getValueFor("PRIMARY_ORGANIZATION_TYPE"));
	
	public static final String KPI_MSISDN_PRD_MAP_INSERT_QUERY = "insert into kpi.tr_msisdn_product_mapping(msisdn_v, prd_id_v, activation_date_n) values(?, ?, ?)";
	public static final String KPI_MSISDN_PRD_MAP_SELECT_QUERY = "select msisdn_v from kpi.tr_msisdn_product_mapping where msisdn_v = ?";
	
	public static final String QUERIES_PROPERTIES_PATH = PropertiesLoader.getValueFor("QUERIES_PROPERTIES_PATH");
//	public static final String metricsNotExistQuery = "select 11 as metrics_type_n, a.metrics_key_v from( select distinct split_part(metrics_key_v, ',', 1) metrics_key_v from kpi.tr_temp_upload_metrics_trans_aggr where file_id_n = ? union select distinct split_part(metrics_key_v, ',', 3) metrics_key_v from kpi.tr_temp_upload_metrics_trans_aggr where file_id_n = ?) a where a.metrics_key_v not in (select metrics_name_v from kpi.ms_metrics_master where metrics_type_n = 11 and status_n in (1,174)) union select 13 as metrics_type_n, a.metrics_key_v from ( select distinct split_part(metrics_key_v, ',', 2) metrics_key_v from kpi.tr_temp_upload_metrics_trans_aggr where file_id_n = ? union select distinct split_part(metrics_key_v, ',', 4) metrics_key_v from kpi.tr_temp_upload_metrics_trans_aggr where file_id_n = ?) a where a.metrics_key_v not in (select metrics_name_v from kpi.ms_metrics_master where metrics_type_n = 13 and status_n in (1,174)) union select 33 as metrics_type_n, a.instance_key_v as metrics_key_v from ( select distinct instance_key_v from kpi.tr_temp_upload_metrics_trans_aggr where file_id_n = ?) a where a.instance_key_v not in (select ext_reference_id_v from kpi.ms_metrics_master where metrics_type_n = 33)";
	public static final String metricsInsertQuery = "insert into kpi.ms_metrics_master(metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v) values (?, ?, ?, ?, ?)";
//	public static final String metricsSequenceQuery = "select nextval('kpi.metrics_id_seq')";
//	public static final String actorSequenceQuery = "select nextval('kpi.aggregation_id_seq')";
	public static final String actorInsertQuery = "insert into kpi.ms_actor_master(actor_id_v, actor_type_n) values (?, ?)";
	public static final String siteSourceInsertQuery = "insert into kpi.ms_site_master(site_name_v, ref_code_v) values (?, ?)";
	public static final String deleteRejectFileQry = "delete from interface.tr_interface_file_summary_details where file_id_n = ? and file_type_v = 'R'";
	
	public static final String SORT_IBM_DATA_FEED_SCRIPT = PropertiesLoader.getValueFor("SORT_IBM_DATA_FEED_SCRIPT");
	public static final Long MSISDN_INJECTION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("MSISDN_INJECTION_INTERFACE_ID"));
	public static final Long MSISDN_RGU_GA_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("MSISDN_RGU_GA_INTERFACE_ID"));
	
	public static final String INCOMING_FILE_RECORDS_LIMIT = PropertiesLoader.getValueFor("INCOMING_FILE_RECORDS_LIMIT");
	public static final String HTTP_CONNECTION_TIMEOUT_ATTR = PropertiesLoader.getValueFor("HTTP_CONNECTION_TIMEOUT_ATTR");
	public static final String HTTP_READ_TIMEOUT_ATTR = PropertiesLoader.getValueFor("HTTP_READ_TIMEOUT_ATTR");
	public static final String CLEANUP_PROCESSOR_ATTR = PropertiesLoader.getValueFor("CLEANUP_PROCESSOR_ATTR");
	
	public static final Long REGISTRATION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("REGISTRATION_FEED_INTERFACE_ID"));
	public static final List<String> REJECTION_BY_FILE_ID_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("REJECTION_BY_FILE_ID_INTERFACES").split(","));
	
	public static final String kpiFeedsDupValQry = "select validation_str_v from schema_name.table_name where validation_str_v = ?";
	public static final String kpiFeedsDupValTableInsertQry = "insert into schema_name.table_name(validation_str_v) values(?)";
	
	public static final String COLLECTION_NAME_PATTERN = PropertiesLoader.getValueFor("COLLECTION_NAME_PATTERN");
	public static final String DUMP_DATE_COUNT = PropertiesLoader.getValueFor("DUMP_DATE_COUNT");
	
	public static final String SORT_STOCK_ENTRY_UPLOAD_SCRIPT = PropertiesLoader.getValueFor("SORT_STOCK_ENTRY_UPLOAD_SCRIPT");
	public static final String stockEntryUploadInsertQry = "INSERT INTO interface.stock_entry_upload(from_node, to_node, bill_no, bill_date, product_id, quantity, start_serial, end_serial, expiry_date, iccid_adnl_sn_1, msisdn_adnl_sn_2, imsi_adnl_sn_3, adnl_sn_4, file_id_n, status_n, error_code_v, error_message_v) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String multipleFromToNodeValQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where bill_no in (select bill_no from interface.stock_entry_upload where file_id_n = ?  and status_n = ? group by bill_no having count(distinct (from_node,to_node)) > 1 ) and file_id_n = ? and status_n = ?";
	public static final String stockEntryUploadDistQry = "select distinct from_node, to_node, product_id from interface.stock_entry_upload where file_id_n = ? and status_n = ?";
	public static final String distinctBillNoPrdIdQry = "select distinct bill_no, product_id from interface.stock_entry_upload where file_id_n = ? and status_n = ?";
	public static final String stockEntryUploadSelectQry = "select stock_id_n, from_node, to_node, bill_no, product_id, quantity, expiry_date, start_serial, end_serial, iccid_adnl_sn_1, msisdn_adnl_sn_2, imsi_adnl_sn_3, adnl_sn_4  from interface.stock_entry_upload where file_id_n = ? and status_n = ?";
	public static final String stockEntryUploadUpdateQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where stock_id_n = ?";
	public static final String stockEntryUploadPrdSelectQry = "select stock_id_n, from_node, to_node, bill_no, bill_date, product_id, quantity, start_serial, end_serial, expiry_date, iccid_adnl_sn_1, msisdn_adnl_sn_2, imsi_adnl_sn_3, adnl_sn_4 from interface.stock_entry_upload where bill_no = ? and product_id = ? and file_id_n = ? and status_n = ?";
	public static final String invalidSerialUpdateQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where bill_no = ? and product_id = ? and start_serial = ? and end_serial = ? and file_id_n = ? and status_n = ?";
	public static final String serialValFailureUpdateQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where bill_no = ? and product_id = ? and file_id_n = ? and status_n = ?";
	public static final String stockUploadServiceFailUpdateQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where bill_no = ? and file_id_n = ? and status_n = ?";
	public static final String stockUploadDupTransUpdateQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where bill_no = ? and file_id_n = ? and status_n = ?";
	public static final String stockUploadStatusUpdtQry = "update interface.stock_entry_upload set status_n = ? where bill_no in (billNoSet) and file_id_n = ?";
	public static final String stockEntryUploadTotalsQry = "select status_n::character varying, count(*) from interface.stock_entry_upload where file_id_n = ? group by status_n union select 'total', count(*) from interface.stock_entry_upload where file_id_n = ?";
	public static final String stockEntryUploadDistNodeQry = "select distinct from_node, to_node from interface.stock_entry_upload where file_id_n = ? and status_n = ?";
	public static final String invalidNodeLinkUpdateQry = "update interface.stock_entry_upload set status_n = ?, error_code_v = ?, error_message_v = ? where from_node = ? and to_node = ?  and file_id_n = ? and status_n = ?";
	
	public static final Long DEFAULT_PRIMARY_NODE_ID = Long.parseLong(PropertiesLoader.getValueFor("DEFAULT_PRIMARY_NODE_ID"));
	
	public static final String SORT_QOA_FEED_SCRIPT = PropertiesLoader.getValueFor("SORT_QOA_FEED_SCRIPT");
	public static final Long QOA_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("QOA_INTERFACE_ID"));
	public static final Long TNM_QUERY_STOCK_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("TNM_QUERY_STOCK_INTERFACE_ID"));
	public static final Long TNM_RESERVE_STOCK_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("TNM_RESERVE_STOCK_INTERFACE_ID"));
	public static final Long PJP_PLANNED_CALLS_DUMP = Long.valueOf(PropertiesLoader.getValueFor("PJP_PLANNED_CALLS_DUMP"));
	public static final Long STOCK_REORDER_DUMP = Long.valueOf(PropertiesLoader.getValueFor("STOCK_REORDER_DUMP"));
	
	public static final List<String> CLEANUP_ACTIVITY_USERS_LIST = Arrays.asList(PropertiesLoader.getValueFor("CLEANUP_ACTIVITY_USERS_LIST").split(","));
	public static final String CLEAN_UP_SUMMARY_SELECT_QUERY = "select summary.*, int.* from interface.ms_interface int , (select * from interface.tr_clean_up_summary where cleanup_id_n = ?) summary where int.interface_id_n = summary.interface_id_n";
	public static final String CLEAN_UP_SUMMARY_UPDATE_QUERY = "update interface.tr_clean_up_summary set user_id_v = ?, actvity_name_v = ?, message_v = ?, status_n = ?, error_message_v = ?, completed_time_dt = ? where cleanup_id_n = ?";
	public static final String CLEANUP_SUMMARY_INSERT_QUERY = "insert into interface.tr_clean_up_summary (cleanup_id_n, interface_id_n, user_id_v, actvity_name_v, message_v, status_n, initiated_time_dt) values (?,?,?,?,?,?,?);";
	public static final String CLEANUP_SUMMARY_SEQ_QUERY = "select nextval('interface.TR_CLEAN_UP_SUMMARY_SEQ')";
	public static final String fieldLookupConfQuery = "select value_v from interface.ms_interface_attr where name_v ='Field Lookup Conf' and interface_id_n = ?";
	public static final String cleanUpConfQuery = "select value_v from interface.ms_interface_attr where name_v ='Cleanup Conf' and interface_id_n = ?";
	public static final String tableSizeCheckQuery = "select pg_size_pretty( pg_total_relation_size('TABLE') );";
	
	public static final String INTERFACE_TESTCONF_PATH = PropertiesLoader.getValueFor("INTERFACE_TESTCONF_PATH");
	public static final String INTERFACE_TEST_PROPERTIES = PropertiesLoader.getValueFor("INTERFACE_TEST_PROPERTIES");

*/

	public static final String INTERFACE_JDBC_DRIVER = PropertiesLoader.getValueFor("INTERFACE_JDBC_DRIVER");

	public static final String INTERFACE_JDBC_URL = PropertiesLoader.getValueFor("INTERFACE_JDBC_URL");

	public static final String INTERFACE_JDBC_USER =  PropertiesLoader.getValueFor("INTERFACE_JDBC_USER");

	public static final String INTERFACE_JDBC_PASS = PropertiesLoader.getValueFor("INTERFACE_JDBC_PASS");
	
	public static final String KPI_JDBC_DRIVER = PropertiesLoader.getValueFor("KPI_JDBC_DRIVER");

	public static final String KPI_JDBC_URL = PropertiesLoader.getValueFor("KPI_JDBC_URL");

	public static final String KPI_JDBC_USER = PropertiesLoader.getValueFor("KPI_JDBC_USER");

	public static final String KPI_JDBC_PASS =  PropertiesLoader.getValueFor("KPI_JDBC_PASS");
	
	public static final String KPI_SCHEMA_NAME =  PropertiesLoader.getValueFor("KPI_SCHEMA_NAME");

}
