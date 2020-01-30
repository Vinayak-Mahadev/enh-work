package enh.team.interfaces.dumptest.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.mongodb.DBCursor;

/**
 * <b>Purpose:</b><br>
 * 		Implementation of Cursor bean operations..<br>
 * <br>
 * 
 * <b>DesignReference:</b><br>
 * <br>
 * <br>
 * 
 * <b>CopyRights:</b><br>
 * 		Enhancesys Innovations 2019<br>
 * <br>
 * 
 * <b>RevisionHistory:</b>
 * 
 * <pre>
 * <b>
 * Sl No   Modified Date        Author</b>
 * ==============================================
 * 1        29-12-2019		   Suresh Upparu
 * 	-- Base Release 
 * </pre>
 * 
 * <br>
 */
public class CursorBean 
{
	private ResultSet pgCursor;
	private ResultSetMetaData metaData;
	private DBCursor mongoCursor;
	
	public ResultSet getPgCursor() 
	{
		return pgCursor;
	}
	
	public void setPgCursor(ResultSet pgCursor) 
	{
		this.pgCursor = pgCursor;
	}
	
	public ResultSetMetaData getMetaData() 
	{
		return metaData;
	}
	
	public void setMetaData(ResultSetMetaData metaData) 
	{
		this.metaData = metaData;
	}
	
	public DBCursor getMongoCursor() 
	{
		return mongoCursor;
	}
	
	public void setMongoCursor(DBCursor mongoCursor) 
	{
		this.mongoCursor = mongoCursor;
	}
}