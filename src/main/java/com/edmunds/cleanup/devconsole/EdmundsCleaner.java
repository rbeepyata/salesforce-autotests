package com.edmunds.cleanup.devconsole;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import com.edmunds.pages.HomePage;
import com.edmunds.pages.LoginPage;
import com.edmunds.pages.dev.DeveloperConsole;
import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.PageBase;
import com.epam.automation.exception.TestFailedException;
import com.epam.automation.utilities.LocatorUtil;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

//TODO
public class EdmundsCleaner extends PageBase {

	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	DeveloperConsole developerConsole = new DeveloperConsole();
	
	public void cleanUp(String objectType, String objectName) throws TestFailedException{
		
		
		String apexCode = "list<account> li = [select id from Account where name like '"+objectName+"' ]; system.debug('====='+li); delete li; ";
		
		loginPage.login(ConfigProperties.USERNAME, ConfigProperties.PASSWORD);
		
		actions.saveCurrentWindowHandle();
		
		homePage.buttonUsername.click();
		homePage.menuitemDeveloperConsole.click();
		
		actions.switchToNewWindow();
		
		developerConsole.dropdownDebug.click();
		developerConsole.menuitemOpenExecuteAnonymousWindow.click();
		
		Actions a = new Actions((WebDriver)actions.getTool());
		a.moveToElement(
				((WebDriver)actions.getTool()).findElement(
				LocatorUtil.getSeleniumLocator(developerConsole.txtareaFirstLineOfApexCode.getElementLocator())))
		.click().build().perform();
		
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			robot.keyPress(KeyEvent.VK_DELETE);
			robot.keyRelease(KeyEvent.VK_DELETE);
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
//		actions.waitFor(5);
		
		a.moveToElement(
				((WebDriver)actions.getTool()).findElement(
						LocatorUtil.getSeleniumLocator(developerConsole.txtareaFirstLineOfApexCode.getElementLocator())))
		.click().sendKeys(apexCode).build().perform();
		
//		actions.waitFor(5);
		
		developerConsole.chkboxOpenLog.click();
		developerConsole.btnExecute.click();
		actions.waitUnconditionallyFor(5);
		
		actions.closeCurrentWindow();		
		actions.switchToMainWindow();
		
		homePage.logout();
	}
	
}
