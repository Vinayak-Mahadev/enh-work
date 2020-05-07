delete from kpi.tr_daily_secondary_mobo_aggr;
delete from kpi.tr_monthly_secondary_mobo_aggr;
delete from kpi.tr_temp_secondary_mobo_aggr;
delete from kpi.tr_temp_secondary_mobo_validation;



select * from kpi.tr_daily_secondary_mobo_aggr order by 1;
select * from kpi.tr_monthly_secondary_mobo_aggr order by 1;
select * from kpi.tr_temp_secondary_mobo_aggr order by 1;
select * from kpi.tr_temp_secondary_mobo_validation order by 2;
select * from kpi.tr_temp_upload_aggr_failure order by 1;
