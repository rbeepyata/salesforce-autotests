package com.epam.automation.selenium;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Generic class that models a web table.
 * Provides method to fetch web elements from a specified web table. 
 * 
 * @author Sulaksh
 *
 */
public class WebTable {   
    
    public static HashMap<Integer, List<WebElement>> getWebTable (WebDriver driver, String xpathOfTable) throws Throwable {
        
        HashMap<Integer, List<WebElement>> webTable = new HashMap<Integer, List<WebElement>>() ;
        
        //This method takes the xpath of table i.e. <table>. 
        //Our intention is to get all data from table/tbody. So suffix tbody to the xpath. 
        xpathOfTable = xpathOfTable + "//tbody";
        
        //To locate table. 
        WebElement we = driver.findElement(By.xpath(xpathOfTable)); 
        //To locate rows of table. 
        List<WebElement> rowList = we.findElements(By.tagName("tr"));
         
        //To locate fields in each row.
        for(int row=0; row<rowList.size(); row++ ){
            List<WebElement> webElementList = rowList.get(row).findElements(By.tagName("td"));
            webTable.put(row, webElementList);
        }
        return webTable;
    }

}
