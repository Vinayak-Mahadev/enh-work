-- OUTLET DUMP QUERIES: 
--(1) Starter Pack Count: (Sell-In Qty Starter Pack Only) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as starter_pack_count from kpi.tr_daily_event_metrics_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.event_type_n = 2 and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'starter_pack_master')) and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;
--(2) Voucher Count: (Sell-In Qty Voucher Only) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as voucher_count from kpi.tr_daily_event_metrics_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.event_type_n = 2 and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'voucher_master')) and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;
--(3) ACT1 MSISDN Count: (Activation Qty) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as activation_count from kpi.tr_daily_serial_activation_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n = daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;
--(4) GA-FR MSISDN Count: (Injection Qty) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as injection_count from kpi.tr_daily_serial_injection_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n = daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;
--(5) RGU-GA MSISDN Count: (RGU-GA Qty) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as rguga_count from kpi.tr_daily_serial_rguga_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n = daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;
--(6) MOBO Sellout Value: (Transaction Summary Value) 
		select outlet_org_master.org_id_n, outlet_org_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as mobo_sellout_value from kpi.tr_daily_mobo_transaction_aggr as daily_event_aggr inner join kpi.ms_report_org_master as outlet_org_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.actor_id_n = outlet_org_master.aggregation_id_n and outlet_org_master.org_type_n = 6 and outlet_org_master.aggregation_id_n = daily_event_aggr.source_id_n) group by outlet_org_master.org_id_n, outlet_org_master.org_short_code_v order by outlet_org_master.org_id_n;
--(7) VC Redeem Value: (Voucher Redemption Value) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as vc_redeem_value from kpi.tr_daily_voucher_redemption_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n = daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;
--(8) MOBO Sellin Value: (Using MOBII) 
		select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as mobo_sellin_value from kpi.tr_daily_event_metrics_aggr as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.event_type_n = 13 and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'e-voucher_master')) and daily_event_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

-- CSO DUMP QUERIES: 
--(1) SSO: 
		select cso_org_links.node_id_n as node_id_n, round((count(distinct(outlet_node_master.org_id_n))*100),0) as sso_outlets_n from kpi.tr_daily_serial_injection_aggr as daily_metrics_aggr inner join kpi.ms_report_node_master as outlet_node_master on (daily_metrics_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_metrics_aggr.metrics_type_n = 11 and daily_metrics_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'starter_pack_master')) and daily_metrics_aggr.actor_id_n = outlet_node_master.aggregation_id_n and outlet_node_master.org_type_n = 6 and outlet_node_master.org_aggr_id_n = daily_metrics_aggr.source_id_n) inner join kpi.ms_user_org_links as cso_org_links on (cso_org_links.dest_org_id_n = outlet_node_master.org_id_n and cso_org_links.status_n = 174 and 1031 = any(cso_org_links.position_id_n)) group by cso_org_links.node_id_n order by cso_org_links.node_id_n;
--(2) SSO 3+: 
		select cso_org_links.node_id_n as node_id_n, count(distinct(outlet_org_master.org_id_n)) as sso3_count_n from (select source_id_n as aggregation_id_n, round((sum(dimension_1_n)/100),0) as injection_qty_n from kpi.tr_daily_serial_injection_aggr where day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and metrics_type_n = 11 and metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'starter_pack_master')) group by aggregation_id_n having round((sum(dimension_1_n)/100),0) >= (select property_value_n from kpi.ms_property_master where property_key_v = 'stable_sso_injection_qty') ) as daily_metrics_aggr inner join kpi.ms_report_org_master as outlet_org_master on (outlet_org_master.org_type_n = 6 and outlet_org_master.aggregation_id_n = daily_metrics_aggr.aggregation_id_n) inner join kpi.ms_user_org_links as cso_org_links on (cso_org_links.dest_org_id_n = outlet_org_master.org_id_n and cso_org_links.status_n = 174 and 1031 = any(cso_org_links.position_id_n)) group by cso_org_links.node_id_n order by cso_org_links.node_id_n;
--(3) DAILY SSO: 
		select daily_injection_aggr.entity_id_n as node_id_n, round((sum(daily_injection_aggr.daily_sso_outlets_n)/(select (now()::date - date_trunc('month', now())::date)::numeric)),0) as daily_sso_n from (select daily_metrics_aggr.day_id_n as day_id_n, cso_org_links.node_id_n as entity_id_n, count(distinct(outlet_org_master.org_id_n)) daily_sso_outlets_n from kpi.tr_daily_serial_injection_aggr as daily_metrics_aggr inner join kpi.ms_report_org_master as outlet_org_master on (daily_metrics_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_metrics_aggr.metrics_type_n = 11 and daily_metrics_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'starter_pack_master')) and outlet_org_master.org_type_n = 6 and daily_metrics_aggr.source_id_n = outlet_org_master.aggregation_id_n) inner join kpi.ms_user_org_links as cso_org_links on (cso_org_links.dest_org_id_n = outlet_org_master.org_id_n and cso_org_links.status_n = 174 and 1031 = any(cso_org_links.position_id_n)) group by day_id_n, entity_id_n ) as daily_injection_aggr group by daily_injection_aggr.entity_id_n order by daily_injection_aggr.entity_id_n;
