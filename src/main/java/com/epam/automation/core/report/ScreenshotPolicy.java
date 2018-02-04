package com.epam.automation.core.report;

public enum ScreenshotPolicy {
	TAKE_SCREENSHOTS_OF_FAILED_STEPS_ONLY,
	TAKE_SCREENSHOTS_OF_ALL_STEPS,
	DO_NOT_TAKE_SCREENSHOTS;

	public boolean isScreenshotNeededForPassedStep(){
		if (this.equals(TAKE_SCREENSHOTS_OF_ALL_STEPS))
			return true;
		else 
			return false;
	}
	
	public boolean isScreenshotNeededForFailedStep(){
		if (this.equals(TAKE_SCREENSHOTS_OF_FAILED_STEPS_ONLY) || this.equals(TAKE_SCREENSHOTS_OF_ALL_STEPS))
			return true;
		else 
			return false;
	}
	
}
