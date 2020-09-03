package com.finevm.devtool.swingui.util;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import com.finevm.devtool.swingui.compoLog.TextAreaLogs;
import com.finevm.devtool.swingui.compoOpr.ColorBtn;
import com.finevm.devtool.swingui.compoOpr.GitBtn;
import com.finevm.devtool.swingui.compoOpr.MavenBtn;
import com.finevm.devtool.swingui.compoOpr.PuttyBtn;
import com.finevm.devtool.swingui.compoOpr.ServerProcessBtn;
import com.finevm.devtool.swingui.compoOpr.SshBtn;
import com.finevm.devtool.swingui.compoOpr.WinScpBtn;

public class DevtoolComponents 
{
	public final static ButtonGroup _radioBtnForAutoMvnConfGroup = new ButtonGroup();
	public final static ButtonGroup _radioBtnForServerGroup = new ButtonGroup();
	public final static List<JComponent> _jComponentList = new ArrayList<>();
	public final static List<JComponent> _btnJComponentList = new ArrayList<>();
	public final static List<JRadioButton> _serverRadiolist = new ArrayList<>();
	public final static List<JCheckBox> _autoMvnconfCheckBoxlist = new ArrayList<>();
	public final static JPanel _serOprJPanel = new JPanel();
	public final static JPanel _mvnOprJPanel = new JPanel();
	public final static JPanel _oprContentJPanel = new JPanel();;
	public final static JPanel _mainContentJPanel = new JPanel();;
	public final static JPanel _oprBtnJPanel    = new JPanel();

	static 
	{
		_mainContentJPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		_mainContentJPanel.setVisible(true);
		_mainContentJPanel.setLayout(new GridLayout(3, 3, 3, 3));
		//_mainContentJPanel.setLayout(new GridBagLayout());
		//_mainContentJPanel.setLayout(new GridLayout(2, 2, 2, 2));
		//_mainContentJPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		//_mainContentJPanel.setLayout(new GridLayout(2, 2, 2, 2));
		
		
		
		_serOprJPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		_serOprJPanel.setVisible(true);
		_serOprJPanel.setLayout(new GridLayout(3, 3, 3, 3));

		_mvnOprJPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		_mvnOprJPanel.setVisible(true);
		_mvnOprJPanel.setLayout(new GridLayout(3, 3, 3, 3));

		_oprBtnJPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		_oprBtnJPanel.setVisible(true);
		_oprBtnJPanel.setLayout(new GridLayout(3, 3, 3, 3));

		_oprContentJPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		_oprContentJPanel.setVisible(true);
		_oprContentJPanel.setLayout(new GridLayout(2, 2, 2, 2));

	

		_btnJComponentList.add(MavenBtn.getLaunchAutoMvnBtn());               
		_btnJComponentList.add(PuttyBtn.getLaunchPuttyBtn());                   
		_btnJComponentList.add(WinScpBtn.getLaunchWinScpBtn());                 
		_btnJComponentList.add(SshBtn.getLaunchSshBtn());                       
		_btnJComponentList.add(ServerProcessBtn.getLaunchPlinkBtn()); 
		_btnJComponentList.add(GitBtn.getLaunchGitBtn());                       
		_btnJComponentList.add(ColorBtn.getLaunchNextColor());                 

		for (JComponent jComponent : _btnJComponentList) {
			_oprBtnJPanel.add(jComponent);
		}

		_oprContentJPanel.add(_mvnOprJPanel);
		_oprContentJPanel.add(_serOprJPanel);

		_mainContentJPanel.add(_oprContentJPanel);
		_mainContentJPanel.add(_oprBtnJPanel);
		_mainContentJPanel.add(TextAreaLogs.getTextArea());

	}

	public static void disableAllButton() {
		for (JComponent jComponent : _jComponentList) {
			jComponent.setEnabled(false);
		}
	}

	public static void enableAllButton() {
		for (JComponent jComponent : _jComponentList) {
			jComponent.setEnabled(true);
		}
		_radioBtnForAutoMvnConfGroup.clearSelection();
		_radioBtnForServerGroup.clearSelection();
	}

	private static String OS = null;

	public static String getOsName()
	{
		if(OS == null) { OS = System.getProperty("os.name"); }
		return OS;
	}
	public static boolean isWindows()
	{
		return getOsName().toLowerCase().contains("windows");
	}

}
