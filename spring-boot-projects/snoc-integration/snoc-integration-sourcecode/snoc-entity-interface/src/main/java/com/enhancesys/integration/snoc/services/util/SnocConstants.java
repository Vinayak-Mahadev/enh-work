package com.enhancesys.integration.snoc.services.util;

import java.util.Arrays;
import java.util.List;

import com.enhancesys.integration.snoc.props.PropertiesLoader;

public interface SnocConstants 
{
	public static final Long SP_STOCK_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SP_STOCK_DUMP_INTERFACE_ID"));
	public static final Long SP_ALLOC_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SP_ALLOC_DUMP_INTERFACE_ID"));
	public static final Long VO_STOCK_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VO_STOCK_DUMP_INTERFACE_ID"));
	public static final Long VO_ALLOC_DUMP_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("VO_ALLOC_DUMP_INTERFACE_ID"));
	public static final Long PRODUCT_CREATION_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("PRODUCT_CREATION_INTERFACE_ID"));
	public static final Long SERIAL_EXPIRY_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SERIAL_EXPIRY_INTERFACE_ID"));
	public static final Long SITE_MAPPING_INTERFACE_ID = Long.valueOf(PropertiesLoader.getValueFor("SITE_MAPPING_INTERFACE_ID"));

	public static final List<String> KPI_HADOOP_FEED_INTERFACES_LIST = Arrays.asList(PropertiesLoader.getValueFor("KPI_HADOOP_FEED_INTERFACES_LIST").split(","));
	public static final List<String> KPI_FEED_INTERFACE_LIST = Arrays.asList(PropertiesLoader.getValueFor("KPI_FEED_INTERFACE_LIST").split(","));
	
}
