package SauceDemo.SauceDemo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class CheckOutProcessTest {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@SuppressWarnings("deprecation")
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
	
	@Test(dataProvider = "loginDataProvider")
	public void OrderComplete(String productName) throws InterruptedException, IOException {
		
		test = extent.createTest("Ensure a user can successfully complete a purchase.");
		LogintoApplication login = new LogintoApplication(driver);
		ProductFilterPage filter = new ProductFilterPage(driver);
		AddToCartPage addCart = new AddToCartPage(driver);
		CheckoutProcessPage completeOrder= new CheckoutProcessPage(driver);
		login.loginpage("standard_user", "secret_sauce");
		filter.ProductFilter();
		addCart.AddProduct(productName);
		completeOrder.CheckOutButton();
		completeOrder.CheckOutInfo("Tester1", "Tester2", "123456");
		String confirmMessage = completeOrder.ConfirmationMessage();
		if (confirmMessage.equalsIgnoreCase("THANK YOU FOR YOUR ORDER")) {
			test.pass("Order Placed Successfully");
		}else {
			String path = ReportGenerator.takeScreenshot(getClass().getName(),driver);
			test.fail("Order not placed").addScreenCaptureFromBase64String(path);
		}		
	}
	
	@DataProvider(name = "loginDataProvider")
    public Object[][] getLoginData() throws IOException {
        String filePath = "./testdata/TestData.xlsx";
        return ReadExcelFile.getLoginData(filePath, "Product");
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
