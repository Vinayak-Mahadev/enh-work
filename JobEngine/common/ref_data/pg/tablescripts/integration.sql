-- CREATE SCHEMA

DROP SCHEMA IF EXISTS BIZ;
CREATE SCHEMA  BIZ;

DROP TABLE IF EXISTS BIZ.MS_MODULE_ATTR CASCADE;
DROP TABLE IF EXISTS BIZ.TR_MODULE_SUMMAY CASCADE;
DROP TABLE IF EXISTS BIZ.MS_MODULE CASCADE;
		

-- TABLE MS_MODULE
      CREATE TABLE BIZ.MS_MODULE
      (
      	MODULE_ID_N			    	NUMERIC(19, 0) NOT NULL,
      	REF_ID_N				    NUMERIC(19, 0) NOT NULL,
      	NAME_V						CHARACTER VARYING(75) NOT NULL,
      	DESCRIPTION_V				CHARACTER VARYING(3000),
      	CREATED_TIME_DT				TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
      	LAST_UPDATED_TIME_DT		TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
      	CLOSED_TIME_DT				TIMESTAMP WITH TIME ZONE DEFAULT NULL,
      	CONSTRAINT MS_MODULE_PKEY PRIMARY KEY(MODULE_ID_N)
      );
      
-- TABLE MS_MODULE_ATTR
      CREATE TABLE BIZ.MS_MODULE_ATTR
      (
      	ATTRIBUTE_ID_N				NUMERIC(19, 0) NOT NULL,
      	MODULE_ID_N				    NUMERIC(19, 0) NOT NULL,
      	NAME_V						CHARACTER VARYING(75) NOT NULL,	
      	VALUE_V						CHARACTER VARYING(2550) NOT NULL,	
      	LAST_UPDATED_TIME_DT		TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
      	
      	CONSTRAINT MS_MODULE_ATTR_PKEY PRIMARY KEY(ATTRIBUTE_ID_N),
      	CONSTRAINT MS_MODULE_ATTR_FKEY FOREIGN KEY(MODULE_ID_N) REFERENCES BIZ.MS_MODULE(MODULE_ID_N)
      );
	  
	  

-- TABLE TR_MODULE_SUMMAY
	  
	  CREATE SEQUENCE BIZ.TR_MODULE_SUMMAY_SEQ INCREMENT 1 START 10001 MINVALUE 1 CACHE 1;
      
	  CREATE TABLE BIZ.TR_MODULE_SUMMAY
      (
      	SUMMAY_ID_N				    NUMERIC(19, 0)  NOT NULL DEFAULT NEXTVAL('BIZ.TR_MODULE_SUMMAY_SEQ'::REGCLASS),
      	MODULE_ID_N					NUMERIC(19, 0) NOT NULL,
      	STATUS_N				    NUMERIC(19, 0) NOT NULL,
      	NAME_V						CHARACTER VARYING(255) NOT NULL,	
      	VALUE_V						CHARACTER VARYING(2550) NOT NULL,	
      	CREATED_TIME_DT				TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
      	LAST_UPDATED_TIME_DT		TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
      	CLOSED_TIME_DT				TIMESTAMP WITH TIME ZONE DEFAULT NULL,
      	
      	CONSTRAINT TR_MODULE_SUMMAY_PKEY PRIMARY KEY(SUMMAY_ID_N),
      	CONSTRAINT TR_MODULE_SUMMAY_FKEY FOREIGN KEY(MODULE_ID_N) REFERENCES BIZ.MS_MODULE(MODULE_ID_N)
      );
            
      
      