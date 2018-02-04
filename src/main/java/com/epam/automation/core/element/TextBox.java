package com.epam.automation.core.element;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code TextBox} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a text box in a web page.
 * The class {@code TextBox} includes only methods that are semantically relevant for a text box in a web page.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class TextBox extends ElementBase {
	
	public TextBox(String elementName, Locator elementLocator){
		this.elementName = elementName;
		this.elementLocator = elementLocator;
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
	public TextBox highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}
	
	@Override
	public TextBox scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}

}
