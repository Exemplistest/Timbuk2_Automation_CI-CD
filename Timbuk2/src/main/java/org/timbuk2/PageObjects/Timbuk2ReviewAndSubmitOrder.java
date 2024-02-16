package org.timbuk2.PageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.timbuk2.AbstractComponents.AbstractComponents;

public class Timbuk2ReviewAndSubmitOrder extends AbstractComponents {

	WebDriver driver;

	public Timbuk2ReviewAndSubmitOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "address.country")
	WebElement countryField;
//
//	@FindBy(xpath = "//select[@class='form-control valid']")
//	WebElement stateField;

	@FindBy(id = "address.firstName")
	WebElement firstNameField;

	@FindBy(id = "address.surname")
	WebElement lastNameField;

	@FindBy(id = "address.email")
	WebElement emailField;

	@FindBy(id = "address.line1")
	WebElement addressField;

	@FindBy(id = "address.townCity")
	WebElement cityField;

	@FindBy(id = "address.postcode")
	WebElement postalField;

	@FindBy(id = "address.phone")
	WebElement phoneField;

//	@FindBy(xpath = "//span[@id='addressSubmitLabel']")
//	WebElement addressSubmit;

	@FindBy(xpath = "(//span[@class='checkout-title-edit'])[2]")
	WebElement edit;

	@FindBy(xpath = "//button[@id='deliveryMethodSubmit']")
	WebElement deliveryMode;

	@FindBy(xpath = "//a[@class='btn btn--primary btn-block submit_silentOrderPostForm checkout-next']")
	WebElement inStorePayment;

	@FindBy(xpath = "(//button[@id='placeOrder'])[1]")
	WebElement placeOrder;

	public void selectCountry(String country) {
		countryField.click();
		WebElement countryOption = driver
				.findElement(By.xpath("//select[@id='address.country']/option[text()='" + country + "']"));
		countryOption.click();
	}

	public void selectState(String state) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		//Thread.sleep(1500);
		WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(By.id("address.region")));
		stateField.click();
		WebElement ontarioOption = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//select[@id='address.region']/option[text()='" + state + "']")));
		ontarioOption.click();
	}

	public void enterAddressDetails(String firstName, String lastName, String email, String address, String city,
			String postal, String phone) throws InterruptedException {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		emailField.sendKeys(email);
		addressField.sendKeys(address);
		cityField.sendKeys(city);
		postalField.sendKeys(postal);
		phoneField.sendKeys(phone);
		//addressSubmit.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[@id='addressSubmitLabel']")));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 150);");
		js.executeScript("arguments[0].click();", submit);
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("//button/span[@id='addressSubmitLabel']")).click();

	}

	public void submitAddressDetails() {
		edit.click();
		deliveryMode.click();
		inStorePayment.click();
		placeOrder.click();
	}
//	
//	public class CSVDataReader {
//		public List<String[]> readDataFromCSV(String csvFilePath) throws IOException, CsvException {
//			CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
//			return csvReader.readAll();
//		}
//	}
}
