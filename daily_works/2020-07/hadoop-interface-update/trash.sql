SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n between 1165 and 1182 order by inter.interface_id_n ;


delete from interface.tr_temp_site_mapping;
delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1165)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1165));
delete from interface.tr_interface_file_summary where interface_id_n in (1165);

-- INTERFACE ID	1166
-- INTERFACE NAME	INTHDP002 - Primary MOBO
-- ACTOR	MPC_CODE	-- DAILY TABLE	kpi.tr_daily_primary_mobo_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_primary_mobo_aggr
-- SOURCE		--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_primary_mobo_aggr

select * from interface.ms_interface_attr where interface_id_n = 1166 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1166 order by 1;
select * from kpi.tr_validate_primary_mobo_aggr;
select * from kpi.tr_daily_primary_mobo_aggr;
select * from kpi.tr_monthly_primary_mobo_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1166)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1166));
delete from interface.tr_interface_file_summary where interface_id_n in (1166);
delete from kpi.tr_validate_primary_mobo_aggr;
delete from kpi.tr_daily_primary_mobo_aggr;
delete from kpi.tr_monthly_primary_mobo_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1167
-- INTERFACE NAME	INTHDP003 - Secondary MOBO
-- ACTOR	ORGANIZATION_ID	-- DAILY TABLE	kpi.tr_daily_secondary_mobo_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_secondary_mobo_aggr
-- SOURCE	MPC_CODE	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_secondary_mobo_aggr

select * from interface.ms_interface_attr where interface_id_n = 1167 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1167 order by 1;
select * from kpi.tr_validate_secondary_mobo_aggr;
select * from kpi.tr_daily_secondary_mobo_aggr;
select * from kpi.tr_monthly_secondary_mobo_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1167)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1167));
delete from interface.tr_interface_file_summary where interface_id_n in (1167);
delete from kpi.tr_validate_secondary_mobo_aggr;
delete from kpi.tr_daily_secondary_mobo_aggr;
delete from kpi.tr_monthly_secondary_mobo_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1168
-- INTERFACE NAME	INTHDP004 - Daily SIM Selling Outlet (SSO)
-- ACTOR	{"12":"SITE_ID","10":"ID_OUTLET"}	-- DAILY TABLE	kpi.tr_daily_daily_sso_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_daily_sso_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_daily_sso_aggr

select * from interface.ms_interface_attr where interface_id_n = 1168 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1168 order by 1;
select * from kpi.tr_validate_daily_sso_aggr;
select * from kpi.tr_daily_daily_sso_aggr;
select * from kpi.tr_monthly_daily_sso_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1168)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1168));
delete from interface.tr_interface_file_summary where interface_id_n in (1168);
delete from kpi.tr_validate_daily_sso_aggr;
delete from kpi.tr_daily_daily_sso_aggr;
delete from kpi.tr_monthly_daily_sso_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1169
-- INTERFACE NAME	INTHDP005 - RGU-GA with Injection
-- ACTOR	{"12":"SITE_ID","10":"ID_OUTLET"}	-- DAILY TABLE	kpi.tr_daily_rguga_injection_aggr
-- METRIC	FLAG_ACM	-- MONTHLY TABLE	kpi.tr_monthly_rguga_injection_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_rguga_injection_aggr

select * from interface.ms_interface_attr where interface_id_n = 1169 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1169 order by 1;
select * from kpi.tr_validate_rguga_injection_aggr;
select * from kpi.tr_daily_rguga_injection_aggr;
select * from kpi.tr_monthly_rguga_injection_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1169)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1169));
delete from interface.tr_interface_file_summary where interface_id_n in (1169);
delete from kpi.tr_validate_rguga_injection_aggr;
delete from kpi.tr_daily_rguga_injection_aggr;
delete from kpi.tr_monthly_rguga_injection_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1170
-- INTERFACE NAME	INTHDP006 - Tertiary Sales
-- ACTOR	{"12":"SITE_ID","10":"OUTLET"}	-- DAILY TABLE	kpi.tr_daily_tertiary_sales_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_tertiary_sales_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_tertiary_sales_aggr

select * from interface.ms_interface_attr where interface_id_n = 1170 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1170 order by 1;
select * from kpi.tr_validate_tertiary_sales_aggr;
select * from kpi.tr_daily_tertiary_sales_aggr;
select * from kpi.tr_monthly_tertiary_sales_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1170)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1170));
delete from interface.tr_interface_file_summary where interface_id_n in (1170);
delete from kpi.tr_validate_tertiary_sales_aggr;
delete from kpi.tr_daily_tertiary_sales_aggr;
delete from kpi.tr_monthly_tertiary_sales_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1171
-- INTERFACE NAME	INTHDP007 - Organization MOBO Balance
-- ACTOR	ORG_CODE	-- DAILY TABLE	kpi.tr_daily_closing_balance_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_closing_balance_aggr
-- SOURCE		--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_closing_balance_aggr

