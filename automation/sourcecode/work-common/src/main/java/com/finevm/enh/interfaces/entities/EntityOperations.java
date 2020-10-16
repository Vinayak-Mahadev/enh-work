package com.finevm.enh.interfaces.entities;

import java.util.List;

import javax.persistence.EntityManager;

public class EntityOperations
{
	private static EntityOperations entityOperations = null;
	private static EntityManager manager = null;



	private EntityOperations() {
		super();
		if(manager == null)
			manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");
	}

	public EntityOperations(String persistance) {
		super();
		if(manager == null)
			manager = DataSourceConfig.getEntityManager(persistance);
	}

	public static EntityOperations getInstance() {
		if(entityOperations == null)
			entityOperations = new EntityOperations();
		return entityOperations;
	}
	
	public static EntityOperations getInstance(String persistance) {
		if(persistance == null)
			entityOperations = new EntityOperations();
	else	
		entityOperations = new EntityOperations(persistance);
		return entityOperations;
	}

	public static void closeInstance() {

		if(manager != null && manager.isOpen()) 
		{
			manager.close();
		}
		manager = null;
	}


	@SuppressWarnings("unchecked")
	public  List<InterfaceAttribute> getInterfaceAttribute(long interfaceid) 
	{

		List<InterfaceAttribute> interfaceAttribute = null;

		try 
		{
			if( (manager != null && !manager.isOpen()))
				manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");
			if(manager == null)
				manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");
			interfaceAttribute = manager.createQuery("from " + InterfaceAttribute.class.getCanonicalName()+" where interface_id_n = "+interfaceid+" order by 1").getResultList();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return interfaceAttribute;
	}

}
