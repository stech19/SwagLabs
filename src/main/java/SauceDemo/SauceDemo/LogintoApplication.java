package SauceDemo.SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogintoApplication {
	WebDriver driver;
	
	public LogintoApplication(WebDriver driver) {
		this.driver=driver;
	}
	
	public void loginpage(String username,String password) {
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}
}
