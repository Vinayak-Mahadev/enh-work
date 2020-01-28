INSERT INTO interface.sp_stock_dump(
            stock_dump_id, iccid, msisdn, imsi, do_id, so_id, destination_dealer_id, 
            branch_code, brand, product_expired_date, stock_transfer_date, 
            program_code, program_name, source_dealer_id, file_id, status, 
            error_code, error_message)
    VALUES (?, ?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?);


INSERT INTO interface.sp_alloc_dump(
            alloc_dump_id, iccid, msisdn, imsi, do_id, so_id, dealer_id, 
            branch_code, brand, product_expired_date, so_creation_date, alloc_id, 
            program_code, program_name, type, alloc_date, payment_date, file_id, 
            status, error_code, error_message)
    VALUES (?, ?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, ?, 
            ?, ?, ?);




INSERT INTO interface.stock_dump_voucher(
            stock_dump_id, serial_number, do_id, so_id, dealer_id, artwork_code, 
            branch_code, brand, product_expired_date, nominal_value, stock_transfer_date, 
            program_code, program_name, source_dealer_id, file_id_n, status, 
            error_code, error_message)
    VALUES (?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?);






INSERT INTO interface.alloc_dump_voucher(
            alloc_dump_id, serial_number, do_id, so_id, dealer_id, artwork_code, 
            branch_code, brand, product_expired_date, nominal_value, so_creation_date, 
            alloc_no, program_code, program_name, resource_type, alloc_date, 
            payment_date, file_id_n, status, error_code, error_message)
    VALUES (?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?);







INSERT INTO interface.product_details(
            prd_details_id, productid, productname, category, subcategory, 
            startdt, enddt, salesprice, mrpprice, isserialized, seriltype, 
            seriallength, file_id_n, status, error_code, error_message, materialcode)
    VALUES (?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, ?);








INSERT INTO interface.serial_expiry(
            serial_expiry_id, sl_no, old_prd_code, new_prd_code, expiry_dt, 
            file_id, status, error_code, error_message)
    VALUES (?, ?, ?, ?, ?, 
            ?, ?, ?, ?);







INSERT INTO interface.lacci_data(
            lacci_data_id, date_active, lac_id, cell_id, description, bts_id, 
            bts_name, bts_type, site_id, site_name, site_type, territory_id, 
            updated_date, latitude, longitude, file_id, status, error_code, 
            error_message, snoc_bts_id, snoc_bts_type, snoc_site_id, snoc_site_type)
    VALUES (?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?);




INSERT INTO kpi.tr_temp_upload_aggr_failure(
            temp_id_n, id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, 
            metrics_key_v, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, 
            dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, 
            dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, 
            source_key_v, source_type_n, source_id_n, data_flag_n, instance_key_v, 
            instance_type_n, instance_id_n, status_flag_n, system_type_v, 
            correction_v, file_id_n, batch_number_n, data_string_v, error_code, 
            error_message, last_updated_time_dt)
    VALUES (?, ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?, ?, ?, 
            ?, ?, ?, ?, ?, 
            ?, ?);
