package com.enhancesys.integration.services.dataengine;

import java.util.concurrent.ArrayBlockingQueue;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.job.JobEngine;
import com.enhancesys.integration.services.dataengine.job.JobPipeLine;
import com.enhancesys.integration.services.dataengine.job.connector.queue.ActiveMQueue;
import com.enhancesys.integration.services.dataengine.job.connector.queue.InMemoryQueue;
import com.enhancesys.integration.services.dataengine.job.reader.GenericDataFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.MongoDBDataFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.mongo.MongoDBPrimaryAggregateDataSortProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.mongo.MongoDBPrimaryDataFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.mongo.MongoDBPrimaryDataSortProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.mongo.MongoDBSecondaryDataFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.mongo.MongoDBSencodaryDataSortProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBPrimaryCartesianDataListProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBPrimaryCartesianDataProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBPrimaryDataFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBPrimaryDataListFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBPrimaryDataSortProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBSecondaryDataFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBSecondaryDataListFetcher;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBSecondaryDataSortProcessor;
import com.enhancesys.integration.services.dataengine.job.reader.pg.PostgresDBSecondaryDataStrFetcher;
import com.enhancesys.integration.services.dataengine.job.transformer.common.GenericDataTransformer;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoConnectionUtil;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoDataUtil;
import com.enhancesys.integration.services.dataengine.job.util.mongo.MongoTemplate;
import com.enhancesys.integration.services.dataengine.job.util.pg.JDBCDataSource;
import com.enhancesys.integration.services.dataengine.job.util.pg.JdbcConnectionUtil;
import com.enhancesys.integration.services.dataengine.job.util.pg.JdbcUtility;
import com.enhancesys.integration.services.dataengine.job.writer.SampleProcessor;
import com.enhancesys.integration.services.dataengine.job.writer.file.CSVDataListWriter;
import com.enhancesys.integration.services.dataengine.job.writer.file.CSVDataWriter;
import com.enhancesys.integration.services.dataengine.job.writer.file.ExcelDataWriter;
import com.enhancesys.integration.services.dataengine.job.writer.file.FileDataProcessor;
import com.enhancesys.integration.services.dataengine.job.writer.file.JSONDataWriter;
import com.enhancesys.integration.services.dataengine.job.writer.file.PDFDataWriter;
import com.enhancesys.integration.services.dataengine.job.writer.mongo.MongoDBDataWriter;
import com.enhancesys.integration.services.dataengine.job.writer.pg.PostgresDBDataWriter;
import com.enhancesys.integration.services.dataengine.job.writer.queue.ActiveMQDataPublisher;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;
import com.enhancesys.integration.services.dataengine.util.condition.ExpressionProcessor;
import com.enhancesys.integration.services.dataengine.util.mathematical.MathematicalProcessor;

@ComponentScan("com.enhancesys.integration.services.dataengine")
@Configuration
//@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class})
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAutoConfiguration
public class DataEngineBeans
{

//	@Bean(name = "tokuDAO")
//	public TokuDAO getTokuDAO() 
//	{
//		return new TokuDAO();	
//	}
//	
//	@Bean(name = "TokuStandAloneUser")
//	public TokuStandAloneUser getTokuStandAloneUser() 
//	{
//		return new TokuStandAloneUser();	
//	}
	
	@Bean(name = "jobConfiguration")
	@Scope(scopeName = "prototype")
	public JobConfiguration getJobConfiguration() 
	{
		return new JobConfiguration();	
	}

	@Bean(name = "jobEngine")
	@Scope(scopeName = "prototype")
	public JobEngine getJobEngine() 
	{
		return new JobEngine();	
	}

	@Bean(name = "jobPipeLine")
	@Scope(scopeName = "prototype")
	public JobPipeLine getJobPipeLine() 
	{
		return new JobPipeLine();	
	}

