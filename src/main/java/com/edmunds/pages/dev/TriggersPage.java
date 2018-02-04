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

public class TriggersPage extends PageBase {
	
	public Element lnkValidateDeletionOpp = ElementFactory.getElement("ValidateDeletionOpp link", 
			Locator.xpath("//a[contains(text(),'ValidateDeletionOpp')]"));
	public Element btnEdit = ElementFactory.getElement("Edit button", 
			Locator.cssSelector("input[value='Edit']"));
	public Element btnSave = ElementFactory.getElement("Save button", 
			Locator.cssSelector("input[value='Save']"));
	Element chkboxIsActive = ElementFactory.getElement("Is Active checkbox", 
			Locator.xpath("//input[contains(@id,'isactive')]"));

	public Element lblStatus_Value = ElementFactory.getElement("Status value", 
			Locator.xpath("//label[contains(text(),'Status')]/../following-sibling::*//span"));

	

	public boolean isChecked(Element pageElement) {
		try {
			//"checked" is deemed to be "boolean" attribute, and will return either "true" or null:
			if (pageElement.getAttribute("checked").equals("true")) {
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException npe) {
			return false;
		} catch (Exception e) {
			logger.error("Get attribute returned neither true nor null.");
			return false;
		}
	}
	
	public void checkIsActiveCheckbox() throws Throwable{
		if (!isChecked(chkboxIsActive)) {
			chkboxIsActive.click();
		}
	}
	
	public void uncheckIsActiveCheckbox() throws Throwable{
		if (isChecked(chkboxIsActive)) {
			chkboxIsActive.click();
		}
	}
	
	public boolean disableTrigger(String triggerName) throws Throwable {
		switch (triggerName) {
		case "ValidateDeletionOpp":
			lnkValidateDeletionOpp.click();
			if (!lblStatus_Value.getText().equals("Inactive")) {
				btnEdit.click();
				uncheckIsActiveCheckbox();
				btnSave.click();
			} 
			return lblStatus_Value.getText().equals("Inactive");
		default:
			return lblStatus_Value.getText().equals("Inactive");
		}
	}
}
