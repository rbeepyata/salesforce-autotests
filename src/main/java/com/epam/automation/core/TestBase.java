package com.epam.automation.core;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.automation.core.action.Assertions;
import com.epam.automation.core.action.PageActions;
import com.epam.automation.core.objectrepository.ExcelObjectRepository;
import com.epam.automation.core.objectrepository.ObjectRepository;
import com.epam.automation.core.report.ScreenshotPolicy;
import com.epam.automation.core.report.TestReporter;
import com.epam.automation.core.report.excel.ExcelTestReporter;
import com.epam.automation.core.report.extent.ExtentTestReporter;
import com.epam.automation.core.testdata.TestDataGetter;
import com.epam.automation.core.testdata.excel.ExcelTestDataGetter;
import com.epam.automation.utilities.FileUtil;
import com.epam.automation.utilities.TextUtil;


/**
 * The {@code TestBase} abstract class is the base class for all the tests.
 * This class has methods that provide all the basic requirements for running tests.
 * Has methods that support the life cycle methods for a test like setup and tear down activities.
 * Has methods that mostly instantiate and initialize the various components of the framework. 
 *  * 
 * <p>This class is extended by application specific base class for test classes.</p> 
 *    
 * <p>Epam Automation - Core </p>
 * @author Raghunandan_Beepyata
 *
 */
public abstract class TestBase {
	
	private static Logger logger = Logger.getLogger(TestBase.class);
	private static Tool tool = null;
	protected static PageActions actions;	//Page Actions
	public static TestDataGetter testDataGetter = null;
	public static ObjectRepository objectRepository = null;
	public static ScreenshotPolicy screenshotPolicy;
	public static TestReporter testReporter = null;
	
	public static Cleaner cleaner = null;
	protected static String testReportFilePath = null;

	protected void initLogger(String log4jPropertiesFilePath) {
		PropertyConfigurator.configure(log4jPropertiesFilePath);
	}

	protected void initTestReporter(String testReportType, String testReportFileName, ScreenshotPolicy screenshotPolicy) {
		if(ConfigProperties.RETAIN_OLD_RESULTS) {
//			FileUtil.deleteDirectory(ConfigProperties.TEST_REPORT_FILE_LOCATION);
			String[] temp = testReportFileName.split("\\.");
			String fileNameWithoutExtension = temp[0];
			String extension = "." + temp[1];
			String timeStamp = TextUtil.getCurrentDateTimeStamp("-");
			testReportFilePath = ConfigProperties.TEST_REPORT_FILE_LOCATION 
					+"//"+ fileNameWithoutExtension +"-"+ timeStamp + extension;
		} else {
			FileUtil.deleteDirectory(ConfigProperties.TEST_REPORT_FILE_LOCATION);
			testReportFilePath = ConfigProperties.TEST_REPORT_FILE_LOCATION +"//"+ testReportFileName;
		}
		
		FileUtil.makeParentDirectories(testReportFilePath);
		switch (testReportType) {
		case "excel":
			testReporter = new ExcelTestReporter();
			testReporter.initTestReporter(testReportFileName, screenshotPolicy);
			break;
		case "extent":
			testReporter = new ExtentTestReporter();
			testReporter.initTestReporter(testReportFilePath, screenshotPolicy);			
		}
	}
	
	protected void initTool(Tool tool){
		logger.info("Initializing tool ...");
		TestBase.tool = tool;
		if (null==objectRepository) {
			initActions(TestBase.tool.getActions());
		} else {
			initActions(objectRepository, TestBase.tool.getActions());
		}
		initAssertions();
	}
	
	private void initActions(PageActions actions) {
		PageBase.initPageBase(actions, testReporter);
		TestBase.actions = PageBase.actions;
	}

	private void initActions(ObjectRepository objectRepository, PageActions actions) {
		PageBase.initPageBase(objectRepository, actions, testReporter);
		TestBase.actions = PageBase.actions;
	}

	private void initAssertions() {
		Assertions.init(testReporter);
	}
	
	protected void initTestDataGetter(String testDataFileType) {
		// Initialize Test Data
		switch (testDataFileType) {
		case "properties":
			//TODO
			break;
		case "excel":
			testDataGetter = new ExcelTestDataGetter();
			break;
		default:
			//TODO
			testDataGetter = new ExcelTestDataGetter();
		}
		testDataGetter.initTestDataGetter(ConfigProperties.TEST_DATA_FILE_LOCATION, ConfigProperties.TEST_DATA_FILE_NAME);
	}
	
	protected void initObjectRepository(String objectRepositoryType, String objectRepositoryFilePath) {
		// Initialize Object Repository
		switch (objectRepositoryType) {
		case "excel":
			objectRepository = new ExcelObjectRepository();
		default:
			//TODO
			objectRepository = new ExcelObjectRepository();
		}
		objectRepository.loadObjectRepository(objectRepositoryFilePath);
	}

	protected void initCleaner(Cleaner cleaner) {
		TestBase.cleaner = cleaner;
		TestBase.cleaner.init();
	}
	
	protected void maximizeBrowser() {
		actions.maximizeWindow();
	}
	
	protected void openUrl(String url) {
		actions.get(url);
	}
	
	protected void closeCurrentWindow() {
		actions.closeCurrentWindow();
	}
	
	protected void closeAllWindows() {
		actions.closeAllWindows();
	}
	
	protected void launchTestReport() {
		if(ConfigProperties.LAUNCH_TESTREPORT_AFTER_EXECUTION) {
			File htmlFile = new File(testReportFilePath);
			try {
				Desktop.getDesktop().browse(htmlFile.toURI());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	protected void killChromeInWindows() {
		if (ConfigProperties.BROWSER.equalsIgnoreCase("chrome")) {
			try {
				Runtime.getRuntime().exec("taskkill -f /im chromedriver.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void closeTestReport(){
		testReporter.closeTestReport();
	}
	
	
}
