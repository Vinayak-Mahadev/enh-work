package com.finevm.devtool.swingui.compobtns;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.finevm.devtool.swingui.util.DevtoolComponents;

public class DevToolMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(DevToolMainFrame.class);
	public DevToolMainFrame() {
		try 
		{
			setContentPane(DevtoolComponents._mainContentJPanel);
		} 
		catch (Exception e) 
		{
			logger.error(e);
			JOptionPane.showInputDialog(null,
					"Plz configure properly..\nyou don't know take someone help\n\n*vinay.nagaraj@enhancesys.com");
		}
	}
}
