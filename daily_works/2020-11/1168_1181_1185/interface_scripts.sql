
-- 1168 QURO_STATUS column added
update interface.ms_interface_attr set value_v = 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT|QSSO_STATUS|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1168027;
update interface.ms_interface_attr set value_v = 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT|QSSO_STATUS' where attribute_id_n = 1168031;
update interface.ms_interface_attr set value_v = 'DATE;M;D;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%ID_OUTLET;M;R;%QTY;M;R;[0-9]*+%AMOUNT;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$%QSSO_STATUS;M;R;[0-1]+' where attribute_id_n = 1168032;
update interface.ms_interface_attr set value_v = '{"process_enabled":true,"fields":{"actor_field":{"10":"ID_OUTLET","12":"SITE_ID"},"metric_field":"","source_field":"MICRO","instance_field":"QSSO_STATUS"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"lookup_query":"","notexist-lookup-flag":true,"table_name":"tr_temp_daily_sso_aggr","daily_table":"tr_daily_daily_sso_aggr","monthly_table":"tr_monthly_daily_sso_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_daily_sso_aggr","field_indexes":[0,1,2,3,6],"include-fileId":true},"data_filter":[]}' where attribute_id_n = 1168037;

-- 1181 QSSO_STATUS column added
update interface.ms_interface_attr set value_v = 'DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT|QURO_STATUS|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1181027;
update interface.ms_interface_attr set value_v = 'DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT|QURO_STATUS' where attribute_id_n = 1181031;
update interface.ms_interface_attr set value_v = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%OUTLET;M;R;%HIT;M;R;[0-9]+%AMOUNT;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$%QURO_STATUS;M;R;[0-1]+' where attribute_id_n = 1181032;
update interface.ms_interface_attr set value_v = '{"process_enabled":true,"fields":{"actor_field":{"10":"OUTLET","12":"SITE_ID"},"metric_field":"","source_field":"MICRO","instance_field":"QURO_STATUS"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"table_name":"tr_temp_uro_20k_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_uro_20k_aggr","monthly_table":"tr_monthly_uro_20k_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"data_filter":[],"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_uro_20k_aggr","include-fileId":true,"field_indexes":[0,1,2,3,6]}}' where attribute_id_n = 1181037;


-- 1185 (INTHDP023 - DSSF Attendance)
delete from interface.ms_interface_attr where interface_id_n = 1185;
delete from interface.ms_interface_notification where interface_id_n = 1185;
delete from interface.ms_interface where interface_id_n = 1185;
delete from interface.ms_module where module_id_n = 263;

-- MS_MODULE
INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, LAST_UPDATED_TIME_DT, DESCRIPTION_V) VALUES (263, 'INTHDP023 - DSSF Attendance', NULL, NOW(), 'Incoming Outlet SP Tagging file from Hadoop feed');

-- MS_INTERFACE
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1185, 'INTHDP023 - DSSF Attendance', 263, 2, 2, 1, 'java:global/EnhancesysManagement/IntegrationServices/CSVtoJSONConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, now());