select * from interface.ms_interface_attr where interface_id_n = 1171 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1171 order by 1;
select * from kpi.tr_validate_closing_balance_aggr;
select * from kpi.tr_daily_closing_balance_aggr;
select * from kpi.tr_monthly_closing_balance_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1171)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1171));
delete from interface.tr_interface_file_summary where interface_id_n in (1171);
delete from kpi.tr_validate_closing_balance_aggr;
delete from kpi.tr_daily_closing_balance_aggr;
delete from kpi.tr_monthly_closing_balance_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1172
-- INTERFACE NAME	INTHDP008 - Prepaid Service Revenue
-- ACTOR	SITE_ID	-- DAILY TABLE	kpi.tr_daily_total_revenue_aggr
-- METRIC	REVENUE_TYPE	-- MONTHLY TABLE	kpi.tr_monthly_total_revenue_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_total_revenue_aggr

select * from interface.ms_interface_attr where interface_id_n = 1172 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1172 order by 1;
select * from kpi.tr_validate_total_revenue_aggr;
select * from kpi.tr_daily_total_revenue_aggr;
select * from kpi.tr_monthly_total_revenue_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1172)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1172));
delete from interface.tr_interface_file_summary where interface_id_n in (1172);
delete from kpi.tr_validate_total_revenue_aggr;
delete from kpi.tr_daily_total_revenue_aggr;
delete from kpi.tr_monthly_total_revenue_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1173
-- INTERFACE NAME	INTHDP009 - MOBO Usage Revenue
-- ACTOR	SITE_ID	-- DAILY TABLE	kpi.tr_daily_mobo_revenue_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_mobo_revenue_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_mobo_revenue_aggr

select * from interface.ms_interface_attr where interface_id_n = 1173 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1173 order by 1;
select * from kpi.tr_validate_mobo_revenue_aggr;
select * from kpi.tr_daily_mobo_revenue_aggr;
select * from kpi.tr_monthly_mobo_revenue_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1173)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1173));
delete from interface.tr_interface_file_summary where interface_id_n in (1173);
delete from kpi.tr_validate_mobo_revenue_aggr;
delete from kpi.tr_daily_mobo_revenue_aggr;
delete from kpi.tr_monthly_mobo_revenue_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1174
-- INTERFACE NAME	INTHDP010 - Acquisition Revenue
-- ACTOR	SITE_ID	-- DAILY TABLE	kpi.tr_daily_aquisition_revenue_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_aquisition_revenue_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_aquisition_revenue_aggr

select * from interface.ms_interface_attr where interface_id_n = 1174 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1174 order by 1;
select * from kpi.tr_validate_aquisition_revenue_aggr;
select * from kpi.tr_daily_aquisition_revenue_aggr;
select * from kpi.tr_monthly_aquisition_revenue_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1174)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1174));
delete from interface.tr_interface_file_summary where interface_id_n in (1174);
delete from kpi.tr_validate_aquisition_revenue_aggr;
delete from kpi.tr_daily_aquisition_revenue_aggr;
delete from kpi.tr_monthly_aquisition_revenue_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1175
-- INTERFACE NAME	INTHDP011 - Low revenue Sites
-- ACTOR	SITE_ID	-- DAILY TABLE	kpi.tr_daily_low_revenue_site_aggr
-- METRIC	CATEGORY	-- MONTHLY TABLE	kpi.tr_monthly_low_revenue_site_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_low_revenue_site_aggr

select * from interface.ms_interface_attr where interface_id_n = 1175 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1175 order by 1;
select * from kpi.tr_validate_low_revenue_site_aggr;
select * from kpi.tr_daily_low_revenue_site_aggr;
select * from kpi.tr_monthly_low_revenue_site_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1175)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1175));
delete from interface.tr_interface_file_summary where interface_id_n in (1175);
delete from kpi.tr_validate_low_revenue_site_aggr;
delete from kpi.tr_daily_low_revenue_site_aggr;
delete from kpi.tr_monthly_low_revenue_site_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1176
-- INTERFACE NAME	INTHDP012 - Sites with RGU-GA
-- ACTOR	SITE	-- DAILY TABLE	kpi.tr_daily_site_rguga_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_site_rguga_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_site_rguga_aggr

select * from interface.ms_interface_attr where interface_id_n = 1176 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1176 order by 1;
select * from kpi.tr_validate_site_rguga_aggr;
select * from kpi.tr_daily_site_rguga_aggr;
select * from kpi.tr_monthly_site_rguga_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1176)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1176));
delete from interface.tr_interface_file_summary where interface_id_n in (1176);
delete from kpi.tr_validate_site_rguga_aggr;
delete from kpi.tr_daily_site_rguga_aggr;
delete from kpi.tr_monthly_site_rguga_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1177
-- INTERFACE NAME	INTHDP013 - Cross Selling Cluster Reload
-- ACTOR	CLUSTER_ID	-- DAILY TABLE	kpi.tr_daily_cross_reload_aggr
-- METRIC	CATEGORY	-- MONTHLY TABLE	kpi.tr_monthly_cross_reload_aggr
-- SOURCE		--INSTANCE	CLUSTER_TYPE	--VALIDATION TABLE	kpi.tr_validate_cross_reload_aggr

