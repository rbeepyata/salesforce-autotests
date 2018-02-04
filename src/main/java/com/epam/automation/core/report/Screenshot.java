package com.epam.automation.core.report;

import java.io.File;

public class Screenshot {

	private String screenshotFilePath;
	private File screenshotFile;
	
	public Screenshot(String screenshotFilePath, File screenshotFile){
		this.screenshotFilePath = screenshotFilePath;
		this.screenshotFile = screenshotFile;
	}
	
	public String getScreenshotFilePath() {
		return screenshotFilePath;
	}
	public void setScreenshotFilePath(String screenshotFilePath) {
		this.screenshotFilePath = screenshotFilePath;
	}
	public File getScreenshotFile() {
		return screenshotFile;
	}
	public void setScreenshotFile(File screenshotFile) {
		this.screenshotFile = screenshotFile;
	}
	
	
}
