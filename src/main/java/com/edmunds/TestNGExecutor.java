package com.edmunds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Factory;


public class TestNGExecutor {

	@Factory
	public Object[] factory(){
		Object[] testClassesToExecute = getTestClassesToExecute();
		return testClassesToExecute;
	}
	
	
	/*@Factory
	@Parameters({"environment", "platform", "browser"})
	public Object[] factory(Stringenvironment", "platform", "browser){
		Object[] testClassesToExecute = getTestClassesToExecute();
		return testClassesToExecute;
	}
	
*/	
/*	@Factory
	@Parameters("environment")
	public Object[] setUp(String environment) {
		this.environment = environment;
		readTestCasePropertyFile();
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
	
*/	
	
	public static Object[] getTestClassesToExecute() {
		Map<String, String> executeColumnData = 
				EdmundsConfigProperties.XL_READER.fromSheet("Use Cases List").getDataFromColumn("Execute?");
		Map<String, String> testClassNameColumnData = 
				EdmundsConfigProperties.XL_READER.fromSheet("Use Cases List").getDataFromColumn("TestClassName");

		List<Object> useCasesToExecuteList = new ArrayList<>();
		
		for (Iterator<Entry<String, String>> iterator = executeColumnData.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> entry = iterator.next();
			if (entry.getValue().equals("YES")) {
				
				String packageName = "com.edmunds.tests";
				String className = packageName + "." + testClassNameColumnData.get(entry.getKey());
//				System.out.println(className);
				Object classObj = null;
				try {
					classObj = Class.forName(className).newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				useCasesToExecuteList.add(classObj);	
			}			
		}
		
		return useCasesToExecuteList.toArray();
	}
	
}