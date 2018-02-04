package com.epam.automation.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Epam Automation - Utilities
 * @author Raghunandan Beepyata
 * 
 */

public class TextUtil {
	
	public static String getCurrentDateTimeStamp(SimpleDateFormat simpleDateFormat){
		return (simpleDateFormat.format(new Date()))
					.replace(" ", "_")
					.replace("/", "_")
					.replace(":", "_");
	}
	
	public static String getCurrentDateTimeStamp(){
		return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))
					.replace(" ", "")
					.replace("/", "")
					.replace(":", "");
	}
	
	public static String getCurrentDateTimeStamp(String delimiter) {
		return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))
					.replace(" ", delimiter)
					.replace("/", delimiter)
					.replace(":", delimiter);
	}
	
	public static String getCurrentTimeStamp(){
		return (new SimpleDateFormat("HH:mm:ss").format(new Date()))
					.replace(":", "");
	}
	
	public static String getCurrentTimeStamp(String delimiter){
		return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()))
					.replace(" ", delimiter)
					.replace("/", delimiter)
					.replace(":", delimiter);
	}
	
	public static String getAutoPrefixWithDateTimeStamp(String name){
		return "Auto" + TextUtil.getCurrentDateTimeStamp() + "_" + name;
	}

	public static String getAutoPrefixWithDateTimeStamp(String name, String dateTimeStamp){
		return "Auto" + dateTimeStamp + "_" + name;
	}

	public static String getAutoPrefix(String name){
		return "Auto" + "_" + name;
	}

	public static String trimStringLength(String string, int limit) {
		if (string.length()>limit){
			string = string.substring(0, limit-1);
		}
		return string;
	}
	
}
