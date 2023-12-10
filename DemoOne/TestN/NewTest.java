package TestN;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver;
    @Test
    public void f() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ManojTambe\\work\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
//    open the google chrome page
		driver.get("https://google.com");
  }
}
