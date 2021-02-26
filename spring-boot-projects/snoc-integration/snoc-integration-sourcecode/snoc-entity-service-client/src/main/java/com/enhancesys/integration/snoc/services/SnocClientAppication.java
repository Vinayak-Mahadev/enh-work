package com.enhancesys.integration.snoc.services;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@ComponentScan("com.enhancesys.integration.snoc.services")

public class SnocClientAppication 
{
	public static ApplicationContext context;

	@Autowired
	IntegrationManagementServices integrationManagementServices;

	public static void main(String[] args) 
	{
//		context = SpringApplication.run(SnocClientAppication.class, args);
	}

	public static ApplicationContext context() {
		return context;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws InterruptedException 
	{
	    System.out.println("hello world, I have just started up");
	    Thread.sleep(1000l);
	    System.out.println("Wait state   at SnocClientAppication :: IntegrationManagementServices :: " + integrationManagementServices);
	    System.out.println(integrationManagementServices.getModule(101l).getName());
	}
	
}
