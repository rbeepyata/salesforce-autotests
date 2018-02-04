package com.epam.automation.utilities;

import org.openqa.selenium.By;

import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.LocatorType;
//TODO: JavaDocs and comments
public class LocatorUtil {
	
	public static By getSeleniumLocator(LocatorType locatorType, String locatorValue){
		switch(locatorType){
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case cssSelector:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case tagName:
			return By.tagName(locatorValue);
		case className:
			return By.className(locatorValue);
		case linkText:
			return By.linkText(locatorValue);
		case partialLinkText:
			return By.partialLinkText(locatorValue);
		default:
				return null;
		}
	}
	
	public static By getSeleniumLocator(Locator locator){
		return getSeleniumLocator(locator.getLocatorType(), locator.getLocatorValue());
	}

}
