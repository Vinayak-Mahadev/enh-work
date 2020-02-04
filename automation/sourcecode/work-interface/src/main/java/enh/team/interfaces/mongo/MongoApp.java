package enh.team.interfaces.mongo;

import java.io.File;

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
		String pathname = IWorkConstants.FILE_OPERATION_LOC+"\\PJP_Planning_Details.json";
		File file = new File(pathname);
		String jsonStr = FileOperation.readFileWithoutNewLine(file);
		Object o = com.mongodb.util.JSON.parse(jsonStr);

		DBObject pjpConfigTemplate = (DBObject) o;

		MongoOperation operation = new MongoOperation();

		operation.journeyPlanSummaryReport15055_Step_1(pjpConfigTemplate);
		//operation.journeyPlanSummaryReport15055_Step_3_find_org_id_from_beat_schedule(294826l, null, pjpConfigTemplate);
		//		operation.journeyPlanSummaryReport15055_Step_2_find_org_id_from_beat_defn(294826l, null, pjpConfigTemplate);


		// end--------------------- journeyPlanSummaryReport15055 -------------------------


	}
}
