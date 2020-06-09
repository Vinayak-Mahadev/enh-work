package com.enhancesys.jobengine.consumer.repository;

import java.util.List;
import java.util.Map;

import com.enhancesys.jobengine.consumer.model.User;



public interface JobEngineRepository 
{
	
	public Map<Long,User> getAllUsers();
	public List<User> findByFirstName(String firstName);
	public User getuser(long userid);
	public User getuser(String userid);
	public boolean authenticationUser(User user, String pass);

}
