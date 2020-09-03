package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.puttyopr.DevToolPutty;
import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;

public class ServerProcessBtn extends JButton
{
	private final static Logger logger = Logger.getLogger(ServerProcessBtn.class);
	private static final long serialVersionUID = 1L;
	private JSONObject  serverList  =  DevToolUtils._serverList;
	private JPanel contentPanel     =  DevtoolComponents._oprContentJPanel;
	private static ServerProcessBtn launchPlinkBtn = null;
	private static final DevToolPutty  puttyOpr = new DevToolPutty( DevToolUtils._puttyPath, DevToolUtils._plinkPath );

	public ServerProcessBtn() {
		super(DevToolUtils._serverProcessBtn);
		logger.info(this+" init done..");
	}

	public static JButton getLaunchPlinkBtn() {
		if(launchPlinkBtn==null)
		{
			launchPlinkBtn =  new ServerProcessBtn();
			launchPlinkBtn.addEvent(launchPlinkBtn);
		}
		return  launchPlinkBtn;
	}

	private void addEvent(final ServerProcessBtn launchPlinkBtn) {
		launchPlinkBtn.addActionListener(new ActionListener() {

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

					json  = launchPlinkBtn.serverList.getJSONObject(serveName);

					logger.info(json);
					if(json.getString("hostName").equalsIgnoreCase("localhost")) 
					{
						TextAreaLogs.getTextAreaLogs().log("Plz Select remote Server");

					}
					else 
					{
						String choice  = "";
						String option = "";
						String processName = "";
						String runOpr = "";
						puttyOpr.init(json);

						option = "SELECT OPTION ... \n\n1. Run Script\n2. See process\n3. Kill process \n";
						choice = JOptionPane.showInputDialog(contentPanel, option, null);

						if(choice == null) {
							logger.info("Select vaild Entry...");
							TextAreaLogs.getTextAreaLogs().log("Select vaild Entry...");
							return;
						}
						if(choice.trim().isEmpty()) {
							logger.info("Select vaild Entry...");
							TextAreaLogs.getTextAreaLogs().log("Select vaild Entry...");
							return;	
						}
						runOpr = choice.trim();
						TextAreaLogs.getTextAreaLogs().log("You selected option : "+runOpr);
						option = "Enter cmd..\n\n";
						choice = JOptionPane.showInputDialog(contentPanel, option, null);
						if(choice == null) {
							logger.info("Enter cmd..");
							return;
						}
						if(choice.trim().isEmpty()) {
							logger.info("Enter cmd..");
							return;	
						}
						processName = choice.trim();

						if(runOpr.equals("1")) 
						{
							TextAreaLogs.getTextAreaLogs().log("Please wait script is running..");

							TextAreaLogs.getTextAreaLogs().log(puttyOpr.plinkRunScriptProcess(processName));
							//TextAreaLogs.getTextAreaLogs().log("Disabled");
							return;
						}						

						else if(runOpr.equals("2")) 
						{
							TextAreaLogs.getTextAreaLogs().log("Please wait script is running..");
							TextAreaLogs.getTextAreaLogs().log(puttyOpr.plinkSeeProcess(processName));
							//TextAreaLogs.getTextAreaLogs().log("Script running process done..");
							//TextAreaLogs.getTextAreaLogs().log("Disabled");
							return;
						}
						else if(runOpr.equals("3")) 
						{
							TextAreaLogs.getTextAreaLogs().log("Please wait script is running..");
							puttyOpr.plinkKillProcess(processName);
							TextAreaLogs.getTextAreaLogs().log("Script running process done..");
							return;
						}
						else 
						{
							TextAreaLogs.getTextAreaLogs().log("Select vaild Entry...");
							logger.info("Select vaild Entry...");
							return;
						}



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
					TextAreaLogs.getTextAreaLogs().log("Error : "+e.getMessage());
					
				}
				finally
				{

					json  = null;
					try {
						puttyOpr.destory();
					} catch (Exception e) {
						logger.error(e);
					}
					DevtoolComponents.enableAllButton();
				}
			}
		});
	}
}
