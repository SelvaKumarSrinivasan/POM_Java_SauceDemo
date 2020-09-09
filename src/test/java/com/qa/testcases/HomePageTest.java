package com.qa.testcases;

import java.util.ArrayList;
import java.util.Collections;

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

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1, description = "Verify Products Page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify Products Page")
	public void TC2_Verify_Landing_Page() {
		Assert.assertTrue(homePage.verifyLoggedIn());
	}

	@Test(priority = 2, description = "Verify Z to A sorting functionality in Products page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify Z to A sorting functionality in Products page")
	public void TC4_Standard_User_Verify_Sort_Name_Z_to_A() {
		ArrayList<String> obtainedList = homePage.sortItemsName("za");
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
	}

	@Test(priority = 3, description = "Verify A to Z sorting functionality in Products page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify A to Z sorting functionality in Products page")
	public void TC3_Standard_User_Verify_Sort_Name_A_to_Z() {
		ArrayList<String> obtainedList = homePage.sortItemsName("az");
		ArrayList<String> sortedList = new ArrayList<>();
		for (String s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
	}

	@Test(priority = 4, description = "Verify sorting functionality based on price(low to high) in Products page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify sorting functionality based on price(low to high) in Products page")
	public void TC5_Standard_User_Verify_Sort_Price_Low_to_High() {
		ArrayList<Float> obtainedList = homePage.sortItemsPrice("lohi");
		Collections.sort(obtainedList);
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
	}

	@Test(priority = 5, description = "Verify sorting functionality based on price(high to low) in Products page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify sorting functionality based on price(high to low) in Products page")
	public void TC6_Standard_User_Verify_Sort_Price_High_to_Low() {
		ArrayList<Float> obtainedList = homePage.sortItemsPrice("hilo");
		Collections.sort(obtainedList);
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float s : obtainedList) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		Assert.assertTrue(sortedList.equals(obtainedList));
	}

	@Test(priority = 6, description = "Verify whether Cart Icon is displayed in Products page")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description :Verify whether Cart Icon is displayed in Products page")
	public void TC7_Product_Cart_Icon_Displayed() {
		Assert.assertTrue(homePage.verifyProductCart());
	}

	@Test(priority = 7, description = "Verify whether user is able to add products to Cart")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description :Verify whether user is able to add products to Cart")
	public void TC8_Standard_User_Login_Product_Details_Page_Add_to_Cart() {
		Assert.assertEquals(homePage.verifyAddToCart(), "REMOVE");
	}

	@Test(priority = 8, description = "Verify whether user is able to add multiple products to Cart")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description :Verify whether user is able to add multiple products to Cart")
	public void TC9_Standard_User_Login_Product_Details_Page_Add_to_Cart_Multiple_Products_Add() {
		Assert.assertEquals(homePage.verifyAddMultipleProductsToCart(), homePage.verifyNumberOfProductsInCart(),
				"Mismatch in number of Products in the Cart");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
