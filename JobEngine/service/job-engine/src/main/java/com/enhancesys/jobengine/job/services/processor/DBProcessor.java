package com.enhancesys.jobengine.job.services.processor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.enhancesys.jobengine.job.services.job.JobConfigurationReader;
import com.enhancesys.jobengine.job.services.util.DataConstants;
import com.enhancesys.jobengine.job.services.util.NoMoreDataException;

public abstract class DBProcessor 
{
	protected String pipeLineName;
	protected String processorName;
	protected JSONObject processorConfig;
	protected JobConfigurationReader jobConfigurationReader;
	protected JSONObject jobConfigData = null;
	protected JSONArray collectionArr = null;
	protected String skipOnNotExist = null;
	protected String secondaryMatch = null;
	protected JSONObject jobParameters = null; 
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		this.pipeLineName = pipeLineName;
		this.processorName = processorName;
		this.processorConfig = processorConfig;
		this.jobConfigurationReader = jobConfigurationReader;
		this.jobParameters = jobParameters;
		
		jobConfigData = jobConfigurationReader.getJobConfigData(null);
		collectionArr = (JSONArray) jobConfigData.get(DataConstants.PRIMARY);
		skipOnNotExist = jobConfigData.get(DataConstants.SKIP_ON_NOT_EXIST) != null ? jobConfigData.get(DataConstants.SKIP_ON_NOT_EXIST).toString() : "";
		secondaryMatch = jobConfigData.get(DataConstants.SECONDARY_MATCH) != null ? jobConfigData.get(DataConstants.SECONDARY_MATCH).toString() : "";
	}
	
	public abstract void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException;
	
	public abstract void stop();
}
