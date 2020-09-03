package enh.team.interfaces.transformation;

import org.json.JSONObject;

public class App {

	public static void main(String[] args)
	{
		JSONObject jsonObject = null;
		TransformationOperation transformation = new TransformationOperation();
		
		jsonObject = transformation.loadFromCSVFile("C:\\Users\\vinayak\\Downloads\\analytics_table_size_for_interface.csv", true, "\\|", 5);
		jsonObject = transformation.dataConversionBytesMbKbGBTB(jsonObject);
		System.out.println(jsonObject);
		double totalmb = 0;
		double totalgb = 0;
		double totalbytes = 0;
		for (int i = 1; i<= jsonObject.keySet().size(); i++) {
			
			totalmb = totalmb + jsonObject.getJSONObject(i+"").getFloat("mb");
			totalgb = totalgb + jsonObject.getJSONObject(i+"").getFloat("gb");
			totalbytes = totalbytes + jsonObject.getJSONObject(i+"").getFloat("bytes");
			//System.out.println(jsonObject.getJSONObject(i+"").getFloat("mb"));
			System.out.println(jsonObject.getJSONObject(i+"").getFloat("bytes"));
		}
		
		//System.out.println(totalmb);
		//System.out.println(totalbytes);
	}

}
