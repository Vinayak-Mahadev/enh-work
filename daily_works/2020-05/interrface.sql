select * from interface.ms_interface_attr where interface_id_n = 1153;
select * from interface.ms_interface_attr where interface_id_n = 1165;
select * from interface.ms_interface_attr where interface_id_n = 1166;

select * from interface.tr_interface_file_summary where interface_id_n = 1153 order by 1 desc;
select * from interface.tr_interface_file_summary where interface_id_n = 1165 order by 1 desc;
select * from interface.tr_interface_file_summary where interface_id_n = 1166 order by 1 desc;




select * from kpi.tr_temp_secondary_mobo_validation;
select * from kpi.tr_daily_secondary_mobo_aggr order by last_updated_time_dt desc limit 10;
select * from kpi.tr_monthly_secondary_mobo_aggr order by last_updated_time_dt desc limit 10;


select * from kpi.tr_temp_mobii_daily_sso_validation;
select * from kpi.tr_monthly_mobii_daily_sso_aggr order by last_updated_time_dt desc;
select * from kpi.tr_daily_mobii_daily_sso_aggr order by last_updated_time_dt desc;

select * from kpi.tr_temp_mobii_rgu_ga_injection_validation;
select * from kpi.tr_daily_mobii_rgu_ga_injection_aggr order by last_updated_time_dt desc;
select * from kpi.tr_monthly_mobii_rgu_ga_injection_aggr order by last_updated_time_dt desc;