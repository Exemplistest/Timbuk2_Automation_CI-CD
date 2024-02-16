package org.timbuk2.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.timbuk2.AbstractComponents.AbstractComponents;

public class Timbuk2SecureCheckOut extends AbstractComponents {

	WebDriver driver;

	public Timbuk2SecureCheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[contains(.,'Secure Checkout')]")
	WebElement secureCheckOut;

	@FindBy(xpath = "//input[@class='kisokPasswordInput']")
	WebElement kisokPasswordInput;

	@FindBy(xpath = "//button[contains(.,'Login')]")
	WebElement login;

	@FindBy(xpath = "//input[@id='Address']")
	WebElement address;

	By errorMessage = By.xpath("//div[@class='passwordErrorMessage']");

	public void secureCheckOut(String kisokPassword) {
		secureCheckOut.click();
		kisokPasswordInput.sendKeys(kisokPassword);
		login.click();
	}

	public Timbuk2ReviewAndSubmitOrder secureLogin() {
		address.click();
		return new Timbuk2ReviewAndSubmitOrder(driver);
	}

	public String getErrorMessage() {
		String message = waitElementsToVisible(errorMessage).getText();
		return message ;
	}
}
