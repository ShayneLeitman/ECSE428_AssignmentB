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

import cucumber.api.java.en.Given;

public class emailStepDefs {

	WebDriver driver;
	
//	  Scenario: User attaches an image file to an outgoing email
//    Given the user is creating an email
//    When the user adds an image file attachment
//    Then the email displays that the image file is attached

	@Given("^the user is creating an email$")
	public void the_user_is_creating_an_email() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Robert\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://accounts.google.com");
		driver.findElement(By.id("identifierId")).sendKeys("ecse428assb@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		driver.findElement(By.name("password")).sendKeys("testing123!");
		driver.findElement(By.id("passwordNext")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gb")));
		//Go to Gmail inbox now
		driver.get("https://mail.google.com/mail/#inbox");
		//compose new message
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\":3s\"]/div/div")));
		driver.findElement(By.xpath("//*[@id=\":3s\"]/div/div")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("to")));
		driver.findElement(By.name("to")).sendKeys("testing@gmail.com");
		driver.findElement(By.name("subjectbox")).sendKeys("This is the subject");
		//driver.findElement(By.id(":a4")).sendKeys("This is the body of the email");
		WebElement tempPic = driver.findElement(By.name("Filedata"));
		//driver.findElement(By.id(":af")).click();
		tempPic.sendKeys("C:\\Users\\Robert\\Pictures\\Camera Roll\\sendtoJack.PNG");
		//Either of the following can work for the compose button, lower one is better though
		//*[@id=\":41\"]/div/div
		//*[@id=":3s"]/div/div
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
