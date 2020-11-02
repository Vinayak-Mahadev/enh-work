-- interface id 1168,1181
delete from kpi.mp_event_format_mapping where event_type_n in (303,316);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(303, 'Csv', 'DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT|QSSO_STATUS', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, 'QSSO_STATUS', 21);
insert into kpi.mp_event_format_mapping (event_type_n, format_type_v, format_v, definition_v, date_key_v, date_format_v, source_key_v, source_type_n, instance_key_v, instance_type_n) values(316, 'Csv', 'DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT|QURO_STATUS', 'Aggregation', 'DATE', 'yyyyMMdd', 'MICRO', 13, 'QURO_STATUS', 21);
