--select * from interface.tr_interface_summary where interface_id_n = 1190;
delete from interface.tr_interface_summary where interface_id_n = 1190;
delete from interface.ms_interface_attr where interface_id_n = 1190;
delete from interface.ms_interface where interface_id_n = 1190;
delete from interface.ms_module where module_id_n = 268;


INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (268, 'Shopping cart creation', null , null);
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1190, 'Shopping cart creation', 268, 3, 1, 1, 'java:global/EnhancesysManagement/IntegrationServices/JsonXmlDataConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, now());

INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190001, 1190, 'isEnabled', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190002, 1190, 'Remote ws Url', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190003, 1190, 'Remote ws user name', 'admin', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190004, 1190, 'Remote ws user password', 'admin#123', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190005, 1190, 'Service Type', 'SOAP', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190006, 1190, 'HTTP Connection Timeout', '60000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190007, 1190, 'HTTP Read Timeout', '90000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190008, 1190, 'Xml Data Converter', 'java:global/EnhancesysManagement/IntegrationServices/BeanXmlDataConverter!com.enhancesys.integration.services.interfaces.converter.BeanDataConverter', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190009, 1190, 'Maintain Transactions Conf', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190010, 1190, 'Interface Summary Refdata Conf', '{"isEnabled":true,"refData1":"Payout to","refData2":"Payout Id","refData3":"Request Id","refData4":"","refData5":"","refData6":"","refData7":"","refData8":""}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1190011, 1190, 'Static Response Conf', '{"enabled":true,"file-enabled":true,"url":"http://interface.enhancesys.com:8080/interfaceResponseServices/webservices/InterfaceResponseServicesImpl?wsdl","res_type":"Success","replace-elements":{}}', now());

