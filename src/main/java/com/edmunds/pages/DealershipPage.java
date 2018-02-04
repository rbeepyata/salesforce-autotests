package com.edmunds.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.edmunds.utilities.EdmundsUtil;
import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.action.Assertions;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.DynamicElement;
import com.epam.automation.core.element.Label;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.TextArea;
import com.epam.automation.core.element.TextBox;
import com.epam.automation.utilities.TextUtil;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class DealershipPage extends PageBase {
	
	public Button buttonNew = ElementFactory.getButton("New", 
			Locator.cssSelector("input[name='new']"));
	
	public Label labelDealershipDetail = ElementFactory.getLabel("Dealership Detail", this);
	
	public ListBox listboxBillingProcess = ElementFactory.getListBox("Billing Process", this);
	public TextBox textboxDealershipName = ElementFactory.getTextBox("Dealership Name", this);
	public TextBox textboxCDDDealershipID = ElementFactory.getTextBox("CDD Dealership ID", this);
	public ListBox listboxRegion = ElementFactory.getListBox("Region", this);
	public Label labelDealershipOwner_Value = ElementFactory.getLabel("Dealership Owner", this);
	public TextBox textboxOwnerLookup = ElementFactory.getTextBox("Owner Lookup", this);
	public TextBox textboxAssignedCSR = ElementFactory.getTextBox("Assigned CSR", this);
	public TextBox textboxRegionalDirector = ElementFactory.getTextBox("Regional Director", this);
	public TextBox textboxAssociateRegionalDirector = ElementFactory.getTextBox("Associate Regional Director", this);
	public TextBox textboxRetentionSpecialist = ElementFactory.getTextBox("Retention Specialist", this);
	public TextBox textboxRegionalTrainer = ElementFactory.getTextBox("Regional Trainer", this);
	public TextBox textboxDivisionalDirector = ElementFactory.getTextBox("Divisional Director", this);
	public TextBox textboxNationalManager = ElementFactory.getTextBox("National Manager", this);
	public ListBox dropdownDMAAvailable = ElementFactory.getListBox("DMA - Available", this);
	public TextBox textboxPrimaryDMA = ElementFactory.getTextBox("Primary DMA", this);
	public Button btnDMAAdd = ElementFactory.getButton("DMA Add", this);
	public ListBox dropdownDealerMarket = ElementFactory.getListBox("Dealer Market", this);
	public TextArea textareaPhysicalStreet = ElementFactory.getTextArea("Physical Street", this);
	public TextBox textboxPhysicalCity = ElementFactory.getTextBox("Physical City", this);
	public TextBox textboxPhysicalStateOrProvince = ElementFactory.getTextBox("Physical State/Province",this);
	public TextBox textboxPhysicalZipOrPostalCode = ElementFactory.getTextBox("Physical Zip/Postal Code",this);
	public TextArea textareaBillingStreet = ElementFactory.getTextArea("Billing Street", this);
	public TextBox textboxBillingCity = ElementFactory.getTextBox("Billing City", this);
	public TextBox textboxBillingStateOrProvince = ElementFactory.getTextBox("Billing State/Province", this);
	public TextBox textboxBillingZipOrPostalCode = ElementFactory.getTextBox("Billing Zip/Postal Code", this);
	public Button buttonSave = ElementFactory.getButton("Save", this);
	public DynamicElement dynamic_labelDealershipHeading = ElementFactory.getDynamicPageElement("Opportunity heading label", 
			"//*[@class='pageDescription' and contains(text(),'", "')]");
	public Button buttonPrepContract = ElementFactory.getButton("Prep Contract", this);
	
	//Edit Page
	public Link linkFulfillmentOpportunities = ElementFactory.getLink("Fulfillment Opportunities", this);
	public Link linkFirstFulfillmentOpportunity = ElementFactory.getLink("First Fulfillment Opportunity", this);
	public Link linkFirstPreSalesOpportunity = ElementFactory.getLink("First Pre-Sales Opportunities", this);
	
	//Sellable Products section
	public Button buttonCreateSellableProducts = ElementFactory.getButton("Create Sellable Products", this);
	public Link linkSellableProductsGoToList = ElementFactory.getLink("Sellable Products - Go to list", this);
	
	public Button buttonCreateNewOpportunity = ElementFactory.getButton("Create New Opportunity", this);
	public Button buttonCreateUsedOpportunity = ElementFactory.getButton("Create Used Opportunity", this);
	public Button buttonCreatePricePromiseOpportunity = ElementFactory.getButton("Create Price Promise Opportunity", this);
	public Button buttonCreatePricePromiseLeaseOpportunity = ElementFactory.getButton("Create Price Promise Lease Opportunity", this);
	public Button buttonCreateUsedPlusOpportunity = ElementFactory.getButton("Create UsedPlus Opportunity",this);
	public Button buttonCreateTradeInToolOpportunity = ElementFactory.getButton("Create Trade-In Tool Opportunity", this);
	public Button buttonCreateReviewsWidgetOpportunity = ElementFactory.getButton("Create Reviews Widget Opportunity", this);
	public Button buttonCreateCarCodeTextOpportunity = ElementFactory.getButton("Create CarCode Text Opportunity", this);
	public Button buttonCreateMyAppraiseOpportunity = ElementFactory.getButton("Create My Appraise Opportunity", this);
	public Button buttonCreateEdmundsExpressOpportunity = ElementFactory.getButton("Create Edmunds Express Opportunity", this);
	public Button buttonCreateSiteEnhancerOpportunity = ElementFactory.getButton("Create site enhancer Opportunity", this);
	public Button buttonCreateAdSolutionsUsedOpportunity = ElementFactory.getButton("Create Ad Solutions Used Opportunity", this);
	public Button buttonNewFranchise = ElementFactory.getButton("New Franchise", this);
	public Button buttonOkInPopupDialog = ElementFactory.getButton("Ok", this);
	public Button buttonNewSellableProduct = ElementFactory.getButton("New Sellable Product", this);

	public DynamicElement dynamic_linkFranchise = ElementFactory.getDynamicPageElement("Franchise link", 
			"//a[contains(text(),'", "')]");
	public DynamicElement dynamic_linkDealership = ElementFactory.getDynamicPageElement("Dealership link", 
			"//a[contains(text(),'", "')]");
	public DynamicElement dynamic_linkDealershipRecentlyViewed = ElementFactory.getDynamicPageElement("Dealership Link Recently Opened", 
			"//a[contains(text(),'", "')]");
	
	public String createDealership(String dealershipName){
		buttonNew.click();
		listboxBillingProcess.selectByVisibleText("Both");
		textboxDealershipName.type(dealershipName);
		textboxCDDDealershipID.type(TextUtil.getCurrentTimeStamp());
		listboxRegion.selectByVisibleText("Central");
		buttonSave.click();
		
		dynamic_labelDealershipHeading.getElement(dealershipName).waitForItToBeVisible();
		String dealershipId = EdmundsUtil.getDealershipId(actions.getCurrentURL());

		return dealershipId;
	}

	//Create dealership with some details to be used in the E contract flow.
	public String createDealership2(String dealershipName) {
		buttonNew.click();
		
		textboxDealershipName.type(dealershipName);
		textboxCDDDealershipID.type(TextUtil.getCurrentTimeStamp());
		String dealershipOwnerName = labelDealershipOwner_Value.getText();
		dealershipOwnerName = dealershipOwnerName.isEmpty()? "Epam Team": dealershipOwnerName;
		textboxOwnerLookup.type(dealershipOwnerName);
		textboxAssignedCSR.type(dealershipOwnerName);
		textboxRegionalDirector.type(dealershipOwnerName);
		textboxAssociateRegionalDirector.type(dealershipOwnerName);
		textboxRetentionSpecialist.type(dealershipOwnerName);
		textboxRegionalTrainer.type(dealershipOwnerName);
		textboxDivisionalDirector.type(dealershipOwnerName);
		textboxNationalManager.type(dealershipOwnerName);
		listboxRegion.selectByVisibleText("Central");
		dropdownDMAAvailable.selectByVisibleText("ATLANTA");
		btnDMAAdd.click();
		textboxPrimaryDMA.type("ATLANTA");
		dropdownDealerMarket.selectByVisibleText("Rural");
		textareaPhysicalStreet.type("Physical Street");
		textboxPhysicalCity.type("Physical City");
		textboxPhysicalStateOrProvince.type("CA");
		textboxPhysicalZipOrPostalCode.type("90210");
		textareaBillingStreet.type("Billing Street");
		textboxBillingCity.type("Billing City");
		textboxBillingStateOrProvince.type("CA");
		textboxBillingZipOrPostalCode.type("90210");
		
		buttonSave.click();
		
		dynamic_labelDealershipHeading.getElement(dealershipName).waitForItToBeVisible();
		String dealershipId = EdmundsUtil.getDealershipId(actions.getCurrentURL());

		return dealershipId;
	}

	public void openRecentViewedDealership(String dealershipName) {
		dynamic_linkDealershipRecentlyViewed.getElement(dealershipName).click();
	}

	public void navigateToDealership(String dealershipId) {
		new HomePage().navigateToDealerShips();
		actions.get(ConfigProperties.URL + dealershipId);
		labelDealershipDetail.waitForItToBeVisible();
	}

	public String navigateToFirstFulfillmentOpportunity() {
		linkFulfillmentOpportunities.click();
		linkFirstFulfillmentOpportunity.click();
		new OpportunityPage().labelOpportunityDetail.waitForItToBeVisible();
		String opportunityId = EdmundsUtil.getOpportunityId(actions.getCurrentURL());
		
		return opportunityId;
	}
	
	public String createFranchise(String recordType, String dealershipName, String make, String status) {
		buttonNewFranchise.click();
		FranchisePage franchisePage = new FranchisePage();
		franchisePage.listboxRecordType.selectByVisibleText(recordType);
		franchisePage.buttonContinue.click();
		
		String franchiseName = dealershipName+"_"+make;
		franchisePage.textboxFranchiseName.type(franchiseName);
		franchisePage.listboxFranchiseStatus.selectByVisibleText(status);
		franchisePage.listboxMake.selectByVisibleText(make);
		franchisePage.buttonSave.click();
		franchisePage.dynamic_linkDealership.getElement(dealershipName).click();
		labelDealershipDetail.waitForItToBeVisible();
		return franchiseName;
	}
	
	public void createSellableProducts() {
		buttonCreateSellableProducts.click();
		if (buttonOkInPopupDialog.waitForItToBeVisible()) {
			buttonOkInPopupDialog.click();
		} else {
			Assertions.fail("Couldn't create sellable products");
		}
	}
	
	public void createNewSellableProduct(String recordType, String productType, String franchiseName, String dealershipName) {
		
		buttonNewSellableProduct.click();
		NewSellableProductPage newSellableProductPage = new NewSellableProductPage();
		
		newSellableProductPage.listboxRecordType.selectByVisibleText(recordType);
		newSellableProductPage.buttonContinue.click();
		newSellableProductPage.listboxProductType.selectByVisibleText(productType);
		
		actions.saveCurrentWindowHandle();
		newSellableProductPage.linkFranchiseName.click();
		actions.switchToNewWindow();
		
		actions.switchToFrame("resultsFrame");

		dynamic_linkFranchise.getElement(franchiseName).click();

		actions.switchToMainWindow();

		newSellableProductPage.buttonSave.click();

		newSellableProductPage.dynamic_linkDealership.getElement(dealershipName).waitForItToBeVisible();
		newSellableProductPage.dynamic_linkDealership.getElement(dealershipName).click();
		
	}
	
	public boolean verifyIfAllSellableProductsAreSold() {

		int numberOfProductsSold = 0;
		WebDriver driver = (WebDriver)actions.getTool();
		List<WebElement> listOfStatuses  = driver
				.findElements(By
						.xpath("//table[@class='list']//tr[not (contains(@class,'header'))]//td[7]"));
		
		for (WebElement status : listOfStatuses) {
			if (status.getText().toLowerCase().contains("sold")) {
				numberOfProductsSold++;
			}
		}
		
		if (numberOfProductsSold==listOfStatuses.size()) {
			return true;
		} else {
			return false;
		}
		
	}
}
