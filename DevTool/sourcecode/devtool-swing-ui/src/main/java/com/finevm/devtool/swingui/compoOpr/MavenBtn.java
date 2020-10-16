package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;
import com.finevm.devtool.mvn.DevToolAutoMaven;
import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;

public class MavenBtn extends JButton{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(MavenBtn.class);
	private static MavenBtn launchAutoMvnBtn;

	private ButtonGroup radioBtnForAutoMvnConfGroup;
	private DevToolAutoMaven autoMaven;
	private JSONObject mvnCmdsConf = DevToolUtils._mvnCmdsConf;
	private JPanel contentPanel = DevtoolComponents._oprContentJPanel; ;

	public MavenBtn() {
		super(DevToolUtils._mvnBtn);
		JCheckBox checkBoxBtnForAutoMvnConf = null;
		try 
		{
			autoMaven  = DevToolUtils._AutoMaven;
			radioBtnForAutoMvnConfGroup = DevtoolComponents._radioBtnForAutoMvnConfGroup;

			JSONObject json    = autoMaven.getMavenConfigJson();
			JSONObject proList = autoMaven.getMvnIds(json);

			for (int i = 1; i<= proList.keySet().size() ; i++  ) {
				checkBoxBtnForAutoMvnConf = new JCheckBox(i+". "+proList.getString(i+"_"));
				checkBoxBtnForAutoMvnConf.setActionCommand(i+"_"+proList.getString(i+"_"));
				DevtoolComponents._mvnOprJPanel.add(checkBoxBtnForAutoMvnConf);
				DevtoolComponents._jComponentList.add(checkBoxBtnForAutoMvnConf);
				DevtoolComponents._radioBtnForAutoMvnConfGroup.add(checkBoxBtnForAutoMvnConf);
				DevtoolComponents._autoMvnconfCheckBoxlist.add(checkBoxBtnForAutoMvnConf);
			}

			logger.info("MavenBtn created successfully..");
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage()+"  "+e);
			e.printStackTrace();
		}
		finally 
		{
			checkBoxBtnForAutoMvnConf = null;
		}
	}

	public static JButton getLaunchAutoMvnBtn() {
		if(launchAutoMvnBtn==null)
		{
			launchAutoMvnBtn =  new MavenBtn();
			launchAutoMvnBtn.addEvent(launchAutoMvnBtn);
		}
		return  launchAutoMvnBtn;
	}
	
	private void addEvent(final MavenBtn launchAutoMvnBtn) {
		launchAutoMvnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JSONObject json = null;
				JSONObject proList = null;
				JSONObject proModule = null;
				JSONObject readyJson = new JSONObject();
				String mvncmd = null;
				String batFile = null;
				String logMsg = null;
				String input = null;
				Process process = null;
				try 
				{
					DevtoolComponents.disableAllButton();
					json = autoMaven.getMavenConfigJson();
					proList = autoMaven.getMvnIds(json);
					logMsg = ("Enter your choice :: \n\n");
					for (int i = 1; i<= proList.keySet().size() ; i++  ) {
						logMsg = logMsg + i+" "+proList.getString(i+"_")+"\n";
					}

					input = radioBtnForAutoMvnConfGroup.getSelection().getActionCommand().toString();
					input = input.split("_")[0];
					proModule = (autoMaven.getMvnProjectArtifacts(json, proList.getString(input+"_")));
					logMsg = "Here _artifactIds \n\n";
					for (int i = 1; i<= proModule.keySet().size() ; i++  ) {
						logMsg = logMsg + i+" "+proModule.getJSONObject(i+"_").getString(DevToolConstants._artifactId)+"\n";
					}
					input = JOptionPane.showInputDialog(contentPanel,
							logMsg, null);

					logger.info(readyJson);

					readyJson = (proModule.getJSONObject(input+"_"));

					logMsg = "Enter your choice :: \n\n";

					for (int i = 1; i<= mvnCmdsConf.keySet().size() ; i++  ) {
						logMsg = logMsg +i+" "+mvnCmdsConf.get(i+"")+"\n"; 
					}

					input = JOptionPane.showInputDialog(contentPanel,
							logMsg, null);
					mvncmd = mvnCmdsConf.getString(input+"");

					batFile  = autoMaven.prepareRunFile(readyJson, mvncmd);
					if (batFile!=null && batFile.endsWith(".bat")) {
						//process = autoMavenForNew.runBatFile(batFile);
						process = Runtime.getRuntime().exec(autoMaven.runBatFileCmd(batFile));;
						synchronized (process) {
							process.waitFor();
						}
						TextAreaLogs.getTextAreaLogs().log("Success : "+ mvncmd +"\n " + readyJson);
					}
				} 
				catch (org.json.JSONException e) {
					TextAreaLogs.getTextAreaLogs().log("Plz select mvn");
				}
				catch (NullPointerException e) {
					TextAreaLogs.getTextAreaLogs().log("Plz select mvn");

				}
				catch (Exception e) 
				{
					TextAreaLogs.getTextAreaLogs().log("Plz try Again");
					e.printStackTrace();
					logger.error(e.getMessage()+"  "+e);
				}
				finally 
				{
					DevtoolComponents.enableAllButton();
					json = null;
					proList = null;
					proModule = null;
					readyJson = null;
					mvncmd = null;
					batFile = null;
					process = null;
				}
			}
		});

	}
}
