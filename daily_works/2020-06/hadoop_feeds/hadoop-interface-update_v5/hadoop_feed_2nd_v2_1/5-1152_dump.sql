-- 1152 (INTHDP018 - STOCK TAKING ACTIVITY)

--DELETE FROM INTERFACE.TR_DAILY_DUMP_SUMMARY WHERE INTERFACE_ID_N =1152;
--DELETE FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY_DETAILS WHERE FILE_ID_N IN (SELECT FILE_ID_N FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY WHERE INTERFACE_ID_N IN (1152));
--DELETE FROM INTERFACE.TR_INTERFACE_FILE_SUMMARY WHERE INTERFACE_ID_N IN (1152);
--DELETE FROM INTERFACE.MS_INTERFACE_NOTIFICATION WHERE INTERFACE_ID_N IN (1152);
--DELETE FROM INTERFACE.MS_INTERFACE_ATTR WHERE INTERFACE_ID_N IN (1152);
--DELETE FROM INTERFACE.MS_INTERFACE WHERE INTERFACE_ID_N IN (1152);
--DELETE FROM INTERFACE.MS_MODULE WHERE MODULE_ID_N IN (231);

INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (231, 'INTHDP018 - STOCK TAKING ACTIVITY', null , '');
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1152, 'INTHDP018 - STOCK TAKING ACTIVITY', 231, 2, 1, 1, 'java:global/EnhancesysManagement/IntegrationServices/GenericDataProcessor!com.enhancesys.integration.services.interfaces.processor.PullFromMongo', '', NULL, now());

--Attributes for interface 1152 (INTHDP018 - STOCK TAKING ACTIVITY)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152001, 1152, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152002, 1152, 'Remote Port', 'REMOTE_PORT', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152003, 1152, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152004, 1152, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152005, 1152, 'Remote File', 'stock_taking_activity', now());                                                                             
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152006, 1152, 'Remote FileName Format', 'yyyyMMddHHmmss', now());                                                                          
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152007, 1152, 'Remote File Format', 'csv', now());                                                                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152008, 1152, 'Control file format', 'ctl', now());                                                                                        
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152009, 1152, 'Remote Dir', '/data/02/landing/snd/outlet_stock_balance/', now());                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152010, 1152, 'Remote control directory', '/data/02/landing/snd/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152011, 1152, 'Remote rejected directory', '/data/02/landing/snd/rejected_file/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152012, 1152, 'Remote rejected control directory', '/data/02/landing/snd/rejected_file/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152013, 1152, 'Remote backup directory', '/data/02/landing/snd/backup/master_files/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152014, 1152, 'Remote Control Backup Directory', '/data/02/landing/snd/backup/master_files/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152015, 1152, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152016, 1152, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152017, 1152, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152018, 1152, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152019, 1152, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152020, 1152, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152021, 1152, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152022, 1152, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/filter_file/HADOOP/outlet_stock_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152023, 1152, 'Received File Type', 'Request File Type', now());                                                                           
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152024, 1152, 'Csv Delimeter', '|', now());                                                                                               
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152025, 1152, 'File Headers', 'DATE|CHANNEL_CATEGORY|REGIONAL|AREA|SALES_AREA|CLUSTER|MICRO_CLUSTER|SALE_TERRITORY|TERRITORY_ID|OUTLET_ID|OUTLET_REF_CODE|OUTLET_NAME|CSO_NAME|CSO_OPERATOR_ID|CSO_ORG_ID|CSO_ORG_REF_CODE|CSO_ORG_NAME|VOUCHER_BALANCE|MULTI_CHIP_BALANCE', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152026, 1152, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152027, 1152, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152028, 1152, 'Schema Name', 'beat', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152029, 1152, 'Collection Name', 'stock_taking', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152030, 1152, 'Daily Dump Conf', '{"date":"transaction_date","channel_category":"channel_category","regional":"region","area":"area","sales_area":"sales_area","cluster":"cluster","micro_cluster":"micro_cluster","sale_territory":"sale_territory","territory_id":"territory_id","outlet_id":"outlet_org_id","outlet_ref_code":"outlet_ref_code","outlet_name":"outlet_name","cso_name":"cso_name","cso_operator_id":"cso_operator_id","cso_org_id":"cso_org_id","cso_org_ref_code":"cso_org_ref_code","cso_org_name":"cso_org_name","voucher_balance":"voucher_bal","multi_chip_balance":"multi_chip_bal"}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152031, 1152, 'Query Field', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152032, 1152, 'Date Duration', '1', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152033, 1152, 'Process Name', 'Stock_Taking_Dump', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152034, 1152, 'Mongo Connection Id', 'snoc-r5', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152035, 1152, 'Processor Url', 'java:global/EnhancesysManagement/IntegrationServices/GenericDailyDump!com.enhancesys.integration.services.interfaces.processor.PullFromMongo', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1152036, 1152, 'PPK Path', '/home/appuser/snoc/snocconf/interfaceconf/credentials/TEST_251.ppk', NOW());
