package com.enhancesys.jobengine.consumer.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.enhancesys.jobcommon.Constants;
import com.enhancesys.jobengine.consumer.model.User;

@Repository
@Qualifier(Constants._RDBMS)
@EnableTransactionManagement
public class JobEngineRdbmsDao implements JobEngineRepository {


	@Autowired
	private JpaTransactionManager transactionManager;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private final Map<Long,User> users = new HashMap<Long,User>();


	public JobEngineRdbmsDao()
	{
		users.put(1001l, new User(1001l, "Vinay","vinay@123"));
		users.put(1002l, new User(1002l, "Vinay","vinay@123"));
		users.put(1003l, new User(1003l, "Vinay","vinay@123"));
		users.put(1004l, new User(1004l, "Vinay","vinay@123"));
		users.put(1005l, new User(1005l, "Vinay","vinay@123"));
		users.put(1006l, new User(1006l, "Vinay","vinay@123"));
		users.put(1007l, new User(1007l, "Vinay","vinay@123"));
		users.put(1008l, new User(1008l, "Vinay","vinay@123"));
		users.put(1009l, new User(1009l, "Vinay","vinay@123"));
		users.put(1010l, new User(1010l, "Vinay","vinay@123"));
		users.put(1011l, new User(1011l, "Vinay","vinay@123"));
		users.put(1012l, new User(1012l, "Vinay","vinay@123"));
		users.put(1013l, new User(1013l, "Vinay","vinay@123"));
		users.put(1014l, new User(1014l, "Vinay","vinay@123"));
		users.put(1015l, new User(1015l, "Vinay","vinay@123"));
		System.out.println("JobConsumerAuthenticationService created...");
	}


	@Override
	public Map<Long,User> getAllUsers() 
	{
		// TODO Auto-generated method stub
		return users;
	}

	
	@Override
	public List<User> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getuser(long userid) {
		return users.get(userid);
	}

	@Override
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

	@Override
	public boolean authenticationUser(User user, String pass) {

		if(user != null) {
			return user.getPassword().equals(pass);
		}
		return false;
	}



	private EntityManagerFactory getEntityManagerFactory()
	{
		if(entityManagerFactory==null  )
			entityManagerFactory = transactionManager.getEntityManagerFactory();
		if(!entityManagerFactory.isOpen())
			entityManagerFactory = transactionManager.getEntityManagerFactory();
		return entityManagerFactory;
	}

	@SuppressWarnings("unused")
	private EntityManager getEntityManager()
	{
		if(entityManager==null)
			entityManager = getEntityManagerFactory().createEntityManager();
		if(!entityManager.isOpen())
			entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}

}