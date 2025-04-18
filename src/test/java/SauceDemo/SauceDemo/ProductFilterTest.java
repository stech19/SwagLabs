package SauceDemo.SauceDemo;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// This Test class used to add filer on product Low to high price
public class ProductFilterTest {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeMethod
	public void SetUp() {
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/v1");
		driver.manage().window().maximize();
	}
	
	@BeforeTest
	public void initiateReport() {
		extent = ReportGenerator.extentReporter();
	}
	@Test
	public void ProductFilter() throws IOException
	{
		
		test = extent.createTest("Verify that product filtering (Low to High Price)");
		LogintoApplication login = new LogintoApplication(driver);
		ProductFilterPage filter = new ProductFilterPage(driver);
		login.loginpage("standard_user", "secret_sauce");
		filter.ProductFilter();
		filter.FilterList();
		
		if(!filter.FilterList().isEmpty()) {
			test.pass("Product sorted");
		}else {
			String path = ReportGenerator.takeScreenshot(getClass().getName(),driver);
			test.fail("Product not found").addScreenCaptureFromBase64String(path);
		}
	}
	@AfterTest
	public void closeReport() {
		extent.flush();
	}
	
	@AfterMethod
	 public void TearDown() {
		 driver.close();
	 }

}
