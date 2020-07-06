-- Prepared by Vinay(vinay.nagaraj@enhancesys.com)
-- Scripts to configure interface aggregation for analytics events: 
-- Update for hadoop confiuration

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Update for INTHDP005 - RGU-GA with Injection (1169)

update interface.ms_interface_attr set value_v = '{"process_enabled":true,"fields":{"actor_field":{"10":"ID_OUTLET","12":"SITE_ID"},"metric_field":"FLAG_ACM","source_field":"MICRO","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"table_name":"tr_temp_rguga_injection_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_rguga_injection_aggr","monthly_table":"tr_monthly_rguga_injection_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_rguga_injection_aggr","field_indexes":[0,1,2,3,4,5],"include-fileId":true}}' where attribute_id_n = 1169037 ;
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- DELETE HADOOP FEED IF ANY EXISTING CONFIGURATION
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE FROM KPI.TR_TEMP_HADOOP_FAILURE_AGGR WHERE FILE_ID_N IN (SELECT FILE_ID_N FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY_DETAILS WHERE FILE_ID_N IN (SELECT FILE_ID_N FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY WHERE INTERFACE_ID_N IN (1182)));
DELETE FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY_DETAILS WHERE FILE_ID_N IN (SELECT FILE_ID_N FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY WHERE INTERFACE_ID_N IN (1182));
DELETE FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY WHERE INTERFACE_ID_N IN (1182);
DELETE FROM INTERFACE.MS_INTERFACE_NOTIFICATION WHERE INTERFACE_ID_N IN (1182);
DELETE FROM INTERFACE.MS_INTERFACE_ATTR WHERE INTERFACE_ID_N IN (1182);
DELETE FROM INTERFACE.MS_INTERFACE WHERE INTERFACE_ID_N IN (1182);
DELETE FROM INTERFACE.MS_MODULE WHERE MODULE_ID_N IN (260);
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- INSERT QUERIES

-- INTERFACE.MS_MODULE
INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, LAST_UPDATED_TIME_DT, DESCRIPTION_V) VALUES (260, 'INTHDP018 - Outlet SP Tagging', NULL, NOW(), 'Incoming Outlet SP Tagging file from Hadoop feed');


-- INTERFACE.MS_INTERFACE:
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1182, 'INTHDP018 - Outlet SP Tagging', 260, 2, 2, 1, 'java:global/EnhancesysManagement/IntegrationServices/CSVtoJSONConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, now());


-- Attributes for interface 1182 (Outlet SP Tagging - Receive file)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182001, 1182, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182002, 1182, 'Remote Port', 'REMOTE_PORT', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182003, 1182, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182004, 1182, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182005, 1182, 'Remote File', 'sp_tagging', now());                                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182006, 1182, 'Remote FileName Format', 'yyyyMMddHHmmss', now());                    
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182007, 1182, 'Remote File Format', 'csv', now());                                   
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182008, 1182, 'Control file format', 'ctl', now());                                  
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182009, 1182, 'Remote Dir', '/home/appuser/interfaces/HADOOP/master_files/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182010, 1182, 'Remote control directory', '/home/appuser/interfaces/HADOOP/master_files/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182011, 1182, 'Remote rejected directory', '/home/appuser/interfaces/HADOOP/rejected_file/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182012, 1182, 'Remote rejected control directory', '/home/appuser/interfaces/HADOOP/rejected_file/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182013, 1182, 'Remote backup directory', '/home/appuser/interfaces/HADOOP/backup/master_files/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182014, 1182, 'Remote Control Backup Directory', '/home/appuser/interfaces/HADOOP/backup/master_files/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182015, 1182, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182016, 1182, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182017, 1182, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182018, 1182, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182019, 1182, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182020, 1182, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182021, 1182, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182022, 1182, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182023, 1182, 'Local Filter Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182024, 1182, 'Local Filter Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/sp_tagging/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182025, 1182, 'Received File Type', 'Request File Type', now());                     
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182026, 1182, 'Csv Delimeter', '\|', now());                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182027, 1182, 'Rejection File Headers', 'DATE|OUTLET_ID|SP_TAG_QTY|ERRORCODE|ERRORMESSAGE', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182028, 1182, 'Rejection File Preparation Class', 'java:global/EnhancesysManagement/IntegrationServices/KpiMasterRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182029, 1182, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182030, 1182, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182031, 1182, 'File Headers', 'DATE|OUTLET_ID|SP_TAG_QTY', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182032, 1182, 'Field Validation Conf', 'DATE;M;RD;[0-9]*;yyyyMMdd%OUTLET_ID;M;R;%SP_TAG_QTY;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182033, 1182, 'CSV MERGER', 'java:global/EnhancesysManagement/IntegrationServices/GenericFileProcessor!com.enhancesys.integration.services.interfaces.merger.CSVMerger', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182034, 1182, 'Validation Conf Delimiter', '%', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182035, 1182, 'File Data Batch Size', '5000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182036, 1182, 'Rejection File Query', 'select data_string_v, error_code, error_message from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182037, 1182, 'Field Lookup Conf', '{"process_enabled":true,"fields":{"actor_field":"OUTLET_ID","metric_field":"","source_field":"","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily","3":"cleanup"},"table_name":"tr_temp_sp_tagging_aggr","lookup_query":"","notexist_lookup":"","no_of_params":{},"daily_table":"tr_daily_sp_tagging_aggr","monthly_table":"tr_monthly_sp_tagging_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"duplicate_validation_conf":{"field_indexes":[0,1],"schema_name":"kpi","table_name":"tr_validate_sp_tagging_aggr","include-fileId":true},"data_filter":[]}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182038, 1182, 'Incoming File Records Limit', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1182039, 1182, 'PPK Path', '/home/appuser/snoc/snocconf/interfaceconf/credentials/TEST_251.ppk', NOW());

-- Email Notification Attributes for interface -->INTHDP018 - Outlet SP Tagging
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1182001, 1182, 'FILE_REJECTED_CTL_0', 'email', 'int_md5_hash_key_failed', 'FILE REJECTED - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1182002, 1182, 'FILE_NOT_FOUND_0', 'email', 'int_file_not_found', 'FILE NOT FOUND - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1182003, 1182, 'FILE_REJECTED_PART_0', 'email', 'int_file_rejected_partially', 'FILE REJECTED PARTIALLY - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1182004, 1182, 'FILE_REJECTED_FULL_0', 'email', 'int_file_rejected_fully', 'FILE REJECTED FULLY - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1182005, 1182, 'FILE_SUCCESS_0', 'email', 'int_file_processed_sucessfully', 'FILE SUCCESSFULLY PROCESSED - INTHDP017 - URO 20K', 'Support Team', '', '', '', 2, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1182006, 1182, 'CONNECTION_REFUSED_0', 'email', 'int_connection_refused', 'CONNECTION REFUSED - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');


-- Update Query 