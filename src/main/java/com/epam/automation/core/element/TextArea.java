package com.epam.automation.core.element;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code TextArea} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a text area in a web page.
 * The class {@code TextArea} includes only methods that are semantically relevant for a text area in a web page.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class TextArea extends ElementBase {
	
	public TextArea(String elementName, Locator elementLocator){
		this.elementName = elementName;
		this.elementLocator = elementLocator;
	}
	
	public void click() throws TestFailedException{
		actions.click(elementLocator, elementName);
	}
	
	public void type(String valueToType) throws TestFailedException{
		actions.type(elementLocator, valueToType, elementName);
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
	public TextArea highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}
	
	@Override
	public TextArea scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}

}
