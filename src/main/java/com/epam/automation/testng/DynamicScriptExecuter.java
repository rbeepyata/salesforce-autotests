package com.epam.automation.testng;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;

/**
 * Class to generate testNg class file dynamically based on
 * "TestClassDetails.xls" class files selection
 * 
 *
 *
 */
public class DynamicScriptExecuter {

	static Properties prop;
	static String executionType = "";
	String environment = null;
	/**
	 * Factory method to send dynamically generated test classes to testNg
	 * @param environment 
	 * 
	 * @return
	 */
	@Factory
	@Parameters("environment")	//Implemented for jenkins
	public Object[] setUp(String environment) {
		this.environment = environment;
//		readTestCasePropertyFile();
		List<Object> list = new ArrayList<Object>();

		try {
			// To read classname
			if(environment.equalsIgnoreCase("local")){
			list = GetExcutionClass.getClassList();
			}
			//included changes related to jenkins
			else{
			list = GetExcutionClass.getClassListfromJenkins();
			System.out.println("::::::::Class List= "+list.toString());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Object[] data = list.toArray();
		return data;
	}

	/**
	 * Method to read test case and property from "Sample Tests" folder
	 */
	/*public void readTestCasePropertyFile() {
		File folder = new File("./Sample Tests");
		File[] listOfFiles = folder.listFiles();

		LinkedHashMap<ActionType, Properties> eventPropMap = new LinkedHashMap<ActionType, Properties>();
		for (File testCaseFile : listOfFiles) {
			Properties params = readPropertyFile(testCaseFile);
			ActionType actionType = ActionType.fromString(params, "action");
			eventPropMap.put(actionType, params);
		}

		GlobalVariables.getGV().setEventPropMap(eventPropMap);

	}*/

	/**
	 * Method to read property file
	 * 
	 * @param testCaseFile
	 * @return Properties
	 */
	public static Properties readPropertyFile(File testCaseFile) {
		Properties params = new Properties();
		try {
			FileReader fr = new FileReader(testCaseFile);
			params.load(fr);

		} catch (IOException e) {
			System.err.println("Error reading from the test file. File name: "
					+ testCaseFile.getName());
		}
		return params;

	}

}