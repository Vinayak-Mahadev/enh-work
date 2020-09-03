create temp view interface_test_by_vinay_aug_17_2020 as
select tbl2.interface_id_n, tbl2.trans_id_n, replace(convert_from(loread(lo_open(tbl2.orgnl_request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as orgnl_request_data_b, replace(convert_from(loread(lo_open(tbl2.request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as request_data_b, tbl2.request_time_dt, tbl2.due_time_dt, convert_from(loread(lo_open(tbl2.ack_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as ack_data_b, tbl2.ack_time_dt, replace(convert_from(loread(lo_open(tbl2.orgnl_response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as orgnl_response_data_b, replace(convert_from(loread(lo_open(tbl2.response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as response_data_b, tbl2.response_time_dt, tbl2.status_n, tbl2.retry_count_n, tbl2.ref_data1_v, tbl2.ref_data2_n, tbl2.ref_data3_n, tbl2.ref_data4_n, tbl2.ref_data5_v, tbl2.last_updated_time_dt from ( select distinct (tbl.interface_id_n) as interface_id, max(tbl.trans_id_n) as trans_id_n from interface.tr_interface_summary tbl where tbl.status_n = 1304 group by tbl.interface_id_n order by 2 desc ) tbl1 left join ( select * from interface.tr_interface_summary ) tbl2 on tbl1.trans_id_n = tbl2.trans_id_n order by 1;
\copy (select * from interface_test_by_vinay_aug_17_2020) to '/home/appuser/interface_summary_blob.csv' csv header  delimiter E'|' quote as E' ' null'' ;
drop view interface_test_by_vinay_aug_17_2020;


--select tbl2.interface_id_n, tbl2.trans_id_n, convert_from(loread(lo_open(tbl2.orgnl_request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as orgnl_request_data_b, convert_from(loread(lo_open(tbl2.request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as request_data_b, tbl2.request_time_dt, tbl2.due_time_dt, convert_from(loread(lo_open(tbl2.ack_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as ack_data_b, tbl2.ack_time_dt, convert_from(loread(lo_open(tbl2.orgnl_response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as orgnl_response_data_b, convert_from(loread(lo_open(tbl2.response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as response_data_b, tbl2.response_time_dt, tbl2.status_n, tbl2.retry_count_n, tbl2.ref_data1_v, tbl2.ref_data2_n, tbl2.ref_data3_n, tbl2.ref_data4_n, tbl2.ref_data5_v, tbl2.last_updated_time_dt from ( select distinct (tbl.interface_id_n) as interface_id, max(tbl.trans_id_n) as trans_id_n from interface.tr_interface_summary tbl where tbl.status_n = 1304 group by tbl.interface_id_n order by 2 desc ) tbl1 left join ( select * from interface.tr_interface_summary ) tbl2 on tbl1.trans_id_n = tbl2.trans_id_n order by 1;

--\copy (select tbl2.interface_id_n, tbl2.trans_id_n, tbl2.orgnl_request_data_b, tbl2.request_data_b, tbl2.request_time_dt, tbl2.due_time_dt, tbl2.ack_data_b, tbl2.ack_time_dt, tbl2.orgnl_response_data_b, tbl2.response_data_b, tbl2.response_time_dt, tbl2.status_n, tbl2.retry_count_n, tbl2.ref_data1_v, tbl2.ref_data2_n, tbl2.ref_data3_n, tbl2.ref_data4_n, tbl2.ref_data5_v, tbl2.last_updated_time_dt from (select distinct(tbl.interface_id_n) as interface_id, max(tbl.trans_id_n) as trans_id_n from interface.tr_interface_summary tbl where tbl.status_n = 1304 group by tbl.interface_id_n order by 2 desc) tbl1 left join (select * from interface.tr_interface_summary) tbl2 on tbl1.trans_id_n = tbl2.trans_id_n order by 1) to '/home/appuser/interface_summary.csv' csv header;

/*
select
   tbl2.interface_id_n,
   tbl2.trans_id_n,
   convert_from(loread(lo_open(tbl2.orgnl_request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as orgnl_request_data_b,
   convert_from(loread(lo_open(tbl2.request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as request_data_b,
   tbl2.request_time_dt,
   tbl2.due_time_dt,
   convert_from(loread(lo_open(tbl2.ack_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as ack_data_b,
   tbl2.ack_time_dt,
   convert_from(loread(lo_open(tbl2.orgnl_response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as orgnl_response_data_b,
   convert_from(loread(lo_open(tbl2.response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as response_data_b,
   tbl2.response_time_dt,
   tbl2.status_n,
   tbl2.retry_count_n,
   tbl2.ref_data1_v,
   tbl2.ref_data2_n,
   tbl2.ref_data3_n,
   tbl2.ref_data4_n,
   tbl2.ref_data5_v,
   tbl2.last_updated_time_dt 
from
   (
      select distinct
(tbl.interface_id_n) as interface_id,
         max(tbl.trans_id_n) as trans_id_n 
      from
         interface.tr_interface_summary tbl 
      where
         tbl.status_n = 1304 
      group by
         tbl.interface_id_n 
      order by
         2 desc
   )
   tbl1 
   left join
      (
         select
            * 
         from
            interface.tr_interface_summary 
      )
      tbl2 
      on tbl1.trans_id_n = tbl2.trans_id_n 
order by
   1;
   
*/


/* 

select
   tbl2.interface_id_n,
   tbl2.trans_id_n,
   replace(convert_from(loread(lo_open(tbl2.orgnl_request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as orgnl_request_data_b,
   replace(convert_from(loread(lo_open(tbl2.request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as request_data_b,
   tbl2.request_time_dt,
   tbl2.due_time_dt,
   convert_from(loread(lo_open(tbl2.ack_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as ack_data_b,
   tbl2.ack_time_dt,
   replace(convert_from(loread(lo_open(tbl2.orgnl_response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as orgnl_response_data_b,
   replace(convert_from(loread(lo_open(tbl2.response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'),E'\n','') as response_data_b,
   tbl2.response_time_dt,
   tbl2.status_n,
   tbl2.retry_count_n,
   tbl2.ref_data1_v,
   tbl2.ref_data2_n,
   tbl2.ref_data3_n,
   tbl2.ref_data4_n,
   tbl2.ref_data5_v,
   tbl2.last_updated_time_dt 
from
   (
      select distinct
(tbl.interface_id_n) as interface_id,
         max(tbl.trans_id_n) as trans_id_n 
      from
         interface.tr_interface_summary tbl 
      where
         tbl.status_n = 1304 
      group by
         tbl.interface_id_n 
      order by
         2 desc 
   )
   tbl1 
   left join
      (
         select
            * 
         from
            interface.tr_interface_summary 
      )
      tbl2 
      on tbl1.trans_id_n = tbl2.trans_id_n 
order by
   1;
*/