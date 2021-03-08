-- KPI.MP_EVENT_REFERENCE_MAPPING:
drop table if exists kpi.mp_event_reference_mapping cascade;
create table kpi.mp_event_reference_mapping
(
	event_type_n 			numeric(19,0) not null,
	configuration_n			numeric(02,0) not null default 1,
	ref_1_key_v				character varying(255) not null,
	ref_1_type_n			numeric(19,0) not null, 
	ref_1_id_n				numeric(19,0),
	ref_2_key_v				character varying(255),
	ref_2_type_n			numeric(19,0), 
	ref_2_id_n				numeric(19,0),
	ref_3_key_v				character varying(255),
	ref_3_type_n			numeric(19,0), 
	ref_3_id_n				numeric(19,0),
	ref_4_key_v				character varying(255),
	ref_4_type_n			numeric(19,0), 
	ref_4_id_n				numeric(19,0),
	ref_5_key_v				character varying(255),
	ref_5_type_n			numeric(19,0), 
	ref_5_id_n				numeric(19,0),
	last_updated_by_n		numeric(19,0) not null default 1,
	last_updated_time_dt	timestamp with time zone not null default now(),
	constraint mp_event_reference_mapping_pkey primary key (event_type_n, configuration_n),
	constraint mp_event_reference_map_event_type_fkey foreign key (event_type_n) references kpi.ms_event_type_master (event_type_n) match simple on update no action on delete no action
);

-- RGU-GA INJECTION:
alter table kpi.tr_daily_rguga_injection_aggr drop constraint if exists tr_daily_rguga_injection_aggr_pkey;
alter table kpi.tr_daily_rguga_injection_aggr drop column if exists ref_1_type_n, drop column if exists ref_1_id_n, drop column if exists ref_2_type_n, drop column if exists ref_2_id_n, drop column if exists ref_3_type_n, drop column if exists ref_3_id_n, drop column if exists ref_4_type_n, drop column if exists ref_4_id_n, drop column if exists ref_5_type_n, drop column if exists ref_5_id_n;
alter table kpi.tr_daily_rguga_injection_aggr add column ref_1_type_n numeric(19,0) not null default 0, add column ref_1_id_n numeric(19,0) not null default 0, add column ref_2_type_n numeric(19,0) not null default 0, add column ref_2_id_n numeric(19,0) not null default 0, add column ref_3_type_n numeric(19,0) not null default 0, add column ref_3_id_n numeric(19,0) not null default 0, add column ref_4_type_n numeric(19,0) not null default 0, add column ref_4_id_n numeric(19,0) not null default 0, add column ref_5_type_n numeric(19,0) not null default 0, add column ref_5_id_n numeric(19,0) not null default 0;
alter table kpi.tr_daily_rguga_injection_aggr add constraint tr_daily_rguga_injection_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, ref_1_type_n, ref_1_id_n, ref_2_type_n, ref_2_id_n, ref_3_type_n, ref_3_id_n, ref_4_type_n, ref_4_id_n, ref_5_type_n, ref_5_id_n);

alter table kpi.tr_monthly_rguga_injection_aggr drop constraint if exists tr_monthly_rguga_injection_aggr_pkey;
alter table kpi.tr_monthly_rguga_injection_aggr drop column if exists ref_1_type_n, drop column if exists ref_1_id_n, drop column if exists ref_2_type_n, drop column if exists ref_2_id_n, drop column if exists ref_3_type_n, drop column if exists ref_3_id_n, drop column if exists ref_4_type_n, drop column if exists ref_4_id_n, drop column if exists ref_5_type_n, drop column if exists ref_5_id_n;
alter table kpi.tr_monthly_rguga_injection_aggr add column ref_1_type_n numeric(19,0) not null default 0, add column ref_1_id_n numeric(19,0) not null default 0, add column ref_2_type_n numeric(19,0) not null default 0, add column ref_2_id_n numeric(19,0) not null default 0, add column ref_3_type_n numeric(19,0) not null default 0, add column ref_3_id_n numeric(19,0) not null default 0, add column ref_4_type_n numeric(19,0) not null default 0, add column ref_4_id_n numeric(19,0) not null default 0, add column ref_5_type_n numeric(19,0) not null default 0, add column ref_5_id_n numeric(19,0) not null default 0;
alter table kpi.tr_monthly_rguga_injection_aggr add constraint tr_monthly_rguga_injection_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, ref_1_type_n, ref_1_id_n, ref_2_type_n, ref_2_id_n, ref_3_type_n, ref_3_id_n, ref_4_type_n, ref_4_id_n, ref_5_type_n, ref_5_id_n);

drop table if exists kpi.tr_temp_rguga_injection_aggr cascade;
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
	data_flag_n 			numeric(02,0) not null default 0,
	instance_key_v 			character varying(255),
	instance_type_n 		numeric(19,0) not null default 0,
	instance_id_n 			numeric(19,0) not null default 0,
	ref_1_key_v				character varying(255),
	ref_1_type_n			numeric(19,0) not null default 0, 
	ref_1_id_n				numeric(19,0) not null default 0, 
	ref_2_key_v				character varying(255),
	ref_2_type_n			numeric(19,0) not null default 0, 
	ref_2_id_n				numeric(19,0) not null default 0, 
	ref_3_key_v				character varying(255),
	ref_3_type_n			numeric(19,0) not null default 0, 
	ref_3_id_n				numeric(19,0) not null default 0, 
	ref_4_key_v				character varying(255),
	ref_4_type_n			numeric(19,0) not null default 0, 
	ref_4_id_n				numeric(19,0) not null default 0, 
	ref_5_key_v				character varying(255),
	ref_5_type_n			numeric(19,0) not null default 0, 
	ref_5_id_n				numeric(19,0) not null default 0, 
	status_flag_n 			numeric(02,0) not null default 0,
	system_type_v 			character varying(10) not null default 'external'::character varying,
	correction_v 			character varying(10) not null default 'update'::character varying,
	file_id_n 				numeric(19,0) not null default 0,
	batch_number_n 			numeric(19,0) not null,
	data_string_v 			text,
	message_v 				character varying(255),
	last_updated_time_dt 	timestamp with time zone not null default now(),
	constraint tr_temp_rguga_injection_aggr_pkey primary key (temp_id_n)
);

-- Master reference scripts:
delete from kpi.mp_event_reference_mapping where event_type_n = 304;
insert into kpi.mp_event_reference_mapping(event_type_n, configuration_n, ref_1_key_v, ref_1_type_n, ref_1_id_n, ref_2_key_v, ref_2_type_n, ref_2_id_n, ref_3_key_v, ref_3_type_n, ref_3_id_n, ref_4_key_v, ref_4_type_n, ref_4_id_n, ref_5_key_v, ref_5_type_n, ref_5_id_n) values (304, 1, 'STATUS_INJECTION', 28, null, null, null, null, null, null, null, null, null, null, null, null, null);


select * from kpi.mp_event_reference_mapping where event_type_n in (select event_type_n from kpi.ms_event_type_master where system_event_v = ?);