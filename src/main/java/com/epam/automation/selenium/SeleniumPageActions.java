package com.epam.automation.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.action.PageActions;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.report.ActionResult;
import com.epam.automation.core.report.Screenshot;
import com.epam.automation.core.report.ScreenshotPolicy;
import com.epam.automation.core.report.TestReporter;
import com.epam.automation.utilities.FileUtil;
import com.epam.automation.utilities.LocatorUtil;
import com.epam.automation.utilities.ScreenshotUtil;
import com.epam.automation.utilities.TextUtil;

//TODO: JavaDocs and comments
/**
 * This class is an implementation of <code>IPageActions</code> interface for
 * Selenium WebDriver actions.
 * 
 * <p>
 * <i>Epam Automation - Selenium</i>
 * </p>
 * 
 * @author Raghunandan Beepyata
 * 
 */

public class SeleniumPageActions implements PageActions {

	private long EXPLICIT_WAIT_TIME = ConfigProperties.EXPLICIT_WAIT_TIME;
	private boolean HIGHLIGHT_ELEMENT = ConfigProperties.HIGHLIGHT_ELEMENT;
	private ScreenshotPolicy screenshotPolicy = null;
	private String screenshotsLocation = null;
	private SeleniumWrapper selenium = null;

	public SeleniumPageActions(WebDriver driver, TestReporter testReporter) {
		selenium = new SeleniumWrapper(driver, EXPLICIT_WAIT_TIME, HIGHLIGHT_ELEMENT);
		setScreenshotPolicy(testReporter.getScreenshotPolicy());
		if(ConfigProperties.RETAIN_OLD_RESULTS) {
			screenshotsLocation = ConfigProperties.TEST_REPORT_FILE_LOCATION 
					+ "//" + "screenshots" +"-"+ TextUtil.getCurrentDateTimeStamp("-");
		} else {
			screenshotsLocation = ConfigProperties.TEST_REPORT_FILE_LOCATION + "//" + "screenshots";
		}
		FileUtil.makeDirectories(screenshotsLocation);
	}

	public ScreenshotPolicy getScreenshotPolicy() {
		return screenshotPolicy;
	}

	public void setScreenshotPolicy(ScreenshotPolicy screenshotPolicy) {
		this.screenshotPolicy = screenshotPolicy;
	}

	private String generateScreenshotPath(String action, String elementName) {
		return this.screenshotsLocation + "//" + action + elementName.replace(" ", "") + TextUtil.getCurrentTimeStamp()
				+ ".png";
	}

	private ActionResult getActionResult(boolean status, String elementName) {
		if ((status == true && screenshotPolicy.isScreenshotNeededForPassedStep())
				|| (status == false && screenshotPolicy.isScreenshotNeededForFailedStep())) {
			String actionName = Thread.currentThread().getStackTrace()[2].getMethodName();
			Screenshot screenshot = ScreenshotUtil.takeScreenshot(selenium.getDriver(),
					generateScreenshotPath(actionName, elementName));
			return new ActionResult(status, screenshot);
		} else {
			return new ActionResult(status);
		}
	}

	By getBy(Locator locator) {
		return LocatorUtil.getSeleniumLocator(locator.getLocatorType(), locator.getLocatorValue());
	}

	/*
	 * ########################################### PageActions Implementations	####################################
	 */

