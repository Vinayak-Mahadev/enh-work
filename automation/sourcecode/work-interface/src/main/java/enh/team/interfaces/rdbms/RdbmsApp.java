package enh.team.interfaces.rdbms;

import java.sql.Connection;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

public class RdbmsApp 
{
	public static void main(String[] args) throws Exception
	{
		//new RDBMSOperation().getDatabaseMetaData(RDBMS.getDBConnection(PropType.RDBMS_144));
		
		RDBMSOperation opr = new RDBMSOperation();
		Connection conn = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);

		opr.prepareFileFor1165(conn, "20200612", "D:/TEMP/site_mapping_20200612090000.csv", 10, 3);
		opr.prepareFileFor1166(conn, "20200612", "D:/TEMP/primary_mobo_20200612090000.csv", 10, 1);
		opr.prepareFileFor1167(conn, "20200612", "D:/TEMP/secondary_mobo_20200612090000.csv", 10, 1);
		opr.prepareFileFor1168(conn, "20200612", "D:/TEMP/daily_sso_20200612090000.csv", 10, 1);
		opr.prepareFileFor1169(conn, "20200612", "D:/TEMP/rgu_injection_20200612090000.csv", 10, 1);
		opr.prepareFileFor1170(conn, "20200612", "D:/TEMP/tertiary_20200612090000.csv", 10, 1);
		opr.prepareFileFor1171(conn, "20200612", "D:/TEMP/org_bal_20200612090000.csv", 10, 1);
		opr.prepareFileFor1172(conn, "20200612", "D:/TEMP/total_revenue_20200612090000.csv", 10, 1);
		opr.prepareFileFor1173_1174(conn, "20200612", "D:/TEMP/mobo_revenue_20200612090000.csv", 10, 1);
		opr.prepareFileFor1173_1174(conn, "20200612", "D:/TEMP/total_rgu_ga_20200612090000.csv",10, 1);
		opr.prepareFileFor1175(conn, "20200612", "D:/TEMP/low_revenue_site_20200612090000.csv", 10, 1);
		opr.prepareFileFor1176(conn, "20200612", "D:/TEMP/site_rgu_ga_20200612090000.csv", 10, 1);
		opr.prepareFileFor1177(conn, "20200612", "D:/TEMP/cross_reload_20200612090000.csv", 10, 1);
		opr.prepareFileFor1178(conn, "20200612", "D:/TEMP/cross_data_20200612090000.csv", 10, 1);
		opr.prepareFileFor1179(conn, "202006", "D:/TEMP/outlet_program_achiever_20200612090000.csv", 10, 1);
		opr.prepareFileFor1180(conn, "202006", "D:/TEMP/alloc_payment_20200612090000.csv", 10, 1);
		opr.prepareFileFor1181(conn, "20200612", "D:/TEMP/uro_20k_20200612090000.csv", 10, 1);

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
		
		
		
		
		String sql = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n between 1166 and 1181 order by inter.interface_id_n ;";
		
		opr.printFieldLookupConf(conn, sql);
		
		
		//opr.writeFileWithKpiDailyAndMonthlyData(conn, "daily", "kpi.tr_daily_primary_mobo_aggr",    "E:\\interface\\work\\enh-work\\daily_works\\2020-06\\backup_hadoop_feeds\\1166_DailyFile_001.csv");
		//opr.writeFileWithKpiDailyAndMonthlyData(conn, "monthly", "kpi.tr_monthly_primary_mobo_aggr",  "E:\\interface\\work\\enh-work\\daily_works\\2020-06\\backup_hadoop_feeds\\1166_MonthlyFile_001.csv");
		
	}
}
