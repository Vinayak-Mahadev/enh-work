SELECT
   1082 as interface_id_n,
   temp1.temp_id_n,
   temp1.id_n,
   day.day_id_n,
   temp1.actor_type_n,
   temp1.actor_id_n,
   temp1.event_type_n,
   temp1.metrics_type_n,
   temp1.metrics_id_n,
   temp1.dimension_1_n AS dimension_1_n,
   temp1.dimension_2_n AS dimension_2_n,
   temp1.dimension_3_n AS dimension_3_n,
   temp1.dimension_4_n AS dimension_4_n,
   temp1.dimension_5_n AS dimension_5_n,
   temp1.dimension_6_n AS dimension_6_n,
   temp1.dimension_7_n AS dimension_7_n,
   temp1.dimension_8_n AS dimension_8_n,
   temp1.dimension_9_n AS dimension_9_n,
   temp1.dimension_10_n AS dimension_10_n,
   temp1.no_of_events_n,
   temp1.source_type_n,
   temp1.source_id_n,
   temp1.data_flag_n,
   temp1.instance_type_n,
   temp1.instance_id_n 
FROM
   (
      SELECT
         result.temp_id_n,
         result.id_n,
         result.actor_type_n,
         result.actor_id_n,
         result.event_type_n,
         result.metrics_type_n,
         result.metrics_id_n,
         result.dimension_1_n,
         result.dimension_2_n,
         result.dimension_3_n,
         result.dimension_4_n,
         result.dimension_5_n,
         result.dimension_6_n,
         result.dimension_7_n,
         result.dimension_8_n,
         result.dimension_9_n,
         result.dimension_10_n,
         result.no_of_events_n,
         result.source_type_n,
         result.source_id_n,
         result.data_flag_n,
         result.instance_type_n,
         result.instance_id_n,
         result.file_id_n 
      FROM
         (
            SELECT
               temp.temp_id_n,
               temp.id_n,
               temp.actor_key_v,
               COALESCE(al.reference_type_n, temp.actor_type_n) as actor_type_n,
               COALESCE(org.aggregation_id_n, 0) AS actor_id_n,
               temp.event_type_n,
               temp.metrics_key_v,
               COALESCE(ml.reference_type_n, temp.metrics_type_n) as metrics_type_n,
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
               COALESCE(sl.reference_type_n, temp.source_type_n) as source_type_n,
               COALESCE(dorg.aggregation_id_n, 0) AS source_id_n,
               temp.data_flag_n,
               temp.instance_key_v,
               temp.instance_type_n,
               temp.instance_id_n,
               temp.file_id_n,
               CASE
                  WHEN
                     temp.actor_key_v IS NOT NULL 
                     AND COALESCE(org.aggregation_id_n, 0) = 0 
                  THEN
                     kpi.update_analytics_failed_row('tr_temp_upload_metrics_orgbal_aggr', temp.temp_id_n, 'actor_field%Actor mapping not found for actor_field : ' || temp.actor_key_v) 
                  WHEN
                     temp.metrics_key_v IS NOT NULL 
                     AND COALESCE(met.metrics_id_n, 0) = 0 
                  THEN
                     kpi.update_analytics_failed_row('tr_temp_upload_metrics_orgbal_aggr', temp.temp_id_n, 'metric_field%Metric mapping not found for metric_field : ' || temp.metrics_key_v) 
                  WHEN
                     temp.source_key_v IS NOT NULL 
                     AND COALESCE(dorg.aggregation_id_n, 0) = 0 
                  THEN
                     kpi.update_analytics_failed_row('tr_temp_upload_metrics_orgbal_aggr', temp.temp_id_n, 'source_field%Source mapping not found for source_field : ' || temp.source_key_v) 
                  ELSE
                     1 
               END
               AS id_flag 
            FROM
               kpi.tr_temp_upload_metrics_orgbal_aggr temp 
               LEFT JOIN
                  kpi.ms_actor_lookup_master al 
                  ON temp.actor_type_n = al.actor_type_n 
               LEFT JOIN
                  kpi.ms_metrics_lookup_master ml 
                  ON ml.metrics_type_n = temp.metrics_type_n 
               LEFT JOIN
                  kpi.ms_actor_lookup_master sl 
                  ON sl.actor_type_n = temp.source_type_n 
               LEFT JOIN
                  kpi.ms_org_master org 
                  ON (temp.actor_key_v = org.ref_code_v 
                  and org.status_n = 174) 
               LEFT JOIN
                  kpi.ms_metrics_master met 
                  ON ( temp.metrics_key_v = met.ext_reference_id_v 
                  AND met.metrics_type_n = 34) 
               LEFT JOIN
                  kpi.ms_org_master dorg 
                  ON (temp.source_key_v = dorg.ref_code_v 
                  and dorg.status_n = 174) 
            WHERE
               temp.file_id_n = ? 
            order by
               temp.temp_id_n
         )
         result 
      where
         result.id_flag = 1 
   )
   temp1 
   LEFT JOIN
      kpi.tr_daily_org_balance_aggr day 
      ON temp1.id_n = day.day_id_n 
      AND temp1.actor_type_n = day.actor_type_n 
      AND temp1.actor_id_n = day.actor_id_n 
      AND temp1.event_type_n = day.event_type_n 
      AND temp1.metrics_type_n = day.metrics_type_n 
      AND temp1.metrics_id_n = day.metrics_id_n 
      AND temp1.source_type_n = day.source_type_n 
      AND temp1.source_id_n = day.source_id_n 
      AND temp1.data_flag_n = day.data_flag_n 
      AND temp1.instance_type_n = day.instance_type_n 
      AND temp1.instance_id_n = day.instance_id_n;