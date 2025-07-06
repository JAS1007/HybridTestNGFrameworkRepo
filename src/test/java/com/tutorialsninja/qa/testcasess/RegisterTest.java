package com.tutorialsninja.qa.testcasess;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pom.AccountSuccessPage;
import com.tutorialsninja.qa.pom.HomePage;
import com.tutorialsninja.qa.pom.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends BaseClass{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		
		super();
		
	}
	
	@BeforeMethod
	public void setUp() {
		
		driver = openTheBrowserAndNavigateToTheWebsiteURL(configProp.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifyRegisteringWithMandatoryFields() throws InterruptedException {
		
		registerPage.enterTheFirstName(testDataProp.getProperty("firstName"));
		registerPage.enterTheLastName(testDataProp.getProperty("lastName"));
		registerPage.enterTheEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTheTelephoneNumber(testDataProp.getProperty("telephoneNumber"));
		registerPage.enterThePassword(testDataProp.getProperty("password"));
		registerPage.enterTheConfirmPassword(testDataProp.getProperty("password"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		String actualSuccessMessage = accountSuccessPage.retriveAccountCraetionSuccessMessageText();
		Assert.assertEquals(actualSuccessMessage, testDataProp.getProperty("accountSuccessfullyCreatedHeading"), "Success Message not displayed");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringWithAllFields() {
		
		registerPage.enterTheFirstName(testDataProp.getProperty("firstName"));
		registerPage.enterTheLastName(testDataProp.getProperty("lastName"));
		registerPage.enterTheEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTheTelephoneNumber(testDataProp.getProperty("telephoneNumber"));
		registerPage.enterThePassword(testDataProp.getProperty("password"));
		registerPage.enterTheConfirmPassword(testDataProp.getProperty("password"));
		registerPage.selectSubscribeRadioButton();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		String actualSuccessMessage = accountSuccessPage.retriveAccountCraetionSuccessMessageText();
		Assert.assertEquals(actualSuccessMessage, testDataProp.getProperty("accountSuccessfullyCreatedHeading"), "Success Message not displayed");
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringWithExistingEmailId() {
		
		registerPage.enterTheFirstName(testDataProp.getProperty("firstName"));
		registerPage.enterTheLastName(testDataProp.getProperty("lastName"));
		registerPage.enterTheEmail(configProp.getProperty("validEmail"));
		registerPage.enterTheTelephoneNumber(testDataProp.getProperty("telephoneNumber"));
		registerPage.enterThePassword(testDataProp.getProperty("password"));
		registerPage.enterTheConfirmPassword(testDataProp.getProperty("password"));
		registerPage.selectSubscribeRadioButton();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		String actualWarningMessage = registerPage.retriveDuplicateEmailWarningMessage();
		Assert.assertTrue(actualWarningMessage.contains(testDataProp.getProperty("duplicateEmailWarning")), "Warning Message not displayed");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringWithoutFillingAnyDetails() {
		
		registerPage.clickOnContinueButton();
		
		String actualPrivayPolicyWarning = registerPage.retrivePrivacyPolicyWarningMessage();
		Assert.assertTrue(actualPrivayPolicyWarning.contains(testDataProp.getProperty("privacyPolicyWarning")), "Privacy Policy Warning Message not displayed");
		
		String actualFirstNameWarning = registerPage.retriveFirstNameWarningMessage();
		Assert.assertTrue(actualFirstNameWarning.contains(testDataProp.getProperty("firstNameWarning")), "First Name Warning Message not displayed");
		
		String actualLastNameWarning = registerPage.retriveLastNameWarningMessage();
		Assert.assertTrue(actualLastNameWarning.contains(testDataProp.getProperty("lastNameWarning")), "Last Name Warning Message not displayed");
		
		String actualEmailWarning = registerPage.retriveEmailWarningMessage();
		Assert.assertTrue(actualEmailWarning.contains(testDataProp.getProperty("emailWarning")), "Email Warning Message not displayed");
		
		String actualTelephoneWarning = registerPage.retriveTelephoneWarningMessage();
		Assert.assertTrue(actualTelephoneWarning.contains(testDataProp.getProperty("telephoneWarning")), "Telephone Warning Message not displayed");
		
		String actualPasswordWarning = registerPage.retrivePasswordWarningMessage();
		Assert.assertTrue(actualPasswordWarning.contains(testDataProp.getProperty("passwordWarning")), "Password Warning Message not displayed");
		
	}
	
}
