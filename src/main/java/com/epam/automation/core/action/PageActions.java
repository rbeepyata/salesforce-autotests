package com.epam.automation.core.action;

import com.epam.automation.core.element.Locator;
import com.epam.automation.core.report.ActionResult;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code PageActions} is the main interface to use for performing various actions in the browser, which in general represents a web browser.
 *  
 * <p> Epam Automation - Core </p>
 * @author Raghunandan_Beepyata
 */
public interface PageActions {

	ActionResult waitForElementToBeVisible(Locator locator, String elementName, long timeOutInSeconds);
	ActionResult waitForElementToBeVisible(Locator locator, String elementName);
	ActionResult waitForElementToBoClickable(Locator locator, String elementName, long timeOutInSeconds);
	ActionResult waitForElementToBoClickable(Locator locator, String elementName);

	ActionResult waitForElementToBeInvisible(Locator locator, String elementName, long timeOutInSeconds);
	ActionResult waitForElementToBeInvisible(Locator locator, String elementName);
	
	ActionResult isDisplayed(Locator locator, String elementName, long timeOutInSeconds);
	ActionResult isDisplayed(Locator locator, String elementName);
	ActionResult isEnabled(Locator locator, String elementName, long timeOutInSeconds);
	ActionResult isEnabled(Locator locator, String elementName);
	ActionResult isSelected(Locator locator, String elementName, long timeOutInSeconds);
	ActionResult isSelected(Locator locator, String elementName);
	
	ActionResult click(Locator locator, String elementName) throws TestFailedException;
	ActionResult type(Locator locator, String valueToType, String elementName) throws TestFailedException;
	ActionResult typePassword(Locator locator, String valueToType, String elementName) throws TestFailedException;
	ActionResult clear(Locator locator, String elementName) throws TestFailedException;
	String getText(Locator locator, String elementName);
	ActionResult verifyText(Locator locator, String elementName, String expectedText);
	ActionResult selectByVisibleText(Locator locator, String valueToSelect, String elementName) throws TestFailedException;
	boolean moveToElement(Locator locator, String elementName);
	boolean scrollIntoView(Locator locator, String elementName);
	boolean highlight(Locator locator, String elementName);
	void setHighlightElement(boolean trueOrFalse);
	void setAbortTestCaseOnFailure(boolean trueOrFalse);
	String getAttribute(Locator locator, String elementName, String attributeName);
	
	void get(String url);
	String getCurrentURL();
	String saveCurrentWindowHandle();
	String switchToNewWindow();
	void switchToMainWindow();
	void maximizeWindow();
	void refresh();
	void closeCurrentWindow();
	void closeAllWindows();
	void switchToFrame(String frameName);
	void acceptIfAlertIsPresent();

	Object getTool();
	void waitUnconditionallyFor(long seconds);
	
}