package enh.team.interfaces.util;

import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {

		List<String> dataList = null;
		List<String> validationConfList = null;
		String line = null;
		String fieldConf = null;


		Utility utility = new Utility();

		//fieldConf = "DATE;M;DNF;[0-9]*;yyyyMMdd%SITE_ID;M;R;[a-zA-Z0-9 -]*$+%LONGITUDE;M;R;^\\d+(\\.\\d{1,15})?$%LATITUDE;M;R;^\\d+(\\.\\d{1,15})?$%MICRO_CLUSTER;M;R;[a-zA-Z0-9 -]*$+%SALES_CLUSTER;M;R;[a-zA-Z0-9 -]*$+%SALES_AREA;M;R;[a-zA-Z0-9 -]*$+%AREA;M;R;[a-zA-Z0-9 -]*$+%REGION;M;R;[a-zA-Z0-9 -]*$+%JAVA_NONJAVA;M;R;[a-zA-Z0-9 _-]*$+%SITE_NAME;M;R;[a-zA-Z0-9 _-]*$+%SITE_POPULATION;M;R;[0-9]*";
		fieldConf = "DATE;M;RD;[0-9]*;yyyyMMdd%MICRO;M;R;%SITE_ID;M;R;%QTY;M;R;[0-9]*";
		validationConfList = Arrays.asList(fieldConf.split("%"));

		
//1
		line = "20200611|CW-CJA-TELANGBES-MC DS|Test Site-14|14";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//2
		line = "20200611|BANDUNG KOTA - OUTER|Non Java|Outer|19.45|19.0";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//3
		line = "20200611|CLUSTER-TEST|Java|Outer|15.45|15.0";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//4
		line = "20200611|CW-CJA-KUDEPARA|Non Java|Outer|15.45|15.0";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));



	}

}
