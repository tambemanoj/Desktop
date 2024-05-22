package page_Factory;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import reusableComponent.PropertiesOperations;
import testBase.DriverFactory;
import testBase.TestBase;

public class login_Page extends TestBase{

	//Page Factory
	By username=By.xpath("//div[@class='aw-session-signIn']/ul/li/input[@name='userName']");
	By password=By.xpath("//div[@class='aw-session-signIn']/ul/li[2]/input[@type='password']");
	By signIn=By.xpath("//div[@class='aw-session-signIn']/ul/li[3]/div/div[@class='aw-session-signInButton']");
	
	
	//Actions:
	
		public String validateloginPageTiltle() {
			return DriverFactory.getInstance().getDriver().getTitle();
		}
		
		public void login() throws Throwable {
			DriverFactory.getInstance().getDriver().findElement(username).sendKeys(PropertiesOperations.getPropertyValueByKey("username"));
			DriverFactory.getInstance().getDriver().findElement(password).sendKeys(PropertiesOperations.getPropertyValueByKey("password"));
			DriverFactory.getInstance().getDriver().findElement(signIn).click();	
			Thread.sleep(2000L);
			System.out.println("Window Title Name:"+DriverFactory.getInstance().getDriver().getTitle());
			//Check the window title for successful login.
			//Window title should be "Teamcenter - Home" after login
			Thread.sleep(6000L);
			//Assert.assertEquals(DriverFactory.getInstance().getDriver().getTitle(), "Teamcenter - Home");
			//assertEqualsString_custom("Teamcenter - Home", DriverFactory.getInstance().getDriver().getTitle(), "Window Title Name:");
		}
	
		public void login_Negative() throws Throwable {
			DriverFactory.getInstance().getDriver().findElement(username).sendKeys(PropertiesOperations.getPropertyValueByKey("username"));
			DriverFactory.getInstance().getDriver().findElement(password).sendKeys(PropertiesOperations.getPropertyValueByKey("password_N"));
			DriverFactory.getInstance().getDriver().findElement(signIn).click();	
			Thread.sleep(2000L);
			System.out.println("Window Title Name:"+DriverFactory.getInstance().getDriver().getTitle());
			//Check the window title for successful login.
			//Window title should be "Teamcenter" for login page
			Thread.sleep(3000L);
			Assert.assertEquals(DriverFactory.getInstance().getDriver().getTitle(), "Teamcenter");
			assertEqualsString_custom("Teamcenter", DriverFactory.getInstance().getDriver().getTitle(), "Window Title Name:");

		}
	
}
