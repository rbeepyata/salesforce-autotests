package com.edmunds.tests;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.edmunds.EdmundsConfigProperties;
import com.edmunds.EdmundsTestBase;
import com.edmunds.pages.DealershipPage;
import com.edmunds.pages.HomePage;
import com.edmunds.pages.LoginPage;
import com.edmunds.pages.PresalesOpportunityPage;
import com.edmunds.pages.econtract.OpenPSOPage;
import com.edmunds.pages.econtract.PrepContractPage;
import com.edmunds.pages.econtract.ProductSetupFormPage;
import com.edmunds.pages.pricingwizard.AdvertisingSetupPage;
import com.edmunds.pages.pricingwizard.ContactSetupPage;
import com.edmunds.pages.pricingwizard.DealershipSetupPage;
import com.edmunds.pages.pricingwizard.InventorySetupPage;
import com.edmunds.pages.pricingwizard.LeadSetupPage;
import com.edmunds.pages.pricingwizard.PricePromiseSetupPage;
import com.edmunds.pages.pricingwizard.PricingSetupPage;
import com.edmunds.pages.pricingwizard.PricingWizardPage;
import com.edmunds.pages.pricingwizard.WidgetSetupPage;
import com.edmunds.tests.dataproviders.BillingDataProvider;
import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.action.Assertions;
import com.epam.automation.core.testdata.TestData;
import com.epam.automation.exception.TestFailedException;
import com.epam.automation.utilities.TextUtil;

