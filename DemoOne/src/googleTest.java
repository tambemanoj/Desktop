import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class googleTest {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ManojTambe\\work\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://google.com");
		
		driver.manage().window().maximize();
		
		driver.navigate().refresh();
		
		String title = driver.getTitle();
		System.out.println(title);
//		write the four colors technology in search bar
	    driver.findElement(By.name("q")).sendKeys("seleniumbase.io/demo_page"); 
	    
//	    Click on the search botton. 
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]")).click();
        
//      Open the website
        driver.findElement(By.xpath("/html/body/div[7]/div/div[11]/div/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div/a/h3")).click();
	
		
	}
	
//	@Test(priority=0)
//	public void MyTextInput() throws InterruptedException {
////		driver=new ChromeDriver();
//		driver.findElement(By.id("myTextInput")).sendKeys("I am FCT user");
//		
//		Thread.sleep(3000);
//		
//		driver.findElement(By.id("myTextInput")).clear();
//		
//		driver.findElement(By.id("myTextInput")).sendKeys("Pune city");
//		
//		String a=driver.findElement(By.id("myTextInput")).getAttribute("value");
//		
//		System.out.println("This is your city: -" + a);
//		
//		Thread.sleep(2000);
		
//	}
//	
//	@Test(priority=1)
//	public void Textarea() throws InterruptedException{
//		
//		
//		driver.findElement(By.id("myTextarea")).sendKeys("This repository contains a particular set of the most actual software test automation scenarios including the most complex situations: nested frames, shadow DOM, keypresses, or some complicated document models.");
//		
//		Thread.sleep(2000);
//	}
//	
//	@Test(priority=2)
//	public void PreText() throws InterruptedException {
//				
//		driver.findElement(By.name("preText2")).sendKeys(" Is visible now");
//		
//		Thread.sleep(2000);
//	}
//		
//	
//	@Test(priority=3)
//	public void Button() throws InterruptedException {
//		
//		String b = driver.findElement(By.id("readOnlyText")).getAttribute("value");
//		System.out.println("Button color is: -" +b);
//		
//		Thread.sleep(2000);
//		
//		driver.findElement(By.id("myButton")).click();
//		
//		String c= driver.findElement(By.id("readOnlyText")).getAttribute("value");
//		System.out.println("Button color is: -" +c);
//		
//	}
	
	@Test(priority=4)
	public void DD() {
		Select select = new Select(driver.findElement(By.id("mySelect")));
		select.selectByValue("50%");
        System.out.println("Select value is: " + select.getFirstSelectedOption().getText());

		select.selectByVisibleText("Set to 75%");
        System.out.println("Select value is: " + select.getFirstSelectedOption().getText());
        
		select.selectByIndex(3);
        System.out.println("Select value is: " + select.getFirstSelectedOption().getText());

//		Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
		
	}
	
	
	@AfterMethod
	public void End() {
		driver.close();
		System.out.println("All Test cases completed");
	}

}
