package TestCase;

import java.time.Duration;
import java.util.ArrayList;
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

public class VerifyAllProducts {
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
	public void verifyAllProducts() throws InterruptedException
	{
		//4. Click on 'Products' button
		driver.findElement(By.xpath("//a[text()=' Products']")).click();
		
		//5. Verify user is navigated to ALL PRODUCTS page successfully
		String Product = driver.findElement(By.xpath("//h2[text()='All Products']")).getText();
		//6. The products list is visible
		Assert.assertEquals(Product, "ALL PRODUCTS");
		
		//7. Click on 'View Product' of first product
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//a[text()='View Product'])[1]")).click();
		
		//8. User is landed to product detail page
		String ProductName = driver.findElement(By.xpath("//h2[text()='Blue Top']")).getText();
		Assert.assertEquals(ProductName, "Blue Top");
		
		//9. Verify that detail detail is visible: product name, category, price, availability, condition, brand
		ArrayList<Object> ar = new ArrayList<Object>();
		ar.add("Category: Women > Tops");
		ar.add("Availability: In Stock");
		ar.add("Condition: New");
		ar.add("Brand: Polo");
		
		
		List<WebElement> details = driver.findElements(By.xpath("//div[@class='product-information']//p"));
		for(int i = 0; i<details.size();i++)
		{
			System.out.println(details.get(i).getText());
			Assert.assertEquals(details.get(i).getText(), ar.get(i));
			
		}
		
	}
	
	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
