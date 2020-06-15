package com.enhancesys.interfaces.snoc.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.enhancesys.snoc.utils.EncryptionUtil;

public class JDBCDataSource extends DriverManagerDataSource
{
	public JDBCDataSource() {
	} 
	public JDBCDataSource(String driverClassName, String url, String username, String password)
	{
		try
		{
			setDriverClassName(driverClassName);
			setUrl(url);
			setUsername(Boolean.parseBoolean(PropertiesFileLoaderClass
					.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil
					.decrypt(username) : username);
			setPassword(Boolean.parseBoolean(PropertiesFileLoaderClass
					.getValueAsString("ENCRYPT_DECRYPT_FLAG")) ? EncryptionUtil
					.decrypt(password) : password);
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}