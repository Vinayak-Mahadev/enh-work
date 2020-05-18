SELECT
   1064 as interface_id_n,
   temp1.id_n,
   daily1.day_id_n,
   temp1.actor_type_n,
   temp1.actor_id_n,
   temp1.event_type_n,
   temp1.metrics_type_n,
   temp1.metrics_id_n,
   CASE
      WHEN
         (
            temp1.dimension_1_n IS NULL 
            AND daily1.dimension_1_n IS NULL
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_1_n, 0) + COALESCE(daily1.dimension_1_n, 0) ) 
   END
   AS dimension_1_n, 
   CASE
      WHEN
         (
            temp1.dimension_2_n IS NULL 
            AND daily1.dimension_2_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_2_n, 0) + COALESCE(daily1.dimension_2_n, 0) ) 
   END
   AS dimension_2_n, 
   CASE
      WHEN
         (
            temp1.dimension_3_n IS NULL 
            AND daily1.dimension_3_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_3_n, 0) + COALESCE(daily1.dimension_3_n, 0) ) 
   END
   AS dimension_3_n, 
   CASE
      WHEN
         (
            temp1.dimension_4_n IS NULL 
            AND daily1.dimension_4_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_4_n, 0) + COALESCE(daily1.dimension_4_n, 0) ) 
   END
   AS dimension_4_n, 
   CASE
      WHEN
         (
            temp1.dimension_5_n IS NULL 
            AND daily1.dimension_5_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_5_n, 0) + COALESCE(daily1.dimension_5_n, 0) ) 
   END
   AS dimension_5_n, 
   CASE
      WHEN
         (
            temp1.dimension_6_n IS NULL 
            AND daily1.dimension_6_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_6_n, 0) + COALESCE(daily1.dimension_6_n, 0) ) 
   END
   AS dimension_6_n, 
   CASE
      WHEN
         (
            temp1.dimension_7_n IS NULL 
            AND daily1.dimension_7_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_7_n, 0) + COALESCE(daily1.dimension_7_n, 0) ) 
   END
   AS dimension_7_n, 
   CASE
      WHEN
         (
            temp1.dimension_8_n IS NULL 
            AND daily1.dimension_8_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_8_n, 0) + COALESCE(daily1.dimension_8_n, 0) ) 
   END
   AS dimension_8_n, 
   CASE
      WHEN
         (
            temp1.dimension_9_n IS NULL 
            AND daily1.dimension_9_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_9_n, 0) + COALESCE(daily1.dimension_9_n, 0) ) 
   END
   AS dimension_9_n, 
   CASE
      WHEN
         (
            temp1.dimension_10_n IS NULL 
            AND daily1.dimension_10_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_10_n, 0) + COALESCE(daily1.dimension_10_n, 0) ) 
   END
   AS dimension_10_n, 
   CASE
      WHEN
         (
            temp1.no_of_events_n IS NULL 
            AND daily1.no_of_events_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.no_of_events_n, 0) + COALESCE(daily1.no_of_events_n, 0) ) 
   END
   AS no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n 
