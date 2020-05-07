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
		
		
		opr.prepareFileFor1153(conn, "2020-02-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200210_001.csv");
		opr.prepareFileFor1165(conn, "2020-02-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200210_001.csv");
		opr.prepareFileFor1166(conn, "2020-02-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200210_001.csv");
	
		opr.prepareFileFor1153(conn, "2020-03-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200310_001.csv");
		opr.prepareFileFor1165(conn, "2020-03-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200310_001.csv");
		opr.prepareFileFor1166(conn, "2020-03-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200310_001.csv");
	
		opr.prepareFileFor1153(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200410_001.csv");
		opr.prepareFileFor1165(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200410_001.csv");
		opr.prepareFileFor1166(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200410_001.csv");
	
		opr.prepareFileFor1153(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200410_002.csv");
		opr.prepareFileFor1165(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200410_002.csv");
		opr.prepareFileFor1166(conn, "2020-04-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200410_002.csv");
	
		opr.prepareFileFor1153(conn, "2020-05-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200510_001.csv");
		opr.prepareFileFor1165(conn, "2020-05-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200510_001.csv");
		opr.prepareFileFor1166(conn, "2020-05-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200510_001.csv");
	
		opr.prepareFileFor1153(conn, "2020-05-10", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200510_002.csv");
		opr.prepareFileFor1165(conn, "2020-05-10", "E:/interface/backend/ControlFileGeneration/mobii_rgu_ga_injection_bi_20200510_002.csv");
		opr.prepareFileFor1166(conn, "2020-05-10", "E:/interface/backend/ControlFileGeneration/mobii_daily_sso_bi_20200510_002.csv");
	
	}
}
