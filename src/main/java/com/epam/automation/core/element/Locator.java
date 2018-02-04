package com.epam.automation.core.element;

import com.epam.automation.core.PageBase;

/**
 * The {@code Locator} class models an object locator in a web page. It encapsulates locator strategy ({@link LocatorType}) and
 * the locator value and provides the support for building elements and performing actions on the elements.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class Locator {
	
	private LocatorType locatorType;
	private String locatorValue;
	
	public Locator(LocatorType locatorType, String locatorValue) {
		this.setLocatorType(locatorType);
		this.setLocatorValue(locatorValue);
	}
	
	public static Locator id(String locatorValue) {
		return new Locator(LocatorType.id, locatorValue);
	}

	public static Locator xpath(String locatorValue) {
		return new Locator(LocatorType.xpath, locatorValue);
	}
	
	public static Locator cssSelector(String locatorValue) {
		return new Locator(LocatorType.cssSelector, locatorValue);
	}
	
	public static Locator name(String locatorValue) {
		return new Locator(LocatorType.name, locatorValue);
	}

	public static Locator className(String locatorValue) {
		return new Locator(LocatorType.className, locatorValue);
	}

	public static Locator linkText(String locatorValue) {
		return new Locator(LocatorType.linkText, locatorValue);
	}

	public static Locator partialLinkText(String locatorValue) {
		return new Locator(LocatorType.partialLinkText, locatorValue);
	}

	public static Locator tagName(String locatorValue) {
		return new Locator(LocatorType.tagName, locatorValue);
	}

	public LocatorType getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(LocatorType locatorType) {
		this.locatorType = locatorType;
	}

	public String getLocatorValue() {
		return locatorValue;
	}

	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}

	public static Locator getLocatorFromRepo(Class<?> pageClass, String pageElementName) {
		String key = pageClass.getCanonicalName() + "." + pageElementName;
		return PageBase.objectRepository.getPageObject(key).getLocator();
	}
	
}
