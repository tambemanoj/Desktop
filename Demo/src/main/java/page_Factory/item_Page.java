package page_Factory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import reusableComponent.ActionEngine;
import reusableComponent.PropertiesOperations;
import testBase.DriverFactory;

public class item_Page extends ActionEngine {
	private static String downloadPath = "C:\\Users\\fbtcuser\\Downloads";
	public WebDriverWait wt;
	By itemLabel = By.xpath("//div[@class='aw-layout-locationTitle aw-layout-flexRow ng-binding ng-scope']");
	By content = By.xpath("//a[normalize-space()='Content']");
	By whereUsed = By.xpath("//a[normalize-space()='Where Used']");
	By attachments = By.xpath("//a[normalize-space()='Attachments']");
	By history = By.xpath("//a[normalize-space()='History']");
	By particicpants = By.xpath("//a[normalize-space()='Participants']");
	By attachment_addto = By.xpath("//aw-walker-objectset[@titlekey='tc_xrt_Files']//button[@id='Awp0ShowAddObject']");
	By attachment_addto_frameLabel = By.xpath("//div[@class='gwt-Label ng-binding']");
	By attachment_addto_chooseFileBtn = By.xpath("//input[@ng-model='file.fmsFile']");
	By attachment_addto_Name = By
			.xpath("//div[contains(@class,'aw-widgets-innerWidget')]//input[@placeholder='Required']");
	By attachment_addto_Relation = By.xpath("//aw-listbox[@prop='data.creationRelation']//input[@type='text']");
	By attachment_addto_Rel_dropdownList = By.xpath(
			"//div[contains(@class,'aw-jswidgets-listboxDrop aw-jswidgets-drop aw-base-scrollPanel aw-jswidgets-page ng-scope')]//ul[contains(@class,'aw-widgets-cellListWidget')]/li");
	By attachment_addto_addBtn = By
			.xpath("//button[contains(@visible-when,'commandPanelForm.$valid&&conditions.isNewTabAddButtonVisible')]");
	By popup = By.xpath("//span[contains(@class,'noty_text')]");
	By attachment_datasetList = By.xpath("//ul[@class='aw-widgets-cellListWidget aw-layout-flexColumnContainer']/li");
	// By attachment_downloadBtn = By.xpath("(//*[name()='svg'])[32]");
	By attachment_downloadBtn = By.cssSelector(
			"body.comfy.aw-commands-showIconLabel.nonTouch:nth-child(2) div.aw-layout-mainView.afx-app-tcaw.ng-scope:nth-child(1) aw-include.ng-scope.ng-isolate-scope.aw-layout-flexbox div.aw-layout-include.aw-layout-flexbox.ng-scope div.awRoot.aw-flex-column.ng-scope.ng-isolate-scope.afx-fill div.aw-flex-row.ng-scope.ng-isolate-scope.afx-fill div.aw-flex-column.ng-scope.ng-isolate-scope.afx-fill:nth-child(2) div.aw-flex-row.ng-scope.ng-isolate-scope.afx-fill ui-view.aw-layout-flexColumn.ng-scope aw-showobject-page.ng-scope div.aw-layout-flexRowContainer.aw-layout-flexbox.aw-layout-locationPanel.locationPanel div.aw-layout-row.aw-layout-flexbox.afx-base-parentElement div.aw-layout-column.aw-layout-flexbox.ng-scope div.aw-layout-subLocation.afx-content-background.aw-layout-row.aw-layout-flexbox.afx-base-parentElement.ng-scope div.aw-layout-column.aw-layout-flexbox.ng-scope ng-transclude.aw-layout-flexColumn.ng-scope aw-xrt-sublocation.ng-scope.ng-isolate-scope aw-sublocation.ng-isolate-scope.aw-layout-defaultSublocation.aw-layout-flexColumn div.aw-layout-sublocationContainer div.aw-layout-workarea div.aw-layout-workareaMain aw-sublocation-body.ng-scope div.aw-layout-panelBody.aw-layout-sublocationContent.aw-base-scrollPanel aw-xrt-2.ng-isolate-scope.aw-jswidget-summaryPage aw-walker-view.ng-scope.ng-isolate-scope div.aw-layout-panelSection.ng-scope.ng-isolate-scope:nth-child(1) form.aw-layout-panelSectionContent.afx-base-parentElement.ng-pristine.ng-valid aw-walker-element.aw-xrt-element.ng-scope.ng-isolate-scope div.ng-scope div.ng-scope aw-walker-objectset.ng-isolate-scope div.aw-xrt-objectSetContent.aw-layout-flexColumn.aw-base-scrollPanel aw-list.ng-scope.ng-isolate-scope div.aw-widgets-cellListContainer.aw-widgets-droptable div.aw-widgets-cellListOffsetter div.aw-widgets-modelObjectList ul.aw-widgets-cellListWidget.aw-layout-flexColumnContainer li.aw-widgets-cellListItem.aw-widgets-cellTop.ng-scope.aw-widgets-cellListItemSelected:nth-child(1) div.aw-widgets-cellListItemContainer.aw-widgets-droptable div.aw-widgets-cellListCellCommands.aw-widgets-cellInteraction.ng-scope.aw-list-compiled-element div.aw-widgets-cellCommandsContainer aw-cell-command-bar.ng-scope.ng-isolate-scope aw-command.ng-scope.aw-widgets-cellListCellCommandHover.aw-widgets-cellListCellCommandSelected:nth-child(1) button.aw-commands-commandIconButton.aw-commandId-Awp0ShowObjectCellDataset div.aw-commandIcon ng-bind-html.ng-binding div.aw-commands-svg > svg:nth-child(1)");

