/*
select summary.trans_id_n,summary.interface_id_n,summary.ref_data1_v,summary.ref_data5_v,summary.retry_count_n,to_char(summary.request_time_dt,'yyyy-MM-dd HH24:MI:SS') as request_time_dt,to_char(summary.response_time_dt,'yyyy-MM-dd HH24:MI:SS') as response_time_dt,summary.status_n,summary.ref_data4_v,status.name_v as status_desc,case when summary.ref_data4_v = '0' then 'Success' when summary.ref_data4_v = '1' then 'Failure' else '' end as ext_sys_status,case when summary.ref_data3_n is NULL then case when summary.status_n in (:errorStatus,:callBackFailedStatus) then 1 else 0 end else 0 end as re_process from interface.tr_interface_summary summary, interface.sd_status status where summary.interface_id_n = :interfaceId and summary.status_n = status.status_n and summary.request_time_dt >= to_timestamp(:startDate,'dd-Mon-yyyy HH24:mi:sec.SSS') and summary.request_time_dt <= to_timestamp(:endDate,'dd-Mon-yyyy HH24:mi:sec.SSS')
and summary.trans_id_n = :transactionId
order by summary.last_updated_time_dt desc limit 50;

select summary.trans_id_n,summary.interface_id_n,summary.ref_data1_v,summary.ref_data5_v,summary.retry_count_n,to_char(summary.request_time_dt,'yyyy-MM-dd HH24:MI:SS') as request_time_dt,to_char(summary.response_time_dt,'yyyy-MM-dd HH24:MI:SS') as response_time_dt,summary.status_n,summary.ref_data4_v,status.name_v as status_desc,case when summary.ref_data4_v = '0' then 'Success' when summary.ref_data4_v = '1' then 'Failure' else '' end as ext_sys_status,case when summary.ref_data3_n is NULL then case when summary.status_n in (:errorStatus,:callBackFailedStatus) then 1 else 0 end else 0 end as re_process from interface.tr_interface_summary summary, interface.sd_status status where summary.interface_id_n = :interfaceId and summary.status_n = status.status_n and summary.request_time_dt >= to_timestamp(:startDate,'dd-Mon-yyyy HH24:mi:sec.SSS') and summary.request_time_dt <= to_timestamp(:endDate,'dd-Mon-yyyy HH24:mi:sec.SSS')
and (summary.ref_data1_v ilike :refData or summary.ref_data4_v ilike :refData or summary.ref_data5_v ilike :refData)
order by summary.last_updated_time_dt desc limit 50;

select summary.trans_id_n,summary.interface_id_n,summary.ref_data1_v,summary.ref_data5_v,summary.retry_count_n,to_char(summary.request_time_dt,'yyyy-MM-dd HH24:MI:SS') as request_time_dt,to_char(summary.response_time_dt,'yyyy-MM-dd HH24:MI:SS') as response_time_dt,summary.status_n,summary.ref_data4_v,status.name_v as status_desc,case when summary.ref_data4_v = '0' then 'Success' when summary.ref_data4_v = '1' then 'Failure' else '' end as ext_sys_status,case when summary.ref_data3_n is NULL then case when summary.status_n in (:errorStatus,:callBackFailedStatus) then 1 else 0 end else 0 end as re_process from interface.tr_interface_summary summary, interface.sd_status status where summary.interface_id_n = :interfaceId and summary.status_n = status.status_n and summary.request_time_dt >= to_timestamp(:startDate,'dd-Mon-yyyy HH24:mi:sec.SSS') and summary.request_time_dt <= to_timestamp(:endDate,'dd-Mon-yyyy HH24:mi:sec.SSS')
order by summary.last_updated_time_dt desc limit 50;


interfaceId
startDate
endDate
errorStatus 1302
callBackFailedStatus 1305
{
"interface": {
"interface_id": 1136,
"start_date_time": "01-Sep-2020 00:00:00.000",
"end_date_time": "02-Sep-2020 23:59:59.999",
"transaction_id": 42442
}
}
*/


SELECT
 summary.trans_id_n ,
 summary.interface_id_n ,
 summary.ref_data1_v ,
 summary.ref_data5_v ,
 summary.retry_count_n ,
 TO_CHAR(summary.request_time_dt,'yyyy-MM-dd HH24:MI:SS') AS request_time_dt ,
 TO_CHAR(summary.response_time_dt,'yyyy-MM-dd HH24:MI:SS') AS response_time_dt,
 summary.status_n ,
 summary.ref_data4_v ,
 status.name_v AS status_desc ,
 CASE WHEN summary.ref_data4_v = '0' THEN 'Success' WHEN summary.ref_data4_v = '1' THEN 'Failure' ELSE '' END AS ext_sys_status ,
 CASE WHEN summary.ref_data3_n IS NULL THEN CASE WHEN summary.status_n IN (1302,1305) THEN 1 ELSE 0 END ELSE 0 END AS re_process
FROM
 interface.tr_interface_summary summary,
 interface.sd_status status
WHERE
 summary.interface_id_n = 1008
AND summary.status_n = status.status_n
AND summary.request_time_dt >= to_timestamp('02-Jan-2020 23:59:59.999','dd-Mon-yyyy HH24:mi:sec.SSS')
AND summary.request_time_dt <= to_timestamp('02-May-2020 23:59:59.999','dd-Mon-yyyy HH24:mi:sec.SSS')
ORDER BY
 summary.last_updated_time_dt DESC limit 50;
 
 
 
 
 
 
SELECT interface.*, attr.name_v AS attribute_key, attr.value_v attribute_value FROM ( SELECT * FROM interface.ms_interface)interface LEFT JOIN ( SELECT * FROM interface.ms_interface_attr tbl_a WHERE tbl_a.name_v = 'Search Options Conf' ) attr ON interface.interface_id_n = attr.interface_id_n WHERE interface.interface_type_n = 3 ORDER BY interface.name_v; 
 
 
 
 
 
 
 
 