package enh.team.interfaces.test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.finevm.enh.interfaces.entities.EntityOperations;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;
import com.finevm.enh.util.Server;

import enh.team.interfaces.rdbms.RDBMSOperation;
import enh.team.interfaces.soapservice.PrepareCTL;
import enh.team.interfaces.ssh.SshOperation;

public class HadoopInterfaceTest 
{

	//	private EntityOperations entityOperations; 
	private RDBMSOperation rdbmsOperation;
	private Connection  connection;
	private Server server;
	boolean genearteFileFlag;
	boolean prepareCtlFlag;
	boolean uploadFilesFlag;
	boolean callApiFlag;
	private List<Long> interfaceIdList = null;
	private List<String> idList = null;
	private Map<Long, String> remoteFileMap = null;
	private Map<Long, String> remoteDirMap = null;

	private String controlFileGenerationPath = null;
	private String outputFileFolderControlFileGeneration = null;
	private String inputFileFolderControlFileGeneration = null;
	private String moveOrgnlFilesControlFileGeneration = null;
	private String uploadFileLoc = null;
	private  String wsdlUrl;
	private  String userName;
	private  String password;
	long apiWaitInSecond = 3l;
	private int filedataCount, noOfFiles;
	private boolean callProcessFileApi;
	private boolean callProcessReceivedFilesApi;
	private boolean callRejectionApi;
	public static void main(String[] args) throws Exception
	{


		//		String interfaceIdStr = "1165 ,1166 ,1167 ,1168 ,1169 ,1170 ,1171 ,1172 ,1173 ,1174 ,1175 ,1176 ,1177 ,1178 ,1179 ,1180 ,1181 ,1182 ,1183";

		String interfaceIdStr = "1066,1067,1068,1069,1070,1071,1076,1077,1080,1081,1078,1079";
//		String interfaceIdStr = "1066,1071";

		HadoopInterfaceTest hitest = new HadoopInterfaceTest(interfaceIdStr);;

		String dateInFile = null;

		String fileName   = null;


		
//		dateInFile = "20201001";
//		fileName   = "20200321090001_101.csv";
//		hitest.start(dateInFile, fileName);

		dateInFile = "20201002";
		fileName   = "20200321090001_102.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20201003";
		fileName   = "20200321090001_103.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20201004";
		fileName   = "20200321090001_104.csv";
		hitest.start(dateInFile, fileName);

		dateInFile = "20201005";
		fileName   = "20200321090001_105.csv";
		hitest.start(dateInFile, fileName);

		


		hitest.end();
	}

	public HadoopInterfaceTest(String interfaceIdStr) throws Exception 
	{
		filedataCount = 100;
		noOfFiles = 1;
		wsdlUrl   = "http://50.17.26.200:8080/IntegrationServices/IntegrationManagement?wsdl";  
//		wsdlUrl   = "http://192.168.2.143:8080/IntegrationServices/IntegrationManagement?wsdl";  
		userName  = "admin";                                                                     
		password  = "admin";  
		apiWaitInSecond = 10l;
		ThreadUtil.setProperties(wsdlUrl, userName, password);
//
//		callApiFlag = true;
//		callProcessFileApi = true;
//		callProcessReceivedFilesApi = true;
//		callRejectionApi = true;
//
//		genearteFileFlag = true;
//		prepareCtlFlag = true;
//		uploadFilesFlag = true;


		//		connection = RDBMS.getDBConnection(PropType.RDBMS_144);
		connection = RDBMS.getDBConnection(PropType.RDBMS_KPI_INTERFACE_CLOUD);

		//		server = new Server(PropType.Server_251_PPK);
		server = new Server(PropType.Server_KPI_INTERFACE_CLOUD);

		interfaceIdStr = interfaceIdStr.replace(" ", "").replace("\t", "");


		idList = Arrays.asList(interfaceIdStr.split(",", -1));;
		interfaceIdList = new ArrayList<Long>();

		for (String string : idList) 
			if(!string.trim().isEmpty())
				interfaceIdList.add(Long.parseLong(string.trim()));
		
		Collections.sort(interfaceIdList);

		controlFileGenerationPath = "E:/interface/backend/ControlFileGeneration";
		
		inputFileFolderControlFileGeneration = controlFileGenerationPath + "/";
		outputFileFolderControlFileGeneration = controlFileGenerationPath + "/raw/";
		moveOrgnlFilesControlFileGeneration = controlFileGenerationPath + "/bkp/";
		uploadFileLoc = controlFileGenerationPath + "/raw/";

		rdbmsOperation = new RDBMSOperation();
		System.out.println(server);
		remoteFileMap = rdbmsOperation.getInterfaceAttrValue(connection, "Remote File", interfaceIdList);
		remoteDirMap = rdbmsOperation.getInterfaceAttrValue(connection, "Remote Dir", interfaceIdList);
		//entityOperations = EntityOperations.getInstance();
		//entityOperations = EntityOperations.getInstance("com.finevm.enh.interfaces.entities.kpi.interface.cloud");
		rdbmsOperation.printFieldLookupConf(connection, interfaceIdStr, "delete", false);
	}


	public void start(String dateInFile, String fileName) throws Exception 
	{
		try
		{
			if(genearteFileFlag)
				generateFile(interfaceIdList, connection, dateInFile, inputFileFolderControlFileGeneration,  fileName );
			if(prepareCtlFlag)
				prepareCtl();
			if(uploadFilesFlag)
				uploadFiles(server, uploadFileLoc);
			if(callApiFlag)
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
		rdbmsOperation.prepareFileForInterfaces(interfaceIds, conn, dateInFile, dir, fileName, filedataCount, noOfFiles);
	}

	public void prepareCtl() throws Exception 
	{
		PrepareCTL prepareCTL = new PrepareCTL();
		prepareCTL.prepareCTL(inputFileFolderControlFileGeneration, outputFileFolderControlFileGeneration, moveOrgnlFilesControlFileGeneration);
	}

	public void uploadFiles(Server server, String uploadFilesPath) throws Exception
	{
		SshOperation sshOperation = null;
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

			for (File file : filepath.listFiles()) 
			{
				if(file.getName().startsWith(remoteFileMap.get(id)))
				{
					sshOperation.upload(file.getName(), remoteDirMap.get(id), uploadFilesPath);
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

			if(callProcessFileApi)
			{
				System.out.println("processFile service call :: " + i  );
				ThreadUtil.processFile(i);
				Thread.sleep(1 * apiWaitInSecond * 1000);
			}
			if(callProcessReceivedFilesApi) 
			{
				System.out.println("processReceivedFiles service call :: " + i );
				ThreadUtil.processReceivedFiles(i);
				Thread.sleep(1 * apiWaitInSecond * 1000);
			}

			if(callRejectionApi) 
			{
				Thread.sleep(1 * apiWaitInSecond * 1000);
				System.out.println("prepareRejectionFile service call :: " + i );
				ThreadUtil.prepareRejectionFile(i);
			}
			//			for (long i = from; i <= to; i++) 
			//			{
			//				System.out.println("prepareRejectionFile service call :: " + i );
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
}
