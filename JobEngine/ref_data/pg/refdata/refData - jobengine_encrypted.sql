-- INSERT QUERY

	-- ORG.VM_COMPANY

	INSERT INTO ORG.VM_COMPANY(COMPANY_ID_N, COMPANY_REF_ID_N, NAME_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT, DESCRIPTION_V) VALUES (101,0, 'Enhancesys innovatation pvt.ltd', now() ,now(),NULL,'Enhancesys enables transformation of business by protecting and growing revenue, improving operational efficiency and generating savings. Our innovation engines help in creation of new revenue generating service offerings, comprehensive automation and significant performance enhancements.');

	-- ORG.VM_COMPANY_TYPE

	INSERT INTO ORG.VM_COMPANY_TYPE(COMPANY_TYPE_ID_N, NAME_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, DESCRIPTION_V) VALUES (1,'IT',NOW(),NOW(),'Service');

	-- ORG.VM_COMPANY_PROFILE

	INSERT INTO ORG.VM_COMPANY_PROFILE(COMPANY_ID_N, COMPANY_REF_ID_N, NAME_V, CREATED_TIME_DT, LAST_UPDATED_TIME_DT, CLOSED_TIME_DT, DESCRIPTION_V) VALUES (101,0, 'Enhancesys innovatation pvt.ltd', now() ,now(),NULL,'Enhancesys enables transformation of business by protecting and growing revenue, improving operational efficiency and generating savings. Our innovation engines help in creation of new revenue generating service offerings, comprehensive automation and significant performance enhancements.');




