-- INTERFACE HADOOP DATA FEED EVENT CONFIGURATION: 
delete from kpi.mp_event_crossell_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_table_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_queue_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_frequency_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_dimension_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_metric_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_actor_mapping where event_type_n between 301 and 325;
delete from kpi.mp_event_format_mapping where event_type_n between 301 and 325;
delete from kpi.ms_event_type_master where event_type_n between 301 and 325;

-- MS_EVENT_TYPE_MASTER:
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (301, 'Primary MOBO', 'Hadoop', '1166');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (302, 'Secondary MOBO', 'Hadoop', '1167');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (303, 'Daily SIM Selling Outlet (SSO)', 'Hadoop', '1168');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (304, 'RGU-GA with Injection', 'Hadoop', '1169');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (305, 'Tertiary Sales', 'Hadoop', '1170');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (306, 'Organization MOBO Balance', 'Hadoop', '1171');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (307, 'Prepaid Service Revenue', 'Hadoop', '1172');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (308, 'MOBO Usage Revenue', 'Hadoop', '1173');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (309, 'Acquisition Revenue', 'Hadoop', '1174');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (310, 'Low Revenue Sites', 'Hadoop', '1175');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (311, 'Sites with RGU-GA', 'Hadoop', '1176');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (312, 'Cross Selling Cluster Reload', 'Hadoop', '1177');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (313, 'Cross Selling Area Data Package', 'Hadoop', '1178');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (314, 'Outlet Program Achiever', 'Hadoop', '1179');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (315, 'Ontime Allocation Payment', 'Hadoop', '1180');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (316, 'URO 20K', 'Hadoop', '1181');

