-- OUTLET DUMP QUERIES:
--(1) Starter Pack Count: (Sell-In Qty Starter Pack Only)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as starter_pack_count from (select actor_id_n as actor_id_n, source_id_n as source_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_event_metrics_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and event_type_n = 2 and metrics_type_n = 11 and metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'starter_pack_master')) and actor_type_n = 2 group by actor_id_n, source_id_n ) as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n and outlet_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

--(2) Voucher Count: (Sell-In Qty Voucher Only)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as voucher_count from (select actor_id_n as actor_id_n, source_id_n as source_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_event_metrics_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and event_type_n = 2 and metrics_type_n = 11 and metrics_id_n in (select metrics_id_n from kpi.ms_metrics_master where ext_metrics_id_v in (select unnest(string_to_array(property_value_v::text, ','::text)::character varying[]) from kpi.ms_property_master where property_key_v ilike 'voucher_master')) and actor_type_n = 2 group by actor_id_n, source_id_n ) as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n and outlet_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

--(3) ACT1 MSISDN Count: (Activation Qty)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as activation_count from (select actor_id_n as actor_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_serial_activation_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and metrics_type_n = 11 and actor_type_n = 2 group by actor_id_n ) as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

--(4) GA-FR MSISDN Count: (Injection Qty)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as injection_count from (select actor_id_n as actor_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_serial_injection_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and metrics_type_n = 11 and actor_type_n = 2 group by actor_id_n )as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

--(5) RGU-GA MSISDN Count: (RGU-GA Qty)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as rguga_count from (select actor_id_n as actor_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_serial_rguga_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and metrics_type_n = 11 and actor_type_n = 2 group by actor_id_n ) as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

--(6) MOBO SellOut Value: (Transaction Summary Value)
select outlet_org_master.org_id_n, outlet_org_master.org_short_code_v, round((sum(daily_event_aggr.dimension_2_n)/100),0) as mobo_sellout_value from (select actor_id_n as actor_id_n, sum(dimension_2_n) as dimension_2_n from kpi.tr_daily_mobo_transaction_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and actor_type_n = 1 group by actor_id_n ) as daily_event_aggr inner join kpi.ms_report_org_master as outlet_org_master on (outlet_org_master.org_type_n = 6 and outlet_org_master.aggregation_id_n = daily_event_aggr.actor_id_n) group by outlet_org_master.org_id_n, outlet_org_master.org_short_code_v order by outlet_org_master.org_id_n;

--(7) VC Redeem Value: (Voucher Redemption Value)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as vc_redeem_value from (select actor_id_n as actor_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_voucher_redemption_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and metrics_type_n = 11 and actor_type_n = 2 group by actor_id_n ) as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;

--(8) MOBO SellIn Value: (Using MOBII)
select outlet_node_master.org_id_n, outlet_node_master.org_short_code_v, round((sum(daily_event_aggr.dimension_1_n)/100),0) as mobo_sellin_value from (select actor_id_n as actor_id_n, source_id_n as source_id_n, sum(dimension_1_n) as dimension_1_n from kpi.tr_daily_balance_transfer_aggr where day_id_n between case when (extract(day from now())::numeric != 1) then to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 mon'::interval)::date,'yyyymmdd')::numeric end and case when (extract(day from now())::numeric != 1) then to_char(now() -'1 day'::interval,'yyyymmdd')::numeric else to_char((date_trunc('month', current_date) - '1 day'::interval)::date,'yyyymmdd')::numeric end and metrics_type_n = 11 and actor_type_n = 2 group by actor_id_n, source_id_n ) as daily_event_aggr inner join kpi.ms_report_node_master as outlet_node_master on (outlet_node_master.org_type_n = 6 and outlet_node_master.aggregation_id_n = daily_event_aggr.actor_id_n and outlet_node_master.org_aggr_id_n != daily_event_aggr.source_id_n) group by outlet_node_master.org_id_n, outlet_node_master.org_short_code_v order by outlet_node_master.org_id_n;