--(4) URO: 
		select cso_org_links.node_id_n as node_id_n, count(distinct(outlet_org_master.org_id_n)) as uro_count_n from (select aggregation_id_n from (select redemption_aggr.source_id_n as aggregation_id_n, sum(redemption_aggr.dimension_2_n) as revenue_value_n from kpi.tr_daily_voucher_redemption_aggr as redemption_aggr where redemption_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and redemption_aggr.metrics_type_n = 11 group by redemption_aggr.source_id_n union select transaction_aggr.actor_id_n as aggregation_id_n, sum(transaction_aggr.dimension_2_n) as revenue_value_n from kpi.tr_daily_mobo_transaction_aggr as transaction_aggr where transaction_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and transaction_aggr.metrics_type_n = 21 and transaction_aggr.source_id_n = 0 and transaction_aggr.instance_type_n = 33 and transaction_aggr.instance_id_n in (select metrics_id_n from kpi.ms_metrics_master where metrics_type_n = 33 and metrics_name_v in ('Purchase Data Package', 'Indosat Reload')) group by transaction_aggr.actor_id_n ) as revenue_aggr group by aggregation_id_n having round((sum(revenue_value_n)/100),2) >= (select property_value_n from kpi.ms_property_master where property_key_v = 'revenue_transaction_value') ) as monthly_metrics_aggr inner join kpi.ms_report_org_master as outlet_org_master on (outlet_org_master.org_type_n = 6 and outlet_org_master.aggregation_id_n = monthly_metrics_aggr.aggregation_id_n) inner join kpi.ms_user_org_links as cso_org_links on (cso_org_links.dest_org_id_n = outlet_org_master.org_id_n and cso_org_links.status_n = 174 and 1031 = any(cso_org_links.position_id_n)) group by cso_org_links.node_id_n order by cso_org_links.node_id_n;
--(5) URO 20K+ 
		select cso_org_links.node_id_n as node_id_n, count(distinct(outlet_org_master.org_id_n)) as uro20_count_n from (select aggregation_id_n from (select redemption_aggr.source_id_n as aggregation_id_n, sum(redemption_aggr.dimension_2_n) as revenue_value_n from kpi.tr_daily_voucher_redemption_aggr as redemption_aggr where redemption_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and redemption_aggr.metrics_type_n = 11 group by redemption_aggr.source_id_n union select transaction_aggr.actor_id_n as aggregation_id_n, sum(transaction_aggr.dimension_2_n) as revenue_value_n from kpi.tr_daily_mobo_transaction_aggr as transaction_aggr where transaction_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and transaction_aggr.metrics_type_n = 21 and transaction_aggr.source_id_n = 0 and transaction_aggr.instance_type_n = 33 and transaction_aggr.instance_id_n in (select metrics_id_n from kpi.ms_metrics_master where metrics_type_n = 33 and metrics_name_v in ('Purchase Data Package', 'Indosat Reload')) group by transaction_aggr.actor_id_n ) as revenue_aggr group by revenue_aggr.aggregation_id_n having round((sum(revenue_aggr.revenue_value_n)/100),2) >= (select property_value_n from kpi.ms_property_master where property_key_v = 'stable_uro_revenue_value') ) as monthly_metrics_aggr inner join kpi.ms_report_org_master as outlet_org_master on (outlet_org_master.org_type_n = 6 and outlet_org_master.aggregation_id_n = monthly_metrics_aggr.aggregation_id_n) inner join kpi.ms_user_org_links as cso_org_links on (cso_org_links.dest_org_id_n = outlet_org_master.org_id_n and cso_org_links.status_n = 174 and 1031 = any(cso_org_links.position_id_n)) group by cso_org_links.node_id_n order by cso_org_links.node_id_n;
