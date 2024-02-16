package org.timbuk2.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.timbuk2.AbstractComponents.AbstractComponents;

public class Timbuk2ProductPage extends AbstractComponents {
	WebDriver driver;

	public Timbuk2ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//li[@class='cmp-navigation__item cmp-navigation__item--level-0'])[4]")
	WebElement selectProduct;

	public Timbuk2GetProducts selectProduct() {
		selectProduct.click();
		return new Timbuk2GetProducts(driver);
	}
}
