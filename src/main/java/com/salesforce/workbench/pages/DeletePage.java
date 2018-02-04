package com.salesforce.workbench.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.action.Assertions;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;
import com.epam.automation.selenium.WebTable;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class DeletePage extends PageBase {

	Element lblDelete = ElementFactory.getElement("Delete label", 
			Locator.xpath("//*[@id='pageTitle' and text()='Delete']"));
	Element radiobtnSingleRecord = ElementFactory.getElement("Single Record radiobutton", 
			Locator.id("sourceType_singleRecord"));
	Element txtSingleRecord = ElementFactory.getElement("Single Record textfield", 
			Locator.xpath("//*[@id='sourceType_singleRecord']/../../following-sibling::*//input"));
	Element radiobtnFromFile = ElementFactory.getElement("From File radiobutton", 
			Locator.id("sourceType_file"));
	
	Element btnNext = ElementFactory.getElement("Next button", 
			Locator.cssSelector("input[value='Next']"));
	Element btnConfirmDelete = ElementFactory.getElement("Confirm Delete button", 
			Locator.cssSelector("input[value='Confirm Delete']"));
	Element tableResults = ElementFactory.getElement("Results table", 
			Locator.xpath("//*[text()='Salesforce Id']/ancestor::table"));
	
	
	//Methods
	public void deleteSingleRecord(String salesforceId) throws Throwable{
		radiobtnSingleRecord.click();
		txtSingleRecord.type(salesforceId);
		btnNext.click();
		btnConfirmDelete.waitForItToBeVisible();
		btnConfirmDelete.click();
		tableResults.waitForItToBeVisible();
        
        HashMap<Integer, List<WebElement>> webTable = WebTable.getWebTable((WebDriver) actions.getTool(), "//*[text()='Salesforce Id']/ancestor::table") ;
       
        String actualResult = webTable.get(1).get(2).getText();
        String actualStatus = webTable.get(1).get(3).getText();
        
        Assertions.log(actualStatus.contains("Deleted"), actualStatus, "Not Deleted");
        Assertions.log(actualResult.contains("Success"), actualStatus, "No Success");
        
	}
}
