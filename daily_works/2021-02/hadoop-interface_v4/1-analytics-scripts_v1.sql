-- To update analytics event configuration for old hadoop interfaces: (Gopinath)
delete from kpi.mp_event_metric_mapping where event_type_n = 304;
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (304, 1, 'FLAG_ACM', 'Flag Accuumulation', 24, null, 'Default', null, null, 'INCOMING_ONLY', 0, true, null);

-- To create analytics event configuration for new hadoop interfaces: (Gopinath)
delete from kpi.mp_event_crossell_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_table_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_queue_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_frequency_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_dimension_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_metric_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_actor_mapping where event_type_n between 320 and 323;
delete from kpi.mp_event_format_mapping where event_type_n between 320 and 323;
delete from kpi.ms_event_type_master where event_type_n between 320 and 323;

-- KPI.MS_EVENT_TYPE_MASTER:
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (320, 'Serious Customer Outlet', 'Hadoop', '1186');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (321, 'Serious Customer Cluster', 'Hadoop', '1187');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (322, 'Site with Q-URO and Q-SSO', 'Hadoop', '1188');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (323, 'Cross Selling Territory Chip', 'Hadoop', '1189');

-- KPI.MP_EVENT_FORMAT_MAPPING:
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(320, 'Csv', 'DATE|SITE_ID|ORGANIZATION_ID|MICRO_CLUSTER|CLUSTER|QTY_SERIOUS', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO_CLUSTER', 13, 'SITE_ID', 27);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(321, 'Csv', 'DATE|SITE_ID|MICRO_CLUSTER|CLUSTER|QTY_SERIOUS', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO_CLUSTER', 13, 'SITE_ID', 27);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(322, 'Csv', 'DATE|CLUSTER|MICRO_CLUSTER|TOTAL_SITE_QURO|ACTUAL_SITE_QURO|TOTAL_SITE_QSSO|ACTUAL_SITE_QSSO', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO_CLUSTER', 13, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(323, 'Csv', 'DATE|CLUSTER_ID|TOTAL_RELOAD_PAKET|CROSS', 'Aggregation', 'DATE', 'yyyyMMdd', null, null, null, null);

-- KPI.MP_EVENT_ACTOR_MAPPING:
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (320, 1, 'ORGANIZATION_ID', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (320, 1, 'CLUSTER', 'Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (321, 1, 'CLUSTER', 'Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (322, 1, 'CLUSTER', 'Cluster', 13, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (323, 1, 'CLUSTER_ID', 'Cluster', 13, null, true);

-- KPI.MP_EVENT_METRIC_MAPPING:
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (320, 1, 'ORGANIZATION_ID', 'Overall', 21, array[110], 'Fixed', null, null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (321, 1, 'CLUSTER', 'Overall', 21, array[110], 'Fixed', null, null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (322, 1, 'CLUSTER', 'Overall', 21, array[110], 'Fixed', null, null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_key_v, data_flag_n, rollup_flag_bl, delimiter_v) values (323, 1, 'CLUSTER_ID', 'Overall', 21, array[110], 'Fixed', null, null, null, 0, true, null);

-- KPI.MP_EVENT_DIMENSION_MAPPING:
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (320, 1, 'QTY_SERIOUS', 'Serious Qty', 1, null, null, 'Replace');

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (321, 1, 'QTY_SERIOUS', 'Serious Qty', 1, null, null, 'Replace');

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (322, 1, 'TOTAL_SITE_QURO', 'Total Site QURO', 1, null, null, 'Replace');
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (322, 1, 'ACTUAL_SITE_QURO', 'Actual Site QURO', 2, null, null, 'Replace');
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (322, 1, 'TOTAL_SITE_QSSO', 'Total Site QSSO', 3, null, null, 'Replace');
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (322, 1, 'ACTUAL_SITE_QSSO', 'Actual Site QSSO', 4, null, null, 'Replace');

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (323, 1, 'TOTAL_RELOAD_PAKET', 'Total Reload Packet', 1, null, null, 'Replace');
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (323, 1, 'CROSS', 'Cross Reload Packet', 2, null, null, 'Replace');

-- KPI.MP_EVENT_FREQUENCY_MAPPING:
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (320, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (320, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (321, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (321, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (322, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (322, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (323, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (323, 1, 2);

-- KPI.MP_EVENT_TABLE_MAPPING:
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (320, 1, 'kpi.tr_daily_serious_outlet_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (320, 2, 'kpi.tr_monthly_serious_outlet_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (321, 1, 'kpi.tr_daily_serious_cluster_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (321, 2, 'kpi.tr_monthly_serious_cluster_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (322, 1, 'kpi.tr_daily_site_quro_qsso_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (322, 2, 'kpi.tr_monthly_site_quro_qsso_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (323, 1, 'kpi.tr_daily_cross_chip_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (323, 2, 'kpi.tr_monthly_cross_chip_aggr', false);

-- AGGREGATION TABLES:
-- SERIOUS CUSTOMER OUTLET:
drop table if exists kpi.tr_daily_serious_outlet_aggr cascade;
create table kpi.tr_daily_serious_outlet_aggr
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
	constraint tr_daily_serious_outlet_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_serious_outlet_aggr cascade;
create table kpi.tr_monthly_serious_outlet_aggr
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
	constraint tr_monthly_serious_outlet_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- SERIOUS CUSTOMER CLUSTER:
drop table if exists kpi.tr_daily_serious_cluster_aggr cascade;
create table kpi.tr_daily_serious_cluster_aggr
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
	constraint tr_daily_serious_cluster_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_serious_cluster_aggr cascade;
create table kpi.tr_monthly_serious_cluster_aggr
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
	constraint tr_monthly_serious_cluster_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- SITE WITH QURO AND QSSO:
drop table if exists kpi.tr_daily_site_quro_qsso_aggr cascade;
create table kpi.tr_daily_site_quro_qsso_aggr
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
	constraint tr_daily_site_quro_qsso_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_site_quro_qsso_aggr cascade;
create table kpi.tr_monthly_site_quro_qsso_aggr
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
	constraint tr_monthly_site_quro_qsso_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- DSSF ATTENDANCE:
drop table if exists kpi.tr_daily_cross_chip_aggr cascade;
create table kpi.tr_daily_cross_chip_aggr
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
	constraint tr_daily_cross_chip_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_cross_chip_aggr cascade;
create table kpi.tr_monthly_cross_chip_aggr
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
	constraint tr_monthly_cross_chip_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- TEMPORARY TABLES:
-- SERIOUS CUSTOMER OUTLET:
drop table if exists kpi.tr_temp_serious_outlet_aggr cascade;
drop sequence if exists kpi.tr_temp_serious_outlet_aggr_seq cascade;

create sequence kpi.tr_temp_serious_outlet_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_serious_outlet_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.tr_temp_serious_outlet_aggr_seq'::regclass),
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
	constraint tr_temp_serious_outlet_aggr_pkey primary key (temp_id_n)
);

-- SERIOUS CUSTOMER CLUSTER:
drop table if exists kpi.tr_temp_serious_cluster_aggr cascade;
drop sequence if exists kpi.tr_temp_serious_cluster_aggr_seq cascade;

create sequence kpi.tr_temp_serious_cluster_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_serious_cluster_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.tr_temp_serious_cluster_aggr_seq'::regclass),
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
	constraint tr_temp_serious_cluster_aggr_pkey primary key (temp_id_n)
);

-- SITE WITH Q-URO AND Q-SSO:
drop table if exists kpi.tr_temp_site_quro_qsso_aggr cascade;
drop sequence if exists kpi.tr_temp_site_quro_qsso_aggr_seq cascade;

create sequence kpi.tr_temp_site_quro_qsso_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_site_quro_qsso_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.tr_temp_site_quro_qsso_aggr_seq'::regclass),
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
	constraint tr_temp_site_quro_qsso_pkey primary key (temp_id_n)
);

-- CROSS SELLING TERRITORY CHIP:
drop table if exists kpi.tr_temp_cross_chip_aggr cascade;
drop sequence if exists kpi.tr_temp_cross_chip_aggr_seq cascade;

create sequence kpi.tr_temp_cross_chip_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_cross_chip_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.tr_temp_cross_chip_aggr_seq'::regclass),
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
	constraint tr_temp_cross_chip_pkey primary key (temp_id_n)
);

-- VALIDATION TABLES:
-- SERIOUS CUSTOMER OUTLET:
drop table if exists kpi.tr_validate_serious_outlet_aggr cascade;
create table kpi.tr_validate_serious_outlet_aggr
(
	validation_str_v 		character varying(500) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_serious_outlet_aggr_pkey primary key (validation_str_v)
);

-- SERIOUS CUSTOMER CLUSTER:
drop table if exists kpi.tr_validate_serious_cluster_aggr cascade;
create table kpi.tr_validate_serious_cluster_aggr
(
	validation_str_v 		character varying(500) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_serious_cluster_aggr_pkey primary key (validation_str_v)
);

-- SITE WITH Q-URO AND Q-SSO:
drop table if exists kpi.tr_validate_site_quro_qsso_aggr cascade;
create table kpi.tr_validate_site_quro_qsso_aggr
(
	validation_str_v 		character varying(500) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_site_quro_qsso_aggr_pkey primary key (validation_str_v)
);

-- CROSS SELLING TERRITORY CHIP:
drop table if exists kpi.tr_validate_cross_chip_aggr cascade;
create table kpi.tr_validate_cross_chip_aggr
(
	validation_str_v 		character varying(500) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_cross_chip_aggr_pkey primary key (validation_str_v)
);

-- INDEX SCRIPTS:
-- SERIOUS CUSTOMER OUTLET:
drop index if exists kpi.tr_temp_serious_outlet_aggr_file_idx;
create index tr_temp_serious_outlet_aggr_file_idx on kpi.tr_temp_serious_outlet_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_serious_outlet_aggr_update_keys_idx;
create index tr_temp_serious_outlet_aggr_update_keys_idx on kpi.tr_temp_serious_outlet_aggr using btree (status_flag_n, message_v);

-- SERIOUS CUSTOMER CLUSTER:
drop index if exists kpi.tr_temp_serious_cluster_aggr_file_idx;
create index tr_temp_serious_cluster_aggr_file_idx on kpi.tr_temp_serious_cluster_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_serious_outlet_aggr_update_keys_idx;
create index tr_temp_serious_cluster_aggr_update_keys_idx on kpi.tr_temp_serious_cluster_aggr using btree (status_flag_n, message_v);

-- SITE WITH Q-URO AND Q-SSO:
drop index if exists kpi.tr_temp_site_quro_qsso_aggr_file_idx;
create index tr_temp_site_quro_qsso_aggr_file_idx on kpi.tr_temp_site_quro_qsso_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_site_quro_qsso_aggr_update_keys_idx;
create index tr_temp_site_quro_qsso_aggr_update_keys_idx on kpi.tr_temp_site_quro_qsso_aggr using btree (status_flag_n, message_v);

-- CROSS SELLING TERRITORY CHIP:
drop index if exists kpi.tr_temp_cross_chip_aggr_file_idx;
create index tr_temp_cross_chip_aggr_file_idx on kpi.tr_temp_cross_chip_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_cross_chip_aggr_update_keys_idx;
create index tr_temp_cross_chip_aggr_update_keys_idx on kpi.tr_temp_cross_chip_aggr using btree (status_flag_n, message_v);

