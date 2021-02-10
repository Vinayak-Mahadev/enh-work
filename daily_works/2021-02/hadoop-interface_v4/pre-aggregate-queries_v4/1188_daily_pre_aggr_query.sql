--1188_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_site_quro_qsso_aggr where day_id_n in (select distinct(id_n) from kpi.tr_temp_site_quro_qsso_aggr where file_id_n = ?);
