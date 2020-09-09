package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase {

	WebElement txtUserName = driver.findElement(By.id("user-name"));
	WebElement txtPassword = driver.findElement(By.id("password"));
	WebElement btnLogin = driver.findElement(By.id("login-button"));
	WebElement logo = driver.findElement(By.xpath("//img[@class='bot_column']"));

	@Step("login in with Username:{0} and Password : {0}")
	public HomePage login(String username, String password) {
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		driver.navigate().refresh();
		return new HomePage();
	}

	@Step("login in with Invalid Username:{0} and Password : {0}")
	public boolean invalidLogin(String username, String password) {
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
		return driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed();
	}

	public boolean verifyloginPage() {
		return logo.isDisplayed();
	}
}