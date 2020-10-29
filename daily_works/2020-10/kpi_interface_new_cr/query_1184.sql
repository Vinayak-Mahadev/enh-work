SELECT
        channel_category_v  AS channel_category           ,
        regional_v          AS regional                   ,
        area_v              AS area                       ,
        sales_area_v        AS sale_sarea                 ,
        cluster_v           AS cluster                    ,
        micro_cluster_v     AS micro_cluster              ,
        sale_territory_v    AS sale_territory             ,
        territory_id_v      AS territory_id               ,
        org_id_n            AS outlet_id                  ,
        outlet_ref_code_v   AS outlet_ref_code            ,
        outlet_name_v       AS outlet_name                ,
        cso_name_v          AS cso_name                   ,
        cso_operator_id_v   AS cso_operator_id            ,
        cso_org_id_n        AS cso_org_id                 ,
        cso_org_ref_code_v  AS cso_org_ref_code           ,
        cso_org_name_v      AS cso_org_name               ,
        prd_ctgr_id_n       AS product_category_id        ,
        prd_ctgr_name_v     AS product_category_name      ,
        threshold_n         AS threshold_value            ,
        stock_value_n       AS available_stock            ,
        stock_days_n        AS stock_days                 ,
        percentage_n        AS stock_percentage           ,
        sales_flag_bl       AS prev_month_sales           ,
        prev_month_sales_n  AS prev_month_sales_value     ,
        transaction_days_n  AS prev_month_transaction_days,
        inc_by_percent_n    AS increase_by_percentage     ,
        prev_sellin_n       AS prev_day_sellin            ,
        prev_suggestion_n   AS prev_day_suggestion        ,
        rem_suggestion_n    AS remaining_suggestion       ,
        actual_suggestion_n AS actual_suggestion          ,
        pjp_status_v        AS today_pjp
FROM
        kpi.tr_stock_reorder_aggr
WHERE
        day_id_n = (select to_char('DumpDate'::timestamp,'yyyymmdd')::numeric)

UNION

SELECT
        channel_category_v  AS channel_category           ,
        regional_v          AS regional                   ,
        area_v              AS area                       ,
        sales_area_v        AS sale_sarea                 ,
        cluster_v           AS cluster                    ,
        micro_cluster_v     AS micro_cluster              ,
        sale_territory_v    AS sale_territory             ,
        territory_id_v      AS territory_id               ,
        org_id_n            AS outlet_id                  ,
        outlet_ref_code_v   AS outlet_ref_code            ,
        outlet_name_v       AS outlet_name                ,
        cso_name_v          AS cso_name                   ,
        cso_operator_id_v   AS cso_operator_id            ,
        cso_org_id_n        AS cso_org_id                 ,
        cso_org_ref_code_v  AS cso_org_ref_code           ,
        cso_org_name_v      AS cso_org_name               ,
        prd_ctgr_id_n       AS product_category_id        ,
        prd_ctgr_name_v     AS product_category_name      ,
        threshold_n         AS threshold_value            ,
        stock_value_n       AS available_stock            ,
        stock_days_n        AS stock_days                 ,
        percentage_n        AS stock_percentage           ,
        sales_flag_bl       AS prev_month_sales           ,
        prev_month_sales_n  AS prev_month_sales_value     ,
        transaction_days_n  AS prev_month_transaction_days,
        inc_by_percent_n    AS increase_by_percentage     ,
        prev_sellin_n       AS prev_day_sellin            ,
        prev_suggestion_n   AS prev_day_suggestion        ,
        rem_suggestion_n    AS remaining_suggestion       ,
        actual_suggestion_n AS actual_suggestion          ,
        pjp_status_v        AS today_pjp
FROM
        kpi.tr_stock_suggest_aggr
WHERE
        day_id_n = (select to_char('DumpDate'::timestamp,'yyyymmdd')::numeric);