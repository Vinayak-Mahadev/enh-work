--1172_DAILY_JOIN_QUERY_1=
select 
	1172 AS interface_id_n,
	temp1.id_n,
	daily1.day_id_n,
	temp1.actor_type_n,
	temp1.actor_id_n,
	temp1.event_type_n,
	temp1.metrics_type_n,
	temp1.metrics_id_n,
	case when (temp1.dimension_1_n is null and daily1.dimension_1_n is null) then null else coalesce(temp1.dimension_1_n, 0) end as dimension_1_n,
	case when (temp1.dimension_2_n is null and daily1.dimension_2_n is null) then null else coalesce(temp1.dimension_2_n, 0) end as dimension_2_n,
	case when (temp1.dimension_3_n is null and daily1.dimension_3_n is null) then null else coalesce(temp1.dimension_3_n, 0) end as dimension_3_n,
	case when (temp1.dimension_4_n is null and daily1.dimension_4_n is null) then null else coalesce(temp1.dimension_4_n, 0) end as dimension_4_n,
	case when (temp1.dimension_5_n is null and daily1.dimension_5_n is null) then null else coalesce(temp1.dimension_5_n, 0) end as dimension_5_n,
	case when (temp1.dimension_6_n is null and daily1.dimension_6_n is null) then null else coalesce(temp1.dimension_6_n, 0) end as dimension_6_n,
	case when (temp1.dimension_7_n is null and daily1.dimension_7_n is null) then null else coalesce(temp1.dimension_7_n, 0) end as dimension_7_n,
	case when (temp1.dimension_8_n is null and daily1.dimension_8_n is null) then null else coalesce(temp1.dimension_8_n, 0) end as dimension_8_n,
	case when (temp1.dimension_9_n is null and daily1.dimension_9_n is null) then null else coalesce(temp1.dimension_9_n, 0) end as dimension_9_n,
	case when (temp1.dimension_10_n is null and daily1.dimension_10_n is null) then null else coalesce(temp1.dimension_10_n, 0) end as dimension_10_n,
	case when (temp1.no_of_events_n is null and daily1.no_of_events_n is null) then null else coalesce(temp1.no_of_events_n, 0) end as no_of_events_n,
	temp1.source_type_n,
	temp1.source_id_n,
	temp1.data_flag_n,
	temp1.instance_type_n,
	temp1.instance_id_n
from
	(select 
		result.id_n,
		result.actor_type_n,
		result.actor_id_n,
		result.event_type_n,
		result.metrics_type_n,
		result.metrics_id_n,
		sum(result.dimension_1_n) as dimension_1_n,
		sum(result.dimension_2_n) as dimension_2_n,
		sum(result.dimension_3_n) as dimension_3_n,
		sum(result.dimension_4_n) as dimension_4_n,
		sum(result.dimension_5_n) as dimension_5_n,
		sum(result.dimension_6_n) as dimension_6_n,
		sum(result.dimension_7_n) as dimension_7_n,
		sum(result.dimension_8_n) as dimension_8_n,
		sum(result.dimension_9_n) as dimension_9_n,
		sum(result.dimension_10_n) as dimension_10_n,
		sum(result.no_of_events_n) as no_of_events_n,
		result.source_type_n,
		result.source_id_n,
		result.data_flag_n,
		result.instance_type_n,
		result.instance_id_n,
		result.file_id_n
	from
		(select 
			temp.id_n,
			temp.actor_key_v,
			coalesce((select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.actor_type_n), temp.actor_type_n) as actor_type_n,
			coalesce(site.site_id_n, 0) as actor_id_n,
			temp.event_type_n,
			temp.metrics_key_v,
			coalesce((select reference_type_n from kpi.ms_metrics_lookup_master where metrics_type_n = temp.metrics_type_n), temp.metrics_type_n) as metrics_type_n,
			coalesce(met.metrics_id_n, 0) as metrics_id_n,
			temp.dimension_1_n,
			temp.dimension_2_n,
			temp.dimension_3_n,
			temp.dimension_4_n,
			temp.dimension_5_n,
			temp.dimension_6_n,
			temp.dimension_7_n,
			temp.dimension_8_n,
			temp.dimension_9_n,
			temp.dimension_10_n,
			temp.no_of_events_n,
			temp.source_key_v,
			coalesce((select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.source_type_n), temp.source_type_n) as source_type_n,
			coalesce(micro.lookup_id_n, 0) as source_id_n,
			temp.data_flag_n,
			temp.instance_key_v,
			0 as instance_type_n,
			0 as instance_id_n,
			temp.file_id_n,
			case
				when temp.metrics_key_v is not null and coalesce(met.metrics_id_n, 0) = 0 
				then kpi.update_analytics_failed_row('tr_temp_total_revenue_aggr', temp.temp_id_n, 'metric_field%Metric mapping not found for metric_field : ' || temp.metrics_key_v)
			else 1
			end as id_flag
		from 
			kpi.tr_temp_total_revenue_aggr temp
			left join kpi.ms_site_master site on temp.actor_key_v = site.ref_code_v
			left join kpi.ms_metrics_master met on temp.metrics_key_v = met.ext_reference_id_v and met.metrics_type_n = 25
			left join kpi.ms_lookup_master micro on micro.lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) and temp.source_key_v = micro.lookup_name_v
		where 
			temp.file_id_n = ?
		) result
	where 
		result.id_flag = 1
	group by 
		result.id_n,
		result.actor_type_n,
		result.actor_id_n,
		result.event_type_n,
		result.metrics_type_n,
		result.metrics_id_n,
		result.source_type_n,
		result.source_id_n,
		result.data_flag_n,
		result.instance_type_n,
		result.instance_id_n,
		result.file_id_n
	) temp1
	left join kpi.tr_daily_total_revenue_aggr daily1 on temp1.id_n = daily1.day_id_n
	and temp1.actor_type_n = daily1.actor_type_n
	and temp1.actor_id_n = daily1.actor_id_n
	and temp1.event_type_n = daily1.event_type_n
	and temp1.metrics_type_n = daily1.metrics_type_n
	and temp1.metrics_id_n = daily1.metrics_id_n
	and temp1.source_type_n = daily1.source_type_n
	and temp1.source_id_n = daily1.source_id_n
	and temp1.data_flag_n = daily1.data_flag_n
	and temp1.instance_type_n = daily1.instance_type_n
	and temp1.instance_id_n = daily1.instance_id_n;