package enh.team.interfaces.util;

import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {

		String line = "dealer123|20200420|294843|1000001|100";


		String fieldConf = "MPC_CODE;M;R;[a-zA-Z0-9]+%DATE;M;RD;[0-9]{8};yyyyMMdd%ORGANIZATION_ID;M;R;[0-9]+%DEALER_MSISDN;M;R;[0-9]+%AMOUNT;M;R;^-?([0-9]\\d*(\\.\\d+)?)$";

		List<String> validationConfList = Arrays.asList(fieldConf.split("%"));

		List<String> dataList = Arrays.asList(line.split("\\|"));;

		Utility utility = new Utility();



		System.out.println(utility.basicValidation(validationConfList, dataList, line));
	}

}
