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
import enh.team.interfaces.soapservice.ResponseBean;
import enh.team.interfaces.soapservice.SndIntegrationSoupService;
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

		opr.prepareFileFor1165(conn, "20200614", "E:/interface/backend/ControlFileGeneration/site_mapping_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1166(conn, "20200614", "E:/interface/backend/ControlFileGeneration/primary_mobo_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1167(conn, "20200614", "E:/interface/backend/ControlFileGeneration/secondary_mobo_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1168(conn, "20200614", "E:/interface/backend/ControlFileGeneration/daily_sso_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1169(conn, "20200614", "E:/interface/backend/ControlFileGeneration/rgu_injection_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1170(conn, "20200614", "E:/interface/backend/ControlFileGeneration/tertiary_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1171(conn, "20200614", "E:/interface/backend/ControlFileGeneration/org_close_bal_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1172(conn, "20200614", "E:/interface/backend/ControlFileGeneration/total_revenue_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1173(conn, "20200614", "E:/interface/backend/ControlFileGeneration/mobo_revenue_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1174(conn, "20200614", "E:/interface/backend/ControlFileGeneration/acquisition_revenue_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1175(conn, "20200614", "E:/interface/backend/ControlFileGeneration/low_revenue_site_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1176(conn, "20200614", "E:/interface/backend/ControlFileGeneration/site_rgu_ga_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1177(conn, "20200614", "E:/interface/backend/ControlFileGeneration/cross_reload_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1178(conn, "20200614", "E:/interface/backend/ControlFileGeneration/cross_data_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1179(conn, "202006", "E:/interface/backend/ControlFileGeneration/outlet_program_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1180(conn, "202006", "E:/interface/backend/ControlFileGeneration/alloc_payment_20200614090001.csv", 1000, 1);
		opr.prepareFileFor1181(conn, "20200614", "E:/interface/backend/ControlFileGeneration/uro_20_20200614090001.csv", 1000, 1);


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

		SndIntegrationSoupService service = new SndIntegrationSoupService();
		ResponseBean responseBean = null;
		for (long i = 1165; i <= 1181; i++) 
		{
			responseBean = service.processFile(i);
			Thread.sleep(1 * 5 * 1000);
			responseBean = service.processReceivedFiles(i);
			Thread.sleep(1 * 10 * 1000);
			responseBean = service.prepareRejectionFile(i);
			Thread.sleep(1 * 10 * 1000);
			System.out.println("Service call :: " + i  + "    status  :: " + responseBean.getStatus());
		}


	}


	private String getInterfaceAttributeValue(List<InterfaceAttribute> attributes, String name) {

		for (InterfaceAttribute interfaceAttribute : attributes) {
			if(interfaceAttribute.getName().equalsIgnoreCase(name))
				return interfaceAttribute.getValue();
		}
		return null;
	}
}
