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

	boolean filePresent = false;

	public static void main(String[] args) 
	{
		try
		{

			HadoopInterfaceTest hadoopInterfaceTest = new HadoopInterfaceTest();
			//
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
		ThreadUtil.threadsKill();
	}

	public void generateFile(Connection conn) throws Exception
	{

		//RDBMSOperation opr = new RDBMSOperation();
		//String dir = "E:/interface/backend/ControlFileGeneration/";
		//		opr.prepareFileFor1165(conn, "20200515", dir + "site_mapping"        +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1166(conn, "20200531", dir + "primary_mobo"        +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1167(conn, "20200531", dir + "secondary_mobo"      +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1168(conn, "20200531", dir + "d_sso"               +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1169(conn, "20200531", dir + "rgu_injection"       +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1170(conn, "20200531", dir + "tertiary"            +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1171(conn, "20200531", dir + "org_close_bal"       +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1172(conn, "20200531", dir + "total_revenue"       +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1173(conn, "20200531", dir + "mobo_revenue"        +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1174(conn, "20200531", dir + "acquisition_revenue" +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1175(conn, "20200531", dir + "low_revenue_site"    +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1176(conn, "20200531", dir + "site_rgu_ga"         +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1177(conn, "20200531", dir + "cross_reload"        +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1178(conn, "20200531", dir + "cross_data"          +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1179(conn, "202005"  , dir + "outlet_program"      +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1180(conn, "202005"  , dir + "alloc_payment"       +   "_20200615090004_001.csv",    10000,   1);
		//		opr.prepareFileFor1181(conn, "20200531", dir + "uro20"               +   "_20200615090004_001.csv",    10000,   1);

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
		if(!filepath.isDirectory())
			return;

		if(filepath.listFiles().length != 0)
		{
			entityOperations = EntityOperations.getInstance();
			sshOperation = new SshOperation(server);

		}

		for (long i = 1165; i <= 1181; i++) 
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

		//		for (long i = 1165; i <= 1181; i++) 
		//		{
		//			ThreadUtil.processFile(i);
		//			Thread.sleep(1 * 10 * 1000);
		//			System.out.println("processFile service call :: " + i  + "    status  :: " );
		//		}
		//		ThreadUtil.threadsJoin();
		//		for (long i = 1165; i <= 1181; i++) 
		//		{
		//			ThreadUtil.processReceivedFiles(i);
		//			System.out.println("processReceivedFiles service call :: " + i  );
		//			Thread.sleep(1 * 20 * 1000);
		//		}
		//		ThreadUtil.threadsJoin();
		//		for (long i = 1165; i <= 1181; i++) 
		//		{
		//			ThreadUtil.prepareRejectionFile(i);
		//			Thread.sleep(1 * 20 * 1000);
		//			System.out.println("prepareRejectionFile service call :: " + i  + "    status  :: " );
		//		}
		//		ThreadUtil.threadsJoin();
		//		for (long i = 1152; i <= 1152; i++) 
		//		{
		//			ThreadUtil.pullDataToFile(i);
		//			Thread.sleep(1 * 20 * 1000);
		//			System.out.println("pullDataToFile service call :: " + i  + "    status  :: " );
		//		}
		//		ThreadUtil.threadsJoin();

	}


	private String getInterfaceAttributeValue(List<InterfaceAttribute> attributes, String name) {

		for (InterfaceAttribute interfaceAttribute : attributes) {
			if(interfaceAttribute.getName().equalsIgnoreCase(name))
				return interfaceAttribute.getValue();
		}
		return null;
	}
}
