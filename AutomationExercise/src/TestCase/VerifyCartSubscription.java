package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyCartSubscription {
	WebDriver driver;

	@BeforeClass
	public void invokeBrowser() {

		WebDriverManager.chromedriver().setup();
		// 1. Launch browser
		driver = new ChromeDriver();
		// 2. Navigate to url 'http://automationexercise.com'
		driver.get("http://automationexercise.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

	}

	@Test(priority = 1)
	public void homePageTitleVerification() {
		// 3. Verify that home page is visible successfully
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Automation Exercise", "Incorrect Title");
	}

	@Test(priority = 2)
	public void cartVerifySubscription()
	{
		//4. Click 'Cart' button
		driver.findElement(By.xpath("//a[text()=' Cart']")).click();
		
		//5. Scroll down to footer
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		
		//6. Verify text 'SUBSCRIPTION'
		String text = driver.findElement(By.xpath("//h2[text()='Subscription']")).getText();
		Assert.assertEquals(text, "SUBSCRIPTION");
		
		//7. Enter email address in input and click arrow button
		driver.findElement(By.id("susbscribe_email")).sendKeys("qaautomation@gmail.com");
		driver.findElement(By.id("subscribe")).click();
		
		//8. Verify success message 'You have been successfully subscribed!' is visible
		String succestext = driver.findElement(By.id("success-subscribe")).getText();
		Assert.assertEquals(succestext, "You have been successfully subscribed!");
	}
	
	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
