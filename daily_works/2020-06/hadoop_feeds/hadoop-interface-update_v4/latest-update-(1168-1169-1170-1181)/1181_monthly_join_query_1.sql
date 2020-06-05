--1181_MONTHLY_JOIN_QUERY_1=
select 
	1181 as interface_id_n,
	temp1.month_id_n as id_n,
	monthly1.month_id_n,
	temp1.actor_type_n,
	temp1.actor_id_n,
	temp1.event_type_n,
	temp1.metrics_type_n,
	temp1.metrics_id_n,
	case when (temp1.dimension_1_n is null and monthly1.dimension_1_n is null) then null else (coalesce(temp1.dimension_1_n, 0) + coalesce(monthly1.dimension_1_n, 0)) end as dimension_1_n,
	case when (temp1.dimension_2_n is null and monthly1.dimension_2_n is null) then null else (coalesce(temp1.dimension_2_n, 0) + coalesce(monthly1.dimension_2_n, 0)) end as dimension_2_n,
	case when (temp1.dimension_3_n is null and monthly1.dimension_3_n is null) then null else (coalesce(temp1.dimension_3_n, 0) + coalesce(monthly1.dimension_3_n, 0)) end as dimension_3_n,
	case when (temp1.dimension_4_n is null and monthly1.dimension_4_n is null) then null else (coalesce(temp1.dimension_4_n, 0) + coalesce(monthly1.dimension_4_n, 0)) end as dimension_4_n,
	case when (temp1.dimension_5_n is null and monthly1.dimension_5_n is null) then null else (coalesce(temp1.dimension_5_n, 0) + coalesce(monthly1.dimension_5_n, 0)) end as dimension_5_n,
	case when (temp1.dimension_6_n is null and monthly1.dimension_6_n is null) then null else (coalesce(temp1.dimension_6_n, 0) + coalesce(monthly1.dimension_6_n, 0)) end as dimension_6_n,
	case when (temp1.dimension_7_n is null and monthly1.dimension_7_n is null) then null else (coalesce(temp1.dimension_7_n, 0) + coalesce(monthly1.dimension_7_n, 0)) end as dimension_7_n,
	case when (temp1.dimension_8_n is null and monthly1.dimension_8_n is null) then null else (coalesce(temp1.dimension_8_n, 0) + coalesce(monthly1.dimension_8_n, 0)) end as dimension_8_n,
	case when (temp1.dimension_9_n is null and monthly1.dimension_9_n is null) then null else (coalesce(temp1.dimension_9_n, 0) + coalesce(monthly1.dimension_9_n, 0)) end as dimension_9_n,
	case when (temp1.dimension_10_n is null and monthly1.dimension_10_n is null) then null else (coalesce(temp1.dimension_10_n, 0) + coalesce(monthly1.dimension_10_n, 0)) end as dimension_10_n,
	case when (temp1.no_of_events_n is null and monthly1.no_of_events_n is null) then null else (coalesce(temp1.no_of_events_n, 0) + coalesce(monthly1.no_of_events_n, 0)) end as no_of_events_n,
	temp1.source_type_n,
	temp1.source_id_n,
	temp1.data_flag_n,
	temp1.instance_type_n,
	temp1.instance_id_n
from
	(select 
		result.month_id_n,
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
			day_master.month_id_n,
			temp.actor_key_v,
			coalesce((select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.actor_type_n), temp.actor_type_n) as actor_type_n,
			coalesce(org.aggregation_id_n, 0) as actor_id_n,
			temp.event_type_n,
			temp.metrics_key_v,
			21 as metrics_type_n,
			110 as metrics_id_n,
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
				when temp.actor_key_v is not null and coalesce(org.aggregation_id_n, 0) = 0 
				then kpi.update_analytics_failed_row('tr_temp_uro_20k_aggr', temp.temp_id_n, 'actor_field%Actor mapping not found for actor_field : ' || temp.actor_key_v)
				when temp.source_key_v is not null and coalesce(micro.lookup_id_n, 0) = 0 
				then kpi.update_analytics_failed_row('tr_temp_uro_20k_aggr', temp.temp_id_n, 'source_field%Source mapping not found for source_field : ' || temp.source_key_v)
		    else 1
			end as id_flag
		from 
			kpi.tr_temp_uro_20k_aggr temp
			left join kpi.ms_org_master org on temp.actor_key_v = org.ref_code_v
			left join kpi.ms_lookup_master micro on micro.lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) and temp.source_key_v = micro.ext_ref_code_v
			inner join kpi.ms_day_master as day_master on temp.id_n = day_master.day_id_n
		where 
			temp.file_id_n = ?
			and temp.actor_type_n = 10
			and temp.status_flag_n = 0
		) result
	where 
		result.id_flag = 1
	group by 
		result.month_id_n,
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
	left join kpi.tr_monthly_uro_20k_aggr monthly1 on temp1.month_id_n = monthly1.month_id_n
	and temp1.actor_type_n = monthly1.actor_type_n
	and temp1.actor_id_n = monthly1.actor_id_n
	and temp1.event_type_n = monthly1.event_type_n
	and temp1.metrics_type_n = monthly1.metrics_type_n
	and temp1.metrics_id_n = monthly1.metrics_id_n
	and temp1.source_type_n = monthly1.source_type_n
	and temp1.source_id_n = monthly1.source_id_n
	and temp1.data_flag_n = monthly1.data_flag_n
	and temp1.instance_type_n = monthly1.instance_type_n
	and temp1.instance_id_n = monthly1.instance_id_n;
