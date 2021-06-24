package testScripts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pom.pom;

public class PracticeForm {

	WebDriver driver;
	String driverPath = ".\\src\\test\\java\\chromedriver.exe";
	pom pomMethodCaller;
    
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

   	@Test
	public void happyPath() {
   		pomMethodCaller = new pom(driver);
   		pomMethodCaller.verifyHeader();
   		pomMethodCaller.fillFirstName();
   		pomMethodCaller.fillLastName();
   		pomMethodCaller.fillEmail();
   		pomMethodCaller.selectGender("Male");
   		pomMethodCaller.fillMobile();
   		pomMethodCaller.fillBirthDate();
   		pomMethodCaller.fillSubject("Sports");
   		pomMethodCaller.fillCurrentAddress();
   		pomMethodCaller.fillStateCity();
   		assertEquals(pomMethodCaller.validatorForTextInput(), true);
   		pomMethodCaller.clickSubmit();
   		pomMethodCaller.clickClose();
	}
   	
   	@Test
	public void incompleteForm() {
   		pomMethodCaller = new pom(driver);
   		pomMethodCaller.verifyHeader();
	}
   	
   	@AfterTest
   	public void tearDown() {
   		driver.quit();
   	}
}
