-- Prepared by Vinayak(vinay.nagaraj@enhancesys.com)

-- Update scripts for DWH Interface's KPI confiuration
-- [1066,1067,1068,1069,1070,1071,1076,1077,1078,1079,1080,1081]

--1066
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"CI ID","metric_field":"","source_field":"CI ID","instance_field":""},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"table_name":"tr_temp_upload_metrics_direct_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_direct_master(?, ?);","reprocess":{"event_type":102,"error_code":60102},"daily_table":"tr_daily_sale_revenue_lacci_aggr","monthly_table":"tr_monthly_sale_revenue_lacci_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1066039;
--1067
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"Organization Shortcode","metric_field":"Transaction Type","source_field":"Organization Shortcode","instance_field":""},"error_codes":{"metric_field":"60201","instance_field":"60401","actor_field":"60101","source_field":"60301"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_sale_revenue_org_aggr","monthly_table":"tr_monthly_sale_revenue_org_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1067039;
--1068
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"CI ID","metric_field":"Packet data name","source_field":"Org Id","instance_field":"System source"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"reprocess":{"event_type":104,"error_code":60102},"no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"daily_table":"tr_daily_package_act_seller_aggr","monthly_table":"tr_monthly_package_act_seller_aggr"}' where attribute_id_n = 1068039;
--1069
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"CI ID","metric_field":"Packet data name","source_field":"Org Id","instance_field":"System source"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"reprocess":{"event_type":105,"error_code":60102},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_package_act_buyer_aggr","monthly_table":"tr_monthly_package_act_buyer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1069039;
--1070
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"CI ID","metric_field":{"37":"Revenue type","39":"Unit of Measure"},"source_field":"Org Id","instance_field":"Revenue category"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"reprocess":{"event_type":106,"error_code":60102},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_usage_metrics_aggr","monthly_table":"tr_monthly_usage_metrics_aggr","no_of_join_query_params":2,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1070039;
--1071
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"ORG_CODE","metric_field":"","source_field":"ORG_CODE","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_direct_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_direct_master(?, ?);","daily_table":"tr_daily_subscriber_event_aggr","monthly_table":"tr_monthly_subscriber_event_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1071039;
--1076
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"CI ID","metric_field":"Product Value","source_field":"Org Id","instance_field":"Reload system"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"reprocess":{"event_type":107,"error_code":60102},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_reload_seller_aggr","monthly_table":"tr_monthly_reload_seller_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1076039;
--1077
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"CI ID","metric_field":"Product Value","source_field":"","instance_field":"Reload system"},"error_codes":{"actor_field":"60102","metric_field":"60201","source_field":"60301","instance_field":"60401"},"lacci_mapping":{"CI ID":"LAC ID~CI ID"},"reprocess":{"event_type":108,"error_code":60102},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_reload_buyer_aggr","monthly_table":"tr_monthly_reload_buyer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1077039;
--1078
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"B#MSISDN","metric_field":"B#MSISDN","source_field":"OperatorID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_usage_customer_aggr","monthly_table":"tr_monthly_usage_customer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1078041;
--1079
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"B#MSISDN","metric_field":"PACKAGE_NAME","source_field":"OperatorID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_addon_customer_aggr","monthly_table":"tr_monthly_addon_customer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1079040;
--1080
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"B#MSISDN","metric_field":"B#MSISDN","source_field":"OperatorID","instance_field":""},"error_codes":{"metric_field":"60201","instance_field":"60401","actor_field":"60101","source_field":"60301"},"table_name":"tr_temp_upload_metrics_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_master(?, ?);","notexist-lookup-flag":true,"daily_table":"tr_daily_reload_customer_aggr","monthly_table":"tr_monthly_reload_customer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1080041;
--1081
update interface.ms_interface_attr set value_v = '{"process_enabled":false,"fields":{"actor_field":"Organization code","metric_field":"","source_field":"Organization code","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_direct_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_direct_master(?, ?);","daily_table":"tr_daily_reload_sellinout_aggr","monthly_table":"tr_monthly_reload_sellinout_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1}' where attribute_id_n = 1081039;