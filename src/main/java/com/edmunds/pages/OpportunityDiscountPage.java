package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class OpportunityDiscountPage extends PageBase {

	public TextBox textboxStartDate = ElementFactory.getTextBox("Start Date text", this);
	public Link linkStartDate = ElementFactory.getLink("Start Date link", this);
	public TextBox textboxEndDate = ElementFactory.getTextBox("End Date text", this);
	public Link linkEndDate = ElementFactory.getLink("End Date link", this);
	public ListBox listboxDiscountReason = ElementFactory.getListBox("Discount Reason", this);
	public TextBox textboxDiscountPercentage = ElementFactory.getTextBox("Discount Percentage", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);
	public DynamicElement dynamic_linkOpportunity = ElementFactory.getDynamicPageElement("Opportunity link", 
			"//a[contains(text(),'", "')]");

	public void createOpportunityDiscount(String startDate, String endDate, String discountPercentage, String opportunityName) {
		new OpportunityPage().linkOpportunityDiscounts.click();
		new OpportunityPage().buttonNewOpportunityDiscount.click();
		linkStartDate.click();
		listboxDiscountReason.selectByVisibleText("Multi-store");
		textboxDiscountPercentage.type(discountPercentage);
		
		buttonSave.click();

		dynamic_linkOpportunity.getElement(opportunityName).click();
	}

}
