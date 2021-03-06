package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class emailStepDefs {

	WebDriver driver;
	
//	  Scenario: User attaches an image file to an outgoing email
//    Given the user is creating an email
//    When the user adds an image file attachment
//    Then the email displays that the image file is attached

	
	//Will run before all tests
	//Opens up to 
@Before
public void setup() {
	//login to google account, in the account homepage (not the gmail homepage!)
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Robert\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://accounts.google.com");
	//Enter the username and hit next
	driver.findElement(By.id("identifierId")).sendKeys("ecse428assb@gmail.com");
	driver.findElement(By.id("identifierNext")).click();
	//Wait until the password box pops up, then enter password and hit next!
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
	driver.findElement(By.name("password")).sendKeys("testing123!");
	driver.findElement(By.id("passwordNext")).click();
}
	
@After
public void returnState() {
	//Have the system click the gmail button, returning you to your inbox
	//WebDriverWait wait = new WebDriverWait(driver, 10);
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"gb\"]/div[2]/div[1]/div[4]/div/a/img")));
	//driver.findElement(By.xpath("//*[@id=\\\"gb\\\"]/div[2]/div[1]/div[4]/div/a/img")).click();
	//And now quit the driver
	driver.quit();
}
	
	
	@Given("^the user is logged into Gmail$")
	public void the_user_is_creating_an_email() {
		//Wait until the page is clearly loaded, then go to the gmail homepage (your inbox)
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gb")));
		driver.get("https://mail.google.com/mail/#inbox");
	}
		
	@And("^that the user selects the compose button$")	
	public void that_the_user_selects_the_compose_buton() {	
		//Wait until the compose button is clickable (page fully loaded), and then click the compose button
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")));
		driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")).click();
		//USE THIS PATH, works way better! /html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]
		//*[@id=":4y"]/div/div
		//*[@id=\":41\"]/div/div
	}
	
	@When("^the user adds the \"([^\"]*)\" of the recipient$")
	public void the_user_adds_an_email_recipient(String email) {
		//Wait until the "to" button is clickable, eaning the empty email has fully loaded.
		//Then enter the dynamic email of the recipient.
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
		driver.findElement(By.name("to")).sendKeys(email + Keys.ENTER);
	}
	
	@And("^adds a message title$")
	public void add_a_message_title() {
		driver.findElement(By.name("subjectbox")).sendKeys("This is the title");	
	}
	
	@And("^attaches an \"([^\"]*)\" to the email$")
	public void attaches_an_image_to_the_email(String imagePath) {
		WebElement tempPic = driver.findElement(By.name("Filedata"));
		tempPic.sendKeys("C:\\Users\\Robert\\Pictures\\Camera Roll\\" + imagePath);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("dO")));
	}
	
	@And("^the user hits the send button$")
	public void the_user_hits_the_send_button() {
		//Click the send button
		//Still doesn't work....don't know why....!!!!
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.className("dO")));
		//Important note: I tried forever to click the button, but that just wasn't working. So now I just have the code hit control-enter, which in Gmail is a send macro.
		String keysPressed =  Keys.chord(Keys.CONTROL, Keys.RETURN);
		driver.findElement(By.name("subjectbox")).sendKeys(keysPressed) ;
		//driver.findElement(By.xpath("/html[1]/body[1]/div[26]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[4]/table[1]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[2]/td[1]/div[1]/div[1]/div[4]/table[1]/tbody[1]/tr[1]/td[1]/div[1]/div[2]")).click();
	}
	
	@Then("^the email with the image will be succesfully sent to the correct recipients$")
	public void the_email_with_the_image_will_be_successfully_sent_to_the_correct_recipient() {
		//Wait for the bottom left corner black label thing to pop-up with info
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("aT")));
		//Loop and keep checking if the element says sending, or if it changes!
		WebElement tempEl = driver.findElement(By.className("aT"));
		while(tempEl.getText().contains("Sending")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("aT")));
			tempEl = driver.findElement(By.className("aT"));
		}
		if(tempEl.getText().contains("Message sent")) {
			System.out.println("Email Sent Successfully!");
		}else {
			System.out.println("Email Sending Fails");
		}
	}
	
	@When("^the user adds the \"([^\"]*)\" of all recipients$")
	public void the_user_adds_the_emails_of_all_recipients(String bothEmails) {
		//In this case, both emails from each test case have been attached to the same string variable
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
		driver.findElement(By.name("to")).sendKeys(bothEmails + Keys.ENTER);
	}
	
	@When("^the user adds an invalid \"([^\"]*)\" as the recipient$")
	public void the_user_adds_an_invalid_email_as_the_recipient(String invalidEmail) {
		//In this case, the email being entered will be invalid. It must be entered the same way nonetheless.
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
		driver.findElement(By.name("to")).sendKeys(invalidEmail + Keys.ENTER);
	}
	
	@Then("^the email with the image will not be correctly sent$")
	public void the_email_with_the_image_will_not_be_correctly_sent() {
		//Due to inputting an invalid email, a pop-up will warn the user. We will click ok.
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("ok")));
		driver.findElement(By.name("ok")).click();
	}
	
	
/*
		driver.get("https://accounts.google.com");
		//driver.findElement(By.linkText("Sign in")).click();
		driver.findElement(By.id("identifierId")).sendKeys("ecse428assb@gmail.com");
		//ecse428assignmentb@hotmail.com
		driver.findElement(By.id("idSIButton9")).click();
		//driver.findElement(By.id("i0118")).sendKeys("testing123");
		//driver.findElement(By.id("idSIButton9")).submit();
		//Need to wait for the page to fully load!
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
		driver.findElement(By.id("i0118")).sendKeys("testing123");
		driver.findElement(By.id("idSIButton9")).submit();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id__5")));
		driver.findElement(By.id("id__5")).click();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Need to wait for the empty email to pop-up!
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/div/div/div/div[1]/div/div/input")));
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/div/div/div/div[1]/div/div/input")).sendKeys("testing123@hotmail.com");
		//By now, recipient has been entered
		
		//driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/div/div/div/div[1]/div/div/input")).sendKeys(Keys.RETURN);
		
		//Add a message body
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[2]/div")).sendKeys("Testing");
		
		//Add a title
		//driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[3]/div[2]/div/div[3]/div[1]/div/div/div/div[1]/div[1]/div[2]/div[2]/div/div/div")).sendKeys("This is the message title");
		//driver.findElement(By.id("subjectLine0")).click();
	
	
	
 */
	
	
	
	
	
}
