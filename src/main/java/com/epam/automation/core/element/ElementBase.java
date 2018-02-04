package com.epam.automation.core.element;

import com.epam.automation.core.action.PageActions;

/**
 * The {@code ElementBase} abstract class is the base class for all concrete implementations for page element classes
 * that model page elements in a web page like {@link Button}, {@link TextBox}.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 */
public abstract class ElementBase {
	
	String 	elementName;
	Locator		elementLocator;
	protected static PageActions actions;
	
	public static void initElementBase(PageActions actions) {		
		ElementBase.actions = actions;
	}
	
	public String getElementName(){
		return elementName;
	}
	
	public Locator getElementLocator(){
		return elementLocator;
	}
	
	public void setElementName(String elementName){
		this.elementName = elementName;
	}
	
	public void setElementLocator(Locator elementLocator){
		this.elementLocator = elementLocator;
	}
	
	public Boolean waitForItToBeVisible() {
		return (actions.waitForElementToBeVisible(elementLocator, elementName)).getStatus();
	}
	
	public Boolean waitForItToBeVisible(long timeOutInSeconds) {
		return (actions.waitForElementToBeVisible(elementLocator, elementName, timeOutInSeconds)).getStatus();
	}
	
	public Boolean waitForItToBeClickable() {
		return (actions.waitForElementToBoClickable(elementLocator, elementName)).getStatus();
	}

	public Boolean waitForItToBeInvisible() {
		return (actions.waitForElementToBeInvisible(elementLocator, elementName)).getStatus();
	}
	
	public Boolean waitForItToBeInvisible(long timeOutInSeconds) {
		return (actions.waitForElementToBeInvisible(elementLocator, elementName, timeOutInSeconds)).getStatus();
	}

	public boolean isDisplayed() {
		return (actions.isDisplayed(elementLocator, elementName)).getStatus();
	}

	public boolean isEnabled() {
		return (actions.isEnabled(elementLocator, elementName)).getStatus();
	}

	public String getAttribute(String attributeName) {
		return actions.getAttribute(elementLocator, elementName, attributeName);
	}
	
	public abstract ElementBase scrollIntoView();
	
	public abstract ElementBase highlight();
	
	@Override
	public String toString() {
		return elementName;
	}
	
}
