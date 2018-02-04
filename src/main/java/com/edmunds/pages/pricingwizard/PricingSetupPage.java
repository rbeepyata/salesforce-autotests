package com.edmunds.pages.pricingwizard;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.ListBox;
import com.epam.automation.core.element.TextBox;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public class PricingSetupPage extends PageBase {
	
	public TextBox txtPrecisionSearchDiscountPercent = ElementFactory.getTextBox("Precision Search - Discount %", this);
	public TextBox txtPDPHomeDiscountPercent = ElementFactory.getTextBox("PDP Home - Discount %", this);
	public TextBox txtUsedInventoryDiscountPercent = ElementFactory.getTextBox("Used Inventory - Discount %", this);
	public TextBox txtPricePromiseDiscountPercent = ElementFactory.getTextBox("Price Promise - Discount %", this);
	public TextBox txtUsedPlusDiscountPercent = ElementFactory.getTextBox("UsedPlus - Discount %", this);
	public TextBox txtMyAppraiseTradeInDiscountPercent = ElementFactory.getTextBox("My Appraise Trade-In - Discount %", this);
	public TextBox txtReviewsWidgetDiscountPercent = ElementFactory.getTextBox("Reviews Widget - Discount %", this);
	public TextBox txtPricePromiseLeaseDiscountPercent = ElementFactory.getTextBox("Price Promise - Lease - Discount %", this);
	public TextBox txtCarCodeTextDiscountPercent = ElementFactory.getTextBox("CarCode Text - Discount %", this);
	public TextBox txtMyAppraiseDiscountPercent = ElementFactory.getTextBox("My Appraise - Discount %", this);
	public TextBox txtEdmundsExpressDiscountPercent = ElementFactory.getTextBox("Edmunds Express - Discount %", this);
	public ListBox dropdownUsedPlusBillingType = ElementFactory.getListBox("UsedPlus - Billing Type", this);
	public ListBox dropdownUsedPlusInventoryDiscounted = ElementFactory.getListBox("UsedPlus - Inventory Discounted", this);
	public ListBox dropdownPDPHomeRadius = ElementFactory.getListBox("PDP Home - Radius", this);
	public Button btnConfirmAndContinue = ElementFactory.getButton("Confirm and Continue", this);
	public Button btnConfirmAndContinueToTheFW = ElementFactory.getButton("Confirm and Continue to the FW", this);

}
