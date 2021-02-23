-- To configure status injection metrics master for rgu-ga injection data feed:
delete from kpi.ms_metrics_master where metrics_type_n = 28;
delete from kpi.ms_metrics_lookup_master where metrics_type_n = 28;
delete from kpi.ms_metrics_type_master where metrics_type_n = 28;

-- KPI.MS_METRICS_TYPE_MASTER:
insert into kpi.ms_metrics_type_master(metrics_type_n, metrics_type_name_v, system_type_v) values (28, 'Status Injection', 'Internal');

-- KPI.MS_METRICS_LOOKUP_MASTER:
insert into kpi.ms_metrics_lookup_master(metrics_type_n, reference_path_v, input_type_v, input_column_v, output_type_v, output_column_v, reference_type_n) values (28, 'select ? from kpi.ms_metrics_master where metrics_type_n = 28 and status_n in (1,174) and ?', 'string', 'ext_reference_id_v', 'numeric', 'metrics_id_n', null);

-- KPI.MS_METRICS_MASTER:
insert into kpi.ms_metrics_master(metrics_id_n, metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v, derived_mode_v) values (281, 'With Injection', 'With Injection', 28, 'With Injection', 'Default', null);
insert into kpi.ms_metrics_master(metrics_id_n, metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v, derived_mode_v) values (282, 'Without Injection', 'Without Injection', 28, 'Without Injection', 'Default', null);

-- KPI.MP_EVENT_METRIC_MAPPING:
delete from kpi.mp_event_metric_mapping where event_type_n = 304;
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (304, 1, 'FLAG_ACM', 'Flag Accuumulation', 24, null, 'Default', null, null, 'INCOMING_ONLY', 0, true, null);
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (304, 1, 'STATUS_INJECTION', 'Status Injection', 28, null, 'Default', null, null, 'INCOMING_ONLY', 0, true, null);

-- old
-- update interface.ms_interface_attr set value_v = '{"process_enabled":true,"fields":{"actor_field":{"10":"ID_OUTLET","13":"CLUSTER"},"metric_field":"FLAG_ACM","source_field":"MICRO","instance_field":"SITE_ID"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"table_name":"tr_temp_rguga_injection_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_rguga_injection_aggr","monthly_table":"tr_monthly_rguga_injection_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_rguga_injection_aggr","field_indexes":[0,1,2,3,4,5,6],"include-fileId":true}}' where attribute_id_n = 1169037;
-- new 
-- update interface.ms_interface_attr set value_v = '{"process_enabled":true,"fields":{"actor_field":{"10":"ID_OUTLET","13":"CLUSTER"},"metric_field":{"24":"FLAG_ACM","28":"STATUS_INJECTION"},"source_field":"MICRO","instance_field":"SITE_ID"},"error_codes":{"actor_field":"60101","metric_field":"60201","source_field":"60301","instance_field":"60401"},"pre_aggregation_conf":{"1":"monthly","2":"daily"},"table_name":"tr_temp_rguga_injection_aggr","lookup_query":"","notexist-lookup-flag":true,"daily_table":"tr_daily_rguga_injection_aggr","monthly_table":"tr_monthly_rguga_injection_aggr","no_of_join_query_params":1,"no_of_daily_join_queries":2,"no_of_monthly_join_queries":2,"duplicate_validation_conf":{"schema_name":"kpi","table_name":"tr_validate_rguga_injection_aggr","field_indexes":[0,1,2,3,4,5,6],"include-fileId":true}}' where attribute_id_n = 1169037;