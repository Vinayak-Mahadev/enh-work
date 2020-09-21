-- INTERFACE DATA FEED AGGREGATION SUPPORT DATABASE SCRIPTS:
-- SCRIPTS TO CREATE SEQUENCES:
create sequence kpi.temp_upload_fail_seq increment by 1 start with 1001 minvalue 1 cache 1;
create sequence kpi.temp_upload_direct_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create sequence kpi.temp_upload_revenue_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create sequence kpi.temp_upload_orgbal_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;
create sequence kpi.temp_upload_trans_aggr_seq increment by 1 start with 1001 minvalue 1 cache 1;

-- SCRIPTS TO CREATE TABLES:
drop table if exists kpi.tr_temp_upload_aggr_failure cascade;
create table kpi.tr_temp_upload_aggr_failure
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_upload_fail_seq'::regclass),
	id_n 					numeric(8,0) not null default 0,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(750),
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
	system_type_v 			character varying(10) not null default 'External'::character varying,
	correction_v 			character varying(10) not null default 'Update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	error_code 				numeric(19,0) not null,
	error_message 			text,
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_upload_aggr_failure_pkey primary key (temp_id_n)
);

drop table if exists kpi.tr_temp_upload_metrics_direct_aggr cascade;
create table kpi.tr_temp_upload_metrics_direct_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_upload_direct_aggr_seq'::regclass),
	id_n 					numeric(08,0) not null,
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
	data_flag_n 			numeric(02,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(02,0) not null default 0,
	system_type_v 			character varying(10) not null default 'External',
	correction_v 			character varying(10) not null default 'Update',
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_upload_metrics_direct_aggr_pkey primary key (temp_id_n)
);

drop table if exists kpi.tr_temp_upload_metrics_revenue_aggr cascade;
create table kpi.tr_temp_upload_metrics_revenue_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_upload_revenue_aggr_seq'::regclass),
	id_n 					numeric(08,0) not null,
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
	dimension_4_n			numeric(19,0),
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
	data_flag_n 			numeric(02,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(02,0) not null default 0,
	system_type_v 			character varying(10) not null default 'External',
	correction_v 			character varying(10) not null default 'Update',
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_upload_metrics_revenue_aggr_pkey primary key (temp_id_n)
);

drop table if exists kpi.tr_temp_upload_metrics_orgbal_aggr cascade;
create table kpi.tr_temp_upload_metrics_orgbal_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_upload_orgbal_aggr_seq'::regclass),
	id_n 					numeric(08,0) not null,
	actor_key_v 			character varying(255),
	actor_type_n 			numeric(19,0) not null default 0,
	actor_id_n 				numeric(19,0) not null default 0,
	event_type_n 			numeric(19,0) not null default 0,
	metrics_key_v 			character varying(255),
	metrics_type_n 			numeric(19,0) not null default 0,
	metrics_id_n 			numeric(19,0) not null default 0,
	dimension_1_n 			numeric(19,0),
	dimension_2_n 			numeric(19,0),
	dimension_3_n			numeric(19,0),
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
	data_flag_n 			numeric(02,0) not null default 0,
	instance_key_v			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(02,0) not null default 0,
	system_type_v 			character varying(10) not null default 'External',
	correction_v 			character varying(10) not null default 'Update',
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_upload_metrics_orgbal_aggr_pkey primary key (temp_id_n)
);

drop table if exists kpi.tr_temp_upload_metrics_trans_aggr cascade;
create table kpi.tr_temp_upload_metrics_trans_aggr
(
	temp_id_n 				numeric(19,0) not null default nextval('kpi.temp_upload_trans_aggr_seq'::regclass),
	id_n 					numeric(08,0) not null,
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
	data_flag_n 			numeric(02,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	status_flag_n 			numeric(02,0) not null default 0,
	system_type_v 			character varying(10) not null default 'External',
	correction_v 			character varying(10) not null default 'Update',
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	acellid_v				character varying(255),
	bcellid_v				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_upload_metrics_trans_aggr_pkey primary key (temp_id_n)
);

drop table if exists kpi.tr_msisdn_product_mapping cascade;
create table kpi.tr_msisdn_product_mapping 
(
	msisdn_v 				character varying(50) not null, 
	prd_id_v 				character varying(50) not null, 
	activation_date_n 		numeric(19,0) not null,
	constraint tr_msisdn_product_mapping_pkey primary key(msisdn_v)
);

-- SCRIPTS TO CREATE INTERFACE VALIDATION SEQUENCES & TABLES: 
-- INTSM023 - Org To Org Transfer - 1064
drop table if exists kpi.tr_temp_org_to_org_validation cascade;
create table kpi.tr_temp_org_to_org_validation
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_temp_org_to_org_validation_pkey primary key (validation_str_v)
);
-- INTSM024 - Indosat to Org Transfer - 1065
drop table if exists kpi.tr_temp_indosat_to_org_validation cascade;
create table kpi.tr_temp_indosat_to_org_validation
(
	validation_str_v 		character varying(50) not null,
	created_dt 				date not null default now(),
	constraint tr_temp_indosat_to_org_validation_pkey primary key (validation_str_v)
);
-- INTSM018 - Transaction Summary -  1075
drop table if exists kpi.tr_temp_trans_sum_validation cascade;
create table kpi.tr_temp_trans_sum_validation
(
	validation_str_v 		character varying(1000) not null,
	created_dt 				date not null default now(),
	constraint tr_temp_trans_sum_validation_pkey primary key (validation_str_v)
);



