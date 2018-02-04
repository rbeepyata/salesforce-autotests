package com.edmunds.pages.dev;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;
import com.epam.automation.exception.TestFailedException;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class SetupPage extends PageBase {
	
	public Element txtSearch = ElementFactory.getElement("Search textfield", 
			Locator.id("setupSearch"));
	Element btnCustomize = ElementFactory.getElement("Customize expand/collapse button", 
			Locator.xpath("//*[@id='Customize_font']/preceding-sibling::a//img"));
	Element btnOpportunity = ElementFactory.getElement("Opportunity expand/collapse button", 
			Locator.xpath("//*[@id='Opportunity_font']/preceding-sibling::a//img"));
	public Element lnkTriggers = ElementFactory.getElement("Triggers link", 
			Locator.id("OpportunityTriggers_font"));

	public boolean isExpanded(Element pageElement){
		return pageElement.getAttribute("title").contains("Collapse");
		//For ex: title="Collapse - Customize - Level 1" when the image is in Expanded state
	}
	
	public boolean isCollapsed(Element pageElement){
		return pageElement.getAttribute("title").contains("Expand");
		//For ex: title="Expand - Customize - Level 1" width="11" when the image is in Collapsed state
	}
	
	public void expandCustomizeButton() throws TestFailedException{
		if(isCollapsed(btnCustomize))
			btnCustomize.click();
	}
	
	public void collapseCustomizeButton() throws TestFailedException{
		if(isExpanded(btnCustomize))
			btnCustomize.click();
	}
	
	public void expandOpportunityButton() throws TestFailedException{
		if(isCollapsed(btnOpportunity))
			btnOpportunity.click();
	}
	
	public void collapseOpportunityButton() throws TestFailedException{
		if(isExpanded(btnOpportunity))
			btnOpportunity.click();
	}
	
	public void navigateToOpportunityTriggersPage() throws TestFailedException {
		expandCustomizeButton();
		expandOpportunityButton();
		lnkTriggers.click();
	}
	

}
