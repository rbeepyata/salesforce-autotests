package com.epam.automation.testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * TestNG utility class that provides various methods to form a TestNG XML to be run.
 * 
 * @author Gallop Solutions
 *
 */
public class TestNGUtil {
    
    static String testDataRelativeFilePath = "";//AllProperties.testDataRelativeFilePath;
	
    public static XmlClass[] getTestClasses(String packagename, List<String> testCaseNames)
            throws Throwable {

        XmlClass[] testClasses = new XmlClass[testCaseNames.size()];
        for (int index = 0; index < testCaseNames.size(); index++) {
            try {

                testClasses[index] = new XmlClass(packagename + "." + (testCaseNames
                        .get(index)));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return testClasses;
    }
 
    public static XmlClass[] getTestClasses(String packagename, List<String> testCaseNames, boolean includeMethods)
            throws Throwable {

        XmlClass[] testClasses = new XmlClass[testCaseNames.size()];
        for (int index = 0; index < testCaseNames.size(); index++) {
            try {

                String className = testCaseNames.get(index);
                testClasses[index] = new XmlClass(packagename + "." + (className));
               
                if(includeMethods){
                    //Add methods to run to each class
                    ArrayList<XmlInclude> methodsToRun = getMethodsToInclude(className);
                    testClasses[index].setIncludedMethods(methodsToRun);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return testClasses;
    }
 
    public static ArrayList<XmlInclude> getMethodsToInclude(String className) throws Throwable{
        
        String sheetName = className;
        List<String> tempList = null;//DataProviderUtil.getTestCasesListToEnable(testDataRelativeFilePath, sheetName);
        ArrayList<XmlInclude> listMethodsToInclude = new ArrayList<XmlInclude>();
        
        for (int i=0; i<tempList.size(); i++)         
            listMethodsToInclude.add(new XmlInclude(tempList.get(i)));
        
        return listMethodsToInclude;
        
    }
    

 
   public static TestNG buildTestNG(String suiteName, String testName, String packageName, List<String> testClassNames, boolean includeMethods) throws Throwable {
        
        //Create an instance on TestNG
        TestNG testNG = new TestNG();
       
        //Create an instance of XML Suite and assign a name for it.
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(suiteName);
       
        //Create an instance of XmlTest and assign a name for it.
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName(testName);
       
        //Create a list which can contain the classes that you want to run.
        List<XmlClass> scriptNames = new ArrayList<XmlClass> ();
        
        for(XmlClass s1 : getTestClasses(packageName, testClassNames, includeMethods)){
        	    	scriptNames.add(s1);
        }
        
         //Assign that to the XmlTest Object created earlier.
        xmlTest.setXmlClasses(scriptNames);
       
         //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> xmlTests = new ArrayList<XmlTest>();
        xmlTests.add(xmlTest);
       
        //add the list of tests to your Suite.
        xmlSuite.setTests(xmlTests);
       
        //Add the suite to the list of suites.
        List<XmlSuite> testNgSuites = new ArrayList<XmlSuite>();
        testNgSuites.add(xmlSuite);
       
        //Set the list of Suites to the testNG object you created earlier.
        testNG.setXmlSuites(testNgSuites);
       
        //invoke run() - this will run your class.
        //testNG.run();
        
        return testNG;
       
        }
   
}