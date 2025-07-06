package com.tutorialsninja.qa.testcasess;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pom.AccountPage;
import com.tutorialsninja.qa.pom.HomePage;
import com.tutorialsninja.qa.pom.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

import org.openqa.selenium.WebDriver;

public class LoginTest extends BaseClass {
	
	public WebDriver driver;
	LoginPage loginPage;
	
	public LoginTest() {
		
		super();  // runs the code related to properties file in the BaseClass
		
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = openTheBrowserAndNavigateToTheWebsiteURL(configProp.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption(); //In every test method I am creating the object of LoginPage, in order to avoid it i will return the object of LoginPage in selectLoginOption method(which is present in HomePage.java file). 
		                              //Why am I creating the object of LoginPage in selectLoginOption method? Because after clicking on Login, I will be navigated to the Login Page
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1,dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		AccountPage accountPage = loginPage.login(email, password); //if there is confusion, refer line number 35
//		loginPage.enterTheEmail(email);
//		loginPage.enterThePassword(password);
//		AccountPage accountPage = loginPage.clickOnLoginButton(); // code optimization
		
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Edit your account information not found!");
		
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data = Utilities.getDataFromExcelFile("Login"); //passing the sheet name as an argument
		return data;
		
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		loginPage.enterTheEmail(Utilities.generateEmailWithTimeStamp());
		loginPage.enterThePassword(testDataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
//		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningText();
//		String expectedWarningMessage = testDataProp.getProperty("emailPasswordNotMatchWarning");
//		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message not displayed");
		
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningText().contains(testDataProp.getProperty("emailPasswordNotMatchWarning")), "Expected Warning Message not displayed");  //code optimization
				
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		loginPage.enterTheEmail(Utilities.generateEmailWithTimeStamp());
		loginPage.enterThePassword(configProp.getProperty("validPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = testDataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message not displayed");
				
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterTheEmail(configProp.getProperty("validEmail"));
		loginPage.enterThePassword(testDataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = testDataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message not displayed");
				
	}
	
	@Test(priority=5)
	public void verifyLoginWithEmptyEmailAndPassword() throws InterruptedException {
		
		loginPage.enterTheEmail("");
		loginPage.enterThePassword("");
		loginPage.clickOnLoginButton();
		
		String actualWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningText();
		String expectedWarningMessage = testDataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message not displayed");
				
	}

}
