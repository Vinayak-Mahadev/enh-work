--1179_MONTHLY_PRE_AGGR_QUERY= 
delete from kpi.tr_monthly_outlet_program_aggr where month_id_n in (select distinct(id_n) from kpi.tr_temp_outlet_program_aggr where file_id_n = ?);
