package com.enhancesys.jobengine.job.services.util;

import org.apache.camel.spring.spi.BridgePropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import com.enhancesys.jobcommon.Constants;

public class MyBridgePropertyPlaceholderConfigurer extends BridgePropertyPlaceholderConfigurer
{
	public  MyBridgePropertyPlaceholderConfigurer() 
	{
		setLocation(new FileSystemResource(Constants._JOB_CONFIG_PATH + "jobConfiguration.properties"));
	}
	
	
}
