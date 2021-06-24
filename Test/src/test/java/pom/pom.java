package pom;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pom {

	WebDriver driver;
	
	public pom(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(className ="main-header")
    private WebElement mainHeader;

	@FindBy(id ="firstName")
    private WebElement firstName;
	
	@FindBy(id ="userName-label")
    private WebElement nameText;
	
	@FindBy(id ="lastName")
    private WebElement lastName;
	
	@FindBy(id ="userEmail-label")
    private WebElement emailText;
	
	@FindBy(id ="userEmail")
    private WebElement email;
	
	@FindBy(xpath="//div[@id='genterWrapper']/div[text()='Gender']")
	private WebElement genderText;

	@FindBy(id ="userNumber-label")
    private WebElement mobileText;
	
	@FindBy(id ="userNumber")
    private WebElement mobile;
	
	@FindBy(id ="dateOfBirth-label")
    private WebElement birthDateText;
	
	@FindBy(id ="dateOfBirthInput")
    private WebElement birthDate;
	
	@FindBy(xpath="//label[text()='Subjects']")
    private WebElement subjectText;
	
	@FindBy(xpath="//input[@id='subjectsInput']")
    private WebElement subject;
	
	@FindBy(xpath="//label[text()='Hobbies']")
	private WebElement hobbiesText;
	
	@FindBy(id ="currentAddress-label")
	private WebElement addressText;
	
	@FindBy(id ="currentAddress")
	private WebElement address;
	
	@FindBy(id ="stateCity-label")
	private WebElement stateCityText;
	
	@FindBy(xpath ="//div[text()='Select State']")
	private WebElement stateCityDropDown;
	
	@FindBy(id ="submit")
	private WebElement submit;
	
	public void verifyHeader() {
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(mainHeader));
		assertEquals("Practice Form", mainHeader.getText().trim());
	}
	
	public void fillFirstName() {
		assertEquals("Name", nameText.getText().trim());
		firstName.sendKeys(randomString(chars, randomNumber(1, 26)));
	}
	
	public void fillLastName() {
		lastName.sendKeys(randomString(chars, randomNumber(1, 26)));
	}
	
	public void fillEmail() {
		assertEquals("Email", emailText.getText().trim());
		email.sendKeys(randomString(chars, randomNumber(1, 26))  + "@gmail.com");
	}
	
	public void selectGender(String gender) {
		assertEquals("Gender", genderText.getText().trim());
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", genderSelector(gender));
	}
	
	private WebElement genderSelector (String gender) {
		WebElement genderSelector;
		genderSelector = driver.findElement(By.xpath("//input[@value='" + gender + "']"));
		return genderSelector;
	}	
	
	public void fillMobile() {
		assertEquals("Mobile(10 Digits)", mobileText.getText().trim());

		StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < 10; i++) {
	    	int randomVal = randomNumber(1, 10);
		    sb.append(randomVal);
		    System.out.println(sb);
	    }		
	    
		mobile.sendKeys(sb);
	}
	
	public void fillBirthDate() {
		assertEquals("Date of Birth", birthDateText.getText().trim());
		//If I delete the data page goes blank**
		birthDate.click();
		dateOfBirth().click();
	}
	
	private WebElement dateOfBirth () {
		WebElement dateOfBirth = driver.findElement(By.xpath("//div[@class='react-datepicker__week']/div[@aria-label='Choose Friday, July 2nd, 2021']"));
		return dateOfBirth;
	}	
	
	public void fillSubject(String hobbie) {
		assertEquals("Subjects", subjectText.getText().trim());
		subject.sendKeys(randomString(chars, randomNumber(1, 26)));
		assertEquals("Hobbies", hobbiesText.getText().trim());
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", hobbieSelector(hobbie));
	}
	
	private WebElement hobbieSelector (String hobbie) {
		WebElement hobbieOptions = driver.findElement(By.xpath("//label[text()='" + hobbie + "']"));
		return hobbieOptions;
	}
	
	public void fillCurrentAddress() {
		assertEquals("Current Address", addressText.getText().trim());
		address.sendKeys(randomString(chars, randomNumber(1, 26)));
	}
	
	public void fillStateCity() {
		assertEquals("State and City", stateCityText.getText().trim());
		stateCityDropDown.click();
		int randomVal = randomNumber(0, 3);
		WebElement state = getState(randomVal);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", state);
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(state)).click();
	}
	
	private WebElement getState (int value) {
		WebElement state = driver.findElement(By.xpath("//div[@id='react-select-3-option-" + value + "']"));
		return state;
	}
	
//	private WebElement getCity (int value) {
//		WebElement state = driver.findElement(By.xpath("//div[@class=' css-11unzgr']"));
//		return state;
//	}
	
	public void clickSubmit() {
		submit.click();
	}
	
	public void clickClose() {
		((JavascriptExecutor)driver).executeScript("arguments[0].click()", close());
	}
	
	private WebElement close () {
		WebElement close = driver.findElement(By.id("closeLargeModal"));
		return close;
	}
	
	public boolean validatorForTextInput() {
		Boolean inputValidator = false;
	
		if (firstName.getAttribute("value").length() > 0 
				&& lastName.getAttribute("value").length() > 0
				&& email.getAttribute("value").length() > 0 
				&& mobile.getAttribute("value").length() > 0 ) {
			inputValidator = true;
		}
		
		return inputValidator;
	}
	
	
	//*********RANDOM GENERATORS*********
	String chars = "abcdefghijklmnopqrstuvwxyz";
			
	private int randomNumber(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}
	
	private String randomString(String chars, int length) {
		  Random rand = new Random();
		  StringBuilder sb = new StringBuilder();
		  for (int i=0; i<length; i++) {
			sb.append(chars.charAt(rand.nextInt(chars.length())));
		  }
		  return sb.toString();
		}
}
