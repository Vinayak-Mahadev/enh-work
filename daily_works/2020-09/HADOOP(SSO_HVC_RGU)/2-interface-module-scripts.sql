-- Prepared by Vinay(vinay.nagaraj@enhancesys.com)

-- Online Interfaces Summary -> IMTSM026 - Transfer Balance screen
ALTER TABLE interface.tr_interface_summary 
 ADD COLUMN ref_data6_v CHARACTER VARYING(255),
 ADD COLUMN ref_data7_v CHARACTER VARYING(255),
 ADD COLUMN ref_data8_v CHARACTER VARYING(255);

create index interface_summary_ref_data6_v_index on interface.tr_interface_summary using btree(ref_data6_v); 
create index interface_summary_ref_data7_v_index on interface.tr_interface_summary using btree(ref_data7_v); 
create index interface_summary_ref_data8_v_index on interface.tr_interface_summary using btree(ref_data8_v); 


--Update For 1169-RGU-GA WITH INJECTION (INTHDP005)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE|CLUSTER|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN|ERRORCODE|ERRORMESSAGE' WHERE ATTRIBUTE_ID_N = 1169027;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE|CLUSTER|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN' WHERE ATTRIBUTE_ID_N=1169031;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%CLUSTER;M;R;%MICRO;M;R;%SITE_ID;M;R;%ID_OUTLET;N;R;%STATUS_INJECTION;M;R;%FLAG_ACM;M;R;%COUNT_MSISDN;M;R;[0-9]*' WHERE ATTRIBUTE_ID_N=1169032;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":{"10":"ID_OUTLET","12":"SITE_ID","13":"CLUSTER"},"metric_field":"FLAG_ACM","source_field":"MICRO","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"table_name":"tr_temp_rguga_injection_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_rguga_injection_aggr","monthly_table":"tr_monthly_rguga_injection_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_rguga_injection_aggr","field_indexes":[0,1,2,3,4,5,6],"include-fileId":true}}' WHERE ATTRIBUTE_ID_N=1169037;

-- SIM Selling Outlet HVC Event Configuration:

-- MS_MODULE
INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, LAST_UPDATED_TIME_DT, DESCRIPTION_V) VALUES (261, 'INTHDP020 - SIM Selling Outlet with High Value Customer', NULL, NOW(), 'Incoming Outlet SP Tagging file from Hadoop feed');

-- MS_INTERFACE
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1183, 'INTHDP020 - SIM Selling Outlet with High Value Customer', 261, 2, 2, 1, 'java:global/EnhancesysManagement/IntegrationServices/CSVtoJSONConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, now());

