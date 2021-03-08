package com.enhancesys.integration.snoc.services.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


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
