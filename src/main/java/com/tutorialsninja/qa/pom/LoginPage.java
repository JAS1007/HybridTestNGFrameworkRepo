package com.tutorialsninja.qa.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-email")
	private WebElement emailTextBox;
	
	@FindBy(id = "input-password")
	private WebElement passwordTextBox;
	
	@FindBy(xpath ="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath = "//*[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterTheEmail(String email) {
		
		emailTextBox.sendKeys(email);
		
	}
	
	public void enterThePassword(String password) {
		
		passwordTextBox.sendKeys(password);
		
	}
	
	public AccountPage clickOnLoginButton() {
		
		loginButton.click();
		return new AccountPage(driver);
		
	}
	
	public AccountPage login(String email, String password) {
		
		emailTextBox.sendKeys(email);
		passwordTextBox.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver); //can be used to optimize the code. Instead of calling 3 different methods, i can call just one method (check in Login.java file) 
		
	}
	
	public String retriveEmailPasswordNotMatchingWarningText() {
		
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
		
	}

}
