package com.finevm.devtool.swingui.compoOpr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.finevm.devtool.common.DevToolConstants;
import com.finevm.devtool.mvn.DevToolAutoMvnDepen;
import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;

public class MavenDepenBtn extends JButton{

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(MavenDepenBtn.class);
	private static MavenDepenBtn launchAutoMvnBtn;

	private ButtonGroup radioBtnForAutoMvnConfGroup;
	private DevToolAutoMvnDepen autoMavenDepen;
	private JPanel contentPanel = DevtoolComponents._oprContentJPanel; ;

	public MavenDepenBtn() 
	{
		super(DevToolUtils._mvnDepen);
		try 
		{
			autoMavenDepen  = DevToolUtils._AutoMavenDepn;
			radioBtnForAutoMvnConfGroup = DevtoolComponents._radioBtnForAutoMvnConfGroup;

			logger.info("MavenDepenBtn created successfully..");
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage()+"  "+e);
			e.printStackTrace();
		}
		finally 
		{

		}
	}

	public static JButton getLaunchAutoMvnBtn() {
		if(launchAutoMvnBtn==null)
		{
			launchAutoMvnBtn =  new MavenDepenBtn();
			launchAutoMvnBtn.addEvent(launchAutoMvnBtn);
		}
		return  launchAutoMvnBtn;
	}

	private void addEvent(final MavenDepenBtn launchAutoMvnBtn) 
	{
		launchAutoMvnBtn.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JSONObject writeJson = null;
				JSONObject json = null;
				JSONObject proList = null;
				JSONObject proModule = null;
				JSONObject readyJson = new JSONObject();
				String mvncmd = null;
				String logMsg = null;
				String input = null;
				try 
				{
					DevtoolComponents.disableAllButton();
					json = autoMavenDepen.getMavenConfigJson();
					proList = autoMavenDepen.getMvnIds(json);
					logMsg = ("Enter your choice :: \n\n");
					for (int i = 1; i<= proList.keySet().size() ; i++  ) {
						logMsg = logMsg + i+" "+proList.getString(i+"_")+"\n";
					}

					input = radioBtnForAutoMvnConfGroup.getSelection().getActionCommand().toString();
					input = input.split("_")[0];
					proModule = (autoMavenDepen.getMvnProjectArtifacts(json, proList.getString(input+"_")));
					String temp = proList.getString(input+"_");
					//					System.out.println(jsonArray);
					logMsg = "Here _artifactIds \n\n";

					for (int i = 1; i<= proModule.keySet().size() ; i++  ) 
					{
						String key = proModule.getJSONObject(i+"_").getString(DevToolConstants._artifactId);
						logMsg = logMsg + i + " " + key + "\n";
					}

					input = JOptionPane.showInputDialog(contentPanel, logMsg, null);

					readyJson = (proModule.getJSONObject(input+"_"));
					
					writeJson = autoMavenDepen.getMvnProject(temp, readyJson.getString(DevToolConstants._artifactId));
					logger.info("temp :: key :: writeJson :: "+temp +" :: " + readyJson.getString(DevToolConstants._artifactId) + " :: "+ writeJson);
					logMsg = "Enter your choice :: \n\n";
					JSONObject options = new JSONObject();
					options.put("1", "EXCEL");
					for (int i = 1; i<= options.keySet().size() ; i++  ) 
					{
						logMsg = logMsg +i+" "+options.get(i+"")+"\n"; 
					}

					input = JOptionPane.showInputDialog(contentPanel, logMsg, null);
					mvncmd = options.getString(input+"");

					String path = "";
					if (mvncmd.equalsIgnoreCase("EXCEL")) 
						path = autoMavenDepen.initXlsxSheet(DevToolConstants._fineVmDir, readyJson.getString(DevToolConstants._artifactId), writeJson);
					
					TextAreaLogs.getTextAreaLogs().log("Success : "+ mvncmd +"\n " + path);
				} 
				catch (org.json.JSONException e) {
					TextAreaLogs.getTextAreaLogs().log("Plz select valid entry for " + DevToolUtils._mvnDepen);
				}
				catch (NullPointerException e) {
					TextAreaLogs.getTextAreaLogs().log("Plz select valid entry for " + DevToolUtils._mvnDepen);

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
				}
			}
		});

	}
}
