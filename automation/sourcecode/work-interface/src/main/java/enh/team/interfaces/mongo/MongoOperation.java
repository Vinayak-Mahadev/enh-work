package enh.team.interfaces.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.enh.util.MongoDB;
import com.finevm.enh.util.TestWriter;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class MongoOperation {


	TestWriter writer = TestWriter.getWriter("test.txt");
	DateTimeFormatter EEEE_dateTimeFormat = DateTimeFormat.forPattern("EEEE");
	SimpleDateFormat dd_MM_yyyy_DateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public void journeyPlanSummaryReport15055_Step_1(DBObject pjpConfigTemplate) {


		writer.writeln("[PjpPlanDetailsDump] Entry processRequest..");

		Long startTime = null;
		Long endTime = null;


		DB primaryDB = null;
		DB primaryChildDB = null;
		DBObject queryDbObject = null;
		DBObject projectionDbObject = null;
		DBObject orderBy = null;
		DBCursor cursor = null;
		List<Long> orgIDs = null;
		BasicDBObject primaryDBobj = null;
		BasicDBObject primaryChildDBobj = null;


		DateTime firstDateOfMonth =   null;
		DateTime lastDateOfMonth  =   null;


		DateTime currentDate  =   new DateTime(new Date());
		JSONObject writeJsonObj = null;
		//JSONObject mapOfWriteJsonObj = new JSONObject();
		JSONArray mapOfWriteJsonObj = new JSONArray();
		DateTimeFormatter EEE_dateTimeFormat = DateTimeFormat.forPattern("EEE");
		SimpleDateFormat dd_MM_yyyy_DateFormat = new SimpleDateFormat("dd-MM-yyyy");



		try
		{
			startTime = System.currentTimeMillis();



			if(pjpConfigTemplate.get("custom-date-format")!=null && pjpConfigTemplate.get("custom-date-value")!=null) {
				if(pjpConfigTemplate.get("custom-date-format").toString().equals("dd-MM-yyyy"))
					currentDate =  new DateTime(dd_MM_yyyy_DateFormat.parse(pjpConfigTemplate.get("custom-date-value").toString()));
			}
			else
				currentDate  =   new DateTime(new Date());

			firstDateOfMonth =   new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.dayOfMonth().getMinimumValue(), 0, 0);
			lastDateOfMonth  =   new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.dayOfMonth().getMaximumValue(), 0, 0);

			writer.writeln("CurrentDate  :  "+dd_MM_yyyy_DateFormat.format(currentDate.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(currentDate));
			writer.writeln("FirstDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(firstDateOfMonth.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(firstDateOfMonth));
			writer.writeln("LastDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(lastDateOfMonth.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(lastDateOfMonth));


			primaryDBobj = (BasicDBObject) (pjpConfigTemplate.get("Primary"));
			primaryDB = MongoDB.getMongoClient(primaryDBobj.getString("connection-id")).getDB(primaryDBobj.getString("schema-name"));

			queryDbObject = new BasicDBObject();

			for (Object obj : (BasicDBList)primaryDBobj.get("parameters")) {
				DBObject dbObj = (DBObject) obj;
				queryDbObject.put(dbObj.get("param-name").toString(), dbObj.get("param-value"));
			}

			projectionDbObject  = (DBObject) primaryDBobj.get("projection");
			orderBy = (DBObject) primaryDBobj.get("sort-by");
			writer.writeln("primary queryDbObject "+ queryDbObject);
			cursor = primaryDB.getCollection(primaryDBobj.getString("collection-name")).find(queryDbObject, projectionDbObject).sort(orderBy);
			orgIDs = new ArrayList<Long>();

			if(cursor==null || cursor.count()==0)
			{
				writer.writeln("No org found...");
				return;
			}
			for (DBObject dbObject : cursor) {
				orgIDs.add(Long.parseLong(dbObject.get("_id")!=null ? dbObject.get("_id").toString() : "0"));
			}

			writer.writeln("total orgIDs ::: "+orgIDs.size());
			writer.writeln(orgIDs+"\n\n\n");

			//-------------------------------------- Primary end ------------------------------------------

			//-------------------------------------- Primary child start ------------------------------------------


			primaryChildDBobj = (BasicDBObject) (pjpConfigTemplate.get("Primary-child"));
			primaryChildDB = MongoDB.getMongoClient(primaryChildDBobj.getString("connection-id")).getDB(primaryChildDBobj.getString("schema-name"));


			queryDbObject = new BasicDBObject();
			for (Object obj : (BasicDBList)primaryChildDBobj.get("parameters")) {
				DBObject dbObj = (DBObject) obj;
				queryDbObject.put(dbObj.get("param-name").toString(), dbObj.get("param-value"));
			}
			queryDbObject.put(primaryChildDBobj.getString("Primary-result-in-query"), new BasicDBObject("$in",orgIDs));

			//queryDbObject.put("schedule_dt",new BasicDBObject("$gte",firstDateOfMonth.toDate()).append("$lte",currentDate.toDate() ));

			projectionDbObject  = (DBObject) primaryChildDBobj.get("projection");
			writer.writeln("Primary child  QueryDBObject    "+queryDbObject);

			orderBy = (DBObject) primaryChildDBobj.get("sort-by");
			cursor = primaryChildDB.getCollection(primaryChildDBobj.getString("collection-name")).find(queryDbObject, projectionDbObject).sort(orderBy);

			if(cursor==null || cursor.count()==0)
			{
				writer.writeln("No primaryChild data found...");

			}
			else {
				writer.writeln("\n\nprimary Child cursor count ::: "+cursor.count());
				writer.writeln("primary child  data   :   ");
				for (DBObject object : cursor) {
					BasicDBObject dbObject = (BasicDBObject) object;
					writeJsonObj = new JSONObject();
					//writer.writeln("primary child cursor dbObject  "+ dbObject);
					writeJsonObj.put("region", dbObject.get("region_name"));
					writeJsonObj.put("area", dbObject.get("sub_area_name"));
					writeJsonObj.put("sales_area", dbObject.get("sales_area_name"));
					writeJsonObj.put("cluster", dbObject.get("cluster_name"));
					writeJsonObj.put("micro_cluster", dbObject.get("micro_cluster_name"));
					writeJsonObj.put("mpc_code", dbObject.get("mpc_short_code"));
					writeJsonObj.put("spv_code", dbObject.get("supervisor_code"));
					writeJsonObj.put("spv_name", dbObject.get("supervisor_mobile_number"));
					writeJsonObj.put("cso_code", dbObject.get("cso_code"));
					writeJsonObj.put("outlet_code", dbObject.get("outlet_org_id"));

					if(dbObject.get("cso_code")!=null && dbObject.get("cso_code").toString().isEmpty()) {
						queryDbObject = new BasicDBObject();
						queryDbObject.put("operator_id", dbObject.get("cso_code").toString());
						projectionDbObject = new BasicDBObject();
						projectionDbObject.put("operator_id", 1);
						projectionDbObject.put("operator_nm", 1);
						projectionDbObject.put("_id", 1);
						DBObject resultDBobj = primaryChildDB.getCollection("user_dtls_dump").findOne(queryDbObject);
						if(resultDBobj!=null && resultDBobj.get("operator_nm")!=null && !resultDBobj.get("operator_nm").toString().isEmpty()) {
							writeJsonObj.put("cso_name",resultDBobj.get("operator_nm"));
							writer.writeln("resultDBobj.get(\"operator_nm\")     "+resultDBobj.get("operator_nm"));
						}
						else {
							writeJsonObj.put("cso_name", "");
						}
					}
					else {
						writeJsonObj.put("cso_name", "");
					}


					//mapOfWriteJsonObj.put(dbObject.get("outlet_org_id").toString(), writeJsonObj);

					long orgId = Long.parseLong(writeJsonObj.get("outlet_code").toString());
					journeyPlanSummaryReport15055_Step_3_find_org_id_from_beat_schedule(orgId, writeJsonObj, pjpConfigTemplate);
					journeyPlanSummaryReport15055_Step_2_find_org_id_from_beat_defn(orgId, writeJsonObj, pjpConfigTemplate);

					mapOfWriteJsonObj.put(writeJsonObj);
					//break;
				}
			}
			writer.writeln(mapOfWriteJsonObj);
			writer.writeln("\n\n\n");
			//-------------------------------------- primary child end ------------------------------------------


		}
		catch(Exception exception)
		{
			writer.writeln("[PjpPlanDetailsDump] - Exception :: " + exception.getMessage());
			exception.printStackTrace();
		}
		finally
		{

			primaryDB = null;
			cursor = null;
			orgIDs = null;
			queryDbObject = null;
			projectionDbObject = null;
			endTime = System.currentTimeMillis();
			writer.writeln("PjpPlanDetailsDump : Exit processRequest.. "+(endTime - startTime)+"\n\n\n\n");
			writeJsonObj = null;
			endTime = null;
			startTime = null;
			MongoDB.closeAllConnection();
		}



	}


	public void journeyPlanSummaryReport15055_Step_2_find_org_id_from_beat_defn(final long orgId, final JSONObject info, final DBObject pjpConfigTemplate) {


		writer.writeln("[PjpPlanDetailsDump] Entry processRequest..");

		Long startTime = null;
		Long endTime = null;

		DB dB = null;
		DBObject queryDbObject = null;
		DBObject projectionDbObject = null;
		DBObject orderBy = null;
		DBCursor cursor = null;
		BasicDBObject templateObj = null;

		DateTime firstDateOfMonth =   null;
		DateTime lastDateOfMonth  =   null;
		DateTime currentDate  =   new DateTime(new Date());

		DateTimeFormatter EEE_dateTimeFormat = DateTimeFormat.forPattern("EEE");
		SimpleDateFormat dd_MM_yyyy_DateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try
		{
			startTime = System.currentTimeMillis();

			if(pjpConfigTemplate.get("custom-date-format")!=null && pjpConfigTemplate.get("custom-date-value")!=null) {
				if(pjpConfigTemplate.get("custom-date-format").toString().equals("dd-MM-yyyy"))
					currentDate =  new DateTime(dd_MM_yyyy_DateFormat.parse(pjpConfigTemplate.get("custom-date-value").toString()));
			}
			else
				currentDate  =   new DateTime(new Date());

			firstDateOfMonth =   new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.dayOfMonth().getMinimumValue(), 0, 0);
			lastDateOfMonth  =   new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.dayOfMonth().getMaximumValue(), 0, 0);

			writer.writeln("CurrentDate  :  "+dd_MM_yyyy_DateFormat.format(currentDate.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(currentDate));
			writer.writeln("FirstDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(firstDateOfMonth.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(firstDateOfMonth));
			writer.writeln("LastDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(lastDateOfMonth.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(lastDateOfMonth));


			templateObj = (BasicDBObject) (pjpConfigTemplate.get("Tertiary"));
			dB = MongoDB.getMongoClient(templateObj.getString("connection-id")).getDB(templateObj.getString("schema-name"));

			queryDbObject = new BasicDBObject();
			queryDbObject.put("start_dt", new BasicDBObject("$gte",currentDate.toDate()));
			queryDbObject.put("end_dt", new BasicDBObject("$lte",lastDateOfMonth.toDate()));
			queryDbObject.put("retailers", new BasicDBObject("$elemMatch",new BasicDBObject("org_id", orgId)));

			if(templateObj.get("parameters")!=null && templateObj.get("parameters") instanceof BasicDBList) {
				for (Object obj : (BasicDBList)templateObj.get("parameters")) {
					DBObject dbObj = (DBObject) obj;
					queryDbObject.put(dbObj.get("param-name").toString(), dbObj.get("param-value"));
				}
			}

			projectionDbObject  = (DBObject) templateObj.get("projection");
			orderBy = (DBObject) templateObj.get("sort-by");
			writer.writeln("queryDbObject ::: "+ queryDbObject);
			writer.writeln("projectionDbObject ::: "+ projectionDbObject);
			writer.writeln("orderBy ::: "+ orderBy+"\n\n");

			cursor = dB.getCollection(templateObj.getString("collection-name")).find(queryDbObject, projectionDbObject).sort(orderBy);


			if(cursor==null || cursor.count()==0)
			{
				writer.writeln("No beat_defn data found...");
				return;
			}
			writer.writeln("\n\n\n");
			writer.writeln("cursor beat_defn count ::: "+cursor.count());
			for (DBObject object : cursor) {
				BasicDBObject dbObject = (BasicDBObject) object;
				writer.writeln(dbObject);
			}

			writer.writeln("\n\n\n");


		}
		catch(Exception exception)
		{
			writer.writeln("[PjpPlanDetailsDump] - Exception :: " + exception.getMessage());
			exception.printStackTrace();
		}
		finally
		{

			dB = null;
			cursor = null;
			queryDbObject = null;
			projectionDbObject = null;
			endTime = System.currentTimeMillis();
			writer.writeln("PjpPlanDetailsDump : Exit processRequest.. "+(endTime - startTime)+"\n\n\n\n");
			endTime = null;
			startTime = null;
		}




	}


	public void journeyPlanSummaryReport15055_Step_3_find_org_id_from_beat_schedule(final long orgId, final JSONObject info, final DBObject pjpConfigTemplate) {


		writer.writeln("[PjpPlanDetailsDump] Entry processRequest..");

		Long startTime = null;
		Long endTime = null;

		DB dB = null;
		DBObject queryDbObject = null;
		DBObject projectionDbObject = null;
		DBObject orderBy = null;
		DBCursor cursor = null;
		Set<Long> orgIDs = null;
		BasicDBObject templateObj = null;

		DateTime firstDateOfMonth =   null;
		DateTime lastDateOfMonth  =   null;
		DateTime currentDate  =   new DateTime(new Date());

		DateTimeFormatter EEE_dateTimeFormat = DateTimeFormat.forPattern("EEE");
		SimpleDateFormat dd_MM_yyyy_DateFormat = new SimpleDateFormat("dd-MM-yyyy");

		try
		{
			startTime = System.currentTimeMillis();

			if(pjpConfigTemplate.get("custom-date-format")!=null && pjpConfigTemplate.get("custom-date-value")!=null) {
				if(pjpConfigTemplate.get("custom-date-format").toString().equals("dd-MM-yyyy"))
					currentDate =  new DateTime(dd_MM_yyyy_DateFormat.parse(pjpConfigTemplate.get("custom-date-value").toString()));
			}
			else
				currentDate  =   new DateTime(new Date());

			firstDateOfMonth =   new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.dayOfMonth().getMinimumValue(), 0, 0);
			lastDateOfMonth  =   new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.dayOfMonth().getMaximumValue(), 0, 0);

			writer.writeln("CurrentDate  :  "+dd_MM_yyyy_DateFormat.format(currentDate.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(currentDate));
			writer.writeln("FirstDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(firstDateOfMonth.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(firstDateOfMonth));
			writer.writeln("LastDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(lastDateOfMonth.toDate()) + "  |   Day  :  "+EEE_dateTimeFormat.print(lastDateOfMonth));


			templateObj = (BasicDBObject) (pjpConfigTemplate.get("Secondary"));
			dB = MongoDB.getMongoClient(templateObj.getString("connection-id")).getDB(templateObj.getString("schema-name"));

			queryDbObject = new BasicDBObject();
			queryDbObject.put("schedule_dt",new BasicDBObject("$gte",firstDateOfMonth.toDate()).append("$lte",currentDate.toDate() ));
			queryDbObject.put("org_id", orgId);

			for (Object obj : (BasicDBList)templateObj.get("parameters")) {
				DBObject dbObj = (DBObject) obj;
				queryDbObject.put(dbObj.get("param-name").toString(), dbObj.get("param-value"));
			}

			projectionDbObject  = (DBObject) templateObj.get("projection");
			orderBy = (DBObject) templateObj.get("sort-by");
			writer.writeln("primary queryDbObject ::: "+ queryDbObject);
			cursor = dB.getCollection(templateObj.getString("collection-name")).find(queryDbObject,projectionDbObject).sort(orderBy);

			orgIDs = new HashSet<Long>();

			if(cursor==null || cursor.count()==0)
			{
				writer.writeln("No org found...");
				return;
			}
			for (DBObject object : cursor) {
				BasicDBObject dbObject = (BasicDBObject) object;
				writer.writeln(dbObject);
			}

			writer.writeln("total orgIDs ::: "+orgIDs.size());
			writer.writeln(orgIDs+"\n\n\n");
			writer.writeln("\n\n\n");


		}
		catch(Exception exception)
		{
			writer.writeln("[PjpPlanDetailsDump] - Exception :: " + exception.getMessage());
			exception.printStackTrace();
		}
		finally
		{

			dB = null;
			cursor = null;
			orgIDs = null;
			queryDbObject = null;
			projectionDbObject = null;
			endTime = System.currentTimeMillis();
			writer.writeln("PjpPlanDetailsDump : Exit processRequest.. "+(endTime - startTime)+"\n\n\n\n");
			endTime = null;
			startTime = null;
		}




	}



	/**
	 * <p>This method get the product_properties object with product_values's addnl_sl_ctgr details</p>
	 * 
	 * @param prdIdSet
	 * @return responceDBMap
	 * @author VINAYAK-MAHADEV
	 * @email vinay.nagaraj@enhancesys.com
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,DBObject> getProductAddnlSlsDeatils(DB db, Set<Long> prdIdSet ) {

		String prdId = "prd_id";
		DBObject productPro = null;
		DBCollection productProCol = null;
		DBCollection productValCol = null;
		Map<String,DBObject> responceDBMap = null;
		DBObject childDbObject = null;
		Long prdCtgrRes = null;
		List<Long> addnlSlsList = null;
		DBObject queryDBObject = null;
		DBCursor productProCur = null;
		DBCursor productValCur = null;
		DBObject addnlSlCtgrDBObj = null;
		DBCursor childProductValCur = null;
		List<DBObject> childList = null;
		DBObject productValDbObj = null;

		try
		{

			//db = TokuUser.adminMongoClient.getDB(S_PRODUCT);

			queryDBObject = new BasicDBObject();
			queryDBObject.put(prdId,new BasicDBObject("$in", prdIdSet));
			productProCol = db.getCollection("product_properties");
			productProCur = productProCol.find(queryDBObject);
			responceDBMap = new HashMap<String, DBObject>();

			while (productProCur.hasNext()) 
			{
				productPro = productProCur.next();
				if(productPro.get("prd_ctgr")!=null)
					prdCtgrRes = (Long) productPro.get("prd_ctgr");

				if(prdCtgrRes!=null)
				{
					productValCol = db.getCollection("product_values");
					queryDBObject = new BasicDBObject();				
					queryDBObject.put("_id", prdCtgrRes);
					productValCur = productValCol.find(queryDBObject);

					while (productValCur.hasNext()) 
					{
						childList = new ArrayList<DBObject>();
						addnlSlCtgrDBObj = (DBObject) productValCur.next();
						if(addnlSlCtgrDBObj.get("addnl_sls")!=null){
							addnlSlsList = (List<Long>) addnlSlCtgrDBObj.get("addnl_sls");

							if(addnlSlsList!=null && (!addnlSlsList.isEmpty())){

								queryDBObject = new BasicDBObject();
								queryDBObject.put("addnl_sl_ctgr", new BasicDBObject().append("$in", addnlSlsList));

								childProductValCur = db.getCollection("product_values").find(queryDBObject);
								if(childProductValCur!=null){
									while (childProductValCur.hasNext()) {

										productValDbObj = (DBObject) childProductValCur.next();
										childDbObject = new BasicDBObject();
										childDbObject.put("addnl_sl_ctgr", productValDbObj.get("addnl_sl_ctgr"));
										childDbObject.put("addnl_sl_length", productValDbObj.get("addnl_sl_length"));
										childDbObject.put("description", productValDbObj.get("description"));
										childDbObject.put("is_mandatory", productValDbObj.get("is_mandatory"));
										childDbObject.put("addnl_sl_type", productValDbObj.get("addnl_sl_type"));
										childList.add(childDbObject);
									}
								}
							}
						}
					}
					productPro.put("result", childList);

				}
				responceDBMap.put(productPro.get(prdId).toString(), productPro);
			}


			System.out.println(responceDBMap);


		} 
		catch (Exception e)
		{
			e.printStackTrace();
			responceDBMap = new HashMap<String,DBObject>();

		}
		finally
		{

			prdId = null;
			productPro = null;
			productProCol = null;
			productValCol = null;
			responceDBMap = null;
			childDbObject = null;
			prdCtgrRes = null;
			addnlSlsList = null;
			queryDBObject = null;
			productProCur = null;
			productValCur = null;
			addnlSlCtgrDBObj = null;
			childProductValCur = null;
			childList = null;
			productValDbObj = null;
			db = null;

		}

		return responceDBMap;

	}

	/**
	 * <p>This method takes template as per template it write the data</p>
	 * @author Vinayak-Mahadev
	 * @param db
	 * @param template
	 * @param requiredFields
	 * @return
	 */
	public boolean productCurrentStockInGivenOrg(DB db, String template, List<String> requiredFields) {


		//template = readFile(new File("E:\\vm\\project\\finevm\\devp\\Fine-VM\\commons\\mongo\\test\\template\\writeFile\\sample.json"));
		template = readFile(new File("E:\\vm\\project\\finevm\\devp\\Fine-VM\\commons\\mongo\\test\\template\\writeFile\\stock_config.json"));



		//System.out.println(findByIds(db, new org.json.JSONObject(template).getJSONObject("products")));
		//System.out.println(findByIds(db, new org.json.JSONObject(template).getJSONObject("orgDetails")));

		getStockCount(db, new org.json.JSONObject(template));


		return false;
	}

	public List<DBObject> getStockCount(DB db, org.json.JSONObject jsonObj) 
	{
		List<DBObject> responceList = new ArrayList<DBObject>();
		DBObject tempDbObj = null;
		org.json.JSONArray prdIdsArray = null;
		org.json.JSONArray orgIdsArray = null;

		BasicDBObject dbquery = null;

		if(jsonObj.getJSONArray("prd_ids")!=null) 
			prdIdsArray = jsonObj.getJSONArray("prd_ids");
		if(jsonObj.getJSONArray("org_ids")!=null) 	
			orgIdsArray = jsonObj.getJSONArray("org_ids");


		Long prd_id = null;
		Long org_id = null;

		DBCollection dbCollection = db.getMongo().getDB(jsonObj.getString("dbName")).getCollection(jsonObj.getString("collectionName"));

		for (int i=0; i<prdIdsArray.length(); i++){ 

			prd_id = prdIdsArray.getLong(i);
			for (int j=0; j<orgIdsArray.length(); j++){ 
				org_id = orgIdsArray.getLong(j);

				dbquery = new BasicDBObject();

				dbquery.put("org_id", org_id);
				dbquery.put("prd_id", prd_id);
				//rng_stock
				tempDbObj = dbCollection.findOne(dbquery);


				System.out.println("ORG_ID = "+org_id+"   PRD_ID = " +prd_id+"   Stock Range = "+tempDbObj.get("rng_stock"));

				responceList.add(tempDbObj);


			} 



		} 





		return responceList;
	}

	public List<DBObject> findByIds(DB db, org.json.JSONObject jsonObject) {

		List<Long> list  = new ArrayList<Long>();
		List<DBObject> responceList = new ArrayList<DBObject>();

		DBCursor responceCursor = null;
		DBObject tempDBobj = null;

		BasicDBObject inQuery = null;

		org.json.JSONObject tempJsonObj =null; 

		org.json.JSONArray getValuesArray = null;
		org.json.JSONArray idsJsonArray = null;


		if(jsonObject.getJSONArray("getValues")!=null) 
			getValuesArray = jsonObject.getJSONArray("getValues");
		if(jsonObject.getJSONArray("ids")!=null) 	
			idsJsonArray = jsonObject.getJSONArray("ids");


		if (idsJsonArray != null) { 
			for (int i=0;i<idsJsonArray.length();i++){ 
				list.add(idsJsonArray.getLong(i));
			} 
		} 


		inQuery = new BasicDBObject();
		inQuery.put("_id", new BasicDBObject("$in",list));

		responceCursor = db
				.getMongo()
				.getDB(jsonObject.getString("dbName"))
				.getCollection(jsonObject.getString("collectionName")).find(inQuery);

		while (responceCursor.hasNext()) 
		{
			DBObject dbObject = responceCursor.next();
			tempDBobj = new BasicDBObject();

			for (int i=0;i<getValuesArray.length();i++)
			{ 
				tempJsonObj = getValuesArray.getJSONObject(i);
				tempDBobj.put(tempJsonObj.getString("key"), dbObject.get(tempJsonObj.getString("value")));

			} 

			responceList.add(tempDBobj);
		}
		return responceList;
	}

	public String  readFile(File file) {
		String line=null;
		String responce = "";
		try {
			reader =new BufferedReader( new FileReader(file));
			while ((line = reader.readLine()) != null) 
				responce=responce+line;
		} catch (IOException e) {
			responce = null;
			e.printStackTrace();
		} 	
		return responce;
	} 




	static String csvFilelocation = "src/main/resources/file/csv/";
	static String csv  = ".csv";
	static String eol = System.getProperty("line.separator");
	String delimiter = "|";
	private BufferedReader  reader ;


}
