package enh.team.interfaces.transformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class TransformationOperation 
{
	public JSONArray loadFromFile(String filePath) 
	{
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		BufferedReader reader = null;
		String line = null;
		try 
		{
			int i = 0;
			reader = new BufferedReader(new FileReader(new File(filePath)));
			while ((line = reader.readLine()) != null)
			{
				line.replaceAll(" ", "");
				if(!line.isEmpty()) 
				{
					jsonObject = new JSONObject();
					jsonObject.put(""+i++, line);
					jsonArray.put(jsonObject);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			reader = null;
		}
		return jsonArray;
	}

	public JSONObject loadFromCSVFile(String filePath,  boolean header, String delimiter, int column) 
	{
		JSONObject map = new JSONObject();
		JSONObject jsonObject = null;
		BufferedReader reader = null;
		String line = null;
		try 
		{
			long i = 0;
			reader = new BufferedReader(new FileReader(new File(filePath)));
			while ((line = reader.readLine()) != null)
			{
				try 
				{
					if (header && i == 0)
						continue;

					jsonObject = new JSONObject();
					if(!line.trim().isEmpty() && line.split(delimiter, -1).length>=column)
						jsonObject.put("file_data", line.split(delimiter, -1)[column-1].trim());
					else
						jsonObject.put("file_data", "");
					map.put(i+"", jsonObject);
				} 
				finally 
				{
					i++;
				}


			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			reader = null;
		}
		return map;
	}

	public JSONObject dataConversionBytesMbKbGBTB(JSONObject jObject) 
	{
		JSONObject childJsonObj = null;
		double bytes = 0;
		double kb = 0;
		double mb = 0;
		double gb = 0;
		double tb = 0;
		String temp = null;
		for (int i = 1; i<= jObject.keySet().size(); i++) 
		{
			childJsonObj = (jObject.get(i+"") != null && jObject.get(i+"") instanceof JSONObject) ? jObject.getJSONObject(i+"") : null;
			if(childJsonObj == null)
				continue;
			temp = childJsonObj.getString("file_data").replaceAll(" ", "").trim().toLowerCase();

			if(temp.isEmpty())
			{
				
				childJsonObj.put("bytes", 0);
				childJsonObj.put("kb", 0);
				childJsonObj.put("mb", 0);
				childJsonObj.put("gb", 0);
			}

			if(temp.contains("bytes"))
			{
				temp = temp.replaceAll("bytes", "");
				bytes = Double.parseDouble(temp);
				childJsonObj.put("bytes", bytes);
				childJsonObj.put("kb", bytes_to_kb(bytes));
				childJsonObj.put("mb", bytes_to_mb(bytes));
				childJsonObj.put("gb", bytes_to_gb(bytes));
//				childJsonObj.put("tb", bytes_to_tb(bytes));
			}

			if(temp.contains("kb"))
			{
				temp = temp.replaceAll("kb", "");
				kb = Double.parseDouble(temp);
				childJsonObj.put("bytes", kb_to_bytes(kb));
				childJsonObj.put("kb", kb);
				childJsonObj.put("mb", kb_to_mb(kb));
				childJsonObj.put("gb", kb_to_gb(kb));
//				childJsonObj.put("tb", kb_to_tb(kb));
			}

			if(temp.contains("mb"))
			{
				temp = temp.replaceAll("mb", "");
				mb = Double.parseDouble(temp);
				childJsonObj.put("bytes", mb_to_bytes(mb));
				childJsonObj.put("kb", mb_to_kb(mb));
				childJsonObj.put("mb", mb);
				childJsonObj.put("gb", mb_to_gb(mb));
//				childJsonObj.put("tb", mb_to_tb(mb));
			}

			if(temp.contains("gb"))
			{
				temp = temp.replaceAll("gb", "");
				gb = Double.parseDouble(temp);
				childJsonObj.put("bytes", gb_to_bytes(gb));
				childJsonObj.put("kb", gb_to_kb(gb));
				childJsonObj.put("mb", gb_to_mb(gb));
				childJsonObj.put("gb", gb);
//				childJsonObj.put("tb", gb_to_tb(gb));
			}

			if(temp.contains("tb"))
			{
				temp = temp.replaceAll("tb", "");
				tb = Double.parseDouble(temp);
				childJsonObj.put("bytes", tb_to_bytes(tb));
				childJsonObj.put("kb", tb_to_kb(tb));
				childJsonObj.put("gb", tb_to_gb(tb));
				childJsonObj.put("tb", tb);
			}
			jObject.put(i+"", childJsonObj);

		}
		return jObject;
	}

	public double bytes_to_kb(double data) {
		if(data != 0.00)
			return data/1024;
		return data;
	}
	public double bytes_to_mb(double data) {	
		if(data != 0.00)
			return data/(1024*1024);
		return data;
	}
	public double bytes_to_gb(double data) {	
		if(data != 0.00)
			return data/(1024*1024*1024);
		return data;
	}
	public double bytes_to_tb(double data) {	
		if(data != 0.00)
			return data/(1024*1024*1024*1024);
		return data;
	}

	public double kb_to_bytes(double data) {		
		return data*1024;
	}
	public double kb_to_mb(double data) {		
		if(data != 0.00)
			return data/(1024);
		return data;
	}
	public double kb_to_gb(double data) {
		
		if(data != 0.00)
			return data/(1024*1024);
		return data;
	
	}
	public double kb_to_tb(double data) {	
		if(data != 0.00)
			return data/(1024*1024*1024);
		return data;
	}

	public double mb_to_bytes(double data) {		
		return data*1024*1024;
	}
	public double mb_to_kb(double data) {		
		return data*1024;
	}
	public double mb_to_gb(double data) {		
		if(data != 0.00)
			return data/(1024);
		return data;
	}
	public double mb_to_tb(double data) {		
		if(data != 0.00)
			return data/(1024*1024);
		return data;
	}


	public double gb_to_bytes(double data) {		
		return data*1024*1024*1024;
	}
	public double gb_to_kb(double data) {		
		return data*1024*1024;
	}
	public double gb_to_mb(double data) {		
		return data*1024;
	}
	public double gb_to_tb(double data) {	
		if(data != 0.00)
			return data/(1024);
		return data;
	}


	public double tb_to_bytes(double data) {		
		return data*1024*1024*1024*1024;
	}
	public double tb_to_kb(double data) {		
		return data*1024*1024*1024;
	}
	public double tb_to_mb(double data) {		
		return data*1024*1024;
	}
	public double tb_to_gb(double data) {		
		return data*1024;
	}

}
