package page_Factory;

import org.openqa.selenium.By;

import testBase.DriverFactory;
import testBase.TestBase;

public class home_Page extends TestBase {
	
	//Page Factory
	
	By folder=By.xpath("//aw-command[@class='ng-scope']/button[@id='Awp0ShowHomeFolder']");
	
	By favorites=By.xpath("//button[@id='Awp0GoFavorites']");
	
	By inbox=By.xpath("//button[@id='Awp0GoInboxWithoutBubble']");
	
	By quickAccess=By.xpath("//button[@id='cmdQuickAccess']");
	
	//Actions:
		public void clickFolder() {
			DriverFactory.getInstance().getDriver().findElement(folder).click();
	}

}
