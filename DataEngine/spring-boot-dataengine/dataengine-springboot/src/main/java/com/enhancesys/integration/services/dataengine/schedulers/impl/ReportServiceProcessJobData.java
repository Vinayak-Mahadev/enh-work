package com.enhancesys.integration.services.dataengine.schedulers.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.services.dataengine.controller.ReportServicesActivator;

public class ReportServiceProcessJobData implements Job
{
	private static Logger LOGGER = Logger.getLogger(ReportServiceProcessJobData.class);

	@Autowired
	protected ReportServicesActivator reportServicesActivator;

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException 
	{
		Long startTime = System.currentTimeMillis();
		LOGGER.debug("Entry ProcessFileJob at :: " + new Date());

		Long interfaceId = null;

		try 
		{
			interfaceId = (Long) jobExecutionContext.getJobDetail().getJobDataMap().get("INTERFACE");
			LOGGER.debug("ProcessJobData received interface id :: "+interfaceId);
			if(interfaceId != null && interfaceId.intValue() != 0)
			{
				LOGGER.info("INT: File Transfer Job is Initiated at "+new Date());
				reportServicesActivator.processJobData("");
				LOGGER.info("INT: File Transfer Job is Completed at "+new Date());
			}
		}
		catch(Exception exception)
		{
			LOGGER.error(exception.getMessage(), exception);
		}
		finally
		{
			LOGGER.info("Exit ProcessFileJob at :: " + new Date() + " --- and took " + (System.currentTimeMillis()-startTime) + " milliseconds");
		}
	}
}