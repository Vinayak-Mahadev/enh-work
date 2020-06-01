//package com.enhancesys.jobengine.services.webconf;
//
//import java.util.concurrent.ArrayBlockingQueue;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.orm.jpa.JpaTransactionManager;
//
//import com.enhancesys.jobengine.consumer.config.CustomEntitymanagerFactory;
//import com.enhancesys.jobengine.consumer.util.Utilities;
//import com.enhancesys.jobengine.services.job.JobConfigurationReader;
//import com.enhancesys.jobengine.services.job.JobEngine;
//import com.enhancesys.jobengine.services.job.JobPipeLine;
//import com.enhancesys.jobengine.services.mongo.processor.MongoDBPrimaryAggregateDataSortProcessor;
//import com.enhancesys.jobengine.services.mongo.processor.MongoDBPrimaryDataFetcher;
//import com.enhancesys.jobengine.services.mongo.processor.MongoDBPrimaryDataSortProcessor;
//import com.enhancesys.jobengine.services.mongo.processor.MongoDBSecondaryDataFetcher;
//import com.enhancesys.jobengine.services.mongo.processor.MongoDBSencodaryDataSortProcessor;
//import com.enhancesys.jobengine.services.mongo.util.MongoConnectionUtil;
//import com.enhancesys.jobengine.services.mongo.util.MongoDataUtil;
//import com.enhancesys.jobengine.services.mongo.util.MongoTemplate;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBPrimaryCartesianDataListProcessor;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBPrimaryCartesianDataProcessor;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBPrimaryDataFetcher;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBPrimaryDataListFetcher;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBPrimaryDataSortProcessor;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBSecondaryDataFetcher;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBSecondaryDataListFetcher;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBSecondaryDataSortProcessor;
//import com.enhancesys.jobengine.services.pg.processor.PostgresDBSecondaryDataStrFetcher;
//import com.enhancesys.jobengine.services.pg.util.JDBCDataSource;
//import com.enhancesys.jobengine.services.pg.util.JdbcUtility;
//import com.enhancesys.jobengine.services.processor.ActiveMQDataPublisher;
//import com.enhancesys.jobengine.services.processor.CSVDataListWriter;
//import com.enhancesys.jobengine.services.processor.CSVDataWriter;
//import com.enhancesys.jobengine.services.processor.DataTransformer;
//import com.enhancesys.jobengine.services.processor.ExcelDataWriter;
//import com.enhancesys.jobengine.services.processor.FileDataProcessor;
//import com.enhancesys.jobengine.services.processor.JSONDataWriter;
//import com.enhancesys.jobengine.services.processor.MongoDBDataFetcher;
//import com.enhancesys.jobengine.services.processor.MongoDataWriter;
//import com.enhancesys.jobengine.services.processor.PDFDataWriter;
//import com.enhancesys.jobengine.services.processor.PostgresDataWriter;
//import com.enhancesys.jobengine.services.processor.SampleProcessor;
//import com.enhancesys.jobengine.services.queue.ActiveMQueue;
//import com.enhancesys.jobengine.services.queue.InMemoryQueue;
//import com.enhancesys.jobengine.services.util.PropertiesLoader;
//
//@Configuration
//public class JobEngineBeans
//{
//
//	@Bean(name = "utilities")
//	@Scope("prototype")
//	protected Utilities go()
//	{
//		Utilities utilities = null;
//		try 
//		{
//			utilities = new Utilities();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return utilities;
//	}
//
//	@Bean(name = "customEntitymanagerFactory")
//	protected CustomEntitymanagerFactory getustomEntitymanagerFactory()
//	{
//		CustomEntitymanagerFactory customEntitymanagerFactory = null;
//		try 
//		{
//			customEntitymanagerFactory = new CustomEntitymanagerFactory();
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		return customEntitymanagerFactory;
//	}
//
//	@Bean(name = "jpaTransactionManager")
//	protected JpaTransactionManager getJpaTransactionManager() 
//	{
//		JpaTransactionManager jpaTransactionManager = null;
//		try 
//		{
//			jpaTransactionManager = new JpaTransactionManager();
//		} 
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return jpaTransactionManager;
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Bean(name = "failureQueue")
//	protected ArrayBlockingQueue getFailureQueue() 
//	{
//		ArrayBlockingQueue arrayBlockingQueue = null;
//		try 
//		{
//			arrayBlockingQueue = new ArrayBlockingQueue(2);	
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return arrayBlockingQueue;
//	}
//
//	@Bean(name = "propertiesLoader" )
//	protected PropertiesLoader getPropertiesLoader() 
//	{
//		PropertiesLoader propertiesLoader = null;
//
//		try
//		{
//			propertiesLoader = new PropertiesLoader();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return propertiesLoader;
//	}
//
//	@Bean(name = "inMemoryQueue")
//	@Scope("prototype")
//	protected InMemoryQueue getInMemoryQueue()
//	{
//		InMemoryQueue inMemoryQueue = null;
//		try 
//		{
//			inMemoryQueue = new InMemoryQueue(null);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return inMemoryQueue;
//	}
//
//	@Bean(name = "activeMQueue")
//	@Scope("prototype")
//	protected ActiveMQueue getActiveMQueue()
//	{
//		ActiveMQueue activeMQueue = null;
//		try 
//		{
//			activeMQueue = new ActiveMQueue(null);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return activeMQueue;
//	}
//
//
//	@Bean(name = "jobConfigurationReader")
//	protected JobConfigurationReader getJobConfigurationReader()
//	{
//		JobConfigurationReader jobConfigurationReader = null;
//		try 
//		{
//			jobConfigurationReader = new JobConfigurationReader();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jobConfigurationReader;
//	}
//
//	@Bean(name = "jobEngine")
//	protected JobEngine getJobEngine()
//	{
//		JobEngine jobEngine = null;
//		try 
//		{
//			jobEngine = new JobEngine();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jobEngine;
//	}
//
//
//	@Bean(name = "jobPipeLine")
//	@Scope("prototype")
//	protected JobPipeLine getJobPipeLine()
//	{
//		JobPipeLine jobPipeLine = null;
//		try 
//		{
//			jobPipeLine = new JobPipeLine();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jobPipeLine;
//	}
//
//
//	@Bean(name = "sampleProcessor")
//	@Scope("prototype")
//	protected SampleProcessor getSampleProcessor()
//	{
//		SampleProcessor sampleProcessor = null;
//		try 
//		{
//			sampleProcessor = new SampleProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return sampleProcessor;
//	}
//
//
//	@Bean(name = "dataTransformer")
//	@Scope("prototype")
//	protected DataTransformer getDataTransformer()
//	{
//		DataTransformer dataTransformer = null;
//		try 
//		{
//			dataTransformer = new DataTransformer();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return dataTransformer;
//	}
//
//
//	@Bean(name = "fileDataProcessor")
//	@Scope("prototype")
//	protected FileDataProcessor getFileDataProcessor()
//	{
//		FileDataProcessor fileDataProcessor = null;
//		try 
//		{
//			fileDataProcessor = new FileDataProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return fileDataProcessor;
//	}
//
//
//	@Bean(name = "mongoDataWriter")
//	@Scope("prototype")
//	protected MongoDataWriter getMongoDataWriter()
//	{
//		MongoDataWriter mongoDataWriter = null;
//		try 
//		{
//			mongoDataWriter = new MongoDataWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDataWriter;
//	}
//
//	@Bean(name = "activeMQDataPublisher")
//	@Scope("prototype")
//	protected ActiveMQDataPublisher getActiveMQDataPublisher()
//	{
//		ActiveMQDataPublisher activeMQDataPublisher = null;
//		try 
//		{
//			activeMQDataPublisher = new ActiveMQDataPublisher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return activeMQDataPublisher;
//	}
//
//	@Bean(name = "postgresDataWriter")
//	@Scope("prototype")
//	protected PostgresDataWriter getPostgresDataWriter()
//	{
//		PostgresDataWriter postgresDataWriter = null;
//		try 
//		{
//			postgresDataWriter = new PostgresDataWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDataWriter;
//	}
//
//	@Bean(name = "csvDataWriter")
//	@Scope("prototype")
//	protected CSVDataWriter getCSVDataWriter()
//	{
//		CSVDataWriter csvDataWriter = null;
//		try 
//		{
//			csvDataWriter = new CSVDataWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return csvDataWriter;
//	}
//
//	@Bean(name = "csvDataListWriter")
//	@Scope("prototype")
//	protected CSVDataListWriter getCSVDataListWriter()
//	{
//		CSVDataListWriter csvDataListWriter = null;
//		try 
//		{
//			csvDataListWriter = new CSVDataListWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return csvDataListWriter;
//	}
//
//	@Bean(name = "excelDataWriter")
//	@Scope("prototype")
//	protected ExcelDataWriter getExcelDataWriter()
//	{
//		ExcelDataWriter excelDataWriter = null;
//		try 
//		{
//			excelDataWriter = new ExcelDataWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return excelDataWriter;
//	}
//
//	@Bean(name = "pdfDataWriter")
//	@Scope("prototype")
//	protected PDFDataWriter getPDFDataWriter()
//	{
//		PDFDataWriter pdfDataWriter = null;
//		try 
//		{
//			pdfDataWriter = new PDFDataWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return pdfDataWriter;
//	}
//
//
//	@Bean(name = "mongoDBDataFetcher")
//	@Scope("prototype")
//	protected MongoDBDataFetcher getMongoDBDataFetcher()
//	{
//		MongoDBDataFetcher mongoDBDataFetcher = null;
//		try 
//		{
//			mongoDBDataFetcher = new MongoDBDataFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDBDataFetcher;
//	}
//
//
//	@Bean(name = "mongoDBPrimaryDataFetcher")
//	@Scope("prototype")
//	protected MongoDBPrimaryDataFetcher getMongoDBPrimaryDataFetcher()
//	{
//		MongoDBPrimaryDataFetcher mongoDBPrimaryDataFetcher = null;
//		try 
//		{
//			mongoDBPrimaryDataFetcher = new MongoDBPrimaryDataFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDBPrimaryDataFetcher;
//	}
//
//
//	@Bean(name = "mongoDBSecondaryDataFetcher")
//	@Scope("prototype")
//	protected MongoDBSecondaryDataFetcher getMongoDBSecondaryDataFetcher()
//	{
//		MongoDBSecondaryDataFetcher mongoDBSecondaryDataFetcher = null;
//		try 
//		{
//			mongoDBSecondaryDataFetcher = new MongoDBSecondaryDataFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDBSecondaryDataFetcher;
//	}
//
//
//
//	@Bean(name = "mongoDBPrimaryDataSortProcessor")
//	@Scope("prototype")
//	protected MongoDBPrimaryDataSortProcessor getMongoDBPrimaryDataSortProcessor()
//	{
//		MongoDBPrimaryDataSortProcessor mongoDBPrimaryDataSortProcessor = null;
//		try 
//		{
//			mongoDBPrimaryDataSortProcessor = new MongoDBPrimaryDataSortProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDBPrimaryDataSortProcessor;
//	}
//
//
//	@Bean(name = "mongoDBSencodaryDataSortProcessor")
//	@Scope("prototype")
//	protected MongoDBSencodaryDataSortProcessor getMongoDBSencodaryDataSortProcessor()
//	{
//		MongoDBSencodaryDataSortProcessor mongoDBSencodaryDataSortProcessor = null;
//		try 
//		{
//			mongoDBSencodaryDataSortProcessor = new MongoDBSencodaryDataSortProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDBSencodaryDataSortProcessor;
//	}
//
//
//	@Bean(name = "mongoDBPrimaryAggregateDataSortProcessor")
//	@Scope("prototype")
//	protected MongoDBPrimaryAggregateDataSortProcessor getMongoDBPrimaryAggregateDataSortProcessor()
//	{
//		MongoDBPrimaryAggregateDataSortProcessor mongoDBPrimaryAggregateDataSortProcessor = null;
//		try 
//		{
//			mongoDBPrimaryAggregateDataSortProcessor = new MongoDBPrimaryAggregateDataSortProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDBPrimaryAggregateDataSortProcessor;
//	}
//
//
//	@Bean(name = "mongoDataUtil")
//	protected MongoDataUtil getMongoDataUtil()
//	{
//		MongoDataUtil mongoDataUtil = null;
//		try 
//		{
//			mongoDataUtil = new MongoDataUtil();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoDataUtil;
//	}
//
//
//	@Bean(name = "mongoConnectionUtil")
//	protected MongoConnectionUtil getMongoConnectionUtil()
//	{
//		MongoConnectionUtil mongoConnectionUtil = null;
//		try 
//		{
//			mongoConnectionUtil = new MongoConnectionUtil();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return mongoConnectionUtil;
//	}
//
//
//	@Bean(name = "jdbcUtility")
//	protected JdbcUtility getJdbcUtility()
//	{
//		JdbcUtility jdbcUtility = null;
//		try 
//		{
//			jdbcUtility = new JdbcUtility();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jdbcUtility;
//	}
//
//
//	@Bean(name = "postgresDBPrimaryDataFetcher")
//	@Scope("prototype")
//	protected PostgresDBPrimaryDataFetcher getPostgresDBPrimaryDataFetcher()
//	{
//		PostgresDBPrimaryDataFetcher postgresDBPrimaryDataFetcher = null;
//		try 
//		{
//			postgresDBPrimaryDataFetcher = new PostgresDBPrimaryDataFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBPrimaryDataFetcher;
//	}
//
//
//
//	@Bean(name = "postgresDBSecondaryDataFetcher")
//	@Scope("prototype")
//	protected PostgresDBSecondaryDataFetcher getPostgresDBSecondaryDataFetcher()
//	{
//		PostgresDBSecondaryDataFetcher postgresDBSecondaryDataFetcher = null;
//		try 
//		{
//			postgresDBSecondaryDataFetcher = new PostgresDBSecondaryDataFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBSecondaryDataFetcher;
//	}
//
//
//
//	@Bean(name = "postgresDBPrimaryDataSortProcessor")
//	@Scope("prototype")
//	protected PostgresDBPrimaryDataSortProcessor getPostgresDBPrimaryDataSortProcessor()
//	{
//		PostgresDBPrimaryDataSortProcessor postgresDBPrimaryDataSortProcessor = null;
//		try 
//		{
//			postgresDBPrimaryDataSortProcessor = new PostgresDBPrimaryDataSortProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBPrimaryDataSortProcessor;
//	}
//
//
//
//	@Bean(name = "postgresDBSecondaryDataStrFetcher")
//	@Scope("prototype")
//	protected PostgresDBSecondaryDataStrFetcher getPostgresDBSecondaryDataStrFetcher()
//	{
//		PostgresDBSecondaryDataStrFetcher postgresDBSecondaryDataStrFetcher = null;
//		try 
//		{
//			postgresDBSecondaryDataStrFetcher = new PostgresDBSecondaryDataStrFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBSecondaryDataStrFetcher;
//	}
//
//
//
//	@Bean(name = "postgresDBSecondaryDataSortProcessor")
//	@Scope("prototype")
//	protected PostgresDBSecondaryDataSortProcessor getPostgresDBSecondaryDataSortProcessor()
//	{
//		PostgresDBSecondaryDataSortProcessor postgresDBSecondaryDataSortProcessor = null;
//		try 
//		{
//			postgresDBSecondaryDataSortProcessor = new PostgresDBSecondaryDataSortProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBSecondaryDataSortProcessor;
//	}
//
//
//
//	@Bean(name = "postgresDBPrimaryCartesianDataProcessor")
//	@Scope("prototype")
//	protected PostgresDBPrimaryCartesianDataProcessor getPostgresDBPrimaryCartesianDataProcessor()
//	{
//		PostgresDBPrimaryCartesianDataProcessor postgresDBPrimaryCartesianDataProcessor = null;
//		try 
//		{
//			postgresDBPrimaryCartesianDataProcessor = new PostgresDBPrimaryCartesianDataProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBPrimaryCartesianDataProcessor;
//	}
//
//
//
//	@Bean(name = "postgresDBPrimaryDataListFetcher")
//	@Scope("prototype")
//	protected PostgresDBPrimaryDataListFetcher getPostgresDBPrimaryDataListFetcher()
//	{
//		PostgresDBPrimaryDataListFetcher postgresDBPrimaryDataListFetcher = null;
//		try 
//		{
//			postgresDBPrimaryDataListFetcher = new PostgresDBPrimaryDataListFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBPrimaryDataListFetcher;
//	}
//
//
//
//	@Bean(name = "postgresDBSecondaryDataListFetcher")
//	@Scope("prototype")
//	protected PostgresDBSecondaryDataListFetcher getPostgresDBSecondaryDataListFetcher()
//	{
//		PostgresDBSecondaryDataListFetcher postgresDBSecondaryDataListFetcher = null;
//		try 
//		{
//			postgresDBSecondaryDataListFetcher = new PostgresDBSecondaryDataListFetcher();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBSecondaryDataListFetcher;
//	}
//
//
//
//	@Bean(name = "postgresDBPrimaryCartesianDataListProcessor")
//	@Scope("prototype")
//	protected PostgresDBPrimaryCartesianDataListProcessor getPostgresDBPrimaryCartesianDataListProcessor()
//	{
//		PostgresDBPrimaryCartesianDataListProcessor postgresDBPrimaryCartesianDataListProcessor = null;
//		try 
//		{
//			postgresDBPrimaryCartesianDataListProcessor = new PostgresDBPrimaryCartesianDataListProcessor();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return postgresDBPrimaryCartesianDataListProcessor;
//	}
//
//
//
//	@Bean(name = "jsonDataWriter")
//	@Scope("prototype")
//	protected JSONDataWriter getJSONDataWriter()
//	{
//		JSONDataWriter jsonDataWriter = null;
//		try 
//		{
//			jsonDataWriter = new JSONDataWriter();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jsonDataWriter;
//	}
//
//
//
//	@Bean(name = "util")
//	protected com.enhancesys.jobengine.services.util.Util getUtil()
//	{
//		com.enhancesys.jobengine.services.util.Util util = null;
//		try 
//		{
//			util = new com.enhancesys.jobengine.services.util.Util();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return util;
//	}
//
//
//
//	@Bean(name = "jdbcConnectionUtil")
//	protected com.enhancesys.jobengine.services.pg.util.JdbcConnectionUtil getJdbcConnectionUtil()
//	{
//		com.enhancesys.jobengine.services.pg.util.JdbcConnectionUtil jdbcConnectionUtil = null;
//		try 
//		{
//			jdbcConnectionUtil = new com.enhancesys.jobengine.services.pg.util.JdbcConnectionUtil();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jdbcConnectionUtil;
//	}
//
//
//
//	@Bean(name = "snoc-r1")
//	protected MongoTemplate getMongoTemplateSnocR1()
//	{
//		MongoTemplate snoc = null;
//		try 
//		{
//			snoc = new MongoTemplate(null, null, null, null, null, null);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return snoc;
//	}
//
//	@Bean(name = "snoc-r4")
//	protected MongoTemplate getMongoTemplateSnocR4()
//	{
//		MongoTemplate snoc = null;
//		try 
//		{
//			snoc = new MongoTemplate(null, null, null, null, null, null);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return snoc;
//	}
//
//	@Bean(name = "snoc-r5")
//	protected MongoTemplate getMongoTemplateSnocR5()
//	{
//		MongoTemplate snoc = null;
//		try 
//		{
//			snoc = new MongoTemplate(null, null, null, null, null, null);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return snoc;
//	}
//
//	@Bean(name = "dataSource")
//	protected JDBCDataSource getJDBCDataSource()
//	{
//		JDBCDataSource dataSource = null;
//		try 
//		{
//			dataSource = new JDBCDataSource(null, null, null, null, null);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return dataSource;
//	}
//
//
//
//	@Bean(name = "jdbcTemplate")
//	@Scope("prototype")
//	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate()
//	{
//		NamedParameterJdbcTemplate jdbcTemplate = null;
//		try 
//		{
//			jdbcTemplate = new NamedParameterJdbcTemplate(getJDBCDataSource());
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		return jdbcTemplate;
//	}
//
//
//}
