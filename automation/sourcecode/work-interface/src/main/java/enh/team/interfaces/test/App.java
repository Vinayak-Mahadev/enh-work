package enh.team.interfaces.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class App {

	public static void main(String[] args) 
	{
		try 
		{
			//SimpleDateFormat simpleDateFormat = null;
			//simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			//System.out.println(simpleDateFormat.parse("20200626000002"));
			//System.out.println(simpleDateFormat.parse("20200626000002_10"));
			//System.out.println(simpleDateFormat.parse("20200626000002_08"));
			//			String validationConf = "ICCID;M;R;[0-9]{20}+#MSISDN;M;R;[0-9]{1,20}+#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#SO_CREATION_DATE;M;D;yyyyMMdd#ALLOC_ID;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#TYPE;N;R;[a-zA-Z]#ALLOC_DATE;M;D;dd-MMM-yy#PAYMENT_DATE;N;D;dd-MMM-yy";
			//			List<String> validationConfList = Arrays.asList(validationConf.split("#"));
			//			System.out.println("validationConfList :: " + validationConfList.size());

			//			String data = "1001,1003,1005,1009,1011,1013,1015,1017,1007,1024,1025,1027,1029,1018,1019,1021,1020,1022,1023,1030,1031,1040,1045,1050,1049,1051,1053,1061,1062,1064,1065,1066,1067,1068,1069,1070,1071,1072,1075,1076,1077,1078,1079,1080,1081,1082,1090,1092,1136,1138,1143,1145,1146,1148,1152,1156,1157,1158,1159,1161,1162,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176,1177,1178,1179,1180,1181,1182";
			//
			//			String flag = "1066,1067,1068,1069,1070,1071,1076,1077,1078,1079,1080,1081";
			//
			//			List<String> dataList = Arrays.asList(data.split(","));
			//			System.out.println("dataList :: " + dataList);
			//			System.out.println("dataList size :: " + dataList.size());
			//
			//			System.out.println(flag.contains("1066"));
			//			for (String sid : dataList) 
			//			{
			//				if(!flag.contains(sid))
			//					System.out.print(sid + ",");	
			//			}

			/*String data = "2025-10-30";
			SimpleDateFormat foss = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tnm = new SimpleDateFormat("yyyyMMdd");
			System.out.println(foss.parse(data));*/


			//interfaceModule_uploadFiles_validation(null, null, null);
			//interfaceModule_uploadFiles_validation("total_revenue", "yyyyMMddHHmmss", "total_revenue_20200626000000");
			//			String monthInFile = "12345455";
			//			monthInFile = monthInFile.substring(0, 6);
			//			System.out.println(monthInFile);
			//			runtimeTest();

//			String headerWithType = "REPORT_NAME|CHANNEL|REGION|AREA|SALESAREA|CLUSTER|MICROCLUSTER|ADDITIONALTERRITORY|TERRITORYID|SUBORGTYPE|ORGANIZATIONID|ORGSHORTCODE|ORGANIZATIONNAME|PRODUCTBRAND|PRODUCTTYPE|PRODUCTCATEGORY|PRODUCTID|PRODUCTEXTID|PRODUCTNAME|QTY|AVERAGEQTY|BUSINESSDAYS|";
//			//String headerWithType = "REPORT_NAME:String|CHANNEL:String|REGION:String|AREA:String|SALESAREA:String|CLUSTER:String|MICROCLUSTER:String|ADDITIONALTERRITORY:String|TERRITORYID:String|SUBORGTYPE:String|ORGANIZATIONID:String|ORGSHORTCODE:String|ORGANIZATIONNAME:String|PRODUCTBRAND:String|PRODUCTTYPE:String|PRODUCTCATEGORY:String|PRODUCTID:String|PRODUCTEXTID:String|PRODUCTNAME:String|QTY:String|AVERAGEQTY:String|BUSINESSDAYS:String";
//			String delimiter = "\\|";
//			String childdelimiter = ":";
//			List<String> headerWithTypeList = null;
//			DBObject jobIdDbObject = new BasicDBObject();
//			BasicDBList primaryList = new BasicDBList();
//
//			primaryList.add(new BasicDBObject("query", "$sql_query"));
//
//			headerWithTypeList = Arrays.asList(headerWithType.split(delimiter, -1));
////
////			jobIdDbObject.put("Primary", primaryList);
////			jobIdDbObject.put("Lookup", new BasicDBObject());
////			jobIdDbObject.put("Configuration", prepareConfiguration(headerWithTypeList, childdelimiter));
////			jobIdDbObject.put("OutputConf", prepareOutputConf(headerWithTypeList, childdelimiter));
////			jobIdDbObject.put("SortConf", new BasicDBObject("extension", "csv").append("file-name-pattern", "yyyyMMddHHmmssSSS").append("sort-file-name_0", "collection1.sh").append("field-delimiter", "|"));
//
//			System.out.println(headerWithTypeList);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy h:ss");
			String date = "Fri Sep 25 15:37:36 EEST 2020";
			System.out.println(dateFormat.parse("30.08.2020 4:08"));
			System.out.println(dateFormat.format(dateFormat.parse("30.08.2020 4:08")));
			
			SimpleDateFormat mongoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			
			System.out.println(mongoDateFormat.format(dateFormat.parse("30.08.2020 4:08")));
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static DBObject prepareOutputConf(final List<String> headerList, final String childdelimiter) 
	{
		DBObject dbObject = new BasicDBObject();
		DBObject childDBDbObject = new BasicDBObject();
		String path = null;
		String type = "String";
		BasicDBList fieldconfList = new BasicDBList();
		String header = "";
		try
		{
			dbObject.put("type", "file");
			dbObject.put("extension", "$output_file_type");
			dbObject.put("file-path", "$output_file_path");
			dbObject.put("file-name", "$output_file_name");
			dbObject.put("file-name-pattern", "$output_file_date_patten");

			if(headerList == null || headerList.size() == 0);
			else
				for (int i = 0; i < headerList.size() ; i++) 
				{
					if(headerList.get(i).trim().toLowerCase().split(":").length == 2) {
						path = headerList.get(i).trim().toLowerCase().split(":")[0];
						type = headerList.get(i).trim().toLowerCase().split(":")[1];
					}
					else 
						path = headerList.get(i).trim().toLowerCase();

					childDBDbObject = new BasicDBObject();
					childDBDbObject.put("path", path);
					childDBDbObject.put("type", type);
					fieldconfList.add(childDBDbObject);
					header = header + path.toUpperCase()+"|";
				}

			dbObject.put("file-headers", header.substring(0, header.length()-1));
			dbObject.put("field-delimiter", "|");
			dbObject.put("field-conf", fieldconfList);

			return dbObject;
		}
		finally 
		{
			header = null;
			path = null;
			type = null;
			dbObject = null;
			fieldconfList = null;
			childDBDbObject = null;
		}
	}
	public static DBObject prepareConfiguration(final List<String> headerList, final String childdelimiter) 
	{
		DBObject dbObject = new BasicDBObject();
		DBObject childDBDbObject = new BasicDBObject();
		String key = null;
		try
		{
			if(headerList == null || headerList.size() == 0);
			else
				for (int i = 0; i < headerList.size() ; i++) 
				{
					key = headerList.get(i).trim().toLowerCase().split(":")[0];
					childDBDbObject = new BasicDBObject("field", key);
					dbObject.put(key, childDBDbObject);
				}
			return dbObject;
		}
		finally 
		{
			key = null;
			dbObject = null;
			childDBDbObject = null;
		}
	}

	public static void runtimeTest() {
		//String cmd = "C:/Program Files/Git/git-bash.exe E:/interface/backend/script.sh";
		String[] command = new String[]{"C:/Program Files/Git/git-bash.exe", "E:/interface/backend/script.sh"};
		Process process = null;
		try 
		{
			process = Runtime.getRuntime().exec(command);
			process.wait(5000);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	static void test()
	{
		final String query = "INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (#ATTRIBUTE_ID_N#, #INTERFACE_ID_N#, '#NAME_V#', '#VALUE_V#', now());";
		BufferedReader reader = null;
		String line = null;
		String fileAbPath = "C:\\Users\\vinayak\\Downloads\\3Query - Copy.txt";
		String[] attr = null;

		try 
		{
			reader = new BufferedReader(new FileReader(new File(fileAbPath)));
			while ((line = reader.readLine()) != null)
			{
				attr = line.replace("fossuser", "appuser").split("VINAY", -1);
				System.out.println(query.replace("#ATTRIBUTE_ID_N#", attr[0]).replace("#INTERFACE_ID_N#", attr[1]).replace("#NAME_V#", attr[2]).replace("#VALUE_V#", attr[3]));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			reader = null;
		}
	}

	void sample()
	{

		//tester();
		Set<String> fileNameList = new HashSet<String>();
		Set<String> successList = new HashSet<String>();
		Set<String> failList = new HashSet<String>();

		String orgFileName = "total_revenue";
		String name  = null;
		String temp  = null;
		//Date date = null;
		SimpleDateFormat simpleDateFormat = null;

		fileNameList.add("total_revenue_20200626000002.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_10.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_08.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_04.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_03.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_05a.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_09.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_07.ctl.gz");
		fileNameList.add("total_revenue_bi_20200626000002_06.ctl.gz");

		for (String fileName : fileNameList) 
		{
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			try 
			{
				name = fileName;
				temp = name.replaceAll(orgFileName + "_", "");
				temp = temp.replaceAll("." + "ctl" + "." + "gz", "");
				//			System.out.println(fileName + "   :::   "+ temp);
				simpleDateFormat.parse(temp);
				//			System.out.println(fileName + "   :::   "+ date);
				successList.add(fileName);
			} 
			catch (ParseException e) 
			{
				failList.add(fileName);
			}
		}

		System.out.println(successList);
		System.out.println(failList);

	}
	static void tester() 
	{

		System.out.println("TransType,PaymentType,OrderStatus,isRecordAccept");


		System.out.println(test("22", null, null));
		System.out.println(test("22", null, "2"));
		System.out.println(test("22", "2", null));
		System.out.println(test("22", "2", "2"));

		System.out.println(test("22", null, null));
		System.out.println(test("22", null, "1"));
		System.out.println(test("22", "1", null));
		System.out.println(test("22", "1", "1"));


		System.out.println(test("23", null, null));
		System.out.println(test("23", null, "2"));
		System.out.println(test("23", "2", null));
		System.out.println(test("23", "2", "2"));

		System.out.println(test("23", null, null));
		System.out.println(test("23", null, "1"));
		System.out.println(test("23", "1", null));
		System.out.println(test("23", "1", "1"));


		System.out.println(test(null, null, null));
		System.out.println(test("2", "1", null));
		System.out.println(test("2", "1", "1"));
		System.out.println(test("2", "1", "2"));

		System.out.println(test("1", null, null));
		System.out.println(test("1", "1", null));
		System.out.println(test("1", "1", "1"));
		System.out.println(test("1", "1", "2"));

		System.out.println(test("2", null, null));
		System.out.println(test(null, "1", null));
		System.out.println(test(null, "1", "1"));
		System.out.println(test(null, "1", "2"));




	}


	static boolean test(String transType, String paymentType, String orderStatus) {
		System.out.print(transType+","+paymentType+","+orderStatus+",");
		boolean status = true;

		if(transType != null && transType.equals("22") 
				&& paymentType != null && paymentType.equals("2")
				&& orderStatus != null && orderStatus.equals("2")) {
			//success
			status = true;
		}
		if(transType != null && transType.equals("22") 
				&& paymentType != null && (paymentType.equals("1") || paymentType.equals("2"))
				&& orderStatus != null && (orderStatus.equals("1") || orderStatus.equals("2")) ) {
			//fail
			status = false;
		}
		if(transType != null && transType.equals("23") 
				&& paymentType != null && paymentType.equals("1")
				&& orderStatus != null && orderStatus.equals("1")) {
			//success
			status = true;
		}
		if(transType != null && transType.equals("23") 
				&& paymentType != null && (paymentType.equals("1") || paymentType.equals("2"))
				&& orderStatus != null && (orderStatus.equals("1") || orderStatus.equals("2")) ) {
			//fail
			status = false;
		}
		if(transType != null && (transType.equals("22") || transType.equals("23"))
				&& (paymentType == null || orderStatus == null)) {
			//fail
			status = false;
		}

		return status;
	}


	public static void interfaceModule_uploadFiles_validation(String remoteFile, String remoteFileFormat, String fileName) 
	{
		BasicDBObject attrData = new BasicDBObject();
		BasicDBObject interfaceObj = new BasicDBObject();
		boolean isInValidfile = false;

		attrData.put("Remote File", remoteFile);
		attrData.put("Remote FileName Format", remoteFileFormat);

		interfaceObj.put("file_name", fileName);

		if(interfaceObj.get("file_name") != null && !interfaceObj.getString("file_name").isEmpty()
				&& attrData.get("Remote File") != null
				&& !attrData.getString("Remote File").isEmpty() 
				&& attrData.get("Remote FileName Format") != null
				&& !attrData.getString("Remote FileName Format").isEmpty()){

			String temp = null;
			SimpleDateFormat simpleDateFormat = null;
			try 
			{
				simpleDateFormat = new SimpleDateFormat(attrData.getString("Remote FileName Format"));
				temp = interfaceObj.get("file_name").toString().replaceFirst(attrData.getString("Remote File") + "_", "").split("_", -1)[0];
				simpleDateFormat.parse(temp);
			} 
			catch (Exception e) 
			{
				System.err.println("Skipping file  fileName :: " + interfaceObj.get("file_name").toString() + "     parse name :: " + temp + e);
				isInValidfile = true;
			}
			finally
			{
				simpleDateFormat = null;
				temp  = null;
			}				
		}
		else
		{
			isInValidfile = true;
		}

		if(isInValidfile)
			System.out.println("File is in-valid");
		else
			System.out.println("File is valid");
	}


}
