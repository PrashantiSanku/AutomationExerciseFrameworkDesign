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

public class VerifyProductQuantity {
	
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
	public void quantityProductVerify() throws InterruptedException
	{
		//4. Click 'View Product' for any product on home page
		driver.findElement(By.xpath("//a[@href='/product_details/1']")).click();
		
		//5. Verify product detail is opened
		driver.findElement(By.name("quantity")).clear();
		
		//6. Increase quantity to 4
		driver.findElement(By.name("quantity")).sendKeys("4");
		
		//7. Click 'Add to cart' button
		driver.findElement(By.xpath("//button[@type='button']")).click();
		
		//8. Click 'View Cart' button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a//u[text()='View Cart']")).click();
		
		//9. Verify that product is displayed in cart page with exact quantity
		String qty = driver.findElement(By.xpath("//td[@class='cart_quantity']")).getText();
		Assert.assertEquals(qty, "4");
	}
	
	@AfterClass
	public void terminateBrowser()
	{
		driver.close();
	}

}
