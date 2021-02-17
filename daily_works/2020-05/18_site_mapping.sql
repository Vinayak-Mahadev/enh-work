CREATE TABLE INTERFACE.SITE_MAPPING
(
  TEMP_ID_N NUMERIC(19,0) NOT NULL DEFAULT NEXTVAL('INTERFACE.SITE_MAPPING_SEQ'::REGCLASS),
  DATE CHARACTER VARYING(255) NOT NULL,
  SITE_ID CHARACTER VARYING(255) NOT NULL,
  LONGITUDE CHARACTER VARYING(255) NOT NULL,
  LATITUDE CHARACTER VARYING(255) NOT NULL,
  MICRO_CLUSTER CHARACTER VARYING(255) NOT NULL,
  SALES_CLUSTER CHARACTER VARYING(255) NOT NULL,
  SALES_AREA CHARACTER VARYING(255) NOT NULL,
  AREA CHARACTER VARYING(255) NOT NULL,
  REGION CHARACTER VARYING(255) NOT NULL,
  JAVA_NONJAVA CHARACTER VARYING(255) NOT NULL,
  SITE_NAME CHARACTER VARYING(255) NOT NULL,
  SITE_POPULATION CHARACTER VARYING(255) NOT NULL,
  FILE_ID NUMERIC(19,0) NOT NULL,
  STATUS NUMERIC(19,0),
  ERROR_CODE CHARACTER VARYING(255),
  ERROR_MESSAGE CHARACTER VARYING(255),
  CONSTRAINT SITE_MAPPING_PK PRIMARY KEY (TEMP_ID_N)
);

-- success 
UPDATE INTERFACE.SITE_MAPPING SET STATUS = ? WHERE TEMP_ID_N = ? AND FILE_ID = ?;
-- fail
UPDATE INTERFACE.SITE_MAPPING SET STATUS = ?, ERROR_CODE = ?, ERROR_MESSAGE = ? WHERE TEMP_ID_N = ? AND FILE_ID = ?;

-- basic validation success
INSERT INTO INTERFACE.SITE_MAPPING (DATE, SITE_ID, LONGITUDE, LATITUDE, MICRO_CLUSTER, SALES_CLUSTER, SALES_AREA, AREA, REGION, JAVA_NONJAVA, SITE_NAME, SITE_POPULATION, FILE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
