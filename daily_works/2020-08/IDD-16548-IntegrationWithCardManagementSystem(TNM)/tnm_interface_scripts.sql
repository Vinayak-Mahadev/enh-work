-- Prepared by Vinay(vinay.nagaraj@enhancesys.com)
-- Update for foss interface confiuration according to 03-IDD - 16548 - Integration with Card Management System (TNM)_V1_0.docx

--TNM ID         INTERFACE ID 	      NAME
--INTTNM001      1009	              INTFS003 - Product Creation & INTTNM001 - Product Dump
--INTTNM002      1001	              INTFS001A - Sales Allocation (Starter Pack - Stock Dump) & INTTNM001 - Product Dump
--INTNNM003      1003	              INTFS001B - Sales Allocation (Starter Pack - Alloc Dump) & INTTNM003 - Starterpack Allocation Dump
--INTTNM004      1005	              INTFS002A - Sales Allocation (Vouchers - Stock Dump) & INTTNM004 - Voucher Stock Dump
--INTTNM005      1007	              INTFS002B - Sales Allocation (Vouchers - Alloc Dump) & INTTNM005 - Voucher Allocation Dump
--INTTNM006      1011	              INTFS004 - Serail Number Expiry & INTTNM006 - Extend Expiry Date
--INTTNM007      1163	              Get Product Stock Availability API   
--INTTNM008      1164	              Reserve stock for a Dealer API       
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------





----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 101, interface id - 1001
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set name_v = 'INTFS001A - Sales Allocation (Starter Pack - Stock Dump) & INTTNM001 - Product Dump', description_v = 'Incoming Starter Pack Stock Dump File from FOSS or TNM (For TNM flag should be enabled in attribute table)' where module_id_n = 101;

update interface.ms_interface set name_v = 'INTFS001A - Sales Allocation (Starter Pack - Stock Dump) & INTTNM002 - Starterpack Stock Dump' where interface_id_n = 1001;

update interface.ms_interface_attr set value_v = 'SP_STOCK_DUMP' where attribute_id_n = 1001005;
update interface.ms_interface_attr set value_v = 'yyyyMMddHHmmss' where attribute_id_n = 1001006;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1001036;
update interface.ms_interface_attr set value_v = 'ICCID;M;R;[0-9]{20}+#MSISDN;M;R;[0-9]{1,20}#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DESTINATION_DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#STOCK_TRANSFER_DATE;M;RD;[0-9]{8};yyyyMMdd#PROGRAM_CODE;M;R;[0-9]+#PROGRAM_NAME;N#SOURCE_DEALER_ID;M' where attribute_id_n = 1001038;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 102, interface id - 1003
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set name_v = 'INTFS001B - Sales Allocation (Starter Pack - Alloc Dump) & INTTNM003 - Starterpack Allocation Dump', description_v = 'Incoming Starter Pack Alloc Dump File from FOSS or TNM (For TNM flag should be enabled in attribute table)' where module_id_n = 102;

update interface.ms_interface set name_v = 'INTFS001B - Sales Allocation (Starter Pack - Alloc Dump) & INTTNM003 - Starterpack Allocation Dump' where interface_id_n = 1003;

update interface.ms_interface_attr set value_v = 'SP_ALLOC_DUMP' where attribute_id_n = 1003005;
update interface.ms_interface_attr set value_v = 'yyyyMMddHHmmss' where attribute_id_n = 1003006;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1003036;
update interface.ms_interface_attr set value_v = 'ICCID;M;R;[0-9]{20}+#MSISDN;M;R;[0-9]{1,20}+#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#SO_CREATION_DATE;M;RD;[0-9]{8};yyyyMMdd#ALLOC_ID;M;R;[0-9]+#PROGRAM_CODE;M;R;[0-9]+#PROGRAM_NAME;M#TYPE;N;R;[0-9]+#ALLOC_DATE;M;RD;[0-9]{8};yyyyMMdd#PAYMENT_DATE;N;RD;[0-9]{8};yyyyMMdd' where attribute_id_n = 1003038;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 103, interface id - 1005
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set name_v = 'INTFS002A - Sales Allocation (Vouchers - Stock Dump) & INTTNM004 - Voucher Stock Dump', description_v = 'Incoming Vouchers Stock Dump File from FOSS or TNM (For TNM flag should be enabled in attribute table)' where module_id_n = 103;

update interface.ms_interface set name_v = 'INTFS002A - Sales Allocation (Vouchers - Stock Dump) & INTTNM004 - Voucher Stock Dump' where interface_id_n = 1005;

update interface.ms_interface_attr set value_v = 'VOUCHER_STOCK_DUMP' where attribute_id_n = 1005005;
update interface.ms_interface_attr set value_v = 'yyyyMMddHHmmss' where attribute_id_n = 1005006;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1005035;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N;R;[0-9]+#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#STOCK_TRANSFER_DATE;M;RD;[0-9]{8};yyyyMMdd#PROGRAM_CODE;M;R;[0-9]+#PROGRAM_NAME;N#SOURCE_DEALER_ID;M' where attribute_id_n = 1005037;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 104, interface id - 1007
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set name_v = 'INTFS002B - Sales Allocation (Vouchers - Alloc Dump) & INTTNM005 - Voucher Allocation Dump', description_v = 'Incoming Vouchers Alloc Dump File from FOSS or TNM (For TNM flag should be enabled in attribute table)' where module_id_n = 104;

