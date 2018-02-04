package com.epam.automation.core;

import org.apache.log4j.Logger;

import com.epam.automation.core.action.CorePageActions;
import com.epam.automation.core.action.PageActions;
import com.epam.automation.core.element.ElementBase;
import com.epam.automation.core.objectrepository.ObjectRepository;
import com.epam.automation.core.report.TestReporter;


/**
 * 
 * The {@code PageBase} class is the base class for all the page objects.
 * This class provides all the basic requirements for building page object classes that model various web pages in the application. 
 * Has reference to PageActions and Object Repository. 
 *  
 * <p>This class is extended by application specific base class for Page Object classes.</p> 
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan_Beepyata
 * 
 */

public abstract class PageBase {
	public static final Logger logger = Logger.getLogger(PageBase.class.getName());
	public static PageActions actions;	//Refer to an object of CorePageActions.
	public static ObjectRepository objectRepository = null;

	protected static void initPageBase(PageActions toolSpecificActions, TestReporter testReporter) {		
		CorePageActions.initCorePageActions(toolSpecificActions, testReporter);
		
		//Set the context of PageActions to PageBase and ElementBase
		PageBase.actions = CorePageActions.getInstance();
		ElementBase.initElementBase(CorePageActions.getInstance());
	}
	
	protected static void initPageBase(ObjectRepository objectRepository, PageActions toolSpecificActions, TestReporter testReporter) {		
		PageBase.objectRepository = objectRepository;
		CorePageActions.initCorePageActions(toolSpecificActions, testReporter);
		
		//Set the context of PageActions to PageBase and ElementBase
		PageBase.actions = CorePageActions.getInstance();
		ElementBase.initElementBase(CorePageActions.getInstance());
	}
	
	
	//TODO: Add generic page related methods here
}
