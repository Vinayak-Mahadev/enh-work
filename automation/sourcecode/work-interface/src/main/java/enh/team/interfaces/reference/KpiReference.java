package enh.team.interfaces.reference;

import java.sql.Connection;
import java.util.List;

import org.json.JSONObject;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

import enh.team.interfaces.file.FileOperation;
import enh.team.interfaces.rdbms.RDBMSOperation;
import enh.team.interfaces.transformation.TransformationOperation;

public class KpiReference 
{
	public static void main(String[] args) throws Exception
	{
		 
		 
		RDBMSOperation opr = new RDBMSOperation();
		Connection conn = null;
		conn = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);
		FileOperation fileOperation = new FileOperation();
		TransformationOperation transformation = new TransformationOperation();

		String sql = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf'  order by inter.interface_id_n ;";
		
		//opr.printFieldLookupConf(conn, sql, "all", "1165", true);
		
		String kpi_interfaces_dtls = "E:\\interface\\work\\enh-work\\daily_works\\2020-09\\output\\kpi_interfaces_dtls.csv";
		String analytics_table_size = "E:\\interface\\work\\enh-work\\daily_works\\2020-09\\analytics_table_size.csv";
		String analytics_table_size_for_interface = "E:\\interface\\work\\enh-work\\daily_works\\2020-09\\output\\analytics_table_size_for_interface.csv";
		String interface_related_tables_list = "E:\\interface\\work\\enh-work\\daily_works\\2020-09\\output\\interface_related_tables_list.txt";
		List<String> analytics_table_names = null;
		JSONObject jsonObject = null;
		
		//analytics_table_names = fileOperation.readCSVDataByColumn(analytics_table_size, "\\|", 1, true);
		//System.out.println(analytics_table_names);
		
		jsonObject = opr.getTableDtlsForFieldLookupConf(conn, sql);
		conn.close();

		opr.printFieldLookupConfWithoutQuery(false, jsonObject, 1001, 1200, true, kpi_interfaces_dtls, "ID	NAME	TYPE	TEMPORARY_TABLE	VALIDATION_TABLE	DAILY_TABLE	MONTHLY_TABLE	FAILURE_TABLE");
		analytics_table_names = opr.getAllTablesFromKPI(jsonObject);
		
		fileOperation.writeData(interface_related_tables_list, analytics_table_names, true, true);
		fileOperation.writeFile1DataLine_contains_in_File2DataLine_then_generate_File3(analytics_table_size, interface_related_tables_list, analytics_table_size_for_interface, true, true, " ", "");
		
		
		
		JSONObject transJsonObj = null;
		double totalmb = 0;
		double totalgb = 0;
		double totalbytes = 0;
		
		transJsonObj = transformation.loadFromCSVFile(analytics_table_size_for_interface, true, "\\|", 4);
		
		analytics_table_size = "E:\\interface\\work\\enh-work\\daily_works\\2020-09\\analytics_table_size_03_09_2020.csv";
		transJsonObj = transformation.loadFromCSVFile(analytics_table_size, true, "\\|", 4);
		transJsonObj = transformation.dataConversionBytesMbKbGBTB(transJsonObj);
		
		System.out.println(transJsonObj);
		
		for (int i = 1; i<= transJsonObj.keySet().size(); i++) {
			if(transJsonObj.getJSONObject(i+"") == null) continue;
			totalmb = totalmb + transJsonObj.getJSONObject(i+"").getFloat("mb");
			totalgb = totalgb + transJsonObj.getJSONObject(i+"").getFloat("gb");
			totalbytes = totalbytes + transJsonObj.getJSONObject(i+"").getFloat("bytes");
			System.out.println(transJsonObj.getJSONObject(i+"").getFloat("mb"));
//			System.out.println(transJsonObj.getJSONObject(i+"").getFloat("bytes"));
		}		
		
		System.out.println("\nTotal");
		System.out.println("bytes :  " + totalbytes);
		System.out.println("mb    :  " + totalmb);
		System.out.println("gb    :  " + totalgb);
		


		
	}
}
