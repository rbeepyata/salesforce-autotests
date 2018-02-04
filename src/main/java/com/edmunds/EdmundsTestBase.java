package com.edmunds;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.epam.automation.core.ConfigProperties;
import com.epam.automation.core.TestBase;
import com.epam.automation.core.report.ScreenshotPolicy;
import com.epam.automation.utilities.AutomationTools;
/**
 * Epam Automation - Core
 * @author Raghunandan Beepyata
 * 
 */

public abstract class EdmundsTestBase extends TestBase {

	public final static Logger logger = Logger.getLogger(EdmundsTestBase.class);

	@BeforeSuite
	protected void beforeSuite() {
		//Initialize Logger
		initLogger("src\\main\\resources\\log4j.properties");

		//Instantiate and initialize Test Reporter
		initTestReporter(ConfigProperties.TEST_REPORT_FILE_TYPE, "EdmundsTestResults.html", ScreenshotPolicy.DO_NOT_TAKE_SCREENSHOTS);

		//Instantiate and initialize Object Repository
		initObjectRepository("excel", "src\\main\\resources\\objectrepository\\ObjectRepository.xlsx");
	}

	@BeforeMethod
	@Parameters({"environment", "platform", "browser"})
	protected void beforeMethod(String environment, String platform, String browser) {
		// Instantiate and initialize Driver
		logger.info("Launching the browser...");
//		initTool(AutomationTools.getTool("Selenium", ConfigProperties.ENVIRONMENT, ConfigProperties.BROWSER, testReporter));
		initTool(AutomationTools.getTool("Selenium", environment, platform, browser, testReporter));
		
		// Instantiate and initialize Test Data Getter
		initTestDataGetter(ConfigProperties.TEST_DATA_FILE_TYPE);
		
		maximizeBrowser();
		openUrl(ConfigProperties.URL);
	}

	@AfterMethod
	protected void afterMethod() {
		closeCurrentWindow();
		closeAllWindows();
	}

	@AfterSuite
	protected void afterSuite() throws Throwable {
		try {
			closeTestReport();
			launchTestReport();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			killChromeInWindows();
		}
	}
}