-- Attributes for interface 1185 (INTHDP023 - DSSF Attendance - Receive file)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185001, 1185, 'Remote Host', '50.17.26.200', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185002, 1185, 'Remote Port', '22', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185003, 1185, 'Remote User', 'appuser', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185004, 1185, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185005, 1185, 'Remote File', 'dssf_attendance', now());                                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185006, 1185, 'Remote FileName Format', 'yyyyMMddHHmmss', now());                    
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185007, 1185, 'Remote File Format', 'csv', now());                                   
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185008, 1185, 'Control file format', 'ctl', now());                                  
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185009, 1185, 'Remote Dir', '/home/appuser/interfaces/HADOOP/master_files/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185010, 1185, 'Remote control directory', '/home/appuser/interfaces/HADOOP/master_files/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185011, 1185, 'Remote rejected directory', '/home/appuser/interfaces/HADOOP/rejected_file/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185012, 1185, 'Remote rejected control directory', '/home/appuser/interfaces/HADOOP/rejected_file/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185013, 1185, 'Remote backup directory', '/home/appuser/interfaces/HADOOP/backup/master_files/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185014, 1185, 'Remote Control Backup Directory', '/home/appuser/interfaces/HADOOP/backup/master_files/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185015, 1185, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185016, 1185, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185017, 1185, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185018, 1185, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185019, 1185, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185020, 1185, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185021, 1185, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185022, 1185, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185023, 1185, 'Local Filter Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185024, 1185, 'Local Filter Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/dssf_attendance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185025, 1185, 'Received File Type', 'Request File Type', now());                     
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185026, 1185, 'Csv Delimeter', '\|', now());                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185027, 1185, 'Rejection File Headers', 'DATE|CLUSTER|PROGMOTER_ORG_ID|CHECKIN_TYPE|QTY|ERRORCODE|ERRORMESSAGE', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185028, 1185, 'Rejection File Preparation Class', 'java:global/EnhancesysManagement/IntegrationServices/KpiMasterRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185029, 1185, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185030, 1185, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185031, 1185, 'File Headers', 'DATE|CLUSTER|PROGMOTER_ORG_ID|CHECKIN_TYPE|QTY', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185032, 1185, 'Field Validation Conf', 'DATE;M;RD;[0-9]*;yyyyMMdd%CLUSTER;M;R;%PROGMOTER_ORG_ID;M;R;%CHECKIN_TYPE;M;R;%QTY;M;R;[0-9]+', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185033, 1185, 'CSV MERGER', 'java:global/EnhancesysManagement/IntegrationServices/GenericFileProcessor!com.enhancesys.integration.services.interfaces.merger.CSVMerger', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185034, 1185, 'Validation Conf Delimiter', '%', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185035, 1185, 'File Data Batch Size', '5000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185036, 1185, 'Rejection File Query', 'select data_string_v, error_code, error_message from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185037, 1185, 'Field Lookup Conf', '{"process_enabled":true,"fields":{"actor_field":"PROGMOTER_ORG_ID","metric_field":"CHECKIN_TYPE","source_field":"CLUSTER","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily","3":"cleanup"},"table_name":"tr_temp_dssf_attendance_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_dssf_attendance_aggr","monthly_table":"tr_monthly_dssf_attendance_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"data_filter":[],"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_dssf_attendance_aggr","include-fileId":true,"field_indexes":[0,1,2,3]}}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185038, 1185, 'Incoming File Records Limit', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185039, 1185, 'PPK Path', '/home/appuser/snoc/snocconf/interfaceconf/credentials/TEST_251.ppk', NOW());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1185040, 1185, 'Cleanup Conf', '{"1001":{"cleanup-tables":{"temporary":true,"validation":true,"failure":false,"summary":false},"delete-mode":"normal","retension":100},"1002":{"cleanup-tables":{"temporary":true,"validation":true,"failure":false,"summary":false},"delete-mode":"with-in-batch","batch_size":500,"retension":100},"1003":{"cleanup-tables":{"temporary":true,"validation":true,"failure":true,"summary":true},"delete-mode":"with-batch","batch_size":1000,"retension":100},"1004":{"cleanup-tables":{"temporary":true,"validation":true,"failure":true,"summary":true},"delete-mode":"with-in-batch","batch_size":100,"retension":100},"default":"1001","is-enabled":false}', now());

-- Email Notification Attributes for interface -->INTHDP023 - DSSF Attendance
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1185001, 1185, 'FILE_REJECTED_CTL_0', 'email', 'int_md5_hash_key_failed', 'FILE REJECTED - INTHDP023 - DSSF Attendance', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1185002, 1185, 'FILE_NOT_FOUND_0', 'email', 'int_file_not_found', 'FILE NOT FOUND - INTHDP023 - DSSF Attendance', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1185003, 1185, 'FILE_REJECTED_PART_0', 'email', 'int_file_rejected_partially', 'FILE REJECTED PARTIALLY - INTHDP023 - DSSF Attendance', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1185004, 1185, 'FILE_REJECTED_FULL_0', 'email', 'int_file_rejected_fully', 'FILE REJECTED FULLY - INTHDP023 - DSSF Attendance', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1185005, 1185, 'FILE_SUCCESS_0', 'email', 'int_file_processed_sucessfully', 'FILE SUCCESSFULLY PROCESSED - INTHDP023 - DSSF Attendance', 'Support Team', '', '', '', 2, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1185006, 1185, 'CONNECTION_REFUSED_0', 'email', 'int_connection_refused', 'CONNECTION REFUSED - INTHDP023 - DSSF Attendance', 'Support Team', '', '', '', 1, NOW(),'en');


