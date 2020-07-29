-- SD_TENANT
insert into jobengine.sd_tenant (tenant_id_n, name_v, start_date_dt, end_date_dt, last_updated_time_dt) values (1, 'Default', now(), '2099-12-31 00:00:00+05:30', now());

-- SD_STATUS
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1, 'Active', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (2, 'Retired', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1300, 'InQueue', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1301, 'Processed', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1302, 'Error', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1303, 'Response Available', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1304, 'Transaction Completed', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1305, 'Call Back Failed', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1306, 'Queue Attempt Failed', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1307, 'Intermediate Status', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1308, 'Pending For Res Update Status', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1321, 'File Received', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1322, 'File Uploaded', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1323, 'File Processed', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1324, 'File Completed', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1325, 'File Rejected', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1326, 'File InQueue', 1, now(), '2099-12-31 00:00:00+05:30', now());
insert into jobengine.sd_status (status_n, name_v, tenant_id_n, start_date_dt, end_date_dt, last_updated_time_dt) values (1327, 'File Partially Rejected', 1, now(), '2099-12-31 00:00:00+05:30', now());


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MS_MODULE
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1001, 'Test Module 1', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1002, 'Test Module 2', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1003, 'Test Module 3', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1004, 'Test Module 4', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1005, 'Test Module 5', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1006, 'Test Module 6', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1007, 'Test Module 7', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1008, 'Test Module 8', 1, 1, 1, '', '', null, now());
insert into jobengine.ms_module(module_id_n, name_v, module_type_n, trans_type_n, seq_n, converter_v, publisher_v, response_processor_v, last_updated_time_dt) values (1009, 'Test Module 9', 1, 1, 1, '', '', null, now());

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MS_MODULE_ATTR
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- module(1001)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1001001, 1001, 'job-id', 'FossApprovalReport', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1001002, 1001, 'template-id', 'Sample-feed', now());

-- module(1002)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1002001, 1002, 'job-id', 'postgres_Sample', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1002002, 1002, 'template-id', 'Productive-outlet', now());

-- module(1003)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1003001, 1003, 'job-id', 'Sample-OrgDetailsReport', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1003002, 1003, 'template-id', 'OrgDetails-feed', now());

-- module(1004)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1004001, 1004, 'job-id', 'Sample-csv-output', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1004002, 1004, 'template-id', 'Jasper-feed', now());

-- module(1005)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1005001, 1005, 'job-id', 'postgres_Sample', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1005002, 1005, 'template-id', 'DataList-Outlet', now());

-- module(1006)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1006001, 1006, 'job-id', 'postgres_Sample', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1006002, 1006, 'template-id', 'Sample-report-feed', now());

-- module(1007)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1007001, 1007, 'job-id', 'stockReorderLevelReport_csv', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1007002, 1007, 'template-id', 'Sample-report-feed', now());

-- module(1008)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1008001, 1008, 'job-id', 'stockReorderLevelReport_csv', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1008002, 1008, 'template-id', 'Sample-feed-mongo', now());

-- module(1009)
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1009001, 1009, 'job-id', 'purchaseOrderTracking', now());
insert into jobengine.ms_module_attr(attribute_id_n, module_id_n, name_v, value_v, last_updated_time_dt) values (1009002, 1009, 'template-id', 'purchaseOrderTracking', now());



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------