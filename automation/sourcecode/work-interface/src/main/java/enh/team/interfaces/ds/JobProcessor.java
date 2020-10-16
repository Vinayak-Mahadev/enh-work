package enh.team.interfaces.ds;

import org.json.simple.JSONObject;

import enh.team.interfaces.exception.GenericProcessorException;

public abstract class JobProcessor 
{
	protected String pipeLineName;
	protected String processorName;
	protected JSONObject processorConfig;
	protected JobConfigurationReader jobConfigurationReader;
	protected JSONObject jobConfigData = null;
	protected JSONObject jobParameters = null;
//	protected String secondaryMatch = null;
	
	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfigurationReader jobConfigurationReader, JSONObject jobParameters) throws Exception
	{
		this.pipeLineName = pipeLineName;
		this.processorName = processorName;
		this.processorConfig = processorConfig;
		this.jobConfigurationReader = jobConfigurationReader;
		jobConfigData = jobConfigurationReader.getJobConfigData(null);
		this.jobParameters = jobParameters;
//		secondaryMatch = jobConfigData.get(DataConstants.SECONDARY_MATCH) != null ? jobConfigData.get(DataConstants.SECONDARY_MATCH).toString() : "";
	}
	
	public abstract boolean process(JSONObject jobObject) throws GenericProcessorException;
	
	public abstract void stop();
}
