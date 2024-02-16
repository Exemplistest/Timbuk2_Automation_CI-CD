package org.timbuk2.PageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.timbuk2.AbstractComponents.AbstractComponents;

public class Timbuk2GetProducts extends AbstractComponents {

	WebDriver driver;
	public Timbuk2GetProducts(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='productlisttiles__item']")
	List<WebElement> productlist;
	@FindBy(xpath = "//ul[@class='cart__items-content']/li[2]/div/div[1]")
	List<WebElement> cartList;
	By addToCartButton = By.xpath("//button[contains(text(),'Add To Cart')]");
	By proceedToCheckOut = By.xpath("//span[contains(text(),'Proceed to Checkout')]");
	@FindBy(xpath = "(//button[@class='minicart__product-qty-minus btn btn--round'])[2]")
	WebElement increment;
	@FindBy(xpath = "(//button[@class='minicart__product-qty-minus btn btn--round'])[1]")
	WebElement decrement;
	@FindBy(xpath = "(//button[@class='minicart__product-qty-minus btn btn--round'])[3]")
	WebElement makeZero;
	@FindBy(xpath = "(//button[contains(text(),'REMOVE')])[2]")
	WebElement remove;
	@FindBy(xpath = "(//button[@class='btn btn--round add'])[1]")
	WebElement cartProductIncrement;
	@FindBy(xpath = "(//button[@class='btn btn--round subtract'])[1]")
	WebElement cartProductDecrement;
//	@FindBy(xpath = "(//button[@class='btn btn--round subtract'])[2]")
//	WebElement makeCartProductZero;
//	By makeCartProductZero = By.xpath("(//button[@class='btn btn--round subtract'])[2]");
//	@FindBy(xpath = "(//button[@class='btn btn--round subtract'])[3]")
//	WebElement removeCartProduct;

	public Timbuk2SecureCheckOut getProductList() throws InterruptedException {
		int i = 0;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<String> targetProducts = Arrays.asList("Custom Division Laptop Backpack",
				"Custom Alcatraz Laptop Backpack");
		for (String targetProduct : targetProducts) {
			List<WebElement> matchingProducts = productlist.stream()
					.filter(product -> product.getText().equalsIgnoreCase(targetProduct)).collect(Collectors.toList());
			if (!matchingProducts.isEmpty()) {
				WebElement productToClick = matchingProducts.get(0);
				productToClick.click();
				Thread.sleep(1000);
				WebElement addTocart = waitElementsToVisible(addToCartButton);
				js.executeScript("arguments[0].click();", addTocart);
				waitElementsToVisible(proceedToCheckOut).click();
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
		return new Timbuk2SecureCheckOut(driver);
	}

	public List<String> getProductTexts() {
		List<String> productTexts = new ArrayList<>();
		for (WebElement product : cartList) {
			// Get the text of each product and add it to the list
			String text = product.getText();
			productTexts.add(text);
		}
		return productTexts;
	}

	public void incrementAndDecrement() throws InterruptedException {
		int i = 0;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<String> targetProducts = Arrays.asList("Custom Division Laptop Backpack",
				"Custom Alcatraz Laptop Backpack", "Custom Tuck Backpack");
		for (String targetProduct : targetProducts) {
			List<WebElement> matchingProducts = productlist.stream()
					.filter(product -> product.getText().equalsIgnoreCase(targetProduct)).collect(Collectors.toList());
			if (!matchingProducts.isEmpty()) {
				WebElement productToClick = matchingProducts.get(0);
				productToClick.click();
				Thread.sleep(1000);
				WebElement addTocart = waitElementsToVisible(addToCartButton);
				js.executeScript("arguments[0].click();", addTocart);
				if (i < 2) {
					waitElementsToVisible(proceedToCheckOut).click();
					driver.findElement(By.cssSelector("a[class='btn btn--continue-shopping']:nth-child(1)")).click();
				} else {
					break;
				}
			}
			i++;
		}
		do {
			Thread.sleep(2000);
			increment.click();
			i++;
		} while (i <= 3);
		do {
			Thread.sleep(2000);
			decrement.click();
			i++;
		} while (i == 1);
		do {
			Thread.sleep(2000);
			makeZero.click();
			i++;
		} while (i == 1);
		remove.click();
	}

	public void cart() throws InterruptedException {
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<String> targetProducts = Arrays.asList("Custom Division Laptop Backpack", "Custom Prospect Backpack",
				"Custom Alcatraz Laptop Backpack");
		for (String targetProduct : targetProducts) {
			List<WebElement> matchingProducts = productlist.stream()
					.filter(product -> product.getText().equalsIgnoreCase(targetProduct)).collect(Collectors.toList());
			if (!matchingProducts.isEmpty()) {
				WebElement productToClick = matchingProducts.get(0);
				productToClick.click();
				Thread.sleep(1000);
				WebElement addTocart = waitElementsToVisible(addToCartButton);
				js.executeScript("arguments[0].click();", addTocart);
				if (i < 2) {
					waitElementsToVisible(proceedToCheckOut).click();
					driver.findElement(By.cssSelector("a[class='btn btn--continue-shopping']:nth-child(1)")).click();
				} else if (i == 2) {
					waitElementsToVisible(proceedToCheckOut).click();
				} else {
					break;
				}
			}
			i++;
		}
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement intialPrice =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='total-entry0']")));
		System.out.println("intialPrice:" + intialPrice.getText());
		do {
			cartProductIncrement.click();
			Thread.sleep(2000);  
			WebElement updatedPrice =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='total-entry0']")));
			System.out.println("updatedPrice:" + updatedPrice.getText());		
			j++;
		} while (j < 2);
		do {
			cartProductDecrement.click();
			Thread.sleep(2000);
			WebElement reducedPrice =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='total-entry0']")));
			System.out.println("reducedPrice:" + reducedPrice.getText());	
			k++;
		} while (k < 1);
		Thread.sleep(2000);
		js.executeScript("window.scroll(0, 500);");
		WebElement makeZero = driver.findElement(By.xpath("(//button[@class='btn btn--round subtract'])[2]"));
		Actions act = new Actions(driver);
		act.moveToElement(makeZero).build().perform();
		driver.findElement(By.xpath("/html/body/main/div[3]/div[2]/div[2]/div[1]/ul[4]/li[5]/div[2]/button")).click();
	}
}
