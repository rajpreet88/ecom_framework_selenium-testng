package ecom.framework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.security.auth.callback.ConfirmationCallback;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ecom.framework.components.BaseComponent;
import ecom.framework.pageObjects.CartPage;
import ecom.framework.pageObjects.CheckoutPage;
import ecom.framework.pageObjects.ConfirmationPage;
import ecom.framework.pageObjects.LandingPage;
import ecom.framework.pageObjects.ProductCatalogue;
import ecom.framework.testComponents.BaseTestComponent;
import ecom.framework.testComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTestComponent {
	String productName = "ZARA COAT 3";

	@Test(groups = { "ErrorHandling" }, priority = 1, retryAnalyzer = Retry.class)
	public void submitOrderErrorTest() throws IOException {

		landingPage.loginApplication("wolverine@marvel.com", "Jaigurudev@88");

		Assert.assertEquals(" Incorrect email or password. ", landingPage.getErrorMessage());

	}

	@Test(priority = 2)
	public void purchaseOrderErrorTest() throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("wolverine@marvel..com", "Jaigurudev@8");
		System.out.println("WWWW");
		CartPage cartPage;
		try {
			productCatalogue.addProductToCart(productName);
			cartPage = productCatalogue.goToCartPage();
		} catch (StaleElementReferenceException s) {
			productCatalogue.addProductToCart(productName);
			cartPage = productCatalogue.goToCartPage();
		}
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}
