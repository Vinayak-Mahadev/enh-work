-- SCRIPTS TO CREATE PROCEDURES:
-- FETCH_INTERFACE_FILE_SUMMARY:
create or replace function kpi.fetch_interface_file_summary(ordertype character varying, fileids character varying, startday numeric, endday numeric)
returns table (interface_id_n numeric, interface_name_v character varying, file_id_n numeric, file_name_v character varying, uploaded_date_dt timestamp with time zone, processed_date_dt timestamp with time zone, day_id_n numeric, total_count_n numeric, success_count_n numeric, error_count_n numeric, analytics_count_n numeric, others_count_n numeric) as
$body$
declare 
	eventtable text = null;
begin
	if ordertype is null 
	then
		return query select null::numeric, null::character varying, null::numeric, null::character varying, null::timestamp, null::timestamp, null::numeric, null::numeric, null::numeric, null::numeric, null::numeric, null::numeric;
	end if;
	
	select table_name_v into eventtable from kpi.mp_event_table_mapping where event_type_n = (select event_type_n from kpi.ms_event_type_master where system_event_v = ordertype) and frequency_n = 1;
	if eventtable is null
	then
		return query select null, null, null, null,null, null;
	end if;
	
	if fileids is null and (startday is null or endday is null)
	then
		select to_char((date_trunc('month', current_date))::date,'yyyymmdd')::numeric into startday;
		select to_char(now() - '1 days'::interval, 'YYYYmmdd'::text)::numeric into endday;
	end if;
	
	return query execute format ('select interface.interface_id_n as interface_id_n, interface.name_v as interface_name_v, interface_file_summary.file_id_n as file_id_n, interface_file_summary.file_name_v as file_name_v, interface_file_summary.uploaded_on_dt as uploaded_date_dt, interface_file_summary.processed_on_dt as processed_date_dt, aggregation_query.day_id_n as day_id_n, interface_file_summary.total_count_n as total_count_n, interface_file_summary.success_count_n as success_count_n, interface_file_summary.error_count_n as success_count_n, coalesce(round(((sum(aggregation_query.dimension_1_n) filter (where aggregation_query.actor_id_n <> 0)::numeric)/100),0),0) as analytics_count_n, coalesce(round(((sum(aggregation_query.dimension_1_n) filter (where aggregation_query.actor_id_n = 0)::numeric)/100),0),0) as others_count_n from interface.tr_interface_file_summary as interface_file_summary inner join interface.ms_interface as interface on (interface.interface_id_n = interface_file_summary.interface_id_n) left join %s as aggregation_query on (aggregation_query.instance_id_n = interface_file_summary.file_id_n and case when (%s is null or %s is null) then true else aggregation_query.day_id_n between %s and %s end and aggregation_query.metrics_type_n = 13 and aggregation_query.instance_id_n = interface_file_summary.file_id_n and aggregation_query.actor_type_n = 2) where case when %s is not null then interface_file_summary.file_id_n in (select unnest(string_to_array(%s, '','')::numeric[])) else aggregation_query.day_id_n is not null end group by interface.interface_id_n, interface_name_v, file_id_n, file_name_v, uploaded_date_dt, processed_date_dt, day_id_n, total_count_n, success_count_n, error_count_n;', eventtable, quote_nullable(startday), quote_nullable(endday), quote_nullable(startday), quote_nullable(endday), quote_nullable(fileids), quote_nullable(fileids));
end;
$body$
language plpgsql;

-- UPDATE_ANALYTICS_FAILED_ROW:
create or replace function kpi.update_analytics_failed_row(tablename character varying, row_id numeric, message character varying)
returns numeric as
$body$
begin
	execute format('update kpi.%s set status_flag_n = -1, message_v = ''%s'' where temp_id_n = %s', tablename, message, row_id);
	return 0;
end;
$body$
language plpgsql;

-- INSERT_ORG_BALANCE_AGGR_TRACKER:
create or replace function kpi.insert_org_balance_aggr_tracker()
returns trigger as 
$body$  
begin	
	if (new.actor_id_n = new.source_id_n)
	then
		update 
			kpi.tr_org_balance_aggr set balance_n = new.dimension_1_n, balance_updated_time_dt = new.last_updated_time_dt
		where 
			actor_id_n = new.actor_id_n and metrics_id_n = new.metrics_id_n;
			
		if found then
			return new;
		end if;
		
		insert into kpi.tr_org_balance_aggr(actor_id_n, metrics_id_n, balance_n, balance_updated_time_dt) values (new.actor_id_n, new.metrics_id_n, new.dimension_1_n, new.last_updated_time_dt); 
	end if;	
	return new;
end;
$body$
language plpgsql;

-- CREATING TRIGGER ORG_BALANCE_AGGR_TRIGGER:
create trigger org_balance_aggr_trigger after insert or update on kpi.tr_daily_org_balance_aggr for each row execute procedure kpi.insert_org_balance_aggr_tracker();



