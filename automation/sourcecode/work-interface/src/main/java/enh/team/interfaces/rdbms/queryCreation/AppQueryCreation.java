package enh.team.interfaces.rdbms.queryCreation;

import java.io.IOException;
import java.sql.SQLException;

public class AppQueryCreation 
{
	public static void main(String[] args) throws IOException, SQLException {

		QueryOperations queryCreation = new QueryOperations();


		//queryCreation.interfaceAttrInsertQuery(QueryTemplate.ORIGIN_VM_ORGANIZATION_PROFILE_ATTR_INSERT);
		//queryCreation.createUpdatequeryWith1setAnd1WhereField(QueryTemplate.interface_attr_update_1set_1where);
		queryCreation.interfaceAttrInsertQuery(QueryTemplate.interface_attr_insertQuery);

	}

}
