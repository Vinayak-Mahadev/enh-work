package enh.team.interfaces.selenium.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * 
 * @author Vinayak Mahadev
 *
 */
public final class Componentes 
{

	
	/**
	 * <pre> 
	 * <b>Access Selenium web driver</b>
	 * 
	 * Pass the enum WebDriverType to get new Selenium Driver
	 * Note : Currently it supports only chrome driver
	 * </pre>
	 * @param webDriverType
	 * @return
	 */
	public static final WebDriver getNewWebDriver(WebDriverType webDriverType) 
	{
		WebDriver _webDriver = null;

		try 
		{

			if(webDriverType == WebDriverType._CHROME_DRIVER)
				_webDriver = new ChromeDriver();
			else if(webDriverType == WebDriverType._FIREFOX_DRIVER)
				_webDriver = new ChromeDriver();			// TODO: need to implement code
			else if(webDriverType == WebDriverType._INTERNET_EXPLORER_DRIVER)
				_webDriver = new ChromeDriver();			// TODO: need to implement code
			else 
				_webDriver = null;

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return _webDriver;
	}

}
