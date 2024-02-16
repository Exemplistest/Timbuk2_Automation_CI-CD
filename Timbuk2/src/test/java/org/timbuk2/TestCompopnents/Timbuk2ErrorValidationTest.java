package org.timbuk2.TestCompopnents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.timbuk2.PageObjects.Timbuk2GetProducts;
import org.timbuk2.PageObjects.Timbuk2LandingPage;
import org.timbuk2.PageObjects.Timbuk2ProductPage;
import org.timbuk2.PageObjects.Timbuk2SecureCheckOut;
import org.timbuk2.connections.ConnectionsClass;

public class Timbuk2ErrorValidationTest extends ConnectionsClass {

	public WebDriver driver;

	@Test
	public void discountCodeValidation() throws IOException, InterruptedException {
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-3476415890");
		Assert.assertEquals("CAP Discount code is invalid, Please check.", landingPage.getErrorMessage());
	}

	@Test
	public void secureCheckOutValidation() throws IOException, InterruptedException {
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-347641589");
		Timbuk2ProductPage productPage = landingPage.acceptCookies();
		Timbuk2GetProducts getProducts = productPage.selectProduct();
		Timbuk2SecureCheckOut secureCheckOut = getProducts.getProductList();
		secureCheckOut.secureCheckOut("password!");
		Assert.assertEquals("Invalid Password. Please check", secureCheckOut.getErrorMessage());
	}

}
