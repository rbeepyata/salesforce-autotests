package com.edmunds.pages.pricingwizard;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Element;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class PricingWizardPage extends PageBase {
	
	public Element tabDealershipSetup = ElementFactory.getElement("Dealership Setup tab", this);
	public Element tabContactSetup = ElementFactory.getElement("Contact Setup tab", this);
	public Element tabLeadSetup = ElementFactory.getElement("Lead Setup tab", this);
	public Element tabPricePromiseSetup = ElementFactory.getElement("Price Promise Setup tab", this);
	public Element tabWidgetSetup = ElementFactory.getElement("Widget Setup tab", this);
	public Element tabAdvertisingSetup = ElementFactory.getElement("Advertising Setup tab", this);
	public Element tabInventorySetup = ElementFactory.getElement("Inventory Setup tab", this);
	public Button btnSave = ElementFactory.getButton("Save", this);
	public Button btnExitTheWizard = ElementFactory.getButton("Exit the Wizard", this);
	
}
