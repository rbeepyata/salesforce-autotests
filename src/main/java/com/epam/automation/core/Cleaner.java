package com.epam.automation.core;

/**
 * The {@code Cleaner} interface provide a model to handle clean up activities in the framework. 
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan_Beepyata
 */

public interface Cleaner {
	
	void init();
	void cleanUp();
	void addDataToBeCleaned(Object objectType, Object objectToBeCleaned);
	
}

