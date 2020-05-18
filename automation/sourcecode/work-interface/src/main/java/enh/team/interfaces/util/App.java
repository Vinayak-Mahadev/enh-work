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

		fieldConf = "MPC_CODE;M;R;[a-zA-Z0-9_ ]*$+%DATE;M;D;yyyy-MM-dd%ORGANIZATION_ID;M;R;[a-zA-Z0-9_ ]*$+%DEALER_MSISDN;N;R;[0-9]+%AMOUNT;M;R;^-?([0-9]\\d*(\\.\\d+)?)$";
		validationConfList = Arrays.asList(fieldConf.split("%"));

		
//1
		line = "dealer123|2020-04-20|ref-ref|1000001|100";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//2
		line = "dealer123|2020-04-20|ref-ref|1000001|100.000";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//3
		line = "dealer123|2020-04-20|ref ref|1000001|100";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));
//4
		line = "dealer123|2020-04- 20|ref-ref|1000001|100";
		dataList = Arrays.asList(line.split("\\|"));;
		System.out.println(utility.basicValidation(validationConfList, dataList, line));



	}

}
