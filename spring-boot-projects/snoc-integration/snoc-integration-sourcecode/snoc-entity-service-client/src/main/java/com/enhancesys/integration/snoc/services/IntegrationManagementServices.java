package com.enhancesys.integration.snoc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cxf.com.enhancesys.entities.schema.integration.IntegrationManagement;
import cxf.com.enhancesys.entities.schema.integration.Interfaces;
import cxf.com.enhancesys.entities.schema.integration.Module;


@Component
public class IntegrationManagementServices 
{

	@Autowired
	private final IntegrationManagement integrationManagement;

	IntegrationManagementServices(IntegrationManagement integrationManagement)
	{
		this.integrationManagement = integrationManagement;
	}
	
	public void startProcessFile(Long moduleId) 
	{
		List<Long> list = null;
		try 
		{
			list = new ArrayList<Long>();
			integrationManagement.processFile(list);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			list = null;	
		}
	}
	
	public Module getModule(Long moduleId) 
	{
		Module module = null;
		try 
		{
			module = integrationManagement.getModule(moduleId);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
		}
		return module;
	}
	public Interfaces getInterfaceById(Long interfaceId) 
	{
		Interfaces interfaces = null;
		try 
		{
			interfaces = integrationManagement.getInterfaceById(interfaceId);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
		}
		return interfaces;
	}
}
