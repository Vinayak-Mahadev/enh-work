package com.enhancesys.integration.snoc.services.conf;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <b>Purpose:</b>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations Private Limited<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       Mar 2, 2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */

@Configuration
@PropertySource({"classpath:database.properties"})
@EnableTransactionManagement
@EnableJpaRepositories({"com.enhancesys.integration.snoc.services", "com.enhancesys.integration.snoc.services.repositories"})
public class SnocJpaConf 
{
	@Autowired
	Environment env;

	private final String DRIVER = "jdbc.driverClassName";
	private final String USER = "jdbc.user";
	private final String PASS = "jdbc.pass";
	private final String URL = "jdbc.url";
	private final String DIALECT = "hibernate.dialect";
	private final String SHOW_SQL = "hibernate.show_sql";
	private final String HBM2DDL = "hibernate.hbm2ddl.auto";
	private final String USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
	private final String USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
	private final String EhCache = "hibernate.cache.region.factory_class";
	
	public SnocJpaConf() {
		super();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() throws Exception
	{
		final LocalContainerEntityManagerFactoryBean container = new LocalContainerEntityManagerFactoryBean();
		container.setDataSource(dataSource());
		container.setPackagesToScan(new String[]{"com.enhancesys.integration.snoc.entities"});
		final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		container.setJpaVendorAdapter(adapter);
		container.setJpaProperties(getHibernateProperties());
		return container;
	}
	
	@Bean
	public DataSource dataSource() throws NumberFormatException, SQLException 
	{
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty(DRIVER));
		dataSource.setUrl(env.getProperty(URL));
		dataSource.setUsername(env.getProperty(USER));
		dataSource.setPassword(env.getProperty(PASS));
		return dataSource;
	}
	
	final Properties getHibernateProperties()
	{
		final Properties properties = new Properties();
		properties.put(DIALECT, env.getProperty(DIALECT));
		properties.put(SHOW_SQL, env.getProperty(SHOW_SQL));
		properties.put(HBM2DDL, env.getProperty(HBM2DDL));
		properties.put(USE_SECOND_LEVEL_CACHE, env.getProperty(USE_SECOND_LEVEL_CACHE));
		properties.put(USE_QUERY_CACHE, env.getProperty(USE_QUERY_CACHE));
		properties.put(EhCache, env.getProperty(EhCache));
		
		return properties;
	}
	
}
