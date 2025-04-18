package SauceDemo.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.*;


public class HomepageVerificationTest {
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
	
	// Homepage Load Verification after Login Scenario, Taking user name and password from excel file
	@Test(dataProvider = "loginDataProvider")
	public void LoginTest(String userName, String password) throws IOException {
		// This line add the title of test on Report
		test = extent.createTest("Homepage Load Verification after Logint - " + userName);
		LogintoApplication login = new LogintoApplication(driver); //Login Class object created
		login.loginpage(userName, password); //Calling Login method
		String currentPage = driver.getCurrentUrl();
		
		// Verify Home Page loaded or not
		if(currentPage.contains("inventory")) {
			System.out.println("User logged in with username:"+userName);
			test.pass("Login Successfully");
		}else {
			String path = ReportGenerator.takeScreenshot(getClass().getName(),driver);
			 test.fail("Login Failed").addScreenCaptureFromBase64String(path);
			
		}		
	}	
	
	// Reading data from excel file
	 @DataProvider(name = "loginDataProvider")
	    public Object[][] getLoginData() throws IOException {
	        String filePath = "./testdata/TestData.xlsx";
	        return ReadExcelFile.getLoginData(filePath, "Login");
	    }
	 
	 // closing driver object 
	 @AfterMethod
	 public void TearDown() {
		 driver.close();
	 }
	 @AfterTest
	 public void tearDownReport() {
	     extent.flush(); // This generates and saves the HTML report
	 }
}
