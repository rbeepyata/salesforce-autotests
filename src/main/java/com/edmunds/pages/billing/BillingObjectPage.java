package com.edmunds.pages.billing;

import com.edmunds.pages.OpportunityPage;
import com.edmunds.utilities.EdmundsUtil;
import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Image;
import com.epam.automation.core.element.Label;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.Locator;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class BillingObjectPage extends PageBase {
	
	public Label labelBillingObjectDetail = ElementFactory.getLabel("Billing Object Detail", this);
	public Label labelBillingStatus_Active = ElementFactory.getLabel("Billing Status", this);
	public Image imageBillingStatus_Green = ElementFactory.getImage("Billing Status green color", this);
	public Label labelSalesPrice_Value = ElementFactory.getLabel("Sales Price amount", this);
	public Label labelBillingStartDate_Value = ElementFactory.getLabel("Billing Start Date", this);
	public Label labelBillingEndDate_Value = ElementFactory.getLabel("Billing End Date", this);
	public Link linkInvoicingAccount = ElementFactory.getLink("Invoicing Account", this);
	public Link linkParentAccount = ElementFactory.getLink("Parent Account", this);
	public Link linkAriaHierarchy = ElementFactory.getLink("Aria Hierarchy", this);
	public Image imageJBFlagCreateProductAccount_Checked = ElementFactory.getImage("JB Flag - Create Product Account checked", this);
	public Image imageJBFlagCreateProductAccount_Unchecked = ElementFactory.getImage("JB Flag - Create Product Account unchecked", this);
	public Image imageJBFlagCreateParentAccount_Checked = ElementFactory.getImage("JB Flag - Create Parent Account checked", this);
	public Image imageJBFlagCreateParentAccount_Unchecked = ElementFactory.getImage("JB Flag - Create Parent Account unchecked", this);
	public Image imageSFCancellation_Checked = ElementFactory.getImage("SF Cancellation checked", this);
	public Image imageSFCancellation_Unchecked = ElementFactory.getImage("SF Cancellation unchecked", this);
	public Label labelInvoicingLiveDate_Value = ElementFactory.getLabel("Invoicing Live Date date", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);
	public Label labelAriaInvoicingAccountNo_Value = ElementFactory.getLabel("Aria Invoicing Account No", this);
	public Label labelAriaParentAccountNo_Value = ElementFactory.getLabel("Aria Parent Account No", this);
	public Label labelAriaProductAccountNo_Value = ElementFactory.getLabel("Aria Product Account No", this);
	public Label labelAriaCancellationContractNo_Value = ElementFactory.getLabel("Aria Cancellation ContractNo", this);
	
	/*public Label labelBillingObjectDetail = ElementFactory.getLabel("Billing Object Detail", 
			Locator.xpath("//*[contains(text(), 'Billing Object Detail')]"));
	public Label labelBillingStatus_Active = ElementFactory.getLabel("Billing Status", 
			Locator.xpath("//td[contains(text(),'Billing Status')]/following-sibling::td//*[contains(text(),'Active')]"));
	public Image imageBillingStatus_Green = ElementFactory.getImage("Billing Status green color", 
			Locator.xpath("//td[contains(text(),'Billing Status')]/following-sibling::td//img[contains(@src,'green')]"));
	public Label labelSalesPrice_Value = ElementFactory.getLabel("Sales Price amount", 
			Locator.xpath("//td[contains(text(),'Sales Price')]/following-sibling::td//*[contains(text(),'$')]"));
	public Label labelBillingStartDate_Value = ElementFactory.getLabel("Billing Start Date", 
			Locator.xpath("//td[text()='Billing Start Date' and @class='labelCol']/following-sibling::td"));
	public Label labelBillingEndDate_Value = ElementFactory.getLabel("Billing End Date", 
			Locator.xpath("//td[text()='Billing End Date' and @class='labelCol']/following-sibling::td"));
	public Link linkInvoicingAccount = ElementFactory.getLink("Invoicing Account", 
			Locator.xpath("//td[text()='Invoicing Account']/following-sibling::td[1]//a"));
	public Link linkParentAccount = ElementFactory.getLink("Parent Account", 
			Locator.xpath("//td[text()='Parent Account']/following-sibling::td[1]//a"));
	public Link linkAriaHierarchy = ElementFactory.getLink("Aria Hierarchy", 
			Locator.xpath("//td[text()='Aria Hierarchy']/following-sibling::td[1]//a"));
	public Image imageJBFlagCreateProductAccount_Checked = ElementFactory.getImage("JB Flag - Create Product Account checked", 
			Locator.xpath("//td[text()='JB Flag - Create Product Account']/following-sibling::td[1]//img[contains(@src,'checkbox_checked')]"));
	public Image imageJBFlagCreateProductAccount_Unchecked = ElementFactory.getImage("JB Flag - Create Product Account unchecked", 
			Locator.xpath("//td[text()='JB Flag - Create Product Account']/following-sibling::td[1]//img[contains(@src,'checkbox_unchecked')]"));
	public Image imageJBFlagCreateParentAccount_Checked = ElementFactory.getImage("JB Flag - Create Parent Account checked", 
			Locator.xpath("//td[text()='JB Flag - Create Parent Account']/following-sibling::td[1]//img[contains(@src,'checkbox_checked')]"));
	public Image imageJBFlagCreateParentAccount_Unchecked = ElementFactory.getImage("JB Flag - Create Parent Account unchecked", 
			Locator.xpath("//td[text()='JB Flag - Create Parent Account']/following-sibling::td[1]//img[contains(@src,'checkbox_unchecked')]"));
	public Image imageSFCancellation_Checked = ElementFactory.getImage("SF Cancellation checked", 
			Locator.xpath("//td[text()='SF Cancellation']/following-sibling::td[1]//img[@title='Checked']"));
	public Image imageSFCancellation_Unchecked = ElementFactory.getImage("SF Cancellation unchecked", 
			Locator.xpath("//td[text()='SF Cancellation']/following-sibling::td[1]//img[@title='Not Checked']"));
	public Label labelInvoicingLiveDate_Value = ElementFactory.getLabel("Invoicing Live Date date", 
			Locator.xpath("//td[text()='Invoicing Live Date']/following-sibling::td[1]//div"));
	public Button buttonSave = ElementFactory.getButton("Save", 
			Locator.cssSelector("input[title='Save']"));
	public Label labelAriaInvoicingAccountNo_Value = ElementFactory.getLabel("Aria Invoicing Account No", 
			Locator.xpath("//td[(text()='Aria Invoicing Account No') and contains(@class, 'labelCol')]/following-sibling::td[1]"));
	public Label labelAriaParentAccountNo_Value = ElementFactory.getLabel("Aria Parent Account No", 
			Locator.xpath("//td[(text()='Aria Parent Account No') and contains(@class, 'labelCol')]/following-sibling::td[1]"));
	public Label labelAriaProductAccountNo_Value = ElementFactory.getLabel("Aria Product Account No", 
			Locator.xpath("//td[(text()='Aria Product Account No') and contains(@class, 'labelCol')]/following-sibling::td[1]"));
	public Label labelAriaCancellationContractNo_Value = ElementFactory.getLabel("Aria Cancellation ContractNo", 
			Locator.xpath("//*[contains(text(),'Aria Cancellation ContractNo')]/following-sibling::td"));
	*/
	
	public String getBillingObjectId(){
		if (labelBillingObjectDetail.isDisplayed()) {
			return EdmundsUtil.getBillingObjectId(actions.getCurrentURL());
		} else {
			System.out.println("Not in the Billing Object Page! Navigate to Billing Object Page to getch Id.");
			return null;
		}
	}
	
	public boolean verifyBillingObject() {
		new OpportunityPage().linkBillingObjects.click();
		new OpportunityPage().linkNewNull.click();
		if (imageBillingStatus_Green.isDisplayed() 
				&& labelBillingStatus_Active.isDisplayed()
				&& labelInvoicingLiveDate_Value.isDisplayed() 
				&& labelSalesPrice_Value.isDisplayed()
				&& linkInvoicingAccount.isDisplayed())
			return true;
		else
			return false;
	}
	
	public String getBillingStatus(){
		return labelBillingStatus_Active.getText();
	}
	
	public String getSalesPrice(){
		String salesPrice = labelSalesPrice_Value.getText();
		return salesPrice;
	}

	public void navigateToAriaHierarchy() {
		linkAriaHierarchy.click();
		new AriaHierarchyPage().labelAriaHierarchyDetail.waitForItToBeVisible();
	}
	
	public String getAriaHierarchyId(){
		return linkAriaHierarchy.getText();
	}
	
	public boolean isJBFlagCreateProductAccountChecked() {
		if (imageJBFlagCreateProductAccount_Checked.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean isJBFlagCreateProductAccountUnchecked() {
		if (imageJBFlagCreateProductAccount_Unchecked.isDisplayed())
			return true;
		else 
			return false;
	}

	public boolean isJBFlagCreateParentAccountChecked() {
		if (imageJBFlagCreateParentAccount_Checked.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean isJBFlagCreateParentAccountUnchecked() {
		if (imageJBFlagCreateParentAccount_Unchecked.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean isSFCancellationChecked() {
		if (imageSFCancellation_Checked.isDisplayed())
			return true;
		else 
			return false;
	}
	
	public boolean isSFCancellationUnchecked() {
		if (imageSFCancellation_Unchecked.isDisplayed())
			return true;
		else 
			return false;
	}

}
