package com.enhancesys.integration.snoc.services.conf;

import com.enhancesys.integration.snoc.services.IntegrationManagementServices;

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

public class SchedulerControls 
{

	Module schedulerModule;

	public SchedulerControls() 
	{
		System.out.println("SchedulerControls activated");
	}

	public final boolean isEnabledForProcessFileScheduler(IntegrationManagementServices integrationManagementServices, long interfaceId)
	{
		Interfaces interfaces;
		try 
		{
			interfaces = integrationManagementServices.getInterfaceById(interfaceId);
			if(interfaces != null)
			{
				if(interfaces.getInterfaceId() == interfaceId)
				{
					return true;
				}
				else
				{
					System.out.println("ProcessFileScheduler disabled for given moduleId :: " + interfaceId);
				}
			}
		}
		catch (NullPointerException e) 
		{
			if(integrationManagementServices == null)
				System.out.println("IntegrationManagement service isn't up");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			interfaces = null;
		}
		return false;
	}
}
