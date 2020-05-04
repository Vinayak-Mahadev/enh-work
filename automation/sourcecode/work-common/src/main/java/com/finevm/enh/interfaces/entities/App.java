package com.finevm.enh.interfaces.entities;

import java.util.List;

import javax.persistence.EntityManager;

public class App 
{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{

		EntityManager manager = null;
		List<InterfaceAttribute> interfacesList = null;

		try 
		{
			manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");

			interfacesList = manager.createQuery("from " + InterfaceAttribute.class.getCanonicalName()+" where interface_id_n = 1153 order by 1").getResultList();

			System.out.println(interfacesList.size());

			for (InterfaceAttribute interfaces : interfacesList)
			{
				System.out.println(interfaces);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if(manager != null && manager.isOpen()) 
			{
				manager.close();
			}
			manager = null;
		}

	}

}
