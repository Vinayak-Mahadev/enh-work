package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.puttyopr.DevToolPutty;
import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;

public class PuttyBtn extends JButton
{
	private final static Logger logger = Logger.getLogger(PuttyBtn.class);
	private static final long serialVersionUID = 1L;
	private JSONObject  serverList  =  DevToolUtils._serverList;
	private static PuttyBtn launchPuttyBtn = null;
	private static final DevToolPutty  puttyOpr = new DevToolPutty( DevToolUtils._puttyPath, DevToolUtils._puttyPath );
	public PuttyBtn() {
		super(DevToolUtils._puttyBtn);

		List<String> sortServerList = new ArrayList<>(serverList.keySet());
		Collections.sort(sortServerList);
		int i= 0;
		for (Object obj : sortServerList) {
			i++;
			JRadioButton radioBtnForServer = new JRadioButton(i+". "+obj.toString());
			radioBtnForServer.setActionCommand(obj.toString());
			radioBtnForServer.setName(obj.toString());
			//added to JComponent list
			DevtoolComponents._jComponentList.add(radioBtnForServer);
			DevtoolComponents._serOprJPanel.add(radioBtnForServer);
			DevtoolComponents._radioBtnForServerGroup.add(radioBtnForServer);
			DevtoolComponents._serverRadiolist.add(radioBtnForServer);
		}
		logger.info(this+" init done..");
	}

	public static JButton getLaunchPuttyBtn() {
		if(launchPuttyBtn==null)
		{
			launchPuttyBtn =  new PuttyBtn();
			launchPuttyBtn.addEvent(launchPuttyBtn);
		}
		return  launchPuttyBtn;
	}

	private void addEvent(final PuttyBtn launchPuttyBtn) {
		launchPuttyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DevtoolComponents.disableAllButton();

				JSONObject json  = null;
				try 
				{
					logger.info(this+" action invoked...");
					String serveName = DevtoolComponents._radioBtnForServerGroup.getSelection().getActionCommand();

					if(serveName==null) 
					{
						TextAreaLogs.getTextAreaLogs().log("Select the Server");
						logger.info(this+" Select the Server...");
						return;
					}

					json  = launchPuttyBtn.serverList.getJSONObject(serveName);

					logger.info(json);
					if(json.getString("hostName").equalsIgnoreCase("localhost")) 
					{
						File file = null;
						try {

							if(DevtoolComponents.isWindows()) 
							{
								file = createBatFile(
										DevToolUtils._autoMvnBatFileOutputLoc+serveName, 
										json.getJSONObject("tomee").getString("loc").trim()
										);

								runBatFile(file.getAbsolutePath());

							}
							else 
							{
								// for other os
							}


						} 
						finally 
						{
							file = null;
						}
					}
					else 
					{
						puttyOpr.init(json);
						puttyOpr.launch();	
					}
				} 
				catch (IOException e1)
				{
					e1.printStackTrace();
					TextAreaLogs.getTextAreaLogs().log(e1.getMessage());
					logger.error(e1);
				}
				catch (org.json.JSONException e)
				{
					e.printStackTrace();
					TextAreaLogs.getTextAreaLogs().log("Not configured  :: "+e.getMessage());
					logger.error(e);
				}
				catch (NullPointerException e2) {
					TextAreaLogs.getTextAreaLogs().log("Select the Server");
				}
				catch (Exception e) 
				{
					e.printStackTrace();
					logger.error(e);
				}
				finally
				{

					json  = null;
					DevtoolComponents.enableAllButton();
				}
			}
		});

	}


	private File createBatFile(String filepath, String cmds) 
	{
		File file = null;
		FileWriter writer = null;
		String finalCmd = "REM This is a comment!\n\n";
		try
		{
			file = new File(filepath+".bat");
			writer = new FileWriter(file);


			finalCmd = cmds.substring(0,2)+" && cd "+cmds;


			finalCmd = finalCmd+"\r\n" + 
					"\n\n\nREM -- This file created by Fine-VM software...\r\n" + 
					"REM For more details vinayakmahadev.nm@gmail.com\n\ncls";

			logger.info(finalCmd);



			writer.write(finalCmd);

		}
		catch (Exception e)
		{
			logger.error(e);
		}
		finally {
			if(writer!=null) {
				try {
					writer.close();
				}
				catch (IOException e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
			writer = null;
			finalCmd = null;

		}
		return file;
	}


	private Process runBatFile(String path) throws IOException{
		return Runtime.getRuntime().exec("cmd /c start /wait " +path);
	}
}
