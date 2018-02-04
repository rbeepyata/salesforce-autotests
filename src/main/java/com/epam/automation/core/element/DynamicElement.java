package com.epam.automation.core.element;

import com.epam.automation.core.ElementFactory;

/**
 * The {@code DynamicElement} class represents any dynamic element in a web page that can be located
 * using an xpath. The xpath here is dynamic and needs a dynamic content to be supplied. Xpath for the
 * dynamic element is divided into 3 parts. The second part is the dynamic content or text that needs to
 * be supplied in the test class to complete a valid xpath.
 * The class {@code DynamicElement} includes only a public method that returns an instance of 
 * {@code Element} class after calculating the complete xpath using the supplied dynamic content or text.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 * 
 */

public class DynamicElement { 
	
	String 	elementName;
	String xpathPart1of3;
	String xpathPart2of3;
	String xpathPart3of3;

	public DynamicElement(String elementName, String xpathPart1of3, String xpathPart3of3) {
		this.elementName = elementName;
		this.xpathPart1of3 = xpathPart1of3;
		this.xpathPart2of3 = null;
		this.xpathPart3of3 = xpathPart3of3;
	}
	
	String getDynamicXPathString(String valueToBeSubstitutedInXPath){
		xpathPart2of3 = valueToBeSubstitutedInXPath;
		return xpathPart1of3 + xpathPart2of3 + xpathPart3of3;
	}
	
	Locator getElementLocator(String valueToBeSubstitutedInXPath) {
		return Locator.xpath(getDynamicXPathString(valueToBeSubstitutedInXPath));
	}
	
	public Element getElement(String valueToBeSubstitutedInXPath) {
		return ElementFactory.getElement(valueToBeSubstitutedInXPath+" "+elementName , getElementLocator(valueToBeSubstitutedInXPath));
	}
	
}
