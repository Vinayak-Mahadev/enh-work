--1166_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_primary_mobo_aggr where day_id_n in (select distinct(day_id_n) from kpi.tr_temp_primary_mobo_aggr where file_id_n = ?);
