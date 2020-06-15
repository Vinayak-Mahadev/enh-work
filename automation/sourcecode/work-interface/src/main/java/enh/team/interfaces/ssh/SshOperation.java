package enh.team.interfaces.ssh;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.finevm.enh.util.PropType;
import com.finevm.enh.util.Server;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @author VINAY
 *
 */
public class SshOperation 
{

	private String  username;
	private String  password;
	private String  hostName;
	private Integer portNum;
	private Integer timeOut;
	private String passPath;
	
	private JSch jsch;
	private Session session;
	private Channel channel;
	private ChannelSftp sftpChannel;
	private ChannelExec execChannel;

	private static  SshOperation sshOperation = null;


	public SshOperation(Server server) throws Exception{
		username = server.getUsername();
		password = server.getPassword();
		hostName = server.getHostName();
		portNum  = server.getPortNum();
		passPath = server.getPassPath();
		timeOut = server.getTimeOut();
		session = getSession();
	}

	public static SshOperation getSshOperation(PropType serverType)throws Exception{
		sshOperation = new SshOperation(new Server(serverType));
		return sshOperation;
	}
	public static SshOperation getSshOperation(String serverType)throws Exception{
		sshOperation = new SshOperation(new Server(serverType));
		return sshOperation;
	}


	public static SshOperation getSshOperation(Server server)throws Exception{
		sshOperation = new SshOperation(server);
		return sshOperation;
	}
	public Session getSession() throws Exception{

		try {
			if (session == null) {
				System.out.println("connecting..."+hostName);

				jsch = new JSch();
				if(passPath != null)
					jsch.addIdentity(passPath);
				session = jsch.getSession(username, hostName,portNum);
				session.setConfig("StrictHostKeyChecking", "no");
				session.setPassword(password);
				session.connect();
				System.out.println("connected session with :  "+hostName);
			}

		} catch (JSchException e) {
			e.printStackTrace();
			throw e;
		}
		return session;
	}

