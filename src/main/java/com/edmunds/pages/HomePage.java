package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.Link;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class HomePage extends PageBase {

	public Button buttonUsername = ElementFactory.getButton("Username", this);
	public Element menuitemSetup = ElementFactory.getElement("Setup menuitem", this);
	public Element menuitemDeveloperConsole = ElementFactory.getElement("Developer Console menuitem", this);
	public Element menuitemLogout = ElementFactory.getElement("Logout menuitem", this);
	public Element tabDealerships = ElementFactory.getElement("Dealerships tab", this);
	public Element tabOpportunities = ElementFactory.getElement("Opportunities tab", this);
	public Element tabAllTabs = ElementFactory.getElement("+(All Tabs) tab", this);
	public Link linkAriaIntegration = ElementFactory.getLink("Aria Integration", this);
	

	public void logout() {
		buttonUsername.click();
		menuitemLogout.click();
		new LoginPage().textboxUsername.waitForItToBeVisible();
	}

	public void navigateToDealerShips() {
		tabDealerships.click();
	}

	public void navigateToOpportunities() {
		tabOpportunities.click();
	}

	public void navigateToAllTabs() {
		tabAllTabs.click();
	}

	public void navigateToAriaIntegrationPage() {
		navigateToAllTabs();
		linkAriaIntegration.click();
	}

	public void navigateToSetupPage() {
		buttonUsername.click();
		menuitemSetup.click();
	}

}
