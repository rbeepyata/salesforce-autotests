package com.epam.automation.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.epam.automation.core.Tool;
import com.epam.automation.core.action.PageActions;
import com.epam.automation.core.report.TestReporter;
import com.epam.automation.selenium.SeleniumPageActions;

public class AutomationTools2 {
	
	
	public static WebDriver getWebDriver(String environment, String platform, String browser) {
		WebDriver driver = null;
		
		//Set default values if not initialized.		
		if (null==environment) {
			environment = "local";
		} 
		if (null==platform) {
			platform = "windows";
		}
		if (null==browser) {
			platform = "chrome";
		}
		
		switch (browser.toLowerCase()) {
		case "chrome":
			if (platform.equalsIgnoreCase("windows")) {
				System.setProperty("webdriver.chrome.driver", "lib//drivers//chromedriver.exe");
			} else if (platform.equalsIgnoreCase("non-windows")) {
				System.setProperty("webdriver.chrome.driver", "lib//drivers//linux//chromedriver");
			} else if (platform.equalsIgnoreCase("seleniuim-docker")) {
				String hubURL = "http://0.0.0.0:4444/wd/hub";
				DesiredCapabilities capability = DesiredCapabilities.chrome();
				try {
					driver = new RemoteWebDriver(new URL(hubURL), capability);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			} 
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
		    chromeOptions.addArguments("disable-infobars");
		    Map<String, Object> prefs = new HashMap<String, Object>();
		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
		    chromeOptions.setExperimentalOption("prefs", prefs);
		    
			driver = new ChromeDriver(chromeOptions);

			break;
			
		case "chrome-docker-container":
			String hubURL = "http://0.0.0.0:4444/wd/hub";
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		default:
			driver = new FirefoxDriver();
		}
		return driver;
	}
	
	
	public static Tool getTool(String toolName, String browserName, TestReporter testReporter) {
		return getTool(toolName, "local", "windows", browserName, testReporter);
	}
	
	public static Tool getTool(String toolName, String environment, String platform, String browser, TestReporter testReporter) {
		PageActions actions = null;
		if (toolName.equalsIgnoreCase("selenium")) {
			WebDriver driver = getWebDriver(environment, platform, browser);
			actions = new SeleniumPageActions(driver, testReporter);
		}
		return new Tool(toolName, actions);
	}
	

}
