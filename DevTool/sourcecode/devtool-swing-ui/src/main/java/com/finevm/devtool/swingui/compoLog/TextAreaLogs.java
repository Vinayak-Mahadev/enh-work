package com.finevm.devtool.swingui.compoLog;

import java.awt.TextArea;

import org.apache.log4j.Logger;

import com.finevm.devtool.swingui.util.DevtoolComponents;

public class TextAreaLogs 
{
	private final static Logger logger = Logger.getLogger(TextAreaLogs.class);
	private static TextArea logArea = null;
	private static TextAreaLogs textAreaLogs = null;

	public TextAreaLogs() {

		logArea = new TextArea(3,20);
		logArea.setText("Log Here");
		logArea.setColumns(2);
		logArea.setEditable(false);
		logArea.setSize(3, 6);
		logger.info("TextAreaLogs obj created..");
	}

	public static TextArea getTextArea() {
		if(logArea==null && textAreaLogs == null)
			textAreaLogs = new TextAreaLogs();
		return logArea;
	}


	public static TextAreaLogs getTextAreaLogs() {
		if(logArea==null && textAreaLogs == null)
			textAreaLogs = new TextAreaLogs();
		return textAreaLogs;
	}

	

	public void log(String msg){
		logArea.setText(msg + "");
		DevtoolComponents._radioBtnForAutoMvnConfGroup.clearSelection();
		DevtoolComponents._radioBtnForServerGroup.clearSelection();
	}
}
