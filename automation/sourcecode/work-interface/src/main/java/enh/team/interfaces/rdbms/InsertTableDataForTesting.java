package enh.team.interfaces.rdbms;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import com.finevm.enh.util.IWorkConstants;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

public class InsertTableDataForTesting
{
	private static Connection connection_ = null;
	private PreparedStatement statement = null;

	int totalRecord = 1000000;
	int batchSize = 10000;

	private static FileWriter fileWriter = null;
	private static String data = null;

	public InsertTableDataForTesting()
	{
		try
		{
			connection_= RDBMS.getDBConnection(PropType.RDBMS_144);

			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");

			File file = new File(IWorkConstants.LOG_LOC+"BatchCleanupUtil_Log_" + format.format(date) + ".json");
			fileWriter = new FileWriter(file);


			data = "DB connection made.. " + connection_;
			fileWriter.write(data + "\n");
			fileWriter.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception
	{
		//	process();
		//new InsertTableDataForTesting().insertFor1137();
	}

	static void process()
			throws Exception
	{
		long startTime = 0L;
		long endTime = 0L;
		try
		{
			InsertTableDataForTesting test = new InsertTableDataForTesting();

			startTime = System.currentTimeMillis();

			test.sp_stock_dump(Long.valueOf(1L)).excute("sp_stock_dump => 1  ");

			test.sp_alloc_dump(Long.valueOf(3L)).excute("sp_alloc_dump => 3  ");

			test.stock_dump_voucher(Long.valueOf(5L)).excute("stock_dump_voucher => 5  ");

			test.alloc_dump_voucher(Long.valueOf(7L)).excute("alloc_dump_voucher => 7  ");

			test.product_details(Long.valueOf(9L)).excute("product_details => 9  ");

			test.serial_expiry(Long.valueOf(11L)).excute("serial_expiry => 11  ");

			test.lacci_data(Long.valueOf(62L)).excute("lacci_data => 62  ");

			test.tr_temp_upload_aggr_failure(Long.valueOf(64L)).excute("tr_temp_upload_aggr_failure => 64  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(65L)).excute("tr_temp_upload_aggr_failure => 65  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(66L)).excute("tr_temp_upload_aggr_failure => 66  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(67L)).excute("tr_temp_upload_aggr_failure => 67  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(68L)).excute("tr_temp_upload_aggr_failure => 68  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(69L)).excute("tr_temp_upload_aggr_failure => 69  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(70L)).excute("tr_temp_upload_aggr_failure => 70  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(71L)).excute("tr_temp_upload_aggr_failure => 71  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(75L)).excute("tr_temp_upload_aggr_failure => 75  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(76L)).excute("tr_temp_upload_aggr_failure => 76  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(77L)).excute("tr_temp_upload_aggr_failure => 77  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(78L)).excute("tr_temp_upload_aggr_failure => 78  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(79L)).excute("tr_temp_upload_aggr_failure => 79  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(80L)).excute("tr_temp_upload_aggr_failure => 80  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(81L)).excute("tr_temp_upload_aggr_failure => 81  ");
			test.tr_temp_upload_aggr_failure(Long.valueOf(82L)).excute("tr_temp_upload_aggr_failure => 82  ");

			test.tr_temp_indosat_to_org_validation("tr_temp_indosat_to_org_validation(1065)");
			test.tr_temp_org_to_org_validation("tr_temp_org_to_org_validation(1064)");
			test.tr_temp_trans_sum_validation("tr_temp_trans_sum_validation(1075)");

			endTime = System.currentTimeMillis();
		}
		finally
		{
			if (connection_ != null) {
				connection_.close();
			}
			data = "Program Start Time :  " + startTime + "\n";
			fileWriter.write(data);
			System.out.println(data);

			data = "Program End   Time :  " + endTime + "\n";
			fileWriter.write(data);
			System.out.println(data);

			data = "Program Run Time :  " + (endTime - startTime) + "\n";
			fileWriter.write(data);
			System.out.println(data);

			if (fileWriter != null)
				fileWriter.close();
		}
	}

	InsertTableDataForTesting sp_stock_dump(Long file_id)
	{
		try
		{
			this.statement = connection_.prepareStatement("INSERT INTO interface.sp_stock_dump(iccid, msisdn, imsi, do_id, so_id, destination_dealer_id, branch_code, brand, product_expired_date, stock_transfer_date, program_code, program_name, source_dealer_id, file_id, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setString(1, "34212600066200000008");
			this.statement.setString(2, "1237932971000");
			this.statement.setString(3, "12374929997000");
			this.statement.setString(4, "MDPC//SPStockSE0601");
			this.statement.setString(5, "SPStockSE0601");
			this.statement.setString(6, "Dealer001");
			this.statement.setString(7, "101");
			this.statement.setString(8, "IM3");
			this.statement.setString(9, "20191221");
			this.statement.setString(10, "20171213");
			this.statement.setString(11, "619");
			this.statement.setString(12, "SP PAKET MOBO F");
			this.statement.setString(13, "SAT10");
			this.statement.setString(14, file_id+"");
			this.statement.setString(15, "1304");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting sp_alloc_dump(Long file_id)
	{
		try
		{
			this.statement = connection_.prepareStatement(
					"INSERT INTO interface.sp_alloc_dump(iccid, msisdn, imsi, do_id, so_id, dealer_id, branch_code, brand, product_expired_date, so_creation_date, alloc_id, program_code, program_name, type,alloc_date, payment_date, file_id, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setString(1, "76542107819990000359");
			this.statement.setString(2, "198779900034");
			this.statement.setString(3, "19887798900033");
			this.statement.setString(4, "Dealer001/20171009/SPAllocSE16");
			this.statement.setString(5, "SPAllocSE16");

			this.statement.setString(6, "Giri1234");
			this.statement.setString(7, "22");
			this.statement.setString(8, "MAT");
			this.statement.setString(9, "20990220");
			this.statement.setString(10, "20171009");

			this.statement.setString(11, "100");
			this.statement.setString(12, "103");
			this.statement.setString(13, "BRIGHT SIMCARD");
			this.statement.setString(14, "C");

			this.statement.setString(15, "25-May-19");
			this.statement.setString(16, "25-May-2019");
			this.statement.setString(17, file_id+"");
			this.statement.setString(18, "1304");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting stock_dump_voucher(Long file_id)
	{
		try
		{
			this.statement = connection_.prepareStatement(
					"INSERT INTO interface.stock_dump_voucher(serial_number, do_id, so_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, stock_transfer_date, program_code, program_name, source_dealer_id, file_id_n, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setString(1, "121296595670000030");
			this.statement.setString(2, "Dealer001/20171017/VouStockSE0413001");
			this.statement.setString(3, "VouStockSE0413001");

			this.statement.setString(4, "Dealer001");
			this.statement.setString(5, "133");
			this.statement.setString(6, "001");
			this.statement.setString(7, "IND");
			this.statement.setString(8, "20191221");
			this.statement.setString(9, "1000");
			this.statement.setString(10, "20171213");

			this.statement.setString(11, "555");
			this.statement.setString(12, "RTProd-V-NS");
			this.statement.setString(13, "SAT10");
			this.statement.setLong(14, file_id.longValue());
			this.statement.setLong(15, 1304L);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting alloc_dump_voucher(Long file_id_n)
	{
		try
		{
			this.statement = connection_.prepareStatement(
					"INSERT INTO interface.alloc_dump_voucher(serial_number, do_id, so_id, dealer_id, artwork_code, branch_code, brand, product_expired_date, nominal_value, so_creation_date, alloc_no, program_code, program_name, resource_type, alloc_date, payment_date, file_id_n, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setString(1, "121296595670000030");
			this.statement.setString(2, "Dealer001/20171017/VouStockSE0413001");
			this.statement.setString(3, "VouStockSE0413001");

			this.statement.setString(4, "Dealer001");
			this.statement.setString(5, "131");
			this.statement.setString(6, "027");
			this.statement.setString(7, "IND");

			this.statement.setString(8, "20191221");
			this.statement.setString(9, "25000");
			this.statement.setString(10, "20171213");
			this.statement.setString(11, "002");

			this.statement.setString(12, "555");
			this.statement.setString(13, "RTProd-V-NS");
			this.statement.setString(14, " V");
			this.statement.setString(15, "05-OCT-17");

			this.statement.setString(16, "12-Mar-2018");
			this.statement.setLong(17, file_id_n.longValue());
			this.statement.setLong(18, 1304L);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting product_details(Long file_id_n)
	{
		try
		{
			this.statement = connection_.prepareStatement(
					"INSERT INTO interface.product_details( productid, productname, category, subcategory, startdt, enddt, salesprice, mrpprice, isserialized, seriltype, seriallength, file_id_n, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setString(1, "vini900012");
			this.statement.setString(2, "vini900012");
			this.statement.setString(3, "SP");
			this.statement.setString(4, "IM3");

			this.statement.setString(5, "2017-01-19");
			this.statement.setString(6, "2099-12-31");
			this.statement.setString(7, "100");

			this.statement.setString(8, "100");
			this.statement.setString(9, "1");
			this.statement.setString(10, "1");

			this.statement.setString(11, "18");
			this.statement.setLong(12, file_id_n.longValue());
			this.statement.setLong(13, 1304L);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting serial_expiry(Long file_id)
	{
		try
		{
			this.statement = connection_.prepareStatement("INSERT INTO interface.serial_expiry(sl_no, old_prd_code, new_prd_code, expiry_dt, file_id, status) VALUES (?,?,?,?,?,?)");

			this.statement.setString(1, "98450994439845000001");
			this.statement.setString(2, "103");
			this.statement.setString(3, "102");
			this.statement.setString(4, "2025-12-31");

			this.statement.setLong(5, file_id.longValue());
			this.statement.setLong(6, 1304L);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting lacci_data(Long file_id)
	{
		try
		{
			this.statement = connection_.prepareStatement("INSERT INTO interface.lacci_data(date_active, lac_id, cell_id, description, bts_id, bts_name, bts_type, site_id, site_name, site_type, territory_id, updated_date, latitude, longitude, file_id, status, snoc_bts_id, snoc_bts_type, snoc_site_id, snoc_site_type)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setString(1, "");
			this.statement.setString(2, "1119");
			this.statement.setString(3, "34278");
			this.statement.setString(4, "3G2IP_KOTABMBSEL_PL2");
			this.statement.setString(5, "01JKB047");

			this.statement.setString(6, "KOTA_BAMBU_SELATAN_PL");
			this.statement.setString(7, "MACRO");
			this.statement.setString(8, "01JKB473");
			this.statement.setString(9, "BATUSARI_KMGS");
			this.statement.setString(10, "3G");

			this.statement.setString(11, "MC-GROGOL TAMANSARI");
			this.statement.setString(12, "");
			this.statement.setString(13, "-6.180750");
			this.statement.setString(14, "106.801760");
			this.statement.setString(15, file_id+"");

			this.statement.setString(16, "1304");
			this.statement.setString(17, "113352");
			this.statement.setString(18, "80079");
			this.statement.setString(19, "113355");
			this.statement.setString(20, "80082");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting tr_temp_upload_aggr_failure(Long file_id)
	{
		try
		{
			this.statement = connection_.prepareStatement("INSERT INTO kpi.tr_temp_upload_aggr_failure( id_n, actor_key_v, actor_type_n, actor_id_n, event_type_n, metrics_key_v, metrics_type_n, metrics_id_n, no_of_events_n,source_type_n, source_id_n, data_flag_n, instance_type_n, instance_id_n, status_flag_n, system_type_v, correction_v, file_id_n, batch_number_n, data_string_v, error_code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			this.statement.setLong(1, 0L);
			this.statement.setString(2, "");
			this.statement.setLong(3, 0L);
			this.statement.setLong(4, 0L);
			this.statement.setLong(5, 0L);

			this.statement.setString(6, "");
			this.statement.setLong(7, 0L);
			this.statement.setLong(8, 0L);
			this.statement.setLong(9, 1L);

			this.statement.setLong(10, 0L);
			this.statement.setLong(11, 0L);
			this.statement.setLong(12, 0L);
			this.statement.setLong(13, 0L);

			this.statement.setLong(14, 0L);
			this.statement.setLong(15, -2L);
			this.statement.setString(16, "External");
			this.statement.setString(17, "Update");

			this.statement.setLong(18, file_id.longValue());
			this.statement.setLong(19, 0L);
			this.statement.setString(20, "2019-09-09 01:11:10|Traditional|Indosat Reload|Internet Bank  Prodcut Group Internet Banking|Voucher GPRS 5K   Product   Vocher Category|10|moboOut11|BADSAW3763|1000000|||||Voucher Type|1|2|3");
			this.statement.setLong(21, 50003L);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return this;
	}

	InsertTableDataForTesting tr_temp_indosat_to_org_validation(String message)
	{
		try
		{
			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");

			long start = 0L;
			long end = 0L;
			try
			{
				start = System.currentTimeMillis();

				int bsize = 0;
				int count = 0;

				data = "Please wait  Rows are going insert....\n";
				fileWriter.write(data);
				System.out.println(data);

				this.statement = connection_.prepareStatement("INSERT into  kpi.tr_temp_indosat_to_org_validation values(?,?)");

				this.statement.setDate(2, java.sql.Date.valueOf("2015-09-10"));

				for (int i = 0; i < this.totalRecord; i++)
				{
					count++;
					bsize++;

					this.statement.setString(1, format.format(date) + i);

					this.statement.addBatch();
					if (this.batchSize == bsize) {
						this.statement.executeBatch();
						bsize = 0;
					}

				}

				this.statement.executeBatch();

				data = count + " Rows are inserted.." + "\n";
				fileWriter.write(data);
				System.out.println(data);
			}
			finally
			{
				if (this.statement != null)
				{
					try {
						this.statement.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					this.statement = null;
				}
				end = System.currentTimeMillis();

				data = message + " Time required Start <==> End ::: " + end + "  <==>  " + start + "\n";

				fileWriter.write(data);

				data = message + " Time required in milli::: " + (end - start) + "\n";

				fileWriter.write(data);

				data = message + " Time required in min::: " + (end - start) / 60000L + "\n";

				fileWriter.write(data);

				System.out.println(data);

				start = 0L;
				end = 0L;
				fileWriter.flush();
			}

		}
		catch (Exception localException)
		{
		}

		return this;
	}

	InsertTableDataForTesting tr_temp_org_to_org_validation(String message)
	{
		try
		{
			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");

			long start = 0L;
			long end = 0L;
			try
			{
				start = System.currentTimeMillis();

				int bsize = 0;
				int count = 0;

				data = "Please wait  Rows are going insert....\n";
				fileWriter.write(data);
				System.out.println(data);

				this.statement = connection_.prepareStatement("INSERT into  kpi.tr_temp_org_to_org_validation values(?,?)");

				this.statement.setDate(2, java.sql.Date.valueOf("2015-09-10"));

				for (int i = 0; i < this.totalRecord; i++)
				{
					count++;
					bsize++;

					this.statement.setString(1, format.format(date) + i);

					this.statement.addBatch();
					if (this.batchSize == bsize) {
						this.statement.executeBatch();
						bsize = 0;
					}

				}

				this.statement.executeBatch();

				data = count + " Rows are inserted.." + "\n";
				fileWriter.write(data);
				System.out.println(data);
			}
			finally
			{
				if (this.statement != null)
				{
					try {
						this.statement.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					this.statement = null;
				}
				end = System.currentTimeMillis();

				data = message + " Time required Start <==> End ::: " + end + "  <==>  " + start + "\n";

				fileWriter.write(data);

				data = message + " Time required in milli::: " + (end - start) + "\n";

				fileWriter.write(data);

				data = message + " Time required in min::: " + (end - start) / 60000L + "\n";

				fileWriter.write(data);

				System.out.println(data);

				start = 0L;
				end = 0L;
				fileWriter.flush();
			}

		}
		catch (Exception localException)
		{
		}

		return this;
	}

	InsertTableDataForTesting tr_temp_trans_sum_validation(String message)
	{
		try
		{
			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy");

			long start = 0L;
			long end = 0L;
			try
			{
				start = System.currentTimeMillis();

				int bsize = 0;
				int count = 0;

				data = "Please wait  Rows are going insert....\n";
				fileWriter.write(data);
				System.out.println(data);

				this.statement = connection_.prepareStatement("INSERT into  kpi.tr_temp_trans_sum_validation values(?,?)");

				this.statement.setDate(2, java.sql.Date.valueOf("2015-09-10"));

				for (int i = 0; i < this.totalRecord; i++)
				{
					count++;
					bsize++;

					this.statement.setString(1, format.format(date) + i);

					this.statement.addBatch();
					if (this.batchSize == bsize) {
						this.statement.executeBatch();
						bsize = 0;
					}

				}

				this.statement.executeBatch();

				data = count + " Rows are inserted.." + "\n";
				fileWriter.write(data);
				System.out.println(data);
			}
			finally
			{
				if (this.statement != null)
				{
					try {
						this.statement.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					this.statement = null;
				}
				end = System.currentTimeMillis();

				data = message + " Time required Start <==> End ::: " + end + "  <==>  " + start + "\n";

				fileWriter.write(data);

				data = message + " Time required in milli::: " + (end - start) + "\n";

				fileWriter.write(data);

				data = message + " Time required in min::: " + (end - start) / 60000L + "\n";

				fileWriter.write(data);

				System.out.println(data);

				start = 0L;
				end = 0L;
				fileWriter.flush();
			}

		}
		catch (Exception localException)
		{
		}

		return this;
	}

	void excute(String message) throws IOException
	{
		long start = 0L;
		long end = 0L;
		try
		{
			start = System.currentTimeMillis();

			int bsize = 0;
			int count = 0;

			data = "Please wait  Rows are going insert....\n";
			fileWriter.write(data);
			System.out.println(data);

			for (int i = 0; i < this.totalRecord; i++)
			{
				count++;
				bsize++;
				this.statement.addBatch();
				if (this.batchSize == bsize) {
					this.statement.executeBatch();
					bsize = 0;
				}

			}

			this.statement.executeBatch();

			data = count + " Rows are inserted.." + "\n";
			fileWriter.write(data);
			System.out.println(data);
		}
		catch (SQLException exception)
		{
			System.out.println(exception.getNextException());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if (this.statement != null)
			{
				try {
					this.statement.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
				this.statement = null;
			}
			end = System.currentTimeMillis();

			data = message + " Time required Start <==> End ::: " + end + "  <==>  " + start + "\n";

			fileWriter.write(data);

			data = message + " Time required in milli::: " + (end - start) + "\n";

			fileWriter.write(data);

			data = message + " Time required in min::: " + (end - start) / 60000L + "\n";

			fileWriter.write(data);

			System.out.println(data);

			start = 0L;
			end = 0L;
			fileWriter.flush();
		}
	}


	void insertFor1137() throws Exception {
		Connection connection = connection_;
		PreparedStatement interfaceSummaryStatement = null;
		PreparedStatement interfaceFailureStatement = null;
		LargeObjectManager lobj = null;
		Long trans_id_n = 1137000000l;
		Long interface_id_n = 1137l;
		int totalRecord = 1000000;
		int batchSize = 2000;
		connection.setAutoCommit(false);
		lobj = ((org.postgresql.PGConnection) connection).getLargeObjectAPI();

		String trInterfaceSummaryQuery =
				"INSERT INTO "
						+ "interface.tr_interface_summary(" + 
						"  trans_id_n, interface_id_n, orgnl_request_data_b, request_data_b," + 
						"  request_time_dt, status_n, ref_data3_n, last_updated_time_dt)" + 
						"  VALUES (?, ?, ?, ?, ?, ?, ?, ?);";



		String trInterfaceFailureQuery =
				"INSERT INTO "
						+ "interface.tr_interface_failure(" + 
						"  trans_failure_id_n, trans_id_n, error_code_n, error_message_v," + 
						"  file_id_n,  last_updated_time_dt)" + 
						"  VALUES (?, ?, ?, ?, ?, ? );";


		interfaceSummaryStatement = connection.prepareStatement(trInterfaceSummaryQuery);
		interfaceFailureStatement = connection.prepareStatement(trInterfaceFailureQuery);






		int i = 0;
		for (int j = 0; j < totalRecord; j++) {
			trans_id_n = trans_id_n + j;
			interfaceSummaryStatement.setLong(1, trans_id_n);
			interfaceSummaryStatement.setLong(2, interface_id_n);
			interfaceSummaryStatement.setInt(3, getOidForData(lobj, "BLOB1".getBytes()));
			interfaceSummaryStatement.setInt(4, getOidForData(lobj, "BLOB2".getBytes()));
			interfaceSummaryStatement.setDate(5, java.sql.Date.valueOf("2016-11-20"));
			interfaceSummaryStatement.setLong(6, 1302l);
			interfaceSummaryStatement.setLong(7, trans_id_n+0l);
			interfaceSummaryStatement.setDate(8, java.sql.Date.valueOf("2019-11-20"));
			i++;
			interfaceSummaryStatement.addBatch();

			interfaceFailureStatement.setLong(1, trans_id_n);
			interfaceFailureStatement.setLong(2, trans_id_n);
			interfaceFailureStatement.setLong(3, 10002l);
			interfaceFailureStatement.setString(4, "DEVELOPMENT_TEST");
			interfaceFailureStatement.setLong(5, trans_id_n);
			interfaceFailureStatement.setDate(6, java.sql.Date.valueOf("2016-09-10"));

			interfaceFailureStatement.addBatch();
			if(i==batchSize) {
				System.out.println(j+".   "+
						"interfaceSummaryStatement :: "+
								interfaceSummaryStatement.executeBatch()+
								"   interfaceSummaryStatement   "+interfaceFailureStatement.executeBatch()
						);
				connection.commit();
				i = 0;
			}
		}

		if(i>0) {
			System.out.println(
					"interfaceSummaryStatement :: "+
							interfaceSummaryStatement.executeBatch()+
							"   interfaceSummaryStatement   "+interfaceFailureStatement.executeBatch()
					);
			connection.commit();
		}

	}


	@SuppressWarnings("deprecation")
	private int getOidForData(LargeObjectManager lobj, byte[] bytes) throws Exception
	{
		LargeObject largeObject = null;
		InputStream inputStream = null;
		byte[] buffer = null;
		int oid = 0;
		try
		{
			oid = lobj.create(LargeObjectManager.READ | LargeObjectManager.WRITE);
			largeObject = lobj.open(oid, LargeObjectManager.WRITE);
			buffer = new byte[2048];
			inputStream = new ByteArrayInputStream(bytes);
			int stream;
			while ((stream = inputStream.read(buffer, 0, 2048)) > 0) 
			{
				largeObject.write(buffer, 0, stream);
			}

			inputStream.close();
			if (largeObject != null) 
			{
				largeObject.close();
			}
			return oid;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			try
			{
				if(inputStream != null)
					inputStream.close();
				if(largeObject != null)
					largeObject.close();
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}

			largeObject = null;
			inputStream = null;
			buffer = null;
		}
		return oid;
	}

}