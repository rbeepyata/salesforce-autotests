package com.edmunds.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.epam.automation.core.ElementFactory;
import com.epam.automation.core.PageBase;
import com.epam.automation.core.element.Button;
import com.epam.automation.core.element.Element;

/**
 * Edmunds Automation
 * @author Raghunandan Beepyata
 * 
 */
public class PresalesOpportunityPage extends PageBase {
	
	public Button buttonPricingWizard = ElementFactory.getButton("Pricing Wizard", this); 
	public Element checkboxEASPrecisionSearchMultiStore = ElementFactory.getElement("EAS Precision Search - Multi-store checkbox", this); 
	public Element checkboxPDPHomeMultiStore = ElementFactory.getElement("PDP Home - Multi-store checkbox", this); 
	public Element checkboxUsedInventoryMultiStore = ElementFactory.getElement("Used Inventory - Multi-store checkbox", this); 
	public Element checkboxPricePromiseMultiStore = ElementFactory.getElement("Price Promise - Multi-store checkbox", this); 
	public Element checkboxUsedPlusMultiStore = ElementFactory.getElement("UsedPlus - Multi-store checkbox", this); 
	public Element checkboxMyAppraiseTradeInMultiStore = ElementFactory.getElement("My Appraise Trade-In - Multi-store checkbox", this); 
	public Element checkboxReviewsWidgetMultiStore = ElementFactory.getElement("Reviews Widget - Multi-store checkbox", this); 
	public Element checkboxPricePromiseLeaseMultiStore = ElementFactory.getElement("Price Promise - Lease - Multi-store checkbox", this); 
	public Element checkboxCarCodeTextMultiStore = ElementFactory.getElement("CarCode Text - Multi-store checkbox", this); 
	public Element checkboxMyAppraiseMultiStore = ElementFactory.getElement("My Appraise - Multi-store checkbox", this); 
	public Element checkboxEdmundsExpressMultiStore = ElementFactory.getElement("Edmunds Express - Multi-store checkbox", this); 
	public Button buttonContinueToPricingWizard = ElementFactory.getButton("Continue to Pricing Wizard", this);
	public Button buttonSellableProductsSave = ElementFactory.getButton("Sellable Products - Save", this);
	
	public void includeAllSellableProductsOnPSO() {
		
		actions.waitUnconditionallyFor(5);
		WebDriver driver = (WebDriver)actions.getTool();
		List<WebElement> listOfUnchecked_chkboxIncludeOnPSO  = driver
				.findElements(By
						.xpath("//th[contains(text(),'Include on PSO')]/ancestor::table[@class='list']//tbody//tr[not (contains(@style,'display:none'))]//td[1]//img[not (contains(@class,'inlineEdit')) and contains(@title,'Not Checked')]"));
		
		for (WebElement chkboxIncludeOnPSO : listOfUnchecked_chkboxIncludeOnPSO) {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", chkboxIncludeOnPSO);
			new Actions(driver).moveToElement(chkboxIncludeOnPSO)
				.click().build().perform();
			
			actions.waitUnconditionallyFor(2);
			WebElement we = driver.findElement(By
					.xpath("//th[contains(text(),'Include on PSO')]/ancestor::table[@class='list']//tbody//tr[not (contains(@style,'display:none'))]//td[1]//img[not (contains(@class,'inlineEdit')) and contains(@title,'Not Checked')]/../following-sibling::div//input"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", we); 
			new Actions(driver).moveToElement(we).click().build().perform();
		}
		actions.scrollIntoView(buttonSellableProductsSave.getElementLocator(), buttonSellableProductsSave.getElementName());
		buttonSellableProductsSave.click();
		buttonSellableProductsSave.waitForItToBeVisible();
		
	}
	
	

}
