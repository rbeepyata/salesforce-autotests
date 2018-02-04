
package com.epam.automation.core;

import org.apache.log4j.Logger;

import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.Image;
import com.epam.automation.core.element.Label;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.TextArea;
import com.epam.automation.core.element.TextBox;

/**
 * 
 * The {@code ElementFactory} abstract class that is used to instantiate new page elements.  
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan_Beepyata
 */

public abstract class ElementFactory {
	
	private static final Logger logger = Logger.getLogger(ElementFactory.class);
	public static Element getElement(String elementName, Locator elementLocator) {
		Element element = null;
		try {
			element = new Element(elementName, elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of Element: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return element;
	}
	
	public static Element getElement(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getElement(elementName, elementLocator);
	}
	
	public static ListBox getListBox(String elementName, Locator elementLocator) {
		ListBox listBoxElement = null;
		try {
			listBoxElement = new ListBox(elementName + " list box", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of ListBox: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return listBoxElement;
	}
	
	public static ListBox getListBox(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getListBox(elementName, elementLocator);
	}
	
	public static Button getButton(String elementName, Locator elementLocator) {
		Button button = null;
		try {
			button = new Button(elementName + " button", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of Button: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return button;
	}

	public static Button getButton(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getButton(elementName, elementLocator);
	}
	
	public static TextBox getTextBox(String elementName, Locator elementLocator) {
		TextBox textBox = null;
		try {
			textBox = new TextBox(elementName + " text box", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of TextBox: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return textBox;
	}
	
	public static TextBox getTextBox(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getTextBox(elementName, elementLocator);
	}
	
	public static TextArea getTextArea(String elementName, Locator elementLocator) {
		TextArea textBox = null;
		try {
			textBox = new TextArea(elementName + " text area", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of TextArea: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return textBox;
	}
	
	public static TextArea getTextArea(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getTextArea(elementName, elementLocator);
	}

	public static Label getLabel(String elementName, Locator elementLocator) {
		Label label = null;
		try {
			label = new Label(elementName + " label", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of Label: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return label;
	}
	
	public static Label getLabel(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getLabel(elementName, elementLocator);
	}

	public static Link getLink(String elementName, Locator elementLocator) {
		Link link = null;
		try {
			link = new Link(elementName + " link", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of Link: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return link;
	}
	
	public static Link getLink(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getLink(elementName, elementLocator);
	}

	public static Image getImage(String elementName, Locator elementLocator) {
		Image image = null;
		try {
			image = new Image(elementName + " image", elementLocator);
		} catch (Exception e) {
			logger.error("Instantiation of Image: " +elementName+ " with locator:" + elementLocator+ " has failed.");
			e.getMessage();
		}
		return image;
	}
	
	public static Image getImage(String elementName, PageBase page) {
		Locator elementLocator = PageBase.objectRepository
				.getPageObject(page.getClass().getCanonicalName() + "." + elementName)
				.getLocator();
		return getImage(elementName, elementLocator);
	}
	
	public static DynamicElement getDynamicPageElement(String elementName, String xpathPart1of3, String xpathPart3of3) {
		DynamicElement dynamicPageElement = null;
		try {
			dynamicPageElement = new DynamicElement(elementName, xpathPart1of3, xpathPart3of3);
		} catch (Exception e) {
			logger.error("Instantiation of Page Element:" +elementName+ " with with dynamic xpath:" + xpathPart1of3+" and "+xpathPart3of3+ " has failed.");
			e.getMessage();
		}
		return dynamicPageElement;
	}

}
