package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class CartPage extends TestBase {

	List<WebElement> removeBtnList = driver.findElements(By.xpath("//button[@class='btn_secondary cart_button']"));
	WebElement numberofProducts = driver
			.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']"));
	WebElement lnkContinueShopping = driver.findElement(By.xpath("//a[text()='Continue Shopping']"));
	WebElement lnkCheckout = driver.findElement(By.xpath("//a[text()='CHECKOUT']"));

	@Step("Return number of products in cart")
	public String number_Of_Products_In_Cart() {
		return numberofProducts.getText();
	}

	@Step("Remove product from cart")
	public void remove_Product_From_Cart() {
		removeBtnList.get(0).click();
	}

	@Step("Click on continue shopping")
	public void continueShopping() {
		lnkContinueShopping.click();
	}

	@Step("Click on checkout")
	public CheckOutPage clickCheckout() {
		lnkCheckout.click();
		return new CheckOutPage();
	}

	@Step("Verify checkout link")
	public boolean verifyCheckoutLink() {
		return driver.findElement(By.xpath("//a[text()='CHECKOUT']")).isDisplayed();
	}

}
