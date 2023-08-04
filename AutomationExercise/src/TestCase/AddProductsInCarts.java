package TestCase;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddProductsInCarts {
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
	public void addCartProducts() throws InterruptedException
	{
		//4. Click 'Products' button
		driver.findElement(By.xpath("//a[text()=' Products']")).click();
		
		//5. Hover over first product and click 'Add to cart'
		driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
		
		//6. Click 'Continue Shopping' button
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();
		
		//7. Hover over second product and click 'Add to cart'
		driver.findElement(By.xpath("//a[@data-product-id='2']")).click();
		Thread.sleep(3000);
		
		//8. Click 'View Cart' button
		driver.findElement(By.xpath("//a//u[text()='View Cart']")).click();
		
		//9. Verify both products are added to Cart
		List<WebElement> firstProductDetails = driver.findElements(By.id("product-1"));
		List<WebElement> secondProductDetails = driver.findElements(By.id("product-2"));
		
		//10. Verify their prices, quantity and total price
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Blue Top");
		map.put(1, "Women > Tops");
		map.put(1, "Rs. 500");
		map.put(1, "1");
		map.put(1, "Rs. 500");
		map.put(2, "Men Tshirt");
		map.put(2, "Men > Tshirts");
		map.put(2, "Rs. 400");
		map.put(2, "1");
		map.put(2, "Rs. 400");
		
		for(int i=0; i<firstProductDetails.size();i++)
		{
			Assert.assertTrue(firstProductDetails.get(i).getText().contains(map.get(1)));
			Assert.assertTrue(secondProductDetails.get(i).getText().contains(map.get(2)));
		}
		
	}
	
	@AfterClass
	public void terminateBrowser()
	{
		driver.close();
	}
}
