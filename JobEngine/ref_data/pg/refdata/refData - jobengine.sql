DELETE FROM JOBENGINE.MS_MODULE_ATTR;
DELETE FROM JOBENGINE.MS_MODULE;

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MS_MODULE
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1001, 101, 'Test Module 1', 'Test purpose', now(), now(), null);
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1002, 101, 'Test Module 2', 'Test purpose', now(), now(), null);
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1003, 101, 'Test Module 3', 'Test purpose', now(), now(), null);
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1004, 101, 'Test Module 4', 'Test purpose', now(), now(), null);
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1005, 101, 'Test Module 5', 'Test purpose', now(), now(), null);
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1006, 101, 'Test Module 6', 'Test purpose', now(), now(), null);
INSERT INTO JOBENGINE.MS_MODULE(MODULE_ID_N, REF_ID_N, NAME_V, DESCRIPTION_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT) VALUES (1007, 101, 'Test Module 7', 'Test purpose', now(), now(), null);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- MS_MODULE_ATTR
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- module(1001)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001001, 1001, 'job-id', 'FossApprovalReport', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1001002, 1001, 'template-id', 'Sample-feed', now());

-- module(1002)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1002001, 1002, 'job-id', 'postgres_Sample', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1002002, 1002, 'template-id', 'Productive-outlet', now());

-- module(1003)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1003001, 1003, 'job-id', 'Sample-OrgDetailsReport', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1003002, 1003, 'template-id', 'OrgDetails-feed', now());

-- module(1004)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1004001, 1004, 'job-id', 'Sample-csv-output', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1004002, 1004, 'template-id', 'Jasper-feed', now());

-- module(1005)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1005001, 1005, 'job-id', 'postgres_Sample', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1005002, 1005, 'template-id', 'DataList-Outlet', now());

-- module(1006)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1006001, 1006, 'job-id', 'postgres_Sample', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1006002, 1006, 'template-id', 'Sample-report-feed', now());

-- module(1007)
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1007001, 1007, 'job-id', 'stockReorderLevelReport_csv', now());
INSERT INTO JOBENGINE.MS_MODULE_ATTR(ATTRIBUTE_ID_N, MODULE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (1007002, 1007, 'template-id', 'Sample-report-feed', now());



-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------