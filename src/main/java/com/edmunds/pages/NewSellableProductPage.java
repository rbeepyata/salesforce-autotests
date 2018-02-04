package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.ListBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class NewSellableProductPage extends PageBase {
	
	public ListBox listboxRecordType = ElementFactory.getListBox("Record Type of new record", this);
	public Button buttonContinue = ElementFactory.getButton("Continue", this);
	public ListBox listboxProductType = ElementFactory.getListBox("Product Type", this);
	public Link linkFranchiseName = ElementFactory.getLink("Franchise", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);
	public DynamicElement dynamic_linkDealership = ElementFactory.getDynamicPageElement("Dealership link", 
			"//a[contains(text(),'", "')]");
	
}