FROM
   (
      SELECT
         result.id_n,
         result.actor_type_n,
         result.actor_id_n,
         result.event_type_n,
         result.metrics_type_n,
         result.metrics_id_n,
         Sum(result.dimension_1_n) AS dimension_1_n,
         Sum(result.dimension_2_n) AS dimension_2_n,
         Sum(result.dimension_3_n) AS dimension_3_n,
         Sum(result.dimension_4_n) dimension_4_n,
         Sum(result.dimension_5_n) AS dimension_5_n,
         Sum(result.dimension_6_n) AS dimension_6_n,
         Sum(result.dimension_7_n) AS dimension_7_n,
         Sum(result.dimension_8_n) AS dimension_8_n,
         Sum(result.dimension_9_n) AS dimension_9_n,
         Sum(result.dimension_10_n) AS dimension_10_n,
         Sum(result.no_of_events_n) AS no_of_events_n,
         result.source_type_n,
         result.source_id_n,
         result.data_flag_n,
         result.instance_type_n,
         result.instance_id_n,
         result.file_id_n 
      FROM
         (
            SELECT
               temp.id_n,
               temp.actor_key_v,
               coalesce(( 
               select
                  reference_type_n 
               from
                  kpi.ms_actor_lookup_master 
               where
                  actor_type_n = temp.actor_type_n), temp.actor_type_n) as actor_type_n,
                  COALESCE(org.aggregation_id_n, 0) AS actor_id_n,
                  temp.event_type_n,
                  temp.metrics_key_v,
                  coalesce(( 
                  select
                     reference_type_n 
                  from
                     kpi.ms_metrics_lookup_master 
                  where
                     metrics_type_n = temp.metrics_type_n), temp.metrics_type_n) as metrics_type_n,
                     COALESCE(met.metrics_id_n, 0) AS metrics_id_n,
                     temp.dimension_1_n,
                     temp.dimension_2_n,
                     temp.dimension_3_n,
                     temp.dimension_4_n,
                     temp.dimension_5_n,
                     temp.dimension_6_n,
                     temp.dimension_7_n,
                     temp.dimension_8_n,
                     temp.dimension_9_n,
                     temp.dimension_10_n,
                     temp.no_of_events_n,
                     temp.source_key_v,
                     coalesce(( 
                     select
                        reference_type_n 
                     from
                        kpi.ms_actor_lookup_master 
                     where
                        actor_type_n = temp.source_type_n), temp.source_type_n) as source_type_n,
                        COALESCE(dorg.aggregation_id_n, 0) AS source_id_n,
                        temp.data_flag_n,
                        temp.instance_key_v,
                        coalesce(( 
                        select
                           reference_type_n 
                        from
                           kpi.ms_metrics_lookup_master 
                        where
                           metrics_type_n = temp.instance_type_n), temp.instance_type_n) as instance_type_n,
                           COALESCE(imet.metrics_id_n, 0) AS instance_id_n,
                           temp.file_id_n,
                           CASE
                              WHEN
                                 temp.metrics_key_v IS NOT NULL 
                                 AND COALESCE(met.metrics_id_n, 0) = 0 
                              THEN
                                 kpi.update_analytics_failed_row('tr_temp_upload_metrics_revenue_aggr', temp.temp_id_n, 'metric_field%Metric mapping not found for metric_field : ' || temp.metrics_key_v) 
                              WHEN
                                 temp.source_key_v IS NOT NULL 
                                 AND COALESCE(dorg.aggregation_id_n, 0) = 0 
                              THEN
                                 kpi.update_analytics_failed_row('tr_temp_upload_metrics_revenue_aggr', temp.temp_id_n, 'source_field%Source mapping not found for source_field : ' || temp.source_key_v) 
                              WHEN
                                 temp.instance_key_v IS NOT NULL 
                                 AND COALESCE(imet.metrics_id_n, 0) = 0 
                              THEN
                                 kpi.update_analytics_failed_row('tr_temp_upload_metrics_revenue_aggr', temp.temp_id_n, 'instance_field%Instance mapping not found for instance_field : ' || temp.instance_key_v) 
                              ELSE
                                 1 
                           END
                           AS id_flag 
                        FROM
                           kpi.tr_temp_upload_metrics_revenue_aggr temp 
                           LEFT JOIN
                              kpi.ms_org_master org 
                              ON temp.actor_key_v = org.ref_code_v 
                           LEFT JOIN
                              kpi.ms_metrics_master met 
                              ON ( temp.metrics_key_v = met.ext_reference_id_v 
                              AND met.metrics_type_n = 34 ) 
                           LEFT JOIN
                              kpi.ms_org_master dorg 
                              ON temp.source_key_v = dorg.ref_code_v 
                           LEFT JOIN
                              kpi.ms_metrics_master imet 
                              ON ( temp.instance_key_v = imet.ext_reference_id_v 
                              AND imet.metrics_type_n = 36 ) 
                        WHERE
                           temp.file_id_n = ? 
         )
         result 
      where
         result.id_flag = 1 
      GROUP BY
         result.id_n, result.actor_type_n, result.actor_id_n, result.event_type_n, result.metrics_type_n, result.metrics_id_n, result.source_type_n, result.source_id_n, result.data_flag_n, result.instance_type_n, result.instance_id_n, result.file_id_n 
   )
   temp1 
   LEFT JOIN
      kpi.tr_daily_org_transfer_aggr daily1 
      ON temp1.id_n = daily1.day_id_n 
      AND temp1.actor_type_n = daily1.actor_type_n 
      AND temp1.actor_id_n = daily1.actor_id_n 
      AND temp1.event_type_n = daily1.event_type_n 
      AND temp1.metrics_type_n = daily1.metrics_type_n 
      AND temp1.metrics_id_n = daily1.metrics_id_n 
      AND temp1.source_type_n = daily1.source_type_n 
      AND temp1.source_id_n = daily1.source_id_n 
      AND temp1.data_flag_n = daily1.data_flag_n 
      AND temp1.instance_type_n = daily1.instance_type_n 
      AND temp1.instance_id_n = daily1.instance_id_n;