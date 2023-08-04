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

public class ViewCategoryProducts {

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
	public void categoryProducts() {
		// 3. Verify that categories are visible on left side bar
		String textCategory = driver.findElement(By.xpath("//h2[text()='Category']")).getText();
		Assert.assertEquals(textCategory, "CATEGORY");

		// 4. Click on 'Women' category
		driver.findElement(By.xpath("//a[@href='#Women']")).click();

		// 5. Click on any category link under 'Women' category, for example: Dress
		driver.findElement(By.xpath("//a[text()='Tops ']")).click();

		// 6. Verify that category page is displayed and confirm text 'WOMEN - TOPS
		// PRODUCTS'
		String confirmText = driver.findElement(By.xpath("//h2[text()='Women - Tops Products']")).getText();
		Assert.assertEquals(confirmText, "WOMEN - TOPS PRODUCTS");

		// 7. On left side bar, click on any sub-category link of 'Men' category
		driver.findElement(By.xpath("//a[@href='#Men']")).click();
		driver.findElement(By.xpath("//a[text()='Tshirts ']")).click();

		// 8. Verify that user is navigated to that category page
		String confirmMenText = driver.findElement(By.xpath("//h2[text()='Men - Tshirts Products']")).getText();
		Assert.assertEquals(confirmMenText, "MEN - TSHIRTS PRODUCTS");
		System.out.println("User Is Navigated To That Category Page..");

	}

	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}

}
