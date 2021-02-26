package com.enhancesys.integration.snoc.services.schedulers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.integration.snoc.services.IntegrationManagementServices;

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
public class INT_ProcessFileJob implements Job
{
	@Autowired
	private final IntegrationManagementServices integrationManagementServices;

	public INT_ProcessFileJob(IntegrationManagementServices integrationManagementServices) 
	{
		this.integrationManagementServices = integrationManagementServices;
	}
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException 
	{
		System.out.println("Entry ProcessFileJob Started at --> "+ new Date());
		Long startTime = System.currentTimeMillis();
		List<Long> interfaceList = null;
		Long interfaceId = null;
		Interfaces interfaces = null;
		Module module = null;
		try 
		{
			interfaceList = new ArrayList<Long>();
			interfaceId = (Long) jobExecutionContext.getJobDetail().getJobDataMap().get("INTERFACE");
			System.out.println("ProcessFileJob received interface id-->"+interfaceId);
			interfaceList.add(interfaceId);
			if(!interfaceList.isEmpty())
			{
				interfaces = integrationManagementServices.getInterfaceById(interfaceId);
				if(interfaces != null)
				{
					System.out.println("INT: "+interfaces.getModuleId()+" File Transfer Job is Initiated at " + new Date());
					module = integrationManagementServices.getModule(interfaces.getModuleId());
					System.out.println(module.getModuleId() + " :: " + module.getName());
					integrationManagementServices.startProcessFile(interfaceId);
					System.out.println("INT: "+interfaces.getModuleId()+" File Transfer Job is Completed at " + new Date());
				}
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
}