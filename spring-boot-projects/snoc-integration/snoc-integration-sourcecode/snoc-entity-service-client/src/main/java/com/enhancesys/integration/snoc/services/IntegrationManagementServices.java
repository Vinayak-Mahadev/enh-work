package com.enhancesys.integration.snoc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cxf.com.enhancesys.entities.schema.integration.IntegrationManagement;
import cxf.com.enhancesys.entities.schema.integration.Interfaces;
import cxf.com.enhancesys.entities.schema.integration.Module;

/**
 * <b>Purpose:</b>
 * <br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * Enhancesys Innovations Private Limited<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date		Author</b>
 * ==============================================
 * 1       Mar 2, 2020			Vinayak Mahadev
 * 	-- Base Release
 * </pre>
 * 
 * <br>
 */


@Component
public class IntegrationManagementServices 
{

	@Autowired
	private final IntegrationManagement integrationManagement;

	IntegrationManagementServices(IntegrationManagement integrationManagement)
	{
		this.integrationManagement = integrationManagement;
		System.out.println("IntegrationManagement services activated :: " + integrationManagement);
	}
	
	public void startProcessFile(final List<Long> list) 
	{
		try 
		{
			integrationManagement.processFile(list);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
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
