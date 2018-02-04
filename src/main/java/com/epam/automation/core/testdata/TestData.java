package com.epam.automation.core.testdata;
//TODO: JavaDocs and comments
import java.util.Map;

/**
 * Epam Automation - Core
 * @author Raghunandan Beepyata
 * 
 */

public final class TestData {
	
	private Map<String, String> testData;
	
	public String get(String key) {
		return testData.get(key);
	}

	public void setTestData(Map<String, String> map) {
		testData = map;
	}

}
