package com.tutorialsninja.qa.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "input-email")
	private WebElement emailTextBox;
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneTextBox;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(name = "agree")
	private WebElement privacyPloicyCheckBox;
	
	@FindBy(xpath = "//*[@class='btn btn-primary']")
	private WebElement continueButton;
	
	@FindBy(xpath = "(//input[@value='1'])[2]")
	private WebElement subscribeRadioButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarningMessage;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningMessage;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningMessage;
	
	public RegisterPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterTheFirstName(String firstName) {
		
		firstNameTextBox.sendKeys(firstName);
		
	}
	
	public void enterTheLastName(String lastName) {
		
		lastNameTextBox.sendKeys(lastName);
		
	}
	
    public void enterTheEmail(String email) {
		
		emailTextBox.sendKeys(email);
		
	}
    
    public void enterTheTelephoneNumber(String telephoneNumber) {
    	
    	telephoneTextBox.sendKeys(telephoneNumber);
    }
    
    public void enterThePassword(String password) {
    	
    	passwordTextBox.sendKeys(password);
    	
    }
    
    public void enterTheConfirmPassword(String password) {
    	
    	confirmPasswordTextBox.sendKeys(password);
    	
    }
    
    public void selectPrivacyPolicy() {
    	
    	privacyPloicyCheckBox.click();
    	
    }
    
    public AccountSuccessPage clickOnContinueButton() {
    	
    	continueButton.click();
    	return new AccountSuccessPage(driver);
    	
    }
    
    public void selectSubscribeRadioButton() {
    	
    	subscribeRadioButton.click();
    	
    }
    
    public String retriveDuplicateEmailWarningMessage() {
    	
    	String warningText = duplicateEmailWarningMessage.getText();
    	return warningText;
    	
    }
    
    public String retrivePrivacyPolicyWarningMessage() {
    	
    	String warningText = privacyPolicyWarningMessage.getText();
    	return warningText;
    	
    }
    
    public String retriveFirstNameWarningMessage() {
    	
    	String warningText = firstNameWarningMessage.getText();
    	return warningText;
    	
    }
    
    public String retriveLastNameWarningMessage() {
    	
    	String warningText = lastNameWarningMessage.getText();
    	return warningText;
    	
    }
    
    public String retriveEmailWarningMessage() {
    	
    	String warningText = emailWarningMessage.getText();
    	return warningText;
    	
    }
    
    public String retriveTelephoneWarningMessage() {
    	
    	String warningText = telephoneWarningMessage.getText();
    	return warningText;
    	
    }
    
    public String retrivePasswordWarningMessage() {
    	
    	String warningText = passwordWarningMessage.getText();
    	return warningText;
    	
    }
    
}
