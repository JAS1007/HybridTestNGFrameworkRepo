package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReports;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
		
		extentReports = ExtentReporter.generateExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		testName = result.getName();
		extentTest = extentReports.createTest(testName);
		extentTest.log(Status.INFO, testName+" started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, testName+" got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		
			e.printStackTrace();
		}
		
		String destScreenshotPath = Utilities.captureScreenshot(driver, testName);
		
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		extentTest.info(result.getThrowable());
		extentTest.fail(testName+" got failed");
//		extentTest.log(Status.INFO,result.getThrowable() );
//		extentTest.log(Status.FAIL, testName+" got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.info(result.getThrowable());
		extentTest.skip(testName+" got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
	    
		extentReports.flush(); //if i don't add this line, all the information that i added before will not reflect in the report
		
		String pathOfEctentReports = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReports = new File(pathOfEctentReports);
		try {
			
			Desktop.getDesktop().browse(extentReports.toURI()); //to open the extent report automatically after completion of execution
			
		} catch (IOException e) {
	
			e.printStackTrace();
			
		}
	}
	
}