update interface.ms_interface set name_v = 'INTFS002B - Sales Allocation (Vouchers - Alloc Dump) & INTTNM005 - Voucher Allocation Dump' where interface_id_n = 1007;

update interface.ms_interface_attr set value_v = 'VOUCHER_ALLOC_DUMP' where attribute_id_n = 1007005;
update interface.ms_interface_attr set value_v = 'yyyyMMddHHmmss' where attribute_id_n = 1007006;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1007035;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N;R;[0-9]+#BRANCH_CODE;N;#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#SO_CREATION_DATE;M;RD;[0-9]{8};yyyyMMdd#ALLOC_NO;M;R;[0-9]+#PROGRAM_CODE;M;R;[0-9]+#PROGRAM_NAME;M;#RESOURCE_TYPE;N;R;[0-9]+#ALLOC_DATE;M;RD;[0-9]{8};yyyyMMdd#PAYMENT_DATE;N;RD;[0-9]{8};yyyyMMdd' where attribute_id_n = 1007037;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 105, interface id - 1009
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set name_v = 'INTFS003 - Product Creation & INTTNM001 - Product Dump', description_v = 'Incoming Product Creation File from FOSS or TNM (For TNM flag should be enabled in attribute table)' where module_id_n = 105;

update interface.ms_interface set name_v = 'INTFS003 - Product Creation & INTTNM001 - Product Dump' where interface_id_n = 1009;

update interface.ms_interface_attr set value_v = 'yyyyMMddHHmmss' where attribute_id_n = 1009006;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1009036;
--update interface.ms_interface_attr set value_v = 'PRODUCT_CODE;M;R;[0-9]+%SAP_MATERIAL_CODE;M;R;[0-9]{1,10}%PRODUCT_NAME;M;R;[\sa-zA-Z0-9$_#-]{3,50}%PRODUCT_CATEGORY;M%PRODUCT_SUBCATEGORY;M%PRODUCT_START_DATE;M;RD;[0-9]{8};yyyyMMdd%PRODUCT_END_DATE;M;RD;[0-9]{8};yyyyMMdd%SALE_PRICE;M;R;[0-9]+(\\.[0-9][0-9]?)?%MRP_PRICE;M;R;[0-9]+(\\.[0-9][0-9]?)?%IS_SERIALIZED;M;R;[0-1]%SERIAL_TYPE;M;R;[0-1]%SERIAL_LENGTH;M;R;^(20|1[0-9]|0?[1-9])$%MIN_ORDER_QTY;N;R;[0-9]+%MIN_PACKAGE_QTY;N;R;[0-9]+' where attribute_id_n = 1009034;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 106, interface id - 1011
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set name_v = 'INTFS004 - Serail Number Expiry & INTTNM006 - Extend Expiry Date', description_v = 'Incoming Serail Number Expiry File from FOSS or TNM (For TNM flag should be enabled in attribute table)' where module_id_n = 106;

update interface.ms_interface set name_v = 'INTFS004 - Serail Number Expiry & INTTNM006 - Extend Expiry Date' where interface_id_n = 1011;

update interface.ms_interface_attr set value_v = 'EXPIRY_DETAILS' where attribute_id_n = 1011005;
update interface.ms_interface_attr set value_v = 'yyyyMMddHHmmss' where attribute_id_n = 1011006;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1011034;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER_SIM;M;R;[0-9]+|NEW_PRODUCT_CODE;M|NEW_EXPIRY_DATE;M;RD;[0-9]{8};yyyy-MM-dd' where attribute_id_n = 1011036;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 241, interface id - 1163
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set description_v = 'Get Product Stock Availability API - Invoking REST API with GET method service with requested data' where module_id_n = 241;

update interface.ms_interface_attr set value_v = 'http://{tnm-server}/mobii/stock' where attribute_id_n = 1163001;
update interface.ms_interface_attr set value_v = 'USERNAME' where attribute_id_n = 1163002;
update interface.ms_interface_attr set value_v = 'PASSWORD' where attribute_id_n = 1163003;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1163004;
update interface.ms_interface_attr set value_v = '60000' where attribute_id_n = 1163005;
update interface.ms_interface_attr set value_v = '90000' where attribute_id_n = 1163006;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--module id 242, interface id - 1164
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
update interface.ms_module set description_v = 'Reserve stock for a Dealer API - Invoking REST API with POST method service with requested data' where module_id_n = 242;

update interface.ms_interface_attr set value_v = 'http://{tnm-server}/mobi/stock/resv' where attribute_id_n = 1164001;
update interface.ms_interface_attr set value_v = 'USERNAME' where attribute_id_n = 1164002;
update interface.ms_interface_attr set value_v = 'PASSWORD' where attribute_id_n = 1164003;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1164004;
update interface.ms_interface_attr set value_v = '60000' where attribute_id_n = 1164005;
update interface.ms_interface_attr set value_v = '90000' where attribute_id_n = 1164006;
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



