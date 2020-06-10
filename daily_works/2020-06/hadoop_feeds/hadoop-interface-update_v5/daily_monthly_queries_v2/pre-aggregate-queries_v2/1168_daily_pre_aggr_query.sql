--1168_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_daily_sso_aggr where day_id_n in (select distinct(id_n) from kpi.tr_temp_daily_sso_aggr where file_id_n = ?);
