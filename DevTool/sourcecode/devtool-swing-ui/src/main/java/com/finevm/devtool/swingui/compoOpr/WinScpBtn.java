package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;
import com.finevm.devtool.winscpopr.DevToolWinScp;

public class WinScpBtn extends JButton
{
	private final static Logger logger = Logger.getLogger(WinScpBtn.class);
	private static final long serialVersionUID = 1L;
	private String      winscpPath   =  DevToolUtils._winscpPath;
	private String      winscpConType=  DevToolUtils._winscpConType;
	private JSONObject  serverList   =  DevToolUtils._serverList;
	private static WinScpBtn launchWinScpBtn = null;
	private static final DevToolWinScp winScp = new DevToolWinScp();
	
	public WinScpBtn() {
		super(DevToolUtils._winscpBtn);
		logger.info("Object created..");
	}

	public static JButton getLaunchWinScpBtn() {
		if(launchWinScpBtn==null)
		{
			launchWinScpBtn =  new WinScpBtn();
			launchWinScpBtn.addEvent(launchWinScpBtn);
		}
		return  launchWinScpBtn;
	}

	private void addEvent(final WinScpBtn launchWinScpBtn) {
		launchWinScpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DevtoolComponents.disableAllButton();
				JSONObject json  = null;
				String serveName = null;
				try 
				{
					logger.info("LaunchWinScpBtn operation inted..");
					if(DevtoolComponents._radioBtnForServerGroup.getSelection() != null)
					serveName = DevtoolComponents._radioBtnForServerGroup.getSelection().getActionCommand();

					if(serveName==null) 
					{
						TextAreaLogs.getTextAreaLogs().log("Select the Server");
						return;
					}

					json  = launchWinScpBtn.serverList.getJSONObject(serveName);

					logger.info(json);
					if(json.get("hostName") !=null && json.getString("hostName").equalsIgnoreCase("localhost")) 
					{
						TextAreaLogs.getTextAreaLogs().log("Plz Select remote Server");
					}
					else 
					{
						winScp.init(json,winscpPath,winscpConType);
						winScp.launch();
						TextAreaLogs.getTextAreaLogs().log("WinSCP launched with :: " + json.getString("hostName") +":"+ json.getString("username"));
					}
				} 
				catch (IOException e1)
				{
					//e1.printStackTrace();
					TextAreaLogs.getTextAreaLogs().log(e1.getMessage());
					logger.error(e1.getMessage(), e1);
				}
				catch (org.json.JSONException e)
				{
					//e.printStackTrace();
					logger.error(e.getMessage(), e);
					TextAreaLogs.getTextAreaLogs().log("Not configured  :: "+e.getMessage());
				}
				catch (NullPointerException e2) {
					logger.error(e2.getMessage(), e2);
					//e2.printStackTrace();
					TextAreaLogs.getTextAreaLogs().log("Select the Server");
				}
				catch (Exception e) 
				{
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}
				finally
				{
					serveName = null;
					json  = null;
					DevtoolComponents.enableAllButton();
				}
			}
		});

	}
}