--(6) DAILY URO: 
		select daily_uro_aggr.node_id_n as node_id_n, round((sum(daily_uro_outlets_n)/(select (now()::date - date_trunc('month', now())::date)::numeric)),0) as daily_uro_n from (select revenue_aggr.day_id_n as day_id_n, cso_org_links.node_id_n as node_id_n, count(distinct(outlet_org_master.org_id_n)) as daily_uro_outlets_n from (select redemption_aggr.day_id_n as day_id_n, redemption_aggr.source_id_n as aggregation_id_n, sum(redemption_aggr.dimension_2_n) as revenue_value_n from kpi.tr_daily_voucher_redemption_aggr as redemption_aggr where redemption_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and redemption_aggr.metrics_type_n = 11 group by redemption_aggr.day_id_n, redemption_aggr.source_id_n union select transaction_aggr.day_id_n as day_id_n, transaction_aggr.actor_id_n as aggregation_id_n, sum(transaction_aggr.dimension_2_n) as revenue_value_n from kpi.tr_daily_mobo_transaction_aggr as transaction_aggr where transaction_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and transaction_aggr.metrics_type_n = 21 and transaction_aggr.source_id_n = 0 and transaction_aggr.instance_type_n = 33 and transaction_aggr.instance_id_n in (select metrics_id_n from kpi.ms_metrics_master where metrics_type_n = 33 and metrics_name_v in ('Purchase Data Package', 'Indosat Reload')) group by transaction_aggr.day_id_n, transaction_aggr.actor_id_n ) as revenue_aggr inner join kpi.ms_report_org_master as outlet_org_master on (outlet_org_master.org_type_n = 6 and outlet_org_master.aggregation_id_n = revenue_aggr.aggregation_id_n) inner join kpi.ms_user_org_links as cso_org_links on (cso_org_links.dest_org_id_n = outlet_org_master.org_id_n and cso_org_links.status_n = 174 and 1031 = any(cso_org_links.position_id_n)) group by day_id_n, node_id_n having round((sum(revenue_value_n)/100),2) >= (select property_value_n from kpi.ms_property_master where property_key_v = 'revenue_transaction_value') ) as daily_uro_aggr group by daily_uro_aggr.node_id_n order by daily_uro_aggr.node_id_n;
--(7) MOBO Sell-In >= 50K: 
		select cso_node_master.node_id_n, round((sum(beat_summary_aggr.mobo_high_n)/(select (now()::date - date_trunc('month', now())::date)::numeric)),0) as mobo_sellin_count_n from kpi.tr_beat_summary_aggr as beat_summary_aggr inner join kpi.ms_report_node_master as cso_node_master on (beat_summary_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and cso_node_master.node_id_n = beat_summary_aggr.node_id_n) group by cso_node_master.node_id_n having round((sum(beat_summary_aggr.mobo_high_n)/count(distinct(beat_summary_aggr.day_id_n))),0) > 0 order by cso_node_master.node_id_n;

-- MPC DUMP QUERIES: 
--(1) Starter Pack: (Stock Entry) 
		select mpc_node_master.org_id_n, mpc_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as starter_pack_count from kpi.tr_daily_event_metrics_aggr as daily_event_aggr inner join kpi.ms_report_node_master as mpc_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.event_type_n = 1 and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'starter_pack_master')) and daily_event_aggr.actor_id_n = mpc_node_master.aggregation_id_n and mpc_node_master.org_type_n = 7 and 1039 = any(mpc_node_master.position_id_n) and mpc_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by mpc_node_master.org_id_n, mpc_node_master.org_short_code_v order by mpc_node_master.org_id_n;
--(2) Voucher: (Stock Entry) 
		select mpc_node_master.org_id_n, mpc_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as voucher_count from kpi.tr_daily_event_metrics_aggr as daily_event_aggr inner join kpi.ms_report_node_master as mpc_node_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric and daily_event_aggr.event_type_n = 1 and daily_event_aggr.metrics_type_n = 11 and daily_event_aggr.metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'voucher_master')) and daily_event_aggr.actor_id_n = mpc_node_master.aggregation_id_n and mpc_node_master.org_type_n = 7 and 1039 = any(mpc_node_master.position_id_n) and mpc_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by mpc_node_master.org_id_n, mpc_node_master.org_short_code_v order by mpc_node_master.org_id_n;
--(3) MOBO Balance: (Indosat To Org Transfer) 
		select mpc_org_master.org_id_n, mpc_org_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as mobo_balance from kpi.tr_daily_indosat_transfer_aggr as daily_event_aggr inner join kpi.ms_report_org_master as mpc_org_master on (daily_event_aggr.day_id_n between to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric and to_char(now() -'1 day'::interval,'yyyymmdd')::numeric  and daily_event_aggr.actor_id_n = mpc_org_master.aggregation_id_n and mpc_org_master.org_type_n = 7) group by mpc_org_master.org_id_n, mpc_org_master.org_short_code_v order by mpc_org_master.org_id_n;
