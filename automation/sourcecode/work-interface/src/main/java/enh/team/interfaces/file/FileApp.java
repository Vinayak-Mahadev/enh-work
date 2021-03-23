package enh.team.interfaces.file;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.enh.util.IWorkConstants;

@SuppressWarnings("unused")
public class FileApp 
{
	private static FileOperation fileOperation = new FileOperation();

	public static void main(String[] args) throws Exception 
	{
		//new FileApp().deleteLineInFile();
		//new FileApp().loadkey();
		//new FileApp().startFileSearch();
		//new FileApp().checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile();
		//System.out.println("delete from interface.sp_alloc_dump where file_id = ?::character varying".split("#").length);


		//System.out.println("123|vinay|abc".split("\\|").length);
		//System.out.println("123||abc".split("\\|").length);
		//System.out.println("123|vinay||".split("\\|", -1).length);

		//fileOperation.printDecimalFormatNumber(50);


		//fileOperation.readFileCountData("E:\\interface\\backend\\ControlFileGeneration\\bkp\\total_revenue_20200615090001_103.csv", "\\|", "DATE|MICRO|SITE_ID|REVENUE_TYPE|REVENUE_TOTAL", 4);

		
		//checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile();

		//fileOperation.fileNamingWithSequence("C:\\Users\\vinayak\\Downloads\\ppdt\\ThematicApperceptionTest\\", "C:\\Users\\vinayak\\Downloads\\ppdt\\ThematicApperceptionTest\\bkp\\", "TAT-", ".jpg");


		/**/
	
	}



	static void checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile() throws Exception{



		//		String csvFileAbPath =  "E:\\interface\\work\\enh-work\\daily_works\\2020-01\\___29_visit_org_dtls\\Visit_Organization_Detail_20200128.csv";
		//		String outputFileAbPath = "E:\\interface\\work\\enh-work\\daily_works\\2020-01\\___29_visit_org_dtls\\Visit_Organization_Detail.log";
		//		String csvDelimiter = "\\|";
		//		String header = "SCHEDULEDATETIME|VIsitDatetime|Channel|Region|Area|Sales Area|Cluster|Micro Cluster|Additional Territory|TerritoryID|VISIT_TYPE|BeatPlanID|BeatPlanName|VisitorOrgType|VisitorORGID|VisitorSaldoMOBO Org ID|VisitorOrgName|VisitedOrgType|VisitedORGID|VisitedSaldoMOBO Org ID|VisitedOrgName|VisitedOrg Address|OperatorType|OperatorId|UserName|Operator Full Name|StartDateTime|EndDatetime|Time On Beat|Comments|Planned Distance(in KM)|Activity Name|Activity Status|PRODUCT_CATEGORY|PRODUCT_BRAND|PRODUCT_CODE|PRODUCT_NAME|Total Sales Qty|Total Sales Value|Org Location|Activity Location|Deviation Distance(in KM)|Reason";
		//		String columnName = "Reason";
		//
		//		fileOperation.checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile(header, columnName, csvFileAbPath, outputFileAbPath, csvDelimiter);
		//



		String csvFileAbPath =  "C:\\Users\\vinayak\\Downloads\\hierarchy_master_02_20200708_002.csv";
		String outputFileAbPath = "C:\\Users\\vinayak\\Downloads\\hierarchy_master_02_20200708_002.csv.log";
		String csvDelimiter = "\\|";
		String header = "node_id|node_name|org_id|parent_node_id|status|account_id|account_type|Organization Type|Territory|Organization Territory|Entity Type|Country|Region|Position|City|Sales Networks|District|Cluster|Sector|Level|feature1|feature2|feature3|supervisor_mono|additional_region";
		String columnName = "additional_region";

		fileOperation.checkCsvColumnDataIfValueNotEmptyWriteInFileWithLineNumberOfFile(header, columnName, csvFileAbPath, outputFileAbPath, csvDelimiter);


		System.out.println("done");

	}

	void startFileSearch() throws Exception
	{
		JSONObject jsonObject  = new JSONObject();
		JSONArray extenInFiles = new JSONArray();
		JSONArray skipFiles    = new JSONArray();



		int skiptimes = 0;
		String keyLocation      = "D:\\vm\\github\\Fine-VM\\commons\\fileOperation\\loadKeys\\result.txt";
		//String keyLocation    = "D:/interface/backend/indo-c/snd-integration/conf/common.properties";

		String searchDir      = "D:/interface/backend/indo-c/snd-integration";
		String outputFileName = IWorkConstants.FILE_OPERATION_SEARCH_OUTPUT_LOC+"test.txt";


		extenInFiles.put(".xml");
		extenInFiles.put(".cfg");
		extenInFiles.put(".java");
		extenInFiles.put(".properties");



		skipFiles.put("common.properties");
		skipFiles.put("common_encrypted.properties");
		skipFiles.put("IntegrationConstants.java");
		skipFiles.put("errorCode.properties");
		skipFiles.put("errorMessage_UK.properties");
		skipFiles.put(".ppk");
		skipFiles.put(".json");
		skipFiles.put(".sh");
		skipFiles.put(".doc");
		skipFiles.put(".war");
		skipFiles.put(".jar");
		skipFiles.put(".ear");
		skipFiles.put(".tm");
		skipFiles.put(".xlsx");


		jsonObject.put("outputFileName", outputFileName);
		jsonObject.put("keyLocation", keyLocation);
		jsonObject.put("searchDir", searchDir);
		jsonObject.put("extenInFiles", extenInFiles);
		jsonObject.put("skipFiles", skipFiles);
		jsonObject.put("skiptimes", skiptimes);


		new FileOperation().startFileSearch(jsonObject);

	}

	void deleteLineInFile() throws Exception{

		String inputFile  = IWorkConstants.DELETE_LINE_IN_FILE_INPUT;
		String deleteFile = IWorkConstants.DELETE_LINE_IN_FILE_DELETE;
		String outputFile = IWorkConstants.DELETE_LINE_IN_FILE_OUTPUT;


		JSONObject jsonObject  = new JSONObject();
		jsonObject.put("inputFile", inputFile);
		jsonObject.put("deleteFile", deleteFile);
		jsonObject.put("outputFile", outputFile);

		new FileOperation().deleteLineInFile(jsonObject);
	}

	void loadkeyFromPropertiesFile() throws Exception
	{
		String keyLocation    = "D:\\interface\\work\\daily_works\\2019-09\\05___common.properties";
		boolean ifNeedAsAFile = true;

		new FileOperation().loadKeys(keyLocation, ifNeedAsAFile);
	}
}
