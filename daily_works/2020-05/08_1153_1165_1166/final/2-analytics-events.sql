-- INTERFACE EXTERNAL DATA FEED EVENT AGGREGATION CONFIGURATION: 
delete from kpi.mp_event_crossell_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_table_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_queue_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_frequency_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_dimension_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_metric_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_actor_mapping where event_type_n between 301 and 303;
delete from kpi.mp_event_format_mapping where event_type_n between 301 and 303;
delete from kpi.ms_event_type_master where event_type_n between 301 and 303;

-- MS_EVENT_TYPE_MASTER:
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (301, 'Secondary MOBO', 'External', '1153');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (302, 'Mobii RGU-GA Injection', 'External', '1165');

insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (303, 'Mobii Daily SSO', 'External', '1166');

-- MP_EVENT_FORMAT_MAPPING:
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(301, 'Csv', 'MPC_CODE|DATE|ORGANIZATION_ID|DEALER_MSISDN|AMOUNT', 'Aggregation', 'DATE', 'yyyy-MM-dd', 'MPC_CODE', 10, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(302, 'Csv', 'DATE|MICRO|SITE ID|ID OUTLET|STATUS INJECTION|QTY', 'Aggregation', 'DATE', 'yyyy-MM-dd', 'SITE ID', 12, null, null);

insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(303, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT', 'Aggregation', 'DATE', 'yyyy-MM-dd', 'SITE_ID', 12, null, null);

-- MP_EVENT_ACTOR_MAPPING:
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (301, 1, 'ORGANIZATION_ID', 'MOBO Transfer', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (302, 1, 'ID OUTLET', 'RGU-GA Injection', 10, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (303, 1, 'ID_OUTLET', 'Daily SSO', 10, null, true);

-- MP_EVENT_METRIC_MAPPING:
insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (301, 1, 'ORGANIZATION_ID', 'MOBO Transfer', 21, array[111], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (302, 1, 'ID OUTLET', 'RGU-GA Injection', 21, array[112], 'Fixed', null, null, 0, true, null);

insert into kpi.mp_event_metric_mapping (event_type_n, configuration_n, metric_key_v, description_v, metrics_type_n, metrics_id_n, selection_v, condition_key_v, condition_val_v, data_flag_n, rollup_flag_bl, delimiter_v) values (303, 1, 'ID_OUTLET', 'Daily SSO', 21, array[113], 'Fixed', null, null, 0, true, null);

-- MP_EVENT_DIMENSION_MAPPING:
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (301, 1, 'AMOUNT', 'Amount', 1, null, null, 'Update', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (302, 1, 'QTY', 'Qty', 1, null, null, 'Update', true);

insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (303, 1, 'QTY', 'Qty', 1, null, null, 'Update', true);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (303, 1, 'AMOUNT', 'Amount', 2, null, null, 'Update', true);

-- MP_EVENT_FREQUENCY_MAPPING:
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (301, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (301, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (302, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (302, 1, 2);

insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (303, 1, 1);
insert into kpi.mp_event_frequency_mapping (event_type_n, configuration_n, frequency_n) values (303, 1, 2);

-- MP_EVENT_TABLE_MAPPING:
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (301, 1, 'kpi.tr_daily_secondary_mobo_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (301, 2, 'kpi.tr_monthly_secondary_mobo_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (302, 1, 'kpi.tr_daily_mobii_rguga_injection_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (302, 2, 'kpi.tr_monthly_mobii_rguga_injection_aggr', false);

insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (303, 1, 'kpi.tr_daily_mobii_daily_sso_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (303, 2, 'kpi.tr_monthly_mobii_daily_sso_aggr', false);

-- INTERFACE EVENT AGGREGATION TABLES:
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

-- MOBII RGU-GA INJECTION:
drop table if exists kpi.tr_daily_mobii_rguga_injection_aggr cascade;
create table kpi.tr_daily_mobii_rguga_injection_aggr
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
	constraint tr_daily_mobii_rguga_injection_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_mobii_rguga_injection_aggr cascade;
create table kpi.tr_monthly_mobii_rguga_injection_aggr
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
	constraint tr_monthly_mobii_rguga_injection_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- MOBII DAILY SSO:
drop table if exists kpi.tr_daily_mobii_daily_sso_aggr cascade;
create table kpi.tr_daily_mobii_daily_sso_aggr
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
	constraint tr_daily_mobii_daily_sso_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

drop table if exists kpi.tr_monthly_mobii_daily_sso_aggr cascade;
create table kpi.tr_monthly_mobii_daily_sso_aggr
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
	constraint tr_monthly_mobii_daily_sso_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- INTERFACE EVENT TEMPORARY TABLES:
drop table if exists kpi.tr_temp_secondary_mobo_aggr cascade;
drop table if exists kpi.tr_temp_mobii_rguga_injection_aggr cascade;
drop table if exists kpi.tr_temp_mobii_daily_sso_aggr cascade;

drop sequence if exists kpi.temp_secondary_mobo_aggr_seq cascade;
drop sequence if exists kpi.temp_mobii_rguga_injection_aggr_seq cascade;
drop sequence if exists kpi.temp_mobii_daily_sso_aggr_seq cascade;

create sequence kpi.temp_secondary_mobo_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create sequence kpi.temp_mobii_rguga_injection_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create sequence kpi.temp_mobii_daily_sso_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;

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

create table kpi.tr_temp_mobii_rguga_injection_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_mobii_rguga_injection_aggr_seq'::regclass),
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
	data_string_v			text,
	message_v				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint temp_mobii_rguga_injection_aggr_seq_pkey primary key (temp_id_n)
);

create table kpi.tr_temp_mobii_daily_sso_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_mobii_daily_sso_aggr_seq'::regclass),
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
	constraint temp_mobii_daily_sso_aggr_seq_pkey primary key (temp_id_n)
);

-- INTERFACE EVENT TEMPORARY TABLES:
drop table if exists kpi.tr_temp_secondary_mobo_validation cascade;
create table kpi.tr_temp_secondary_mobo_validation
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_temp_secondary_mobo_validation_pkey primary key (validation_str_v)
);

drop table if exists kpi.tr_temp_mobii_rguga_injection_validation cascade;
create table kpi.tr_temp_mobii_rguga_injection_validation
(
  validation_str_v character varying(50) not null,
  created_dt date not null default now(),
  constraint tr_temp_mobii_rguga_injection_validation_pkey primary key (validation_str_v)
);

drop table if exists kpi.tr_temp_mobii_daily_sso_validation cascade;
create table kpi.tr_temp_mobii_daily_sso_validation
(
  validation_str_v character varying(50) not null,
  created_dt date not null default now(),
  constraint tr_temp_mobii_daily_sso_validation_pkey primary key (validation_str_v)
);

-- INTERFACE EVENT AGGREGATE INDEXES:
drop index if exists kpi.tr_temp_secondary_mobo_aggr_file_idx;
create index tr_temp_secondary_mobo_aggr_file_idx on kpi.tr_temp_secondary_mobo_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_secondary_mobo_aggr_update_keys_idx;
create index tr_temp_secondary_mobo_aggr_update_keys_idx on kpi.tr_temp_secondary_mobo_aggr using btree (status_flag_n, message_v);

drop index if exists kpi.tr_temp_mobii_rguga_injection_aggr_file_idx;
create index tr_temp_mobii_rguga_injection_aggr_file_idx on kpi.tr_temp_mobii_rguga_injection_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_mobii_rguga_injection_aggr_update_keys_idx;
create index tr_temp_mobii_rguga_injection_aggr_update_keys_idx on kpi.tr_temp_mobii_rguga_injection_aggr using btree (status_flag_n, message_v);

drop index if exists kpi.tr_temp_mobii_daily_sso_aggr_file_idx;
create index tr_temp_mobii_daily_sso_aggr_file_idx on kpi.tr_temp_mobii_daily_sso_aggr using btree (file_id_n);

drop index if exists kpi.tr_temp_mobii_daily_sso_aggr_update_keys_idx;
create index tr_temp_mobii_daily_sso_aggr_update_keys_idx on kpi.tr_temp_mobii_daily_sso_aggr using btree (status_flag_n, message_v);
