delete from INTERFACE.MS_INTERFACE_ATTR where interface_id_n = 1162;
delete from INTERFACE.MS_INTERFACE where interface_id_n = 1162;
delete from INTERFACE.MS_MODULE where MODULE_ID_N = 240;


INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (240, 'DAILY_DUMP - PJP Planning Details Dump', null , '');

INSERT INTO INTERFACE.MS_INTERFACE values(1162,'PJP_PLAN_DTLS_TEST',240,2,1,1,'java:global/EnhancesysManagement/IntegrationServices/PjpPlanDetailsDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo','','',now());


INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162001, 1162, 'Remote Host', '192.168.2.251', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162002, 1162, 'Remote Port', '22', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162003, 1162, 'Remote User', 'appuser', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162004, 1162, 'Remote Password', 'app@123', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162005, 1162, 'Remote File', 'PJP_Planning_Details', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162006, 1162, 'Remote FileName Format', 'yyyyMMdd', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162007, 1162, 'Remote File Format', 'csv', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162008, 1162, 'Control file format', 'ctl', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162009, 1162, 'Remote Dir', '/home/appuser/interfaces/DAILY_DUMPS/master_files/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162010, 1162, 'Remote control directory', '/home/appuser/interfaces/DAILY_DUMPS/master_files/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162011, 1162, 'Remote rejected directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162012, 1162, 'Remote rejected control directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162013, 1162, 'Remote backup directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162014, 1162, 'Remote Control Backup Directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162015, 1162, 'Local Dir', '/home/appuser/interfaces/S-NOC/interfaces/master_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162016, 1162, 'Local control directory', '/home/appuser/interfaces/S-NOC/interfaces/master_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162017, 1162, 'Local backup directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162018, 1162, 'Local Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162019, 1162, 'Local rejected directory', '/home/appuser/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162020, 1162, 'Local rejected control directory', '/home/appuser/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162021, 1162, 'Local rejected backup directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162022, 1162, 'Local rejected control backup directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/pjp_planning_details/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162023, 1162, 'Received File Type', 'Request File Type', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162024, 1162, 'Csv Delimeter', '|', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162025, 1162, 'File Headers', 'Region|Area|Sales Area|Cluster|Micro Cluster|MPC Code|SPV Code|SPV Name|CSO Code|CSO Name|PJP Plan Id|PJP Plan name|PJP start date|PJP end date|PJP created by|PJP creation date|PJP Frequency|PJP Modify Date|PJP Status|Outlet Code|Total Planned calls|Planned Days', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162026, 1162, 'Mongo Connection Id', 'snoc-r1', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162027, 1162, 'Processor Url', 'java:global/EnhancesysManagement/IntegrationServices/PjpPlanDetailsDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1162028, 1162, 'Process Name', 'PJP_Planning_Details', now());
