package enh.team.interfaces.rdbms;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RDBMSOperation {

	public void getDatabaseMetaData(Connection conn)
	{
		try {

			DatabaseMetaData dbmd = conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			while (rs.next()) {
				System.out.println(rs.getString("TABLE_SCHEM")+"."+rs.getString("TABLE_NAME"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void prepareFileFor1153(Connection conn, String dateInFile, String filePath, int limit)
	{
		ResultSet org = null;
		ResultSet mpc = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MPC_CODE|DATE|ORGANIZATION_ID|DEALER_MSISDN|AMOUNT\n".getBytes());
			org = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6 order by 1");
			mpc = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 7 order by 1");

			int i = 1;

			ArrayList<String> mpcList = new ArrayList<String>();
			while (mpc.next()) {
				mpcList.add(mpc.getString(1));
			}

			out : while (org.next()) {

				for (String mpcRef : mpcList) {
					fos.write((mpcRef+"|"+dateInFile+"|"+org.getString(1)+"|1"+format.format(i)+"|1"+ (format.format(i)+10) +".50\n").getBytes());
					i++;
					if(limit != 0 )
						if( limit==i)
							break out;
				}

				fos.flush();
			}

			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	public void prepareFileFor1165(Connection conn, String dateInFile, String filePath, int limit)
	{
		ResultSet outlet = null;
		ResultSet site = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE ID|ID OUTLET|STATUS INJECTION|QTY\n".getBytes());
			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			site = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master  where lookup_type_n in (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 115 ) and ext_ref_code_v is not null order by 1;");

			int i = 1;

			ArrayList<String> siteList = new ArrayList<String>();
			while (site.next()) {
				siteList.add(site.getString(1));
			}

			out : while (outlet.next()) {

				for (String siteRef : siteList) {
					fos.write((dateInFile+"|"+format.format(i+10)+"|"+siteRef+"|"+outlet.getString(1)+"|yes|"+ (i+10) +"\n").getBytes());
					i++;
					if(limit != 0 )
						if( limit==i)
							break out;
				}
				fos.flush();
			}

			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	public void prepareFileFor1166(Connection conn, String dateInFile, String filePath, int limit)
	{
		ResultSet outlet = null;
		ResultSet site = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT\n".getBytes());

			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			site = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master  where lookup_type_n in (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 115 ) and ext_ref_code_v is not null order by 1;");

			int i = 1;

			ArrayList<String> siteList = new ArrayList<String>();
			while (site.next()) {
				siteList.add(site.getString(1));
			}

			out : while (outlet.next()) {

				for (String siteRef : siteList) {
					fos.write((dateInFile+ "|"+format.format(i+10)+"|"+siteRef+"|"+outlet.getString(1)+"|"+i+"|1"+format.format(i+10)+".05\n").getBytes());
					i++;
					if(limit != 0 )
						if( limit==i)
							break out;
				}
				fos.flush();
			}

			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
