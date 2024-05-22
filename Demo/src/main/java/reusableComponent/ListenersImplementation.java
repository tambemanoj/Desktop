package reusableComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;


import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import testBase.DriverFactory;
import testBase.ExtentFactory;
import testBase.ExtentReportNG;
import testBase.TestBase;

public class ListenersImplementation extends TestBase implements ITestListener{
	
	static ExtentReports report;
		   ExtentTest test;
		   
	public void onTestStart(ITestResult result) {
		//before each test case
		test = report.createTest(result.getMethod().getMethodName());
		ExtentFactory.getInstance().setExtent(test);
	}

	public void onTestSuccess(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+ " is Passed.");
		
		
		//add screenshot for Passed test.
		//File src = ((TakesScreenshot)DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
		//SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		//Date date = new Date();
		//String actualDate = format.format(date);
		
		//String screenshotPath = System.getProperty("user.dir")+
		//		"/Reports/Screenshots/"+actualDate+".jpeg";
		//File dest = new File(screenshotPath);
		
		//try {
		//	FileUtils.copyFile(src, dest);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		//ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath, "Test case PASS screenshot");
		ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailure(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "+result.getMethod().getMethodName()+ " is Failed.");
		ExtentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());
		
		
		try {
			String imagepath=getScreenShots(result.getMethod().getMethodName()+".jpeg");
			File f= new File(imagepath);
			FileInputStream fis= new FileInputStream(f);
			byte[] bytes= new byte[(int)f.length()];
			fis.read(bytes);
			//String base64img= new String(Base64.getEncoder().encode(bytes),StandardCharsets.UTF_8);
			
			  ExtentFactory.getInstance().getExtent().addScreenCaptureFromPath(
			  getScreenShots(imagepath), "Test case failure screenshot")
			  .fail(MediaEntityBuilder.createScreenCaptureFromPath(imagepath).build());
			 
			//ExtentFactory.getInstance().getExtent().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64img).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExtentFactory.getInstance().removeExtentObject();
		
	}

	
	public void onTestSkipped(ITestResult result) {
		ExtentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "+result.getMethod().getMethodName()+ " is skipped.");
		ExtentFactory.getInstance().removeExtentObject();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		
		try {
			 getIssueID();
			 report = ExtentReportNG.setupExtentReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
		public static String getIssueID() {
			createJiraIssue_TestExecution jiraIssue = new createJiraIssue_TestExecution();
			String issueNumber = null;
			String automaticJIRAcreation = PropertiesOperations.getPropertyValueByKey("automatic_Issue_Creation_In_JIRA");
			if (automaticJIRAcreation.trim().equalsIgnoreCase("ON")) {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
				Date date = new Date();
				String actualDate = format.format(date);
				String issueS = "Automation Test Execution - " + actualDate;
				String issueD = "Automated Test Execution.";

				try {
					issueNumber = jiraIssue.createJiraIssue("AB", issueS, issueD);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			return issueNumber;
		}
	

	public void onFinish(ITestContext context) {
		//close extent
		report.flush();
	}

}