select * from interface.ms_interface_attr where interface_id_n = 1177 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1177 order by 1;
select * from kpi.tr_validate_cross_reload_aggr;
select * from kpi.tr_daily_cross_reload_aggr;
select * from kpi.tr_monthly_cross_reload_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1177)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1177));
delete from interface.tr_interface_file_summary where interface_id_n in (1177);
delete from kpi.tr_validate_cross_reload_aggr;
delete from kpi.tr_daily_cross_reload_aggr;
delete from kpi.tr_monthly_cross_reload_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1178
-- INTERFACE NAME	INTHDP014 - Cross Selling Area Data Package
-- ACTOR	CLUSTER_ID	-- DAILY TABLE	kpi.tr_daily_cross_data_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_cross_data_aggr
-- SOURCE		--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_cross_data_aggr

select * from interface.ms_interface_attr where interface_id_n = 1178 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1178 order by 1;
select * from kpi.tr_validate_cross_data_aggr;
select * from kpi.tr_daily_cross_data_aggr;
select * from kpi.tr_monthly_cross_data_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1178)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1178));
delete from interface.tr_interface_file_summary where interface_id_n in (1178);
delete from kpi.tr_validate_cross_data_aggr;
delete from kpi.tr_daily_cross_data_aggr;
delete from kpi.tr_monthly_cross_data_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1179
-- INTERFACE NAME	INTHDP015 - Outlet Program Achiever
-- ACTOR	ID_OUTLET	-- DAILY TABLE	kpi.
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_outlet_program_aggr
-- SOURCE	CLUSTER	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_outlet_program_aggr

select * from interface.ms_interface_attr where interface_id_n = 1179 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1179 order by 1;
select * from kpi.tr_validate_outlet_program_aggr;
select * from kpi.tr_monthly_outlet_program_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1179)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1179));
delete from interface.tr_interface_file_summary where interface_id_n in (1179);
delete from kpi.tr_validate_outlet_program_aggr;
delete from kpi.tr_monthly_outlet_program_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1180
-- INTERFACE NAME	INTHDP016 - Ontime Allocation Payment
-- ACTOR	MPC_CODE	-- DAILY TABLE	kpi.
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_alloc_payment_aggr
-- SOURCE	CLUSTER	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_alloc_payment_aggr

select * from interface.ms_interface_attr where interface_id_n = 1180 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1180 order by 1;
select * from kpi.tr_validate_alloc_payment_aggr;
select * from kpi.tr_monthly_alloc_payment_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1180)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1180));
delete from interface.tr_interface_file_summary where interface_id_n in (1180);
delete from kpi.tr_validate_alloc_payment_aggr;
delete from kpi.tr_monthly_alloc_payment_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1181
-- INTERFACE NAME	INTHDP017 - URO 20K
-- ACTOR	{"12":"SITE_ID","10":"OUTLET"}	-- DAILY TABLE	kpi.tr_daily_uro_20k_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_uro_20k_aggr
-- SOURCE	MICRO	--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_uro_20k_aggr

select * from interface.ms_interface_attr where interface_id_n = 1181 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1181 order by 1;
select * from kpi.tr_validate_uro_20k_aggr;
select * from kpi.tr_daily_uro_20k_aggr;
select * from kpi.tr_monthly_uro_20k_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1181)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1181));
delete from interface.tr_interface_file_summary where interface_id_n in (1181);
delete from kpi.tr_validate_uro_20k_aggr;
delete from kpi.tr_daily_uro_20k_aggr;
delete from kpi.tr_monthly_uro_20k_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-- INTERFACE ID	1182
-- INTERFACE NAME	INTHDP018 - Outlet sp tagging
-- ACTOR	OUTLET_ID	-- DAILY TABLE	kpi.tr_daily_sp_tagging_aggr
-- METRIC		-- MONTHLY TABLE	kpi.tr_monthly_sp_tagging_aggr
-- SOURCE		--INSTANCE		--VALIDATION TABLE	kpi.tr_validate_sp_tagging_aggr

select * from interface.ms_interface_attr where interface_id_n = 1182 order by 1;
-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;
select * from interface.tr_interface_file_summary where interface_id_n = 1182 order by 1;
select * from kpi.tr_validate_sp_tagging_aggr;
select * from kpi.tr_daily_sp_tagging_aggr;
select * from kpi.tr_monthly_sp_tagging_aggr;

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1182)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1182));
delete from interface.tr_interface_file_summary where interface_id_n in (1182);
delete from kpi.tr_validate_sp_tagging_aggr;
delete from kpi.tr_daily_sp_tagging_aggr;
delete from kpi.tr_monthly_sp_tagging_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



