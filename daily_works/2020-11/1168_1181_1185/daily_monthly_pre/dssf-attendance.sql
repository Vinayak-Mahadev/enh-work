-- KPI.MP_EVENT_FORMAT_MAPPING:
delete from kpi.mp_event_format_mapping where event_type_n in (303,316);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(303, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, 'QSSO_STATUS', 21);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(316, 'Csv', 'DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, 'QURO_STATUS', 21);

-- KPI.MS_METRICS_TYPE_MASTER:
delete from kpi.ms_metrics_lookup_master where metrics_type_n = 26;
delete from kpi.ms_metrics_type_master where metrics_type_n = 26;

insert into kpi.ms_metrics_type_master(metrics_type_n, metrics_type_name_v, system_type_v) values (26, 'Check-In Type', 'Custom');
insert into kpi.ms_metrics_lookup_master(metrics_type_n, reference_path_v, input_type_v, input_column_v, output_type_v, output_column_v, reference_type_n) values (26, 'select ? from kpi.ms_metrics_master where metrics_type_n = 26 and status_n in (1,174) and ?', 'string', 'ext_reference_id_v', 'numeric', 'metrics_id_n', null);

-- KPI.MS_ACTOR_LOOKUP_MASTER:
delete from kpi.ms_actor_lookup_master where actor_type_n = 7;
insert into kpi.ms_actor_lookup_master(actor_type_n, reference_path_v, input_type_v, input_column_v, output_type_v, output_column_v, reference_type_n) values (7, 'select ? as actor_id_n from kpi.ms_actor_master where actor_type_n = 7 and ?', 'string', 'actor_id_v', 'numeric', 'aggregation_id_n', null);

-- KPI.MS_ACTOR_TYPE_MASTER:
delete from kpi.ms_actor_lookup_master where actor_type_n = 15;
delete from kpi.ms_actor_type_master where actor_type_n = 15;

insert into kpi.ms_actor_type_master(actor_type_n, actor_type_name_v, description_v, system_type_v) values (15,'Generic Promoter', 'Promotor Lookup', 'External');
insert into kpi.ms_actor_lookup_master(actor_type_n, reference_path_v, input_type_v, input_column_v, output_type_v, output_column_v, reference_type_n) values (15,'select ? as actor_id_n from kpi.ms_actor_master where actor_type_n = 15 and ?', 'string', 'actor_id_v', 'numeric', 'aggregation_id_n', null);

-- DSSF ATTENDANCE(INTHDP023): (Event Type - 319)
delete from kpi.mp_event_crossell_mapping where event_type_n = 319;
delete from kpi.mp_event_table_mapping where event_type_n = 319;
delete from kpi.mp_event_queue_mapping where event_type_n = 319;
delete from kpi.mp_event_frequency_mapping where event_type_n = 319;
delete from kpi.mp_event_dimension_mapping where event_type_n = 319;
delete from kpi.mp_event_metric_mapping where event_type_n = 319;
delete from kpi.mp_event_actor_mapping where event_type_n = 319;
delete from kpi.mp_event_format_mapping where event_type_n = 319;
delete from kpi.ms_event_type_master where event_type_n = 319;

-- KPI.MS_EVENT_TYPE_MASTER:
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (319, 'DSSF Attendance', 'Hadoop', '1184');

-- KPI.MP_EVENT_FORMAT_MAPPING:
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(319, 'Csv', 'DATE|CLUSTER|PROGMOTER_ORG_ID|CHECKIN_TYPE|QTY', 'Aggregation', 'DATE', 'yyyyMMdd', 'CLUSTER', 13, null, null);

-- KPI.MP_EVENT_ACTOR_MAPPING:
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (319, 1, 'PROGMOTER_ORG_ID', 'Promoter', 15, null, true);

-- KPI.MP_EVENT_METRIC_MAPPING:
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (319, 1, 'CHECKIN_TYPE', 'Check-In Type', 26, null, 'Default', null, null, 0, true, null);

-- KPI.MP_EVENT_DIMENSION_MAPPING:
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v) values (319, 1, 'QTY', 'No.of Visits', 1, null, null, 'Replace');

-- KPI.MP_EVENT_FREQUENCY_MAPPING:
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (319, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (319, 1, 2);

-- KPI.MP_EVENT_TABLE_MAPPING:
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (319, 1, 'kpi.tr_daily_dssf_attendance_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (319, 2, 'kpi.tr_monthly_dssf_attendance_aggr', false);

-- KPI.TR_DAILY_DSSF_ATTENDANCE_AGGR:
drop table if exists kpi.tr_daily_dssf_attendance_aggr cascade;
create table kpi.tr_daily_dssf_attendance_aggr
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
	constraint tr_daily_dssf_attendance_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- KPI.TR_MONTHLY_DSSF_ATTENDANCE_AGGR:
drop table if exists kpi.tr_monthly_dssf_attendance_aggr cascade;
create table kpi.tr_monthly_dssf_attendance_aggr
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
	constraint tr_monthly_dssf_attendance_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- KPI.TR_TEMP_DSSF_ATTENDANCE_AGGR:
drop table if exists kpi.tr_temp_dssf_attendance_aggr cascade;
drop sequence if exists kpi.tr_temp_dssf_attendance_aggr_seq cascade;

create sequence kpi.tr_temp_dssf_attendance_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create table kpi.tr_temp_dssf_attendance_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.tr_temp_dssf_attendance_aggr_seq'::regclass),
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
	constraint tr_temp_dssf_attendance_aggr_pkey primary key (temp_id_n)
);

-- KPI.TR_VALIDATE_DSSF_ATTENDANCE_AGGR:
drop table if exists kpi.tr_validate_dssf_attendance_aggr cascade;
create table kpi.tr_validate_dssf_attendance_aggr
(
	validation_str_v 		character varying(500) not null,
	created_dt 				date not null default now(),
	constraint tr_validate_dssf_attendance_aggr_pkey primary key (validation_str_v)
);

drop index if exists kpi.tr_temp_dssf_attendance_aggr_file_idx;
create index tr_temp_dssf_attendance_aggr_file_idx on kpi.tr_temp_dssf_attendance_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_dssf_attendance_aggr_update_keys_idx;
create index tr_temp_dssf_attendance_aggr_update_keys_idx on kpi.tr_temp_dssf_attendance_aggr using btree (status_flag_n, message_v);


