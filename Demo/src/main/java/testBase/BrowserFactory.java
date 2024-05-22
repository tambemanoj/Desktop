
package testBase;

//import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BrowserFactory {

	//create webdriver object for given browser
	public WebDriver createBrowserInstance(String browser) {

	//	WebDriver driver = null;
		RemoteWebDriver driver = null;

		if(browser.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver();


		}else if (browser.equalsIgnoreCase("firefox")) {

		WebDriverManager.firefoxdriver().setup();
		//	FirefoxOptions foptions = new FirefoxOptions();
		//	foptions.addArguments("-private");
			
			//driver = new RemoteWebDriver(new URL("http:192.168.225.219:4444/wd/hub"), DesiredCapabilities.firefox());		        

			
			//driver = new FirefoxDriver(foptions);

		} if (browser.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			InternetExplorerOptions iOptions = new InternetExplorerOptions();
			iOptions.addCommandSwitches("-private");

			driver = new InternetExplorerDriver(iOptions);
		}
		return driver;
	}


}
