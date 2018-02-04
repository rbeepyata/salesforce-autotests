package com.epam.automation.core.report;

public class ActionResult {
	
	private boolean status;
	private String description;
	private boolean hasScreenshot;
	private Screenshot screenshot;
	private Object returnObject;
	private boolean hasReturnObject;
	
	public ActionResult(boolean status) {
		this.setStatus(status);
		this.setDescription("Action status: " + (status?"Passed":"Failed"));
		this.setHasScreenshot(false);
		this.setScreenshot(null);
		this.setHasReturnObject(false);
		this.setReturnObject(null);
	}

	public ActionResult(boolean status, Screenshot screenshot) {
		this.setStatus(status);
		this.setDescription("Action status: " + (status?"Passed":"Failed"));
		this.setHasScreenshot(true);
		this.setScreenshot(screenshot);
		this.setHasReturnObject(false);
		this.setReturnObject(null);
	}
	
/*	public ActionResult(boolean status, String description) {
		this.setStatus(status);
		this.setDescription(description);
		this.setHasScreenshot(false);
		this.setScreenshot(null);
	}
*/
/*	public ActionResult(boolean status, String description, Screenshot screenshot) {
		this.setStatus(status);
		this.setDescription(description);
		this.setHasScreenshot(true);
		this.setScreenshot(screenshot);
	}
*/	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Screenshot getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(Screenshot screenshot) {
		this.screenshot = screenshot;
	}

	public boolean hasScreenshot() {
		return hasScreenshot;
	}

	public void setHasScreenshot(boolean hasScreenshot) {
		this.hasScreenshot = hasScreenshot;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
		this.setHasReturnObject(true);
	}

	public boolean hasReturnObject() {
		return hasReturnObject;
	}

	public void setHasReturnObject(boolean hasReturnObject) {
		this.hasReturnObject = hasReturnObject;
	}

}
