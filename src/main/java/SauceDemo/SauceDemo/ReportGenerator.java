package SauceDemo.SauceDemo;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportGenerator {
	static ExtentReports extent;
	static WebDriver driver;
	
	public ReportGenerator(WebDriver driver) {
		this.driver = driver;
	}
	public static ExtentReports extentReporter() {
		if(extent==null) {
			// create a object of ExtentSparkReporter class object 
			ExtentSparkReporter reporter= new ExtentSparkReporter("./Reports/Automation_report.html");
			extent = new ExtentReports();// initialize Reporter
			extent.attachReporter(reporter);			
		}
		return extent;
	}
	
	// Method created to capture screenshot for failed test case and test class name given to screenshot
	public static String takeScreenshot (String name,WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "./reports/screenshots/" + name + ".png";
        FileUtils.copyFile(src, new File(path));
        return path;
    }
}
