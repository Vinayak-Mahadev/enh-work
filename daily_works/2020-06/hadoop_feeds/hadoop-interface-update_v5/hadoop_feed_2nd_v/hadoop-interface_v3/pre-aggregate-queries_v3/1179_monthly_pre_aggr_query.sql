--1179_MONTHLY_PRE_AGGR_QUERY= 
delete from kpi.tr_monthly_outlet_program_aggr where month_id_n in (select distinct(to_char(to_date(id_n::text,'yyyymmdd'),'yyyymm')::numeric) from kpi.tr_temp_outlet_program_aggr where file_id_n = ?); 
