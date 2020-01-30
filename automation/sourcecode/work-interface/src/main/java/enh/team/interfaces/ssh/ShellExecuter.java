package enh.team.interfaces.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.finevm.enh.util.Server;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ShellExecuter {

	private List<String> feachData = new ArrayList<String>();
	private List<String> feachDataByUsername = new ArrayList<String>();
	private String[] tittleArry; 

	public void run(String scriptFileName) {

		String tittle = null;

		feachData = (List<String>)execute(scriptFileName);
		feachDataByUsername = new ArrayList<String>();

		if(feachData.get(0).startsWith("UID")) {
			tittle = feachData.get(0);
			if(tittle!=null) {

				feachData.remove(0);
				tittleArry = tittle.split("\\|");
			}
		}

		Collections.sort(feachData);

		for (String data : feachData) {
			data = data.trim();
			if(!data.isEmpty()) 
			{
				//System.out.println(data);
				if(data.startsWith(USERNAME))
				{
					feachDataByUsername.add(data);	
				}
			}
		}
		if(tittle!=null)
			feachData.add(0, tittle);

	}

	/**
	 * This method will execute the script file on the server.
	 * This takes file name to be executed as an argument
	 * The result will be returned in the form of the list
	 * @param scriptFileName
	 * @return
	 */

	public  List<String> execute(String scriptFileName){

		List<String> result = new ArrayList<String>();
		InputStream in = null;
		BufferedReader reader = null;
		String line = "";
		int exitStatus = 0;
		String cmd = null;
		try

		{

			channelExec = (ChannelExec)session.openChannel("exec");
			// Gets an InputStream for this channel. All data arriving in as messages from the remote side can be read from this stream.
			in = channelExec.getInputStream();
			// Set the command that you want to execute
			// In our case its the remote shell script
			//cmd = "sh "+scriptFileName;
			cmd = scriptFileName;
			channelExec.setCommand(cmd);

			// Execute the command

			channelExec.connect();
			// Read the output from the input stream we set above
			reader = new BufferedReader(new InputStreamReader(in));

			//Read each line from the buffered reader and add it to result list
			// You can also simple print the result here

			while ((line = reader.readLine()) != null)
			{
				result.add(line);
				//System.out.println(line);
			}
			//retrieve the exit status of the remote command corresponding to this channel
			exitStatus = channelExec.getExitStatus();

			if(exitStatus < 0)
			{
				System.out.println("Done, but exit status not set!");
			}
			else if(exitStatus > 0)
			{
				System.out.println("Done, but with error!");
			}
			else
			{
				System.out.println("Done!");
			}


			System.out.println("exitStatus ::: "+exitStatus);
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e);
		}
		finally 
		{
			try 
			{
				if(reader!=null)
					reader.close();
				if(in!=null)
					in.close();
				if(channelExec!=null)
					channelExec.disconnect();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			in = null;
			reader = null;
			line = null;
			cmd = null;
		}
		return result;
	}


	public void disconnect() {
		try 
		{
			//Safely disconnect channel and disconnect session. If not done then it may cause resource leak
			if(channelExec!=null)
				channelExec.disconnect();
			if(session!=null)
				session.disconnect();
			if(jsch!=null)
				jsch.removeAllIdentity();


		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			jsch = null;
			session = null;
			channelExec = null;
		}

	}
	private void connect() {
		try 
		{
			/**
			 * Create a new Jsch object
			 * This object will execute shell commands or scripts on server
			 */

			jsch = new JSch();
			/**
			 * Open a new session, with your username, host and port
			 * Set the password and call connect.
			 * session.connect() opens a new connection to remote SSH server.
			 * Once the connection is established, you can initiate a new channel.
			 * this channel is needed to connect to remotely execution program
			 */

			session = jsch.getSession(USERNAME, HOST, PORT);

			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(PASSWORD);
			session.connect();
			//create the excution channel over the session
			System.out.println("Session is connected");

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public ShellExecuter(final String username, final String password, final String host, final int port) {
		super();
		this.USERNAME = username;
		this.PASSWORD = password;
		this.HOST = host;
		if(port >=0 )
			this.PORT = port;
		else
			this.PORT = 22;
		connect();
	}

	public ShellExecuter(Server server) {
		this(server.getUsername(), server.getPassword(), server.getHostName(), server.getPortNum());
	}
	public ShellExecuter(final String username, final String password, final String host) {
		this(username, password, host, -1);

	}

	private  final String USERNAME;
	private  final String PASSWORD;
	private  final String HOST;
	private  final int PORT;
	private JSch jsch;
	private Session session = null;
	private ChannelExec channelExec = null;


	public List<String> getFeachData() {
		return feachData;
	}

	public List<String> getFeachDataByUsername() {
		return feachDataByUsername;
	}

	public String[] getTittleArry() {
		return tittleArry;
	}

}