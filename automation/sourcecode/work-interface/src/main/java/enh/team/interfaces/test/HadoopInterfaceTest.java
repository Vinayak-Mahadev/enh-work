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

	boolean filePresent = false;
	long from = 1172l;
	long to   = 1172l;

	public static void main(String[] args) 
	{
		HadoopInterfaceTest hitest = null;
		Connection conn = null;
		Server server = null;

		try
		{

			hitest = new HadoopInterfaceTest();

			conn   = RDBMS.getDBConnection(PropType.RDBMS_144);
			server = new Server(PropType.Server_251_PPK);

			hitest.generateFile(conn, "E:/interface/backend/ControlFileGeneration/");
			hitest.prepareCtl();
			hitest.uploadFiles(server, "E:/interface/backend/ControlFileGeneration/raw/");
			hitest.callApi();


		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		EntityOperations.closeInstance();
	}

	public void generateFile(Connection conn, String dir) throws Exception
	{

		String sql = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n between "+ from +" and "+ to +" order by inter.interface_id_n ;";
		opr.printFieldLookupConf(conn, sql, "all", "1165");

		//
		//		opr.prepareFileFor1165(conn, "20200510", dir + "site_mapping"        +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1166(conn, "20200510", dir + "primary_mobo"        +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1167(conn, "20200510", dir + "secondary_mobo"      +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1168(conn, "20200510", dir + "d_sso"               +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1169(conn, "20200410", dir + "rgu_injection"       +   "_20200617090004_001.csv",    10,   1);
		//		opr.prepareFileFor1170(conn, "20200510", dir + "tertiary"            +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1171(conn, "20200510", dir + "org_close_bal"       +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1172(conn, "20200510", dir + "total_revenue"       +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1173(conn, "20200510", dir + "mobo_revenue"        +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1174(conn, "20200410", dir + "acquisition_revenue" +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1175(conn, "20200510", dir + "low_revenue_site"    +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1176(conn, "20200510", dir + "site_rgu_ga"         +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1177(conn, "20200510", dir + "cross_reload"        +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1178(conn, "20200510", dir + "cross_data"          +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1179(conn, "202005"  , dir + "outlet_program"      +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1180(conn, "202005"  , dir + "alloc_payment"       +   "_20200615090004_001.csv",    10,   1);
		//		opr.prepareFileFor1181(conn, "20200510", dir + "uro20"               +   "_20200615090004_001.csv",    10,   1);
		//
		//		opr.prepareFileFor1165(conn, "20200510", dir + "site_mapping"        +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1166(conn, "20200510", dir + "primary_mobo"        +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1167(conn, "20200510", dir + "secondary_mobo"      +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1168(conn, "20200510", dir + "d_sso"               +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1169(conn, "20200410", dir + "rgu_injection"       +   "_20200617090004_002.csv",    10,   1);
		//		opr.prepareFileFor1170(conn, "20200510", dir + "tertiary"            +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1171(conn, "20200510", dir + "org_close_bal"       +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1172(conn, "20200510", dir + "total_revenue"       +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1173(conn, "20200510", dir + "mobo_revenue"        +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1174(conn, "20200410", dir + "acquisition_revenue" +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1175(conn, "20200510", dir + "low_revenue_site"    +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1176(conn, "20200510", dir + "site_rgu_ga"         +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1177(conn, "20200510", dir + "cross_reload"        +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1178(conn, "20200510", dir + "cross_data"          +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1179(conn, "202005"  , dir + "outlet_program"      +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1180(conn, "202005"  , dir + "alloc_payment"       +   "_20200615090004_002.csv",    10,   1);
		//		opr.prepareFileFor1181(conn, "20200510", dir + "uro20"               +   "_20200615090004_002.csv",    10,   1);
		//
		//
		//		opr.prepareFileFor1165(conn, "20200510", dir + "site_mapping"        +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1166(conn, "20200510", dir + "primary_mobo"        +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1167(conn, "20200510", dir + "secondary_mobo"      +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1168(conn, "20200510", dir + "d_sso"               +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1169(conn, "20200410", dir + "rgu_injection"       +   "_20200617090004_003.csv",    10,   1);
		//		opr.prepareFileFor1170(conn, "20200510", dir + "tertiary"            +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1171(conn, "20200510", dir + "org_close_bal"       +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1172(conn, "20200510", dir + "total_revenue"       +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1173(conn, "20200510", dir + "mobo_revenue"        +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1174(conn, "20200410", dir + "acquisition_revenue" +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1175(conn, "20200510", dir + "low_revenue_site"    +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1176(conn, "20200510", dir + "site_rgu_ga"         +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1177(conn, "20200510", dir + "cross_reload"        +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1178(conn, "20200510", dir + "cross_data"          +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1179(conn, "202005"  , dir + "outlet_program"      +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1180(conn, "202005"  , dir + "alloc_payment"       +   "_20200615090004_003.csv",    10,   1);
		//		opr.prepareFileFor1181(conn, "20200510", dir + "uro20"               +   "_20200615090004_003.csv",    10,   1);
		//
		//
		//		opr.prepareFileFor1165(conn, "20200510", dir + "site_mapping"        +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1166(conn, "20200510", dir + "primary_mobo"        +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1167(conn, "20200510", dir + "secondary_mobo"      +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1168(conn, "20200510", dir + "d_sso"               +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1169(conn, "20200410", dir + "rgu_injection"       +   "_20200617090004_004.csv",    10,   1);
		//		opr.prepareFileFor1170(conn, "20200510", dir + "tertiary"            +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1171(conn, "20200510", dir + "org_close_bal"       +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1172(conn, "20200510", dir + "total_revenue"       +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1173(conn, "20200510", dir + "mobo_revenue"        +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1174(conn, "20200410", dir + "acquisition_revenue" +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1175(conn, "20200510", dir + "low_revenue_site"    +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1176(conn, "20200510", dir + "site_rgu_ga"         +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1177(conn, "20200510", dir + "cross_reload"        +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1178(conn, "20200510", dir + "cross_data"          +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1179(conn, "202005"  , dir + "outlet_program"      +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1180(conn, "202005"  , dir + "alloc_payment"       +   "_20200615090004_004.csv",    10,   1);
		//		opr.prepareFileFor1181(conn, "20200510", dir + "uro20"               +   "_20200615090004_004.csv",    10,   1);
		//
		//
		//		opr.prepareFileFor1165(conn, "20200510", dir + "site_mapping"        +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1166(conn, "20200510", dir + "primary_mobo"        +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1167(conn, "20200510", dir + "secondary_mobo"      +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1168(conn, "20200510", dir + "d_sso"               +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1169(conn, "20200410", dir + "rgu_injection"       +   "_20200617090004_005.csv",    10,   1);
		//		opr.prepareFileFor1170(conn, "20200510", dir + "tertiary"            +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1171(conn, "20200510", dir + "org_close_bal"       +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1172(conn, "20200510", dir + "total_revenue"       +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1173(conn, "20200510", dir + "mobo_revenue"        +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1174(conn, "20200410", dir + "acquisition_revenue" +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1175(conn, "20200510", dir + "low_revenue_site"    +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1176(conn, "20200510", dir + "site_rgu_ga"         +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1177(conn, "20200510", dir + "cross_reload"        +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1178(conn, "20200510", dir + "cross_data"          +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1179(conn, "202005"  , dir + "outlet_program"      +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1180(conn, "202005"  , dir + "alloc_payment"       +   "_20200615090004_005.csv",    10,   1);
		//		opr.prepareFileFor1181(conn, "20200510", dir + "uro20"               +   "_20200615090004_005.csv",    10,   1);


		//opr.prepareFileFor1173(conn, "20200510", dir + "mobo_revenue"        +   "_20200510090004_001.csv",    10,   1);
		//opr.prepareFileFor1173(conn, "20200520", dir + "mobo_revenue"        +   "_20200520090004_001.csv",    10,   1);
		//opr.prepareFileFor1173(conn, "20200525", dir + "mobo_revenue"        +   "_20200525090004_001.csv",    10,   1);
		opr.prepareFileFor1172(conn, "20200510", dir + "total_revenue"       +   "_202006090004_005.csv",    100000,   3);

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
			ThreadUtil.processFile(i);
			Thread.sleep(1 * 10 * 1000);
			System.out.println("processFile service call :: " + i  + "    status  :: " );
		}

		for (long i = from; i <= to; i++) 
		{
			ThreadUtil.processReceivedFiles(i);
			System.out.println("processReceivedFiles service call :: " + i  );
			Thread.sleep(1 * 20 * 1000);
		}
		Thread.sleep(1 * 20 * 1000);
		for (long i = from; i <= to; i++) 
		{
			ThreadUtil.prepareRejectionFile(i);
			Thread.sleep(1 * 20 * 1000);
			System.out.println("prepareRejectionFile service call :: " + i  + "    status  :: " );
		}

		ThreadUtil.threadsKill();
		//		for (long i = from; i <= to; i++) 
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
