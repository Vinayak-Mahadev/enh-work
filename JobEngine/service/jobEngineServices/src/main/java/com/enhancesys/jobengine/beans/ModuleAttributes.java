package com.enhancesys.jobengine.beans;

import java.sql.Date;

public class ModuleAttributes 
{
	private long moduleId;

	private long attributeId;

	private String name;
	
	private String value;
	
	private Date lastUpdateTime;

	public long getModuleId() {
		return moduleId;
	}

	public long getAttributeId() {
		return attributeId;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
	
}
