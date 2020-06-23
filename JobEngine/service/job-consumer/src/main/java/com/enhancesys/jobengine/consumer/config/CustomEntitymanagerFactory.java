//package com.enhancesys.jobengine.consumer.config;
//
//import java.util.Map;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.hibernate.ejb.HibernatePersistence;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import com.enhancesys.jobcommon.Constants;
//import com.enhancesys.jobcommon.PropertyLoader;
//
//public class CustomEntitymanagerFactory extends LocalContainerEntityManagerFactoryBean
//{
//
//	private static final Map<String, String> PROPERTY = PropertyLoader.loadProperties(Constants._JOB_CONSUMER_SERVICE_DATASOURCE_CONFIG_LOC);	
//
//	public  CustomEntitymanagerFactory() 
//	{
//		super();
//		try
//		{
//			setDataSource(dataSource());
//			setPersistenceProviderClass(HibernatePersistence.class);
//			setPackagesToScan(Constants._SET_PACKAGES_TO_SCAN_FOR_JOB_CONSUMER_1, Constants._SET_PACKAGES_TO_SCAN_FOR_JOB_CONSUMER_2);
//			setJpaProperties(hibernateProps());
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		System.out.println("CustomEntitymanagerFactory PROPERTY :: " + PROPERTY);
//		System.out.println("CustomEntitymanagerFactory created..");
//	}
//
//
//
//	private DataSource dataSource() {
//		DriverManagerDataSource ds = null;
//		try 
//		{
//			ds = new DriverManagerDataSource();
//			ds.setDriverClassName(PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._DRIVER));
//			ds.setUrl(PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._URL));
//			ds.setUsername(PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._USERNAME));
//			ds.setPassword(PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._PASSWORD));
//
//		} 
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return ds;
//	}
//
//	private Properties hibernateProps() {
//		Properties properties = null;
//		try 
//		{
//			properties = new Properties();
//			properties.setProperty(Constants._DIALECT, PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._DIALECT));
//			properties.setProperty(Constants._SHOW_SQL, PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._SHOW_SQL));
//			properties.setProperty(Constants._METADATA_DEFAULTS, PROPERTY.get(Constants._PREFIX_FOR_JOB_CONSUMER+Constants._METADATA_DEFAULTS));
//
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return properties;
//	}
//
//}
