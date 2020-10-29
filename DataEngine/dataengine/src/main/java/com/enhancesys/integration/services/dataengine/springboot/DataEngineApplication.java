package com.enhancesys.integration.services.dataengine.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.enhancesys.integration.services.dataengine")
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
