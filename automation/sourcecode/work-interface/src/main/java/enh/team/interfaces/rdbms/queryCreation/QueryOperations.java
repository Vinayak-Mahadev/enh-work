package enh.team.interfaces.rdbms.queryCreation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.finevm.enh.util.IWorkConstants;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;


public class QueryOperations {




	void  createUpdatequeryWith1setAnd1WhereField(String updateQuery) throws IOException, SQLException {


		File setFile = new File(IWorkConstants.queryCreationSetPath); 
		File whereFile = new File(IWorkConstants.queryCreationWherePath); 

		File queryFile = new File(IWorkConstants.setWhereResultFile);

		BufferedReader setReader = new BufferedReader(new FileReader(setFile));
		BufferedReader whereReader = new BufferedReader(new FileReader(whereFile));

		BufferedWriter queryWriter = new BufferedWriter(new FileWriter(queryFile));

		String setField   = null;
		String whereField = null;

		String query = null;
		PreparedStatement preparedStatement = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST).prepareStatement(updateQuery);
		while ((whereField = whereReader.readLine()) != null){
			if(((setField = setReader.readLine()))!= null){

			}else {
				setField = "";
			}
			preparedStatement.setString(1, setField);
			query = (preparedStatement.toString()+whereField+";");
			queryWriter.write(query+"\n");
		}
		queryWriter.close();
		setReader.close();
		whereReader.close();
	}

	void  interfaceAttrInsertQuery(String insertQuery) throws IOException, SQLException {
		Connection con = RDBMS.getDBConnection(PropType.RDBMS_LOCALHOST);

		File file1 = new File(IWorkConstants.attribute_id_file); 
		File file2 = new File(IWorkConstants.name_v_file); 
		File file3 = new File(IWorkConstants.value_v_file); 

		File insertQueryFile = new File(IWorkConstants.queryResultInsertforInterfaceAttr);

		BufferedReader file1Reader = new BufferedReader(new FileReader(file1));
		BufferedReader file2Reader = new BufferedReader(new FileReader(file2));
		BufferedReader file3Reader = new BufferedReader(new FileReader(file3));

		BufferedWriter insertQueryWriter = new BufferedWriter(new FileWriter(insertQueryFile));

		String interfaceAttributeID   = null;
		String name = null;
		String value = null;

		String query = null;
		PreparedStatement preparedStatement = null;
		while (
				((interfaceAttributeID = file1Reader.readLine()) != null) 
				&& (((name = file2Reader.readLine()))!= null))

		{
			if(((value = file3Reader.readLine()))!= null) {

			}
			else {

			}

			query = null;

			query = insertQuery.replaceFirst("FIELD1", interfaceAttributeID.trim());
			query = query.replaceFirst("FIELD2", (interfaceAttributeID.trim().substring(0, 4)));
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, value);
			query = (preparedStatement.toString()+";");
			insertQueryWriter.write(query+"\n");


		}
		insertQueryWriter.close();
		file1Reader.close();
		file2Reader.close();
		file3Reader.close();
		con.close();
	}

}

