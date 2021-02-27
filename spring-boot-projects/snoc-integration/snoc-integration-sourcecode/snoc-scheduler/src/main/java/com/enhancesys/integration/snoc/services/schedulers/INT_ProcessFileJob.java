package com.enhancesys.integration.snoc.services.schedulers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.enhancesys.integration.snoc.services.IntegrationManagementServices;
import com.enhancesys.integration.snoc.services.conf.SchedulerControls;

import cxf.com.enhancesys.entities.schema.integration.Interfaces;
import cxf.com.enhancesys.entities.schema.integration.Module;

/**
 * <b>Purpose:</b>
 * <br>
 * 		Implementation of File Process Job.
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 *		Enhancesys Innovations 2014<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1       20-10-2020          Vinayak Mahadev
 * -- Base Release
 */

@DisallowConcurrentExecution
public class INT_ProcessFileJob extends SchedulerControls implements Job
{

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException 
	{
		System.out.println("Entry ProcessFileJob Started at --> "+ new Date());
		Long startTime = System.currentTimeMillis();
		List<Long> interfaceList = null;
		Long interfaceId = null;
		Interfaces interfaces = null;
		Module module = null;
		JobDataMap  jobDataMap = null;
		IntegrationManagementServices integrationManagementServices = null;
		try 
		{
			jobDataMap = jobExecutionContext.getMergedJobDataMap();
			integrationManagementServices = (IntegrationManagementServices) jobDataMap.get("IntegrationManagement");
			System.out.println("IntegrationManagementServices :: " + integrationManagementServices);
			interfaceId =  Long.parseLong(jobDataMap.get("INTERFACE").toString());
			System.out.println("INTERFACE :: " + interfaceId);
			if(isEnabledForProcessFileScheduler(integrationManagementServices, interfaceId))
			{
				interfaces = integrationManagementServices.getInterfaceById(interfaceId);
				if(interfaces != null)
				{
					interfaceList = new ArrayList<Long>();
					interfaceList.add(interfaceId);
					System.out.println("INT: "+interfaces.getModuleId()+" File Transfer Job is Initiated at " + new Date());
					module = integrationManagementServices.getModule(interfaces.getModuleId());
					System.out.println(module.getModuleId() + " :: " + module.getName());
					integrationManagementServices.startProcessFile(interfaceList);
					System.out.println("INT: "+interfaces.getModuleId()+" File Transfer Job is Completed at " + new Date());
				}
				else
					System.out.println("interfaces :: " + interfaces);
			}
			else
			{
				System.out.println("Disabled interface id for process file :: " + interfaceId);
			}
		}
		catch(Exception exception)
		{
			System.out.println(exception.getMessage() + exception);
			exception.printStackTrace();
		}
		finally
		{
			interfaceList = null;
			interfaces = null;
			System.out.println("Exit ProcessFileJob Ended at -->  " + new Date() + " --- and took " + (System.currentTimeMillis()-startTime)+" milliseconds");
		}
	}

	public void name() {

	}
}