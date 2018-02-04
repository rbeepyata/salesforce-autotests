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
public class HomePage extends PageBase {

	public Element lnkLogout = ElementFactory.getElement("Logout link", Locator.xpath("//a[contains(text(),'Log Out')]"));
	
	public void logout() throws TestFailedException {
		lnkLogout.click();
	}

}
