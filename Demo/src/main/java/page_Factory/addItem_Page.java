package page_Factory;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import reusableComponent.ActionEngine;
import testBase.DriverFactory;

public class addItem_Page extends ActionEngine {
	public WebDriverWait wt;
	   // Page Factory to create item
		By ARVR = By.xpath("//div[@class='aw-tcWidgets-modelTypeCell']/div[@title='ARVR Part']");
		By item_id = By.xpath("//textarea[contains(@class,'aw-widgets-propertyEditValue ng-pristine ng-untouched ng-valid ng-scope ng-isolate-scope ng-not-empty ng-valid-required ng-valid-maxlength ng-valid-aw-required')]");
		By item_revision = By.xpath("//input[@class='aw-widgets-propertyEditValue ng-pristine ng-untouched ng-valid ng-scope ng-isolate-scope ng-not-empty ng-valid-required ng-valid-maxlength ng-valid-aw-required']");
		By item_progressFlag = By.xpath("//div[contains(@class,'aw-layout-flexRowContainer aw-widget-iconParent ng-scope')]//input[@placeholder='Required']");
		By item_ProgressFlagList=By.xpath("//div[contains(@class,'aw-jswidgets-listboxDrop')]/ul[@class='aw-widgets-cellListWidget']/li");
		By item_name = By.xpath("//textarea[@class='aw-widgets-propertyEditValue ng-pristine ng-untouched ng-scope ng-isolate-scope ng-empty ng-invalid ng-invalid-required ng-valid-maxlength ng-invalid-aw-required']");
		//By item_description = By.xpath(null);
		By addButton=By.xpath("//button[@class='aw-base-blk-button ng-scope ng-isolate-scope aw-accent-highContrast']");
		By addItemFrameTitle=By.xpath("//div[contains(@class,'gwt-Label')]");
		By panelSectionTitle=By.xpath("//div[@class='aw-layout-panelSectionTitle']/a");
		By popup=By.xpath("//span[contains(@class,'noty_text')]");
		
		// Page Factory to create Folder
		By filter =By.xpath("//div[@class='aw-widgets-innerWidget']/input");
		By folders= By.xpath("//div[@class='aw-tcWidgets-modelTypeCell']/div[@title='Folder']");
		By folder_name=By.xpath("//input[@placeholder='Required']");
		By folder_description=By.xpath("//textarea[@class='aw-widgets-propertyEditValue ng-pristine ng-untouched ng-valid ng-scope ng-isolate-scope ng-empty ng-valid-required ng-valid-maxlength ng-valid-aw-required']");
		
		//Actions:
		
		//Get Frame Title"Add"
		public WebElement getaddFrameTitle() {
			return DriverFactory.getInstance().getDriver().findElement(addItemFrameTitle);
		}
		public List<WebElement> getitem_ProgressFlagList(){
			return DriverFactory.getInstance().getDriver().findElements(item_ProgressFlagList);
			
		}
		
