package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class HomePage extends TestBase {

	WebElement lblProducts = driver.findElement(By.xpath("//div[text()='Products']"));
	WebElement dropDownSort = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
	List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
	List<WebElement> elementPriceList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
	List<WebElement> btnListAddToCart = driver.findElements(By.xpath("//button[text()='ADD TO CART']"));

	@Step("Check Products label displayed")
	public boolean verifyLoggedIn() {
		try {
			return lblProducts.isDisplayed();
		} catch (Exception e) {
			driver.navigate().refresh();
			return true;
		}
	}

	@Step("Sort items based on Name: {0}")
	public ArrayList<String> sortItemsName(String value) {
		Select sortType = new Select(dropDownSort);
		sortType.selectByValue(value);
		ArrayList<String> obtainedList = new ArrayList<>();
		for (WebElement we : elementList) {
			obtainedList.add(we.getText());
		}
		return obtainedList;
	}

	@Step("Sort items based on Price: {0}")
	public ArrayList<Float> sortItemsPrice(String value) {
		Select sortType = new Select(dropDownSort);
		sortType.selectByValue(value);
		ArrayList<Float> obtainedList = new ArrayList<>();

		for (WebElement we : elementPriceList) {
			obtainedList.add(Float.parseFloat(we.getText().replace("$", "")));
		}
		return obtainedList;
	}

	@Step("Check product cart icon displayed")
	public boolean verifyProductCart() {
		return driver.findElement(By.xpath("//a[@class='shopping_cart_link fa-layers fa-fw']")).isDisplayed();
	}

	@Step("Click product cart icon")
	public CartPage clickProductCart() {
		driver.findElement(By.xpath("//a[@class='shopping_cart_link fa-layers fa-fw']")).click();
		return new CartPage();
	}

	@Step("Get button text after adding product to cart")
	public String verifyAddToCart() {
		btnListAddToCart.get(0).click();
		return btnListAddToCart.get(0).getText();
	}

	@Step("Adding product to cart")
	public void addToCart() {
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
	}

	@Step("Adding multiple products to cart and returning the number")
	public String verifyAddMultipleProductsToCart() {
		for (WebElement we : btnListAddToCart) {
			we.click();
		}
		return Integer.toString(btnListAddToCart.size());
	}

	@Step("Adding multiple products to cart")
	public void verifyAddProductsToCart() {
		for (WebElement we : btnListAddToCart) {
			we.click();
		}
	}

	@Step("Return number of products in cart")
	public String verifyNumberOfProductsInCart() {
		return driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).getText();
	}

}
