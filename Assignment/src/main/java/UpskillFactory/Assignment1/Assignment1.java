package UpskillFactory.Assignment1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class Assignment1 {
	
	
	
	
		
		WebDriver driver;
		public String browser;

		/**
		 * This function will execute before each Test tag in testng.xml
		 * @param browser
		 * @throws Exception
		 */
		@Parameters({"browser"})
		
		@BeforeTest
		
		public void setup(String browser) throws Exception{
			//Check if parameter passed from TestNG is 'firefox'
			if(browser.equalsIgnoreCase("firefox")){
			//create firefox instance
				System.setProperty("webdriver.chrome.driver","./drivers/geckodriver");
				driver = new FirefoxDriver();
			}
			//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("chrome")){
				//set path to chromedriver.exe
				System.setProperty("webdriver.chrome.driver","./drivers/chromedriver");
				//create chrome instance
				driver = new ChromeDriver();
			}
			//Check if parameter passed as 'Edge'
//					else if(browser.equalsIgnoreCase("Edge")){
//						//set path to Edge.exe
//						System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
//						//create Edge instance
//						driver = new EdgeDriver();
//					}
			else{
				//If no browser passed throw exception
				throw new Exception("Browser is not correct");
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
		@Test
		
		public void runTestinDiffBrowser() throws Exception{
		
			//driver = new ChromeDriver();
			driver.findElement(By.id("email")).sendKeys("kumar.testleaf@gmail.com");
			driver.findElement(By.id("password")).sendKeys("leaf@12");
			driver.findElement(By.id("buttonLogin")).click();
			Actions builder  = new Actions(driver);
			WebElement mouseOver= driver.findElement(By.xpath("//button[text()=' Invoices']"));
			builder.moveToElement(mouseOver).perform();
			driver.findElement(By.xpath("//a[@href='/invoices/search']")).click();
			driver.findElement(By.id("vendorTaxID")).sendKeys("FR121212");
			driver.findElement(By.id("buttonSearch")).click();
			
			List<WebElement> table = driver.findElements(By.xpath("//table[@class='table']//tr"));
			 
			for (WebElement element : table) {
				if (element.getText().contains("IT Support"))
					System.out.println("Invoice Numbers  " +  element.getText().subSequence(0, 6));
			}
		
	}

}
