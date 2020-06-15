package com.finevm.enh.interfaces.entities;

public class App 
{

	public static void main(String[] args) 
	{
		System.out.println(EntityOperations.getInstance().getInterfaceAttribute(1165));
		EntityOperations.closeInstance();
	}

}
