package com.enhancesys.interfaces.snoc.common;

import org.apache.log4j.Logger;

import com.enhancesys.interfaces.snoc.util.PropertiesFileLoaderClass;



public class QueryConstants 
{	
	private static Logger LOGGER = Logger.getLogger(QueryConstants.class);

	public static String SCHEME_NAME = null;
	
	static{
		try{
			SCHEME_NAME = PropertiesFileLoaderClass.getValueAsString("INTERFACE_SCHEMA_NAME");
		}catch (Exception e) {
			LOGGER.error("[ QueryConstants ] Exception message is : "+e.getMessage());		}
	}
	
	public static final String Q_GET_INTERFACE_BY_INTERFACE_TYPE = "SELECT * FROM "+SCHEME_NAME+".MS_INTERFACE WHERE INTERFACE_TYPE_N = :INTERFACE_TYPE_ID ORDER BY NAME_V";
	
	public static final String Q_GET_MODELUE_INTERFACE_BY_ID = "SELECT * FROM "+SCHEME_NAME+".MS_INTERFACE WHERE MODULE_ID_N = :MODULE_ID";
	
	public static final String Q_GET_INTERFACE_ID_BY_MODULE_AND_TYPE = "SELECT INTERFACE_ID_N FROM "+SCHEME_NAME+".MS_INTERFACE WHERE MODULE_ID_N = :MODULE_ID AND TRANS_TYPE_N = :TRANS_TYPE_ID";
	
	public static final String Q_GET_INTERFACE_FILE_SUMMARY_DETAILS = "SELECT FILE_ID_N, FILE_NAME_V, IFS.INTERFACE_ID_N, FILE_ID_N, UPLOADED_BY_N, to_char(PROCESSED_ON_DT, 'YYYY-MM-DD HH24:MI:SS') as PROCESSED_ON_DT, to_char(UPLOADED_ON_DT, 'yyyy-MM-dd HH24:MI:SS') as UPLOADED_ON_DT, TOTAL_COUNT_N, ERROR_COUNT_N, SUCCESS_COUNT_N, MESSAGE_V, FILTER_COUNT_N, IFS.STATUS_N , ST.NAME_V FROM "
			+ SCHEME_NAME + ".TR_INTERFACE_FILE_SUMMARY IFS, " + SCHEME_NAME
			+ ".SD_STATUS ST, "+SCHEME_NAME+".MS_INTERFACE MSI "+"WHERE IFS.INTERFACE_ID_N = ? and IFS.STATUS_N = ST.STATUS_N and IFS.INTERFACE_ID_N = MSI.INTERFACE_ID_N " ;

	public static final  String Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_DATE = " AND CASE WHEN (MSI.INTERFACE_TYPE_N = 2 AND MSI.TRANS_TYPE_N = 1) THEN CAST(PROCESSED_ON_DT AS DATE) BETWEEN TO_TIMESTAMP(?,'dd-Mon-yyyy')  AND  TO_TIMESTAMP(?,'dd-Mon-yyyy') ELSE "
			+ "CAST(UPLOADED_ON_DT AS DATE) BETWEEN TO_TIMESTAMP(?,'dd-Mon-yyyy')  AND  TO_TIMESTAMP(?,'dd-Mon-yyyy') END";

	public static final  String Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_FILE_NAME = " AND FILE_NAME_V ILIKE '%' || ? || '%' ";
	
	public static final  String Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_STATUS_NAME = " AND ST.NAME_V ILIKE '%' || ? || '%' ";
	
	
	public static final String Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_ORDER_BY_FILE_ID = " ORDER BY FILE_ID_N DESC";

	public static final String Q_GET_INTERFACE_FILE_SUMMARY_DETAILS_SORT_BY_FILE_ID = " AND FILE_ID_N = ?";
	
	public static String Q_GET_STATUS_NAME = "SELECT NAME_V FROM "+SCHEME_NAME+".SD_STATUS WHERE STATUS_N = :STATUS_ID ";
	
	public static String Q_GET_INTERFACE_ATTRIBUTE_VALUE_BY_KEY = "SELECT * FROM "+SCHEME_NAME+".MS_INTERFACE_ATTR WHERE INTERFACE_ID_N = :INTERFACE_ID AND ";
	
	public static String Q_GET_TR_FILE_SUMMARY_DETAILS = "SELECT * FROM "+SCHEME_NAME+".TR_INTERFACE_FILE_SUMMARY_DETAILS WHERE FILE_ID_N = :FILE_ID";
	
