package com.enhancesys.integration.snoc.services;

import static org.quartz.JobBuilder.newJob;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.enhancesys.integration.snoc.services.schedulers.INT_ProcessFileJob;
import com.enhancesys.integration.snoc.services.util.SchedulerUtil;



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

	IntegrationManagementServices integrationManagementServices;
	
	public SchedulerServices(IntegrationManagementServices integrationManagementServices, SchedulerUtil schedulerUtil) 
	{
		this.schedulerUtil = schedulerUtil;
		this.integrationManagementServices = integrationManagementServices;
		System.out.println("SchedulerServices activated  :: schedulerUtil" + " :: " + schedulerUtil);
	}

	public void initialize() throws SchedulerException
	{
		try 
		{
			schedulerProcessFileId = schedulerUtil.getList("SC_PROCESS_FILE_JOB", ",");
			/** Get the Default scheduler from the standard scheduler factory */
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("IntegrationManagement", integrationManagementServices);
			scheduler = new StdSchedulerFactory().getScheduler();
//			Date startTime = DateBuilder.nextGivenSecondDate(null, 10);

			/* Process File job to transfer files from sftp to inerface and interface to sftp*/
			System.out.println("Job transfer files from sftp to interface and interface to sftp");
			for (String interfaceIdStr : schedulerProcessFileId) 
			{
				//				System.out.println("ProcessFile scheduler configuration stared for :: " + interfaceIdStr);
				String groupName = null;
				String cronScheduler = null;
				/** define the job and tie it to our corresponding class */
				JobDetail processFilesJob = null;
				CronTrigger groupTrigger = null;

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
					//					group3Trigger = new CronTrigger(groupName + "_Trigger", cronScheduler);

					groupTrigger = TriggerBuilder
							.newTrigger()
							.withIdentity("transferFilesJob_" + interfaceId, groupName)
//							.startAt(startTime)
							// startNow()
							.usingJobData("INTERFACE", interfaceId)
							.usingJobData(jobDataMap)
							.withSchedule(CronScheduleBuilder.cronSchedule(cronScheduler))
							.build();
					
					groupTrigger.getJobDataMap().put("INTERFACE", interfaceId);
					scheduler.scheduleJob(processFilesJob, groupTrigger);

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				finally
				{
					groupName = null;
					cronScheduler = null;
					/** define the job and tie it to our corresponding class */
					processFilesJob = null;
					groupTrigger = null;
				}
				//				System.out.println("ProcessFile scheduler configuration ended for :: " + interfaceIdStr);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

//	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws InterruptedException, SchedulerException 
	{
		/** Scheduler starts */
		System.out.println("Scheduler starts now 1");
		scheduler.startDelayed(12000);
		if(!scheduler.isStarted())
		{
			System.out.println("Scheduler starts now 2");
			scheduler.startDelayed(12000);
		}
		if(!scheduler.isStarted())
			scheduler.standby();
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
				e.printStackTrace();
			}
		}
	}

	private static Scheduler scheduler = null;
}