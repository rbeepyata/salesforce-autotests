package com.epam.automation.core.action;

import org.apache.log4j.Logger;

import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.report.ActionResult;
import com.epam.automation.core.report.TestReporter;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code CorePageActions} is the concrete implementation of the main
 * interface {@code PageActions}. The class, however, is intended to wrap an
 * action with appropriate logging and reporting, and doesn't have contain any
 * code that is tool specific.
 * <p>
 * This class delegates the task of performing an action to an object of tool
 * specific implementation of {@code PageActions} interface. This class is
 * initialized by providing another concrete implementation of the
 * {@code PageActions} interface. For instance, SeleniumPageActions is the
 * implementation of the {@code PageActions} interface that provides Selenium
 * specific code to perform actions.
 * 
 * <p>
 * Epam Automation - Core
 * </p>
 * 
 * @author Raghunandan Beepyata
 * 
 */

public class CorePageActions implements PageActions {

	private static final Logger logger = Logger.getLogger(CorePageActions.class);
	private static PageActions toolSpecificActions;
	public static TestReporter testReporter = null;
	private final long EXPLICIT_WAIT_TIME = ConfigProperties.EXPLICIT_WAIT_TIME;
	private boolean abortTestCaseOnError = true;

	private static final CorePageActions PAGE_ACTIONS = new CorePageActions();

	private CorePageActions() {
	}

	public static CorePageActions getInstance() {
		return PAGE_ACTIONS;
	}

	public static void initCorePageActions(PageActions toolSpecificActions, TestReporter testReporter) {
		CorePageActions.toolSpecificActions = toolSpecificActions;
		CorePageActions.testReporter = testReporter;
	}