		//Add item 
		public void addItem() throws Throwable {
			wt=new WebDriverWait(DriverFactory.getInstance().getDriver(),Duration.ofSeconds(30));
			Thread.sleep(2000L);
			wt.until(ExpectedConditions.visibilityOf(getaddFrameTitle()));
			Thread.sleep(2000L);
			DriverFactory.getInstance().getDriver().findElement(ARVR).click();
			Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(panelSectionTitle).isDisplayed(),"Title link is not displayed");
			Assert.assertEquals(DriverFactory.getInstance().getDriver().findElement(panelSectionTitle).getText(), "ARVR PART");
			assertEqualsString_custom( "ARVR PART", DriverFactory.getInstance().getDriver().findElement(panelSectionTitle).getText(), "Add Panel Section Title :");
			Thread.sleep(2000L);
			Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(item_id).isDisplayed(),"Item ID is not displayed");
			WebElement itemID=DriverFactory.getInstance().getDriver().findElement(item_id);
			System.out.println("Item ID : "+itemID.getAttribute("value"));
			WebElement itemRevision=DriverFactory.getInstance().getDriver().findElement(item_revision);
			//ngDriver.waitForAngularRequestsToFinish();
			System.out.println("Item Revision : "+itemRevision.getAttribute("value"));
			//--------------to get the progressflag options
			/*
			 * DriverFactory.getInstance().getDriver().findElement(item_progressFlag).click(
			 * ); Thread.sleep(1000L); List<WebElement> list=getitem_ProgressFlagList();
			 * System.out.println("List Size:"+list.size()); for(int i=0;i<list.size();i++)
			 * { System.out.println("dropdown option: "+list.get(i).getText());
			 * 
			 * }
			 */
			DriverFactory.getInstance().getDriver().findElement(item_name).sendKeys("Test-NewItem");
			wt.until(ExpectedConditions.elementToBeClickable(addButton));
			DriverFactory.getInstance().getDriver().findElement(addButton).click();
			wt.until(ExpectedConditions.visibilityOf(DriverFactory.getInstance().getDriver().findElement(popup)));
			WebElement popupMsg=DriverFactory.getInstance().getDriver().findElement(popup);
			String mesg=popupMsg.getText();
			System.out.println("PopUp Message: "+mesg);
			
			}
		
		//Add Item Revision
		public void addItemRevision() throws InterruptedException {
			wt=new WebDriverWait(DriverFactory.getInstance().getDriver(),Duration.ofSeconds(30));
			Thread.sleep(2000L);
			wt.until(ExpectedConditions.visibilityOf(getaddFrameTitle()));
			Thread.sleep(2000L);
			DriverFactory.getInstance().getDriver().findElement(ARVR).click();
			Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(panelSectionTitle).isDisplayed(),"Title link is not displayed");
			Thread.sleep(2000L);
			Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(item_id).isDisplayed(),"Item ID is not displayed");
			String itemID=DriverFactory.getInstance().getDriver().findElement(item_id).getText();
			System.out.println("Item ID : "+itemID);
			Thread.sleep(2000L);
			Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(item_revision).isDisplayed(),"Item revision is not displayed");
			String itemRevision=DriverFactory.getInstance().getDriver().findElement(item_revision).getText();
			System.out.println("Item Revision : "+itemRevision);
			//--------------to get the progressflag options
			/*
			 * DriverFactory.getInstance().getDriver().findElement(item_progressFlag).click(
			 * ); Thread.sleep(1000L); List<WebElement> list=getitem_ProgressFlagList();
			 * System.out.println("List Size:"+list.size()); for(int i=0;i<list.size();i++)
			 * { System.out.println("dropdown option: "+list.get(i).getText());
			 * 
			 * }
			 */
			DriverFactory.getInstance().getDriver().findElement(item_name).sendKeys("Test-ItemRevision");
			wt.until(ExpectedConditions.elementToBeClickable(addButton));
			DriverFactory.getInstance().getDriver().findElement(addButton).click();
			//DriverFactory.getInstance().getDriver().getWindowHandle();
			DriverFactory.getInstance().getDriver().findElement(By.xpath("//span[contains(@class,'noty_text')]")).getText();
			
		}
		
		
		//Add New Folder
		public void addFolder() throws Throwable {
			wt=new WebDriverWait(DriverFactory.getInstance().getDriver(),Duration.ofSeconds(30));
			Thread.sleep(2000L);
			wt.until(ExpectedConditions.visibilityOf(getaddFrameTitle()));
			DriverFactory.getInstance().getDriver().findElement(filter).sendKeys("Folder");
			Thread.sleep(2000L);
			DriverFactory.getInstance().getDriver().findElement(folders).click();
			Assert.assertTrue(DriverFactory.getInstance().getDriver().findElement(panelSectionTitle).isDisplayed(),"Title link is not displayed");
			Assert.assertEquals(DriverFactory.getInstance().getDriver().findElement(panelSectionTitle).getText(), "FOLDER");
			String foldername="TestFolder";
			DriverFactory.getInstance().getDriver().findElement(folder_name).sendKeys(foldername);
			DriverFactory.getInstance().getDriver().findElement(addButton).click();
			wt.until(ExpectedConditions.visibilityOf(DriverFactory.getInstance().getDriver().findElement(popup)));
			WebElement popupMsg=DriverFactory.getInstance().getDriver().findElement(popup);
			String mesg=popupMsg.getText();
			System.out.println("PopUp Message: "+mesg);
			Assert.assertEquals(mesg,"\"TestFolder\" was added.");
			assertEqualsString_custom("\"TestFolder\" was added.", mesg, "Pop-up message :");
			String[] msgSplit=mesg.split(" ",2);
			for (String a : msgSplit)
	            System.out.println(a);
			
			
		}
}
