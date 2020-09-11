select
   int_summary.interface_id_n,
   int_summary.trans_id_n,
   replace(convert_from(loread(lo_open(int_summary.orgnl_request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'), E'\n', '') as orgnl_request_data_b,
   replace(convert_from(loread(lo_open(int_summary.request_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'), E'\n', '') as request_data_b,
   int_summary.request_time_dt,
   int_summary.due_time_dt,
   convert_from(loread(lo_open(int_summary.ack_data_b::int, x'40000'::int), x'40000'::int), 'UTF8') as ack_data_b,
   int_summary.ack_time_dt,
   replace(convert_from(loread(lo_open(int_summary.orgnl_response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'), E'\n', '') as orgnl_response_data_b,
   replace(convert_from(loread(lo_open(int_summary.response_data_b::int, x'40000'::int), x'40000'::int), 'UTF8'), E'\n', '') as response_data_b,
   int_summary.response_time_dt,
   int_summary.status_n,
   int_summary.retry_count_n,
   int_summary.ref_data1_v,
   int_summary.ref_data2_n,
   int_summary.ref_data3_n,
   int_summary.ref_data4_n,
   int_summary.ref_data5_v,
   int_summary.last_updated_time_dt 
from
   interface.tr_interface_summary int_summary 
where
   int_summary.trans_id_n = ? 
order by
   1;