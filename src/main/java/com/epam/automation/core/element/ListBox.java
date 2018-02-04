package com.epam.automation.core.element;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code ListBox} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a list box in a web page.
 * The class {@code ListBox} includes only methods that are semantically relevant for a list box or drop down in a web page.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */

public class ListBox extends ElementBase {

	public ListBox(String elementName, Locator elementLocator) {
		this.elementName = elementName;
		this.elementLocator = elementLocator;
	}

	public void selectByVisibleText(String text) throws TestFailedException {
		actions.selectByVisibleText(elementLocator, text, elementName);
	}

	@Override
	public ListBox highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}
	
	@Override
	public ListBox scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}

}
