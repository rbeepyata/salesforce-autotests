package com.edmunds.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.epam.automation.core.objectrepository.ExcelObjectRepository;

public class Rough {

	public static void main(String[] args) throws InterruptedException {

		
	}
	
	@Test
	public void test() throws InterruptedException{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "lib//drivers//linux//chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
	    chromeOptions.addArguments("disable-infobars");
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    chromeOptions.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(chromeOptions);	
		
		driver.get("http://google.com");
		
		Thread.sleep(5000);
		
		driver.close();
	}

}
