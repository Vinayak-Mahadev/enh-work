package com.finevm.devtool.fileopr.ssh;

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

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.beans.DevToolServer;
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
public class DevToolSSH 
{
	private final static Logger logger = Logger.getLogger(DevToolSSH.class);
	private String  username;
	private String  passPath;
	private String  password;
	private String  hostName;
	private Integer portNum;
	private Integer timeOut;
	private JSch jsch;
	private Session session;
	private Channel channel;
	private ChannelSftp sftpChannel;
	private ChannelExec execChannel;
	private boolean uploadStatus = false;
	public String excepMsg = null;

	public DevToolSSH(DevToolServer server) throws Exception{
		init(server);
	}

	public DevToolSSH(){
		logger.info("DevToolSSH Obj created...");
	}
	public void init(DevToolServer server) throws Exception{
		disconnect();
		username = server.getUsername();
		password = server.getPassword();
		hostName = server.getHostName();
		passPath = server.getPassPath();
		portNum  = server.getPortNum();
		timeOut = server.getTimeOut();
		session = getSession();
		logger.info("DevToolSSH  init...");
	}
	public void init(JSONObject server) {

		try 
		{
			init(new DevToolServer(server));

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public Session getSession() throws Exception{

		try {
			logger.info("connecting..."+hostName);

			jsch = new JSch();
			session = jsch.getSession(username, hostName,portNum);
			if(passPath != null && !passPath.trim().isEmpty()) {
				jsch.addIdentity(passPath);
			}
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			session.connect();
			logger.info("connected session with :  "+hostName);


		} catch (JSchException e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
		return session;
	}

	public DevToolSSH connectExecChannel() throws Exception{
		try {
			getSession();
			channel = session.openChannel("exec");
			channel.connect(timeOut);
			execChannel = (ChannelExec) channel;
			logger.info("Connected to execChannel");


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
					logger.info("exit-status: "+channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}
			}


		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
		return this;
	}

	public DevToolSSH setCommand(String cmd){
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
					logger.info("exit-status: "+channel.getExitStatus());
					break;
				}
				try{Thread.sleep(1000);}catch(Exception ee){}		

			}
		}catch (Exception e) {
			logger.error(e);
		}
		return this;
	}

	public DevToolSSH disconnect() {

		if (channel!=null) {

			channel.disconnect();
		}

		if (session!=null) {

			session.disconnect();
		}

		username = null;
		password = null;
		hostName = null;
		portNum = null;
		timeOut = null;
		jsch = null;
		session = null;
		channel = null;
		sftpChannel = null;
		execChannel = null;

		excepMsg = null;

		logger.info("disconnected...");
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
	private DevToolSSH createPath(String root, String createPath) throws SftpException, JSchException   {

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
					logger.info("moved to : " + folder);
				}
				catch ( SftpException e ) {
					sftpChannel.mkdir( folder );
					logger.info("mkdir : "+folder);
					sftpChannel.cd( folder );
					logger.info("in catch moved to : " + folder);
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

					logger.info("moved to : " + folder+"   "+ sftpChannel.pwd());
				}
				catch ( SftpException e ) {
					sftpChannel.mkdir(folder.trim());
					logger.info("mkdir : "+folder);
					sftpChannel.cd( folder.trim() );
					logger.info("in catch moved to : " + folder);
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
			logger.error(e);
		}

		return sftpChannel;
	}
	public final DevToolSSH upload(final String remoteFileName,final String remoteFileLocation,final String localDir) throws Exception{

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
			logger.info("File uploaded successfully ");
			setUploadStatus(true);
			//			channel.disconnect();
		} 
		catch(FileNotFoundException e) {
			excepMsg = e.getMessage();
			setUploadStatus(false);
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) {
			setUploadStatus(false);
			excepMsg = e.getMessage();
			e.printStackTrace();
			logger.error(e);
			throw e;
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
	public final DevToolSSH download(final String remoteFileName,final String remoteFileLocation,final String localDir) {

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
			logger.info("File downloaded successfully ");

			channel.disconnect();
		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return this;
	}



	/**
	 * <pre>Careful to use this one....</pre>
	 * @throws SftpException
	 * @author VINAY
	 */
	public final DevToolSSH deleteFile(final String localDir, final String fileName) {
		try {

			getsftpChannel().rm(localDir+fileName);
			logger.info("File Deleted..");
			channel.disconnect();
		} 
		catch (SftpException e) {
			logger.error(e);
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
					logger.error(e);
				}
			}
		}

		sftpChannel.rmdir(path); // delete the parent directory after empty
		logger.info("folder deleted :  "+path);
	}



	/**
	 * <pre>Careful to use this one....</pre>
	 * @throws SftpException
	 * @author VINAY
	 */
	public final DevToolSSH deleteFileDir(final String localDir, final String fileDir) {
		try {
			channel =  session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp)channel;
			sftpChannel.cd(localDir);
			recursiveFolderDelete(fileDir);

			channel.disconnect();


		} 
		catch (SftpException e) {
			logger.info("No such fileDir as name : "+ fileDir);
		} catch (JSchException e) {

			e.printStackTrace();
			logger.error(e);
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

		logger.info(localDir+remoteFileName + "   "+ remoteFileLocation);
		try 
		{
			Files.copy(new File(localDir+remoteFileName).toPath(), new File(remoteFileLocation+remoteFileName).toPath(), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
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
			logger.info("File Already Deleted");
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
					logger.info("Deleting "+infile.getName());
					infile.delete();
				}
				fileDir.delete();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			logger.info("FileDir Already Deleted");
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


}
