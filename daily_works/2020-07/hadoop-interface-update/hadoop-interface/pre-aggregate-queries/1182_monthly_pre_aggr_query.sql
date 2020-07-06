--1182_MONTHLY_PRE_AGGR_QUERY=
update
	kpi.tr_monthly_sp_tagging_aggr as monthly
set
	dimension_1_n = case when (coalesce(monthly.dimension_1_n,0) = 0) then monthly.dimension_1_n else (coalesce(monthly.dimension_1_n,0) - coalesce(daily.dimension_1_n,0)) end,
	dimension_2_n = case when (coalesce(monthly.dimension_2_n,0) = 0) then monthly.dimension_2_n else (coalesce(monthly.dimension_2_n,0) - coalesce(daily.dimension_2_n,0)) end,
	dimension_3_n = case when (coalesce(monthly.dimension_3_n,0) = 0) then monthly.dimension_3_n else (coalesce(monthly.dimension_3_n,0) - coalesce(daily.dimension_3_n,0)) end,
	dimension_4_n = case when (coalesce(monthly.dimension_4_n,0) = 0) then monthly.dimension_4_n else (coalesce(monthly.dimension_4_n,0) - coalesce(daily.dimension_4_n,0)) end,
	dimension_5_n = case when (coalesce(monthly.dimension_5_n,0) = 0) then monthly.dimension_5_n else (coalesce(monthly.dimension_5_n,0) - coalesce(daily.dimension_5_n,0)) end,
	dimension_6_n = case when (coalesce(monthly.dimension_6_n,0) = 0) then monthly.dimension_6_n else (coalesce(monthly.dimension_6_n,0) - coalesce(daily.dimension_6_n,0)) end,
	dimension_7_n = case when (coalesce(monthly.dimension_7_n,0) = 0) then monthly.dimension_7_n else (coalesce(monthly.dimension_7_n,0) - coalesce(daily.dimension_7_n,0)) end,
	dimension_8_n = case when (coalesce(monthly.dimension_8_n,0) = 0) then monthly.dimension_8_n else (coalesce(monthly.dimension_8_n,0) - coalesce(daily.dimension_8_n,0)) end,
	dimension_9_n = case when (coalesce(monthly.dimension_9_n,0) = 0) then monthly.dimension_9_n else (coalesce(monthly.dimension_9_n,0) - coalesce(daily.dimension_9_n,0)) end,
	dimension_10_n = case when (coalesce(monthly.dimension_10_n,0) = 0) then monthly.dimension_10_n else (coalesce(monthly.dimension_10_n,0) - coalesce(daily.dimension_10_n,0)) end,
	no_of_events_n = case when (coalesce(monthly.no_of_events_n,0) = 0) then monthly.no_of_events_n else (coalesce(monthly.no_of_events_n,0) - coalesce(daily.no_of_events_n,0)) end,
	last_updated_time_dt = now()
from
	(select
		to_char(to_date(day_id_n::text,'yyyymmdd'),'yyyymm')::numeric as month_id_n, 
		actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n,
		sum(dimension_1_n) as dimension_1_n,
		sum(dimension_2_n) as dimension_2_n,
		sum(dimension_3_n) as dimension_3_n,
		sum(dimension_4_n) as dimension_4_n,
		sum(dimension_5_n) as dimension_5_n,
		sum(dimension_6_n) as dimension_6_n,
		sum(dimension_7_n) as dimension_7_n,
		sum(dimension_8_n) as dimension_8_n,
		sum(dimension_9_n) as dimension_9_n,
		sum(dimension_10_n) as dimension_10_n,
		sum(no_of_events_n) as no_of_events_n,
		source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n
	from 
		kpi.tr_daily_sp_tagging_aggr
	where 
		day_id_n in (select distinct(id_n) from kpi.tr_temp_sp_tagging_aggr where file_id_n = ?)
	group by 
		month_id_n, actor_type_n, actor_id_n, event_type_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n
	) as daily 
where
	monthly.month_id_n = daily.month_id_n 
	and monthly.actor_type_n = daily.actor_type_n 
	and monthly.actor_id_n = daily.actor_id_n 
	and monthly.event_type_n = daily.event_type_n 
	and monthly.metrics_type_n = daily.metrics_type_n 
	and monthly.metrics_id_n = daily.metrics_id_n
	and monthly.source_type_n = daily.source_type_n 
	and monthly.source_id_n = daily.source_id_n
	and monthly.data_flag_n = daily.data_flag_n
	and monthly.instance_type_n = daily.instance_type_n 
	and monthly.instance_id_n = daily.instance_id_n;
