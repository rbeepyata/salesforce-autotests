package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class ProductPage extends PageBase {
	
	public TextBox textboxSalesPrice = ElementFactory.getTextBox("Sales Price", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);
	public Element checkboxTestRecord = ElementFactory.getElement("Test Record checkbox", this);
	public DynamicElement dynamic_linkOpportunity = ElementFactory.getDynamicPageElement("Opportunity link",  
			"//a[contains(text(),'", "')]");

	public void createProduct(OpportunityType opportunityType, String salesPrice, String opportunityName) {
		OpportunityPage opportunityPage = new OpportunityPage();
		opportunityPage.linkProducts.click();
		switch (opportunityType) {
		case New:
			opportunityPage.buttonNewPDPHomeProduct.click();
			break;
		case Used:
			opportunityPage.buttonNewUsedProduct.click();
			break;
		default:
			break;
		}
		textboxSalesPrice.type(salesPrice);
		checkboxTestRecord.click();
		buttonSave.click();
		dynamic_linkOpportunity.getElement(opportunityName).click();
	}

}
