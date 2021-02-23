package com.enhancesys.integration.services.dataengine;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.enhancesys.entities.integration.Interfaces;
import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:db_conf/persistence-postgresql.properties" })
public class HibernateConf 
{
	@Autowired
	private Environment LOCAL_ENV;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws NumberFormatException, SQLException 
	{
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {Interfaces.class.getPackage().getName().toString()});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() throws NumberFormatException, SQLException 
	{
		String driverName = Preconditions.checkNotNull(LOCAL_ENV.getProperty("jdbc.driverClassName"));
		String url = Preconditions.checkNotNull(Preconditions.checkNotNull(LOCAL_ENV.getProperty("jdbc.url")));
		String user = Preconditions.checkNotNull(Preconditions.checkNotNull(LOCAL_ENV.getProperty("jdbc.user")));
		String pass = Preconditions.checkNotNull(Preconditions.checkNotNull(LOCAL_ENV.getProperty("jdbc.pass")));
		int socketTimeout = Integer.parseInt(LOCAL_ENV.getProperty("socket.timeout"));
		Properties properties = new Properties();

		properties.put("socketTimeout", socketTimeout);
		
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		//		//		final BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pass);
		dataSource.setSchema("interface");
		dataSource.setConnectionProperties(properties);
		
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() throws NumberFormatException, SQLException 
	{
		final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private Properties hibernateProperties() 
	{
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", LOCAL_ENV.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", LOCAL_ENV.getProperty("hibernate.show_sql"));
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", LOCAL_ENV.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", LOCAL_ENV.getProperty("hibernate.cache.use_second_level_cache"));
		hibernateProperties.setProperty("hibernate.cache.use_query_cache", LOCAL_ENV.getProperty("hibernate.cache.use_query_cache"));
		hibernateProperties.setProperty("hibernate.cache.region.factory_class", LOCAL_ENV.getProperty("hibernate.cache.region.factory_class"));
		hibernateProperties.setProperty("hibernate.cache.provider_class", LOCAL_ENV.getProperty("hibernate.cache.provider_class"));
		hibernateProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", LOCAL_ENV.getProperty("hibernate.temp.use_jdbc_metadata_defaults"));

		return hibernateProperties;
	}
}
