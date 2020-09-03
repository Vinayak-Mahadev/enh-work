package com.finevm.devtool.swingui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.finevm.devtool.common.DevToolConstants;
import com.finevm.devtool.swingui.compobtns.DevToolMainFrame;
import com.finevm.devtool.swingui.util.DevToolUtils;

public class DevToolRunner {  
	private final static Logger logger = Logger.getLogger(DevToolRunner.class);
	public static void main(String[] args) throws Exception {
		try 
		{

			if(DevToolConstants.load() != null)
			{
				logger.info("DevToolRunner started..");
				EventQueue.invokeLater(new Runnable() { 
					@Override
					public void run() {
						try 
						{   
							final String tittle = DevToolUtils._swingTitle;
							final DevToolMainFrame  frame = new DevToolMainFrame();
							frame.setTitle(tittle);
							frame.setSize(700, 450);
							frame.setVisible(true);
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame.setLocationRelativeTo(null);
						}
						catch (Exception e) {
							logger.error(e);
							e.printStackTrace();
						}
					}
				});
			}
		} 
		catch (Exception e)
		{
			logger.error(e);
			JOptionPane.showInputDialog(null, "Plz configure properly..\nyou don't know take someone help\n\n*vinay.nagaraj@enhancesys.com");
			e.printStackTrace();
		}
	}	
}