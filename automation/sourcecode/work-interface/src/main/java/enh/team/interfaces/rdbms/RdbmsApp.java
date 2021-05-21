package enh.team.interfaces.rdbms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

public class RdbmsApp 
{
	public static void main(String[] args) throws Exception
	{
		//new RDBMSOperation().getDatabaseMetaData(RDBMS.getDBConnection(PropType.RDBMS_144));
		Connection conn = null;
		conn = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);
		pos_int_1001(conn);
		/*
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


		//		conn = RDBMS.getDBConnection("org.postgresql.Driver", "jdbc:postgresql://52.6.190.165:5432/snoc2", "postgres", "postgres");
		//		String sql = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n between 1001 and 1181 order by inter.interface_id_n ;";

		//		String sql = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf'  order by inter.interface_id_n ;";
		//opr.printFieldLookupConf(conn, sql, "all", "1165", true);
		//		JSONObject jsonObject = opr.getTableDtlsForFieldLookupConf(conn, sql);
		//		opr.printFieldLookupConfWithoutQuery(true, jsonObject, 1001, 1200, false, null, null);
		//		System.out.println(opr.getAllTablesFromKPI(jsonObject));

		//		System.out.println(opr.preareCleanUpWithFieldLookupConf(conn, sql, true));
		conn.close();




		//opr.writeFileWithKpiDailyAndMonthlyData(conn, "daily", "kpi.tr_daily_primary_mobo_aggr",    "E:\\interface\\work\\enh-work\\daily_works\\2020-06\\backup_hadoop_feeds\\1166_DailyFile_001.csv");
		//opr.writeFileWithKpiDailyAndMonthlyData(conn, "monthly", "kpi.tr_monthly_primary_mobo_aggr",  "E:\\interface\\work\\enh-work\\daily_works\\2020-06\\backup_hadoop_feeds\\1166_MonthlyFile_001.csv");

	}

	static void pos_int_1001(Connection connection)
	{
		try 
		{

			PreparedStatement statement = connection.prepareStatement(_INSERT_TEMP_TABLE_);
			List<String> posReqIds = Arrays.asList("abcd1111","abcd2222","abcd3333");
			List<String> foosReqIds = Arrays.asList("FOSS1","FOSS2","FOSS3");
			List<String> materialIds = Arrays.asList("111","222","333");
			System.out.println(posReqIds);
			long count = 0;
			for (String reqId : posReqIds) 
			{
				for (String fossReq : foosReqIds) 
				{
					for (String material : materialIds) 
					{
						for (long i = 11l; i < 21l; i++) 
						{
							for (long j = 555l; j < 666l; j++) 
							{
								statement.setLong(1, 1001l);
								statement.setLong(2, 4l);
								statement.setString(3, reqId+"|"+fossReq+"|"+material+"|"+i+"|"+j);
								statement.setString(4, reqId);
								statement.setString(5, fossReq);
								statement.setString(6, material);
								statement.setString(7, i+"");
								statement.setString(8, j+"");
								statement.setNull(9, Types.NULL);
								statement.setNull(10, Types.NULL);
								count++;
								System.out.println(statement);
								statement.addBatch();

								if(count %100 == 0)
								{
									statement.execute();
									count = 0;
								}
							}
						}
					}	
				}	
			}
			if(count !=0)
				statement.execute();

			statement.close();


		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	private static String _INSERT_TEMP_TABLE_ = "insert into interface.temp_interface_1001(file_id_n,status_n,data_string_v,new_pos_requestid,foss_id,material_code,material,msisdn,error_code_n,error_message_v) values (?,?,?,?,?,?,?,?,?,?)";
	private static String _SELECT_TEMP_TABLE_ = "select temp_id_n,new_pos_requestid,foss_id,material_code,material,msisdn,error_code_n,error_message_v from interface.temp_interface_1001 where file_id_n = ? and status_n = ? order by new_pos_requestid,foss_id,material_code";
	private static String _UPDATE_TEMP_TABLE_ = "update interface.temp_interface_1001 set status_n=?, error_code_n=?, error_message_v=? where temp_id_n=?";

}
