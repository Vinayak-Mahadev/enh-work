package com.enhancesys.integration.services.dataengine.job.reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.job.JobConfiguration;
import com.enhancesys.integration.services.dataengine.util.DataConstants;
import com.enhancesys.integration.services.dataengine.util.exception.NoMoreDataException;

public abstract class DBProcessor 
{
	protected String _pipeLineName_;
	protected String _processorName_;
	protected JSONObject _processorConfig_;
	protected JobConfiguration _jobConfiguration_;
	protected JSONObject _jobConfigData_;
	protected JSONArray _collectionArr_;
	protected String _skipOnNotExist_;
	protected String _secondaryMatch_;
	protected JSONObject _jobParameters_; 

	public void init(String pipeLineName, String processorName, JSONObject processorConfig, JobConfiguration jobConfiguration, JSONObject jobParameters) throws Exception
	{
		this._pipeLineName_ = pipeLineName;
		this._processorName_ = processorName;
		this._processorConfig_ = processorConfig;
		this._jobConfiguration_ = jobConfiguration;
		this._jobParameters_ = jobParameters;

		_jobConfigData_ = jobConfiguration.getJobConfigData(null);
		if(_jobConfigData_.get(DataConstants.PRIMARY) != null)
			_collectionArr_ = (JSONArray) _jobConfigData_.get(DataConstants.PRIMARY);
		_skipOnNotExist_ = _jobConfigData_.get(DataConstants.SKIP_ON_NOT_EXIST) != null ? _jobConfigData_.get(DataConstants.SKIP_ON_NOT_EXIST).toString() : "";
		_secondaryMatch_ = _jobConfigData_.get(DataConstants.SECONDARY_MATCH) != null ? _jobConfigData_.get(DataConstants.SECONDARY_MATCH).toString() : "";
	}

	public abstract void fetchData(JSONArray keyField, JSONObject fetchedData) throws NoMoreDataException;

	public abstract void stop();
}
