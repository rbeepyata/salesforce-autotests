package com.epam.automation.core.report.extent;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.epam.automation.core.report.ActionResult;
import com.epam.automation.core.report.Screenshot;
import com.epam.automation.core.report.ScreenshotPolicy;
import com.epam.automation.core.report.TestReporter;
import com.epam.automation.exception.TestFailedException;

public class ExtentTestReporter implements TestReporter {
	
	private static final Logger logger = Logger.getLogger(ExtentTestReporter.class.getName());
	ExtentReports extentReports;
	String testReportFilePath;
	ExtentTest extentTest;
	ScreenshotPolicy screenshotPolicy = null;
	private boolean isInitialized = false;
	private boolean isTestCaseInitialized = false;

	@Override
	public void initTestReporter(String testReportFilePath) {
		initTestReporter(testReportFilePath, ScreenshotPolicy.TAKE_SCREENSHOTS_OF_FAILED_STEPS_ONLY);
	}

	@Override
	public void initTestReporter(String testReportFilePath, ScreenshotPolicy screenshotPolicy) {
        // start reporters
        try {
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(testReportFilePath);
			htmlReporter.loadXMLConfig("src//main//resources//extent-config.xml");
			
			htmlReporter.config().setJS("");
			setScreenshotPolicy(screenshotPolicy);
   
			// create ExtentReports and attach reporter(s)
			extentReports = new ExtentReports();
			extentReports.attachReporter(htmlReporter);
			isInitialized=true;
		} catch (Exception e) {
			logger.error("ExtentTestReporter Initialization failed! " + e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void setScreenshotPolicy(ScreenshotPolicy screenshotPolicy) {
		this.screenshotPolicy = screenshotPolicy;
	}
	
	@Override
	public ScreenshotPolicy getScreenshotPolicy() {
		return screenshotPolicy;
	}
	
	@Override
	public void initTestCase(String testCaseName, String description) {
		extentTest = extentReports.createTest(testCaseName, description);
		setTestCaseInitialized(true);
	}
	
	@Override
	public void initTestCase(String testCaseName, String description, String category) {
		extentTest = extentReports.createTest(testCaseName, description)
				.assignCategory(category);
		setTestCaseInitialized(true);
	}

	@Override
	public void passTestCase(String testCaseName, String... details) {
		extentTest.pass(testCaseName + " passed. ");
	}

	@Override
	public void failTestCase(String testCaseName, String... details) {
		extentTest.fail(testCaseName + " failed. ");
	}

	@Override
	public void closeTestReport() {
		extentReports.flush();
	}

	@Override
	public void launchTestReport() {
		// TODO Auto-generated method stub
	}

	@Override
	public void info(String details) {
		extentTest.info(details);
	}
	
	@Override
	public void passTestStep(String description) {
		extentTest.pass(description);
	}

	@Override
	public void failTestStep(String description) {
		extentTest.fail(description);
	}

	@Override
	public void info(ActionResult actionResult) {
		if(screenshotPolicy.isScreenshotNeededForPassedStep()){
			try {
				Screenshot screenshot = actionResult.getScreenshot();
				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(screenshot.getScreenshotFilePath()).build();
				extentTest.info(actionResult.getDescription(), mediaModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			extentTest.info(actionResult.getDescription());
		}
	}

	@Override
	public void passTestStep(ActionResult actionResult) {
		try {
			if (!isInitialized) {
				throw new TestFailedException();
			}
			if (screenshotPolicy.isScreenshotNeededForPassedStep()) {
				Screenshot screenshot = actionResult.getScreenshot();
				MediaEntityModelProvider mediaModel = MediaEntityBuilder
						.createScreenCaptureFromPath(screenshot.getScreenshotFilePath()).build();
				extentTest.pass(actionResult.getDescription(), mediaModel);
			} else {
				extentTest.pass(actionResult.getDescription());
			}
		} catch (TestFailedException tfe) {
			logger.error("ExtentTestReporter not initialized!" + tfe);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void failTestStep(ActionResult actionResult) {
		if(screenshotPolicy.isScreenshotNeededForFailedStep()){
			try {
				Screenshot screenshot = actionResult.getScreenshot();
//				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(screenshot.getScreenshotFilePath()).build();
				MediaEntityModelProvider mediaModel = MediaEntityBuilder.createScreenCaptureFromPath(screenshot.getScreenshotFile().getAbsolutePath()).build();
				extentTest.fail(actionResult.getDescription(), mediaModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			extentTest.fail(actionResult.getDescription());
		}
	}

	public boolean hasTestCaseInitialized() {
		return isTestCaseInitialized;
	}

	public void setTestCaseInitialized(boolean isTestCaseInitialized) {
		this.isTestCaseInitialized = isTestCaseInitialized;
	}
}
