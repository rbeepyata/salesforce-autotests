package com.epam.automation.core.report;
//TODO: JavaDocs and comments
/**
 * Epam Automation - Core
 * @author Raghunandan Beepyata
 * 
 */

public interface TestReporter {
	
	void initTestReporter(String testReportFilePath);
	void initTestReporter(String testReportFilePath, ScreenshotPolicy screenshotPolicy);

	ScreenshotPolicy getScreenshotPolicy();
	void setScreenshotPolicy(ScreenshotPolicy screenshotPolicy);
	
	void initTestCase(String testCaseName, String description);
	void initTestCase(String testCaseName, String description, String category);
	
	void passTestCase(String testCaseName, String... details);
	void failTestCase(String testCaseName, String... details);
	
	void info(String details);
	void passTestStep(String description);
	void failTestStep(String description);

	void info(ActionResult actionResult);
	void passTestStep(ActionResult actionResult);
	void failTestStep(ActionResult actionResult);
	
	void closeTestReport();
	void launchTestReport();

}
