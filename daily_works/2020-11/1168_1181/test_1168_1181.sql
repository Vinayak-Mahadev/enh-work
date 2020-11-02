-- INTERFACE ID	1168
-- INTERFACE NAME	INTHDP004 - Daily SIM Selling Outlet (SSO)
-- ACTOR	{"10":"ID_OUTLET","12":"SITE_ID"}	-- DAILY TABLE	kpi.tr_daily_daily_sso_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_daily_sso_aggr
-- SOURCE	MICRO	--INSTANCE	QSSO_STATUS	--VALIDATION TABLE	kpi.tr_validate_daily_sso_aggr

select * from interface.ms_interface_attr where interface_id_n = 1168 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1168 order by 1;
select * from kpi.tr_validate_daily_sso_aggr;
select * from kpi.tr_daily_daily_sso_aggr;
select * from kpi.tr_monthly_daily_sso_aggr;


/*
delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1168)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1168));
delete from interface.tr_interface_file_summary where interface_id_n in (1168);
delete from kpi.tr_validate_daily_sso_aggr;
delete from kpi.tr_daily_daily_sso_aggr;
delete from kpi.tr_monthly_daily_sso_aggr;
*/



select * from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (0);


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1181
-- INTERFACE NAME	INTHDP017 - URO 20K
-- ACTOR	{"10":"OUTLET","12":"SITE_ID"}	-- DAILY TABLE	kpi.tr_daily_uro_20k_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_uro_20k_aggr
-- SOURCE	MICRO	--INSTANCE	QURO_STATUS	--VALIDATION TABLE	kpi.tr_validate_uro_20k_aggr

select * from interface.ms_interface_attr where interface_id_n = 1181 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1181 order by 1;
select * from kpi.tr_validate_uro_20k_aggr;
select * from kpi.tr_daily_uro_20k_aggr;
select * from kpi.tr_monthly_uro_20k_aggr;


/*
delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1181)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1181));
delete from interface.tr_interface_file_summary where interface_id_n in (1181);
delete from kpi.tr_validate_uro_20k_aggr;
delete from kpi.tr_daily_uro_20k_aggr;
delete from kpi.tr_monthly_uro_20k_aggr;
*/



select * from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (0);
