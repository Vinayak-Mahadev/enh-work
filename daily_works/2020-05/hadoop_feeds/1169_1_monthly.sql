SELECT
   1169 as interface_id_n,
   temp1.month_id_n AS id_n,
   monthly1.month_id_n,
   temp1.actor_type_n,
   temp1.actor_id_n,
   temp1.event_type_n,
   temp1.metrics_type_n,
   temp1.metrics_id_n,
   CASE
      WHEN
         (
            temp1.dimension_1_n IS NULL 
            AND monthly1.dimension_1_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_1_n, 0) + COALESCE(monthly1.dimension_1_n, 0) ) 
   END
   AS dimension_1_n, 
   CASE
      WHEN
         (
            temp1.dimension_2_n IS NULL 
            AND monthly1.dimension_2_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_2_n, 0) + COALESCE(monthly1.dimension_2_n, 0) ) 
   END
   AS dimension_2_n, 
   CASE
      WHEN
         (
            temp1.dimension_3_n IS NULL 
            AND monthly1.dimension_3_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_3_n, 0) + COALESCE(monthly1.dimension_3_n, 0) ) 
   END
   AS dimension_3_n, 
   CASE
      WHEN
         (
            temp1.dimension_4_n IS NULL 
            AND monthly1.dimension_4_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_4_n, 0) + COALESCE(monthly1.dimension_4_n, 0) ) 
   END
   AS dimension_4_n, 
   CASE
      WHEN
         (
            temp1.dimension_5_n IS NULL 
            AND monthly1.dimension_5_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_5_n, 0) + COALESCE(monthly1.dimension_5_n, 0) ) 
   END
   AS dimension_5_n, 
   CASE
      WHEN
         (
            temp1.dimension_6_n IS NULL 
            AND monthly1.dimension_6_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_6_n, 0) + COALESCE(monthly1.dimension_6_n, 0) ) 
   END
   AS dimension_6_n, 
   CASE
      WHEN
         (
            temp1.dimension_7_n IS NULL 
            AND monthly1.dimension_7_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_7_n, 0) + COALESCE(monthly1.dimension_7_n, 0) ) 
   END
   AS dimension_7_n, 
   CASE
      WHEN
         (
            temp1.dimension_8_n IS NULL 
            AND monthly1.dimension_8_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_8_n, 0) + COALESCE(monthly1.dimension_8_n, 0) ) 
   END
   AS dimension_8_n, 
   CASE
      WHEN
         (
            temp1.dimension_9_n IS NULL 
            AND monthly1.dimension_9_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_9_n, 0) + COALESCE(monthly1.dimension_9_n, 0) ) 
   END
   AS dimension_9_n, 
   CASE
      WHEN
         (
            temp1.dimension_10_n IS NULL 
            AND monthly1.dimension_10_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.dimension_10_n, 0) + COALESCE(monthly1.dimension_10_n, 0) ) 
   END
   AS dimension_10_n, 
   CASE
      WHEN
         (
            temp1.no_of_events_n IS NULL 
            AND monthly1.no_of_events_n IS NULL 
         )
      THEN
         NULL 
      ELSE
( COALESCE(temp1.no_of_events_n, 0) + COALESCE(monthly1.no_of_events_n, 0) ) 
   END
   AS no_of_events_n, temp1.source_type_n, temp1.source_id_n, temp1.data_flag_n, temp1.instance_type_n, temp1.instance_id_n 
FROM
   (
      SELECT
         result.month_id_n,
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
               day_master.month_id_n,
               temp.actor_key_v,
               COALESCE(( 
               select
                  reference_type_n 
               from
                  kpi.ms_actor_lookup_master 
               where
                  actor_type_n = temp.actor_type_n), temp.actor_type_n) as actor_type_n,
                  COALESCE(org.aggregation_id_n, 0) AS actor_id_n,
                  temp.event_type_n,
                  temp.metrics_key_v,
                  21 as metrics_type_n,
                  110 AS metrics_id_n,
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
                  COALESCE(( 
                  select
                     reference_type_n 
                  from
                     kpi.ms_actor_lookup_master 
                  where
                     actor_type_n = temp.source_type_n), temp.source_type_n) as source_type_n,
                     COALESCE(site.site_id_n, 0) AS source_id_n,
                     temp.data_flag_n,
                     temp.instance_key_v,
                     0 as instance_type_n,
                     0 AS instance_id_n,
                     temp.file_id_n,
                     CASE
                        WHEN
                           temp.actor_key_v IS NOT NULL 
                           AND COALESCE(org.aggregation_id_n, 0) = 0 
                        THEN
                           kpi.update_analytics_failed_row('tr_temp_rguga_injection_aggr', temp.temp_id_n, 'actor_field%Actor mapping not found for actor_field : ' || temp.actor_key_v) 
                        WHEN
                           temp.metrics_key_v IS NOT NULL 
                           AND COALESCE(met.metrics_id_n, 0) = 0 
                           THEN
                           kpi.update_analytics_failed_row('tr_temp_rguga_injection_aggr', temp.temp_id_n, 'metric_field%Metric mapping not found for metric_field : ' || temp.metrics_key_v) 
						WHEN
                           temp.source_key_v IS NOT NULL 
                           AND COALESCE(site.site_id_n, 0) = 0 
                        THEN
                           kpi.update_analytics_failed_row('tr_temp_rguga_injection_aggr', temp.temp_id_n, 'source_field%Source mapping not found for source_field : ' || temp.source_key_v) 
                        ELSE
                           1 
                     END
                     AS id_flag 
                  FROM
                     kpi.tr_temp_rguga_injection_aggr temp 
                     LEFT JOIN
                        kpi.ms_org_master org 
                        on  (temp.actor_key_v = org.ref_code_v )
					 LEFT JOIN
                        kpi.ms_metrics_master met 
                        ON ( temp.metrics_key_v = met.ext_reference_id_v 
                        AND met.metrics_type_n = 24)
                     LEFT JOIN
                        kpi.ms_site_master site 
                        ON temp.source_key_v = site.ref_code_v 
                     INNER JOIN
                        kpi.ms_day_master AS day_master 
                        ON ( temp.id_n = day_master.day_id_n ) 
                  WHERE
                     temp.file_id_n = ? 
                     and temp.status_flag_n = 0 
         )
         result 
      where
         result.id_flag = 1 
      GROUP BY
         result.month_id_n, result.actor_type_n, result.actor_id_n, result.event_type_n, result.metrics_type_n, result.metrics_id_n, result.source_type_n, result.source_id_n, result.data_flag_n, result.instance_type_n, result.instance_id_n, result.file_id_n 
   )
   temp1 
   LEFT JOIN
      kpi.tr_monthly_rguga_injection_aggr monthly1 
      ON temp1.month_id_n = monthly1.month_id_n 
      AND temp1.actor_type_n = monthly1.actor_type_n 
      AND temp1.actor_id_n = monthly1.actor_id_n 
      AND temp1.event_type_n = monthly1.event_type_n 
      AND temp1.metrics_type_n = monthly1.metrics_type_n 
      AND temp1.metrics_id_n = monthly1.metrics_id_n 
      AND temp1.source_type_n = monthly1.source_type_n 
      AND temp1.source_id_n = monthly1.source_id_n 
      AND temp1.data_flag_n = monthly1.data_flag_n 
      AND temp1.instance_type_n = monthly1.instance_type_n 
      AND temp1.instance_id_n = monthly1.instance_id_n;