	// Actions:
	public void uploadDataset() throws Throwable {
		wt = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30));
		Thread.sleep(2000L);
		Assert.assertEquals(DriverFactory.getInstance().getDriver().findElement(itemLabel).getText(), "P0002615-01-ww");
		DriverFactory.getInstance().getDriver().findElement(attachments).click();
		Thread.sleep(2000L);
		DriverFactory.getInstance().getDriver().findElement(attachment_addto).click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(attachment_addto_frameLabel));
		WebElement m = DriverFactory.getInstance().getDriver().findElement(attachment_addto_chooseFileBtn);
		// Action class to move and click element
		Actions a = new Actions(DriverFactory.getInstance().getDriver());
		a.moveToElement(m).click().build().perform();
		Thread.sleep(3000L);
		Runtime.getRuntime().exec("src\\main\\java\\testData\\testFile.exe");
		Thread.sleep(3000L);
		String fileName = DriverFactory.getInstance().getDriver().findElement(attachment_addto_Name)
				.getAttribute("value");
		System.out.println("fileName: " + fileName);
		DriverFactory.getInstance().getDriver().findElement(attachment_addto_Relation).click();
		List<WebElement> list = DriverFactory.getInstance().getDriver().findElements(attachment_addto_Rel_dropdownList);
		System.out.println("List Size: " + list.size());

		for (WebElement we : list) {
			try {
				Thread.sleep(2000L);
				if (we.getText().contains("Specifications")) {

					we.click();
					break;
				}
			} catch (StaleElementReferenceException e) {
				System.out.println("Please handle the element if it get change: reinitialize");
			}

		}
		DriverFactory.getInstance().getDriver().findElement(attachment_addto_addBtn).click();
		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(popup));
		Thread.sleep(1000L);
		String mesg = DriverFactory.getInstance().getDriver().findElement(popup).getText();
		System.out.println("PopUp Message: " + mesg);
		Assert.assertEquals(mesg, '"' + fileName + '"' + " was added.");
		assertEqualsString_custom(mesg, '"' + fileName + '"' + " was added.", "Respective File has added");
		String[] msgSplit = mesg.split(" ", 2);
		for (String msg : msgSplit) {
			System.out.println(msg);

		}

	}

	public void downloadDataset() throws Throwable {
		String fileName = null;
		wt = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30));
		Thread.sleep(2000L);
		Assert.assertEquals(DriverFactory.getInstance().getDriver().findElement(itemLabel).getText(),
				PropertiesOperations.getPropertyValueByKey("FileName"));
		assertEqualsString_custom(PropertiesOperations.getPropertyValueByKey("FileName"), DriverFactory.getInstance().getDriver().findElement(itemLabel).getText(), "File name :");
		DriverFactory.getInstance().getDriver().findElement(attachments).click();
		Thread.sleep(2000L);
		List<WebElement> list = DriverFactory.getInstance().getDriver().findElements(attachment_datasetList);
		System.out.println("List Size: " + list.size());
		for (WebElement we : list) {

			Thread.sleep(2000L);
			if (we.getText().contains("test")) {
				fileName = we.getText();
				we.click();
				break;
			}
		}

		String[] labelSplit = fileName.split("T");
		System.out.println(labelSplit[0]);
		for (int i = 0; i < labelSplit.length; i++) {
			System.out.println("FileName : " + i + "=" + labelSplit[i]);
			fileName = labelSplit[0];
			System.out.println("FileName : " + fileName.trim() + ".txt");
		}
		// System.out.println("FileName : "+fileName+".txt");
		WebElement ele = DriverFactory.getInstance().getDriver().findElement(attachment_downloadBtn);
		// Action class to move and click element
		Actions act = new Actions(DriverFactory.getInstance().getDriver());
		act.moveToElement(ele).click().build().perform();
		Thread.sleep(2000L);
		Assert.assertTrue(isFileDownloaded(downloadPath, fileName.trim() + ".txt"),
				"Failed to download Expected document");
	}

	// to check the download file
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			// System.out.println("File Name"+dir_contents[i].getName());
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}
}
