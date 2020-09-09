package com.qa.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homepage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@Test(priority = 1, description = "Verify valid user login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify valid user login")
	public void TC1_Login_Page_Standard_User_Login() throws InterruptedException {
		homepage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		assertTrue(homepage.verifyLoggedIn(), "Login failed");
	}

	@Test(priority = 2, description = "Verify Invalid user login")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description : Verify Invalid user login")
	public void TC2_Login_Page_Locked_Out_User_Login() {
		assertTrue(loginPage.invalidLogin(prop.getProperty("lockedUser"), prop.getProperty("password")),
				"Login Successful");
	}

	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
