-- SCRIPTS TO CONFIGURE ANALYTICS KPI QUERIES FOR INTERFACE DUMPS:
-- INTERFCAE_IDs = 1094,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1122,1124,1125,1126,1127,1128,1129

--(1) SOM Summary Territory:
select 
  '1094_SOM_Summary_Territory' as report_name, 
  report_org_master.channel_category_v as channel, 
  report_org_master.region_v as region, 
  report_org_master.area_v as area, 
  report_org_master.sales_area_v as salesarea, 
  report_org_master.cluster_v as cluster, 
  report_org_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_org_master.org_short_code_v as territoryid, 
  product_brand.metrics_name_v as productbrand, 
  null as producttype, 
  product_category.metrics_name_v as productcategory, 
  product_master.metrics_id_n :: text as productid, 
  product_master.ext_reference_id_v as productextid, 
  product_master.metrics_name_v as productname, 
  sum(
    groupby_query_results.closing_qty_n
  ) as qty, 
  case when (
    sum(
      groupby_query_results.stock_in_qty_n
    ) - sum(
      groupby_query_results.stock_out_qty_n
    )
  ) > 0 then round(
    (
      (
        sum(
          groupby_query_results.stock_in_qty_n
        ) - sum(
          groupby_query_results.stock_out_qty_n
        )
      )/ 7
    ), 
    0
  ) else (
    sum(
      groupby_query_results.stock_in_qty_n
    ) - sum(
      groupby_query_results.stock_out_qty_n
    )
  ) end as avgqty, 
  case when (
    sum(
      groupby_query_results.stock_in_qty_n
    ) - sum(
      groupby_query_results.stock_out_qty_n
    )
  ) > 0 
  and round(
    (
      (
        sum(
          groupby_query_results.stock_in_qty_n
        ) - sum(
          groupby_query_results.stock_out_qty_n
        )
      )/ 7
    ), 
    0
  ) > 0 then round(
    (
      sum(
        groupby_query_results.closing_qty_n
      )/ round(
        (
          (
            sum(
              groupby_query_results.stock_in_qty_n
            ) - sum(
              groupby_query_results.stock_out_qty_n
            )
          )/ 7
        ), 
        0
      )
    ), 
    0
  ) else -1 end as businessdays 
