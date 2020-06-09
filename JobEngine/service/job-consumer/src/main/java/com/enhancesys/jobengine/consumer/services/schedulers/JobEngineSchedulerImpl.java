package com.enhancesys.jobengine.consumer.services.schedulers;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.enhancesys.jobengine.job.services.logging.TLogger;
import com.enhancesys.jobengine.job.services.util.PropertiesLoader;

public class JobEngineSchedulerImpl implements Serializable
{

	private static final long serialVersionUID = 1L;


	@PostConstruct
	public void initialize() throws SchedulerException
	{
		/** Get the Default scheduler from the standard scheduler factory */
		scheduler = StdSchedulerFactory.getDefaultScheduler();
		String groupName = null;
		String cronScheduler = null;
		/** define the job and tie it to our corresponding class */

		JobDetail processmodulesJob = null;
		Trigger group3Trigger = null;
		JobKey jobKey3 = null;

		//**********************************************************************************************************//
		for (Long moduleId : getLongLists(PropertiesLoader.getValueFor("SC_PROCESS_MODULE_JOB"))) 
		{
			groupName = "Generate_data_with_" + moduleId;
			processmodulesJob = newJob(ProcessModuleScheduler.class).withIdentity("transferFilesJob_" + moduleId, groupName).build();
			cronScheduler = PropertiesLoader.getValueFor("SCHEDULAR_FOR_PROCESS_MODULE_" + moduleId);
			if(cronScheduler == null)
			{
				TLogger.debug("Please configure Scheduler properties for SCHEDULAR_FOR_PROCESS_MODULE_"+ moduleId + "="+ PropertiesLoader.getValueFor("SCHEDULAR_FOR_PROCESS_MODULE_" + moduleId));
				continue;
			}
			group3Trigger = newTrigger().withIdentity(groupName + "_Trigger", groupName).startNow().withSchedule(CronScheduleBuilder.cronSchedule(cronScheduler)).build();

			jobKey3 = new JobKey("processModuleJob_" + moduleId, groupName);

			if(scheduler.getJobDetail(jobKey3) == null)
			{
				processmodulesJob.getJobDataMap().put("MODULE", moduleId);
				scheduler.scheduleJob(processmodulesJob, group3Trigger);
			}
		}
		//**********************************************************************************************************//

		/* Scheduler starts */
		scheduler.start();
	}

	@PreDestroy
	public void terminate()
	{
		System.out.println("Shutting down scheduler");
		if(scheduler != null)
		{
			try
			{
				scheduler.clear();
				scheduler.shutdown();
			}
			catch (SchedulerException e)
			{
				System.err.println(e.getMessage() + e);
			}
		}
	}
	private List<Long> getLongLists(String key)
	{
		List<Long> returnList = new ArrayList<Long>();
		if(key != null && !"".equals(key.trim()))
		{
			for(String str : key.split(","))
			{
				returnList.add(Long.valueOf(str.trim()));
			}
		}
		return returnList;
	}


	private Scheduler scheduler = null;


}
