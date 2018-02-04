package com.epam.automation.core.element;

/**
 * The {@code LocatorType} enum models locator strategy.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public enum LocatorType {
	id,
	name,
	cssSelector,
	xpath,
	className,
	tagName,
	linkText,
	partialLinkText;
	
	public static LocatorType convertStringToLocatorType(String locatorType){
		switch (locatorType.toLowerCase()) {
		case "id":
			return id;
		case "name":
			return name;
		case "cssselector":
			return cssSelector;
		case "xpath":
			return xpath;
		case "classname":
			return className;
		case "tagname":
			return tagName;
		case "linktext":
			return linkText;
		case "partiallinktext":
			return partialLinkText;
		default:
			return null;
		}
	}
}
