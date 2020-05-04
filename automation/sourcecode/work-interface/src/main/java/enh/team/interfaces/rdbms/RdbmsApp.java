package enh.team.interfaces.rdbms;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.RDBMS;

public class RdbmsApp 
{
	public static void main(String[] args) throws Exception
	{
		new RDBMSOperation().getDatabaseMetaData(RDBMS.getDBConnection(PropType.RDBMS_144));
	}
}
