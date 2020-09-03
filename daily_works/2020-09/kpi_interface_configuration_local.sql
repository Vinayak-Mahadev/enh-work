create temp view kpi_interface_configuration as
select attribute_id_n, interface_id_n, value_v from interface.ms_interface_attr where name_v = 'Field Lookup Conf' order by interface_id_n;
\copy (select * from kpi_interface_configuration) to 'E:/interface/work/enh-work/daily_works/2020-09/kpi_interface_configuration_local.csv' csv header  delimiter E'|' quote as E' ' null'' ;
drop view kpi_interface_configuration;