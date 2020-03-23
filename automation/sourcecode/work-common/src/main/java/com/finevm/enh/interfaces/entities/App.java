//package com.finevm.enh.interfaces.entities;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//public class App 
//{
//
//	public static void main(String[] args) 
//	{
//
//		EntityManager manager = null;
//		List<Interfaces> interfacesList = null;
//
//		try 
//		{
//			manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");
//
//			interfacesList = manager.createQuery("from " + Interfaces.class.getCanonicalName()+" order by 1").getResultList();
//
//			System.out.println(interfacesList.size());
//
//			for (Interfaces interfaces : interfacesList)
//			{
//				System.out.println(interfaces);
//			}
//
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		finally 
//		{
//			if(manager != null && manager.isOpen()) 
//			{
//				manager.close();
//			}
//			manager = null;
//		}
//
//	}
//
//}
