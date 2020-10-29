package com.enhancesys.integration.services.dataengine.job;

import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public abstract class JobProcessor 
{
	protected Long _requestId_;
	protected Long _recordLimit_;
	protected String _pipeLineName_;
	protected String _processorName_;
	protected JSONObject _jobConfigData_;
	protected JSONObject _jobParameters_;
	protected JSONObject _processorConfig_;
	protected JSONObject _outputConf_;
	protected JSONObject _requestUpdateConf_;
	protected JSONObject _storeFileConf_;
	protected JobConfiguration _jobConfiguration_;
	
//	protected String secondaryMatch = null;
	
	public void init(final String pipeLineName, final String processorName, final JSONObject processorConfig, final JobConfiguration jobConfiguration, final JSONObject jobParameters) throws Exception
	{
		this._pipeLineName_ = pipeLineName;
		this._processorName_ = processorName;
		_processorConfig_ = processorConfig;
		_jobConfiguration_ = jobConfiguration;
		_jobConfigData_ = jobConfiguration.getJobConfigData(null);
		_jobParameters_ = jobParameters;
		
		if(jobParameters.containsKey(DataConstants.REQUEST_ID) && jobParameters.get(DataConstants.REQUEST_ID) != null && !jobParameters.get(DataConstants.REQUEST_ID).toString().isEmpty())
			_requestId_ = (Long) jobParameters.get("request-id");
		
		if(_jobConfigData_.containsKey(DataConstants.OUTPUT_CONF))
			_outputConf_ = (JSONObject) _jobConfigData_.get(DataConstants.OUTPUT_CONF); 
		
		if(_jobConfigData_.containsKey(DataConstants.REQUEST_UPDATE_CONF))
			_requestUpdateConf_ = (JSONObject) _jobConfigData_.get(DataConstants.REQUEST_UPDATE_CONF);

		if(_jobConfigData_.containsKey(DataConstants.STORE_FILE_CONF))
			_storeFileConf_ = (JSONObject) _jobConfigData_.get(DataConstants.STORE_FILE_CONF);

		if(_outputConf_.get(DataConstants.FILE_RECORD_LIMIT) != null && !_outputConf_.get(DataConstants.FILE_RECORD_LIMIT).toString().trim().isEmpty())
			_recordLimit_ = Long.parseLong(_outputConf_.get(DataConstants.FILE_RECORD_LIMIT).toString().trim()); 

//		secondaryMatch = jobConfigData.get(DataConstants.SECONDARY_MATCH) != null ? jobConfigData.get(DataConstants.SECONDARY_MATCH).toString() : "";
	}
	
	public abstract boolean process(JSONObject jobObject) throws GenericProcessorException;
	
	public abstract void stop();
}