	@Override
	public ActionResult waitForElementToBeVisible(Locator locator, String elementName, long timeOutInSeconds) {
		ActionResult actionResult = null;
		try {
			logger.info("Waiting for visibility of the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.waitForElementToBeVisible(locator, elementName, timeOutInSeconds);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully found that the element: '" + elementName + "' is visible.");
				logger.info(actionResult.getDescription());
				testReporter.info(actionResult);
			} else {
				actionResult.setDescription("Failed to find that the element: '" + elementName + "' is visible.");
				logger.error(actionResult.getDescription());
				testReporter.info(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public ActionResult waitForElementToBeVisible(Locator locator, String elementName) {
		return waitForElementToBeVisible(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult waitForElementToBoClickable(Locator locator, String elementName, long timeOutInSeconds) {
		ActionResult actionResult = null;
		try {
			logger.info("Waiting for the element: '" + elementName + "' to be clickable ...");
			actionResult = toolSpecificActions.waitForElementToBoClickable(locator, elementName, timeOutInSeconds);
			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully found that the element: '" + elementName + "' is clickable.");
				logger.info(actionResult.getDescription());
				testReporter.info(actionResult);
			} else {
				actionResult.setDescription("Failed to find that the element: '" + elementName + "' is clickable.");
				logger.error(actionResult.getDescription());
				testReporter.info(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public ActionResult waitForElementToBoClickable(Locator locator, String elementName) {
		return waitForElementToBoClickable(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult waitForElementToBeInvisible(Locator locator, String elementName, long timeOutInSeconds) {
		ActionResult actionResult = null;
		try {
			logger.info("Waiting for invisibility of the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.waitForElementToBeVisible(locator, elementName, timeOutInSeconds);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully found that the element: '" + elementName + "' is invisible.");
				logger.info(actionResult.getDescription());
				testReporter.info(actionResult);
			} else {
				actionResult.setDescription("Failed to find that the element: '" + elementName + "' is invisible.");
				logger.error(actionResult.getDescription());
				testReporter.info(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public ActionResult waitForElementToBeInvisible(Locator locator, String elementName) {
		return waitForElementToBeInvisible(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult isDisplayed(Locator locator, String elementName, long timeOutInSeconds) {
		ActionResult actionResult = null;
		try {
			logger.info("Verifying the visibility of element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.isDisplayed(locator, elementName);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Element: '" + elementName + "' is visible.");
				logger.info(actionResult.getDescription());
				testReporter.info(actionResult);
			} else {
				actionResult.setDescription("Element: '" + elementName + "' is NOT visible.");
				logger.error(actionResult.getDescription());
				testReporter.info(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public ActionResult isDisplayed(Locator locator, String elementName) {
		return isDisplayed(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult isEnabled(Locator locator, String elementName, long timeOutInSeconds) {
		ActionResult actionResult = null;
		try {
			logger.info("Verifying if the element is clickable: '" + elementName + "' ...");
			actionResult = toolSpecificActions.isEnabled(locator, elementName, timeOutInSeconds);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Element: '" + elementName + "' is clickable.");
				logger.info(actionResult.getDescription());
				testReporter.info(actionResult);
			} else {
				actionResult.setDescription("Element: '" + elementName + "' is NOT clickable.");
				logger.error(actionResult.getDescription());
				testReporter.info(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public ActionResult isEnabled(Locator locator, String elementName) {
		return isEnabled(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult isSelected(Locator locator, String elementName, long timeOutInSeconds) {
		ActionResult actionResult = null;
		try {
			logger.info("Verifying if the element is selected: '" + elementName + "' ...");
			actionResult = toolSpecificActions.isSelected(locator, elementName, timeOutInSeconds);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Element: '" + elementName + "' is selected.");
				logger.info(actionResult.getDescription());
				testReporter.info(actionResult);
			} else {
				actionResult.setDescription("Element: '" + elementName + "' is NOT selected.");
				logger.error(actionResult.getDescription());
				testReporter.info(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public ActionResult isSelected(Locator locator, String elementName) {
		return isSelected(locator, elementName, EXPLICIT_WAIT_TIME);
	}

	@Override
	public ActionResult click(Locator locator, String elementName) throws TestFailedException {
		ActionResult actionResult = null;
		try {
			logger.info("Clicking on the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.click(locator, elementName);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully clicked on the element: '" + elementName + "'.");
				logger.info(actionResult.getDescription());
				testReporter.passTestStep(actionResult);
			} else {
				actionResult.setDescription("Failed to click on the element: '" + elementName + "'.");
				logger.error(actionResult.getDescription());
				testReporter.failTestStep(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
			if (abortTestCaseOnError) {
				throw new TestFailedException();
			}
		}
		return actionResult;
	}

	@Override
	public ActionResult type(Locator locator, String valueToType, String elementName) throws TestFailedException {
		ActionResult actionResult = null;
		try {
			logger.info("Typing in the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.type(locator, valueToType, elementName);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription(
						"Successfully entered the value: '" + valueToType + "' in the element: '" + elementName + "'.");
				logger.info(actionResult.getDescription());
				testReporter.passTestStep(actionResult);
			} else {
				actionResult.setDescription(
						"Failed to enter the value: '" + valueToType + "' in the element: '" + elementName + "'.");
				logger.error(actionResult.getDescription());
				testReporter.failTestStep(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
			if (abortTestCaseOnError) {
				throw new TestFailedException();
			}
		}
		return actionResult;
	}

	@Override
	public ActionResult typePassword(Locator locator, String valueToType, String elementName)
			throws TestFailedException {
		ActionResult actionResult = null;
		try {
			logger.info("Typing password in the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.typePassword(locator, valueToType, elementName);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully entered the password in the element: '" + elementName + "'.");
				logger.info(actionResult.getDescription());
				testReporter.passTestStep(actionResult);
			} else {
				actionResult.setDescription("Failed to enter the password in the element: '" + elementName + "'.");
				logger.error(actionResult.getDescription());
				testReporter.failTestStep(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
			if (abortTestCaseOnError) {
				throw new TestFailedException();
			}
		}
		return actionResult;
	}

	@Override
	public ActionResult clear(Locator locator, String elementName) throws TestFailedException {
		ActionResult actionResult = null;
		try {
			logger.info("Clearing the contents of the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.clear(locator, elementName);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully cleared the contents of the element: '" + elementName + "'.");
				logger.info(actionResult.getDescription());
				testReporter.passTestStep(actionResult);
			} else {
				actionResult.setDescription("Failed to clear the contents of the element: '" + elementName + "'.");
				logger.error(actionResult.getDescription());
				testReporter.failTestStep(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
			if (abortTestCaseOnError) {
				throw new TestFailedException();
			}
		}
		return actionResult;
	}

	@Override
	public String getText(Locator locator, String elementName) {
		String text = null;
		try {
			logger.info("Getting the contents of the element: '" + elementName + "' ...");
			text = toolSpecificActions.getText(locator, elementName);
			if (text != null) {
				logger.info(
						"Successfully got the contents of the element: '" + elementName + "' Contents: " + text + ".");
			} else {
				logger.info("Failed to get the contents of the element: '" + elementName + "'.");
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return text;
	}

	@Override
	public ActionResult verifyText(Locator locator, String elementName, String expectedText) {
		ActionResult actionResult = null;
		String actualText = null;
		try {
			logger.info("Verifying the contents of the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.verifyText(locator, elementName, expectedText);
			if (actionResult.hasReturnObject())
				actualText = (String) actionResult.getReturnObject();
			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully verified the contents of the element: '" + elementName
						+ "' Contents: " + actualText + ".");
				logger.info(actionResult.getDescription());
				testReporter.passTestStep(actionResult);
			} else {
				actionResult.setDescription("Failed to verify the contents of the element: " + "'" + elementName
						+ " Expected: " + expectedText + " Found: " + actualText);
				logger.error(actionResult.getDescription());
				testReporter.failTestStep(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionResult;
	}

	@Override
	public boolean highlight(Locator locator, String elementName) {
		boolean actionSucceeded = false;
		try {
			logger.info("Highlighting the element: '" + elementName + "' ...");
			actionSucceeded = toolSpecificActions.highlight(locator, elementName);
			if (actionSucceeded) {
				logger.debug("Successfully highlighted the element: '" + elementName + "'.");
			} else {
				logger.debug("Failed to highlight the element: '" + elementName + "'.");
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionSucceeded;
	}

	@Override
	public ActionResult selectByVisibleText(Locator locator, String valueToSelect, String elementName)
			throws TestFailedException {
		ActionResult actionResult = null;
		try {
			logger.info("Selecting '" + valueToSelect + "' in the element: '" + elementName + "' ...");
			actionResult = toolSpecificActions.selectByVisibleText(locator, valueToSelect, elementName);

			if (actionResult.getStatus() == true) {
				actionResult.setDescription("Successfully selected the value: '" + valueToSelect + "' in the element: '"
						+ elementName + "'.");
				logger.info(actionResult.getDescription());
				testReporter.passTestStep(actionResult);
			} else {
				actionResult.setDescription(
						"Failed to select the value: '" + valueToSelect + "' in the element: '" + elementName + "'.");
				logger.error(actionResult.getDescription());
				testReporter.failTestStep(actionResult);
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
			if (abortTestCaseOnError) {
				throw new TestFailedException();
			}
		}
		return actionResult;
	}

	@Override
	public boolean moveToElement(Locator locator, String elementName) {
		boolean actionSucceeded = false;
		try {
			logger.info("Moving the mouse to the element: " + "'" + elementName + "'");
			actionSucceeded = toolSpecificActions.moveToElement(locator, elementName);
			if (actionSucceeded) {
				logger.info("Successfully moved the mouse to the middle of the element: " + "'" + elementName + "'");
			} else {
				logger.info("Failed to move to the element: " + "'" + elementName + "'");
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionSucceeded;
	}

	// TODO: Add logging and reporting code

	@Override
	public String getAttribute(Locator locator, String elementName, String attributeName) {
		return toolSpecificActions.getAttribute(locator, elementName, attributeName);
	}

	@Override
	public String saveCurrentWindowHandle() {
		return toolSpecificActions.saveCurrentWindowHandle();
	}

	@Override
	public String switchToNewWindow() {
		return toolSpecificActions.switchToNewWindow();
	}

	@Override
	public void switchToFrame(String frameName) {
		try {
			logger.info("Switching to frame '" + frameName + "' in the window ...");
			toolSpecificActions.switchToFrame(frameName);
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
	}

	@Override
	public void switchToMainWindow() {
		logger.info("Switching to main window...");
		toolSpecificActions.switchToMainWindow();
	}

	@Override
	public String getCurrentURL() {
		return toolSpecificActions.getCurrentURL();
	}

	@Override
	public void closeCurrentWindow() {
		logger.info("Closing the browser...");
		toolSpecificActions.closeCurrentWindow();
	}

	@Override
	public Object getTool() {
		return toolSpecificActions.getTool();

	}

	@Override
	public void acceptIfAlertIsPresent() {
		toolSpecificActions.acceptIfAlertIsPresent();
	}

	public void waitUnconditionallyFor(long seconds) {
		toolSpecificActions.waitUnconditionallyFor(seconds);
	}

	@Override
	public void get(String url) {
		logger.info("Hitting the URL: " + url + " in the browser...");
		toolSpecificActions.get(url);
	}

	@Override
	public void closeAllWindows() {
		logger.info("Closing all browser windows opened by the tool ...");
		toolSpecificActions.closeAllWindows();
	}

	@Override
	public void maximizeWindow() {
		logger.info("Maximizing the browser...");
		toolSpecificActions.maximizeWindow();
	}

	@Override
	public void refresh() {
		logger.info("Refreshing the browser...");
		toolSpecificActions.refresh();

	}

	@Override
	public void setHighlightElement(boolean trueOrFalse) {
		logger.info("Setting highlighting of the elements to " + trueOrFalse + " ...");
		toolSpecificActions.setHighlightElement(trueOrFalse);

	}

	@Override
	public void setAbortTestCaseOnFailure(boolean trueOrFalse) {
		abortTestCaseOnError = trueOrFalse;

	}

	@Override
	public boolean scrollIntoView(Locator locator, String elementName) {
		boolean actionSucceeded = false;
		try {
			logger.info("Scrolling in to view the element: " + "'" + elementName + "'");
			actionSucceeded = toolSpecificActions.scrollIntoView(locator, elementName);
			if (actionSucceeded) {
				logger.info("Successfully scrolled to view the element: " + "'" + elementName + "'");
			} else {
				logger.info("Failed to scroll in to view the element: " + "'" + elementName + "'");
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionSucceeded;
	}

}
