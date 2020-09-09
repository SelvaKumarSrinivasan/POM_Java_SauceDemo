package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class FinishPage extends TestBase {
	WebElement elementFinish = driver.findElement(By.xpath("//h2[text()='THANK YOU FOR YOUR ORDER']"));
	WebElement elementMenu = driver.findElement(By.xpath("//button[text()='Open Menu']"));

	@Step("Verify successful order message")
	public boolean validateSuccessfulOrderMessage() {
		return elementFinish.isDisplayed();
	}

	@Step("Click menu button")
	public void clickMenuButton() {
		elementMenu.click();
	}

	@Step("Click logout button")
	public LoginPage clickLogout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
		element.click();
		return new LoginPage();
	}

}
