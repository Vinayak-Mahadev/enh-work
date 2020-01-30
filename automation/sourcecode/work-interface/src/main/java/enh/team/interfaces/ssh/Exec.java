package enh.team.interfaces.ssh;
import com.finevm.enh.util.PropType;
import com.finevm.enh.util.Server;


public class Exec{

	public static void main(String[] arg)
	{
		new Exec().run();
	}


	private void run() 
	{
		Server server = new Server (PropType.Server_143_);
		System.out.println(server);
		final String scriptFileName = "ps -ef";
		String childScriptFileName = "ps -ef";
		final String customSearchBy = "";
		String childCustomSearchBy = null;
		String scriptPrintOp  = " | awk {'print $1\"|\"$2\"|\"$3\"|\"$5\"|\"$6\"|\"$7\"|\"$8\"|\"$9\"|\"$10\"|\"$11\"|\"$12\"|\"$13\"|\"$14\"|\"$15'}"; 

		
		// UID|PID|PPID|SZ|RSS|PSR|STIME|TTY|TIME|CMD||||
		// UID|PID|PPID|STIME|TTY|TIME|CMD|||||||
		//UID        PID  PPID  C STIME TTY          TIME CMD

		ShellExecuter shellExecuter = null;
		try
		{

			if(customSearchBy!=null && !customSearchBy.trim().isEmpty()) {
				childCustomSearchBy =  "grep "+customSearchBy;
			}
			else
				childCustomSearchBy = null;

			if(childCustomSearchBy==null)
				childScriptFileName = scriptFileName+scriptPrintOp;
			else
				childScriptFileName = scriptFileName+" | "+childCustomSearchBy+scriptPrintOp;

			System.out.println("script ::::   "+childScriptFileName);

			shellExecuter = new ShellExecuter(server);

			shellExecuter.run(childScriptFileName);


			System.out.println("\n\n<======================\n");

			for (String data : shellExecuter.getFeachData()) {
				for (String child : childScriptFileName.split(" ")) {
					if(!child.trim().isEmpty())
					{
						
					}
				}
				System.out.println(data);
				
			}

			//			for (String data : shellExecuter.execute("/opt/share/apache-tomee-plus-1.7.1/bin/startup.sh")) {
			//				System.out.println(data);
			//			}
			System.out.println("\n======================>\n");

		} 
		finally
		{
			if(shellExecuter!=null)
				shellExecuter.disconnect();

			shellExecuter = null;
			server  = null;
			childScriptFileName = null;
			scriptPrintOp = null;
		}
	}
}