	@Bean(name = "failureQueue")
	@Scope(scopeName = "prototype")
	public ArrayBlockingQueue<Exception> getArrayBlockingQueue() 
	{
		return new ArrayBlockingQueue<Exception>(2);	
	}

	//<!-- Connectors start -->

	@Bean(name = "inMemoryQueue")
	@Scope(scopeName = "prototype")
	public InMemoryQueue getInMemoryQueue() 
	{
		return new InMemoryQueue();	
	}


	@Bean(name = "activeMQueue")
	@Scope(scopeName = "prototype")
	public ActiveMQueue getActiveMQueue() 
	{
		return new ActiveMQueue();	
	}

	// <!-- Connectors end -->

	// <!-- Transformer -->	
	@Bean(name = "genericDataTransformer")
	@Scope(scopeName = "prototype")
	public GenericDataTransformer getGenericDataTransformer() 
	{
		return new GenericDataTransformer();	
	}

	// <!-- Reader start -->

	@Bean(name = "genericDataFetcher")
	@Scope(scopeName = "prototype")
	public GenericDataFetcher getGenericDataFetcher() 
	{
		return new GenericDataFetcher();	
	}

	@Bean(name = "mongoDBDataFetcher")
	@Scope(scopeName = "prototype")
	public MongoDBDataFetcher getMongoDBDataFetcher() 
	{
		return new MongoDBDataFetcher();	
	}

	//	@Bean(name = "mongoDBDataSyncReader")
	//	@Scope(scopeName = "prototype")
	//	public MongoDBDataSyncReader getMongoDBDataSyncReader() 
	//	{
	//		return new MongoDBDataSyncReader();	
	//	}

	//<!-- mongo implementation for DBProcessor -->

	@Bean(name = "mongoDBPrimaryDataFetcher")
	@Scope(scopeName = "prototype")
	public MongoDBPrimaryDataFetcher getMongoDBPrimaryDataFetcher() 
	{
		return new MongoDBPrimaryDataFetcher();	
	}

	@Bean(name = "mongoDBSecondaryDataFetcher")
	@Scope(scopeName = "prototype")
	public MongoDBSecondaryDataFetcher getMongoDBSecondaryDataFetcher() 
	{
		return new MongoDBSecondaryDataFetcher();	
	}

	@Bean(name = "mongoDBPrimaryDataSortProcessor")
	@Scope(scopeName = "prototype")
	public MongoDBPrimaryDataSortProcessor getMongoDBPrimaryDataSortProcessor() 
	{
		return new MongoDBPrimaryDataSortProcessor();	
	}

	@Bean(name = "mongoDBSencodaryDataSortProcessor")
	@Scope(scopeName = "prototype")
	public MongoDBSencodaryDataSortProcessor getMongoDBSencodaryDataSortProcessor() 
	{
		return new MongoDBSencodaryDataSortProcessor();	
	}

	@Bean(name = "mongoDBPrimaryAggregateDataSortProcessor")
	@Scope(scopeName = "prototype")
	public MongoDBPrimaryAggregateDataSortProcessor getMongoDBPrimaryAggregateDataSortProcessor() 
	{
		return new MongoDBPrimaryAggregateDataSortProcessor();	
	}

	//<!-- pg implementation for DBProcessor -->


	@Bean(name = "postgresDBPrimaryDataFetcher")
	@Scope(scopeName = "prototype")
	public PostgresDBPrimaryDataFetcher getPostgresDBPrimaryDataFetcher() 
	{
		return new PostgresDBPrimaryDataFetcher();	
	}


	@Bean(name = "postgresDBSecondaryDataFetcher")
	@Scope(scopeName = "prototype")
	public PostgresDBSecondaryDataFetcher getPostgresDBSecondaryDataFetcher() 
	{
		return new PostgresDBSecondaryDataFetcher();	
	}


