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

	EntityOperations entityOperations  = EntityOperations.getInstance();
	public static void main(String[] args) 
	{
		try
		{

			HadoopInterfaceTest hadoopInterfaceTest = new HadoopInterfaceTest();

			Connection conn = RDBMS.getDBConnection(PropType.RDBMS_144);
			Server server = new Server(PropType.Server_251_PPK);
			hadoopInterfaceTest.generateFile(conn);
			hadoopInterfaceTest.prepareCtl();
			hadoopInterfaceTest.uploadFiles(server, "E:/interface/backend/ControlFileGeneration/raw/");
			hadoopInterfaceTest.callApi();

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		EntityOperations.closeInstance();
	}

	public void generateFile(Connection conn) throws Exception
	{

		RDBMSOperation opr = new RDBMSOperation();
		String dir = "E:/interface/backend/ControlFileGeneration/";
		opr.prepareFileFor1165(conn, "20200613", dir + "site_mapping"        +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1166(conn, "20200613", dir + "primary_mobo"        +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1167(conn, "20200613", dir + "secondary_mobo"      +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1168(conn, "20200613", dir + "d_sso"               +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1169(conn, "20200613", dir + "rgu_injection"       +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1170(conn, "20200613", dir + "tertiary"            +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1171(conn, "20200613", dir + "org_close_bal"       +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1172(conn, "20200613", dir + "total_revenue"       +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1173(conn, "20200613", dir + "mobo_revenue"        +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1174(conn, "20200613", dir + "acquisition_revenue" +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1175(conn, "20200613", dir + "low_revenue_site"    +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1176(conn, "20200613", dir + "site_rgu_ga"         +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1177(conn, "20200613", dir + "cross_reload"        +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1178(conn, "20200613", dir + "cross_data"          +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1179(conn, "202006",   dir + "outlet_program"      +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1180(conn, "202006",   dir + "alloc_payment"       +   "_20200614090001.csv",    10000,   2);
		opr.prepareFileFor1181(conn, "20200615", dir + "uro_20"              +   "_20200614090001.csv",    10000,   2);
		
		

		opr.prepareFileFor1165(conn, "20200614", dir + "site_mapping"        +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1166(conn, "20200614", dir + "primary_mobo"        +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1167(conn, "20200614", dir + "secondary_mobo"      +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1168(conn, "20200614", dir + "d_sso"               +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1169(conn, "20200614", dir + "rgu_injection"       +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1170(conn, "20200614", dir + "tertiary"            +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1171(conn, "20200614", dir + "org_close_bal"       +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1172(conn, "20200614", dir + "total_revenue"       +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1173(conn, "20200614", dir + "mobo_revenue"        +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1174(conn, "20200614", dir + "acquisition_revenue" +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1175(conn, "20200614", dir + "low_revenue_site"    +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1176(conn, "20200614", dir + "site_rgu_ga"         +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1177(conn, "20200614", dir + "cross_reload"        +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1178(conn, "20200614", dir + "cross_data"          +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1179(conn, "202006",   dir + "outlet_program"      +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1180(conn, "202006",   dir + "alloc_payment"       +   "_20200614090002.csv",    10000,   2);
		opr.prepareFileFor1181(conn, "20200614", dir + "uro_20"              +   "_20200614090002.csv",    10000,   2);


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


		sshOperation = new SshOperation(server);


		for (long i = 1165; i <= 1181; i++) 
		{
			interfaceAttributes = entityOperations.getInterfaceAttribute(i);



			filepath = new File(uploadFilesPath);
			if(!filepath.isDirectory())
				return;
			for (File file : filepath.listFiles()) 
			{
				if(file.getName().startsWith(getInterfaceAttributeValue(interfaceAttributes, "Remote File"))){
					System.out.println(file.getName());

					sshOperation.upload(file.getName(), getInterfaceAttributeValue(interfaceAttributes, "Remote Dir"), uploadFilesPath);
					file.delete();
				}
			}
		}

		sshOperation.disconnect();

	}

	public void callApi() throws Exception 
	{

		for (long i = 1165; i <= 1181; i++) 
		{
			ThreadUtil.processFile(i);
			Thread.sleep(1 * 5 * 1000);
			System.out.println("processFile service call :: " + i  + "    status  :: " );
		}
		ThreadUtil.threadsJoin();
		for (long i = 1165; i <= 1181; i++) 
		{
			ThreadUtil.processReceivedFiles(i);
			Thread.sleep(1 * 10 * 1000);
			System.out.println("processReceivedFiles service call :: " + i  + "    status  :: " );
		}
		ThreadUtil.threadsJoin();
		for (long i = 1165; i <= 1181; i++) 
		{
			ThreadUtil.prepareRejectionFile(i);
			Thread.sleep(1 * 10 * 1000);
			System.out.println("prepareRejectionFile service call :: " + i  + "    status  :: " );
		}
		ThreadUtil.threadsJoin();

	}


	private String getInterfaceAttributeValue(List<InterfaceAttribute> attributes, String name) {

		for (InterfaceAttribute interfaceAttribute : attributes) {
			if(interfaceAttribute.getName().equalsIgnoreCase(name))
				return interfaceAttribute.getValue();
		}
		return null;
	}
}
