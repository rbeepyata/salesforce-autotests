package com.epam.automation.selenium;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//TODO: JavaDocs and comments
/**
 * This class is a wrapper over Selenium WebDriver actions.
 * 
 * <p>
 * <i>Epam Automation - Selenium</i>
 * </p>
 * 
 * @author Raghunandan Beepyata
 * 
 */

public class SeleniumWrapper {

	private static final Logger logger = Logger.getLogger(SeleniumWrapper.class);
	private WebDriver driver;
	private long EXPLICIT_WAIT_TIME;
	private boolean HIGHLIGHT_ELEMENT;
	private String mainWindowHandle = null;

	public SeleniumWrapper(WebDriver driver, long waitTime, boolean highlightElement){
		this.driver = driver;
		this.EXPLICIT_WAIT_TIME = waitTime;
		this.HIGHLIGHT_ELEMENT = highlightElement;
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
	public boolean isElementPresent(By by) {
		boolean status;
		if (status = (!driver.findElements(by).isEmpty())) {
			logger.info("Element is present in the DOM." );
		} else {
			logger.info("Element is NOT present in the DOM." );
		}
		return status;
	}
	
	public boolean waitForElementToBePresent(By by) {
		boolean isElementPresent = false; 
		try {
			new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
					.until(ExpectedConditions.presenceOfElementLocated(by));
			isElementPresent = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return isElementPresent;
	}
	
	public boolean waitForElementToBeVisible(By by, long timeOutInSeconds) {
		boolean status = false;
		try {
			new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.visibilityOfElementLocated(by));
			driver.findElement(by);
			status = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public boolean waitForElementToBeVisible(By by) {
		return waitForElementToBeVisible(by, EXPLICIT_WAIT_TIME);
	}

	public boolean waitForElementToBoClickable(By by, long timeOutInSeconds) {
		boolean status = false;
		try {
			new WebDriverWait(driver, timeOutInSeconds)
			 	.until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by);
			status = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public boolean waitForElementToBoClickable(By locator) {
		return waitForElementToBoClickable(locator, EXPLICIT_WAIT_TIME);
	}

	public boolean waitForElementToBeInvisible(By by, long timeOutInSeconds) {
		boolean status = false;
		try {
			status = new WebDriverWait(driver, timeOutInSeconds)
				.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public boolean waitForElementToBeInvisible(By by) {
		return waitForElementToBeInvisible(by, EXPLICIT_WAIT_TIME);
	}

	public boolean isDisplayed(By by, long timeOutInSeconds) {
		boolean status = false;
		try {
			status = new WebDriverWait(driver, timeOutInSeconds)
						.until(ExpectedConditions.visibilityOfElementLocated(by)).isDisplayed();
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
		} catch (Exception e) {
			logger.debug("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public boolean isDisplayed(By by, String elementName) {
		return isDisplayed(by, EXPLICIT_WAIT_TIME);
	}

	public boolean isEnabled(By by, long timeOutInSeconds) {
		boolean isEnabled = false;
		try {
			isEnabled = new WebDriverWait(driver, timeOutInSeconds)
								.until(ExpectedConditions.elementToBeClickable(by)).isEnabled();
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return isEnabled;
	}

	public boolean isEnabled(By by) {
		return isEnabled(by, EXPLICIT_WAIT_TIME);
	}

	public boolean isSelected(By by, long timeOutInSeconds) {
		boolean isSelected = false;
		try {
			WebElement we = new WebDriverWait(driver, timeOutInSeconds)
								.until(ExpectedConditions.elementToBeClickable(by));
			isSelected = we.isSelected();
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return isSelected;
	}

	public boolean isSelected(By by, String elementName) {
		return isSelected(by, EXPLICIT_WAIT_TIME);
	}

	public boolean click(By by) {
		boolean status = false;
		try {
			new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
				.until(ExpectedConditions.elementToBeClickable(by));
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
			driver.findElement(by).click();
			status = true;
		} catch(WebDriverException wde){
			WebElement element = driver.findElement(by);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            status = true;
		} catch (Exception e) {
			status = false;
			logger.debug("Exception Details: " + e.getMessage());
			throw e;
		}
		return status;
	}


	public boolean type(By by, String valueToType) {
		boolean status = false;
		try {
			new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
			 	.until(ExpectedConditions.elementToBeClickable(by));
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(valueToType);
			status = true;
		} catch (Exception e) {
			logger.debug("Exception Details: " + e.getMessage());
			throw e;
		}
		return status;
	}

	public boolean clear(By by) {
		boolean status = false;
		try {
			new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
				.until(ExpectedConditions.elementToBeClickable(by));
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
			driver.findElement(by).clear();
			status = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public String getText(By by) {
		String text = null;
		try {
			WebElement we = new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
								.until(ExpectedConditions.visibilityOfElementLocated(by));
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
			text = we.getText();
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		if (null==text) {
			logger.info("No innertext.");
			return text;
		} else {
			return text;
		}
	}
	
	public boolean verifyText(By by, String expectedText) {
		boolean status = false;
		String actualText = null;
		try {
			actualText = getText(by);
			if (actualText!=null) {
				status = (actualText.equals(expectedText)) ? true : false;
			} else {
				logger.info("Could not get the actual text.");
			}
		} catch (Exception e) {
			status = false;
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public boolean highlight(By by) {
		boolean status = false;
		try {
			WebElement we = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px groove yellow'", we);
			status = true;
		} catch (Exception e) {
			status = false;
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}

	public void waitUnconditionallyFor(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.info("Exception Details: " + e.getMessage());
		}
	}
	
	public boolean selectByVisibleText(By locator, String valueToSelect) {
		boolean status = false;
		try {
			new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
				.until(ExpectedConditions.elementToBeClickable(locator));
			if (HIGHLIGHT_ELEMENT)
				highlight(locator);
			new Select(driver.findElement(locator)).selectByVisibleText(valueToSelect);
			status = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
			throw e;
		}
		return status;
	}

	public boolean moveToElement(By locator, String elementName) {
		boolean status = false;
		try {
			Actions actions = new Actions(driver);
			WebElement element = new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
									.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if (HIGHLIGHT_ELEMENT)
				highlight(locator);
			actions.moveToElement(element).build().perform();
			status = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}
	
	public boolean scrollIntoView(By by) {
		boolean status = false;
		try {
			WebElement we = new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
								.until(ExpectedConditions.visibilityOfElementLocated(by));
			if (HIGHLIGHT_ELEMENT)
				highlight(by);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", we); 
			status = true;
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return status;
	}
	
	public String saveCurrentWindowHandle() {
		return mainWindowHandle = driver.getWindowHandle();
	}

	public String switchToNewWindow() {
		String newWindowHandle = null;
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(mainWindowHandle)) {
				newWindowHandle = window;
				driver.switchTo().window(window);
			}
		}
		return newWindowHandle;
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().defaultContent();
		new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
			.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
		driver.switchTo().frame(frameName);
	}

	public void switchToMainWindow() {
		driver.switchTo().window(mainWindowHandle);
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void closeCurrentWindow() {
		driver.close();
	}

	public void acceptIfAlertIsPresent() {
		Alert alert = null;
		try {
			alert = new WebDriverWait(driver, EXPLICIT_WAIT_TIME)
					.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		if (alert != null) {
			driver.switchTo().alert().accept();
		} else {
			logger.info("Alert missing.");
		}
	}

	public String getAttribute(By by, String attributeName) {
		return driver.findElement(by).getAttribute(attributeName);
	}

	public void get(String url) {
		driver.get(url);
	}

	public void closeAllWindows() {
		driver.quit();
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	
	
	
}
