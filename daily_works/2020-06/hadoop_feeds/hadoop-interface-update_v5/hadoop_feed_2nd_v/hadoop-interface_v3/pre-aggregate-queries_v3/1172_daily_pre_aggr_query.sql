--1172_DAILY_PRE_AGGR_QUERY= 
delete from kpi.tr_daily_total_revenue_aggr where day_id_n in (select distinct(id_n) from kpi.tr_temp_total_revenue_aggr where file_id_n = ?);
