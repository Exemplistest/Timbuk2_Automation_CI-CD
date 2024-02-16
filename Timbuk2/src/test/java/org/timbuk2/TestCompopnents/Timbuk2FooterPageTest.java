package org.timbuk2.TestCompopnents;

import java.io.IOException;

import org.testng.annotations.Test;
import org.timbuk2.PageObjects.Timbuk2FooterPage;
import org.timbuk2.PageObjects.Timbuk2GetProducts;
import org.timbuk2.PageObjects.Timbuk2LandingPage;
import org.timbuk2.PageObjects.Timbuk2ProductPage;
import org.timbuk2.connections.ConnectionsClass;

public class Timbuk2FooterPageTest extends ConnectionsClass {

	@Test
	public void footerLinksValidation() throws IOException, InterruptedException
	{
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-347641589");
		Timbuk2FooterPage footerPage = landingPage.acceptCookies1();
		footerPage.openFooterLogoInNewTab();
		footerPage.aboutUs();
	}
	
	@Test
	public void minicartValidations() throws IOException, InterruptedException
	{
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-347641589");
		Timbuk2ProductPage productPage = landingPage.acceptCookies();
		Timbuk2GetProducts getProducts = productPage.selectProduct();
		getProducts.incrementAndDecrement();
	}
	
	@Test 
	public void cartPageValidations() throws IOException, InterruptedException
	{
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-347641589");
		Timbuk2ProductPage productPage = landingPage.acceptCookies();
		Timbuk2GetProducts getProducts = productPage.selectProduct();
		getProducts.cart();
	}
}