	@Bean(name = "postgresDBPrimaryDataSortProcessor")
	@Scope(scopeName = "prototype")
	public PostgresDBPrimaryDataSortProcessor getPostgresDBPrimaryDataSortProcessor() 
	{
		return new PostgresDBPrimaryDataSortProcessor();	
	}


	@Bean(name = "postgresDBSecondaryDataStrFetcher")
	@Scope(scopeName = "prototype")
	public PostgresDBSecondaryDataStrFetcher getPostgresDBSecondaryDataStrFetcher() 
	{
		return new PostgresDBSecondaryDataStrFetcher();	
	}


	@Bean(name = "postgresDBSecondaryDataSortProcessor")
	@Scope(scopeName = "prototype")
	public PostgresDBSecondaryDataSortProcessor getPostgresDBSecondaryDataSortProcessor() 
	{
		return new PostgresDBSecondaryDataSortProcessor();	
	}


	@Bean(name = "postgresDBPrimaryCartesianDataProcessor")
	@Scope(scopeName = "prototype")
	public PostgresDBPrimaryCartesianDataProcessor getPostgresDBPrimaryCartesianDataProcessor() 
	{
		return new PostgresDBPrimaryCartesianDataProcessor();	
	}


	@Bean(name = "postgresDBPrimaryDataListFetcher")
	@Scope(scopeName = "prototype")
	public PostgresDBPrimaryDataListFetcher getPostgresDBPrimaryDataListFetcher() 
	{
		return new PostgresDBPrimaryDataListFetcher();	
	}


	@Bean(name = "postgresDBSecondaryDataListFetcher")
	@Scope(scopeName = "prototype")
	public PostgresDBSecondaryDataListFetcher getPostgresDBSecondaryDataListFetcher() 
	{
		return new PostgresDBSecondaryDataListFetcher();	
	}


	@Bean(name = "postgresDBPrimaryCartesianDataListProcessor")
	@Scope(scopeName = "prototype")
	public PostgresDBPrimaryCartesianDataListProcessor getPostgresDBPrimaryCartesianDataListProcessor() 
	{
		return new PostgresDBPrimaryCartesianDataListProcessor();	
	}
	//<!-- Reader end -->

	//<!-- Writer start -->
	//<!-- file -->
	@Bean(name = "sampleProcessor")
	@Scope(scopeName = "prototype")
	public SampleProcessor getSampleProcessor() 
	{
		return new SampleProcessor();	
	}
	
	@Bean(name = "fileDataProcessor")
	@Scope(scopeName = "prototype")
	public FileDataProcessor getFileDataProcessor() 
	{
		return new FileDataProcessor();	
	}
	
	@Bean(name = "csvDataWriter")
	@Scope(scopeName = "prototype")
	public CSVDataWriter getCSVDataWriter() 
	{
		return new CSVDataWriter();	
	}
	
	@Bean(name = "csvDataListWriter")
	@Scope(scopeName = "prototype")
	public CSVDataListWriter getCSVDataListWriter() 
	{
		return new CSVDataListWriter();	
	}
	
	@Bean(name = "excelDataWriter")
	@Scope(scopeName = "prototype")
	public ExcelDataWriter getExcelDataWriter() 
	{
		return new ExcelDataWriter();	
	}

	@Bean(name = "pdfDataWriter")
	@Scope(scopeName = "prototype")
	public PDFDataWriter getPDFDataWriter() 
	{
		return new PDFDataWriter();	
	}

	@Bean(name = "jsonDataWriter")
	@Scope(scopeName = "prototype")
	public JSONDataWriter getJSONDataWriter() 
	{
		return new JSONDataWriter();	
	}
	//<!-- mongo -->

	@Bean(name = "mongoDBDataWriter")
	@Scope(scopeName = "prototype")
	public MongoDBDataWriter getMongoDBDataWriter() 
	{
		return new MongoDBDataWriter();	
	}
	//<!-- pg -->