-- MP_EVENT_FORMAT_MAPPING:
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(301, 'Csv', 'MPC_CODE|MOBO_DATE|AMOUNT', 'Aggregation', 'MOBO_DATE', 'yyyyMMdd', null, null, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(302, 'Csv', 'MPC_CODE|DATE|ORGANIZATION_ID|DEALER_MSISDN|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'MPC_CODE', 10, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(303, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(304, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(305, 'Csv', 'DATE|MICRO|SITE_ID|OUTLET|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(306, 'Csv', 'MOBO_DATE|ORG_CODE|AMOUNT', 'Aggregation', 'MOBO_DATE', 'yyyyMMdd', null, null, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(307, 'Csv', 'DATE|MICRO|SITE_ID|REVENUE_TYPE|REVENUE_TOTAL', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(308, 'Csv', 'DATE|MICRO|SITE_ID|REVENUE', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(309, 'Csv', 'DATE|MICRO|SITE_ID|REVENUE', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(310, 'Csv', 'DATE|MICRO|SITE_ID|CATEGORY|TARGET|REVENUE', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(311, 'Csv', 'DATE|MICRO|SITE|QTY', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(312, 'Csv', 'DATE|CLUSTER_ID|CATEGORY|CLUSTER_TYPE|TOTAL_RELOAD|CROSS_RELOAD', 'Aggregation', 'DATE', 'yyyyMMdd', null, null, 'CLUSTER_TYPE', 22);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(313, 'Csv', 'DATE|CLUSTER_ID|TOTAL_DATA|CROSS_DATA', 'Aggregation', 'DATE', 'yyyyMMdd', null, null, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(314, 'Csv', 'MONTH_ID|CLUSTER|ID_OUTLET|TARGET|ACTUAL', 'Aggregation', 'MONTH_ID', 'yyyyMM', 'CLUSTER', 13, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(315, 'Csv', 'MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION', 'Aggregation', 'MONTH_ID', 'yyyyMM', 'CLUSTER', 13, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(316, 'Csv', 'DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

-- MP_EVENT_ACTOR_MAPPING:
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (301, 1, 'MPC_CODE', 'Organization', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (302, 1, 'ORGANIZATION_ID', 'Organization', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (303, 1, 'ID_OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (303, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (304, 1, 'ID_OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (304, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (305, 1, 'OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (305, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (306, 1, 'ORG_CODE', 'Organization', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (307, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (308, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (309, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (310, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (311, 1, 'MICRO', 'Micro Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (312, 1, 'CLUSTER_ID', 'Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (313, 1, 'CLUSTER_ID', 'Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (314, 1, 'ID_OUTLET', 'Organization', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (315, 1, 'MPC_CODE', 'Organization', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (316, 1, 'OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (316, 1, 'MICRO', 'Micro Cluster', 13, null, true);

-- MP_EVENT_METRIC_MAPPING:
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (301, 1, 'MPC_CODE', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (302, 1, 'ORGANIZATION_ID', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (303, 1, 'ID_OUTLET', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (304, 1, 'FLAG_ACM', 'Flag Accuumulation', 24, null, 'Default', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (305, 1, 'OUTLET', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (306, 1, 'ORG_CODE', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (307, 1, 'REVENUE_TYPE', 'Revenue Type', 25, null, 'Default', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (308, 1, 'MICRO', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (309, 1, 'MICRO', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (310, 1, 'CATEGORY', 'Category', 23, null, 'Default', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (311, 1, 'MICRO', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (312, 1, 'CATEGORY', 'Category', 23, null, 'Default', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (313, 1, 'CLUSTER_ID', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (314, 1, 'ID_OUTLET', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (315, 1, 'MPC_CODE', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (316, 1, 'OUTLET', 'Overall', 21, array[110], 'Fixed', null, null, 0, true, null);

-- MP_EVENT_DIMENSION_MAPPING:
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (301, 1, 'AMOUNT', 'Amount', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (302, 1, 'AMOUNT', 'Amount', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (303, 1, 'QTY', 'Qty', 1, null, null, 'Replace', true);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (303, 1, 'AMOUNT', 'Amount', 2, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (304, 1, 'COUNT_MSISDN', 'Msisdn Count', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (305, 1, 'AMOUNT', 'Amount', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (306, 1, 'AMOUNT', 'Amount', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (307, 1, 'REVENUE_TOTAL', 'Revenue Total', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (308, 1, 'REVENUE', 'Revenue', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (309, 1, 'REVENUE', 'Revenue', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (310, 1, 'REVENUE', 'Revenue', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (311, 1, 'QTY', 'Qty', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (312, 1, 'TOTAL_RELOAD', 'Total Reload', 1, null, null, 'Replace', true);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (312, 1, 'CROSS_RELOAD', 'Cross Reload', 2, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (313, 1, 'TOTAL_DATA', 'Total Data', 1, null, null, 'Replace', true);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (313, 1, 'CROSS_DATA', 'Cross Data', 2, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (314, 1, 'ACTUAL', 'Actual', 1, null, null, 'Replace', true);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (314, 1, 'TARGET', 'Target', 2, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (315, 1, 'PAYMENT_ALLOCATION', 'Patyment Allocation', 1, null, null, 'Replace', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (316, 1, 'HIT', 'Hit', 1, null, null, 'Replace', true);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (316, 1, 'AMOUNT', 'Amount', 2, null, null, 'Replace', true);

-- MP_EVENT_FREQUENCY_MAPPING:
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (301, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (301, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (302, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (302, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (303, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (303, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (304, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (304, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (305, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (305, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (306, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (306, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (307, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (307, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (308, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (308, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (309, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (309, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (310, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (310, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (311, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (311, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (312, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (312, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (313, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (313, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (314, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (315, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (316, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (316, 1, 2);

-- MP_EVENT_TABLE_MAPPING:
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (301, 1, 'kpi.tr_daily_primary_mobo_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (301, 2, 'kpi.tr_monthly_primary_mobo_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (302, 1, 'kpi.tr_daily_secondary_mobo_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (302, 2, 'kpi.tr_monthly_secondary_mobo_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (303, 1, 'kpi.tr_daily_daily_sso_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (303, 2, 'kpi.tr_monthly_daily_sso_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (304, 1, 'kpi.tr_daily_rguga_injection_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (304, 2, 'kpi.tr_monthly_rguga_injection_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (305, 1, 'kpi.tr_daily_tertiary_sales_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (305, 2, 'kpi.tr_monthly_tertiary_sales_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (306, 1, 'kpi.tr_daily_closing_balance_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (306, 2, 'kpi.tr_monthly_closing_balance_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (307, 1, 'kpi.tr_daily_total_usage_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (307, 2, 'kpi.tr_monthly_total_usage_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (308, 1, 'kpi.tr_daily_mobo_usage_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (308, 2, 'kpi.tr_monthly_mobo_usage_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (309, 1, 'kpi.tr_daily_total_rguga_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (309, 2, 'kpi.tr_monthly_total_rguga_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (310, 1, 'kpi.tr_daily_low_revenue_site_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (310, 2, 'kpi.tr_monthly_low_revenue_site_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (311, 1, 'kpi.tr_daily_site_rguga_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (311, 2, 'kpi.tr_monthly_site_rguga_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (312, 1, 'kpi.tr_daily_cross_reload_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (312, 2, 'kpi.tr_monthly_cross_reload_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (313, 1, 'kpi.tr_daily_cross_data_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (313, 2, 'kpi.tr_monthly_cross_data_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (314, 1, 'kpi.tr_daily_outlet_program_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (314, 2, 'kpi.tr_monthly_outlet_program_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (315, 1, 'kpi.tr_daily_ontime_payment_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (315, 2, 'kpi.tr_monthly_ontime_payment_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (316, 1, 'kpi.tr_daily_uro_20k_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (316, 2, 'kpi.tr_monthly_uro_20k_aggr', false);

-- INTERFACE EVENT AGGREGATION TABLES:
-- PRIMARY MOBO:
drop table if exists kpi.tr_daily_primary_mobo_aggr cascade;
create table kpi.tr_daily_primary_mobo_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_primary_mobo_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_primary_mobo_aggr cascade;
create table kpi.tr_monthly_primary_mobo_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_primary_mobo_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- SECONDARY MOBO:
drop table if exists kpi.tr_daily_secondary_mobo_aggr cascade;
create table kpi.tr_daily_secondary_mobo_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_secondary_mobo_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_secondary_mobo_aggr cascade;
create table kpi.tr_monthly_secondary_mobo_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_secondary_mobo_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- DAILY SSO:
drop table if exists kpi.tr_daily_daily_sso_aggr cascade;
create table kpi.tr_daily_daily_sso_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_daily_sso_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_daily_sso_aggr cascade;
create table kpi.tr_monthly_daily_sso_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_daily_sso_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- RGU-GA INJECTION:
drop table if exists kpi.tr_daily_rguga_injection_aggr cascade;
create table kpi.tr_daily_rguga_injection_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_rguga_injection_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_rguga_injection_aggr cascade;
create table kpi.tr_monthly_rguga_injection_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_rguga_injection_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- TERTIARY SALES:
drop table if exists kpi.tr_daily_tertiary_sales_aggr cascade;
create table kpi.tr_daily_tertiary_sales_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_tertiary_sales_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_tertiary_sales_aggr cascade;
create table kpi.tr_monthly_tertiary_sales_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_tertiary_sales_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- ORGANIZATION MOBO BALANCE:
drop table if exists kpi.tr_daily_closing_balance_aggr cascade;
create table kpi.tr_daily_closing_balance_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_closing_balance_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_closing_balance_aggr cascade;
create table kpi.tr_monthly_closing_balance_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_closing_balance_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- PREPAID SERVICE REVENUE:
drop table if exists kpi.tr_daily_total_usage_aggr cascade;
create table kpi.tr_daily_total_usage_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_total_usage_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_total_usage_aggr cascade;
create table kpi.tr_monthly_total_usage_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_total_usage_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- MOBO USAGE REVENUE:
drop table if exists kpi.tr_daily_mobo_usage_aggr cascade;
create table kpi.tr_daily_mobo_usage_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_mobo_usage_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_mobo_usage_aggr cascade;
create table kpi.tr_monthly_mobo_usage_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_mobo_usage_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- AQUISITION REVENUE:
drop table if exists kpi.tr_daily_total_rguga_aggr cascade;
create table kpi.tr_daily_total_rguga_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_total_rguga_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_total_rguga_aggr cascade;
create table kpi.tr_monthly_total_rguga_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_total_rguga_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- LOW REVENUE SITES:
drop table if exists kpi.tr_daily_low_revenue_site_aggr cascade;
create table kpi.tr_daily_low_revenue_site_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_low_revenue_site_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_low_revenue_site_aggr cascade;
create table kpi.tr_monthly_low_revenue_site_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_low_revenue_site_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- SITES WITH RGU-GA:
drop table if exists kpi.tr_daily_site_rguga_aggr cascade;
create table kpi.tr_daily_site_rguga_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_site_rguga_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_site_rguga_aggr cascade;
create table kpi.tr_monthly_site_rguga_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_site_rguga_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- CROSS SELLING CLUSTER RELOAD:
drop table if exists kpi.tr_daily_cross_reload_aggr cascade;
create table kpi.tr_daily_cross_reload_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_cross_reload_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_cross_reload_aggr cascade;
create table kpi.tr_monthly_cross_reload_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_cross_reload_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- CROSS SELLING AREA DATA PACKAGE:
drop table if exists kpi.tr_daily_cross_data_aggr cascade;
create table kpi.tr_daily_cross_data_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_cross_data_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_cross_data_aggr cascade;
create table kpi.tr_monthly_cross_data_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_cross_data_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- OUTLET PROGRAM ACHIEVER:
drop table if exists kpi.tr_monthly_outlet_program_aggr cascade;
create table kpi.tr_monthly_outlet_program_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_outlet_program_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- ONETIME ALLOCATION PAYMENT:
drop table if exists kpi.tr_monthly_ontime_payment_aggr cascade;
create table kpi.tr_monthly_ontime_payment_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_ontime_payment_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- URO 20K:
drop table if exists kpi.tr_daily_uro_20k_aggr cascade;
create table kpi.tr_daily_uro_20k_aggr
(
	day_id_n 				numeric(08,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n 			numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_daily_uro_20k_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_uro_20k_aggr cascade;
create table kpi.tr_monthly_uro_20k_aggr
(
	month_id_n 				numeric(06,0) not null,
	actor_type_n	 		numeric(19,0) not null,
	actor_id_n		 		numeric(19,0) not null,
	event_type_n 			numeric(19,0) not null,
	metrics_type_n	 		numeric(19,0) not null,
	metrics_id_n 			numeric(19,0) not null,
	dimension_1_n			numeric(19,0),
	dimension_2_n			numeric(19,0),
	dimension_3_n			numeric(19,0),
	dimension_4_n			numeric(19,0),
	dimension_5_n			numeric(19,0),
	dimension_6_n			numeric(19,0),
	dimension_7_n			numeric(19,0),
	dimension_8_n			numeric(19,0),
	dimension_9_n			numeric(19,0),
	dimension_10_n			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n				numeric(02,0) not null default 0,
	instance_type_n			numeric(19,0) not null default 0,
	instance_id_n			numeric(19,0) not null default 0,
	status_flag_n			numeric(02,0),
	data_string_v			text,
	system_type_v			character varying(25) not null default 'External',
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint tr_monthly_uro_20k_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- TEMPORARY AGGREGATION TABLES:
-- PRIMARY MOBO:
drop table if exists kpi.tr_temp_primary_mobo_aggr cascade;
drop sequence if exists kpi.temp_primary_mobo_aggr_seq cascade;

create sequence kpi.temp_primary_mobo_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_primary_mobo_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_primary_mobo_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_primary_mobo_aggr_pkey primary key (temp_id_n)
);

-- SECONDARY MOBO:
drop table if exists kpi.tr_temp_secondary_mobo_aggr cascade;
drop sequence if exists kpi.temp_secondary_mobo_aggr_seq cascade;

create sequence kpi.temp_secondary_mobo_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_secondary_mobo_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_secondary_mobo_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_secondary_mobo_aggr_pkey primary key (temp_id_n)
);

-- DAILY SSO:
drop table if exists kpi.tr_temp_daily_sso_aggr cascade;
drop sequence if exists kpi.temp_daily_sso_aggr_seq cascade;

create sequence kpi.temp_daily_sso_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_daily_sso_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_daily_sso_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_daily_sso_aggr_pkey primary key (temp_id_n)
);

-- RGU-GA INJECTION:
drop table if exists kpi.tr_temp_rguga_injection_aggr cascade;
drop sequence if exists kpi.temp_rguga_injection_aggr_seq cascade;

create sequence kpi.temp_rguga_injection_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_rguga_injection_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_rguga_injection_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_rguga_injection_aggr_pkey primary key (temp_id_n)
);

-- TERTIARY SALES:
drop table if exists kpi.tr_temp_tertiary_sales_aggr cascade;
drop sequence if exists kpi.temp_tertiary_sales_aggr_seq cascade;

create sequence kpi.temp_tertiary_sales_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_tertiary_sales_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_tertiary_sales_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_tertiary_sales_aggr_pkey primary key (temp_id_n)
);

-- ORGANIZATION MOBO BALANCE:
drop table if exists kpi.tr_temp_closing_balance_aggr cascade;
drop sequence if exists kpi.temp_closing_balance_aggr_seq cascade;

create sequence kpi.temp_closing_balance_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_closing_balance_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_closing_balance_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_closing_balance_aggr_pkey primary key (temp_id_n)
);

-- PREPAID SERVICE REVENUE:
drop table if exists kpi.tr_temp_total_usage_aggr cascade;
drop sequence if exists kpi.temp_total_usage_aggr_seq cascade;

create sequence kpi.temp_total_usage_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_total_usage_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_total_usage_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_total_usage_aggr_pkey primary key (temp_id_n)
);

-- MOBO USAGE REVENUE:
drop table if exists kpi.tr_temp_mobo_usage_aggr cascade;
drop sequence if exists kpi.temp_mobo_usage_aggr_seq cascade;

create sequence kpi.temp_mobo_usage_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_mobo_usage_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_mobo_usage_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_mobo_usage_aggr_pkey primary key (temp_id_n)
);

-- AQUISITION REVENUE:
drop table if exists kpi.tr_temp_total_rguga_aggr cascade;
drop sequence if exists kpi.temp_total_rguga_aggr_seq cascade;

create sequence kpi.temp_total_rguga_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_total_rguga_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_total_rguga_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_total_rguga_aggr_pkey primary key (temp_id_n)
);

-- LOW REVENUE SITES:
drop table if exists kpi.tr_temp_low_revenue_site_aggr cascade;
drop sequence if exists kpi.temp_low_revenue_site_seq cascade;

create sequence kpi.temp_low_revenue_site_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_low_revenue_site_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_low_revenue_site_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_low_revenue_site_aggr_pkey primary key (temp_id_n)
);

-- SITES WITH RGU-GA:
drop table if exists kpi.tr_temp_site_rguga_aggr cascade;
drop sequence if exists kpi.temp_site_rguga_aggr_seq cascade;

create sequence kpi.temp_site_rguga_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_site_rguga_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_site_rguga_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_site_rguga_aggr_pkey primary key (temp_id_n)
);

-- CROSS SELLING CLUSTER RELOAD:
drop table if exists kpi.tr_temp_cross_reload_aggr cascade;
drop sequence if exists kpi.temp_cross_reload_aggr_seq cascade;

create sequence kpi.temp_cross_reload_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_cross_reload_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_cross_reload_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_cross_reload_aggr_pkey primary key (temp_id_n)
);

-- CROSS SELLING AREA DATA PACKAGE:
drop table if exists kpi.tr_temp_cross_data_aggr cascade;
drop sequence if exists kpi.temp_cross_area_package_aggr_seq cascade;

create sequence kpi.temp_cross_area_package_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_cross_data_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_cross_area_package_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_cross_data_aggr_pkey primary key (temp_id_n)
);

-- OUTLET PROGRAM ACHIEVER:
drop table if exists kpi.tr_temp_outlet_program_aggr cascade;
drop sequence if exists kpi.temp_outlet_program_aggr_seq cascade;

create sequence kpi.temp_outlet_program_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_outlet_program_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_outlet_program_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_outlet_program_aggr_pkey primary key (temp_id_n)
);

-- ONETIME PAYMENT ALLOCATION:
drop table if exists kpi.tr_temp_ontime_payment_aggr cascade;
drop sequence if exists kpi.temp_ontime_payment_aggr_seq cascade;

create sequence kpi.temp_ontime_payment_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_ontime_payment_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_ontime_payment_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_ontime_payment_aggr_pkey primary key (temp_id_n)
);

-- URO 20K:
drop table if exists kpi.tr_temp_uro_20k_aggr cascade;
drop sequence if exists kpi.temp_uro_20k_aggr_seq cascade;

create sequence kpi.temp_uro_20k_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_uro_20k_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_uro_20k_aggr_seq'::regclass),
	id_n 					numeric(8,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n 			numeric(19,0),
	dimension_4_n 			numeric(19,0),
	dimension_5_n 			numeric(19,0),
	dimension_6_n 			numeric(19,0),
	dimension_7_n 			numeric(19,0),
	dimension_8_n 			numeric(19,0),
	dimension_9_n 			numeric(19,0),
	dimension_10_n 			numeric(19,0),
	no_of_events_n 			numeric(19,0) not null default 1,
	source_key_v 			character varying(255),
	source_type_n 			numeric(19,0) not null default 0,
	source_id_n 			numeric(19,0) not null default 0,
	data_flag_n 			numeric(2,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(2,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_uro_20k_aggr_pkey primary key (temp_id_n)
);

-- VALIDATION TABLES:
-- PRIMARY MOBO:
drop table if exists kpi.tr_validate_primary_mobo_aggr cascade;
create table kpi.tr_validate_primary_mobo_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_primary_mobo_aggr_pkey primary key (validation_str_v)
);

-- SECONDARY MOBO:
drop table if exists kpi.tr_validate_secondary_mobo_aggr cascade;
create table kpi.tr_validate_secondary_mobo_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_secondary_mobo_aggr_pkey primary key (validation_str_v)
);

-- DAILY SSO:
drop table if exists kpi.tr_validate_daily_sso_aggr cascade;
create table kpi.tr_validate_daily_sso_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_daily_sso_aggr_pkey primary key (validation_str_v)
);

-- RGU-GA INJECTION:
drop table if exists kpi.tr_validate_rguga_injection_aggr cascade;
create table kpi.tr_validate_rguga_injection_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_rguga_injection_aggr_pkey primary key (validation_str_v)
);

-- TERTIARY SALES:
drop table if exists kpi.tr_validate_tertiary_sales_aggr cascade;
create table kpi.tr_validate_tertiary_sales_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_tertiary_sales_aggr_pkey primary key (validation_str_v)
);

-- ORGANIZATION MOBO BALANCE:
drop table if exists kpi.tr_validate_closing_balance_aggr cascade;
create table kpi.tr_validate_closing_balance_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_closing_balance_aggr_pkey primary key (validation_str_v)
);

-- PREPAID SERVICE REVENUE:
drop table if exists kpi.tr_validate_total_usage_aggr cascade;
create table kpi.tr_validate_total_usage_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_total_usage_aggr_pkey primary key (validation_str_v)
);

-- MOBO USAGE REVENUE:
drop table if exists kpi.tr_validate_usage_revenue_aggr cascade;
create table kpi.tr_validate_usage_revenue_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_usage_revenue_aggr_pkey primary key (validation_str_v)
);

-- AQUISITION REVENUE:
drop table if exists kpi.tr_validate_total_rguga_aggr cascade;
create table kpi.tr_validate_total_rguga_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_total_rguga_aggr_pkey primary key (validation_str_v)
);

-- LOW REVENUE SITES:
drop table if exists kpi.tr_validate_low_revenue_site_aggr cascade;
create table kpi.tr_validate_low_revenue_site_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_low_revenue_site_aggr_pkey primary key (validation_str_v)
);

-- SITES WITH RGU-GA:
drop table if exists kpi.tr_validate_site_rguga_aggr cascade;
create table kpi.tr_validate_site_rguga_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_site_rguga_aggr_pkey primary key (validation_str_v)
);

-- CROSS SELLING CLUSTER RELOAD:
drop table if exists kpi.tr_validate_cross_reload_aggr cascade;
create table kpi.tr_validate_cross_reload_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_cross_reload_aggr_pkey primary key (validation_str_v)
);

-- CROSS SELLING AREA DATA PACKAGE:
drop table if exists kpi.tr_validate_cross_data_aggr cascade;
create table kpi.tr_validate_cross_data_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_cross_data_aggr_pkey primary key (validation_str_v)
);

-- OUTLET PROGRAM ACHIEVER:
drop table if exists kpi.tr_validate_outlet_program_aggr cascade;
create table kpi.tr_validate_outlet_program_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_outlet_program_aggr_pkey primary key (validation_str_v)
);

-- ONETIME ALLOCATION PAYMENT:
drop table if exists kpi.tr_validate_ontime_payment_aggr cascade;
create table kpi.tr_validate_ontime_payment_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_ontime_payment_aggr_pkey primary key (validation_str_v)
);

-- URO 20K:
drop table if exists kpi.tr_validate_uro_20k_aggr cascade;
create table kpi.tr_validate_uro_20k_aggr
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_uro_20k_aggr_pkey primary key (validation_str_v)
);

-- INDEX SCRIPTS:
-- PRIMARY MOBO:
drop index if exists kpi.tr_temp_primary_mobo_aggr_file_idx;
create index tr_temp_primary_mobo_aggr_file_idx on kpi.tr_temp_primary_mobo_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_primary_mobo_aggr_update_keys_idx;
create index tr_temp_primary_mobo_aggr_update_keys_idx on kpi.tr_temp_primary_mobo_aggr using btree (status_flag_n, message_v);

-- SECONDARY MOBO:
drop index if exists kpi.tr_temp_secondary_mobo_aggr_file_idx;
create index tr_temp_secondary_mobo_aggr_file_idx on kpi.tr_temp_secondary_mobo_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_secondary_mobo_aggr_update_keys_idx;
create index tr_temp_secondary_mobo_aggr_update_keys_idx on kpi.tr_temp_secondary_mobo_aggr using btree (status_flag_n, message_v);

-- DAILY SSO:
drop index if exists kpi.tr_temp_daily_sso_aggr_file_idx;
create index tr_temp_daily_sso_aggr_file_idx on kpi.tr_temp_daily_sso_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_daily_sso_aggr_update_keys_idx;
create index tr_temp_daily_sso_aggr_update_keys_idx on kpi.tr_temp_daily_sso_aggr using btree (status_flag_n, message_v);

-- RGU-GA INHECTION:
drop index if exists kpi.tr_temp_rguga_injection_aggr_file_idx;
create index tr_temp_secondary_rguga_injection_file_idx on kpi.tr_temp_rguga_injection_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_rguga_injection_aggr_update_keys_idx;
create index tr_temp_rguga_injection_aggr_update_keys_idx on kpi.tr_temp_rguga_injection_aggr using btree (status_flag_n, message_v);

-- TERTIARY SALES:
drop index if exists kpi.tr_temp_tertiary_sales_aggr_file_idx;
create index tr_temp_tertiary_sales_aggr_file_idx on kpi.tr_temp_tertiary_sales_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_tertiary_sales_aggr_update_keys_idx;
create index tr_temp_tertiary_sales_aggr_update_keys_idx on kpi.tr_temp_tertiary_sales_aggr using btree (status_flag_n, message_v);

-- ORGANIZATION MOBO BALANCE:
drop index if exists kpi.tr_temp_closing_balance_aggr_file_idx;
create index tr_temp_closing_balance_aggr_file_idx on kpi.tr_temp_closing_balance_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_closing_balance_aggr_update_keys_idx;
create index tr_temp_closing_balance_aggr_update_keys_idx on kpi.tr_temp_closing_balance_aggr using btree (status_flag_n, message_v);

-- PREPAID SERVICE REVENUE:
drop index if exists kpi.tr_temp_total_usage_aggr_file_idx;
create index tr_temp_total_usage_aggr_file_idx on kpi.tr_temp_total_usage_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_total_usage_aggr_update_keys_idx;
create index tr_temp_total_usage_aggr_update_keys_idx on kpi.tr_temp_total_usage_aggr using btree (status_flag_n, message_v);

-- MOBO USAGE REVENUE:
drop index if exists kpi.tr_temp_mobo_usage_aggr_file_idx;
create index tr_temp_mobo_usage_aggr_file_idx on kpi.tr_temp_mobo_usage_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_mobo_usage_aggr_update_keys_idx;
create index tr_temp_mobo_usage_aggr_update_keys_idx on kpi.tr_temp_mobo_usage_aggr using btree (status_flag_n, message_v);

-- AQUISITION REVENUE:
drop index if exists kpi.tr_temp_total_rguga_aggr_file_idx;
create index tr_temp_total_rguga_aggr_file_idx on kpi.tr_temp_total_rguga_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_total_rguga_aggr_update_keys_idx;
create index tr_temp_total_rguga_aggr_update_keys_idx on kpi.tr_temp_total_rguga_aggr using btree (status_flag_n, message_v);

-- LOW REVENUE SITES:
drop index if exists kpi.tr_temp_low_revenue_site_aggr_file_idx;
create index tr_temp_low_revenue_site_aggr_file_idx on kpi.tr_temp_low_revenue_site_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_low_revenue_site_aggr_update_keys_idx;
create index tr_temp_low_revenue_site_aggr_update_keys_idx on kpi.tr_temp_low_revenue_site_aggr using btree (status_flag_n, message_v);

-- SITES WITH RGU-GA:
drop index if exists kpi.tr_temp_site_rguga_aggr_file_idx;
create index tr_temp_site_rguga_aggr_file_idx on kpi.tr_temp_site_rguga_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_site_rguga_aggr_update_keys_idx;
create index tr_temp_site_rguga_aggr_update_keys_idx on kpi.tr_temp_site_rguga_aggr using btree (status_flag_n, message_v);

-- CROSS SELLING CLUSTER RELOAD:
drop index if exists kpi.tr_temp_cross_reload_aggr_file_idx;
create index tr_temp_cross_reload_aggr_file_idx on kpi.tr_temp_cross_reload_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_cross_reload_aggr_update_keys_idx;
create index tr_temp_cross_reload_aggr_update_keys_idx on kpi.tr_temp_cross_reload_aggr using btree (status_flag_n, message_v);

-- CROSS SELLING AREA DATA PACKAGE:
drop index if exists kpi.tr_temp_cross_data_aggr_file_idx;
create index tr_temp_cross_data_aggr_file_idx on kpi.tr_temp_cross_data_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_cross_data_aggr_update_keys_idx;
create index tr_temp_cross_data_aggr_update_keys_idx on kpi.tr_temp_cross_data_aggr using btree (status_flag_n, message_v);

-- OUTLET PROGRAM ACHIEVER:
drop index if exists kpi.tr_temp_outlet_program_aggr_file_idx;
create index tr_temp_outlet_program_aggr_file_idx on kpi.tr_temp_outlet_program_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_outlet_program_aggr_update_keys_idx;
create index tr_temp_outlet_program_aggr_update_keys_idx on kpi.tr_temp_outlet_program_aggr using btree (status_flag_n, message_v);

-- ONETIME ALLOCATION PAYMENT:
drop index if exists kpi.tr_temp_ontime_payment_aggr_file_idx;
create index tr_temp_ontime_payment_aggr_file_idx on kpi.tr_temp_ontime_payment_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_ontime_payment_aggr_update_keys_idx;
create index tr_temp_ontime_payment_aggr_update_keys_idx on kpi.tr_temp_ontime_payment_aggr using btree (status_flag_n, message_v);

-- URO 20K:
drop index if exists kpi.tr_temp_uro_20k_aggr_file_idx;
create index tr_temp_uro_20k_aggr_file_idx on kpi.tr_temp_uro_20k_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_uro_20k_aggr_update_keys_idx;
create index tr_temp_uro_20k_aggr_update_keys_idx on kpi.tr_temp_uro_20k_aggr using btree (status_flag_n, message_v);



