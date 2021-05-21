delete from interface.tr_interface_summary where interface_id_n in (1001);
delete from interface.ms_interface_attr where interface_id_n in (1001);
delete from interface.ms_interface where interface_id_n in (1001);
delete from interface.ms_module where module_id_n in (101);


INSERT INTO INTERFACE.MS_MODULE(MODULE_ID_N, NAME_V, CALL_BACK_V, DESCRIPTION_V) VALUES  (101, 'INTSRM001-Shopping Cart Creation', null , null);
INSERT INTO INTERFACE.MS_INTERFACE (INTERFACE_ID_N, NAME_V, MODULE_ID_N, INTERFACE_TYPE_N, TRANS_TYPE_N, SEQ_N, CONVERTER_V, PUBLISHER_V, RESPONSE_PROCESSOR_V, LAST_UPDATED_TIME_DT) VALUES (1001, 'INTSRM001-Shopping Cart Creation', 268, 3, 1, 1, 'java:global/EnhancesysManagement/IntegrationServices/BeanXmlDataConverter!com.enhancesys.integration.services.interfaces.converter.DataConverter', 'java:global/EnhancesysManagement/IntegrationPublishers/WebServicePublisher!com.enhancesys.integration.services.interfaces.publisher.DataPublisher', NULL, now());

INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001001, 1190, 'isEnabled', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001002, 1190, 'Remote ws Url', '', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001003, 1190, 'Remote ws user name', 'admin', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001004, 1190, 'Remote ws user password', 'admin#123', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001005, 1190, 'Service Type', 'SOAP', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001006, 1190, 'HTTP Connection Timeout', '60000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001007, 1190, 'HTTP Read Timeout', '90000', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001008, 1190, 'Xml Data Converter', 'java:global/EnhancesysManagement/IntegrationServices/BeanDataConverterImpl!com.enhancesys.integration.services.interfaces.converter.BeanDataConverter', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001009, 1190, 'Maintain Transactions Conf', 'true', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001010, 1190, 'Interface Summary Refdata Conf', '{"isEnabled":true,"refData1":"payout_type","refData2":"payout_id","refData3":"request_id","refData4":"sc_number","refData5":"","refData6":"","refData7":"","refData8":""}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001011, 1190, 'Static Response Conf', '{"enabled":true,"file-enabled":true,"url":"http://interface.enhancesys.com:8080/interfaceResponseServices/webservices/InterfaceResponseServicesImpl?wsdl","res_type":"Success","replace-elements":{}}', now());