from 
  (
    select 
      daily_stock_ledger.org_id_n as org_id_n, 
      daily_stock_ledger.metrics_id_n as metrics_id_n, 
      sum(
        daily_stock_ledger.closing_stock_qty_n
      ) as closing_qty_n, 
      sum(
        average_stock_ledger.stock_in_qty_n
      ) as stock_in_qty_n, 
      sum(
        average_stock_ledger.stock_out_qty_n
      ) as stock_out_qty_n 
    from 
      kpi.tr_daily_stock_ledger as daily_stock_ledger 
      inner join kpi.tr_daily_stock_ledger as average_stock_ledger on (
        daily_stock_ledger.day_id_n = (
          select 
            to_number(
              to_char(
                (
                  select 
                    date 'yesterday'
                ), 
                'yyyymmdd'
              ), 
              '99999999'
            )
        ) 
        and average_stock_ledger.day_id_n between (
          select 
            to_char(
              (
                select 
                  date 'yesterday' - interval '6 days'
              ), 
              'yyyymmdd'
            ):: numeric
        ) 
        and daily_stock_ledger.day_id_n 
        and daily_stock_ledger.account_id_n = average_stock_ledger.account_id_n 
        and daily_stock_ledger.node_id_n = average_stock_ledger.node_id_n 
        and daily_stock_ledger.org_id_n = average_stock_ledger.org_id_n 
        and daily_stock_ledger.metrics_id_n = average_stock_ledger.metrics_id_n
      ) 
    group by 
      daily_stock_ledger.org_id_n, 
      daily_stock_ledger.metrics_id_n
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.org_id_n = groupby_query_results.org_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  left join kpi.ms_metrics_master as product_brand on (
    product_brand.metrics_type_n = 16 :: numeric 
    and product_brand.metrics_id_n = product_master.brand_id_n
  ) 
group by 
  channel, 
  region, 
  area, 
  salesarea, 
  cluster, 
  microcluster, 
  additionalterritory, 
  territoryid, 
  producttype, 
  productbrand, 
  productcategory, 
  productname, 
  productid, 
  productextid;

--(2) SOM Summary Org Wise:  
select 
  '1106_SOM_Summary_Organization_Wise' as report_name, 
  report_org_master.channel_category_v as channel, 
  report_org_master.region_v as region, 
  report_org_master.area_v as area, 
  report_org_master.sales_area_v as salesarea, 
  report_org_master.cluster_v as cluster, 
  report_org_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_org_master.org_short_code_v as territoryid, 
  report_org_master.sub_org_type_name_v as suborgtype, 
  report_org_master.org_id_n :: text as organizationid, 
  report_org_master.org_short_code_v as orgshortcode, 
  report_org_master.org_name_v as organizationname, 
  product_brand.metrics_name_v as productbrand, 
  null as producttype, 
  product_category.metrics_name_v as productcategory, 
  product_master.metrics_id_n :: text as productid, 
  product_master.ext_reference_id_v as productextid, 
  product_master.metrics_name_v as productname, 
  sum(
    groupby_query_results.closing_qty_n
  ) as qty, 
  case when (
    sum(
      groupby_query_results.stock_in_qty_n
    ) - sum(
      groupby_query_results.stock_out_qty_n
    )
  ) > 0 then round(
    (
      (
        sum(
          groupby_query_results.stock_in_qty_n
        ) - sum(
          groupby_query_results.stock_out_qty_n
        )
      )/ 7
    ), 
    0
  ) else (
    sum(
      groupby_query_results.stock_in_qty_n
    ) - sum(
      groupby_query_results.stock_out_qty_n
    )
  ) end as avgqty, 
  case when (
    sum(
      groupby_query_results.stock_in_qty_n
    ) - sum(
      groupby_query_results.stock_out_qty_n
    )
  ) > 0 
  and round(
    (
      (
        sum(
          groupby_query_results.stock_in_qty_n
        ) - sum(
          groupby_query_results.stock_out_qty_n
        )
      )/ 7
    ), 
    0
  ) > 0 then round(
    (
      sum(
        groupby_query_results.closing_qty_n
      )/ round(
        (
          (
            sum(
              groupby_query_results.stock_in_qty_n
            ) - sum(
              groupby_query_results.stock_out_qty_n
            )
          )/ 7
        ), 
        0
      )
    ), 
    0
  ) else -1 end as businessdays 
from 
  (
    select 
      daily_stock_ledger.org_id_n as org_id_n, 
      daily_stock_ledger.metrics_id_n as metrics_id_n, 
      sum(
        daily_stock_ledger.closing_stock_qty_n
      ) as closing_qty_n, 
      sum(
        average_stock_ledger.stock_in_qty_n
      ) as stock_in_qty_n, 
      sum(
        average_stock_ledger.stock_out_qty_n
      ) as stock_out_qty_n 
    from 
      kpi.tr_daily_stock_ledger as daily_stock_ledger 
      inner join kpi.tr_daily_stock_ledger as average_stock_ledger on (
        daily_stock_ledger.day_id_n = (
          select 
            to_number(
              to_char(
                (
                  select 
                    date 'yesterday'
                ), 
                'yyyymmdd'
              ), 
              '99999999'
            )
        ) 
        and average_stock_ledger.day_id_n between (
          select 
            to_char(
              (
                select 
                  date 'yesterday' - interval '6 days'
              ), 
              'yyyymmdd'
            ):: numeric
        ) 
        and daily_stock_ledger.day_id_n 
        and daily_stock_ledger.account_id_n = average_stock_ledger.account_id_n 
        and daily_stock_ledger.node_id_n = average_stock_ledger.node_id_n 
        and daily_stock_ledger.org_id_n = average_stock_ledger.org_id_n 
        and daily_stock_ledger.metrics_id_n = average_stock_ledger.metrics_id_n
      ) 
    group by 
      daily_stock_ledger.org_id_n, 
      daily_stock_ledger.metrics_id_n
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.org_id_n = groupby_query_results.org_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  left join kpi.ms_metrics_master as product_brand on (
    product_brand.metrics_type_n = 16 :: numeric 
    and product_brand.metrics_id_n = product_master.brand_id_n
  ) 
group by 
  channel, 
  region, 
  area, 
  salesarea, 
  cluster, 
  microcluster, 
  additionalterritory, 
  territoryid, 
  suborgtype, 
  organizationid, 
  orgshortcode, 
  organizationname, 
  productbrand, 
  producttype, 
  productcategory, 
  productname, 
  productid, 
  productextid;

--(3) POI Revenue LACCI Wise:  
select 
  '1107_POI_Revenue_LACCI_Wise' as report_name, 
  report_day_master.date_v as datetime, 
  null as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_network_master.territory_code_v as territoryid, 
  report_network_master.poi_type_name_v as poitype, 
  null as poicategories, 
  null as poiradiusrange, 
  report_network_master.poi_id_n :: text as poiid, 
  report_network_master.poi_name_v as poiname, 
  null as additionalinformation, 
  site_master.lookup_id_n :: text as siteid, 
  site_master.lookup_name_v :: text as sitename, 
  report_network_master.bts_type_n :: text as btstype, 
  null as lac_des, 
  null as cell_des, 
  groupby_query_results.revenue_value as revenuevalue 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.event_type_n as event_type_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      round(
        sum(
          (
            daily_upload_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        2
      ) as revenue_value 
    from 
      kpi.tr_daily_sale_revenue_lacci_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n in (21) 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.event_type_n, 
      daily_upload_metrics_aggr.actor_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_network_master as report_network_master on (
    groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
  ) 
  inner join kpi.ms_lookup_master as site_master on (
    report_network_master.site_id_n = site_master.lookup_id_n :: text 
    and site_master.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_type_master 
      where 
        ext_lookup_type_n = 115
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(4) POI Revenue Org Wise:  
select 
  '1108_POI_Revenue_Org_Wise' as report_name, 
  report_day_master.date_v as datetime, 
  report_org_master.channel_category_v as channel, 
  report_org_master.region_v as region, 
  report_org_master.area_v as area, 
  report_org_master.sales_area_v as salesarea, 
  report_org_master.cluster_v as cluster, 
  report_org_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_org_master.org_short_code_v as territoryid, 
  report_org_master.poi_type_name_v as poitypename, 
  null as poicategories, 
  null as poiradiusrange, 
  report_org_master.poi_id_n :: text as poiid, 
  report_org_master.poi_name_v as poiname, 
  null as additionalinformation, 
  report_org_master.sub_org_type_name_v as suborgtype, 
  report_org_master.org_name_v as organizationname, 
  report_org_master.org_short_code_v as orgshortcode, 
  transaction_type.metrics_name_v as transactiontype, 
  groupby_query_results.revenuehits as revenuehits, 
  groupby_query_results.revenuevalue as revenuevalue 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.source_id_n as source_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      sum(
        round(
          (
            daily_upload_metrics_aggr.dimension_1_n / 100
          ), 
          0
        )
      ) as revenuehits, 
      sum(
        round(
          (
            daily_upload_metrics_aggr.dimension_2_n / 100
          ), 
          2
        )
      ) as revenuevalue 
    from 
      kpi.tr_daily_sale_revenue_org_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n = 33 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.source_id_n, 
      daily_upload_metrics_aggr.metrics_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    groupby_query_results.source_id_n = report_org_master.aggregation_id_n 
    and groupby_query_results.actor_id_n = report_org_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as transaction_type on (
    groupby_query_results.metrics_id_n = transaction_type.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    report_day_master.day_id_n = groupby_query_results.day_id_n
  );

--(5)	Hotspot_Performance:  
select 
  '1109_Hotspot_Performance' as report_name, 
  report_day_master.date_v as datetime, 
  report_node_master.channel_category_v as channel, 
  report_node_master.region_v as region, 
  report_node_master.area_v as area, 
  report_node_master.sales_area_v as salesarea, 
  report_node_master.cluster_v as cluster, 
  report_node_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_node_master.org_short_code_v as territoryid, 
  report_node_master.sub_org_type_name_v as hotspottype, 
  report_node_master.hospot_category_v as hotspotcategory, 
  null as hostspotradiusrange, 
  report_node_master.org_id_n as hotspotid, 
  report_node_master.hotspot_name_v as hotspotname, 
  null as additionalinformation, 
  case when(
    groupby_query_results.event_type_n = 2
  ) then 'sellin' else case when(
    groupby_query_results.event_type_n = 3
  ) then 'sellout' end end as transactiontype, 
  groupby_query_results.transaction_hits as transactionhits, 
  groupby_query_results.transaction_value as transactionvalue 
from 
  (
    select 
      daily_metrics_aggr.day_id_n as day_id_n, 
      daily_metrics_aggr.event_type_n as event_type_n, 
      daily_metrics_aggr.actor_id_n as actor_id_n, 
      daily_metrics_aggr.source_id_n as source_id_n, 
      round(
        sum(
          daily_metrics_aggr.no_of_events_n
        ), 
        0
      ) as transaction_hits, 
      round(
        (
          sum(
            daily_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as transaction_value 
    from 
      kpi.tr_daily_event_metrics_aggr as daily_metrics_aggr 
    where 
      daily_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_metrics_aggr.event_type_n in (2, 3) 
      and daily_metrics_aggr.metrics_type_n = 13 
    group by 
      daily_metrics_aggr.day_id_n, 
      daily_metrics_aggr.event_type_n, 
      daily_metrics_aggr.actor_id_n, 
      daily_metrics_aggr.source_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_node_master as report_node_master on (
    report_node_master.aggregation_id_n = groupby_query_results.actor_id_n 
    and report_node_master.org_aggr_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    report_day_master.day_id_n = groupby_query_results.day_id_n
  );

--(6)	Reload A# By LACCI:  
select 
  '1110_Reload_A_By_LACCI' as report_name, 
  report_day_master.date_v as date, 
  null as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_network_master.territory_code_v as territoryid, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  reload_system.metrics_name_v as systemsource, 
  null as denomination, 
  groupby_query_results.reload_count as reloadcount, 
  groupby_query_results.reload_value as reloadvalue 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        0
      ) as reload_count, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as reload_value 
    from 
      kpi.tr_daily_reload_seller_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n = 30 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.instance_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_network_master as report_network_master on (
    groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as reload_system on (
    groupby_query_results.instance_id_n = reload_system.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(7)	Reload B# By LACCI:  
select 
  '1111_Reload_B_By_LACCI' as report_name, 
  report_day_master.date_v as date, 
  null as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_network_master.territory_code_v as territoryid, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  reload_system.metrics_name_v as systemsource, 
  null as denomination, 
  groupby_query_results.reload_count as reloadcount, 
  groupby_query_results.reload_value as reloadvalue 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.instance_type_n as instance_type_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        0
      ) as reload_count, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as reload_value 
    from 
      kpi.tr_daily_reload_buyer_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n in (30) 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.instance_type_n, 
      daily_upload_metrics_aggr.instance_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_network_master as report_network_master on (
    groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as reload_system on (
    groupby_query_results.instance_id_n = reload_system.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(8) Detail Package Activation A#:  
select 
  '1112_Detail_Package_Activation_A' as report_name, 
  report_day_master.date_v as date, 
  null as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_network_master.territory_code_v as territoryid, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  system_source.metrics_name_v as systemsource, 
  null as sid, 
  null as smstext, 
  metrics_master.ext_reference_id_v as packagecode, 
  metrics_master.metrics_name_v as packagename, 
  groupby_query_results.activation_qty as qty, 
  groupby_query_results.activation_value as amount 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      round(
        sum(
          (
            daily_upload_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        2
      ) as activation_qty, 
      round(
        sum(
          (
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as activation_value 
    from 
      kpi.tr_daily_package_act_seller_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n in (13) 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_network_master as report_network_master on (
    groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as system_source on (
    groupby_query_results.instance_id_n = system_source.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as metrics_master on (
    groupby_query_results.metrics_id_n = metrics_master.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(9) Detail Package Activation B#:  
select 
  '1113_Detail_Package_Activation_B' as report_name, 
  report_day_master.date_v as date, 
  null as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_network_master.territory_code_v as territoryid, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  system_source.metrics_name_v as systemsource, 
  null as sid, 
  null as smstext, 
  metrics_master.ext_reference_id_v as packagecode, 
  metrics_master.metrics_name_v as packagename, 
  groupby_query_results.activation_qty as qty, 
  groupby_query_results.activation_value as amount 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      round(
        sum(
          (
            daily_upload_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        2
      ) as activation_qty, 
      round(
        sum(
          (
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as activation_value 
    from 
      kpi.tr_daily_package_act_buyer_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n in (13) 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_network_master as report_network_master on (
    groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as system_source on (
    groupby_query_results.instance_id_n = system_source.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as metrics_master on (
    groupby_query_results.metrics_id_n = metrics_master.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(10) Rev by MC:  
select 
  '1114_Rev_By_MC' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.region_v as region, 
  report_org_master.area_v as area, 
  report_org_master.sales_area_v as salesarea, 
  report_org_master.cluster_v as cluster, 
  report_org_master.micro_cluster_v as microcluster, 
  revenue_category.metrics_name_v as revenuecategory, 
  revenue_type.metrics_name_v as revenuetype, 
  groupby_query_results.revenue as revenue 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.source_id_n as source_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as revenue 
    from 
      kpi.tr_daily_usage_metrics_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n = 37 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.source_id_n, 
      daily_upload_metrics_aggr.metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    groupby_query_results.source_id_n = report_org_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as revenue_type on (
    groupby_query_results.metrics_id_n = revenue_type.metrics_id_n
  ) 
  left join kpi.ms_metrics_master as revenue_category on (
    groupby_query_results.instance_id_n = revenue_category.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(11) Rev by Lacci:  
select 
  '1115_Rev_By_LACCI' as report_name, 
  report_day_master.date_v as date, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  null as additionalST, 
  report_network_master.lac_id_v as lacci, 
  report_network_master.cell_id_v as cellid, 
  revenue_category.metrics_name_v as revenuecategory, 
  revenue_type.metrics_name_v as revenuetype, 
  groupby_query_results.revenue as revenue 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.source_id_n as source_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as revenue 
    from 
      kpi.tr_daily_usage_metrics_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.metrics_type_n = 37 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.source_id_n, 
      daily_upload_metrics_aggr.metrics_id_n, 
      daily_upload_metrics_aggr.instance_id_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_network_master as report_network_master on (
    groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
  ) 
  inner join kpi.ms_metrics_master as revenue_type on (
    groupby_query_results.metrics_id_n = revenue_type.metrics_id_n
  ) 
  left join kpi.ms_metrics_master as revenue_category on (
    groupby_query_results.instance_id_n = revenue_category.metrics_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(12) Kpi Report:  
select 
  '1116_KPI_Detail' as report_name, 
  report_day_master.date_v as date, 
  report_user_master.user_id_n :: text as userid, 
  report_user_master.user_name_v as username, 
  report_user_master.org_short_code_v as saldomoborefcode, 
  report_user_master.region_v as region, 
  report_user_master.area_v as area, 
  report_user_master.sales_area_v as salesarea, 
  report_user_master.cluster_v as cluster, 
  report_user_master.micro_cluster_v as microcluster, 
  null as additionalterrtirory, 
  metrics_master.metrics_name_v as kpiname, 
  groupby_query_results.targetperiod as targetperiod, 
  groupby_query_results.achievement as achievement, 
  groupby_query_results.target as target, 
  groupby_query_results.achievementpercent as achievementpercent, 
  groupby_query_results.result as result 
from 
  (
    select 
      daily_upload_aggr.day_id_n as day_id_n, 
      daily_upload_aggr.actor_id_n as actor_id_n, 
      daily_upload_aggr.metrics_id_n as metrics_id_n, 
      'Period-' || daily_target_aggr.data_flag_n :: text as targetperiod, 
      case when sum(
        daily_target_aggr.achievement_n
      ) is not null then round(
        (
          sum(
            daily_target_aggr.achievement_n
          )/ 100
        ), 
        2
      ) else round(
        (
          sum(
            daily_upload_aggr.dimension_1_n
          )/ 100
        ), 
        2
      ) end as achievement, 
      round(
        (
          sum(daily_target_aggr.target_n)/ 100
        ), 
        2
      ) as target, 
      round(
        (
          sum(daily_target_aggr.percentage_n)/ 100
        ), 
        2
      ) as achievementpercent, 
      case when (
        sum(
          daily_target_aggr.status_flag_n
        ) is not null
      ) then case when (
        sum(
          daily_target_aggr.status_flag_n
        ) > 0
      ) then 'Success' else 'Failure' end else null end as result 
    from 
      kpi.tr_daily_custom_metrics_aggr as daily_upload_aggr 
      left join kpi.tr_daily_target_metrics_aggr as daily_target_aggr on (
        daily_target_aggr.day_id_n = daily_upload_aggr.day_id_n 
        and daily_target_aggr.event_type_n = daily_upload_aggr.event_type_n 
        and daily_target_aggr.metrics_type_n = daily_upload_aggr.metrics_type_n 
        and daily_target_aggr.metrics_id_n = daily_upload_aggr.metrics_id_n 
        and daily_target_aggr.actor_type_n = daily_upload_aggr.actor_type_n 
        and daily_target_aggr.actor_id_n = daily_upload_aggr.actor_id_n 
        and daily_target_aggr.description_v ilike 'achievement'
      ) 
    where 
      daily_upload_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
    group by 
      daily_upload_aggr.day_id_n, 
      daily_upload_aggr.metrics_id_n, 
      daily_upload_aggr.actor_id_n, 
      daily_target_aggr.data_flag_n 
    order by 
      daily_upload_aggr.day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_metrics_master as metrics_master on (
    groupby_query_results.metrics_id_n = metrics_master.metrics_id_n
  ) 
  inner join kpi.ms_report_user_master as report_user_master on (
    report_user_master.node_aggr_id_n = groupby_query_results.actor_id_n
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    report_day_master.day_id_n = groupby_query_results.day_id_n
  );

--(13) Summary HVC-Report:  
select 
  '1122_Summary_HVC_Report' as report_name, 
  report_day_master.date_dt as date, 
  report_day_master.month_v as month, 
  report_day_master.year_v as year, 
  report_user_master.channel_category_v as channelcategory, 
  report_user_master.region_v as region, 
  report_user_master.area_v as area, 
  report_user_master.sales_area_v as salesarea, 
  report_user_master.cluster_v as cluster, 
  report_user_master.micro_cluster_v as microcluster, 
  null as additionalterritory, 
  report_user_master.sub_org_type_name_v as suborgtype, 
  report_user_master.org_short_code_v as orgshortcode, 
  report_user_master.org_name_v as organization, 
  report_user_master.position_name_v as position, 
  report_user_master.node_id_n :: text as nodeid, 
  report_user_master.node_name_v as nodename, 
  report_user_master.operator_type_v as operatortype, 
  report_user_master.operator_id_v as operatorid, 
  report_user_master.user_name_v as username, 
  report_user_master.operator_name_v as operatorname, 
  groupby_query_results.selloutqty as selloutqty, 
  groupby_query_results.activitionqty as activitionqty, 
  groupby_query_results.selloutamount as selloutamount, 
  groupby_query_results.activitionamount as activitionamount, 
  reload_query_results.tresholdreloadqty as tresholdreloadqty, 
  reload_query_results.tresholdreloadamount as tresholdreloadamount 
from 
  (
    select 
      daily_metrics_aggr.day_id_n as day_id_n, 
      daily_metrics_aggr.actor_id_n as actor_id_n, 
      round(
        (
          (
            sum(
              daily_metrics_aggr.dimension_1_n
            ) filter (
              where 
                daily_metrics_aggr.event_type_n = 3
            )
          )/ 100
        ), 
        0
      ) as selloutqty, 
      round(
        (
          (
            sum(
              daily_metrics_aggr.dimension_2_n
            ) filter (
              where 
                daily_metrics_aggr.event_type_n = 3
            )
          )/ 100
        ), 
        2
      ) as selloutamount, 
      round(
        (
          (
            sum(
              daily_metrics_aggr.dimension_1_n
            ) filter (
              where 
                daily_metrics_aggr.event_type_n = 6
            )
          )/ 100
        ), 
        0
      ) as activitionqty, 
      round(
        (
          (
            sum(
              daily_metrics_aggr.dimension_2_n
            ) filter (
              where 
                daily_metrics_aggr.event_type_n = 6
            )
          )/ 100
        ), 
        2
      ) as activitionamount 
    from 
      kpi.tr_daily_event_metrics_aggr as daily_metrics_aggr 
    where 
      daily_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_metrics_aggr.event_type_n in (3, 6) 
      and daily_metrics_aggr.actor_type_n = 2 
      and daily_metrics_aggr.metrics_type_n = 13 
    group by 
      daily_metrics_aggr.day_id_n, 
      daily_metrics_aggr.actor_id_n
  ) as groupby_query_results 
  inner join kpi.ms_report_user_master as report_user_master on (
    report_user_master.node_aggr_id_n = groupby_query_results.actor_id_n
  ) 
  left join lateral (
    select 
      round(
        (
          sum(
            daily_upload_aggr.dimension_1_n
          )/ 100
        ), 
        2
      ) as tresholdreloadqty, 
      round(
        (
          sum(
            daily_upload_aggr.dimension_2_n
          )/ 100
        ), 
        2
      ) as tresholdreloadamount 
    from 
      kpi.tr_daily_reload_customer_aggr as daily_upload_aggr 
    where 
      daily_upload_aggr.day_id_n = groupby_query_results.day_id_n 
      and daily_upload_aggr.event_type_n = 109 
      and report_user_master.aggregation_id_n = daily_upload_aggr.source_id_n 
      and daily_upload_aggr.day_id_n = (
        select 
          to_number(
            daily_upload_aggr.instance_id_n :: text, 
            '999999'
          )
      ) 
      and (
        daily_upload_aggr.dimension_1_n / 100
      ) >= 1000
  ) as reload_query_results on (true) 
  inner join kpi.ms_report_day_master as report_day_master on (
    report_day_master.day_id_n = groupby_query_results.day_id_n
  );

--(14) Summary CS Reload Physical Report:  
select 
  '1124_Summary_CS_Reload_Physical_Report' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.channel_category_v as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  groupby_query_results.territoryid as territoryid, 
  null as additionalterritory, 
  product_master.ext_reference_id_v as physicalproductcode, 
  product_category.metrics_name_v as physicalproductcategory, 
  product_master.metrics_name_v as physicalproductname, 
  groupby_query_results.territorycrossellingtype as territorycrossellingtype, 
  null as totalvalue, 
  groupby_query_results.totalqty as totalqty, 
  null as crossellingactivationvalue, 
  groupby_query_results.crossellingactivationqty as crossellingactivationqty, 
  null as inneractivationvalue, 
  groupby_query_results.inneractivationqty as inneractivationqty 
from 
  (
    select 
      daily_event_metrics_aggr.day_id_n as day_id_n, 
      daily_event_metrics_aggr.actor_id_n as actor_id_n, 
      daily_event_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_event_metrics_aggr.instance_type_n as instance_type_n, 
      daily_event_metrics_aggr.instance_id_n as instance_id_n, 
      daily_event_metrics_aggr.source_id_n as source_id_n, 
      daily_event_metrics_aggr.data_flag_n as data_flag_n, 
      daily_crossell.instance_id_n as territorycrossellingtype, 
      daily_crossell.instance_type_n as territoryid, 
      round(
        (
          sum(
            daily_not_crossell.dimension_1_n
          )/ 100
        ), 
        0
      ) as inneractivationqty, 
      round(
        (
          sum(daily_crossell.dimension_1_n)/ 100
        ), 
        0
      ) as crossellingactivationqty, 
      round(
        (
          sum(
            daily_event_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        0
      ) as totalqty 
    from 
      kpi.tr_daily_event_metrics_aggr as daily_event_metrics_aggr 
      left join kpi.tr_daily_event_metrics_aggr as daily_crossell on (
        daily_event_metrics_aggr.day_id_n = daily_crossell.day_id_n 
        and daily_crossell.data_flag_n in (1)
      ) 
      left join kpi.tr_daily_event_metrics_aggr as daily_not_crossell on (
        daily_event_metrics_aggr.day_id_n = daily_not_crossell.day_id_n 
        and daily_not_crossell.data_flag_n in (2)
      ) 
    where 
      daily_event_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_event_metrics_aggr.event_type_n = 8 
      and daily_event_metrics_aggr.actor_type_n = 8 
      and daily_event_metrics_aggr.metrics_type_n in (13) 
      and daily_event_metrics_aggr.data_flag_n in (1, 2) 
    group by 
      daily_event_metrics_aggr.day_id_n, 
      daily_event_metrics_aggr.actor_id_n, 
      daily_event_metrics_aggr.metrics_id_n, 
      daily_event_metrics_aggr.instance_type_n, 
      daily_event_metrics_aggr.instance_id_n, 
      daily_event_metrics_aggr.source_id_n, 
      daily_event_metrics_aggr.data_flag_n, 
      daily_crossell.instance_id_n, 
      daily_crossell.instance_type_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.aggregation_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  inner join kpi.ms_lookup_master as origin_cluster on (
    groupby_query_results.instance_id_n = origin_cluster.lookup_id_n
  ) 
  inner join kpi.ms_lookup_master as crossell_cluster on (
    groupby_query_results.instance_id_n = crossell_cluster.lookup_id_n 
    and crossell_cluster.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_master as lookup_master 
      where 
        lookup_master.lookup_id_n = groupby_query_results.instance_type_n
    )
  ) 
  inner join kpi.ms_report_network_master as report_network_master on (
    (
      groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
    ) 
    or (
      origin_cluster.lookup_id_n = report_network_master.territory_id_n 
      and origin_cluster.lookup_type_n = report_network_master.territory_type_n
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(15) CS Reload Physical Report:  
select 
  '1125_CS_Reload_Physical_Report' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.channel_category_v as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  groupby_query_results.instance_type_n as territoryid, 
  null as additionalterritory, 
  product_master.ext_reference_id_v as physicalproductcode, 
  product_category.metrics_name_v as physicalproductcategory, 
  product_master.metrics_name_v as physicalproductname, 
  groupby_query_results.instance_id_n as territorycrossellingtype, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  null as targetterritoryname, 
  null as targetterritoryid, 
  null as totalvalue, 
  groupby_query_results.totalqty as totalqty 
from 
  (
    select 
      daily_event_metrics_aggr.day_id_n as day_id_n, 
      daily_event_metrics_aggr.actor_id_n as actor_id_n, 
      daily_event_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_event_metrics_aggr.instance_type_n as instance_type_n, 
      daily_event_metrics_aggr.instance_id_n as instance_id_n, 
      daily_event_metrics_aggr.source_id_n as source_id_n, 
      daily_event_metrics_aggr.data_flag_n as data_flag_n, 
      round(
        (
          sum(
            daily_event_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        0
      ) as totalqty 
    from 
      kpi.tr_daily_event_metrics_aggr as daily_event_metrics_aggr 
    where 
      daily_event_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_event_metrics_aggr.event_type_n = 8 
      and daily_event_metrics_aggr.actor_type_n = 8 
      and daily_event_metrics_aggr.metrics_type_n in (13) 
      and daily_event_metrics_aggr.data_flag_n in (1) 
    group by 
      daily_event_metrics_aggr.day_id_n, 
      daily_event_metrics_aggr.actor_id_n, 
      daily_event_metrics_aggr.metrics_id_n, 
      daily_event_metrics_aggr.instance_type_n, 
      daily_event_metrics_aggr.instance_id_n, 
      daily_event_metrics_aggr.source_type_n, 
      daily_event_metrics_aggr.source_id_n, 
      daily_event_metrics_aggr.data_flag_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.aggregation_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  inner join kpi.ms_lookup_master as origin_cluster on (
    groupby_query_results.instance_id_n = origin_cluster.lookup_id_n
  ) 
  inner join kpi.ms_lookup_master as crossell_cluster on (
    groupby_query_results.instance_id_n = crossell_cluster.lookup_id_n 
    and crossell_cluster.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_master as lookup_master 
      where 
        lookup_master.lookup_id_n = groupby_query_results.instance_type_n
    )
  ) 
  inner join kpi.ms_report_network_master as report_network_master on (
    (
      groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
    ) 
    or (
      origin_cluster.lookup_id_n = report_network_master.territory_id_n 
      and origin_cluster.lookup_type_n = report_network_master.territory_type_n
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(16) Sum CS Reload of E-Voucher Report:  
select 
  '1126_Sum_CS_Reload_of_e_voucher' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.channel_category_v as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  groupby_query_results.territoryid as territoryid, 
  null as additionalterritory, 
  product_master.ext_reference_id_v as physicalproductcode, 
  product_category.metrics_name_v as physicalproductcategory, 
  product_master.metrics_name_v as physicalproductname, 
  groupby_query_results.territorycrossellingtype as territorycrossellingtype, 
  null as totalqty, 
  groupby_query_results.totalamount as totalamount, 
  null as crossellingactivationqty, 
  groupby_query_results.crossellingactivationamount as crossellingactivationamount, 
  null as inneractivationqty, 
  groupby_query_results.inneractivationamount as inneractivationamount 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_upload_metrics_aggr.source_id_n as source_id_n, 
      daily_upload_metrics_aggr.data_flag_n as data_flag_n, 
      daily_crossell.instance_type_n as territoryid, 
      daily_crossell.instance_id_n as territorycrossellingtype, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        0
      ) as totalamount, 
      round(
        (
          sum(daily_crossell.dimension_2_n)/ 100
        ), 
        0
      ) as crossellingactivationamount, 
      round(
        (
          sum(
            daily_not_crossell.dimension_2_n
          )/ 100
        ), 
        0
      ) as inneractivationamount 
    from 
      kpi.tr_daily_mobo_crosselling_aggr as daily_upload_metrics_aggr 
      left join kpi.tr_daily_mobo_crosselling_aggr as daily_crossell on (
        daily_upload_metrics_aggr.day_id_n = daily_crossell.day_id_n 
        and daily_crossell.data_flag_n = 1
      ) 
      left join kpi.tr_daily_mobo_crosselling_aggr as daily_not_crossell on (
        daily_upload_metrics_aggr.day_id_n = daily_not_crossell.day_id_n 
        and daily_not_crossell.data_flag_n = 2
      ) 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.event_type_n = 116 
      and daily_upload_metrics_aggr.data_flag_n in (1, 2, 3, 4) 
    group by 
      daily_upload_metrics_aggr.day_id_n, 
      daily_upload_metrics_aggr.actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n, 
      daily_upload_metrics_aggr.source_id_n, 
      daily_upload_metrics_aggr.data_flag_n, 
      territorycrossellingtype, 
      territoryid 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.aggregation_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  inner join kpi.ms_lookup_master as origin_cluster on (
    groupby_query_results.territorycrossellingtype = origin_cluster.lookup_id_n
  ) 
  inner join kpi.ms_lookup_master as crossell_cluster on (
    groupby_query_results.territorycrossellingtype = crossell_cluster.lookup_id_n 
    and crossell_cluster.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_master as lookup_master 
      where 
        lookup_master.lookup_id_n = groupby_query_results.territoryid
    )
  ) 
  inner join kpi.ms_report_network_master as report_network_master on (
    (
      groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
    ) 
    or (
      origin_cluster.lookup_id_n = report_network_master.territory_id_n 
      and origin_cluster.lookup_type_n = report_network_master.territory_type_n
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(17) Detail CS Reload of E-Voucher:  
select 
  '1127_Detail_CS_Reload_of_e_voucher' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.channel_category_v as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  groupby_query_results.instance_id_n as territoryid, 
  null as additionalterritory, 
  product_master.ext_reference_id_v as physicalproductcode, 
  product_category.metrics_name_v as physicalproductcategory, 
  product_master.metrics_name_v as physicalproductname, 
  groupby_query_results.instance_type_n as territorycrossellingtype, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  case when (
    groupby_query_results.data_flag_n = 3
  ) then origin_cluster.lookup_name_v else null end as bterritoryname, 
  case when (
    groupby_query_results.data_flag_n = 3
  ) then origin_cluster.lookup_id_n else null end as bterritoryid, 
  report_day_master.date_v as date, 
  null as totalqty, 
  groupby_query_results.totalamount as totalamount 
from 
  (
    select 
      daily_upload_metrics_aggr.day_id_n as day_id_n, 
      daily_upload_metrics_aggr.actor_id_n as actor_id_n, 
      daily_upload_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_upload_metrics_aggr.instance_type_n as instance_type_n, 
      daily_upload_metrics_aggr.instance_id_n as instance_id_n, 
      daily_upload_metrics_aggr.source_id_n as source_id_n, 
      daily_upload_metrics_aggr.data_flag_n as data_flag_n, 
      round(
        (
          sum(
            daily_upload_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        0
      ) as totalamount 
    from 
      kpi.tr_daily_mobo_crosselling_aggr as daily_upload_metrics_aggr 
    where 
      daily_upload_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_upload_metrics_aggr.event_type_n = 116 
      and daily_upload_metrics_aggr.data_flag_n in (1, 3) 
    group by 
      day_id_n, 
      actor_id_n, 
      metrics_id_n, 
      instance_type_n, 
      instance_id_n, 
      source_id_n, 
      data_flag_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.aggregation_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  inner join kpi.ms_lookup_master as origin_cluster on (
    groupby_query_results.instance_id_n = origin_cluster.lookup_id_n
  ) 
  inner join kpi.ms_lookup_master as crossell_cluster on (
    groupby_query_results.instance_id_n = crossell_cluster.lookup_id_n 
    and crossell_cluster.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_master as lookup_master 
      where 
        lookup_master.lookup_id_n = groupby_query_results.instance_type_n
    )
  ) 
  inner join kpi.ms_report_network_master as report_network_master on (
    (
      groupby_query_results.actor_id_n = report_network_master.aggregation_id_n 
      and groupby_query_results.data_flag_n = 3
    ) 
    or (
      origin_cluster.lookup_id_n = report_network_master.territory_id_n 
      and origin_cluster.lookup_type_n = report_network_master.territory_type_n
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(18) Summary CS SP Act:  
select 
  '1128_Summary_CS_SP_Act' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.channel_category_v as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  groupby_query_results.territoryid as territoryid, 
  null as additionalterritory, 
  product_master.ext_reference_id_v as physicalproductcode, 
  product_category.metrics_name_v as physicalproductcategory, 
  product_master.metrics_name_v as physicalproductname, 
  groupby_query_results.territorycrossellingtype as territorycrossellingtype, 
  null as totalvalue, 
  groupby_query_results.totalqty as totalqty, 
  null as crossellingactivationvalue, 
  groupby_query_results.crossellingactivationqty as crossellingactivationqty, 
  null as inneractivationvalue, 
  groupby_query_results.inneractivationqty as inneractivationqty 
from 
  (
    select 
      daily_metrics_aggr.day_id_n as day_id_n, 
      daily_metrics_aggr.actor_id_n as actor_id_n, 
      daily_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_metrics_aggr.source_id_n as source_id_n, 
      daily_crossell.instance_type_n as territoryid, 
      daily_crossell.instance_id_n as territorycrossellingtype, 
      daily_metrics_aggr.data_flag_n as data_flag_n, 
      round(
        (
          sum(
            daily_metrics_aggr.dimension_2_n
          )/ 100
        ), 
        0
      ) as totalqty, 
      round(
        (
          sum(daily_crossell.dimension_2_n)/ 100
        ), 
        0
      ) as crossellingactivationqty, 
      round(
        (
          sum(
            daily_not_crossell.dimension_2_n
          )/ 100
        ), 
        0
      ) as inneractivationqty 
    from 
      kpi.tr_daily_event_metrics_aggr as daily_metrics_aggr 
      left join kpi.tr_daily_event_crossell_aggr as daily_crossell on (
        daily_metrics_aggr.day_id_n = daily_crossell.day_id_n 
        and daily_crossell.data_flag_n = 1
      ) 
      left join kpi.tr_daily_event_crossell_aggr as daily_not_crossell on (
        daily_metrics_aggr.day_id_n = daily_not_crossell.day_id_n 
        and daily_not_crossell.data_flag_n = 2
      ) 
    where 
      daily_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_metrics_aggr.event_type_n = 6 
      and daily_metrics_aggr.metrics_type_n in (13) 
      and daily_metrics_aggr.data_flag_n in (1, 2) 
    group by 
      daily_metrics_aggr.day_id_n, 
      daily_metrics_aggr.actor_id_n, 
      daily_metrics_aggr.metrics_id_n, 
      daily_metrics_aggr.instance_id_n, 
      daily_metrics_aggr.source_id_n, 
      daily_metrics_aggr.data_flag_n, 
      territorycrossellingtype, 
      territoryid 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.aggregation_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n 
    and product_master.category_id_n in (
      select 
        metrics_id_n 
      from 
        kpi.ms_metrics_master 
      where 
        ext_metrics_id_v in (
          select 
            unnest(
              string_to_array(
                property_value_v :: text, ',' :: text
              ):: character varying[]
            ) 
          from 
            kpi.ms_property_master 
          where 
            property_key_v ilike 'starter_pack_master'
        )
    )
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  inner join kpi.ms_lookup_master as origin_cluster on (
    groupby_query_results.territorycrossellingtype = origin_cluster.lookup_id_n
  ) 
  inner join kpi.ms_lookup_master as crossell_cluster on (
    groupby_query_results.territorycrossellingtype = crossell_cluster.lookup_id_n 
    and crossell_cluster.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_master as lookup_master 
      where 
        lookup_master.lookup_id_n = groupby_query_results.territoryid
    )
  ) 
  inner join kpi.ms_report_network_master as report_network_master on (
    (
      groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
    ) 
    or (
      origin_cluster.lookup_id_n = report_network_master.territory_id_n 
      and origin_cluster.lookup_type_n = report_network_master.territory_type_n
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );

--(19) Detail SP CS Act:  
select 
  '1129_Detail_SP_CS_Act' as report_name, 
  report_day_master.date_v as date, 
  report_org_master.channel_category_v as channel, 
  report_network_master.region_v as region, 
  report_network_master.area_v as area, 
  report_network_master.sales_area_v as salesarea, 
  report_network_master.cluster_v as cluster, 
  report_network_master.micro_cluster_v as microcluster, 
  groupby_query_results.instance_id_n as territoryid, 
  null as additionalterritory, 
  product_master.ext_reference_id_v as physicalproductcode, 
  product_category.metrics_name_v as physicalproductcategory, 
  product_master.metrics_name_v as physicalproductname, 
  groupby_query_results.instance_type_n as territorycrossellingtype, 
  report_network_master.lac_id_v as lacid, 
  report_network_master.cell_id_v as cellid, 
  null as targetterritoryname, 
  null as targetterritoryid, 
  null as value, 
  groupby_query_results.totalqty as qty 
from 
  (
    select 
      daily_metrics_aggr.day_id_n as day_id_n, 
      daily_metrics_aggr.actor_id_n as actor_id_n, 
      daily_metrics_aggr.metrics_id_n as metrics_id_n, 
      daily_metrics_aggr.instance_type_n as instance_type_n, 
      daily_metrics_aggr.instance_id_n as instance_id_n, 
      daily_metrics_aggr.source_id_n as source_id_n, 
      daily_metrics_aggr.data_flag_n as data_flag_n, 
      round(
        (
          sum(
            daily_metrics_aggr.dimension_1_n
          )/ 100
        ), 
        0
      ) as totalqty 
    from 
      kpi.tr_daily_event_metrics_aggr as daily_metrics_aggr 
    where 
      daily_metrics_aggr.day_id_n = (
        select 
          to_number(
            to_char(
              date(now() - interval '1 day'), 
              'yyyymmdd'
            ), 
            '99999999'
          )
      ) 
      and daily_metrics_aggr.event_type_n = 6 
      and daily_metrics_aggr.metrics_type_n in (13) 
      and daily_metrics_aggr.data_flag_n = 1 
      and daily_metrics_aggr.actor_type_n = 8 
    group by 
      day_id_n, 
      actor_id_n, 
      metrics_id_n, 
      instance_type_n, 
      instance_id_n, 
      source_id_n, 
      data_flag_n 
    order by 
      day_id_n desc
  ) as groupby_query_results 
  inner join kpi.ms_report_org_master as report_org_master on (
    report_org_master.aggregation_id_n = groupby_query_results.source_id_n
  ) 
  inner join kpi.ms_metrics_master as product_master on (
    groupby_query_results.metrics_id_n = product_master.metrics_id_n 
    and product_master.category_id_n in (
      select 
        metrics_id_n 
      from 
        kpi.ms_metrics_master 
      where 
        ext_metrics_id_v in (
          select 
            unnest(
              string_to_array(
                property_value_v :: text, ',' :: text
              ):: character varying[]
            ) 
          from 
            kpi.ms_property_master 
          where 
            property_key_v ilike 'starter_pack_master'
        )
    )
  ) 
  inner join kpi.ms_metrics_master as product_category on (
    product_category.metrics_type_n = 11 
    and product_category.metrics_id_n = product_master.category_id_n
  ) 
  inner join kpi.ms_lookup_master as origin_cluster on (
    groupby_query_results.instance_id_n = origin_cluster.lookup_id_n
  ) 
  inner join kpi.ms_lookup_master as crossell_cluster on (
    groupby_query_results.instance_id_n = crossell_cluster.lookup_id_n 
    and crossell_cluster.lookup_type_n = (
      select 
        lookup_type_n 
      from 
        kpi.ms_lookup_master as lookup_master 
      where 
        lookup_master.lookup_id_n = groupby_query_results.instance_type_n
    )
  ) 
  inner join kpi.ms_report_network_master as report_network_master on (
    (
      groupby_query_results.actor_id_n = report_network_master.aggregation_id_n
    ) 
    or (
      origin_cluster.lookup_id_n = report_network_master.territory_id_n 
      and origin_cluster.lookup_type_n = report_network_master.territory_type_n
    )
  ) 
  inner join kpi.ms_report_day_master as report_day_master on (
    groupby_query_results.day_id_n = report_day_master.day_id_n
  );
