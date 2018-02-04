package com.epam.automation.core.objectrepository;

import com.epam.automation.core.element.Locator;

public class PageObject {
	
	private String pageObjectName;
	private Locator locator;
	
	public PageObject(String pageObjectName, Locator locator) {
		this.pageObjectName = pageObjectName;
		this.locator = locator;
	}
	
	public String getPageObjectName() {
		return pageObjectName;
	}
	
	public void setPageObjectName(String pageObjectName) {
		this.pageObjectName = pageObjectName;
	}
	
	public Locator getLocator() {
		return locator;
	}
	
	public void setLocator(Locator locator) {
		this.locator = locator;
	} 

}
