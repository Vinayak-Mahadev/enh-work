package com.enhancesys.interfaces.snoc.common;

public class SNOCBsonSchemaDefinations {

	public static final String GET_INTERFACES ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"org_type\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\",\"optional\":true},"+
						"\"interface_type\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_INTERFACE_FILE_DETAILS ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"org_type\":{\"type\":\"number\",\"optional\":true},"+
						"\"module_id\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_INTERFACE_MODULE_FILES ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"org_type\":{\"type\":\"number\"},"+
						"\"interface_id\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\",\"optional\":true}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_INTERFACE_FILE_SUMMARY ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"org_type\":{\"type\":\"number\",\"optional\":true},"+
						"\"file_id\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\",\"optional\":true}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String DOWNLOAD_INTERFACE_FILE_DATA ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"file_ext\":{\"type\":\"string\"},"+
						"\"file_path\":{\"type\":\"string\"},"+
						"\"file_name\":{\"type\":\"string\"},"+
						"\"node_id\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\"},"+
						"\"mode\":{\"type\":\"string\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String UPLOAD_INTERFACE_FILE_DATA ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"file_ext\":{\"type\":\"string\"},"+	
						"\"interface_id\":{\"type\":\"number\"},"+
						"\"file_data\":{\"type\":\"string\"},"+
						"\"file_path\":{\"type\":\"string\",\"optional\":true},"+
						"\"file_name\":{\"type\":\"string\"},"+
						"\"node_id\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\"},"+
						"\"mode\":{\"type\":\"string\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_ONLINE_INTERFACE_SUMMARIES ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"interface_id\":{\"type\":\"number\"},"+
						"\"ref_data\":{\"type\":\"string\",\"optional\":true},"+
						"\"transaction_id\":{\"type\":\"number\",\"optional\":true},"+
						"\"start_date_time\":{\"type\":\"string\"},"+
						"\"end_date_time\":{\"type\":\"string\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_REQUEST_RESPONSE_BY_TRANSACTION_ID ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"interface_id\":{\"type\":\"number\"},"+
						"\"transaction_id\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_ONLINE_INTERFACE_LIST_WRAPPER ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +
						"\"ext_system\":{\"type\":\"array\"},"+
						"\"org_type\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\",\"optional\":true},"+
						"\"interface_type\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String REPROCESS_INTERFACE_SUMMARY ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +
						"\"status_n\":{\"type\":\"number\"},"+
						"\"trans_id_n\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String DOWNLOAD_INTERFACE_REPORTS ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +	
						"\"reportPath\":{\"type\":\"string\"},"+
						"\"startDate\":{\"type\":\"string\"},"+
						"\"reportId\":{\"type\":\"string\"},"+
						"\"endDate\":{\"type\":\"string\"},"+
						"\"interfaceIds\": {\"type\":\"array\",\"items\": {" +
						"\"type\":\"number\"}}," +
						"\"node_id\":{\"type\":\"number\"},"+
						"\"org_id\":{\"type\":\"number\"},"+
						"\"file_type\":{\"type\":\"string\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_INTERFACES_BY_INTERFACEIDS ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +
						"\"interface_ids\":{\"type\":\"array\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_KYC_SYNC_ATTR_BY_INTERFACEID ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +
						"\"interface_id\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String UPDATE_KYC_SYNC_ATTRIBUTES ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +
						"\"kyc_attr_dtls\":{\"type\":\"array\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
	public static final String GET_INTERFACE_DETAILS ="{\"type\":\"object\",\"properties\": {" +
			"\"envelope\": {\"type\":\"object\",\"properties\": {" +
					"\"header\": {\"type\":\"object\",\"properties\": {" +
						"\"uname\": {\"type\":\"string\"}," +
						"\"pwd\": {\"type\":\"string\"}," +
						"\"utype\": {\"type\":\"number\"}," +
						"\"rqst\": {\"type\":\"string\"}," +
						"\"tz\": {\"type\":\"string\"}," +
						"\"src\": {\"type\":\"string\"}," +
						"\"chnl\": {\"type\":\"string\"}," +
						"\"locale\": {\"type\":\"string\"}," +
						"\"uid\": {\"type\":\"number\"}" +
					"}},"+
			"\"payload\":{\"type\":\"object\",\"properties\": {" +
					"\"interface\":{\"type\":\"object\",\"properties\": {" +
						"\"interface_id\":{\"type\":\"number\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
	
}
