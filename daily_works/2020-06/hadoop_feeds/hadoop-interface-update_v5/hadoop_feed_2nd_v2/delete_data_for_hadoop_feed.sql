delete from interface.tr_temp_site_mapping;
delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1165)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1165));
delete from interface.tr_interface_file_summary where interface_id_n in (1165);

delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1166)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1166));
delete from interface.tr_interface_file_summary where interface_id_n in (1166);
delete from kpi.tr_validate_primary_mobo_aggr;
delete from kpi.tr_daily_primary_mobo_aggr;
delete from kpi.tr_monthly_primary_mobo_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1167)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1167));
delete from interface.tr_interface_file_summary where interface_id_n in (1167);
delete from kpi.tr_validate_secondary_mobo_aggr;
delete from kpi.tr_daily_secondary_mobo_aggr;
delete from kpi.tr_monthly_secondary_mobo_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1168)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1168));
delete from interface.tr_interface_file_summary where interface_id_n in (1168);
delete from kpi.tr_validate_daily_sso_aggr;
delete from kpi.tr_daily_daily_sso_aggr;
delete from kpi.tr_monthly_daily_sso_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1169)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1169));
delete from interface.tr_interface_file_summary where interface_id_n in (1169);
delete from kpi.tr_validate_rguga_injection_aggr;
delete from kpi.tr_daily_rguga_injection_aggr;
delete from kpi.tr_monthly_rguga_injection_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1170)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1170));
delete from interface.tr_interface_file_summary where interface_id_n in (1170);
delete from kpi.tr_validate_tertiary_sales_aggr;
delete from kpi.tr_daily_tertiary_sales_aggr;
delete from kpi.tr_monthly_tertiary_sales_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1171)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1171));
delete from interface.tr_interface_file_summary where interface_id_n in (1171);
delete from kpi.tr_validate_closing_balance_aggr;
delete from kpi.tr_daily_closing_balance_aggr;
delete from kpi.tr_monthly_closing_balance_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1172)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1172));
delete from interface.tr_interface_file_summary where interface_id_n in (1172);
delete from kpi.tr_validate_total_revenue_aggr;
delete from kpi.tr_daily_total_revenue_aggr;
delete from kpi.tr_monthly_total_revenue_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1173)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1173));
delete from interface.tr_interface_file_summary where interface_id_n in (1173);
delete from kpi.tr_validate_mobo_revenue_aggr;
delete from kpi.tr_daily_mobo_revenue_aggr;
delete from kpi.tr_monthly_mobo_revenue_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1174)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1174));
delete from interface.tr_interface_file_summary where interface_id_n in (1174);
delete from kpi.tr_validate_aquisition_revenue_aggr;
delete from kpi.tr_daily_aquisition_revenue_aggr;
delete from kpi.tr_monthly_aquisition_revenue_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1175)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1175));
delete from interface.tr_interface_file_summary where interface_id_n in (1175);
delete from kpi.tr_validate_low_revenue_site_aggr;
delete from kpi.tr_daily_low_revenue_site_aggr;
delete from kpi.tr_monthly_low_revenue_site_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1176)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1176));
delete from interface.tr_interface_file_summary where interface_id_n in (1176);
delete from kpi.tr_validate_site_rguga_aggr;
delete from kpi.tr_daily_site_rguga_aggr;
delete from kpi.tr_monthly_site_rguga_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1177)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1177));
delete from interface.tr_interface_file_summary where interface_id_n in (1177);
delete from kpi.tr_validate_cross_reload_aggr;
delete from kpi.tr_daily_cross_reload_aggr;
delete from kpi.tr_monthly_cross_reload_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1178)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1178));
delete from interface.tr_interface_file_summary where interface_id_n in (1178);
delete from kpi.tr_validate_cross_data_aggr;
delete from kpi.tr_daily_cross_data_aggr;
delete from kpi.tr_monthly_cross_data_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1179)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1179));
delete from interface.tr_interface_file_summary where interface_id_n in (1179);
delete from kpi.tr_validate_outlet_program_aggr;
delete from kpi.tr_monthly_outlet_program_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1180)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1180));
delete from interface.tr_interface_file_summary where interface_id_n in (1180);
delete from kpi.tr_validate_alloc_payment_aggr;
delete from kpi.tr_monthly_alloc_payment_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1181)));
delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1181));
delete from interface.tr_interface_file_summary where interface_id_n in (1181);
delete from kpi.tr_validate_uro_20k_aggr;
delete from kpi.tr_daily_uro_20k_aggr;
delete from kpi.tr_monthly_uro_20k_aggr;



----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