-- MS_INTERFACE_ATTR
-- Attributes for interface 1183 (Outlet SP Tagging - Receive file)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183001, 1183, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183002, 1183, 'Remote Port', 'REMOTE_PORT', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183003, 1183, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183004, 1183, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183005, 1183, 'Remote File', 'sso_hvc_rgu', now());                                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183006, 1183, 'Remote FileName Format', 'yyyyMMddHHmmss', now());                    
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183007, 1183, 'Remote File Format', 'csv', now());                                   
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183008, 1183, 'Control file format', 'ctl', now());                                  
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183009, 1183, 'Remote Dir', '/home/appuser/interfaces/HADOOP/master_files/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183010, 1183, 'Remote control directory', '/home/appuser/interfaces/HADOOP/master_files/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183011, 1183, 'Remote rejected directory', '/home/appuser/interfaces/HADOOP/rejected_file/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183012, 1183, 'Remote rejected control directory', '/home/appuser/interfaces/HADOOP/rejected_file/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183013, 1183, 'Remote backup directory', '/home/appuser/interfaces/HADOOP/backup/master_files/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183014, 1183, 'Remote Control Backup Directory', '/home/appuser/interfaces/HADOOP/backup/master_files/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183015, 1183, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183016, 1183, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183017, 1183, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183018, 1183, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183019, 1183, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183020, 1183, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183021, 1183, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183022, 1183, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183023, 1183, 'Local Filter Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183024, 1183, 'Local Filter Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/sso_hvc_rgu/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183025, 1183, 'Received File Type', 'Request File Type', now());                     
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183026, 1183, 'Csv Delimeter', '\|', now());                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183027, 1183, 'Rejection File Headers', 'DATE|OUTLET_ID|SAME_MC_RGU_QTY|SAME_CL_RGU_QTY|ALL_CL_RGU_QTY|ERRORCODE|ERRORMESSAGE', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183028, 1183, 'Rejection File Preparation Class', 'java:global/EnhancesysManagement/IntegrationServices/KpiMasterRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183029, 1183, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183030, 1183, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183031, 1183, 'File Headers', 'DATE|OUTLET_ID|SAME_MC_RGU_QTY|SAME_CL_RGU_QTY|ALL_CL_RGU_QTY', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183032, 1183, 'Field Validation Conf', 'DATE;M;RD;[0-9]*;yyyyMMdd%OUTLET_ID;M;R;%SAME_MC_RGU_QTY;M;R;[0-9]+%SAME_CL_RGU_QTY;M;R;[0-9]+%ALL_CL_RGU_QTY;M;R;[0-9]+', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183033, 1183, 'CSV MERGER', 'java:global/EnhancesysManagement/IntegrationServices/GenericFileProcessor!com.enhancesys.integration.services.interfaces.merger.CSVMerger', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183034, 1183, 'Validation Conf Delimiter', '%', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183035, 1183, 'File Data Batch Size', '5000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183036, 1183, 'Rejection File Query', 'select data_string_v, error_code, error_message from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183037, 1183, 'Field Lookup Conf', '{"process_enabled":true,"fields":{"actor_field":"OUTLET_ID","metric_field":"","source_field":"","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily","3":"cleanup"},"table_name":"tr_temp_sso_hvc_aggr","lookup_query":"","notexist_lookup":"","no_of_params":{},"daily_table":"tr_daily_sso_hvc_aggr","monthly_table":"tr_monthly_sso_hvc_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":3,"no_of_monthly_join_queries":3,"duplicate_validation_conf":{"field_indexes":[0,1],"schema_name":"kpi","table_name":"tr_validate_sso_hvc_aggr","include-fileId":true},"data_filter":[]}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183038, 1183, 'Incoming File Records Limit', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1183039, 1183, 'PPK Path', '/home/appuser/snoc/snocconf/interfaceconf/credentials/TEST_251.ppk', NOW());

-- MS_INTERFACE_NOTIFICATION
-- Email Notification Attributes for interface -->INTHDP019 - Outlet SP Tagging
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1183001, 1183, 'FILE_REJECTED_CTL_0', 'email', 'int_md5_hash_key_failed', 'FILE REJECTED - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1183002, 1183, 'FILE_NOT_FOUND_0', 'email', 'int_file_not_found', 'FILE NOT FOUND - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1183003, 1183, 'FILE_REJECTED_PART_0', 'email', 'int_file_rejected_partially', 'FILE REJECTED PARTIALLY - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1183004, 1183, 'FILE_REJECTED_FULL_0', 'email', 'int_file_rejected_fully', 'FILE REJECTED FULLY - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1183005, 1183, 'FILE_SUCCESS_0', 'email', 'int_file_processed_sucessfully', 'FILE SUCCESSFULLY PROCESSED - INTHDP017 - URO 20K', 'Support Team', '', '', '', 2, NOW(),'en');
INSERT INTO INTERFACE.MS_INTERFACE_NOTIFICATION (INTERFACE_NOTIFICATION_ID_N, INTERFACE_ID_N, SCENARIO_V, MEDIA_V, TEMPLATE_NAME_V, SUBJECT_V, TO_FIRST_NAME_V, TO_ADDRESS_V, TO_CC_V, TO_BCC_V, STATUS_N, LAST_UPDATED_TIME_DT,LANGUAGE_V) VALUES (1183006, 1183, 'CONNECTION_REFUSED_0', 'email', 'int_connection_refused', 'CONNECTION REFUSED - INTHDP017 - URO 20K', 'Support Team', '', '', '', 1, NOW(),'en');
