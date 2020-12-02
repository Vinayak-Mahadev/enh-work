--1185_DAILY_JOIN_QUERY_1=
select 
	1185 as interface_id_n,
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
			coalesce(promoter.aggregation_id_n, 0) as actor_id_n,
			temp.event_type_n,
			temp.metrics_key_v,
			coalesce((select reference_type_n from kpi.ms_metrics_lookup_master where metrics_type_n = temp.metrics_type_n), temp.metrics_type_n) as metrics_type_n,
			coalesce(checkin.metrics_id_n, 0) as metrics_id_n,
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
			coalesce(clus.lookup_id_n, 0) as source_id_n,
			temp.data_flag_n,
			temp.instance_key_v,
			0 as instance_type_n,
			0 as instance_id_n,
			temp.file_id_n,
			1 as id_flag
		from 
			kpi.tr_temp_dssf_attendance_aggr temp
			left join kpi.ms_actor_master promoter on temp.actor_type_n = promoter.actor_type_n and temp.actor_key_v = promoter.actor_id_v
			left join kpi.ms_lookup_master clus on clus.lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) and temp.source_key_v = clus.lookup_name_v
			left join kpi.ms_metrics_master checkin on temp.metrics_key_v = checkin.ext_reference_id_v and checkin.metrics_type_n = 26
		where 
			temp.file_id_n = ?
			and temp.actor_type_n = 15
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
	left join kpi.tr_daily_dssf_attendance_aggr daily1 on temp1.id_n = daily1.day_id_n
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
	
	
