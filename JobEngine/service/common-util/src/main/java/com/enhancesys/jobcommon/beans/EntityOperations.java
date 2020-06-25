package com.enhancesys.jobcommon.beans;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class EntityOperations
{
	private static Logger log = Logger.getLogger(EntityOperations.class);

	private static EntityOperations entityOperations = null;
	private static  Map<String , EntityManager> entityManagerMap = new HashMap<String, EntityManager>();
	private static EntityManager manager = null;



	private EntityOperations() {
		super();
		if(manager == null)
			manager = getEntityManager("com.enhancesys.jobcommon.beans");
	}

	public static EntityOperations getInstance() {
		if(entityOperations == null)
			entityOperations = new EntityOperations();
		return entityOperations;
	}

	public static void closeInstance() 
	{
		if(manager != null && manager.isOpen()) 
		{
			manager.close();
		}
		manager = null;
	}

	public static EntityManager getEntityManager(String persistenceUnitName) 
	{
		EntityManagerFactory entitymanagerFactory = null;
		EntityManager entityManager = null;
		try 
		{
			if(persistenceUnitName == null)
				persistenceUnitName = "com.enhancesys.jobcommon.beans";
			if(entityManagerMap.get(persistenceUnitName) == null) 
			{
				entitymanagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
				entityManager = entitymanagerFactory.createEntityManager();
				entityManagerMap.put(persistenceUnitName, entityManager);
				log.debug("EntityManager created with persistenceUnitName :: "+ persistenceUnitName);
			}
			if(!entityManagerMap.get(persistenceUnitName).isOpen()) 
			{
				entityManagerMap.put(persistenceUnitName, null);
				getEntityManager(persistenceUnitName);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			entitymanagerFactory = null;
			//	dataSourceConfig = null;
		}
		return entityManagerMap.get(persistenceUnitName);
	}	

	public static void closeEntityManager(String persistenceUnitName) 
	{
		if(entityManagerMap.get(persistenceUnitName) != null) 
		{
			if( entityManagerMap.get(persistenceUnitName).isOpen())
				entityManagerMap.get(persistenceUnitName).close();
			entityManagerMap.put(persistenceUnitName, null);
		}
	}
}
