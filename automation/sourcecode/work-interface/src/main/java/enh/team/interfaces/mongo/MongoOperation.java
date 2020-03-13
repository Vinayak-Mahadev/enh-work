package enh.team.interfaces.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class MongoOperation {




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
