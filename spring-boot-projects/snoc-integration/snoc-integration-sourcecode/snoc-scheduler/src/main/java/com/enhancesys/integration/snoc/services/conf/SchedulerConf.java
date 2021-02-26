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


@Configuration
public class SchedulerConf 
{
	@Autowired
	private SchedulerUtil schedulerUtil;

	@Autowired
	IntegrationManagementServices integrationManagementServices;

	@Bean("SchedulerServices")
	@Lazy
	@EventListener(ApplicationReadyEvent.class)
	public SchedulerServices getSchedulerImpl() throws SchedulerException 
	{
		SchedulerServices schedulerServices = null;
		System.out.println("SchedulerServices :: Lazy :: EventListener" );
		schedulerServices =  new SchedulerServices(integrationManagementServices, schedulerUtil);
		schedulerServices.initialize();
		return schedulerServices;
	}
}