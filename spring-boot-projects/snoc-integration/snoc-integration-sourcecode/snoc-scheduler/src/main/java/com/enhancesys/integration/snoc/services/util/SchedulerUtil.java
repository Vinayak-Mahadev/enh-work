package com.enhancesys.integration.snoc.services.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:scheduler.properties"})
@Primary
//@PropertySource("file:${HOME}/scheduler.properties")
public class SchedulerUtil 
{

	@Autowired
	Environment env;

	public SchedulerUtil()
	{
		System.out.println("SchedulerUtil activated...");
	}
	
	public String getProperty(String key) 
	{
		return (env.getProperty(key) != null && !env.getProperty(key).trim().isEmpty()) ? env.getProperty(key).trim() : null;
	}

	public List<String> getList(String key, String regex) 
	{
		return (env.getProperty(key) != null && !env.getProperty(key).trim().isEmpty()) ? Arrays.asList(env.getProperty(key).split(regex)): new ArrayList<String>();
	}


}
