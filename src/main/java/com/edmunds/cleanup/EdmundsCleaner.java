package com.edmunds.cleanup;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.edmunds.EdmundsConfigProperties;
import com.edmunds.pages.HomePage;
import com.edmunds.pages.LoginPage;
import com.edmunds.pages.dev.SetupPage;
import com.edmunds.pages.dev.TriggersPage;
import com.epam.automation.core.Cleaner;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.action.Assertions;
import com.epam.automation.exception.TestFailedException;
import com.salesforce.workbench.pages.DeletePage;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class EdmundsCleaner extends PageBase implements Cleaner {
	
	private static Logger logger = Logger.getLogger(EdmundsCleaner.class.getName());

	private Set<String> opportunitySet_ToBeCleanedUp;
	private Set<String> ariaHierarchySet_ToBeCleanedUp;
	private Set<String> billingObjectSet_ToBeCleanedUp;
	private Set<String> dealershipSet_ToBeCleanedUp;
	private boolean isInitialized = false;

	@Override
	public void init() {
		opportunitySet_ToBeCleanedUp = new HashSet<>();
		ariaHierarchySet_ToBeCleanedUp = new HashSet<>();
		billingObjectSet_ToBeCleanedUp = new HashSet<>();
		dealershipSet_ToBeCleanedUp = new HashSet<>();
		isInitialized = true;
	}
	
	@Override
	public void addDataToBeCleaned(Object objectType, Object objectToBeCleaned) {
		if (isInitialized) {
			switch ((EdmundsObjectType) objectType) {
			case Opportunities:
				addOpportunityToCleanUpData((String) objectToBeCleaned);
				break;
			case AriaHierarchies:
				addAriaHierarchyToCleanUpData((String) objectToBeCleaned);
				break;
			case BillingObjects:
				addBillingObjectToCleanUpData((String) objectToBeCleaned);
				break;
			case Dealerships:
				addDealershipToCleanUpData((String) objectToBeCleaned);
				break;
			default:
				logger.error("Incorrect type of Edmunds Objects was tried to be added to clean up data!");
				break;
			}
		} else {
			logger.error("Cleaner is NOT initialized!");
		}
	}
	
	@Override
	public void cleanUp(){
		try {
			
			if (nothingToDelete()) {
				logger.info("There are NO objects to delete. Skipping 'disabling of triggers in Salesforce' and 'logging into Workbench'.");
			} else {
				//Login to Salesforce and disable triggers on opportunities
				logger.info("Logging into Salesforce and disable triggers on opportunities ...");
				actions.get(EdmundsConfigProperties.URL);
				new LoginPage().login(EdmundsConfigProperties.USERNAME, EdmundsConfigProperties.PASSWORD);
				prepareOpportunitiesForCleanUp();
				new HomePage().logout();
				
				//Login to workbench
				com.salesforce.workbench.pages.LoginPage loginPage = new com.salesforce.workbench.pages.LoginPage();
				com.salesforce.workbench.pages.HomePage homePage = new com.salesforce.workbench.pages.HomePage();
				
				logger.info("Logging into Workbench to delete all objects created in the automation ...");
				actions.get(EdmundsConfigProperties.WORKBENCH_URL);
				loginPage.login("Sandbox", EdmundsConfigProperties.USERNAME, EdmundsConfigProperties.PASSWORD); //Sandbox only here. NO Production.
				
				deleteAllObjects();
				
				homePage.logout();
			}
		} catch (TestFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	boolean nothingToDelete(){
		return ( 	opportunitySet_ToBeCleanedUp.isEmpty() 
				&& 	ariaHierarchySet_ToBeCleanedUp.isEmpty()
				&& 	billingObjectSet_ToBeCleanedUp.isEmpty()
				&& 	dealershipSet_ToBeCleanedUp.isEmpty()
			);
	}
	
	void deleteAllObjects() throws Throwable{
		/*
		 * Delete objects in the order of Opportunities, Aria Hierarchies, Billing Objects, Dealerships
		 */
		delete(opportunitySet_ToBeCleanedUp);
		delete(ariaHierarchySet_ToBeCleanedUp);
		delete(billingObjectSet_ToBeCleanedUp);
		delete(dealershipSet_ToBeCleanedUp);
	}
	
	void delete(Set<String> idSet) throws Throwable {
		com.salesforce.workbench.pages.HomePage homePage = new com.salesforce.workbench.pages.HomePage();
		DeletePage deletePage = new DeletePage();
		for (String id : idSet) {
			homePage.navigateToDeletePage();
			deletePage.deleteSingleRecord(id);
		}
	}

	void prepareOpportunitiesForCleanUp() throws Throwable{
		//Disable Triggers on Opportunity to let them get deleted during clean up process without validations.
		new HomePage().navigateToSetupPage();
		new SetupPage().navigateToOpportunityTriggersPage();
		boolean status = new TriggersPage().disableTrigger("ValidateDeletionOpp");
		Assertions.log(status, 
				"ValidateDeletionOpp trigger disabled.", 
				"ValidateDeletionOpp trigger is NOT disabled.");
	}

	void addOpportunityToCleanUpData(String opportunityId){
		try {
			opportunitySet_ToBeCleanedUp.add(opportunityId);
		} catch (Exception e) {
			logger.error("Opportunity Id: " + opportunityId + "could NOT be added to clean up set. " + e);
		}
	}
	
	void addAriaHierarchyToCleanUpData(String ariaHierarchyId){
		try {
			ariaHierarchySet_ToBeCleanedUp.add(ariaHierarchyId);
		} catch (Exception e) {
			logger.error("Aria Hierarchy Id: " + ariaHierarchyId + "could NOT be added to clean up set. " + e);
		}
	}
	
	void addBillingObjectToCleanUpData(String billingObjectId){
		try {
			billingObjectSet_ToBeCleanedUp.add(billingObjectId);
		} catch (Exception e) {
			logger.error("Billing Object Id: " + billingObjectId + "could NOT be added to clean up set. " + e);
		}
	}
	
	void addDealershipToCleanUpData(String dealershipId){
		try {
			dealershipSet_ToBeCleanedUp.add(dealershipId);
		} catch (Exception e) {
			logger.error("Dealership Id: " + dealershipId + "could NOT be added to clean up set. " + e);
		}
	}

	
}
