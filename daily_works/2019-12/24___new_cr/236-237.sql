

--delete from INTERFACE.MS_INTERFACE_ATTR where interface_id_n in (1157,1158);
--delete from INTERFACE.MS_INTERFACE where interface_id_n in (1157,1158);
--delete from INTERFACE.MS_MODULE where module_id_n in (236,237);

-- ---------------------------------------------------------------236---------- ------------------------------------------------------------------------- ------------------------------------------------------------------------- -----------------------------------------------------------------------

INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (236, 'DAILY_DUMP - CSO Sales', null , '');

INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1157, 'DAILY_DUMP - CSO Sales', 236 , 2, 1, 1, 'java:global/EnhancesysManagement/IntegrationServices/OutletSalesDump!com.enhancesys.integration.services.interfaces.processor.PullDataToFile', '', NULL, now());

--Attributes for interface 1157 (DAILY_DUMP - CSO Sales)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157001, 1157, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157002, 1157, 'Remote Port', 'REMOTE_PORT',  now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157003, 1157, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157004, 1157, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157005, 1157, 'Remote File', 'CSO_Sales', now());                                                                             
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157006, 1157, 'Remote FileName Format', 'yyyyMMdd', now());                                                                          
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157007, 1157, 'Remote File Format', 'csv', now());                                                                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157008, 1157, 'Control file format', 'ctl', now());                                                                                        
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157009, 1157, 'Remote Dir', '/home/appuser/interfaces/DAILY_DUMPS/master_files/cso_sales/', now());                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157010, 1157, 'Remote control directory', '/home/appuser/interfaces/DAILY_DUMPS/master_files/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157011, 1157, 'Remote rejected directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157012, 1157, 'Remote rejected control directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157013, 1157, 'Remote backup directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157014, 1157, 'Remote Control Backup Directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157015, 1157, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157016, 1157, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157017, 1157, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157018, 1157, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157019, 1157, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157020, 1157, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157021, 1157, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157022, 1157, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/cso_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157023, 1157, 'Received File Type', 'Request File Type', now());                                                                           
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157024, 1157, 'Csv Delimeter', '|', now());                                                                                               
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157025, 1157, 'File Headers', 'Date|Region|HOR Name|Area|HOS Name|Sales Area|SAM Name|CSM Name|CSO Name|SSO|SSO 3+|Daily SSO|URO|URO  20K+|Daily URO|MOBO sellin >=50K', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157026, 1157, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157027, 1157, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1157028, 1157, 'Process Name', 'CSO_Sales_Dump', now());



-- ----------------------------------------------------------------237 ------------------------------------------------------------------------- ------------------------------------------------------------------------- -----------------------------------------------------------------------


INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (237, 'DAILY_DUMP - MPC Sales', null , '');

INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1158, 'DAILY_DUMP - MPC Sales', 237	, 2, 1, 1, 'java:global/EnhancesysManagement/IntegrationServices/OutletSalesDump!com.enhancesys.integration.services.interfaces.processor.PullDataToFile', '', NULL, now());

--Attributes for interface 1158 (DAILY_DUMP - MPC Sales)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158001, 1158, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158002, 1158, 'Remote Port', 'REMOTE_PORT',  now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158003, 1158, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158004, 1158, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158005, 1158, 'Remote File', 'MPC_Sales', now());                                                                             
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158006, 1158, 'Remote FileName Format', 'yyyyMMdd', now());                                                                          
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158007, 1158, 'Remote File Format', 'csv', now());                                                                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158008, 1158, 'Control file format', 'ctl', now());                                                                                        
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158009, 1158, 'Remote Dir', '/home/appuser/interfaces/DAILY_DUMPS/master_files/mpc_sales/', now());                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158010, 1158, 'Remote control directory', '/home/appuser/interfaces/DAILY_DUMPS/master_files/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158011, 1158, 'Remote rejected directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158012, 1158, 'Remote rejected control directory', '/home/appuser/interfaces/DAILY_DUMPS/rejected_file/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158013, 1158, 'Remote backup directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158014, 1158, 'Remote Control Backup Directory', '/home/appuser/interfaces/DAILY_DUMPS/backup/master_files/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158015, 1158, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158016, 1158, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158017, 1158, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158018, 1158, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158019, 1158, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158020, 1158, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158021, 1158, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158022, 1158, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/daily_dumps/mpc_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158023, 1158, 'Received File Type', 'Request File Type', now());                                                                           
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158024, 1158, 'Csv Delimeter', '|', now());                                                                                               
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158025, 1158, 'File Headers', 'Date|Region|HOR Name|Area|HOS Name|Sales Area|SAM Name|MPC code|MPC Name|Contact number|Total Child Outlets|Starter pack|Voucher|MOBO Balance', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158026, 1158, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158027, 1158, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1158028, 1158, 'Process Name', 'MPC_Sales_Dump', now());
