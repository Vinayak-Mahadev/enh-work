package com.enhancesys.integration.snoc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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
