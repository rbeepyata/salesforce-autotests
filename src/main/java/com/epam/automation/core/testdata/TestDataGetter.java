package com.epam.automation.core.testdata;
//TODO: JavaDocs and comments
/**
 * Epam Automation - Core
 * @author Raghunandan Beepyata
 * 
 */

public interface TestDataGetter {
	
	void initTestDataGetter(String testDataLocation, String fileName);

	TestData getTestData();
	TestData getTestData(String moduleName);
	TestData getTestData(String moduleName, String testCaseName);
	
	void setTestDataLocation(String testDataLocation);
	
}

