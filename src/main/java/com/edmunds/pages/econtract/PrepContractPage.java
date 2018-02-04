package com.edmunds.pages.econtract;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Element;
import com.epam.automation.core.element.Image;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class PrepContractPage extends PageBase {

	public Link lnkPrepContract = ElementFactory.getLink("Prep Contract", this);
	public Element chkboxIncludeAvailableProducts = ElementFactory.getElement("Include available products checkbox", this);
	public TextBox txtIncludeAvailableProductsSalesPrice = ElementFactory.getTextBox("Include available products - SalesPrice", this);
	public Element chkboxManagedMessaging = ElementFactory.getElement("Managed Messaging checkbox", this);
	public TextBox txtManagedMessagingSalesPrice = ElementFactory.getTextBox("Managed Messaging SalesPrice", this);
	public Element chkboxSearchNew = ElementFactory.getElement("Search - New checkbox", this);
	public TextBox txtSearchNewSpend = ElementFactory.getTextBox("Search - New - Spend", this);
	public Element chkboxSocialNew = ElementFactory.getElement("Social - New checkbox", this);
	public TextBox txtSocailNewSpend = ElementFactory.getTextBox("Social - New - Spend", this);
	public Element chkboxSocialUsed = ElementFactory.getElement("Social - Used checkbox", this);
	public TextBox txtSocailUsedSpend = ElementFactory.getTextBox("Social - Used - Spend", this);
	public Button btnSave = ElementFactory.getButton("Save", this);
	public Image imgLoading = ElementFactory.getImage("Loading", this);

	
}
