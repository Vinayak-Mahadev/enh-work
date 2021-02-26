package com.enhancesys.integration.snoc.services;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.enhancesys.integration.snoc.services.schedulers.INT_ProcessFileJob;
import com.enhancesys.integration.snoc.services.util.SchedulerUtil;

import cxf.com.enhancesys.entities.schema.integration.Module;



/**
 * <b>Purpose:</b><br>
 *		Implementation of SchedularImpl to process the integration management orders..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 *		Enhancesys Innovations 2020<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       08-11-2020          Vinayak Mahadev
 * -- Base Release
 */

public class SchedulerServices implements Serializable
{
	private static final long serialVersionUID = 1L;

	private List<String> schedulerProcessFileId;

	private final SchedulerUtil schedulerUtil;
	private final IntegrationManagementServices integrationManagementServices;

	private Module schedulerModule;

	public SchedulerServices(IntegrationManagementServices integrationManagementServices, SchedulerUtil schedulerUtil) 
	{
		this.integrationManagementServices = integrationManagementServices;
		this.schedulerUtil = schedulerUtil;
		System.out.println("SchedulerServices activated :: integrationManagementServices :: schedulerUtil " + integrationManagementServices + " :: schedulerUtil");
	}

//	@PostConstruct
//	@EventListener(ApplicationReadyEvent.class)
	public void initialize() throws SchedulerException
	{
		try 
		{
			if(integrationManagementServices != null)
				schedulerModule = integrationManagementServices.getModule(101l);
			if(schedulerModule == null)
			{
				System.out.println("SchedulerModule is null, Scheduler will not start...");		
				return;
			}
			System.out.println("schedulerModule :: " + schedulerModule.getModuleId() + " :: " + schedulerModule.getName());
			schedulerProcessFileId = schedulerUtil.getList("SC_PROCESS_FILE_JOB", ",");
			/** Get the Default scheduler from the standard scheduler factory */

			scheduler = StdSchedulerFactory.getDefaultScheduler();
			String groupName = null;
			String cronScheduler = null;
			/** define the job and tie it to our corresponding class */

			JobDetail processFilesJob = null;
			Trigger group3Trigger = null;
			JobKey jobKey3 = null;


			/* Process File job to transfer files from sftp to inerface and interface to sftp*/
			System.out.println("Job transfer files from sftp to interface and interface to sftp");
			for (String interfaceIdStr : schedulerProcessFileId) 
			{
				System.out.println("ProcessFile scheduler configuration stared for :: " + interfaceIdStr);
				try
				{

					Long interfaceId = Long.parseLong(interfaceIdStr);
					groupName = "TransferFiles_" + interfaceId;
					processFilesJob = newJob(INT_ProcessFileJob.class).withIdentity("transferFilesJob_" + interfaceId, groupName).build();
					cronScheduler = schedulerUtil.getProperty("SCHEDULAR_FOR_PROCESS_FILE_"+interfaceId);
					if(cronScheduler == null)
					{
						System.out.println("Please configure Scheduler properties for SCHEDULAR_FOR_PROCESS_FILE_"+ interfaceId +"="+schedulerUtil.getProperty("SCHEDULAR_FOR_PROCESS_FILE_"+interfaceId));
						continue;
					}
					group3Trigger = newTrigger().withIdentity(groupName + "_Trigger", groupName).startNow().withSchedule(CronScheduleBuilder.cronSchedule(cronScheduler)).build();

					jobKey3 = new JobKey("transferFilesJob_" + interfaceId, groupName);

					if(scheduler.getJobDetail(jobKey3) == null)
					{
						processFilesJob.getJobDataMap().put("INTERFACE", interfaceId);
						scheduler.scheduleJob(processFilesJob, group3Trigger);
					}

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				System.out.println("ProcessFile scheduler configuration ended for :: " + interfaceIdStr);
			}

			/** Scheduler starts */
			System.out.println("Wait state at SchedulerServices" );

			scheduler.start();

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
				e.printStackTrace();
			}
		}
	}

	private static Scheduler scheduler = null;
}