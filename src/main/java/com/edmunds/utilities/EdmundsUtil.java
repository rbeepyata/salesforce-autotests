package com.edmunds.utilities;

import java.text.DecimalFormat;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.epam.automation.utilities.CalendarUtil;

public class EdmundsUtil {
	private static Logger logger = Logger.getLogger(EdmundsUtil.class.getName());
	
	public static Double getUnitsForToday(){
		Double d1 = (double) CalendarUtil.getNumberOfDaysLeftInTheCurrentMonth();
		logger.info("Units Calculation: Number of days left in the current month = " + d1);
		Double d2 = (double) CalendarUtil.getLastDayOfTheMonth();
		logger.info("Units Calculation: Last day of the current month = " + d2);
		Double unitsForToday = d1/d2; 
		logger.info("Units Calculation: Units calculated for today = " + unitsForToday);
		
		return unitsForToday;
	}
	
	public static Double getUnitsForToday(TimeZone timeZone){
		Double d1 = (double) CalendarUtil.getNumberOfDaysLeftInTheCurrentMonth(timeZone);
		logger.info("Units Calculation: Number of days left in the current month = " + d1);
		Double d2 = (double) CalendarUtil.getLastDayOfTheMonth();
		logger.info("Units Calculation: Last day of the current month = " + d2);
		Double unitsForToday = d1/d2; 
		logger.info("Units Calculation: Units calculated for today = " + unitsForToday);
		
		return unitsForToday;
	}
	
	public static String getDecimalFormat(String format, Double doubleNo) {
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return decimalFormat.format(doubleNo);
	}
	
	public static String getDealershipId(String currentURL) {
		int startIndexOfID = currentURL.indexOf("id=") + 3;
		int endIndexOfID = currentURL.indexOf("&");
		return currentURL.substring(startIndexOfID, endIndexOfID)	;
	}

	public static String getOpportunityId(String currentURL) {
		int startIndexOfID = currentURL.indexOf("id=") + 3;
		int endIndexOfID = currentURL.indexOf("&");
		return currentURL.substring(startIndexOfID, endIndexOfID)	;
	}

	public static String getBillingObjectId(String currentURL) {
		int startIndexOfID = currentURL.indexOf(".com/") + 5;
		return currentURL.substring(startIndexOfID)	;
	}
}
