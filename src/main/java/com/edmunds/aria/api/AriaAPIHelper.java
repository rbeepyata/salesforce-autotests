package com.edmunds.aria.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.aria.common.shared.AcctHierarchyDetailsReturnElement;
import com.aria.common.shared.AcctPlansReturnElement;
import com.aria.common.shared.AllAcctContractsReturnElement;
import com.aria.common.shared.InvoiceHistoryReturnElement;
import com.aria.common.shared.InvoiceLineItemsReturnElement;
import com.aria.common.shared.QueuedPlansReturnElement;
import com.aria.common.shared.SuppFieldsReturnElement;
import com.aria.sdk.classes.AriaBillingComplete;
import com.edmunds.EdmundsConfigProperties;
import com.epam.automation.utilities.CalendarUtil;

public class AriaAPIHelper {
	
	final static Logger logger = Logger.getLogger(CalendarUtil.class);
	
	static AriaBillingComplete ariaBillingComplete = new com.aria.sdk.classes.AriaBillingCompleteRest(EdmundsConfigProperties.ARIA_ENDPOINT_URI);
	
	public static boolean verifyIfProductExistsInAria(String accountNumber){
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String, Object> acctHierarchyDetails = ariaBillingComplete
	    		 .getAcctDetailsAll(EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue, null);
		String error_msg = (String) acctHierarchyDetails.get("error_msg");
		if (error_msg.equals("OK")) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<AcctPlansReturnElement> getSuppPlans(String accountNumber) {
		List<AcctPlansReturnElement> suppPlansList = new ArrayList<>();
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String, Object> acctPlans = ariaBillingComplete
	    		 .getAcctPlans(EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue, null);
		String error_msg = (String) acctPlans.get("error_msg");
		if (error_msg.equals("OK")) {
			List<AcctPlansReturnElement> acctPlansList =  (List<AcctPlansReturnElement>) acctPlans.get("acct_plans");
			if (!acctPlansList.isEmpty()) {
				for (AcctPlansReturnElement acctPlan : acctPlansList) {
					if (acctPlan.getSuppPlanInd()==1) {
						suppPlansList.add(acctPlan);
					}
				}
			} 
			return suppPlansList;
		} else {
//TODO			System.out.println(error_msg);
			return null;
		}
	}

	public static boolean checkIfSuppPlanIsAssigned(String accountNumber) {
		
		boolean suppPlanFound = false;
		List<AcctPlansReturnElement> suppPlansList =  getSuppPlans(accountNumber);

		if (suppPlansList==null) {
//			System.out.println("Product Account Number doesn't seem to exist in Aria!");
		} else {
			if (suppPlansList.isEmpty()) {
//				System.out.println("No Supplemental plans!");
			} else {
				suppPlanFound = true;
//				System.out.println(suppPlansList);
			}
		}
		return suppPlanFound;
	}

	public static AcctPlansReturnElement getLatestAssignedSuppPlan(String accountNumber) {
		
		AcctPlansReturnElement latestAssignedSuppPlan = null; 
		List<AcctPlansReturnElement> suppPlansList =  getSuppPlans(accountNumber);

		if (suppPlansList==null) {
			logger.error("Product Account Number doesn't seem to exist in Aria!");
		} else {
			if (suppPlansList.isEmpty()) {
				logger.info("No Supplemental plans!");
			} else {
				for (AcctPlansReturnElement suppPlan : suppPlansList) {
					// TODO: suppPlan.getSuppPlanActivateDate();
					latestAssignedSuppPlan = suppPlan;
				}
			}
		}
		return latestAssignedSuppPlan;
	}

	public static InvoiceHistoryReturnElement getLatestInvoiceFromInvoiceHistory(String accountNumber) {
		InvoiceHistoryReturnElement latestInvoice = null;
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String, Object> acctInvoiceHistory = ariaBillingComplete.getAcctInvoiceHistory(
				EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue, null, null, null,
				null, null, null, null);
		List<InvoiceHistoryReturnElement> invoiceHistory = (List<InvoiceHistoryReturnElement>) acctInvoiceHistory.get("invoice_history");

		if (invoiceHistory==null){
			return null;
		}
		if (!invoiceHistory.isEmpty()) {
			for (InvoiceHistoryReturnElement invoice : invoiceHistory) {
				// TODO Add logic to fetch latest. Currently works for a brand
				// new account.
				latestInvoice = invoice;
			}
		} else {
			logger.info("No Invoice History!");
		}
		return latestInvoice;
	}
	
	public static InvoiceLineItemsReturnElement getLatestInvoiceLineItem(String accountNumber, String invoiceNo) {
		InvoiceLineItemsReturnElement latestInvoiceLineItem = null;
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Long invoiceNoLongValue = Long.valueOf(invoiceNo);
		Map<String,Object> invoiceDetails = ariaBillingComplete.getInvoiceDetails(EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue, invoiceNoLongValue, null);
	
		List<InvoiceLineItemsReturnElement> invoiceLineItems = (List<InvoiceLineItemsReturnElement>) invoiceDetails.get("invoice_line_items");
		
		if (!invoiceLineItems.isEmpty()) {
			for (InvoiceLineItemsReturnElement invoiceLineItem : invoiceLineItems) {
				// TODO
				latestInvoiceLineItem = invoiceLineItem;
			}
		} else {
			logger.info("No Invoice Line Items!");
		} 
		return latestInvoiceLineItem;
	}
	
	public static Map<String, String> getSuppFields(String accountNumber){
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String,Object> acctSuppFields = ariaBillingComplete.getAcctSuppFields(EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue, null);
		List<SuppFieldsReturnElement> suppFieldsReturnElementList = (List<SuppFieldsReturnElement>) acctSuppFields.get("supp_fields");
		Map<String, String> suppFields = new HashMap<>();
		
		for (SuppFieldsReturnElement suppFieldsReturnElement : suppFieldsReturnElementList) {
			
		}
		for (SuppFieldsReturnElement suppFieldsReturnElement : suppFieldsReturnElementList) {
			suppFields.put(suppFieldsReturnElement.getFieldName(), suppFieldsReturnElement.getFieldValue());
		}
		return suppFields;
	}

	public static AcctHierarchyDetailsReturnElement getAcctHierarchyDetailsElement(String accountNumber) {
		AcctHierarchyDetailsReturnElement acctHierarchyDetailsElement = null;		
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String,Object> acctHierarchyDetails = ariaBillingComplete
	    		 .getAcctHierarchyDetails(EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue, 0L, null, null);
	     
	    @SuppressWarnings("unchecked")
		List<AcctHierarchyDetailsReturnElement> hierarchyDetailsList = (List<AcctHierarchyDetailsReturnElement>) acctHierarchyDetails.get("acct_hierarchy_details");
	    
		if (!hierarchyDetailsList.isEmpty()) {
			for (AcctHierarchyDetailsReturnElement element : hierarchyDetailsList) {
				if (element.getAcctNo().equals(accountNumber_LongValue)) {
					acctHierarchyDetailsElement = element;
				}
			}
		}
		return acctHierarchyDetailsElement;
	}
	
	public static String getResponsibilityLevel_FromCode(Long code) {
		switch(code.intValue()){
		case 1:
			return "Standard Self-Pay";
		case 2:
			return "Parent Pay";
		case 3:
			return "Parent Usage & Pay";
		case 4:
			return "Parent Usage & Pay w/note";
		default:
			return null;
			
			/*
			 * 1	Standard Self-Pay
			 * 2	Parent Pay
			 * 3	Parent Usage & Pay
			 * 4	Parent Usage & Pay w/note
			 * 
			 * Reference: https://developer.ariasystems.net/Aria6_core_api/get_acct_hierarchy_details
			 */
		}
	}
	
	public static String getStatus_FromCode(Long code) {
		switch(code.intValue()){
		case 0:
			return "INACTIVE";
		case 1:
			return "ACTIVE";
		default:
			return null;
		
			//TODO Other status codes
		/*	
			0	INACTIVE
			1	ACTIVE
			2	CANCELLATION PENDING
			3	TERMINATION PENDING
			10	ACTIVE DUNNING PENDING
			11	ACTIVE DUNNING 1
			12	ACTIVE DUNNING 1
			13	ACTIVE DUNNING 3
			14	ACTIVE DUNNING 4
			15	ACTIVE DUNNING 5
			16	ACTIVE DUNNING 6
			17	ACTIVE DUNNING 7
			18	ACTIVE DUNNING 8
			21	REINSTATED FORWARD BILL
			22	REINSTATED BACK BILL
			31	INSTALLATION PENDING
			32	REGISTERED PENDING ACTIVATION
			41	ACTIVE TRIAL
			51	TEMPORARY SERVICE BAN
			61	ACTIVE NON-BILLABLE
			99	PERMANENT
			-1	SUSPENDED
			-2	CANCELLED
			-3	TERMINATED
			-4	CONTRACT EXPIRED
			-11	SUSPENDED DUNNING 1
			-12	SUSPENDED DUNNING 2
			-13	SUSPENDED DUNNING 3
			-14	SUSPENDED DUNNING 4
			-15	SUSPENDED DUNNING 5
			-99	ARCHIVED
			
			Reference: https://developer.ariasystems.net/Aria6_core_api/get_acct_hierarchy_details
			
			*/
		}
	}
	
	public static boolean checkIfFuturePlanIsCreated(String accountNumber) {
		
		boolean futurePlanFound = false;
		List<QueuedPlansReturnElement> queuedPlansReturnElementList =  getQueuedServicePlans(accountNumber);

		if (queuedPlansReturnElementList==null) {
			logger.error("Account Number doesn't seem to exist in Aria!");
		} else {
			if (queuedPlansReturnElementList.isEmpty()) {
				logger.error("No Future plans created!");
			} else {
				futurePlanFound = true;
				logger.info(queuedPlansReturnElementList);
			}
		}
		return futurePlanFound;
	}

	
	@SuppressWarnings("unchecked")
	public static List<QueuedPlansReturnElement> getQueuedServicePlans(String accountNumber) {
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String, Object> queuedServicePlans = ariaBillingComplete
				.getQueuedServicePlans(EdmundsConfigProperties.ARIA_CLIENT_NO, 
						EdmundsConfigProperties.ARIA_AUTH_KEY,  accountNumber_LongValue, null);
		ArrayList<QueuedPlansReturnElement> queuedPlansReturnElementList = (ArrayList<QueuedPlansReturnElement>) queuedServicePlans
				.get("queued_plans");

		return queuedPlansReturnElementList;

	}
	
	@SuppressWarnings("unchecked")
	public static QueuedPlansReturnElement getLatestQueuedServicePlan(String accountNumber) {
		QueuedPlansReturnElement latestQueuedPlansReturnElement = null;
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Map<String, Object> queuedServicePlans = ariaBillingComplete
				.getQueuedServicePlans(EdmundsConfigProperties.ARIA_CLIENT_NO, 
						EdmundsConfigProperties.ARIA_AUTH_KEY,  accountNumber_LongValue, null);
		ArrayList<QueuedPlansReturnElement> queuedPlansReturnElementList = (ArrayList<QueuedPlansReturnElement>) queuedServicePlans
				.get("queued_plans");
		if (queuedPlansReturnElementList==null) {
			logger.error("Account Number doesn't seem to exist in Aria!");
		} else {
			if (!queuedPlansReturnElementList.isEmpty()) {
				for (QueuedPlansReturnElement queuedPlansReturnElement : queuedPlansReturnElementList) {
					// TODO
					latestQueuedPlansReturnElement = queuedPlansReturnElement;
				}
			} else {
				logger.error("No Queued Service Plans!");
			} 
		}
		return latestQueuedPlansReturnElement;
	}
	
	@SuppressWarnings("unchecked")
	public static AllAcctContractsReturnElement getContract(String accountNumber, String contractNumber) {
		AllAcctContractsReturnElement contractsReturnElement = null;
		Long accountNumber_LongValue = Long.valueOf(accountNumber);
		Long contractNumber_LongValue = Long.valueOf(contractNumber);
		try {
			Map<String, Object> allAcctContracts = ariaBillingComplete.getAllAcctContracts(
					EdmundsConfigProperties.ARIA_CLIENT_NO, EdmundsConfigProperties.ARIA_AUTH_KEY, accountNumber_LongValue,
					null, null, null);

			ArrayList<AllAcctContractsReturnElement> acctContractsReturnElementList = (ArrayList<AllAcctContractsReturnElement>) allAcctContracts
					.get("all_acct_contracts");
			for (AllAcctContractsReturnElement allAcctContractsReturnElement : acctContractsReturnElementList) {
				if (allAcctContractsReturnElement.getContractNo().equals(contractNumber_LongValue)){
					contractsReturnElement = allAcctContractsReturnElement;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contractsReturnElement;

	}
	
	public static String getContractStatus_FromCode(Long contractStatusCode) {
		switch(contractStatusCode.intValue()){
		case -3:
			return "Contract no longer in scope, usually due to mid-contract plan change on account.";
		case -2:
			return "Terminated.";
		case -1:
			return "Cancelled.";
		case 0:
			return "Contract completed and renewed.";
		case 1:
			return "In Effect.";
		case 99:
			return "Completed.";
		default:
			return null;
		
			//TODO Other status codes
		/*	
			-3	Contract no longer in scope, usually due to mid-contract plan change on account.
			-2	Terminated. Contract cancelled by client or system.
			-1	Cancelled. Contract cancelled by end user.
			0	Contract completed and renewed. New contract record refers to this contract record.
			1	In Effect. Contract is currently active.
			99	Completed. Contract is completed, no renewal.
			
			Reference: https://developer.ariasystems.net/Aria6_core_api/get_all_acct_contracts
			
			*/
		}
	}
	

	
}