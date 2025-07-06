package com.tutorialsninja.qa.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	private WebElement accountCreationSuccessMessage;
	
    public AccountSuccessPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
    
    public String retriveAccountCraetionSuccessMessageText() {
    	
    	String successMessage = accountCreationSuccessMessage.getText();
    	return successMessage;
    	
    }

}
