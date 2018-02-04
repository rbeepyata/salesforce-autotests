package com.edmunds.pages;

import com.edmunds.pages.billing.BillingObjectPage;
import com.edmunds.utilities.EdmundsUtil;
import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.Image;
import com.epam.automation.core.element.Label;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.TextBox;
import com.epam.automation.utilities.CalendarUtil;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class OpportunityPage extends PageBase {
	
	public TextBox textboxOpportunityName = ElementFactory.getTextBox("Opportunity Name", this);
	public Link linkCloseDate = ElementFactory.getLink("Close Date link", this);
	public TextBox textboxCloseDate = ElementFactory.getTextBox("Close Date text", this);
	public ListBox listboxStage = ElementFactory.getListBox("Stage", this);
	//Options: Ready to Go Live, Confirmed Live/Active, Pending Cancellation
	public TextBox textboxInvoicingAccount = ElementFactory.getTextBox("Invoicing Account text", this);
	public Image imageInvoicingAccount = ElementFactory.getImage("Invoicing Account image", this);
	public Element checkboxTestRecord = ElementFactory.getElement("Test Record checkbox", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);
	public DynamicElement dynamic_labelOpportunityHeading = ElementFactory.getDynamicPageElement("Opportunity heading label",  
			"//*[@class='pageDescription' and contains(text(),'", "')]");
	public ListBox dropdownUsedLotSizeList = ElementFactory.getListBox("Used Lot Size List",this);
	public Label labelOpportunityDetail = ElementFactory.getLabel("Opportunity Detail", this);
	public TextBox textboxEffectiveCancellationDate = ElementFactory.getTextBox("Effective Cancellation Date", this);
	public ListBox listboxCancellationReasonAvailable = ElementFactory.getListBox("Cancellation Reason - Available",this);
	//Options: Billing Issues
	public Button buttonCancellationReasonAdd = ElementFactory.getButton("Cancellation Reason - Add", this);
	public ListBox listboxCancellationReasonChosen = ElementFactory.getListBox("Cancellation Reason - Chosen", this);
	
	//Edit Page
	public Link linkProducts = ElementFactory.getLink("Products", this);
	public Button buttonNewPDPHomeProduct = ElementFactory.getButton("New PDP Home Product", this);
	public Button buttonNewUsedProduct = ElementFactory.getButton("New Used Product", this);
	public Link linkOpportunityDiscounts = ElementFactory.getLink("Opportunity Discounts", this);
	public Button buttonNewOpportunityDiscount = ElementFactory.getButton("New Opportunity Discount", this);
	public Link linkBillingObjects = ElementFactory.getLink("Billing Objects", this);
	public Link linkNewNull = ElementFactory.getLink("New-null", this);
	public Link linkUsedUsedPlusSubscription = ElementFactory.getLink("Used-UsedPlus-Subscription", this);
	public Button buttonEdit = ElementFactory.getButton("Edit", this);
	public DynamicElement dynamic_linkDealership = ElementFactory.getDynamicPageElement("Dealership link",  
			"//a[contains(text(),'", "')]");
	public Label labelTotalSalesPrice_Value = ElementFactory.getLabel("Total Sales Price amount", this);
	public Label labelLiveDate_Value = ElementFactory.getLabel("Live Date", this);
	public Link linkInvoicingAccount = ElementFactory.getLink("Invoicing linkAccount",this);
	public Link linkDealershipName = ElementFactory.getLink("Dealership Name", this);
	
	public String createOpportunity(OpportunityType opportunityType, String opportunityName, String initialStage, String dealershipName) {
		DealershipPage dealershipPage = new DealershipPage();
		dealershipPage.linkFulfillmentOpportunities.click();
		switch (opportunityType) {
		case New:
			dealershipPage.buttonCreateNewOpportunity.click();
			break;
		case Used:
			dealershipPage.buttonCreateUsedOpportunity.click();
			dropdownUsedLotSizeList.selectByVisibleText("0-100 Vehicles");
			break;
		case Price_Promise:
			dealershipPage.buttonCreatePricePromiseOpportunity.click();
			break;
		case Price_Promise_Lease:
			dealershipPage.buttonCreatePricePromiseLeaseOpportunity.click();
			break;
		case UsedPlus:
			dealershipPage.buttonCreateUsedPlusOpportunity.click();
			break;
		case Trade_In_Tool:
			dealershipPage.buttonCreateTradeInToolOpportunity.click();
			break;
		case Reviews_Widget:
			dealershipPage.buttonCreateReviewsWidgetOpportunity.click();
			break;
		case CarCode_Text:
			dealershipPage.buttonCreateCarCodeTextOpportunity.click();
			break;
		case My_Appraise:
			dealershipPage.buttonCreateMyAppraiseOpportunity.click();
			break;
		case Edmunds_Express:
			dealershipPage.buttonCreateEdmundsExpressOpportunity.click();
			break;
		case Site_Enhancer:
			dealershipPage.buttonCreateSiteEnhancerOpportunity.click();
			break;
		case Ad_Solutions_Used:
			dealershipPage.buttonCreateAdSolutionsUsedOpportunity.click();
			break;
		default:
			dealershipPage.buttonCreateNewOpportunity.click();
			break;
		}
		listboxStage.selectByVisibleText(initialStage);
		textboxOpportunityName.type(opportunityName);
		textboxCloseDate.type(CalendarUtil.addDaysToToday(60, "MM/dd/yyyy")); // set close date as two months future date.
		checkboxTestRecord.click();
		textboxInvoicingAccount.type(dealershipName);
		
		buttonSave.click();
		
		boolean hasOpportunityCreated = dynamic_labelOpportunityHeading.getElement(opportunityName).waitForItToBeVisible();
		if (hasOpportunityCreated) {
			String opportunityId = EdmundsUtil.getOpportunityId(actions.getCurrentURL());
			return opportunityId;
		} else {
			return null;
		}
	}

	void selectInvoicingAccount(String dealershipName) {
		actions.saveCurrentWindowHandle();
		imageInvoicingAccount.click();
		actions.switchToNewWindow();
		actions.switchToFrame("resultsFrame");
		dynamic_linkDealership.getElement(dealershipName).click();
		actions.switchToMainWindow();
	}

	public void navigateToOpportunity(String opportunityId) {
		new HomePage().navigateToOpportunities();
		actions.get(ConfigProperties.URL + opportunityId);
		labelOpportunityDetail.waitForItToBeVisible();
	}
	
	public void navigateToBillingObject() {
		linkBillingObjects.click();
		linkNewNull.click();
		new BillingObjectPage().labelBillingObjectDetail.waitForItToBeVisible();
	}
	
	
	public void navigateToBillingObject(OpportunityType opportunityType) {
		linkBillingObjects.click();
		
		switch (opportunityType) {
		case New:
			linkNewNull.click();
			break;
		case Used:
			linkUsedUsedPlusSubscription.click();
			break;
		default:
			break;
		}
		new BillingObjectPage().labelBillingObjectDetail.waitForItToBeVisible();
	}

	public void clickOpportunityButtonByType(OpportunityType opportunityType) {
		DealershipPage dealershipPage = new DealershipPage();
		switch (opportunityType) {
		case New:
			dealershipPage.buttonCreateNewOpportunity.click();
			break;
		case Used:
			dealershipPage.buttonCreateUsedOpportunity.click();
			break;
		case Price_Promise:
			dealershipPage.buttonCreatePricePromiseOpportunity.click();
			break;
		case Price_Promise_Lease:
			dealershipPage.buttonCreatePricePromiseLeaseOpportunity.click();
			break;
		case UsedPlus:
			dealershipPage.buttonCreateUsedPlusOpportunity.click();
			break;
		case Trade_In_Tool:
			dealershipPage.buttonCreateTradeInToolOpportunity.click();
			break;
		case Reviews_Widget:
			dealershipPage.buttonCreateReviewsWidgetOpportunity.click();
			break;
		case CarCode_Text:
			dealershipPage.buttonCreateCarCodeTextOpportunity.click();
			break;
		case My_Appraise:
			dealershipPage.buttonCreateMyAppraiseOpportunity.click();
			break;
		case Edmunds_Express:
			dealershipPage.buttonCreateEdmundsExpressOpportunity.click();
			break;
		case Site_Enhancer:
			dealershipPage.buttonCreateSiteEnhancerOpportunity.click();
			break;
		case Ad_Solutions_Used:
			dealershipPage.buttonCreateAdSolutionsUsedOpportunity.click();
			break;
		default:
			dealershipPage.buttonCreateNewOpportunity.click();
			break;
		}
	}
	
	public boolean changeStageTo(String toStageName) {
		buttonEdit.click();
		listboxStage.selectByVisibleText(toStageName);
		buttonSave.click();
		return buttonEdit.waitForItToBeVisible();
	}

}