	public static String Q_GET_TR_FILE_SUMMARY = "SELECT * FROM "+SCHEME_NAME+".TR_INTERFACE_FILE_SUMMARY WHERE FILE_ID_N = :FILE_ID";
	
	public static String Q_GET_INTERFACE_ATTRIBUTE = "SELECT * FROM "+SCHEME_NAME+".MS_INTERFACE_ATTR WHERE INTERFACE_ID_N = :INTERFACE_ID";
	
	public static String Q_GET_ONLINE_INTERFACE_SUMMARY_1 = "select summary.trans_id_n,summary.interface_id_n,summary.ref_data1_v,summary.ref_data5_v,summary.retry_count_n,to_char(summary.request_time_dt,'yyyy-MM-dd HH24:MI:SS') as request_time_dt,to_char(summary.response_time_dt,'yyyy-MM-dd HH24:MI:SS') as response_time_dt,summary.status_n,summary.ref_data4_n,status.name_v as status_desc,case when summary.ref_data4_n = 0 then 'Success' when summary.ref_data4_n = 1 then 'Failure' else '' end as ext_sys_status,case when summary.ref_data3_n is NULL then case when summary.status_n in (:errorStatus,:callBackFailedStatus) then 1 else 0 end else 0 end as re_process from "+SCHEME_NAME+".tr_interface_summary summary, "+SCHEME_NAME+".sd_status status where summary.interface_id_n = :interfaceId and summary.status_n = status.status_n and summary.request_time_dt >= to_timestamp(:startDate,'dd-Mon-yyyy HH24:mi:sec.SSS') and summary.request_time_dt <= to_timestamp(:endDate,'dd-Mon-yyyy HH24:mi:sec.SSS')";
	public static String Q_GET_ONLINE_INTERFACE_SUMMARY_2 = "and summary.trans_id_n = :transactionId";
	public static String Q_GET_ONLINE_INTERFACE_SUMMARY_3 = "and (summary.ref_data1_v ilike :refData or summary.ref_data5_v ilike :refData)";
	
	public static String Q_GET_REQUEST_RESPONSE_BY_TRANSACTION_ID = "select convert_from(loread(lo_open(orgnl_request_data_b::int, x'40000'::int), x'40000'::int),  'UTF8') as orgnl_request_data_b,convert_from(loread(lo_open(request_data_b::int, x'40000'::int), x'40000'::int),  'UTF8') as request_data_b,convert_from(loread(lo_open(orgnl_response_data_b::int, x'40000'::int), x'40000'::int),  'UTF8') as orgnl_response_data_b,convert_from(loread(lo_open(response_data_b::int, x'40000'::int), x'40000'::int),  'UTF8') as response_data_b  from "+SCHEME_NAME+".tr_interface_summary where trans_id_n = :transactionId";
	public static String Q_CHECK_FILE_NAME_EXISTS = "select file_id_n,file_name_v from interface.tr_interface_file_summary where file_name_v ilike :fileName";
	public static String Q_REPROCESS_INTERFACE_SUMMARY = "update interface.tr_interface_summary set status_n = :status where trans_id_n = :transactionId";
	
	public static String Q_GET_INTERFACES_BY_INTERFACE_IDS = "select interface_id_n,name_v from interface.ms_interface where interface_id_n in (:interfaceIds)";
	public static String Q_GET_KYC_SYNC_ATTR_BY_INTERFACEID = "select sync_id_n,interface_id_n,kyc_field_v,kyc_flag_n from interface.ms_kyc_sync_info where interface_id_n = :interfaceId order by kyc_field_v";
	public static String Q_UPDATE_KYC_SYNC_ATTRIBUTES = "update interface.ms_kyc_sync_info set kyc_flag_n = :kycFlag,last_updated_time_dt = :lastUpdatedDate where sync_id_n in (:syncIds)";
	
	public static String Q_GET_ORGNL_REQUEST_BLOB_DATA = "select convert_from(loread(lo_open(orgnl_request_data_b::int, x'40000'::int), x'40000'::int),  'UTF8') from interface.tr_interface_summary where trans_id_n in (select trans_id_n from interface.tr_interface_summary where ref_data3_n = :ref_data3_n and status_n = :status_n)";
	
	public static String Q_GET_INTERFACE_DETAILS_ATTR = "select module.description_v from "+SCHEME_NAME+".ms_interface msi, "+SCHEME_NAME+".ms_module module  where msi.interface_id_n = ? and msi.module_id_n=module.module_id_n;";

}