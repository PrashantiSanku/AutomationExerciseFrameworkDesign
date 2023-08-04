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

public class RemoveCartProducts {
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
	public void cartProductRemove() throws InterruptedException
	{
		//4. Add products to cart
		driver.findElement(By.xpath("//a[text()=' Products']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
		Thread.sleep(5000);
		
		//5. Click 'Cart' button
		driver.findElement(By.xpath("//a//u[text()='View Cart']")).click();
		Thread.sleep(5000);
		
		//6. Verify that cart page is displayed
		String text = driver.findElement(By.xpath("//li[@class='active']")).getText();
		Assert.assertEquals(text, "Shopping Cart");
		
		//7. Click 'X' button corresponding to particular product
		driver.findElement(By.xpath("//a[@class='cart_quantity_delete']")).click();
		Thread.sleep(3000);
		
		//8. Verify that product is removed from the cart
		String cartEmpty =driver.findElement(By.xpath("//span[@id='empty_cart']//b")).getText();
		Assert.assertEquals(cartEmpty, "Cart is empty!");
	}
	
	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
