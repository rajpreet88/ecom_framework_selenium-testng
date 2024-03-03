package ecom.framework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecom.framework.components.BaseComponent;
import ecom.framework.pageObjects.CartPage;
import ecom.framework.pageObjects.CheckoutPage;
import ecom.framework.pageObjects.ConfirmationPage;
import ecom.framework.pageObjects.LandingPage;
import ecom.framework.pageObjects.OrdersPage;
import ecom.framework.pageObjects.ProductCatalogue;
import ecom.framework.testComponents.BaseTestComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2ETest extends BaseTestComponent {
//	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData")
	public void submitOrderTest(HashMap<String, String> input) throws IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		CartPage cartPage;
		try {
			productCatalogue.addProductToCart(input.get("productName"));
			cartPage = productCatalogue.goToCartPage();
		} catch (StaleElementReferenceException s) {
			productCatalogue.addProductToCart(input.get("productName"));
			cartPage = productCatalogue.goToCartPage();
		}

		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		checkoutPage.selectCountry("india");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String message = confirmationPage.verifyConfirmationMessage();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

	}

//	@Test(dependsOnMethods = { "submitOrderTest" })
//	public void orderHistoryTest() {
//
//		ProductCatalogue productCatalogue = landingPage.loginApplication("wolverine@marvel.com", "Jaigurudev@8");
//		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
//		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
//	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\ecom\\framework\\testData\\PurchaseOrder.json");

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "wolverine@marvel.com");
//		map.put("password", "Jaigurudev@8");
//		map.put("productname", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "hulk@avengers.com");
//		map1.put("password", "Jaigurudev@8");
//		map1.put("productname", "ADIDAS ORIGINAL");

//		return new Object[][] { { "wolverine@marvel.com", "Jaigurudev@8", "ZARA COAT 3" },
//				{ "hulk@avengers.com", "Jaigurudev@8", "ADIDAS ORIGINAL" } };

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
