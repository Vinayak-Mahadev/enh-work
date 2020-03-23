


-- FOR PRODUCTION



DELETE FROM INTERFACE.MS_INTERFACE_ATTR WHERE INTERFACE_ID_N = 1162;
--DELETE FROM INTERFACE.MS_INTERFACE WHERE INTERFACE_ID_N = 1162;
--DELETE FROM INTERFACE.MS_MODULE WHERE MODULE_ID_N = 240;
UPDATE INTERFACE.MS_INTERFACE SET CONVERTER_V = 'java:global/EnhancesysManagement/IntegrationServices/GenericDailyDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo' WHERE INTERFACE_ID_N = 1162;



--INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (240, 'DAILY_DUMP - PJP Planned Calls Dump', null , '');

--INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1162, 'PJP Planned Calls Dump', 240, 2, 1, 1,'java:global/EnhancesysManagement/IntegrationServices/GenericDailyDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo','','',now());

-- PJP Planned Calls Dump (1162)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162001, 1162, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162002, 1162, 'Remote Port', 'REMOTE_PORT', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162003, 1162, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162004, 1162, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162005, 1162, 'Remote File', 'Pjp_Planned_Calls', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162006, 1162, 'Remote FileName Format', 'yyyyMMdd', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162007, 1162, 'Remote File Format', 'csv', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162008, 1162, 'Control file format', 'ctl', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162009, 1162, 'Remote Dir', '/home/appuser/interfaces/DAILY_DUMPS/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162010, 1162, 'Remote control directory', '/home/appuser/interfaces/DAILY_DUMPS/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162011, 1162, 'Remote rejected directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162012, 1162, 'Remote rejected control directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162013, 1162, 'Remote backup directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162014, 1162, 'Remote Control Backup Directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162015, 1162, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162016, 1162, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162017, 1162, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162018, 1162, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162019, 1162, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162020, 1162, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162021, 1162, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162022, 1162, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162023, 1162, 'Csv Delimeter', '|', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162024, 1162, 'File Headers', 'Region|Area|Sales Area|Cluster|Micro Cluster|MPC Code|SPV Code|SPV Name|CSO Code|CSO Name|PJP Plan Id|PJP Plan name|PJP start date|PJP end date|PJP created by|PJP creation date|PJP Frequency|PJP Modify Date|PJP Status|Outlet Code|Plan Date|Plan Day', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162025, 1162, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162026, 1162, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162027, 1162, 'Schema Name', 'daily_dump', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162028, 1162, 'Collection Name', 'beat_schedule_dump', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162029, 1162, 'Daily Dump Conf', '{"region":"regional_desc","area":"area_desc","sales area":"sales_area_desc","cluster":"cluster_desc","micro cluster":"micro_cluster_desc","mpc code":"mpc_code","spv code":"spv_code","spv name":"spv_name","cso code":"cso_code","cso name":"cso_name","pjp plan id":"beat_id","pjp plan name":"beat_name","pjp start date":"beat_st_dt","pjp end date":"beat_ed_dt","pjp created by":"beat_crtd_by","pjp creation date":"beat_crtd_dt","pjp frequency":"beat_frequency","pjp modify date":"beat_updtd_dt","pjp status":"beat_status","outlet code":"org_ref_code","plan date":"plan_dt","plan day":"plan_day"}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162030, 1162, 'Query Field', 'dump_crtd_dt_n', now());
------------------------------------------ FOR LOCAL DEVELOPMENT

DELETE FROM INTERFACE.MS_INTERFACE_ATTR WHERE INTERFACE_ID_N = 1162;
--DELETE FROM INTERFACE.MS_INTERFACE WHERE INTERFACE_ID_N = 1162;
--DELETE FROM INTERFACE.MS_MODULE WHERE MODULE_ID_N = 240;
UPDATE INTERFACE.MS_INTERFACE SET CONVERTER_V = 'java:global/EnhancesysManagement/IntegrationServices/GenericDailyDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo' WHERE INTERFACE_ID_N = 1162;



-- INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (240, 'DAILY_DUMP - PJP Planned Calls Dump', null , '');

-- INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1162, 'PJP Planned Calls Dump', 240, 2, 1, 1,'java:global/EnhancesysManagement/IntegrationServices/GenericDailyDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo','','',now());



INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162001, 1162, 'Remote Host', '192.168.2.251', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162002, 1162, 'Remote Port', '22', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162003, 1162, 'Remote User', 'appuser', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162004, 1162, 'Remote Password', 'app@123', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162005, 1162, 'Remote File', 'Pjp_Planned_Calls', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162006, 1162, 'Remote FileName Format', 'yyyyMMdd', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162007, 1162, 'Remote File Format', 'csv', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162008, 1162, 'Control file format', 'ctl', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162009, 1162, 'Remote Dir', '/home/appuser/interfaces/DAILY_DUMPS/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162010, 1162, 'Remote control directory', '/home/appuser/interfaces/DAILY_DUMPS/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162011, 1162, 'Remote rejected directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162012, 1162, 'Remote rejected control directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162013, 1162, 'Remote backup directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162014, 1162, 'Remote Control Backup Directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162015, 1162, 'Local Dir', '/home/appuser/interfaces/S-NOC/interfaces/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162016, 1162, 'Local control directory', '/home/appuser/interfaces/S-NOC/interfaces/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162017, 1162, 'Local backup directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162018, 1162, 'Local Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162019, 1162, 'Local rejected directory', '/home/appuser/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162020, 1162, 'Local rejected control directory', '/home/appuser/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162021, 1162, 'Local rejected backup directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162022, 1162, 'Local rejected control backup directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/pjp_planned_calls/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162023, 1162, 'Csv Delimeter', '|', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162024, 1162, 'File Headers', 'Region|Area|Sales Area|Cluster|Micro Cluster|MPC Code|SPV Code|SPV Name|CSO Code|CSO Name|PJP Plan Id|PJP Plan name|PJP start date|PJP end date|PJP created by|PJP creation date|PJP Frequency|PJP Modify Date|PJP Status|Outlet Code|Plan Date|Plan Day', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162025, 1162, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162026, 1162, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162027, 1162, 'Schema Name', 'daily_dump', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162028, 1162, 'Collection Name', 'beat_schedule_dump', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162030, 1162, 'Query Field', 'dump_crtd_dt_n', now());


