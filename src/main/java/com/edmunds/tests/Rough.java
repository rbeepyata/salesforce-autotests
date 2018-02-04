package com.edmunds.tests;

import com.epam.automation.core.objectrepository.ExcelObjectRepository;

public class Rough {

	public static void main(String[] args) {

		ExcelObjectRepository xor = new ExcelObjectRepository();
		xor.loadObjectRepository("src\\main\\resources\\or\\locators.xlsx");
		
		System.out.println(xor.getRepo().get("com.edmunds.pages.LoginPage.Username").getLocator().getLocatorValue());
		
				
		
	}

}
