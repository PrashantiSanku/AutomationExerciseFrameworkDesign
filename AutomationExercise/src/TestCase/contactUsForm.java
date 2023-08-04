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

public class contactUsForm {
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
	public void contactUsForm() {
		// 4. Click on 'Contact Us' button
		driver.findElement(By.xpath("//a[text()=' Contact us']")).click();

		// 5. Verify 'GET IN TOUCH' is visible
		String text = driver.findElement(By.xpath("//h2[text()='Get In Touch']")).getText();
		Assert.assertEquals(text, "GET IN TOUCH");

		// 6. Enter name, email, subject and message
		driver.findElement(By.name("name")).sendKeys("AutomationEngineer");
		driver.findElement(By.name("email")).sendKeys("automationengineer@gmail.com");
		driver.findElement(By.name("subject")).sendKeys("Software Testing");
		driver.findElement(By.id("message")).sendKeys("Demo QA");

		// 7. Upload file
		driver.findElement(By.name("upload_file")).sendKeys("C:\\Users\\admin\\Desktop\\New folder (2)");

		// 8. Click 'Submit' button
		driver.findElement(By.name("submit")).click();

		// 9. Click OK button
		driver.switchTo().alert().accept();

		// 10. Verify success message 'Success! Your details have been submitted
		// successfully.' is visible
		String success = driver
				.findElement(By.xpath("//div[@class='contact-form']//div[contains(@class,'alert-success')]")).getText();
		Assert.assertEquals(success, "Success! Your details have been submitted successfully.");

		// 11. Click 'Home' button and verify that landed to home page successfully
		driver.findElement(By.xpath("//span[text()=' Home']")).click();
		homePageTitleVerification();

	}

	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
