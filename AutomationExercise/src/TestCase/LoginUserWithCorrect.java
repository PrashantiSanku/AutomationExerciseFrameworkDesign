package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginUserWithCorrect {
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
	public void signUp() {

		// 4. Click on 'Signup / Login' button
		driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();
		
		//5. Verify 'Login to your account' is visible
		boolean logUpText = driver.findElement(By.xpath("//h2[text()='Login to your account']")).isDisplayed();
		Assert.assertTrue(logUpText, "'Login to your account' is visible");
		
		//6. Enter correct email address and password
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("AutomationEngineer@gmail.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("teSt@789");
		
		//7. Click 'login' button
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		//8. Verify that 'Logged in as username' is visible
		boolean userVisible = driver.findElement(By.xpath("//a[text()=' Logged in as ']/b[text()='Qa Seleniums']")).isDisplayed();
		Assert.assertTrue(userVisible, "'Logged in as username!' is visible");
		
		//9. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[text()=' Delete Account']")).click();
		
		//10. Verify that 'ACCOUNT DELETED!' is visible
		boolean deleteText = driver.findElement(By.xpath("//b[text()='Account Deleted!']")).isDisplayed();
		Assert.assertTrue(deleteText, "'ACCOUNT DELETED!' is visible");
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
	}
	
	@AfterClass
	public void terminateBrowser()
	{
		driver.close();
	}

}
