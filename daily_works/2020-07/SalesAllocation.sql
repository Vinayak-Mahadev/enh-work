
/*
----------------------------------------------------------------
ID   | Name
----------------------------------------------------------------	      
1001 | INTFS001A - Sales Allocation (Starter Pack - Stock Dump)
1003 | INTFS001B - Sales Allocation (Starter Pack - Alloc Dump)
1005 | INTFS002A - Sales Allocation (Vouchers - Stock Dump)
1007 | INTFS002B - Sales Allocation (Vouchers - Alloc Dump)
----------------------------------------------------------------   
*/



--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 1001 | INTFS001A - Sales Allocation (Starter Pack - Stock Dump)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- SP_STOCK_DUMP

-- select
select * from interface.sp_stock_dump where file_id in (select file_id_n::character varying from interface.tr_interface_file_summary where interface_id_n = 1001 and uploaded_on_dt  < current_date - 100);

-- delete
delete from interface.sp_stock_dump where file_id in (select file_id_n::character varying from interface.tr_interface_file_summary where interface_id_n = 1001 and uploaded_on_dt  < current_date - 100);

-- TR_INTERFACE_FILE_SUMMARY

-- select
select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1001 and uploaded_on_dt  < current_date - 100;

-- delete
delete from interface.tr_interface_file_summary where interface_id_n = 1001 and uploaded_on_dt  < current_date - 100;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 1003 | INTFS001B - Sales Allocation (Starter Pack - Alloc Dump)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- SP_ALLOC_DUMP

-- select
select * from interface.sp_alloc_dump where file_id in (select file_id_n::character varying from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100);

-- delete
delete from interface.sp_alloc_dump where file_id in (select file_id_n::character varying from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100);

-- TR_INTERFACE_FILE_SUMMARY

-- select
select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100;

-- delete
delete from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 1005 | INTFS002A - Sales Allocation (Vouchers - Stock Dump)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- STOCK_DUMP_VOUCHER

-- select
select * from interface.stock_dump_voucher where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100);

-- delete
delete from interface.stock_dump_voucher where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100);

-- TR_INTERFACE_FILE_SUMMARY

-- select
select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100;

-- delete
delete from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 1007 | INTFS002B - Sales Allocation (Vouchers - Alloc Dump)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- ALLOC_DUMP_VOUCHER

-- select
select * from interface.alloc_dump_voucher where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100);

-- delete
delete from interface.alloc_dump_voucher where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100);

-- TR_INTERFACE_FILE_SUMMARY

-- select
select file_id_n from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100;

-- delete
delete from interface.tr_interface_file_summary where interface_id_n = 1003 and uploaded_on_dt  < current_date - 100;

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
