package enh.team.interfaces.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

			String data = "1001,1003,1005,1009,1011,1013,1015,1017,1007,1024,1025,1027,1029,1018,1019,1021,1020,1022,1023,1030,1031,1040,1045,1050,1049,1051,1053,1061,1062,1064,1065,1066,1067,1068,1069,1070,1071,1072,1075,1076,1077,1078,1079,1080,1081,1082,1090,1092,1136,1138,1143,1145,1146,1148,1152,1156,1157,1158,1159,1161,1162,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176,1177,1178,1179,1180,1181,1182";

			String flag = "1066,1067,1068,1069,1070,1071,1076,1077,1078,1079,1080,1081";

			List<String> dataList = Arrays.asList(data.split(","));
			System.out.println("dataList :: " + dataList);
			System.out.println("dataList size :: " + dataList.size());

			System.out.println(flag.contains("1066"));
			for (String sid : dataList) 
			{
				if(!flag.contains(sid))
					System.out.print(sid + ",");	
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
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

}
