package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CartPage;
import com.qa.pages.CheckOutPage;
import com.qa.pages.CheckoutOverviewPage;
import com.qa.pages.FinishPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CheckoutOverviewPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewPage;
	FinishPage finishPage;

	public CheckoutOverviewPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.verifyAddProductsToCart();
		cartPage = homePage.clickProductCart();
		checkoutPage = cartPage.clickCheckout();
		checkoutPage.enterDataInFields("Selva", "Srinivasan", "12345");
		checkoutOverviewPage = checkoutPage.clickSubmit();
	}

	@Test(priority = 1, description = "Verify the total amount of the products")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify the total amount of the products")

	public void TC17_Standard_User_Login_Verify_Product_Total() {
		Assert.assertTrue(
				checkoutOverviewPage.verifyActualTotal().contains(checkoutOverviewPage.verifyItemTotal().toString()),
				"There is mismatch in the price");
	}

	@Test(priority = 2, description = "Verify Checkout finish functionality")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify Checkout finish functionality")
	public void TC18_Standard_User_Login_Cart_Checkout_Finish_Page() {
		finishPage = checkoutOverviewPage.clickFinish();
		Assert.assertTrue(finishPage.validateSuccessfulOrderMessage());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
