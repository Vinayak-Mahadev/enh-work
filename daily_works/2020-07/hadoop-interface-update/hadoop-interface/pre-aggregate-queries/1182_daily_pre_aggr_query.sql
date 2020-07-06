--1182_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_sp_tagging_aggr where day_id_n in (select distinct(id_n) from kpi.tr_temp_sp_tagging_aggr where file_id_n = ?);
