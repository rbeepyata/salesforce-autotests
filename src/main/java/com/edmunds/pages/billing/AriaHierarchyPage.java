package com.edmunds.pages.billing;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Label;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.Locator;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class AriaHierarchyPage extends PageBase {
	
	public Label labelAriaHierarchyDetail = ElementFactory.getLabel("Aria Hierarchy Detail", this);
	public Link linkDealership = ElementFactory.getLink("Dealership", this);
	public Label labelInvoicingAccount_Value = ElementFactory.getLabel("Invoicing Account name", this);
	public Label labelAriaDealershipAccountNo_Value = ElementFactory.getLabel("Aria Dealership Account Number", this);
	public Label labelAriaInvoicingAccountNo_Value = ElementFactory.getLabel("Aria Invoicing Account Number", this);
	
	/*public Label labelAriaHierarchyDetail = ElementFactory.getLabel("Aria Hierarchy Detail", 
			Locator.xpath("//*[contains(text(),'Aria Hierarchy Detail')]"));
	public Link linkDealership = ElementFactory.getLink("Dealership", 
			Locator.xpath("//td[text()='Dealership']/following-sibling::td[1]//a"));
	public Label labelInvoicingAccount_Value = ElementFactory.getLabel("Invoicing Account name", 
			Locator.xpath("//td[text()='Invoicing Account']/following-sibling::td[1]"));
	public Label labelAriaDealershipAccountNo_Value = ElementFactory.getLabel("Aria Dealership Account Number", 
			Locator.xpath("//td[text()='Aria Dealership Account No']/following-sibling::td[1]"));
	public Label labelAriaInvoicingAccountNo_Value = ElementFactory.getLabel("Aria Invoicing Account Number", 
			Locator.xpath("//td[text()='Aria Invoicing Account No']/following-sibling::td[1]"));*/
	
	
}
