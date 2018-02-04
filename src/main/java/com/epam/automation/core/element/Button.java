package com.epam.automation.core.element;
import com.epam.automation.exception.TestFailedException;

/**
 * The {@code Button} class is a concrete implementation of the base class {@link ElementBase}, and
 * it models a button in a web page.
 * The class {@code Button} includes only methods that are semantically relevant for a button in a web page.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */
public class Button extends ElementBase {
	
	public Button(String elementName, Locator elementLocator){
		this.elementName = elementName;
		this.elementLocator = elementLocator;
	}
	
	public void click() throws TestFailedException{
		actions.click(elementLocator, elementName);
	}
	
	@Override
	public Button highlight() {
		actions.highlight(elementLocator, elementName);
		return this;
	}
	
	@Override
	public Button scrollIntoView() {
		actions.scrollIntoView(elementLocator, elementName);
		return this;
	}

}
