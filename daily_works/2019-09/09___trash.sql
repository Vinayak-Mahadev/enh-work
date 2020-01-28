
create sequence kpi.tr_temp_org_to_org_validation_seq increment 1 start 10001 minvalue 1 cache 1;

create table kpi.tr_temp_org_to_org_validation(
id_n numeric(19,0) not null default nextval('kpi.tr_temp_org_to_org_validation_seq'),
validation_str_v character varying(255) not null,
constraint tr_temp_org_to_org_validation_pkey primary key (id_n)
);

create sequence kpi.tr_temp_indosat_to_org_validation_seq increment 1 start 10001 minvalue 1 cache 1;

create table kpi.tr_temp_indosat_to_org_validation(
id_n numeric(19,0) not null default nextval('kpi.tr_temp_indosat_to_org_validation_seq'),
validation_str_v character varying not null,
constraint tr_temp_indosat_to_org_validation_pkey primary key (id_n)
);

create sequence kpi.tr_temp_transaction_summary_validation_seq increment 1 start 10001 minvalue 1 cache 1;

create table kpi.tr_temp_transaction_summary_validation (
id_n numeric(19,0) not null default nextval('kpi.tr_temp_transaction_summary_validation_seq'),
validation_str_v text not null,
constraint tr_temp_transaction_summary_validation_pkey primary key (id_n)
);








-- 1075

 UPDATE interface.ms_interface_attr Set value_v = '{"process_enabled":true,"fields":{"actor_field":"SMORGShortcode","metric_field":{"43":"TransactionType,ProductName","46":"TransactionType,ProductGroup"},"source_field":"SMORGShortcode","instance_field":"TransactionType"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"default_conf":{"field":"ProductGroup","default_value":"Default Package Type"},"table_name":"tr_temp_upload_metrics_trans_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_trans_master(?, ?);","metric_delimiter":"\\|","notexist_lookup":"metrics","no_of_params":{"metrics":1},"daily_table":"tr_daily_mobo_transaction_aggr","monthly_table":"tr_monthly_mobo_transaction_aggr","cs_daily_table":"tr_daily_mobo_crosselling_aggr","cs_monthly_table":"tr_monthly_mobo_crosselling_aggr","no_of_join_query_params":1,"cross_sell_lacci_config":{"A#CIID":"A#LACID~A#CIID","B#CIID":"B#LACID~B#CIID"},"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"data_filter":[{"field_index":2,"values":["Purchase Data Package","Indosat Reload","Voucher card injection"],"operation":"in"}],"duplicate_validation_conf":{"field_indexes":[0,9,11,4,1,2],"schema_name":"kpi","table_name":"tr_temp_transaction_summary_validation"}}' where attribute_id_n = 1075040;




-- 1064


UPDATE interface.ms_interface_attr Set value_v = '{"process_enabled":true,"fields":{"actor_field":"Credit Party ID","metric_field":"Account Type","source_field":"Organization ID","instance_field":"Transaction Channel"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":2},"daily_table":"tr_daily_org_transfer_aggr","monthly_table":"tr_monthly_org_transfer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"duplicate_validation_conf":{"field_indexes":[1],"schema_name":"kpi","table_name":"tr_temp_org_to_org_validation"}}' where attribute_id_n = 1064040;

-- 1065



UPDATE interface.ms_interface_attr Set value_v = '{"process_enabled":true,"fields":{"actor_field":"Organization ID","metric_field":"Initiator Type","source_field":"SP Operator ID","instance_field":""},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"table_name":"tr_temp_upload_metrics_revenue_aggr","lookup_query":"select * from kpi.update_aggregate_lookup_revenue_master(?, ?);","notexist_lookup":"metrics","no_of_params":{"metrics":1},"daily_table":"tr_daily_indosat_transfer_aggr","monthly_table":"tr_monthly_indosat_transfer_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":1,"no_of_monthly_join_queries":1,"duplicate_validation_conf":{"field_indexes":[3],"schema_name":"kpi","table_name":"tr_temp_indosat_to_org_validation"}}' where attribute_id_n = 1065039;