package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class registerUser {
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

		// 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
		boolean accountText = driver.findElement(By.xpath("//b[text()='Enter Account Information']")).isDisplayed();
		Assert.assertTrue(accountText, "'ENTER ACCOUNT INFORMATION' is visible");

		// 9. Fill details: Title, Name, Email, Password, Date of birth
		driver.findElement(By.id("id_gender2")).click();
		driver.findElement(By.id("password")).sendKeys("teSt@789");

		WebElement day = driver.findElement(By.id("days"));
		Select daysDropDown = new Select(day);
		daysDropDown.selectByVisibleText("19");

		WebElement Month = driver.findElement(By.id("months"));
		Select monthDropDown = new Select(Month);
		monthDropDown.selectByVisibleText("September");

		WebElement Year = driver.findElement(By.id("years"));
		Select yearDropDown = new Select(Year);
		yearDropDown.selectByVisibleText("2008");

		// 10. Select checkbox 'Sign up for our newsletter!'
		driver.findElement(By.xpath("//input[@id='newsletter']")).click();

		// 11. Select checkbox 'Receive special offers from our partners!'
		driver.findElement(By.cssSelector("input[name='optin']")).click();

		// 12. Fill details: First name, Last name, Company, Address, Address2, Country,
		// State, City, Zipcode, Mobile Number
		driver.findElement(By.name("first_name")).sendKeys("QA");
		driver.findElement(By.name("last_name")).sendKeys("Engineer");
		driver.findElement(By.id("company")).sendKeys("Qa Engineer");
		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys("Mira Road");
		driver.findElement(By.cssSelector("input[id='address2']")).sendKeys("Maharashtra");

		WebElement Country = driver.findElement(By.name("country"));
		Select countryDropDown = new Select(Country);
		countryDropDown.selectByVisibleText("India");

		driver.findElement(By.name("state")).sendKeys("Maharashtra");
		driver.findElement(By.id("city")).sendKeys("Mumbai");

		driver.findElement(By.xpath("//input[@name='zipcode']")).sendKeys("400018");
		driver.findElement(By.cssSelector("input[id='mobile_number']")).sendKeys("9988667543");

		// 13. Click 'Create Account button'
		driver.findElement(By.xpath("//button[text()='Create Account']")).click();

		// 14. Verify that 'ACCOUNT CREATED!' is visible
		boolean accountVisible = driver.findElement(By.xpath("//b[text()='Account Created!']")).isDisplayed();
		Assert.assertTrue(accountVisible, "'ACCOUNT CREATED!' is visible");
		
		//15. Click 'Continue' button
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		
		//16. Verify that 'Logged in as username' is visible
		boolean userVisible = driver.findElement(By.xpath("//a[text()='Logged in as']/b[text()='QA Seleniums']")).isDisplayed();
		Assert.assertTrue(userVisible, "'Logged in as username!' is visible");
		
		//17. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[text()='Delete Account']")).click();
		
		//18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		boolean deleteText = driver.findElement(By.xpath("//b[text()='Account Deleted!']")).isDisplayed();
		Assert.assertTrue(deleteText, "'ACCOUNT DELETED!' is visible");
		
		driver.findElement(By.xpath("//a[text()='Continue'")).click();
		

	}
	
	@AfterClass
	public void terminateBrowser()
	{
		driver.close();
	}

}
