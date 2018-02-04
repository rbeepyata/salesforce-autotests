package com.edmunds;


import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class Runner {

	public static void main(String[] args) {
		
		TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add("src\\main\\resources\\testng-for-executable.xml");
        testng.setTestSuites(suites);
        testng.run();
		

	}

}
