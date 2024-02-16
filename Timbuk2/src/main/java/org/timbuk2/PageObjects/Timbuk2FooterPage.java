package org.timbuk2.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.timbuk2.AbstractComponents.AbstractComponents;

import java.time.Duration;
import java.util.Set;

public class Timbuk2FooterPage extends AbstractComponents {

	private WebDriver driver;

	@FindBy(xpath = "(//div[@class='logo'])[2]")
	private WebElement footerLogo;

	@FindBy(xpath = "(//a[@title='About Us'])[2]")
	private WebElement aboutUs;

	public Timbuk2FooterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openFooterLogoInNewTab() {
		footerLogo.click();
		String linkUrl = driver.getCurrentUrl();
		((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", linkUrl);
		switchToNewTab();
	}

	public void aboutUs() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
		wait.until(ExpectedConditions.elementToBeClickable(aboutUs));
		aboutUs.click();
	}

	private void switchToNewTab() {
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			driver.switchTo().window(handle);
		}
	}
}
