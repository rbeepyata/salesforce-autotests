package com.epam.automation.utilities;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.edmunds.EdmundsTestBase;

/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class CalendarUtil {
	
	final static Logger logger = Logger.getLogger(CalendarUtil.class);
	
	public static int getNumberOfDaysLeftInTheCurrentMonth() {

		LocalDate today = LocalDate.now();
		Period period = Period.between(today, today.with(TemporalAdjusters.lastDayOfMonth()));
		int numberOfDays = period.getDays() + 1;

		return numberOfDays;
	}
	
	public static int getNumberOfDaysLeftInTheCurrentMonth(TimeZone timeZone) {

		LocalDate today = LocalDate.now(timeZone.toZoneId());
		Period period = Period.between(today, today.with(TemporalAdjusters.lastDayOfMonth()));
		int numberOfDays = period.getDays() + 1;

		return numberOfDays;
	}

	
	public static int getLastDayOfTheMonth() {
		LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		return lastDayOfMonth.getDayOfMonth();
	}
	
	public static LocalDate addDaysToToday(long numberOfDaysToAdd) {
		return LocalDate.now().plusDays(numberOfDaysToAdd);
	}	
	
	public static String addDaysToToday(long numberOfDaysToAdd, String requiredDateFormat) {
		
		LocalDate requiredDate = addDaysToToday(numberOfDaysToAdd);
		DateTimeFormatter requiredFormat = DateTimeFormatter.ofPattern(requiredDateFormat);

		return requiredDate.format(requiredFormat);
	}	
	
	public static String convertDateFormat(String date, String currentFormatOfDate, String requiredFormatOfDate){
		logger.info("Converting the date: " + date + " from the current format: " + currentFormatOfDate + " to the required format: " + requiredFormatOfDate);
		DateTimeFormatter 	currentFormat = DateTimeFormatter.ofPattern(currentFormatOfDate),
							requiredFormat = DateTimeFormatter.ofPattern(requiredFormatOfDate);
//		String requiredDate = LocalDate.parse(date, currentFormat).format(requiredFormat);
		LocalDate localDate = LocalDate.parse(date, currentFormat);
		String requiredDate = localDate.format(requiredFormat);
		logger.info("Converted date: " + requiredDate);
		return requiredDate;
	}
	
	public static LocalDate convertStringToDate(String date, String formatOfDate){
		logger.info("Converting the string: '" + date + "' with assumed format of : '" + formatOfDate + "' to the Date data type...");
		
		String[] temp = date.split("/");
		
		if(temp.length>2) {
			String month 	= temp[0].length()==1	?	"0"+temp[0]	:	temp[0] ;
			String day 		= temp[1].length()==1	? 	"0"+temp[1]	:	temp[1] ;
			date = month +"/" + day +"/"+ temp[2];
		} else {
			return null;
		}
						
		DateTimeFormatter 	currentFormat = DateTimeFormatter.ofPattern(formatOfDate);
		LocalDate localDate = LocalDate.parse(date, currentFormat);
		logger.info("Converted date: " + localDate);
		return localDate;
	}
	
	public static String getTodaysDate(String formatOfDate) {
		logger.info("Getting today's date in the format: '" + formatOfDate + "' ...");

		DateTimeFormatter 	requiredFormat = DateTimeFormatter.ofPattern(formatOfDate);
		String date = LocalDate.now().format(requiredFormat);
		logger.info("Today's date: '" + date + "' (in the format: '" + formatOfDate + "').");
		return date;
	}
	
}
