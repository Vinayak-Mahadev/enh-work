package enh.team.interfaces.ssh;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.Server;


public class Exec{

	public static void main(String[] arg) throws IOException
	{

		//new Exec().run();

		String plink = "C:/Program Files/PuTTY/plink.exe";
		String host = "192.168.2.143";
		String username = "appuser"; 
		String password = "app@123";
		for (String string : getAllProcessDetailsForCurrentUser(plink, host, username, password, false)) {
			System.out.println(string);
		}
		System.out.println("\n\n\n\n");
		for (String string : getAllProcessDetailsForCurrentUser(plink, host, username, password, true)) {
			System.out.println(string);
		}
	}

	private static List<String> getAllProcessDetailsForCurrentUser(String plink,String host, String username, String password, boolean withPipeSymbol) throws IOException{
		List<String> list = new ArrayList<String>();
		Process proc = null;
		String commands =  null;
		Runtime rt = null;
		BufferedReader stdInput = null;
		String s = null;

		String ps_ef = "ps -ef";
		String ps_ef_cmd = "ps|-ef";
		String awk_print = "|awk|{print|$1";
		try {

			rt = Runtime.getRuntime();
			if(withPipeSymbol)
				commands = plink+" -ssh "+username+"@"+host+" -pw "+password+"  \""+ps_ef+" | awk {'print $1\\\"|\\\"$2\\\"|\\\"$3\\\"|\\\"$5\\\"|\\\"$6\\\"|\\\"$7\\\"|\\\"$8\\\"|\\\"$9\\\"|\\\"$10\\\"|\\\"$11\\\"|\\\"$12\\\"|\\\"$13\\\"|\\\"$14\\\"|\\\"$15'}\" ";
			else
				commands = plink+" -ssh "+username+"@"+host+" -pw "+password+"  \""+ps_ef+" \" ";

			proc = rt.exec(commands);

			stdInput = new BufferedReader(new 
					InputStreamReader(proc.getInputStream()));
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				if(s.contains("UID")||s.startsWith(username)) {
					if(s.contains(ps_ef)||s.contains(ps_ef_cmd) || s.contains(awk_print)) {

					}
					else {
						list.add(s);
					}
				}
			}
			s = list.get(0);
			list.remove(0);
			Collections.sort(list);
			list.add(0,s);
		} 
		catch (Exception e) {
			e.printStackTrace();
			list = null;
		}
		finally {
			if(proc!=null) {
				proc.destroy();
			}
			if (stdInput!=null) {
				stdInput.close();
			}
			proc = null;
			commands =  null;
			rt = null;
			stdInput = null;
			s = null;

		}
		return list;
	}

	private void run() 
	{
		Server server = new Server (PropType.Server_143);
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