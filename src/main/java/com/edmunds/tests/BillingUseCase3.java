package com.edmunds.tests;

import java.time.LocalDate;
import org.testng.annotations.Test;

import com.aria.common.shared.AllAcctContractsReturnElement;
import com.edmunds.EdmundsConfigProperties;
import com.edmunds.EdmundsTestBase;
import com.edmunds.aria.api.AriaAPIHelper;
import com.edmunds.pages.AriaIntegrationPage;
import com.edmunds.pages.DealershipPage;
import com.edmunds.pages.HomePage;
import com.edmunds.pages.LoginPage;
import com.edmunds.pages.OpportunityPage;
import com.edmunds.pages.billing.BillingObjectPage;
import com.epam.automation.core.action.Assertions;
import com.epam.automation.core.testdata.TestData;
import com.epam.automation.exception.TestFailedException;
import com.epam.automation.utilities.CalendarUtil;

/**
 * Billing Use Cases
 * Smoke Testing
 *  
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class BillingUseCase3 extends EdmundsTestBase {
	
	/**
	 * Case #3: Account manager populates an “Effective Cancellation Date” 
	 * on an Opportunity for a month where Billing HAS NOT run 
	 * (i.e. A 11/24/17 cancellation date populated in the salesforce UI 
	 * on 10/15/17 when billing for November has not run)
	 * 
	 */
	@Test(priority = 3)
	public void Billing_UseCase3() throws TestFailedException {

		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		DealershipPage dealershipPage = new DealershipPage();
		OpportunityPage opportunityPage = new OpportunityPage();
		BillingObjectPage billingObjectPage = new BillingObjectPage();
		AriaIntegrationPage ariaIntegrationPage = new AriaIntegrationPage();

		// Fetch Use Case details
		TestData useCasedetails = testDataGetter.getTestData("Use Cases List", "Case 3");
		
		//Initialize test case for reporting purpose
		testReporter.initTestCase(useCasedetails.get("Use Case ID"), useCasedetails.get("Description"),
				useCasedetails.get("Category"));
		
		// Fetch Use Case test data
		TestData data = testDataGetter.getTestData("Billing Cancellation Test Data", useCasedetails.get("Use Case ID"));
		
		String finalStage = data.get("Final Stage");
		String effectiveCancellationDate_InOppty = data.get("Effective Cancellation Date");
		LocalDate effectiveCancellationDate_DateValue = 
				CalendarUtil.convertStringToDate(effectiveCancellationDate_InOppty, "MM/dd/yyyy");
		
		try {
			// Test Steps
			loginPage.login(EdmundsConfigProperties.USERNAME, EdmundsConfigProperties.PASSWORD);

			dealershipPage.navigateToDealership(data.get("Dealership Id"));
			opportunityPage.navigateToOpportunity(data.get("Opportunity Id"));
			
			opportunityPage.buttonEdit.click();
			opportunityPage.textboxEffectiveCancellationDate.type(effectiveCancellationDate_InOppty);
			opportunityPage.listboxStage.selectByVisibleText(finalStage);
			opportunityPage.listboxCancellationReasonAvailable.selectByVisibleText("Billing Issues");
			opportunityPage.buttonCancellationReasonAdd.click();
			opportunityPage.buttonSave.click();
			
			opportunityPage.navigateToBillingObject();
			String billingEndDate_InBO = billingObjectPage.labelBillingEndDate_Value.getText();
			LocalDate billingEndDate_InBO_DateValue = 
					CalendarUtil.convertStringToDate(billingEndDate_InBO, "MM/dd/yyyy");
			
			Assertions.verify(billingEndDate_InBO_DateValue.equals(effectiveCancellationDate_DateValue.plusDays(1)), 
					"Billing Object Ngb_billing_end_date__c = Opportunity effective_cancellation_date__c +1", 
					"Billing Object Ngb_billing_end_date__c IS NOT EQUAL TO Opportunity effective_cancellation_date__c +1"	);
			
			Assertions.verify(billingObjectPage.isSFCancellationChecked(), 
					"SF Cancellation check box is checked", 
					"SF Cancellation check box is NOT checked");
			
			// Aria Integration:
			homePage.navigateToAriaIntegrationPage();
			Assertions.info("Starting Jitterbit job ...");
			ariaIntegrationPage.startJitterBitJob();
			// Wait for Jitterbit job to complete.
			boolean ariaIntegrationIsDone = ariaIntegrationPage.waitForJitterBitJobToComplete(800); 

			if (ariaIntegrationIsDone) {

				// Salesforce Validations after Aria Integration:
				Assertions.info("======== Salesforce Validations after Aria Integration ========");
				opportunityPage.navigateToOpportunity(data.get("Opportunity Id")); 
				opportunityPage.navigateToBillingObject();

				String ariaProductAccountNo_AfterIntegration = billingObjectPage.labelAriaProductAccountNo_Value.getText();
				String ariaCancellationContractNo_AfterIntegration = billingObjectPage.labelAriaCancellationContractNo_Value.getText();

				Assertions.verify(!ariaCancellationContractNo_AfterIntegration.equals(""), 
						"Aria Cancellation Contract No is " +ariaCancellationContractNo_AfterIntegration+ ".", 
						"Aria Cancellation Contract No is NOT populated.");
				
				Assertions.verify(billingObjectPage.isSFCancellationUnchecked(), 
						"After Aria Integration: SF Cancellation check box is unchecked", 
						"After Aria Integration: SF Cancellation check box is NOT unchecked");

				String billingEndDate_InBO_afterAI = billingObjectPage.labelBillingEndDate_Value.getText();
				LocalDate billingEndDate_InBO_DateValue_afterAI = 
						CalendarUtil.convertStringToDate(billingEndDate_InBO_afterAI, "MM/dd/yyyy");
				
				Assertions.verify(billingEndDate_InBO_DateValue_afterAI.equals(effectiveCancellationDate_DateValue.plusDays(1)), 
						"After Aria Integration: Billing Object Ngb_billing_end_date__c = Opportunity effective_cancellation_date__c +1", 
						"After Aria Integration: Billing Object Ngb_billing_end_date__c IS NOT EQUAL TO Opportunity effective_cancellation_date__c +1"	);
				
				
				
				homePage.logout();

				// Aria API Validations:
				Assertions.info("======== Aria side API Validations after Integration ========");
				String accountNumber = ariaProductAccountNo_AfterIntegration;

				boolean isFuturePlanCreated = AriaAPIHelper.checkIfFuturePlanIsCreated(accountNumber);
				Assertions.verify(isFuturePlanCreated, 
						"Future Plan Change is created for the Product Account "+accountNumber+".", 
						"Future Plan Change is NOT created for the Product Account "+accountNumber+".");
				
			    AllAcctContractsReturnElement contract = AriaAPIHelper
			    		 .getContract(ariaProductAccountNo_AfterIntegration, ariaCancellationContractNo_AfterIntegration);
			     
			    String expectedCreatedDate = CalendarUtil.getTodaysDate("MM/dd/yyyy");
			    String createdDate_InDefaultFormat = contract.getCreateDate();
				String actualCreatedDate = CalendarUtil.convertDateFormat(createdDate_InDefaultFormat, "yyyy-MM-dd", "MM/dd/yyyy");
				
				Assertions.verify("Created Date in Aria", 
						expectedCreatedDate, actualCreatedDate);

			    String expectedContractStatus = AriaAPIHelper.getContractStatus_FromCode(1L); // Status code for "In Effect"
				String actualContractStatus = AriaAPIHelper.getContractStatus_FromCode(contract.getStatusCode());
				
				Assertions.verify("Status in Aria", 
						expectedContractStatus, actualContractStatus);

			    String expectedCompletedDate = effectiveCancellationDate_InOppty;
				String actualCompletedDate = CalendarUtil.convertDateFormat(contract.getEndDate(), "yyyy-MM-dd", "MM/dd/yyyy");
				
				Assertions.verify("Completed Date in Aria", 
						expectedCompletedDate, actualCompletedDate);
				
			} else {
				Assertions.fail("Aria Integration");
				homePage.logout();
			}
			Assertions.info("Use Case : " + useCasedetails.get("Use Case ID") + " completed.");
		} catch (Exception e) {
			Assertions.fail("Use case could NOT be completed.");
			e.printStackTrace();
		}
	}
	
}
