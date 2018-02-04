package com.epam.automation.core.element;

/**
 * The {@code Label} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a label in a web page.
 * The class {@code Label} includes only methods that are semantically relevant for a label in a web page.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class Label extends ElementBase {
	
	public Label(String elementName, Locator elementLocator){
		this.elementName = elementName;
		this.elementLocator = elementLocator;
	}
	
	public String getText() {
		return actions.getText(elementLocator, elementName);
	}

	public boolean verifyText(String expectedText) {
		return actions.verifyText(elementLocator, elementName, expectedText).getStatus();
	}
	
	@Override
	public Label highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}
	
	@Override
	public Label scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}

}
