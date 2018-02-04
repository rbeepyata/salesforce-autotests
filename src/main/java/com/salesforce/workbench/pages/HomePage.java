package com.salesforce.workbench.pages;

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
public class HomePage extends PageBase {
	
	

	Element lnkUserInfo = ElementFactory.getElement("User Info link",
			Locator.id("myUserInfo"));
	Element btnWorkbench = ElementFactory.getElement("workbench button",
			Locator.xpath("	//img[contains(@src, 'workbench')]"));
	Element menuitemLogout = ElementFactory.getElement("Logout menuitem", 
			Locator.xpath("//a[text()='Logout']"));
	
	Element btnData = ElementFactory.getElement("data button", 
			Locator.xpath("//a//*[text()='data']"));
	Element menuitemDelete = ElementFactory.getElement("Delete menuitem", 
			Locator.xpath("//a[text()='Delete']"));
	
	//Methods
	public void navigateToDeletePage() throws TestFailedException{
		btnData.click();
		menuitemDelete.click();
		boolean status = new DeletePage().lblDelete.waitForItToBeVisible();
		if (!status) {
			new LoginPage().refreshIfApplicationError();
		}
	}
	
	public void logout() throws TestFailedException {
		btnWorkbench.click();
		menuitemLogout.click();
		new LoginPage().dropdownEnvironment.waitForItToBeVisible();
	}

}
