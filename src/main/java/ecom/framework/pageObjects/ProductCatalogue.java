package ecom.framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.framework.components.BaseComponent;

public class ProductCatalogue extends BaseComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card']")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	public List<WebElement> getProductList() {

		waitForElementToAppear(By.xpath("//div[@class='card']"));
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(item -> item.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
//		System.out.println("3");
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
//		System.out.println(prod.getSize());
		System.out.println("4");
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToAppear(By.cssSelector("#toast-container"));
		waitForElementToDisappear(spinner);
//		prod.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
//		System.out.println("Buttn licked");
	}

}
