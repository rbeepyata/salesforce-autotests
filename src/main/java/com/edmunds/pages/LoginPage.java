package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class LoginPage extends PageBase {
	
	public TextBox textboxUsername = ElementFactory.getTextBox("Username", this);
	public TextBox textboxPassword = ElementFactory.getTextBox("Password", this);
	public Button buttonLogin = ElementFactory.getButton("Login", this);

	public void login(String username, String password) {
		textboxUsername.type(username);
		textboxPassword.typePassword(password);
		buttonLogin.click();
	}
}
