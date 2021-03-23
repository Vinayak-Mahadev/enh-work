package enh.team.interfaces.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@SuppressWarnings("unused")
public class App {

	public static void main(String[] args) 
	{
		try 
		{
			//SimpleDateFormat simpleDateFormat = null;
			//simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			//System.out.println(simpleDateFormat.parse("20200626000002"));
			//System.out.println(simpleDateFormat.parse("20200626000002_10"));
			//System.out.println(simpleDateFormat.parse("20200626000002_08"));
			//			String validationConf = "ICCID;M;R;[0-9]{20}+#MSISDN;M;R;[0-9]{1,20}+#IMSI;M;R;[0-9]+#DO_ID;M;R;.*/.*/[0-9]+#DEALER_ID;M#BRANCH_CODE;N;R;[0-9]+#BRAND;M#PRODUCT_EXPIRED_DATE;M;D;yyyyMMdd#SO_CREATION_DATE;M;D;yyyyMMdd#ALLOC_ID;M;R;[0-9]+#PROGRAM_CODE;M#PROGRAM_NAME;N#TYPE;N;R;[a-zA-Z]#ALLOC_DATE;M;D;dd-MMM-yy#PAYMENT_DATE;N;D;dd-MMM-yy";
			//			List<String> validationConfList = Arrays.asList(validationConf.split("#"));
			//			System.out.println("validationConfList :: " + validationConfList.size());

			//			String data = "1001,1003,1005,1009,1011,1013,1015,1017,1007,1024,1025,1027,1029,1018,1019,1021,1020,1022,1023,1030,1031,1040,1045,1050,1049,1051,1053,1061,1062,1064,1065,1066,1067,1068,1069,1070,1071,1072,1075,1076,1077,1078,1079,1080,1081,1082,1090,1092,1136,1138,1143,1145,1146,1148,1152,1156,1157,1158,1159,1161,1162,1165,1166,1167,1168,1169,1170,1171,1172,1173,1174,1175,1176,1177,1178,1179,1180,1181,1182";
			//
			//			String flag = "1066,1067,1068,1069,1070,1071,1076,1077,1078,1079,1080,1081";
			//
			//			List<String> dataList = Arrays.asList(data.split(","));
			//			System.out.println("dataList :: " + dataList);
			//			System.out.println("dataList size :: " + dataList.size());
			//
			//			System.out.println(flag.contains("1066"));
			//			for (String sid : dataList) 
			//			{
			//				if(!flag.contains(sid))
			//					System.out.print(sid + ",");	
			//			}

			/*String data = "2025-10-30";
			SimpleDateFormat foss = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tnm = new SimpleDateFormat("yyyyMMdd");
			System.out.println(foss.parse(data));*/


			//interfaceModule_uploadFiles_validation(null, null, null);
			//interfaceModule_uploadFiles_validation("total_revenue", "yyyyMMddHHmmss", "total_revenue_20200626000000");
			//			String monthInFile = "12345455";
			//			monthInFile = monthInFile.substring(0, 6);
			//			System.out.println(monthInFile);
			//			runtimeTest();

			//			String headerWithType = "REPORT_NAME|CHANNEL|REGION|AREA|SALESAREA|CLUSTER|MICROCLUSTER|ADDITIONALTERRITORY|TERRITORYID|SUBORGTYPE|ORGANIZATIONID|ORGSHORTCODE|ORGANIZATIONNAME|PRODUCTBRAND|PRODUCTTYPE|PRODUCTCATEGORY|PRODUCTID|PRODUCTEXTID|PRODUCTNAME|QTY|AVERAGEQTY|BUSINESSDAYS|";
			//			//String headerWithType = "REPORT_NAME:String|CHANNEL:String|REGION:String|AREA:String|SALESAREA:String|CLUSTER:String|MICROCLUSTER:String|ADDITIONALTERRITORY:String|TERRITORYID:String|SUBORGTYPE:String|ORGANIZATIONID:String|ORGSHORTCODE:String|ORGANIZATIONNAME:String|PRODUCTBRAND:String|PRODUCTTYPE:String|PRODUCTCATEGORY:String|PRODUCTID:String|PRODUCTEXTID:String|PRODUCTNAME:String|QTY:String|AVERAGEQTY:String|BUSINESSDAYS:String";
			//			String delimiter = "\\|";
			//			String childdelimiter = ":";
			//			List<String> headerWithTypeList = null;
			//			DBObject jobIdDbObject = new BasicDBObject();
			//			BasicDBList primaryList = new BasicDBList();
			//
			//			primaryList.add(new BasicDBObject("query", "$sql_query"));
			//
			//			headerWithTypeList = Arrays.asList(headerWithType.split(delimiter, -1));
			////
			////			jobIdDbObject.put("Primary", primaryList);
			////			jobIdDbObject.put("Lookup", new BasicDBObject());
			////			jobIdDbObject.put("Configuration", prepareConfiguration(headerWithTypeList, childdelimiter));
			////			jobIdDbObject.put("OutputConf", prepareOutputConf(headerWithTypeList, childdelimiter));
			////			jobIdDbObject.put("SortConf", new BasicDBObject("extension", "csv").append("file-name-pattern", "yyyyMMddHHmmssSSS").append("sort-file-name_0", "collection1.sh").append("field-delimiter", "|"));
			//
			//			System.out.println(headerWithTypeList);

			/*
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy h:ss");
			String date = "Fri Sep 25 15:37:36 EEST 2020";
			System.out.println(dateFormat.parse("30.08.2020 4:08"));
			System.out.println(dateFormat.format(dateFormat.parse("30.08.2020 4:08")));

			SimpleDateFormat mongoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

			System.out.println(mongoDateFormat.format(dateFormat.parse("30.08.2020 4:08")));
			 */

			//System.out.println("$1,$1,$1".replace("$1", "vinay"));

		
			/*
			String jsonStr = "{\"organizaitonid\":\"org_id\",\"saldo mobo organization shortcode\":\"org_ref_code\",\"territoryid\":\"teritory_id\",\"authenticationtype\":\"auth_type\",\"language\":\"preferred_lang\",\"operatorid\":\"operator_id\",\"username\":\"user_nm\",\"saldomobo msisdn\":\"saldo_msisdn\",\"role_id\":\"roleId\",\"role_effective_date\":\"role_efct_date\",\"role_expired_date\":\"role_exp_date\",\"operator type\":\"operator_type\",\"contact number\":\"reg_mob\",\"operator name\":\"operator_nm\",\"first name\":\"first_name\",\"middle name\":\"middle_name\",\"last name\":\"last_name\",\"npwp number\":\"npwp_num\",\"mother's maiden name\":\"mother_mad_name\",\"birth place\":\"birth_place\",\"birth date\":\"dob\",\"gender\":\"gender\",\"marital\":\"marital_status\",\"religion\":\"religion\",\"citizen of\":\"citizen_of\",\"last education\":\"last_edu\",\"hobby\":\"hobby\",\"size clothing\":\"size_cloth\",\"pants size\":\"pants_size\",\"shoe size\":\"shoe_size\",\"formal education\":\"formal_education\",\"informal education\":\"in_formal_education\",\"working start date\":\"wrk_start_date\",\"working end date\":\"wrk_end_date\",\"organization owner\":\"org_owner\",\"province\":\"user_province\",\"city/district\":\"user_city\",\"districts\":\"user_district\",\"sub district\":\"user_subDistrict\",\"bank name\":\"bank_name_desc\",\"bank account number\":\"bank_acc_no\",\"preferred notification channel\":\"notify_chnl\",\"notification receiving msisdn\":\"notif_msisdn\",\"notification receiving e-mail\":\"notif_email_id\",\"preferred notification language\":\"notifi_lang\",\"user type\":\"user_tp\",\"operatorstatus\":\"operator_status\",\"register datetime\":\"register_date\",\"register operator id\":\"register_operator_id\",\"register operator name\":\"register_operator_nm\",\"approval datetime\":\"approval_dt\",\"approval operator id\":\"approval_operator_id\",\"approval operator name\":\"approval_operator_nm\",\"status\":\"user_status_desc\",\"nodeid\":\"node_id\",\"nodename\":\"node_name\",\"roleid\":\"role_id\",\"rolename\":\"role_nm\",\"blood type\":\"blood_type_desc\",\"height\":\"height\",\"weight\":\"weight\",\"telephone no\":\"user_telephone_no\",\"personal mobile no\":\"personal_mobile\",\"personal email\":\"personal_emailId\",\"ec - full name\":\"emrgncyCont_name\",\"ec - contact number\":\"emrgncyCont_mobile_no\",\"ec - relationship\":\"emrgncyCont_relation_desc\",\"ec - address\":\"emrgncyCont_adrs_1,emrgncyCont_zone_desc,emrgncyCont_city_desc,emrgncyCont_state_desc,emrgncyCont_country_desc;cond_field\",\"bank account holder name\":\"bank_acc_holder\"}";

			BasicDBObject configObject = (BasicDBObject) JSON.parse(jsonStr);

			String[] header = "OrganizaitonID|Saldo Mobo Organization ShortCode|TerritoryID|AuthenticationType|Language|OperatorId|UserName|SALDOMOBO MSISDN|Role_id|Role_Effective_date|Role_Expired_date|Operator Type|Contact Number|Operator Name|First Name|Middle Name|Last Name|NPWP Number|Mother's Maiden Name|Birth Place|Birth Date|Gender|Marital|Religion|Citizen Of|Last Education|Hobby|size Clothing|Pants Size|Shoe Size|Formal Education|InFormal Education|Working Start Date|Working End Date|Organization Owner|Province|City/District|Districts|Sub District|Bank Name|Bank Account Number|Preferred Notification Channel|Notification Receiving MSISDN|Notification Receiving E-mail|Preferred Notification Language|User Type|OperatorStatus|Status|Register DateTime|Register Operator ID|Register Operator Name|Approval DateTime|Approval Operator ID|Approval Operator Name|NodeId|nodeName|RoleID|RoleName|Blood Type|Height|Weight|Telephone No|Personal Mobile No|Personal Email|EC - Full Name|EC - Contact Number|EC - Relationship|EC - Address|Bank Account Holder Name".split("\\|");

			int i = 0;
			for (String string : header) 
			{
//				System.out.println((++i)+ " :: " + string);

				if(configObject.get(string.toLowerCase()) != null)
					if(configObject.get(string.toLowerCase()).toString().contains(";"))
						System.out.println( configObject.get(string.toLowerCase()));	
				
			}

			String str = "emrgncyCont_adrs_1,emrgncyCont_zone_desc,emrgncyCont_city_desc,emrgncyCont_state_desc,emrgncyCont_country_desc;cond_field";

			String[] fields = str.split(";");
			 for (String string : fields) {
				System.out.println(string);
			}
			*/
			/*
			BasicDBObject outputObject = new BasicDBObject();
			outputObject.put("ref_code", "DlrOrg017");
			System.out.println(outputObject.getString("ref_code") != null ? outputObject.getString("ref_code") : outputObject.getString("operator_id"));
		
			*/

			String jsonPath = "organization.org_id";
			JSONObject requestJson = (JSONObject) new JSONParser().parse("{\"node\":[{\"org_type\":7,\"updtd_by\":30001,\"crtd_dt\":{\"$date\":\"2021-01-08T07:21:46.553Z\"},\"trans_list\":[{\"trans_values\":[1042],\"trans_type\":4},{\"trans_values\":[7],\"trans_type\":1},{\"trans_values\":[5501],\"trans_type\":84},{\"trans_values\":[57682],\"trans_type\":85},{\"trans_values\":[57689],\"trans_type\":86},{\"trans_values\":[57739],\"trans_type\":87},{\"trans_values\":[57838],\"trans_type\":88},{\"trans_values\":[401],\"trans_type\":2},{\"trans_values\":[54288],\"trans_type\":3},{\"trans_values\":[54306],\"trans_type\":5},{\"trans_values\":[54311],\"trans_type\":6}],\"status\":174,\"sale_territory_desc\":\"CW-CJA-KUDEPARA->KUDUS->CENTRAL JAVA1->Central and West Java->NASIONAL\",\"children\":[],\"crtd_by\":30001,\"territory_id\":[57838],\"node_id\":304144,\"org_id\":294600,\"_id\":304144,\"updtd_dt\":{\"$date\":\"2021-01-12T09:43:01.267Z\"},\"name\":\"MPC - BRANCH MANAGER_CW-CJA-KUDEPARA_304144\",\"roles\":[4007],\"target\":0,\"props\":[240,237,239],\"parent_id\":0}],\"organization\":{\"org_type\":7,\"updtd_by\":30001,\"desc\":\"DlrOrg017_ed\",\"tin_no\":\"\",\"sub_org_type\":59,\"gstin_no\":\"\",\"org_email_id\":\"\",\"visibility_type\":1,\"ref_code\":\"DlrOrg017\",\"sale_territory\":{},\"addnl_params\":{\"org_long\":\"\",\"org_painting\":\"\",\"org_group\":\"\",\"rule_prof_id\":\"1951\",\"org_category\":\"2060\",\"charge_prof_key\":\"1301\",\"org_classification\":\"1703\",\"dompetku_no\":\"DlrOrg017\",\"org_addnl_dropdown_2\":\"\",\"org_stg_info\":\"\",\"org_addnl_dropdown_1\":\"\",\"undefined\":\"\",\"org_addnl_field_3\":\"\",\"org_addnl_field_2\":\"\",\"org_addnl_field_1\":\"\",\"dealer_code\":[],\"marketing_program\":\"2255\",\"org_addnl_field_5\":\"\",\"org_addnl_field_4\":\"\",\"org_pareto_manual\":\"1\",\"invoice_note\":\"\",\"org_width\":\"\",\"org_loc_type\":\"\",\"org_pareto_bvwise\":\"1\",\"prd_dtls\":[{\"prd_exp_date\":{\"$date\":\"2099-12-30T18:30:00.000Z\"},\"prd_id\":58319,\"prd_efct_date\":{\"$date\":\"2021-01-11T13:07:14.840Z\"}},{\"prd_exp_date\":{\"$date\":\"2099-12-30T18:30:00.000Z\"},\"prd_id\":58357,\"prd_efct_date\":{\"$date\":\"2021-01-11T13:07:14.840Z\"}},{\"prd_exp_date\":{\"$date\":\"2019-05-30T18:29:59.000Z\"},\"prd_id\":58319,\"prd_efct_date\":{\"$date\":\"2021-01-11T13:07:14.840Z\"}},{\"prd_exp_date\":{\"$date\":\"2019-05-24T18:29:59.000Z\"},\"prd_id\":58346,\"prd_efct_date\":{\"$date\":\"2021-01-11T13:07:14.840Z\"}}],\"org_addnl_dropdown_5\":\"\",\"npwp_num\":\"\",\"charge_prof_id\":\"1801\",\"org_addnl_dropdown_3\":\"\",\"telephone_no\":\"\",\"charge_dist_model\":\"2002\",\"org_addnl_dropdown_4\":\"\",\"partner_type\":\"\",\"chnl_ctgr\":\"1753\"},\"org_id\":294600,\"extnl_sync\":true,\"end_dt\":\"2099-12-31T00:00:00.000Z\",\"parent_short_code\":\"SAT10\",\"updtd_dt\":{\"$date\":\"2021-01-11T13:07:14.843Z\"},\"addresses\":[{\"adrs_type\":\"745\",\"subDistrict\":\"\",\"postal_code\":\"\",\"state\":\"54288\",\"adrs_1\":\"1\",\"adrs_2\":\"\",\"longitude\":\"\",\"latitude\":\"\",\"zone\":\"54306\",\"city\":\"54311\",\"country\":\"401\"}],\"start_dt\":\"2021-01-11T00:00:00.000Z\",\"reg_mob\":\"\"},\"accounts\":[{\"ac_name\":\"testorg110\",\"currency_id\":\"IDR\",\"ac_code\":\"304065\",\"updtd_by\":null,\"crtd_dt\":{\"$date\":\"2019-08-30T08:36:44.448Z\"},\"status\":174,\"location\":{\"adrs_type\":\"744\",\"adrs_3\":null,\"postal_code\":\"\",\"adrs_4\":null,\"state\":\"54288\",\"adrs_1\":\"testorg110\",\"adrs_2\":\"\",\"longitude\":\"\",\"latitude\":\"\",\"zone\":\"54306\",\"city\":\"54311\",\"country\":\"401\"},\"sys\":\"\",\"crtd_by\":277435,\"opening_bal\":0,\"ent_no\":304065,\"end_dt\":{\"$date\":\"2099-12-31T18:29:59.999Z\"},\"ent_tp\":6,\"acc_type\":\"f\",\"_id\":5928281,\"acc_no\":5928281,\"updtd_dt\":null,\"value\":0,\"ent_org\":294600,\"start_dt\":{\"$date\":\"2019-08-29T18:30:00.000Z\"}},{\"ac_name\":\"DlrOrg017_MPC - BRANCH MANAGER_5972017\",\"ac_code\":304144,\"updtd_by\":null,\"crtd_dt\":{\"$date\":\"2021-01-08T07:21:46.560Z\"},\"location\":{\"adrs_type\":\"744\",\"subDistrict\":\"\",\"postal_code\":\"\",\"adrs_1\":\"1\",\"state\":\"54288\",\"adrs_2\":\"\",\"longitude\":\"\",\"latitude\":\"\",\"zone\":\"54306\",\"country\":\"401\",\"city\":\"54311\"},\"status\":174,\"sys\":\"\",\"crtd_by\":30001,\"ent_no\":304144,\"end_dt\":{\"$date\":\"2099-12-31T23:59:59.999Z\"},\"ent_tp\":7,\"acc_type\":\"i\",\"_id\":5972017,\"acc_no\":5972017,\"updtd_dt\":null,\"value\":0,\"ent_org\":294600,\"start_dt\":{\"$date\":\"2021-01-08T00:00:00.000Z\"}}],\"user\":{\"pants_size\":\"\",\"shoe_size\":\"\",\"accept\":1,\"utype\":730,\"paypro_msisdn_no\":\"623344364324\",\"religion\":\"\",\"first_name\":\"DlrOrg017\",\"formal_education\":\"\",\"upwd\":\"G9h+cM8EXU7rXz+gjhb9EXk/qcpGIY07b6Sx3BCrMBk=\",\"marital_status\":\"\",\"emrgncyCont_state\":\"51658\",\"wrk_end_date\":\"31-Dec-2099\",\"user_type\":\"1351\",\"addnl_field_5\":\"\",\"image_Type\":\"5005\",\"middle_name\":\"\",\"mother_mad_name\":\"mother\",\"uname\":\"DLRORG017\",\"hobby\":\"\",\"operator_type\":\"58553\",\"emrgncyCont_zone\":\"51895\",\"extnl_sync\":true,\"ruleType\":30001,\"logged_in\":\"N\",\"opr_classification\":\"2303\",\"personal_emailId\":\"\",\"citizen_of\":\"\",\"addnl_field_4\":\"\",\"last_name\":\"\",\"addnl_field_3\":\"\",\"addnl_field_2\":\"\",\"isStatusUpdate\":false,\"addnl_field_1\":\"\",\"locked\":\"N\",\"addresses\":[{\"adrs_type\":\"742\",\"subDistrict\":\"\",\"postal_code\":\"\",\"state\":\"54288\",\"adrs_1\":\"1\",\"adrs_2\":\"\",\"longitude\":\"\",\"latitude\":\"\",\"zone\":\"54306\",\"city\":\"54311\",\"country\":\"401\"}],\"last_login_dt\":\"\",\"addnl_dropdown_4\":\"\",\"addnl_dropdown_5\":\"\",\"addnl_dropdown_2\":\"\",\"addnl_dropdown_3\":\"\",\"copy_adrs_type\":\"0\",\"updtd_by\":30001,\"addnl_dropdown_1\":\"\",\"atmpt_cnt\":0,\"owner_alt_id_type\":\"1551\",\"role_id_dtls\":[{\"role_id\":58577,\"role_exp_date\":{\"$date\":\"2030-09-30T18:29:59.000Z\"},\"role_efct_date\":{\"$date\":\"2021-01-08T07:21:46.525Z\"}}],\"org_type_desc\":\"Distribution\",\"_id\":277655,\"workflowActionName\":\"user_update\",\"operator_name\":\"DlrOrg017\",\"wrk_start_date\":\"08-Jan-2021\",\"emrgncyCont_adrs_1\":\"1\",\"emrgncyCont_adrs_2\":\"\",\"crtd_by\":30001,\"emrgncyCont_mobile_no\":\"\",\"taskStatus\":\"I\",\"dob\":\"\",\"bank_name\":\"58420\",\"bank_acc_holder\":\"User017\",\"owner_name\":\"DlrOrg017\",\"emrgncyCont_city\":\"51896\",\"bank_acc_no\":\"00934894334\",\"blood_type\":\"\",\"father_name\":\"\",\"contacts\":[{\"no\":\"no@no.com\",\"type\":\"email_id\"},{\"no\":\"623627347576\",\"type\":\"mobile_no\"},{\"no\":\"\",\"type\":\"telephone_no\"}],\"height\":\"\",\"updtd_dt\":{\"$date\":\"2021-01-11T10:32:42.892Z\"},\"gender\":\"\",\"org_type\":7,\"emrgncyCont_postal_code\":\"\",\"crtd_dt\":{\"$date\":\"2021-01-08T07:21:46.573Z\"},\"status\":170,\"emrgncyCont_subDistrict\":\"\",\"notifi_lang\":\"9001\",\"workflow\":true,\"birth_place\":\"\",\"saldomobo_msisdn_no\":\"6243544646545\",\"parent_org_type\":5,\"node\":{\"1\":{\"value\":[{\"_id\":\"7\",\"description\":\"Distribution\"}],\"type\":{\"description\":\"Organization Type\"}},\"2\":{\"value\":[{\"_id\":\"401\",\"description\":\"Nasional\"}],\"type\":{\"description\":\"Country\"}},\"3\":{\"value\":[{\"_id\":\"54288\",\"description\":\"BALI\"}],\"type\":{\"description\":\"Province\"}},\"4\":{\"value\":[{\"_id\":\"1042\",\"description\":\"MPC - BRANCH MANAGER\"}],\"type\":{\"description\":\"Position\"}},\"5\":{\"value\":[{\"_id\":\"54306\",\"description\":\"BADUNG\"}],\"type\":{\"description\":\"City\"}},\"6\":{\"value\":[{\"_id\":\"54311\",\"description\":\"ABIANSEMAL\"}],\"type\":{\"description\":\"District\"}},\"84\":{\"value\":[{\"_id\":\"5501\",\"description\":\"NASIONAL\"}],\"type\":{\"description\":\"Nasional\"}},\"85\":{\"value\":[{\"_id\":\"57682\",\"description\":\"Central and West Java\"}],\"type\":{\"description\":\"Regional\"}},\"86\":{\"value\":[{\"_id\":\"57689\",\"description\":\"CENTRAL JAVA1\"}],\"type\":{\"description\":\"Area\"}},\"87\":{\"value\":[{\"_id\":\"57739\",\"description\":\"KUDUS\"}],\"type\":{\"description\":\"Sales Area\"}},\"88\":{\"value\":[{\"_id\":\"57838\",\"description\":\"CW-CJA-KUDEPARA\"}],\"type\":{\"description\":\"Cluster\"}},\"roles\":{\"value\":[{\"_id\":\"4007\",\"description\":\"MPC - FINANCE\"}],\"type\":{\"description\":\"Roles\"}}},\"parent_org_id\":20,\"personal_mobile\":\"623773445345\",\"salt\":\"DLRORG017\",\"weight\":\"\",\"role_id\":[\"58577\"],\"operator_id\":\"DlrOrg017\",\"auth_type\":[\"1151\",\"1152\"],\"emrgncyCont_relation\":\"60088\",\"owner_alt_id_no\":\"DlrOrg017\",\"emrgncyCont_name\":\"\",\"org_owner\":\"1\",\"processInstanceId\":\"5ffbf4e5e4b0bcec68a8dc89\",\"blocked\":\"N\",\"user_id\":277655,\"frst_login\":\"Y\",\"emrgncyCont_country\":\"401\",\"size_cloth\":\"\",\"emrgncyCont_copy_adrs_type\":\"\",\"kyc_info\":{\"5001\":{\"image_ref\":1384,\"id_no\":\"\"},\"5005\":{\"image_ref\":1387,\"id_no\":\"6757\"},\"5006\":{\"image_ref\":1371,\"id_no\":\"654\"},\"5009\":{\"image_ref\":1386,\"id_no\":\"5657\"}},\"notify_chnl\":\"1201\",\"org_id\":294600,\"present_parent_org_id\":20,\"emrgncyCont_adrs_type\":\"\",\"ref_id\":277655},\"user_trans\":{\"city_id\":[54306],\"updtd_by\":30001,\"org_desc\":\"DlrOrg017_ed\",\"province_id\":[54288],\"updtd_dt\":{\"$date\":\"2021-01-11T13:07:14.854Z\"},\"country_id\":[401],\"district_id\":[54311],\"province_desc\":[\"BALI\"],\"country_desc\":[\"Nasional\"],\"district_desc\":[\"ABIANSEMAL\"],\"city_desc\":[\"BADUNG\"]}}");
//			System.out.println(getFieldValue(jsonPath, requestJson));
			
			if(getFieldValue(jsonPath, requestJson) instanceof String)
				System.out.println(getFieldValue(jsonPath, requestJson));
		} 

		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static Object getFieldValue(String jsonPath, JSONObject requestJson) throws Exception
	{
		JSONObject tempJson = null;
		String[] arr = null;
		
		try
		{
			tempJson = requestJson;
			arr = jsonPath.split("\\.");
//			TLogger.debug("arr.length : " + arr.length);
			for(int i = 0; i < arr.length; i ++)
			{
//				TLogger.debug("arr[i]" + arr[i] + " :: tempJson.get(arr[i]) : " + tempJson.get(arr[i]));
				if(tempJson.get(arr[i]) == null || tempJson.get(arr[i]).toString().trim().isEmpty())
				{
					return "";
				}
				if(tempJson.get(arr[i]) instanceof JSONObject)
				{
//					TLogger.debug("insdie object..");
					tempJson = (JSONObject) tempJson.get(arr[i]);
					if(i == arr.length - 1)
						return tempJson;
				}
				if(tempJson.get(arr[i]) instanceof JSONArray)
				{
//					TLogger.debug("inside array..");
					return (JSONArray) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof String)
				{
//					TLogger.debug("insdie string..");
					return (String) tempJson.get(arr[i]);
				}
				if(tempJson.get(arr[i]) instanceof Long)
				{
//					TLogger.debug("insdie long.." + tempJson.get(arr[i]));
					return ((Long) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Integer)
				{
//					TLogger.debug("insdie integer.." + tempJson.get(arr[i]));
					return ((Integer) tempJson.get(arr[i])).toString();
				}
				if(tempJson.get(arr[i]) instanceof Double)
				{
//					TLogger.debug("insdie double.." + tempJson.get(arr[i]));
					return ((Double) tempJson.get(arr[i])).toString();
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			tempJson = null;
			arr = null;
		}
		return null;
	}
	
	
	public static DBObject prepareOutputConf(final List<String> headerList, final String childdelimiter) 
	{
		DBObject dbObject = new BasicDBObject();
		DBObject childDBDbObject = new BasicDBObject();
		String path = null;
		String type = "String";
		BasicDBList fieldconfList = new BasicDBList();
		String header = "";
		try
		{
			dbObject.put("type", "file");
			dbObject.put("extension", "$output_file_type");
			dbObject.put("file-path", "$output_file_path");
			dbObject.put("file-name", "$output_file_name");
			dbObject.put("file-name-pattern", "$output_file_date_patten");

			if(headerList == null || headerList.size() == 0);
			else
				for (int i = 0; i < headerList.size() ; i++) 
				{
					if(headerList.get(i).trim().toLowerCase().split(":").length == 2) {
						path = headerList.get(i).trim().toLowerCase().split(":")[0];
						type = headerList.get(i).trim().toLowerCase().split(":")[1];
					}
					else 
						path = headerList.get(i).trim().toLowerCase();

					childDBDbObject = new BasicDBObject();
					childDBDbObject.put("path", path);
					childDBDbObject.put("type", type);
					fieldconfList.add(childDBDbObject);
					header = header + path.toUpperCase()+"|";
				}

			dbObject.put("file-headers", header.substring(0, header.length()-1));
			dbObject.put("field-delimiter", "|");
			dbObject.put("field-conf", fieldconfList);

			return dbObject;
		}
		finally 
		{
			header = null;
			path = null;
			type = null;
			dbObject = null;
			fieldconfList = null;
			childDBDbObject = null;
		}
	}
	public static DBObject prepareConfiguration(final List<String> headerList, final String childdelimiter) 
	{
		DBObject dbObject = new BasicDBObject();
		DBObject childDBDbObject = new BasicDBObject();
		String key = null;
		try
		{
			if(headerList == null || headerList.size() == 0);
			else
				for (int i = 0; i < headerList.size() ; i++) 
				{
					key = headerList.get(i).trim().toLowerCase().split(":")[0];
					childDBDbObject = new BasicDBObject("field", key);
					dbObject.put(key, childDBDbObject);
				}
			return dbObject;
		}
		finally 
		{
			key = null;
			dbObject = null;
			childDBDbObject = null;
		}
	}

	public static void runtimeTest() {
		//String cmd = "C:/Program Files/Git/git-bash.exe E:/interface/backend/script.sh";
		String[] command = new String[]{"C:/Program Files/Git/git-bash.exe", "E:/interface/backend/script.sh"};
		Process process = null;
		try 
		{
			process = Runtime.getRuntime().exec(command);
			process.wait(5000);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	static void test()
	{
		final String query = "INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES (#ATTRIBUTE_ID_N#, #INTERFACE_ID_N#, '#NAME_V#', '#VALUE_V#', now());";
		BufferedReader reader = null;
		String line = null;
		String fileAbPath = "C:\\Users\\vinayak\\Downloads\\3Query - Copy.txt";
		String[] attr = null;

		try 
		{
			reader = new BufferedReader(new FileReader(new File(fileAbPath)));
			while ((line = reader.readLine()) != null)
			{
				attr = line.replace("fossuser", "appuser").split("VINAY", -1);
				System.out.println(query.replace("#ATTRIBUTE_ID_N#", attr[0]).replace("#INTERFACE_ID_N#", attr[1]).replace("#NAME_V#", attr[2]).replace("#VALUE_V#", attr[3]));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally {
			if(reader!=null)
				try {
					reader.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
			reader = null;
		}
	}

	void sample()
	{

		//tester();
		Set<String> fileNameList = new HashSet<String>();
		Set<String> successList = new HashSet<String>();
		Set<String> failList = new HashSet<String>();

		String orgFileName = "total_revenue";
		String name  = null;
		String temp  = null;
		//Date date = null;
		SimpleDateFormat simpleDateFormat = null;

		fileNameList.add("total_revenue_20200626000002.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_10.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_08.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_04.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_03.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_05a.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_09.ctl.gz");
		fileNameList.add("total_revenue_20200626000002_07.ctl.gz");
		fileNameList.add("total_revenue_bi_20200626000002_06.ctl.gz");

		for (String fileName : fileNameList) 
		{
			simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			try 
			{
				name = fileName;
				temp = name.replaceAll(orgFileName + "_", "");
				temp = temp.replaceAll("." + "ctl" + "." + "gz", "");
				//			System.out.println(fileName + "   :::   "+ temp);
				simpleDateFormat.parse(temp);
				//			System.out.println(fileName + "   :::   "+ date);
				successList.add(fileName);
			} 
			catch (ParseException e) 
			{
				failList.add(fileName);
			}
		}

		System.out.println(successList);
		System.out.println(failList);

	}
	static void tester() 
	{

		System.out.println("TransType,PaymentType,OrderStatus,isRecordAccept");


		System.out.println(test("22", null, null));
		System.out.println(test("22", null, "2"));
		System.out.println(test("22", "2", null));
		System.out.println(test("22", "2", "2"));

		System.out.println(test("22", null, null));
		System.out.println(test("22", null, "1"));
		System.out.println(test("22", "1", null));
		System.out.println(test("22", "1", "1"));


		System.out.println(test("23", null, null));
		System.out.println(test("23", null, "2"));
		System.out.println(test("23", "2", null));
		System.out.println(test("23", "2", "2"));

		System.out.println(test("23", null, null));
		System.out.println(test("23", null, "1"));
		System.out.println(test("23", "1", null));
		System.out.println(test("23", "1", "1"));


		System.out.println(test(null, null, null));
		System.out.println(test("2", "1", null));
		System.out.println(test("2", "1", "1"));
		System.out.println(test("2", "1", "2"));

		System.out.println(test("1", null, null));
		System.out.println(test("1", "1", null));
		System.out.println(test("1", "1", "1"));
		System.out.println(test("1", "1", "2"));

		System.out.println(test("2", null, null));
		System.out.println(test(null, "1", null));
		System.out.println(test(null, "1", "1"));
		System.out.println(test(null, "1", "2"));




	}


	static boolean test(String transType, String paymentType, String orderStatus) {
		System.out.print(transType+","+paymentType+","+orderStatus+",");
		boolean status = true;

		if(transType != null && transType.equals("22") 
				&& paymentType != null && paymentType.equals("2")
				&& orderStatus != null && orderStatus.equals("2")) {
			//success
			status = true;
		}
		if(transType != null && transType.equals("22") 
				&& paymentType != null && (paymentType.equals("1") || paymentType.equals("2"))
				&& orderStatus != null && (orderStatus.equals("1") || orderStatus.equals("2")) ) {
			//fail
			status = false;
		}
		if(transType != null && transType.equals("23") 
				&& paymentType != null && paymentType.equals("1")
				&& orderStatus != null && orderStatus.equals("1")) {
			//success
			status = true;
		}
		if(transType != null && transType.equals("23") 
				&& paymentType != null && (paymentType.equals("1") || paymentType.equals("2"))
				&& orderStatus != null && (orderStatus.equals("1") || orderStatus.equals("2")) ) {
			//fail
			status = false;
		}
		if(transType != null && (transType.equals("22") || transType.equals("23"))
				&& (paymentType == null || orderStatus == null)) {
			//fail
			status = false;
		}

		return status;
	}


	public static void interfaceModule_uploadFiles_validation(String remoteFile, String remoteFileFormat, String fileName) 
	{
		BasicDBObject attrData = new BasicDBObject();
		BasicDBObject interfaceObj = new BasicDBObject();
		boolean isInValidfile = false;

		attrData.put("Remote File", remoteFile);
		attrData.put("Remote FileName Format", remoteFileFormat);

		interfaceObj.put("file_name", fileName);

		if(interfaceObj.get("file_name") != null && !interfaceObj.getString("file_name").isEmpty()
				&& attrData.get("Remote File") != null
				&& !attrData.getString("Remote File").isEmpty() 
				&& attrData.get("Remote FileName Format") != null
				&& !attrData.getString("Remote FileName Format").isEmpty()){

			String temp = null;
			SimpleDateFormat simpleDateFormat = null;
			try 
			{
				simpleDateFormat = new SimpleDateFormat(attrData.getString("Remote FileName Format"));
				temp = interfaceObj.get("file_name").toString().replaceFirst(attrData.getString("Remote File") + "_", "").split("_", -1)[0];
				simpleDateFormat.parse(temp);
			} 
			catch (Exception e) 
			{
				System.err.println("Skipping file  fileName :: " + interfaceObj.get("file_name").toString() + "     parse name :: " + temp + e);
				isInValidfile = true;
			}
			finally
			{
				simpleDateFormat = null;
				temp  = null;
			}				
		}
		else
		{
			isInValidfile = true;
		}

		if(isInValidfile)
			System.out.println("File is in-valid");
		else
			System.out.println("File is valid");
	}


}
