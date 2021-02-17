package com.finevm.rdbms.pg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class PGOperations 
{
	public static void tableIntoXls(final Connection connection, List<String> tableList, final String query, String fileLoc) 
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ResultSetMetaData metaData = null;
		int rowCount = 0;
		OutputStream outputStream = null;
		try 
		{
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			outputStream = new FileOutputStream(new File(fileLoc));
			for (String tableName : tableList) 
			{
				Sheet sheet = workbook.createSheet(tableName);
				rowCount = 0;
				Row  row = sheet.createRow(rowCount++);

				if(query == null)
				{
					System.out.println("Sheet :: " + tableName + "  query :: " + "select * from "  + tableName + "  order by 1;");
					statement = connection.prepareStatement("select * from "  + tableName + "  order by 1;");
				}
				resultSet = statement.executeQuery();
				metaData = resultSet.getMetaData();

				int columnCount = metaData.getColumnCount();

				for (int i = 0; i < columnCount; i++) 
					row.createCell(i).setCellValue(metaData.getColumnLabel(i+1).toUpperCase());

				while(resultSet.next())
				{
					row = sheet.createRow(rowCount++);
					for (int i = 0; i < columnCount; i++) 
						if(resultSet.getString(i+1) != null)
							row.createCell(i).setCellValue("'"+resultSet.getObject(i+1)+"'");
						else
							row.createCell(i).setCellValue("");
				}
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			workbook.write(outputStream);

			if(workbook != null)
				workbook.dispose();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
