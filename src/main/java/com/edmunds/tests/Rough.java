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
		System.setProperty("webdriver.chrome.driver", "lib//drivers//linux64//chromedriver");
		driver = new ChromeDriver();	
		
		driver.get("http://google.com");
		
		Thread.sleep(5000);
		
		driver.close();
	}

}
