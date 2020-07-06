package enh.team.interfaces.test;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.finevm.enh.interfaces.entities.EntityOperations;
import com.finevm.enh.interfaces.entities.InterfaceAttribute;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;
import com.finevm.enh.util.Server;

import enh.team.interfaces.rdbms.RDBMSOperation;
import enh.team.interfaces.soapservice.PrepareCTL;
import enh.team.interfaces.ssh.SshOperation;

public class HadoopInterfaceTest {

	EntityOperations entityOperations; 
	RDBMSOperation opr = new RDBMSOperation();
	private static HadoopInterfaceTest hitest = new HadoopInterfaceTest();;

	boolean filePresent = false;
	long from = 1165l;
	long to   = 1182l;

	public static void main(String[] args)
	{


		String dateInFile = "20200510";
		String dir   = "E:/interface/backend/ControlFileGeneration/";
		String fileName   = "_20200615090004_02.csv";
		String uploadFileLoc = "E:/interface/backend/ControlFileGeneration/raw/";
		Connection	conn = RDBMS.getDBConnection(PropType.RDBMS_144);

		try
		{ 
			String sql = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n between "+ hitest.from +" and "+ hitest.to +" order by inter.interface_id_n ;\n\n";
			System.out.println(sql);
			hitest.opr.printFieldLookupConf(conn, sql, "all", "1165", true);


			dateInFile = "20200410";
			fileName   = "_20200415090001_101.csv";
			conn = RDBMS.getDBConnection(PropType.RDBMS_144);
			hitest.start(conn, dateInFile, dir, fileName, uploadFileLoc);

			dateInFile = "20200510";
			fileName   = "_20200515090002_102.csv";
			conn = RDBMS.getDBConnection(PropType.RDBMS_144);
			hitest.start(conn, dateInFile, dir, fileName, uploadFileLoc);

			dateInFile = "20200610";
			fileName   = "_20200615090002_103.csv";
			conn = RDBMS.getDBConnection(PropType.RDBMS_144);
			hitest.start(conn, dateInFile, dir, fileName, uploadFileLoc);
			//hitest.callApi();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(conn != null)
					conn.close();
				EntityOperations.closeInstance();
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
	}

	public void start(Connection conn, String dateInFile, String dir, String fileName, String uploadFileLoc) throws Exception 
	{
		try
		{
			generateFile(conn, dateInFile, dir,  fileName );
			prepareCtl();
			uploadFiles(new Server(PropType.Server_251_PPK), uploadFileLoc);
			callApi();
			
			if(conn != null)
				conn.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			conn.close();
		}
	}
	public void generateFile(Connection conn, String dateInFile, String dir, String fileName) throws Exception
	{

		opr.prepareFileFor1165(conn, dateInFile,                 dir + "site_mapping"        +  fileName ,    100,   1);
		opr.prepareFileFor1166(conn, dateInFile,                 dir + "primary_mobo"        +  fileName ,    100,   1);
		opr.prepareFileFor1167(conn, dateInFile,                 dir + "secondary_mobo"      +  fileName ,    100,   1);
		opr.prepareFileFor1168(conn, dateInFile,                 dir + "d_sso"               +  fileName ,    100,   1);
		opr.prepareFileFor1169(conn, dateInFile,                 dir + "rgu_injection"       +  fileName ,    100,   1);
		opr.prepareFileFor1170(conn, dateInFile,                 dir + "tertiary"            +  fileName ,    100,   1);
		opr.prepareFileFor1171(conn, dateInFile,                 dir + "org_close_bal"       +  fileName ,    100,   1);
		opr.prepareFileFor1172(conn, dateInFile,                 dir + "total_revenue"       +  fileName ,    100,   1);
		opr.prepareFileFor1173(conn, dateInFile,                 dir + "mobo_revenue"        +  fileName ,    100,   1);
		opr.prepareFileFor1174(conn, dateInFile,                 dir + "acquisition_revenue" +  fileName ,    100,   1);
		opr.prepareFileFor1175(conn, dateInFile,                 dir + "low_revenue_site"    +  fileName ,    100,   1);
		opr.prepareFileFor1176(conn, dateInFile,                 dir + "site_rgu_ga"         +  fileName ,    100,   1);
		opr.prepareFileFor1177(conn, dateInFile,                 dir + "cross_reload"        +  fileName ,    100,   1);
		opr.prepareFileFor1178(conn, dateInFile,                 dir + "cross_data"          +  fileName ,    100,   1);
		opr.prepareFileFor1179(conn, dateInFile.substring(0, 6), dir + "outlet_program"      +  fileName ,    100,   1);
		opr.prepareFileFor1180(conn, dateInFile.substring(0, 6), dir + "alloc_payment"       +  fileName ,    100,   1);
		opr.prepareFileFor1181(conn, dateInFile,                 dir + "uro20"               +  fileName ,    100,   1);
		opr.prepareFileFor1182(conn, dateInFile,                 dir + "outlet_sp_tagging"   +  fileName ,    100,   1);
	}

	public void prepareCtl() throws Exception 
	{
		PrepareCTL prepareCTL = new PrepareCTL();
		String inputFileFolder = "E:\\interface\\backend\\ControlFileGeneration\\";
		String outputFileFolder = "E:\\interface\\backend\\ControlFileGeneration\\raw\\";
		String moveOrgnlFiles = "E:\\interface\\backend\\ControlFileGeneration\\bkp\\";
		prepareCTL.prepareCTL(inputFileFolder, outputFileFolder, moveOrgnlFiles);

	}

	public void uploadFiles(Server server, String uploadFilesPath) throws Exception
	{
		SshOperation sshOperation = null;
		List<InterfaceAttribute> interfaceAttributes = null;
		File filepath = null;

		filepath = new File(uploadFilesPath);
		if(!filepath.isDirectory() )
			return;

		if(filepath.listFiles().length != 0)
		{
			entityOperations = EntityOperations.getInstance();
			sshOperation = new SshOperation(server);

		}
		for (long i = from; i <= to; i++) 
		{
			interfaceAttributes = entityOperations.getInterfaceAttribute(i);

			for (File file : filepath.listFiles()) 
			{
				if(file.getName().startsWith(getInterfaceAttributeValue(interfaceAttributes, "Remote File"))){
					System.out.println(file.getName());

					sshOperation.upload(file.getName(), getInterfaceAttributeValue(interfaceAttributes, "Remote Dir"), uploadFilesPath);
					file.delete();
					filePresent = true;
				}
			}
		}

		if(sshOperation != null)
			sshOperation.disconnect();

		if(entityOperations != null)
			EntityOperations.closeInstance();
	}

	public void callApi() throws Exception 
	{


		for (long i = from; i <= to; i++) 
		{
			System.out.println("processFile service call :: " + i  );
			ThreadUtil.processFile(i);
			Thread.sleep(1 * 10 * 1000);
		}

		for (long i = from; i <= to; i++) 
		{
			System.out.println("processReceivedFiles service call :: " + i );
			ThreadUtil.processReceivedFiles(i);
			Thread.sleep(1 * 10 * 1000);
		}
		Thread.sleep(1 * 10 * 1000);
		for (long i = from; i <= to; i++) 
		{
			System.out.println("prepareRejectionFile service call :: " + i );
			ThreadUtil.prepareRejectionFile(i);
			Thread.sleep(1 * 10 * 1000);
		}

		//		for (long i = from; i <= to; i++) 
		//		{
		//			System.out.println("pullDataToFile service call :: " + i );
		//			ThreadUtil.pullDataToFile(i);
		//			Thread.sleep(1 * 20 * 1000);
		//		}

	}


	private String getInterfaceAttributeValue(List<InterfaceAttribute> attributes, String name) {

		for (InterfaceAttribute interfaceAttribute : attributes) {
			if(interfaceAttribute.getName().equalsIgnoreCase(name))
				return interfaceAttribute.getValue();
		}
		return null;
	}
}
