-- MP_EVENT_FORMAT_MAPPING:
delete from kpi.mp_event_format_mapping where event_type_n in (303,304,305,316);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(303, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, null, null);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(304, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, null, null);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(305, 'Csv', 'DATE|MICRO|SITE_ID|OUTLET|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, null, null);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(316, 'Csv', 'DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, null, null);

-- MP_EVENT_ACTOR_MAPPING:
delete from kpi.mp_event_actor_mapping where event_type_n in (303,304,305,316);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (303, 1, 'ID_OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (303, 1, 'SITE_ID', 'Site Master', 12, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (304, 1, 'ID_OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (304, 1, 'SITE_ID', 'Site Master', 12, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (305, 1, 'OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (305, 1, 'SITE_ID', 'Site Master', 12, null, true);

insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (316, 1, 'OUTLET', 'Organization', 10, null, true);
insert into kpi.mp_event_actor_mapping (event_type_n, configuration_n, actor_key_v, description_v, actor_type_n, actor_id_n, is_default_bl) values (316, 1, 'SITE_ID', 'Site Master', 12, null, true);

