package com.enhancesys.integration.services.dataengine.util;

public class SNOCBsonSchemaDefinations {

	public static final String PROCESS_JOB_DATA ="{\"type\":\"object\",\"properties\": {" +
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
						"\"job-id\":{\"type\":\"string\"},"+
						"\"job-data\":{\"type\":\"object\"},"+
						"\"template-id\":{\"type\":\"string\"},"+
						"\"template-name\":{\"type\":\"string\"}"+
					"}}"+
				"}}"+
			"}}"+
		"}}";
		
	
}
