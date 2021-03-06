package com.finevm.enh.util;

import org.json.JSONObject;

import com.finevm.WorkEnvironment;
import com.finevm.PropsLoader;

public interface IWorkConstants {

	public static final String INTERFACE_WORK_SOURCECODE_LOC        	= 	WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC+  "/commons/config/common.properties";;

	public static final JSONObject RDBMS_LIST     	=  PropsLoader.getProperties("RDBMS_LIST")!=null ? new JSONObject(PropsLoader.getProperties("RDBMS_LIST")) : new JSONObject() ;

	public static final JSONObject SERVER_LIST    	=  PropsLoader.getProperties("SERVER_LIST")!=null ? new JSONObject(PropsLoader.getProperties("SERVER_LIST")) : new JSONObject() ;

	public static final JSONObject EMAIL_SEND_USERS =  PropsLoader.getProperties("EMAIL_SEND_USERS")!=null ? new JSONObject(PropsLoader.getProperties("EMAIL_SEND_USERS")) : new JSONObject() ;

	public static final JSONObject MONGODB_LIST     =  PropsLoader.getProperties("MONGODB_LIST")!=null ? new JSONObject(PropsLoader.getProperties("MONGODB_LIST")) : new JSONObject() ;                                       


	public static final String  FILE_OPERATION_LOADKEY_OUTPUT_FILE = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC+  "/output/fileOperation/";

	public static final String  FILE_OPERATION_SEARCH_OUTPUT_LOC   = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC+  "/output/fileOperation/";

	public final static String DELETE_LINE_IN_FILE_INPUT           = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/fileOperation/deleteLineInFile/input.txt";

	public final static String DELETE_LINE_IN_FILE_OUTPUT          = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/fileOperation/deleteLineInFile/output.txt";

	public final static String DELETE_LINE_IN_FILE_DELETE          = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/fileOperation/deleteLineInFile/delete.txt";

	public final static String FILE_OPERATION_LOC        		   = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/fileOperation/";

	public final static String SAMPLE_FILES__LOC        		   = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/sample/";

	public final static String LOG_LOC        		               = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/log/";

	public final static String INTEGRATION_XML_REQUEST_TEMPLATE_PATH   = WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/integration-templates/";
	public final static String queryCreationSetPath              =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/set_where/setField.txt";
	public final static String queryCreationWherePath            =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/set_where/whereField.txt";
	public final static String setWhereResultFile                =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/result/setWhereResult.sql";
	public final static String queryResultInsertforInterfaceAttr =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/result/interface_attr_insert_query.sql";
	public final static String attribute_id_file                 =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/interface_attr_insert_query/attribute_id.txt";
	public final static String name_v_file                       =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/interface_attr_insert_query/name_v.txt";
	public final static String value_v_file                      =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/queryGenerate/interface_attr_insert_query/value_v.txt";
	public final static String INTERFACE_REFERENCE_GENERATION    =  WorkEnvironment.INTERFACE_WORK_SOURCECODE_LOC +  "/commons/interfaceReference";


}