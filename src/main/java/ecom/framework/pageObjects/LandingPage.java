package ecom.framework.pageObjects;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ecom.framework.components.BaseComponent;

public class LandingPage extends BaseComponent {
	WebDriver driver;
	Properties prop;

	public LandingPage(WebDriver driver, Properties prop) {
		super(driver);
		this.driver = driver;
		this.prop = prop;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement loginBtn;

//	@FindBy(xpath = "//div[@class='ng-tns-c4-17 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toaster toast-error']")
	@FindBy(css = "div[class='toast-error']")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String pwd) {
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		loginBtn.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}

	public void landingHome() {
		driver.get(prop.getProperty("url"));
	}

	public String getErrorMessage() {
		System.out.println("QQQQ");
		waitForWebElementToAppear(errorMessage);

		return errorMessage.getText();
	}

}
