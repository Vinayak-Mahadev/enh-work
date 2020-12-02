package enh.team.interfaces.util;

import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {

		/*
		List<String> dataList = null;
		List<String> validationConfList = null;
		String line = null;
		String fieldConf = null;



		//fieldConf = "DATE;M;DNF;[0-9]*;yyyyMMdd%SITE_ID;M;R;[a-zA-Z0-9 -]*$+%LONGITUDE;M;R;^\\d+(\\.\\d{1,15})?$%LATITUDE;M;R;^\\d+(\\.\\d{1,15})?$%MICRO_CLUSTER;M;R;[a-zA-Z0-9 -]*$+%SALES_CLUSTER;M;R;[a-zA-Z0-9 -]*$+%SALES_AREA;M;R;[a-zA-Z0-9 -]*$+%AREA;M;R;[a-zA-Z0-9 -]*$+%REGION;M;R;[a-zA-Z0-9 -]*$+%JAVA_NONJAVA;M;R;[a-zA-Z0-9 _-]*$+%SITE_NAME;M;R;[a-zA-Z0-9 _-]*$+%SITE_POPULATION;M;R;[0-9]*";
		fieldConf = "SERIAL_NUMBER_SIM;M;R;[0-1]+|NEW_PRODUCT_CODE;M|NEW_EXPIRY_DATE;M;RD;[0-9]{8};yyyyMMdd";
		validationConfList = Arrays.asList(fieldConf.split("\\|"));

		
//1
		line = "1|SISP|20200805";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(Utility.basicValidation(validationConfList, dataList, line));
//2
		line = "20200510|CLUSTER-TEST-MC DS|Test Site-2|Non Java|2.30|2.70";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(Utility.basicValidation(validationConfList, dataList, line));
//3
		line = "20200510|CW-CJA-BANGUNYO-MC DS|Test Site-3|Non Java|3.30|3.70";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(Utility.basicValidation(validationConfList, dataList, line));
//4
		line = "20200510|CW-CJA-SEMARIN-MC DS|Test Site-9|Non Java|90000.30|-1119.70";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(Utility.basicValidation(validationConfList, dataList, line));

*/
		String str1 ="MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION";
		String str2 ="MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION";
		if (!str1.trim().equalsIgnoreCase(str2.trim()))
		{
			System.out.println(str1);
			System.out.println(str2);
		}
		else
		{
			System.out.println("same");
		}

	}

}
