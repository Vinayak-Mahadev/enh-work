package enh.team.interfaces.selenium;

import org.openqa.selenium.WebDriver;

import enh.team.interfaces.selenium.util.Componentes;
import enh.team.interfaces.selenium.util.WebDriverType;

public class App 
{
	public static void main(String[] args)
	{
		WebDriver webDriver = null;
		App app= null;
		WebDriverType webDriverType = null;
		try
		{
			app = new App();
			webDriverType = WebDriverType._CHROME_DRIVER;
			webDriver = Componentes.getNewWebDriver(webDriverType);
			
			app.LaunchDriver(webDriver);
			Thread.sleep(1 * 1000 * 60);
			app.closeDriver(webDriver);
		}
		catch (Exception e)
		{

		} 
		finally 
		{
			if(webDriver != null)
				webDriver.close();

			webDriver = null;
		}	
	}


	void LaunchDriver(WebDriver webDriver) 
	{

	}
	
	void closeDriver(WebDriver webDriver) 
	{


	}
}
