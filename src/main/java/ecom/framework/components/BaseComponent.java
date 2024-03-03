package ecom.framework.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ecom.framework.pageObjects.CartPage;
import ecom.framework.pageObjects.OrdersPage;

public class BaseComponent {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;

	@FindBy(xpath = "button[@routerlink='/dashboard/myorders']")
	WebElement ordersBtn;

	public BaseComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By card) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(card));
	}

	public void waitForWebElementToAppear(WebElement card) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(card));
		System.out.println("WWW");
	}

	public void waitForElementToDisappear(WebElement locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(locator));
	}

	public CartPage goToCartPage() {
//		System.out.println("Hi");
		cartBtn.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}

	public OrdersPage goToOrdersPage() {
//		System.out.println("Hi");
		ordersBtn.click();
		OrdersPage orderPage = new OrdersPage(driver);
		return orderPage;

	}
	
	

}
