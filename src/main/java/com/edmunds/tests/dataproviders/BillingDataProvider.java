package com.edmunds.tests.dataproviders;

import org.testng.annotations.DataProvider;

import com.epam.automation.core.ConfigProperties;
import com.epam.automation.utilities.ExcelReader;

public class BillingDataProvider {
	
	@DataProvider(name="dataProviderForEContract")
	public static Object[][] dataProviderForEContract(){
		
		ExcelReader xlReader = new ExcelReader(ConfigProperties.TEST_DATA_FILE_LOCATION + "//" 
				+ ConfigProperties.TEST_DATA_FILE_NAME);
		
		String[] testCases = xlReader.fromSheet("E Contract Test Data").getDataArrayFromRow("Fields");

		String[][] testCases2DArray = new String[testCases.length][1];
		
		for(int i=0;i<(testCases.length) ; i++){
			testCases2DArray[i][0] = testCases[i];
		}
		
		return testCases2DArray;
		
	}
	
	@DataProvider(name="dataProviderForGoLive")
	public static Object[][] dataProviderForGoLive(){
		
		ExcelReader xlReader = new ExcelReader(ConfigProperties.TEST_DATA_FILE_LOCATION + "//" 
				+ ConfigProperties.TEST_DATA_FILE_NAME);
		
		String[] testCases = xlReader.fromSheet("Billing Go Live Test Data").getDataArrayFromRow("Fields");

		String[][] testCases2DArray = new String[testCases.length][1];
		
		for(int i=0;i<(testCases.length) ; i++){
			testCases2DArray[i][0] = testCases[i];
		}
		
		return testCases2DArray;
		
	}
	

	@DataProvider
	public Object[][] dataProvider(){
		
		ExcelReader xlReader = new ExcelReader(ConfigProperties.TEST_DATA_FILE_LOCATION + "//" 
				+ ConfigProperties.TEST_DATA_FILE_NAME);
		
		String[] testCases = xlReader.fromSheet("GoLiveSingleProduct").getDataArrayFromColumn("Test Case ID");
		
		String[][] testCases2DArray = new String[testCases.length][1];
		
		for(int i=0;i<(testCases.length) ; i++){
			testCases2DArray[i][0] = testCases[i];
		}
		
		return testCases2DArray;
		
	}

}
