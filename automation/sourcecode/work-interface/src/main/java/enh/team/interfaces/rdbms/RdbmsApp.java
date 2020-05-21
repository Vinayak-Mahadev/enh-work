package enh.team.interfaces.rdbms;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

public class RdbmsApp 
{
	public static void main(String[] args) throws Exception
	{
		//new RDBMSOperation().getDatabaseMetaData(RDBMS.getDBConnection(PropType.RDBMS_144));
		
		RDBMSOperation opr = new RDBMSOperation();
		Connection conn = RDBMS.getDBConnection(PropType.RDBMS_144);
						
		opr.prepareFileFor1165(conn, "20200521", "D:/TEMP/site_mapping_bi_2020052121212.csv", 1000);
		opr.prepareFileFor1166(conn, "20200521", "D:/TEMP/primary_mobo_bi_2020052121212.csv", 10000);
		opr.prepareFileFor1167(conn, "20200521", "D:/TEMP/secondary_mobo_bi_2020052121212.csv", 10000);
		opr.prepareFileFor1168(conn, "20200521", "D:/TEMP/daily_sso_bi_2020052121212.csv", 10000);
		opr.prepareFileFor1169(conn, "20200521", "D:/TEMP/rgu_ga_injection_bi_2020052121212.csv", 10000);
		opr.prepareFileFor1170(conn, "20200521", "D:/TEMP/tertiary_sales_bi_2020052121212.csv", 10000);
		opr.prepareFileFor1171(conn, "20200521", "D:/TEMP/organization_closing_balance_bi_2020052121212.csv", 10000);
		
		//Territory Validation
		/*String line = "20200520|TestID2|19.10|31.23|micro110|1007034|60|13|REG5|F1|G1|15";
		List<String>dataList = Arrays.asList(line.split("\\|", -1));		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("HEADERS", "DATE|SITE_ID|LONGITUDE|LATITUDE|MICRO_CLUSTER|SALES_CLUSTER|SALES_AREA|AREA|REGION|JAVA_NONJAVA|SITE_NAME|SITE_POPULATION");
		jsonObject.put("CSV_DELIMITER", "\\|");
		jsonObject.put("MICRO_CLUSTER", dataList.get(4));
		jsonObject.put("SALES_CLUSTER", dataList.get(5));
		jsonObject.put("SALES_AREA", dataList.get(6));
		jsonObject.put("AREA", dataList.get(7));
		jsonObject.put("REGION", dataList.get(8));		
		JSONObject responceObj = new JSONObject();		
		responceObj = opr.validateSaleTerritoryObj(conn, jsonObject);
		System.out.println(responceObj);*/
	}
}
