-- To update actor type & lookup configuration for site:(Gopinath)
delete from kpi.ms_actor_lookup_master where actor_type_n in (12,13);
delete from kpi.ms_actor_type_master where actor_type_n in (12,13);

insert into kpi.ms_actor_type_master(actor_type_n, actor_type_name_v, description_v, system_type_v) values (12,'Site Master', 'Site Lookup', 'Internal');
insert into kpi.ms_actor_type_master(actor_type_n, actor_type_name_v, description_v, system_type_v) values (13,'Operator Node Master', 'Operator Node Lookup', 'Internal');

insert into kpi.ms_actor_lookup_master(actor_type_n, reference_path_v, input_type_v, input_column_v, output_type_v, output_column_v, reference_type_n) values (12,'select ? as actor_id_n from kpi.ms_lookup_master where ?', 'string', 'ext_ref_code_v', 'numeric', 'aggregation_id_n', null);
insert into kpi.ms_actor_lookup_master(actor_type_n, reference_path_v, input_type_v, input_column_v, output_type_v, output_column_v, reference_type_n) values (13,'select ? as actor_id_n from kpi.ms_report_user_master where ?', 'string', 'operator_id_v', 'numeric', 'node_aggr_id_n', 2);

-- To configure new metrics for interface hadoop file feeds:(Gopinath)
delete from kpi.ms_metrics_master where metrics_id_n in (111,112,113);
insert into kpi.ms_metrics_master(metrics_id_n, metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v, derived_mode_v) values (111, 'MOBO Transfer', 'MOBO Transfer', 21, 'MOBO Transfer', 'Aggregated', null);
insert into kpi.ms_metrics_master(metrics_id_n, metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v, derived_mode_v) values (112, 'RGU-GA Injection', 'RGU-GA Injection', 21, 'RGU-GA Injection', 'Aggregated', null);
insert into kpi.ms_metrics_master(metrics_id_n, metrics_name_v, description_v, metrics_type_n, ext_reference_id_v, definition_v, derived_mode_v) values (113, 'Daily SSO', 'Daily SSO', 21, 'Daily SSO', 'Aggregated', null);
