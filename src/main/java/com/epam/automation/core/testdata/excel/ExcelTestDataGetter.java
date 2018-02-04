package com.epam.automation.core.testdata.excel;

import java.util.HashMap;
import java.util.Map;

import com.epam.automation.core.testdata.TestData;
import com.epam.automation.core.testdata.TestDataGetter;
import com.epam.automation.utilities.ExcelReader;
//TODO: JavaDocs and comments
/**
 * Epam Automation - Core
 * @author Raghunandan Beepyata
 * 
 */

public class ExcelTestDataGetter implements TestDataGetter {
	
	private ExcelReader excelReader;
	private String testDataLocation;
	private TestData testData;

	@Override
	public void initTestDataGetter(String testDataLocation, String testDataFileName) {
		this.testDataLocation = testDataLocation;
		String testDataFilePath = this.testDataLocation + "\\" + testDataFileName;
		testData = new TestData();
		try {
			excelReader = new ExcelReader(testDataFilePath);
			testData.setTestData(getMapFromExcel(excelReader));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public TestData getTestData() {
		return testData;
	}

	@Override
	public TestData getTestData(String moduleName) {
		TestData testData = new TestData();
		try {
			testData.setTestData(getMapFromExcel(excelReader, moduleName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	@Override
	public TestData getTestData(String moduleName, String testCaseName) {
		TestData testData = new TestData();
		try {
			testData.setTestData(getMapFromExcel(excelReader, moduleName, testCaseName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

	@Override
	public void setTestDataLocation(String testDataLocation) {
		this.testDataLocation = testDataLocation;
	}

	Map<String, String> getMapFromExcel(ExcelReader excelReader) {
		Map<String, String> map = new HashMap<String, String>();
		map = excelReader.getDataFromColumn("Value");
		return map;
	}

	Map<String, String> getMapFromExcel(ExcelReader excelReader, String sheetName) {
		Map<String, String> map = new HashMap<String, String>();
		map = excelReader.fromSheet(sheetName).getDataFromColumn("Value");
		return map;
	}
	
	/*Map<String, String> getMapFromExcel(ExcelReader excelReader, String sheetName, String rowName) {
		Map<String, String> map = new HashMap<String, String>();
		map = excelReader.fromSheet(sheetName).getDataFromRow(rowName);
		return map;
	}*/

	Map<String, String> getMapFromExcel(ExcelReader excelReader, String sheetName, String columnName) {
		Map<String, String> map = new HashMap<String, String>();
		map = excelReader.fromSheet(sheetName).getDataFromColumn(columnName);
		return map;
	}

}
