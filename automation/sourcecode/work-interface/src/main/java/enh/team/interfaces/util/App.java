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
		fieldConf = "DATE;M;DNF;[0-9]*;yyyyMMdd%SITE_ID;M;R;[a-zA-Z0-9 -]*$+%LONGITUDE;M;R;^-?\\d*\\.\\d{1,15}+$%LATITUDE;M;R;^-?\\d*\\.\\d{1,15}+$%MICRO_CLUSTER;M;R;[a-zA-Z0-9 -]*$+%SALES_CLUSTER;M;R;[a-zA-Z0-9 -]*$+%SALES_AREA;M;R;[a-zA-Z0-9 -]*$+%AREA;M;R;[a-zA-Z0-9 -]*$+%REGION;M;R;[a-zA-Z0-9 -]*$+%JAVA_NONJAVA;M;R;[a-zA-Z0-9 _-]*$+%SITE_NAME;M;R;[a-zA-Z0-9 _-]*$+%SITE_POPULATION;M;R;[0-9]*";
		validationConfList = Arrays.asList(fieldConf.split("%"));

		
//1
		line = "20200506|01COW015|-106.78437|-6.2817899999999999|MC-KEBAYORAN|JB-JAK-JAKARTA SELATAN|SOUTH JAKARTA|JAKARTA|Jabotabek|JAVA|M_LAP_TNS_PI|12345";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//2
		line = "20200506|01JKB006|106.76925|-6.197|MC-KEBON JERUK-KEMBANGAN|JB-JAK-JAKARTA BARAT|WEST JAKARTA|JAKARTA|Jabotabek|JAVA|POLIYAMA|76543";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//3
		line = "20200506|01JKB006|106.1119999999999999999|-6.197|MC-KEBON JERUK-KEMBANGAN|JB-JAK-JAKARTA BARAT|WEST JAKARTA|JAKARTA|Jabotabek|JAVA|POLIYAMA|76543";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//4
		line = "20200506|01JKB006|106.7635355|-105.19755455|MC-KEBON JERUK-KEMBANGAN|JB-JAK-JAKARTA BARAT|WEST JAKARTA|JAKARTA|Jabotabek|JAVA|POLIYAMA|76543";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));



	}

}
