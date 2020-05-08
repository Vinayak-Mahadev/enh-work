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
		
		
		//opr.prepareFileFor1153(conn, "2020-01-11", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200210_2001.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-01-11", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200210_2001.csv", 0);
		opr.prepareFileFor1166(conn, "2020-01-11", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200210_2001.csv", 0);
	
		//opr.prepareFileFor1153(conn, "2020-03-15", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200310_2001.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-03-15", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200310_2001.csv", 0);
		opr.prepareFileFor1166(conn, "2020-03-15", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200310_2001.csv", 0);
	
		//opr.prepareFileFor1153(conn, "2020-04-13", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200410_2001.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-04-13", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200410_2001.csv", 0);
		opr.prepareFileFor1166(conn, "2020-04-13", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200410_2001.csv", 0);
	
		//opr.prepareFileFor1153(conn, "2020-04-20", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200410_2002.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-04-20", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200410_2002.csv", 0);
		opr.prepareFileFor1166(conn, "2020-04-20", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200410_2002.csv", 0);
	
		//opr.prepareFileFor1153(conn, "2020-05-01", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200502_2003.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-05-01", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200502_2003.csv", 0);
		opr.prepareFileFor1166(conn, "2020-05-01", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200502_2003.csv", 0);
	
		//opr.prepareFileFor1153(conn, "2020-05-06", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200505_2004.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-05-06", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200505_2004.csv", 0);
		opr.prepareFileFor1166(conn, "2020-05-06", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200505_2004.csv", 0);
	
		//opr.prepareFileFor1153(conn, "2020-05-07", "D:/BIGGGGGGGGGGGGGGG/ALL/secondary_mobo_bi_20200505_2005.csv", 0);
		//opr.prepareFileFor1165(conn, "2020-05-07", "D:/BIGGGGGGGGGGGGGGG/ALL/mobii_rgu_ga_injection_bi_20200505_2005.csv", 0);
		opr.prepareFileFor1166(conn, "2020-05-07", "D:/BIGGGGGGGGGGGGGGG/temp/mobii_daily_sso_bi_20200505_2005.csv", 0);
		
		
	}
}
