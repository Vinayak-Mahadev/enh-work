package com.finevm.enh.interfaces.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DataSourceConfig 
{

	private static  Map<String , EntityManager> entityManagerMap = new HashMap<String, EntityManager>();

	public static EntityManager getEntityManager(String persistenceUnitName) 
	{

		EntityManagerFactory entitymanagerFactory = null;
		EntityManager entityManager = null;
		try 
		{
			if(DataSourceConfig.entityManagerMap.get(persistenceUnitName) == null) 
			{
				entitymanagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
				entityManager = entitymanagerFactory.createEntityManager();
				DataSourceConfig.entityManagerMap.put(persistenceUnitName, entityManager);
				System.out.println("EntityManager created with persistenceUnitName :: "+ persistenceUnitName);
			}
			if(!DataSourceConfig.entityManagerMap.get(persistenceUnitName).isOpen()) 
			{
				DataSourceConfig.entityManagerMap.put(persistenceUnitName, null);
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
		return DataSourceConfig.entityManagerMap.get(persistenceUnitName);
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
