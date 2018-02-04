package com.edmunds.pages;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */

public enum OpportunityType {
	New,
	Used,
	Price_Promise,
	Price_Promise_Lease,
	UsedPlus,
	Trade_In_Tool,
	Reviews_Widget,
	CarCode_Text,
	My_Appraise,
	Edmunds_Express,
	Site_Enhancer,
	Ad_Solutions_Used;
	
	public static OpportunityType convertStringToOpportunityType(String opportunityType){
		switch (opportunityType.toLowerCase().replace(" ", "").replace("-", "")) {
		case "new":
			return OpportunityType.New;
		case "used":
			return OpportunityType.Used;
		case "pricepromise":
			return OpportunityType.Price_Promise;
		case "pricepromiselease":
			return OpportunityType.Price_Promise_Lease;
		case "usedplus":
			return OpportunityType.UsedPlus;
		case "tradeintool":
			return OpportunityType.Trade_In_Tool;
		case "reviewswidget":
			return OpportunityType.Reviews_Widget;
		case "carcodetext":
			return OpportunityType.CarCode_Text;
		case "myappraise":
			return OpportunityType.My_Appraise;
		case "edmundsexpress":
			return OpportunityType.Edmunds_Express;
		case "siteenhancer":
			return OpportunityType.Site_Enhancer;
		case "adsolutionsused":
			return OpportunityType.Ad_Solutions_Used;
		default:
			return OpportunityType.New;
		}
	}

}
