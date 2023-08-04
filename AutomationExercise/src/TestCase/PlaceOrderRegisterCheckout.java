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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaceOrderRegisterCheckout {
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
	public void placeOrder() throws InterruptedException
	{
		//4. Add products to cart
		driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		driver.findElement(By.xpath("//a[@data-product-id='2']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		driver.findElement(By.xpath("//a[@data-product-id='3']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		
		//5. Click 'Cart' button
		driver.findElement(By.xpath("//a[text()=' Cart']")).click();
		
		//6. Verify that cart page is displayed
		String verifyCartText = driver.findElement(By.xpath("//li[text()='Shopping Cart']")).getText();
		Assert.assertEquals(verifyCartText, "Shopping Cart");
		
		//7. Click Proceed To Checkout
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
		
		//8. Click 'Register / Login' button
		driver.findElement(By.xpath("//a//u[text()='Register / Login']")).click();
		
		//9. Fill all details in Signup and create account
		driver.findElement(By.name("name")).sendKeys("rahulQAJava");
		driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("RahulQAJava87967@gmail.com");
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		
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
		driver.findElement(By.xpath("//input[@id='newsletter']")).click();
		driver.findElement(By.cssSelector("input[name='optin']")).click();
		
		driver.findElement(By.name("first_name")).sendKeys("QASelenium");
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
		driver.findElement(By.xpath("//button[text()='Create Account']")).click();
		
		//10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		boolean accountVisible = driver.findElement(By.xpath("//b[text()='Account Created!']")).isDisplayed();
		Assert.assertTrue(accountVisible, "'ACCOUNT CREATED!' is visible");
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		
		//11. Verify ' Logged in as username' at top
		boolean userVisible = driver.findElement(By.xpath("//li/a[text()=' Logged in as ']/b[text()='rahulQAJava']")).isDisplayed();
		Assert.assertTrue(userVisible, "'Logged in as username!' is visible");
		
		//12.Click 'Cart' button
		driver.findElement(By.xpath("//a[text()=' Cart']")).click();
		
		//13. Click 'Proceed To Checkout' button
		driver.findElement(By.xpath("//a[@class=\"btn btn-default check_out\"]")).click();
		
		//14. Verify Address Details and Review Your Order
		String addDetails = driver.findElement(By.xpath("//h2[text()='Address Details']")).getText();
		Assert.assertEquals(addDetails, "Address Details");
		
		String revDetails = driver.findElement(By.xpath("//h2[text()='Review Your Order']")).getText();
		Assert.assertEquals(revDetails, "Review Your Order");
		
		//15. Enter description in comment text area and click 'Place Order'
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("i am happy for this products");
		driver.findElement(By.xpath("//a[text()='Place Order']")).click();
		
		//16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		driver.findElement(By.name("name_on_card")).sendKeys("SeleniumAutomation");
		driver.findElement(By.name("card_number")).sendKeys("90887766554");
		driver.findElement(By.name("cvc")).sendKeys("311");
		driver.findElement(By.name("expiry_month")).sendKeys("11");
		driver.findElement(By.name("expiry_year")).sendKeys("2025");
		
		//17. Click 'Pay and Confirm Order' button
		driver.findElement(By.xpath("//button[text()='Pay and Confirm Order']")).click();
		Thread.sleep(5000);
		//18. Verify success message 'Your order has been placed successfully!'
		String successMsg = driver.findElement(By.xpath("//div[@class='alert-success alert']")).getText();
		Assert.assertEquals(successMsg, "Your order has been placed successfully!");
		
		
		//19. Click 'Delete Account' button
		driver.findElement(By.xpath("//li//a[text()=' Delete Account']")).click();
		
		//20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		driver.findElement(By.xpath("//h2//b[text()='Account Deleted!']")).click();
		driver.findElement(By.xpath("//div//a[text()='Continue']")).click();
	}
	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}

}
