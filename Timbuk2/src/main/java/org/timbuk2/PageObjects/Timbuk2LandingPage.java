package org.timbuk2.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.timbuk2.AbstractComponents.AbstractComponents;

public class Timbuk2LandingPage extends AbstractComponents {

	WebDriver driver;

	public Timbuk2LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[@id='details-button']")
	WebElement advanced;

	@FindBy(xpath = "//a[@id='proceed-link']")
	WebElement proceedtosite;

	@FindBy(css = "[name='CapCodeInput']")
	WebElement capCode;

	@FindBy(css = "[class='btn btn--primary popup2Submit']")
	WebElement popup;

	@FindBy(css = "[class='btn btn--primary js-accept-cookies']")
	WebElement acceptCookies;

	By errorMessage = By.xpath("//p[text()='CAP Discount code is invalid, Please check.']");

	public void goTo() {
		driver.get("https://local.timbuk2.com");
		advanced.click();
		proceedtosite.click();
	}

	public void discountCode(String discountCode) {
		capCode.sendKeys(discountCode);
		popup.click();
	}

	public Timbuk2ProductPage acceptCookies() {
		acceptCookies.click();
		return new Timbuk2ProductPage(driver);
	}

	public Timbuk2FooterPage acceptCookies1() {
		acceptCookies.click();
		return new Timbuk2FooterPage(driver);

	}

	public String getErrorMessage() {
		String message = waitElementsToVisible(errorMessage).getText();
		return message;
	}
}
