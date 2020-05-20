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
		
		
//		opr.prepareFileFor1153(conn, "2020-01-11", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200111_2001.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-01-11", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200111_2001.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-01-11", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200111_2001.csv", 1000);
//	
//		opr.prepareFileFor1153(conn, "2020-03-15", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200315_2001.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-03-15", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200315_2001.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-03-15", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200315_2001.csv", 1000);
//	
//		opr.prepareFileFor1153(conn, "2020-04-13", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200413_2001.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-04-13", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200413_2001.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-04-13", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200413_2001.csv", 1000);
//	
//		opr.prepareFileFor1153(conn, "2020-04-20", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200420_2001.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-04-20", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200420_2001.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-04-20", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200420_2001.csv", 1000);
//
//		opr.prepareFileFor1153(conn, "2020-05-01", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200501_2001.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-05-01", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200501_2001.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-05-01", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200501_2001.csv", 1000);
//
//		opr.prepareFileFor1153(conn, "2020-05-02", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200502_2002.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-05-02", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200502_2002.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-05-02", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200502_2002.csv", 1000);
//
//		opr.prepareFileFor1153(conn, "2020-05-03", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200503_2003.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-05-03", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200503_2003.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-05-03", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200503_2003.csv", 1000);
//	
//		opr.prepareFileFor1153(conn, "2020-05-06", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200506_2004.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-05-06", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200506_2004.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-05-06", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200506_2004.csv", 1000);
//
//		opr.prepareFileFor1153(conn, "2020-05-07", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200505_2005.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-05-07", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200505_2005.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-05-07", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200505_2005.csv", 1000);
//		
//
//		opr.prepareFileFor1153(conn, "2020-05-08", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200508_2006.csv", 1000);
//		opr.prepareFileFor1165(conn, "2020-05-08", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200508_2006.csv", 1000);
//		opr.prepareFileFor1166(conn, "2020-05-08", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200508_2006.csv", 1000);
		
		//opr.prepareFileFor1153(conn, "2020-04-13", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200413_001.csv", 1000);
		//opr.prepareFileFor1153(conn, "2020-05-13", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200513_001.csv", 1000);
		
		//opr.prepareFileForSiteMapping(conn, "2020-05-20", "D:/TEMP/site_mapping_bi_20200520101010.csv", 1, 110000);
		
		//Territory Validation
		String line = "20200520|TestID2|19.10|31.23|micro110|1007034|60|13|REG5|F1|G1|15";
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
		System.out.println(responceObj);
	}
}
