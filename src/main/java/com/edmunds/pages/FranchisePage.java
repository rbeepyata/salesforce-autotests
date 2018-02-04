package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class FranchisePage extends PageBase {
	
	public ListBox listboxRecordType = ElementFactory.getListBox("Record Type of new record", this);
	public Button buttonContinue = ElementFactory.getButton("Continue", this);
	public TextBox textboxFranchiseName = ElementFactory.getTextBox("Franchise Name", this);
	public ListBox listboxFranchiseStatus = ElementFactory.getListBox("Franchise Status", this);
	public ListBox listboxMake = ElementFactory.getListBox("Make", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);

	public DynamicElement dynamic_linkDealership = ElementFactory.getDynamicPageElement("Dealership link", 
			"//a[contains(text(),'", "')]");
	
}
