--1178_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_cross_data_aggr where day_id_n in (select distinct(id_n) from kpi.tr_temp_cross_data_aggr where file_id_n = ?);