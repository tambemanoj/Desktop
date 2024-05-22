package basicTestCases;
import page_Factory.addItem_Page;
import page_Factory.folder_Page;
import page_Factory.home_Page;
import testBase.DriverFactory;
import testBase.TestBase;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import app.getxray.xray.testng.annotations.XrayTest;
import testBase.DriverFactory;
import testBase.TestBase;
//@Listeners({ app.getxray.xray.testng.listeners.XrayListener.class })
public class AWC_CreateNewItem extends TestBase{
	
	public WebDriverWait wt;
	home_Page hp=new home_Page();
	folder_Page fp=new folder_Page();
	addItem_Page ap=new addItem_Page();
	@Test
	@XrayTest(key = "AB-9")
	public void createNewItem() throws Throwable {
		wt=new WebDriverWait(DriverFactory.getInstance().getDriver(),Duration.ofSeconds(30));
		AWC_Login login=new AWC_Login();
		login.login();
		wt.until(ExpectedConditions.titleContains("Teamcenter - Home"));
		hp.clickFolder();
		fp.clickNewAdd();
		ap.addItem();
		
	}
}
