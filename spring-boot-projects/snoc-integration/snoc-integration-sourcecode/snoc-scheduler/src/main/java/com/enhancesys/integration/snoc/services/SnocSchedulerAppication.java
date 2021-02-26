package com.enhancesys.integration.snoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.enhancesys.integration.snoc.services")

public class SnocSchedulerAppication 
{
	public static ApplicationContext context;

	@Autowired
	IntegrationManagementServices integrationManagementServices;

	public static void main(String[] args) 
	{
		context = SpringApplication.run(SnocSchedulerAppication.class, args);
	}

	public static ApplicationContext context() 
	{
		return context;
	}

}
