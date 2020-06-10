--1173_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_mobo_usage_aggr where day_id_n in (select distinct(id_n) from kpi.tr_temp_mobo_usage_aggr where file_id_n = ?);
