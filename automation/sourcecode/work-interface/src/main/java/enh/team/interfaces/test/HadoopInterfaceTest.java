package enh.team.interfaces.test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.finevm.enh.interfaces.entities.EntityOperations;
import com.finevm.enh.interfaces.entities.InterfaceAttribute;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;
import com.finevm.enh.util.Server;

import enh.team.interfaces.rdbms.RDBMSOperation;
import enh.team.interfaces.soapservice.PrepareCTL;
import enh.team.interfaces.ssh.SshOperation;

public class HadoopInterfaceTest 
{

	private EntityOperations entityOperations; 
	private RDBMSOperation rdbmsOperation = null;
	private Connection  connection = null;

	boolean genearteFileFlag = false;

	private List<Long> interfaceIdList = null;
	private List<String> idList = null;

	private String outputFileFolderControlFileGeneration = null;
	private String inputFileFolderControlFileGeneration = null;
	private String moveOrgnlFilesControlFileGeneration = null;
	private String interfaceFieldLookupConfQuery = null;
	private String uploadFileLoc = null;
	long apiWaitInSecond = 0l;

	public static void main(String[] args) throws Exception
	{


		String interfaceIdStr = "1165 ,1166 ,1167 ,1168 ,1169 ,1170 ,1171 ,1172 ,1173 ,1174 ,1175 ,1176 ,1177 ,1178 ,1179 ,1180 ,1181 ,1182 ,1183";

		interfaceIdStr = "1169, 1183";

		HadoopInterfaceTest hitest = new HadoopInterfaceTest(interfaceIdStr);;

		String dateInFile = null;

		String fileName   = null;

		hitest.rdbmsOperation.printFieldLookupConf(hitest.connection, hitest.interfaceFieldLookupConfQuery, "all", "", true);


		dateInFile = "20200620";
		fileName   = "_20200601090020_401.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20200720";
		fileName   = "_20200701090001_402.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20200820";
		fileName   = "_20200801090020_403.csv";
		hitest.start( dateInFile, fileName);
		
		


		dateInFile = "20200620";
		fileName   = "_20200601090020_501.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20200720";
		fileName   = "_20200701090020_502.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20200820";
		fileName   = "_20200801090020_503.csv";
		hitest.start( dateInFile, fileName);
		


		dateInFile = "20200620";
		fileName   = "_20200601090020_601.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20200720";
		fileName   = "_20200701090020_602.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20200820";
		fileName   = "_20200801090020_603.csv";
		hitest.start( dateInFile, fileName);

		hitest.end();
	}

	public HadoopInterfaceTest(String interfaceIdStr) 
	{
		apiWaitInSecond = 20l;
		entityOperations = EntityOperations.getInstance();

		interfaceIdStr = interfaceIdStr.replace(" ", "").replace("\t", "");
		genearteFileFlag = true;


		idList = Arrays.asList(interfaceIdStr.split(",", -1));;
		interfaceIdList = new ArrayList<Long>();
		for (String string : idList) {
			if(!string.trim().isEmpty())
				interfaceIdList.add(Long.parseLong(string.trim()));
		}
		Collections.sort(interfaceIdList);

		inputFileFolderControlFileGeneration = "E:\\interface\\backend\\ControlFileGeneration\\";
		outputFileFolderControlFileGeneration = "E:\\interface\\backend\\ControlFileGeneration\\raw\\";
		moveOrgnlFilesControlFileGeneration = "E:\\interface\\backend\\ControlFileGeneration\\bkp\\";
		uploadFileLoc = "E:/interface/backend/ControlFileGeneration/raw/";

		interfaceFieldLookupConfQuery = "SELECT inter.interface_id_n,inter.name_v, attr.value_v FROM interface.ms_interface_attr attr INNER JOIN interface.ms_interface inter ON inter.interface_id_n=attr.interface_id_n where attr.name_v ='Field Lookup Conf' and inter.interface_id_n in ("+ interfaceIdStr +") order by inter.interface_id_n ;\n\n";
		connection = RDBMS.getDBConnection(PropType.RDBMS_144);
		rdbmsOperation = new RDBMSOperation();
	}


	public void start(String dateInFile, String fileName) throws Exception 
	{
		try
		{
//			if(genearteFileFlag)
//				generateFile(interfaceIdList, connection, dateInFile, inputFileFolderControlFileGeneration,  fileName );
//			prepareCtl();
//			uploadFiles(new Server(PropType.Server_251_PPK), uploadFileLoc);
			callApi();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public void end() throws SQLException {
		if(connection != null)
			connection.close();
		EntityOperations.closeInstance();
	}
	public void generateFile(List<Long> interfaceIds, Connection conn, String dateInFile, String dir, String fileName) throws Exception
	{
		rdbmsOperation.prepareFileForInterfaces(interfaceIds, conn, dateInFile, dir, fileName, 100, 1);
	}

	public void prepareCtl() throws Exception 
	{
		PrepareCTL prepareCTL = new PrepareCTL();
		prepareCTL.prepareCTL(inputFileFolderControlFileGeneration, outputFileFolderControlFileGeneration, moveOrgnlFilesControlFileGeneration);
	}

	public void uploadFiles(Server server, String uploadFilesPath) throws Exception
	{
		SshOperation sshOperation = null;
		List<InterfaceAttribute> interfaceAttributes = null;
		File filepath = null;

		filepath = new File(uploadFilesPath);
		if(!filepath.isDirectory() ) {
			System.out.println("uploadFilesPath files empty  :: " + uploadFilesPath);
			return;
		}
		else
		{
			//			for(String str : filepath.list())
			//				System.out.println(str);
		}

		if(filepath.listFiles().length != 0)
		{
			sshOperation = new SshOperation(server);
		}

		for (Long id : interfaceIdList)
		{
			interfaceAttributes = entityOperations.getInterfaceAttribute(id);

			for (File file : filepath.listFiles()) 
			{
				if(file.getName().startsWith(getInterfaceAttributeValue(interfaceAttributes, "Remote File")))
				{
					sshOperation.upload(file.getName(), getInterfaceAttributeValue(interfaceAttributes, "Remote Dir"), uploadFilesPath);
					file.delete();
				}
			}
		}
		if(sshOperation != null)
			sshOperation.disconnect();
		sshOperation = null;
	}

	public void callApi() throws Exception 
	{
		

		for (String id : idList)
		{
			long i = Long.parseLong(id);

			System.out.println("processFile service call :: " + i  );
			ThreadUtil.processFile(i);
			Thread.sleep(1 * apiWaitInSecond * 1000);

			System.out.println("processReceivedFiles service call :: " + i );
			ThreadUtil.processReceivedFiles(i);
			Thread.sleep(1 * apiWaitInSecond * 1000);

			Thread.sleep(1 * 10 * 1000);
			//			for (long i = from; i <= to; i++) 
			//			{
			//				System.out.println("prepareRejectionFile service call :: " + i );
			//				ThreadUtil.prepareRejectionFile(i);
			//				Thread.sleep(1 * second * 1000);
			//			}


			//		for (long i = from; i <= to; i++) 
			//		{
			//			System.out.println("pullDataToFile service call :: " + i );
			//			ThreadUtil.pullDataToFile(i);
			//			Thread.sleep(1 * 20 * 1000);
			//		}
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
