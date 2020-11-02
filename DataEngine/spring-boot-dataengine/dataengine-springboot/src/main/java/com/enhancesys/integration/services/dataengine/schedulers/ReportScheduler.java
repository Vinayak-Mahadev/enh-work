package com.enhancesys.integration.services.dataengine.schedulers;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import com.enhancesys.integration.services.dataengine.schedulers.impl.ReportServiceProcessJobData;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.Utilities;

@Component
public class ReportScheduler implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4579824696898892519L;
	private Scheduler scheduler;
	private static Logger LOGGER = Logger.getLogger(ReportScheduler.class);

	@PostConstruct
	public void initialize() throws SchedulerException
	{
		/** Get the Default scheduler from the standard scheduler factory */
		LOGGER.info("Entry Job initialize");

		
		scheduler = StdSchedulerFactory.getDefaultScheduler();
		String groupName = null;
		String cronScheduler = null;

		/** define the job and tie it to our corresponding class */

		JobDetail processFilesJob = null;
		Trigger group3Trigger = null;
		JobKey jobKey3 = null;


		try 
		{
			/* Process File job to transfer files from sftp to inerface and interface to sftp*/
			LOGGER.info("Job transfer files from sftp to interface and interface to sftp");

			for (Long moduleId : Utilities.getLongLists("")) 
			{
				groupName = "TransferFiles_" + moduleId;
				processFilesJob = newJob(ReportServiceProcessJobData.class).withIdentity("transferFilesJob_" + moduleId, groupName).build();
				cronScheduler = DataConstants.Env.getValueFor("SCHEDULAR_FOR_PROCESS_FILE_" + moduleId);
				if(cronScheduler == null)
				{
					LOGGER.debug("Please configure Scheduler properties for SCHEDULAR_FOR_PROCESS_FILE_" + moduleId + "=" + DataConstants.Env.getValueFor("SCHEDULAR_FOR_PROCESS_FILE"));
					continue;
				}
				group3Trigger = newTrigger().withIdentity(groupName + "_Trigger", groupName).startNow().withSchedule(CronScheduleBuilder.cronSchedule(cronScheduler)).build();

				jobKey3 = new JobKey("transferFilesJob_" + moduleId, groupName);

				if(scheduler.getJobDetail(jobKey3) == null)
				{
					processFilesJob.getJobDataMap().put("INTERFACE", moduleId);
					scheduler.scheduleJob(processFilesJob, group3Trigger);
				}
			}

		} 
		catch (Exception e) 
		{
			LOGGER.error(e.getMessage(),e);
		}
		finally 
		{
			processFilesJob = null;
			group3Trigger = null;
			jobKey3 = null;	
			LOGGER.info("Exit Job initialize");
		}
	}


	@PreDestroy
	public void terminate()
	{
		LOGGER.debug("Shutting down scheduler");
		if(scheduler != null)
		{
			try
			{
				scheduler.clear();
				scheduler.shutdown();
			}
			catch (SchedulerException e)
			{
				LOGGER.error(e.getMessage(),e);
			}
		}
	}

}
