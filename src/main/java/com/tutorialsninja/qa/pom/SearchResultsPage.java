package com.tutorialsninja.qa.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy (xpath = "//p[text()='There is no product that matches the search criteria.']")
	private WebElement noProductFoundMessage;
	
	public SearchResultsPage(WebDriver driver) {
		
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean displayStatusOfValidHPProduct() {
		
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
		
	}
	
	public boolean displayStatusOfNoProductFoundTextMessage() {
		
		boolean displayStatus = noProductFoundMessage.isDisplayed();
		return displayStatus;
		
	}

}