	@Bean(name = "postgresDBDataWriter")
	@Scope(scopeName = "prototype")
	public PostgresDBDataWriter getPostgresDBDataWriter() 
	{
		return new PostgresDBDataWriter();	
	}
	//<!-- queue -->
	

	@Bean(name = "activeMQDataPublisher")
	@Scope(scopeName = "prototype")
	public ActiveMQDataPublisher getActiveMQDataPublisher() 
	{
		return new ActiveMQDataPublisher();	
	}
	//<!-- Writer end -->


	//<!-- Util start -->	
	@Bean(name = "utilities")
	public Utilities getUtilities() 
	{
		return new Utilities();	
	}

	@Bean(name = "mathematicalProcessor")
	@Scope(scopeName = "prototype")
	public MathematicalProcessor getMathematicalProcessor() 
	{
		return new MathematicalProcessor();	
	}

	@Bean(name = "expressionProcessor")
	@Scope(scopeName = "prototype")
	public ExpressionProcessor getExpressionProcessor() 
	{
		return new ExpressionProcessor();	
	}

	@Bean(name = "mongoDataUtil")
	public MongoDataUtil getMongoDataUtil() 
	{
		return new MongoDataUtil();	
	}

	@Bean(name = "mongoConnectionUtil")
	public MongoConnectionUtil getMongoConnectionUtil() 
	{
		return new MongoConnectionUtil();	
	}

	@Bean(name = "jdbcUtility")
	public JdbcUtility getJdbcUtility() 
	{
		return new JdbcUtility();	
	}


	@Bean(name = "jdbcConnectionUtil")
	public JdbcConnectionUtil getJdbcConnectionUtil() 
	{
		return new JdbcConnectionUtil();	
	}

	@Bean(name = "dataSource")
	public DataSource getJDBCDataSource() 
	{
		dataSource = new JDBCDataSource();
		
		return dataSource;	
	}

	DataSource dataSource;
	
//	@Bean(name = "jdbcTemplate")
//	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() 
//	{
//		if(dataSource != null)
//			getJDBCDataSource();
//		return new NamedParameterJdbcTemplate(dataSource);	
//	}

	@Bean(name = DataConstants.MONGO_R_123)
	public MongoTemplate getMongoTemplate_r1() 
	{
		return new MongoTemplate(
				DataConstants.MONGO_DB_USER_R_123,
				DataConstants.MONGO_DB_PASSWORD_R_123,
				DataConstants.MONGO_DB_DATABASE_R_123,
				DataConstants.MONGO_CONNECTIONS_MAX_R123,
				DataConstants.MONGO_CONNECTION_TIMEOUT_MAX_R123,
				DataConstants.MONGO_DB_REPLICAS_R123
				);	
	}

	@Bean(name = DataConstants.MONGO_R_4)
	public MongoTemplate getMongoTemplate_r4() 
	{
		return new MongoTemplate(
				DataConstants.MONGO_DB_R4_USER,
				DataConstants.MONGO_DB_R4_PASSWORD,
				DataConstants.MONGO_DB_R4_DATABASE,
				DataConstants.MONGO_CONNECTIONS_MAX_R4,
				DataConstants.MONGO_CONNECTION_TIMEOUT_MAX_R4,
				DataConstants.MONGO_DB_REPLICAS_R4
				);	
	}

	@Bean(name = DataConstants.MONGO_R_5)
	public MongoTemplate getMongoTemplate_r5() 
	{
		return new MongoTemplate(
				DataConstants.MONGO_DB_STANDALONE_USER,
				DataConstants.MONGO_DB_STANDALONE_PASSWORD,
				DataConstants.MONGO_DB_STANDALONE_DATABASE,
				DataConstants.MONGO_CONNECTIONS_MAX_R5,
				DataConstants.MONGO_CONNECTION_TIMEOUT_MAX_R5,
				DataConstants.MONGO_DB_REPLICAS_R5
				);	
	}


	
}
