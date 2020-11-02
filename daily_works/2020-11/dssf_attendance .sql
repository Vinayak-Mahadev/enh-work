-- 1184 (INTHDP023 - DSSF Attendance)

delete from interface.ms_interface_attr where interface_id_n = 1184;
delete from interface.ms_interface where interface_id_n = 1184;
delete from interface.ms_module where module_id_n = 263;

-- MS_MODULE
INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (263, 'INTHDP023 - DSSF Attendance', null , '');

-- MS_INTERFACE
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1184, 'INTHDP023 - DSSF Attendance', 263, 2, 2, 1, 'java:global/EnhancesysManagement/IntegrationServices/CSVtoJSONConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, now());

-- Attributes for interface 1184 (INTHDP023 - DSSF Attendance - Receive file)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184001, 1184, 'Remote Host', '50.17.26.200', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184002, 1184, 'Remote Port', '22', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184003, 1184, 'Remote User', 'appuser', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184004, 1184, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184005, 1184, 'Remote File', 'dssf_attendance ', now());                                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184006, 1184, 'Remote FileName Format', 'yyyyMMddHHmmss', now());                    
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184007, 1184, 'Remote File Format', 'csv', now());                                   
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184008, 1184, 'Control file format', 'ctl', now());                                  
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184009, 1184, 'Remote Dir', '/home/appuser/interfaces/HADOOP/master_files/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184010, 1184, 'Remote control directory', '/home/appuser/interfaces/HADOOP/master_files/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184011, 1184, 'Remote rejected directory', '/home/appuser/interfaces/HADOOP/rejected_file/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184012, 1184, 'Remote rejected control directory', '/home/appuser/interfaces/HADOOP/rejected_file/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184013, 1184, 'Remote backup directory', '/home/appuser/interfaces/HADOOP/backup/master_files/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184014, 1184, 'Remote Control Backup Directory', '/home/appuser/interfaces/HADOOP/backup/master_files/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184015, 1184, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184016, 1184, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184017, 1184, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184018, 1184, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184019, 1184, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184020, 1184, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184021, 1184, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184022, 1184, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184023, 1184, 'Local Filter Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184024, 1184, 'Local Filter Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/dssf_attendance /', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184025, 1184, 'Received File Type', 'Request File Type', now());                     
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184026, 1184, 'Csv Delimeter', '\|', now());                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184027, 1184, 'Rejection File Headers', 'DATE|CLUSTER|PROGMOTER_ORG_ID|CHECKIN_TYPE|QTY|ERRORCODE|ERRORMESSAGE', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184028, 1184, 'Rejection File Preparation Class', 'java:global/EnhancesysManagement/IntegrationServices/KpiMasterRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184029, 1184, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184030, 1184, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184031, 1184, 'File Headers', 'DATE|CLUSTER|PROGMOTER_ORG_ID|CHECKIN_TYPE|QTY', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184032, 1184, 'Field Validation Conf', 'DATE;M;RD;[0-9]*;yyyyMMdd%CLUSTER;M;R;%PROGMOTER_ORG_ID;M;R;%CHECKIN_TYPE;M;R;%QTY;M;R;[0-9]+', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184033, 1184, 'CSV MERGER', 'java:global/EnhancesysManagement/IntegrationServices/GenericFileProcessor!com.enhancesys.integration.services.interfaces.merger.CSVMerger', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184034, 1184, 'Validation Conf Delimiter', '%', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184035, 1184, 'File Data Batch Size', '5000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184036, 1184, 'Rejection File Query', 'select data_string_v, error_code, error_message from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184037, 1184, 'Field Lookup Conf', '{"process_enabled":true,"fields":{"actor_field":{"10":"CLUSTER","12":"PROGMOTER_ORG_ID"},"metric_field":"","source_field":"","instance_field":"CHECKIN_TYPE"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"table_name":"tr_temp_dssf_attendance_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_dssf_attendance_aggr","monthly_table":"tr_monthly_dssf_attendance_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"data_filter":[],"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_dssf_attendance_aggr","include-fileId":true,"field_indexes":[0,1,2,3]}}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184038, 1184, 'Incoming File Records Limit', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184039, 1184, 'PPK Path', '/home/appuser/snoc/snocconf/interfaceconf/credentials/TEST_251.ppk', NOW());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1184040, 1184, 'Cleanup Conf', '{"1001":{"cleanup-tables":{"temporary":true,"validation":true,"failure":false,"summary":false},"delete-mode":"normal","retension":100},"1002":{"cleanup-tables":{"temporary":true,"validation":true,"failure":false,"summary":false},"delete-mode":"with-in-batch","batch_size":500,"retension":100},"1003":{"cleanup-tables":{"temporary":true,"validation":true,"failure":true,"summary":true},"delete-mode":"with-batch","batch_size":1000,"retension":100},"1004":{"cleanup-tables":{"temporary":true,"validation":true,"failure":true,"summary":true},"delete-mode":"with-in-batch","batch_size":100,"retension":100},"default":"1001","is-enabled":false}', now());


--remote
--mkdir -p /home/appuser/interfaces/S-NOC/REMOTE/DAILY_DUMPS/master_files/dssf_attendance /
--mkdir -p /home/appuser/interfaces/S-NOC/REMOTE/DAILY_DUMPS/rejected_file/dssf_attendance /
--mkdir -p /home/appuser/interfaces/S-NOC/REMOTE/DAILY_DUMPS/backup/master_files/dssf_attendance /

--local
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/master_file/daily_dumps/dssf_attendance /
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/backup/master_file/daily_dumps/dssf_attendance /
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/rejected_file/daily_dumps/dssf_attendance /
--mkdir -p /home/appuser/interfaces/S-NOC/LOCAL/backup/rejected_file/daily_dumps/dssf_attendance /