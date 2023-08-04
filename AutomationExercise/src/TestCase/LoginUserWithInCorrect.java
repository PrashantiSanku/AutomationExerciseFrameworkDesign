package TestCase;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginUserWithInCorrect {

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
	public void signUp() throws InterruptedException {

		// 4. Click on 'Signup / Login' button
		driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

		// 5. Verify 'Login to your account' is visible
		boolean logUpText = driver.findElement(By.xpath("//h2[text()='Login to your account']")).isDisplayed();
		Assert.assertTrue(logUpText, "'Login to your account' is visible");

		// 6. Enter incorrect email address and password
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("AutomationEngineer@gmail.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("teSt@789");

		// 7. Click 'login' button
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(5000);
		// 8. Verify error 'Your email or password is incorrect!' is visible
		boolean errorText = driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']")).isDisplayed();
		//String errorText = driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']")).getText();
		
		Assert.assertTrue(errorText, "'Login to your account' is visible");
		//assertEquals(errorText, "Your email or password is incorrect!");

	}
	
	@AfterClass
	public void terminateBrowser()
	{
		driver.close();
	}

}
