package com.enhancesys.integration.snoc.services.util;

import org.apache.log4j.Logger;

import com.enhancesys.integration.snoc.entities.InterfaceSummary;
import com.enhancesys.integration.snoc.exception.ApplicationException;
import com.enhancesys.integration.snoc.services.repositories.SnocRepository;

public class InterfaceServiceUtilThread implements Runnable 
{
	private static Logger LOGGER = Logger.getLogger(InterfaceServiceUtilThread.class);

	private  final SnocRepository snocRepository;
	private InterfaceSummary summary;
	private String methodName;

	public InterfaceServiceUtilThread(SnocRepository snocRepository) 
	{
		super();
		this.snocRepository = snocRepository;
	}

	private void setSummary(InterfaceSummary summary) 
	{
		this.summary = summary;
	}

	private void setMethodName(String methodName) 
	{
		this.methodName = methodName;
	}

	@Override
	public void run() 
	{
		LOGGER.debug("InterfaceServiceUtilThread "+methodName+" started...");
		try 
		{
			if("updateInterfaceSummary".equalsIgnoreCase(methodName))
				snocRepository.updateInterfaceSummary(summary);
		}
		catch (ApplicationException e) 
		{
			LOGGER.error(e.getMessage(), e);
		}
	}

	public static void updateInterfaceSummary(InterfaceSummary summary, SnocRepository snocRepository)
	{
		InterfaceServiceUtilThread serviceUtilThread = new InterfaceServiceUtilThread(snocRepository);
		serviceUtilThread.setSummary(summary);
		serviceUtilThread.setMethodName("updateInterfaceSummary");

		Thread thread = new Thread(serviceUtilThread);
		thread.start();
	}


}
