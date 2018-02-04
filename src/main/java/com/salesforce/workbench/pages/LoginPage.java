package com.salesforce.workbench.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;
import com.epam.automation.exception.TestFailedException;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class LoginPage extends PageBase {

	ListBox dropdownEnvironment = ElementFactory.getListBox("Environment", 
			Locator.id("oauth_env"));
	//Option value = Sandbox
	Element chkboxTermsOfService = ElementFactory.getElement("Terms of Service checkbox", 
			Locator.id("termsAccepted"));
	Element btnLoginWithSalesforce = ElementFactory.getElement("Login with Salesforce button", 
			Locator.id("loginBtn"));
	
	Element txtUsername = ElementFactory.getElement("Username textfield", 
			Locator.id("username"));
	Element txtPassword = ElementFactory.getElement("Password textfield", Locator.id("password"));
	Element btnLogin = ElementFactory.getElement("Login button", Locator.id("Login"));

	
	Element btnData = ElementFactory.getElement("data button", 
			Locator.xpath("//a//*[text()='data']"));
	Element menuitemDelete = ElementFactory.getElement("Delete menuitem", 
			Locator.xpath("//a[text()='Delete']"));
	Element radiobtnSingleRecord = ElementFactory.getElement("Single Record radiobutton", 
			Locator.id("sourceType_singleRecord"));
	Element txtSingleRecord = ElementFactory.getElement("Single Record textfield", 
			Locator.xpath("//*[@id='sourceType_singleRecord']/../../following-sibling::*//input"));
	Element radiobtnFromFile = ElementFactory.getElement("From File radiobutton", 
			Locator.id("sourceType_file"));
	
	Element btnNext = ElementFactory.getElement("Next button", 
			Locator.cssSelector("input[value='Next']"));
	Element btnConfirmDelete = ElementFactory.getElement("Confirm Delete button", 
			Locator.cssSelector("input[value='Confirm Delete']"));
	
	Element lblApplicationError = ElementFactory.getElement("Application error label", 
			Locator.xpath("//*[contains(text(),'Application error')]"));
	
	//Methods
	public void login(String environment,String username, String password) throws TestFailedException {
		//TODO: Without SSO
		dropdownEnvironment.selectByVisibleText(environment);
		chkboxTermsOfService.click();
		btnLoginWithSalesforce.click();
		
		txtUsername.type(username);
		txtPassword.type(password);
		btnLogin.click();
		boolean status = new HomePage().lnkUserInfo.waitForItToBeVisible();
		if (!status) {
			refreshIfApplicationError();
		}
	}
	
	public void refreshIfApplicationError() {
		if (lblApplicationError.isDisplayed()) {
			actions.refresh();
		}
	}

}
