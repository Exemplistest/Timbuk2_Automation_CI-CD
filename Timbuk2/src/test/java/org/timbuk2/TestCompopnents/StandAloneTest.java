package org.timbuk2.TestCompopnents;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public StandAloneTest() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		new Actions(driver);
		js = (JavascriptExecutor) driver;

	}

	public void goTo() {
		driver.get("https://local.timbuk2.com");
		driver.findElement(By.xpath("//button[@id='details-button']")).click();
		driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
	}

	public void discountCode() {
		driver.findElement(By.cssSelector("[name='CapCodeInput']")).sendKeys("VeniceBeach-Retail-DTA935E9ED-347641589");
		driver.findElement(By.cssSelector("[class='btn btn--primary popup2Submit']")).click();
		driver.findElement(By.cssSelector("[class='btn btn--primary js-accept-cookies']")).click();
	}

	public void selectProduct() {
		driver.findElement(By.xpath("(//li[@class='cmp-navigation__item cmp-navigation__item--level-0'])[4]")).click();
	}

	public void getProductList() throws InterruptedException {
		List<String> targetProducts = Arrays.asList("Custom Division Laptop Backpack",
				"Custom Alcatraz Laptop Backpack");
		int i = 0;
		for (String targetProduct : targetProducts) {
			List<WebElement> element = driver.findElements(By.xpath("//div[@class='productlisttiles__item']"));
			List<WebElement> matchingProducts = element.stream()
					.filter(product -> product.getText().equalsIgnoreCase(targetProduct)).collect(Collectors.toList());

			if (!matchingProducts.isEmpty()) {
				WebElement productToClick = matchingProducts.get(0);
				productToClick.click();
				Thread.sleep(1000);
				WebElement addToCartButton = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add To Cart')]")));
				js.executeScript("arguments[0].click();", addToCartButton);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Proceed to Checkout')]")))
						.click();
				if (i == 0) {
					driver.findElement(By.cssSelector("a[class='btn btn--continue-shopping']:nth-child(1)")).click();
				} else {
					break;
				}
			} else {
				System.out.println("Product not found: " + targetProduct);
			}
			i++;
		}
	}

	public void secureCheckOut() {
		driver.findElement(By.xpath("//span[contains(.,'Secure Checkout')]")).click();
		driver.findElement(By.xpath("//input[@class='kisokPasswordInput']")).sendKeys("password");
		driver.findElement(By.xpath("//button[contains(.,'Login')]")).click();
		driver.findElement(By.cssSelector("input[id='Address']")).click();
	}

	public void reviewAndSubmitOrder() throws IOException, CsvException {

		CSVReader userAddress = new CSVReader(
				new FileReader("C:\\Users\\Nagendra Vellanki\\Desktop\\Timbuk2\\timbuk2address.csv"));
		List<String[]> csvData = userAddress.readAll();
		userAddress.close();
		// for (String[] row : csvData) {
		for (int i = 1; i < csvData.size(); i++) {
			String[] row = csvData.get(i);
			String firstName = row[0];
			String lastName = row[1];
			String email = row[2];
			String address = row[3];
			String city = row[4];
			String postal = row[5];
			String phone = row[6];
			// Process each row of the CSV file
			for (String cell : row) {
				// Process each cell of the current row
				System.out.println(cell);
			}

			WebElement countryField = wait.until(ExpectedConditions.elementToBeClickable(By.id("address.country")));
			countryField.click();
			WebElement canadaOption = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//select[@id='address.country']/option[text()='Canada']")));
			canadaOption.click();
			WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(By.id("address.region")));
			stateField.click();
			WebElement ontarioOption = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//select[@id='address.region']/option[text()='Ontario']")));
			ontarioOption.click();
			WebElement firstNameField = driver.findElement(By.id("address.firstName"));
			firstNameField.sendKeys(firstName);
			WebElement lastNameField = driver.findElement(By.id("address.surname"));
			lastNameField.sendKeys(lastName);
			WebElement emailField = driver.findElement(By.id("address.email"));
			emailField.sendKeys(email);
			WebElement addressField = driver.findElement(By.id("address.line1"));
			addressField.sendKeys(address);
			WebElement cityField = driver.findElement(By.id("address.townCity"));
			cityField.sendKeys(city);
			WebElement postalField = driver.findElement(By.id("address.postcode"));
			postalField.sendKeys(postal);
			WebElement phoneField = driver.findElement(By.id("address.phone"));
			phoneField.sendKeys(phone);
			driver.findElement(By.id("addressSubmit")).click();
			driver.findElement(By.xpath("(//span[@class='checkout-title-edit'])[2]")).click();
			driver.findElement(By.xpath("//button[@id='deliveryMethodSubmit']")).click();
			driver.findElement(By.xpath("//a[@class='btn btn--primary btn-block submit_silentOrderPostForm checkout-next']")).click();
			driver.findElement(By.xpath("(//button[@id='placeOrder'])[1]")).click();
		}	
	}

	public void execuatables() throws InterruptedException, IOException, CsvException {
		goTo();
		discountCode();
		selectProduct();
		getProductList();
		secureCheckOut();
		reviewAndSubmitOrder();
	}

	public static void main(String[] args) throws InterruptedException, IOException, CsvException {
		StandAloneTest sat = new StandAloneTest();
		sat.execuatables();
	}

}
