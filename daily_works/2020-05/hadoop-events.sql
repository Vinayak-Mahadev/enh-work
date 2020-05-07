-- INTERFACE EXTERNAL DATA FEED EVENT AGGREGATION CONFIGURATION: 
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
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (301, 'Secondary MOBO', 'Hadoop', '1153');
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (302, 'Mobii RGU-GA Injection', 'Hadoop', '1153');
insert into kpi.ms_event_type_master (event_type_n, event_type_name_v, system_type_v, system_event_v) values (303, 'Mobii Daily SSO', 'Hadoop', '1153');

-- MP_EVENT_FORMAT_MAPPING:
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(301, 'Csv', '', 'Aggregation', 'DATE', 'yyyyMMdd', 'MPC_CODE', 10, null, null);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(302, 'Csv', '', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE ID', 12, null, null);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(303, 'Csv', '', 'Aggregation', 'DATE', 'yyyyMMdd', 'SITE_ID', 12, null, null);

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
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (302, 1, 'QTY', 'Qty', 1, null, null, 'Update', false);
insert into kpi.mp_event_dimension_mapping (event_type_n, configuration_n, dimension_v, description_v, sequence_n, expression_v, procedure_v, correction_v, is_decimal_bl) values (303, 1, 'QTY', 'Qty', 1, null, null, 'Update', false);
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
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (302, 1, 'kpi.tr_daily_mobii_rgu_ga_injection_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (302, 2, 'kpi.tr_monthly_mobii_rgu_ga_injection_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (303, 1, 'kpi.tr_daily_mobii_daily_sso_aggr', false);
insert into kpi.mp_event_table_mapping(event_type_n, frequency_n, table_name_v, is_snapshot_bl) values (303, 2, 'kpi.tr_monthly_mobii_daily_sso_aggr', false);

-- INTERFACE EVENT AGGREGATION TABLES:

--
-- SECONDARY MOBO:

-- daily_table
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

-- monthly_table
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


--1153_DAILY_JOIN_QUERY_1=
SELECT 1153 as interface_id_n, temp1.id_n, daily1.day_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, CASE WHEN( temp1.dimension_1_n IS NULL AND daily1.dimension_1_n IS NULL) THEN NULL ELSE ( COALESCE(temp1.dimension_1_n, 0) + COALESCE(daily1.dimension_1_n, 0) ) END AS dimension_1_n, CASE WHEN ( temp1.dimension_2_n IS NULL AND daily1.dimension_2_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_2_n, 0) + COALESCE(daily1.dimension_2_n, 0) ) END AS dimension_2_n, CASE WHEN ( temp1.dimension_3_n IS NULL AND daily1.dimension_3_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_3_n, 0) + COALESCE(daily1.dimension_3_n, 0) ) END AS dimension_3_n, CASE WHEN ( temp1.dimension_4_n IS NULL AND daily1.dimension_4_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_4_n, 0) + COALESCE(daily1.dimension_4_n, 0) ) END AS dimension_4_n, CASE WHEN ( temp1.dimension_5_n IS NULL AND daily1.dimension_5_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_5_n, 0) + COALESCE(daily1.dimension_5_n, 0) ) END AS dimension_5_n, CASE WHEN ( temp1.dimension_6_n IS NULL AND daily1.dimension_6_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_6_n, 0) + COALESCE(daily1.dimension_6_n, 0) ) END AS dimension_6_n, CASE WHEN ( temp1.dimension_7_n IS NULL AND daily1.dimension_7_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_7_n, 0) + COALESCE(daily1.dimension_7_n, 0) ) END AS dimension_7_n, CASE WHEN ( temp1.dimension_8_n IS NULL AND daily1.dimension_8_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_8_n, 0) + COALESCE(daily1.dimension_8_n, 0) ) END AS dimension_8_n, CASE WHEN ( temp1.dimension_9_n IS NULL AND daily1.dimension_9_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_9_n, 0) + COALESCE(daily1.dimension_9_n, 0) ) END AS dimension_9_n, CASE WHEN ( temp1.dimension_10_n IS NULL AND daily1.dimension_10_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_10_n, 0) + COALESCE(daily1.dimension_10_n, 0) ) END AS dimension_10_n, CASE WHEN ( temp1.no_of_events_n IS NULL AND daily1.no_of_events_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.no_of_events_n, 0) + COALESCE(daily1.no_of_events_n, 0) ) END AS no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n FROM ( SELECT result.id_n, result.actor_type_n, result.actor_id_n, result.event_type_n, result.metrics_type_n, result.metrics_id_n, Sum(result.dimension_1_n) AS dimension_1_n, Sum(result.dimension_2_n) AS dimension_2_n, Sum(result.dimension_3_n) AS dimension_3_n, Sum(result.dimension_4_n) dimension_4_n, Sum(result.dimension_5_n) AS dimension_5_n, Sum(result.dimension_6_n) AS dimension_6_n, Sum(result.dimension_7_n) AS dimension_7_n, Sum(result.dimension_8_n) AS dimension_8_n, Sum(result.dimension_9_n) AS dimension_9_n, Sum(result.dimension_10_n) AS dimension_10_n, Sum(result.no_of_events_n) AS no_of_events_n, result.source_type_n, result.source_id_n, result.data_flag_n, result.instance_type_n, result.instance_id_n, result.file_id_n FROM ( SELECT temp.id_n, temp.actor_key_v, coalesce(( select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.actor_type_n), temp.actor_type_n) as actor_type_n, COALESCE(org.aggregation_id_n, 0) AS actor_id_n, temp.event_type_n, temp.metrics_key_v, coalesce(( select reference_type_n from kpi.ms_metrics_lookup_master where metrics_type_n = temp.metrics_type_n), temp.metrics_type_n) as metrics_type_n, COALESCE(met.metrics_id_n, 0) AS metrics_id_n, temp.dimension_1_n, temp.dimension_2_n, temp.dimension_3_n, temp.dimension_4_n, temp.dimension_5_n, temp.dimension_6_n, temp.dimension_7_n, temp.dimension_8_n, temp.dimension_9_n, temp.dimension_10_n, temp.no_of_events_n, temp.source_key_v, coalesce(( select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.source_type_n), temp.source_type_n) as source_type_n, COALESCE(dorg.aggregation_id_n, 0) AS source_id_n, temp.data_flag_n, temp.instance_key_v, coalesce(( select reference_type_n from kpi.ms_metrics_lookup_master where metrics_type_n = temp.instance_type_n), temp.instance_type_n) as instance_type_n, COALESCE(imet.metrics_id_n, 0) AS instance_id_n, temp.file_id_n, CASE WHEN temp.metrics_key_v IS NOT NULL AND COALESCE(met.metrics_id_n, 0) = 0 THEN kpi.update_analytics_failed_row('tr_temp_upload_metrics_aggr', temp.temp_id_n, 'metric_field%Metric mapping not found for metric_field : ' || temp.metrics_key_v) WHEN temp.source_key_v IS NOT NULL AND COALESCE(dorg.aggregation_id_n, 0) = 0 THEN kpi.update_analytics_failed_row('tr_temp_upload_metrics_aggr', temp.temp_id_n, 'source_field%Source mapping not found for source_field : ' || temp.source_key_v) WHEN temp.instance_key_v IS NOT NULL AND COALESCE(imet.metrics_id_n, 0) = 0 THEN kpi.update_analytics_failed_row('tr_temp_upload_metrics_aggr', temp.temp_id_n, 'instance_field%Instance mapping not found for instance_field : ' || temp.instance_key_v) ELSE 1 END AS id_flag FROM kpi.tr_temp_upload_metrics_aggr temp LEFT JOIN kpi.ms_org_master org ON temp.actor_key_v = org.ref_code_v LEFT JOIN kpi.ms_metrics_master met ON ( temp.metrics_key_v = met.ext_reference_id_v AND met.metrics_type_n = 34 ) LEFT JOIN kpi.ms_org_master dorg ON temp.source_key_v = dorg.ref_code_v LEFT JOIN kpi.ms_metrics_master imet ON ( temp.instance_key_v = imet.ext_reference_id_v AND imet.metrics_type_n = 36 ) WHERE temp.file_id_n = ? ) result where result.id_flag = 1 GROUP BY result.id_n, result.actor_type_n, result.actor_id_n, result.event_type_n, result.metrics_type_n, result.metrics_id_n, result.source_type_n, result.source_id_n, result.data_flag_n, result.instance_type_n, result.instance_id_n, result.file_id_n ) temp1 LEFT JOIN kpi.tr_daily_org_transfer_aggr daily1 ON temp1.id_n = daily1.day_id_n AND temp1.actor_type_n = daily1.actor_type_n AND temp1.actor_id_n = daily1.actor_id_n AND temp1.event_type_n = daily1.event_type_n AND temp1.metrics_type_n = daily1.metrics_type_n AND temp1.metrics_id_n = daily1.metrics_id_n AND temp1.source_type_n = daily1.source_type_n AND temp1.source_id_n = daily1.source_id_n AND temp1.data_flag_n = daily1.data_flag_n AND temp1.instance_type_n = daily1.instance_type_n AND temp1.instance_id_n = daily1.instance_id_n;
--1153_MONTHLY_JOIN_QUERY_1=
SELECT 1153 as interface_id_n, temp1.month_id_n AS id_n, monthly1.month_id_n, temp1.actor_type_n, temp1.actor_id_n, temp1.event_type_n, temp1.metrics_type_n, temp1.metrics_id_n, CASE WHEN( temp1.dimension_1_n IS NULL AND monthly1.dimension_1_n IS NULL) THEN NULL ELSE ( COALESCE(temp1.dimension_1_n, 0) + COALESCE(monthly1.dimension_1_n, 0) ) END AS dimension_1_n, CASE WHEN ( temp1.dimension_2_n IS NULL AND monthly1.dimension_2_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_2_n, 0) + COALESCE(monthly1.dimension_2_n, 0) ) END AS dimension_2_n, CASE WHEN ( temp1.dimension_3_n IS NULL AND monthly1.dimension_3_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_3_n, 0) + COALESCE(monthly1.dimension_3_n, 0) ) END AS dimension_3_n, CASE WHEN ( temp1.dimension_4_n IS NULL AND monthly1.dimension_4_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_4_n, 0) + COALESCE(monthly1.dimension_4_n, 0) ) END AS dimension_4_n, CASE WHEN ( temp1.dimension_5_n IS NULL AND monthly1.dimension_5_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_5_n, 0) + COALESCE(monthly1.dimension_5_n, 0) ) END AS dimension_5_n, CASE WHEN ( temp1.dimension_6_n IS NULL AND monthly1.dimension_6_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_6_n, 0) + COALESCE(monthly1.dimension_6_n, 0) ) END AS dimension_6_n, CASE WHEN ( temp1.dimension_7_n IS NULL AND monthly1.dimension_7_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_7_n, 0) + COALESCE(monthly1.dimension_7_n, 0) ) END AS dimension_7_n, CASE WHEN ( temp1.dimension_8_n IS NULL AND monthly1.dimension_8_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_8_n, 0) + COALESCE(monthly1.dimension_8_n, 0) ) END AS dimension_8_n, CASE WHEN ( temp1.dimension_9_n IS NULL AND monthly1.dimension_9_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_9_n, 0) + COALESCE(monthly1.dimension_9_n, 0) ) END AS dimension_9_n, CASE WHEN ( temp1.dimension_10_n IS NULL AND monthly1.dimension_10_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.dimension_10_n, 0) + COALESCE(monthly1.dimension_10_n, 0) ) END AS dimension_10_n, CASE WHEN ( temp1.no_of_events_n IS NULL AND monthly1.no_of_events_n IS NULL ) THEN NULL ELSE ( COALESCE(temp1.no_of_events_n, 0) + COALESCE(monthly1.no_of_events_n, 0) ) END AS no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n FROM ( SELECT result.month_id_n, result.actor_type_n, result.actor_id_n, result.event_type_n, result.metrics_type_n, result.metrics_id_n, Sum(result.dimension_1_n) AS dimension_1_n, Sum(result.dimension_2_n) AS dimension_2_n, Sum(result.dimension_3_n) AS dimension_3_n, Sum(result.dimension_4_n) dimension_4_n, Sum(result.dimension_5_n) AS dimension_5_n, Sum(result.dimension_6_n) AS dimension_6_n, Sum(result.dimension_7_n) AS dimension_7_n, Sum(result.dimension_8_n) AS dimension_8_n, Sum(result.dimension_9_n) AS dimension_9_n, Sum(result.dimension_10_n) AS dimension_10_n, Sum(result.no_of_events_n) AS no_of_events_n, result.source_type_n, result.source_id_n, result.data_flag_n, result.instance_type_n, result.instance_id_n, result.file_id_n FROM ( SELECT day_master.month_id_n, temp.actor_key_v, COALESCE(( select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.actor_type_n), temp.actor_type_n) as actor_type_n, COALESCE(org.aggregation_id_n, 0) AS actor_id_n, temp.event_type_n, temp.metrics_key_v, COALESCE(( select reference_type_n from kpi.ms_metrics_lookup_master where metrics_type_n = temp.metrics_type_n), temp.metrics_type_n) as metrics_type_n, COALESCE(met.metrics_id_n, 0) AS metrics_id_n, temp.dimension_1_n, temp.dimension_2_n, temp.dimension_3_n, temp.dimension_4_n, temp.dimension_5_n, temp.dimension_6_n, temp.dimension_7_n, temp.dimension_8_n, temp.dimension_9_n, temp.dimension_10_n, temp.no_of_events_n, temp.source_key_v, COALESCE(( select reference_type_n from kpi.ms_actor_lookup_master where actor_type_n = temp.source_type_n), temp.source_type_n) as source_type_n, COALESCE(dorg.aggregation_id_n, 0) AS source_id_n, temp.data_flag_n, temp.instance_key_v, COALESCE(( select reference_type_n from kpi.ms_metrics_lookup_master where metrics_type_n = temp.instance_type_n), temp.instance_type_n) as instance_type_n, COALESCE(imet.metrics_id_n, 0) AS instance_id_n, temp.file_id_n, CASE WHEN temp.metrics_key_v IS NOT NULL AND COALESCE(met.metrics_id_n, 0) = 0 THEN kpi.update_analytics_failed_row('tr_temp_upload_metrics_aggr', temp.temp_id_n, 'metric_field%Metric mapping not found for metric_field : ' || temp.metrics_key_v) WHEN temp.source_key_v IS NOT NULL AND COALESCE(dorg.aggregation_id_n, 0) = 0 THEN kpi.update_analytics_failed_row('tr_temp_upload_metrics_aggr', temp.temp_id_n, 'source_field%Source mapping not found for source_field : ' || temp.source_key_v) WHEN temp.instance_key_v IS NOT NULL AND COALESCE(imet.metrics_id_n, 0) = 0 THEN kpi.update_analytics_failed_row('tr_temp_upload_metrics_aggr', temp.temp_id_n, 'instance_field%Instance mapping not found for instance_field : ' || temp.instance_key_v) ELSE 1 END AS id_flag FROM kpi.tr_temp_upload_metrics_aggr temp LEFT JOIN kpi.ms_org_master org ON temp.actor_key_v = org.ref_code_v LEFT JOIN kpi.ms_metrics_master met ON ( temp.metrics_key_v = met.ext_reference_id_v AND met.metrics_type_n = 34 ) LEFT JOIN kpi.ms_org_master dorg ON temp.source_key_v = dorg.ref_code_v LEFT JOIN kpi.ms_metrics_master imet ON ( temp.instance_key_v = imet.ext_reference_id_v AND imet.metrics_type_n = 36 ) INNER JOIN kpi.ms_day_master AS day_master ON ( temp.id_n = day_master.day_id_n ) WHERE temp.file_id_n = ? and temp.status_flag_n = 0 ) result where result.id_flag = 1 GROUP BY result.month_id_n, result.actor_type_n, result.actor_id_n, result.event_type_n, result.metrics_type_n, result.metrics_id_n, result.source_type_n, result.source_id_n, result.data_flag_n, result.instance_type_n, result.instance_id_n, result.file_id_n ) temp1 LEFT JOIN kpi.tr_monthly_secondary_mobo_aggr monthly1 ON temp1.month_id_n = monthly1.month_id_n AND temp1.actor_type_n = monthly1.actor_type_n AND temp1.actor_id_n = monthly1.actor_id_n AND temp1.event_type_n = monthly1.event_type_n AND temp1.metrics_type_n = monthly1.metrics_type_n AND temp1.metrics_id_n = monthly1.metrics_id_n AND temp1.source_type_n = monthly1.source_type_n AND temp1.source_id_n = monthly1.source_id_n AND temp1.data_flag_n = monthly1.data_flag_n AND temp1.instance_type_n = monthly1.instance_type_n AND temp1.instance_id_n = monthly1.instance_id_n;



-- MOBII RGUGA INJECTION:
-- daily_table
drop table if exists kpi.tr_daily_mobii_rgu_ga_injection_aggr cascade;
create table kpi.tr_daily_mobii_rgu_ga_injection_aggr
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
	constraint tr_daily_mobii_rgu_ga_injection_aggr_pkey primary key (day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- monthly_table
drop table if exists kpi.tr_monthly_mobii_rgu_ga_injection_aggr cascade;
create table kpi.tr_monthly_mobii_rgu_ga_injection_aggr
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
	constraint tr_monthly_mobii_rgu_ga_injection_aggr_pkey primary key (month_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n)
);

-- MOBII DAILY SSO:
-- daily_table
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

-- monthly_table
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


