package com.enhancesys.integration.snoc.service.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.enhancesys.integration.snoc.exception.ApplicationException;
import com.enhancesys.integration.snoc.interfaces.processor.PullDataFromMongoOrPG;

@Component("com.enhancesys.integration.snoc.service.processor.GenericDailyDump")
@Scope("prototype")
public class GenericDailyDump implements PullDataFromMongoOrPG
{
	@Override
	public void processRequest(Long interfaceId) throws ApplicationException
	{
		System.out.println("Interface id :: " + interfaceId);
	}
}