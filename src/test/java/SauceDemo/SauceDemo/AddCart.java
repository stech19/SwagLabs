package SauceDemo.SauceDemo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

// THi class used to add product in cart and product reading from excel file
public class AddCart {
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
	
	@Test(dataProvider = "loginDataProvider")
	public void AddToCart(String productName) throws IOException {
		
		test = extent.createTest("Validate cart functionality.");
		LogintoApplication login = new LogintoApplication(driver);
		ProductFilterPage filter = new ProductFilterPage(driver);
		AddToCartPage addCart = new AddToCartPage(driver);
		login.loginpage("standard_user", "secret_sauce");
		filter.ProductFilter();
		if(addCart.AddProduct(productName).equalsIgnoreCase("Pass")) {
			test.pass("Clicked on add cart");
		}else {
			String path = ReportGenerator.takeScreenshot(getClass().getName(),driver);
			test.fail("Add Cart button is disabled").addScreenCaptureFromPath(path);
		}
		if(addCart.ValidateItemAdded().size()>0) {
			test.pass("Item added to cart");
		}else {
			String path = ReportGenerator.takeScreenshot(getClass().getName(),driver);
			test.fail("Item not added in cart").addScreenCaptureFromPath(path);
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