/**
 * Billing Use Cases
 * Smoke Testing
 *  
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class EContractUseCase extends EdmundsTestBase {

	/**
	 *  Case #1: Opportunity goes live on Dealer Account that is not yet integrated into ARIA
	 */
	@Test(priority=1, dataProviderClass = BillingDataProvider.class, dataProvider="dataProviderForEContract")
	public void EContract_UseCase1(String testDataSetId) throws TestFailedException{
		
		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		DealershipPage dealershipPage = new DealershipPage();
		OpenPSOPage openPSOPage = new OpenPSOPage();
		PrepContractPage prepContractPage = new PrepContractPage();
		ProductSetupFormPage productSetupFormPage = new ProductSetupFormPage();
		PresalesOpportunityPage presalesOpportunityPage = new PresalesOpportunityPage();
		PricingWizardPage pricingWizard = new PricingWizardPage();
		PricingSetupPage pricingSetupPage = new PricingSetupPage();
		DealershipSetupPage dealershipSetupPage = new DealershipSetupPage();
		ContactSetupPage contactSetupPage = new ContactSetupPage();
		LeadSetupPage leadSetupPage = new LeadSetupPage();
		PricePromiseSetupPage pricePromiseSetupPage = new PricePromiseSetupPage();
		WidgetSetupPage widgetSetupPage = new WidgetSetupPage();
		AdvertisingSetupPage advertisingSetupPage = new AdvertisingSetupPage();
		InventorySetupPage inventorySetupPage = new InventorySetupPage();
		
		//Fetch Use Case details
		Map<String, String> useCaseDetails = EdmundsConfigProperties
				.XL_READER.fromSheet("Use Cases List").getDataFromRow("Case 4");
	
//		TestData useCaseDetails = testDataGetter.getTestData("Use Cases List", "Case 4");
		
		testReporter.initTestCase(useCaseDetails.get("Use Case ID") + " (with " + testDataSetId + ")", 
				useCaseDetails.get("Description"), 
				useCaseDetails.get("Category"));
		
		//Fetch Use Case test data
		TestData data = testDataGetter.getTestData("E Contract Test Data", testDataSetId);
	/*	TestData data = new TestData();
		data.setTestData(ConfigProperties.XL_READER.fromSheet("E Contract Test Data").getDataFromColumn(testCaseName));
	*/	
		
		
		String dateTimeStamp = TextUtil.getCurrentDateTimeStamp();
		String dealershipName = TextUtil.getAutoPrefixWithDateTimeStamp(data.get("Dealership Name"), dateTimeStamp);

		try {
			// Test Steps
			loginPage.login(EdmundsConfigProperties.USERNAME, EdmundsConfigProperties.PASSWORD);
			
			homePage.navigateToDealerShips();
			String dealershipId = dealershipPage.createDealership2(dealershipName);
			Assertions.info("Dealership Id 		= " + dealershipId);
//			cleaner.addDataToBeCleaned(EdmundsObjectType.Dealerships, dealershipId);
			
//			dealershipPage.createFranchise("New", dealershipName, "Acura", "Active");
//			dealershipPage.createFranchise("Used", dealershipName, "Used", "Active");
			String franchise1 = dealershipPage.createFranchise(data.get("Franchise Record Type"), 
					dealershipName, data.get("Franchise Make"), data.get("Franchise Status"));
			String franchise2 = dealershipPage.createFranchise(data.get("Franchise2 Record Type"), 
					dealershipName, data.get("Franchise2 Make"), data.get("Franchise2 Status"));
			
			dealershipPage.createSellableProducts();
			
//			dealershipPage.createNewSellableProduct("CarCode Text", "Managed Messaging", dealershipName+"_"+"Acura", dealershipName);
			dealershipPage.createNewSellableProduct(data.get("Sellable Product Record Type"), 
					data.get("Sellable Product Product Type"), franchise1, dealershipName);
			
			dealershipPage.linkSellableProductsGoToList.click();
			//TODO: More validations
			dealershipPage.dynamic_linkDealership.getElement(dealershipName).click();
			
//			String dealershipId = "0011D000002Winc";
//			homePage.navigateToDealerShips();
//			dealershipPage.navigateToDealership(dealershipId);
			
			dealershipPage.buttonPrepContract.click();
			
			actions.setHighlightElement(false);
			openPSOPage.lnkPSOName.click();
			
			prepContractPage.lnkPrepContract.click();
			prepContractPage.chkboxIncludeAvailableProducts.click();
			prepContractPage.txtIncludeAvailableProductsSalesPrice.waitForItToBeVisible();
			prepContractPage.txtIncludeAvailableProductsSalesPrice.type("1000");
			prepContractPage.chkboxManagedMessaging.click();
			prepContractPage.txtManagedMessagingSalesPrice.type("15000");
			prepContractPage.chkboxSearchNew.click();
			prepContractPage.txtSearchNewSpend.type("1110");
			prepContractPage.chkboxSocialNew.scrollIntoView().click();
			prepContractPage.chkboxSocialUsed.click();
			prepContractPage.txtSocailNewSpend.type("900");
			prepContractPage.txtSocailUsedSpend.type("800");
			prepContractPage.btnSave.click();
			actions.waitUnconditionallyFor(3); //TODO: Remove this. Wait for Discount $ to be reloaded
			/*prepContractPage.imgLoading.waitForItToBeVisible();
			prepContractPage.imgLoading.waitForItToBeInvisible();*/
			
			productSetupFormPage.lnkProductSetupForm.click();
			productSetupFormPage.txtGeneralContactEmail.type("GeneralContactEmail@test.com");
			productSetupFormPage.txtGeneralContactFirstName.type("GeneralContactFirstName");
			productSetupFormPage.txtGeneralContactLastName.type("GeneralContactLastName");
			productSetupFormPage.txtGeneralContactTitle.type("GeneralContactTitle");
			productSetupFormPage.txtGeneralContactPhone.type("9998880001");
			
			productSetupFormPage.txtTechnicalContactEmail.type("TechnicalContactEmail@test.com");
			productSetupFormPage.txtTechnicalContactFirstName.type("TechnicalContactFirstName");
			productSetupFormPage.txtTechnicalContactLastName.type("TechnicalContactLastName");
			productSetupFormPage.txtTechnicalContactTitle.type("TechnicalContactTitle");
			productSetupFormPage.txtTechnicalContactPhone.type("9998880001");
			
			productSetupFormPage.txtBillingContactEmail.type("BillingContactEmail@test.com");
			productSetupFormPage.txtBillingContactFirstName.type("BillingContactFirstName");
			productSetupFormPage.txtBillingContactLastName.type("BillingContactLastName");
			productSetupFormPage.txtBillingContactTitle.type("BillingContactTitle");
			productSetupFormPage.txtBillingContactPhone.type("9998880001");
			
			productSetupFormPage.txtDealerContactForTrainingEmail.type("DealerContactForTrainingEmail@test.com");
			productSetupFormPage.txtDealerContactForTrainingFirstName.type("DealerContactForTrainingFirstName");
			productSetupFormPage.txtDealerContactForTrainingLastName.type("DealerContactForTrainingLastName");
			productSetupFormPage.txtDealerContactForTrainingPhone.type("9998880001");
			
			productSetupFormPage.btnSave.click();
			//Wait for Save button to be disabled.
			actions.waitUnconditionallyFor(3); //TODO: Remove this. Update to wait dynamically.
			
			/*productSetupFormPage.imgLoading.waitForItToBeVisible();
			productSetupFormPage.imgLoading.waitForItToBeInvisible();*/
			productSetupFormPage.btnExit.click();

			dealershipPage.linkFirstPreSalesOpportunity.waitForItToBeVisible();
			dealershipPage.linkFirstPreSalesOpportunity.click();
			
			presalesOpportunityPage.includeAllSellableProductsOnPSO();

			actions.setHighlightElement(true);
			actions.setAbortTestCaseOnFailure(false);
			presalesOpportunityPage.buttonPricingWizard.click();
			presalesOpportunityPage.checkboxEASPrecisionSearchMultiStore.click();
			presalesOpportunityPage.checkboxPDPHomeMultiStore.click();
			presalesOpportunityPage.checkboxUsedInventoryMultiStore.click();
			presalesOpportunityPage.checkboxPricePromiseMultiStore.click();
			presalesOpportunityPage.checkboxUsedPlusMultiStore.click();
			presalesOpportunityPage.checkboxMyAppraiseTradeInMultiStore.click();
			presalesOpportunityPage.checkboxReviewsWidgetMultiStore.click();
			presalesOpportunityPage.buttonContinueToPricingWizard.click();
			actions.setAbortTestCaseOnFailure(true);
			
			pricingSetupPage.txtPrecisionSearchDiscountPercent.type("10");
//			actions.waitFor(2); //TODO: Remove this. Wait for Discount $ to be reloaded
			pricingSetupPage.txtPDPHomeDiscountPercent.type("10");
//			actions.waitFor(2); //TODO: Remove this. Wait for Discount $ to be reloaded
			pricingSetupPage.txtUsedInventoryDiscountPercent.type("10");
//			actions.waitFor(2); //TODO: Remove this. Wait for Discount $ to be reloaded
			pricingSetupPage.dropdownUsedPlusBillingType.selectByVisibleText("SUB");
			pricingSetupPage.dropdownUsedPlusInventoryDiscounted.selectByVisibleText("Yes");
			pricingSetupPage.dropdownPDPHomeRadius.selectByVisibleText("30");
			pricingSetupPage.btnConfirmAndContinue.click();
			pricingSetupPage.btnConfirmAndContinueToTheFW.waitForItToBeClickable();
			pricingSetupPage.btnConfirmAndContinueToTheFW.click();

			pricingWizard.tabDealershipSetup.click();
			pricingWizard.tabContactSetup.click();
			pricingWizard.tabLeadSetup.click();
			pricingWizard.tabPricePromiseSetup.click();
			pricingWizard.tabWidgetSetup.click();
			pricingWizard.tabAdvertisingSetup.click();
			
			inventorySetupPage.tabInventorySetup.click();
			inventorySetupPage.txtareaDescription.type("Description");
			inventorySetupPage.txtareaCaseComment.type("Comment");
			pricingWizard.btnSave.click();
			pricingWizard.btnExitTheWizard.click();
			
			homePage.navigateToDealerShips();
			dealershipPage.navigateToDealership(dealershipId);
			dealershipPage.linkSellableProductsGoToList.click();

			boolean allSold = dealershipPage.verifyIfAllSellableProductsAreSold();
			
			Assertions.verify(allSold, 
					"All Sellable Products' status is 'Sold'.", 
					"Failed to verify 'Sold' status of all the Sellable Products.");
//			dealershipPage.dyn_lnkDealership.getPageElement(dealershipName).click();
//			dealershipPage.lnkFirstPreSalesOpportunity.click();
//			actions.waitFor(2); //TODO: 
			
			homePage.logout();
			

			
			Assertions.info("Use Case : " + useCaseDetails.get("Use Case ID") + " completed.");
		} catch (Exception e) {
			Assertions.fail("Use case could NOT be completed.");
			e.printStackTrace();
		}
	}

	
}
