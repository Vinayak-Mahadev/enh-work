-- SCRIPTS TO CREATE INDEXES:
create index tr_temp_upload_metrics_aggr_file_idx on kpi.tr_temp_upload_metrics_aggr using btree (file_id_n);
create index tr_temp_upload_metrics_aggr_update_keys_idx on kpi.tr_temp_upload_metrics_aggr using btree (status_flag_n, message_v);

create index tr_temp_upload_aggr_failure_file_idx on kpi.tr_temp_upload_aggr_failure using btree (file_id_n);

create index tr_temp_upload_metrics_direct_aggr_file_idx on kpi.tr_temp_upload_metrics_direct_aggr using btree (file_id_n);
create index tr_temp_upload_metrics_direct_aggr_update_keys_idx on kpi.tr_temp_upload_metrics_direct_aggr using btree (status_flag_n, message_v);

create index tr_temp_upload_metrics_revenue_aggr_file_idx on kpi.tr_temp_upload_metrics_revenue_aggr using btree (file_id_n);
create index tr_temp_upload_metrics_revenue_aggr_update_keys_idx on kpi.tr_temp_upload_metrics_revenue_aggr using btree (status_flag_n, message_v);

create index tr_temp_upload_metrics_orgbal_aggr_file_idx on kpi.tr_temp_upload_metrics_orgbal_aggr using btree (file_id_n);
create index tr_temp_upload_metrics_orgbal_aggr_update_keys_idx on kpi.tr_temp_upload_metrics_orgbal_aggr using btree (status_flag_n, message_v);

create index tr_temp_upload_metrics_trans_aggr_file_idx on kpi.tr_temp_upload_metrics_trans_aggr using btree (file_id_n);
create index tr_temp_upload_metrics_trans_aggr_update_keys_idx on kpi.tr_temp_upload_metrics_trans_aggr using btree (status_flag_n, message_v);

create index tr_msisdn_product_mapping_prd_idx on kpi.tr_msisdn_product_mapping using btree (prd_id_v);
