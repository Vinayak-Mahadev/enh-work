package enh.team.interfaces.mongo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finevm.enh.util.IWorkConstants;
import com.mongodb.DBObject;

import enh.team.interfaces.file.FileOperation;

public class MongoApp 
{
	public static void main(String[] args) throws Exception
	{
		//  start--------------------- test db-------------------------

		//		MongoClient mongoClient = MongoDB.getMongoClient(PropType.MONGO_153_22026);
		//
		//		System.out.println(mongoClient);
		//		System.out.println(mongoClient.getDatabaseNames());
		//  end--------------------- test db -------------------------



		//  start--------------------- journeyPlanSummaryReport15055 -------------------------
		//
		String pathname = IWorkConstants.FILE_OPERATION_LOC+"\\PJP_TEST.json";
		File file = new File(pathname);
		String jsonStr = FileOperation.readFileWithoutNewLine(file);
		Object o = com.mongodb.util.JSON.parse(jsonStr);

		DBObject pjpConfigTemplate = (DBObject) o;


		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String givenDateStr = "15-11-2019";
		Date convertedDate = dateFormat.parse(givenDateStr);



		MongoOperation operation = new MongoOperation();

		operation.journeyPlanSummaryReport15055(pjpConfigTemplate, convertedDate);




		// end--------------------- journeyPlanSummaryReport15055 -------------------------


	}
}
