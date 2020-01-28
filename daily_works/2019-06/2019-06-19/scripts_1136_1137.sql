INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1136009, 1136, 'Static Response Conf', '{"enabled":true,"res_type":"Success"}', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1137010, 1137, 'Static Response Conf', '{"enabled":true,"res_type":"Success"}', now());




UPDATE INTERFACE.MS_INTERFACE_ATTR SET NAME_V = 'Static Response Conf',VALUE_V = '{"enabled":true,"res_type":"Success"}' WHERE ATTRIBUTE_ID_N = 1136009;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET NAME_V = 'Static Response Conf',VALUE_V = '{"enabled":true,"res_type":"Success"}' WHERE ATTRIBUTE_ID_N = 1137010;



