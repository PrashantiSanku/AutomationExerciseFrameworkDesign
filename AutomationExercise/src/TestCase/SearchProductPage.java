package TestCase;

import java.time.Duration;
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

public class SearchProductPage {
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
	public void searchProducts() throws InterruptedException
	{
		//4. Click on 'Products' button
		driver.findElement(By.xpath("//a[text()=' Products']")).click();
		
		//5. Verify user is navigated to ALL PRODUCTS page successfully
		String Product = driver.findElement(By.xpath("//h2[text()='All Products']")).getText();
		Assert.assertEquals(Product, "ALL PRODUCTS");
		Thread.sleep(3000);
		//6. Enter product name in search input and click search button
		driver.findElement(By.id("search_product")).sendKeys("Jeans");
		driver.findElement(By.id("submit_search")).click();
		
		//7. Verify 'SEARCHED PRODUCTS' is visible
		String text = driver.findElement(By.xpath("//h2[text()='Searched Products']")).getText();
		Assert.assertEquals(text, "SEARCHED PRODUCTS");
		
		//8. Verify all the products related to search are visible
		List<WebElement> items = driver.findElements(By.xpath("//div[contains(@class,'productinfo')]//p"));
		for(int i =0; i<items.size(); i++)
		{
			Assert.assertTrue(items.get(i).getText().contains("Jeans"));
		}
	}
	

	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
