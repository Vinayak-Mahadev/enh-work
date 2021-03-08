package com.enhancesys.integration.snoc.services.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.enhancesys.integration.snoc.entities.Interfaces;
import com.enhancesys.integration.snoc.exception.ApplicationException;


/**
 * <b>Purpose:</b><br>
 * 		Implementation for Cleanup services..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2017<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        01-12-2020		   Vinayak Mahadev
 * 	-- Base Release
 * 
 * </pre>
 * 
 * <br>
 */
public class CleanupUtility 
{
	private static Logger LOGGER = Logger.getLogger(CleanupUtility.class);

	public JSONObject getTableDtlsForFieldLookupConf(final Connection connection, Interfaces interfaces, Long cleanupCode) throws ApplicationException, Exception
	{
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		JSONObject jsonObject = null;
		JSONObject responseObj = null;
		JSONObject cleanupAttrJson = null;
		String cleanupAttr = null;
		JSONObject tablesJson = null;
		ArrayList<Object> tableList = null;
		try
		{
			statement = connection.prepareStatement(QueryConstants.cleanUpConfQuery);
			statement.setLong(1, interfaces.getInterfaceId().longValue());
			resultSet = statement.executeQuery();

			if(resultSet.next()) 
			{
				cleanupAttr = resultSet.getString(1);
				resultSet.close();
				statement.close();
			}
			if(cleanupAttr == null || (cleanupAttr != null && cleanupAttr.trim().isEmpty()))
				throw new ApplicationException("cleanupAttr is null 'cleanupAttr' :: " + cleanupAttr, null);
			else
			{

				cleanupAttrJson = new JSONObject(cleanupAttr);

				if(!cleanupAttrJson.getBoolean("is-enabled")) 
				{
					LOGGER.debug("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + "is diabled with isEnabled : false");	
					throw new ApplicationException("is-enabled is false cleanupAttrJson :: " + cleanupAttrJson, null);
				}

				if(cleanupCode.intValue() == 0 && !cleanupAttrJson.isNull("default")
						&& cleanupAttrJson.get("default") != null
						&& cleanupAttrJson.get(cleanupAttrJson.get("default").toString()) != null) 
				{
					responseObj = new JSONObject(); 
					responseObj.put("cleanup-tables-flag", cleanupAttrJson.getJSONObject(cleanupAttrJson.get("default").toString()).getJSONObject("cleanup-tables"));
					responseObj.put("cleanup-code", cleanupAttrJson.getLong("default"));
					responseObj.put("retension", cleanupAttrJson.getJSONObject(cleanupAttrJson.get("default").toString()).getInt("retension"));
					responseObj.put("is-enabled", cleanupAttrJson.getBoolean("is-enabled"));
					responseObj.put("delete-mode", cleanupAttrJson.getJSONObject(cleanupAttrJson.get("default").toString()).get("delete-mode"));
					if(!cleanupAttrJson.getJSONObject(cleanupAttrJson.get("default").toString()).isNull("batch_size"))
						responseObj.put("batch_size", cleanupAttrJson.getJSONObject(cleanupAttrJson.get("default").toString()).getInt("batch_size"));
				}
				else if(cleanupCode != null && cleanupCode.intValue() != 0 
						&& !cleanupAttrJson.isNull(cleanupCode.toString())
						&& cleanupAttrJson.get(cleanupCode.toString()) != null) 
				{
					responseObj = new JSONObject();
					responseObj.put("cleanup-tables-flag", cleanupAttrJson.getJSONObject(cleanupCode.toString()).getJSONObject("cleanup-tables"));
					responseObj.put("cleanup-code", cleanupCode);
					responseObj.put("retension", cleanupAttrJson.getJSONObject(cleanupCode.toString()).getInt("retension"));
					responseObj.put("is-enabled", cleanupAttrJson.getBoolean("is-enabled"));
					responseObj.put("delete-mode", cleanupAttrJson.getJSONObject(cleanupCode.toString()).get("delete-mode"));
					if(!cleanupAttrJson.getJSONObject(cleanupCode.toString()).isNull("batch_size"))
						responseObj.put("batch_size", cleanupAttrJson.getJSONObject(cleanupCode.toString()).getInt("batch_size"));
				}
				else 
				{
					LOGGER.debug("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + " cleanup code is wrong : " + cleanupCode);	
					throw new ApplicationException("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + " cleanup code is wrong : " + cleanupCode, null);
				}
			}

			responseObj.put("id", interfaces.getInterfaceId().toString());
			responseObj.put("name", interfaces.getName());
			if(interfaces.getName().contains("INTSM"))
				responseObj.put("type", "SM");
			if(interfaces.getName().contains("INTDW"))
				responseObj.put("type", "DWH");
			if(interfaces.getName().contains("INTHDP"))
				responseObj.put("type", "HDP");

			tablesJson = new JSONObject();
			tablesJson.put("validation", "");
			tablesJson.put("daily", "");
			tablesJson.put("monthly", "");
			tablesJson.put("failure", "");
			tablesJson.put("summary", "interface.tr_interface_file_summary");

			if(interfaces.getInterfaceId().longValue() == SnocConstants.SP_STOCK_DUMP_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.sp_stock_dump");
			else if(interfaces.getInterfaceId().longValue() == SnocConstants.SP_ALLOC_DUMP_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.sp_alloc_dump");
			else if(interfaces.getInterfaceId().longValue() == SnocConstants.VO_STOCK_DUMP_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.stock_dump_voucher");
			else if(interfaces.getInterfaceId().longValue() == SnocConstants.VO_ALLOC_DUMP_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.alloc_dump_voucher");
			else if(interfaces.getInterfaceId().longValue() == SnocConstants.PRODUCT_CREATION_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.product_details");
			else if(interfaces.getInterfaceId().longValue() == SnocConstants.SERIAL_EXPIRY_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.serial_expiry");
			else if(interfaces.getInterfaceId().longValue() == SnocConstants.SITE_MAPPING_INTERFACE_ID.longValue())
				tablesJson.put("temporary", "interface.tr_temp_site_mapping");
			else
			{
				statement = connection.prepareStatement(QueryConstants.fieldLookupConfQuery);
				statement.setLong(1, interfaces.getInterfaceId().longValue());
				resultSet = statement.executeQuery();
				if(resultSet.next())
				{
					tablesJson = new JSONObject();
					jsonObject = new JSONObject(resultSet.getString(1));
					resultSet.close();
					JSONObject duplicate_validation_conf = null;

					if(jsonObject.has("duplicate_validation_conf") && jsonObject.get("duplicate_validation_conf") != null) 
						duplicate_validation_conf = jsonObject.getJSONObject("duplicate_validation_conf");


					tablesJson.put("summary", "interface.tr_interface_file_summary");
					tablesJson.put("temporary", (jsonObject.get("table_name") != null && !jsonObject.getString("table_name").trim().isEmpty() ? "kpi." + jsonObject.get("table_name").toString().toLowerCase() : ""));
					tablesJson.put("validation", (((duplicate_validation_conf != null && duplicate_validation_conf.get("table_name") != null && !duplicate_validation_conf.getString("table_name").trim().isEmpty()) ) ? "kpi." + duplicate_validation_conf.get("table_name").toString().toLowerCase() : ""));
					tablesJson.put("daily", ((jsonObject.get("daily_table") != null && !jsonObject.getString("daily_table").trim().isEmpty()) ? "kpi." + jsonObject.get("daily_table").toString().toLowerCase() : ""));
					tablesJson.put("monthly", ((jsonObject.get("monthly_table") != null && !jsonObject.getString("monthly_table").trim().isEmpty()) ? "kpi." + jsonObject.get("monthly_table").toString().toLowerCase() : ""));

					if (SnocConstants.KPI_HADOOP_FEED_INTERFACES_LIST.contains(interfaces.getInterfaceId().toString()))
						tablesJson.put("failure", "kpi.tr_temp_hadoop_failure_aggr");
					else if(SnocConstants.KPI_FEED_INTERFACE_LIST.contains(interfaces.getInterfaceId().toString()))
						tablesJson.put("failure", "kpi.tr_temp_upload_aggr_failure");
					else 
						tablesJson.put("failure", "");
				}
				else
				{
					LOGGER.debug("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + " fieldLookupConf is not configured for this interface. ");	
					throw new ApplicationException("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + " fieldLookupConf is not configured for this interface. ", null);
				}
			}

			if(responseObj != null) 
			{
				tableList = new ArrayList<Object>();
				tableList.add("summary");
				tableList.add("temporary");
				tableList.add("validation");
				tableList.add("failure");
				responseObj.put("cleanup-tables-list", tableList);
				if(tablesJson != null)
					responseObj.put("cleanup-tables", tablesJson);
			}
		}
		finally 
		{
			if(resultSet != null)
				resultSet.close();
			if(statement != null)
				statement.close();

			tableList = null;
			resultSet = null;
			statement = null;
			jsonObject = null;
			cleanupAttrJson = null;
			cleanupAttr = null;
			tablesJson = null;
		}
		return responseObj;
	}

	public JSONObject preareCleanUpWithFieldLookupConf(Interfaces interfaces , final JSONObject jsonObject, boolean temporary, boolean validation, boolean failure, boolean summary) throws Exception
	{
		JSONObject prepareCleanupConf = null;
		JSONObject tablesJson = null;
		int retension = !jsonObject.isNull("retension") && jsonObject.get("retension") != null ? jsonObject.getInt("retension") : 180;
		if(retension <= 30)
		{
			LOGGER.debug("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + " retension should not below than 30 days");	
			throw new ApplicationException("Interface : " + interfaces.getInterfaceId()+" | "+ interfaces.getName() + " retension should not below than 30 days", null);
		}
		long interfaceid = interfaces.getInterfaceId().longValue();

		String sel_query = "select file_id_n from interface.tr_interface_file_summary where interface_id_n = " + interfaceid + " and uploaded_on_dt < current_date - " + retension + ";";
		String cnt_query = "select count(1) from interface.tr_interface_file_summary where interface_id_n = " + interfaceid + " and uploaded_on_dt < current_date - " + retension + ";";
		String del_query = "";
		String valDel = "delete from TABLE where created_dt < current_date - " + retension + ";";
		String tempDel = "delete from TABLE where file_id_n = ?;"; 
		String failDel = "delete from TABLE where file_id_n = ?;"; 
		String inFileSumDtls = "delete from interface.tr_interface_file_summary_details where file_id_n = ?;";
		String inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
		String inFileSum = "delete from interface.tr_interface_file_summary where interface_id_n = " + interfaceid + " and uploaded_on_dt < current_date - " + retension + ";";
		String temDelQuery = "";
		boolean isEnabled = !jsonObject.isNull("is-enabled") && jsonObject.get("is-enabled") != null ? jsonObject.getBoolean("is-enabled") : false;
		String delimiter = !jsonObject.isNull("delimiter") && jsonObject.get("delimiter") != null ? jsonObject.getString("delimiter") : "#";
		tablesJson = jsonObject.getJSONObject("cleanup-tables");

		prepareCleanupConf = new JSONObject();
		prepareCleanupConf.put("id", interfaceid);
		prepareCleanupConf.put("name", jsonObject.getString("name"));
		prepareCleanupConf.put("retension", retension);
		prepareCleanupConf.put("is-enabled", isEnabled);
		prepareCleanupConf.put("sel_query", sel_query);
		prepareCleanupConf.put("cnt_query", cnt_query);
		prepareCleanupConf.put("batch_size", (!jsonObject.isNull("batch_size") && jsonObject.get("batch_size") != null) ? jsonObject.getInt("batch_size") : 100);

		if(!jsonObject.isNull("delete-mode") && jsonObject.get("delete-mode") != null)
			prepareCleanupConf.put("delete-mode", jsonObject.getString("delete-mode"));
		else
			prepareCleanupConf.put("delete-mode", "no-mode");

		if(interfaceid == SnocConstants.SP_STOCK_DUMP_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.sp_stock_dump where file_id = ?::character varying;" + delimiter;

			if(summary) 
			{
				inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
				temDelQuery = temDelQuery + inSummary + delimiter + inFileSumDtls + delimiter + inFileSum;
			}	
		}
		else if(interfaceid == SnocConstants.SP_ALLOC_DUMP_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.sp_alloc_dump where file_id = ?::character varying;" + delimiter;

			if(summary) 
			{
				inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
				temDelQuery = temDelQuery + inSummary + delimiter + inFileSumDtls + delimiter + inFileSum;
			}	
		}
		else if(interfaceid == SnocConstants.VO_STOCK_DUMP_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.stock_dump_voucher where file_id_n = ?;" + delimiter;

			if(summary) 
			{
				inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
				temDelQuery = temDelQuery + inSummary + delimiter + inFileSumDtls + delimiter + inFileSum;
			}	
		}
		else if(interfaceid == SnocConstants.VO_ALLOC_DUMP_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.alloc_dump_voucher where file_id_n = ?;" + delimiter;

			if(summary) 
			{
				inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
				temDelQuery = temDelQuery + inSummary + delimiter + inFileSumDtls + delimiter + inFileSum;
			}	
		}
		else if(interfaceid == SnocConstants.PRODUCT_CREATION_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.product_details where file_id_n = ?;" + delimiter;

			if(summary) 
			{
				inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
				temDelQuery = temDelQuery + inSummary + delimiter + inFileSumDtls + delimiter + inFileSum;
			}	
		}
		else if(interfaceid == SnocConstants.SERIAL_EXPIRY_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.serial_expiry where file_id = ?;" + delimiter;

			if(summary) 
			{
				inSummary = "delete from interface.tr_interface_summary where ref_data3_n = ?;";
				temDelQuery = temDelQuery + inSummary + delimiter + inFileSumDtls + delimiter + inFileSum;
			}	
		}
		else if(interfaceid == SnocConstants.SITE_MAPPING_INTERFACE_ID.longValue())
		{
			if(temporary)
				temDelQuery = "delete from interface.tr_temp_site_mapping where file_id_n = ?;" + delimiter;
			if(failure)
				temDelQuery = temDelQuery + "delete from kpi.tr_temp_hadoop_failure_aggr where file_id_n = ?;" + delimiter;
			if(summary) 
				temDelQuery = temDelQuery + inFileSumDtls + delimiter + inFileSum;
		}				
		else
		{
			if(validation && !tablesJson.getString("validation").trim().isEmpty()) 
				temDelQuery = temDelQuery + valDel.replaceAll("TABLE", tablesJson.getString("validation")) + delimiter;
			if(temporary && !tablesJson.getString("temporary").trim().isEmpty()) 
				temDelQuery = temDelQuery + tempDel.replaceAll("TABLE", tablesJson.getString("temporary")) + delimiter;
			if(failure && !tablesJson.getString("failure").trim().isEmpty()) 
				temDelQuery = temDelQuery + failDel.replaceAll("TABLE", tablesJson.getString("failure")) + delimiter;
			if(summary) 
				temDelQuery = temDelQuery + inFileSumDtls + delimiter + inFileSum;
		}
		del_query = temDelQuery;
		prepareCleanupConf.put("del_query", del_query);
		jsonObject.put("cleanup-conf", prepareCleanupConf);
		return jsonObject;
	}

	public JSONObject checkTableSize(final Connection connection, final JSONObject tableDetailsJson, final String choice) throws Exception 
	{

		JSONObject jsonObject = null;
		String tempQuery = null;
		ResultSet resultSet = null;
		Statement statement = null;
		JSONObject tablesJson = tableDetailsJson.getJSONObject("cleanup-tables");
		List<String> tableList = null;
		try 
		{
			jsonObject = new JSONObject();
			tableList = new ArrayList<String>();
			tableList.add("summary");
			tableList.add("temporary");
			tableList.add("validation");
			tableList.add("failure");
			tableDetailsJson.put("cleanup-tables-list", tableList);

			LOGGER.debug("choice :: "+ choice + " Checking TableSize List :: " + tableList);
			for (String tableName : tableList) 
			{

				if(!tablesJson.isNull(tableName) && !tablesJson.getString(tableName).trim().isEmpty()) 
				{
					tempQuery = QueryConstants.tableSizeCheckQuery.replaceAll("TABLE", tablesJson.getString(tableName));
					statement = connection.createStatement();
					resultSet = statement.executeQuery(tempQuery);
					if(resultSet.next())
						jsonObject.put(tableName, resultSet.getString(1));
					else
						jsonObject.put(tableName, "NA");
					resultSet.close();
					statement.close();
				}
				else
				{
					jsonObject.put(tableName, "NA");
				}
			}

			tableDetailsJson.put(choice, jsonObject);
		} 
		catch (Exception e) 
		{
			LOGGER.error("Problem in checkTableSize ", e);
			throw e;
		}
		finally 
		{
			if(resultSet != null)
				resultSet.close();
			if(statement != null)
				statement.close();
			tempQuery = null;
			statement = null;
			resultSet = null;
			jsonObject = null;
		}
		return tableDetailsJson;
	}

	public Long processCounts(final Connection connection, final JSONObject tableDtls, final String msg) throws Exception
	{
		Long startTime = System.currentTimeMillis();
		Long endTime = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String tempQuery = null;
		Long count = null;
		JSONObject cleanupConf = null;
		try
		{
			LOGGER.info("Entry processCounts with message :: startTime :: " + msg +" :: "+ startTime);
			cleanupConf = tableDtls.getJSONObject("cleanup-conf");
			//			LOGGER.debug("processCounts :: cleanupConf :: " + cleanupConf);
			if(connection == null)
			{
				LOGGER.error("Connection is not available..");
				throw new ApplicationException("Connection is not available..", null);
			}


			if(!cleanupConf.isNull("is-enabled") && cleanupConf.get("is-enabled") != null && "true".equalsIgnoreCase(cleanupConf.getString("is-enabled").trim()) 
					&& cleanupConf.get("cnt_query") != null && !cleanupConf.getString("cnt_query").trim().isEmpty()	
					&& cleanupConf.get("retension") != null && !cleanupConf.get("retension").toString().trim().isEmpty())
			{
				tempQuery = cleanupConf.getString("cnt_query");
				statement = connection.prepareStatement(tempQuery);
				resultSet = statement.executeQuery();
				if(resultSet.next())
				{
					count = resultSet.getLong(1);
					tableDtls.put(msg, count);
				}
				LOGGER.info("ID : "+cleanupConf.get("id") + " : NAME : " + cleanupConf.get("name") + " : RETENSION "+cleanupConf.get("retension")+" : COUNT: " + count);
			}
			return count;
		}
		catch(Exception exception)
		{
			//System.err.println("Exception : " + exception.getMessage());
			LOGGER.error("Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			endTime = System.currentTimeMillis();
			LOGGER.info("Entry processCounts with message :: Time required :: " + msg +" :: " + (endTime - startTime));
			tempQuery = null;
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			tempQuery = null;
			count = null;
			startTime = null;
			endTime = null;
			resultSet = null;
			statement = null;
		}
	}

	public void processDelete(final long interfaceId, final Connection connection, final JSONObject tableDtls) throws Exception
	{
		Long startTime = System.currentTimeMillis();
		Long endTime = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String[] qryArr = null;
		String tempQuery = null;
		JSONObject cleanupConf = null;
		try
		{
			LOGGER.info("Entry processDelete :: "+ interfaceId +" with normal mode :: startTime :: " + startTime);
			cleanupConf = tableDtls.getJSONObject("cleanup-conf");
			if(connection == null)
			{
				//System.err.println("Connection is not available..");
				LOGGER.error("Connection is not available..");
				throw new ApplicationException("Connection is not available..", null);
			}

			if(cleanupConf.get("is-enabled") != null && "true".equalsIgnoreCase(cleanupConf.getString("is-enabled").trim()) 
					&& cleanupConf.get("sel_query") != null && !cleanupConf.getString("sel_query").trim().isEmpty()&& !cleanupConf.get("retension").toString().trim().isEmpty())
			{
				tempQuery = cleanupConf.getString("sel_query").trim();

				statement = connection.prepareStatement(tempQuery);
				statement.setFetchSize(100);
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{
					if(cleanupConf.get("del_query") != null && !cleanupConf.getString("del_query").trim().isEmpty())
					{
						tempQuery = cleanupConf.getString("del_query").trim();
						if(tempQuery.contains("#"))
						{
							qryArr = tempQuery.split("#");
							for(String query : qryArr)
							{
								deleteData(connection, cleanupConf, query.trim(), resultSet.getString(1));
							}
						}
						else
							deleteData(connection, cleanupConf, tempQuery, resultSet.getString(1));
					}
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			endTime = System.currentTimeMillis();
			LOGGER.info("Exit processDelete :: "+ interfaceId +" with normal mode :: Time requires in ms :: " + (endTime - startTime));
			tempQuery = null;
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			resultSet = null;
			statement = null;
			qryArr = null;
			cleanupConf= null;
			startTime = null;
			endTime = null;
		}
	}

	public void processDeleteWithBatch(final long interfaceId, final Connection connection, final JSONObject tableDtls) throws Exception
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String[] qryArr = null;
		String selectQuery = null;
		String deleteQuery = null;
		String tempQuery = null;
		Set<Long> deleteIds = null;
		Long startTime = System.currentTimeMillis();
		Long endTime = null;
		JSONObject cleanupConf = null;
		int batchSize = 500;
		int queryCount = 0;
		try
		{
			LOGGER.info("Process DeleteWithBatch :: "+ interfaceId +" With Batchwise Start Time :: "+startTime);

			cleanupConf = tableDtls.getJSONObject("cleanup-conf");

			if(connection == null)
			{
				LOGGER.error("Connection is not available..");
				throw new ApplicationException("Connection is not available..", null);
			}

			if(cleanupConf.get("is-enabled") != null && "true".equalsIgnoreCase(cleanupConf.getString("is-enabled").trim()) 
					&& cleanupConf.get("sel_query") != null && !cleanupConf.getString("sel_query").trim().isEmpty()&& !cleanupConf.get("retension").toString().trim().isEmpty())
			{
				selectQuery = cleanupConf.getString("sel_query").trim();
				deleteQuery = cleanupConf.getString("del_query").trim();
				batchSize = (!cleanupConf.isNull("batch_size") && cleanupConf.get("batch_size") != null && cleanupConf.getInt("batch_size") != 0) ? cleanupConf.getInt("batch_size") : 500;
				tempQuery = deleteQuery;
				LOGGER.debug("Action perform with selectQuery :: " + selectQuery + "   deleteQuery :: " + deleteQuery);
			}
			else
				throw new ApplicationException("Wrong cleanupConf :: " + cleanupConf , null);

			qryArr = tempQuery.split("#");

			for(String query : qryArr)
			{
				LOGGER.info("Batch query :: " + (++queryCount) + " :: " + query);
				if(!query.trim().isEmpty() && query.contains("?"))
				{
					deleteIds = new HashSet<Long>();
					statement = connection.prepareStatement(selectQuery);
					statement.setFetchSize(100);
					resultSet = statement.executeQuery();
					while(resultSet.next())
					{
						deleteIds.add(resultSet.getLong(1));
						if(deleteIds.size() == batchSize)
						{
							deleteDataWithBatch(connection, cleanupConf, query.trim(), deleteIds);
							deleteIds.clear();
							deleteIds = null;
							deleteIds = new HashSet<Long>();
						}
					}

					if(deleteIds != null && !deleteIds.isEmpty())
					{
						deleteDataWithBatch(connection, cleanupConf, query.trim(), deleteIds);
						deleteIds.clear();
						deleteIds = null;
					}
					else if(deleteIds != null && deleteIds.isEmpty())
					{
						LOGGER.debug("Delete file Ids are empty");
					}
				}
				else if(!query.trim().isEmpty())
				{
					statement = connection.prepareStatement(query);
					int row = statement.executeUpdate();
					LOGGER.debug("Single Query Executed with out batch in Delete With Batch row :: " + row);
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			endTime = System.currentTimeMillis();
			LOGGER.info("Process DeleteWithBatch :: "+ interfaceId +" With Batchwise End Time:: "+(endTime-startTime));
			tempQuery = null;
			deleteIds = null;
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			resultSet = null;
			statement = null;
			qryArr = null;
			startTime = null;
			endTime = null;
			cleanupConf = null;
		}
	}

	public void processDeleteWithInClause(final long interfaceId, final Connection connection, final JSONObject tableDtls) throws Exception
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String[] qryArr = null;
		String tempQuery = null;
		String selectQuery = null;
		String deleteQuery = null;
		Set<Long> deleteIds = null;
		Long startTime = System.currentTimeMillis();
		Long endTime = null;
		Set<String> deleteIdStr = null;
		JSONObject cleanupConf = null;
		int batchSize = 500;
		int queryCount = 0;
		try
		{
			LOGGER.info("Process DeleteWithInClause :: "+ interfaceId + " with Batchwise Start Time :: "+startTime);

			cleanupConf = tableDtls.getJSONObject("cleanup-conf");

			if(connection == null)
			{
				LOGGER.error("Connection is not available..");
				throw new ApplicationException("Connection is not available..", null);
			}

			if(cleanupConf.get("is-enabled") != null && "true".equalsIgnoreCase(cleanupConf.getString("is-enabled").trim()) 
					&& cleanupConf.get("sel_query") != null && !cleanupConf.getString("sel_query").trim().isEmpty()&& !cleanupConf.get("retension").toString().trim().isEmpty())
			{
				selectQuery = cleanupConf.getString("sel_query").trim();
				deleteQuery = cleanupConf.getString("del_query").trim();
				batchSize = (!cleanupConf.isNull("batch_size") && cleanupConf.get("batch_size") != null && cleanupConf.getInt("batch_size") != 0) ? cleanupConf.getInt("batch_size") : 500;
				tempQuery = deleteQuery;
				LOGGER.debug("Action perform with selectQuery :: " + selectQuery + "   deleteQuery :: " + deleteQuery);
			}
			else
				throw new ApplicationException("Wrong cleanupConf :: " + cleanupConf , null);

			qryArr = tempQuery.split("#");

			for(String query : qryArr)
			{
				LOGGER.info("Batch query :: " + (++queryCount) + " :: " + query);
				if(!query.trim().isEmpty() && query.contains("?"))
				{
					statement = connection.prepareStatement(selectQuery);
					statement.setFetchSize(100);
					resultSet = statement.executeQuery();
					deleteIds = new HashSet<Long>();
					deleteIdStr = new HashSet<String>();

					while(resultSet.next())
					{
						deleteIds.add(resultSet.getLong(1));
						deleteIdStr.add("'"+resultSet.getLong(1)+"'");
						if(deleteIds.size() == batchSize) 
						{
							deleteDataWithInClause(connection, cleanupConf, query.trim(), deleteIds, deleteIdStr);
							deleteIds.clear();deleteIdStr.clear();
							deleteIds = null; deleteIdStr = null;
							deleteIds = new HashSet<Long>(); deleteIdStr = new HashSet<String>();
						}
					}
					if(deleteIds != null && !deleteIds.isEmpty())
					{
						deleteDataWithInClause(connection, cleanupConf, query, deleteIds, deleteIdStr);
						deleteIds.clear(); deleteIdStr.clear();
						deleteIds = null; deleteIdStr = null;
					}
					else if(deleteIds != null && deleteIds.isEmpty())
					{
						LOGGER.debug("Delete file Ids are empty");
					}
				}
				else if(!query.trim().isEmpty())
				{
					statement = connection.prepareStatement(query);
					int statementResult = statement.executeUpdate();
					LOGGER.debug("Single Query Executed with out batch in Delete With Batch SQL Statement Result :: " + statementResult);
				}
			}
		}
		catch(Exception exception)
		{
			LOGGER.error("Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			endTime = System.currentTimeMillis();
			LOGGER.info("Process DeleteWithInClause :: "+ interfaceId +" with Batchwise End Time:: "+(endTime-startTime));
			tempQuery = null;
			deleteIds = null;
			try
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			resultSet = null;
			statement = null;
			qryArr = null;
			startTime = null;
			endTime = null;
			deleteIdStr = null;
			cleanupConf = null;
		}
	}

	public void deleteData(final Connection connection, JSONObject json, String query, String value) throws Exception
	{
		PreparedStatement statement = null;
		try
		{
			LOGGER.debug("Delete Data :: value :: query " + value +" :: " + query);
			query = query.replace("?", value);
			statement = connection.prepareStatement(query);
			int statementResult = statement.executeUpdate();
			LOGGER.info("SQL Statement Result :: " + statementResult + " from " + json.get("name") + " query :: " + query);
		}
		catch (Exception exception)
		{
			LOGGER.error("Exception : " + exception.getMessage(), exception);
			throw exception;
		}
		finally
		{
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			statement = null;
		}
	}

	public void deleteDataWithBatch(final Connection connection, final JSONObject json, String query, Set<Long> deleteIds)
	{
		PreparedStatement statement = null;
		Long startTime = System.currentTimeMillis();
		Long endTime = null;
		try
		{

			if(deleteIds!= null && !deleteIds.isEmpty())
				LOGGER.info("query ::: " + query +" batch_size :: "+json.getInt("batch_size")+ " Ids ::: " + deleteIds);
			else
			{
				LOGGER.debug("deleteIds is empty");
				return;				
			}

			if(query.contains("?") && deleteIds != null && !deleteIds.isEmpty())
			{
				statement = connection.prepareStatement(query);
				int i = 0;
				for (Long id : deleteIds) 
				{
					statement.setLong(1, id);
					statement.addBatch();
					i++;
					if(json.getInt("batch_size") == i) 
					{
						int rows[] = statement.executeBatch();	
						LOGGER.info(rows + " Rows deleted from " + json.get("name") + " with Delete Data With Batch");
						i = 0;
					}
				}
				if(i > 0)
				{
					int rows[] = statement.executeBatch();	
					LOGGER.info(rows + " Rows deleted from " + json.get("name") + " with Delete Data With Batch");
				}
			}

		}
		catch (Exception exception)
		{
			LOGGER.error("Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			endTime = System.currentTimeMillis();
			LOGGER.info("Delete Data With Batch Time Required :: "+(endTime-startTime));
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			statement = null;
			startTime = null;
			endTime = null;
		}
	}

	public void deleteDataWithInClause(final Connection connection, final JSONObject json, final String query, Set<Long> deleteIds, Set<String> deleteIdStr)
	{
		PreparedStatement statement = null;
		Long startTime = System.currentTimeMillis();
		Long endTime = null;
		String ids = null;
		String tempQuery = query;
		try
		{

			if(deleteIds!= null && !deleteIds.isEmpty())
				LOGGER.info("Batch Start Time :: " + startTime);
			else
			{
				LOGGER.debug("deleteIds is empty");
				return;				
			}
			if(tempQuery.contains("::character varying")) 
			{
				tempQuery = tempQuery.replace("::character varying", "");
				ids = deleteIdStr.toString().replaceAll("\\[", "(").replaceAll("\\]", ")");
			}
			else 
				ids = deleteIds.toString().replaceAll("\\[", "(").replaceAll("\\]", ")");

			tempQuery = tempQuery.replace("=", "in");
			tempQuery = tempQuery.replace("?", ids);

			LOGGER.info("batch_size :: " + json.getInt("batch_size")+"   query ::: " + tempQuery );

			statement = connection.prepareStatement(tempQuery);
			int statementResult = statement.executeUpdate();


			LOGGER.debug(" Delete Data With In Clause Single Query Executed :: SQL Statement Result :: " + statementResult);
		}
		catch (Exception exception)
		{
			LOGGER.error("Exception : " + exception.getMessage(), exception);
		}
		finally
		{
			endTime = System.currentTimeMillis();
			LOGGER.info("Delete Data With In Clause Time Required :: "+(endTime-startTime));
			try
			{
				if(statement != null)
					statement.close();
			}
			catch(Exception exception2)
			{
				LOGGER.error("Exception : " + exception2.getMessage(), exception2);
			}
			statement = null;
			startTime = null;
			endTime = null;
			ids = null;
		}
	}

}