package com.tutorialsninja.qa.testcasess;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pom.HomePage;
import com.tutorialsninja.qa.pom.SearchResultsPage;


//added comment

public class SearchTest extends BaseClass {
	
	public WebDriver driver; //if i don't make it public, it wont be passed inside the methods in MyListeners.java
	SearchResultsPage searchResultsPage;
	
    public SearchTest() {
		
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		
		driver = openTheBrowserAndNavigateToTheWebsiteURL(configProp.getProperty("browserName"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameIntoSearchTextBox(testDataProp.getProperty("validProduct"));
		searchResultsPage= homePage.clickSearchButton();
		
		Assert.assertTrue(searchResultsPage.displayStatusOfValidHPProduct(), "Product not found!!!");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameIntoSearchTextBox(testDataProp.getProperty("invalidProduct"));
		searchResultsPage= homePage.clickSearchButton();
		Assert.fail();
		//Assert.assertTrue(searchResultsPage.displayStatusOfNoProductFoundTextMessage(), "Product Not Found message not displayed");
		
	}
	
	@Test(priority=3, dependsOnMethods = "verifySearchWithInvalidProduct")
	public void verifySearchWithoutAnyProductName() {
		
		HomePage homePage = new HomePage(driver);
		searchResultsPage= homePage.clickSearchButton();
		
		Assert.assertTrue(searchResultsPage.displayStatusOfNoProductFoundTextMessage(), "Product Not Found message not displayed");
		
	}

}
