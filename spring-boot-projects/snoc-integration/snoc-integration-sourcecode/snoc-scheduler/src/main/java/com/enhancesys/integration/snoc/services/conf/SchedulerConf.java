package com.enhancesys.integration.snoc.services.conf;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;

import com.enhancesys.integration.snoc.services.IntegrationManagementServices;
import com.enhancesys.integration.snoc.services.SchedulerServices;
import com.enhancesys.integration.snoc.services.util.SchedulerUtil;

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
public class SchedulerConf 
{
	@Autowired

	private SchedulerUtil schedulerUtil;

	@Autowired
	private IntegrationManagementServices integrationManagementServices;
	

	@Bean("SchedulerServices")
	@Lazy
	@EventListener(ApplicationReadyEvent.class)
	public SchedulerServices getSchedulerImpl() throws SchedulerException, InterruptedException 
	{
		SchedulerServices schedulerServices = null;
		System.out.println("SchedulerServices :: Lazy :: EventListener" );
		schedulerServices =  new SchedulerServices(integrationManagementServices, schedulerUtil);
		schedulerServices.initialize();
		schedulerServices.doSomethingAfterStartup();
		return schedulerServices;
	}

	public static IntegrationManagementServices getIntegrationManagementServices() 
	{
		return null;	
	}
}