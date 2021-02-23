package com.enhancesys.integration.snoc.interfaces.reprocess;

import com.enhancesys.integration.snoc.exception.ApplicationException;


public interface ReProcess {
	public void processRequest(Long interfaceId, Long fileId, Long errorCode) throws ApplicationException;
}
