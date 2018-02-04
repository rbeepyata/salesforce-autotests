package com.epam.automation.core.element;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code Link} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a link in a web page.
 * The class {@code Link} includes only methods that are semantically relevant for a link in a web page.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class Link extends ElementBase {
	
	public Link(String elementName, Locator elementLocator){
		this.elementName = elementName;
		this.elementLocator = elementLocator;
	}
	
	public void click() throws TestFailedException{
		actions.click(elementLocator, elementName);
	}
	
	public String getText() {
		return actions.getText(elementLocator, elementName);
	}

	public boolean verifyText(String expectedText) {
		return actions.verifyText(elementLocator, elementName, expectedText).getStatus();
	}
	
	@Override
	public Link highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}
	
	@Override
	public Link scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}

}
