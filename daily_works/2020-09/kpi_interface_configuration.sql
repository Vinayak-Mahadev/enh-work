create temp view kpi_interface_configuration as
select attribute_id_n, interface_id_n, value_v from interface.ms_interface_attr where name_v = 'Field Lookup Conf' order by interface_id_n;
\copy (select * from kpi_interface_configuration) to '/home/appuser/kpi_interface_configuration_03_09_2020.csv' csv header  delimiter E'|' quote as E' ' null'' ;
drop view kpi_interface_configuration;