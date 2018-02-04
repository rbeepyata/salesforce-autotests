package com.epam.automation.core.objectrepository;

import java.util.Map;

public interface ObjectRepository {
	
	void loadObjectRepository(String objectRepositoryFilePath);
	Map<String, PageObject> getRepo();
	void setRepo(Map<String, PageObject> repo);

	PageObject getPageObject(String pageObjectName);
	void addPageObject(PageObject pageObject);
	
}
