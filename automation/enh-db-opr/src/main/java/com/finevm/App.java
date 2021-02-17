package com.finevm;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import com.finevm.rdbms.pg.PGOperations;
import com.finevm.util.PropsLoader;
import com.finevm.util.RDBMS;

public class App 
{
	public static void main(String[] args) 
	{
		String propsFileLoc = null;
		String operation = "";
		Connection connection = null;
		try 
		{
			propsFileLoc = args[0];
			operation = args[1].trim();
			System.out.println("propsFileLoc" + " :: " + propsFileLoc);
			PropsLoader.loadProperties(propsFileLoc);

			if(operation.equalsIgnoreCase("GENERATE_EXCEL_FILE_FROM_PGTABLES"))
			{
				System.out.println("Entry in GENERATE_EXCEL_FILE_FROM_PGTABLES");
				String outPutLoc = args[2];
				List<String> tableList = Arrays.asList(PropsLoader.getProperties("GENERATE_EXCEL_FILE_FROM_PGTABLES").split(","));
				connection = RDBMS.getDBConnection(PropsLoader.getProperties("DB_DRIVER"), PropsLoader.getProperties("DB_URL"), PropsLoader.getProperties("DB_USER"), PropsLoader.getProperties("DB_PASSWORD"));			
				PGOperations.tableIntoXls(connection, tableList, null, outPutLoc);
				System.out.println("Entry in GENERATE_EXCEL_FILE_FROM_PGTABLES");
			}
			if(connection != null)
				connection.close();	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		finally 
		{
			System.gc();
		}
	}
}
