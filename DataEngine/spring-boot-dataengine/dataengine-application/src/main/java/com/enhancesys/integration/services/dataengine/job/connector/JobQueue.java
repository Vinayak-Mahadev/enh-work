package com.enhancesys.integration.services.dataengine.job.connector;

import org.json.simple.JSONObject;

import com.enhancesys.integration.services.dataengine.job.components.DataConnector;
import com.enhancesys.integration.services.dataengine.util.exception.GenericProcessorException;

public abstract class JobQueue implements DataConnector
{
	protected long _queueSize_;
	protected long _pollWaitTime_;
	protected long _pushWaitTime_;
	protected long _pollSleepTime_;
	protected long _pushSleepTime_;
	protected long _maxWaitTime_;
	public String _name_;
	protected long _retryCount_;
	
	public void init(JSONObject configData)
	{

		if(configData != null && configData.get("size") != null && !configData.get("size").toString().trim().isEmpty())
			_queueSize_ = (Long) configData.get("size");
		else
			_queueSize_ = 2;

		if(configData != null && configData.get("poll_wait_time") != null && !configData.get("poll_wait_time").toString().trim().isEmpty())
			_pollWaitTime_ = (Long) configData.get("poll_wait_time");
		else
			_pollWaitTime_ = 10;

		if(configData != null && configData.get("push_wait_time") != null && !configData.get("push_wait_time").toString().trim().isEmpty())
			_pushWaitTime_ = (Long) configData.get("push_wait_time");
		else
			_pushWaitTime_ = 10;

		if(configData != null && configData.get("poll_sleep_time") != null && !configData.get("poll_sleep_time").toString().trim().isEmpty())
			_pollSleepTime_ = (Long) configData.get("poll_sleep_time");
		else
			_pollSleepTime_ = 100;
		// recommended pushSleepTime is more than 1000;
		if(configData != null && configData.get("push_sleep_time") != null && !configData.get("push_sleep_time").toString().trim().isEmpty())
			_pushSleepTime_ = (Long) configData.get("push_sleep_time");
		else
			_pushSleepTime_ = 500;

		if(configData != null && configData.get("max_wait_time") != null && !configData.get("max_wait_time").toString().trim().isEmpty())
			_maxWaitTime_ = (Long) configData.get("max_wait_time");
		else
			_maxWaitTime_ = 1 * 30 * 1000;// 30 sec

		if(configData != null && configData.get("retry_count") != null && !configData.get("retry_count").toString().trim().isEmpty())
			_retryCount_ = (Long) configData.get("retry_count");
		else
			_retryCount_ = 10;

	}

	public void setName(String name)
	{
		this._name_ = name;
	}

	public abstract void enqueue(JSONObject jobObject) throws GenericProcessorException;
	public abstract JSONObject dequeue(JSONObject jobObject) throws GenericProcessorException;
}