package com.enhancesys.integration.snoc.services.repositories;

import org.springframework.stereotype.Repository;

import com.enhancesys.integration.snoc.services.interfaces.ExternalInterfaceLocal;
import com.enhancesys.integration.snoc.services.interfaces.IntegrationManagementLocal;

@Repository
public interface SnocRepository extends IntegrationManagementLocal, ExternalInterfaceLocal
{


}
