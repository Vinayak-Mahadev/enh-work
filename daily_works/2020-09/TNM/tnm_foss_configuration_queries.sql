-- Prepared by Vinay(vinay.nagaraj@enhancesys.com)

--101, 102, 103, 104, 105, 106, 241, 242
select * from interface.ms_module where module_id_n in (101, 102, 103, 104, 105, 106, 241, 242) order by 1;
select * from interface.ms_interface where module_id_n in (101, 102, 103, 104, 105, 106, 241, 242) order by 1;
select * from interface.ms_interface_attr where interface_id_n in (select interface_id_n from interface.ms_interface where module_id_n in (101, 102, 103, 104, 105, 106, 241, 242)) order by 1;
