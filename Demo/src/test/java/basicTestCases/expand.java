package basicTestCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tc_awc.constants.PropertiesOperations;
import com.tc_awc.pageobjects.home_Page;
import com.tc_awc.performancetestcases.TC_login;
import com.tc_awc.utilities.DriverFactory;
import com.tc_awc.utilities.TestBase;

public class expand extends TestBase{
	public WebDriverWait wt;
	home_Page hp = new home_Page();
	String itemID;
	
	@Test(priority=2, dataProvider = "getDataSet2")
	public void ExpandAssembly(String ItemID) throws Throwable {
		wt = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30));
		TC_login login = new TC_login();
		login.login();
		wt.until(ExpectedConditions.titleContains("Teamcenter - Home"));
		hp.searchItemID(ItemID);	  // Time calculate
	}
	
	
	@DataProvider
	public Object[][] getDataSet2() {
		Object[][] data = new Object[1][1];
		data[0][0] = PropertiesOperations.getPropertyValueByKey("SearchItemID");
		return data;
	}
}
