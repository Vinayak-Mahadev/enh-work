--1189_CLEANUP_PRE_AGGR_QUERY=
delete from kpi.tr_monthly_cross_chip_aggr where month_id_n in (select distinct(to_char(to_date(id_n::text,'yyyymmdd'),'yyyymm')::numeric) from kpi.tr_temp_cross_chip_aggr where file_id_n = ?) and (coalesce(dimension_1_n,0) = 0 and coalesce(dimension_2_n,0) = 0 and coalesce(dimension_3_n,0) = 0 and coalesce(dimension_4_n,0) = 0 and coalesce(dimension_5_n,0) = 0);
