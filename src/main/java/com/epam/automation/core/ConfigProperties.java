package com.epam.automation.core;

import java.util.Map;

import com.epam.automation.utilities.ExcelReader;
import com.epam.automation.utilities.PropertyUtil;
import com.epam.automation.utilities.TextUtil;

/**
 * The {@code ConfigProperties} abstract class is a single point of reference to get all the required configuration related properties.
 * 
 * <p> Epam Automation - Core </p>
 * @author Raghunandan_Beepyata
 */
public abstract class ConfigProperties {
	
	private static final String CONFIG_PROPERTIES_FILEPATH = ".\\src\\main\\resources\\config.properties";
	private static final PropertyUtil PROPERTIES_UTIL = new PropertyUtil(CONFIG_PROPERTIES_FILEPATH);

    public static final String TEST_DATA_FILE_TYPE =  PROPERTIES_UTIL.getProperty("testDataFileType");
    public static final String TEST_DATA_FILE_LOCATION =  PROPERTIES_UTIL.getProperty("testDataFileLocation");
    public static final String TEST_DATA_FILE_NAME =  PROPERTIES_UTIL.getProperty("testDataFileName");
	
    public static final String TEST_REPORT_FILE_TYPE =  PROPERTIES_UTIL.getProperty("testReportType");
    public static final String TEST_REPORT_FILE_LOCATION =  PROPERTIES_UTIL.getProperty("testReportFileLocation");
    public static final String TEST_REPORT_FILE_NAME =  PROPERTIES_UTIL.getProperty("testReportFileName");

	public static final ExcelReader XL_READER = new ExcelReader(TEST_DATA_FILE_LOCATION + "\\" + TEST_DATA_FILE_NAME);
	protected static final Map<String, String> CONFIG_DATA = XL_READER.fromSheet("Configuration").getDataFromColumn("Value");
	
	public static final String ENVIRONMENT =  CONFIG_DATA.get("Environment");
	public static final String BROWSER =  CONFIG_DATA.get("Browser");
    public static final String URL =  CONFIG_DATA.get("URL");
    public static final String USERNAME = CONFIG_DATA.get("Username");
    public static final String PASSWORD = CONFIG_DATA.get("Password");
    public static final Long IMPLICIT_WAIT_TIME = Long.valueOf(CONFIG_DATA.get("Implicit Wait Time"));
    public static final Long EXPLICIT_WAIT_TIME = Long.valueOf(CONFIG_DATA.get("Explicit Wait Time"));
    public static final Boolean HIGHLIGHT_ELEMENT = Boolean.valueOf(CONFIG_DATA.get("Highlight Element")); 
    public static final Boolean LAUNCH_TESTREPORT_AFTER_EXECUTION =  Boolean.valueOf(CONFIG_DATA.get("Launch Test Report After Execution")); 
    public static final String TESTER_NAME = TextUtil.trimStringLength(CONFIG_DATA.get("Tester Name"), 31); //Used as excel sheet name; sheet name limited to 31 chars
    public static final Boolean RETAIN_OLD_RESULTS = Boolean.valueOf(CONFIG_DATA.get("Retain Old Results"));
}
