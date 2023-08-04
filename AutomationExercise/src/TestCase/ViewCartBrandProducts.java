package TestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewCartBrandProducts {

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
	public void brandsProducts() throws InterruptedException {
		// 3. Click on 'Products' button
		driver.findElement(By.xpath("//a[text()=' Products']")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		
		// 4. Verify that Brands are visible on left side bar
		String textBrands = driver.findElement(By.xpath("//h2[text()='Brands']")).getText();
		Assert.assertEquals(textBrands, "BRANDS");

		// 5. Click on any brand name
		driver.findElement(By.xpath("//a[text()='Polo']")).click();

		// 6. Verify that user is navigated to brand page and brand products are
		// displayed
	String productBrands = driver.findElement(By.xpath("//h2[text()='Brand - Polo Products']")).getText();
		//productBrands.isDisplayed();
		//Assert.assertTrue(true, "productBrands");
		//productBrands.getText();
		Assert.assertEquals(productBrands, "BRAND - POLO PRODUCTS");

		// 7. On left side bar, click on any other brand link
		driver.findElement(By.xpath("//a[text()='Babyhug']")).click();

		// 8. Verify that user is navigated to that brand page and can see products
		String pageBrands = driver.findElement(By.xpath("//h2[text()='Brand - Babyhug Products']")).getText();
		//pageBrands.isDisplayed();
		//Assert.assertTrue(true, "pageBrands");
		//pageBrands.getText();
		Assert.assertEquals(pageBrands, "BRAND - BABYHUG PRODUCTS");
	}

	@AfterClass
	public void terminateBrowser() {
		driver.close();
	}
}
