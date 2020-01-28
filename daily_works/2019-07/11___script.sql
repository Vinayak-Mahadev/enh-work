--  TR_INTERFACE_FILE_SUMMARY  table  altered column  LOCAL_SERVER_V (size changed)
ALTER TABLE INTERFACE.TR_INTERFACE_FILE_SUMMARY ALTER COLUMN LOCAL_SERVER_V TYPE CHARACTER VARYING(2000);
--  TR_INTERFACE_FILE_SUMMARY  table new column  added (FILTER_COUNT_N)
ALTER TABLE INTERFACE.TR_INTERFACE_FILE_SUMMARY ADD COLUMN FILTER_COUNT_N NUMERIC(19,0);


-- Local Filter Backup Directory & Local Filter Control Backup Directory for Interface ids : (1064,1065,1066,1067,1068,1069,1070,1071,1075,1076,1077,1078,1079,1080,1081,1082)
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1064041, 1064, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/org_to_org_transfer/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1064042, 1064, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/org_to_org_transfer/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1065040, 1065, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/indosat_to_org_transfer/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1065041, 1065, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/indosat_to_org_transfer/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1066041, 1066, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/sale_rev_lacci/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1066042, 1066, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/sale_rev_lacci/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1067040, 1067, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/sale_rev_org/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1067041, 1067, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/sale_rev_org/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1068041, 1068, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/pkg_act_a/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1068042, 1068, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/pkg_act_a/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1069041, 1069, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/pkg_act_b/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1069042, 1069, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/pkg_act_b/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1070041, 1070, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/usage_rev_lacci/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1070042, 1070, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/usage_rev_lacci/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1071040, 1071, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/sge/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1071041, 1071, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/sge/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1075042, 1075, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/transaction_summary/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1075043, 1075, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/transaction_summary/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1076041, 1076, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_a/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1076042, 1076, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_a/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1077041, 1077, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_b/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1077042, 1077, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_b/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1078042, 1078, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/usage_ab/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1078043, 1078, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/usage_ab/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1079041, 1079, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/addon_ab/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1079042, 1079, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/addon_ab/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1080042, 1080, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_ab/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1080043, 1080, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_ab/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1081040, 1081, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1081041, 1081, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/reload_sales/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1082040, 1082, 'Local Filter Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/org_balance/', now());
INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1082041, 1082, 'Local Filter Control Backup Directory', '/home/appuser/interfaces/S-NOC/interfaces/backup/filter_file/org_balance/', now());




-- Field Lookup Conf for Interface ids : (1064,1065,1066,1067,1068,1069,1070,1071,1075,1076,1077,1078,1079,1080,1081,1082)

UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"Credit Party ID","metric_field":"Account Type","source_field":"Organization ID","instance_field":"Transaction Channel"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":2},"daily_table":"tr_daily_org_transfer_aggr","monthly_table":"tr_monthly_org_transfer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1064040;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"Organization ID","metric_field":"Initiator Type","source_field":"SP Operator ID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":1},"daily_table":"tr_daily_indosat_transfer_aggr","monthly_table":"tr_monthly_indosat_transfer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1065039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"CI ID","metric_field":"","source_field":"CI ID","instance_field":""},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_direct_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_direct_master(?, ?);","reprocess":{"event_type":102,"error_code":60102},"daily_table":"tr_daily_sale_revenue_lacci_aggr","monthly_table":"tr_monthly_sale_revenue_lacci_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1066039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"Organization Shortcode","metric_field":"Transaction Type","source_field":"Organization Shortcode","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":1},"daily_table":"tr_daily_sale_revenue_org_aggr","monthly_table":"tr_monthly_sale_revenue_org_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1067039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"CI ID","metric_field":"Packet data name","source_field":"Org Id","instance_field":"System source"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":2},"reprocess":{"event_type":104,"error_code":60102},"daily_table":"tr_daily_package_act_seller_aggr","monthly_table":"tr_monthly_package_act_seller_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1068039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"CI ID","metric_field":"Packet data name","source_field":"Org Id","instance_field":"System source"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":2},"reprocess":{"event_type":105,"error_code":60102},"daily_table":"tr_daily_package_act_buyer_aggr","monthly_table":"tr_monthly_package_act_buyer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1069039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"CI ID","metric_field":{"37":"Revenue type","39":"Unit of Measure"},"source_field":"Org Id","instance_field":"Revenue category"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":3},"reprocess":{"event_type":106,"error_code":60102},"daily_table":"tr_daily_usage_metrics_aggr","monthly_table":"tr_monthly_usage_metrics_aggr","no_of_join_query_params":2,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1070039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"ORG_CODE","metric_field":"","source_field":"ORG_CODE","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_direct_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_direct_master(?, ?);","daily_table":"tr_daily_subscriber_event_aggr","monthly_table":"tr_monthly_subscriber_event_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1071039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"SMORGShortcode","metric_field":{"43":"TransactionType,ProductName","46":"TransactionType,ProductGroup"},"source_field":"SMORGShortcode","instance_field":"TransactionType"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"default_conf":{"field":"ProductGroup","default_value":"Default Package Type"},"table_name":"tr_temp_upload_metrics_trans_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_trans_master(?, ?);","metric_delimiter":"\\|","notexist_lookup":"metrics","no_of_params":{"metrics":1},"daily_table":"tr_daily_mobo_transaction_aggr","monthly_table":"tr_monthly_mobo_transaction_aggr","cs_daily_table":"tr_daily_mobo_crosselling_aggr","cs_monthly_table":"tr_monthly_mobo_crosselling_aggr","no_of_join_query_params":1,"cross_sell_lacci_config":{"A#CIID":"A#LACID~A#CIID","B#CIID":"B#LACID~B#CIID"},"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"data_filter":[{"field_index":2,"values":["Transaction Type 1","Transaction Type 2","Transaction Type 3"],"operation":"in"}]}' WHERE ATTRIBUTE_ID_N = 1075040;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"CI ID","metric_field":"Product Value","source_field":"Org Id","instance_field":"Reload system"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":2},"reprocess":{"event_type":107,"error_code":60102},"daily_table":"tr_daily_reload_seller_aggr","monthly_table":"tr_monthly_reload_seller_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1076039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"CI ID","metric_field":"Product Value","source_field":"","instance_field":"Reload system"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":2},"reprocess":{"event_type":108,"error_code":60102},"daily_table":"tr_daily_reload_buyer_aggr","monthly_table":"tr_monthly_reload_buyer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1077039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"B#MSISDN","metric_field":"B#MSISDN","source_field":"OperatorID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"actor","no_of_params":{"actor":1},"daily_table":"tr_daily_usage_customer_aggr","monthly_table":"tr_monthly_usage_customer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1078041;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"B#MSISDN","metric_field":"PACKAGE_NAME","source_field":"OperatorID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"actor","no_of_params":{"actor":1},"daily_table":"tr_daily_addon_customer_aggr","monthly_table":"tr_monthly_addon_customer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1079040;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"B#MSISDN","metric_field":"B#MSISDN","source_field":"OperatorID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist_lookup":"actor","no_of_params":{"actor":1},"daily_table":"tr_daily_reload_customer_aggr","monthly_table":"tr_monthly_reload_customer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1080041;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"Organization code","metric_field":"","source_field":"Organization code","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_direct_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_direct_master(?, ?);","daily_table":"tr_daily_reload_sellinout_aggr","monthly_table":"tr_monthly_reload_sellinout_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1081039;
UPDATE INTERFACE.MS_INTERFACE_ATTR SET VALUE_V = '{"process_enabled":true,"fields":{"actor_field":"Organization ID","metric_field":"Account Type","source_field":"Organization ID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_orgbal_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_orgbal_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":1},"daily_table":"tr_daily_org_balance_aggr","monthly_table":"tr_monthly_org_balance_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' WHERE ATTRIBUTE_ID_N = 1082039;





