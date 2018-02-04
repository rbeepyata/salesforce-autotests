package com.epam.automation.core;

import com.epam.automation.core.action.PageActions;

/**
 * The {@code Tool} class represents any automation tool in general. 
 * In this framework's context, it is a web automation tool like Selenium.  
 * 
 * <p>Epam Automation - Core </p>
 * @author Raghunandan Beepyata
 *	
 */
public class Tool {
	
	private String toolName;
	private PageActions actions;
	
	public Tool(String toolName, PageActions actions) {
		this.toolName = toolName;
		this.actions = actions;
	}
	
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public PageActions getActions() {
		return actions;
	}
	public void setActions(PageActions actions) {
		this.actions = actions;
	}

}
