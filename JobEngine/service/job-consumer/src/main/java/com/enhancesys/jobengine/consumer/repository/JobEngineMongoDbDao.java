package com.enhancesys.jobengine.consumer.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.enhancesys.jobcommon.Constants;
import com.enhancesys.jobengine.consumer.model.User;

@Repository
@Qualifier(Constants._MongoDB)
public class JobEngineMongoDbDao implements JobEngineRepository{

	@Override
	public Map<Long, User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getuser(long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getuser(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticationUser(User user, String pass) {
		// TODO Auto-generated method stub
		return false;
	}


}
