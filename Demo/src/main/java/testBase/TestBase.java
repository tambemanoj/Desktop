
package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import reusableComponent.ActionEngine;
import reusableComponent.PropertiesOperations;
import reusableComponent.createJiraIssue_TestExecution;

public class TestBase extends ActionEngine {
	BrowserFactory bf = new BrowserFactory();
	
	@BeforeSuite
	public void CreateJiraIssue() {
		String xrayClientID = PropertiesOperations.getPropertyValueByKey("xrayClientID");
		String xrayClientSecret = PropertiesOperations.getPropertyValueByKey("xrayClientSecret");
		String xrayHost = PropertiesOperations.getPropertyValueByKey("xrayHost");
		createJiraIssue_TestExecution jiraOps= new createJiraIssue_TestExecution();
	///////JIRA  creation part
			String automaticJIRAcreation = PropertiesOperations.getPropertyValueByKey("automatic_Issue_Creation_In_JIRA");
			if(automaticJIRAcreation.trim().equalsIgnoreCase("ON")) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
				Date date = new Date();
				String actualDate = format.format(date);
				String issueS = "Automation Basic Test Execution - "+actualDate;
				String issueD = "Automated Test Execution.";
				String issueNumber = null;
				try {
					issueNumber = jiraOps.createJiraIssue("AB", issueS, issueD);
					//@SuppressWarnings("static-access")
					//String auth = jiraOps.getXRAYAuthorization( xrayHost, xrayClientID, xrayClientSecret);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				/*
				 * try { jiraOps.addAttachmentToJiraIssue(issueNumber, screenshotPath); } catch
				 * (Exception e) { e.printStackTrace(); }
				 */
			}	}

	@BeforeMethod
	public void LaunchApplication() throws Exception {
		String browser = PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));

		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		DriverFactory.getInstance().getDriver().navigate().to(url);

	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}

	public String getScreenShots(String imagename) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) DriverFactory.getInstance().getDriver();
		File f = ts.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);

		String screenshotPath = System.getProperty("user.dir") + "/Reports/Screenshots/" + actualDate + ".jpeg";

		FileUtils.copyFile(f, new File(screenshotPath));
		return screenshotPath;
	}
}
