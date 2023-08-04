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

public class PlcOrderLoginBeforeCheckout {

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
	public void logBeforeCheckout() throws InterruptedException
	{
		//4. Click 'Signup / Login' button
		driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();
		
		//5. Fill email, password and click 'Login' button
		driver.findElement(By.name("email")).sendKeys("Qa123@gmail.com");
		driver.findElement(By.name("password")).sendKeys("teSt@789");
		driver.findElement(By.cssSelector("button[@data-qa='login-button']")).click();
		
		//6. Verify 'Logged in as username' at top
		boolean userVisible = driver.findElement(By.xpath("//li/a[text()=' Logged in as ']/b[text()='SoftwareEngineerJava']")).isDisplayed();
		Assert.assertTrue(userVisible, "'Logged in as username!' is visible");
		
		//7. Add products to cart
		driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		driver.findElement(By.xpath("//a[@data-product-id='2']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		driver.findElement(By.xpath("//a[@data-product-id='3']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		
		//8. Click 'Cart' button
		driver.findElement(By.xpath("//a[text()=' Cart']")).click();
		
		//9. Verify that cart page is displayed
		String verifyCartText = driver.findElement(By.xpath("//li[text()='Shopping Cart']")).getText();
		Assert.assertEquals(verifyCartText, "Shopping Cart");
		
		//10. Click Proceed To Checkout
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
		
		//11. Verify Address Details and Review Your Order
		String addDetails = driver.findElement(By.xpath("//h2[text()='Address Details']")).getText();
		Assert.assertEquals(addDetails, "Address Details");
		
		String revDetails = driver.findElement(By.xpath("//h2[text()='Review Your Order']")).getText();
		Assert.assertEquals(revDetails, "Review Your Order");
		
		//12. Enter description in comment text area and click 'Place Order'
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("i am happy for this products");
		driver.findElement(By.xpath("//a[text()='Place Order']")).click();
		
		//13. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		driver.findElement(By.name("name_on_card")).sendKeys("SeleniumAutomation");
		driver.findElement(By.name("card_number")).sendKeys("90887766554");
		driver.findElement(By.name("cvc")).sendKeys("311");
		driver.findElement(By.name("expiry_month")).sendKeys("11");
		driver.findElement(By.name("expiry_year")).sendKeys("2025");
		
		//14. Click 'Pay and Confirm Order' button
		driver.findElement(By.xpath("//button[text()='Pay and Confirm Order']")).click();
		Thread.sleep(5000);
		
		//15. Verify success message 'Your order has been placed successfully!'
		String successMsg = driver.findElement(By.xpath("//div[@class='alert-success alert']")).getText();
		Assert.assertEquals(successMsg, "Your order has been placed successfully!");
		
		//16. Click 'Delete Account' button
		driver.findElement(By.xpath("//li//a[text()=' Delete Account']")).click();
		
		//17. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		driver.findElement(By.xpath("//h2//b[text()='Account Deleted!']")).click();
		driver.findElement(By.xpath("//div//a[text()='Continue']")).click();
	}
	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
