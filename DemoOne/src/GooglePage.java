import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
public class GooglePage {
	 WebDriver driver;
 
	@Test
		public void test() {
		
		    System.setProperty("webdriver.chrome.driver", "C:\\Users\\ManojTambe\\work\\chromedriver.exe");
		 driver = new ChromeDriver();
		    driver.get("https://google.com");
		    
		    driver.findElement(By.name("q")).sendKeys("javatpoint tutorials");  
		          
//		    driver.findElement(By.name("btnK")).click();

		    
		}
	
	public void test() {
		
	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\ManojTambe\\work\\chromedriver.exe");
	 driver = new ChromeDriver();
	    driver.get("https://google.com");
	    
	  

	    
	}
	
	}
