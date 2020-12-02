-- REPLACE_RETENSION
drop table if exists interface.tr_cleanup_summary cascade;
-- tr_temp_site_mapping (1165)
delete
from
   interface.tr_temp_site_mapping 
where
   file_id_n in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1165 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- sp_stock_dump (1001)
delete
from
   interface.sp_stock_dump 
where
   file_id in 
   (
      select
         file_id_n::character varying 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1001 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- sp_alloc_dump (1003)
delete
from
   interface.sp_alloc_dump 
where
   file_id in 
   (
      select
         file_id_n::character varying 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1003 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- stock_dump_voucher (1005)
delete
from
   interface.stock_dump_voucher 
where
   file_id_n in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1005 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- alloc_dump_voucher (1007)
delete
from
   interface.alloc_dump_voucher 
where
   file_id_n in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1007 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- product_details (1009)
delete
from
   interface.product_details 
where
   file_id_n in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1009 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- serial_expiry (1011)
delete
from
   interface.serial_expiry 
where
   file_id in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1011 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- lacci_data (1062)
delete
from
   interface.lacci_data 
where
   file_id in 
   (
      select
         file_id_n::character varying 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1062 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- tr_temp_upload_metrics_trans_aggr (1075)
delete
from
   kpi.tr_temp_upload_metrics_trans_aggr 
where
   file_id_n in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n = 1075 
         and uploaded_on_dt < current_date - REPLACE_RETENSION
   )
;
-- tr_temp_upload_aggr_failure(1064-1071,1075-1082 =>16)
delete
from
   kpi.tr_temp_upload_aggr_failure 
where
   file_id_n in 
   (
      select
         file_id_n 
      from
         interface.tr_interface_file_summary 
      where
         interface_id_n in 
         (
            1064,
            1065,
            1066,
            1067,
            1068,
            1069,
            1070,
            1071,
            1075,
            1076,
            1077,
            1078,
            1079,
            1080,
            1081,
            1082 
         )
         and uploaded_on_dt < current_date - REPLACE_RETENSION 
   )
;