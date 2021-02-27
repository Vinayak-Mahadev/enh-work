package com.enhancesys.integration.snoc.services.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.enhancesys.integration.snoc.entities.Interfaces;
import com.enhancesys.integration.snoc.entities.Status;
import com.enhancesys.integration.snoc.exception.ApplicationException;
import com.enhancesys.integration.snoc.services.interfaces.ExternalInterfaceLocal;
import com.enhancesys.integration.snoc.services.interfaces.IntegrationManagementLocal;

@Repository
public interface SnocRepository extends IntegrationManagementLocal, ExternalInterfaceLocal
{

	public List<Interfaces> getInterfaceByListOfId(List<Long> interfaceIds) throws ApplicationException;
	public Status getStatusByID(Long status) throws ApplicationException;
	

}
