package com.edmunds.pages.dev;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class DeveloperConsole extends PageBase {
	
	public Element dropdownDebug = ElementFactory.getElement("Debug dropdown", 
			Locator.id("debugMenuEntry-btnEl"));
	public Element menuitemOpenExecuteAnonymousWindow = ElementFactory.getElement("Open Execute Anonymous Window menuitem", 
			Locator.xpath("//div[contains(text(),'Open Execute Anonymous Window')]"));
	public Element txtareaFirstLineOfApexCode = ElementFactory.getElement("First Line Of Apex Code textarea", 
			Locator.xpath("//div[@id='ExecAnon-body']//div[text()='1']/../.."));
	public Element btnExecute = ElementFactory.getElement("Execute button", 
			Locator.xpath("//span[text()='Execute']/parent::button"));
	public Element chkboxOpenLog = ElementFactory.getElement("Open Log checkbox", 
			Locator.id("openLogAfterExec-inputEl"));

}
