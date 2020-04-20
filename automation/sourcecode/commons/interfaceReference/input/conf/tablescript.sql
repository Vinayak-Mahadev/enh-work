DROP TABLE IF EXISTS INTERFACE.MS_MODULE CASCADE ;
DROP TABLE IF EXISTS INTERFACE.MS_INTERFACE CASCADE ;
DROP TABLE IF EXISTS INTERFACE.MS_INTERFACE_ATTR CASCADE ;
DROP SCHEMA IF EXISTS INTERFACE CASCADE ;

CREATE SCHEMA INTERFACE;
CREATE TABLE INTERFACE.MS_MODULE (  MODULE_ID_N  NUMERIC(19, 0) NOT NULL,  NAME_V   CHARACTER VARYING(255) NOT NULL,  CALL_BACK_V  CHARACTER VARYING(500),  PRIORITY_N  NUMERIC(19, 0),  LAST_UPDATED_TIME_DT  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),  DESCRIPTION_V   CHARACTER VARYING(255),  CONSTRAINT MS_MODULE_PKEY PRIMARY KEY(MODULE_ID_N) );
CREATE TABLE INTERFACE.MS_INTERFACE (  INTERFACE_ID_N  NUMERIC(19, 0) NOT NULL,  NAME_V   CHARACTER VARYING(255) NOT NULL,   MODULE_ID_N  NUMERIC(19, 0) NOT NULL,  INTERFACE_TYPE_N  NUMERIC(19, 0) NOT NULL,   TRANS_TYPE_N  NUMERIC(19, 0) NOT NULL,   SEQ_N   NUMERIC(19, 0),  CONVERTER_V  CHARACTER VARYING(500) NOT NULL,  PUBLISHER_V  CHARACTER VARYING(500) NOT NULL,  RESPONSE_PROCESSOR_V  CHARACTER VARYING(500),  LAST_UPDATED_TIME_DT  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),  CONSTRAINT MS_INTERFACE_PKEY PRIMARY KEY(INTERFACE_ID_N),  CONSTRAINT MS_INTERFACE_FKEY FOREIGN KEY(MODULE_ID_N) REFERENCES INTERFACE.MS_MODULE (MODULE_ID_N) ); 
CREATE TABLE INTERFACE.MS_INTERFACE_ATTR (  ATTRIBUTE_ID_N  NUMERIC(19, 0) NOT NULL,  INTERFACE_ID_N  NUMERIC(19, 0) NOT NULL,  NAME_V   CHARACTER VARYING(255) NOT NULL,  VALUE_V   CHARACTER VARYING(5000) NOT NULL,  LAST_UPDATED_TIME_DT  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),  CONSTRAINT MS_INTERFACE_ATTR_PKEY PRIMARY KEY(ATTRIBUTE_ID_N),  CONSTRAINT MS_INTERFACE_ATTR_FKEY FOREIGN KEY(INTERFACE_ID_N) REFERENCES INTERFACE.MS_INTERFACE (INTERFACE_ID_N) );  

DELETE FROM  INTERFACE.MS_MODULE;
DELETE FROM  INTERFACE.MS_INTERFACE;
DELETE FROM  INTERFACE.MS_INTERFACE_ATTR;