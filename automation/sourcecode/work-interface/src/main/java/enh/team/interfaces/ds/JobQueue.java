package enh.team.interfaces.ds;

import org.json.simple.JSONObject;

import enh.team.interfaces.exception.GenericProcessorException;

public abstract class JobQueue
{
	protected long queueSize;
	protected long pollWaitTime;
	protected long pushWaitTime;
	protected long pollSleepTime;
	protected long pushSleepTime;
	protected long maxWaitTime = 0;
	public String name;
	protected long retryCount;
	
	public JobQueue(JSONObject configData)
	{
		if(configData.get("size") != null && !configData.get("size").toString().trim().isEmpty())
			queueSize = (Long) configData.get("size");
		else
			queueSize = 2;
		
		if(configData.get("poll_wait_time") != null && !configData.get("poll_wait_time").toString().trim().isEmpty())
			pollWaitTime = (Long) configData.get("poll_wait_time");
		else
			pollWaitTime = 0;
		
		if(configData.get("push_wait_time") != null && !configData.get("push_wait_time").toString().trim().isEmpty())
			pushWaitTime = (Long) configData.get("push_wait_time");
		else
			pushWaitTime = 0;
		
		if(configData.get("poll_sleep_time") != null && !configData.get("poll_sleep_time").toString().trim().isEmpty())
			pollSleepTime = (Long) configData.get("poll_sleep_time");
		else
			pollSleepTime = 0;
		
		if(configData.get("push_sleep_time") != null && !configData.get("push_sleep_time").toString().trim().isEmpty())
			pushSleepTime = (Long) configData.get("push_sleep_time");
		else
			pushSleepTime = 0;
		
		if(configData.get("max_wait_time") != null && !configData.get("max_wait_time").toString().trim().isEmpty())
			maxWaitTime = (Long) configData.get("max_wait_time");
		
		if(configData.get("retry_count") != null && !configData.get("retry_count").toString().trim().isEmpty())
			retryCount = (Long) configData.get("retry_count");
		else
			retryCount = 10;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public abstract void enqueue(JSONObject jobObject) throws GenericProcessorException;
	public abstract JSONObject dequeue(JSONObject jobObject) throws GenericProcessorException;
}