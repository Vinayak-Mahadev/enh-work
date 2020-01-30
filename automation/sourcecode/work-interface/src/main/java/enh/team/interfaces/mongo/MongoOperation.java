package enh.team.interfaces.mongo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.enh.util.MongoDB;
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class MongoOperation {


	DateTimeFormatter EEEE_dateTimeFormat = DateTimeFormat.forPattern("EEEE");
	SimpleDateFormat dd_MM_yyyy_DateFormat = new SimpleDateFormat("dd-MM-yyyy");


	public void journeyPlanSummaryReport15055(DBObject pjpConfigTemplate, final Date unConvertedDate) 
	{
		DB primaryDB = null;
		DB secondaryDB = null;
		DB tertiaryDB = null;
		DBObject queryDbObject = null;
		DBObject projectionDbObject = null;
		DBObject orderBy = null;
		DBCursor cursor = null;
		List<Long> orgIDs = null;
		BasicDBObject primaryDBobj = null;
		BasicDBObject secondaryDBobj = null;
		BasicDBObject tertiaryDBobj = null;

		final DateTime givenDate  =   new DateTime(unConvertedDate);

		DateTime firstDateOfMonth =   null;
		DateTime lastDateOfMonth  =   null;

		try 
		{

			firstDateOfMonth =   new DateTime(givenDate.getYear(), givenDate.getMonthOfYear(), givenDate.dayOfMonth().getMinimumValue(), 0, 0);
			lastDateOfMonth  =   new DateTime(givenDate.getYear(), givenDate.getMonthOfYear(), givenDate.dayOfMonth().getMaximumValue(), 0, 0);

			System.out.println("GivenDate  :  "+dd_MM_yyyy_DateFormat.format(givenDate.toDate()) + "  |   Day  :  "+EEEE_dateTimeFormat.print(givenDate));

			System.out.println("FirstDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(firstDateOfMonth.toDate()) + "  |   Day  :  "+EEEE_dateTimeFormat.print(firstDateOfMonth));

			System.out.println("LastDateOfMonth  :  "+dd_MM_yyyy_DateFormat.format(lastDateOfMonth.toDate()) + "  |   Day  :  "+EEEE_dateTimeFormat.print(lastDateOfMonth));



			// start ---------------------------   	primary 		

			primaryDBobj = (BasicDBObject) (pjpConfigTemplate.get("Primary"));
			primaryDB = MongoDB.getMongoClient(primaryDBobj.getString("connection-id")).getDB(primaryDBobj.getString("schema-name"));

			queryDbObject = new BasicDBObject();

			for (Object obj : (BasicDBList)primaryDBobj.get("parameters")) {
				DBObject dbObj = (DBObject) obj;
				queryDbObject.put(dbObj.get("param-name").toString(), dbObj.get("param-value"));
			}

			projectionDbObject  = (DBObject) primaryDBobj.get("projection");
			orderBy = (DBObject) primaryDBobj.get("sort-by");
			System.out.println("primary queryDbObject "+ queryDbObject);
			cursor = primaryDB.getCollection(primaryDBobj.getString("collection-name")).find(queryDbObject, projectionDbObject).sort(orderBy);
			orgIDs = new ArrayList<Long>();

			if(cursor==null || cursor.count()==0)
			{
				System.out.println("No org found...");
				return;
			}
			for (DBObject dbObject : cursor) {
				orgIDs.add(Long.parseLong(dbObject.get("_id")!=null ? dbObject.get("_id").toString() : "0"));
			}

			System.out.println("total orgIDs ::: "+orgIDs.size());
			System.out.println(orgIDs+"\n\n\n");

			//-------------------------------------- Primary end ------------------------------------------
			//-------------------------------------- secondary start ------------------------------------------


			secondaryDBobj = (BasicDBObject) (pjpConfigTemplate.get("Secondary"));
			secondaryDB = MongoDB.getMongoClient(secondaryDBobj.getString("connection-id")).getDB(secondaryDBobj.getString("schema-name"));


			queryDbObject = new BasicDBObject();

			queryDbObject.put("org_id", new BasicDBObject("$in",orgIDs));

			queryDbObject.put("schedule_dt",new BasicDBObject("$gte",firstDateOfMonth.toDate()).append("$lte",givenDate.toDate() ));

			projectionDbObject  = (DBObject) secondaryDBobj.get("projection");
			System.out.println(queryDbObject);

			orderBy = (DBObject) secondaryDBobj.get("sort-by");
			cursor = secondaryDB.getCollection(secondaryDBobj.getString("collection-name")).find(queryDbObject).sort(orderBy);
			//			cursor = secondaryDB.getCollection(secondaryDBobj.getString("collection-name")).find(queryDbObject, projectionDbObject).sort(orderBy);

			if(cursor==null || cursor.count()==0)
			{
				System.out.println("No secondary data found...");

			}
			else {
				System.out.println("\n\nsecondary cursor count ::: "+cursor.count());
				System.out.println("secondary data   :   ");
				for (DBObject dbObject : cursor) {
					System.out.println(dbObject);
					break;
				}
			}
			System.out.println("\n\n\n");
			//-------------------------------------- secondary end ------------------------------------------


			//-------------------------------------- tertiary start ------------------------------------------
			tertiaryDBobj = (BasicDBObject) (pjpConfigTemplate.get("Tertiary"));
			
			tertiaryDB = MongoDB.getMongoClient(tertiaryDBobj.getString("connection-id")).getDB(tertiaryDBobj.getString("schema-name"));


			queryDbObject = new BasicDBObject();

			queryDbObject.put("dist_org_id", new BasicDBObject("$in",orgIDs));

			queryDbObject.put("start_dt", new BasicDBObject("$gte",givenDate.toDate()));
			queryDbObject.put("end_dt", new BasicDBObject("$lte",lastDateOfMonth.toDate()));

			projectionDbObject  = (DBObject) tertiaryDBobj.get("projection");
			System.out.println(queryDbObject);
			orderBy = (DBObject) tertiaryDBobj.get("sort-by");
			cursor = tertiaryDB.getCollection(tertiaryDBobj.getString("collection-name")).find(queryDbObject).sort(orderBy);
			//			cursor = secondaryDB.getCollection(tertiaryDBobj.getString("collection-name")).find(queryDbObject, projectionDbObject).sort(orderBy);

			if(cursor==null || cursor.count()==0)
			{
				System.out.println("No tertiary data found...");
			}
			else {
				System.out.println("\n\ntertiary cursor count ::: "+cursor.count());
				System.out.println("Tertiary data found...");
				for (DBObject dbObject : cursor) {
					System.out.println(dbObject);
					break;
				}
			}

			//-------------------------------------- tertiary end ------------------------------------------
			System.out.println("\n\n\n\n\n");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally {
			MongoDB.closeAllConnection();
			primaryDB = null;
			secondaryDB = null;
			cursor = null;
			orgIDs = null;
			queryDbObject = null;
			projectionDbObject = null;

		}


	}


	public static DBObject keyValueExchange(String confStr) {

		BasicDBObject confObj = (BasicDBObject) JSON.parse(confStr);
		BasicDBObject newObj = new BasicDBObject();
		for(String field : confObj.keySet())
		{
			newObj.put(confObj.getString(field), field);
		}

		return newObj;
	}

	public Cursor match(DBCollection collection,String key, Object value){

		DBObject       match    = new BasicDBObject();
		DBObject       matchObj = new BasicDBObject();
		List<DBObject> pipeline = new ArrayList<DBObject>();

		matchObj.put(key, value);
		match.put("$match", matchObj);

		pipeline.add(match);
		return aggregate(collection, pipeline);
	}	

	public boolean insertDBObject(DB db,String collectionName, DBObject dbo) {
		try {
			db.getCollection(collectionName).insert(dbo);
			return true;
		} catch (Exception e) {
			System.out.println("Insertion failed");
			e.printStackTrace();
		}
		return false;
	}

	public void copyCollection(DB from , DB to ,String collectionName) {

		Cursor       cursor           = null;
		DBCollection toDBCollection   = null;
		DBCollection fromDBCollection = null;
		try {
			toDBCollection    = to.getCollection(collectionName);
			fromDBCollection  = from.getCollection(collectionName);
			cursor            = fromDBCollection.find();
			while(cursor.hasNext())
				toDBCollection.insert(cursor.next());
			cursor = toDBCollection.getDB().getCollection(collectionName).find();
			System.out.println("-----------------------------"+collectionName+"------------------------------------");
			while(cursor.hasNext())
				System.out.println(cursor.next());
		} finally {
			toDBCollection      = null;
			fromDBCollection    = null;
			cursor              = null;
			from                = null;
			to	                = null;
			collectionName      = null;
		}	
	}

	public void twoColumnMatch(DBCollection collection1, DBCollection collection2, String key1, String key2, String getValue) {

		DBObject dbo1 = null;
		DBObject dbo2 = null;
		Cursor cursor1= null;
		Cursor cursor2=null;
		int i = 0;
		int j = 0;

		try {
			cursor1 = collection1.find().sort(new BasicDBObject(key1, 1));
			collection1   = null;
			while(cursor1.hasNext()){
				dbo1 = cursor1.next();
				cursor2 = collection2.find(new BasicDBObject(key2,""+(dbo1.get(key1).toString())));
				System.out.println(++i+".  "+key1+" < "+dbo1.get(key1)+" > -"+getValue+"- < "+dbo1.get(getValue)+" >\n");
				j = 0;
				while(cursor2.hasNext()){
					dbo2 = cursor2.next();
					System.out.println("\t"+(++j)+". "+dbo2.get(getValue));
				}

				System.out.println("----------------------------------------------------------------------");
			}
		} finally {
			collection1   = null;
			collection2   = null;
			cursor1       = null;
			cursor2       = null;
			dbo1		  = null;
			dbo2          = null;
		}

	}

	public Cursor aggregate(DBCollection collection, List<DBObject> pipeline) {
		try {
			AggregationOptions aggregationOptions = AggregationOptions.builder()
					.outputMode(AggregationOptions.OutputMode.CURSOR).build(); 

			return	collection.aggregate(pipeline, aggregationOptions);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cursor find(DB db,String collectionName, DBObject dbObject) {
		try {
			return db.getCollection(collectionName).find(dbObject);	

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<String,Object> createMap(Cursor cursor,String key,String value) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(key, value);
		try {
			while (cursor.hasNext()) {
				DBObject dbObject = (DBObject) cursor.next();
				map.put(
						dbObject.get(key).toString(), dbObject
						);
			}

			return map;
		} catch (Exception e) {
			e.printStackTrace();		
			return null;
		}
	}

	public boolean writeMapToCSVFile(Map<String,Object> myHashMap, String filename) {
		try (Writer writer = new BufferedWriter(new  FileWriter(csvFilelocation+filename+csv))) {
			for (Map.Entry<String, Object> entry : myHashMap.entrySet()) {
				writer.write(entry.getKey() + delimiter + entry.getValue().toString() + delimiter+eol);
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
		return false;
	}

	public static boolean writeListToCSVFile(List<String> list, String filename) {
		try (Writer writer = new BufferedWriter(new  FileWriter(csvFilelocation+filename+csv))) {
			for(String line : list){ 
				writer.write(line+eol);
			}
		} catch (IOException ex) {
			ex.printStackTrace(System.err);
		}
		return false;
	}

	public void fetchData(JSONArray keyField, JSONObject fetchedData) throws Exception 
	{
		BufferedReader reader = null;
		List<JSONObject> list = new LinkedList<>();

		String fileName = "nodes";
		String separator = "|";
		String line = null;
		try {
			reader = new BufferedReader(new FileReader(csvFilelocation+fileName+csv));


			line = reader.readLine();

			while (line != null) {
				System.out.println(line.substring(line.lastIndexOf(separator)+1, line.length()).toString());
				JSONObject jsonObject = new JSONObject(line.substring(line.lastIndexOf(separator)+1, line.length()).toString());
				list.add(jsonObject);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("-----------------------------------------");
			if(!list.isEmpty())
				for (JSONObject jsonObject : list) 
					System.out.println(jsonObject);

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




	private static String csvFilelocation = "src/main/resources/file/csv/";
	private static String csv  = ".csv";
	private static String eol = System.getProperty("line.separator");
	private String delimiter = "|";
	private BufferedReader  reader ;


}
