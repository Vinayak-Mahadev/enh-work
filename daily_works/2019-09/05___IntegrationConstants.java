package com.enhancesys.integration.services.interfaces;

import java.util.Arrays;
import java.util.List;

import com.enhancesys.common.configuration.PropertiesLoader;
import com.enhancesys.snoc.utils.EncryptionUtil;

public interface IntegrationConstants 
{
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

	public static final String CALL_BACK_JNDI_NAME = PropertiesLoader.getValueFor("CALL_BACK_JNDI_NAME");

	public static final Long ASYNC_QUEUE_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_QUEUE_INTERFACE_TYPE"));

	public static final Long ASYNC_FILE_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_FILE_INTERFACE_TYPE"));

	public static final Long ASYNC_WS_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ASYNC_WS_INTERFACE_TYPE"));

	public static final Long SYNC_INTERFACE_TYPE = Long.parseLong(PropertiesLoader.getValueFor("SYNC_INTERFACE_TYPE"));

	public static final Long SEND_TRANS_TYPE = Long.parseLong(PropertiesLoader.getValueFor("SEND_TRANS_TYPE"));

	public static final Long RECEIVE_TRANS_TYPE = Long.parseLong(PropertiesLoader.getValueFor("RECEIVE_TRANS_TYPE"));

	public static final Long QUEUE_ERROR_CODE = Long.parseLong(PropertiesLoader.getValueFor("QUEUE_ERROR_CODE"));

	public static final Long SERVICE_ERROR_CODE = Long.parseLong(PropertiesLoader.getValueFor("SERVICE_ERROR_CODE"));

	public static final String HOST_FOR_SMS = PropertiesLoader.getValueFor("HOST_FOR_SMS");

	public static final String SMSC_USER_ID = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMSC_USER_ID")) : PropertiesLoader.getValueFor("SMSC_USER_ID");

	public static final String SMSC_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMSC_PASSWORD")): PropertiesLoader.getValueFor("SMSC_PASSWORD");

	public static final String SMSC_SRC_NO = PropertiesLoader.getValueFor("SMSC_SRC_NO");

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

	public static final String INTEGRATION_XML_REQUEST_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_XML_REQUEST_TEMPLATE_PATH");

	public static final String INTEGRATION_XML_RESPONSE_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_XML_RESPONSE_TEMPLATE_PATH");

	public static final String TEMPLATE_NAME_ATTR =  PropertiesLoader.getValueFor("TEMPLATE_NAME_ATTR");

	public static final String RETRY_COUNT_ATTR = PropertiesLoader.getValueFor("RETRY_COUNT_ATTR");

	public static final String TRANS_ID_PATH_ATTR = PropertiesLoader.getValueFor("TRANS_ID_PATH_ATTR");

	public static final String TRANS_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("TRANS_ID_IN_CONVERTER_TEMPLATE");

	public static final String EXT_REF_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("EXT_REF_ID_IN_CONVERTER_TEMPLATE");

