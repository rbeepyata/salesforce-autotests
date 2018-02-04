package com.edmunds.pages.pricingwizard;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class DealershipSetupPage extends PageBase {
	
	public TextBox txtDealershipName = ElementFactory.getTextBox("Dealership Name", this);
	public Element txtareaPhysicalLocation = ElementFactory.getElement("Physical Location textarea", this);
	public Element txtareaBillingAddress = ElementFactory.getElement("Billing Address textarea", this);
	public TextBox txtCDDDealershipID = ElementFactory.getTextBox("CDD Dealership ID", this);

}
