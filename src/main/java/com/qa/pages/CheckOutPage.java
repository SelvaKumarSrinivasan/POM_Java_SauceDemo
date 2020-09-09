package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class CheckOutPage extends TestBase {

	WebElement txtFirstName = driver.findElement(By.id("first-name"));
	WebElement txtLastName = driver.findElement(By.id("last-name"));
	WebElement txtPostalCode = driver.findElement(By.id("postal-code"));
	WebElement btnCancel = driver.findElement(By.xpath("//a[text()='CANCEL']"));
	WebElement btnSubmit = driver.findElement(By.xpath("//input[@type='submit']"));

	@Step("Verify fields in the checkout page")
	public boolean verifyCheckoutDataFields() {
		return (txtFirstName.isDisplayed() && txtLastName.isDisplayed() && txtPostalCode.isDisplayed());
	}

	@Step("Enter data in checkout page fields")
	public void enterDataInFields(String firstName, String lastName, String postalCode) {
		txtFirstName.sendKeys(firstName);
		txtLastName.sendKeys(lastName);
		txtPostalCode.sendKeys(postalCode);
	}

	@Step("Click cancel in checkout page")
	public CartPage clickCancel() {
		btnCancel.click();
		return new CartPage();
	}

	@Step("Click submit in checkout page")
	public CheckoutOverviewPage clickSubmit() {
		btnSubmit.click();
		return new CheckoutOverviewPage();
	}

	@Step("Verify error message")
	public boolean verifyErrorMessage() {
		return driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
	}
}
