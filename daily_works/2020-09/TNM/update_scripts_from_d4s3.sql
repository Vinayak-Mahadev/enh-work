
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ?;
--1001
update interface.ms_interface_attr set value_v = 'REMOTE_HOST' where attribute_id_n = 1001001;
update interface.ms_interface_attr set value_v = 'REMOTE_PORT' where attribute_id_n = 1001002;
update interface.ms_interface_attr set value_v = 'REMOTE_USER' where attribute_id_n = 1001003;
update interface.ms_interface_attr set value_v = 'REMOTE_PWD' where attribute_id_n = 1001004;
update interface.ms_interface_attr set value_v = 'STOCK_DUMP' where attribute_id_n = 1001005;
update interface.ms_interface_attr set value_v = 'yyyyMMdd' where attribute_id_n = 1001006;
update interface.ms_interface_attr set value_v = 'csv' where attribute_id_n = 1001007;
update interface.ms_interface_attr set value_v = 'ctl' where attribute_id_n = 1001008;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/sp_dump/' where attribute_id_n = 1001009;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/sp_dump/' where attribute_id_n = 1001010;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/sp_dump/' where attribute_id_n = 1001011;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/sp_dump/' where attribute_id_n = 1001012;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/sp_dump/' where attribute_id_n = 1001013;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/sp_dump/' where attribute_id_n = 1001014;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/sp_dump/' where attribute_id_n = 1001015;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/sp_dump/' where attribute_id_n = 1001016;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/sp_dump/' where attribute_id_n = 1001017;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/sp_dump/' where attribute_id_n = 1001018;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/sp_dump/' where attribute_id_n = 1001019;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/sp_dump/' where attribute_id_n = 1001020;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/sp_dump/' where attribute_id_n = 1001021;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/sp_dump/' where attribute_id_n = 1001022;
update interface.ms_interface_attr set value_v = 'Request File Type' where attribute_id_n = 1001023;
update interface.ms_interface_attr set value_v = '\|' where attribute_id_n = 1001024;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DESTINATION DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE DEALER ID|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1001025;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/FOSSRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation' where attribute_id_n = 1001026;
update interface.ms_interface_attr set value_v = '0' where attribute_id_n = 1001027;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1001028;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DESTINATION DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE DEALER ID' where attribute_id_n = 1001029;
update interface.ms_interface_attr set value_v = 'ICCID;M;R;[0-9]{20}+#MSISDN;M;R;[0-9]{1,20}+#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DESTINATION DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#STOCK_TRANSFER_DATE;M;D;yyyyMMdd#PROGRAM_CODE;M#PROGRAM_NAME;N#SOURCE DEALER ID;M' where attribute_id_n = 1001030;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/SPStockDumpMerger!com.enhancesys.integration.services.interfaces.merger.CSVMerger' where attribute_id_n = 1001031;
update interface.ms_interface_attr set value_v = '1002' where attribute_id_n = 1001032;
update interface.ms_interface_attr set value_v = '#' where attribute_id_n = 1001033;
update interface.ms_interface_attr set value_v = '5000' where attribute_id_n = 1001034;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1001035;
update interface.ms_interface_attr set value_v = 'false' where attribute_id_n = 1001036;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DESTINATION_DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE_DEALER_ID' where attribute_id_n = 1001037;
update interface.ms_interface_attr set value_v = 'ICCID;M;R;[0-9]{20}#MSISDN;M;R;[0-9]{1,20}#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DESTINATION_DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#STOCK_TRANSFER_DATE;M;RD;[0-9]{8};yyyyMMdd#PROGRAM_CODE;M#PROGRAM_NAME;N#SOURCE_DEALER_ID;M' where attribute_id_n = 1001038;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DESTINATION_DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE_DEALER_ID|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1001039;
--1003
update interface.ms_interface_attr set value_v = 'REMOTE_HOST' where attribute_id_n = 1003001;
update interface.ms_interface_attr set value_v = 'REMOTE_PORT' where attribute_id_n = 1003002;
update interface.ms_interface_attr set value_v = 'REMOTE_USER' where attribute_id_n = 1003003;
update interface.ms_interface_attr set value_v = 'REMOTE_PWD' where attribute_id_n = 1003004;
update interface.ms_interface_attr set value_v = 'ALLOC_DUMP' where attribute_id_n = 1003005;
update interface.ms_interface_attr set value_v = 'yyyyMMdd' where attribute_id_n = 1003006;
update interface.ms_interface_attr set value_v = 'csv' where attribute_id_n = 1003007;
update interface.ms_interface_attr set value_v = 'ctl' where attribute_id_n = 1003008;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/sp_dump/' where attribute_id_n = 1003009;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/sp_dump/' where attribute_id_n = 1003010;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/sp_dump/' where attribute_id_n = 1003011;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/sp_dump/' where attribute_id_n = 1003012;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/sp_dump/' where attribute_id_n = 1003013;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/sp_dump/' where attribute_id_n = 1003014;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/sp_dump/' where attribute_id_n = 1003015;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/sp_dump/' where attribute_id_n = 1003016;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/sp_dump/' where attribute_id_n = 1003017;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/sp_dump/' where attribute_id_n = 1003018;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/sp_dump/' where attribute_id_n = 1003019;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/sp_dump/' where attribute_id_n = 1003020;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/sp_dump/' where attribute_id_n = 1003021;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/sp_dump/' where attribute_id_n = 1003022;
update interface.ms_interface_attr set value_v = 'Request File Type' where attribute_id_n = 1003023;
update interface.ms_interface_attr set value_v = '\|' where attribute_id_n = 1003024;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|SO_CREATION_DATE|ALLOC_ID|PROGRAM_CODE|PROGRAM_NAME|TYPE|ALLOC_DATE|PAYMENT_DATE|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1003025;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/FOSSRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation' where attribute_id_n = 1003026;
update interface.ms_interface_attr set value_v = '0' where attribute_id_n = 1003027;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1003028;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|SO_CREATION_DATE|ALLOC_ID|PROGRAM_CODE|PROGRAM_NAME|TYPE|ALLOC_DATE|PAYMENT_DATE' where attribute_id_n = 1003029;
update interface.ms_interface_attr set value_v = 'ICCID;M;R;[0-9]{20}+#MSISDN;M;R;[0-9]{1,20}+#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#SO_CREATION_DATE;M;D;yyyyMMdd#ALLOC_ID;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#TYPE;N;R;[a-zA-Z]#ALLOC_DATE;M;D;dd-MMM-yy#PAYMENT_DATE;N;D;dd-MMM-yy' where attribute_id_n = 1003030;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/SPAllocDumpMerger!com.enhancesys.integration.services.interfaces.merger.CSVMerger' where attribute_id_n = 1003031;
update interface.ms_interface_attr set value_v = '1004' where attribute_id_n = 1003032;
update interface.ms_interface_attr set value_v = '#' where attribute_id_n = 1003033;
update interface.ms_interface_attr set value_v = '5000' where attribute_id_n = 1003034;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1003035;
update interface.ms_interface_attr set value_v = 'false' where attribute_id_n = 1003036;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|SO_CREATION_DATE|ALLOC_ID|PROGRAM_CODE|PROGRAM_NAME|TYPE|ALLOC_DATE|PAYMENT_DATE' where attribute_id_n = 1003037;
update interface.ms_interface_attr set value_v = 'ICCID;M;R;[0-9]{20}#MSISDN;M;R;[0-9]{1,20}#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#SO_CREATION_DATE;M;RD;[0-9]{8};yyyyMMdd#ALLOC_ID;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#TYPE;N;R;[a-zA-Z]#ALLOC_DATE;M;RD;[0-9]{8};yyyyMMdd#PAYMENT_DATE;N;RD;[0-9]{8};yyyyMMdd' where attribute_id_n = 1003038;
update interface.ms_interface_attr set value_v = 'ICCID|MSISDN|IMSI|DO_ID|DEALER_ID|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|SO_CREATION_DATE|ALLOC_ID|PROGRAM_CODE|PROGRAM_NAME|TYPE|ALLOC_DATE|PAYMENT_DATE|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1003039;
--1005
update interface.ms_interface_attr set value_v = 'REMOTE_HOST' where attribute_id_n = 1005001;
update interface.ms_interface_attr set value_v = 'REMOTE_PORT' where attribute_id_n = 1005002;
update interface.ms_interface_attr set value_v = 'REMOTE_USER' where attribute_id_n = 1005003;
update interface.ms_interface_attr set value_v = 'REMOTE_PWD' where attribute_id_n = 1005004;
update interface.ms_interface_attr set value_v = 'STOCK_DUMP' where attribute_id_n = 1005005;
update interface.ms_interface_attr set value_v = 'yyyyMMdd' where attribute_id_n = 1005006;
update interface.ms_interface_attr set value_v = 'csv' where attribute_id_n = 1005007;
update interface.ms_interface_attr set value_v = 'ctl' where attribute_id_n = 1005008;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/voucher_dump/' where attribute_id_n = 1005009;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/voucher_dump/' where attribute_id_n = 1005010;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/voucher_dump/' where attribute_id_n = 1005011;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/voucher_dump/' where attribute_id_n = 1005012;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/voucher_dump/' where attribute_id_n = 1005013;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/voucher_dump/' where attribute_id_n = 1005014;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/voucher_dump/' where attribute_id_n = 1005015;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/voucher_dump/' where attribute_id_n = 1005016;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/voucher_dump/' where attribute_id_n = 1005017;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/voucher_dump/' where attribute_id_n = 1005018;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/voucher_dump/' where attribute_id_n = 1005019;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/voucher_dump/' where attribute_id_n = 1005020;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/voucher_dump/' where attribute_id_n = 1005021;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/voucher_dump/' where attribute_id_n = 1005022;
update interface.ms_interface_attr set value_v = 'Request File Type' where attribute_id_n = 1005023;
update interface.ms_interface_attr set value_v = '\|' where attribute_id_n = 1005024;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE DEALER ID|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1005025;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/FOSSRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation' where attribute_id_n = 1005026;
update interface.ms_interface_attr set value_v = '0' where attribute_id_n = 1005027;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1005028;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE DEALER ID' where attribute_id_n = 1005029;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N;R;[0-9]+#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#STOCK_TRANSFER_DATE;M;D;yyyyMMdd#PROGRAM_CODE;M#PROGRAM_NAME;N#SOURCE DEALER ID;M' where attribute_id_n = 1005030;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/VoucherStockDumpMerger!com.enhancesys.integration.services.interfaces.merger.CSVMerger' where attribute_id_n = 1005031;
update interface.ms_interface_attr set value_v = '1006' where attribute_id_n = 1005032;
update interface.ms_interface_attr set value_v = '#' where attribute_id_n = 1005033;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1005034;
update interface.ms_interface_attr set value_v = 'false' where attribute_id_n = 1005035;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE_DEALER_ID' where attribute_id_n = 1005036;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N;R;[0-9]+#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#STOCK_TRANSFER_DATE;M;RD;[0-9]{8};yyyyMMdd#PROGRAM_CODE;M#PROGRAM_NAME;N#SOURCE_DEALER_ID;M' where attribute_id_n = 1005037;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|STOCK_TRANSFER_DATE|PROGRAM_CODE|PROGRAM_NAME|SOURCE_DEALER_ID|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1005038;
--1007
update interface.ms_interface_attr set value_v = 'REMOTE_HOST' where attribute_id_n = 1007001;
update interface.ms_interface_attr set value_v = 'REMOTE_PORT' where attribute_id_n = 1007002;
update interface.ms_interface_attr set value_v = 'REMOTE_USER' where attribute_id_n = 1007003;
update interface.ms_interface_attr set value_v = 'REMOTE_PWD' where attribute_id_n = 1007004;
update interface.ms_interface_attr set value_v = 'ALLOC_DUMP' where attribute_id_n = 1007005;
update interface.ms_interface_attr set value_v = 'yyyyMMdd' where attribute_id_n = 1007006;
update interface.ms_interface_attr set value_v = 'csv' where attribute_id_n = 1007007;
update interface.ms_interface_attr set value_v = 'ctl' where attribute_id_n = 1007008;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/voucher_dump/' where attribute_id_n = 1007009;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/voucher_dump/' where attribute_id_n = 1007010;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/voucher_dump/' where attribute_id_n = 1007011;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/voucher_dump/' where attribute_id_n = 1007012;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/voucher_dump/' where attribute_id_n = 1007013;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/voucher_dump/' where attribute_id_n = 1007014;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/voucher_dump/' where attribute_id_n = 1007015;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/voucher_dump/' where attribute_id_n = 1007016;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/voucher_dump/' where attribute_id_n = 1007017;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/voucher_dump/' where attribute_id_n = 1007018;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/voucher_dump/' where attribute_id_n = 1007019;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/voucher_dump/' where attribute_id_n = 1007020;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/voucher_dump/' where attribute_id_n = 1007021;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/voucher_dump/' where attribute_id_n = 1007022;
update interface.ms_interface_attr set value_v = 'Request File Type' where attribute_id_n = 1007023;
update interface.ms_interface_attr set value_v = '\|' where attribute_id_n = 1007024;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|SO_CREATION_DATE|ALLOC_NO|PROGRAM_CODE|PROGRAM_NAME|RESOURCE_TYPE|ALLOC_DATE|PAYMENT_DATE|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1007025;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/FOSSRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation' where attribute_id_n = 1007026;
update interface.ms_interface_attr set value_v = '0' where attribute_id_n = 1007027;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1007028;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|SO_CREATION_DATE|ALLOC_NO|PROGRAM_CODE|PROGRAM_NAME|RESOURCE_TYPE|ALLOC_DATE|PAYMENT_DATE' where attribute_id_n = 1007029;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#SO_CREATION_DATE;M;D;yyyyMMdd#ALLOC_NO;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#RESOURCE_TYPE;N#ALLOC_DATE;M;D;dd-MMM-yy#PAYMENT_DATE;N;D;dd-MMM-yy' where attribute_id_n = 1007030;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/VoucherAllocDumpMerger!com.enhancesys.integration.services.interfaces.merger.CSVMerger' where attribute_id_n = 1007031;
update interface.ms_interface_attr set value_v = '1008' where attribute_id_n = 1007032;
update interface.ms_interface_attr set value_v = '#' where attribute_id_n = 1007033;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1007034;
update interface.ms_interface_attr set value_v = 'false' where attribute_id_n = 1007035;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|SO_CREATION_DATE|ALLOC_NO|PROGRAM_CODE|PROGRAM_NAME|RESOURCE_TYPE|ALLOC_DATE|PAYMENT_DATE' where attribute_id_n = 1007036;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#ARTWORK_CODE;N#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;RD;[0-9]{8};yyyyMMdd#NOMINAL_VALUE;M;R;^[0-9]\d*(\.\d+)?$#SO_CREATION_DATE;M;RD;[0-9]{8};yyyyMMdd#ALLOC_NO;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#RESOURCE_TYPE;N#ALLOC_DATE;M;RD;[0-9]{8};yyyyMMdd#PAYMENT_DATE;N;RD;[0-9]{8};yyyyMMdd' where attribute_id_n = 1007037;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER|DO_ID|DEALER_ID|ARTWORK_CODE|BRANCH_CODE|BRAND|PRODUCT_EXPIRED_DATE|NOMINAL_VALUE|SO_CREATION_DATE|ALLOC_NO|PROGRAM_CODE|PROGRAM_NAME|RESOURCE_TYPE|ALLOC_DATE|PAYMENT_DATE|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1007038;
--1009
update interface.ms_interface_attr set value_v = 'REMOTE_HOST' where attribute_id_n = 1009001;
update interface.ms_interface_attr set value_v = 'REMOTE_PORT' where attribute_id_n = 1009002;
update interface.ms_interface_attr set value_v = 'REMOTE_USER' where attribute_id_n = 1009003;
update interface.ms_interface_attr set value_v = 'REMOTE_PWD' where attribute_id_n = 1009004;
update interface.ms_interface_attr set value_v = 'PRODUCTS' where attribute_id_n = 1009005;
update interface.ms_interface_attr set value_v = 'yyyyMMdd' where attribute_id_n = 1009006;
update interface.ms_interface_attr set value_v = 'csv' where attribute_id_n = 1009007;
update interface.ms_interface_attr set value_v = 'ctl' where attribute_id_n = 1009008;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/product/' where attribute_id_n = 1009009;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/product/' where attribute_id_n = 1009010;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/product/' where attribute_id_n = 1009011;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/product/' where attribute_id_n = 1009012;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/product/' where attribute_id_n = 1009013;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/product/' where attribute_id_n = 1009014;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/product/' where attribute_id_n = 1009015;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/product/' where attribute_id_n = 1009016;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/product/' where attribute_id_n = 1009017;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/product/' where attribute_id_n = 1009018;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/product/' where attribute_id_n = 1009019;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/product/' where attribute_id_n = 1009020;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/product/' where attribute_id_n = 1009021;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/product/' where attribute_id_n = 1009022;
update interface.ms_interface_attr set value_v = 'Request File Type' where attribute_id_n = 1009023;
update interface.ms_interface_attr set value_v = '\|' where attribute_id_n = 1009024;
update interface.ms_interface_attr set value_v = 'Product Code|Sap Material Code|Product Name|Product Category|Product Sub Category|Product Start date|Product End date|Sale Price|MRP Price|Is Serialized|Serial Type|Serial Length|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1009025;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/FOSSRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation' where attribute_id_n = 1009026;
update interface.ms_interface_attr set value_v = '0' where attribute_id_n = 1009027;
update interface.ms_interface_attr set value_v = 'Product Code;M;R;[a-zA-Z0-9]+%Sap Material Code;M;R;[a-zA-Z0-9]{1,10}%Product Name;M;R;[\sa-zA-Z0-9$_#-]{3,50}%Product Category;M%Product Sub Category;M%Product Start date;M;RD;[0-9]{4}-[0-9]{2}-[0-9]{2};yyyy-MM-dd%Product End date;M;RD;[0-9]{4}-[0-9]{2}-[0-9]{2};yyyy-MM-dd%Sale Price;M;R;[0-9]+(\\.[0-9][0-9]?)?%MRP Price;M;R;[0-9]+(\\.[0-9][0-9]?)?%Is Serialized;M;R;[0,1]%Serial Type;M;R;[0,1]%Serial Length;M;R;^(20|1[0-9]|0?[1-9])$' where attribute_id_n = 1009028;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/ProductCreation!com.enhancesys.integration.services.interfaces.merger.CSVMerger' where attribute_id_n = 1009029;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1009030;
update interface.ms_interface_attr set value_v = 'Product Code|Sap Material Code|Product Name|Product Category|Product Sub Category|Product Start date|Product End date|Sale Price|MRP Price|Is Serialized|Serial Type|Serial Length' where attribute_id_n = 1009031;
update interface.ms_interface_attr set value_v = '1010' where attribute_id_n = 1009032;
update interface.ms_interface_attr set value_v = 'PRODUCT_CODE|SAP_MATERIAL_CODE|PRODUCT_NAME|PRODUCT_CATEGORY|PRODUCT_SUBCATEGORY|PRODUCT_START_DATE|PRODUCT_END_DATE|SALE_PRICE|MRP_PRICE|IS_SERIALIZED|SERIAL_TYPE|SERIAL_LENGTH|MIN_ORDER_QTY|MIN_PACKAGE_QTY' where attribute_id_n = 1009033;
update interface.ms_interface_attr set value_v = 'PRODUCT_CODE;M;R;[0-9]+%SAP_MATERIAL_CODE;M;R;[0-9]{1,10}%PRODUCT_NAME;M;R;[\sa-zA-Z0-9$_#-]{3,50}%PRODUCT_CATEGORY;M%PRODUCT_SUBCATEGORY;M%PRODUCT_START_DATE;M;RD;[0-9]{8};yyyyMMdd%PRODUCT_END_DATE;M;RD;[0-9]{8};yyyyMMdd%SALE_PRICE;M;R;[0-9]+(\\.[0-9][0-9]?)?%MRP_PRICE;M;R;[0-9]+(\\.[0-9][0-9]?)?%IS_SERIALIZED;M;R;[0-1]%SERIAL_TYPE;M;R;[0-1]%SERIAL_LENGTH;M;R;^(20|1[0-9]|0?[1-9])$%MIN_ORDER_QTY;N;R;[0-9]+%MIN_PACKAGE_QTY;N;R;[0-9]+' where attribute_id_n = 1009034;
update interface.ms_interface_attr set value_v = 'PRODUCT_CODE|SAP_MATERIAL_CODE|PRODUCT_NAME|PRODUCT_CATEGORY|PRODUCT_SUBCATEGORY|PRODUCT_START_DATE|PRODUCT_END_DATE|SALE_PRICE|MRP_PRICE|IS_SERIALIZED|SERIAL_TYPE|SERIAL_LENGTH|MIN_ORDER_QTY|MIN_PACKAGE_QTY|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1009035;
update interface.ms_interface_attr set value_v = 'false' where attribute_id_n = 1009036;
update interface.ms_interface_attr set value_v = '%' where attribute_id_n = 1009037;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1009038;
--1011
update interface.ms_interface_attr set value_v = 'REMOTE_HOST' where attribute_id_n = 1011001;
update interface.ms_interface_attr set value_v = 'REMOTE_PORT' where attribute_id_n = 1011002;
update interface.ms_interface_attr set value_v = 'REMOTE_USER' where attribute_id_n = 1011003;
update interface.ms_interface_attr set value_v = 'REMOTE_PWD' where attribute_id_n = 1011004;
update interface.ms_interface_attr set value_v = 'EXPIRY_DETAILS' where attribute_id_n = 1011005;
update interface.ms_interface_attr set value_v = 'yyyyMMdd' where attribute_id_n = 1011006;
update interface.ms_interface_attr set value_v = 'csv' where attribute_id_n = 1011007;
update interface.ms_interface_attr set value_v = 'ctl' where attribute_id_n = 1011008;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/expiry_details/' where attribute_id_n = 1011009;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/master_files/expiry_details/' where attribute_id_n = 1011010;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/expiry_details/' where attribute_id_n = 1011011;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/rejected_file/expiry_details/' where attribute_id_n = 1011012;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/expiry_details/' where attribute_id_n = 1011013;
update interface.ms_interface_attr set value_v = '/home/appuser/interfaces/FOSS/backup/master_files/expiry_details/' where attribute_id_n = 1011014;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/expiry_details/' where attribute_id_n = 1011015;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/master_file/expiry_details/' where attribute_id_n = 1011016;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/expiry_details/' where attribute_id_n = 1011017;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/master_file/expiry_details/' where attribute_id_n = 1011018;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/expiry_details/' where attribute_id_n = 1011019;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/rejected_file/expiry_details/' where attribute_id_n = 1011020;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/expiry_details/' where attribute_id_n = 1011021;
update interface.ms_interface_attr set value_v = '/snoc/interfaces/S-NOC/interfaces/backup/rejected_file/expiry_details/' where attribute_id_n = 1011022;
update interface.ms_interface_attr set value_v = 'Request File Type' where attribute_id_n = 1011023;
update interface.ms_interface_attr set value_v = '\|' where attribute_id_n = 1011024;
update interface.ms_interface_attr set value_v = 'Serial Number|Old Product Code|new product code|Expriry Date|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1011025;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/FOSSRejectionFilePreparation!com.enhancesys.integration.services.interfaces.rejection.RejectionFilePreparation' where attribute_id_n = 1011026;
update interface.ms_interface_attr set value_v = '0' where attribute_id_n = 1011027;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1011028;
update interface.ms_interface_attr set value_v = '1012' where attribute_id_n = 1011029;
update interface.ms_interface_attr set value_v = 'Serial Number|Old Product Code|new product code|Expriry Date' where attribute_id_n = 1011030;
update interface.ms_interface_attr set value_v = 'Serial Number;M;R;[0-9]+|Old Product Code;M;R;[a-zA-Z0-9]+|new product code;N|Expriry Date;M;D;yyyy-MM-dd' where attribute_id_n = 1011031;
update interface.ms_interface_attr set value_v = 'java:global/EnhancesysManagement/IntegrationServices/SerialExpiryMerger!com.enhancesys.integration.services.interfaces.merger.CSVMerger' where attribute_id_n = 1011032;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1011033;
update interface.ms_interface_attr set value_v = 'false' where attribute_id_n = 1011034;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER_SIM|NEW_PRODUCT_CODE|NEW_EXPIRY_DATE' where attribute_id_n = 1011035;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER_SIM;M;R;[0-9]+|NEW_PRODUCT_CODE;M|NEW_EXPIRY_DATE;M;RD;[0-9]{8};yyyyMMdd' where attribute_id_n = 1011036;
update interface.ms_interface_attr set value_v = 'SERIAL_NUMBER_SIM|NEW_PRODUCT_CODE|NEW_EXPIRY_DATE|ERRORCODE|ERRORMESSAGE' where attribute_id_n = 1011037;
--1163
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1163001;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1163002;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1163003;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1163004;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1163005;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1163006;
--1164
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1164001;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1164002;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1164003;
update interface.ms_interface_attr set value_v = 'true' where attribute_id_n = 1164004;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1164005;
update interface.ms_interface_attr set value_v = '' where attribute_id_n = 1164006;