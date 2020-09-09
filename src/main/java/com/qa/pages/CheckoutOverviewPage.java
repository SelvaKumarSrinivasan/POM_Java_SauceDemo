package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qa.base.TestBase;

import io.qameta.allure.Step;

public class CheckoutOverviewPage extends TestBase {
	public Float verifyItemTotal() {
		List<WebElement> elementPriceList = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		Float totalPrice = 0.f;
		for (WebElement we : elementPriceList) {
			totalPrice = totalPrice + (Float.parseFloat(we.getText().replace("$", "")));
		}
		return totalPrice;
	}

	@Step("Verify and return Total Amount")
	public String verifyActualTotal() {
		return driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();

	}

	@Step("Click order finish")
	public FinishPage clickFinish() {
		driver.findElement(By.xpath("//a[text()='FINISH']")).click();
		return new FinishPage();
	}

}
