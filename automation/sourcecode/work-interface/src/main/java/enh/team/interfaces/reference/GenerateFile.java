package enh.team.interfaces.reference;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.PropsLoader;
import com.finevm.enh.interfaces.entities.DataSourceConfig;
import com.finevm.enh.interfaces.entities.Interfaces;
import com.finevm.enh.util.IWorkConstants;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

import enh.team.interfaces.file.FileOperation;

public class GenerateFile {

	private JSONObject config;
	private EntityManager manager;
	private Connection pgConnection;
	private List<Interfaces> interfaces;
	private Properties commonProperties;
	private Properties descProperties;

	public void start(boolean withPGdb)
	{
		try 
		{
			init(IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/input/conf/config.json", IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/input/common.properties", IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/input/conf/desc.properties");

			if(withPGdb)
			{
				generateTable(IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/input/conf/tablescript.sql");
				populatePgData(IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/input/refData - indoSat.sql");
			}

			getInterfaces();

			writeXlsxSheet(IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/input/conf/interface_template.xlsx", "interface", IWorkConstants.INTERFACE_REFERENCE_GENERATION + "/output/interface_out.xlsx");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(manager != null) 
					manager.close();
				if(pgConnection != null)
					pgConnection.close();
				config = null;
				interfaces = null;
				manager = null;
				pgConnection = null;
				commonProperties = null;
				descProperties = null;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	private void init(String configLoc, String commonPro, String descPro) 
	{
		System.out.println("Entry to init...\n\n");
		File file = null;
		try
		{	
			pgConnection = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);
			manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");

			file = new File(configLoc);
			config = new JSONObject(FileOperation.readFileWithoutNewLine(file));

			commonProperties = PropsLoader.loadProperties(commonPro);
			descProperties = PropsLoader.loadProperties(descPro);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			file = null;
			System.out.println("Exit from init...\n\n");
		}
	}

	private void generateTable(String tableScriptsPath)
	{
		System.out.println("GenerateTable init...\n\n");
		String script = null;
		Statement statement = null;
		ResultSet resultSet = null;
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		String line = null;
		try 
		{

			if(pgConnection == null) 
			{
				new Exception("Connection is null :: " + pgConnection);
			}

			fileReader = new FileReader(tableScriptsPath);
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) 
			{
				script = line.trim();
				if(script != null && !script.isEmpty()) 
				{
					System.out.println("Script : " + script);
					statement = pgConnection.createStatement();
					statement.executeUpdate(script);
				}
			} 
		} 
		catch (Exception e) 
		{
			System.out.println("Table generation failed...\n\n");
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(resultSet != null)
					resultSet.close();
				if(statement != null)
					statement.close();	
				if(fileReader != null)
					fileReader.close();
				if(bufferedReader != null)
					bufferedReader.close();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
			script = null;
			statement = null;
			resultSet = null;
			bufferedReader = null;
			fileReader = null;
			line = null;
			System.out.println("Exit from GenerateTable...\n\n");
		}
	}

	private void populatePgData(String refDataSql) 
	{
		System.out.println("Entry to PopulatePgData...\n\n");

		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		Statement statement = null;
		String line = null;
		JSONArray jsonArray = null;
		File file = null;
		try 
		{
			file = new File(refDataSql);
			statement = pgConnection.createStatement();
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) 
			{
				if(config.get("TABLE_NAMES_TO_GENERATE") !=null && config.get("TABLE_NAMES_TO_GENERATE") instanceof JSONArray) {
					jsonArray = config.getJSONArray("TABLE_NAMES_TO_GENERATE");
					for (Object object : jsonArray) 
					{
						if(line.contains(object.toString()))
						{
							statement.executeUpdate(line); 
							//System.out.println(statement.executeUpdate(line) + "  ::  "+line);;
						}
					}
				}
			} 

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if(statement != null)
					statement.close();
				if(fileReader != null)
					fileReader.close();
				if(bufferedReader != null)
					bufferedReader.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			statement = null;
			fileReader = null;
			bufferedReader = null;
			jsonArray = null;
			file = null;
			line = null;
			System.out.println("Exit from PopulatePgData...\n\n");
		}
	}

	@SuppressWarnings("unchecked")
	private List<Interfaces> getInterfaces() 
	{
		interfaces = manager.createQuery("from " + Interfaces.class.getCanonicalName()+" order by 1").getResultList();
		return interfaces;
	}


	private void writeXlsxSheet(String xlsxFileLoc, String sheetName, String newxlsxFileName) 
	{
		System.out.println("Entry to StartJob...\n\n");

		File file = null;
		File newFile = null;
		FileInputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		FileOutputStream outputStream = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		Iterator<Cell> cellIterator = null;
		Iterator<Row> rowIterator = null;
		Row row = null;
		Cell cell = null;
		Interfaces interfac = null;
		Object object = null;
		JSONObject headers = null;
		JSONObject descAndService = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String descAndServiceStr = null;
		String iType = null;
		String iTransType = null;

		try 
		{
			file = new File(xlsxFileLoc);
			newFile = new File(newxlsxFileName);
			inputStream = new FileInputStream(file);
			bufferedInputStream = new BufferedInputStream(inputStream, 1024);
			outputStream = new FileOutputStream(newFile);
			preparedStatement = pgConnection.prepareStatement(config.getString("ATTR_VALUE_QUERY"));
			//Get the workbook instance for XLS file 
			workbook = new XSSFWorkbook(bufferedInputStream);

			//Get first sheet from the workbook
			sheet = workbook.getSheet(sheetName);
			headers = config.getJSONObject("XLSX_HEADER");
			rowIterator = sheet.iterator();

			System.out.println("headers size :: " + headers.keySet().size());

			while(rowIterator.hasNext()) 
			{


				row = rowIterator.next();

				if(row.getRowNum() != 0 &&  interfaces.size() > row.getRowNum()-1 ) 
				{
					interfac = interfaces.get(row.getRowNum()-1);
					System.out.println(interfac.getInterfaceId() +"  :: " + interfac);
				} 

				if(interfaces.size() < row.getRowNum()) {
					break;
				}
				//For each row, iterate through each columns
				cellIterator = row.cellIterator();



				cell_iterator : while(cellIterator.hasNext()) 
				{
					cell = cellIterator.next();
					cell.setCellValue("-");
					if(row.getRowNum() == 0)
					{
						if(cell.getColumnIndex() < headers.keySet().size()) {
							cell.setCellValue(headers.get(cell.getColumnIndex()+"")+"");
						}
						continue cell_iterator;
					}

					//if(row.getRowNum() == 0 &&  interfaces.size() < row.getRowNum()-1 )
					//continue row_iterator;

					if(cell.getColumnIndex() == 0) 
						cell.setCellValue(interfac.getInterfaceId());//INTERFACE ID	

					else if(cell.getColumnIndex() == 1) 
						cell.setCellValue(interfac.getModule().getModuleId());//MODULE ID	

					else if(cell.getColumnIndex() == 2) 
						cell.setCellValue(interfac.getName());//NAME	

					else if(cell.getColumnIndex() == 3) 
					{
						//DESCRIPTION
						descAndServiceStr = (String) descProperties.get(interfac.getInterfaceId().toString());
						if(descAndServiceStr != null ) {
							descAndService = new JSONObject(descAndServiceStr);
							if(descAndService.get("description") == null)
								descAndService.put("description", "");

							if(descAndService.get("description") != null) {
								if(interfac.getInterfaceType().intValue() == 1)
									iType = "1. ASYNC_QUEUE_INTERFACE_TYPE\n";
								if(interfac.getInterfaceType().intValue() == 2)
									iType = "1. ASYNC_FILE_INTERFACE_TYPE\n";
								if(interfac.getInterfaceType().intValue() == 3)
									iType = "1. SYNC_INTERFACE_TYPE\n";

								if(interfac.getTransactionType().intValue() == 1)
									iTransType = "2. SEND_TRANS_TYPE\n\n";
								if(interfac.getTransactionType().intValue() == 2)
									iTransType = "2. RECEIVE_TRANS_TYPE\n\n";
								//System.out.println(iType + iTransType + descAndService.getString("description").trim());
								cell.setCellValue(iType + iTransType + descAndService.getString("description").trim());	
							}
						}
						//-- ASYNC_QUEUE_INTERFACE_TYPE = 1
						//-- ASYNC_FILE_INTERFACE_TYPE = 2
						//-- SYNC_INTERFACE_TYPE = 3
						//-- SEND_TRANS_TYPE = 1
						//-- RECEIVE_TRANS_TYPE = 2
					}
					else if(cell.getColumnIndex() == 4) 
					{
						//SERVICES
						descAndServiceStr = (String) descProperties.get(interfac.getInterfaceId().toString());
						if(descAndServiceStr != null && !descAndServiceStr.trim().isEmpty()) {
							descAndService = new JSONObject(descAndServiceStr);
							if(descAndService.get("services") != null && !descAndService.getString("services").trim().isEmpty())
								cell.setCellValue(descAndService.getString("services").trim());	
						}

					}

					else if(cell.getColumnIndex() == 5) {
						if(interfac.getModule().getCallBack() != null && !interfac.getModule().getCallBack().trim().isEmpty())
							cell.setCellValue(interfac.getModule().getCallBack());//CALLBACK	
					}
					else if(cell.getColumnIndex() == 6) {
						if(interfac.getConverter() != null && !interfac.getConverter().trim().isEmpty())
							cell.setCellValue(interfac.getConverter());//CONVERTER	
					}
					else if(cell.getColumnIndex() == 7) { 
						if(interfac.getPublisher() != null && !interfac.getPublisher().trim().isEmpty())
							cell.setCellValue(interfac.getPublisher());//PUBLISHER	
					}
					else if(cell.getColumnIndex() == 8) 
					{

						//CALL_BACK_JNDI_NAME
						preparedStatement.setString(1, commonProperties.get("CALL_BACK_JNDI_NAME").toString());
						preparedStatement.setLong(2, interfac.getInterfaceId());
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next())
							cell.setCellValue(resultSet.getString(1));
					}

					else if(cell.getColumnIndex() == 9) 
					{
						//CSV_MERGER_ATTR
						preparedStatement.setString(1, commonProperties.get("CSV_MERGER_ATTR").toString());
						preparedStatement.setLong(2, interfac.getInterfaceId());
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next())
							cell.setCellValue(resultSet.getString(1));
					}

					else if(cell.getColumnIndex() == 10) 
					{
						//PROCESSOR_URL_ATTR
						preparedStatement.setString(1, commonProperties.get("PROCESSOR_URL_ATTR").toString());
						preparedStatement.setLong(2, interfac.getInterfaceId());
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next())
							cell.setCellValue(resultSet.getString(1));
					}

					else if(cell.getColumnIndex() == 11) 
					{
						//QUEUE_CONSUMER_ATTR
						preparedStatement.setString(1, commonProperties.get("QUEUE_CONSUMER_ATTR").toString());
						preparedStatement.setLong(2, interfac.getInterfaceId());
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next())
							cell.setCellValue(resultSet.getString(1));
					}

					else if(cell.getColumnIndex() == 12) 
					{
						//REJECTION_FILE_ATTR
						preparedStatement.setString(1, commonProperties.get("REJECTION_FILE_ATTR").toString());
						preparedStatement.setLong(2, interfac.getInterfaceId());
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next())
							cell.setCellValue(resultSet.getString(1));
					}

					else if(cell.getColumnIndex() == 13) 
					{
						//XML_DATA_CONVERTER_ATTR
						preparedStatement.setString(1, commonProperties.get("XML_DATA_CONVERTER_ATTR").toString());
						preparedStatement.setLong(2, interfac.getInterfaceId());
						resultSet = preparedStatement.executeQuery();
						if(resultSet.next())
							cell.setCellValue(resultSet.getString(1));
					}

					else if(cell.getColumnIndex() == 14) 
					{
						if(commonProperties.get("REJECTION_BY_FILE_ID_INTERFACES") != null && commonProperties.get("REJECTION_BY_FILE_ID_INTERFACES").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 15) 
					{
						if(commonProperties.get("KPI_FEED_INTERFACE_LIST") != null && commonProperties.get("KPI_FEED_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}
					else if(cell.getColumnIndex() == 16) 
					{
						if(commonProperties.get("KPI_LACCI_FEED_INTERFACE_LIST") != null && commonProperties.get("KPI_LACCI_FEED_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 17) 
					{
						if(commonProperties.get("KPI_RELATED_INTERFACE_LIST") != null && commonProperties.get("KPI_RELATED_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 18) 
					{
						if(commonProperties.get("PULL_MONGO_INTERFACE_LIST_STR") != null && commonProperties.get("PULL_MONGO_INTERFACE_LIST_STR").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}
					else if(cell.getColumnIndex() == 19) 
					{
						if(commonProperties.get("PULL_MONGO_INTERFACE_LIST") != null && commonProperties.get("PULL_MONGO_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 20) 
					{
						if(commonProperties.get("ASYNC_PULL_DATA_TO_FILE_INTERFACE_LIST") != null && commonProperties.get("ASYNC_PULL_DATA_TO_FILE_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 21) 
					{
						if(commonProperties.get("JSON_CONVERTER_INTERFACE_LIST") != null && commonProperties.get("JSON_CONVERTER_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}
					else if(cell.getColumnIndex() == 22) 
					{
						if(commonProperties.get("ASYNC_PULL_DATA_TO_FILE_FROM_MONGO_INTERFACE_LIST") != null && commonProperties.get("ASYNC_PULL_DATA_TO_FILE_FROM_MONGO_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 23) 
					{
						if(commonProperties.get("SELF_NOTIFY_INTERFACE_LIST") != null && commonProperties.get("SELF_NOTIFY_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 24) 
					{
						if(commonProperties.get("DUMP_JOB_INTERFACE_IDS") != null && commonProperties.get("DUMP_JOB_INTERFACE_IDS").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 25) 
					{
						if(commonProperties.get("DAILY_DUMP_INTERFACE_LIST") != null && commonProperties.get("DAILY_DUMP_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 26) 
					{
						if(commonProperties.get("PG_DAILY_DUMP_INTERFACE_LIST") != null && commonProperties.get("PG_DAILY_DUMP_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 27) 
					{
						if(commonProperties.get("ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST") != null && commonProperties.get("ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 28) 
					{
						if(commonProperties.get("REPROCESS_ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST") != null && commonProperties.get("REPROCESS_ASYNC_PULL_DATA_TO_FILE_AND_PUSH_INTERFACE_LIST").toString().contains(interfac.getInterfaceId()+"") )
							cell.setCellValue("yes");	
					}

					else if(cell.getColumnIndex() == 29) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PROCESS_FILE_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PROCESS_FILE_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 30) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PROCESS_RECEIVE_FILE_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PROCESS_RECEIVE_FILE_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 31) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PREPARE_REJECTION_FILE_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PREPARE_REJECTION_FILE_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 32) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PULLDATA_FROM_POSTGERS_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PULLDATA_FROM_POSTGERS_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 33) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_REPROCESS_FILE_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_REPROCESS_FILE_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 34) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PROCESS_PENDING_ORDER_RES_CONSUMER_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PROCESS_PENDING_ORDER_RES_CONSUMER_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 35) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PROCESS_RESPONSE_AVAILABLE_RECORDS_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PROCESS_RESPONSE_AVAILABLE_RECORDS_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 36) 
					{
						object = commonProperties.get("SCHEDULAR_FOR_PROCESS_INQUEUE_RECORD_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PROCESS_INQUEUE_RECORD_"+interfac.getInterfaceId()+"="+object.toString());	
					}

					else if(cell.getColumnIndex() == 37) 
					{
						if(commonProperties.containsKey("SCHEDULAR_FOR_PROCESS_CONSUMER_"+interfac.getInterfaceId().longValue()))
							object = commonProperties.get("SCHEDULAR_FOR_PROCESS_CONSUMER_"+interfac.getInterfaceId().longValue());
						if(object != null && !object.toString().isEmpty())
							cell.setCellValue("SCHEDULAR_FOR_PROCESS_CONSUMER_"+interfac.getInterfaceId()+"="+object.toString());	
					}
					else if(cell.getColumnIndex() == 38) 
					{
						cell.setCellValue(interfac.getInterfaceId());	
					}
					continue cell_iterator;

				} 
			}
			workbook.write(outputStream);
			inputStream.close();
			outputStream.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{

			try 
			{
				if(bufferedInputStream != null)
					bufferedInputStream.close();
				if(inputStream != null)
					inputStream.close();
				if(outputStream != null)
					outputStream.close();
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}


			file = null;
			newFile = null;
			bufferedInputStream = null;
			inputStream = null;
			outputStream = null;
			workbook = null;
			sheet = null;
			cellIterator = null;
			rowIterator = null;
			row = null;
			cell = null;
			interfac = null;
			object = null;
			headers = null;
			descAndService = null;
			preparedStatement = null;
			resultSet = null;
			descAndServiceStr = null;
			iType = null;
			iTransType = null;

			System.out.println("Exit from StartJob...\n\n");
		}
	}


	public void test() 
	{
		pgConnection = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);
		manager = DataSourceConfig.getEntityManager("com.finevm.enh.interfaces.entities");
		//ResultSet resultSet = null;
		try 
		{
			for (Interfaces inter : getInterfaces()) {
				if((inter.getInterfaceType().longValue()== 2l && inter.getTransactionType().longValue() == 1l) || (inter.getInterfaceType().longValue()== 2l && inter.getTransactionType().longValue() == 2l))
				System.out.println("INSERT INTO INTERFACE.MS_INTERFACE_ATTR (ATTRIBUTE_ID_N, INTERFACE_ID_N, NAME_V, VALUE_V, LAST_UPDATED_TIME_DT) VALUES ("+inter.getInterfaceId()+"0"+(inter.getAttributes().size()+1)+", "+inter.getInterfaceId()+", 'PPK Path', '/home/appuser/snoc/snocconf/interfaceconf/credentials/SFTP.ppk', NOW());");
				
			}
		} catch (Exception e) 
		{
		}
		
	}
	
}
