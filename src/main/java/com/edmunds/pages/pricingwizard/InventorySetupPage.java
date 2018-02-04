package com.edmunds.pages.pricingwizard;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.TextArea;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class InventorySetupPage extends PageBase {
	
	public Element tabInventorySetup = ElementFactory.getElement("Inventory Setup tab", this);
	public Button btnConfirmAndContinue = ElementFactory.getButton("Confirm and Continue", this);
	public TextBox txtSubject = ElementFactory.getTextBox("Subject", this);
	public TextArea txtareaDescription = ElementFactory.getTextArea("Description", this);
	public TextArea txtareaCaseComment = ElementFactory.getTextArea("Case Comment", this);
	
}
