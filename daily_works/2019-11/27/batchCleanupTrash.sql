--           14097|1064
--           14542|1065
--           14305|1066
--           14129|1067
--           14488|1068
--           14372|1069
--           14361|1070
--           14203|1071
--           14089|1075
--           14064|1076
--           13934|1077
--           14090|1078
--           13936|1079
--           14810|1080
--           14477|1081
--           15184|1082



select * from  kpi.tr_temp_upload_aggr_failure  order by 1;

select * from  kpi.tr_temp_upload_aggr_failure  where file_id_n in () order by 1;


select file_id_n, interface_id_n from interface.tr_interface_file_summary where interface_id_n in (1064,1065,1066,1067,1068,1069,1070,1071,1075,1076,1077,1078,1079,1080,1081,1082) and uploaded_on_dt < current_date - 100   order by 2;

select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14097,14542,14305,14129,14488,14372,14361,14203,14089,14064,13934,14090,13936,14810,14477,15184) order by 1;

select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14097);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14542);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14305);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14129);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14488);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14372);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14361);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14203);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14089);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14064);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (13934);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14090);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (13936);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14810);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (14477);
select count(0) from  kpi.tr_temp_upload_aggr_failure  where file_id_n in (15184);





--         test.tr_temp_upload_aggr_failure(14310l).excute();
--         test.tr_temp_upload_aggr_failure(14097l).excute();
--         test.tr_temp_upload_aggr_failure(14542l).excute();
--         test.tr_temp_upload_aggr_failure(14305l).excute();
--         test.tr_temp_upload_aggr_failure(14129l).excute();
--         test.tr_temp_upload_aggr_failure(14488l).excute();
--         test.tr_temp_upload_aggr_failure(14372l).excute();
--         test.tr_temp_upload_aggr_failure(14361l).excute();
--         test.tr_temp_upload_aggr_failure(14203l).excute();
--         test.tr_temp_upload_aggr_failure(14089l).excute();
--         test.tr_temp_upload_aggr_failure(14064l).excute();
--         test.tr_temp_upload_aggr_failure(13934l).excute();
--         test.tr_temp_upload_aggr_failure(14090l).excute();
--         test.tr_temp_upload_aggr_failure(13936l).excute();
--         test.tr_temp_upload_aggr_failure(14810l).excute();
--         test.tr_temp_upload_aggr_failure(14477l).excute();
--         test.tr_temp_upload_aggr_failure(15184l).excute();

