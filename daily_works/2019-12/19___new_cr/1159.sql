INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (238, 'INTDWH020 – Quality of Acquisition', null , 'Incoming Quality of Acquisition File from DWH');


INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1159, 'INTDWH020 – Quality of Acquisition', 238, 2, 2, 1, 'java:global/EnhancesysManagement/IntegrationServices/CSVtoJSONConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, '2017-11-29 12:21:21.721+05:30');



-- Attributes for interface 1159 (INTDWH020 – Quality of Acquisition - Receive file)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159001, 1159, 'Remote Host', 'REMOTE_HOST', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159002, 1159, 'Remote Port', 'REMOTE_PORT', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159003, 1159, 'Remote User', 'REMOTE_USER', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159004, 1159, 'Remote Password', 'REMOTE_PWD', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159005, 1159, 'Remote File', 'quality_of_acquisition', now());                                 
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159006, 1159, 'Remote FileName Format', 'yyyyMMdd', now());                    
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159007, 1159, 'Remote File Format', 'csv', now());                                   
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159008, 1159, 'Control file format', 'ctl', now());                                  
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159009, 1159, 'Remote Dir', '/home/appuser/interfaces/DWH/master_files/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159010, 1159, 'Remote control directory', '/home/appuser/interfaces/DWH/master_files/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159011, 1159, 'Remote rejected directory', '/home/appuser/interfaces/DWH/rejected_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159012, 1159, 'Remote rejected control directory', '/home/appuser/interfaces/DWH/rejected_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159013, 1159, 'Remote backup directory', '/home/appuser/interfaces/DWH/backup/master_files/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159014, 1159, 'Remote Control Backup Directory', '/home/appuser/interfaces/DWH/backup/master_files/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159015, 1159, 'Local Dir', '/snoc/interfaces/S-NOC/interfaces/master_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159016, 1159, 'Local control directory', '/snoc/interfaces/S-NOC/interfaces/master_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159017, 1159, 'Local backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159018, 1159, 'Local Control Backup Directory', '/snoc/interfaces/S-NOC/interfaces/backup/master_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159019, 1159, 'Local rejected directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159020, 1159, 'Local rejected control directory', '/snoc/interfaces/S-NOC/interfaces/rejected_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159021, 1159, 'Local rejected backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159022, 1159, 'Local rejected control backup directory', '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/quality_of_acquisition/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159023, 1159, 'Received File Type', 'Request File Type', now());                     
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159024, 1159, 'Csv Delimeter', '\|', now());                                         
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159025, 1159, 'Rejection File Headers', 'MSISDN|ACTIVATION_DATE|LAC|CI|ERRORCODE|ERRORMESSSAGE', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159026, 1159, 'Rejection File Preparation Class', 'java:global/EnhancesysManagement/IntegrationServices/DWHFeedsRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159027, 1159, 'Client Id', '0', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159028, 1159, 'Is Sftp', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159029, 1159, 'File Headers', 'MSISDN|ACTIVATION_DATE|LAC|CI', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159030, 1159, 'Field Validation Conf', 'MSISDN;M;R;[0-9]+#ACTIVATION_DATE;M;RD;[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2};yyyy-MM-dd HH:mm:ss#LAC;N#CI;N', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159031, 1159, 'CSV MERGER', 'java:global/EnhancesysManagement/IntegrationServices/ActivationFeedMerger!com.enhancesys.integration.services.interfaces.merger.CSVMerger', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159032, 1159, 'Publisher Interface', '1014', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159033, 1159, 'Validation Conf Delimiter', '#', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159034, 1159, 'Mongo Config', '{ "replica": "R1", "schema": "interface", "collection": "qoa_data", "batch_size": "100", "projection_obj": {"data_string":1,"err_code":1,"err_msg":1} }', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159035, 1159, 'Service Batch Size', '1000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1159036, 1159, 'Incoming File Records Limit', '', now());

