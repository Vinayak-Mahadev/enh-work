
--1075 escape(\\) character added
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"SMORGShortcode","metric_field":{"43":"TransactionType,ProductName","46":"TransactionType,ProductGroup"},"source_field":"SMORGShortcode","instance_field":"TransactionType"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_trans_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_trans_master(?, ?);","notexist-lookup-flag":true,"default_conf":{"field":"ProductGroup","default_value":"Default Package Type"},"metric_delimiter":"\\|","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"cs_daily_table":"tr_daily_mobo_crosselling_aggr","cs_monthly_table":"tr_monthly_mobo_crosselling_aggr","daily_table":"tr_daily_mobo_transaction_aggr","monthly_table":"tr_monthly_mobo_transaction_aggr","cross_sell_lacci_config":{"B#CIID":"B#LACID~B#CIID","A#CIID":"A#LACID~A#CIID"},"data_filter":[{"operation":"in","values":["Purchase Data Package","Indosat Reload","Voucher card injection"],"field_index":2}]}' WHERE ATTRIBUTE_ID_N = 1075040;


--1166
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"MPC_CODE","metric_field":"","source_field":"","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily","3":"cleanup"},"table_name":"tr_temp_primary_mobo_aggr","lookup_query":"","notexist_lookup":"","no_of_params":{},"daily_table":"tr_daily_primary_mobo_aggr","monthly_table":"tr_monthly_primary_mobo_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"duplicate_validation_conf":{"field_indexes":[0,1],"schema_name":"kpi","table_name":"tr_validate_primary_mobo_aggr","include-fileId":true},"data_filter":[]}' WHERE ATTRIBUTE_ID_N = 1166037;1166032

--FIELD_VALIDATION_CONF
-- Attributes for interface 1166 (INTHDP002 - Primary MOBO - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'MPC_CODE;M;R;%MOBO_DATE;M;DNF;[0-9]*;yyyyMMdd%AMOUNT;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1166032;

-- Attributes for interface 1167 (INTHDP003 - Secondary MOBO - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'MPC_CODE;M;R;%DATE;M;D;yyyyMMdd%ORGANIZATION_ID;M;R;%DEALER_MSISDN;M;R;%AMOUNT;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1167032;

-- Attributes for interface 1168 (INTHDP004 - Daily SIM Selling Outlet (SSO) - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;D;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%ID_OUTLET;M;R;%QTY;M;R;^\d+(\.\d{1,2})?$+%AMOUNT;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1168032;

-- Attributes for interface 1169 (INTHDP005 - RGU-GA with Injection - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%ID_OUTLET;M;R;%STATUS_INJECTION;M;R;%FLAG_ACM;M;R;%COUNT_MSISDN;M;R;[0-9]*' WHERE ATTRIBUTE_ID_N = 1169032;

-- Attributes for interface 1170 (INTHDP006 - Tertiary Sales - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%OUTLET;M;R;%AMOUNT;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1170032;

-- Attributes for interface 1171 (INTHDP007 - Organization MOBO Balance - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%ORG_CODE;M;R;%AMOUNT;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1171032;

-- Attributes for interface 1172 (INTHDP008 - Prepaid Service Revenue - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%REVENUE_TYPE;M;R;%REVENUE_TOTAL;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1172032;

-- Attributes for interface 1173 (INTHDP009 - MOBO Usage Revenue - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;N;R;%SITE_ID;N;R;%REVENUE;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1173032;

-- Attributes for interface 1174 (INTHDP010 - Acquisition Revenue - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;N;R;%SITE_ID;N;R;%REVENUE;M;R;^\d+(\.\d{1,2})?$' WHERE ATTRIBUTE_ID_N = 1174032;

-- Attributes for interface 1175 (INTHDP011 - Low revenue Sites - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%CATEGORY;M;R;%TARGET;N;R;^-?([0-9]\d*(\.\d{1,2}+)?)$%REVENUE;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$' WHERE ATTRIBUTE_ID_N = 1175032;

-- Attributes for interface 1176 (INTHDP012 - Sites with RGU-GA - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%QTY;M;R;[0-9]*' WHERE ATTRIBUTE_ID_N = 1176032;

-- Attributes for interface 1177 (INTHDP013 - Cross Selling Cluster Reload - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%CLUSTER_ID;M;R;%CATEGORY;M;R;%CLUSTER_TYPE;M;R;%TOTAL_RELOAD;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$%CROSS_RELOAD;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$' WHERE ATTRIBUTE_ID_N = 1177032;

-- Attributes for interface 1178 (INTHDP014 - Cross Selling Area Data Package - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%CLUSTER_ID;M;R;%TOTAL_DATA;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$%CROSS_DATA;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$' WHERE ATTRIBUTE_ID_N = 1178032;

-- Attributes for interface 1179 (INTHDP015 - Outlet Program Achiever - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'MONTH_ID;M;RD;[0-9]*;yyyyMM%CLUSTER;M;R;%ID_OUTLET;M;R;%TARGET;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$%ACTUAL;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$' WHERE ATTRIBUTE_ID_N = 1179032;

-- Attributes for interface 1180 (INTHDP016 - Ontime Allocation Payment - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'MONTH_ID;M;RD;[0-9]*;yyyyMM%CLUSTER;M;R;%MPC_CODE;M;R;%PAYMENT_ALLOCATION;M;R;[0-9]+' WHERE ATTRIBUTE_ID_N = 1180032;

-- Attributes for interface 1181 (INTHDP017 - URO 20K - Receive file)
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = 'DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%OUTLET;M;R;%HIT;M;R;[0-9]+%AMOUNT;M;R;^-?([0-9]\d*(\.\d{1,2}+)?)$' WHERE ATTRIBUTE_ID_N = 1181032;