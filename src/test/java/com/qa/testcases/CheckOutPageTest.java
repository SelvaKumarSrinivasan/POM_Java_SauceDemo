package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CartPage;
import com.qa.pages.CheckOutPage;
import com.qa.pages.CheckoutOverviewPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CheckOutPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	CheckoutOverviewPage checkoutOverviewPage;

	public CheckOutPageTest() {
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
	}

	@Test(priority = 1, description = "Verify fields in the checkout page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify fields in the checkout page")
	public void TC12_Standard_User_Login_Cart_Checkout_Your_Info_Page() {
		Assert.assertTrue(checkoutPage.verifyCheckoutDataFields(), "Error in Checkout fields");
	}

	@Test(priority = 2, description = "Verify cancel functionality in the checkout page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify cancel functionality in the checkout page")
	public void TC13_Standard_User_Login_Cart_Checkout_Your_Info_Page_Navigation() {
		checkoutPage.enterDataInFields("Selva", "Srinivasan", "12345");
		cartPage = checkoutPage.clickCancel();
		Assert.assertTrue(cartPage.verifyCheckoutLink(), "Error Navigating to Cart Page");
	}

	@Test(priority = 3, description = "First name field validation in the checkout page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : First name field validation in the checkout page")
	public void TC14_Standard_User_Login_Cart_Checkout_Overview_Page_FirstName_Validation() {
		checkoutPage.enterDataInFields("", "Srinivasan", "12345");
		checkoutOverviewPage = checkoutPage.clickSubmit();
		Assert.assertTrue(checkoutPage.verifyErrorMessage(), "Error filling  delivery address");
	}

	@Test(priority = 4, description = "Last name field validation in the checkout page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Last name field validation in the checkout page")
	public void TC15_Standard_User_Login_Cart_Checkout_Overview_Page_LastName_Validation() {
		checkoutPage.enterDataInFields("Selva", "", "12345");
		checkoutOverviewPage = checkoutPage.clickSubmit();
		Assert.assertTrue(checkoutPage.verifyErrorMessage(), "Error filling  delivery address");

	}

	@Test(priority = 5, description = "Postal code field validation in the checkout page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Postal code field validation in the checkout page")
	public void TC16_Standard_User_Login_Cart_Checkout_Overview_Page_PostalCode_Validation() {
		checkoutPage.enterDataInFields("Selva", "Srinivasan", "");
		checkoutOverviewPage = checkoutPage.clickSubmit();
		Assert.assertTrue(checkoutPage.verifyErrorMessage(), "Error filling  delivery address");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
