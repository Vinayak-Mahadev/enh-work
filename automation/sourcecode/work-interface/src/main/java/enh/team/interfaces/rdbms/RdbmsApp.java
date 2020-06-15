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
		Connection conn = RDBMS.getDBConnection(PropType.RDBMS_144);

		opr.prepareFileFor1165(conn, "20200614", "E:/interface/backend/ControlFileGeneration/site_mapping_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1166(conn, "20200614", "E:/interface/backend/ControlFileGeneration/primary_mobo_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1167(conn, "20200614", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1168(conn, "20200614", "E:/interface/backend/ControlFileGeneration/daily_sso_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1169(conn, "20200614", "E:/interface/backend/ControlFileGeneration/rgu_injection_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1170(conn, "20200614", "E:/interface/backend/ControlFileGeneration/tertiary_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1171(conn, "20200614", "E:/interface/backend/ControlFileGeneration/org_close_bal_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1172(conn, "20200614", "E:/interface/backend/ControlFileGeneration/total_revenue_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1173(conn, "20200614", "E:/interface/backend/ControlFileGeneration/mobo_revenue_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1174(conn, "20200614", "E:/interface/backend/ControlFileGeneration/acquisition_revenue_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1175(conn, "20200614", "E:/interface/backend/ControlFileGeneration/low_revenue_site_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1176(conn, "20200614", "E:/interface/backend/ControlFileGeneration/site_rgu_ga_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1177(conn, "20200614", "E:/interface/backend/ControlFileGeneration/cross_reload_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1178(conn, "20200614", "E:/interface/backend/ControlFileGeneration/cross_data_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1179(conn, "202006", "E:/interface/backend/ControlFileGeneration/outlet_program_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1180(conn, "202006", "E:/interface/backend/ControlFileGeneration/alloc_payment_20200614090000.csv", 1000, 1);
		opr.prepareFileFor1181(conn, "20200614", "E:/interface/backend/ControlFileGeneration/uro_20_20200614090000.csv", 1000, 1);

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
