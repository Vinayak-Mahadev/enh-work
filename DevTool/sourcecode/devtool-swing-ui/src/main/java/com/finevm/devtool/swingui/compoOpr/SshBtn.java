package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;
import com.finevm.devtool.fileopr.ssh.DevToolSSH;
import com.finevm.devtool.mvn.DevToolAutoMaven;
import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;

public class SshBtn extends JButton{

	private final static Logger logger = Logger.getLogger(SshBtn.class);
	private static final long serialVersionUID = 1L;
	private DevToolAutoMaven _AutoMaven;
	private static SshBtn launchSshBtn = null;
	private JPanel contentPanel     =  DevtoolComponents._oprContentJPanel;
	private final DevToolSSH devToolSSH = new DevToolSSH();

	public SshBtn() {
		super(DevToolUtils._uploadBtn);
		_AutoMaven  = DevToolUtils._AutoMaven;
		logger.info("SshBtn inited successfully.. _AutoMaven :: "+_AutoMaven);
	}

	public static JButton getLaunchSshBtn() {
		if(launchSshBtn==null)
		{
			launchSshBtn =  new SshBtn();
			launchSshBtn.addEvent(launchSshBtn);
		}
		return  launchSshBtn;
	}

	private void addEvent(final SshBtn launchSshBtn) {
		launchSshBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DevtoolComponents.disableAllButton();

				String autoMVName = null;
				String uploadOption = null;
				String buildLoc = null;
				String serverName = null;
				String deleteFileLoc = null;
				String uploadFileDirLoc = "";
				String packageType = null;
				String projectName = null;
				List<String> sortList = null;
				List<String> packageAndArtifact = new ArrayList<String>();
				JSONArray autoMavenJson = null;
				JSONObject serverUploadList = null;
				JSONObject tomeeJson = null;
				JSONObject uploadServerJson = null;
				JSONObject chObject = null;
				JSONObject jarList = new JSONObject();
				JSONObject warList = new JSONObject();
				JSONObject earList = new JSONObject();


				File file = null;
				try 
				{
					logger.info("SshBtn operation started..");
					serverName = DevtoolComponents._radioBtnForServerGroup.getSelection().getActionCommand();
					autoMVName = DevtoolComponents._radioBtnForAutoMvnConfGroup.getSelection().getActionCommand().split("_")[1];
					uploadServerJson = DevToolUtils._serverList.getJSONObject(serverName);
					if(uploadServerJson.isNull(DevToolConstants._tomee)) 
					{
						TextAreaLogs.getTextAreaLogs().log("tomee not configured");	
						return;
					}
					tomeeJson = uploadServerJson.getJSONObject(DevToolConstants._tomee); 

					for (Object obj : _AutoMaven.getMavenConfigJson().getJSONArray(autoMVName))
					{
						JSONObject jsonObject = (JSONObject) obj;

						JSONObject tObject  = new JSONObject();

						buildLoc = jsonObject.getString(DevToolConstants._projectTargetLoc);

						packageType = jsonObject.getString(DevToolConstants._packaging);
						projectName = jsonObject.getString(DevToolConstants._artifactId);
						tObject.put(DevToolConstants._artifactId,projectName);
						tObject.put(DevToolConstants._AbsPathBuildPackage,jsonObject.getString(DevToolConstants._AbsPathBuildPackage));
						tObject.put(DevToolConstants._AbsPathBuildPackageFlag,jsonObject.getString(DevToolConstants._AbsPathBuildPackageFlag));
						tObject.put(DevToolConstants._buildFinalName,jsonObject.getString(DevToolConstants._buildFinalName));
						tObject.put(DevToolConstants._buildFinalNameFlag,jsonObject.getString(DevToolConstants._buildFinalNameFlag));
						tObject.put(DevToolConstants._projectAbsolutePath,jsonObject.getString(DevToolConstants._projectAbsolutePath));
						tObject.put(DevToolConstants._version,jsonObject.getString(DevToolConstants._version));
						tObject.put(DevToolConstants._projectLoc,jsonObject.getString(DevToolConstants._projectLoc));
						tObject.put(DevToolConstants._targetLoc,jsonObject.getString(DevToolConstants._projectLoc)+"target/");
						tObject.put(DevToolConstants._packaging, packageType);
						packageAndArtifact.add(packageType+"_"+projectName);
						tObject.put(DevToolConstants._targetFiles, new JSONArray());

						file = new File(buildLoc);
						if(file.isDirectory()) {
							for (File fileL : file.listFiles())
							{
								if(fileL.isFile()) {
									//logger.info(fileL.getAbsolutePath());
									//									tObject.put(Constants._targetFile, fileL.getAbsolutePath());

									if(fileL.getAbsolutePath().endsWith(".jar"))
									{

										tObject.getJSONArray(DevToolConstants._targetFiles).put(fileL.getAbsolutePath());
										jarList.put(projectName, tObject);

									}
									if(fileL.getAbsolutePath().endsWith(".war")) 
									{
										tObject.getJSONArray(DevToolConstants._targetFiles).put(fileL.getAbsolutePath());
										warList.put(projectName, tObject);
									}
									if(fileL.getAbsolutePath().endsWith(".ear"))
									{
										tObject.getJSONArray(DevToolConstants._targetFiles).put(fileL.getAbsolutePath());
										earList.put(projectName, tObject);
									}

								}
							}
						}

					}

					if(jarList.keySet().isEmpty()&&warList.keySet().isEmpty()&&earList.keySet().isEmpty()) {
						TextAreaLogs.getTextAreaLogs().log("Build files not found");	
						return;
					}

					uploadOption = "SELECT OPTION ... \n\n1. Ear\n2. War\n3. Jar\n";
					uploadOption = JOptionPane.showInputDialog(contentPanel, uploadOption, null);

					int key =Integer.parseInt(uploadOption);
					switch (key)
					{
					case 1:
						serverUploadList = earList;
						uploadFileDirLoc = tomeeJson.getString(DevToolConstants._earLoc);
						break;
					case 2:
						serverUploadList = warList;
						uploadFileDirLoc = tomeeJson.getString(DevToolConstants._warLoc);
						break;
					case 3:
						serverUploadList = jarList;
						uploadFileDirLoc = tomeeJson.getString(DevToolConstants._jarLoc);
						break;
					default:
						TextAreaLogs.getTextAreaLogs().log("Plz choose valid entry");
						return;
					}

					logger.info(uploadFileDirLoc);
					if(uploadFileDirLoc == null || uploadFileDirLoc.trim().isEmpty()) {
						TextAreaLogs.getTextAreaLogs().log("UploadFileDirLoc not found");	
						return;
					}

					uploadOption = "SELECT OPTION ... \n\n";
					int i = 0;
					sortList = new ArrayList<String>(serverUploadList.keySet());
					Collections.sort(sortList);
					if(sortList.isEmpty()) {
						TextAreaLogs.getTextAreaLogs().log("No files found... ");
						return;
					}
					for (String logs : sortList) 
					{
						i++;
						uploadOption = uploadOption+i+". " +logs+"\n";
					}

					uploadOption = uploadOption+"\n*** 0 for upload all";
					uploadOption = JOptionPane.showInputDialog(contentPanel, uploadOption, null);
					i = Integer.parseInt(uploadOption);
					autoMavenJson = new JSONArray();
					if(i==0)
						for (String string : serverUploadList.keySet()) 
							autoMavenJson.put(serverUploadList.get(string));
					else
						autoMavenJson.put(serverUploadList.get(sortList.get(i-1)));

					if(!uploadServerJson.getString(DevToolConstants._hostName).equalsIgnoreCase("localhost")) {


						devToolSSH.init(uploadServerJson);
					}

					String log = "Uploaded files..\n\n";
					i = 0;
					for (Object obj : autoMavenJson) 
					{
						chObject = (JSONObject) obj;
						for (Object fullpath : chObject.getJSONArray(DevToolConstants._targetFiles)) {
							file = new File(fullpath.toString());
							if(file.isFile()) {
								//uploadFileDirLoc
								deleteFileLoc = file.getName().substring(0,(file.getName().length()-4));

								if(uploadServerJson.getString(DevToolConstants._hostName).equalsIgnoreCase("localhost")) 
								{
									DevToolSSH.deleteFileLocally(deleteFileLoc, file.getName());
									DevToolSSH.deleteFileDirLocally(uploadFileDirLoc, deleteFileLoc);
									if(DevToolSSH.uploadLocally(file.getName(), uploadFileDirLoc, chObject.getString(DevToolConstants._targetLoc))) 
										log =log +(++i)+". "+ file.getName()+"\n";
								}
								else 
								{
									devToolSSH.deleteFile(uploadFileDirLoc, file.getName());
									devToolSSH.deleteFileDir(uploadFileDirLoc, deleteFileLoc);
									
									devToolSSH.init(uploadServerJson);
									if(devToolSSH.upload(file.getName(), uploadFileDirLoc, chObject.getString(DevToolConstants._targetLoc)).isUploadStatus())
										log =log +(++i)+". "+ file.getName()+"\n";
								}
							}
						}
					}
					TextAreaLogs.getTextAreaLogs().log(log);
					logger.info("LaunchSshBtn operation done..");
				} 
				catch (org.json.JSONException e)
				{
					TextAreaLogs.getTextAreaLogs().log("Not configured");
					e.printStackTrace();
					logger.error(e);
				}
				catch (NumberFormatException e) {
					TextAreaLogs.getTextAreaLogs().log("Plz choose valid entry");
					logger.error(2);
				}
				catch (NullPointerException e2) {
					TextAreaLogs.getTextAreaLogs().log("Select Server&Mvn ");
					logger.error(2);
				}
				catch (Exception e) 
				{
					TextAreaLogs.getTextAreaLogs().log(e.getMessage());
					logger.error(e);
					e.printStackTrace();
				}
				finally
				{
					if(devToolSSH!=null) {
						logger.info("disconnect() happens..");
						devToolSSH.disconnect();
					}
					autoMVName = null;
					uploadOption = null;
					buildLoc = null;
					serverName = null;
					deleteFileLoc = null;
					uploadFileDirLoc = "";
					packageType = null;
					projectName = null;
					sortList = null;
					packageAndArtifact = null;
					autoMavenJson = null;
					serverUploadList = null;
					tomeeJson = null;
					uploadServerJson = null;
					chObject = null;
					jarList = null;
					warList = null;
					earList = null;
					file = null;

					DevtoolComponents.enableAllButton();
				}
			}
		});
	}
}
