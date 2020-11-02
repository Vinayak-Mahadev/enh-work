package enh.team.interfaces.rdbms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class RDBMSOperation {

	public void getDatabaseMetaData(Connection conn) throws SQLException
	{
		try 
		{
			DatabaseMetaData dbmd = conn.getMetaData();
			String[] types = {"TABLE"};
			ResultSet rs = dbmd.getTables(null, null, "%", types);
			while (rs.next()) {
				System.out.println(rs.getString("TABLE_SCHEM")+"."+rs.getString("TABLE_NAME"));
			}
		} 
		catch (SQLException e) {
			throw e;
		}
	}

	public void prepareFileForInterfaces(List<Long> interfaceIds, Connection conn, String dateInFile, final String filePath, String fileName, int limit, int fileCount) throws Exception
	{
		Map<Long, String> map = null;
		String tempPath = null;
		try 
		{
			map = getInterfaceAttrValue(conn, "Remote File", interfaceIds);

			for (long id : map.keySet()) 
			{
				tempPath = filePath + map.get(id) +"_"+fileName;
				System.out.println("prepareFileForInterface : " + id);
				//System.out.println("filePath : " + filePath);

				if(id == 1165l)
					prepareFileFor1165(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1166l)
					prepareFileFor1166(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1167l)
					prepareFileFor1167(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1168l)
					prepareFileFor1168(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1169l)
					prepareFileFor1169(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1170l)
					prepareFileFor1170(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1171l)
					prepareFileFor1171(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1172l)
					prepareFileFor1172(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1173l)
					prepareFileFor1173(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1174l)
					prepareFileFor1174(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1175l)
					prepareFileFor1175(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1176l)
					prepareFileFor1176(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1177l)
					prepareFileFor1177(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1178l)
					prepareFileFor1178(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1179l)
					prepareFileFor1179(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1180l)
					prepareFileFor1180(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1181l)
					prepareFileFor1181(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1182l)
					prepareFileFor1182(conn, dateInFile, tempPath, limit, fileCount);
				if(id == 1183l)
					prepareFileFor1183(conn, dateInFile, tempPath, limit, fileCount);


			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 


	}

	public void prepareFileFor1165(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		FileOutputStream fos = null;
		try 
		{
			int fileSequence = 1;
			limit = limit * fileCount;
			int calcRowLimit = limit/fileCount; 
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|SITE_ID|LONGITUDE|LATITUDE|MICRO_CLUSTER|SALES_CLUSTER|SALES_AREA|AREA|REGION|JAVA_NONJAVA|SITE_NAME|SITE_POPULATION\n".getBytes());					

			int population = 5;
			int lastFileRowCount = 0;
			for(int i = 1; i <= limit; i++)
			{
				fos.write((dateInFile + "|Test Site "+""+" - "+ i +"|19.10|31.23|MC-KOBAR-LAMANDAU|KS-KAL-WEST KALTENG|SAMPIT|KALIMANTAN|KaliSumapa|NON_JAVA|Test Site-"+ i +"|" + population + "\n").getBytes());
				if(population++ % 100 == 0)
					population = 5;

				if(i % calcRowLimit == 0 && i != limit)
				{
					System.out.println("Total Rows : " + (i/fileSequence));
					fos.close();
					fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
					fileSequence++;
					fos.write("DATE|SITE_ID|LONGITUDE|LATITUDE|MICRO_CLUSTER|SALES_CLUSTER|SALES_AREA|AREA|REGION|JAVA_NONJAVA|SITE_NAME|SITE_POPULATION\n".getBytes());					
				}

				lastFileRowCount = i;
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (lastFileRowCount/fileSequence) + "   1165");
		}
		catch(Exception exception)
		{
			throw exception;
		}
		finally
		{

		}
	}

	public void prepareFileFor1166(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet mpc = null;
		FileOutputStream fos = null;
		//DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MPC_CODE|MOBO_DATE|AMOUNT\n".getBytes());

			limit = limit * fileCount;
			int fileSequence = 1;			
			int calcRowLimit = limit/fileCount; 
			int i = 0;

			out : while(i <= limit)
			{
				mpc = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 7 order by 1");
				while (mpc.next()) 
				{
					if(i%2 == 0)
						fos.write((mpc.getString(1) + "|" + dateInFile + "|" + (i*900356) + ".23\n").getBytes());
					else
						fos.write((mpc.getString(1) + "|" + dateInFile + "|" + (i*90228) + ".59\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("MPC_CODE|MOBO_DATE|AMOUNT\n".getBytes());
						fileSequence++;
					}												

					if( limit==i)
						break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}			

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1166");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1167(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
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

			limit = limit * fileCount;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			int i = 0;

			ArrayList<String> mpcList = new ArrayList<String>();
			while (mpc.next()) {
				mpcList.add(mpc.getString(1));
			}

			out : while (org.next())
			{
				for (String mpcRef : mpcList)
				{
					if(i%2 == 0)
						fos.write((mpcRef+"|"+dateInFile+"|"+org.getString(1)+"|1"+format.format(i)+"|133"+ (format.format(i)+10) +".52\n").getBytes());
					else
						fos.write((mpcRef+"|"+dateInFile+"|"+org.getString(1)+"|1"+format.format(i)+"|233"+ (format.format(i)+10) +".53\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("MPC_CODE|DATE|ORGANIZATION_ID|DEALER_MSISDN|AMOUNT\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 && limit==i)
						break out;
				}

				fos.flush();
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1167");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}	

	public void prepareFileFor1168(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet outlet = null;
		ResultSet micro = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT|QSSO_STATUS\n".getBytes());

			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			int QSSO_STATUS = 0;
			ArrayList<String> microList = new ArrayList<String>();
			while (micro.next()) {
				microList.add(micro.getString(1));
			}

			out : while (outlet.next()) 
			{

				for (String microId : microList) 
				{
					if(i%2 == 0)
					{
						fos.write((dateInFile+ "|"+microId+"|Test Site-"+i+ "|"+outlet.getString(1)+"|"+i+"|8579"+format.format(i+10)+".32|" + QSSO_STATUS + "\n").getBytes());
						QSSO_STATUS = 1;
					}
					else 
					{
						fos.write((dateInFile+ "|"+microId+"|Test Site-"+i+ "|"+outlet.getString(1)+"|"+i+"|1579"+format.format(i+10)+".13|" + QSSO_STATUS + "\n").getBytes());
						QSSO_STATUS = 0;
					}
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 && limit==i)
						break out;
				}
				fos.flush();
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1168");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1169(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet outlet = null;
		ResultSet micro = null;
		ResultSet cluster = null;
		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|CLUSTER|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN\n".getBytes());
			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1 limit 20;");
			cluster = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1 limit 20;");

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			Set<String> microList = new HashSet<String>();
			ArrayList<String> clusterList = new ArrayList<String>();
			while (micro.next()) {
				microList.add(micro.getString(1));
			}

			while (cluster.next()) {
				clusterList.add(cluster.getString(1));
			}

			out : while (outlet.next()) {

				for (String microId : microList) {
					for (String clusterid : clusterList) {

						if(i%2 == 0)
							fos.write((dateInFile+"|"+clusterid+"|"+microId+"|Test Site-"+i+ "|" +outlet.getString(1)+"|WithOut Injection|c. >=7k - <10k|"+ (i+10) +"\n").getBytes());
						else
							fos.write((dateInFile+"|"+clusterid+"|"+microId+"|Test Site-"+i+ "|" +outlet.getString(1)+"|No Injection|c. >=7k - <10k|"+ (i+10) +"\n").getBytes());
						i++;

						if(i % calcRowLimit == 0 && i != limit)
						{
							System.out.println("Total Rows : " + (i/fileSequence));
							fos.close();
							fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
							fos.write("DATE|CLUSTER|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN\n".getBytes());
							fileSequence++;
						}

						if(limit != 0 )
							if( limit==i)
								break out;
					}


				}
				fos.flush();
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1169");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1170(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet micro = null;
		ResultSet out = null;		

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|OUTLET|AMOUNT\n".getBytes());
			micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
			out = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6 order by 1");

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount;

			ArrayList<String> outletList = new ArrayList<String>();
			while (out.next()) 
			{
				outletList.add(out.getString(1));
			}

			out : while (micro.next()) 
			{
				for (String outlet : outletList) 
				{
					if(i%2 == 0)
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+ outlet + "|" + (i*926915) + ".29\n").getBytes());
					else
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+ outlet + "|" + (i*926915) + ".53\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|OUTLET|AMOUNT\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}

				fos.flush();
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1170");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}	

	public void prepareFileFor1171(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet org = null;
		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MOBO_DATE|ORG_CODE|AMOUNT\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				org = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 7 order by 1");
				while (org.next()) 
				{
					if(i%2 == 0)
						fos.write((dateInFile + "|" + org.getString(1) + "|-" + (i*902531) + ".16\n").getBytes());
					else
						fos.write((dateInFile + "|" + org.getString(1) + "|-" + (i*902531) + ".52\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("MOBO_DATE|ORG_CODE|AMOUNT\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 && limit==i)
						break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1171");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1172(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|REVENUE_TYPE|REVENUE_TOTAL\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount;

			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{
					String revenueType = "";
					if(i % 3 == 0)
						revenueType = "voice";
					else
						revenueType = "sms";
					if(i%2 == 0)
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+revenueType+"|" + (i*936791) + ".32\n").getBytes());
					else
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+revenueType+"|" + (i*936791) + ".13\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|REVENUE_TYPE|REVENUE_TOTAL\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1172");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1173(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|REVENUE\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{				
					if(i%2 == 0)
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|" + (i*686131) + ".23\n").getBytes());
					else
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|" + (i*986131) + ".22\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|REVENUE\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1173");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1174(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|REVENUE\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{				
					fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site"+ "-" + (i) +"|" + (i*73951) + ".43\n").getBytes());
					if(i%2 == 0)
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site"+ "-" + (i) +"|" + (i*53187) + ".14\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|REVENUE\n".getBytes());
						fileSequence++;

						if(limit != 0 )
							if( limit==i)
								break out;
					}
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1174");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1175(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|CATEGORY|TARGET|REVENUE\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{
					String category = "";
					if(i % 4 == 0)
						category = "Java";
					else
						category = "Non Java";

					if(i%2 == 0)
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+category+"|" + i*98131 + ".14" + "|" + i*906721 + ".11\n").getBytes());
					else
						fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+category+"|" + i*198131 + ".19" + "|" + i*906721 + ".76\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|CATEGORY|TARGET|REVENUE\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}
			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1175");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1176(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE|QTY\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;		
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{				
					fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|" + i + "\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE|QTY\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}
			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1176");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1177(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet cluster = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|CLUSTER_ID|CATEGORY|CLUSTER_TYPE|TOTAL_RELOAD|CROSS_RELOAD\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;	
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			String category = "";
			String clusterType = "";
			out : while(i <= limit)
			{
				cluster = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");
				while (cluster.next()) 
				{				
					if(i % 4 == 0)
						category = "Java";
					else
						category = "Non Java";
					if(i % 6 == 0)
						clusterType = "Inner";
					else
						clusterType = "Outer";

					fos.write((dateInFile+"|"+cluster.getString(1) + "|"+category+"|" + clusterType + "|" + i + ".0" + "|" + i + ".25\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|CLUSTER_ID|CATEGORY|CLUSTER_TYPE|TOTAL_RELOAD|CROSS_RELOAD\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1177");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1178(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{		
		ResultSet cluster = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|CLUSTER_ID|TOTAL_DATA|CROSS_DATA\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				cluster = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");
				while (cluster.next()) 
				{				
					fos.write((dateInFile+"|"+cluster.getString(1) + "|" + i + ".45|" + i + ".0\n").getBytes());
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|CLUSTER_ID|TOTAL_DATA|CROSS_DATA\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}
				Integer date = Integer.valueOf(dateInFile.substring(dateInFile.length() - 2, dateInFile.length()));

				if(date == 1)
				{
					int month = Integer.valueOf(dateInFile.substring(4, dateInFile.length() - 2));
					if(month == 1)
					{
						int year = Integer.valueOf(dateInFile.substring(0, dateInFile.length() - 4));
						dateInFile = (year - 1) + "1229";
					}
					else if(month<=10)
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + "0" + (month - 1) + "29";
					else
						dateInFile = dateInFile.substring(0, dateInFile.length() - 4) + (month - 1) + "29";
				}
				else if(date<=10)
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + "0" + (date - 1);
				else
					dateInFile = dateInFile.substring(0, dateInFile.length() - 2) + (date - 1);
			}
			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1178");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1179(Connection conn, String monthInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet cluster = null;
		ResultSet out = null;
		monthInFile = monthInFile.substring(0, 6);
		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("00000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MONTH_ID|CLUSTER|ID_OUTLET|TARGET|ACTUAL\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			ArrayList<String> outletList = new ArrayList<String>();
			while (out.next()) 
			{
				outletList.add(out.getString(1));
			}

			out : while(i <= limit)
			{
				cluster = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");				
				while (cluster.next()) 
				{
					for(String outlet : outletList)
					{
						if(i%2 == 0)
							fos.write((monthInFile+"|"+cluster.getString(1)+"|"+outlet+"|17"+format.format(i*1)+".71|13"+ (format.format(i)+10) +".12\n").getBytes());
						else 
							fos.write((monthInFile+"|"+cluster.getString(1)+"|"+outlet+"|49"+format.format(i*1)+".72|41"+ (format.format(i)+10) +".11\n").getBytes());
						i++;

						if(i % calcRowLimit == 0 && i != limit)
						{
							System.out.println("Total Rows : " + (i/fileSequence));
							fos.close();
							fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
							fos.write("MONTH_ID|CLUSTER|ID_OUTLET|TARGET|ACTUAL\n".getBytes());
							fileSequence++;
						}

						if(limit != 0 )
							if( limit==i)
								break out;
					}
				}
				int month = Integer.valueOf(monthInFile.substring(4, monthInFile.length()));
				if(month == 1)
				{
					int year = Integer.valueOf(monthInFile.substring(0, monthInFile.length() - 2));
					monthInFile = (year - 1) + "12";
				}
				else if(month<=10)
					monthInFile = monthInFile.substring(0, monthInFile.length() - 2) + "0" + (month - 1);
				else
					monthInFile = monthInFile.substring(0, monthInFile.length() - 2) + (month - 1);				
			}
			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1179");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}	

	public void prepareFileFor1180(Connection conn, String monthInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet cluster = null;
		ResultSet mpc = null;
		monthInFile = monthInFile.substring(0, 6);
		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION\n".getBytes());			

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 			

			mpc = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 7 order by 1");
			ArrayList<String> mpcList = new ArrayList<String>();
			while (mpc.next()) 
			{
				mpcList.add(mpc.getString(1));
			}

			out : while(i <= limit)
			{
				cluster = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");				
				while (cluster.next()) 
				{
					for(String mpcCode : mpcList)
					{
						fos.write((monthInFile+"|"+cluster.getString(1)+"|"+mpcCode+"|1"+format.format(i) + "\n").getBytes());
						i++;

						if(i % calcRowLimit == 0 && i != limit)
						{
							System.out.println("Total Rows : " + (i/fileSequence));
							fos.close();
							fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
							fos.write("MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION\n".getBytes());
							fileSequence++;
						}

						if(limit != 0 )
							if( limit==i)
								break out;
					}
				}
				int month = Integer.valueOf(monthInFile.substring(4, monthInFile.length()));
				if(month == 1)
				{
					int year = Integer.valueOf(monthInFile.substring(0, monthInFile.length() - 2));
					monthInFile = (year - 1) + "12";
				}
				else if(month<=10)
					monthInFile = monthInFile.substring(0, monthInFile.length() - 2) + "0" + (month - 1);
				else
					monthInFile = monthInFile.substring(0, monthInFile.length() - 2) + (month - 1);
			}
			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1180");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}	

	public void prepareFileFor1181(Connection conn, String monthInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet micro = null;
		ResultSet out = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("00000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT|QURO_STATUS\n".getBytes());
			micro = conn.createStatement().executeQuery("select lookup_name_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
			out = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");

			limit = limit * fileCount;
			int i = 0;			
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			int QURO_STATUS = 1;
			ArrayList<String> outletList = new ArrayList<String>();
			while (out.next()) {
				outletList.add(out.getString(1));
			}

			out : while (micro.next()) {

				for (String outlet : outletList)
				{
					if(i%2 == 0)
					{
						fos.write((monthInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+outlet+"|1"+format.format(i) +"|136"+format.format(i) + ".11|" + QURO_STATUS +"\n").getBytes());
						QURO_STATUS = 1;
					}
						
					else 
					{
						fos.write((monthInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+outlet+"|1"+format.format(i) +"|931"+format.format(i) + ".22|" + QURO_STATUS +"\n").getBytes());
						QURO_STATUS = 0;						
					}
					i++;

					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT\n".getBytes());
						fileSequence++;
					}

					if(limit != 0 )
						if( limit==i)
							break out;
				}

				fos.flush();
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1181");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}	

	public void prepareFileFor1182(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet outlet = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|OUTLET_ID|SP_TAG_QTY\n".getBytes());
			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174;");

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 


			out : while (outlet.next()) {

				fos.write((dateInFile+"|" +outlet.getString(1)+"|"+ (i+10) +"\n").getBytes());
				i++;

				if(i % calcRowLimit == 0 && i != limit)
				{
					System.out.println("Total Rows : " + (i/fileSequence));
					fos.close();
					fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
					fos.write("DATE|OUTLET_ID|SP_TAG_QTY\n".getBytes());
					fileSequence++;
				}

				if(limit != 0 )
					if( limit==i)
						break out;
				fos.flush();
			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1182");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public void prepareFileFor1183(Connection conn, String dateInFile, String filePath, int limit, int fileCount) throws Exception
	{
		ResultSet outlet = null;

		FileOutputStream fos = null;
		Set<String> ouletid = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|OUTLET_ID|SAME_MC_RGU_QTY|SAME_CL_RGU_QTY|ALL_CL_RGU_QTY\n".getBytes());
			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 limit " + limit+";");

			limit = limit * fileCount;
			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			ouletid = new HashSet<String>();
			while (outlet.next())
				ouletid.add(outlet.getString(1));

			out : for (String id : ouletid) 
			{
				fos.write((dateInFile+"|" +id+"|"+ (i+1)+"|"+ (i+10)+"|"+ (i+100) +"\n").getBytes());
				i++;
				if(i % calcRowLimit == 0 && i != limit)
				{
					System.out.println("Total Rows : " + (i/fileSequence));
					fos.close();
					fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
					fos.write("DATE|OUTLET_ID|SAME_MC_RGU_QTY|SAME_CL_RGU_QTY|ALL_CL_RGU_QTY\n".getBytes());
					fileSequence++;
				}

				if(limit != 0 )
					if( limit==i)
						break out;
				fos.flush();

			}

			fos.close();
			System.out.print("File generated       ");
			System.out.println("Total Rows : " + (i/fileSequence) + "   1183");
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public JSONObject validateSaleTerritoryObj(Connection connection, JSONObject inputObject) 
	{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		JSONObject responceObj = null;
		JSONObject territoryObject = null;
		try 
		{
			boolean isMicroclusterPresent = false;
			boolean isClusterPresent = false;
			boolean isSalesAreaPresent = false;
			boolean isArearPresent = false;
			boolean isRegionPresent = false;
			responceObj = new JSONObject();
			responceObj.put("status", "SUCCESS");
			territoryObject = new JSONObject();

			preparedStatement = connection.prepareStatement("select lookup_master.lookup_id_n, lookup_master.lookup_type_n, lookup_master.lookup_name_v, lookup_type_master.ext_lookup_type_n from kpi.ms_lookup_master as lookup_master inner join kpi.ms_lookup_type_master as lookup_type_master on (lookup_master.lookup_name_v in (?, ?, ?, ?, ?) and lookup_type_master.ext_lookup_type_n in (84,85,86,87,88,89) and lookup_type_master.lookup_type_n = lookup_master.lookup_type_n) group by lookup_master.lookup_id_n, lookup_master.lookup_type_n, lookup_master.lookup_name_v, lookup_type_master.ext_lookup_type_n order by lookup_type_master.ext_lookup_type_n desc");
			preparedStatement.setString(1, inputObject.getString("MICRO_CLUSTER"));
			preparedStatement.setString(2, inputObject.getString("SALES_CLUSTER"));
			preparedStatement.setString(3, inputObject.getString("SALES_AREA"));
			preparedStatement.setString(4, inputObject.getString("AREA"));
			preparedStatement.setString(5, inputObject.getString("REGION"));
			resultSet = preparedStatement.executeQuery();	
			int count = 0;
			while(resultSet.next())
			{				
				if(resultSet.getLong("ext_lookup_type_n") == 89)
				{
					territoryObject.put("MICRO_CLUSTER", resultSet.getLong("lookup_id_n"));
					isMicroclusterPresent = true;
				}
				else if(resultSet.getLong("ext_lookup_type_n") == 88)
				{
					territoryObject.put("SALES_CLUSTER", resultSet.getLong("lookup_id_n"));
					isClusterPresent = true;
				}
				else if(resultSet.getLong("ext_lookup_type_n") == 87)
				{
					territoryObject.put("SALES_AREA", resultSet.getLong("lookup_id_n"));
					isSalesAreaPresent = true;
				}
				else if(resultSet.getLong("ext_lookup_type_n") == 86)
				{
					territoryObject.put("AREA", resultSet.getLong("lookup_id_n"));
					isArearPresent = true;
				}
				else if(resultSet.getLong("ext_lookup_type_n") == 85)
				{
					territoryObject.put("REGION", resultSet.getLong("lookup_id_n"));
					isRegionPresent = true;
				}
				count++;
			}			

			if(count != 5)
			{
				String errorMessage = "INVALID TERRITORY ID : ";
				boolean lastCond = false;
				List<String>headers = Arrays.asList(inputObject.getString("HEADERS").split(inputObject.getString("CSV_DELIMITER"), -1));
				if(!isMicroclusterPresent)
				{
					territoryObject.put("MICRO_CLUSTER", "NOT_AVAILABLE");
					errorMessage = errorMessage + headers.get(4) + " = " + inputObject.getString("MICRO_CLUSTER") + ", ";
				}
				if(!isClusterPresent)
				{
					territoryObject.put("SALES_CLUSTER", "NOT_AVAILABLE");
					errorMessage = errorMessage + headers.get(5) + " = " + inputObject.getString("SALES_CLUSTER") + ", ";
				}
				if(!isSalesAreaPresent)
				{
					territoryObject.put("SALES_AREA", "NOT_AVAILABLE");
					errorMessage = errorMessage + headers.get(6) + " = " + inputObject.getString("SALES_AREA") + ", ";
				}
				if(!isArearPresent)
				{
					territoryObject.put("AREA", "NOT_AVAILABLE");
					errorMessage = errorMessage + headers.get(7) + " = " + inputObject.getString("AREA") + ", ";
				}
				if(!isRegionPresent)
				{
					territoryObject.put("REGION", "NOT_AVAILABLE");
					errorMessage = errorMessage + headers.get(8) + " = " + inputObject.getString("REGION");
					lastCond = true;
				}

				if(!lastCond)
					errorMessage = errorMessage.substring(0, errorMessage.length() - 2);

				responceObj.put("error_code", "500002");
				responceObj.put("status", "FAILURE");
				responceObj.put("error_msg", errorMessage);
			}

			responceObj.put("territoryObject", territoryObject);
		} 
		catch (Exception e) 
		{
			responceObj.put("status", "EXCEPTION_OCCURED");	
		}
		finally
		{
			try
			{
				if(preparedStatement != null && !preparedStatement.isClosed())
					preparedStatement.close();
				if(resultSet != null && !resultSet.isClosed())
					resultSet.close();
			}
			catch(Exception exception)
			{
			}
			territoryObject = null;
		}
		return responceObj;
	}

	public JSONArray preareCleanUpWithFieldLookupConf(Connection conn, String sql, boolean flagFor1165) throws Exception
	{

		JSONObject jsonObject = getTableDtlsForFieldLookupConf(conn, sql);
		JSONArray jsonArray = new JSONArray();
		JSONObject childJson = null;
		JSONObject conf = null;
		String temDelQuery = null;
		String delimiter = "#";
		int retension = 100;
		String isEnabled = "false";
		String sel = "select file_id_n from interface.tr_interface_file_summary where interface_id_n = REPLACE_ID and uploaded_on_dt  < current_date - REPLACE_RETENSION";
		String cnt = "select count(1) from interface.tr_interface_file_summary where interface_id_n = REPLACE_ID and uploaded_on_dt  < current_date - REPLACE_RETENSION";
		String del = "";

		String valDel = "delete from TABLE where created_dt  < current_date - REPLACE_RETENSION;"; 
		String tempDel = "delete from TABLE where file_id_n = ?;"; 
		String failDel = "delete from TABLE where file_id_n = ?;"; 

		String inFileSumDtls = "delete from interface.tr_interface_file_summary_details where file_id_n = ?;";
		String inFileSum = "delete from interface.tr_interface_file_summary where interface_id_n = REPLACE_ID and uploaded_on_dt  < current_date - REPLACE_RETENSION";
		int interface_id_n = 0;
		for (String key : jsonObject.keySet())
		{

			temDelQuery = "";
			childJson = jsonObject.getJSONObject(key);
			interface_id_n = childJson.getInt("id");
			conf = new JSONObject();
			conf.put("id", interface_id_n);
			conf.put("name", childJson.getString("name"));
			conf.put("retension", retension);
			conf.put("isEnabled", isEnabled);


			conf.put("sel_query", sel.replaceAll("REPLACE_ID", interface_id_n+""));
			conf.put("cnt_query", cnt.replaceAll("REPLACE_ID", interface_id_n+""));

			if(childJson.getInt("id") == 1165)
			{
				temDelQuery = "delete from interface.tr_temp_site_mapping where file_id_n = ?;#delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?;#delete from interface.tr_interface_file_summary_details where file_id_n = ?;#delete from interface.tr_interface_file_summary where interface_id_n = 1165 and uploaded_on_dt  < current_date - REPLACE_RETENSION;";	
			}				
			else
			{
				if(!childJson.getString("validation").trim().isEmpty()) 
				{
					temDelQuery = temDelQuery + valDel.replaceAll("TABLE", childJson.getString("validation")) + delimiter;
				}
				if(!childJson.getString("temporary").trim().isEmpty()) 
				{
					temDelQuery = temDelQuery + tempDel.replaceAll("TABLE", childJson.getString("temporary")) + delimiter;
				}
				if(!childJson.getString("failure").trim().isEmpty()) 
				{
					temDelQuery = temDelQuery + failDel.replaceAll("TABLE", childJson.getString("failure")) + delimiter;
				}


			}
			temDelQuery = temDelQuery + inFileSumDtls + delimiter;
			temDelQuery = temDelQuery + inFileSum.replaceAll("REPLACE_ID", interface_id_n + "");

			del = temDelQuery;

			conf.put("del_query", del);


			jsonArray.put(conf);
		}
		return jsonArray;

	}

	public void printFieldLookupConf(Connection conn, String interfaceIdStr, String choice, boolean deleteQueryCmdFlag) throws Exception
	{
		ResultSet resultSet = null;
		JSONObject jsonObject = null;
		JSONObject fields = null;
		JSONObject duplicate_validation_conf = null;
		String interfaceFieldLookupConfQuery = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n in ("+ interfaceIdStr +") order by inter.interface_id_n ;\n\n";
		String summaryDetails = ("select inter.interface_id_n,inter.name_v, summ.file_id_n, summ.file_name_v, summ.total_count_n, summ.success_count_n, summ.error_count_n, summ.filter_count_n, summ.status_n, summ.message_v FROM interface.tr_interface_file_summary summ INNER JOIN interface.ms_interface inter ON inter.interface_id_n=summ.interface_id_n where  inter.interface_id_n in ("+ interfaceIdStr +")  order by inter.interface_id_n ;");
		String lineBreak = ("\n\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n\n");
		try 
		{

			if(interfaceIdStr != null && interfaceIdStr.contains("1165"))
			{
				System.out.println(lineBreak);
				if(choice.equalsIgnoreCase("all") || choice.equalsIgnoreCase("select")) 
				{
					System.out.println("select * from interface.ms_interface_attr where interface_id_n = 1165 order by 1;");
					System.out.println("-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;");
					System.out.println("select * from interface.tr_interface_file_summary where interface_id_n = 1165 order by 1 desc;");
					System.out.println("select * from interface.tr_temp_site_mapping where file_id_n in (0) order by 1;");

				}
				if(deleteQueryCmdFlag)
					System.out.println("\n/*");
				if(choice.equalsIgnoreCase("all") || choice.equalsIgnoreCase("delete")) 
				{

					System.out.println( "delete from interface.tr_temp_site_mapping;\n"
							+ "delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1165)));\n"
							+ "delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in (1165));\n"
							+ "delete from interface.tr_interface_file_summary where interface_id_n in (1165);");
				}					
				if(deleteQueryCmdFlag)
					System.out.println("*/\n\n\n");
				System.out.println(lineBreak);
			}
			resultSet = conn.createStatement().executeQuery(interfaceFieldLookupConfQuery);

			while(resultSet.next()) 
			{
				jsonObject = new JSONObject(resultSet.getString(3));
				fields = jsonObject.getJSONObject("fields");
				if(jsonObject.has("duplicate_validation_conf") && jsonObject.get("duplicate_validation_conf") != null) 
				{
					duplicate_validation_conf = jsonObject.getJSONObject("duplicate_validation_conf");
				}
				if(choice.equalsIgnoreCase("all"))
				{
					System.out.println("-- INTERFACE ID\t" + resultSet.getString(1));
					System.out.println("-- INTERFACE NAME\t" + resultSet.getString(2));
					System.out.print( "-- ACTOR"+ "\t" + fields.get("actor_field")+ "\t" );
					System.out.println( "-- DAILY TABLE"+ "	kpi." + jsonObject.get("daily_table").toString().toLowerCase());
					System.out.print( "-- METRIC"+ "\t" + fields.get("metric_field")+ "\t" );
					System.out.println( "-- MONTHLY TABLE"+ "	kpi." + jsonObject.get("monthly_table").toString().toLowerCase());
					System.out.print( "-- SOURCE"+ "\t" + fields.get("source_field")+ "\t" );
				}
				//System.out.println("FAILURE TABLE" + "	kpi.TR_TEMP_HADOOP_FAILURE_AGGR".toLowerCase());

				if(choice.equalsIgnoreCase("all") || choice.equalsIgnoreCase("select")) 
				{
					if(duplicate_validation_conf != null) 
					{
						System.out.print( "--INSTANCE"+ "\t" + fields.get("instance_field")+ "\t" );
						System.out.println( "--VALIDATION TABLE"+ "	kpi." + duplicate_validation_conf.get("table_name").toString().toLowerCase() + "\n");
						System.out.println("select * from interface.ms_interface_attr where interface_id_n = " + resultSet.getString(1)+" order by 1;");
						System.out.println("-- update interface.ms_interface_attr set value_v = '' where attribute_id_n = ? ;");
						System.out.println("select * from interface.tr_interface_file_summary where interface_id_n = " + resultSet.getString(1)+" order by 1;");
						System.out.println("select * from kpi." + duplicate_validation_conf.get("table_name").toString().toLowerCase()+";");
					}
					if(!jsonObject.get("daily_table").toString().trim().isEmpty())
						System.out.println("select * from kpi." + jsonObject.get("daily_table").toString().toLowerCase()+";");
					if(!jsonObject.get("monthly_table").toString().trim().isEmpty())
						System.out.println("select * from kpi." + jsonObject.get("monthly_table").toString().toLowerCase()+";\n");

				}

				if(deleteQueryCmdFlag)
					System.out.println("\n/*");
				if(choice.equalsIgnoreCase("all") || choice.equalsIgnoreCase("delete")) 
				{
					System.out.println("delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (select file_id_n from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in ("+resultSet.getString(1)+")));");
					System.out.println("delete from interface.tr_interface_file_summary_details where file_id_n in (select file_id_n from interface.tr_interface_file_summary where interface_id_n in ("+resultSet.getString(1)+"));");
					System.out.println("delete from interface.tr_interface_file_summary where interface_id_n in ("+resultSet.getString(1)+");");

					if(jsonObject.has("duplicate_validation_conf") && jsonObject.get("duplicate_validation_conf") != null) 
						System.out.println("delete from kpi." + duplicate_validation_conf.get("table_name").toString().toLowerCase()+";");
					if(!jsonObject.get("daily_table").toString().trim().isEmpty())
						System.out.println("delete from kpi." + jsonObject.get("daily_table").toString().toLowerCase()+";");
					if(!jsonObject.get("monthly_table").toString().trim().isEmpty())
						System.out.println("delete from kpi." + jsonObject.get("monthly_table").toString().toLowerCase()+";");

				}

				if(deleteQueryCmdFlag)
					System.out.println("*/\n\n\n");

				System.out.println("select * from kpi.tr_temp_hadoop_failure_aggr where file_id_n in (0);");
				System.out.println(lineBreak);
				duplicate_validation_conf = null;
			}
			System.out.println(summaryDetails);
		}
		catch (Exception e) 
		{
			throw e;
		}
	}

	public JSONObject getTableDtlsForFieldLookupConf(Connection conn, String sql) throws Exception
	{
		ResultSet resultSet = null;
		JSONObject jsonObject = null;
		JSONObject childObj = null;
		JSONObject responseObj = new JSONObject();
		try
		{
			resultSet = conn.createStatement().executeQuery(sql);

			while(resultSet.next()){

				childObj = new JSONObject();
				jsonObject = new JSONObject(resultSet.getString(3));
				JSONObject duplicate_validation_conf = null;

				if(jsonObject.has("duplicate_validation_conf") && jsonObject.get("duplicate_validation_conf") != null) 
					duplicate_validation_conf = jsonObject.getJSONObject("duplicate_validation_conf");

				if(resultSet.getString(1).equalsIgnoreCase("1166")) {
					childObj.put("id", "1165");
					childObj.put("name", "INTHDP001 - Site Mapping");
					childObj.put("type", "HDP");
					childObj.put("temporary", "interface.tr_temp_site_mapping");
					childObj.put("validation", "interface.tr_temp_site_mapping");
					childObj.put("daily", "");
					childObj.put("monthly", "");
					childObj.put("failure", "interface.tr_temp_site_mapping");

					responseObj.put("1165", childObj);
				} 

				childObj = new JSONObject();
				childObj.put("id", resultSet.getString(1));
				childObj.put("name", resultSet.getString(2));

				if(resultSet.getString(2).contains("INTSM"))
					childObj.put("type", "SM");
				if(resultSet.getString(2).contains("INTDW"))
					childObj.put("type", "DWH");
				if(resultSet.getString(2).contains("INTHDP"))
					childObj.put("type", "HDP");


				childObj.put("temporary", (jsonObject.get("table_name") != null && !jsonObject.getString("table_name").trim().isEmpty() ? "kpi." + jsonObject.get("table_name").toString().toLowerCase() : ""));
				childObj.put("validation", (((duplicate_validation_conf  != null && duplicate_validation_conf.get("table_name") != null && !duplicate_validation_conf.getString("table_name").trim().isEmpty()) ) ?  "kpi." + duplicate_validation_conf.get("table_name").toString().toLowerCase() : ""));
				childObj.put("daily", ((jsonObject.get("daily_table") != null && !jsonObject.getString("daily_table").trim().isEmpty()) ?  "kpi." + jsonObject.get("daily_table").toString().toLowerCase() : ""));
				childObj.put("monthly", ((jsonObject.get("monthly_table") != null && !jsonObject.getString("monthly_table").trim().isEmpty()) ?  "kpi." + jsonObject.get("monthly_table").toString().toLowerCase() : ""));

				if(resultSet.getInt(1) >= 1166 && resultSet.getInt(1) <= 1182)
					childObj.put("failure", "kpi.tr_temp_hadoop_failure_aggr");
				else
					childObj.put("failure", "kpi.tr_temp_upload_aggr_failure");
				responseObj.put(resultSet.getString(1), childObj);

			}
		}
		catch (Exception e){
			throw e;
		}
		resultSet.close();
		return responseObj;
	}

	public void printFieldLookupConfWithoutQuery(Boolean printFlag, JSONObject jsonObject, int from, int to, boolean needAsFile, String filepath, String header) throws Exception
	{
		String temp = null;
		FileWriter fileWriter = null;

		if(needAsFile) { fileWriter = new FileWriter(new File(filepath)); fileWriter.write(header + "\n");}

		for (int i = from; i <= to; i++) 
		{
			if(!jsonObject.isNull(i+"") && jsonObject.get(i+"") != null) {
				temp = (
						jsonObject.getJSONObject(i+"").getString("id") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("name") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("type") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("temporary") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("validation") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("daily") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("monthly") + "\t"
								+ jsonObject.getJSONObject(i+"").getString("failure")
						).trim();
				if(printFlag) System.out.println(temp);
				if(needAsFile)fileWriter.write((temp + "\n"));
			}
			if(needAsFile)fileWriter.flush();
		}

		if(needAsFile)fileWriter.close();
		//		System.out.println("printFieldLookupConfWithoutQuery  :: done");
		jsonObject = null;
	}

	public List<String> getAllTablesFromKPI(JSONObject jsonObject) 
	{
		Set<String> set = new HashSet<String>();
		List<String> list = null;
		for (String str: jsonObject.keySet())
		{
			set.add(jsonObject.getJSONObject(str).getString("temporary"));
			set.add(jsonObject.getJSONObject(str).getString("validation"));
			set.add(jsonObject.getJSONObject(str).getString("daily"));
			set.add(jsonObject.getJSONObject(str).getString("monthly"));
			set.add(jsonObject.getJSONObject(str).getString("failure"));
			//		
			//			set.add(jsonObject.getJSONObject(str).getString("type") + "|" + jsonObject.getJSONObject(str).getString("temporary"));
			//			set.add(jsonObject.getJSONObject(str).getString("type") + "|" + jsonObject.getJSONObject(str).getString("validation"));
			//			set.add(jsonObject.getJSONObject(str).getString("type") + "|" + jsonObject.getJSONObject(str).getString("daily"));
			//			set.add(jsonObject.getJSONObject(str).getString("type") + "|" + jsonObject.getJSONObject(str).getString("monthly"));
			//			set.add(jsonObject.getJSONObject(str).getString("type") + "|" + jsonObject.getJSONObject(str).getString("failure"));

		}
		set.remove("");
		list = new ArrayList<String>(set);

		Collections.sort(list);
		set.clear();
		return list;
	}

	public Set<String> dataFrom(Connection conn, String sql, int columnCount, String loadORprint) throws Exception
	{
		ResultSet resultSet = null;
		Set<String> set = new LinkedHashSet<String>();
		try
		{
			resultSet = conn.createStatement().executeQuery(sql);

			while(resultSet.next())
			{
				set.add(resultSet.getString(1));
				if(loadORprint != null && "print".equals(loadORprint))
				{
					for (int i = 1; i <= columnCount; i++) 
					{
						System.out.print(resultSet.getString(i)+"\t");
					}
					System.out.println();
				}
			}
		}
		catch (Exception e){
			throw e;
		}
		return set;
	}

	public void writeFileWithKpiDailyAndMonthlyData(Connection connection, String seq, String kpiTableName, String fileName) throws Exception {

		PreparedStatement preparedStatement = null;
		String query = null;
		String header = null;
		ResultSet resultSet = null;
		FileWriter fileWriter = null;
		try 
		{
			if(seq.equals("daily"))
			{
				header = "day_id_n|actor_type_n|actor_id_n|event_type_n|metrics_id_n|dimension_1_n|no_of_events_n|source_type_n|source_id_n|data_flag_n|instance_type_n|instance_id_n|status_flag_n|data_string_v|system_type_v|last_updated_time_dt".toUpperCase();
				query = "select concat(day_id_n, '|', actor_type_n, '|', actor_id_n, '|', event_type_n, '|', metrics_id_n, '|', dimension_1_n, '|', no_of_events_n, '|', source_type_n, '|', source_id_n, '|', data_flag_n, '|', instance_type_n, '|', instance_id_n, '|', status_flag_n, '|', data_string_v, '|', system_type_v, '|', last_updated_time_dt) as msg from TABLE_NAME  order by last_updated_time_dt;".replaceAll("TABLE_NAME", kpiTableName) ;
				preparedStatement = connection.prepareStatement(query);
			}
			else if(seq.equals("monthly"))
			{
				header = "month_id_n|actor_type_n|actor_id_n|event_type_n|metrics_id_n|dimension_1_n|no_of_events_n|source_type_n|source_id_n|data_flag_n|instance_type_n|instance_id_n|status_flag_n|data_string_v|system_type_v|last_updated_time_dt".toUpperCase();
				query = "select concat(month_id_n, '|', actor_type_n, '|', actor_id_n, '|', event_type_n, '|', metrics_id_n, '|', dimension_1_n, '|', no_of_events_n, '|', source_type_n, '|', source_id_n, '|', data_flag_n, '|', instance_type_n, '|', instance_id_n, '|', status_flag_n, '|', data_string_v, '|', system_type_v, '|', last_updated_time_dt) as msg from TABLE_NAME  order by last_updated_time_dt;".replaceAll("TABLE_NAME", kpiTableName) ;
				preparedStatement = connection.prepareStatement(query);
			}
			System.out.println(query);
			fileWriter = new FileWriter(fileName);
			resultSet = preparedStatement.executeQuery();
			fileWriter.write(header + "\n"); 
			while(resultSet.next()) 
			{
				fileWriter.write(resultSet.getString(1) + "\n");
			}
			fileWriter.close();
		} 
		catch (Exception e) 
		{
			throw e;
		}
		finally 
		{
			if(resultSet != null)
				resultSet.close();
			if(preparedStatement != null)
				preparedStatement.close();
			if(fileWriter != null)
				fileWriter.close();
			query = null;
			fileWriter = null;
			resultSet = null;
			preparedStatement = null;
			System.out.println("Generated :: " + fileName);
		}
	}


	public Map<Long,String> getInterfaceAttrValue(Connection connection, String attributeName, List<Long> interfaceIdList) throws SQLException {
		Map<Long , String> response = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		String sql = "select interface_id_n, value_v from interface.ms_interface_attr where name_v = ? and  interface_id_n in ";
		try 
		{
			if(interfaceIdList == null)
				return null;

			sql = sql + interfaceIdList.toString().replace("[", "(").replace("]", ")");
			response = new HashMap<Long, String>();
			statement = connection.prepareStatement(sql);
			statement.setString(1, attributeName);
			resultSet = statement.executeQuery();
			while (resultSet.next()) 
				response.put(resultSet.getLong(1), resultSet.getString(2)!= null ? resultSet.getString(2).trim() : resultSet.getString(2));
			return response;
		} 
		catch (Exception e) 
		{

		}
		finally {
			if(resultSet != null)
				resultSet.close();
			if(statement != null)
				statement.close();
		}
		return response;
	}
}
