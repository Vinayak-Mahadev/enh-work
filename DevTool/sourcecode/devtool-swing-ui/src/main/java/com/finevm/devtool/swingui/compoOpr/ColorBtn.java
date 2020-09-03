package com.finevm.devtool.swingui.compoOpr;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;

import org.apache.log4j.Logger;

import com.finevm.devtool.swingui.util.DevToolUtils;
import com.finevm.devtool.swingui.util.DevtoolComponents;

public class ColorBtn extends JButton{
	private final static Logger logger = Logger.getLogger(ColorBtn.class);
	private static final long serialVersionUID = 1L;
	private static ColorBtn launchNextColor = null;
	private  final Random rnd = new Random();


	public ColorBtn() {
		super(DevToolUtils._nextColor);
	}

	public static JButton getLaunchNextColor() {
		if(launchNextColor==null)
		{
			launchNextColor =  new ColorBtn();
			launchNextColor.addEvent(launchNextColor);
		}
		return  launchNextColor;
	}

	private void addEvent(final ColorBtn launchNextColor) {
		launchNextColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					logger.info("LaunchNextColor invoked..");
					int rgb     = 0;
					Color color = null;

					
					rgb = Color.HSBtoRGB(rnd.nextFloat(),2120f,0.5f);
					color = new Color(rgb);
					for (JComponent component : DevtoolComponents._btnJComponentList) 
					{
						component.setBackground(color);
					}

					rgb   = Color.HSBtoRGB(rnd.nextFloat(),2100f,0.5f);
					color = new Color(rgb);
					for (JComponent component : DevtoolComponents._serverRadiolist)
						component.setBackground(color);


					rgb   = Color.HSBtoRGB(rnd.nextFloat(),2130f,0.5f);
					color = new Color(rgb);
					for (JComponent component : DevtoolComponents._autoMvnconfCheckBoxlist)
						component.setBackground(color);



				} 
				catch (Exception e) 
				{
					logger.error(e);
					e.printStackTrace();
				}
			}

		});

	}
}
