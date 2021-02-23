package com.enhancesys.integration.services.dataengine.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.enhancesys.integration.services.dataengine")
@EntityScan("com.enhancesys.entities.integration")
public class DataEngineApplication 
{

	public static ApplicationContext context;
	
	public static void main(String[] args) 
	{
		context = SpringApplication.run(DataEngineApplication.class, args);
	}
	
	public static ApplicationContext context() {
		return context;
	}
}
