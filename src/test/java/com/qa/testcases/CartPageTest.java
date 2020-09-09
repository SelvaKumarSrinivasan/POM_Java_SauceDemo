package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CartPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;

	public CartPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		homePage.verifyAddProductsToCart();
		cartPage = homePage.clickProductCart();
	}

	@Test(priority = 1, description = "Verify whether user is able to add and remove products from cart")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify whether user is able to add and remove products from cart")
	public void TC10_Standard_User_Login_Add_Products_To_Cart_And_Remove() {
		String initalNumberofProducts = cartPage.number_Of_Products_In_Cart();
		cartPage.remove_Product_From_Cart();
		String countAfterRemovingProduct = cartPage.number_Of_Products_In_Cart();
		Assert.assertEquals(Integer.parseInt(countAfterRemovingProduct), Integer.parseInt(initalNumberofProducts) - 1,
				"Mismatch in number of products");
	}

	@Test(priority = 2, description = "Verify whether user is able to continue shopping from the cart page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify whether user is able to continue shopping from the cart page")
	public void TC11_Standard_User_Login_Product_Details_Page_Your_Cart_Functionality_Continue_Shopping() {
		cartPage.remove_Product_From_Cart();
		cartPage.continueShopping();
		homePage.addToCart();
		cartPage = homePage.clickProductCart();
		Assert.assertTrue(cartPage.verifyCheckoutLink());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
