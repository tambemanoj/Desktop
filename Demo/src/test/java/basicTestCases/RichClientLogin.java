package basicTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import testBase.DriverFactory;
import testBase.TestBase;


public class RichClientLogin extends TestBase {
	WebDriverWait wt;
	@Test
	public void RC_login() throws InterruptedException, IOException {
		wt = new WebDriverWait(DriverFactory.getInstance().getDriver(), Duration.ofSeconds(30));
		Thread.sleep(3000L);
		Runtime.getRuntime().exec("C:\\Users\\fbtcuser\\Desktop\\Automation\\AutoIt\\demoScript.exe");
		Thread.sleep(7000L);
		//wt.until(exc)
		System.out.println("Rich Client Login Test has passed");
	}

}
