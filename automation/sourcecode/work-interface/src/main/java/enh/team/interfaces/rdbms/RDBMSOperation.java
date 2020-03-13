package enh.team.interfaces.rdbms;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RDBMSOperation {

	public void getDatabaseMetaData(Connection conn)
    {
        try {

            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null, null, "%", types);
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_SCHEM")+"."+rs.getString("TABLE_NAME"));
            }
        } 
            catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
