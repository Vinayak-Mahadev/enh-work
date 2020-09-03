package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.finevm.devtool.fileopr.git.DevToolGitLog;
import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;
import com.finevm.devtool.fileopr.git.DevToolGit;

public class GitBtn extends JButton{
	private final static Logger logger = Logger.getLogger(GitBtn.class);
	private static final long serialVersionUID = 1L;
	private static GitBtn launchGitBtn = null;
	private JPanel contentPanel = DevtoolComponents._oprContentJPanel;
	private JSONObject mvnConf = DevToolUtils._mvnConf;
	public GitBtn() {
		super(DevToolUtils._launchGit);
		logger.info("LaunchGitBtn init successfully..");
	}

	public static JButton getLaunchGitBtn() {
		if(launchGitBtn==null)
		{
			launchGitBtn =  new GitBtn();
			launchGitBtn.addEvent(launchGitBtn);
		}
		return  launchGitBtn;
	}

	private void addEvent(final GitBtn launchGitBtn) {
		launchGitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String keyinput = null;
				try 
				{

					keyinput = DevtoolComponents._radioBtnForAutoMvnConfGroup.getSelection().getActionCommand();
					keyinput = keyinput.split("_")[1];
					logger.info(keyinput);


				} 
				catch (NullPointerException e) {
					TextAreaLogs.getTextAreaLogs().log("select items");
					return;
				}
				DevtoolComponents.disableAllButton();
				DevToolGit operations = 	null;


				String logMsg = "Press Key .. \n\n";
				logMsg = logMsg 
						+"1.  PULL ...\n";

				logMsg = logMsg 
						+"2.  SHOW RECENT COMMIT LOGS ...\n";

				//				logMsg = logMsg 
				//						+"3.  Git Pull ...\n";



				int    key    = 0;
				try 
				{
					String option = JOptionPane.showInputDialog(contentPanel, logMsg, null);
					try 
					{
						key = Integer.parseInt(option);
					}
					catch (NumberFormatException e)
					{
						key = 0;
					}

					try 
					{
						logger.info(mvnConf.getJSONObject(keyinput));
						//logger.info(keyinput+" "+autoConf.getJson().getJSONObject(keyinput));
						JSONObject json = mvnConf.getJSONObject(keyinput);//json.getJSONObject("git");
						operations      = 	new DevToolGit(json.getJSONObject("git"));
					} 
					catch(NullPointerException ne) {
						TextAreaLogs.getTextAreaLogs().log("Select Git Option");
					}
					catch (JSONException json_e) {
						TextAreaLogs.getTextAreaLogs().log("Git not Configured....");
					}

					switch (key) {
					case 1:
					{
						if(operations.gitPull())
							TextAreaLogs.getTextAreaLogs().log("Git Operation done..");

						break;
					}
					case 2:
					{
						int logLimits = 0;
						String logs = "Here logs...\n\n"
								+ "ID | NAME | EMAIL | TIME |\n MSG\n\n";
						int i =0;

						try 
						{
							String logLim = JOptionPane.showInputDialog(contentPanel,
									"Enter Log Limit", null);

							logLimits = Integer.parseInt(logLim);

						} catch (NumberFormatException e) {
							logLimits = 5;

						}



						for(DevToolGitLog glog: operations.showLogs(logLimits+1))
						{
							logs = logs + (++i)+". " + 
									glog.get_id() +" | "+
									glog.getAuthName() +" | "+
									glog.getAuthEmail() +" | "+
									glog.getCommitTime() +" | \n"+
									glog.getCommitMsg() +"  "
									+ "\n\n";
						}

						JOptionPane.showInputDialog(contentPanel,logs,null);

						break;
					}


					default:
					{
						TextAreaLogs.getTextAreaLogs().log("Select valid option");
						break;
					}
					}

				} 
				catch (Exception e) 
				{
					TextAreaLogs.getTextAreaLogs().log(e.getMessage());
					logger.error(e.getMessage()+"  "+e);
				}
				finally 
				{
					operations = 	null;
					DevtoolComponents.enableAllButton();	
				}
			}

		});

	}
}