	@Override
	public ActionResult waitForElementToBeVisible(Locator locator, String elementName, long timeOutInSeconds) {
		boolean status = selenium.waitForElementToBeVisible(getBy(locator), timeOutInSeconds);
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult waitForElementToBeVisible(Locator locator, String elementName) {
		return waitForElementToBeVisible(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult waitForElementToBoClickable(Locator locator, String elementName, long timeOutInSeconds) {
		boolean status = selenium.waitForElementToBoClickable(getBy(locator), timeOutInSeconds);
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult waitForElementToBoClickable(Locator locator, String elementName) {
		return waitForElementToBoClickable(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult waitForElementToBeInvisible(Locator locator, String elementName, long timeOutInSeconds) {
		boolean status = selenium.waitForElementToBeInvisible(getBy(locator), timeOutInSeconds);
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult waitForElementToBeInvisible(Locator locator, String elementName) {
		return waitForElementToBeInvisible(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult isDisplayed(Locator locator, String elementName, long timeOutInSeconds) {
		boolean status = selenium.isDisplayed(getBy(locator), timeOutInSeconds);
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult isDisplayed(Locator locator, String elementName) {
		return isDisplayed(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult isEnabled(Locator locator, String elementName, long timeOutInSeconds) {
		boolean status = selenium.isEnabled(getBy(locator), timeOutInSeconds);
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult isEnabled(Locator locator, String elementName) {
		return isEnabled(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult isSelected(Locator locator, String elementName, long timeOutInSeconds) {
		boolean status = selenium.isSelected(getBy(locator), timeOutInSeconds);
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult isSelected(Locator locator, String elementName) {
		return isSelected(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult click(Locator locator, String elementName) {
		boolean status = selenium.click(getBy(locator));
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult type(Locator locator, String valueToType, String elementName) {
		boolean status = selenium.type(getBy(locator), valueToType);
		status = status || false;
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult typePassword(Locator locator, String valueToType, String elementName) {
		boolean status = selenium.type(getBy(locator), valueToType);
		status = status || false;
		return getActionResult(status, elementName);
	}

	@Override
	public ActionResult clear(Locator locator, String elementName) {
		boolean status = selenium.clear(getBy(locator));
		return getActionResult(status, elementName);
	}

	@Override
	public String getText(Locator locator, String elementName) {
		return selenium.getText(getBy(locator));
	}

	@Override
	public ActionResult verifyText(Locator locator, String elementName, String expectedText) {
		boolean status = selenium.verifyText(getBy(locator), expectedText);
		return getActionResult(status, elementName);
	}

	@Override
	public boolean highlight(Locator locator, String elementName) {
		return selenium.highlight(getBy(locator));
	}

	@Override
	public ActionResult selectByVisibleText(Locator locator, String valueToSelect, String elementName) {
		boolean status = selenium.selectByVisibleText(getBy(locator), valueToSelect);
		return getActionResult(status, elementName);
	}

	@Override
	public String saveCurrentWindowHandle() {
		return selenium.saveCurrentWindowHandle();
	}

	@Override
	public String switchToNewWindow() {
		return selenium.switchToNewWindow();
	}

	@Override
	public void switchToFrame(String frameName) {
		selenium.switchToFrame(frameName);
	}

	@Override
	public void switchToMainWindow() {
		selenium.switchToMainWindow();
	}

	@Override
	public boolean moveToElement(Locator locator, String elementName) {
		return selenium.moveToElement(getBy(locator), elementName);
	}

	@Override
	public String getCurrentURL() {
		return selenium.getCurrentURL();
	}

	@Override
	public void closeCurrentWindow() {
		selenium.closeCurrentWindow();
	}

	@Override
	public WebDriver getTool() {
		return selenium.getDriver();
	}

	@Override
	public void acceptIfAlertIsPresent() {
		selenium.acceptIfAlertIsPresent();
	}

	@Override
	public void waitUnconditionallyFor(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getAttribute(Locator locator, String elementName, String attributeName) {
		By by = LocatorUtil.getSeleniumLocator(locator);
		return selenium.getDriver().findElement(by).getAttribute(attributeName);
	}

	@Override
	public void get(String url) {
		selenium.get(url);
	}

	@Override
	public void closeAllWindows() {
		selenium.closeAllWindows();
	}

	@Override
	public void maximizeWindow() {
		selenium.maximizeWindow();
	}

	@Override
	public void refresh() {
		selenium.refresh();
	}

	@Override
	public void setHighlightElement(boolean trueOrFalse) {
			HIGHLIGHT_ELEMENT=trueOrFalse;
	}

	@Override
	public void setAbortTestCaseOnFailure(boolean trueOrFalse) {
		// This is taken care in CorePageActions. No implementation needed here.
	}

	@Override
	public boolean scrollIntoView(Locator locator, String elementName) {
		return selenium.scrollIntoView(getBy(locator));
	}


}
