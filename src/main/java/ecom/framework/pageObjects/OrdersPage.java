package ecom.framework.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.framework.components.BaseComponent;

public class OrdersPage extends BaseComponent {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	WebElement ordersDisplay;
	
	public boolean verifyOrderDisplay(String productName) {
		return ordersDisplay.getText().equalsIgnoreCase(productName);
	}

}
