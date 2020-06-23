package com.enhancesys.integration.response.util;

import java.util.Arrays;
import java.util.List;

import com.enhancesys.snoc.utils.EncryptionUtil;

public interface IntegrationConstants 
{
	public static final boolean ENCRYPT_DECRYPT_FLAG = Boolean.parseBoolean(PropertiesFileLoader.getValueFor("ENCRYPT_DECRYPT_FLAG"));
	
	public static final String INTERFACE_JDBC_DRIVER=PropertiesFileLoader.getValueFor("INTERFACE_JDBC_DRIVER");

	public static final String INTERFACE_JDBC_URL=PropertiesFileLoader.getValueFor("INTERFACE_JDBC_URL");

	public static final String INTERFACE_JDBC_USER= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesFileLoader.getValueFor("INTERFACE_JDBC_USER")) : PropertiesFileLoader.getValueFor("INTERFACE_JDBC_USER");

	public static final String INTERFACE_JDBC_PASS= ENCRYPT_DECRYPT_FLAG ? EncryptionUtil.decrypt(PropertiesFileLoader.getValueFor("INTERFACE_JDBC_PASS")) : PropertiesFileLoader.getValueFor("INTERFACE_JDBC_PASS");
	
	public static final String STATIC_RESPONSE_CONF_ATTR = PropertiesFileLoader.getValueFor("STATIC_RESPONSE_CONF_ATTR");
	
	public static final String RESPONSE_CONF_ATTR = PropertiesFileLoader.getValueFor("RESPONSE_CONF_ATTR");
	
	public static final String getInterfaceAttrValQry = "select name_v, value_v from interface.ms_interface_attr where interface_id_n = ?";

	public static final Long TRANSFER_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("TRANSFER_BALANCE_INTERFACE_ID"));

	public static final Long QUERY_BALANCE_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("QUERY_BALANCE_INTERFACE_ID"));
	
	public static final Long QUERY_STOCK_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("QUERY_STOCK_INTERFACE_ID"));
	
	public static final Long CREATE_SO_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("CREATE_SO_INTERFACE_ID"));
	
	public static final List<String> SM_SYNC_INTERFACE_IDS = Arrays.asList(PropertiesFileLoader.getValueFor("SM_SYNC_INTERFACE_IDS").split(","));

	public static final Long ORG_CREATION_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("ORG_CREATION_INTERFACE_ID"));
	
	public static final Long ORG_STATUS_CHANGE_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("ORG_STATUS_CHANGE_INTERFACE_ID"));
	
	public static final Long ORG_MODIFICATION_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("ORG_MODIFICATION_INTERFACE_ID"));
	
	public static final Long USER_CREATION_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("USER_CREATION_INTERFACE_ID"));
	
	public static final Long USER_STATUS_CHANGE_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("USER_STATUS_CHANGE_INTERFACE_ID"));
	
	public static final Long USER_MODIFICATION_INTERFACE_ID = Long.parseLong(PropertiesFileLoader.getValueFor("USER_MODIFICATION_INTERFACE_ID"));
	

}