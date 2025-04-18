package SauceDemo.SauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutProcessPage {
	WebDriver driver;
	
	public CheckoutProcessPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void CheckOutButton() {
		WebElement checkOutButton = driver.findElement(By.xpath("//a[normalize-space()='CHECKOUT']"));		
		checkOutButton.click();	
	}
	
	public void CheckOutInfo(String name,String lastName,String zip)
	{
		driver.findElement(By.id("first-name")).sendKeys(name);
		driver.findElement(By.id("last-name")).sendKeys(lastName);
		driver.findElement(By.id("postal-code")).sendKeys(zip);
		driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='FINISH']")).click();
		
	}
	
	public String ConfirmationMessage() {
		String confirmMessage = driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']")).getText();
		return confirmMessage;
	}
}
