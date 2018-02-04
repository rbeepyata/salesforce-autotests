package com.epam.automation.core.objectrepository;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.epam.automation.core.element.Locator;
import com.epam.automation.core.element.LocatorType;
import com.epam.automation.utilities.ExcelReader;


public class ExcelObjectRepository implements ObjectRepository{
	
	public final static Logger logger = Logger.getLogger(ExcelObjectRepository.class);
	
	private Map<String, PageObject> repo;

	@Override
	public void loadObjectRepository(String objectRepositoryFilePath) {
		ExcelReader xlReader = new ExcelReader(objectRepositoryFilePath);
		repo = new HashMap<>();
		
		String[][] sheetData = xlReader.getDataArrayBySheet("Locators");
		
		
		for (int i = 0; i < sheetData.length; i++) {
			
			String packageName = sheetData[i][0];
			String pageName = sheetData[i][1];
			String pageObjectName = sheetData[i][2];
			String locatorType_string = sheetData[i][3];
			String locatorValue = sheetData[i][4];
			
			String uniquePageObjectName_key = packageName + "." + pageName + "." + pageObjectName;
			logger.debug(uniquePageObjectName_key + ": " + locatorType_string );
			LocatorType locatorType = LocatorType.convertStringToLocatorType(locatorType_string);
			Locator locator = new Locator(locatorType, locatorValue);
			PageObject pageObject_value = new PageObject(uniquePageObjectName_key, locator);
			
			repo.put(uniquePageObjectName_key, pageObject_value);
		}
	}
	
	@Override
	public Map<String, PageObject> getRepo() {
		return repo;
	}

	@Override
	public void setRepo(Map<String, PageObject> repo) {
		this.repo = repo;
	}

	@Override
	public PageObject getPageObject(String pageObjectName) {
		return repo.get(pageObjectName);
	}

	@Override
	public void addPageObject(PageObject pageObject) {
		repo.put(pageObject.getPageObjectName(), pageObject);
	}

	
}
