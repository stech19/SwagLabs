package SauceDemo.SauceDemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductFilterPage {

	WebDriver driver;

	public ProductFilterPage(WebDriver driver) {
		this.driver = driver;
	}

	public void ProductFilter() {
		WebElement filter = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
		Select filterdrop = new Select(filter);
		filterdrop.selectByVisibleText("Price (low to high)");
	}

	public List<WebElement> FilterList() {
		List<WebElement> list=null;
		try {
			list = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
			for (WebElement item : list) {			
				System.out.println("List After Filer Apply :-"+item.getText());			
			}			
		}catch(Exception e){
			e.getMessage();
		}
		return list;
	}

}
