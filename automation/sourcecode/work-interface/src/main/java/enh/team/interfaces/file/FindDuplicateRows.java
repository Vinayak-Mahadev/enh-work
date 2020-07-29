package enh.team.interfaces.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FindDuplicateRows
{
	public static void main(String[] args) throws Exception 
	{
		File file = new File("C:\\Users\\vinayak\\Downloads\\uro20_20200713154617_1.csv");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String dataLine = null;
		Set<String> set = new HashSet<String>();
		List<String> dataSet = new ArrayList<String>();
		while((dataLine = bufferedReader.readLine()) != null)
		{
			dataSet.add(dataLine);
		}
		System.out.println("DataSet Size : " + dataSet.size());
		for(String dataLine1 : dataSet)
		{
			int count = 0;
			for(String tempLine : dataSet)
			{
				if(dataLine1.equalsIgnoreCase(tempLine))
				{
					count ++;
				}
			}
			
			if(count > 1)
			{
				System.out.println(count + " : " + dataLine1);
				set.add(dataLine1);
			}
		}
		
		System.out.println(set);
		
		System.out.println("Number of Duplicates are : " + set.size());
		
		bufferedReader.close();
		fileReader.close();
	}
}
