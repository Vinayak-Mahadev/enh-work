INSERT INTO
   kpi.daily_table( day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, dimension_1_n, dimension_2_n, dimension_3_n, dimension_4_n, dimension_5_n, dimension_6_n, dimension_7_n, dimension_8_n, dimension_9_n, dimension_10_n, no_of_events_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, system_type_v ) 
values
   (
      ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? 
   )
   on conflict ( day_id_n, actor_type_n, actor_id_n, event_type_n, metrics_type_n, metrics_id_n, source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n ) do 
   update
   set
      dimension_1_n = ? , dimension_2_n = ? , dimension_3_n = ? , dimension_4_n = ? , dimension_5_n = ? , dimension_6_n = ? , dimension_7_n = ? , dimension_8_n = ? , dimension_9_n = ? , dimension_10_n = ? , no_of_events_n = ? ;
	  
	  
	  
	  
---
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  UPDATE
   kpi.daily_table 
SET
   dimension_1_n = ? , dimension_2_n = ? , dimension_3_n = ? , dimension_4_n = ? , dimension_5_n = ? , dimension_6_n = ? , dimension_7_n = ? , dimension_8_n = ? , dimension_9_n = ? , dimension_10_n = ? , no_of_events_n = ? , last_updated_time_dt = 
   (
      select
         now() 
   )
WHERE
   day_id_n = ? 
   and event_type_n = ? 
   and actor_type_n = ? 
   and actor_id_n = ? 
   and metrics_type_n = ? 
   and metrics_id_n = ? 
   and source_type_n = ? 
   and source_id_n = ? 
   and data_flag_n = ? 
   and instance_type_n = ? 
   and instance_id_n = ?