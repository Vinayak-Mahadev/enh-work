

-- Take from interface file summary
-- Sort the file (SORT_LACCI_DATA_SCRIPT)
-- take the back up

INSERT INTO
   interface.lacci_data(date_active, lac_id, cell_id, description, bts_id, bts_name, bts_type, site_id, site_name, site_type, territory_id, updated_date, latitude, longitude, file_id, status, error_code, error_message) 
VALUES
   (
       ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? 
   );

-- SELECT data from 

SELECT
   bts_id,
   bts_type,
   bts_name,
   site_id,
   site_type,
   site_name,
   array_to_string(array_agg(lacci_data_id), ',') lacciDataIds 
FROM
   interface.lacci_data 
WHERE
   file_id = ? 
   and status = ? 
group by
   bts_id,
   bts_type,
   bts_name,
   site_id,
   site_type,
   site_name;
