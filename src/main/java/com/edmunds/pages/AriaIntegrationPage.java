package com.edmunds.pages;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.Element;
import com.epam.automation.exception.TestFailedException;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class AriaIntegrationPage extends PageBase {

	public Button buttonStartJitterbitJob = ElementFactory.getButton("Start Jitterbit Job",
			Locator.cssSelector("input[value='Start Jitterbit Job']"));
	
	public Element msgJitterbitJobIsRunning = ElementFactory.getElement("Jitterbit job is running message",
			Locator.xpath("//*[contains(text(),'Jitterbit job is running')]"));
	
	public Element msgJitterbitJobIsOffNow = ElementFactory.getElement("Jitterbit job is off now message",
			Locator.xpath("//*[contains(text(),'Jitterbit job is off now')]"));
	
	

	public void startJitterBitJob() throws TestFailedException {
		buttonStartJitterbitJob.click();
	}
	
	public boolean isJitterBitJobRunning(){
		if (msgJitterbitJobIsRunning.isEnabled())
			return true;
		else
			return false;
	}
	
	public boolean waitForJitterBitJobToComplete(long timeOutInSeconds){
		if (isJitterBitJobRunning()) {
			actions.refresh();
			msgJitterbitJobIsOffNow.waitForItToBeVisible(timeOutInSeconds);
			actions.refresh();
			if (msgJitterbitJobIsOffNow.isDisplayed() && buttonStartJitterbitJob.isEnabled()) {
				System.out.println("Jitterjob is completed.");
				return true;
			} else {
				System.out.println("Timed out in "+timeOutInSeconds+" seconds before Jitterjob could be completed.");
				return false;
			}
		} else {
			System.out.println("Jitterjob is completed.");
			return true;
		}
	}

}
