package com.enhancesys.jobengine.beans;

import java.sql.Date;

/**
 * @author Vinayak
 *
 */
public class Module
{

	private long moduleId;

	private String name;
		
	private Date lastUpdateTime;

	public long getModuleId() {
		return moduleId;
	}

	public String getName() {
		return name;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", name=" + name + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
	
	
}