delete from interface.ms_interface_attr where attribute_id_n in (1163007,1164007);
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1163007, 1163, 'Static Response Conf', '{"enabled":fasle,"url":"http://interface.enhancesys.com:8080/interfaceResponseServices/webservices/InterfaceResponseServicesImpl?wsdl","user":"admin","password":"admin","res_type":"Success"}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1164007, 1164, 'Static Response Conf', '{"enabled":fasle,"url":"http://interface.enhancesys.com:8080/interfaceResponseServices/webservices/InterfaceResponseServicesImpl?wsdl","user":"admin","password":"admin","res_type":"Success"}', now());


--mkdir -p /home/appuser/interfaces/S-NOC/REMOTE/HADOOP/master_files/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/REMOTE/HADOOP/rejected_file/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/REMOTE/HADOOP/backup/master_files/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/master_file/HADOOP/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/backup/master_file/HADOOP/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/rejected_file/HADOOP/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/backup/rejected_file/HADOOP/dssf_attendance/
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/backup/filter_file/HADOOP/dssf_attendance/


 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/REMOTE/HADOOP/master_files/dssf_attendance/' where attribute_id_n = 1185009;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/REMOTE/HADOOP/master_files/dssf_attendance/' where attribute_id_n = 1185010;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/REMOTE/HADOOP/rejected_file/dssf_attendance/' where attribute_id_n = 1185011;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/REMOTE/HADOOP/rejected_file/dssf_attendance/' where attribute_id_n = 1185012;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/REMOTE/HADOOP/backup/master_files/dssf_attendance/' where attribute_id_n = 1185013;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/REMOTE/HADOOP/backup/master_files/dssf_attendance/' where attribute_id_n = 1185014;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/master_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185015;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/master_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185016;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/backup/master_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185017;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/backup/master_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185018;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/rejected_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185019;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/rejected_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185020;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/backup/rejected_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185021;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/backup/rejected_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185022;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/backup/filter_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185023;
 update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/S-NOC/LOCAL/backup/filter_file/HADOOP/dssf_attendance/' where attribute_id_n = 1185024;
 
 update interface.ms_interface_attr set value_v = '/home/appuser/snoc/INTERFACE_CONFIGURATION/INTERFACE_KPI_200.ppk' where attribute_id_n = 1185039;

-- 1185_NOT_EXIST_QUERY={ "ACTOR": { "select": "select 15 as actor_type_n, a.actor_key_v from (select distinct actor_key_v from kpi.tr_temp_dssf_attendance_aggr where file_id_n = ?) a where a.actor_key_v not in (select actor_id_v from kpi.ms_actor_master where actor_type_n = 15)","insert":"insert into kpi.ms_actor_master(actor_type_n, actor_id_v) values (?, ?)","select-placeholders":1,"insert-placeholders":[{"1":"long"},{"2":"string"}]},"METRICS":{"select":"select a.metrics_key_v, a.metrics_key_v, 26 as metrics_type_n, a.metrics_key_v, 'Interface' as definition_v from( select distinct metrics_key_v from kpi.tr_temp_dssf_attendance_aggr where file_id_n = ?) a where a.metrics_key_v not in (select ext_reference_id_v from kpi.ms_metrics_master where metrics_type_n = 26)","insert":"insert into kpi.ms_metrics_master(metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v) values (?, ?, ?, ?, ?)","select-placeholders":1,"insert-placeholders":[{"1":"string"},{"2":"string"},{"3":"long"},{"4":"string"},{"5":"string"}]}}
