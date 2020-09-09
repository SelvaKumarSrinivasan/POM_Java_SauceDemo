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

public class FinishPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewPage;
	FinishPage finishPage;

	public FinishPageTest() {
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
		finishPage = checkoutOverviewPage.clickFinish();
	}

	@Test(priority = 1, description = "Verify processing order")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify processing order")
	public void TC18_Standard_User_Login_Cart_Checkout_Finish_Page() {
		Assert.assertTrue(finishPage.validateSuccessfulOrderMessage(), "Error processing order");
	}

	@Test(priority = 2, description = "Verify processing order and log out of the application")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify processing order and log out of the application")
	public void TC19_Standard_User_Login_Cart_Checkout_Finish_Page_Logout() {
		finishPage.clickMenuButton();
		loginPage = finishPage.clickLogout();
		Assert.assertTrue(loginPage.verifyloginPage(), "Error logging out");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