	public SshOperation connectExecChannel(){
		try {
			getSession();
			channel = session.openChannel("exec");
			channel.connect(timeOut);
			execChannel = (ChannelExec) channel;
			System.out.println("Connected to execChannel");


			channel.setInputStream(null);

			//channel.setOutputStream(System.out);

			//FileOutputStream fos=new FileOutputStream("/tmp/stderr");
			//((ChannelExec)channel).setErrStream(fos);
			((ChannelExec)channel).setErrStream(System.err);

			InputStream in=channel.getInputStream();

			channel.connect();

			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					System.out.print(new String(tmp, 0, i));
				}
				if(channel.isClosed()){
					if(in.available()>0) continue; 
					System.out.println("exit-status: "+channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}





	public SshOperation setCommand(String cmd){
		try {
			String command=JOptionPane.showInputDialog("Enter command", "set|grep SSH");

			channel = session.openChannel("exec");
			channel.connect();

			execChannel = (ChannelExec) channel;
			execChannel.setCommand(command);



			// X Forwarding
			// channel.setXForwarding(true);

			//channel.setInputStream(System.in);
			channel.setInputStream(null);

			//channel.setOutputStream(System.out);

			//FileOutputStream fos=new FileOutputStream("/tmp/stderr");
			//((ChannelExec)channel).setErrStream(fos);
			((ChannelExec)channel).setErrStream(System.err);

			InputStream in=channel.getInputStream();

			channel.connect();

			byte[] tmp=new byte[1024];
			while(true){
				while(in.available()>0){
					int i=in.read(tmp, 0, 1024);
					if(i<0)break;
					System.out.print(new String(tmp, 0, i));
				}
				if(channel.isClosed()){
					if(in.available()>0) continue; 
					System.out.println("exit-status: "+channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}		

			}
		}catch (Exception e) {
		}
		return this;
	}


	public SshOperation disconnect() {

		if (channel!=null) {

			channel.disconnect();
		}

		if (session!=null) {

			session.disconnect();
		}

		System.out.println("disconnected...");
		return this;
	}

	/**
	 * <pre>As of now unused... </pre>
	 * @param root
	 * @param createPath
	 * @return
	 * @throws SftpException
	 * @throws JSchException
	 */
	@SuppressWarnings("unused")
	private SshOperation createPath(String root, String createPath) throws SftpException, JSchException   {

		channel = session.openChannel("sftp");
		channel.connect();
		sftpChannel = (ChannelSftp) channel;

		sftpChannel.cd(root);

		/*String[] folders = path.split( "/" );

		for ( String folder : folders ) {
			if ( folder.length() != 0 )
			{
				try {
					sftpChannel.cd( "/"+folder );
					System.out.println("moved to : " + folder);
				}
				catch ( SftpException e ) {
					sftpChannel.mkdir( folder );
					System.out.println("mkdir : "+folder);
					sftpChannel.cd( folder );
					System.out.println("in catch moved to : " + folder);
				}
			}
		}
		 */
		String[] folders = createPath.split( "/" );
		sftpChannel.cd("/");
		for ( String folder : folders ) {
			if ( folder.length() != 0 )
			{
				try {
					sftpChannel.cd(folder.trim());

					System.out.println("moved to : " + folder+"   "+ sftpChannel.pwd());
				}
				catch ( SftpException e ) {
					sftpChannel.mkdir(folder.trim());
					System.out.println("mkdir : "+folder);
					sftpChannel.cd( folder.trim() );
					System.out.println("in catch moved to : " + folder);
				}
			}
		}

		/*try {
			sftpChannel.stat(path);
		} catch (SftpException e) {
			// dir does not exist.
			if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
				sftpChannel.mkdir(path);
			}
		}*/
		sftpChannel.disconnect();
		channel.disconnect();
		return this;
	}
	/**
	 * 
	 * @param remoteFileName
	 * @param remoteFileLocation
	 * @param localDir
	 * @return
	 * @author VINAY
	 * @throws Exception 
	 * @throws JSchException 
	 */
	private final ChannelSftp getsftpChannel() {
		try 
		{
			if(sftpChannel==null) {
				channel = getSession().openChannel("sftp");
				channel.connect();
				sftpChannel = (ChannelSftp) channel;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return sftpChannel;
	}
	public final SshOperation upload(final String remoteFileName,final String remoteFileLocation,final String localDir) {

		FileInputStream fis = null;
		File file = null;

		try {

			sftpChannel =  (ChannelSftp) getsftpChannel();
			sftpChannel.cd(remoteFileLocation);

			// Upload file
			file = new File(localDir+remoteFileName);
			fis  = new FileInputStream(file);
			sftpChannel.put(fis, file.getName());

			fis.close();
			System.out.println("File uploaded successfully ");
			setUploadStatus(true);
			//			channel.disconnect();
		} 
		catch(FileNotFoundException e) {
			excepMsg = e.getMessage();
			setUploadStatus(false);
			e.printStackTrace();
		}
		catch (Exception e) {
			setUploadStatus(false);
			excepMsg = e.getMessage();
			e.printStackTrace();
		}
		return this;
	}


	public String excepMsg = null;
	/**
	 * 
	 * @param remoteFileName
	 * @param remoteFileLocation
	 * @param localDir
	 * @return
	 * @author VINAY
	 */
	public final SshOperation download(final String remoteFileName,final String remoteFileLocation,final String localDir) {

		byte[] buffer = new byte[1024];
		BufferedInputStream bis;
		File file = null;
		File newFile = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {

			channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			// Change to output directory
			sftpChannel.cd(remoteFileLocation);

			file = new File(remoteFileName);
			bis  = new BufferedInputStream(sftpChannel.get(file.getName()));

			newFile = new File(localDir + "/" + file.getName());

			// Download file
			os = new FileOutputStream(newFile);
			bos = new BufferedOutputStream(os);
			int readCount;

			while ((readCount = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();
			System.out.println("File downloaded successfully ");

			channel.disconnect();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}



	/**
	 * <pre>Careful to use this one....</pre>
	 * @throws SftpException
	 * @author VINAY
	 */
	public final SshOperation deleteFile(final String localDir, final String fileName) {
		try {

			getsftpChannel().rm(localDir+fileName);
			System.out.println("File Deleted..");
			channel.disconnect();
		} 
		catch (SftpException e) {
			System.out.println("No such file name as  "+ fileName);
		} 
		return this;
	}


	/**
	 * <pre>Careful to use this one....</pre>
	 * @param path
	 * @throws SftpException
	 * @author VINAY
	 */
	@SuppressWarnings("unchecked")
	private  void recursiveFolderDelete(String path) throws SftpException {


		Vector<ChannelSftp.LsEntry> fileAndFolderList = sftpChannel.ls(path);  

		// Iterate objects in the list to get file/folder names.
		for (ChannelSftp.LsEntry item : fileAndFolderList) { 

			// If it is a file (not a directory).
			if (!item.getAttrs().isDir()) {

				sftpChannel.rm(path + "/" + item.getFilename()); // Remove file.

			} else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) { // If it is a subdir.

				try {

					// removing sub directory.
					sftpChannel.rmdir(path + "/" + item.getFilename()); 

				} catch (Exception e) { // If subdir is not empty and error occurs.

					// Do lsFolderRemove on this subdir to enter it and clear its contents.
					recursiveFolderDelete(path + "/" + item.getFilename()); 
				}
			}
		}

		sftpChannel.rmdir(path); // delete the parent directory after empty
		System.out.println("folder deleted :  "+path);
	}



	/**
	 * <pre>Careful to use this one....</pre>
	 * @throws SftpException
	 * @author VINAY
	 */
	public final SshOperation deleteFileDir(final String localDir, final String fileDir) {
		try {
			channel =  session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp)channel;
			sftpChannel.cd(localDir);
			recursiveFolderDelete(fileDir);

			channel.disconnect();


		} 
		catch (SftpException e) {
			System.out.println("No such fileDir as name : "+ fileDir);
		} catch (JSchException e) {

			e.printStackTrace();
		}
		return this;
	}



	/**
	 * 
	 * @param remoteFileName
	 * @param remoteFileLocation
	 * @param localDir
	 * @return
	 * @author VINAY
	 */
	public final static boolean uploadLocally(final String remoteFileName,final String remoteFileLocation,final String localDir) throws Exception{

		System.out.println(localDir+remoteFileName + "   "+ remoteFileLocation);
		try 
		{
			Files.copy(new File(localDir+remoteFileName).toPath(), new File(remoteFileLocation+remoteFileName).toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally
		{

		}
		return true;
	}


	public final static void deleteFileLocally(final String deleteFileLoc,final String  deleteFileName) throws Exception
	{
		File file = null;
		try 
		{
			file = new File(deleteFileLoc+deleteFileName);

			if(file.isFile()) {
				file.delete();
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("File Already Deleted");
		}
		finally 
		{
			file = null;
		}
	}


	public final static void deleteFileDirLocally(final String deleteFileDirLocAddr,final String  deleteFileDirName) throws Exception
	{
		File fileDir = null;
		try 
		{
			fileDir = new File(deleteFileDirLocAddr+deleteFileDirName);

			if(fileDir.isDirectory()) {

				File[] listFiles = fileDir.listFiles();
				for(File infile : listFiles)
				{
					System.out.println("Deleting "+infile.getName());
					infile.delete();
				}
				fileDir.delete();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("FileDir Already Deleted");
		}
		finally 
		{
			fileDir = null;
		}
	}


	public boolean isUploadStatus() {
		return uploadStatus;
	}

	private void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}


	private boolean uploadStatus = false;

}
