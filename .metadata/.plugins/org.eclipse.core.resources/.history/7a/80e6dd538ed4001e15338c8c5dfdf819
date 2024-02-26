package org.timbuk2.TestCompopnents;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.timbuk2.PageObjects.Timbuk2GetProducts;
import org.timbuk2.PageObjects.Timbuk2LandingPage;
import org.timbuk2.PageObjects.Timbuk2ProductPage;
import org.timbuk2.PageObjects.Timbuk2ReviewAndSubmitOrder;
import org.timbuk2.PageObjects.Timbuk2SecureCheckOut;
import org.timbuk2.connections.ConnectionsClass;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


public class Timbuk2CheckOutFlowTest extends ConnectionsClass {

	public WebDriver driver;

	//changed
	@Test
	public void checkOutFlow() throws InterruptedException, IOException, CsvException {
//		WebDriverManager.edgedriver().setup();
//		driver = new EdgeDriver();
//		driver.manage().window().maximize();
//		Timbuk2LandingPage landingPage = new Timbuk2LandingPage(driver);
//		landingPage.goTo();
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-347641589");
		Timbuk2ProductPage productPage = landingPage.acceptCookies();
		Timbuk2GetProducts getProducts = productPage.selectProduct();
		Timbuk2SecureCheckOut secureCheckOut = getProducts.getProductList();
		secureCheckOut.secureCheckOut("password");
		Timbuk2ReviewAndSubmitOrder reviewAndSubmitOrder = secureCheckOut.secureLogin();
		reviewAndSubmitOrder.selectCountry("Canada");
		reviewAndSubmitOrder.selectState("Ontario");
		CSVReader userAddress = new CSVReader(
				new FileReader("C:\\Users\\Nagendra Vellanki\\Desktop\\Timbuk2\\timbuk2address.csv"));
		List<String[]> csvData = userAddress.readAll();
		for (int i = 1; i < csvData.size(); i++) {
			String[] row = csvData.get(i);
			String firstName = row[0];
			String lastName = row[1];
			String email = row[2];
			String address = row[3];
			String city = row[4];
			String postal = row[5];
			String phone = row[6];
			reviewAndSubmitOrder.enterAddressDetails(firstName, lastName, email, address, city, postal, phone);
			reviewAndSubmitOrder.submitAddressDetails();
		}
	}
	

	//changes made
	@Test
	public void productTextValidation() throws IOException, InterruptedException {
		Timbuk2LandingPage landingPage = launchApplication();
		landingPage.discountCode("VeniceBeach-Retail-DTA935E9ED-347641589");
		Timbuk2ProductPage productPage = landingPage.acceptCookies();
		Timbuk2GetProducts getProducts = productPage.selectProduct();
		getProducts.getProductList();
		List<String> productTexts = getProducts.getProductTexts();
		Assert.assertEquals("Custom Division Laptop Backpack", productTexts.get(0));
		Assert.assertEquals("Custom Alcatraz Laptop Backpack", productTexts.get(1));

	}
}
