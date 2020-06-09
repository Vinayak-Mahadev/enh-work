package com.enhancesys.jobengine.consumer.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.enhancesys.jobcommon.Constants;
import com.enhancesys.jobengine.consumer.model.User;
import com.enhancesys.jobengine.consumer.repository.JobEngineRepository;

@Service
public class JobConsumerAuthenticationService
{

	@Autowired
	@Qualifier(Constants._RDBMS)
	private JobEngineRepository jobEngineRepository;
	
	private final Map<Long,User> users = new HashMap<Long,User>();


	JobConsumerAuthenticationService()
	{
		users.putAll(jobEngineRepository.getAllUsers());
		System.out.println("JobConsumerAuthenticationService created...");
	}


	public User getuser(String userid) 
	{
		Long usrId = null;
		try
		{
			usrId = Long.parseLong(userid);
		}
		catch (NumberFormatException e) 
		{
			usrId = 0l;
		}
		return users.get(usrId);
	}

	public User getuser(long userid) {
		return users.get(userid);
	}


	public boolean authenticationUser(User user, String pass) {

		if(user != null) {
			return user.getPassword().equals(pass);
		}
		return false;
	}



}
