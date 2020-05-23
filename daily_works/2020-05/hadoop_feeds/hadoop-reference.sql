301 - MPC_CODE|MOBO_DATE|AMOUNT (1166)

-- Source	: null, null
-- Instance	: null, null
-- Actor	: MPC_CODE, 10
-- Metric	: 21, 110

302 - MPC_CODE|DATE|ORGANIZATION_ID|DEALER_MSISDN|AMOUNT (1167)

-- Source	: MPC_CODE
-- Instance	: null, null
-- Actor	: ORGANIZATION_ID, 10
-- Metric	: 21, 110	

303 - DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT (1168)

(1)
-- Source	: SITE_ID, 12
-- Instance	: null, null
-- Actor	: ID_OUTLET, 10
-- Metric	: 21, 110	

(2)
-- Source	: SITE_ID, 12
-- Instance	: null, null
-- Actor	: MICRO, 13
-- Metric	: 21, 110	

304 - DATE|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN (1169)

(1)
-- Source	: SITE_ID, 12
-- Instance	: null, null
-- Actor	: ID_OUTLET, 10
-- Metric   : FLAG_ACM, 24

(2)
-- Source	: SITE_ID, 12
-- Instance	: null, null
-- Actor	: MICRO, 13
-- Metric   : FLAG_ACM, 24

305 - DATE|MICRO|SITE_ID|OUTLET|AMOUNT (1170)

(1)
-- Source	: SITE_ID, 12
-- Instance	: null, null
-- Actor	: OUTLET, 10
-- Metric   : 21, 110

(2)
-- Source	: SITE_ID, 12
-- Instance	: null, null
-- Actor	: MICRO, 13
-- Metric   : 21, 110

306 - MOBO_DATE|ORG_CODE|AMOUNT (1171)

-- Source	: null, null
-- Instance	: null, null
-- Actor	: ORG_CODE, 10
-- Metric   : 21, 110

307 - DATE|MICRO|SITE_ID|REVENUE_TYPE|REVENUE_TOTAL (1172)

-- Source	: SITE_ID, 12
-- Instance : null, null
-- Actor	: MICRO, 13
-- Metric	: REVENUE_TYPE, 25	

308 - DATE|MICRO|SITE_ID|REVENUE (1173)

-- Source	: SITE_ID, 12
-- Instance : null, null
-- Actor	: MICRO, 13
-- Metric	: 21, 110

309 - DATE|MICRO|SITE_ID|REVENUE (1174)

-- Source	: SITE_ID, 12
-- Instance : null, null
-- Actor	: MICRO, 13
-- Metric	: 21, 110

310 - DATE|MICRO|SITE_ID|CATEGORY|TARGET|REVENUE (1175)

-- Source	: SITE_ID, 12
-- Instance : null, null
-- Actor	: MICRO, 13
-- Metric	: CATEGORY, 23

311 - DATE|MICRO|SITE|QTY (1176)

-- Source	: SITE, 12
-- Instance : null, null
-- Actor	: MICRO, 13
-- Metric	: 21, 110

312 - DATE|CLUSTER_ID|CATEGORY|CLUSTER_TYPE|TOTAL_RELOAD|CROSS_RELOAD (1177)

-- Source	: null, null
-- Instance : CLUSTER_TYPE, 22
-- Actor	: CLUSTER_ID, 13
-- Metric	: CATEGORY, 23	

313 - DATE|CLUSTER_ID|TOTAL_DATA|CROSS_DATA (1178)

-- Source	: null, null
-- Instance	: null, null
-- Actor	: CLUSTER_ID, 13
-- Metric	: 21, 110

314 - MONTH_ID|CLUSTER|ID_OUTLET|TARGET|ACTUAL (1179)

-- Source	: CLUSTER, 13
-- Instance : null, null	
-- Actor	: ID_OUTLET, 10
-- Metric 	: 21, 110

315 - MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION (1180)

-- Source	: CLUSTER, 13
-- Instance : null, null
-- Actor	: MPC_CODE, 10
-- Metric 	: 21, 110

316 - DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT (1181)

(1)
-- Source	: SITE_ID, 12
-- Instance : null, null
-- Actor	: OUTLET, 10
-- Metric   : 21, 110

(2)
-- Source	: SITE_ID, 12
-- Instance : null, null
-- Actor	: MICRO, 13
-- Metric   : 21, 110

1168_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_daily_sso_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1169_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_rguga_injection_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1170_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_tertiary_sales_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1172_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_total_usage_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1173_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_mobo_usage_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1174_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_total_rguga_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1175_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_low_revenue_site_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1176_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_site_rguga_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
1181_ACTOR_NOT_EXIST_QUERY=select 12 as source_type_n, a.source_key_v from (select distinct source_key_v from kpi.tr_temp_uro_20k_aggr where file_id_n = ?) s where s.source_key_v not in (select ref_code_v from kpi.ms_site_master)
