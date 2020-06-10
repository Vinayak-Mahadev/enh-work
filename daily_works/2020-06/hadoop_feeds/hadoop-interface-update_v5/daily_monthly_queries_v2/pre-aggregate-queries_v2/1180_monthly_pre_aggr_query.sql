--1180_MONTHLY_PRE_AGGR_QUERY= 
delete from kpi.tr_monthly_ontime_payment_aggr where month_id_n in (select distinct(id_n) from kpi.tr_temp_ontime_payment_aggr where file_id_n = ?);
