package com.enhancesys.jobengine.consumer.services.schedulers;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.enhancesys.jobengine.consumer.model.Module;
import com.enhancesys.jobengine.consumer.services.JobEngineServices;

@DisallowConcurrentExecution
public class ProcessModuleScheduler implements Job
{
	@Autowired
	private JobEngineServices jobEngineServices;	


	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		Module module = null;
		Long id = null;
		try 
		{
			module = new Module();

			id  =  (Long) jobExecutionContext.getJobDetail().getJobDataMap().get("MODULE");
			module.set_moduleId(id);
			jobEngineServices.processModule(module);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
