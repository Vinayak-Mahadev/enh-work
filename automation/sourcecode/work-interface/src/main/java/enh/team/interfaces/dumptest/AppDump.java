package enh.team.interfaces.dumptest;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

import enh.team.interfaces.dumptest.util.FilePropertiesBean;

public class AppDump {

	public static void main(String[] args) throws Exception {


		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		long interfaceId = 1156l;
		FilePropertiesBean filePropertiesBean = new FilePropertiesBean();
		filePropertiesBean.setFileHeaders("Date|Region|HOR Name|Area|HOS Name|Sales Area|SAM Name|CSM Name|CSO Name|Outlet Ref Id|Outlet name|Contact number|Mobo number|Starter pack Count|Voucher Count|MOBO Sellin Value (Using MOBII)|ACT 1 MSISDN Count|GA-FR MSISDN Count|RGU-GA MSISDN Count|MOBO Sellout value|VC Redeem value");
		filePropertiesBean.setProcessName("E:\\interface\\backend\\indo-d\\snd-integration\\conf\\dump-data-templates\\Outlet_Sales_Dump.json");
		filePropertiesBean.setFileName("E:\\interface\\test\\dumpTest\\Outlet_Sales_"+format.format(new Date())+".");
		filePropertiesBean.setRemoteFileFormat("csv");
		filePropertiesBean.setCsvDelimeter("|");


		Connection 	connection = RDBMS.getDBConnection(PropType.RDBMS_251);

		OutletSalesDump.processRequest(interfaceId, filePropertiesBean, connection);

	
	}
}
