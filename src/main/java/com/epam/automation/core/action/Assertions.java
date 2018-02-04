package com.epam.automation.core.action;

import org.apache.log4j.Logger;

import com.epam.automation.core.report.TestReporter;
/**
 * The {@code Assertions} class provides a single means to make verifications in the test classes.
 * Contains static methods to verify actual results with expected results and do both logging and reporting.
 * 
 * <p>Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 *
 */
public class Assertions {
	private static Logger logger = Logger.getLogger(Assertions.class.getName());
	private static TestReporter testReporter = null;
	
	public static void init(TestReporter testReporter ){
		Assertions.testReporter = testReporter;
	}
	
	public static void verify(boolean expectedCondition, String descriptionForPass, String descriptionForFail) {
		if (expectedCondition) {
			logger.info("********** Verification Successful ********** : " + descriptionForPass);
			testReporter.passTestStep("********** Verification Successful ********** : " + descriptionForPass);
		} else {
			logger.error("!!!!!!!!!! Verification Failed !!!!!!!!!! : " + descriptionForFail);
			testReporter.failTestStep("!!!!!!!!!! Verification Failed !!!!!!!!!! : " + descriptionForFail);
		}
	}
	
	public static boolean verify(String elementName, String expectedValue, String actualValue) {
		boolean actionSucceeded = false;
		try {
			logger.info("Verifying " + elementName + " ...");
			actionSucceeded = expectedValue.equals(actualValue);
			if (actionSucceeded) {
				logger.info("********** Verification Successful ********** : '" + elementName + "' is: " + expectedValue + ".");
				testReporter.passTestStep("********** Verification Successful ********** : '" + elementName + "' is: " + expectedValue + ".");
			} else {
				logger.error("!!!!!!!!!! Verification Failed !!!!!!!!!! : '" + elementName + "'! Expected: '" + expectedValue + "' but found: " + "'" + actualValue + "'.");
				testReporter.failTestStep("!!!!!!!!!! Verification Failed !!!!!!!!!! : '" + elementName + "'! Expected: '" + expectedValue + "' but found: " + "'" + actualValue + "'.");
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionSucceeded;
	}
	
	public static boolean verify(String elementName, boolean expectedValue, boolean actualValue) {
		boolean actionSucceeded = false;
		try {
			logger.info("Verifying " + elementName + " ...");
			if (expectedValue==actualValue) {
				logger.info("********** Verification Successful ********** : '" + elementName + "' is: " + expectedValue + ".");
				testReporter.passTestStep("********** Verification Successful ********** : '" + elementName + "' is: " + expectedValue + ".");
			} else {
				logger.error("!!!!!!!!!! Verification Failed !!!!!!!!!! : '" + elementName + "'! Expected: '" + expectedValue + "' but found: " + "'" + actualValue + "'.");
				testReporter.failTestStep("!!!!!!!!!! Verification Failed !!!!!!!!!! : '" + elementName + "'! Expected: '" + expectedValue + "' but found: " + "'" + actualValue + "'.");
			}
		} catch (Exception e) {
			logger.info("Exception Details: " + e.getMessage());
		}
		return actionSucceeded;
	}
	
	public static void info(String details) {
		logger.info(details);
		testReporter.info(details);
	}

	public static void info(String details, Logger logger) {
		logger.info(details);
		testReporter.info(details);
	}

	public static void pass(String descriptionForPass) {
		logger.info("********** Successful ********** : " + descriptionForPass);
		testReporter.passTestStep("********** Successful ********** : " + descriptionForPass);
	}

	public static void fail(String descriptionForFail) {
		logger.error("!!!!!!!!!! Failed !!!!!!!!!!: " + descriptionForFail);
		testReporter.failTestStep("!!!!!!!!!! Failed !!!!!!!!!! : " + descriptionForFail);
	}

	public static void log(boolean expectedCondition, String descriptionForPass, String descriptionForFail) {
		if (expectedCondition) {
			logger.info(descriptionForPass);
		} else {
			logger.error(descriptionForFail);
		}
	}

} 