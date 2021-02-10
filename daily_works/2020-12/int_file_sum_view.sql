

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- DROP VIEW
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

drop view if exists tr_interface_file_summary_for_kpi;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- CREATE VIEW
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
create temp view tr_interface_file_summary_for_kpi as

select query.* from
   (
      select
         file_summary.interface_id_n, interface.name_v,
         case
            when    name_v ilike '%INTSM%' then 'SALDOMOBO' else
		case
            when    name_v ilike '%DAILY_DUMP%' then 'DAILY_DUMP' else
         case
            when    name_v ilike '%INTFS%' then 'FOSS'      else
         case
            when    name_v ilike '%INTHDP%'then 'HADOOP'    else
         case
            when    name_v ilike '%INTUP%' then 'UPLOAD'    else
         case
            when    name_v ilike '%IntDW%' then 'DWH'       else null  end end end end end end
         as external_system_v, file_id_n, file_name_v, validated_on_dt::date, status_n, message_v, total_count_n, success_count_n, error_count_n, filter_count_n 
      from
         interface.tr_interface_file_summary as file_summary 
         inner join
            interface.ms_interface as interface 
            on (interface.interface_id_n = file_summary.interface_id_n) 
   )
   as query left join
      (
         select
            interface_id_n, max(validated_on_dt::date) as validated_on_dt 
         from interface.tr_interface_file_summary 
         where
            ( '2020-11-17' is null or validated_on_dt::date = '2020-11-17' )
         group by
            interface_id_n
      )
      as aggr 
      on (query.interface_id_n = aggr.interface_id_n 
      and query.validated_on_dt = aggr.validated_on_dt) 
order by query.interface_id_n;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- SELECT FROM VIEW
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

select * from tr_interface_file_summary_for_kpi;

----select from tr_interface_file_summary_for_kpi where interface_id_n = 1166;
