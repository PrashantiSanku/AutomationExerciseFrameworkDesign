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

public class registerUserExistingEmail {
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

		// 5. Verify 'New User Signup!' is visible
		boolean signUpText = driver.findElement(By.xpath("//h2[text()='New User Signup!']")).isDisplayed();
		Assert.assertTrue(signUpText, "'New User Signup!' is visible");

		// 6. Enter name and email address
		driver.findElement(By.name("name")).sendKeys("Qa Seleniums");
		driver.findElement(By.xpath("(//input[@name='email'])[2]")).sendKeys("AutomationEngineer@gmail.com");

		// 7. Click 'Signup' button
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		
		//8. Verify error 'Email Address already exist!' is visible
		String errorText = driver.findElement(By.xpath("//p[text()='Email Address already exist!']")).getText();
		Assert.assertEquals(errorText, "Email Address already exist!");
		
	}
	
	@AfterClass
	public void terminateBrowser()
	{
		driver.close();
	}
}
