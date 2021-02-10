drop function if exists interface.fetch_file_summary(numeric[], date);

create or replace function interface.fetch_file_summary(instanceids numeric[] default null,  validateddate date default null)
returns table(interface_id_n numeric, name_v character varying, external_system_v character varying, file_id_n numeric, file_name_v character varying, validated_on_dt date, status_n numeric, message_v character varying, total_count_n numeric, success_count_n numeric, error_count_n numeric, filter_count_n numeric) as
$body$
begin
	return query 
		select query.* from
			(select 
				file_summary.interface_id_n as interfaceid, 
				interface.name_v as name, 
				case when interface.name_v ilike '%INTSM%' then 'SALDOMOBO' else case when interface.name_v ilike '%INTFS%' then 'FOSS' else case when interface.name_v ilike '%INTHDP%' then 'Hadoop' else case when interface.name_v ilike '%INTUP%' then 'UPLOAD' else case when interface.name_v ilike '%IntDW%' then 'DWH' else case when interface.name_v ilike '%DAILY_DUMP%' then 'DAILY_DUMP' else null::character varying end end end end end end as externalsystem,
				file_summary.file_id_n as fileid, 
				file_summary.file_name_v as filename, 
				file_summary.validated_on_dt::date as validateddatedt, 
				file_summary.status_n as status, 
				file_summary.message_v as message, 
				file_summary.total_count_n as totalcount, 
				file_summary.success_count_n as successcount, 
				file_summary.error_count_n as errorcount, 
				file_summary.filter_count_n as filtercount
			from
				interface.tr_interface_file_summary as file_summary
				inner join interface.ms_interface as interface on ((instanceids is null or file_summary.interface_id_n = any(instanceids)) and interface.interface_id_n = file_summary.interface_id_n)
				order by interfaceid
			) as query
			left join (select ifs.interface_id_n, max(ifs.validated_on_dt::date) as validated_on_dt from interface.tr_interface_file_summary as ifs where (instanceids is null or ifs.interface_id_n = any(instanceids)) and (validateddate is null or ifs.validated_on_dt::date = validateddate) group by ifs.interface_id_n) as aggr 
			on (query.interfaceid = aggr.interface_id_n and query.validateddatedt = aggr.validated_on_dt);
	end;
$body$
language plpgsql;

--mm-dd-yyyy
select * from interface.fetch_file_summary();
select * from interface.fetch_file_summary(array[1001, 1002, 1003]);
select * from interface.fetch_file_summary(array[1001, 1002, 1003], '12-17-2020');
select * from interface.fetch_file_summary(null, '12-17-2020');
