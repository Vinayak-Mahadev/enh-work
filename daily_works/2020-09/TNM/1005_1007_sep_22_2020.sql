-- Prepared by Vinay(vinay.nagaraj@enhancesys.com)

--1003 (Field Validation Conf)
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N;R;[0-9]+#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#STOCK_TRANSFER_DATE;M;D;yyyyMMdd#PROGRAM_CODE;M#PROGRAM_NAME;N#SOURCE DEALER ID;M' where attribute_id_n=1005030;
--1005 (Field Validation Conf)
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#SO_CREATION_DATE;M;D;yyyyMMdd#ALLOC_NO;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#RESOURCE_TYPE;N#ALLOC_DATE;M;D;dd-MMM-yy#PAYMENT_DATE;N;D;dd-MMM-yy' where attribute_id_n=1007030;
