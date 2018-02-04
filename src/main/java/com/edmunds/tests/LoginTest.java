package com.edmunds.tests;

import org.testng.annotations.Test;

import com.edmunds.EdmundsConfigProperties;
import com.edmunds.EdmundsTestBase;
import com.edmunds.pages.HomePage;
import com.edmunds.pages.LoginPage;
import com.epam.automation.core.action.Assertions;

/**
 * Billing Use Cases
 * Smoke Testing
 *  
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class LoginTest extends EdmundsTestBase {
	
	@Test
	public void loginTest() {
		
		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();

		try {
			// Test Steps
			loginPage.login(EdmundsConfigProperties.USERNAME, EdmundsConfigProperties.PASSWORD);
			
			boolean status = homePage.buttonUsername.isDisplayed();
			Assertions.verify(status, 
					"Successfully logged in", 
					"Failed to log in");
			homePage.logout();
		} catch (Exception e) {
			Assertions.fail("Use case could NOT be completed.");
			e.printStackTrace();
		}
	}

	
}
