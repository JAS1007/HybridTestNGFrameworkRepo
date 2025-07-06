package com.tutorialsninja.qa.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	private WebElement searchTextBox;
	
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//Actions
	public void clickOnMyAccount() {
		
		myAccountDropMenu.click();
		
	}
	
	public LoginPage selectLoginOption() {
		
		loginOption.click();
		return new LoginPage(driver);
		
	}
	
	public RegisterPage selectRegisterOption() {
		
		registerOption.click();
		return new RegisterPage(driver);
		
	}
	
	public void enterProductNameIntoSearchTextBox(String productName) {
		
		searchTextBox.sendKeys(productName);
		
	}
	
	public SearchResultsPage clickSearchButton() {
		
		searchButton.click();
		return new SearchResultsPage(driver);
		
	}
	
}
