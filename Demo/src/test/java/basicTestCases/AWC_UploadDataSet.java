package basicTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import app.getxray.xray.testng.annotations.XrayTest;
import page_Factory.addItem_Page;
import page_Factory.folder_Page;
import page_Factory.home_Page;
import page_Factory.item_Page;
import testBase.DriverFactory;
import testBase.TestBase;

public class AWC_UploadDataSet extends TestBase {
	
	public WebDriverWait wt;
	home_Page hp=new home_Page();
	folder_Page fp=new folder_Page();
	addItem_Page ap=new addItem_Page();
	@Test
	@XrayTest(key = "AB-57")
	public void uploadDataSet() throws Throwable {
		wt=new WebDriverWait(DriverFactory.getInstance().getDriver(),Duration.ofSeconds(50));
		AWC_Login login=new AWC_Login();
		login.login();
		wt.until(ExpectedConditions.titleContains("Teamcenter - Home"));
		hp.clickFolder();
		Thread.sleep(2000L);
		fp.selectFolder();
		item_Page ip=new item_Page();
		ip.uploadDataset();
	}
}
