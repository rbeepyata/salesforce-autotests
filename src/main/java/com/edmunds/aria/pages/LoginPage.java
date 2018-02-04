package com.edmunds.aria.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;
import com.epam.automation.exception.TestFailedException;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class LoginPage extends PageBase {

	Element txtUsername = ElementFactory.getElement("Username textfield", 
			Locator.id("username"));
	Element txtPassword = ElementFactory.getElement("Password textfield", Locator.id("password"));
	Element btnLogin = ElementFactory.getElement("Login button", Locator.cssSelector("input[value='Login']"));

	public void login(String username, String password) throws TestFailedException {
		txtUsername.type(username);
		txtPassword.type(password);
		btnLogin.click();
	}

}
