package SauceDemo.SauceDemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class AddToCartPage {
	WebDriver driver;
		
	public AddToCartPage(WebDriver driver) {
		this.driver =driver;
	}
	
	public String AddProduct(String productName) {
		String result=null;
		driver.findElement(By.xpath("//div[text()='"+productName+"']")).click();
		WebElement productToAdd = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
		if(productToAdd.isEnabled()) {
			productToAdd.click();
			result ="pass";
		}else {
			result ="Failed";
		}
		
		WebElement gotoCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link fa-layers fa-fw']//*[name()='svg']"));
		if (gotoCart.isEnabled()) {
			gotoCart.click();
			result ="pass";
		}else {
			result= "Failed";
		}
		return result;
				
	}
	
	public List<WebElement> ValidateItemAdded() {
		
		List<WebElement> item = driver.findElements(By.xpath("//div[@class='cart_item']"));
		
		return item;
	}
}
