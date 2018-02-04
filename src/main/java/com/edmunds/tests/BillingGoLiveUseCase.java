package com.edmunds.tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TimeZone;

import org.testng.annotations.Test;

import com.aria.common.shared.AcctHierarchyDetailsReturnElement;
import com.aria.common.shared.InvoiceHistoryReturnElement;
import com.aria.common.shared.InvoiceLineItemsReturnElement;
import com.edmunds.EdmundsConfigProperties;
import com.edmunds.EdmundsTestBase;
import com.edmunds.aria.api.AriaAPIHelper;
import com.edmunds.pages.AriaIntegrationPage;
import com.edmunds.pages.DealershipPage;
import com.edmunds.pages.HomePage;
import com.edmunds.pages.LoginPage;
import com.edmunds.pages.OpportunityPage;
import com.edmunds.pages.OpportunityType;
import com.edmunds.pages.ProductPage;
import com.edmunds.pages.billing.AriaHierarchyPage;
import com.edmunds.pages.billing.BillingObjectPage;
import com.edmunds.tests.dataproviders.BillingDataProvider;
import com.edmunds.utilities.EdmundsUtil;
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

public class BillingGoLiveUseCase extends EdmundsTestBase {
	
	/**
	 *  Case #1: Opportunity goes live on Dealer Account that is not yet integrated into ARIA
	 */
	@Test(priority=1, dataProviderClass = BillingDataProvider.class, dataProvider="dataProviderForGoLive")
	public void Billing_UseCase1(String testDataSetId) {
		
		LoginPage loginPage = new LoginPage();
		HomePage homePage = new HomePage();
		DealershipPage dealershipPage = new DealershipPage();
		OpportunityPage opportunityPage = new OpportunityPage();
		ProductPage productPage = new ProductPage();
		BillingObjectPage billingObjectPage = new BillingObjectPage();
		AriaHierarchyPage ariaHierarchyPage = new AriaHierarchyPage();
		AriaIntegrationPage ariaIntegrationPage = new AriaIntegrationPage();

		/*Fetch Use Case details*/
		Map<String, String> useCaseDetails = EdmundsConfigProperties
				.XL_READER.fromSheet("Use Cases List").getDataFromRow("Case 1");
		
		testReporter.initTestCase(useCaseDetails.get("Use Case ID") + " (with " + testDataSetId + ")", 
				useCaseDetails.get("Description"), 
				useCaseDetails.get("Category"));
		
		/*Fetch Use Case test data*/
		TestData data = testDataGetter.getTestData("Billing Go Live Test Data", testDataSetId);

		OpportunityType opportunityType = OpportunityType.convertStringToOpportunityType(data.get("Opportunity Type"));
		String dateTimeStamp = TextUtil.getCurrentDateTimeStamp();
		String dealershipName = TextUtil.getAutoPrefixWithDateTimeStamp(data.get("Dealership Name"), dateTimeStamp);
		String opportunityName = TextUtil.getAutoPrefixWithDateTimeStamp(data.get("Opportunity Name"), dateTimeStamp);
		String initialStage = data.get("Initial Stage");
		String finalStage = data.get("Final Stage");
		
		try {
			/*Test Steps*/
			loginPage.login(EdmundsConfigProperties.USERNAME, EdmundsConfigProperties.PASSWORD);
			
			homePage.navigateToDealerShips();
			String dealershipId = dealershipPage.createDealership(dealershipName);
			Assertions.info("Dealership Id 		= " + dealershipId);

			String opportunityId = opportunityPage.createOpportunity(opportunityType, opportunityName, initialStage, dealershipName);
			Assertions.verify(opportunityId!=null, 
					"Opportunity Id 	= " + opportunityId, 
					"Failed to create opportunity.");

			productPage.createProduct(opportunityType, data.get("Sales Price"), opportunityName);
			boolean stageChanged = opportunityPage.changeStageTo(finalStage);
			
			if (!stageChanged) {
				Assertions.fail("Stage could NOT be changed to " + finalStage);
				throw new TestFailedException();
			} 
			
			String totalSalesPrice_InOppty = opportunityPage.labelTotalSalesPrice_Value.getText();
			String liveDate_InOppty = opportunityPage.labelLiveDate_Value.getText();
			String invoicingAccount_InOppty = opportunityPage.linkInvoicingAccount.getText();
			String dealershipName_InOppty = opportunityPage.linkDealershipName.getText();

			if (opportunityType.equals(OpportunityType.New)) {
				opportunityPage.navigateToBillingObject();
			} else {
				opportunityPage.navigateToBillingObject(opportunityType);
			}
//			opportunityPage.navigateToBillingObject();
			String billingObjectId = billingObjectPage.getBillingObjectId();
			Assertions.info("Billing Object Id 	= " + billingObjectId);
			
			String ariaHierarchyId = billingObjectPage.getAriaHierarchyId();
			Assertions.info("Aria Hierarchy Id 	= " + ariaHierarchyId);
			
			String salesPrice_InBO = billingObjectPage.labelSalesPrice_Value.getText();
			String billingStartDate_InBO = billingObjectPage.labelBillingStartDate_Value.getText();
			String invoicingAccount_InBO = billingObjectPage.linkInvoicingAccount.getText();
			String parentAccount_InBO = billingObjectPage.linkParentAccount.getText();
			boolean jBFlagCreateProductAccount_InBO = billingObjectPage.imageJBFlagCreateProductAccount_Checked.isDisplayed();
			boolean jBFlagCreateParentAccount_InBO = billingObjectPage.imageJBFlagCreateParentAccount_Checked.isDisplayed();
			
			//Salesforce Validations before Aria Integration:
			Assertions.info("======== Salesforce Validations before Aria Integration ========");
					
			Assertions.verify(totalSalesPrice_InOppty.equals(salesPrice_InBO),
					"Opportunity Total Sales Price = Billing Object Sales Price",
					"Opportunity Total Sales Price is NOT equal to Billing Object Sales Price");
			Assertions.verify(liveDate_InOppty.equals(billingStartDate_InBO),
					"Opportunity Live Date = Billing Object Billing Start Date",
					"Opportunity Live Date is NOT equal to Billing Object Billing Start Date");
			Assertions.verify(invoicingAccount_InOppty.equals(invoicingAccount_InBO),
					"Opportunity Invoicing Account = Billing Object Invoicing Account",
					"Opportunity Invoicing Account is NOT equal to Billing Object Invoicing Account");
			Assertions.verify(dealershipName_InOppty.equals(parentAccount_InBO),
					"Opportunity Dealership Name = Billing Object Parent Account",
					"Opportunity Dealership Name is NOT equal to Billing Object Parent Account");

			Assertions.verify("JB Flag Create Product Account", 
					true, jBFlagCreateProductAccount_InBO);
			Assertions.verify("JB Flag Create Parent Account", 
					true, jBFlagCreateParentAccount_InBO);
					
			billingObjectPage.linkAriaHierarchy.click();
			
			String dealership_InAH = ariaHierarchyPage.linkDealership.getText();
			String invoicingAccount_InAH = ariaHierarchyPage.labelInvoicingAccount_Value.getText();
			String ariaDealershipAccountNo_InAH = ariaHierarchyPage.labelAriaDealershipAccountNo_Value.getText();
			String ariaInvoicingAccountNo_InAH = ariaHierarchyPage.labelAriaInvoicingAccountNo_Value.getText();
			String empty = " ";

			System.out.println();
			System.out.println("Aria Hierarchy:");
			Assertions.verify(dealership_InAH.equals(dealershipName_InOppty),
					"Dealership = Opportunity Dealership Name",
					"Dealership is NOT equal to Opportunity Dealership Name");
			Assertions.verify(invoicingAccount_InAH.equals(empty)||invoicingAccount_InAH.equals(""),
					"Invoicing Account = <Null> ",
					"Invoicing Account is NOT <Null> ");
			Assertions.verify(ariaDealershipAccountNo_InAH.equals(empty)||ariaDealershipAccountNo_InAH.equals(""),
					"Aria Dealership Account No = <Null>",
					"Aria Dealership Account No is NOT <Null>");
			Assertions.verify(ariaInvoicingAccountNo_InAH.equals(" "),
					"Aria Invoicing Account No = <Null>",
					"Aria Invoicing Account No is NOT <Null>");
			
			//Aria Integration:
			homePage.navigateToAriaIntegrationPage();
			Assertions.info("Starting Jitterbit job ...");
			ariaIntegrationPage.startJitterBitJob();
			// Wait to let Jitterbit job complete.
			boolean ariaIntegrationIsDone = ariaIntegrationPage
					.waitForJitterBitJobToComplete(600);//(EdmundsConfigProperties.ARIA_INTEGRATION_WAITING_TIME);

			if (ariaIntegrationIsDone) {
			
				//Salesforce Validations after Aria Integration:
				Assertions.info("======== Salesforce Validations after Aria Integration ========");
				opportunityPage.navigateToOpportunity(opportunityId);
				if (opportunityType.equals(OpportunityType.New)) {
					opportunityPage.navigateToBillingObject();
				} else {
					opportunityPage.navigateToBillingObject(opportunityType);
				}
				
//				opportunityPage.navigateToBillingObject();
				
				
				String ariaInvoicingAccountNo_InBO_AfterIntegration = billingObjectPage.labelAriaInvoicingAccountNo_Value.getText();
				String ariaParentAccountNo_InBO_AfterIntegration = billingObjectPage.labelAriaParentAccountNo_Value.getText();
				String ariaProductAccountNo_InBO_AfterIntegration = billingObjectPage.labelAriaProductAccountNo_Value.getText();
				
				Assertions.verify(ariaInvoicingAccountNo_InBO_AfterIntegration.equals("") || ariaInvoicingAccountNo_InBO_AfterIntegration.equals(" "),
						"Aria Invoicing Account No = <null>",
						"Aria Invoicing Account No is NOT <null>");
				Assertions.verify(	!	(ariaParentAccountNo_InBO_AfterIntegration.equals("")||ariaParentAccountNo_InBO_AfterIntegration.equals(" ")),
						"Aria Parent Account No = " + ariaParentAccountNo_InBO_AfterIntegration,
						"Aria Parent Account No is NOT <populated>");
				Assertions.verify(!ariaProductAccountNo_InBO_AfterIntegration.equals(""),
						"Aria Product Account No = " + ariaProductAccountNo_InBO_AfterIntegration,
						"Aria Product Account No is NOT <populated>");
				Assertions.verify(billingObjectPage.isJBFlagCreateProductAccountUnchecked(),
						"JB Flag Create Product Account is unchecked", 
						"JB Flag Create Product Account is NOT unchecked");
				Assertions.verify(billingObjectPage.isJBFlagCreateParentAccountUnchecked(),
						"JB Flag Create Parent Account is unchecked", 
						"JB Flag Create Parent Account is NOT unchecked");
				
				billingObjectPage.navigateToAriaHierarchy();
				
				String ariaDealershipAccountNo_InAH_AfterIntegration = ariaHierarchyPage.labelAriaDealershipAccountNo_Value.getText();
				
				Assertions.verify(	!	(ariaDealershipAccountNo_InAH_AfterIntegration.equals("")||ariaDealershipAccountNo_InAH_AfterIntegration.equals(" ")),
						"Aria Dealership Account No = " + ariaDealershipAccountNo_InAH_AfterIntegration,
						"Aria Dealership Account No is NOT <populated>");
				
				homePage.logout();
				
				
				//Aria API Validations:
				
				//Product Account
				Assertions.info("======== Aria side API Validations after Integration ========");
				String productNo = ariaProductAccountNo_InBO_AfterIntegration;
				boolean isProductExists = AriaAPIHelper.verifyIfProductExistsInAria(productNo);
				Assertions.verify(isProductExists, 
						"Product with number "+productNo+" exists in the Aria.", 
						"Product with number "+productNo+" does NOT exists in the Aria.");
				
				boolean isSuppPlanAssigned = AriaAPIHelper.checkIfSuppPlanIsAssigned(productNo);
				Assertions.verify(isSuppPlanAssigned, 
						"Supplemental Plan is assigned to the Product Account with number "+productNo+".", 
						"Supplemental Plan is NOT assigned to the Product Account with number "+productNo+".");
				
				AcctHierarchyDetailsReturnElement acctHierarchyDetails = AriaAPIHelper.getAcctHierarchyDetailsElement(productNo);
				String actualResponsibilityLevel = AriaAPIHelper.getResponsibilityLevel_FromCode(acctHierarchyDetails.getRespLevelCd());
				String expectedResponsibilityLevel = "Parent Pay";
				Assertions.verify("Responsibility Level", expectedResponsibilityLevel, actualResponsibilityLevel);
				
				String suppPlanName = AriaAPIHelper.getLatestAssignedSuppPlan(productNo).getPlanName();
				Assertions.verify("Supplemental Plan Name", data.get("Opportunity Name"), suppPlanName);
				
				//Invoice Date
				InvoiceHistoryReturnElement invoiceHistory = AriaAPIHelper.getLatestInvoiceFromInvoiceHistory(productNo);
				if (invoiceHistory==null){
					Assertions.info("No Invoice History");
				} else {
					String invoiceDateFromApi = (invoiceHistory.getPostingDate().split(" ")[0]);
					
					DateTimeFormatter 	receivedFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd"),
							requiredFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate date = LocalDate.parse(invoiceDateFromApi, receivedFormat);
					String actualInvoiceDate = date.format(requiredFormat);
					
					String splitDate[] = billingStartDate_InBO.split("/");
					LocalDate billingStartDate_InDate = LocalDate.of(Integer.valueOf(splitDate[2]), Integer.valueOf(splitDate[0]), Integer.valueOf(splitDate[1]));
					String expectedInvoiceDate = billingStartDate_InDate.format(requiredFormat);//date2.format(requiredFormat);
					
					
					Assertions.verify("Invoice Date", expectedInvoiceDate, actualInvoiceDate);
					
					//Invoice Details
					String invoiceNo = invoiceHistory.getInvoiceNo().toString();
					InvoiceLineItemsReturnElement invoiceLineItem = AriaAPIHelper.getLatestInvoiceLineItem(productNo, invoiceNo);
					String actualUnits = invoiceLineItem.getUnits().toString();
					String actualRatePerUnit = invoiceLineItem.getRatePerUnit().toString();
					String actualAmount = invoiceLineItem.getAmount().toString();
					//TODO
					Double expectedUnitsDoubleValue = Double.valueOf(EdmundsUtil.getUnitsForToday(TimeZone.getTimeZone("PST"))); 
					Double expectedRatePerUnitDoubleValue = Double.valueOf("1000");
					Double expectedAmountDoubleValue = expectedUnitsDoubleValue*expectedRatePerUnitDoubleValue;
					String expectedUnits = //EdmundsUtil.getDecimalFormat("#0.00", expectedUnitsDoubleValue);
					
					(actualUnits.split("\\.")[1].length()==1)? 
							EdmundsUtil.getDecimalFormat("#0.0", expectedUnitsDoubleValue):
								EdmundsUtil.getDecimalFormat("#0.00", expectedUnitsDoubleValue);
					
					String expectedRatePerUnit = EdmundsUtil.getDecimalFormat("#0.0", expectedRatePerUnitDoubleValue);
					String expectedAmount = (actualAmount.split("\\.")[1].length()==1)? 
							EdmundsUtil.getDecimalFormat("#0.0", expectedAmountDoubleValue):
								EdmundsUtil.getDecimalFormat("#0.00", expectedAmountDoubleValue);
							
							Assertions.verify("Units", expectedUnits, actualUnits);
							Assertions.verify("Rate Per Unit", expectedRatePerUnit, actualRatePerUnit);
							Assertions.verify("Amount", expectedAmount, actualAmount);
							
							//Supplemental Fields
							Map<String, String> suppFields = AriaAPIHelper.getSuppFields(productNo);
							String cddId = suppFields.get("CDDID");
							String bundleId = suppFields.get("BundleID");
							Assertions.verify(!cddId.isEmpty(), 
									"CDD ID = " + cddId +".",
									"CDD ID is NOT populated.");
							Assertions.verify(!bundleId.isEmpty(), 
									"Bundle ID = " + bundleId +".",
									"Bundle ID is NOT populated.");
				}
			} else {
				Assertions.fail("Aria Integration");
				homePage.logout();
			}
			Assertions.info("Use Case : " + useCaseDetails.get("Use Case ID") + " completed.");
		} catch (Exception e) {
			Assertions.fail("Use case could NOT be completed.");
			e.printStackTrace();
		}
	}

	
}
