package com.edmunds.pages.econtract;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Image;
import com.epam.automation.core.element.Link;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class ProductSetupFormPage extends PageBase {

	public Link lnkProductSetupForm = ElementFactory.getLink("Product Setup Form", this);

	public TextBox txtGeneralContactEmail = ElementFactory.getTextBox("General Contact: Email", this);
	public TextBox txtGeneralContactFirstName = ElementFactory.getTextBox("General Contact: First Name", this);
	public TextBox txtGeneralContactLastName = ElementFactory.getTextBox("General Contact: Last Name", this);
	public TextBox txtGeneralContactTitle = ElementFactory.getTextBox("General Contact: Title", this);
	public TextBox txtGeneralContactPhone = ElementFactory.getTextBox("General Contact: Phone", this);
	public TextBox txtTechnicalContactEmail = ElementFactory.getTextBox("Technical Contact: Email", this);
	public TextBox txtTechnicalContactFirstName = ElementFactory.getTextBox("Technical Contact: First Name", this);
	public TextBox txtTechnicalContactLastName = ElementFactory.getTextBox("Technical Contact: Last Name", this);
	public TextBox txtTechnicalContactTitle = ElementFactory.getTextBox("Technical Contact: Title", this);
	public TextBox txtTechnicalContactPhone = ElementFactory.getTextBox("Technical Contact: Phone", this);
	public TextBox txtBillingContactEmail = ElementFactory.getTextBox("Billing Contact: Email", this);
	public TextBox txtBillingContactFirstName = ElementFactory.getTextBox("Billing Contact: First Name", this);
	public TextBox txtBillingContactLastName = ElementFactory.getTextBox("Billing Contact: Last Name", this);
	public TextBox txtBillingContactTitle = ElementFactory.getTextBox("Billing Contact: Title", this);
	public TextBox txtBillingContactPhone = ElementFactory.getTextBox("Billing Contact: Phone", this);
	public TextBox txtDealerContactForTrainingEmail = ElementFactory.getTextBox("Dealer Contact for Training: Email", this);
	public TextBox txtDealerContactForTrainingFirstName = ElementFactory.getTextBox("Dealer Contact for Training: First Name", this);
	public TextBox txtDealerContactForTrainingLastName = ElementFactory.getTextBox("Dealer Contact for Training: Last Name", this);
	public TextBox txtDealerContactForTrainingPhone = ElementFactory.getTextBox("Dealer Contact for Training: Phone", this);
	public Button btnSave = ElementFactory.getButton("Save", this);
	public Image imgLoading = ElementFactory.getImage("Loading", this);
	public Button btnExit = ElementFactory.getButton("Exit", this);
	
}