	public static final List<String> UPLOAD_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("UPLOAD_INTERFACE_LIST").split(","));

	public static final List<String> NOTIFICATION_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("NOTIFICATION_INTERFACE_LIST").split(","));

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

	public static final List<String> COMPLETED_STATUS_INTERFACE_IDS = Arrays.asList(PropertiesLoader.getValueFor("COMPLETED_STATUS_INTERFACE_IDS").split(","));

	public static final Long INTERMEDIATE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("INTERMEDIATE_STATUS"));

	public static final String INTEGRATION_CSV_TO_JSON_TEMPLATE_PATH = PropertiesLoader.getValueFor("INTEGRATION_CSV_TO_JSON_TEMPLATE_PATH");

	public static final String CONTROL_FILE_DELIMETER = PropertiesLoader.getValueFor("CONTROL_FILE_DELIMETER");

	public static final String NEXT_RECORD_DELIMITER = PropertiesLoader.getValueFor("NEXT_RECORD_DELIMITER");

	public static final String FIELD_DELIMITER = PropertiesLoader.getValueFor("FIELD_DELIMITER");

	public static final String FIELD_SPLIT_DELIMITER = PropertiesLoader.getValueFor("FIELD_SPLIT_DELIMITER");

	public static final String TIMESTAMP = PropertiesLoader.getValueFor("TIMESTAMP");

	public static final String DATE = PropertiesLoader.getValueFor("DATE");

	public static final String DEFAULT_VALUE = PropertiesLoader.getValueFor("DEFAULT_VALUE");

	public static final String PARTNER_SET = PropertiesLoader.getValueFor("PARTNER_SET");

	public static final String COMMA_DELIMITER = PropertiesLoader.getValueFor("COMMA_DELIMITER");

	public static final String LOCAL_CTRL_BACKUP_DIR_ATTR = PropertiesLoader.getValueFor("LOCAL_CTRL_BACKUP_DIR_ATTR");

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

	public static final String MERGE_FIRST_FILE_FIELDS_ATTR = PropertiesLoader.getValueFor("MERGE_FIRST_FILE_FIELDS_ATTR");

	public static final String FIRST_CSV_FILE_MERGE_DELIMETER_ATTR = PropertiesLoader.getValueFor("FIRST_CSV_FILE_MERGE_DELIMETER_ATTR");

	public static final String SECOND_FILE_VALIDATION_CONF_ATTR = PropertiesLoader.getValueFor("SECOND_FILE_VALIDATION_CONF_ATTR");

	public static final String FIRST_FILE_VALIDATION_CONF_ATTR = PropertiesLoader.getValueFor("FIRST_FILE_VALIDATION_CONF_ATTR");

	public static final String CSV_MERGER_ATTR = PropertiesLoader.getValueFor("CSV_MERGER_ATTR");

	public static final Long REST_SERVICE_SUCCESS_CODE = Long.parseLong(PropertiesLoader.getValueFor("REST_SERVICE_SUCCESS_CODE"));

	public static final String FILE_TRANSFER_PROCESS = PropertiesLoader.getValueFor("FILE_TRANSFER_PROCESS");

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

	public static final List<String> INVENTORY_SALES_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("INVENTORY_SALES_INTERFACE_LIST").split(","));

	public static final String DATE_TIME_FORMAT = PropertiesLoader.getValueFor("DATE_TIME_FORMAT");

	public static final String DATE_FORMAT = PropertiesLoader.getValueFor("DATE_FORMAT");

	public static final String ACTIVE_MQ_LOCAL_URL = PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_URL");

	public static final String ACTIVE_MQ_LOCAL_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_USER")) : PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_USER");

	public static final String ACTIVE_MQ_LOCAL_PWD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_PWD")): PropertiesLoader.getValueFor("ACTIVE_MQ_LOCAL_PWD");

	public static final String ACTUAL_FILE_TYPE_CHAR = PropertiesLoader.getValueFor("ACTUAL_FILE_TYPE_CHAR");

	public static final String REJECTED_FILE_TYPE_CHAR = PropertiesLoader.getValueFor("REJECTED_FILE_TYPE_CHAR");

	public static final String FILTER_FILE_TYPE_CHAR = PropertiesLoader.getValueFor("FILTER_FILE_TYPE_CHAR");

	public static final String FILE_HEADER_ATTR = PropertiesLoader.getValueFor("FILE_HEADER_ATTR");

	public static final String LTYPE_ENTITY_MAPPING = PropertiesLoader.getValueFor("LTYPE_ENTITY_MAPPING");

	public static final String LTYPE_DATE_MAPPING = PropertiesLoader.getValueFor("LTYPE_DATE_MAPPING");

	public static final String LTYPE_CONDITION_MAPPING = PropertiesLoader.getValueFor("LTYPE_CONDITION_MAPPING");

	public static final Long ERROR_CODE_ENTITY_TYPE = Long.parseLong(PropertiesLoader.getValueFor("ERROR_CODE_ENTITY_TYPE"));

	public static final String SORT_FILE_SCRIPT = PropertiesLoader.getValueFor("SORT_FILE_SCRIPT");

	public static final String MONGO_DB_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_USER")) : PropertiesLoader.getValueFor("MONGO_DB_USER");

	public static final String MONGO_DB_DATABASE = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_DATABASE")) : PropertiesLoader.getValueFor("MONGO_DB_DATABASE");

	public static final String MONGO_DB_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_PASSWORD")) : PropertiesLoader.getValueFor("MONGO_DB_PASSWORD");

	public static final String MONGO_DB_STANDALONE_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_USER")) : PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_USER");

	public static final String MONGO_DB_STANDALONE_DATABASE = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_DATABASE")) : PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_DATABASE");

	public static final String MONGO_DB_STANDALONE_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_PASSWORD")) : PropertiesLoader.getValueFor("MONGO_DB_STANDALONE_PASSWORD");

	public static final String MONGO_DB_R4_USER = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_R4_USER")) : PropertiesLoader.getValueFor("MONGO_DB_R4_USER");

	public static final String MONGO_DB_R4_DATABASE = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_R4_DATABASE")) : PropertiesLoader.getValueFor("MONGO_DB_R4_DATABASE");

	public static final String MONGO_DB_R4_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("MONGO_DB_R4_PASSWORD")) : PropertiesLoader.getValueFor("MONGO_DB_R4_PASSWORD");

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

	public static final String LOOKUP = PropertiesLoader.getValueFor("LOOKUP");

	public static final String ZIP_FILE_FORMAT = PropertiesLoader.getValueFor("ZIP_FILE_FORMAT");

	public static final String INTERFACE_FILE_SUMMARY_ID_IN_CONVERTER_TEMPLATE = PropertiesLoader.getValueFor("INTERFACE_FILE_SUMMARY_ID_IN_CONVERTER_TEMPLATE");

	public static final Integer CTL_MD5_HASHKEY_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_MD5_HASHKEY_INDEX"));

	public static final Integer CTL_FILE_SIZE_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_FILE_SIZE_INDEX"));

	public static final Integer CTL_RECORD_COUNT_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_RECORD_COUNT_INDEX"));

	public static final Integer CTL_FILE_NAME_INDEX = Integer.parseInt(PropertiesLoader.getValueFor("CTL_FILE_NAME_INDEX"));

	public static final String RESPONSE_ID_PATH_ATTR = PropertiesLoader.getValueFor("RESPONSE_ID_PATH_ATTR");

	public static final String REJECTION_FILE_ATTR = PropertiesLoader.getValueFor("REJECTION_FILE_ATTR");

	public static final String INVENTORY_SALES_FILE_NAME = PropertiesLoader.getValueFor("INVENTORY_SALES_FILE_NAME");

	public static final String HOST_FOR_EMAIL = PropertiesLoader.getValueFor("HOST_FOR_EMAIL");

	public static final String PORT_FOR_EMAIL = PropertiesLoader.getValueFor("PORT_FOR_EMAIL");

	public static final String SMTP_USER_NAME = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMTP_USER_NAME")) : PropertiesLoader.getValueFor("SMTP_USER_NAME");

	public static final String SMTP_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMTP_PASSWORD")) : PropertiesLoader.getValueFor("SMTP_PASSWORD");

	public static final String SMTP_FROM_USER_NAME = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("SMTP_FROM_USER_NAME")) : PropertiesLoader.getValueFor("SMTP_FROM_USER_NAME");

	public static final String NOTIFICATION_TEMPLATE_PATH = PropertiesLoader.getValueFor("NOTIFICATION_TEMPLATE_PATH");	

	public static final String QUEUE_NAMES_SET_ATTR = PropertiesLoader.getValueFor("QUEUE_NAMES_SET_ATTR");

	public static final String ENHANCESYS_DEFAULT_NODE_ID = PropertiesLoader.getValueFor("ENHANCESYS_DEFAULT_NODE_ID");

	public static final String ERROR_CODE_PATH_IN_RESPONSE = PropertiesLoader.getValueFor("ERROR_CODE_PATH_IN_RESPONSE");

	public static final String ERROR_MESSAGE_PATH_IN_RESPONSE = PropertiesLoader.getValueFor("ERROR_MESSAGE_PATH_IN_RESPONSE");

	public static final Long ACTIVE_STATUS = Long.parseLong(PropertiesLoader.getValueFor("ACTIVE_STATUS").toString());

	public static final String FILE_NOT_EXIST_SCENARIO = PropertiesLoader.getValueFor("FILE_NOT_EXIST_SCENARIO");

	public static final String FILE_PROCESS_SUCCESS_SCENARIO = PropertiesLoader.getValueFor("FILE_PROCESS_SUCCESS_SCENARIO");

	public static final String FILE_REJECTION_FULL_SCENARIO = PropertiesLoader.getValueFor("FILE_REJECTION_FULL_SCENARIO");

	public static final String FILE_REJECTION_PARTIAL_SCENARIO = PropertiesLoader.getValueFor("FILE_REJECTION_PARTIAL_SCENARIO");

	public static final String FILE_REJECTION_AT_CTL_SCENARIO = PropertiesLoader.getValueFor("FILE_REJECTION_AT_CTL_SCENARIO");

	public static final String FILE_TRANSFERRED_TO_SFTP_SCENARIO = PropertiesLoader.getValueFor("FILE_TRANSFERRED_TO_SFTP_SCENARIO");

	public static final String CONNECTION_REFUSED_SCENARIO = PropertiesLoader.getValueFor("CONNECTION_REFUSED_SCENARIO");

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

	public static final List<String> PREPARE_EMPTY_FILES_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("PREPARE_EMPTY_FILES_INTERFACE_LIST").split(","));

	public static final String INTEGRATION_ATTACHMENT_API_REQUEST_FILE = PropertiesLoader.getValueFor("INTEGRATION_ATTACHMENT_API_REQUEST_FILE");

	public static final String PPK_PATH_ATTR = PropertiesLoader.getValueFor("PPK_PATH_ATTR");

	public static final Long EMAIL_ATTACHMENT_LIMIT_IN_KB = Long.parseLong(PropertiesLoader.getValueFor("EMAIL_ATTACHMENT_LIMIT_IN_KB"));

	public static final List<String> ASYNC_PULL_DATA_TO_FILE_FROM_EXCEL_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_FROM_EXCEL_INTERFACE_LIST").split(","));

	public static final String STOCK_TAKING_FILE_NAME = PropertiesLoader.getValueFor("STOCK_TAKING_FILE_NAME");

	public static final String AUDIT_FILE_NAME = PropertiesLoader.getValueFor("AUDIT_FILE_NAME");

	public static final String FORM_NAME = PropertiesLoader.getValueFor("FORM_NAME");

	public static final String _ID = PropertiesLoader.getValueFor("_ID");

	public static final String INTERFACE_JDBC_DRIVER=PropertiesLoader.getValueFor("INTERFACE_JDBC_DRIVER");

	public static final String INTERFACE_JDBC_URL=PropertiesLoader.getValueFor("INTERFACE_JDBC_URL");

	public static final String INTERFACE_JDBC_USER= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("INTERFACE_JDBC_USER")) : PropertiesLoader.getValueFor("INTERFACE_JDBC_USER");

	public static final String INTERFACE_JDBC_PASS= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("INTERFACE_JDBC_PASS")) : PropertiesLoader.getValueFor("INTERFACE_JDBC_PASS");

	public static final String KPI_JDBC_DRIVER=PropertiesLoader.getValueFor("KPI_JDBC_DRIVER");

	public static final String KPI_JDBC_URL=PropertiesLoader.getValueFor("KPI_JDBC_URL");

	public static final String KPI_JDBC_USER= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("KPI_JDBC_USER")) : PropertiesLoader.getValueFor("KPI_JDBC_USER");

	public static final String KPI_JDBC_PASS= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("KPI_JDBC_PASS")) : PropertiesLoader.getValueFor("KPI_JDBC_PASS");

	public static final String KPI_SCHEMA_NAME= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("KPI_SCHEMA_NAME")) : PropertiesLoader.getValueFor("KPI_SCHEMA_NAME");

	public static final List<String> RETRY_ERROR_CODE_LIST = Arrays.asList(PropertiesLoader.getValueFor("RETRY_ERROR_CODE_LIST").split(","));

	public static final String ACTIVEMQ_INITIAL_CONTEXT_FACTORY = PropertiesLoader.getValueFor("ACTIVEMQ_INITIAL_CONTEXT_FACTORY");

	public static final String ACTIVEMQ_PROVIDER_URL = PropertiesLoader.getValueFor("ACTIVEMQ_PROVIDER_URL");

	public static final String ACTIVEMQ_NOTIFICATIONQ_NAME = PropertiesLoader.getValueFor("ACTIVEMQ_NOTIFICATIONQ_NAME");

	public static final String ACTIVEMQ_USER_NAME = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVEMQ_USER_NAME")) : PropertiesLoader.getValueFor("ACTIVEMQ_USER_NAME");

	public static final String ACTIVEMQ_PASSWORD = ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesLoader.getValueFor("ACTIVEMQ_PASSWORD")) : PropertiesLoader.getValueFor("ACTIVEMQ_PASSWORD");

	public static final Integer HTTP_CONNECTION_TIMEOUT = Integer.parseInt(PropertiesLoader.getValueFor("HTTP_CONNECTION_TIMEOUT"));

	public static final Integer HTTP_READ_TIMEOUT = Integer.parseInt(PropertiesLoader.getValueFor("HTTP_READ_TIMEOUT"));

	//	Error Codes..
	public static final Long INTERNAL_SERVER_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INTERNAL_SERVER_ERROR"));

	public static final Long FILE_NAME_EXIST_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("FILE_NAME_EXIST_ERROR"));

	public static final Long DUPLICATE_RECORD_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("DUPLICATE_RECORD_ERROR"));

	public static final Long MANDATORY_FIELD_MISSING_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("MANDATORY_FIELD_MISSING_ERROR"));

	public static final Long INVALID_DATA_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_DATA_ERROR"));

	public static final Long MAPPING_DATA_NOT_FOUND_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("MAPPING_DATA_NOT_FOUND_ERROR"));

	public static final Long INVALID_RECORD_STRUCTURE_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_RECORD_STRUCTURE_ERROR"));

	public static final Long UNKNOWN_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("UNKNOWN_ERROR"));

	public static final Long PARTIAL_FILE_REJECTION_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("PARTIAL_FILE_REJECTION_ERROR"));

	public static final Long FULL_FILE_REJECTION_ERROR = Long.parseLong(PropertiesLoader.getErrorCodeFor("FULL_FILE_REJECTION_ERROR"));

	public static final Integer FILE_SUM_ERROR_MSG_CHAR_LIMIT = Integer.parseInt(PropertiesLoader.getValueFor("FILE_SUM_ERROR_MSG_CHAR_LIMIT"));

	public static final Long QUERY_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("QUERY_BALANCE_INTERFACE_ID"));

	public static final Long QUERY_STOCK_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("QUERY_STOCK_INTERFACE_ID"));

	public static final Long CREATE_SO_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("CREATE_SO_INTERFACE_ID"));

	public static final Long UPDATE_SO_STATUS_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("UPDATE_SO_STATUS_INTERFACE_ID"));

	public static final Long SAP_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("SAP_USER_ID"));

	public static final String BALANCE_RESPONSE_CONF_ATTR = PropertiesLoader.getValueFor("BALANCE_RESPONSE_CONF_ATTR");

	public static final String STATIC_RESPONSE_CONF_ATTR = PropertiesLoader.getValueFor("STATIC_RESPONSE_CONF_ATTR");

	public static final String RESPONSE_CONF_ATTR = PropertiesLoader.getValueFor("RESPONSE_CONF_ATTR");

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

	public static final Long  SERIAL_DOES_NOT_EXIST_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("SERIAL_DOES_NOT_EXIST_ERR_CODE"));

	public static final Long  INVALID_SERIAL_LENGTH_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_SERIAL_LENGTH_ERR_CODE"));

	public static final Long  ACC_NOT_EXISTS_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("ACC_NOT_EXISTS_ERR_CODE"));

	public static final Long  INACTIVE_DEFAULT_ACC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INACTIVE_DEFAULT_ACC_ERR_CODE"));

	public static final Long  INVALID_OR_INACTIVE_ACC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_OR_INACTIVE_ACC_ERR_CODE"));

	public static final Long  INVALID_TERR_ID_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_TERR_ID_ERR_CODE"));

	public static final Long  UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_CODE"));

	public static final Long  INVALID_METRIC_ERR_CODE=Long.parseLong(PropertiesLoader.getErrorCodeFor("INVALID_METRIC_ERR_CODE"));

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

	public static final String  SERIAL_DOES_NOT_EXIST_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(SERIAL_DOES_NOT_EXIST_ERR_CODE.toString());

	public static final String  INVALID_SERIAL_LENGTH_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_SERIAL_LENGTH_ERR_CODE.toString());

	public static final String  ACC_NOT_EXISTS_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(ACC_NOT_EXISTS_ERR_CODE.toString());

	public static final String  INACTIVE_DEFAULT_ACC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INACTIVE_DEFAULT_ACC_ERR_CODE.toString());

	public static final String  INVALID_OR_INACTIVE_ACC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_OR_INACTIVE_ACC_ERR_CODE.toString());

	public static final String  INVALID_TERR_ID_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_TERR_ID_ERR_CODE.toString());

	public static final String  UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(UNAVAILABLE_COUNTRY_FOR_PROVINCE_ERR_CODE.toString());

	public static final String  INVALID_METRIC_ERR_MSG = PropertiesLoader.getErrorDescriptionFor(INVALID_METRIC_ERR_CODE.toString());

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

	public static final String SORT_STOCK_DUMP_SCRIPT = PropertiesLoader.getValueFor("SORT_STOCK_DUMP_SCRIPT");

	public static final Long USER_SYNC_UPLOAD_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("USER_SYNC_UPLOAD_INTERFACE_ID"));

	public static final Integer KPI_FEED_QUEUE_RETRY_COUNT = Integer.parseInt(PropertiesLoader.getValueFor("KPI_FEED_QUEUE_RETRY_COUNT"));

	public static final String ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST = PropertiesLoader.getValueFor("ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST");

	public static final String REPROCESS_ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST = PropertiesLoader.getValueFor("REPROCESS_ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST");

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

	public static final String SORT_FILE_SCRIPT_AF = PropertiesLoader.getValueFor("SORT_FILE_SCRIPT_AF");

	public static final String AF_SORT = PropertiesLoader.getValueFor("AF_SORT");

	public static final int  AF_MAX_SN_LIMIT = Integer.parseInt(PropertiesLoader.getValueFor("AF_MAX_SN_LIMIT"));

	public static final String FIRST_FILE_HEADER_VALIDATION_ATTR = PropertiesLoader.getValueFor("FIRST_FILE_HEADER_VALIDATION_ATTR");

	public static final String SECOND_FILE_HEADER_VALIDATION_ATTR = PropertiesLoader.getValueFor("SECOND_FILE_HEADER_VALIDATION_ATTR");

	public static final String CSV_DELIMETER = "|";

	public static final String ERROR_CODE_STR="errorCode";

	public static final String ERROR_MSG_STR="errorMessage";

	public static final String STATUS_STR="STATUS";

	public static final String FAIL_STR="FAIL";

	public static final String SUCCESS_STR="SUCCESS";

	public static final List<String> INTERFACE_FILE_VALIDATION_LIST = Arrays.asList(PropertiesLoader.getValueFor("INTERFACE_FILE_VALIDATION_LIST").split(","));

	public static final String DWH_FV_SUBJECT = PropertiesLoader.getValueFor("DWH_FV_SUBJECT");

	public static final String DWH_FV_TEMPLATE = PropertiesLoader.getValueFor("DWH_FV_TEMPLATE");

	public static final String DWH_FV_TO_ADDRESS = PropertiesLoader.getValueFor("DWH_FV_TO_ADDRESS");

	public static final String DWH_FV_MEDIA = PropertiesLoader.getValueFor("DWH_FV_MEDIA");

	public static final String DWH_FV_LANG = PropertiesLoader.getValueFor("DWH_FV_LANG");

	public static final String QUERY_FIELD_CONF_ATTR = PropertiesLoader.getValueFor("QUERY_FIELD_CONF_ATTR");

	public static final List<String> SM_UPLOAD_ORG_USER_SYNC_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("SM_UPLOAD_ORG_USER_SYNC_INTERFACES").split(","));

	public static final String spStockDumpInsertQry = "INSERT INTO interface.sp_stock_dump(iccid, msisdn, imsi, do_id, so_id, destination_dealer_id, branch_code, brand, product_expired_date, stock_transfer_date, program_code, program_name, source_dealer_id, file_id, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String spStockDumpSelDistQry = "select distinct(so_id) from interface.sp_stock_dump where file_id = ? and status = ? order by so_id";

	public static final String spStockDumpSelQry = "SELECT iccid, msisdn, imsi, do_id, so_id, destination_dealer_id, branch_code, brand, product_expired_date, stock_transfer_date, program_code, program_name, source_dealer_id, file_id, status, error_code, error_message, stock_dump_id FROM interface.sp_stock_dump where so_id = ? and file_id = ? and status = ? order by program_code, product_expired_date, iccid::numeric";

	public static final String spStockDumpUpdateQry = "update interface.sp_stock_dump set status = ?, error_code = ?, error_message = ? where so_id = ? and file_id = ? and status = ?";

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

	public static final String spAllocDumpTotalsQry = "select status, count(*) from interface.sp_alloc_dump where file_id = ? group by status union select 'total', count(*) from interface.sp_alloc_dump where file_id = ?";

	public static final String spAllocDumpSuccessCountQry = "select count(1) from interface.sp_alloc_dump where file_id = ? and status in (statusIds)";

	public static final String spAllocDumpFailQry = "select iccid, msisdn, imsi, do_id, dealer_id, branch_code, brand, product_expired_date, so_creation_date, alloc_id, program_code, program_name, type, alloc_date, payment_date, error_code, error_message from interface.sp_alloc_dump where file_id = ? and status = ?";

	public static final String spAllocDumpSuccessRecordsDelQry = "delete from interface.sp_alloc_dump where file_id = ? and status = ?";

	public static final String spAllocDumpValidateQry = "select alloc_dump_id from interface.sp_alloc_dump where file_id = ? and status not in (statusIds) limit 1";

	public static final String spAllocDumpStatusUpdtQry = "update interface.sp_alloc_dump set status = ? where so_id in (soIds) and alloc_id in (allocIds) and file_id = ?";

	public static final String fileDelimeter = "\\|";

	public static final String delimeter = "|";

	public static final String st_success = "1301";

	public static final String st_error = "1302";

	public static final String lineSeparator = System.getProperty("line.separator");

	public static final Integer PARTNER_FILE_INSERTION_BATCH = Integer.parseInt(PropertiesLoader.getValueFor("PARTNER_FILE_INSERTION_BATCH"));

	public static final String IS_SFTP_ATTR = PropertiesLoader.getValueFor("IS_SFTP_ATTR");

	public static final String EXCLUDED_OPERATOR_TYPES = PropertiesLoader.getValueFor("EXCLUDED_OPERATOR_TYPES_ATTR");

	public static final String EXCLUDED_ORGANIZATION_TYPES = PropertiesLoader.getValueFor("EXCLUDED_ORG_TYPES_ATTR");

	public static final String FIELD_VALIDATION_CONF_ATTR = PropertiesLoader.getValueFor("FIELD_VALIDATION_CONF_ATTR");

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

	public static final String productTblUpdtQry = "UPDATE interface.product_details set status = ?,error_code=?,error_message=? where prd_details_id=?";

	public static final String getProductDtlsQry = "select * from interface.product_details where file_id_n = ? and status = ?  order by productid";

	public static final String productCreationFailQry = "select productId, productname, category, subcategory, startdt, enddt, salesprice, mrpprice, isserialized, seriltype, seriallength, error_code, error_message, materialCode from interface.product_details where file_id_n = ? and status = ?";

	public static final String productCreationDelQry = "delete from interface.product_details where file_id_n = ?";

	public static final String FILE_HEADER_VALIDATION_ATTR = PropertiesLoader.getValueFor("FILE_HEADER_VALIDATION_ATTR");

	public static final Long DEFAULT_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("DEFAULT_USER_ID"));

	public static final Long FOSS_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("FOSS_USER_ID"));

	public static final Long DWH_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("DWH_USER_ID"));

	public static final Long NDB_USER_ID = Long.parseLong(PropertiesLoader.getValueFor("NDB_USER_ID"));

	public static final String SORT_SERIAL_EXPIRY_SCRIPT = PropertiesLoader.getValueFor("SORT_SERIAL_EXPIRY_SCRIPT");

	public static final String serialExpryInsertQry = "INSERT INTO interface.serial_expiry( sl_no, old_prd_code, new_prd_code, expiry_dt, file_id, status, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String serialExpryUpdtQry = "UPDATE interface.serial_expiry set status = ?,error_code=?,error_message=? where serial_expiry_id in (serialExpryIds)";

	public static final String srlExpryUpdtQry = "UPDATE interface.serial_expiry set status = ?,error_code=?,error_message=? where sl_no=? and old_prd_code=? and status=?";

	public static final String getSerialExpryQry = "select * from interface.serial_expiry pd where pd.file_id=? and pd.status=?  order by pd.sl_no";

	public static final String spSerialExpryTotalsQry = "select status::character varying, count(*) from interface.serial_expiry where file_id = ? group by status union select 'total', count(*) from interface.serial_expiry where file_id = ?";

	public static final String serialExpiryFailQry = "select sl_no, old_prd_code, new_prd_code, expiry_dt, error_code, error_message from interface.serial_expiry where file_id = ? and status = ?";

	public static final String serialExpiryDelQry = "delete from interface.serial_expiry where file_id = ?";

	public static final Long PRODUCT_CREATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("PRODUCT_CREATION_INTERFACE_ID"));

	public static final Long SERIAL_EXPIRY_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SERIAL_EXPIRY_INTERFACE_ID"));

	public static final String EDIT_REMOTE_WS_URL_ATTR = PropertiesLoader.getValueFor("EDIT_REMOTE_WS_URL_ATTR");

	public static final String voucherStockDumpDistSelectQry = "select distinct dealer_id, source_dealer_id, program_code, so_id from interface.stock_dump_voucher where file_id_n = ?  and status = ?";

	public static final String voucherStockDumpSelectQry = "select stock_dump_id,dealer_id,source_dealer_id,program_code,so_id,serial_number from interface.stock_dump_voucher where file_id_n = ? and status = ?";

	public static final String voucherStockDumpSelDistQry = "select so_id, program_code, product_expired_date from interface.stock_dump_voucher where file_id_n = ? and status = ? group by so_id, program_code, product_expired_date";

	public static final String voucherStockDumpSelDistExpDtQry = "select * from interface.stock_dump_voucher where so_id = ? and program_code = ? and file_id_n = ? and status = ? and product_expired_date = ?";

	public static final String voucherStockDumpValidateSerialFaiureQry = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and program_code = ? and file_id_n = ? and status = ?";

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

	public static final String prdDtlsTotalsQry = "select status::character varying, count(*) from interface.product_details where file_id_n = ? group by status union select 'total', count(*) from interface.product_details where file_id_n = ?";

	public static final String LTYPE_MONGO_MAPPING = PropertiesLoader.getValueFor("LTYPE_MONGO_MAPPING");

	public static final String REF_ID_ATTR = PropertiesLoader.getValueFor("REF_ID_ATTR");

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

	public static final String TRANSACTIONS_DATA_ORDERS_CONFIGURATION = PropertiesLoader.getValueFor("TRANSACTIONS_DATA_ORDERS_CONFIGURATION");

	public static final Long STARTER_PACK_CATEGORY_ID = Long.valueOf(PropertiesLoader.getValueFor("STARTER_PACK_CATEGORY_ID"));

	public static final Long VOUCHER_CATEGORY_ID = Long.valueOf(PropertiesLoader.getValueFor("VOUCHER_CATEGORY_ID"));

	public static final String VO_CAT_REF_CODE = PropertiesLoader.getValueFor("VO_CAT_REF_CODE");

	public static final Long MULTIPLY_FACTOR = Long.valueOf(PropertiesLoader.getValueFor("MULTIPLY_FACTOR"));

	public static final String SORT_USER_SYNC_SCRIPT = PropertiesLoader.getValueFor("SORT_USER_SYNC_SCRIPT");

	public static final String FOSS_SYSTEM = PropertiesLoader.getValueFor("FOSS_SYSTEM");

	public static final String SALDOMOBO_SYSTEM = PropertiesLoader.getValueFor("SALDOMOBO_SYSTEM");

	public static final String DWH_SYSTEM = PropertiesLoader.getValueFor("DWH_SYSTEM");

	public static final String NDB_SYSTEM = PropertiesLoader.getValueFor("NDB_SYSTEM");

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

	public static final String USER_SYNC_DUMP_DATA_CONFIG = PropertiesLoader.getValueFor("USER_SYNC_DUMP_DATA_CONFIG");

	public static final Long USER_SYNC_DUMP_TYPE_ENTITY_TYPE_ID = Long.valueOf(PropertiesLoader.getValueFor("USER_SYNC_DUMP_TYPE_ENTITY_TYPE_ID"));

	public static final String SORT_ACTIVATION_FEED_SCRIPT = PropertiesLoader.getValueFor("SORT_ACTIVATION_FEED_SCRIPT");

	public static final Long ACTIVATION_FEED_BATCH_SIZE = Long.valueOf(PropertiesLoader.getValueFor("ACTIVATION_FEED_BATCH_SIZE"));

	public static final Long ACTIVATION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("ACTIVATION_FEED_INTERFACE_ID"));

	public static final Long DEACTIVATION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("DEACTIVATION_FEED_INTERFACE_ID"));

	public static final String activationFeedSuccessRecordsDelQry = "delete from interface.dwh_activation_feed where file_id = ? and status = ?";

	public static final String deactivationFeedSuccessRecordsDelQry = "delete from interface.dwh_deactivation_feed where file_id = ? and status = ?";

	public static final String voucherRedemptionFeedDelQry = "delete from interface.voucher_redemption_feed where file_id = ?"; 

	public static final String SORT_VOUCHER_REDEMPTION_SCRIPT = PropertiesLoader.getValueFor("SORT_VOUCHER_REDEMPTION_SCRIPT");

	public static final Long VOUCHER_REDEMPTION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VOUCHER_REDEMPTION_FEED_INTERFACE_ID"));

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

	public static final Integer ACTUAL_SERIAL_LENGTH = Integer.parseInt(PropertiesLoader.getValueFor("ACTUAL_SERIAL_LENGTH"));

	public static final String updateInvalidSerialsInVoucherAllocDump = "update interface.alloc_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and alloc_no = ? and program_code = ? and file_id_n = ? and status = ? and serial_number = ?";

	public static final String updateInvalidSerialsInVoucherStockDump = "update interface.stock_dump_voucher set status = ?, error_code = ?, error_message = ? where so_id = ? and program_code = ? and file_id_n = ? and status = ? and serial_number = ?";

	public static final String updateSupplierFailureInVoucherStockDump =  "update interface.stock_dump_voucher set status = ?, error_code = ? ,error_message = ? where so_id = ? and program_code = ? and file_id_n = ? and status = ?";

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

	public static final String DATE_TIME_FORMAT_ATTR = PropertiesLoader.getValueFor("DATE_TIME_FORMAT_ATTR");

	public static final String INTERFACE_SUMMARY_STAUS_UPDATE_QUERY = "update interface.tr_interface_summary set status_n = ?, retry_count_n = ? where trans_id_n = ?";

	public static final String INTERFACE_FAILURE_SEQ_QUERY = "select nextval('interface.TR_INTERFACE_FAILURE_SEQ')";

	public static final String INTERFACE_FAILURE_INSERT_QUERY = "insert into interface.tr_interface_failure (trans_failure_id_n, trans_id_n, ack_data_b, ack_time_dt, response_data_b, response_time_dt, file_id_n, error_code_n, error_message_v) values(?,?,?,?,?,?,?,?,?)";

	public static final String INTERFACE_SUMMARY_UPDATE_QUERY = "update interface.tr_interface_summary set request_data_b = ?, ack_data_b = ?, ack_time_dt = ?, orgnl_response_data_b = ?, response_data_b = ?, response_time_dt = ?, status_n = ?, retry_count_n = ?, ref_data1_v = ?, ref_data2_n = ?, ref_data3_n = ?, ref_data4_n = ?, ref_data5_v = ? where trans_id_n = ?";

	public static final String INTERFACE_SUMMARY_SEQ_QUERY = "select nextval('interface.TR_INTERFACE_SUMMARY_SEQ')";

	public static final String INTERFACE_SUMMARY_INSERT_QUERY = "insert into interface.tr_interface_summary (trans_id_n, interface_id_n, orgnl_request_data_b, request_data_b, request_time_dt, ack_data_b, ack_time_dt, orgnl_response_data_b, response_data_b, response_time_dt, status_n, retry_count_n, ref_data1_v, ref_data2_n, ref_data3_n, ref_data4_n, ref_data5_v) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

	public static final List<String> SELF_NOTIFY_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("SELF_NOTIFY_INTERFACE_LIST").split(","));

	public static final String PROCESSOR_CLASS_ATTR = PropertiesLoader.getValueFor("PROCESSOR_CLASS_ATTR");

	public static final String REJECTION_FILE_QUERY_ATTR = PropertiesLoader.getValueFor("REJECTION_FILE_QUERY_ATTR");

	public static final String FILE_ROW_COUNT_ATTR = PropertiesLoader.getValueFor("FILE_ROW_COUNT_ATTR");

	public static final String LINE_UPDATE_STR="lineUpdate";

	public static final String YES_STR="YES";

	/* KPI Aggregation related constants*/

	public static final String PRAMS_MAP = "PARAMS_MAP";

	public static final String QUERY = "QUERY";

	public static final String kpiEventFormatQry = "select m.event_type_n, m.system_type_v, f.source_type_n, f.source_key_v, f.instance_type_n, f.instance_key_v, f.date_format_v, f.date_key_v from kpi.ms_event_type_master m, kpi.mp_event_format_mapping f where m.event_type_n = f.event_type_n and m.system_event_v = 'interfaceId'";

	public static final String tempTableInsertQry = "INSERT INTO kpi.table_name(id_n, actor_type_n, actor_key_v, event_type_n, metrics_type_n, metrics_key_v, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_key_v, system_type_v, correction_v, batch_number_n, instance_type_n, instance_key_v, data_flag_n, instance_id_n, metrics_id_n, file_id_n, data_string_v) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String transTempTblInsertQry = "INSERT INTO kpi.table_name(id_n, actor_type_n, actor_key_v, event_type_n, metrics_type_n, metrics_key_v, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_key_v, system_type_v, correction_v, batch_number_n, instance_type_n, instance_key_v, data_flag_n, instance_id_n, metrics_id_n, file_id_n, data_string_v, acellid_v, bcellid_v) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String tempTableDeleteQry = "DELETE FROM kpi.table_name where file_id_n = ? and batch_number_n = ?";

	public static final String dailyTableInsertQry = "INSERT INTO kpi.daily_table(day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String dailyTableInsertQryForOrgBalance = "INSERT INTO kpi.daily_table( day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) on conflict ( day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n ) do update set dimension_1_n = ?, dimension_2_n = ?, dimension_3_n = ?, dimension_4_n = ?, dimension_5_n = ?, dimension_6_n = ?, dimension_7_n = ?, dimension_8_n = ?, dimension_9_n = ?, dimension_10_n = ?, no_of_events_n = ?;";

	public static final String dailyTableUpdateQry = "UPDATE kpi.daily_table SET dimension_1_n = ?, dimension_2_n = ?, dimension_3_n = ?, dimension_4_n = ?, dimension_5_n = ?, dimension_6_n = ?, dimension_7_n = ?, dimension_8_n = ?, dimension_9_n = ?, dimension_10_n = ?, no_of_events_n = ?, last_updated_time_dt = ( select now() ) WHERE day_id_n = ? and event_type_n = ? and actor_type_n = ? and actor_id_n = ? and metrics_type_n = ? and metrics_id_n = ? and source_type_n = ? and source_id_n = ? and data_flag_n = ? and instance_type_n = ? and instance_id_n = ?";

	public static final String monthlyTableInsertQry = "INSERT INTO kpi.monthly_table(month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String monthlyTableUpdateQry = "UPDATE kpi.monthly_table SET dimension_1_n = ?, dimension_2_n = ?, dimension_3_n = ?, dimension_4_n = ?, dimension_5_n = ?, dimension_6_n = ?, dimension_7_n = ?, dimension_8_n = ?, dimension_9_n = ?, dimension_10_n = ?, no_of_events_n = ?, last_updated_time_dt = ( select now() ) WHERE month_id_n = ? and event_type_n = ? and actor_type_n = ? and actor_id_n = ? and metrics_type_n = ? and metrics_id_n = ? and source_type_n = ? and source_id_n = ? and data_flag_n = ? and instance_type_n = ? and instance_id_n = ?";

	public static final String kpiActorMappingQry = "select * from kpi.mp_event_actor_mapping where event_type_n = ?;";

	public static final String kpiMetricMappingQry = "select * from kpi.mp_event_metric_mapping where event_type_n = ? order by metric_key_v;";

	public static final String kpiDimensioMappingQry = "select * from kpi.mp_event_dimension_mapping where event_type_n = ?;";

	public static final String kpiFrequencyMappingQry = "select * from kpi.mp_event_frequency_mapping where event_type_n = ?;";

	public static final String kpiCrossSellingMappingQry = "select * from kpi.mp_event_crossell_mapping where event_type_n = ?;";

	public static final String kpiCrossSellMpQry = "select * from kpi.mp_event_crossell_mapping where event_type_n = (select event_type_n from kpi.ms_event_type_master where system_event_v = ?);";

	public static final String tempTblSlctQry = "select *, case when actor_key_v is not null and actor_id_n = 0 then 'actor_field%Actor mapping not found for actor_field ' || actor_key_v when actor_id_n = -1 then 'actor-field%Found duplicate mapping for actor-field ' || actor_key_v when metrics_key_v is not null and metrics_id_n = 0 then 'metric_field%Metric mapping not found for metric_field ' || metrics_key_v when metrics_id_n = -1 then 'metric_field%Found duplicate mapping for metric_field ' || metrics_key_v when source_key_v is not null and source_id_n = 0 then 'source_field%Source mapping not found for source_field ' || source_key_v when source_id_n = -1 then 'source_field%Found duplicate mapping for source_field ' || source_key_v when instance_key_v is not null and instance_id_n = 0 then 'instance_field%Instance mapping not found for instance_field ' || instance_key_v when instance_id_n = -1 then 'instance_field%Found duplicate mapping for instance_field ' || instance_key_v end as failure_value from kpi.table_name where file_id_n = ? and batch_number_n = ? and status_flag_n = ?";

	public static final String tempKPIAggrInsertQry = "INSERT INTO kpi.tr_temp_upload_aggr_failure( file_id_n, batch_number_n, data_string_v, status_flag_n, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?);";

	public static final String tempKPIInsertQuery = "INSERT INTO kpi.tr_temp_upload_aggr_failure(id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, status_flag_n, system_type_v, correction_v, file_id_n, batch_number_n, data_string_v, error_code, error_message) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";

	public static final String tempTblSelectQry = "select * from kpi.table_name where file_id_n = ? and status_flag_n = ?";

	public static final String failureCountQry = "select count(1) from kpi.tr_temp_upload_aggr_failure where file_id_n = ?";

	public static final String failureDistinctCountQry = "select count(distinct data_string_v) from kpi.tr_temp_upload_aggr_failure where file_id_n = ?";

	public static final String tempTblDataSlctQry = "select data_string_v from kpi.table_name where file_id_n = ? and status_flag_n = ?";

	public static final String kpiReprocessSelectQry = "select * from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ?";

	public static final String kpiLacciReprocessSelectQry = "select * from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ? and error_code = ?";

	public static final String kpiReprocessTempTblInsrtQry = "insert into kpi.table_name( id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, system_type_v, correction_v, file_id_n, data_string_v, batch_number_n ) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String kpiReprocessTransTempTblInsrtQry = "insert into kpi.table_name( id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, instance_type_n, instance_id_n, system_type_v, correction_v, file_id_n, data_string_v, batch_number_n, acellid_v, bcellid_v) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public static final String kpiTempTblTotalQry = "select status_flag_n :: character varying, count(status_flag_n) from kpi.table_name where file_id_n = ? group by status_flag_n union select 'total', count(*) from kpi.table_name where file_id_n = ?";

	public static final String kpiTmpFailureTblDltQry = "delete from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ?";

	public static final String kpiLacciTmpFailureTblDltQry = "delete from kpi.tr_temp_upload_aggr_failure where file_id_n = ? and status_flag_n = ? and error_code = ?";

	public static final String tempTblAllDltQry = "delete from kpi.table_name where file_id_n = ? ";

	public static final String freqMapQry = "select * from kpi.mp_event_frequency_mapping where event_type_n = (select event_type_n from kpi.ms_event_type_master where system_event_v = ?);";

	public static final Long KPI_DIMENSION_MAPPING = Long.valueOf(PropertiesLoader.getValueFor("KPI_DIMENSION_MAPPING"));

	public static final String STATUS_CHECK_INTERFACE_CONF = PropertiesLoader.getValueFor("STATUS_CHECK_INTERFACE_CONF");

	public static final String LOOKUP_CONF_ATTR = PropertiesLoader.getValueFor("LOOKUP_CONF_ATTR");

	public static final String FIELD_LOOKUP_CONF_ATTR = PropertiesLoader.getValueFor("FIELD_LOOKUP_CONF_ATTR");

	public static final String CROSS_SELLING_FLAG_ATTR = PropertiesLoader.getValueFor("CROSS_SELLING_FLAG_ATTR");

	public static final Long TRANSFER_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesLoader.getValueFor("TRANSFER_BALANCE_INTERFACE_ID"));

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

	public static final String metricsInsertQuery = "insert into kpi.ms_metrics_master(metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v) values (?, ?, ?, ?, ?)";

	public static final String actorInsertQuery = "insert into kpi.ms_actor_master(actor_id_v, actor_type_n) values (?, ?)";

	public static final String deleteRejectFileQry = "delete from interface.tr_interface_file_summary_details where file_id_n = ? and file_type_v = 'R'";

	public static final String SORT_IBM_DATA_FEED_SCRIPT = PropertiesLoader.getValueFor("SORT_IBM_DATA_FEED_SCRIPT");

	public static final Long MSISDN_INJECTION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("MSISDN_INJECTION_INTERFACE_ID"));

	public static final Long MSISDN_RGU_GA_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("MSISDN_RGU_GA_INTERFACE_ID"));

	public static final String INCOMING_FILE_RECORDS_LIMIT = PropertiesLoader.getValueFor("INCOMING_FILE_RECORDS_LIMIT");

	public static final String HTTP_CONNECTION_TIMEOUT_ATTR = PropertiesLoader.getValueFor("HTTP_CONNECTION_TIMEOUT_ATTR");

	public static final String HTTP_READ_TIMEOUT_ATTR = PropertiesLoader.getValueFor("HTTP_READ_TIMEOUT_ATTR");

	public static final Long REGISTRATION_FEED_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("REGISTRATION_FEED_INTERFACE_ID"));

	public static final List<String> REJECTION_BY_FILE_ID_INTERFACES = Arrays.asList(PropertiesLoader.getValueFor("REJECTION_BY_FILE_ID_INTERFACES").split(","));
}