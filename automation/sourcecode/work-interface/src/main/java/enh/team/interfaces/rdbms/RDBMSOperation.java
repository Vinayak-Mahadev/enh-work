package enh.team.interfaces.rdbms;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

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

	public void prepareFileFor1165(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{
		FileOutputStream fos = null;
		try 
		{
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|SITE_ID|LONGITUDE|LATITUDE|MICRO_CLUSTER|SALES_CLUSTER|SALES_AREA|AREA|REGION|JAVA_NONJAVA|SITE_NAME|SITE_POPULATION\n".getBytes());					

			int population = 5;
			int lastFileRowCount = 0;
			for(int i = 1; i <= limit; i++)
			{
				fos.write((dateInFile + "|Test Site-"+ i +"|19.10|31.23|micro107|100709|59|13|REG5|NON_JAVA|Test Site-"+ i +"|" + population + "\n").getBytes());
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
			
			System.out.println("Total Rows : " + (lastFileRowCount/fileSequence));
			fos.close();
			System.out.println("File generated");
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			
		}
	}
	
	public void prepareFileFor1166(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{
		ResultSet mpc = null;
		FileOutputStream fos = null;
		//DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MPC_CODE|MOBO_DATE|AMOUNT\n".getBytes());			
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			int i = 0;

			out : while(i <= limit)
			{
				mpc = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 7 order by 1");
				while (mpc.next()) 
				{
					fos.write((mpc.getString(1) + "|" + dateInFile + "|" + i + ".50\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence));
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1167(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
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
					fos.write((mpcRef+"|"+dateInFile+"|"+org.getString(1)+"|1"+format.format(i)+"|1"+ (format.format(i)+10) +".50\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1167");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	

	public void prepareFileFor1168(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{
		ResultSet outlet = null;
		ResultSet micro = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|ID_OUTLET|QTY|AMOUNT\n".getBytes());

			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			ArrayList<String> microList = new ArrayList<String>();
			while (micro.next()) {
				microList.add(micro.getString(1));
			}

			out : while (outlet.next()) 
			{

				for (String microId : microList) 
				{
					fos.write((dateInFile+ "|"+microId+"|Test Site-"+i+ "|"+outlet.getString(1)+"|"+i+"|1"+format.format(i+10)+".05\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1168");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void prepareFileFor1169(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{
		ResultSet outlet = null;
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN\n".getBytes());
			outlet = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");
			micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			ArrayList<String> microList = new ArrayList<String>();
			while (micro.next()) {
				microList.add(micro.getString(1));
			}

			out : while (outlet.next()) {

				for (String microId : microList) {
					fos.write((dateInFile+"|"+microId+"|Test Site-"+i+ "|" +outlet.getString(1)+"|injected|c. >=7k - <10k|"+ (i+10) +"\n").getBytes());
					i++;
										
					if(i % calcRowLimit == 0 && i != limit)
					{
						System.out.println("Total Rows : " + (i/fileSequence));
						fos.close();
						fos = new FileOutputStream(new File(filePath.replace(".csv", "_00" + fileSequence + ".csv")));
						fos.write("DATE|MICRO|SITE_ID|ID_OUTLET|STATUS_INJECTION|FLAG_ACM|COUNT_MSISDN\n".getBytes());
						fileSequence++;
					}
					
					if(limit != 0 )
						if( limit==i)
							break out;
				}
				fos.flush();
			}

			System.out.println("Total Rows : " + (i/fileSequence) + "   1169");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1170(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{
		ResultSet micro = null;
		ResultSet out = null;		

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|OUTLET|AMOUNT\n".getBytes());
			micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
			out = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6 order by 1");

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
					fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+ outlet + "|" + i + ".50\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1170");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	public void prepareFileFor1171(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{
		ResultSet org = null;
		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MOBO_DATE|ORG_CODE|AMOUNT\n".getBytes());			

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 

			out : while(i <= limit)
			{
				org = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 7 order by 1");
				while (org.next()) 
				{
					fos.write((dateInFile + "|" + org.getString(1) + "|" + i + ".50\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1171");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1172(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|REVENUE_TYPE|REVENUE_TOTAL\n".getBytes());			

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount;
			
			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{
					String revenueType = "";
					if(i % 3 == 0)
						revenueType = "voice";
					else
						revenueType = "sms";
					fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+revenueType+"|" + i + ".30\n").getBytes());
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
			
			System.out.println("Total Rows : " + (i/fileSequence) + "   1172");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1173_1174(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|REVENUE\n".getBytes());			

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{				
					fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|" + i + ".40\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1173_1174");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1175(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|CATEGORY|TARGET|REVENUE\n".getBytes());			

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
				while (micro.next()) 
				{
					String category = "";
					if(i % 4 == 0)
						category = "Java";
					else
						category = "Non Java";
					fos.write((dateInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+category+"|" + i + ".30" + "|" + i + ".70\n").getBytes());
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
			System.out.println("Total Rows : " + (i/fileSequence) + "   1175");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1176(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{		
		ResultSet micro = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE|QTY\n".getBytes());			

			int i = 0;		
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			out : while(i <= limit)
			{
				micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
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
			System.out.println("Total Rows : " + (i/fileSequence) + "   1176");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1177(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{		
		ResultSet cluster = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|CLUSTER_ID|CATEGORY|CLUSTER_TYPE|TOTAL_RELOAD|CROSS_RELOAD\n".getBytes());			

			int i = 0;	
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			String category = "";
			String clusterType = "";
			out : while(i <= limit)
			{
				cluster = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1177");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1178(Connection conn, String dateInFile, String filePath, int limit, int fileCount)
	{		
		ResultSet cluster = null;

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|CLUSTER_ID|TOTAL_DATA|CROSS_DATA\n".getBytes());			

			int i = 0;
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			out : while(i <= limit)
			{
				cluster = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");
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
			System.out.println("Total Rows : " + (i/fileSequence) + "   1178");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void prepareFileFor1179(Connection conn, String monthInFile, String filePath, int limit, int fileCount)
	{
		ResultSet cluster = null;
		ResultSet out = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MONTH_ID|CLUSTER|ID_OUTLET|TARGET|ACTUAL\n".getBytes());			

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
				cluster = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");				
				while (cluster.next()) 
				{
					for(String outlet : outletList)
					{
						fos.write((monthInFile+"|"+cluster.getString(1)+"|"+outlet+"|1"+format.format(i)+".75|1"+ (format.format(i)+10) +".0\n").getBytes());
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
			System.out.println("Total Rows : " + (i/fileSequence) + "   1179");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	public void prepareFileFor1180(Connection conn, String monthInFile, String filePath, int limit, int fileCount)
	{
		ResultSet cluster = null;
		ResultSet mpc = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("MONTH_ID|CLUSTER|MPC_CODE|PAYMENT_ALLOCATION\n".getBytes());			

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
				cluster = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 88) order by 1;");				
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
			System.out.println("Total Rows : " + (i/fileSequence) + "   1180");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}	
	
	public void prepareFileFor1181(Connection conn, String monthInFile, String filePath, int limit, int fileCount)
	{
		ResultSet micro = null;
		ResultSet out = null;

		FileOutputStream fos = null;
		DecimalFormat format = new DecimalFormat("0000");
		try 
		{
			fos = new FileOutputStream(new File(filePath));
			fos.write("DATE|MICRO|SITE_ID|OUTLET|HIT|AMOUNT\n".getBytes());
			micro = conn.createStatement().executeQuery("select ext_ref_code_v from kpi.ms_lookup_master where lookup_type_n = (select lookup_type_n from kpi.ms_lookup_type_master where ext_lookup_type_n = 89) order by 1;");
			out = conn.createStatement().executeQuery("select ref_code_v from kpi.ms_org_master where org_type_n = 6   and sub_org_type_n = 66 and status_n = 174 order by 1;");

			int i = 0;			
			int fileSequence = 1;
			int calcRowLimit = limit/fileCount; 
			
			ArrayList<String> outletList = new ArrayList<String>();
			while (out.next()) {
				outletList.add(out.getString(1));
			}

			out : while (micro.next()) {

				for (String outlet : outletList)
				{
					fos.write((monthInFile+"|"+micro.getString(1)+"|Test Site-"+i+"|"+outlet+"|1"+format.format(i) +"|1"+format.format(i) + ".0\n").getBytes());
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

			System.out.println("Total Rows : " + (i/fileSequence) + "   1181");
			fos.close();
			System.out.println("File generated");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
			
			preparedStatement = connection.prepareStatement("select lookup_master.lookup_id_n, lookup_master.lookup_type_n, lookup_master.ext_ref_code_v, lookup_type_master.ext_lookup_type_n from kpi.ms_lookup_master as lookup_master inner join kpi.ms_lookup_type_master as lookup_type_master on (lookup_master.ext_ref_code_v in (?, ?, ?, ?, ?) and lookup_type_master.ext_lookup_type_n in (84,85,86,87,88,89) and lookup_type_master.lookup_type_n = lookup_master.lookup_type_n) group by lookup_master.lookup_id_n, lookup_master.lookup_type_n, lookup_master.ext_ref_code_v, lookup_type_master.ext_lookup_type_n order by lookup_type_master.ext_lookup_type_n desc");
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
	
	
	
}
