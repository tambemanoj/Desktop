package basicTestCases;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.testng.Reporter;
import org.testng.reporters.XMLReporter;
import org.testng.ITestResult;
import app.getxray.xray.testng.annotations.XrayTest;
import page_Factory.login_Page;
import app.getxray.xray.testng.annotations.Requirement;
import testBase.TestBase;
//@Listeners({ app.getxray.xray.testng.listeners.XrayListener.class })
public class AWC_Login extends TestBase {
	//@JiraPolicy(logTestCaseStatus = true)
	
	@Test
	@XrayTest(key = "AB-7")
	public void login() throws Throwable {
		login_Page lp = new login_Page();
		lp.login();
		Thread.sleep(2000L);
		 
	}
	//@JiraPolicy(logTestCaseStatus = true)
	@Test
	@XrayTest(key = "AB-8")
    public void Incorrect_login() throws Throwable {
		login_Page lp = new login_Page();
	
		lp.login_Negative();
		Thread.sleep(2000L);

	}
}
