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
		
		
		opr.prepareFileFor1153(conn, "2020-02-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200210_1001.csv", 0);
		opr.prepareFileFor1165(conn, "2020-02-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200210_1001.csv", 0);
		opr.prepareFileFor1166(conn, "2020-02-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200210_1001.csv", 0);
	
		opr.prepareFileFor1153(conn, "2020-03-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200310_1001.csv", 0);
		opr.prepareFileFor1165(conn, "2020-03-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200310_1001.csv", 0);
		opr.prepareFileFor1166(conn, "2020-03-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200310_1001.csv", 0);
	
		opr.prepareFileFor1153(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200410_1001.csv", 0);
		opr.prepareFileFor1165(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200410_1001.csv", 0);
		opr.prepareFileFor1166(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200410_1001.csv", 0);
	
		opr.prepareFileFor1153(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200410_1002.csv", 0);
		opr.prepareFileFor1165(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200410_1002.csv", 0);
		opr.prepareFileFor1166(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200410_1002.csv", 0);
	
		opr.prepareFileFor1153(conn, "2020-05-02", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200502_1003.csv", 0);
		opr.prepareFileFor1165(conn, "2020-05-02", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200502_1003.csv", 0);
		opr.prepareFileFor1166(conn, "2020-05-02", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200502_1003.csv", 0);
	
		opr.prepareFileFor1153(conn, "2020-05-05", "E:/interface/backend/ControlFileGeneration/secondary_mobo_bi_20200505_1004.csv", 0);
		opr.prepareFileFor1165(conn, "2020-05-05", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200505_1004.csv", 0);
		opr.prepareFileFor1166(conn, "2020-05-05", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200505_1004.csv", 0);
	
		
		
	}
}
