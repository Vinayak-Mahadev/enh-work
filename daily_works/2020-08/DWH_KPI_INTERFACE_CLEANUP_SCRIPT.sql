--kpi daily table
truncate table kpi.tr_daily_sale_revenue_lacci_aggr;
truncate table kpi.tr_daily_sale_revenue_org_aggr;
truncate table kpi.tr_daily_package_act_seller_aggr;
truncate table kpi.tr_daily_package_act_buyer_aggr;
truncate table kpi.tr_daily_usage_metrics_aggr;
truncate table kpi.tr_daily_subscriber_event_aggr;
truncate table kpi.tr_daily_reload_seller_aggr;
truncate table kpi.tr_daily_reload_buyer_aggr;
truncate table kpi.tr_daily_usage_customer_aggr;
truncate table kpi.tr_daily_addon_customer_aggr;
truncate table kpi.tr_daily_reload_customer_aggr;
truncate table kpi.tr_daily_reload_sellinout_aggr;

--kpi monthly table
truncate table kpi.tr_monthly_sale_revenue_lacci_aggr;
truncate table kpi.tr_monthly_sale_revenue_org_aggr;
truncate table kpi.tr_monthly_package_act_seller_aggr;
truncate table kpi.tr_monthly_package_act_buyer_aggr;
truncate table kpi.tr_monthly_usage_metrics_aggr;
truncate table kpi.tr_monthly_subscriber_event_aggr;
truncate table kpi.tr_monthly_reload_seller_aggr;
truncate table kpi.tr_monthly_reload_buyer_aggr;
truncate table kpi.tr_monthly_usage_customer_aggr;
truncate table kpi.tr_monthly_addon_customer_aggr;
truncate table kpi.tr_monthly_reload_customer_aggr;
truncate table kpi.tr_monthly_reload_sellinout_aggr;

--kpi temp table
delete from kpi.tr_temp_upload_metrics_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1066, 1067, 1068, 1069, 1070, 1071, 1076, 1077, 1078, 1079, 1080, 1081));
delete from kpi.tr_temp_upload_metrics_revenue_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1066, 1067, 1068, 1069, 1070, 1071, 1076, 1077, 1078, 1079, 1080, 1081));

--kpi failure table
delete from kpi.tr_temp_upload_aggr_failure where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1066, 1067, 1068, 1069, 1070, 1071, 1076, 1077, 1078, 1079, 1080, 1081));

--interface file summary details
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1066, 1067, 1068, 1069, 1070, 1071, 1076, 1077, 1078, 1079, 1080, 1081));

--interface file summary 
delete from interface.tr_interface_file_summary where interface_id_n in (1066, 1067, 1068, 1069, 1070, 1071, 1076, 1077, 1078, 1079, 1080, 1081);

