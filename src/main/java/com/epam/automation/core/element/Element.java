package com.epam.automation.core.element;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code Element} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a generic page element in a web page. Any type of element 
 * (except for dynamic page element with dynamic content/text) can be modeled in the page object 
 * class using the {@code Element} class type.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class Element extends ElementBase {
	
	public Element(String elementName, Locator elementLocator){
		this.elementName = elementName;
		this.elementLocator = elementLocator;
	}
	
	public boolean isSelected() {
		return actions.isSelected(elementLocator, elementName).getStatus();
	}
	
	public void click() throws TestFailedException{
		actions.click(elementLocator, elementName);
	}
	
	public void type(String valueToType) throws TestFailedException{
		actions.type(elementLocator, valueToType, elementName);
	}
	
	public void typePassword(String valueToType) throws TestFailedException{
		actions.typePassword(elementLocator, valueToType, elementName);
	}
	
	public void clear() throws TestFailedException {
		actions.clear(elementLocator, elementName);		
	}

	public String getText() {
		return actions.getText(elementLocator, elementName);
	}

	public boolean verifyText(String expectedText) {
		return actions.verifyText(elementLocator, elementName, expectedText).getStatus();
	}
	
	@Override
	public Element highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}

	@Override
	public Element scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}
	
}
