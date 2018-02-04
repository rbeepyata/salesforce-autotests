package com.edmunds.pages.econtract;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class OpenPSOPage extends PageBase {

	public Link lnkOpenPSO = ElementFactory.getLink("Open PSO", this);
	public Link lnkPSOName = ElementFactory.getLink("PSO Name link", this);
	public TextBox txtPSOName = ElementFactory.getTextBox("PSO Name text", this);
	public Button btnEditName = ElementFactory.getButton("Edit Name", this);
	public Button btnSave = ElementFactory.getButton("Save", this);
	public Button btnExit = ElementFactory.getButton("Exit", this);

}
